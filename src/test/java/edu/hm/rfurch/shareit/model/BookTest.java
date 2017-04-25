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
        IBook b = new Book("eins", "zwei", "drei");
        Assert.assertEquals("eins",b.getTitle());
        Assert.assertEquals("zwei",b.getAuthor());
        Assert.assertEquals("drei",b.getIsbn());
    }

    @Test
    public void checkEqual(){
        IBook b1 = new Book("eins", "zwei", "drei");
        IBook b2 = new Book("eins", "zwei", "drei");
        IBook c1 = new Book("eins1", "zwei", "drei");
        IBook c2 = new Book("eins", "zwei1", "drei");
        IBook c3 = new Book("eins", "zwei", "drei1");

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
        IBook b1 = new Book("1", "2", "3");
        IBook b1Tmp = new Book("1", "2", "3");
        IBook b2 = new Book("5", "4", "3");
        IBook b2Tmp = new Book("5", "4", "3");

        Optional<IBook> oBook = b1.update(b2);
        Assert.assertTrue(oBook.isPresent());

        Assert.assertEquals("5",oBook.get().getTitle());
        Assert.assertEquals("4",oBook.get().getAuthor());
        Assert.assertEquals("3",oBook.get().getIsbn());

        Assert.assertEquals(b1, b1Tmp);
        Assert.assertEquals(b2, b2Tmp);

    }
    @Test
    public void checkEasyUpdateOnlyNullFields(){
        IBook b1 = new Book("1", "2", "3");
        IBook b2 = new Book("5", null, "3");

        Optional<IBook> oBook = b1.update(b2);
        Assert.assertTrue(oBook.isPresent());

        Assert.assertEquals("5",oBook.get().getTitle());
        Assert.assertEquals("2",oBook.get().getAuthor());
        Assert.assertEquals("3",oBook.get().getIsbn());



    }

    @Test
    public void checkEasyUpdateNotSameBook(){
        IBook b1 = new Book("1", "2", "3");
        IBook b1Tmp = new Book("1", "2", "3");
        IBook b2 = new Book("5", "4", "4");
        IBook b2Tmp = new Book("5", "4", "4");

        Optional<IBook> oBook = b1.update(b2);
        Assert.assertFalse(oBook.isPresent());

        Assert.assertEquals(b1, b1Tmp);
        Assert.assertEquals(b2, b2Tmp);

    }

    @Test
    public void checkHashCode(){
        IBook b1 = new Book("1", "2", "3");
        IBook b1Tmp = new Book("1", "2", "3");
        IBook b2 = new Book("4", "5", "6");
        IBook b2Tmp = new Book("4", "5", "6");

        Assert.assertEquals(b1.hashCode(), b1Tmp.hashCode());
        Assert.assertEquals(b2.hashCode(), b2Tmp.hashCode());

        Assert.assertNotEquals(b1.hashCode(), b2Tmp.hashCode());
        Assert.assertNotEquals(b2.hashCode(), b1Tmp.hashCode());

    }

    @Test
    public void checkToString(){
        IBook b1 = new Book("1", "2", "3");
        IBook b2 = new Book("4", "5", "6");

        Assert.assertEquals("ISBN: 3 - Title: 1 - Author: 2", b1.toString());
        Assert.assertEquals("ISBN: 6 - Title: 4 - Author: 5", b2.toString());


    }
}
