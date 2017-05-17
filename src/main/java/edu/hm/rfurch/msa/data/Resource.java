package edu.hm.rfurch.msa.data;

import edu.hm.rfurch.msa.model.Token;
import edu.hm.rfurch.msa.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Raphael Furch on 17/05/2017.
 */
public class Resource implements IResource {


    private static Map<User, Token> DATABASE = new HashMap<>();

    @Override
    public boolean exists(User user) {
       return DATABASE.containsKey(user);
    }

    @Override
    public void add(User user) {

    }

    @Override
    public void addToken(User user, Token token) {

    }

    @Override
    public boolean hasValidToken(User user) {
        return false;
    }
}
