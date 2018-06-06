package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import com.unbosque.edu.co.calendarioFifa.entity.Audit;
import com.unbosque.edu.co.calendarioFifa.entity.Goalscorer;
import com.unbosque.edu.co.calendarioFifa.service.AuditService;
import com.unbosque.edu.co.calendarioFifa.service.GoalscorerService;
import com.unbosque.edu.co.calendarioFifa.util.DireccionIp;

/**
 * The Class GoalscorerBean.
 */
@ManagedBean
@SessionScoped
public class GoalscorerBean {

	/** The Constant log. */
	final static Logger log = Logger.getLogger(GoalscorerBean.class);
	
	/** The goleador. */
	private Goalscorer goleador;
	
	/** The lista goleadores. */
	private DataModel listaGoleadores;
	
	@ManagedProperty("#{userBean}")
	private UserBean userBean;
	
	@ManagedProperty("#{auditBean}")
	private AuditBean auditBean;
	
	
	
	public AuditBean getAuditBean() {
		return auditBean;
	}

	public void setAuditBean(AuditBean auditBean) {
		this.auditBean = auditBean;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * Gets the goleador.
	 *
	 * @return the goleador
	 */
	public Goalscorer getGoleador() {
		return goleador;
	}
	
	/**
	 * Sets the goleador.
	 *
	 * @param goleador the new goleador
	 */
	public void setGoleador(Goalscorer goleador) {
		this.goleador = goleador;
	}
	
	/**
	 * Preparar adicionar goleador.
	 *
	 * @return the string
	 */
	public String prepararAdicionarGoleador() {
		goleador = new Goalscorer();
		
		return "goalscorerAgregar";
	}
	
	/**
	 * Preparar modificar goleador.
	 *
	 * @return the string
	 */
	public String prepararModificarGoleador() {
		goleador = (Goalscorer) (listaGoleadores.getRowData());
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA MODIFICAR GOLEADOR");
		}
		return "goalscorerModificar";
	}
	
	
	/**
	 * Adicionar goleador.
	 *
	 * @return the string
	 */
	public String adicionarGoleador() {
		GoalscorerService dao = new GoalscorerService();
		
		Goalscorer existe = dao.verificarGoleador(goleador.getFullName());
		if(existe == null) {
		goleador.setPhoto(goleador.getFullName()+"jpg");
		dao.save(goleador);
		
		
		
		auditBean.adicionarAuditoria(userBean.getUsuario().getId(), "Goalscorer", goleador.getId(), DireccionIp.getRemoteAddress());
		if(log.isDebugEnabled()) {
			log.debug("ADICIONAR GOLEADOR");
		}
		return "funcional";
		}
		return "goalscorerAgregar";
	}
	
	/**
	 * Modificar goleador.
	 *
	 * @return the string
	 */
	public String modificarGoleador() {
		GoalscorerService dao = new GoalscorerService();
		dao.update(goleador);
		auditBean.actualizarAuditoria(userBean.getUsuario().getId(), "Goalscorer", goleador.getId(), DireccionIp.getRemoteAddress());
		if(log.isDebugEnabled()) {
			log.debug("MODIFICAR EQUIPO");
		}
		return "funcional";
	}
	
	/**
	 * Gets the listar goleadores.
	 *
	 * @return the listar goleadores
	 */
	public DataModel getListarGoleadores() {
		List<Goalscorer> lista = new GoalscorerService().list();
		listaGoleadores = new ListDataModel(lista);
		if(log.isDebugEnabled()) {
			log.debug("LISTAR GOLEADORES");
		}
		return listaGoleadores;
	}
	
}
