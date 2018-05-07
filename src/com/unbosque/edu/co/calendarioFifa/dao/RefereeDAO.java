package com.unbosque.edu.co.calendarioFifa.dao;


import java.util.List;

import com.unbosque.edu.co.calendarioFifa.entity.Referee;


public interface RefereeDAO {


	public void save(Referee arbitro);
	
	public Referee getReferee(long id);
	
	public List<Referee> list();
	
	public void remove(Referee arbitro);
	
	public void update(Referee arbitro);

	
}

