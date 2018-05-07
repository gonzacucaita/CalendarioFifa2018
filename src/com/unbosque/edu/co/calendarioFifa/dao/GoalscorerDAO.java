package com.unbosque.edu.co.calendarioFifa.dao;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.entity.Goalscorer;


public interface GoalscorerDAO {

	public void save(Goalscorer goleador);

	public Goalscorer getGoalscorer(long id);

	public List<Goalscorer> list();

	public void remove(Goalscorer goleador);

	public void update(Goalscorer goleadors);

}

