package edu.hm.rfurch.shareit.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by Admin on 22.04.2017.
 */
public class BookTest {

    @Test(expected = IllegalArgumentException.class)
    public void checkPrimeKeyIsNotNull(){
        new Book("", "", null);
    }

    @Test
    public void checkSettingFinalVars(){
        IBook b = new Book("eins", "zwei", "978-3-86680-192-9");
        Assert.assertEquals("eins",b.getTitle());
        Assert.assertEquals("zwei",b.getAuthor());
        Assert.assertEquals("9783866801929",b.getIsbn());
    }

    @Test
    public void checkEqual(){
        IBook b1 = new Book("eins", "zwei", "978-3-12-732320-7");
        IBook b2 = new Book("eins", "zwei", "978-3-12-732320-7");
        IBook c1 = new Book("eins1", "zwei", "978-3-12-732320-7");
        IBook c2 = new Book("eins", "zwei1", "978-3-12-732320-7");
        IBook c3 = new Book("eins", "zwei", "978-3-86680-192-9");

        Assert.assertEquals(b1,b1);
        Assert.assertEquals(b1,b2);

        Assert.assertNotEquals(b1,c1);
        Assert.assertNotEquals(b2,c1);

        Assert.assertNotEquals(b1,c2);
        Assert.assertNotEquals(b2,c2);

        Assert.assertNotEquals(b1,c3);
        Assert.assertNotEquals(b2,c3);

    }

    @Test
    public void checkEasyUpdate(){
        IBook b1 = new Book("1", "2", "978-3-86680-192-9");
        IBook b1Tmp = new Book("1", "2", "978-3-86680-192-9");
        IBook b2 = new Book("5", "4", "978-3-86680-192-9");
        IBook b2Tmp = new Book("5", "4", "978-3-86680-192-9");

        Optional<IBook> oBook = b1.update(b2);
        Assert.assertTrue(oBook.isPresent());

        Assert.assertEquals("5",oBook.get().getTitle());
        Assert.assertEquals("4",oBook.get().getAuthor());
        Assert.assertEquals("9783866801929",oBook.get().getIsbn());

        Assert.assertEquals(b1, b1Tmp);
        Assert.assertEquals(b2, b2Tmp);

    }
    @Test
    public void checkEasyUpdateOnlyNullFields(){
        IBook b1 = new Book("1", "2", "978-3-86680-192-9");
        IBook b2 = new Book("5", null, "978-3-86680-192-9");

        Optional<IBook> oBook = b1.update(b2);
        Assert.assertTrue(oBook.isPresent());

        Assert.assertEquals("5",oBook.get().getTitle());
        Assert.assertEquals("2",oBook.get().getAuthor());
        Assert.assertEquals("9783866801929",oBook.get().getIsbn());



    }

    @Test
    public void checkEasyUpdateNotSameBook(){
        IBook b1 = new Book("1", "2", "978-3-86680-192-9");
        IBook b1Tmp = new Book("1", "2", "978-3-86680-192-9");
        IBook b2 = new Book("5", "4", "978-3-12-732320-7");
        IBook b2Tmp = new Book("5", "4", "978-3-12-732320-7");

        Optional<IBook> oBook = b1.update(b2);
        Assert.assertFalse(oBook.isPresent());

        Assert.assertEquals(b1, b1Tmp);
        Assert.assertEquals(b2, b2Tmp);

    }

    @Test
    public void checkHashCode(){
        IBook b1 = new Book("1", "2", "978-3-86680-192-9");
        IBook b1Tmp = new Book("1", "2", "978-3-86680-192-9");
        IBook b2 = new Book("4", "5", "978-3-12-732320-7");
        IBook b2Tmp = new Book("4", "5", "978-3-12-732320-7");

        Assert.assertEquals(b1.hashCode(), b1Tmp.hashCode());
        Assert.assertEquals(b2.hashCode(), b2Tmp.hashCode());

        Assert.assertNotEquals(b1.hashCode(), b2Tmp.hashCode());
        Assert.assertNotEquals(b2.hashCode(), b1Tmp.hashCode());

    }

    @Test
    public void checkToString(){
        IBook b1 = new Book("1", "2", "978-3-86680-192-9");
        IBook b2 = new Book("4", "5", "978-3-12-732320-7");

        Assert.assertEquals("ISBN: 9783866801929 - Title: 1 - Author: 2", b1.toString());
        Assert.assertEquals("ISBN: 9783127323207 - Title: 4 - Author: 5", b2.toString());
    }
    
    @Test
    public void isbnCheckValid() {
    	Assert.assertTrue(IBook.validISBN("978-3-86680-192-9"));
    }
    
    @Test
    public void isbnCheckValidZeroChecksum() {
    	Assert.assertTrue(IBook.validISBN("978-3-8369-4917-0"));
    }
    
    @Test
    public void isbnCheckInValidChecksum() {
    	Assert.assertFalse(IBook.validISBN("978-3-86680-192-8"));
    }
    
    @Test
    public void isbnCheckInValid() {
    	Assert.assertFalse(IBook.validISBN("978-3-87680-192-9"));
    }
    
    @Test
    public void isbnCheckText() {
    	Assert.assertFalse(IBook.validISBN("Text"));
    }
    
    @Test
    public void isbnCheckFourParts() {
    	Assert.assertFalse(IBook.validISBN("978-3-192-3"));
    }
    
    @Test
    public void isbnCheckToShort() {
    	Assert.assertFalse(IBook.validISBN("978-3-86680-92-8"));
    }
}
