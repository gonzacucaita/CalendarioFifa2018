package com.unbosque.edu.co.calendarioFifa.dao;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.entity.Parameter;


public interface ParameterDAO {
	public void save(Parameter parametro);

	public Parameter getParameter(long id);

	public List<Parameter> list();

	public void remove(Parameter parametro);

	public void update(Parameter parametro);
}

