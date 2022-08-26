package gl.coco.notification.dto.notification;

import gl.coco.notification.model.notification.NotificationType;
import lombok.NoArgsConstructor;
import lombok.Value;

import static lombok.AccessLevel.PROTECTED;

@Value
@NoArgsConstructor(force = true, access = PROTECTED)
public class NotificationDto {
    String id;
    String title;
    String content;
    Boolean isRead;
    NotificationType type;
    Long createdAt;
    Long updatedAt;
}
