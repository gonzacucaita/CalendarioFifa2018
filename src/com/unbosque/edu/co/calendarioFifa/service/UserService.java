package com.unbosque.edu.co.calendarioFifa.service;

import com.unbosque.edu.co.calendarioFifa.dao.impl.UserDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.User;

import java.util.List;
public class UserService {

	private UserDAOImpl dao = new UserDAOImpl();
	
	public void save(User usuario) {
		dao.save(usuario);
	}
	
	public User getUsuario(long id) {
		return dao.getUsuario(id);
	}
	
	public List<User> list(){
		return dao.list();
	}
	
	public void remove(User usuario) {
		dao.remove(usuario);
	}
	
	public void update(User usuario) {
		dao.update(usuario);
	}
	
	public User verificarUsuario(String userName, String password) {
		
		return dao.verificarUsuario(userName, password);
	}
}
