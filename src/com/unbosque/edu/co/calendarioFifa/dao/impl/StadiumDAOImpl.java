package com.unbosque.edu.co.calendarioFifa.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.unbosque.edu.co.calendarioFifa.dao.StadiumDAO;
import com.unbosque.edu.co.calendarioFifa.entity.Stadium;
import com.unbosque.edu.co.calendarioFifa.util.HibernateUtil;

public class StadiumDAOImpl  implements StadiumDAO{

	@Override
	public void save(Stadium estadio) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(estadio);
		t.commit();
	}

	@Override
	public Stadium getStadium(long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Stadium) session.load(Stadium.class, id);
	}

	@Override
	public List<Stadium> list() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Stadium").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(Stadium estadio) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(estadio);
		t.commit();
	}

	@Override
	public void update(Stadium estadio) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(estadio);
		t.commit();
	}
	@Override
	public Stadium verificarEstadiio(String city) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Stadium) session.createQuery("from Stadium WHERE city = '"+city+"'").uniqueResult();
	}

}
