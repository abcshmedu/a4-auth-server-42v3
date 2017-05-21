package edu.hm.rfurch.msa.logic;

import edu.hm.rfurch.msa.model.User;
import edu.hm.rfurch.msa.data.ResourceManager;

/**
 * Created by Admin on 21.05.2017.
 */
public interface IAuthService {

    MsaServiceResult createNewUser(String name, String password, boolean isAdmin);
    MsaServiceResult getToken(String name, String password);
    MsaServiceResult proofToken(String tokenValue, boolean asAdmin);
    MsaServiceResult endSession(String tokenValue, String password);

}
