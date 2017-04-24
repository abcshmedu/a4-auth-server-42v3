package edu.hm.rfurch.shareit.logic;

import edu.hm.rfurch.shareit.data.ResourceManager;
import edu.hm.rfurch.shareit.model.IBook;
import edu.hm.rfurch.shareit.model.IDisc;
import edu.hm.rfurch.shareit.model.IMedium;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by FURCH on 19/04/2017.
 */
public class MediaService implements IMediaService {


    @Override
    public Optional<MediaServiceResult> addBook(IBook book) {
        if(book == null)
            throw new NullPointerException();
        return Optional.of(ResourceManager.dataAccess().add(book).get()?
                MediaServiceResult.OK:
                MediaServiceResult.BadRequest);
    }

    @Override
    public Optional<MediaServiceResult> getBooks() {
        return Optional.of(MediaServiceResult.OK.setResponseData(
                ResourceManager.dataAccess().getBooks().orElse(new ArrayList<>())));
    }

    @Override
    public Optional<MediaServiceResult> getBook(IBook book) {
        if(book == null)
            throw new NullPointerException();
        return Optional.of(MediaServiceResult.OK.setResponseData(
                ResourceManager.dataAccess().getBook(book.getIsbn()).get()));

    }

    @Override
    public Optional<MediaServiceResult> updateBook(IBook book) {
        if(book == null)
            throw new NullPointerException();
        Optional<IMedium> oFullBook = ResourceManager.dataAccess().getBook(book.getIsbn());
        if(oFullBook.isPresent()){
            Optional<IBook> updatedBook = ((IBook)oFullBook.get()).update(book);
            if(updatedBook.isPresent()){
                ResourceManager.dataAccess().remove(oFullBook.get());
                ResourceManager.dataAccess().add(updatedBook.get());
                return Optional.of(MediaServiceResult.OK);
            }
        }
        return Optional.of(MediaServiceResult.BadRequest);
    }

    @Override
    public Optional<MediaServiceResult> removeBook(IBook book) {
        if(book == null)
            throw new NullPointerException();
        return  Optional.of(ResourceManager.dataAccess().remove(book).orElse(false)?
                MediaServiceResult.NoContent:
                MediaServiceResult.BadRequest);
    }

    @Override
    public Optional<MediaServiceResult> addDisc(IDisc disc) {
        if(disc == null)
            throw new NullPointerException();
        return Optional.of(ResourceManager.dataAccess().add(disc).get()?
                MediaServiceResult.OK:
                MediaServiceResult.BadRequest);
    }

    @Override
    public Optional<MediaServiceResult> getDiscs() {
        return Optional.of(MediaServiceResult.OK.setResponseData(
                ResourceManager.dataAccess().getDiscs().orElse(new ArrayList<>())
        ));
    }

    @Override
    public Optional<MediaServiceResult> getDisc(IDisc disc) {
        if(disc == null)
            throw new NullPointerException();
        return Optional.of(MediaServiceResult.OK.setResponseData(
                ResourceManager.dataAccess().getDisc(disc.getBarcode()).get()));
    }

    @Override
    public Optional<MediaServiceResult> updateDisc(IDisc disc) {
        if(disc == null)
            throw new NullPointerException();
        Optional<IMedium> oFullDisc = ResourceManager.dataAccess().getDisc(disc.getBarcode());
        if(oFullDisc.isPresent()){
            Optional<IDisc> updatedDisc = ((IDisc)oFullDisc.get()).update(disc);
            if(updatedDisc.isPresent()){
                ResourceManager.dataAccess().remove(oFullDisc.get());
                ResourceManager.dataAccess().add(updatedDisc.get());
                return Optional.of(MediaServiceResult.OK);
            }
        }
        return Optional.of(MediaServiceResult.BadRequest);
    }

    @Override
    public Optional<MediaServiceResult> removeDisk(IDisc disc) {
       if(disc == null)
           throw new NullPointerException();
        return  Optional.of(ResourceManager.dataAccess().remove(disc).orElse(false)?
                MediaServiceResult.NoContent:
                MediaServiceResult.BadRequest);
    }

    @Override
    public Optional<MediaServiceResult> getMediums() {
        return Optional.of(MediaServiceResult.OK.setResponseData(
                ResourceManager.dataAccess().getMediums())
        );
    }
}
