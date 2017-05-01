package edu.hm.rfurch.shareit.data;

/**
 * 
 * @author Raphael Furch, rfurch@hm.edu / Michael Schmid, m.schmid@hm.edu
 *
 */
public final class ResourceManager {
    /**
     * Singleton of the stored data.
     */
    private static final IData DATA = new MediaResource();
    
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
}
