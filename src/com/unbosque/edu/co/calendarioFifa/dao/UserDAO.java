package com.unbosque.edu.co.calendarioFifa.dao;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.entity.User;

/**
 * The Interface UserDAO.
 */
public interface UserDAO {

	/**
	 * Save.
	 *
	 * @param usuario the usuario
	 */
	public void save(User usuario);
	
	/**
	 * Gets the usuario.
	 *
	 * @param id the id
	 * @return the usuario
	 */
	public User getUsuario(long id);
	
	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<User> list();
	
	/**
	 * Removes the.
	 *
	 * @param usuario the usuario
	 */
	public void remove(User usuario);
	
	/**
	 * Verificar usuario.
	 *
	 * @param userName the user name
	 * @return the user
	 */
	public User verificarUsuario(String userName); 
	
	/**
	 * Update.
	 *
	 * @param usuario the usuario
	 */
	public void update(User usuario);
	
}
