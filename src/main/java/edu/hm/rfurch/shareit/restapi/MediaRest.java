package edu.hm.rfurch.shareit.restapi;

import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.hm.rfurch.shareit.logic.MediaService;
import edu.hm.rfurch.shareit.logic.MediaServiceResult;
import edu.hm.rfurch.shareit.model.Book;
import edu.hm.rfurch.shareit.model.Disc;

@Path("/media")
public class MediaRest {
	
    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        return new MediaService().getBooks().get().getResponse();
    }
	
    @GET
    @Path("/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn") String isbn) {
    	Response result = MediaServiceResult.BadRequest.getResponse();
        if(isbn != null) {
        	Optional<MediaServiceResult> oBook = new MediaService().getBook(isbn);
        	result = oBook.isPresent() ? oBook.get().getResponse() : MediaServiceResult.BadRequest.getResponse();
        }
       return result;
    }
    
    
    @POST
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
    	Response result;
    	try {
    		result = new MediaService().addBook(book).get().getResponse();
    	} catch (NullPointerException exception) {
			result = MediaServiceResult.BadRequest.getResponse();
		}
    	return result;
    }
    
    @PUT
    @Path("/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book, @PathParam("isbn") String isbn) {
    	Response result;
    	if(book != null && book.getIsbn().equals(isbn)) {
    		result = new MediaService().updateBook(book).get().getResponse();
    	} else {
			result = MediaServiceResult.BadRequest.getResponse();
		}
    	return result;
    }
    
    @GET
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs() {
        return new MediaService().getDiscs().get().getResponse();
    }
    
    @GET
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisc(@PathParam("barcode") String barcode) {
    	Response result = MediaServiceResult.BadRequest.getResponse();
        if(barcode != null) {
        	Optional<MediaServiceResult> oBook = new MediaService().getDisc(barcode);
        	result = oBook.isPresent() ? oBook.get().getResponse() : MediaServiceResult.BadRequest.getResponse();
        }
       return result;
    }
    
    @POST
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDisc(Disc disc) {
    	Response result;
    	try {
    		result = new MediaService().addDisc(disc).get().getResponse();
    	} catch (NullPointerException exception) {
			result = MediaServiceResult.BadRequest.getResponse();
		}
    	return result;
    }
    
    @PUT
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDisc(Disc disc, @PathParam("barcode") String barcode) {
    	Response result;
    	if(disc != null && disc.getBarcode().equals(barcode)) {
    		result = new MediaService().updateDisc(disc).get().getResponse();
    	} else {
			result = MediaServiceResult.BadRequest.getResponse();
		}
    	return result;
    }
    
    @GET
    @Path("/mediums")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMediums() {
        return new MediaService().getMediums().get().getResponse();
    }

}
