package edu.hm.rfurch.msa.data;

import edu.hm.rfurch.msa.model.Token;
import edu.hm.rfurch.msa.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public boolean exists(String name, String password) {
        return this.exists(new User(name, password, false));
    }
    @Override
    public boolean exists(String name) {
        return  DATABASE.keySet().stream().filter(f -> f.getName().equals(name)).findAny().isPresent();
    }

    @Override
    public User getUser(String name) {
        return DATABASE.keySet().stream().filter(f -> f.getName().equals(name)).findAny().get();
    }

    @Override
    public Optional<User> getUser(Token token, String password) {
        return DATABASE.entrySet().stream().filter(f -> f.getValue().equals(token)).map(Map.Entry::getKey)
                .filter(f -> f.equals(new User(f.getName(), password, false))).findFirst();
    }

    @Override
    public void addUser(User user) {
        DATABASE.put(user,null);
    }

    @Override
    public void addToken(User user, Token token) {
        if(DATABASE.values().stream().anyMatch(t -> t.equals(token)))
            throw new IllegalArgumentException("Token already exists.");
        DATABASE.put(user,token);
    }

    @Override
    public void delToken(User user, Token token) {
        DATABASE.put(user,null);
    }


    @Override
    public boolean hasValidToken(User user) {
       return getOnlyLivingToken(user).isPresent();
    }

    @Override
    public Map<Token, Boolean> getAllTokenAndRights() {
        return DATABASE.entrySet().stream().filter(f -> f.getValue()!=null)
                .filter(ff -> ff.getValue().isLiving())
                .collect(Collectors.toMap(c -> c.getValue(), c -> c.getKey().isAdmin()));
    }


    @Override
    public Token getToken(User user) {
        return getOnlyLivingToken(user).get();
    }


    private Optional<Token> getOnlyLivingToken(User user){
        final Token t = DATABASE.get(user);
        if(t.isLiving())
            return Optional.of(t);
        else
            return Optional.empty();
    }
}
