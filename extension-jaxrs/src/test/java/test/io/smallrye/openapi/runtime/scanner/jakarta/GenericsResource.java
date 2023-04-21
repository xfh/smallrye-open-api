package test.io.smallrye.openapi.runtime.scanner.jakarta;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path(value = "/v1/generics")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class GenericsResource extends BaseResource<Generics> {

    @POST
    @RequestBody(content = @Content(schema = @Schema(implementation = Generics.class)))
    public Result<Generics> post(Generics deployment) {
        return super.post1(deployment);
    }
}
