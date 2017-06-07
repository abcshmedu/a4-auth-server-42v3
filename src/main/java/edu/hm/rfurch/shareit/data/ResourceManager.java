package edu.hm.rfurch.shareit.data;

import javax.inject.Inject;

/**
 * 
 * @author Raphael Furch, rfurch@hm.edu / Michael Schmid, m.schmid@hm.edu
 *
 */
public final class ResourceManager {
    /**
     * Singleton of the stored data.
     */
    private static IData DATA = null;
    //private static IData DATA = new MediaResource();
    
    /**
     * Look constructor.
     */
    public ResourceManager() {
        super();
    }
    /**
     * Get data access.
     * @return reference to data
     */
    public static IData dataAccess() {
        return DATA;
    }
    
    @Inject
    private void setData(IData data) {
    	DATA = data;
    }
}
