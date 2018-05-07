package com.unbosque.edu.co.calendarioFifa.service;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.dao.impl.AuditDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.Audit;


public class AuditService {
private AuditDAOImpl dao = new AuditDAOImpl();
	
	public void save(Audit auditoria) {
		dao.save(auditoria);
	}
	
	public Audit getEquipo(long id) {
		return dao.getAudit(id);
	}
	
	public List<Audit> list(){
		return dao.list();
	}
	
	
	public void update(Audit auditoria) {
		dao.update(auditoria);
	}
}
