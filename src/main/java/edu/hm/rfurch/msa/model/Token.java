package edu.hm.rfurch.msa.model;

import javax.jws.soap.SOAPBinding;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Raphael Furch on 17/05/2017.
 */
public class Token {

    private final long createTime;
    private final long liveTime;
    private final int length;
    private final User owner;

    public Token(User owner, long liveTime, int length){
        if(owner == null || liveTime <= 0 || length <= 0)
            throw new IllegalArgumentException();

        this.liveTime = liveTime;
        this.createTime = System.currentTimeMillis();
        this.length = length;
        this.owner = owner;
    }

    public long getCreateTime(){
        return this.createTime;
    }
    public long getLiveTime(){
        return this.createTime;
    }

    public int getLength(){
        return this.length;
    }

    public String getTokenValue(){
        return new Integer(this.hashCode()).toString();
    }

    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;

        Token token = (Token) o;

        if (createTime != token.createTime) return false;
        if (liveTime != token.liveTime) return false;
        if (length != token.length) return false;
        return owner.equals(token.owner);
    }

    @Override
    public int hashCode() {
        int result = (int) (createTime ^ (createTime >>> 32));
        result = 31 * result + (int) (liveTime ^ (liveTime >>> 32));
        result = 31 * result + length;
        result = 31 * result + owner.hashCode();
        return result;
    }
}
