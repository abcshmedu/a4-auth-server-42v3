package edu.hm.rfurch.shareit.logic;


import edu.hm.rfurch.shareit.model.IMedium;
import org.eclipse.jetty.server.Response;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * @author Raphael Furch, rfurch@hm.edu / Michael Schmid, m.schmid@hm.edu
 *
 */
public enum MediaServiceResult {
    OK(Response.SC_OK, "OK"),
    Created(Response.SC_CREATED, "Created"),
    NoContent(Response.SC_NO_CONTENT, "No Content"), //After delete
    BadRequest(Response.SC_BAD_REQUEST, "Bad Request"), // Missing information or data
    IamATeapot(418, "I'm a teapot");
/* Unauthorized(Response.SC_UNAUTHORIZED, "Unauthorized"),
    NotFound(Response.SC_NOT_FOUND, "Not Found"),
    NotAcceptable(Response.SC_NOT_ACCEPTABLE, "NotAcceptable"),
    Conflict(Response.SC_CONFLICT,"Conflict"),
    InternalServerError(Response.SC_INTERNAL_SERVER_ERROR, "Internal Server Error"),
    NotImplemented(Response.SC_NOT_IMPLEMENTED,"Not implemented"),
    ServiceUnavailable(Response.SC_SERVICE_UNAVAILABLE, "Service unavailable");
*/


    private final int code;
    private final String status;
    private Collection<IMedium> responseData;
    
    /**
     * Ctor to create a MediaServiceResult.
     * @param code of the result
     * @param status of the result
     */
    MediaServiceResult(int code, String status) {
        this.code = code;
        this.status = status;
    }

    /**
     * Getter of the code.
     * @return code of the result
     */
    public int getCode() {
        return this.code;
    }
    
    /**
     * Getter of the status.
     * @return status of result
     */
    public String getStatus() {
        return this.status;
    }
    
    /**
     * Add list of medium to result.
     * @param data list of mediums
     * @return result
     */
    public MediaServiceResult setResponseData(Collection<IMedium> data) {
        this.responseData = data;
        return this;
    }
    
    /**
     * Add medium to result.
     * @param data the medium
     * @return result
     */
    public MediaServiceResult setResponseData(IMedium data) {
        Collection<IMedium> datas = new ArrayList<>();
        datas.add(data);
        return this.setResponseData(datas);
    }

    /**
     * Getter of the medium list.
     * @return collection of mediums
     */
    public Collection<IMedium> getResponseData() {
        return this.responseData;
    }

    /**
     * Generate the result.
     * @return response of the MediaServiceResult
     */
    public javax.ws.rs.core.Response getResponse() {
        return javax.ws.rs.core.Response.status(this.getCode())
                .entity(new JSONObject()
                .put("status", this.getCode())
                .put("message", this.getStatus())
                .put("data-length", this.getResponseData() != null ? this.getResponseData().size() : -1)
                .put("data", this.getResponseData() != null ? this.getResponseData().size() > 0 ? this.getResponseData() : "" : "error") // TODO json serilize
                .put("help", "http://lmgtfy.com/?q=http+statuscode+" + this.getCode())
                .toString())
                .build();
    }




}
