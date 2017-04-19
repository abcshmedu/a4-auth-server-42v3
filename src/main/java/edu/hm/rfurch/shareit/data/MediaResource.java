package edu.hm.rfurch.shareit.data;

import edu.hm.rfurch.shareit.model.Book;
import edu.hm.rfurch.shareit.model.Disc;
import edu.hm.rfurch.shareit.model.IMedium;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FURCH on 19/04/2017.
 */
public class MediaResource implements IData {

    private static List<IMedium> mediums = new ArrayList<IMedium>(){
        {
            add(new Book("Title1", "Author1", "1"));
            add(new Disc("Disc1", "Code1", "Dirctor1", 1));
            add(new Book("Title2", "Author2", "2"));
            add(new Disc("Disc2", "Code2", "Dirctor2", 2));
            add(new Book("Title3", "Author3", "3"));
            add(new Disc("Disc3", "Code3", "Dirctor3", 3));
            add(new Book("Title4", "Author4", "4"));
            add(new Disc("Disc4", "Code4", "Dirctor4", 4));
        }
    };

    @Override
    public List<IMedium> getMediums() {
       return MediaResource.mediums; // TODO make read only
    }

    @Override
    public void add(IMedium medium) {
        throw new NotImplementedException(); // TODO add or update
    }
}
