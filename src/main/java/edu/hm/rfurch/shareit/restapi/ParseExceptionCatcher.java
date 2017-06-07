package edu.hm.rfurch.shareit.restapi;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import edu.hm.rfurch.shareit.logic.MediaServiceResult;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.Collection;

/**
 * Created by Raphael Furch on 31/05/2017.
 */
public class ParseExceptionCatcher implements ExceptionMapper<PropertyBindingException> {


    public Response toResponse(PropertyBindingException exception){
        return MediaServiceResult.IamATeapot.getResponse();
    }

}
