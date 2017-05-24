package edu.hm.rfurch.msa.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Admin on 21.05.2017.
 */
public class UserTest {

    @Test(expected = IllegalArgumentException.class)
    public void constructorNameNull() {
        final User t = new User(null, "test", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorPasswordNull() {
        final User t = new User("test",null,  true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorIsAdminNull() {
        final User t = new User("test","test" , null);
    }

    @Test
    public void checkSetter() {
        final User t = new User("test","test" , true);
        Assert.assertEquals("test", t.getName());
        Assert.assertEquals(true, t.isAdmin());
    }

    @Test
    public void equalsCheck() {
        final User t = new User("test","test" , true);
        final User t1 = new User("test1","test" , true);
        final User t2 = new User("test","tes1t" , true);
        final User t3 = new User("test","test" , false);

        Assert.assertEquals(t,t);
        Assert.assertEquals(t1,t1);
        Assert.assertEquals(t2,t2);
        Assert.assertEquals(t3,t3);
        Assert.assertEquals(t,t3);

        Assert.assertNotEquals(t,t1);
        Assert.assertNotEquals(t,t2);

    }

    @Test
    public void checkToString() {
        final User t = new User("test","test" , true);
        System.out.println(t);
        Assert.assertEquals("User: {name=test, isAdmin=true}", t.toString());
    }



}
