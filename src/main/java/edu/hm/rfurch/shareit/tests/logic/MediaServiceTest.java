package edu.hm.rfurch.shareit.tests.logic;

import edu.hm.rfurch.shareit.data.ResourceManager;
import edu.hm.rfurch.shareit.logic.MediaService;
import edu.hm.rfurch.shareit.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 23.04.2017.
 */
public class MediaServiceTest {

    // <editor-fold defaultstate="collapsed" desc="Get">
    @Test
    public void getMediumsFromDefaultValues(){
        List<IMedium> expects = new ArrayList<>();
        expects.add(new Book("Title1", "Author1", "1"));
        expects.add(new Disc("Disc1", "Code1", "Dirctor1", 1));

        Collection<? extends IMedium> actuals = new MediaService().getMediums().get().getResponseData();
        Assert.assertArrayEquals(expects.toArray(), actuals.toArray());
    }

    @Test
    public void getBooksFromDefaultValues(){
        List<IBook> expects = new ArrayList<>();
        expects.add(new Book("Title1", "Author1", "1"));
        Collection<? extends IMedium> actuals = new MediaService().getBooks().get().getResponseData();
        Assert.assertArrayEquals(expects.toArray(),actuals.toArray());
    }

    @Test
    public void getDiscsFromDefaultValues(){
        List<IDisc> expects = new ArrayList<>();
        expects.add(new Disc("Disc1", "Code1", "Dirctor1", 1));
        Collection<? extends IMedium> actuals = new MediaService().getDiscs().get().getResponseData();
        Assert.assertArrayEquals(expects.toArray(),actuals.toArray());
    }

    @Test
    public void getDiscByBarcodeFromDefaultValuesExists(){
        IDisc expect = new Disc("Disc1", "Code1", "Dirctor1", 1);
        Optional<IMedium> actual =  new MediaService().getDiscs().get().getResponseData().stream().findFirst();
        Assert.assertTrue(actual.isPresent());
        Assert.assertEquals(expect,actual.get());
    }
   /** @Test
    public void getDiscByBarcodeFromDefaultValuesNotExists(){
        Optional<IDisc> actual =  ResourceManager.dataAccess().getDisc("No");
        Assert.assertFalse(actual.isPresent());
    }

    @Test
    public void getBookByIsbnFromDefaultValuesExists(){
        IBook expect = new Book("Title1", "Author1", "1");
        Optional<IBook> actual =  ResourceManager.dataAccess().getBook("1");
        Assert.assertTrue(actual.isPresent());
        Assert.assertEquals(expect,actual.get());
    }
    @Test
    public void getBookByIsbnFromDefaultValuesNotExists(){
        Optional<IBook> actual =  ResourceManager.dataAccess().getBook("No");
        Assert.assertFalse(actual.isPresent());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Add">
    @Test
    public void addABookAndDeleteIt(){
        IBook expect = new Book("Title2", "Author2", "2");
        ResourceManager.dataAccess().add(expect);
        Optional<IBook> actual =  ResourceManager.dataAccess().getBook("2");

        Assert.assertEquals(expect,actual.get());
        Assert.assertEquals(expect,ResourceManager.dataAccess().getBooks().get().stream().filter(f -> f.getIsbn().equals("2")).findAny().get());
        Assert.assertEquals(expect,ResourceManager.dataAccess().getMediums().stream().filter(f -> f.getTitle().equals("Title2")).findAny().get());

        ResourceManager.dataAccess().remove(expect);

        Optional<IBook> actual2 =  ResourceManager.dataAccess().getBook("2");
        Assert.assertFalse(actual2.isPresent());
        Assert.assertFalse(ResourceManager.dataAccess().getBooks().get().stream().filter(f -> f.getIsbn().equals("2")).findAny().isPresent());
        Assert.assertFalse(ResourceManager.dataAccess().getMediums().stream().filter(f -> f.getTitle().equals("Title2")).findAny().isPresent());

    }

    @Test
    public void addADiscAndDeleteIt(){
        IDisc expect = new Disc("Disc2", "Code2", "Dirctor2", 1);
        ResourceManager.dataAccess().add(expect);
        Optional<IDisc> actual =  ResourceManager.dataAccess().getDisc("Code2");

        Assert.assertEquals(expect,actual.get());
        Assert.assertEquals(expect,ResourceManager.dataAccess().getDiscs().get().stream().filter(f -> f.getBarcode().equals("Code2")).findAny().get());
        Assert.assertEquals(expect,ResourceManager.dataAccess().getMediums().stream().filter(f -> f.getTitle().equals("Disc2")).findAny().get());

        ResourceManager.dataAccess().remove(expect);

        Optional<IDisc> actual2 =  ResourceManager.dataAccess().getDisc("Code2");
        Assert.assertFalse(actual2.isPresent());
        Assert.assertFalse(ResourceManager.dataAccess().getDiscs().get().stream().filter(f -> f.getBarcode().equals("Code2")).findAny().isPresent());
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

        ResourceManager.dataAccess().add(new Book("Title1", "Author1", "1"));
        ResourceManager.dataAccess().add(new Disc("Disc1", "Code1", "Dirctor1", 1));
    }
    // </editor-fold>
    **/

}
