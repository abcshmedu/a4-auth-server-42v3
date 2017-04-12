package edu.hm.rfurch.shareit.model;

/**
 * Created by rapha on 12.04.2017.
 */
public abstract class BaseMedium implements IMedium {

    private final String title;

    protected BaseMedium(String title){
        this.title = title;
    }
    @Override
    public String getTitle() {
        return this.title;
    }

    public abstract int hashCode();
    public abstract boolean equals(Object object);
    public abstract String toString();
}
