package edu.hm.rfurch.shareit.logic;


import org.eclipse.jetty.server.Response;
import org.json.JSONObject;

/**
 * Created by FURCH on 19/04/2017.
 */
public enum MediaServiceResult {
    OK(Response.SC_OK, "OK"),
    Created(Response.SC_CREATED,"Created"),
    NoContent(Response.SC_NO_CONTENT, "No Content"), //After delete
    BadRequest(Response.SC_BAD_REQUEST, "Bad Request"), // Missing information or data
    Unauthorized(Response.SC_UNAUTHORIZED, "Unauthorized"),
    NotFound(Response.SC_NOT_FOUND, "Not Found"),
    NotAcceptable(Response.SC_NOT_ACCEPTABLE, "NotAcceptable"),
    Conflict(Response.SC_CONFLICT,"Conflict"),
    IamATeapot(418, "I'm a teapot"),
    InternalServerError(Response.SC_INTERNAL_SERVER_ERROR, "Internal Server Error"),
    NotImplemented(Response.SC_NOT_IMPLEMENTED,"Not implemented"),
    ServiceUnavailable(Response.SC_SERVICE_UNAVAILABLE, "Service unavailable");




    private final int code;
    private final String status;
    MediaServiceResult(int code, String status){
        this.code = code;
        this.status = status;
    }

    public int getCode(){
        return this.code;
    }
    public String getStatus(){
        return this.status;
    }

    public javax.ws.rs.core.Response getResponse(){

        return javax.ws.rs.core.Response.status(this.getCode())
                .entity(new JSONObject()
                .put("status", this.getCode())
                .put("message", this.getStatus())
                .put("help", "http://lmgtfy.com/?q=http+statuscode+"+this.getCode()))
                .build();
    }




}
