package com.unbosque.edu.co.calendarioFifa.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.unbosque.edu.co.calendarioFifa.dao.TeamDAO;
import com.unbosque.edu.co.calendarioFifa.entity.Team;
import com.unbosque.edu.co.calendarioFifa.util.HibernateUtil;

public class TeamDAOImpl implements TeamDAO{

	@Override
	public void save(Team equipo) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(equipo);
		t.commit();
	}

	@Override
	public Team getTeam(long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Team) session.load(Team.class, id);
	}

	@Override
	public List<Team> list() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Team").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(Team equipo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(equipo);
		t.commit();
	}

	@Override
	public void update(Team equipo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(equipo);
		t.commit();
	}

}
