package edu.hm.rfurch.shareit.data;

import edu.hm.rfurch.shareit.model.*;
import java.util.*;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;

/**
 * MediaResource class for persistent data.
 * @author Elias Porcio, elias.porcio@hm.edu
 * @version Jun 7, 2017
 */
public class HibernateMediaResource implements IData {

	private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
    /**
     * Ctor of HibernateMediaResource.
     */
    protected HibernateMediaResource() {
    	final Session session = sessionFactory.getCurrentSession();
    	Transaction tx = session.beginTransaction();
    	session.persist(new Book("Title1", "Author1", "9783866801929"));
    	session.persist(new Disc("Disc1", "4059251015567", "Dirctor1", 1));
        tx.commit();
    }

    @Override
    public List<IMedium> getMediums() {
    	final Session session = sessionFactory.getCurrentSession();
    	final Transaction tx = session.beginTransaction();
    	final List<IMedium> result = session.createQuery("from BaseMedium").list();
    	tx.commit();
    	return result;	
    }

    @Override
    public Optional<Boolean> add(IMedium medium) {
    	final Session session = sessionFactory.getCurrentSession();
    	final Transaction tx = session.beginTransaction();
  	
        Optional<Boolean> result;
        if (medium != null && medium.valid() && !exists(medium)) {
            session.persist(medium);
            result = Optional.of(true);
        } else {
            result = Optional.of(false);
        }
        
        tx.commit();
        return result;

    }
    
    @Override
    public Optional<Boolean> remove(IMedium medium) {
    	final Session session = sessionFactory.getCurrentSession();
    	Transaction tx = session.beginTransaction();
    	
    	Optional<Boolean> result = Optional.of(exists(medium));
    	session.remove(medium);
        
        tx.commit();
        return result;
    }

    @Override
    public Optional<Boolean> clear() {
    	final Session session = sessionFactory.getCurrentSession();
    	Transaction tx = session.beginTransaction();
    	
    	Query query = session.createNativeQuery("DELETE FROM Medium");
        query.executeUpdate();
        
        tx.commit();
        return Optional.of(true);
    }
    
    private boolean exists(IMedium medium) {
    	final Session session = sessionFactory.getCurrentSession();
    	Query query = null;
    	boolean result = false; 
    	if(medium instanceof IBook) {
    		query =  session.createNativeQuery("SELECT count(*) FROM Medium WHERE isbn = :isbn");
    		query.setParameter("isbn", ((IBook)medium).getIsbn());
    	} else if(medium instanceof IDisc) {
    		query =  session.createNativeQuery("SELECT count(*) FROM Medium WHERE barcode = :barcode");
    		query.setParameter("barcode", ((IDisc)medium).getBarcode());
    	}
    	
    	if(query != null)
    		result = query.getFirstResult() == 1;
    	
    	return result;
    }
    
}
