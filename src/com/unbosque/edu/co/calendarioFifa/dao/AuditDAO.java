package com.unbosque.edu.co.calendarioFifa.dao;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.entity.Audit;

public interface AuditDAO {

	public void save(Audit auditoria);

	public List<Audit> list();


	public Audit getAudit(long id);



	public void update(Audit auditoria);
}
