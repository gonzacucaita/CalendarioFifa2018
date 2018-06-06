package com.unbosque.edu.co.calendarioFifa.dao;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.entity.New;

/**
 * The Interface NewsDAO.
 */
public interface NewsDAO {

	/**
	 * Save.
	 *
	 * @param noticia the noticia
	 */
	public void save(New noticia);
	
	/**
	 * Gets the news.
	 *
	 * @param id the id
	 * @return the news
	 */
	public New getNews(long id);
	
	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<New> list();
	
	/**
	 * Removes the.
	 *
	 * @param noticia the noticia
	 */
	public void remove(New noticia);
	
	/**
	 * Update.
	 *
	 * @param noticia the noticia
	 */
	public void update(New noticia);

	New verificarNoticia(String shortDescription);
}

