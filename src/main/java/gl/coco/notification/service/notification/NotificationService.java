package gl.coco.notification.service.notification;

import gl.coco.notification.exception.notification.NotificationNotFoundException;
import gl.coco.notification.model.notification.Notification;
import gl.coco.notification.model.recipient.Recipient;
import gl.coco.notification.repository.notification.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static gl.coco.notification.specification.notification.IsUnread.isUnread;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public Mono<Notification> readOne(String id) {
        return notificationRepository.findById(id)
                .flatMap(notification -> {
                    if (notification.isRead()) return Mono.just(notification);
                    else {
                        notification.read();
                        return notificationRepository.save(notification);
                    }
                })
                .switchIfEmpty(Mono.error(NotificationNotFoundException::new));
    }

    public Flux<Notification> getAll(Mono<Recipient> recipientMono, Pageable pageable) {
        return recipientMono
                .flatMapMany(recipient ->
                        notificationRepository.findByRecipientId(recipient.getId(), pageable));
    }

    public Mono<Long> getUnreadCount(Mono<Recipient> recipientMono) {
        return recipientMono
                .flatMap(recipient ->
                        notificationRepository.count(isUnread(recipient)));
    }
}
