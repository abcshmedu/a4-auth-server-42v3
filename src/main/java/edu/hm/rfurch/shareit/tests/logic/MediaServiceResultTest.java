package edu.hm.rfurch.shareit.tests.logic;

import edu.hm.rfurch.shareit.logic.MediaServiceResult;
import edu.hm.rfurch.shareit.model.Disc;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public class MediaServiceResultTest {

    @Test
    public void okValues(){
        MediaServiceResult re = MediaServiceResult.OK;
        Assert.assertEquals("OK", re.getStatus());
        Assert.assertEquals(200, re.getCode());
        Assert.assertEquals(200, re.getResponse().getStatus());
        Assert.assertEquals("OutboundJaxrsResponse{status=200, reason=OK, hasEntity=true, closed=false, buffered=false}", re.getResponse().toString());
    }

    @Test
    public void createdValues(){
        MediaServiceResult re = MediaServiceResult.Created;
        Assert.assertEquals("Created", re.getStatus());
        Assert.assertEquals(201, re.getCode());
        Assert.assertEquals(201, re.getResponse().getStatus());
        Assert.assertEquals("OutboundJaxrsResponse{status=201, reason=Created, hasEntity=true, closed=false, buffered=false}", re.getResponse().toString());
    }

    @Test
    public void noContentValues(){
        MediaServiceResult re = MediaServiceResult.NoContent;
        Assert.assertEquals("No Content", re.getStatus());
        Assert.assertEquals(204, re.getCode());
        Assert.assertEquals(204, re.getResponse().getStatus());
        Assert.assertEquals("OutboundJaxrsResponse{status=204, reason=No Content, hasEntity=true, closed=false, buffered=false}", re.getResponse().toString());
    }

    @Test
    public void badRequestValues(){
        MediaServiceResult re = MediaServiceResult.BadRequest;
        Assert.assertEquals("Bad Request", re.getStatus());
        Assert.assertEquals(400, re.getCode());
        Assert.assertEquals(400, re.getResponse().getStatus());
        Assert.assertEquals("OutboundJaxrsResponse{status=400, reason=Bad Request, hasEntity=true, closed=false, buffered=false}", re.getResponse().toString());
    }

    @Test
    public void teaRequestValues(){
        MediaServiceResult re = MediaServiceResult.IamATeapot;
        Assert.assertEquals("I'm a teapot", re.getStatus());
        Assert.assertEquals(418, re.getCode());
        Assert.assertEquals(418, re.getResponse().getStatus());
        Assert.assertEquals("OutboundJaxrsResponse{status=418, reason=, hasEntity=true, closed=false, buffered=false}", re.getResponse().toString());
    }

    @Test
    public void serilizeContentTest(){
        MediaServiceResult re = MediaServiceResult.OK;
        re.setResponseData(new Disc("t","b","d",1));
        System.out.println((new JSONObject(re.getResponse()).get("entity").toString());
    }
}
