package edu.hm.rfurch.shareit.data;

import edu.hm.rfurch.shareit.model.*;
import java.util.*;

/**
 * 
 * @author Raphael Furch, rfurch@hm.edu / Michael Schmid, m.schmid@hm.edu
 *
 */
public class MediaResource implements IData {

    private final List<IMedium> mediaDatabase;
    /**
     * Ctor of MediaResource.
     */
    protected MediaResource() {
        mediaDatabase = new ArrayList<>();
        mediaDatabase.add(new Book("Title1", "Author1", "1"));
        mediaDatabase.add(new Disc("Disc1", "Code1", "Dirctor1", 1));
    }

    @Override
    public List<IMedium> getMediums() {
       return Collections.unmodifiableList(getMediaDatabase());
    }

    @Override
    public Optional<Boolean> add(IMedium medium) {
        Optional<Boolean> result;
        if (medium != null && !getMediaDatabase().contains(medium)) {
            getMediaDatabase().add(medium);
            result = Optional.of(true);
        } else {
            result = Optional.of(false);
        }
        return result;

    }
    
    /**
     * Get list of media.
     * @return list of media
     */
    private List<IMedium> getMediaDatabase() {
        return mediaDatabase;
    }

    @Override
    public Optional<Boolean> remove(IMedium medium) {
        final boolean result = getMediaDatabase().remove(medium);
        return Optional.of(result);
        }

    @Override
    public Optional<Boolean> clear() {
        getMediaDatabase().clear();
        return Optional.of(true);
    }
    
    
}
