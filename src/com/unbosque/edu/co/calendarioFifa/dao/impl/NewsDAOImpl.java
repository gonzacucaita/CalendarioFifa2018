package com.unbosque.edu.co.calendarioFifa.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.unbosque.edu.co.calendarioFifa.dao.NewsDAO;
import com.unbosque.edu.co.calendarioFifa.entity.New;
import com.unbosque.edu.co.calendarioFifa.util.HibernateUtil;

public class NewsDAOImpl implements NewsDAO {

	@Override
	public void save(New noticia) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(noticia);
		t.commit();
	}

	@Override
	public New getNews(long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		return (New) session.load(New.class, id);
	}

	@Override
	public List<New> list() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from New").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(New noticia) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(noticia);
		t.commit();
	}

	@Override
	public void update(New noticia) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(noticia);
		t.commit();
	}

	@Override
	public New verificarNoticia(String shortDescription) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		return (New) session.createQuery("from New WHERE shortDescription = '"+shortDescription+"'").uniqueResult();
	}

}
