package edu.hm.rfurch.msa.restapi;

/**
 * Created by ms1511 on 23.05.17.
 */
public class TokenTransferObject {
    private final String token;

    private TokenTransferObject() {
        this("");
    }

    private TokenTransferObject(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
