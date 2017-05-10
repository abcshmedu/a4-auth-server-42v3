package edu.hm.rfurch.shareit.data;

import edu.hm.rfurch.shareit.model.IBook;
import edu.hm.rfurch.shareit.model.IDisc;
import edu.hm.rfurch.shareit.model.IMedium;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 
 * @author Raphael Furch, rfurch@hm.edu / Michael Schmid, m.schmid@hm.edu
 *
 */
public interface IData {

    /**
     * Get disc to barcode.
     * @param barcode of the disc
     * @return Optional of the disc
     */
    default Optional<IMedium> getDisc(String barcode) {
        return this.getDiscs().get().stream()
                .filter(IDisc.class::isInstance)
                .map(IDisc.class::cast)
                .filter(d -> d.getBarcode().equals(barcode))
                .map(IMedium.class::cast).findAny();
    }
    
    /**
     * Get book to isbn.
     * @param isbn of the book
     * @return Optional of the book
     */
    default Optional<IMedium> getBook(String isbn) {
        return this.getBooks().get().stream()
                .filter(IBook.class::isInstance)
                .map(IBook.class::cast)
                .filter(b -> b.getIsbn().equals(isbn.replace("-","")))
                .map(IMedium.class::cast)
                .findAny();
    }
    
    /**
     * Get all books.
     * @return Optional of all books
     */
    default Optional<List<IMedium>> getBooks() {
        return Optional.of(this.getMediums().stream()
                .filter(m -> m instanceof IBook)
                .map(m -> (IBook)m).collect(Collectors.toList()));
    }
    
    /**
     * Get all discs.
     * @return Optional of all discs
     */
    default Optional<List<IMedium>> getDiscs() {
        return Optional.of(this.getMediums().stream().filter(m -> m instanceof IDisc)
                .map(m -> (IDisc)m).collect(Collectors.toList()));
    }
    
    /**
     * List all mediums.
     * @return list of all mediums
     */
    List<IMedium> getMediums();
    
    /**
     * Add medium to data.
     * @param medium to add
     * @return result of the action
     */
    Optional<Boolean> add(IMedium medium);
    
    /**
     * Remove medium from data.
     * @param medium to remove
     * @return result of the action
     */
    Optional<Boolean> remove(IMedium medium);
    
    /**
     * Delete all mediums.
     * @return result of the action
     */
    Optional<Boolean> clear();

}
