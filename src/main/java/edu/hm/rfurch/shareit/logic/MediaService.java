package edu.hm.rfurch.shareit.logic;

import edu.hm.rfurch.shareit.model.IBook;
import edu.hm.rfurch.shareit.model.IDisc;
import edu.hm.rfurch.shareit.model.IMedium;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Optional;

/**
 * Created by FURCH on 19/04/2017.
 */
public class MediaService implements IMediaService {

    private MediaService(){
        throw new NotImplementedException();
    }

    @Override
    public Optional<MediaServiceResult> addBook(IBook book) {
        throw new NotImplementedException();
    }

    @Override
    public Optional<MediaServiceResult> addDisc(IDisc disc) {
        throw new NotImplementedException();
    }

    @Override
    public Optional<IMedium[]> getBooks() {
        throw new NotImplementedException();
    }

    @Override
    public Optional<IMedium[]> getDiscs() {
        throw new NotImplementedException();
    }

    @Override
    public Optional<MediaServiceResult> updateBook(IBook book) {
        throw new NotImplementedException();
    }

    @Override
    public Optional<MediaServiceResult> updateDisc(IDisc disc) {
        throw new NotImplementedException();
    }
}
