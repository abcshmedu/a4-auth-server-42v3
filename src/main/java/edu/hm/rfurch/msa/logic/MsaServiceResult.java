package edu.hm.rfurch.msa.logic;


import edu.hm.rfurch.msa.model.Token;
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
public enum MsaServiceResult {
    OK(Response.SC_OK, "OK"),
    Forbidden (Response.SC_FORBIDDEN, "Access for"), //After delete
    BadRequest(Response.SC_BAD_REQUEST, "Bad Request"), // Missing information or data
    IamATeapot(418, "I'm a teapot");



    private final int code;
    private String status;
    private String msg;
    private String token;
    
    /**
     * Ctor to create a MediaServiceResult.
     * @param code of the result
     * @param status of the result
     */
    MsaServiceResult(int code, String status) {
        this.code = code;
        this.status = status;
        this.token = null;
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
     * @param token token.
     * @return result
     */
    public MsaServiceResult setToken(Token token) {
        this.token = token.getTokenValue();
        return this;
    }

    public MsaServiceResult setMessage(String msg){
        this.msg =  msg;
        return this;
    }
    private String getMessage(){
        return msg;
    }


    /**
     * Getter of the medium list.
     * @return collection of mediums
     */
    public String getToken() {
        return this.token;
    }

    /**
     * Generate the result.
     * @return response of the MediaServiceResult
     */
    public javax.ws.rs.core.Response getResponse() {
        final JSONObject json = new JSONObject()
                .put("code", this.getCode())
                .put("status", this.getStatus())
                .put("msg", this.getMessage())
                .put("help", "http://lmgtfy.com/?q=http+statuscode+" + this.getCode());
        if(this.getToken() != null)
            json.put("token",this.getToken());
        this.token = null;
        return javax.ws.rs.core.Response.status(this.getCode())
                .entity(json.toString())
                .build();
    }




}
