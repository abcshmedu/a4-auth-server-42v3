package edu.hm.rfurch.shareit.logic;

import edu.hm.rfurch.shareit.model.IBook;
import edu.hm.rfurch.shareit.model.IDisc;
import edu.hm.rfurch.shareit.data.ResourceManager;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

/**
 * Created by FURCH on 19/04/2017.
 */
public class MediaManager {


    // <editor-fold defaultstate="collapsed" desc="Books">
    public Optional<Response> createBook(IBook book){
            return Optional.of(ResourceManager.dataAccess().add(book).get()?
                    MediaServiceResult.OK.getResponse():
                    MediaServiceResult.BadRequest.getResponse());
    }
    public Optional<Response> getBooks(){
        return Optional.of(MediaServiceResult.OK.setResponseData(ResourceManager.dataAccess().getBooks().orElse(null)).getResponse());

    }
    public Optional<Response> getBook(String isbn){throw new NotImplementedException(); }
    public Optional<Response> updateBook(IBook book){
        throw new NotImplementedException();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Books">
    public Optional<Response> createDisc(IDisc disc){
        throw new NotImplementedException();
    }
    public Optional<Response> getDiscs(){throw new NotImplementedException(); }
    public Optional<Response> getDisc(String barcode){throw new NotImplementedException(); }
    public Optional<Response> updatDisc(IDisc disc){
        throw new NotImplementedException();
    }
    // </editor-fold>
}
