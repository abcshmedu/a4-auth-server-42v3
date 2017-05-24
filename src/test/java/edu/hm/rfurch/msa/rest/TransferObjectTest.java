package edu.hm.rfurch.msa.rest;

import edu.hm.rfurch.msa.restapi.TokenTransferObject;
import edu.hm.rfurch.msa.restapi.UserTransferObject;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Raphael Furch on 24/05/2017.
 */
public class TransferObjectTest {
    @Test
    public void UtoTest(){
        UserTransferObject uto = new UserTransferObject("test","test2");
        Assert.assertEquals("test", uto.getName());
        Assert.assertEquals("test2", uto.getPassword());
    }

    @Test
    public void TtoTest(){
        TokenTransferObject tto = new TokenTransferObject("token",false);
        Assert.assertEquals("token", tto.getToken());
        Assert.assertEquals(false, tto.isAdmin());
    }
}
