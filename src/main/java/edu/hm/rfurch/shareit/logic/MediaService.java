package edu.hm.rfurch.shareit.logic;

import edu.hm.rfurch.shareit.data.IData;
import edu.hm.rfurch.shareit.model.Book;
import edu.hm.rfurch.shareit.model.IBook;
import edu.hm.rfurch.shareit.model.IDisc;
import edu.hm.rfurch.shareit.model.IMedium;


import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.Optional;

/**
 * 
 * @author Raphael Furch, rfurch@hm.edu / Michael Schmid, m.schmid@hm.edu
 *
 */
public class MediaService implements IMediaService {


    private final IData data;
    private IData getData(){
        return data;
    }
    @Inject
    public MediaService(IData data){
        this.data = data;
    }

    @Override
    public Optional<MediaServiceResult> addBook(IBook book) {
        if (book == null) {
            throw new NullPointerException();
        }
        return Optional.of(this.getData().add(new Book(book.getTitle(), book.getAuthor(), book.getIsbn())).orElse(false) ?
                MediaServiceResult.OK :
                MediaServiceResult.BadRequest);
    }

    @Override
    public Optional<MediaServiceResult> getBooks() {
        return Optional.of(MediaServiceResult.OK.setResponseData(
                this.getData().getBooks().orElse(new ArrayList<>())));
    }


    @Override
    public Optional<MediaServiceResult> getBook(String isbn) {
        if (isbn == null) {
            throw new NullPointerException();
        }
        Optional<IMedium> oMediaServiceResult = this.getData().getBook(isbn.replace("-",""));
        return oMediaServiceResult.isPresent() ? Optional.of(MediaServiceResult.OK.setResponseData(oMediaServiceResult.get())) : Optional.empty();

    }

    @Override
    public Optional<MediaServiceResult> updateBook(IBook book) {
        if (book == null) {
            throw new NullPointerException();
        }
        Optional<IMedium> oFullBook = this.getData().getBook(book.getIsbn());
        if (oFullBook.isPresent()) {
            Optional<IBook> updatedBook = ((IBook)oFullBook.get()).update(new Book(book.getTitle(), book.getAuthor(), book.getIsbn()));
            if (updatedBook.isPresent()) {
                this.getData().remove(oFullBook.get());
                this.getData().add(updatedBook.get());
                return Optional.of(MediaServiceResult.OK);
            }
        }
        return Optional.of(MediaServiceResult.BadRequest);
    }

    @Override
    public Optional<MediaServiceResult> removeBook(IBook book) {
        if (book == null) {
            throw new NullPointerException();
        }
        return Optional.of(this.getData().remove(new Book(book.getTitle(), book.getAuthor(), book.getIsbn())).orElse(false) ? MediaServiceResult.NoContent : MediaServiceResult.BadRequest);
    }

    @Override
    public Optional<MediaServiceResult> addDisc(IDisc disc) {
        if (disc == null) {
            throw new NullPointerException();
        }
        return Optional.of(this.getData().add(disc).get() ? MediaServiceResult.OK : MediaServiceResult.BadRequest);
    }

    @Override
    public Optional<MediaServiceResult> getDiscs() {
        return Optional.of(MediaServiceResult.OK.setResponseData(
                this.getData().getDiscs().orElse(new ArrayList<>())
        ));
    }

    @Override
    public Optional<MediaServiceResult> getDisc(String barcode) {
        if (barcode == null) {
            throw new NullPointerException();
        }
        Optional<IMedium> oMediaServiceResult = this.getData().getDisc(barcode);
        return oMediaServiceResult.isPresent() ?
                Optional.of(MediaServiceResult.OK.setResponseData(oMediaServiceResult.get())) :
                Optional.empty();
    }

    @Override
    public Optional<MediaServiceResult> updateDisc(IDisc disc) {
        if (disc == null) {
            throw new NullPointerException();
        }
        Optional<IMedium> oFullDisc = this.getData().getDisc(disc.getBarcode());
        if (oFullDisc.isPresent()) {
            Optional<IDisc> updatedDisc = ((IDisc)oFullDisc.get()).update(disc);
            if (updatedDisc.isPresent()) {
                this.getData().remove(oFullDisc.get());
                this.getData().add(updatedDisc.get());
                return Optional.of(MediaServiceResult.OK);
            }
        }
        return Optional.of(MediaServiceResult.BadRequest);
    }

    @Override
    public Optional<MediaServiceResult> removeDisc(IDisc disc) {
       if (disc == null) {
           throw new NullPointerException();
       }
        return  Optional.of(this.getData().remove(disc).orElse(false) ? MediaServiceResult.NoContent : MediaServiceResult.BadRequest);
    }


    @Override
    public Optional<MediaServiceResult> getMediums() {
        return Optional.of(MediaServiceResult.OK.setResponseData(
                this.getData().getMediums())
        );
    }
}
