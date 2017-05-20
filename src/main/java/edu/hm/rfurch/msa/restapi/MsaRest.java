package edu.hm.rfurch.msa.restapi;

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
    public Response newUser() {
        return null;
    }

    @GET
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getToken() {
        return null;
    }

    @HEAD
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    public Response isTokenValid() {
        return null;
    }

    @DELETE
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout() {
        return null;
    }
}
