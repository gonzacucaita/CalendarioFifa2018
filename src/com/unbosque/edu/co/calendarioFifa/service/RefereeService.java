package com.unbosque.edu.co.calendarioFifa.service;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.dao.impl.RefereeDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.Referee;


public class RefereeService {
private RefereeDAOImpl dao = new RefereeDAOImpl();
	
	public void save(Referee arbitro) {
		dao.save(arbitro);
	}
	
	public Referee getArbitro(long id) {
		return dao.getReferee(id);
	}
	
	public List<Referee> list(){
		return dao.list();
	}
	
	public void remove(Referee arbitro) {
		dao.remove(arbitro);
	}
	
	public void update(Referee arbitro) {
		dao.update(arbitro);
	}
}
