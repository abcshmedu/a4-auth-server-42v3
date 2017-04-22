package edu.hm.rfurch.shareit.model;

import java.util.Optional;

/**
 * Created by rapha on 12.04.2017.
 */
public class Disc extends BaseMedium implements IDisc {
    public Disc(String title, String barcode, String director, Integer fsk) {
        super(title);
        if(barcode == null)
            throw new IllegalArgumentException("Barcode = null is bad.");
        this.barcode = barcode;
        this.director = director;
        this.fsk = fsk;
    }


    @SuppressWarnings("unused") // Constructor for Reflection
    private Disc(){
        this("","","",0);
    }

    private final String barcode;
    @Override
    public String getBarcode() {
        return this.barcode;
    }

    private final String director;
    @Override
    public String getDirector() {
        return this.director;
    }

    private final Integer fsk;
    @Override
    public Integer getFsk() {
        return this.fsk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disc disc = (Disc) o;

        if (fsk != disc.fsk) return false;
        if (!barcode.equals(disc.barcode)) return false;
        return director.equals(disc.director);
    }

    @Override
    public int hashCode() {
        int result = barcode.hashCode();
        result = 31 * result + director.hashCode();
        result = 31 * result + fsk;
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Barcode: " + this.getBarcode() + " - ")
                .append("Title: " + this.getTitle() + " - ")
                .append("Director: " + this.getDirector() + " - ")
                .append("Fsk: " + this.getFsk()).toString();
    }

    @Override
    public Optional<IDisc> update(IDisc disc) {
        Optional<IDisc> result = Optional.empty();
        if(disc != null && this.getBarcode().equals(disc.getBarcode())){
            result = Optional.of(new Disc(
                    disc.getTitle()!= null?disc.getTitle():this.getTitle(),
                    this.getBarcode(),
                    disc.getDirector()!= null?disc.getDirector():this.getDirector(),
                    disc.getFsk()!=null?disc.getFsk():this.getFsk()));
        }
        return result;
    }
}
