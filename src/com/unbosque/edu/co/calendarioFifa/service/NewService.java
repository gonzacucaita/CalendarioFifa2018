package com.unbosque.edu.co.calendarioFifa.service;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.dao.impl.NewsDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.New;


public class NewService {
	
private NewsDAOImpl dao = new NewsDAOImpl();
	
	public void save(New noticia) {
		dao.save(noticia);
	}
	
	public New getUsuario(long id) {
		return dao.getNews(id);
	}
	
	public List<New> list(){
		return dao.list();
	}
	
	public void remove(New noticia) {
		dao.remove(noticia);
	}
	
	public void update(New noticia){
		dao.update(noticia);
	}
}
