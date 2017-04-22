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

    default Optional<IDisc> getDisc(String barcode){
        return this.getDiscs().get().stream().filter(d -> d.getBarcode().equals(barcode)).findAny();
    }
    default Optional<IBook> getBook(String isbn){
        return this.getBooks().get().stream().filter(b -> b.getIsbn().equals(isbn)).findAny();
    }
    default Optional<List<IBook>> getBooks(){
        return Optional.of(this.getMediums().stream()
                .filter(m -> m instanceof IBook)
                .map(m -> (IBook)m).collect(Collectors.toList()));
    }
    default Optional<List<IDisc>> getDiscs(){
        return Optional.of(this.getMediums().stream().filter(m -> m instanceof IDisc)
                .map(m -> (IDisc)m).collect(Collectors.toList()));
    }
    List<IMedium> getMediums();
    Optional<Boolean> add(IMedium medium);
    Optional<Boolean> remove(IMedium medium);
    Optional<Boolean> clear();

}
