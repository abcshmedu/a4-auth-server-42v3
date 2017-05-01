package edu.hm.rfurch.shareit.logic;

import edu.hm.rfurch.shareit.model.IBook;
import edu.hm.rfurch.shareit.model.IDisc;

import java.util.Optional;


/**
 * 
 * @author Raphael Furch, rfurch@hm.edu / Michael Schmid, m.schmid@hm.edu
 *
 */ //TODO ist das I ok? Angabe ist ohne I.
public interface IMediaService {
    /**
     * Add a book.
     * @param book with the values
     * @return Optional with the MediaServiceResult of the action
     */
    Optional<MediaServiceResult> addBook(IBook book);
    
    /**
     * List of all books.
     * @return Optional with a MediaServiceResult of all books
     */
    Optional<MediaServiceResult> getBooks();
    
    /**
     * Get the book values of the isbn.
     * @param isbn of the book
     * @return Optional with a MediaServiceResult of the book
     */
    Optional<MediaServiceResult> getBook(String isbn);
    
    /**
     * 
     * @param book with the new values
     * @return Optional with the MediaServiceResult of the action
     */
    Optional<MediaServiceResult> updateBook(IBook book);
    
    /**
     * 
     * @param book to remove
     * @return Optional with the MediaServiceResult of the action
     */
    Optional<MediaServiceResult> removeBook(IBook book);
    
    /**
     * Add a disc.
     * @param disc with the values
     * @return Optional with the MediaServiceResult of the action
     */
    Optional<MediaServiceResult> addDisc(IDisc disc);
    
    /**
     * List all discs.
     * @return Optional with a MediaServiceResult of all discs
     */
    Optional<MediaServiceResult> getDiscs();
    
    /**
     * Get the disc values of the barcode.
     * @param barcode of the disc
     * @return Optional with a MediaServiceResult of the disc
     */
    Optional<MediaServiceResult> getDisc(String barcode);
    
    /**
     * Update the values of a disk.
     * @param disc with the new values
     * @return Optional with the MediaServiceResult of the action
     */
    Optional<MediaServiceResult> updateDisc(IDisc disc);
    
    /**
     * Remove a disk.
     * @param disc to remove
     * @return Optional with the MediaServiceResult of the action
     */
    Optional<MediaServiceResult> removeDisc(IDisc disc);
    
    /**
     * List all mediums.
     * @return Optional with a MediaServiceResult of all mediums
     */
    Optional<MediaServiceResult> getMediums();

}
