package com.unbosque.edu.co.calendarioFifa.dao;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.entity.User;

public interface UserDAO {

	public void save(User usuario);
	
	public User getUsuario(long id);
	
	public List<User> list();
	
	public void remove(User usuario);
	
	public User verificarUsuario(String userName); 
	
	public void update(User usuario);
	
}
