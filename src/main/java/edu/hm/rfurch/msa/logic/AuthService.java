package edu.hm.rfurch.msa.logic;

import edu.hm.rfurch.msa.data.ResourceManager;
import edu.hm.rfurch.msa.model.Token;
import edu.hm.rfurch.msa.model.User;

import java.util.Map;
import java.util.Optional;

/**
 * Created by Admin on 21.05.2017.
 */
public class AuthService implements IAuthService {

    public MsaServiceResult createNewUser(String name, String password, boolean isAdmin){
        try{
            if(name == null || name.trim().equals("") || password == null || password.trim().equals("")){
                return MsaServiceResult.BadRequest.setMessage("Some value is null or empty.");
            }
            else if(ResourceManager.dataAccess().exists(name))
                return MsaServiceResult.BadRequest.setMessage("User already exists.");
            else{
                ResourceManager.dataAccess().addUser(new User(name, password, isAdmin));
                return MsaServiceResult.OK;
            }
        }
        catch (Exception ex){
            return MsaServiceResult.IamATeapot;
        }

    }

    @Override
    public MsaServiceResult getToken(String name, String password) {
            if(name == null || name.trim().equals("") || password == null || password.trim().equals("")){
                return MsaServiceResult.BadRequest.setMessage("Some value is null or empty.");
            }
            else if(!ResourceManager.dataAccess().exists(name, password))
                return MsaServiceResult.BadRequest.setMessage("User not exists or wrong password.");
            else{
                final User user = ResourceManager.dataAccess().getUser(name);
                Token token;
                if(ResourceManager.dataAccess().hasValidToken(user))
                    token = ResourceManager.dataAccess().getToken(user);
                else{
                    token = new Token(user,Token.DEFAULT_TOKEN_LIVE_TIME);
                    ResourceManager.dataAccess().addToken(user, token);
                }
                return MsaServiceResult.OK.setToken(token);
            }
    }

    @Override
    public MsaServiceResult proofToken(String tokenValue, boolean asAdmin) {
        if(ResourceManager.dataAccess().getAllTokenAndRights().get(getTokenFromTokenValue(tokenValue))){
            return MsaServiceResult.OK.setMessage(tokenValue);
        }
        else{
            return MsaServiceResult.Forbidden.setMessage("Invalid token or rights.");
        }
    }

    @Override
    public MsaServiceResult endSession(String tokenValue, String password) {
        final Optional<Token> oToken = getTokenFromTokenValue(tokenValue);
        if(oToken.isPresent()){
            Optional<User> oUser = ResourceManager.dataAccess().getUser(oToken.get(),password);
            if(oUser.isPresent()){
                ResourceManager.dataAccess().delToken(oUser.get(), oToken.get());
                return MsaServiceResult.IamATeapot;
            }
            else{
                return MsaServiceResult.BadRequest.setMessage("Wrong password.");
            }
        }
        else{
            return MsaServiceResult.Forbidden.setMessage("Invalid token.");
        }

    }

    private Optional<Token> getTokenFromTokenValue(String tokenValue){
        return ResourceManager.dataAccess().getAllTokenAndRights().entrySet().stream().map(Map.Entry::getKey)
                .filter(f -> f.getTokenValue().equals(tokenValue)).findFirst();
    }
}
