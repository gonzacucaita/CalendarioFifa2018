package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import com.unbosque.edu.co.calendarioFifa.entity.Parameter;
import com.unbosque.edu.co.calendarioFifa.service.ParameterService;

/**
 * The Class ParameterBean.
 */
@ManagedBean
@SessionScoped
public class ParameterBean {

	/** The parametro. */
	private Parameter parametro;
	
	/** The lista parametros. */
	private DataModel listaParametros;
	
	/** The Constant log. */
	final static Logger log = Logger.getLogger(ParameterBean.class);
	
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
	 * Gets the parametro.
	 *
	 * @return the parametro
	 */
	public Parameter getParametro() {
		return parametro;
	}
	
	/**
	 * Sets the parametro.
	 *
	 * @param parametro the new parametro
	 */
	public void setParametro(Parameter parametro) {
		this.parametro = parametro;
	}
	
	/**
	 * Preparar adicionar parametro.
	 *
	 * @return the string
	 */
	public String prepararAdicionarParametro() {
		parametro = new Parameter();
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return "parameter";
	}
	
	/**
	 * Preparar modificar parametro.
	 *
	 * @return the string
	 */
	public String prepararModificarParametro() {
		parametro = (Parameter) (listaParametros.getRowData());
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return "parameter";
	}
	
	/**
	 * Eliminar parametro.
	 *
	 * @return the string
	 */
	public String eliminarParametro() {
		Parameter parametroTemp = (Parameter)(listaParametros.getRowData());
		ParameterService dao = new ParameterService();
		dao.update(parametroTemp);
		
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return "inicio";
	}
	
	/**
	 * Adicionar parametro.
	 *
	 * @return the string
	 */
	public String adicionarParametro() {
		ParameterService dao = new ParameterService();
		dao.save(parametro);
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return "inicio";
	}
	
	/**
	 * Modificar parametro.
	 *
	 * @return the string
	 */
	public String modificarParametro() {
		ParameterService dao = new ParameterService();
		dao.update(parametro);
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return "inicio";
	}
	
	/**
	 * Gets the listar parametros.
	 *
	 * @return the listar parametros
	 */
	public DataModel getListarParametros() {
		List<Parameter> lista = new ParameterService().list();
		listaParametros = new ListDataModel(lista);
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return listaParametros;
	}
}
