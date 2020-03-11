package io.smallrye.openapi.runtime.reader;

import java.util.Iterator;

import org.eclipse.microprofile.openapi.models.media.Content;
import org.eclipse.microprofile.openapi.models.media.MediaType;
import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.JsonNode;

import io.smallrye.openapi.api.constants.OpenApiConstants;
import io.smallrye.openapi.api.models.media.ContentImpl;
import io.smallrye.openapi.runtime.scanner.spi.AnnotationScannerContext;
import io.smallrye.openapi.runtime.util.JandexUtil;

/**
 * Reading the Content object annotation and json
 * 
 * @see https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.3.md#mediaTypeObject
 * 
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 * @author Eric Wittmann (eric.wittmann@gmail.com)
 */
public class ContentReader {
    private static final Logger LOG = Logger.getLogger(ContentReader.class);

    private ContentReader() {
    }

    /**
     * Reads a single Content annotation into a model. The value in this case is an array of
     * Content annotations.
     * 
     * @param context the scanning context
     * @param annotationValue the {@literal @}Content annotation
     * @param direction the content direction
     * @return Content model
     */
    public static Content readContent(final AnnotationScannerContext context,
            final AnnotationValue annotationValue,
            final ContentDirection direction) {

        if (annotationValue == null) {
            return null;
        }
        LOG.debug("Processing a single @Content annotation.");
        Content content = new ContentImpl();
        AnnotationInstance[] nestedArray = annotationValue.asNestedArray();
        for (AnnotationInstance nested : nestedArray) {
            String contentType = JandexUtil.stringValue(nested, OpenApiConstants.PROP_MEDIA_TYPE);
            MediaType mediaTypeModel = MediaTypeReader.readMediaType(context, nested);
            if (contentType == null) {
                // If the content type is not provided in the @Content annotation, then
                // we assume it applies to all the jax-rs method's @Consumes or @Produces
                String[] mimeTypes = {};
                if (direction == ContentDirection.Input && CurrentContentTypes.getCurrentConsumes() != null) {
                    mimeTypes = CurrentContentTypes.getCurrentConsumes();
                }
                if (direction == ContentDirection.Output && CurrentContentTypes.getCurrentProduces() != null) {
                    mimeTypes = CurrentContentTypes.getCurrentProduces();
                }
                if (direction == ContentDirection.Parameter) {
                    mimeTypes = OpenApiConstants.DEFAULT_MEDIA_TYPES.get();
                }
                for (String mimeType : mimeTypes) {
                    content.addMediaType(mimeType, mediaTypeModel);
                }
            } else {
                content.addMediaType(contentType, mediaTypeModel);
            }
        }
        return content;
    }

    /**
     * Reads a {@link Content} OpenAPI node.
     * 
     * @param node the json node
     * @return Content model
     */
    public static Content readContent(final JsonNode node) {
        if (node == null || !node.isObject()) {
            return null;
        }
        Content content = new ContentImpl();
        for (Iterator<String> fieldNames = node.fieldNames(); fieldNames.hasNext();) {
            String fieldName = fieldNames.next();
            content.addMediaType(fieldName, MediaTypeReader.readMediaType(node.get(fieldName)));
        }
        return content;
    }

}
