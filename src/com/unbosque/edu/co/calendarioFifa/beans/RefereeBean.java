package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import com.unbosque.edu.co.calendarioFifa.entity.Referee;
import com.unbosque.edu.co.calendarioFifa.entity.User;
import com.unbosque.edu.co.calendarioFifa.service.RefereeService;
import com.unbosque.edu.co.calendarioFifa.util.DireccionIp;

/**
 * The Class RefereeBean.
 */
@ManagedBean
@SessionScoped
public class RefereeBean {

	
	 /** The arbitro. */
 	private Referee arbitro;
	 
 	/** The lista arbitro. */
 	private DataModel listaArbitro;
	 
	 
	
	 /** The Constant log. */
 	final static Logger log = Logger.getLogger(RefereeBean.class);
		
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
 	 * Gets the arbitro.
 	 *
 	 * @return the arbitro
 	 */
 	public Referee getArbitro() {
		 return arbitro;
	 }
	 
	 /**
 	 * Sets the arbitro.
 	 *
 	 * @param arbitro the new arbitro
 	 */
 	public void setArbitro(Referee arbitro) {
		 this.arbitro = arbitro;
	 }
	 
 	/**
 	 * Preparar adicionar arbitro.
 	 *
 	 * @return the string
 	 */
 	public String prepararAdicionarArbitro() {
			arbitro = new Referee();
			arbitro.setState("A");
			if(log.isDebugEnabled()) {
				log.debug("PREPARAR PARA ADICIONAR ARBITRO");
			}
			return "refereeAgregar";
		}
		
		/**
		 * Preparar modificar arbitro.
		 *
		 * @return the string
		 */
		public String prepararModificarArbitro() {
			arbitro = (Referee) (listaArbitro.getRowData());
			if(log.isDebugEnabled()) {
				log.debug("PREPARAR PARA MODIFICAR ARBITRO");
			}
			return "refereeModificar";
		}
		
		/**
		 * Eliminar arbitro.
		 *
		 * @return the string
		 */
		public String eliminarArbitro() {
			arbitro = (Referee)(listaArbitro.getRowData());
			RefereeService dao = new RefereeService();
			arbitro.setState("I");
			dao.update(arbitro);
			auditBean.bloquearAuditoria(userBean.getUsuario().getId(), "Team", arbitro.getId(), DireccionIp.getRemoteAddress());
			
			if(log.isDebugEnabled()) {
				log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
			}
			return "inicio";
		}
		
		/**
		 * Adicionar arbitro.
		 *
		 * @return the string
		 */
		public String adicionarArbitro() {
			RefereeService dao = new RefereeService();
			
			Referee existe = dao.verificarArbitro(arbitro.getFullName());
			if(existe == null) {
			dao.save(arbitro);
			auditBean.adicionarAuditoria(userBean.getUsuario().getId(), "Team", arbitro.getId(), DireccionIp.getRemoteAddress());
			if(log.isDebugEnabled()) {
				log.debug("ADICIONAR ARBITRO");
			}
			return "funcional";
			}
			return "refereeAgregar";
		}
		
		/**
		 * Modificar arbitro.
		 *
		 * @return the string
		 */
		public String modificarArbitro() {
			RefereeService dao = new RefereeService();
			dao.update(arbitro);
			auditBean.actualizarAuditoria(userBean.getUsuario().getId(), "Team", arbitro.getId(), DireccionIp.getRemoteAddress());
			if(log.isDebugEnabled()) {
				log.debug("MODIFICAR ARBITRO");
			}
			return "funcional";
		}
		
		/**
		 * Gets the listar arbitros.
		 *
		 * @return the listar arbitros
		 */
		public DataModel getListarArbitros() {
			List<Referee> lista = new RefereeService().list();
			listaArbitro = new ListDataModel(lista);
			if(log.isDebugEnabled()) {
				log.debug("LISTAR ARBITROS");
			}
			return listaArbitro;
		}

		
	
		
}
