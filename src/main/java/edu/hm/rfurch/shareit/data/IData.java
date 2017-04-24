package edu.hm.rfurch.shareit.data;

import edu.hm.rfurch.shareit.model.IBook;
import edu.hm.rfurch.shareit.model.IDisc;
import edu.hm.rfurch.shareit.model.IMedium;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Raphael Furch on 12.04.2017.
 */
public interface IData {

    default Optional<IMedium> getDisc(String barcode) {
        return this.getDiscs().get().stream()
                .filter(IDisc.class::isInstance)
                .map(IDisc.class::cast)
                .filter(d -> d.getBarcode().equals(barcode))
                .map(IMedium.class::cast).findAny();
    }
    default Optional<IMedium> getBook(String isbn){
        return this.getBooks().get().stream()
                .filter(IBook.class::isInstance)
                .map(IBook.class::cast)
                .filter(b -> b.getIsbn().equals(isbn))
                .map(IMedium.class::cast)
                .findAny();
    }
    default Optional<List<IMedium>> getBooks(){
        return Optional.of(this.getMediums().stream()
                .filter(m -> m instanceof IBook)
                .map(m -> (IBook)m).collect(Collectors.toList()));
    }
    default Optional<List<IMedium>> getDiscs(){
        return Optional.of(this.getMediums().stream().filter(m -> m instanceof IDisc)
                .map(m -> (IDisc)m).collect(Collectors.toList()));
    }
    List<IMedium> getMediums();
    Optional<Boolean> add(IMedium medium);
    Optional<Boolean> remove(IMedium medium);
    Optional<Boolean> clear();

}
