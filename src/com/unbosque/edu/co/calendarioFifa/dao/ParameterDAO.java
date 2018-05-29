package com.unbosque.edu.co.calendarioFifa.dao;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.entity.Parameter;


/**
 * The Interface ParameterDAO.
 */
public interface ParameterDAO {
	
	/**
	 * Save.
	 *
	 * @param parametro the parametro
	 */
	public void save(Parameter parametro);

	/**
	 * Gets the parameter.
	 *
	 * @param id the id
	 * @return the parameter
	 */
	public Parameter getParameter(long id);
	
	/**
	 * Gets the parameter from user.
	 *
	 * @param idUser the id user
	 * @return the parameter from user
	 */
	public Parameter getParameterFromUser(String idUser);

	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<Parameter> list();

	/**
	 * Removes the.
	 *
	 * @param parametro the parametro
	 */
	public void remove(Parameter parametro);

	/**
	 * Update.
	 *
	 * @param parametro the parametro
	 */
	public void update(Parameter parametro);
	
	/**
	 * Verificar parametro.
	 *
	 * @param textValue the text value
	 * @return the parameter
	 */
	public Parameter verificarParametro(String textValue);
}

