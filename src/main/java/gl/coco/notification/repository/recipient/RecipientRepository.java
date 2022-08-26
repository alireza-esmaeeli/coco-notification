package gl.coco.notification.repository.recipient;

import gl.coco.notification.model.recipient.Recipient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipientRepository
        extends ReactiveMongoRepository<Recipient, String> {
}
