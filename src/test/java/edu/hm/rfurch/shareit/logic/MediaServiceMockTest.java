package edu.hm.rfurch.shareit.logic;

import edu.hm.rfurch.shareit.data.IData;
import edu.hm.rfurch.shareit.model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Admin on 14.06.2017.
 */
public class MediaServiceMockTest {
    final IBook b = new Book("Title1", "Author1", "978-3-86680-192-9");
    final IDisc d = new Disc("Disc1", "4059251015567", "Dirctor1", 1);
    final String wrongBarcoe = "4059251015568";
    IData mockData;
    IMediaService service;
    @Before
    public void setup(){
        this.mockData = mock(IData.class);
        this.service = new MediaService(mockData);
    }


    // <editor-fold defaultstate="collapsed" desc="Get">
    @Test
    public void getMediumsFromDefaultValues(){
        // mock
        Mockito.when(mockData.getMediums()).thenReturn(new ArrayList<IMedium>());
        //
        MediaServiceResult expect = MediaServiceResult.OK.setResponseData(new ArrayList<IMedium>());
        Assert.assertEquals(Optional.of(expect),service.getMediums());
        verify(mockData).getMediums();
    }


    @Test
    public void getBooksFromDefaultValues(){
        // mock
        List<IMedium> l = new ArrayList<IMedium>();
        l.add(b);
        Mockito.when(mockData.getMediums()).thenReturn(l);
        //
        MediaServiceResult expect = MediaServiceResult.OK.setResponseData(l);
        Assert.assertEquals(Optional.of(expect),service.getMediums());

        verify(mockData).getMediums();
    }

    @Test
    public void getBooksFromDefaultValues2(){
        // mock
        List<IMedium> l = new ArrayList<IMedium>();
        l.add(b);
        Mockito.when(mockData.getMediums()).thenReturn(l);
        Mockito.when(mockData.getBooks()).thenCallRealMethod();
        //
        MediaServiceResult expect = MediaServiceResult.OK.setResponseData(l);
        Assert.assertEquals(Optional.of(expect),service.getBooks());

        verify(mockData).getBooks();
    }

    @Test
    public void getDiscsFromDefaultValues(){
        // mock
        List<IMedium> l = new ArrayList<IMedium>();
        l.add(d);
        Mockito.when(mockData.getMediums()).thenReturn(l);
        //
        MediaServiceResult expect = MediaServiceResult.OK.setResponseData(l);
        Assert.assertEquals(Optional.of(expect),service.getMediums());

        verify(mockData).getMediums();
    }
    @Test
    public void getDiscsFromDefaultValues2(){
        // mock
        List<IMedium> l = new ArrayList<IMedium>();
        l.add(d);
        Mockito.when(mockData.getMediums()).thenReturn(l);
        Mockito.when(mockData.getDiscs()).thenCallRealMethod();
        //
        MediaServiceResult expect = MediaServiceResult.OK.setResponseData(l);
        Assert.assertEquals(Optional.of(expect),service.getDiscs());

        verify(mockData).getDiscs();
    }

    @Test
    public void getDiscByBarcodeFromDefaultValuesExists(){
        // mock
        List<IMedium> l = new ArrayList<IMedium>();
        l.add(d);
        Mockito.when(mockData.getMediums()).thenReturn(l);
        Mockito.when(mockData.getDiscs()).thenCallRealMethod();
        Mockito.when(mockData.getDisc(d.getBarcode())).thenCallRealMethod();
        //
        MediaServiceResult expect = MediaServiceResult.OK.setResponseData(d);
        Assert.assertEquals(Optional.of(expect),service.getDisc(d.getBarcode()));

        verify(mockData).getDisc(d.getBarcode());
    }
    @Test
    public void getDiscByBarcodeFromDefaultValuesNotExists(){
        // mock
        List<IMedium> l = new ArrayList<IMedium>();
        l.add(d);
        l.add(b);
        Mockito.when(mockData.getMediums()).thenReturn(l);
        Mockito.when(mockData.getDiscs()).thenCallRealMethod();
        Mockito.when(mockData.getDisc(wrongBarcoe)).thenCallRealMethod();
        //
        MediaServiceResult expect = MediaServiceResult.BadRequest;
        Assert.assertEquals(Optional.empty(),service.getDisc(wrongBarcoe));

        verify(mockData).getDisc(wrongBarcoe);
    }

