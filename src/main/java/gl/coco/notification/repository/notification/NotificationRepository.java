package gl.coco.notification.repository.notification;

import gl.coco.notification.model.notification.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface NotificationRepository
        extends ReactiveMongoRepository<Notification, String> {

    Flux<Notification> findByRecipientId(String recipientId, Pageable pageable);
}
