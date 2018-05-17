package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.unbosque.edu.co.calendarioFifa.entity.Referee;
import com.unbosque.edu.co.calendarioFifa.service.RefereeService;

@ManagedBean
@SessionScoped
public class RefereeBean {

	
	 private Referee arbitro;
	 private DataModel listaArbitro;
	 
	 public Referee getArbitro() {
		 return arbitro;
	 }
	 
	 public void setArbitro(Referee arbitro) {
		 this.arbitro = arbitro;
	 }
	 public String prepararAdicionarArbitro() {
			arbitro = new Referee();
			arbitro.setState("A");
			return "refereeAgregar";
		}
		
		public String prepararModificarArbitro() {
			arbitro = (Referee) (listaArbitro.getRowData());
			return "refereeModificar";
		}
		
		public String eliminarArbitro() {
			Referee arbitroTemp = (Referee)(listaArbitro.getRowData());
			RefereeService dao = new RefereeService();
			arbitroTemp.setState("I");;
			dao.update(arbitroTemp);
			return "inicio";
		}
		
		public String adicionarArbitro() {
			RefereeService dao = new RefereeService();
			dao.save(arbitro);
			return "funcional";
		}
		
		public String modificarArbitro() {
			RefereeService dao = new RefereeService();
			dao.update(arbitro);
			return "funcional";
		}
		
		public DataModel getListarArbitros() {
			List<Referee> lista = new RefereeService().list();
			listaArbitro = new ListDataModel(lista);
			return listaArbitro;
		}
	
}
