package com.unbosque.edu.co.calendarioFifa.dao;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.entity.Stadium;

/**
 * The Interface StadiumDAO.
 */
public interface StadiumDAO {

/**
 * Save.
 *
 * @param estadio the estadio
 */
public void save(Stadium estadio);
	
	/**
	 * Gets the stadium.
	 *
	 * @param id the id
	 * @return the stadium
	 */
	public Stadium getStadium(long id);
	
	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<Stadium> list();
	
	/**
	 * Removes the.
	 *
	 * @param estadio the estadio
	 */
	public void remove(Stadium estadio);
	
	/**
	 * Update.
	 *
	 * @param estadio the estadio
	 */
	public void update(Stadium estadio);

	Stadium verificarEstadiio(String city);
}
