package com.unbosque.edu.co.calendarioFifa.beans;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import com.unbosque.edu.co.calendarioFifa.dao.impl.StadiumDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.Stadium;
import com.unbosque.edu.co.calendarioFifa.service.StadiumService;

/**
 * The Class StadiumBean.
 */
@ManagedBean
@SessionScoped
public class StadiumBean {

	/** The estadio. */
	private Stadium estadio;
	
	/** The lista estadio. */
	private DataModel listaEstadio;
	
	/** The Constant log. */
	final static Logger log = Logger.getLogger(StadiumBean.class);
	
	@ManagedProperty("#{userBean}")
	private UserBean userBean;
	
	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	@ManagedProperty("#{auditBean}")
	private AuditBean auditBean;
	
	
	
	public AuditBean getAuditBean() {
		return auditBean;
	}

	public void setAuditBean(AuditBean auditBean) {
		this.auditBean = auditBean;
	}

	/**
	 * Gets the estadio.
	 *
	 * @return the estadio
	 */
	public Stadium getEstadio() {
		return estadio;
	}
	
	/**
	 * Sets the estadio.
	 *
	 * @param estadio the new estadio
	 */
	public void setEstadio(Stadium estadio) {
		this.estadio = estadio;
	}
	
	/**
	 * Preparar adicionar estadio.
	 *
	 * @return the string
	 */
	public String prepararAdicionarEstadio() {
		estadio = new Stadium();
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR ESTADIO");
		}
		return "stadiumAgregar";
	}
	
	
	
	/**
	 * Preparar modificar estadio.
	 *
	 * @return the string
	 */
	public String prepararModificarEstadio() {
		estadio = (Stadium) (listaEstadio.getRowData());
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA MODIFICAR ESTADIO");
		}
		return "stadiumModificar";
	}
	
	/**
	 * Adicionar estadio.
	 *
	 * @return the string
	 */
	public String adicionarEstadio() {
		StadiumService dao = new StadiumService();
		dao.save(estadio);
		if(log.isDebugEnabled()) {
			log.debug("ADICIONAR ESTADIO");
		}
		return "funcional";
	}
	
	/**
	 * Eliminar estadio.
	 *
	 * @return the string
	 */
	public String eliminarEstadio() {
		StadiumService dao = new StadiumService();
		dao.remove(estadio);
		if(log.isDebugEnabled()) {
			log.debug("ELIMINAR ESTADIO");
		}
		return "inicio";
	}
	
	/**
	 * Modificar estadio.
	 *
	 * @return the string
	 */
	public String modificarEstadio() {
		StadiumService dao = new StadiumService();
		dao.update(estadio);
		if(log.isDebugEnabled()) {
			log.debug("MODIFICAR ESTADIO");
		}
		return "funcional";
	}
	
	/**
	 * Gets the listar estadios.
	 *
	 * @return the listar estadios
	 */
	public DataModel getListarEstadios() {
		List<Stadium> lista = new StadiumService().list();
		listaEstadio = new ListDataModel(lista);
		if(log.isDebugEnabled()) {
			log.debug("LISTAR ESTADIOS");
		}
		return listaEstadio;
	}
}