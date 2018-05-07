package com.unbosque.edu.co.calendarioFifa.dao;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.entity.Stadium;


public interface StadiumDAO {

public void save(Stadium estadio);
	
	public Stadium getStadium(long id);
	
	public List<Stadium> list();
	
	public void remove(Stadium estadio);
	
	public void update(Stadium estadio);
}
