package edu.hm.rfurch.shareit.logic;

import edu.hm.rfurch.shareit.model.IBook;
import edu.hm.rfurch.shareit.model.IDisc;
import org.eclipse.jetty.server.Response;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Optional;

/**
 * Created by FURCH on 19/04/2017.
 */
public class MediaManager {


    // <editor-fold defaultstate="collapsed" desc="Books">
    public Optional<Response> createBook(IBook book){

    }
    public Optional<Response> getBooks(){ throw new NotImplementedException(); }
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
