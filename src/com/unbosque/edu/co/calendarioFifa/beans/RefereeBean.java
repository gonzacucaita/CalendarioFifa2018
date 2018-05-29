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

@ManagedBean
@SessionScoped
public class RefereeBean {

	
	 private Referee arbitro;
	 private DataModel listaArbitro;
	 
	 private User usuario;
	 
	
	 final static Logger log = Logger.getLogger(RefereeBean.class);
		
	 public Referee getArbitro() {
		 return arbitro;
	 }
	 
	 public void setArbitro(Referee arbitro) {
		 this.arbitro = arbitro;
	 }
	 public String prepararAdicionarArbitro() {
			arbitro = new Referee();
			arbitro.setState("A");
			if(log.isDebugEnabled()) {
				log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
			}
			return "refereeAgregar";
		}
		
		public String prepararModificarArbitro() {
			arbitro = (Referee) (listaArbitro.getRowData());
			if(log.isDebugEnabled()) {
				log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
			}
			return "refereeModificar";
		}
		
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
		
		public String adicionarArbitro() {
			RefereeService dao = new RefereeService();
			dao.save(arbitro);
			if(log.isDebugEnabled()) {
				log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
			}
			return "funcional";
		}
		
		public String modificarArbitro() {
			RefereeService dao = new RefereeService();
			dao.update(arbitro);
			if(log.isDebugEnabled()) {
				log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
			}
			return "funcional";
		}
		
		public DataModel getListarArbitros() {
			
			List<Referee> lista = new RefereeService().list();
			listaArbitro = new ListDataModel(lista);
			if(log.isDebugEnabled()) {
				log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
			}
			return listaArbitro;
		}

		
	
		
}
