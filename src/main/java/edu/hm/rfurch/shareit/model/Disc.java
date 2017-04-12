package edu.hm.rfurch.shareit.model;

/**
 * Created by rapha on 12.04.2017.
 */
public class Disc extends BaseMedium implements IDisc {
    public Disc(String title, String barcode, String director, int fsk) {
        super(title);
        if(fsk < 0)
            throw new IllegalArgumentException("Fsk must be bigger then 0");
        this.barcode = barcode;
        this.director = director;
        this.fsk = fsk;
    }


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

    private final int fsk;
    @Override
    public int getFsk() {
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
        return null;
    }
}