    @Test
    public void getBookByIsbnFromDefaultValuesExists(){
        // mock
        List<IMedium> l = new ArrayList<IMedium>();
        l.add(d);
        l.add(b);
        Mockito.when(mockData.getMediums()).thenReturn(l);
        Mockito.when(mockData.getBooks()).thenCallRealMethod();
        Mockito.when(mockData.getBook(b.getIsbn())).thenCallRealMethod();
        //
        MediaServiceResult expect = MediaServiceResult.OK.setResponseData(b);
        Assert.assertEquals(Optional.of(expect),service.getBook(b.getIsbn()));

        verify(mockData).getBook(b.getIsbn());
    }
    @Test
    public void getBookByIsbnFromDefaultValuesNotExists(){
        // mock
        List<IMedium> l = new ArrayList<IMedium>();
        l.add(d);
        l.add(b);
        Mockito.when(mockData.getMediums()).thenReturn(l);
        Mockito.when(mockData.getBooks()).thenCallRealMethod();
        Mockito.when(mockData.getBook(wrongBarcoe)).thenCallRealMethod();
        //
        MediaServiceResult expect = MediaServiceResult.BadRequest;
        Assert.assertEquals(Optional.empty(),service.getBook(wrongBarcoe));

        verify(mockData).getBook(wrongBarcoe);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Add">
    @Test
    public void addABookAndDeleteIt() {
        IBook expect = new Book("Title2", "Author2", "978-3-12-732320-7");
        when(this.mockData.add(expect)).thenReturn(Optional.of(true));
        this.service.addBook(expect);
        verify(mockData).add(expect);
        this.service.removeBook(expect);
        verify(mockData).remove(expect);

    }

    @Test
    public void addADiscAndDeleteIt(){
        IDisc expect = new Disc("Disc2", "5055011702189", "Dirctor2", 1);
        when(this.mockData.add(expect)).thenReturn(Optional.of(true));
        this.service.addDisc(expect);
        verify(mockData).add(expect);
        this.service.removeDisc(expect);
        verify(mockData).remove(expect);
    }
    // </editor-fold>

    @Test
    public void updateBook(){
        IBook expect1 = new Book("Title2", "Author2", b.getIsbn());
        when(this.mockData.add(expect1)).thenReturn(Optional.of(true));
        when(this.mockData.remove(b)).thenReturn(Optional.of(true));
        when(this.mockData.getBook(b.getIsbn())).thenReturn(Optional.of(b));
        this.service.updateBook(expect1);
        verify(mockData).add(expect1);
        verify(mockData).remove(b);

    }

    @Test
    public void updateDisc(){
        IDisc expect1 =  new Disc("Disc2", d.getBarcode(), "Dirctor2", 2);
        when(this.mockData.add(expect1)).thenReturn(Optional.of(true));
        when(this.mockData.remove(b)).thenReturn(Optional.of(true));
        when(this.mockData.getDisc(d.getBarcode())).thenReturn(Optional.of(d));
        this.service.updateDisc(expect1);
        verify(mockData).add(expect1);
        verify(mockData).remove(d);

    }



    //<editor-fold desc="Exceptions">
    @Test(expected = NullPointerException.class)
    public void addBookException(){
       this.service.addBook(null);
    }

    @Test(expected = NullPointerException.class)
    public void addDiscException(){
        this.service.addDisc(null);
    }

    @Test(expected = NullPointerException.class)
    public void bookUpdateException(){
        this.service.updateBook(null);
    }

    @Test(expected = NullPointerException.class)
    public void discUpdateException(){
        this.service.updateDisc(null);
    }

    @Test(expected = NullPointerException.class)
    public void getBookException(){
        this.service.getBook(null);
    }

    @Test(expected = NullPointerException.class)
    public void getDiscException(){
        this.service.getDisc(null);
    }

    @Test(expected = NullPointerException.class)
    public void removeBookException(){
        this.service.removeBook(null);
    }

    @Test(expected = NullPointerException.class)
    public void removeDiscException(){
        this.service.removeDisc(null);
    }
    //</editor-fold>

}
