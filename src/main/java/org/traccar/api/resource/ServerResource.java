package org.traccar.api.resource;

import java.sql.SQLException;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.traccar.Context;
import org.traccar.api.BaseResource;
import org.traccar.helper.LogAction;
import org.traccar.model.BaseModel;
import org.traccar.model.Server;


@Path("server")
@Produces({"application/json"})
@Consumes({"application/json"})
public class ServerResource
        extends BaseResource {
    @PermitAll
    @GET
    public Server get() throws SQLException {
        return Context.getPermissionsManager().getServer();
    }

    @PUT
    public Response update(Server entity) throws SQLException {
        Context.getPermissionsManager().checkAdmin(getUserId());
        Context.getPermissionsManager().updateServer(entity);
        LogAction.edit(getUserId(), (BaseModel) entity);
        return Response.ok(entity).build();
    }

    @Path("geocode")
    @GET
    public String geocode(@QueryParam("latitude") double latitude, @QueryParam("longitude") double longitude) {
        if (Context.getGeocoder() != null) {
            return Context.getGeocoder().getAddress(latitude, longitude, null);
        }
        throw new RuntimeException("Reverse geocoding is not enabled");
    }
}


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\resource\ServerResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */