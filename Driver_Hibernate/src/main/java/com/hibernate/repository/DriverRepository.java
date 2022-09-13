package com.hibernate.repository;

import java.util.List;

import org.hibernate.Session;

import com.hibernate.entities.Driver;
import com.hibernate.utils.HibernateUtils;

public class DriverRepository {
	 public List<Driver> getAll() {
	        Session session = HibernateUtils.getSessionFactory().openSession();
	        return session.createQuery("from Driver", Driver.class).list();
	    }

	    public void saveAll(List<Driver> drivers) {
//	        if (DataUtil.isEmptyCollection(drivers)) {
//	            return;
//	        }
	        Session session = HibernateUtils.getSessionFactory().openSession();
	        session.getTransaction().begin();
	        try {
	            for (Driver driver : drivers) {
	                session.save(driver);
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            session.getTransaction().rollback();
	        }
	        session.getTransaction().commit();
	    }

	    public Driver findById(int driverId) {
	        Session session = HibernateUtils.getSessionFactory().openSession();
	        return session.createQuery("from Driver where id = :p_id", Driver.class)
	                .setParameter("p_id", (Integer) driverId)
	                .getSingleResult();
	    }
}
