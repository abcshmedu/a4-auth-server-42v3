package edu.hm.rfurch.msa.restapi;

import edu.hm.rfurch.msa.data.Resource;
import edu.hm.rfurch.msa.data.ResourceManager;
import edu.hm.rfurch.msa.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by ms1511 on 20.05.17.
 */
@Path("/msa")
public class MsaRest {

    @POST
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newUser(User user) {
        Response result = null;
        if(ResourceManager.dataAccess().exists(user.getName())) {
            result = null;
        } else {
            ResourceManager.dataAccess().addUser(user);
            result = null;
        }
        return result;
    }

    @GET
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToken(UserTransferObject user) {

        Response result = null;
        if (ResourceManager.dataAccess().exists(user.getName(), user.getPassword())) {
            //getToken user, password
            //ResourceManager.dataAccess().getToken(user);
        } else {
            result = null;
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
            result = null;
        }
        return result;
    }
}
