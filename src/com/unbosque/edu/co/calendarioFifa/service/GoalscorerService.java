package com.unbosque.edu.co.calendarioFifa.service;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.dao.impl.GoalscorerDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.Goalscorer;


public class GoalscorerService {

	private GoalscorerDAOImpl dao = new GoalscorerDAOImpl();
	
	public void save(Goalscorer goleador) {
		dao.save(goleador);
	}
	
	public Goalscorer getUsuario(long id) {
		return dao.getGoalscorer(id);
	}
	
	public List<Goalscorer> list(){
		return dao.list();
	}
	
	public void remove(Goalscorer goleador) {
		dao.remove(goleador);
	}
	
	public void update(Goalscorer goleador) {
		dao.update(goleador);
	}
}
