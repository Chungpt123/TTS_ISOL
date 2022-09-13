package com.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.hibernate.entities.Driver;
import com.hibernate.utils.HibernateUtils;
import org.hibernate.query.Query;

public class DriverDaoImpl implements DriverDao {
//	 Logger logger = Logger.getLogger(DriverDaoImpl.class);
	@SuppressWarnings("unchecked")
	public Driver getOneById(int id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			session.beginTransaction();			
			Query<Driver> query = session.createQuery("select d from Driver d where d.id = :p_id");
			query.setParameter("p_id", id);
			Driver driver = query.getSingleResult();
			session.getTransaction().commit();
			return driver;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public boolean addNew(Driver driver) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(driver);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	public boolean update(Driver driver) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(driver);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	public boolean delete(Driver driver) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(driver);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}
	
	public List<Driver> getAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			List<Driver> drivers = session.createQuery("from Driver", Driver.class).getResultList();
			session.getTransaction().commit();
			return drivers;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return new ArrayList<Driver>();

	}
}
