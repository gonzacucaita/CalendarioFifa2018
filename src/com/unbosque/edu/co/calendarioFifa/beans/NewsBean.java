package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.unbosque.edu.co.calendarioFifa.entity.New;
import com.unbosque.edu.co.calendarioFifa.service.NewService;

@ManagedBean
@SessionScoped
public class NewsBean {

	private New noticia;
	private DataModel listaNoticia;
	
	public New getNoticia() {
		return noticia;
	}
	
	public void setNoticia(New noticia) {
		this.noticia = noticia;
	}
	
	public String prepararAdicionarNoticia() {
		noticia = new New();
		noticia.setState("A");
		return "new";
	}
	
	public String prepararModificarNoticia() {
		noticia = (New) (listaNoticia.getRowData());
		return "new";
	}
	
	public String eliminarNoticia() {
		New teamTemp = (New)(listaNoticia.getRowData());
		NewService dao = new NewService();
		teamTemp.setState("I");
		dao.update(teamTemp);
		return "inicio";
	}
	
	public String adicionarNoticia() {
		NewService dao = new NewService();
		dao.save(noticia);
		return "inicio";
	}
	
	public String modificarEquipo() {
		NewService dao = new NewService();
		dao.update(noticia);
		return "inicio";
	}
	
	public DataModel getListarEquipos() {
		List<New> lista = new NewService().list();
		listaNoticia = new ListDataModel(lista);
		return listaNoticia;
	}
}
