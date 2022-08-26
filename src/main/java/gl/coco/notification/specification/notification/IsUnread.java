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
import static org.springframework.data.domain.ExampleMatcher.matchingAll;

@RequiredArgsConstructor(access = PRIVATE)
public class IsUnread implements Example<Notification> {

    private final Recipient recipient;

    @Override
    public Notification getProbe() {
        return Notification.withoutId(null, null, null, this.recipient.getId());
    }

    @Override
    public ExampleMatcher getMatcher() {
        return matchingAll()
                .withMatcher(recipientId, exact())
                .withMatcher(isRead, exact());
    }

    public static Example<Notification> isUnread(Recipient recipient) {
        return new IsUnread(recipient);
    }
}
