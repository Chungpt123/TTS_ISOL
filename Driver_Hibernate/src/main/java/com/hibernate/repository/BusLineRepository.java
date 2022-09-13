package com.hibernate.repository;

import java.util.List;

import org.hibernate.Session;

import com.hibernate.entities.BusLine;
import com.hibernate.utils.HibernateUtils;

public class BusLineRepository {
	public List<BusLine> getAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        return session.createQuery("from BusLine", BusLine.class).list();
    }
	public void saveAll(List<BusLine> busLines) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            for (BusLine busLine : busLines) {
                session.save(busLine);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
    }
	public BusLine findById(int busLineId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        return session.createQuery("from BusLine where id = :p_id", BusLine.class)
                .setParameter("p_id", (Integer) busLineId)
                .getSingleResult();

    }

}
