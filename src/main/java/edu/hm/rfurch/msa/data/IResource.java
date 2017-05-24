package edu.hm.rfurch.msa.data;

import edu.hm.rfurch.msa.model.Token;
import edu.hm.rfurch.msa.model.User;

import java.util.Map;
import java.util.Optional;

/**
 * Created by Raphael Furch on 17/05/2017.
 */
public interface IResource {
        boolean exists(User user);
        boolean exists(String name, String password);
        boolean exists(String name);
        User getUser(String name);
        Optional<User> getUser(Token token, String password);
        void addUser(User user);
        void addToken(User user, Token token);
        void delToken(User user, Token token);
        boolean hasValidToken(User user);
        Map<Token, Boolean> getAllTokenAndRights();
        Token getToken(User user);
        int count();
        void clear();
    }
