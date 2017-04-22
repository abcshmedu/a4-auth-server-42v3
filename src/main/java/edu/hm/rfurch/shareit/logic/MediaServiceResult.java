package edu.hm.rfurch.shareit.logic;


import edu.hm.rfurch.shareit.model.IMedium;
import org.eclipse.jetty.server.Response;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by FURCH on 19/04/2017.
 */
public enum MediaServiceResult {
    OK(Response.SC_OK, "OK"),
    Created(Response.SC_CREATED,"Created"),
    NoContent(Response.SC_NO_CONTENT, "No Content"), //After delete
    BadRequest(Response.SC_BAD_REQUEST, "Bad Request"); // Missing information or data
    /**
    IamATeapot(418, "I'm a teapot"),
    Unauthorized(Response.SC_UNAUTHORIZED, "Unauthorized"),
    NotFound(Response.SC_NOT_FOUND, "Not Found"),
    NotAcceptable(Response.SC_NOT_ACCEPTABLE, "NotAcceptable"),
    Conflict(Response.SC_CONFLICT,"Conflict"),
    InternalServerError(Response.SC_INTERNAL_SERVER_ERROR, "Internal Server Error"),
    NotImplemented(Response.SC_NOT_IMPLEMENTED,"Not implemented"),
    ServiceUnavailable(Response.SC_SERVICE_UNAVAILABLE, "Service unavailable");
**/



    private final int code;
    private final String status;
    private Collection<Object> responseData;
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
    public  MediaServiceResult setResponseData( Collection<Object> data){
        this.responseData = data;
        return this;
    }
    public MediaServiceResult setResponseData(Object data){
        this.setResponseData(new ArrayList<>());
        this.getResponseData().add(data);
        return this;
    }

    public Collection<Object> getResponseData(){
        return this.responseData;
    }

    public javax.ws.rs.core.Response getResponse(){

        return javax.ws.rs.core.Response.status(this.getCode())
                .entity(new JSONObject()
                .put("status", this.getCode())
                .put("message", this.getStatus())
                .put("data-length", this.getResponseData() != null?this.getResponseData().size():-1)
                .put("data", this.getResponseData() != null?this.getResponseData().size()>0?this.getResponseData():"":"error") // TODO json serilize
                .put("help", "http://lmgtfy.com/?q=http+statuscode+"+this.getCode()))
                .build();
    }




}
