package com.unbosque.edu.co.calendarioFifa.dao;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.entity.Goalscorer;

/**
 * The Interface GoalscorerDAO.
 */
public interface GoalscorerDAO {

	/**
	 * Save.
	 *
	 * @param goleador the goleador
	 */
	public void save(Goalscorer goleador);

	/**
	 * Gets the goalscorer.
	 *
	 * @param id the id
	 * @return the goalscorer
	 */
	public Goalscorer getGoalscorer(long id);

	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<Goalscorer> list();

	/**
	 * Removes the.
	 *
	 * @param goleador the goleador
	 */
	public void remove(Goalscorer goleador);

	/**
	 * Update.
	 *
	 * @param goleadors the goleadors
	 */
	public void update(Goalscorer goleadors);

	Goalscorer verificarGoleador(String fullName);

}

