package test.io.smallrye.openapi.runtime.scanner.jakarta;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(type = SchemaType.STRING, format = "entity-id", implementation = String.class)
public class ID<T> {

    private Long id;

    private Class<T> entityClazz;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Class<T> getEntityClazz() {
        return entityClazz;
    }

    public void setEntityClazz(final Class<T> entityClazz) {
        this.entityClazz = entityClazz;
    }
}
