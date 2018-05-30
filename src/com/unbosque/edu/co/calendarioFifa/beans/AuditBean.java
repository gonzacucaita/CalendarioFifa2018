package com.unbosque.edu.co.calendarioFifa.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import com.unbosque.edu.co.calendarioFifa.dao.impl.AuditDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.Audit;
import com.unbosque.edu.co.calendarioFifa.entity.Team;
import com.unbosque.edu.co.calendarioFifa.entity.User;
import com.unbosque.edu.co.calendarioFifa.service.AuditService;
import com.unbosque.edu.co.calendarioFifa.service.TeamService;

/**
 * The Class AuditBean.
 */
@ManagedBean
@SessionScoped
public class AuditBean implements Serializable{

	/** The Constant log. */
	final static Logger log = Logger.getLogger(AuditBean.class);
	
	/** The auditoria. */
	private Audit auditoria = new Audit();
	
	/** The lista auditorias. */
	private DataModel listaAuditorias;
	  
	/** The listt. */
	private List<Audit> listt;
	
	/** The as. */
	private AuditService as = new AuditService();

	
	/**
	 * Gets the listt.
	 *
	 * @return the listt
	 */
	public List<Audit> getListt() {
		return listt = new AuditService().list();
	}
	
	/**
	 * Sets the listt.
	 *
	 * @param listt the new listt
	 */
	public void setListt(List<Audit> listt) {
		this.listt = listt;
	}
	
	/**
	 * Gets the auditoria.
	 *
	 * @return the auditoria
	 */
	public Audit getAuditoria() {
		return auditoria;
	}
	
	/**
	 * Sets the auditoria.
	 *
	 * @param auditoria the new auditoria
	 */
	public void setAuditoria(Audit auditoria) {
		this.auditoria = auditoria;
	}
	
	
	/**
	 * Adicionar auditoria.
	 *
	 * @param userId the user id
	 * @param tableName the table name
	 * @param tableId the table id
	 * @param ip the ip
	 * @return the string
	 */
	public void adicionarAuditoria(long userId, String tableName, long tableId,String ip) {

		auditoria.setUserId(userId);
		auditoria.setOperation("C");
		auditoria.setTableId(tableId);
		auditoria.setTableName(tableName);
		auditoria.setCreateDate(new Date());
		auditoria.setIp(ip);
		as.save(auditoria);
		if(log.isInfoEnabled()) {
			log.info("SE HA ADICIONADO LA AUDITORIA");
		}
	}

	/**
	 * Ingresar auditoria.
	 *
	 * @param userId the user id
	 * @param tableName the table name
	 * @param tableId the table id
	 * @param ip the ip
	 */
	public void ingresarAuditoria(long userId, String tableName, long tableId,String ip) {

		auditoria.setUserId(userId);
		auditoria.setOperation("E");
		auditoria.setTableId(tableId);
		auditoria.setTableName(tableName);
		auditoria.setCreateDate(new Date());
		auditoria.setIp(ip);
		as.save(auditoria);
		if(log.isInfoEnabled()) {
			log.info("SE HA ADICIONADO LA AUDITORIA");
		}
	}
	
	/**
	 * Salir auditoria.
	 *
	 * @param userId the user id
	 * @param tableName the table name
	 * @param tableId the table id
	 * @param ip the ip
	 */
	public void salirAuditoria(long userId, String tableName, long tableId,String ip) {

		auditoria.setUserId(userId);
		auditoria.setOperation("S");
		auditoria.setTableId(tableId);
		auditoria.setTableName(tableName);
		auditoria.setCreateDate(new Date());
		auditoria.setIp(ip);
		as.save(auditoria);
		if(log.isInfoEnabled()) {
			log.info("SE HA ADICIONADO LA AUDITORIA");
		}
	}

	/**
	 * Actualizar auditoria.
	 *
	 * @param userId the user id
	 * @param tableName the table name
	 * @param tableId the table id
	 * @param ip the ip
	 */
	public void actualizarAuditoria(long userId, String tableName, long tableId, String ip) {

		auditoria.setUserId(userId);
		auditoria.setOperation("U");
		auditoria.setTableId(tableId);
		auditoria.setTableName(tableName);
		auditoria.setCreateDate(new Date());
		auditoria.setIp(ip);
		as.save(auditoria);
		if(log.isInfoEnabled()) {
			log.info("SE HA ADICIONADO LA AUDITORIA");
		}
	}

	
	
	/**
	 * Bloquear auditoria.
	 *
	 * @param userId the user id
	 * @param tableName the table name
	 * @param tableId the table id
	 * @param ip the ip
	 */
	public void bloquearAuditoria(long userId, String tableName, long tableId, String ip) {

		auditoria.setUserId(userId);
		auditoria.setOperation("B");
		auditoria.setTableId(tableId);
		auditoria.setTableName(tableName);
		auditoria.setCreateDate(new Date());
		auditoria.setIp(ip);
		as.save(auditoria);
		if(log.isInfoEnabled()) {
			log.info("SE HA ADICIONADO LA AUDITORIA");
		}
	}
	
	
	
	/**
	 * Gets the listar auditorias.
	 *
	 * @return the listar auditorias
	 */
	public DataModel getListarAuditorias() {
		List<Audit> lista = new AuditService().list();
		listaAuditorias = new ListDataModel(lista);
		if(log.isInfoEnabled()) {
			log.info("SE HA LISTADO LA AUDITORIA");
		}
		return listaAuditorias;
	}
	
	/**
	 * Mostrar.
	 *
	 * @return the string
	 */
	public String mostrar() {
		String res = "";
		return res;
	}

	/**
	 * Gets the as.
	 *
	 * @return the as
	 */
	public AuditService getAs() {
		return as;
	}

	/**
	 * Sets the as.
	 *
	 * @param as the new as
	 */
	public void setAs(AuditService as) {
		this.as = as;
	}
	
	
	
	
}
