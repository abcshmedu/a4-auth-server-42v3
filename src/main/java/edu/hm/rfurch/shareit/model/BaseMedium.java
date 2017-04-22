package edu.hm.rfurch.shareit.model;

import com.fasterxml.jackson.databind.ser.Serializers;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseMedium that = (BaseMedium) o;

        return title.equals(that.title);
    }

    public abstract String toString();
}
