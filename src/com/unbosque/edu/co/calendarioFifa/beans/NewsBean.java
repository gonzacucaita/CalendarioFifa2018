package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import java.util.Collection;

import com.unbosque.edu.co.calendarioFifa.entity.New;
import com.unbosque.edu.co.calendarioFifa.service.NewService;

@ManagedBean
@SessionScoped
public class NewsBean {

	private New noticia;
	private DataModel listaNoticia;
	final static Logger log = Logger.getLogger(NewsBean.class);
	

	
	public New getNoticia() {
		return noticia;
	}
	
	public void setNoticia(New noticia) {
		this.noticia = noticia;
	}
	
	public String prepararAdicionarNoticia() {
		noticia = new New();
		noticia.setState("A");
		noticia.setDateNews(new Date());
		
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return "newsAgregar";
	}
	
	public String prepararModificarNoticia() {
		noticia = (New) (listaNoticia.getRowData());
		noticia.setDateNews(new Date());
		
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return "newsModificar";
	}
	
	public String eliminarNoticia() {
		New teamTemp = (New)(listaNoticia.getRowData());
		NewService dao = new NewService();
		teamTemp.setState("I");
		dao.update(teamTemp);
		
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return "inicio";
	}
	
	public String adicionarNoticia() {
		NewService dao = new NewService();
		dao.save(noticia);
		
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return "funcional";
	}
	
	public String modificarEquipo() {
		NewService dao = new NewService();
		dao.update(noticia);
		
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return "funcional";
	}
	
	public DataModel getListarNoticias() {
		List<New> lista = new NewService().list();
		listaNoticia = new ListDataModel(lista);
		
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return listaNoticia;
	}
	public List<New> getListar(){
		
		List<New> lista = new NewService().list();
		
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return lista;
	}
}
