package gl.coco.notification.handler.notification;

import gl.coco.notification.model.notification.Notification;
import lombok.SneakyThrows;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Objects;

import static gl.coco.notification.model.notification.Notification.Fields.*;
import static java.time.Instant.now;
import static org.springframework.util.ReflectionUtils.findField;
import static org.springframework.util.ReflectionUtils.makeAccessible;

@Component
public class NotificationListener
        extends AbstractMongoEventListener<Notification> {

    @SneakyThrows
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Notification> event) {
        var notification = event.getSource();

        var idField = findField(notification.getClass(), id);
        makeAccessible(idField);
        var id = idField.get(notification);

        Field field;
        if (Objects.isNull(id)) {
            field = findField(notification.getClass(), createdAt);
        } else {
            field = findField(notification.getClass(), updatedAt);
        }
        makeAccessible(field);
        field.set(notification, now().toEpochMilli());
    }
}