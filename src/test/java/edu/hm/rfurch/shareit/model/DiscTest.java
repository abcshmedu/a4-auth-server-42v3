package edu.hm.rfurch.shareit.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by Admin on 22.04.2017.
 */
public class DiscTest {
    @Test(expected = IllegalArgumentException.class)
    public void checkPrimeKeyIsNotNull(){
        new Disc("", null,"", 0);
    }

    @Test
    public void checkSettingFinalVars(){
        IDisc b = new Disc("eins", "zwei", "drei", 7);
        Assert.assertEquals("eins",b.getTitle());
        Assert.assertEquals("zwei",b.getBarcode());
        Assert.assertEquals("drei",b.getDirector());
        Assert.assertEquals((Integer)7,b.getFsk());
    }

    @Test
    public void checkEqual(){
        IDisc b1 = new Disc("eins", "zwei", "drei", 1);
        IDisc b2 = new Disc("eins", "zwei", "drei", 1);
        IDisc c1 = new Disc("eins1", "zwei", "drei",1);
        IDisc c2 = new Disc("eins", "zwei1", "drei", 1);
        IDisc c3 = new Disc("eins", "zwei", "drei1",1);
        IDisc c4 = new Disc("eins", "zwei", "drei",42);

        Assert.assertEquals(b1,b1);
        Assert.assertEquals(b1,b2);

        Assert.assertNotEquals(b1,c1);
        Assert.assertNotEquals(b2,c1);

        Assert.assertNotEquals(b1,c2);
        Assert.assertNotEquals(b2,c2);

        Assert.assertNotEquals(b1,c3);
        Assert.assertNotEquals(b2,c3);

        Assert.assertNotEquals(b1,c4);
        Assert.assertNotEquals(b2,c4);

    }

    @Test
    public void checkEasyUpdate(){
        IDisc b1 = new Disc("1", "2", "3",1);
        IDisc b1Tmp = new Disc("1", "2", "3",1);
        IDisc b2 = new Disc("5", "2", "3",2);
        IDisc b2Tmp = new Disc("5", "2", "3",2);

        Optional<IDisc> oDisc = b1.update(b2);
        Assert.assertTrue(oDisc.isPresent());

        Assert.assertEquals("5",oDisc.get().getTitle());
        Assert.assertEquals("2",oDisc.get().getBarcode());
        Assert.assertEquals("3",oDisc.get().getDirector());
        Assert.assertEquals((Integer)2,oDisc.get().getFsk());

        Assert.assertEquals(b1, b1Tmp);
        Assert.assertEquals(b2, b2Tmp);

    }

    @Test
    public void checkEasyUpdateNotSameBook(){
        IDisc b1 = new Disc("1", "2", "3",1);
        IDisc b1Tmp = new Disc("1", "2", "3",1);
        IDisc b2 = new Disc("5", "4", "4",1);
        IDisc b2Tmp = new Disc("5", "4", "4",1);

        Optional<IDisc> oDisc = b1.update(b2);
        Assert.assertFalse(oDisc.isPresent());

        Assert.assertEquals(b1, b1Tmp);
        Assert.assertEquals(b2, b2Tmp);

    }

    @Test
    public void checkHashCode(){
        IDisc b1 = new Disc("1", "2", "3",1);
        IDisc b1Tmp = new Disc("1", "2", "3",1);
        IDisc b2 = new Disc("4", "5", "6",2);
        IDisc b2Tmp = new Disc("4", "5", "6",2);

        Assert.assertEquals(b1.hashCode(), b1Tmp.hashCode());
        Assert.assertEquals(b2.hashCode(), b2Tmp.hashCode());

        Assert.assertNotEquals(b1.hashCode(), b2Tmp.hashCode());
        Assert.assertNotEquals(b2.hashCode(), b1Tmp.hashCode());

    }

    @Test
    public void checkToString(){
        IDisc b1 = new Disc("1", "2", "3",1);
        IDisc b2 = new Disc("4", "5", "6",2);

        Assert.assertEquals("Barcode: 2 - Title: 1 - Director: 3 - Fsk: 1", b1.toString());
        Assert.assertEquals("Barcode: 5 - Title: 4 - Director: 6 - Fsk: 2", b2.toString());


    }
}
