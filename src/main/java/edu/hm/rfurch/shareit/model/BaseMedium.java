package edu.hm.rfurch.shareit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * 
 * @author Raphael Furch, rfurch@hm.edu / Michael Schmid, m.schmid@hm.edu
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "Medium")
@JsonIgnoreProperties({"id"})
public abstract class BaseMedium implements IMedium {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    private final String title;
    
    /**
     * Ctor to create a BaseMedium.
     * @param title of the medium
     */
    protected BaseMedium(String title) {
        this.title = title;
    }
    
    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaseMedium that = (BaseMedium) o;

        return title.equals(that.title);
    }

    @Override
    public abstract String toString();
}
