package edu.hm.rfurch.msa.restapi;

/**
 * Created by ms1511 on 24.05.17.
 */
public class UserTransferObject {
    private final String name;
    private final String password;

    private UserTransferObject() {
        this("","");
    }

    private UserTransferObject(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
