package edu.hm.rfurch.msa.restapi;

import edu.hm.rfurch.msa.data.Resource;
import edu.hm.rfurch.msa.data.ResourceManager;
import edu.hm.rfurch.msa.model.User;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ms1511 on 20.05.17.
 */
@Path("/auth")
public class MsaRest {

    @POST
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newUser(User user) {
        Response result = null;
        if(ResourceManager.dataAccess().exists(user.getName())) {
            result = reply(409, "CONFLICT", new HashMap<>());
        } else {
            ResourceManager.dataAccess().addUser(user);
            result = reply(200, "OK", new HashMap<>());
        }
        return result;
    }

    @PUT
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToken(UserTransferObject user) {

        Response result = null;
        if (ResourceManager.dataAccess().exists(user.getName(), user.getPassword())) {
            //getToken user, password
            //ResourceManager.dataAccess().getToken(user);
            result = reply(200, "OK", new HashMap<>());
        } else {
            result = reply(401, "UNAUTHORIZED", new HashMap<>());
        }
        return result;
    }

    @HEAD
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    public Response isTokenValid(TokenTransferObject token) {
        //getUser token
        ResourceManager.dataAccess().getUser(token.getToken());
        String tokenString = token.getToken();
        return null;
    }

    @DELETE
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(UserTransferObject user) {
        Response result = null;
        if (ResourceManager.dataAccess().exists(user.getName(), user.getPassword())) {
            //ResourceManager.dataAccess().getToken(user);
            //getToken user, password
            //delToken token
        } else {
            result = reply(401, "UNAUTHORIZED", new HashMap<>());
        }
        return result;
    }


    private Response reply(int code, String message, Map<String, String> data) {
        return Response.status(code)
                .entity(new JSONObject()
                        .put("status", code)
                        .put("message", message)
                        .put("data", data)
                        .toString())
                .build();
    }
}
