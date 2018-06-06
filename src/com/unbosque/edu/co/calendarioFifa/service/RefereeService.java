package com.unbosque.edu.co.calendarioFifa.service;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.dao.impl.RefereeDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.Referee;

/**
 * The Class RefereeService.
 */
public class RefereeService {

/** The dao. */
private RefereeDAOImpl dao = new RefereeDAOImpl();
	
	/**
	 * Save.
	 *
	 * @param arbitro the arbitro
	 */
	public void save(Referee arbitro) {
		dao.save(arbitro);
	}
	
	/**
	 * Gets the arbitro.
	 *
	 * @param id the id
	 * @return the arbitro
	 */
	public Referee getArbitro(long id) {
		return dao.getReferee(id);
	}
	
	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<Referee> list(){
		return dao.list();
	}
	
	/**
	 * Removes the.
	 *
	 * @param arbitro the arbitro
	 */
	public void remove(Referee arbitro) {
		dao.remove(arbitro);
	}
	
	/**
	 * Update.
	 *
	 * @param arbitro the arbitro
	 */
	public void update(Referee arbitro) {
		dao.update(arbitro);
	}

	public Referee verificarArbitro(String fullName) {
		return dao.verificarArbitro(fullName);
	}
}
