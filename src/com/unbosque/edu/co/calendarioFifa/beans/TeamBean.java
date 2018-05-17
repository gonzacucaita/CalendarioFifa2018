package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import com.unbosque.edu.co.calendarioFifa.entity.Team;
import com.unbosque.edu.co.calendarioFifa.service.TeamService;

@ManagedBean
@SessionScoped
public class TeamBean {
	
	private Team equipo;
	private DataModel listaEquipos;
	
	public Team getEquipo() {
		return equipo;
	}

	public void setEquipo(Team team) {
		this.equipo = team;
	}
	
	public String prepararAdicionarEquipo() {
		equipo = new Team();
		equipo.setState("A");
		return "teamAgregar";
	}
	
	public String prepararModificarEquipo() {
		equipo = (Team) (listaEquipos.getRowData());
		return "team";
	}
	
	public String eliminarEquipo() {
		Team teamTemp = (Team)(listaEquipos.getRowData());
		TeamService dao = new TeamService();
		teamTemp.setState("I");
		dao.update(teamTemp);
		return "inicio";
	}
	
	public String adicionarEquipo() {
		TeamService dao = new TeamService();
		dao.save(equipo);
		return "funcional";
	}
	
	public String modificarEquipo() {
		TeamService dao = new TeamService();
		dao.update(equipo);
		return "funcional";
	}
	
	public DataModel getListarEquipos() {
		List<Team> lista = new TeamService().list();
		listaEquipos = new ListDataModel(lista);
		return listaEquipos;
	}
}