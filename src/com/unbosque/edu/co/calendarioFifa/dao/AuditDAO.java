package com.unbosque.edu.co.calendarioFifa.dao;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.entity.Audit;

/**
 * The Interface AuditDAO.
 */
public interface AuditDAO {

	/**
	 * Save.
	 *
	 * @param auditoria the auditoria
	 */
	public void save(Audit auditoria);

	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<Audit> list();

	/**
	 * Gets the audit.
	 *
	 * @param id the id
	 * @return the audit
	 */
	public Audit getAudit(long id);

	/**
	 * Update.
	 *
	 * @param auditoria the auditoria
	 */
	public void update(Audit auditoria);
}
