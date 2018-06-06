package com.unbosque.edu.co.calendarioFifa.dao;


import java.util.List;

import com.unbosque.edu.co.calendarioFifa.entity.Referee;

/**
 * The Interface RefereeDAO.
 */
public interface RefereeDAO {


	/**
	 * Save.
	 *
	 * @param arbitro the arbitro
	 */
	public void save(Referee arbitro);
	
	/**
	 * Gets the referee.
	 *
	 * @param id the id
	 * @return the referee
	 */
	public Referee getReferee(long id);
	
	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<Referee> list();
	
	/**
	 * Removes the.
	 *
	 * @param arbitro the arbitro
	 */
	public void remove(Referee arbitro);
	
	/**
	 * Update.
	 *
	 * @param arbitro the arbitro
	 */
	public void update(Referee arbitro);

	Referee verificarArbitro(String fullName);

	
}

