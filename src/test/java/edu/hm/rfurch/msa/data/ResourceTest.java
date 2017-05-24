package edu.hm.rfurch.msa.data;

import edu.hm.rfurch.msa.model.Token;
import edu.hm.rfurch.msa.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;

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
        this.dummy = new User(NAME+"2", PASSWORD+"2", false);
        this.longLiveToken = new Token(admin, Integer.MAX_VALUE);
        this.deadToken = new Token(admin, 1);
        Thread.sleep(5);
    }

    @Test
    public void addNewUserAndGetIt(){
        ResourceManager.dataAccess().clear();
        ResourceManager.dataAccess().addUser(admin);
        final User u = ResourceManager.dataAccess().getUser(NAME);
        Assert.assertEquals(admin,u);
    }
    @Test
    public void addNewUserTwice(){
        ResourceManager.dataAccess().clear();
        ResourceManager.dataAccess().addUser(admin);
        ResourceManager.dataAccess().addUser(admin);
        ResourceManager.dataAccess().addUser(admin);
        Assert.assertEquals(1,ResourceManager.dataAccess().count());
    }

    @Test
    public void checkExists(){
        ResourceManager.dataAccess().clear();
        ResourceManager.dataAccess().addUser(admin);
        Assert.assertTrue(ResourceManager.dataAccess().exists(admin));
        Assert.assertTrue(ResourceManager.dataAccess().exists(NAME, PASSWORD));
        Assert.assertTrue(ResourceManager.dataAccess().exists(NAME));
        final User u = ResourceManager.dataAccess().getUser(NAME);
        Assert.assertEquals(admin,u);
    }

    @Test
    public void tokenDealing(){
        ResourceManager.dataAccess().clear();
        ResourceManager.dataAccess().addUser(admin);
        Assert.assertFalse(ResourceManager.dataAccess().hasValidToken(admin));
        ResourceManager.dataAccess().addToken(admin, longLiveToken);
        Assert.assertNotNull(ResourceManager.dataAccess().getToken(admin));
        Assert.assertEquals(longLiveToken, ResourceManager.dataAccess().getToken(admin));
        ResourceManager.dataAccess().delToken(admin, longLiveToken);
        Assert.assertFalse(ResourceManager.dataAccess().hasValidToken(admin));
    }

    @Test(expected = NoSuchElementException.class)
    public void noToken(){
        ResourceManager.dataAccess().clear();
        ResourceManager.dataAccess().addUser(admin);
        ResourceManager.dataAccess().getToken(admin);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tokenTwiceSameUser(){
        ResourceManager.dataAccess().clear();
        ResourceManager.dataAccess().addUser(admin);
        ResourceManager.dataAccess().addToken(admin,longLiveToken);
        ResourceManager.dataAccess().addToken(admin,longLiveToken);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tokenTwiceOtherUser(){
        ResourceManager.dataAccess().clear();
        ResourceManager.dataAccess().addUser(dummy);
        ResourceManager.dataAccess().addToken(admin,longLiveToken);
        ResourceManager.dataAccess().addToken(dummy,longLiveToken);
    }

    @Test
    public void invalidTokenDealing(){
        ResourceManager.dataAccess().clear();
        ResourceManager.dataAccess().addUser(admin);
        Assert.assertFalse(ResourceManager.dataAccess().hasValidToken(admin));
        ResourceManager.dataAccess().addToken(admin, deadToken );
        Assert.assertFalse(ResourceManager.dataAccess().hasValidToken(admin));

    }

    @Test
    public void getUserByTokenAndPw(){
        ResourceManager.dataAccess().clear();
        ResourceManager.dataAccess().addUser(admin);
        ResourceManager.dataAccess().addToken(admin, longLiveToken);
        Assert.assertEquals(admin, ResourceManager.dataAccess().getUser(longLiveToken, PASSWORD).get());
        Assert.assertFalse(ResourceManager.dataAccess().getUser(longLiveToken, PASSWORD+"u").isPresent());
        Assert.assertFalse(ResourceManager.dataAccess().getUser(deadToken, PASSWORD).isPresent());
    }
    @Test
    public void getAllTokenAndRights(){
        ResourceManager.dataAccess().clear();
        ResourceManager.dataAccess().addUser(admin);
        ResourceManager.dataAccess().addToken(admin, longLiveToken);
        ResourceManager.dataAccess().addUser(dummy);
        Map<Token, Boolean> m = ResourceManager.dataAccess().getAllTokenAndRights();
        Assert.assertEquals(1, m.size());
        Assert.assertEquals(longLiveToken, m.entrySet().stream().findFirst().get().getKey());
        Assert.assertEquals(true, m.entrySet().stream().findFirst().get().getValue());
    }






}
