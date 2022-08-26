package gl.coco.notification.specification.recipient;

import gl.coco.notification.model.recipient.Recipient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import static gl.coco.notification.model.recipient.Recipient.Fields.*;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;
import static org.springframework.data.domain.ExampleMatcher.matching;

@RequiredArgsConstructor(access = PRIVATE)
public class EmailIs implements Example<Recipient> {

    private final String emailToMatch;

    @Override
    public Recipient getProbe() {
        return Recipient.withoutId(this.emailToMatch);
    }

    @Override
    public ExampleMatcher getMatcher() {
        return matching().withMatcher(email, exact())
                .withIgnorePaths(firstname, lastname);
    }

    public static Example<Recipient> emailIs(String emailToMatch) {
        return new EmailIs(emailToMatch);
    }
}
