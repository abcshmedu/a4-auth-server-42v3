package edu.hm.rfurch.shareit.model;

import java.util.Optional;

/**
 * 
 * @author Raphael Furch, rfurch@hm.edu / Michael Schmid, m.schmid@hm.edu
 *
 */
public interface IBook extends IMedium {
    /**
     * Getter to get the author of a book.
     * @return author of the book
     */
    String getAuthor();
    
    /**
     * Getter to get the isbn of a book.
     * @return isbn of the book
     */
    String getIsbn();
    
    /**
     * Setter to update the values of the book.
     * @param medium with the new values.
     * @return an Optional of a book
     */
    Optional<IBook> update(IBook medium);
}
