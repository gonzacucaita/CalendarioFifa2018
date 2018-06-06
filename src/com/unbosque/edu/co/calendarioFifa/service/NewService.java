package com.unbosque.edu.co.calendarioFifa.service;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.dao.impl.NewsDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.New;

/**
 * The Class NewService.
 */
public class NewService {
	
/** The dao. */
private NewsDAOImpl dao = new NewsDAOImpl();
	
	/**
	 * Save.
	 *
	 * @param noticia the noticia
	 */
	public void save(New noticia) {
		dao.save(noticia);
	}
	
	/**
	 * Gets the noticia.
	 *
	 * @param id the id
	 * @return the noticia
	 */
	public New getNoticia(long id) {
		return dao.getNews(id);
	}
	
	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<New> list(){
		return dao.list();
	}
	
	/**
	 * Removes the.
	 *
	 * @param noticia the noticia
	 */
	public void remove(New noticia) {
		dao.remove(noticia);
	}
	
	/**
	 * Update.
	 *
	 * @param noticia the noticia
	 */
	public void update(New noticia){
		dao.update(noticia);
	}

	public New verificarNoticia(String shortDescription) {
		return dao.verificarNoticia(shortDescription);
	}
}
