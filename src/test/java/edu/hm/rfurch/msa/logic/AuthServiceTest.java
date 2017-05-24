package edu.hm.rfurch.msa.logic;

import edu.hm.rfurch.msa.data.Resource;
import edu.hm.rfurch.msa.data.ResourceManager;
import edu.hm.rfurch.msa.model.Token;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Raphael Furch on 24/05/2017.
 */
public class AuthServiceTest {


    @Test
    public void proofTest(){
        ResourceManager.dataAccess().clear();
        new AuthService().createNewUser("Michael", "Schmid", false);
        String tokenV = new AuthService().getToken("Michael", "Schmid").getToken();
        Assert.assertEquals(200,new AuthService().proofToken(tokenV, false).getCode());
    }

    @Test
    public void proofTest2(){
        ResourceManager.dataAccess().clear();
        new AuthService().createNewUser("Michael", "Schmid", true);
        String tokenV = new AuthService().getToken("Michael", "Schmid").getToken();
        Assert.assertEquals(200,new AuthService().proofToken(tokenV, false).getCode());
    }

    @Test
    public void proofTest3(){
        ResourceManager.dataAccess().clear();
        new AuthService().createNewUser("Michael", "Schmid", true);
        String tokenV = new AuthService().getToken("Michael", "Schmid").getToken();
        Assert.assertEquals(200,new AuthService().proofToken(tokenV, true).getCode());
    }
}
