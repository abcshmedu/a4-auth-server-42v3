package edu.hm.rfurch.shareit.data;

import com.sun.javafx.UnmodifiableArrayList;
import edu.hm.rfurch.shareit.model.Book;
import edu.hm.rfurch.shareit.model.Disc;
import edu.hm.rfurch.shareit.model.IMedium;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * Created by FURCH on 19/04/2017.
 */
public class MediaResource implements IData {

    private final static List<IMedium> MEDIUMS = new ArrayList<IMedium>(){
        {
            add(new Book("Title1", "Author1", "1"));
            add(new Disc("Disc1", "Code1", "Dirctor1", 1));
        }
    };

    @Override
    public List<IMedium> getMediums() {
       return Collections.unmodifiableList(MEDIUMS);
    }

    @Override
    public Optional<Boolean> add(IMedium medium) {

        Optional<Boolean> result;
        if(medium != null && !MEDIUMS.contains(medium)){
            MEDIUMS.add(medium);
            result = Optional.of(true);
        }
        else
            result = Optional.of(false);


        return result;

    }
}
