package com.unbosque.edu.co.calendarioFifa.service;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.dao.impl.TeamDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.Team;


public class TeamService {

	private TeamDAOImpl dao = new TeamDAOImpl();
	
	public void save(Team equipo) {
		dao.save(equipo);
	}
	
	public Team getUsuario(long id) {
		return dao.getTeam(id);
	}
	
	public List<Team> list(){
		return dao.list();
	}
	
	public void remove(Team equipo) {
		dao.remove(equipo);
	}
	
	public void update(Team equipo) {
		dao.update(equipo);
	}
}
