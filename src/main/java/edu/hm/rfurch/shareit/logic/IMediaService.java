package edu.hm.rfurch.shareit.logic;

import edu.hm.rfurch.shareit.model.IBook;
import edu.hm.rfurch.shareit.model.IDisc;
import edu.hm.rfurch.shareit.model.IMedium;

import java.util.Optional;

/**
 * Created by FURCH on 19/04/2017.
 */ //TODO ist das I ok? Angabe ist ohne I.
public interface IMediaService {

    Optional<MediaServiceResult> addBook(IBook book);
    Optional<MediaServiceResult> getBooks();
    Optional<MediaServiceResult> getBook(IBook book);
    Optional<MediaServiceResult> updateBook(IBook book);
    Optional<MediaServiceResult> removeBook(IBook book);

    Optional<MediaServiceResult> addDisc(IDisc disc);
    Optional<MediaServiceResult> getDiscs();
    Optional<MediaServiceResult> getDisc(IDisc disc);
    Optional<MediaServiceResult> updateDisc(IDisc disc);
    Optional<MediaServiceResult> removeDisk(IDisc disc);

}
