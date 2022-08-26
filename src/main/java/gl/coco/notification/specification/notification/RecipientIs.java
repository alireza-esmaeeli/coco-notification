package gl.coco.notification.specification.notification;

import gl.coco.notification.model.notification.Notification;
import gl.coco.notification.model.recipient.Recipient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import static gl.coco.notification.model.notification.Notification.Fields.isRead;
import static gl.coco.notification.model.notification.Notification.Fields.recipientId;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;
import static org.springframework.data.domain.ExampleMatcher.matching;

@RequiredArgsConstructor(access = PRIVATE)
public class RecipientIs implements Example<Notification> {

    private final Recipient recipientToMatch;

    @Override
    public Notification getProbe() {
        return Notification.withoutId(null, null, null, this.recipientToMatch.getId());
    }

    @Override
    public ExampleMatcher getMatcher() {
        return matching().withMatcher(recipientId, exact())
                .withIgnorePaths(isRead);
    }

    public static Example<Notification> recipientIs(Recipient recipient) {
        return new RecipientIs(recipient);
    }
}
