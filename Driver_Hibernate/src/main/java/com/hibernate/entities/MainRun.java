package com.hibernate.entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.utils.HibernateUtil;


public class MainRun {
	static final SessionFactory factory = HibernateUtil.getSessionFactory();
	public static void main(String[] args) {
		Session session = factory.openSession();
		Transaction txTransaction = session.beginTransaction();
		Driver driver = new Driver();
		driver.setName("chung");
		driver.setPhone("chung");
		driver.setEmail("chung");
		driver.setLevel(1);
		session.save(driver);
		txTransaction.commit();
	}
}
