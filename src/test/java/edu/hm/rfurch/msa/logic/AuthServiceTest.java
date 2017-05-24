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
    public void createNewUser(){
        ResourceManager.dataAccess().clear();
        Assert.assertEquals(200,new AuthService().createNewUser("Kevin", "Mueller", false).getCode());
        Assert.assertEquals(400,new AuthService().createNewUser("Kevin", "Mueller", false).getCode());
        Assert.assertEquals(400,new AuthService().createNewUser("Kevin", "Mueller", true).getCode());
        Assert.assertEquals(400,new AuthService().createNewUser("Kevin", "Mueller2", false).getCode());
        Assert.assertEquals(200,new AuthService().createNewUser("Kevin2", "Mueller", false).getCode());

        Assert.assertEquals(400,new AuthService().createNewUser("", "Mueller", false).getCode());
        Assert.assertEquals(400,new AuthService().createNewUser("Kevin2", "", false).getCode());
        Assert.assertEquals(400,new AuthService().createNewUser(null, "Mueller", false).getCode());
        Assert.assertEquals(400,new AuthService().createNewUser("Kevin2", null, false).getCode());
    }

    @Test
    public void getTokenTestNot(){
        ResourceManager.dataAccess().clear();
        Assert.assertEquals(200,new AuthService().createNewUser("Kevin", "Mueller", false).getCode());
        Assert.assertEquals(400,new AuthService().getToken("Gevin", "Mueller").getCode());
        Assert.assertEquals(400,new AuthService().getToken("Kevin", "Nueller").getCode());

        Assert.assertEquals(400,new AuthService().getToken("", "Mueller").getCode());
        Assert.assertEquals(400,new AuthService().getToken("Kevin", "").getCode());

        Assert.assertEquals(400,new AuthService().getToken(null, "Mueller").getCode());
        Assert.assertEquals(400,new AuthService().getToken("Kevin", null).getCode());
        Assert.assertEquals(400,new AuthService().getToken(null, null).getCode());

    }

    @Test
    public void getTokenTest(){
        ResourceManager.dataAccess().clear();
        Assert.assertEquals(200,new AuthService().createNewUser("Kevin", "Mueller", false).getCode());
        final String tokenValue = new AuthService().getToken("Kevin", "Mueller").getToken();
        Assert.assertNotNull(tokenValue);
        Assert.assertEquals(tokenValue, new AuthService().getToken("Kevin", "Mueller").getToken());

    }


    @Test
    public void proofTestFalseFalse(){
        ResourceManager.dataAccess().clear();
        Assert.assertEquals(200,new AuthService().createNewUser("Kevin", "Mueller", false).getCode());
        String tokenV = new AuthService().getToken("Kevin", "Mueller").getToken();
        Assert.assertEquals(200,new AuthService().proofToken(tokenV, false).getCode());
    }

    @Test
    public void proofTestTrueFalse(){
        ResourceManager.dataAccess().clear();
        Assert.assertEquals(200,new AuthService().createNewUser("Kevin", "Mueller", true).getCode());
        String tokenV = new AuthService().getToken("Kevin", "Mueller").getToken();
        Assert.assertEquals(200,new AuthService().proofToken(tokenV, false).getCode());
    }

    @Test
    public void proofTestTrueTrue(){
        ResourceManager.dataAccess().clear();
        Assert.assertEquals(200,new AuthService().createNewUser("Kevin", "Mueller", true).getCode());
        String tokenV = new AuthService().getToken("Kevin", "Mueller").getToken();
        Assert.assertEquals(200,new AuthService().proofToken(tokenV, true).getCode());
    }
    @Test
    public void proofTestFalseTrue(){
        ResourceManager.dataAccess().clear();
        Assert.assertEquals(200,new AuthService().createNewUser("Kevin", "Mueller", false).getCode());
        String tokenV = new AuthService().getToken("Kevin", "Mueller").getToken();
        Assert.assertEquals(403,new AuthService().proofToken(tokenV, true).getCode());
    }

    @Test
    public void proofTestWrongUser(){
        ResourceManager.dataAccess().clear();
        Assert.assertEquals(200,new AuthService().createNewUser("Kevin", "Mueller", false).getCode());
        String tokenV = new AuthService().getToken("Kevin2", "Mueller").getToken();
        Assert.assertEquals(403,new AuthService().proofToken(tokenV, true).getCode());
    }

    @Test
    public void proofTestWrongPw(){
        ResourceManager.dataAccess().clear();
        Assert.assertEquals(200,new AuthService().createNewUser("Kevin", "Mueller", false).getCode());
        String tokenV = new AuthService().getToken("Kevin", "Mueller2").getToken();
        Assert.assertEquals(403,new AuthService().proofToken(tokenV, true).getCode());
    }

    @Test
    public void proofTestAllWrong(){
        ResourceManager.dataAccess().clear();
        Assert.assertEquals(200,new AuthService().createNewUser("Kevin", "Mueller", false).getCode());
        String tokenV = new AuthService().getToken("Kevin2", "Mueller2").getToken();
        Assert.assertEquals(403,new AuthService().proofToken(tokenV, true).getCode());
    }
}
