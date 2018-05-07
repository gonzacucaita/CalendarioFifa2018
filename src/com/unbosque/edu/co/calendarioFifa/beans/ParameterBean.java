package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.unbosque.edu.co.calendarioFifa.entity.Parameter;
import com.unbosque.edu.co.calendarioFifa.service.ParameterService;

@ManagedBean
@SessionScoped
public class ParameterBean {

	private Parameter parametro;
	private DataModel listaParametros;
	
	public Parameter getParametro() {
		return parametro;
	}
	
	public void setParametro(Parameter parametro) {
		this.parametro = parametro;
	}
	
	public String prepararAdicionarParametro() {
		parametro = new Parameter();
		return "parameter";
	}
	
	public String prepararModificarParametro() {
		parametro = (Parameter) (listaParametros.getRowData());
		return "parameter";
	}
	
	public String eliminarParametro() {
		Parameter parametroTemp = (Parameter)(listaParametros.getRowData());
		ParameterService dao = new ParameterService();
		dao.update(parametroTemp);
		return "inicio";
	}
	
	public String adicionarParametro() {
		ParameterService dao = new ParameterService();
		dao.save(parametro);
		return "inicio";
	}
	
	public String modificarParametro() {
		ParameterService dao = new ParameterService();
		dao.update(parametro);
		return "inicio";
	}
	
	public DataModel getListarParametros() {
		List<Parameter> lista = new ParameterService().list();
		listaParametros = new ListDataModel(lista);
		return listaParametros;
	}
}
