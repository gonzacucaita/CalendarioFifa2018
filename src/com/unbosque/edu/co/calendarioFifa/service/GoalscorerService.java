package com.unbosque.edu.co.calendarioFifa.service;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.dao.impl.GoalscorerDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.Goalscorer;

/**
 * The Class GoalscorerService.
 */
public class GoalscorerService {

	/** The dao. */
	private GoalscorerDAOImpl dao = new GoalscorerDAOImpl();
	
	/**
	 * Save.
	 *
	 * @param goleador the goleador
	 */
	public void save(Goalscorer goleador) {
		dao.save(goleador);
	}
	
	/**
	 * Gets the goleador.
	 *
	 * @param id the id
	 * @return the goleador
	 */
	public Goalscorer getGoleador(long id) {
		return dao.getGoalscorer(id);
	}
	
	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<Goalscorer> list(){
		return dao.list();
	}
	
	/**
	 * Removes the.
	 *
	 * @param goleador the goleador
	 */
	public void remove(Goalscorer goleador) {
		dao.remove(goleador);
	}
	
	/**
	 * Update.
	 *
	 * @param goleador the goleador
	 */
	public void update(Goalscorer goleador) {
		dao.update(goleador);
	}
}
