package edu.hm.rfurch.shareit.data;

import edu.hm.rfurch.shareit.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 22.04.2017.
 */
public class MediaResourceTest {


    @Test
    public void testDataAccessOverOnePoint(){
        Assert.assertEquals(ResourceManager.dataAccess(), ResourceManager.dataAccess());
    }

    // <editor-fold defaultstate="collapsed" desc="Get">
    @Test
    public void getMediumsFromDefaultValues(){
        List<IMedium> expects = new ArrayList<>();
        expects.add(new Book("Title1", "Author1", "978-3-86680-192-9"));
        expects.add(new Disc("Disc1", "4059251015567", "Dirctor1", 1));
        List<IMedium> actuals =  ResourceManager.dataAccess().getMediums();
        System.out.println(actuals);
        Assert.assertArrayEquals(expects.toArray(),actuals.toArray());
    }

    @Test
    public void getBooksFromDefaultValues(){
        List<IBook> expects = new ArrayList<>();
        expects.add(new Book("Title1", "Author1", "978-3-86680-192-9"));
        List<IMedium> actuals =  ResourceManager.dataAccess().getBooks().get();
        Assert.assertArrayEquals(expects.toArray(),actuals.toArray());
    }

    @Test
    public void getDiscsFromDefaultValues(){
        List<IDisc> expects = new ArrayList<>();
        expects.add(new Disc("Disc1", "4059251015567", "Dirctor1", 1));
        List<IMedium> actuals =  ResourceManager.dataAccess().getDiscs().get();
        Assert.assertArrayEquals(expects.toArray(),actuals.toArray());
    }

    @Test
    public void getDiscByBarcodeFromDefaultValuesExists(){
        IDisc expect = new Disc("Disc1", "4059251015567", "Dirctor1", 1);
        Optional<IMedium> actual =  ResourceManager.dataAccess().getDisc("4059251015567");
        Assert.assertTrue(actual.isPresent());
        Assert.assertEquals(expect,actual.get());
    }
    @Test
    public void getDiscByBarcodeFromDefaultValuesNotExists(){
        Optional<IMedium> actual =  ResourceManager.dataAccess().getDisc("No");
        Assert.assertFalse(actual.isPresent());
    }

    @Test
    public void getBookByIsbnFromDefaultValuesExists(){
        IBook expect = new Book("Title1", "Author1", "978-3-86680-192-9");
        Optional<IMedium> actual =  ResourceManager.dataAccess().getBook("978-3-86680-192-9");
        Assert.assertTrue(actual.isPresent());
        Assert.assertEquals(expect,actual.get());
    }
    @Test
    public void getBookByIsbnFromDefaultValuesNotExists(){
        Optional<IMedium> actual =  ResourceManager.dataAccess().getBook("No");
        Assert.assertFalse(actual.isPresent());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Add">
    @Test
    public void addABookAndDeleteIt(){
        IBook expect = new Book("Title2", "Author2", "978-3-8369-4917-0");
        ResourceManager.dataAccess().add(expect);
        Optional<IMedium> actual =  ResourceManager.dataAccess().getBook("978-3-8369-4917-0");

        Assert.assertEquals(expect,actual.get());
        Assert.assertEquals(expect,ResourceManager.dataAccess().getBooks().get().stream().filter(IBook.class::isInstance)
                .map(IBook.class::cast).filter(f -> f.getIsbn().equals("978-3-8369-4917-0")).findAny().get());
        Assert.assertEquals(expect,ResourceManager.dataAccess().getMediums().stream().filter(f -> f.getTitle().equals("Title2")).findAny().get());

        ResourceManager.dataAccess().remove(expect);

        Optional<IMedium> actual2 =  ResourceManager.dataAccess().getBook("978-3-8369-4917-0");
        Assert.assertFalse(actual2.isPresent());
        Assert.assertFalse(ResourceManager.dataAccess().getBooks().get().stream().filter(IBook.class::isInstance)
                .map(IBook.class::cast).filter(f -> f.getIsbn().equals("978-3-8369-4917-0")).findAny().isPresent());
        Assert.assertFalse(ResourceManager.dataAccess().getMediums().stream().filter(f -> f.getTitle().equals("Title2")).findAny().isPresent());

    }

    @Test
    public void addADiscAndDeleteIt(){
        IDisc expect = new Disc("Disc2", "5055011702189", "Dirctor2", 1);
        ResourceManager.dataAccess().add(expect);
        Optional<IMedium> actual =  ResourceManager.dataAccess().getDisc("5055011702189");

        Assert.assertEquals(expect,actual.get());
        Assert.assertEquals(expect,ResourceManager.dataAccess().getDiscs().get().stream().filter(IDisc.class::isInstance)
                .map(IDisc.class::cast).filter(f -> f.getBarcode().equals("5055011702189")).findAny().get());
        Assert.assertEquals(expect,ResourceManager.dataAccess().getMediums().stream().filter(f -> f.getTitle().equals("Disc2")).findAny().get());

        ResourceManager.dataAccess().remove(expect);

        Optional<IMedium> actual2 =  ResourceManager.dataAccess().getDisc("5055011702189");
        Assert.assertFalse(actual2.isPresent());
        Assert.assertFalse(ResourceManager.dataAccess().getDiscs().get().stream().filter(IDisc.class::isInstance)
                .map(IDisc.class::cast).filter(f -> f.getBarcode().equals("5055011702189")).findAny().isPresent());
        Assert.assertFalse(ResourceManager.dataAccess().getDiscs().get().stream().filter(f -> f.getTitle().equals("Disc2")).findAny().isPresent());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Clear">
    @Test
    public void checkIfClearWorks() {
       ResourceManager.dataAccess().clear();
        Assert.assertEquals(0, ResourceManager.dataAccess().getMediums().size());
        Assert.assertEquals(0, ResourceManager.dataAccess().getBooks().get().size());
        Assert.assertEquals(0, ResourceManager.dataAccess().getDiscs().get().size());

        ResourceManager.dataAccess().add(new Book("Title1", "Author1", "978-3-86680-192-9"));
        ResourceManager.dataAccess().add(new Disc("Disc1", "4059251015567", "Dirctor1", 1));
    }
    // </editor-fold>

}
