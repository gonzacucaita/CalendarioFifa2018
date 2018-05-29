package com.unbosque.edu.co.calendarioFifa.beans;

import java.io.Serializable;
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
	private Audit auditoria;
	
	/** The lista auditorias. */
	private DataModel listaAuditorias;
	
	/** The auditorias. */
	private String auditorias;
	
	/** The usuarios. */
	private String usuarios;
	
	/** The salir. */
	private String salir;
	  
	/** The listt. */
	private List<Audit> listt;
	
	@ManagedProperty("#{userBean}")
	private UserBean userBean;
	

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
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
	 * Gets the lista auditorias.
	 *
	 * @return the lista auditorias
	 */
	public DataModel getListaAuditorias() {
		return listaAuditorias;
	}
	
	/**
	 * Sets the lista auditorias.
	 *
	 * @param listaAuditorias the new lista auditorias
	 */
	public void setListaAuditorias(DataModel listaAuditorias) {
		this.listaAuditorias = listaAuditorias;
	}
	
	/**
	 * Gets the auditorias.
	 *
	 * @return the auditorias
	 */
	public String getAuditorias() {
		return auditorias;
	}
	
	/**
	 * Sets the auditorias.
	 *
	 * @param auditorias the new auditorias
	 */
	public void setAuditorias(String auditorias) {
		this.auditorias = auditorias;
	}
	
	/**
	 * Gets the usuarios.
	 *
	 * @return the usuarios
	 */
	public String getUsuarios() {
		return usuarios;
	}
	
	/**
	 * Sets the usuarios.
	 *
	 * @param usuarios the new usuarios
	 */
	public void setUsuarios(String usuarios) {
		this.usuarios = usuarios;
	}
	
	/**
	 * Gets the salir.
	 *
	 * @return the salir
	 */
	public String getSalir() {
		return salir;
	}
	
	/**
	 * Sets the salir.
	 *
	 * @param salir the new salir
	 */
	public void setSalir(String salir) {
		this.salir = salir;
	}
	
	/**
	 * Gets the audit.
	 *
	 * @return the audit
	 */
	public Audit getAudit() {
		return auditoria;
	}
	
	/**
	 * Sets the audit.
	 *
	 * @param audit the new audit
	 */
	public void setAudit(Audit audit) {
		this.auditoria = audit;
	}
	
	/**
	 * Preparar adicionar auditoria.
	 *
	 * @return the string
	 */
	public String prepararAdicionarAuditoria() {
		auditoria = new Audit();
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return "audit";
	}
	
	/**
	 * Preparar modificar auditoria.
	 *
	 * @return the string
	 */
	public String prepararModificarAuditoria() {
		auditoria = (Audit) (listaAuditorias.getRowData());
		
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA MODIFICAR LA AUDITORIA");
		}
		return "audit";
	}
	
	/**
	 * Eliminar auditoria.
	 *
	 * @return the string
	 */
	public String eliminarAuditoria() {
		Audit auditoriaTemp = (Audit)(listaAuditorias.getRowData());
		AuditService dao = new AuditService();
		dao.update(auditoriaTemp);
		if(log.isInfoEnabled()) {
			log.info("SE HA ELIMINADO LA AUDITORIA");
		}
		return "inicio";
	}
	
	/**
	 * Adicionar auditoria.
	 *
	 * @return the string
	 */
	public String adicionarAuditoria() {
		AuditService dao = new AuditService();
		dao.save(auditoria);
		if(log.isInfoEnabled()) {
			log.info("SE HA ADICIONADO LA AUDITORIA");
		}
		return "inicio";
	}
	
	/**
	 * Modificar auditoria.
	 *
	 * @return the string
	 */
	public String modificarAuditoria() {
		AuditService dao = new AuditService();
		dao.update(auditoria);
		if(log.isInfoEnabled()) {
			log.info("SE HA MODIFICADO LA AUDITORIA");
		}
		return "inicio";
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
	
	
}
