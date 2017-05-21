package edu.hm.rfurch.msa.model;

import javax.print.DocFlavor;

/**
 * Created by Raphael Furch on 17/05/2017.
 */
public class User {

    private final String name;
    private final String password;
    private final boolean isAdmin;

    public User(String name, String password, Boolean isAdmin){
        if(name == null || password == null || isAdmin == null)
            throw new IllegalArgumentException();

        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getName(){
        return this.name;
    }

    public boolean isAdmin(){
        return this.isAdmin;
    }

    @Override
    public String toString(){
        return "User: {name="+ this.name + ", isAdmin=" + this.isAdmin+"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;
        if (!name.equals(user.name)) return false;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (isAdmin ? 1 : 0);
        return result;
    }
}
