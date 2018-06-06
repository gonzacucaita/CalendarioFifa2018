package com.unbosque.edu.co.calendarioFifa.dao;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.entity.Team;

/**
 * The Interface TeamDAO.
 */
public interface TeamDAO {
	
	/**
	 * Save.
	 *
	 * @param equipo the equipo
	 */
	public void save(Team equipo);

	/**
	 * Gets the team.
	 *
	 * @param id the id
	 * @return the team
	 */
	public Team getTeam(long id);

	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<Team> list();

	/**
	 * Removes the.
	 *
	 * @param equipo the equipo
	 */
	public void remove(Team equipo);

	/**
	 * Update.
	 *
	 * @param equipo the equipo
	 */
	public void update(Team equipo);

	Team validarEquipo(String country);
}
