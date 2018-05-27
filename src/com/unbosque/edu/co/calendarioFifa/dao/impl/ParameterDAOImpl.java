package com.unbosque.edu.co.calendarioFifa.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.unbosque.edu.co.calendarioFifa.dao.ParameterDAO;
import com.unbosque.edu.co.calendarioFifa.entity.Parameter;
import com.unbosque.edu.co.calendarioFifa.util.HibernateUtil;

public class ParameterDAOImpl implements ParameterDAO {

	@Override
	public void save(Parameter parametro) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(parametro);
		t.commit();
	}

	@Override
	public Parameter getParameter(long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Parameter p = (Parameter) session.load(Parameter.class, id);
		t.commit();
		return p;
	}

	@Override
	public Parameter getParameterFromUser(String idUser) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		return (Parameter) session.load(Parameter.class, idUser);
	}
	@Override
	public List<Parameter> list() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Parameter").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(Parameter parametro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(parametro);
		t.commit();
	}

	@Override
	public void update(Parameter parametro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(parametro);
		t.commit();
	}

	@Override
	public Parameter verificarParametro(String textValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Parameter) session.createQuery("from Parameter WHERE textValue = '"+ textValue+ "'").uniqueResult();
	}

}
