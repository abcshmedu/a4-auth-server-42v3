package edu.hm.rfurch.shareit.restapi;

import edu.hm.rfurch.shareit.logic.MediaService;
import edu.hm.rfurch.shareit.logic.MediaServiceResult;
import edu.hm.rfurch.shareit.model.IMedium;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * Created by rapha on 25.04.2017.
 */
@Path("/media")
public class TestRestApiJodel {

	public TestRestApiJodel() {
		
	}
	
    @GET
    @Path("/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn") String isbn) {
        if(isbn == null)
            throw new NullPointerException();
       Optional<MediaServiceResult> oBook = new MediaService().getBook(isbn);
       return oBook.isPresent()?oBook.get().getResponse():MediaServiceResult.BadRequest.getResponse();

    }
    
    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
      return "Hello, world!";
    }
}
