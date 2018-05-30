package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.List;
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
	
	/** The Constant log. */
	final static Logger log = Logger.getLogger(TeamBean.class);
	
	@ManagedProperty("#{userBean}")
	private UserBean userBean;
	
	@ManagedProperty("#{auditBean}")
	private AuditBean auditBean;
	
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
		System.out.println("entro");
		TeamService dao = new TeamService();
		
		dao.save(equipo);
		
		auditBean.adicionarAuditoria(userBean.getUsuario().getId(), "Team", equipo.getId(), DireccionIp.getRemoteAddress());
		if(log.isDebugEnabled()) {
			log.debug("ADICIONAR EQUIPO");
		}
		return "funcional";
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
			log.debug("LISTAR EQUIPO");
		}
		return listaEquipos;
	}
}