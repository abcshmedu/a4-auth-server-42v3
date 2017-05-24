package edu.hm.rfurch.msa.logic;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public class MsaServiceResultTest {

    @Test
    public void okValues(){
        MsaServiceResult re = MsaServiceResult.OK;
        Assert.assertEquals("OK", re.getStatus());
        Assert.assertEquals(200, re.getCode());
        Assert.assertEquals(200, re.getResponse().getStatus());
        Assert.assertEquals("OutboundJaxrsResponse{status=200, reason=OK, hasEntity=true, closed=false, buffered=false}", re.getResponse().toString());
    }

    @Test
    public void createdValues(){
        MsaServiceResult re = MsaServiceResult.Created;
        Assert.assertEquals("Created", re.getStatus());
        Assert.assertEquals(201, re.getCode());
        Assert.assertEquals(201, re.getResponse().getStatus());
        Assert.assertEquals("OutboundJaxrsResponse{status=201, reason=Created, hasEntity=true, closed=false, buffered=false}", re.getResponse().toString());
    }

    @Test
    public void noContentValues(){
        MsaServiceResult re = MsaServiceResult.NoContent;
        Assert.assertEquals("No Content", re.getStatus());
        Assert.assertEquals(204, re.getCode());
        Assert.assertEquals(204, re.getResponse().getStatus());
        Assert.assertEquals("OutboundJaxrsResponse{status=204, reason=No Content, hasEntity=true, closed=false, buffered=false}", re.getResponse().toString());
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
    public void serilizeContentSingleDiscTest(){
        MsaServiceResult re = MsaServiceResult.OK;
        re.setResponseData(new Disc("t","5055011702189","d",1));
        System.out.println(new JSONObject(re.getResponse()).get("entity").toString());
        Assert.assertEquals("{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"director\":\"d\",\"title\":\"t\",\"barcode\":\"5055011702189\",\"fsk\":1}],\"message\":\"OK\",\"data-length\":1,\"status\":200}", new JSONObject(re.getResponse()).get("entity").toString());
    }

    @Test
    public void serilizeContentSingleBookTest(){
        MsaServiceResult re = MsaServiceResult.OK;
        re.setResponseData(new Book("t","b","978-3-86680-192-9"));
        Assert.assertEquals("{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"b\",\"isbn\":\"9783866801929\",\"title\":\"t\"}],\"message\":\"OK\",\"data-length\":1,\"status\":200}", new JSONObject(re.getResponse()).get("entity").toString());
    }

    @Test
    public void serilizeContentListTest(){
        MsaServiceResult re = MsaServiceResult.OK;
        List<IMedium> discs = new ArrayList<>();
        discs.add(new Book("t1","b1","978-3-86680-192-9"));
        discs.add(new Book("t2","b2","978-3-8369-4231-7"));
        discs.add(new Book("t3","b3","978-3-8369-4150-1"));
        discs.add(new Book("t4","b4","978-3-8369-4917-0"));
        discs.add(new Book("t5","b5","978-3-8369-5481-5"));
        re.setResponseData(discs);
        Assert.assertEquals("{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"b1\",\"isbn\":\"9783866801929\",\"title\":\"t1\"},{\"author\":\"b2\",\"isbn\":\"9783836942317\",\"title\":\"t2\"},{\"author\":\"b3\",\"isbn\":\"9783836941501\",\"title\":\"t3\"},{\"author\":\"b4\",\"isbn\":\"9783836949170\",\"title\":\"t4\"},{\"author\":\"b5\",\"isbn\":\"9783836954815\",\"title\":\"t5\"}],\"message\":\"OK\",\"data-length\":5,\"status\":200}", new JSONObject(re.getResponse()).get("entity").toString());
    }
}
