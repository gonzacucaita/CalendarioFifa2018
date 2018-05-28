package com.unbosque.edu.co.calendarioFifa.beans;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import com.unbosque.edu.co.calendarioFifa.dao.impl.StadiumDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.Stadium;
import com.unbosque.edu.co.calendarioFifa.service.StadiumService;

@ManagedBean
@SessionScoped
public class StadiumBean {

	private Stadium estadio;
	private DataModel listaEstadio;
	final static Logger log = Logger.getLogger(StadiumBean.class);
	
	
	public Stadium getEstadio() {
		return estadio;
	}
	
	public void setEstadio(Stadium estadio) {
		this.estadio = estadio;
	}
	public String prepararAdicionarEstadio() {
		estadio = new Stadium();
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR ESTADIO");
		}
		return "stadiumAgregar";
	}
	
	
	
	public String prepararModificarEstadio() {
		estadio = (Stadium) (listaEstadio.getRowData());
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA MODIFICAR ESTADIO");
		}
		return "stadiumModificar";
	}
	
	public String adicionarEstadio() {
		StadiumService dao = new StadiumService();
		dao.save(estadio);
		if(log.isDebugEnabled()) {
			log.debug("ADICIONAR ESTADIO");
		}
		return "funcional";
	}
	
	public String eliminarEstadio() {
		StadiumService dao = new StadiumService();
		dao.remove(estadio);
		if(log.isDebugEnabled()) {
			log.debug("ELIMINAR ESTADIO");
		}
		return "inicio";
	}
	public String modificarEstadio() {
		StadiumService dao = new StadiumService();
		dao.update(estadio);
		if(log.isDebugEnabled()) {
			log.debug("MODIFICAR ESTADIO");
		}
		return "funcional";
	}
	
	public DataModel getListarEstadios() {
		List<Stadium> lista = new StadiumService().list();
		listaEstadio = new ListDataModel(lista);
		if(log.isDebugEnabled()) {
			log.debug("LISTAR ESTADIOS");
		}
		return listaEstadio;
	}
}