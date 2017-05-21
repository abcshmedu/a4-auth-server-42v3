package edu.hm.rfurch.msa.data;

import edu.hm.rfurch.shareit.data.IData;
import edu.hm.rfurch.shareit.data.MediaResource;

/**
 * 
 * @author Raphael Furch, rfurch@hm.edu / Michael Schmid, m.schmid@hm.edu
 *
 */
public final class ResourceManager {
    /**
     * Singleton of the stored data.
     */
    private static final IResource DATA = new Resource();
    
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
    public static IResource dataAccess() {
        return DATA;
    }
}
