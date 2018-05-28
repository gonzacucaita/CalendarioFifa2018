package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import com.unbosque.edu.co.calendarioFifa.entity.Team;
import com.unbosque.edu.co.calendarioFifa.service.TeamService;

@ManagedBean
@SessionScoped
public class TeamBean {
	
	private Team equipo;
	private DataModel listaEquipos;
	
	final static Logger log = Logger.getLogger(TeamBean.class);
	
	
	public Team getEquipo() {
		return equipo;
	}

	public void setEquipo(Team team) {
		this.equipo = team;
	}
	
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
	
	public String prepararModificarEquipo() {
		equipo = (Team) (listaEquipos.getRowData());
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA MODIFICAR EQUIPO");
		}
		return "team";
	}
	
	public String eliminarEquipo() {
		Team teamTemp = (Team)(listaEquipos.getRowData());
		TeamService dao = new TeamService();
		teamTemp.setState("I");
		dao.update(teamTemp);
		if(log.isDebugEnabled()) {
			log.debug("ELIMINAR EQUIPO");
		}
		return "inicio";
	}
	
	public String adicionarEquipo() {
		TeamService dao = new TeamService();
		equipo.setState("A");
		equipo.setGoalsAgainst(0);
		equipo.setGoalsFavor(0);
		equipo.setLostMatches(0);
		equipo.setWonMatches(0);
		equipo.setTiedMatches(0);
		equipo.setPlayedGames(0);
		dao.save(equipo);
		if(log.isDebugEnabled()) {
			log.debug("ADICIONAR EQUIPO");
		}
		return "funcional";
	}
	
	public String modificarEquipo() {
		TeamService dao = new TeamService();
		dao.update(equipo);
		if(log.isDebugEnabled()) {
			log.debug("MODIFICAR EQUIPO");
		}
		return "funcional";
	}
	
	public DataModel getListarEquipos() {
		List<Team> lista = new TeamService().list();
		listaEquipos = new ListDataModel(lista);
		if(log.isDebugEnabled()) {
			log.debug("LISTAR EQUIPO");
		}
		return listaEquipos;
	}
}