package edu.hm.rfurch.shareit.logic;

import edu.hm.rfurch.shareit.logic.MediaService;
import edu.hm.rfurch.shareit.logic.MediaServiceResult;
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
        expects.add(new Book("Title1", "Author1", "1"));
        expects.add(new Disc("Disc1", "Code1", "Dirctor1", 1));

        Collection<IMedium> actuals = new MediaService().getMediums().get().getResponseData();
        System.out.println(new JSONObject(new MediaService().getMediums().get().getResponse()).get("entity").toString());
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
    @Test
    public void getDiscByBarcodeFromDefaultValuesNotExists(){
        IDisc expect = new Disc("", "No", "", 1);
        Optional<MediaServiceResult> actual =   new MediaService().getDisc(expect);
        Assert.assertFalse(actual.isPresent());
    }

    @Test
    public void getBookByIsbnFromDefaultValuesExists(){
        IBook expect = new Book("Title1", "Author1", "1");
        Optional<IMedium> actual =  new MediaService().getBook(expect).get().getResponseData().stream().findAny();
        Assert.assertTrue(actual.isPresent());
        Assert.assertEquals(expect,actual.get());
    }
    @Test
    public void getBookByIsbnFromDefaultValuesNotExists(){
        IBook expect = new Book("Title1", "Author1", "No");
        Optional<MediaServiceResult> actual =  new MediaService().getBook(expect);
        Assert.assertFalse(actual.isPresent());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Add">
    @Test
    public void addABookAndDeleteIt(){
        IBook expect = new Book("Title2", "Author2", "2");
        new MediaService().addBook(expect);
        Optional<IMedium> actual =  new MediaService().getBook(expect).get().getResponseData().stream().findAny();

        Assert.assertEquals(expect,actual.get());
        Assert.assertEquals(expect,new MediaService().getBooks().get().getResponseData().stream().filter(IBook.class::isInstance)
                .map(IBook.class::cast).filter(f -> f.getIsbn().equals("2")).findAny().get());
        Assert.assertEquals(expect,new MediaService().getMediums().get().getResponseData().stream().filter(f -> f.getTitle().equals("Title2")).findAny().get());

        new MediaService().removeBook(expect);

        Optional<MediaServiceResult> actual2 =  new MediaService().getBook(expect);
        Assert.assertFalse(actual2.isPresent());
        Assert.assertFalse(new MediaService().getBooks().get().getResponseData().stream().filter(IBook.class::isInstance)
                .map(IBook.class::cast).filter(f -> f.getIsbn().equals("2")).findAny().isPresent());
        Assert.assertFalse(new MediaService().getMediums().get().getResponseData().stream().filter(f -> f.getTitle().equals("Title2")).findAny().isPresent());

    }

    @Test
    public void addADiscAndDeleteIt(){
        IDisc expect = new Disc("Disc2", "Code2", "Dirctor2", 1);
        new MediaService().addDisc(expect);
        Optional<IMedium> actual = new MediaService().getDisc(expect).get().getResponseData().stream().findAny();

        Assert.assertEquals(expect,actual.get());
        Assert.assertEquals(expect,new MediaService().getDiscs().get().getResponseData().stream().filter(IDisc.class::isInstance)
                .map(IDisc.class::cast).filter(f -> f.getBarcode().equals("Code2")).findAny().get());
        Assert.assertEquals(expect,new MediaService().getMediums().get().getResponseData().stream().filter(f -> f.getTitle().equals("Disc2")).findAny().get());


        new MediaService().removeDisk(expect);

        Optional<MediaServiceResult> actual2 =  new MediaService().getDisc(expect);
        Assert.assertFalse(actual2.isPresent());
        Assert.assertFalse(new MediaService().getDiscs().get().getResponseData().stream().filter(f -> f.getTitle().equals("Disc2")).findAny().isPresent());
    }
    // </editor-fold>




}
