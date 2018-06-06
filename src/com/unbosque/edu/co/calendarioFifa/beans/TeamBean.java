package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import com.unbosque.edu.co.calendarioFifa.entity.Team;
import com.unbosque.edu.co.calendarioFifa.service.AuditService;
import com.unbosque.edu.co.calendarioFifa.service.TeamService;
import com.unbosque.edu.co.calendarioFifa.util.DireccionIp;

/**
 * The Class TeamBean.
 */
@ManagedBean
@SessionScoped
public class TeamBean {
	
	/** The equipo. */
	private Team equipo;
	
	/** The lista equipos. */
	private DataModel listaEquipos;
	
	private DataModel lista;
	
	/** The Constant log. */
	final static Logger log = Logger.getLogger(TeamBean.class);
	
	@ManagedProperty("#{userBean}")
	private UserBean userBean;
	
	@ManagedProperty("#{auditBean}")
	private AuditBean auditBean;
	
	private List<String> equipos;
	
	private List<String> grupos;
	
	@PostConstruct
    public void init() {
		equipos = new LinkedList<>();
		grupos = new LinkedList<>();
		equipos.add("Alemania"); equipos.add("Argentina");equipos.add("Arabia Saudí");equipos.add("Australia");equipos.add("Bélgica");equipos.add("Brasil");equipos.add("Colombia");
		equipos.add("Corea del Sur");equipos.add("Costa Rica");equipos.add("Croacia");equipos.add("Dinamarca");equipos.add("Egipto");
		equipos.add("España");equipos.add("Francia");equipos.add("Inglaterra");equipos.add("Irán");equipos.add("Islandia");	equipos.add("Japón");equipos.add("Marruecos");
		equipos.add("México");equipos.add("Nigeria");equipos.add("Panamá");equipos.add("Perú");equipos.add("Polonia");equipos.add("Portugal");
		equipos.add("Rusia");equipos.add("Senegal");equipos.add("Serbia");equipos.add("Suecia");equipos.add("Suiza");equipos.add("Túnez");
		equipos.add("Uruguay");
		
		grupos.add("A");
		grupos.add("B");
		grupos.add("C");
		grupos.add("D");
		grupos.add("E");
		grupos.add("F");
		grupos.add("G");
		grupos.add("H");
		

	}
	
	public AuditBean getAuditBean() {
		return auditBean;
	}

	public void setAuditBean(AuditBean auditBean) {
		this.auditBean = auditBean;
	}
	
	private AuditService as = new AuditService();
	
	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * Gets the equipo.
	 *
	 * @return the equipo
	 */
	public Team getEquipo() {
		return equipo;
	}

	/**
	 * Sets the equipo.
	 *
	 * @param team the new equipo
	 */
	public void setEquipo(Team team) {
		this.equipo = team;
	}
	
	/**
	 * Preparar adicionar equipo.
	 *
	 * @return the string
	 */
	public String prepararAdicionarEquipo() {
		equipo = new Team();
		equipo.setGoalsAgainst(0);
		equipo.setGoalsFavor(0);
		equipo.setLostMatches(0);
		equipo.setTiedMatches(0);
		equipo.setWonMatches(0);
		equipo.setPlayedGames(0);
		equipo.setState("A");
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR EQUIPO");
		}
		return "teamAgregar";
	}
	
	/**
	 * Preparar modificar equipo.
	 *
	 * @return the string
	 */
	public String prepararModificarEquipo() {
		equipo = (Team) (listaEquipos.getRowData());
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA MODIFICAR EQUIPO");
		}
		return "team";
	}
	
	/**
	 * Eliminar equipo.
	 *
	 * @return the string
	 */
	public String eliminarEquipo() {
		TeamService dao = new TeamService();
		equipo = (Team) (listaEquipos.getRowData());
		equipo.setState("I");
		dao.update(equipo);
		auditBean.bloquearAuditoria(userBean.getUsuario().getId(), "Team", equipo.getId(), DireccionIp.getRemoteAddress());
		if(log.isDebugEnabled()) {
			log.debug("ELIMINAR EQUIPO");
		}
		
//		audit.
		
		return "funcional";
	}
	
	/**
	 * Adicionar equipo.
	 *
	 * @return the string
	 */
	public String adicionarEquipo() {
	
		TeamService dao = new TeamService();
		Team existe = dao.validarEquipo(equipo.getCountry());
		if(existe == null) {
		equipo.setFlag(equipo.getCountry()+".jpg");
		dao.save(equipo);
		
		auditBean.adicionarAuditoria(userBean.getUsuario().getId(), "Team", equipo.getId(), DireccionIp.getRemoteAddress());
		if(log.isDebugEnabled()) {
			log.debug("ADICIONAR EQUIPO");
		}
		return "funcional";
		}
		return "teamAgregar";
		}
	
	/**
	 * Modificar equipo.
	 *
	 * @return the string
	 */
	public String modificarEquipo() {
		TeamService dao = new TeamService();
		dao.update(equipo);
		auditBean.actualizarAuditoria(userBean.getUsuario().getId(), "Team", equipo.getId(), DireccionIp.getRemoteAddress());
		if(log.isDebugEnabled()) {
			log.debug("MODIFICAR EQUIPO");
		}
		return "funcional";
	}
	
	/**
	 * Gets the listar equipos.
	 *
	 * @return the listar equipos
	 */
	public DataModel getListarEquipos() {
		List<Team> lista = new TeamService().list();
		listaEquipos = new ListDataModel(lista);
		if(log.isDebugEnabled()) {
			log.debug("LISTAR EQUIPOS");
		}
		return listaEquipos;
	}
	
	public List<String> getEquipos(){
		
		return equipos;
	}
	
	public List<String> getGrupos(){
		return grupos;
	}

	public DataModel getLista() {
		List<Team> listaequ = new TeamService().list();
//		for (int i = 0; i < listaequ.size(); i++) {
//			if (equipo.getLostMatches()<equipo.getWonMatches()) {
				listaEquipos = new ListDataModel(listaequ);
//			}
//		}
		return lista;
	}

	public void setLista(DataModel lista) {
		this.lista = lista;
	}
	
	
	
	
	
	
}