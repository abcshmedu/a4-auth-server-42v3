package edu.hm.rfurch.shareit.model;

/**
 * 
 * @author Raphael Furch, rfurch@hm.edu / Michael Schmid, m.schmid@hm.edu
 *
 */
public interface ICopy {

    /**
     * Getter to get the medium of the copy.
     * @return the medium of the copy
     */
    IMedium getMedium();
    
    /**
     * Getter to get the user name which borrows the copy.
     * @return the user name of the borrower
     */
    String getUsername();
}
