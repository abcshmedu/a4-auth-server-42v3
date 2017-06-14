package edu.hm.rfurch.shareit.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import edu.hm.rfurch.shareit.model.Book;
import edu.hm.rfurch.shareit.model.Disc;
import edu.hm.rfurch.shareit.model.IBook;
import edu.hm.rfurch.shareit.model.IDisc;
import edu.hm.rfurch.shareit.model.IMedium;

public class HibernateMediaResourceTest {

	private IData data;
	@Before
	public void setup() {
		Injector injector = Guice.createInjector(new HibernateMediaResourceModule());
		this.data = injector.getInstance(IData.class);
		data.add(new Book("Title1", "Author1", "9783866801929"));
		data.add(new Disc("Disc1", "4059251015567", "Dirctor1", 1));
	}
	
	
    @Test
    public void getMediumsFromDefaultValues(){
        List<IMedium> expects = new ArrayList<>();
        expects.add(new Book("Title1", "Author1", "978-3-86680-192-9"));
        expects.add(new Disc("Disc1", "4059251015567", "Dirctor1", 1));
        List<IMedium> actuals = data.getMediums();
        System.out.println(actuals);
        Assert.assertArrayEquals(expects.toArray(),actuals.toArray());
    }

    @Test
    public void getBooksFromDefaultValues(){
        List<IBook> expects = new ArrayList<>();
        expects.add(new Book("Title1", "Author1", "978-3-86680-192-9"));
        List<IMedium> actuals = data.getBooks().get();
        Assert.assertArrayEquals(expects.toArray(),actuals.toArray());
    }

    @Test
    public void getDiscsFromDefaultValues(){
        List<IDisc> expects = new ArrayList<>();
        expects.add(new Disc("Disc1", "4059251015567", "Dirctor1", 1));
        List<IMedium> actuals = data.getDiscs().get();
        Assert.assertArrayEquals(expects.toArray(),actuals.toArray());
    }

    @Test
    public void getDiscByBarcodeFromDefaultValuesExists(){
        IDisc expect = new Disc("Disc1", "4059251015567", "Dirctor1", 1);
        Optional<IMedium> actual = data.getDisc("4059251015567");
        Assert.assertTrue(actual.isPresent());
        Assert.assertEquals(expect,actual.get());
    }
    @Test
    public void getDiscByBarcodeFromDefaultValuesNotExists(){
        Optional<IMedium> actual = data.getDisc("No");
        Assert.assertFalse(actual.isPresent());
    }

    @Test
    public void getBookByIsbnFromDefaultValuesExists(){
        IBook expect = new Book("Title1", "Author1", "978-3-86680-192-9");
        Optional<IMedium> actual = data.getBook("978-3-86680-192-9");
        Assert.assertTrue(actual.isPresent());
        Assert.assertEquals(expect,actual.get());
    }
    @Test
    public void getBookByIsbnFromDefaultValuesNotExists(){
        Optional<IMedium> actual = data.getBook("No");
        Assert.assertFalse(actual.isPresent());
    }
	
    @Test
    public void addABookAndDeleteIt(){
        IBook expect = new Book("Title2", "Author2", "978-3-8369-4917-0");
        assertTrue(data.add(expect).get());
        Optional<IMedium> actual = data.getBook("978-3-8369-4917-0");

        Assert.assertEquals(expect,actual.get());
        Assert.assertEquals(expect,data.getBooks().get().stream().filter(IBook.class::isInstance)
                .map(IBook.class::cast).filter(f -> f.getIsbn().equals("9783836949170")).findAny().get());
        Assert.assertEquals(expect,data.getMediums().stream().filter(f -> f.getTitle().equals("Title2")).findAny().get());

        assertTrue(data.remove(expect).get());

        Optional<IMedium> actual2 =  data.getBook("978-3-8369-4917-0");
        Assert.assertFalse(actual2.isPresent());
        Assert.assertFalse(data.getBooks().get().stream().filter(IBook.class::isInstance)
                .map(IBook.class::cast).filter(f -> f.getIsbn().equals("978-3-8369-4917-0")).findAny().isPresent());
        Assert.assertFalse(data.getMediums().stream().filter(f -> f.getTitle().equals("Title2")).findAny().isPresent());

    }
    
    @Test
    public void addADiscAndDeleteIt(){
        IDisc expect = new Disc("Disc2", "5055011702189", "Dirctor2", 1);
        data.add(expect);
        Optional<IMedium> actual =  data.getDisc("5055011702189");

        Assert.assertEquals(expect,actual.get());
        Assert.assertEquals(expect,data.getDiscs().get().stream().filter(IDisc.class::isInstance)
                .map(IDisc.class::cast).filter(f -> f.getBarcode().equals("5055011702189")).findAny().get());
        Assert.assertEquals(expect,data.getMediums().stream().filter(f -> f.getTitle().equals("Disc2")).findAny().get());

        data.remove(expect);

        Optional<IMedium> actual2 =  data.getDisc("5055011702189");
        Assert.assertFalse(actual2.isPresent());
        Assert.assertFalse(data.getDiscs().get().stream().filter(IDisc.class::isInstance)
                .map(IDisc.class::cast).filter(f -> f.getBarcode().equals("5055011702189")).findAny().isPresent());
        Assert.assertFalse(data.getDiscs().get().stream().filter(f -> f.getTitle().equals("Disc2")).findAny().isPresent());
    }
    
    @Test
    public void addBookTwice(){
        IBook expect = new Book("Title2", "Author2", "978-3-8369-4917-0");
        Assert.assertEquals(true, data.add(expect).get());
        Optional<IMedium> actual = data.getBook("978-3-8369-4917-0");

        Assert.assertEquals(expect,actual.get());
        Assert.assertEquals(false, data.add(expect).get());
        data.remove(expect);
    }

    @Test
    public void checkIfClearWorks() {
       data.clear();
        Assert.assertEquals(0, data.getMediums().size());
        Assert.assertEquals(0, data.getBooks().get().size());
        Assert.assertEquals(0, data.getDiscs().get().size());

        data.add(new Book("Title1", "Author1", "978-3-86680-192-9"));
        data.add(new Disc("Disc1", "4059251015567", "Dirctor1", 1));
    }

}
