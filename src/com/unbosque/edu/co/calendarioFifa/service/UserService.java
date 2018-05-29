package com.unbosque.edu.co.calendarioFifa.service;

import com.unbosque.edu.co.calendarioFifa.dao.impl.UserDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.User;

import java.util.List;

/**
 * The Class UserService.
 */
public class UserService {

	/** The dao. */
	private UserDAOImpl dao = new UserDAOImpl();
	
	/**
	 * Save.
	 *
	 * @param usuario the usuario
	 */
	public void save(User usuario) {
		dao.save(usuario);
	}
	
	/**
	 * Gets the usuario.
	 *
	 * @param id the id
	 * @return the usuario
	 */
	public User getUsuario(long id) {
		return dao.getUsuario(id);
	}
	
	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<User> list(){
		return dao.list();
	}
	
	/**
	 * Removes the.
	 *
	 * @param usuario the usuario
	 */
	public void remove(User usuario) {
		dao.remove(usuario);
	}
	
	/**
	 * Update.
	 *
	 * @param usuario the usuario
	 */
	public void update(User usuario) {
		dao.update(usuario);
	}
	
	/**
	 * Verificar usuario.
	 *
	 * @param userName the user name
	 * @return the user
	 */
	public User verificarUsuario(String userName) {
		
		return dao.verificarUsuario(userName);
	}
}
