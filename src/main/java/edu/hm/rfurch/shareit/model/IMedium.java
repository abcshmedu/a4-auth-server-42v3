package edu.hm.rfurch.shareit.model;

/**
 * 
 * @author Raphael Furch, rfurch@hm.edu / Michael Schmid, m.schmid@hm.edu
 *
 */
public interface IMedium {
    /**
     * Getter to get the title of a medium.
     * @return title of the medium
     */
    String getTitle();
    
    /**
     * Check if the medium is valid.
     * @return if the medium is valid
     */
    boolean valid();

}
