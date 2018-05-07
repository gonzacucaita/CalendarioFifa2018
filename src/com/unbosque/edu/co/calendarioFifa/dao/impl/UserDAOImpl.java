package com.unbosque.edu.co.calendarioFifa.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.unbosque.edu.co.calendarioFifa.dao.UserDAO;
import com.unbosque.edu.co.calendarioFifa.entity.User;
import com.unbosque.edu.co.calendarioFifa.util.HibernateUtil;

public class UserDAOImpl implements UserDAO{

	@Override
	public void save(User usuario) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(usuario);
		t.commit();
	}

	@Override
	public User getUsuario(long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		return (User) session.load(User.class, id);
	}

	@Override
	public List<User> list() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from User").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(User usuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(usuario);
		t.commit();
	}

	@Override
	public void update(User usuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(usuario);
		t.commit();
	}

}
