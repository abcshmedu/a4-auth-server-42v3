package edu.hm.rfurch.shareit.model;

import java.util.Optional;

/**
 * Created by rapha on 12.04.2017.
 */
public interface IDisc extends IMedium {

    String getBarcode();
    String getDirector();
    Integer getFsk();
    Optional<IDisc> update(IDisc disc);





}
