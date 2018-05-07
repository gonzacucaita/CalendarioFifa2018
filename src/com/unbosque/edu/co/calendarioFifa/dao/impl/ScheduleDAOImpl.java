package com.unbosque.edu.co.calendarioFifa.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.unbosque.edu.co.calendarioFifa.dao.ScheduleDAO;
import com.unbosque.edu.co.calendarioFifa.entity.Schedule;
import com.unbosque.edu.co.calendarioFifa.util.HibernateUtil;

public class ScheduleDAOImpl implements ScheduleDAO{

	@Override
	public void save(Schedule calendario) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(calendario);
		t.commit();
	}

	@Override
	public Schedule getSchedule(long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Schedule) session.load(Schedule.class, id);
	}

	@Override
	public List<Schedule> list() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Schedule").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(Schedule calendario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(calendario);
		t.commit();
	}

	@Override
	public void update(Schedule calendario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(calendario);
		t.commit();
	}

}
