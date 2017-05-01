package edu.hm.rfurch.shareit.model;

import java.util.Optional;

/**
 * 
 * @author Raphael Furch, rfurch@hm.edu / Michael Schmid, m.schmid@hm.edu
 *
 */
public interface IDisc extends IMedium {

    /**
     * Getter to get the barcode of a disc.
     * @return barcode of the disc
     */
    String getBarcode();
    
    /**
     * Getter to get the director of a disc.
     * @return director of the disc
     */
    String getDirector();
    
    /**
     * Getter to get the fsk of a disc.
     * @return fsk of the disc
     */
    Integer getFsk();
    
    /**
     * Setter to update the values of the disc.
     * @param disc with the new values.
     * @return an Optional of a disc
     */
    Optional<IDisc> update(IDisc disc);
}
