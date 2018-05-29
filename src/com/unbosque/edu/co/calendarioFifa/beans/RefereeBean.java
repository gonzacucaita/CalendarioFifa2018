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
				log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
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
				log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
			}
			return "refereeModificar";
		}
		
		/**
		 * Eliminar arbitro.
		 *
		 * @return the string
		 */
		public String eliminarArbitro() {
			Referee arbitroTemp = (Referee)(listaArbitro.getRowData());
			RefereeService dao = new RefereeService();
			arbitroTemp.setState("I");
			dao.update(arbitroTemp);
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
			dao.save(arbitro);
			if(log.isDebugEnabled()) {
				log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
			}
			return "funcional";
		}
		
		/**
		 * Modificar arbitro.
		 *
		 * @return the string
		 */
		public String modificarArbitro() {
			RefereeService dao = new RefereeService();
			dao.update(arbitro);
			if(log.isDebugEnabled()) {
				log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
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
				log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
			}
			return listaArbitro;
		}

		
	
		
}
