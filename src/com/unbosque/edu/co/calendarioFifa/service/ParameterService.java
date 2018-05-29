package com.unbosque.edu.co.calendarioFifa.service;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.dao.impl.ParameterDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.Parameter;

/**
 * The Class ParameterService.
 */
public class ParameterService {

	/** The dao. */
	private ParameterDAOImpl dao = new ParameterDAOImpl();
	
	/**
	 * Save.
	 *
	 * @param parametro the parametro
	 */
	public void save(Parameter parametro) {
		dao.save(parametro);
	}
	
	/**
	 * Gets the parametro.
	 *
	 * @param id the id
	 * @return the parametro
	 */
	public Parameter getParametro(long id) {
		return dao.getParameter(id);
	}
	
	/**
	 * Gets the parametro por usuario.
	 *
	 * @param idUsuario the id usuario
	 * @return the parametro por usuario
	 */
	public Parameter getParametroPorUsuario(long idUsuario) {
		return dao.getParameterFromUser(idUsuario+"");
	}
	
	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<Parameter> list(){
		return dao.list();
	}
	
	/**
	 * Removes the.
	 *
	 * @param parametro the parametro
	 */
	public void remove(Parameter parametro) {
		dao.remove(parametro);
	}
	
	/**
	 * Update.
	 *
	 * @param parametro the parametro
	 */
	public void update(Parameter parametro) {
		dao.update(parametro);
	}
	
	/**
	 * Verificar parametros.
	 *
	 * @param idUsuario the id usuario
	 * @return the parameter
	 */
	public Parameter verificarParametros(String idUsuario) {
		return dao.verificarParametro(idUsuario);
	}
}
