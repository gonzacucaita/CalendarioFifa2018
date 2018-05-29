package com.unbosque.edu.co.calendarioFifa.service;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.dao.impl.AuditDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.Audit;

/**
 * The Class AuditService.
 */
public class AuditService {

/** The dao. */
private AuditDAOImpl dao = new AuditDAOImpl();
	
	/**
	 * Save.
	 *
	 * @param auditoria the auditoria
	 */
	public void save(Audit auditoria) {
		dao.save(auditoria);
	}
	
	/**
	 * Gets the auditoria.
	 *
	 * @param id the id
	 * @return the auditoria
	 */
	public Audit getAuditoria(long id) {
		return dao.getAudit(id);
	}
	
	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<Audit> list(){
		return dao.list();
	}
	
	
	/**
	 * Update.
	 *
	 * @param auditoria the auditoria
	 */
	public void update(Audit auditoria) {
		dao.update(auditoria);
	}
}
