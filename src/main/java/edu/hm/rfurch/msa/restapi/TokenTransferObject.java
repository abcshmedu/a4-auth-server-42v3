package edu.hm.rfurch.msa.restapi;

/**
 * Created by ms1511 on 23.05.17.
 */
public class TokenTransferObject {
    private final String token;
    private final boolean admin;

    private TokenTransferObject() {
        this("", false);
    }

    private TokenTransferObject(String token, boolean admin) {
        this.token = token;
        this.admin = admin;
    }

    public String getToken() {
        return token;
    }

    public boolean isAdmin() {
        return admin;
    }
}
