package edu.hm.rfurch.shareit.restapi;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.hm.rfurch.shareit.logic.IMediaService;
import edu.hm.rfurch.shareit.logic.MediaServiceResult;
import edu.hm.rfurch.shareit.logic.TokenChecker;
import edu.hm.rfurch.shareit.model.Book;
import edu.hm.rfurch.shareit.model.Disc;
import edu.hm.rfurch.shareit.model.IMedium;

/**
 * 
 * @author Raphael Furch, rfurch@hm.edu / Michael Schmid, m.schmid@hm.edu
 *
 */
@Path("/media")
public class MediaRest {

    private final IMediaService service;
    private IMediaService getService(){
        return service;
    }
    @Inject
    public MediaRest(IMediaService service){
        this.service = service;
    }

    /**
     * JSON interface to get a list of all books.
     * @param token Token of the user calling the method.
     * @return JSON response
     */
    @GET
    @Path("/books/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    @Inject
    public Response getBooks(@PathParam("token") String token) {
        try {
            if (!new TokenChecker().checkToken(token, false)) {
                return MediaServiceResult.Unauthorized.getResponse();
            }
        } catch(Exception e) {
            return MediaServiceResult.IamATeapot.getResponse();
        }
        
        List<IMedium> oBooks = this.getService().getBooks().get().getResponseData().stream().filter(book -> book instanceof Book).map(book -> (Book)book).collect(Collectors.toList());
        return MediaServiceResult.OK.setResponseData(oBooks).getResponse();
    }

    /**
     * JSON interface to book.
     * @param isbn of the book
     * @param token Token of the user calling the method.
     * @return JSON response
     */
    @GET
    @Path("/books/{isbn}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn") String isbn, @PathParam("token") String token) {
        try {
            if (!new TokenChecker().checkToken(token, false)) {
                return MediaServiceResult.Unauthorized.getResponse();
            }
        } catch(Exception e) {
            return MediaServiceResult.IamATeapot.getResponse();
        }
        
        Response result = MediaServiceResult.BadRequest.getResponse();
            if (isbn != null && token != null) {            
                Optional<MediaServiceResult> oBook = this.getService().getBook(isbn);
                result = oBook.isPresent() ? oBook.get().getResponse() : MediaServiceResult.BadRequest.getResponse();
            }
       return result;
    }
    
    
    /**
     * JSON interface to add a book.
     * @param book build by jackson
     * @param token Token of the user calling the method.
     * @return JSON response
     */
    @POST
    @Path("/books/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book, @PathParam("token") String token) {
        try {
            if (!new TokenChecker().checkToken(token, false)) {
                return MediaServiceResult.Unauthorized.getResponse();
            }
        } catch(Exception e) {
            return MediaServiceResult.IamATeapot.getResponse();
        }
        
        Response result;
        try {
            result = this.getService().addBook(book).get().getResponse();
        } catch (NullPointerException exception) {
            result = MediaServiceResult.BadRequest.getResponse();
        }
        return result;
    }
    
    /**
     * JSON interface to update a book.
     * @param book build by jackson
     * @param isbn of the book
     * @param token Token of the user calling the method.
     * @return JSON response
     */
    @PUT
    @Path("/books/{isbn}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book, @PathParam("isbn") String isbn, @PathParam("token") String token) {
        try {
            if (!new TokenChecker().checkToken(token, true)) {
                return MediaServiceResult.Unauthorized.getResponse();
            }
        } catch(Exception e) {
            return MediaServiceResult.IamATeapot.getResponse();
        }
        
        Response result;
        if (book != null && book.getIsbn().equals(isbn.replace("-", ""))) {
            result = this.getService().updateBook(book).get().getResponse();
        } else {
            result = MediaServiceResult.BadRequest.getResponse();
        }
        return result;
    }
    
    /**
     * JSON interface to get a list of all discs.
     * @param token Token of the user calling the method.
     * @return JSON response
     */
    @GET
    @Path("/discs/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs(@PathParam("token") String token) {
        try {
            if (!new TokenChecker().checkToken(token, false)) {
                return MediaServiceResult.Unauthorized.getResponse();
            }
        } catch(Exception e) {
            return MediaServiceResult.IamATeapot.getResponse();
        }
        
        List<IMedium> oDiscs = this.getService().getDiscs().get().getResponseData().stream().filter(disc -> disc instanceof Disc).map(disc -> (Disc)disc).collect(Collectors.toList());
        return MediaServiceResult.OK.setResponseData(oDiscs).getResponse();
    }
    
    /**
     * JSON interface to get a disc.
     * @param barcode of the disc
     * @param token Token of the user calling the method.
     * @return JSON response
     */
    @GET
    @Path("/discs/{barcode}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisc(@PathParam("barcode") String barcode, @PathParam("token") String token) {
        try {
            if (!new TokenChecker().checkToken(token, false)) {
                return MediaServiceResult.Unauthorized.getResponse();
            }
        } catch(Exception e) {
            return MediaServiceResult.IamATeapot.getResponse();
        }
        
        Response result = MediaServiceResult.BadRequest.getResponse();
        if (barcode != null) {
            Optional<MediaServiceResult> oBook = this.getService().getDisc(barcode);
            result = oBook.isPresent() ? oBook.get().getResponse() : MediaServiceResult.BadRequest.getResponse();
        }
       return result;
    }
    
    /**
     * JSON interface to add a disc.
     * @param disc build by jackson
     * @param token Token of the user calling the method.
     * @return JSON response
     */
    @POST
    @Path("/discs/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDisc(Disc disc, @PathParam("token") String token) {
        try {
            if (!new TokenChecker().checkToken(token, false)) {
                return MediaServiceResult.Unauthorized.getResponse();
            }
        } catch(Exception e) {
            return MediaServiceResult.IamATeapot.getResponse();
        }
        
        Response result;
        try {
            result = this.getService().addDisc(disc).get().getResponse();
        } catch (NullPointerException exception) {
            result = MediaServiceResult.BadRequest.getResponse();
        }
        return result;
    }
    
    /**
     * JSON interface to update a disc.
     * @param disc build by jackson
     * @param barcode of the disc
     * @param token Token of the user calling the method.
     * @return JSON response
     */
    @PUT
    @Path("/discs/{barcode}/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDisc(Disc disc, @PathParam("barcode") String barcode, @PathParam("token") String token) {
        try {
            if (!new TokenChecker().checkToken(token, true)) {
                return MediaServiceResult.Unauthorized.getResponse();
            }
        } catch(Exception e) {
            return MediaServiceResult.IamATeapot.getResponse();
        }
        
        Response result;
        if (disc != null && disc.getBarcode().equals(barcode)) {
            result = this.getService().updateDisc(disc).get().getResponse();
        } else {
            result = MediaServiceResult.BadRequest.getResponse();
        }
        return result;
    }
    
    /**
     * JSON interface to get a list of mediums.
     * @param token Token of the user calling the method.
     * @return JSON response
     */
    @GET
    @Path("/mediums/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMediums(@PathParam("token") String token) {
        try {
            if (!new TokenChecker().checkToken(token, false)) {
                return MediaServiceResult.Unauthorized.getResponse();
            }
        } catch(Exception e) {
            return MediaServiceResult.IamATeapot.getResponse();
        }
        
        return this.getService().getMediums().get().getResponse();
    }

}
