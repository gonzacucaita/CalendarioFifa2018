package com.unbosque.edu.co.calendarioFifa.dao;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.entity.Team;

public interface TeamDAO {
	
	public void save(Team equipo);

	public Team getTeam(long id);

	public List<Team> list();

	public void remove(Team equipo);

	public void update(Team equipo);
}
