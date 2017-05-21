package edu.hm.rfurch.msa.data;

import edu.hm.rfurch.msa.model.Token;
import edu.hm.rfurch.msa.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Admin on 21.05.2017.
 */
public class ResourceTest {

    private User admin;
    private static final String NAME = "name1";
    private static final String PASSWORD = "pw1";
    private User dummy;

    private Token longLiveToken;
    private Token deadToken;
    @Before
    public void createUserBefore() throws InterruptedException {
        this.admin = new User(NAME, PASSWORD, true);
        this.dummy = new User(NAME+"2", PASSWORD+"2", true);
        this.longLiveToken = new Token(admin, Integer.MAX_VALUE);
        this.deadToken = new Token(admin, 1);
        Thread.sleep(5);
    }

    @Test
    public void addNewUserAndGetIt(){
        ResourceManager.dataAccess().addUser(admin);
        final User u = ResourceManager.dataAccess().getUser(NAME);
        Assert.assertEquals(admin,u);
    }
    @Test
    public void addNewUserTwice(){
        ResourceManager.dataAccess().addUser(admin);
        ResourceManager.dataAccess().addUser(admin);
        ResourceManager.dataAccess().addUser(admin);
    }

    @Test
    public void checkExists(){
        ResourceManager.dataAccess().addUser(admin);
        Assert.assertTrue(ResourceManager.dataAccess().exists(admin));
        Assert.assertTrue(ResourceManager.dataAccess().exists(NAME, PASSWORD));
        Assert.assertTrue(ResourceManager.dataAccess().exists(NAME));
        final User u = ResourceManager.dataAccess().getUser(NAME);
        Assert.assertEquals(admin,u);
    }

    @Test
    public void addNewToken(){
        ResourceManager.dataAccess().addUser(admin);
        Assert.assertFalse(ResourceManager.dataAccess().hasValidToken(admin));
        ResourceManager.dataAccess().addToken(admin, longLiveToken);
        Assert.assertNotNull(ResourceManager.dataAccess().getToken(admin));
        Assert.assertEquals(longLiveToken, ResourceManager.dataAccess().getToken(admin));
    }
}
