package edu.hm.rfurch.shareit.data;

import edu.hm.rfurch.shareit.model.IBook;
import edu.hm.rfurch.shareit.model.ICopy;
import edu.hm.rfurch.shareit.model.IDisc;
import edu.hm.rfurch.shareit.model.IMedium;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Raphael Furch on 12.04.2017.
 */
public interface IData {

    default Optional<IDisc> getDisc(String barcode){
        return this.getMediums().stream().filter(m -> m.getClass().equals(IDisc.class))
                .map(m -> (IDisc)m).filter(d -> d.getBarcode().equals(barcode)).findAny();
    }
    default Optional<IBook> getBook(String isbn){
        return this.getMediums().stream().filter(m -> m.getClass().equals(IBook.class))
                .map(m -> (IBook)m).filter(b -> b.getIsbn().equals(isbn)).findAny();
    }
    List<IMedium> getMediums();
    Optional<Boolean> add(IMedium medium);

}
