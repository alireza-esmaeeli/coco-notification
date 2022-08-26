package gl.coco.notification.controller.notification;

import gl.coco.notification.dto.notification.NotificationDto;
import gl.coco.notification.service.notification.NotificationService;
import gl.coco.notification.service.recipient.RecipientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    private final RecipientService recipientService;

    private final ModelMapper modelMapper;

    @GetMapping("{id}")
    public Mono<NotificationDto> readOne(@PathVariable String id) {
        return notificationService.readOne(id)
                .map(notification ->
                        modelMapper.map(notification, NotificationDto.class));
    }

    @GetMapping("unreadCount")
    public Mono<Long> countUnread(@RequestParam String email) {
        return notificationService.getUnreadCount(recipientService.get(email));
    }

    @GetMapping
    public Flux<NotificationDto> getAll(@RequestParam String email, Pageable pageable) {
        return notificationService.getAll(recipientService.get(email), pageable)
                .map(notification -> modelMapper.map(notification, NotificationDto.class));
    }
}
