package gl.coco.notification.service.recipient;

import gl.coco.notification.exception.recipient.RecipientNotFoundException;
import gl.coco.notification.model.recipient.Recipient;
import gl.coco.notification.repository.recipient.RecipientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static gl.coco.notification.specification.recipient.EmailIs.emailIs;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipientService {

    private final RecipientRepository recipientRepository;

    public Mono<Recipient> get(String email) {
        return recipientRepository.findOne(emailIs(email))
                .switchIfEmpty(Mono.error(RecipientNotFoundException::new));
    }
}
