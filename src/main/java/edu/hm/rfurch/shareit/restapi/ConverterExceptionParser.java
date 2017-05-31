package edu.hm.rfurch.shareit.restapi;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import edu.hm.rfurch.shareit.logic.MediaServiceResult;

import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Created by Raphael Furch on 31/05/2017.
 */
public class ConverterExceptionParser extends PropertyBindingException {

    protected ConverterExceptionParser(String msg, JsonLocation loc, Class<?> referringClass, String propName, Collection<Object> propertyIds) {
        super(msg, loc, referringClass, propName, propertyIds);
    }

    public Response toResponse(PropertyBindingException exception){
        System.out.println("test2");
        return MediaServiceResult.IamATeapot.getResponse();
    }

}
