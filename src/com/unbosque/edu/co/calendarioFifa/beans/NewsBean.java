package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import java.util.Collection;

import com.unbosque.edu.co.calendarioFifa.entity.New;
import com.unbosque.edu.co.calendarioFifa.service.NewService;
import com.unbosque.edu.co.calendarioFifa.util.DireccionIp;

/**
 * The Class NewsBean.
 */
@ManagedBean
@SessionScoped
public class NewsBean {

	/** The noticia. */
	private New noticia;
	
	/** The lista noticia. */
	private DataModel listaNoticia;
	
	/** The Constant log. */
	final static Logger log = Logger.getLogger(NewsBean.class);
	
	@ManagedProperty("#{userBean}")
	private UserBean userBean;
	
	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	@ManagedProperty("#{auditBean}")
	private AuditBean auditBean;
	
	
	
	public AuditBean getAuditBean() {
		return auditBean;
	}

	public void setAuditBean(AuditBean auditBean) {
		this.auditBean = auditBean;
	}

	
	/**
	 * Gets the noticia.
	 *
	 * @return the noticia
	 */
	public New getNoticia() {
		return noticia;
	}
	
	/**
	 * Sets the noticia.
	 *
	 * @param noticia the new noticia
	 */
	public void setNoticia(New noticia) {
		this.noticia = noticia;
	}
	
	/**
	 * Preparar adicionar noticia.
	 *
	 * @return the string
	 */
	public String prepararAdicionarNoticia() {
		noticia = new New();
		noticia.setState("A");
		noticia.setDateNews(new Date());
		noticia.setIdUser(userBean.getUsuario().getId());
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR NOTICIA");
		}
		return "newsAgregar";
	}
	
	/**
	 * Preparar modificar noticia.
	 *
	 * @return the string
	 */
	public String prepararModificarNoticia() {
		noticia = (New) (listaNoticia.getRowData());
		noticia.setDateNews(new Date());
		
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA MODIFICAR NOTICIA");
		}
		return "newsModificar";
	}
	
	/**
	 * Eliminar noticia.
	 *
	 * @return the string
	 */
	public String eliminarNoticia() {
		noticia = (New)(listaNoticia.getRowData());
		NewService dao = new NewService();
		noticia.setState("I");
		dao.update(noticia);
		auditBean.bloquearAuditoria(userBean.getUsuario().getId(), "News", noticia.getId(), DireccionIp.getRemoteAddress());
		if(log.isDebugEnabled()) {
			log.debug("ELIMINAR NOTICIA");
		}
		return "inicio";
	}
	
	/**
	 * Adicionar noticia.
	 *
	 * @return the string
	 */
	public String adicionarNoticia() {
		NewService dao = new NewService();
		dao.save(noticia);
		
		auditBean.adicionarAuditoria(userBean.getUsuario().getId(), "News", noticia.getId(), DireccionIp.getRemoteAddress());
		if(log.isDebugEnabled()) {
			log.debug("ADICIONAR NOTICIA");
		}
		return "funcional";
	}
	
	/**
	 * Modificar equipo.
	 *
	 * @return the string
	 */
	public String modificarNoticia() {
		NewService dao = new NewService();
		dao.update(noticia);
		auditBean.actualizarAuditoria(userBean.getUsuario().getId(), "News", noticia.getId(), DireccionIp.getRemoteAddress());
		if(log.isDebugEnabled()) {
			log.debug("MODIFICAR NOTICIA");
		}
		return "funcional";
	}
	
	/**
	 * Gets the listar noticias.
	 *
	 * @return the listar noticias
	 */
	public DataModel getListarNoticias() {
		List<New> lista = new NewService().list();
		listaNoticia = new ListDataModel(lista);
		
		if(log.isDebugEnabled()) {
			log.debug("LISTAR NOTICIAS");
		}
		return listaNoticia;
	}
	
	/**
	 * Gets the listar.
	 *
	 * @return the listar
	 */
	public List<New> getListar(){
		
		List<New> lista = new NewService().list();
		
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
		}
		return lista;
	}
}
