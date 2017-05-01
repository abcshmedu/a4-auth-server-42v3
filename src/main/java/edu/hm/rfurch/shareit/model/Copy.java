package edu.hm.rfurch.shareit.model;

/**
 * 
 * @author Raphael Furch, rfurch@hm.edu / Michael Schmid, m.schmid@hm.edu
 *
 */
public class Copy implements ICopy {
    /**
     * Ctor to create an exemplar of a medium.
     * @param owner of the copy
     * @param medium of which the copy is an exemplar
     */
    public Copy(String owner, IMedium medium) {
        if (medium == null) {
            throw new IllegalArgumentException("Can not copy a not existing medium.");
        }
        this.medium = medium;
        this.owner = owner;
    }
    private final IMedium medium;
    @Override
    public IMedium getMedium() {
        return medium;
    }

    private final String owner;
    @Override
    public String getUsername() {
        return owner;
    }
}
