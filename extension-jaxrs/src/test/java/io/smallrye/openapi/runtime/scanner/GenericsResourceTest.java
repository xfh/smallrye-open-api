package io.smallrye.openapi.runtime.scanner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.eclipse.microprofile.openapi.models.OpenAPI;
import org.jboss.jandex.Index;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import test.io.smallrye.openapi.runtime.scanner.jakarta.*;

public class GenericsResourceTest extends IndexScannerTestBase {

    @Test
    void testMultiGenericsApplication() throws IOException, JSONException {
        Index i = indexOf(
                BaseModel.class,
                GenericsResource.class,
                Generics.class,
                OpenAPIConfig.class,
                Result.class,
                ResultList.class,
                ID.class,
                Set.class,
                List.class);

        OpenApiAnnotationScanner scanner = new OpenApiAnnotationScanner(dynamicConfig(new HashMap<>()), i);
        OpenAPI result = scanner.scan();
        printToConsole(result);
        assertJsonEquals("resource.generics.json", result);
    }
}
