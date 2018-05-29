package com.unbosque.edu.co.calendarioFifa.service;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.dao.impl.TeamDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.Team;

/**
 * The Class TeamService.
 */
public class TeamService {

	/** The dao. */
	private TeamDAOImpl dao = new TeamDAOImpl();
	
	/**
	 * Save.
	 *
	 * @param equipo the equipo
	 */
	public void save(Team equipo) {
		dao.save(equipo);
	}
	
	/**
	 * Gets the usuario.
	 *
	 * @param id the id
	 * @return the usuario
	 */
	public Team getUsuario(long id) {
		return dao.getTeam(id);
	}
	
	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<Team> list(){
		return dao.list();
	}
	
	/**
	 * Removes the.
	 *
	 * @param equipo the equipo
	 */
	public void remove(Team equipo) {
		dao.remove(equipo);
	}
	
	/**
	 * Update.
	 *
	 * @param equipo the equipo
	 */
	public void update(Team equipo) {
		dao.update(equipo);
	}
}
