package gl.coco.notification.model.recipient;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Document
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = PRIVATE)
@FieldNameConstants
public class Recipient {

    @Id
    @Getter
    private String id;
    @Getter
    private String email;
    @Getter
    private String firstname;
    @Getter
    private String lastname;

    @Getter
    private Long createdAt;
    @Getter
    private Long updatedAt;

    public static Recipient withoutId(String email) {
        return new Recipient(null, email, EMPTY, EMPTY, null, null);
    }

    public static Recipient withoutId(String email, String firstname, String lastname) {
        return new Recipient(null, email, firstname, lastname, null, null);
    }

    public static Recipient withId(String id, String email, String firstname, String lastname) {
        return new Recipient(id, email, firstname, lastname, null, null);
    }
}
