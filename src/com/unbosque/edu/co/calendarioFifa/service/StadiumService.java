package com.unbosque.edu.co.calendarioFifa.service;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.dao.impl.StadiumDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.Stadium;

/**
 * The Class StadiumService.
 */
public class StadiumService {

/** The dao. */
private StadiumDAOImpl dao = new StadiumDAOImpl();
	
	/**
	 * Save.
	 *
	 * @param estadio the estadio
	 */
	public void save(Stadium estadio) {
		dao.save(estadio);
	}
	
	/**
	 * Gets the estadio.
	 *
	 * @param id the id
	 * @return the estadio
	 */
	public Stadium getEstadio(long id) {
		return dao.getStadium(id);
	}
	
	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<Stadium> list(){
		return dao.list();
	}
	
	/**
	 * Removes the.
	 *
	 * @param estadio the estadio
	 */
	public void remove(Stadium estadio) {
		dao.remove(estadio);
	}
	
	/**
	 * Update.
	 *
	 * @param estadio the estadio
	 */
	public void update(Stadium estadio) {
		dao.update(estadio);
	}

	public Stadium verificarEstadio(String city) {
		return dao.verificarEstadiio(city);
	}
}
