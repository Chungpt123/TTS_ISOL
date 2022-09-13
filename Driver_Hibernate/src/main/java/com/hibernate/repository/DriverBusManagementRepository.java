package com.hibernate.repository;

import java.util.List;

import org.hibernate.Session;

import com.hibernate.entities.DriverManagement;
import com.hibernate.utils.DataUtil;
import com.hibernate.utils.HibernateUtils;

public class DriverBusManagementRepository {
	public List<DriverManagement> getAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        return session.createQuery("from DriverManagement", DriverManagement.class).list();
    }

    public void saveAll(List<DriverManagement> toEntity) {
        if (DataUtil.isEmptyCollection(toEntity)) {
            return;
        }
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            for (DriverManagement driverManagement : toEntity) {
                session.save(driverManagement);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();

    }
}
