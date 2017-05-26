package edu.hm.rfurch.msa.model;

import javax.jws.soap.SOAPBinding;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Raphael Furch on 17/05/2017.
 */
public class Token {

    public static long DEFAULT_TOKEN_LIVE_TIME = 180000;
    private final long createTime;
    private final long liveTime;
    private final User owner;

    public Token(User owner, long liveTime){
        if(owner == null || liveTime <= 0)
            throw new IllegalArgumentException();

        this.liveTime = liveTime;
        this.createTime = System.currentTimeMillis();
        this.owner = owner;
    }

    public long getCreateTime(){
        return this.createTime;
    }
    public long getLiveTime(){
        return this.liveTime;
    }


    public String getTokenValue(){
        return Integer.toString(this.hashCode());
    }

    public boolean isLiving(){
        return (System.currentTimeMillis() - this.getCreateTime() - this.getLiveTime()) < 0;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;

        Token token = (Token) o;

        if (createTime != token.createTime) return false;
        if (liveTime != token.liveTime) return false;
        return owner.equals(token.owner);
    }

    @Override
    public int hashCode() {
        int result = (int) (createTime ^ (createTime >>> 32));
        result = 31 * result + (int) (liveTime ^ (liveTime >>> 32));
        result = 31 * result + owner.hashCode();
        return result;
    }
}
