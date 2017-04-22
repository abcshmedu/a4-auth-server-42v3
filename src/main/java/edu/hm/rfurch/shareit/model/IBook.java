package edu.hm.rfurch.shareit.model;

import java.util.Optional;

/**
 * Created by rapha on 12.04.2017.
 */
public interface IBook extends IMedium {
    String getAuthor();
    String getIsbn();
    Optional<IBook> update(IBook medium);
}
