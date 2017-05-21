package edu.hm.rfurch.msa.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Admin on 21.05.2017.
 */
public class TokenTest {


    @Test(expected = IllegalArgumentException.class)
    public void constructorOwnerNull() {
       final Token t = new Token(null, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorLiveTimeNegative() {
        final Token t = new Token(new User("test", "test",false), -10);
    }

    @Test
    public void takeTokenTwoTimes() {
        final Token t = new Token(new User("test", "test",false), 10);
        Assert.assertEquals(t.getTokenValue(), t.getTokenValue());
    }

    @Test
    public void sameUserDiffrentTime() throws InterruptedException {
        final User u = new User("test", "test",false);
        final Token t1 = new Token(u, 10);
        Thread.sleep(5);
        final Token t2 = new Token(u, 10);
        Assert.assertNotEquals(t1.getTokenValue(), t2.getTokenValue());
    }

    @Test
    public void sameUserDifferentLiveTime() throws InterruptedException {
        final User u = new User("test", "test",false);
        final Token t1 = new Token(u, 10);
        final Token t2 = new Token(u, 15);
        Assert.assertNotEquals(t1.getTokenValue(), t2.getTokenValue());
    }

    @Test
    public void tokenIsvalidIn20() throws InterruptedException {
        final User u = new User("test", "test",false);
        final Token t1 = new Token(u, 20);
        Assert.assertTrue(t1.isLiving());
        Thread.sleep(25);
        Assert.assertFalse(t1.isLiving());
    }
}
