package com.unbosque.edu.co.calendarioFifa.service;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.dao.impl.ParameterDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.Parameter;


public class ParameterService {

	private ParameterDAOImpl dao = new ParameterDAOImpl();
	
	public void save(Parameter parametro) {
		dao.save(parametro);
	}
	
	public Parameter getParametro(long id) {
		return dao.getParameter(id);
	}
	
	public Parameter getParametroPorUsuario(long idUsuario) {
		return dao.getParameterFromUser(idUsuario+"");
	}
	
	public List<Parameter> list(){
		return dao.list();
	}
	
	public void remove(Parameter parametro) {
		dao.remove(parametro);
	}
	
	public void update(Parameter parametro) {
		dao.update(parametro);
	}
}
