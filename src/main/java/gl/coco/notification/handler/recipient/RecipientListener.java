package gl.coco.notification.handler.recipient;

import gl.coco.notification.model.recipient.Recipient;
import lombok.SneakyThrows;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Objects;

import static gl.coco.notification.model.recipient.Recipient.Fields.*;
import static java.time.Instant.now;
import static org.springframework.util.ReflectionUtils.findField;
import static org.springframework.util.ReflectionUtils.makeAccessible;

@Component
public class RecipientListener
        extends AbstractMongoEventListener<Recipient> {

    @SneakyThrows
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Recipient> event) {
        var recipient = event.getSource();

        var idField = findField(recipient.getClass(), id);
        makeAccessible(idField);
        var id = idField.get(recipient);

        Field field;
        if (Objects.isNull(id)) {
            field = findField(recipient.getClass(), createdAt);
        } else {
            field = findField(recipient.getClass(), updatedAt);
        }
        makeAccessible(field);
        field.set(recipient, now().toEpochMilli());
    }
}
