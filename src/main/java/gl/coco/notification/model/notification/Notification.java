package gl.coco.notification.model.notification;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static lombok.AccessLevel.PRIVATE;

@Document
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = PRIVATE)
@FieldNameConstants
public class Notification {

    @Id
    @Getter
    private String id;
    @Getter
    private String title;
    @Getter
    private String content;
    @Getter
    private Boolean isRead;
    @Getter
    private NotificationType type;
    @Getter
    private String recipientId;

    @Getter
    private Long createdAt;
    @Getter
    private Long updatedAt;

    public static Notification withoutId(
            String title, String content, NotificationType type, String recipientId) {
        return new Notification(null, title, content, FALSE, type, recipientId, null, null);
    }

    public void read() {
        this.isRead = TRUE;
    }

    public boolean isRead() {
        return this.isRead.equals(TRUE);
    }
}
