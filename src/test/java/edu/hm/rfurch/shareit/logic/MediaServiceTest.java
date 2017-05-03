package edu.hm.rfurch.shareit.logic;

import edu.hm.rfurch.shareit.model.*;
import org.json.JSONObject;
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
        expects.add(new Book("Title1", "Author1", "978-3-86680-192-9"));
        expects.add(new Disc("Disc1", "4059251015567", "Dirctor1", 1));

        Collection<IMedium> actuals = new MediaService().getMediums().get().getResponseData();
        System.out.println("-----------");
        System.out.println(new JSONObject(new MediaService().getMediums().get().getResponse()).get("entity").toString());
        System.out.println("-----------");
        Assert.assertArrayEquals(expects.toArray(), actuals.toArray());
    }

    @Test
    public void getBooksFromDefaultValues(){
        List<IBook> expects = new ArrayList<>();
        expects.add(new Book("Title1", "Author1", "978-3-86680-192-9"));
        Collection<? extends IMedium> actuals = new MediaService().getBooks().get().getResponseData();
        Assert.assertArrayEquals(expects.toArray(),actuals.toArray());
    }

    @Test
    public void getDiscsFromDefaultValues(){
        List<IDisc> expects = new ArrayList<>();
        expects.add(new Disc("Disc1", "4059251015567", "Dirctor1", 1));
        Collection<? extends IMedium> actuals = new MediaService().getDiscs().get().getResponseData();
        Assert.assertArrayEquals(expects.toArray(),actuals.toArray());
    }

    @Test
    public void getDiscByBarcodeFromDefaultValuesExists(){
        IDisc expect = new Disc("Disc1", "4059251015567", "Dirctor1", 1);
        Optional<IMedium> actual =  new MediaService().getDiscs().get().getResponseData().stream().findFirst();
        Assert.assertTrue(actual.isPresent());
        Assert.assertEquals(expect,actual.get());
    }
    @Test
    public void getDiscByBarcodeFromDefaultValuesNotExists(){
        IDisc expect = new Disc("", "No", "", 1);
        Optional<MediaServiceResult> actual =   new MediaService().getDisc(expect.getBarcode());
        Assert.assertFalse(actual.isPresent());
    }

    @Test
    public void getBookByIsbnFromDefaultValuesExists(){
        IBook expect = new Book("Title1", "Author1", "978-3-86680-192-9");
        Optional<IMedium> actual =  new MediaService().getBook(expect.getIsbn()).get().getResponseData().stream().findAny();
        Assert.assertTrue(actual.isPresent());
        Assert.assertEquals(expect,actual.get());
    }
    @Test
    public void getBookByIsbnFromDefaultValuesNotExists(){
        IBook expect = new Book("Title1", "Author1", "978-3-8369-4231-7");
        Optional<MediaServiceResult> actual =  new MediaService().getBook(expect.getIsbn());
        Assert.assertFalse(actual.isPresent());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Add">
    @Test
    public void addABookAndDeleteIt(){
        IBook expect = new Book("Title2", "Author2", "978-3-12-732320-7");
        new MediaService().addBook(expect);
        Optional<IMedium> actual =  new MediaService().getBook(expect.getIsbn()).get().getResponseData().stream().findAny();

        Assert.assertEquals(expect,actual.get());
        Assert.assertEquals(expect,new MediaService().getBooks().get().getResponseData().stream().filter(IBook.class::isInstance)
                .map(IBook.class::cast).filter(f -> f.getIsbn().equals("978-3-12-732320-7")).findAny().get());
        Assert.assertEquals(expect,new MediaService().getMediums().get().getResponseData().stream().filter(f -> f.getTitle().equals("Title2")).findAny().get());

        new MediaService().removeBook(expect);

        Optional<MediaServiceResult> actual2 =  new MediaService().getBook(expect.getIsbn());
        Assert.assertFalse(actual2.isPresent());
        Assert.assertFalse(new MediaService().getBooks().get().getResponseData().stream().filter(IBook.class::isInstance)
                .map(IBook.class::cast).filter(f -> f.getIsbn().equals("978-3-12-732320-7")).findAny().isPresent());
        Assert.assertFalse(new MediaService().getMediums().get().getResponseData().stream().filter(f -> f.getTitle().equals("Title2")).findAny().isPresent());

    }

    @Test
    public void addADiscAndDeleteIt(){
        IDisc expect = new Disc("Disc2", "5055011702189", "Dirctor2", 1);
        new MediaService().addDisc(expect);
        Optional<IMedium> actual = new MediaService().getDisc(expect.getBarcode()).get().getResponseData().stream().findAny();

        Assert.assertEquals(expect,actual.get());
        Assert.assertEquals(expect,new MediaService().getDiscs().get().getResponseData().stream().filter(IDisc.class::isInstance)
                .map(IDisc.class::cast).filter(f -> f.getBarcode().equals("5055011702189")).findAny().get());
        Assert.assertEquals(expect,new MediaService().getMediums().get().getResponseData().stream().filter(f -> f.getTitle().equals("Disc2")).findAny().get());


        new MediaService().removeDisc(expect);

        Optional<MediaServiceResult> actual2 =  new MediaService().getDisc(expect.getBarcode());
        Assert.assertFalse(actual2.isPresent());
        Assert.assertFalse(new MediaService().getDiscs().get().getResponseData().stream().filter(f -> f.getTitle().equals("Disc2")).findAny().isPresent());
    }
    // </editor-fold>




}
