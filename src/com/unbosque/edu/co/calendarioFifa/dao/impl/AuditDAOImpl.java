package com.unbosque.edu.co.calendarioFifa.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.unbosque.edu.co.calendarioFifa.dao.AuditDAO;
import com.unbosque.edu.co.calendarioFifa.entity.Audit;
import com.unbosque.edu.co.calendarioFifa.entity.Goalscorer;
import com.unbosque.edu.co.calendarioFifa.entity.User;
import com.unbosque.edu.co.calendarioFifa.util.HibernateUtil;

public class AuditDAOImpl implements AuditDAO {

	@Override
	public void save(Audit auditoria) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(auditoria);
		t.commit();
	}

	@Override
	public Audit getAudit(long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Audit) session.load(Audit.class, id);
	}

	@Override
	public List<Audit> list() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Audit").list();
		t.commit();
		return lista;
	}

	

	@Override
	public void update(Audit auditoria) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(auditoria);
		t.commit();
	}


}
