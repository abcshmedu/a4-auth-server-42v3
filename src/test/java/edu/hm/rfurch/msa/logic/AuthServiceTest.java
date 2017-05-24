package edu.hm.rfurch.msa.logic;

import edu.hm.rfurch.msa.model.Token;
import org.junit.Test;

/**
 * Created by Raphael Furch on 24/05/2017.
 */
public class AuthServiceTest {


    @Test
    public void proofTest(){
        new AuthService().createNewUser("Michael", "Schmid", false);
        String tokenV = new AuthService().getToken("Michael", "Schmid").getToken();
        System.out.println(new AuthService().proofToken(tokenV, false).getCode());
    }
}
