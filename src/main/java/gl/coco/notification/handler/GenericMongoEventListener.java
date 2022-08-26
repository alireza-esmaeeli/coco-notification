package gl.coco.notification.handler;

import lombok.SneakyThrows;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import static java.time.Instant.now;
import static org.springframework.util.ReflectionUtils.*;

public class GenericMongoEventListener
        extends AbstractMongoEventListener<Object> {

    @SneakyThrows
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        var document = event.getSource();

        var idField = findField(document.getClass(), "id");
        if (idField != null) {
            makeAccessible(idField);
            var id = idField.get(document);

            if (id == null)
                doWithFields(
                        document.getClass(),
                        documentField -> {
                            makeAccessible(documentField);
                            documentField.set(document, now().toEpochMilli());
                        },
                        documentField -> documentField.getName().equals("createdAt"));

            doWithFields(
                    document.getClass(),
                    documentField -> {
                        makeAccessible(documentField);
                        documentField.set(document, now().toEpochMilli());
                    },
                    documentField -> documentField.getName().equals("updatedAt"));
        }
    }
}
