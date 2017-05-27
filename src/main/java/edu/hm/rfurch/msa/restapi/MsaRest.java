package edu.hm.rfurch.msa.restapi;

import edu.hm.rfurch.msa.logic.AuthService;
import edu.hm.rfurch.msa.model.User;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Rest Interface of MSA.
 * @author Raphael Furch, rfurch@hm.edu / Michael Schmid, m.schmid@hm.edu / Elias Porcio
 */
@Path("/auth")
public class MsaRest {

	/**
	 * Service to create a new user.
	 * @param user to create
	 * @return Response with the result state of the user creation 
	 */
    @POST
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newUser(User user) {
    	return new AuthService().createNewUser(user.getName(), user.getPassword(), user.isAdmin()).getResponse();
    }

    /**
     * Service to get a token for a user.
     * @param user which is requesting the token
     * @return Response with the result state and a token, if the user is valid.
     */
    @PUT
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToken(UserTransferObject user) {
    	return new AuthService().getToken(user.getName(), user.getPassword()).getResponse();
    }

    /**
     * Check if the token is valid and the owner of the token has the needed permissions.
     * @param token to check
     * @param admin boolean if admin rights are needed
     * @return Response with the result of the token validation.
     */
    @GET
    @Path("/token/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response isTokenValid(@PathParam("token") String token, @QueryParam("admin") String admin) {
    	final boolean isAdmin = admin != null && "true".equals(admin) ? true : false;
    	return new AuthService().proofToken(token, isAdmin).getResponse();
    }

    /**
     * Logout a user by deleting the current token of the user.
     * @param user to logout
     * @return Response with the result state of the logout
     */
    @DELETE
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(UserTransferObject user) {
    	return new AuthService().endSession(user.getName(), user.getPassword()).getResponse();
    }
}
