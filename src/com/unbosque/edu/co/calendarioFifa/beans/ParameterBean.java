package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import com.unbosque.edu.co.calendarioFifa.entity.Parameter;
import com.unbosque.edu.co.calendarioFifa.service.ParameterService;

@ManagedBean
@SessionScoped
public class ParameterBean {

	private Parameter parametro;
	private DataModel listaParametros;
	final static Logger log = Logger.getLogger(ParameterBean.class);
	
	
	public Parameter getParametro() {
		return parametro;
	}
	
	public void setParametro(Parameter parametro) {
		this.parametro = parametro;
	}
	
	public String prepararAdicionarParametro() {
		parametro = new Parameter();
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return "parameter";
	}
	
	public String prepararModificarParametro() {
		parametro = (Parameter) (listaParametros.getRowData());
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return "parameter";
	}
	
	public String eliminarParametro() {
		Parameter parametroTemp = (Parameter)(listaParametros.getRowData());
		ParameterService dao = new ParameterService();
		dao.update(parametroTemp);
		
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return "inicio";
	}
	
	public String adicionarParametro() {
		ParameterService dao = new ParameterService();
		dao.save(parametro);
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return "inicio";
	}
	
	public String modificarParametro() {
		ParameterService dao = new ParameterService();
		dao.update(parametro);
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return "inicio";
	}
	
	public DataModel getListarParametros() {
		List<Parameter> lista = new ParameterService().list();
		listaParametros = new ListDataModel(lista);
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return listaParametros;
	}
}
