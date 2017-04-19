package edu.hm.rfurch.shareit.logic;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by FURCH on 19/04/2017.
 */
public enum MediaServiceResult {
    State1(),
    State2(),
    State3(),
    StateN(); // TODO richtige states.



    public int getCode(){
        throw new NotImplementedException();
    }
    public Status getStatus(){ // TODO welches status? classe? package?
        throw new NotImplementedException();
    }

    public MediaServiceResult valueOf(String msg){
        throw new NotImplementedException();
    }

    public MediaServiceResult[] values(){
        throw new NotImplementedException();
    }


}
