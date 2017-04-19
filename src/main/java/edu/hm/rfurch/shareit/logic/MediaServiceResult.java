package edu.hm.rfurch.shareit.logic;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by FURCH on 19/04/2017.
 */
public enum MediaServiceResult {
    State1(200),
    State2(),
    State3(),
    StateN(); // TODO richtige states.


    private final int code;
    private final String status;
    private MediaServiceResult(int code, String status){
        this.code = code;
        this.status = status;
    }

    public int getCode(){
        throw new NotImplementedException();
    }
    public String getStatus(){ // TODO welches status? classe? package?
        throw new NotImplementedException();
    }




}
