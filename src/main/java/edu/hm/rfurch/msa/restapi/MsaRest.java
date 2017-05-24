package edu.hm.rfurch.msa.restapi;

import edu.hm.rfurch.msa.logic.AuthService;
import edu.hm.rfurch.msa.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by ms1511 on 20.05.17.
 */
@Path("/auth")
public class MsaRest {

    @POST
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newUser(User user) {
    	return new AuthService().createNewUser(user.getName(), user.getPassword(), user.isAdmin()).getResponse();
    }

    @PUT
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToken(UserTransferObject user) {
    	return new AuthService().getToken(user.getName(), user.getPassword()).getResponse();
    }

    @HEAD
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    public Response isTokenValid(TokenTransferObject token) {
    	return new AuthService().proofToken(token.getToken(), token.isAdmin()).getResponse();
    }

    @DELETE
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(UserTransferObject user) {
    	return new AuthService().endSession(user.getName(), user.getPassword()).getResponse();
    }
}
