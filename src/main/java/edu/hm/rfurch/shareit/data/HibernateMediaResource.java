package edu.hm.rfurch.shareit.data;

import edu.hm.rfurch.shareit.model.*;
import java.util.*;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * MediaResource class for persistent data.
 * @author Elias Porcio, elias.porcio@hm.edu
 * @version Jun 7, 2017
 */
public class HibernateMediaResource implements IData {

	private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	private final Session entityManager = sessionFactory.getCurrentSession();
    /**
     * Ctor of HibernateMediaResource.
     */
    protected HibernateMediaResource() {
    	Transaction tx = entityManager.beginTransaction();
        entityManager.persist(new Book("Title1", "Author1", "9783866801929"));
        entityManager.persist(new Disc("Disc1", "4059251015567", "Dirctor1", 1));
        tx.commit();
    }

    @Override
    public List<IMedium> getMediums() {
    	Query query = entityManager.createQuery("SELECT * FROM Medium");
    	return query.getResultList();
    }

    @Override
    public Optional<Boolean> add(IMedium medium) {
    	Transaction tx = entityManager.beginTransaction();
    	
    	Query query = entityManager.createQuery("SELECT * FROM Medium WHERE title = :title");
    	query.setParameter("title", medium.getTitle());
    	List<IMedium> response = query.getResultList();
    	
        Optional<Boolean> result;
        if (medium != null && medium.valid() && response.size() != 0) {
            entityManager.persist(medium);
            result = Optional.of(true);
        } else {
            result = Optional.of(false);
        }
        
        tx.commit();
        return result;

    }
    
    @Override
    public Optional<Boolean> remove(IMedium medium) {
    	Transaction tx = entityManager.beginTransaction();
    	
    	Query query = entityManager.createQuery("SELECT * FROM Medium WHERE title = :title");	//checks whether medium exists for boolean result
    	query.setParameter("title", medium.getTitle());
    	Optional<Boolean> result = Optional.of(query.getResultList().size() == 1);
    	
        entityManager.remove(medium);
        
        tx.commit();
        return result;
    }

    @Override
    public Optional<Boolean> clear() {
    	Transaction tx = entityManager.beginTransaction();
    	
    	Query query = entityManager.createQuery("DELETE FROM Medium");
        query.executeUpdate();
        
        tx.commit();
        return Optional.of(true);
    }
    
    
}
