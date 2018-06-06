package com.unbosque.edu.co.calendarioFifa.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.unbosque.edu.co.calendarioFifa.dao.RefereeDAO;
import com.unbosque.edu.co.calendarioFifa.entity.Referee;
import com.unbosque.edu.co.calendarioFifa.util.HibernateUtil;

public class RefereeDAOImpl implements RefereeDAO {

	@Override
	public void save(Referee arbitro) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(arbitro);
		t.commit();
	}

	@Override
	public Referee getReferee(long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Referee) session.load(Referee.class, id);
	}

	@Override
	public List<Referee> list() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Referee").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(Referee arbitro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(arbitro);
		t.commit();
	}

	@Override
	public void update(Referee arbitro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(arbitro);
		t.commit();
	}

	@Override
	public Referee verificarArbitro(String fullName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		return (Referee) session.createQuery("from Referee WHERE fullName = '"+ fullName +"'").uniqueResult();
	}

}
