package com.unbosque.edu.co.calendarioFifa.service;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.dao.impl.StadiumDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.Stadium;


public class StadiumService {
private StadiumDAOImpl dao = new StadiumDAOImpl();
	
	public void save(Stadium estadio) {
		dao.save(estadio);
	}
	
	public Stadium getEstadio(long id) {
		return dao.getStadium(id);
	}
	
	public List<Stadium> list(){
		return dao.list();
	}
	
	public void remove(Stadium estadio) {
		dao.remove(estadio);
	}
	
	public void update(Stadium estadio) {
		dao.update(estadio);
	}
}
