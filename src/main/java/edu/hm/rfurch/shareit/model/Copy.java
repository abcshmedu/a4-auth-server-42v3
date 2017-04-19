package edu.hm.rfurch.shareit.model;

/**
 * Created by rapha on 12.04.2017.
 */
public class Copy implements ICopy {

    public Copy(String owner, IMedium medium){
        if(medium == null)
            throw new IllegalArgumentException("Can not copy a not existing medium.");
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
