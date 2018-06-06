package com.unbosque.edu.co.calendarioFifa.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.unbosque.edu.co.calendarioFifa.dao.GoalscorerDAO;
import com.unbosque.edu.co.calendarioFifa.entity.Goalscorer;
import com.unbosque.edu.co.calendarioFifa.util.HibernateUtil;

public class GoalscorerDAOImpl implements GoalscorerDAO {

	@Override
	public void save(Goalscorer goleador) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(goleador);
		t.commit();
	}

	@Override
	public Goalscorer getGoalscorer(long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Goalscorer) session.load(Goalscorer.class, id);
	}

	@Override
	public List<Goalscorer> list() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Goalscorer").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(Goalscorer goleador) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(goleador);
		t.commit();
	}

	@Override
	public void update(Goalscorer goleador) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(goleador);
		t.commit();
	}
	
	@Override
	public Goalscorer verificarGoleador(String fullName) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Goalscorer) session.createQuery("from Goalscorer WHERE fullName = '"+fullName+"'").uniqueResult();
	}


}
