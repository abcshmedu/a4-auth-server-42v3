package edu.hm.rfurch.shareit.logic;

import edu.hm.rfurch.shareit.model.BaseMedium;
import edu.hm.rfurch.shareit.model.IBook;
import edu.hm.rfurch.shareit.model.IDisc;
import edu.hm.rfurch.shareit.data.ResourceManager;

import edu.hm.rfurch.shareit.model.IMedium;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
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
        return Optional.of(MediaServiceResult.OK.setResponseData(
                ResourceManager.dataAccess().getBooks().orElse(new ArrayList<>())
        ).getResponse());

    }
    public Optional<Response> getBook(String isbn){
        return Optional.of(MediaServiceResult.OK.setResponseData(
                ResourceManager.dataAccess().getBook(isbn)
        ).getResponse());
    }
    public Optional<Response> updateBook(IBook book){
        Optional<IBook> oFullBook = ResourceManager.dataAccess().getBook(book.getIsbn());
        if(oFullBook.isPresent()){
            Optional<IBook> updatedBook = oFullBook.get().update(book);
            if(updatedBook.isPresent()){
                ResourceManager.dataAccess().remove(oFullBook.get());
                ResourceManager.dataAccess().add(updatedBook.get());
                return Optional.of(MediaServiceResult.OK.getResponse());
            }
        }
        return Optional.of(MediaServiceResult.BadRequest.getResponse());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Discs">
    public Optional<Response> createDisc(IDisc disc){
        return Optional.of(ResourceManager.dataAccess().add(disc).get()?
                MediaServiceResult.OK.getResponse():
                MediaServiceResult.BadRequest.getResponse());

    }
    public Optional<Response> getDiscs(){
        return Optional.of(MediaServiceResult.OK.setResponseData(
                ResourceManager.dataAccess().getDiscs().orElse(new ArrayList<>())
        ).getResponse());
    }
    public Optional<Response> getDisc(String barcode){
        return Optional.of(MediaServiceResult.OK.setResponseData(
                ResourceManager.dataAccess().getDisc(barcode)
        ).getResponse());
    }
    public Optional<Response> updatDisc(IDisc disc){

        Optional<IDisc> oFullDisc = ResourceManager.dataAccess().getDisc(disc.getBarcode());
        if(oFullDisc.isPresent()){
            Optional<IDisc> updatedDisc = oFullDisc.get().update(disc);
            if(updatedDisc.isPresent()){
                ResourceManager.dataAccess().remove(oFullDisc.get());
                ResourceManager.dataAccess().add(updatedDisc.get());
                return Optional.of(MediaServiceResult.OK.getResponse());
            }
        }
        return Optional.of(MediaServiceResult.BadRequest.getResponse());
    }
    // </editor-fold>
}
