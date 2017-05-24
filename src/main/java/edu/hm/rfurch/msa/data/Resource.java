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



    //region Tested
    @Override
    public boolean exists(User user) {
       return DATABASE.containsKey(user);
    }
    @Override
    public boolean exists(String name, String password) {
        return this.exists(new User(name, password, false)) ||
                this.exists(new User(name, password, true));
    }
    @Override
    public boolean exists(String name) {
        return DATABASE.keySet().stream().anyMatch(f -> f.getName().equals(name));
    }

    @Override
    public void addUser(User user) {
        DATABASE.put(user,null);
    }
    @Override
    public User getUser(String name) {
        return DATABASE.keySet().stream().filter(f -> f.getName().equals(name)).findAny().get();
    }
    @Override
    public int count() {
        return DATABASE.entrySet().size();
    }

    @Override
    public void clear() {
        DATABASE.clear();
    }

    @Override
    public void addToken(User user, Token token) {
        if(DATABASE.values().stream().anyMatch(token::equals))
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
    public Token getToken(User user) {
        return getOnlyLivingToken(user).get();
    }
    @Override
    public Optional<User> getUser(Token token, String password) {
        return DATABASE.entrySet().stream().filter(f -> token.equals(f.getValue())).map(Map.Entry::getKey)
                .filter(f -> f.equals(new User(f.getName(), password, f.isAdmin())))
                .findFirst();
    }

    @Override
    public Map<Token, Boolean> getAllTokenAndRights() {
        return DATABASE.entrySet().stream().filter(f -> f.getValue()!=null)
                .filter(ff -> ff.getValue().isLiving())
                .collect(Collectors.toMap(Map.Entry::getValue, c -> c.getKey().isAdmin()));
    }
    //endregion






















    private Optional<Token> getOnlyLivingToken(User user){
        final Token t = DATABASE.get(user);
        if(t != null && t.isLiving())
            return Optional.of(t);
        else
            return Optional.empty();
    }

}
