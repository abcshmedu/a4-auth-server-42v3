package edu.hm.rfurch.msa.logic;

import edu.hm.rfurch.msa.model.Token;
import edu.hm.rfurch.msa.model.User;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public class MsaServiceResultTest {

    private User user = new User("Michael Schmid", "secretPassword", true);
    @Test
    public void okValues(){
        MsaServiceResult re = MsaServiceResult.OK;
        Assert.assertEquals("OK", re.getStatus());
        Assert.assertEquals(200, re.getCode());
        Assert.assertEquals(200, re.getResponse().getStatus());
        Assert.assertEquals("OutboundJaxrsResponse{status=200, reason=OK, hasEntity=true, closed=false, buffered=false}", re.getResponse().toString());
    }



    @Test
    public void badRequestValues(){
        MsaServiceResult re = MsaServiceResult.BadRequest;
        Assert.assertEquals("Bad Request", re.getStatus());
        Assert.assertEquals(400, re.getCode());
        Assert.assertEquals(400, re.getResponse().getStatus());
        Assert.assertEquals("OutboundJaxrsResponse{status=400, reason=Bad Request, hasEntity=true, closed=false, buffered=false}", re.getResponse().toString());
    }

    @Test
    public void teaRequestValues(){
        MsaServiceResult re = MsaServiceResult.IamATeapot;
        Assert.assertEquals("I'm a teapot", re.getStatus());
        Assert.assertEquals(418, re.getCode());
        Assert.assertEquals(418, re.getResponse().getStatus());
        Assert.assertEquals("OutboundJaxrsResponse{status=418, reason=, hasEntity=true, closed=false, buffered=false}", re.getResponse().toString());
    }

    @Test
    public void serilializeToken(){
        MsaServiceResult re = MsaServiceResult.OK;
        final Token t = new Token(user, 1000000);
        re.setToken(t).setMessage("Message");
        System.out.println();
        Assert.assertEquals("{\"msg\":\"Message\",\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"code\":200,\"status\":\"OK\",\"token\":\""+t.getTokenValue()+"\"}", new JSONObject(re.getResponse()).get("entity").toString());
    }



}
