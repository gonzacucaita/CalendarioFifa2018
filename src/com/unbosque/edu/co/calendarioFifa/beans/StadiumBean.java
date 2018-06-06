package com.unbosque.edu.co.calendarioFifa.beans;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import com.unbosque.edu.co.calendarioFifa.entity.Stadium;
import com.unbosque.edu.co.calendarioFifa.service.StadiumService;
import com.unbosque.edu.co.calendarioFifa.util.DireccionIp;

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
	
	private List<String> estadios;
	
	@PostConstruct
    public void init() {
		estadios = new LinkedList<>();
		estadios.add("Rostov Arena");
		estadios.add("Spartak stadium");
		estadios.add("Luzhniki Stadium");
		estadios.add("Nizhny Novgorod Stadium");
		estadios.add("Samara Arena");
		estadios.add("Kazan Arena");
		estadios.add("Saint Petersburg Stadium");
		estadios.add("Mordovia Arena");
		estadios.add("Ekaterinburg Arena");
		estadios.add("Kaliningrad Stadium");
		estadios.add("Volgogrado Arena");
		estadios.add("Fisht Stadium");
	}
	
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
		
		Stadium existe = dao.verificarEstadio(estadio.getCity());
		if(existe == null) {
		estadio.setPhoto(estadio.getCity()+".jpg");
		dao.save(estadio);
		auditBean.adicionarAuditoria(userBean.getUsuario().getId(), "Team", estadio.getId(), DireccionIp.getRemoteAddress());
		
		if(log.isDebugEnabled()) {
			log.debug("ADICIONAR ESTADIO");
		}
		return "funcional";
		}
		return "stadiumAgregar";
	}
	
	
	/**
	 * Modificar estadio.
	 *
	 * @return the string
	 */
	public String modificarEstadio() {
		StadiumService dao = new StadiumService();
		dao.update(estadio);
		auditBean.actualizarAuditoria(userBean.getUsuario().getId(), "Team", estadio.getId(), DireccionIp.getRemoteAddress());
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
	
	public List<String> getEstadios(){
		return estadios;
	}
}