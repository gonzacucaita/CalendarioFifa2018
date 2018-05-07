package com.unbosque.edu.co.calendarioFifa.beans;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.unbosque.edu.co.calendarioFifa.dao.impl.StadiumDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.Stadium;
import com.unbosque.edu.co.calendarioFifa.service.StadiumService;

@ManagedBean
@SessionScoped
public class StadiumBean {

	private Stadium estadio;
	private DataModel listaEstadio;
	
	public Stadium getEstadio() {
		return estadio;
	}
	
	public void setEstadio(Stadium estadio) {
		this.estadio = estadio;
	}
	public String prepararAdicionarEstadio() {
		estadio = new Stadium();
		return "stadium";
	}
	
	
	
	public String prepararModificarEstadio() {
		estadio = (Stadium) (listaEstadio.getRowData());
		return "stadium";
	}
	
	public String adicionarEstadio() {
		StadiumService dao = new StadiumService();
		dao.save(estadio);
		return "inicio";
	}
	
	public String eliminarEstadio() {
		StadiumService dao = new StadiumService();
		dao.remove(estadio);
		return "inicio";
	}
	public String modificarEstadio() {
		StadiumService dao = new StadiumService();
		dao.update(estadio);
		return "inicio";
	}
	
	public DataModel getListarEstadios() {
		List<Stadium> lista = new StadiumService().list();
		listaEstadio = new ListDataModel(lista);
		return listaEstadio;
	}
}