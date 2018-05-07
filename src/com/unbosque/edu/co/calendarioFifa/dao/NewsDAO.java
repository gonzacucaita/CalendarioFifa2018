package com.unbosque.edu.co.calendarioFifa.dao;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.entity.New;


public interface NewsDAO {

	public void save(New noticia);
	
	public New getNews(long id);
	
	public List<New> list();
	
	public void remove(New noticia);
	
	public void update(New noticia);
}

