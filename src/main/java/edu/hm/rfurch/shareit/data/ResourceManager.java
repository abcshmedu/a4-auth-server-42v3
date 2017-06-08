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
    
    private final static ResourceManager RESOURCEMANAGER = new ResourceManager();
    
    /**
     * Look constructor.
     */
    private ResourceManager() {
        super();
    }
    /**
     * Get data access.
     * @return reference to data
     */
    public static IData dataAccess() {
        return DATA;
    }
    
    public static ResourceManager getResourceManager() {
    	return RESOURCEMANAGER;
    }
    
    @Inject
    private void setData(IData data) {
    	DATA = data;
    }
}
