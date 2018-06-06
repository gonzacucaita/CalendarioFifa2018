package com.unbosque.edu.co.calendarioFifa.dao;

import java.util.Date;
import java.util.List;

import com.unbosque.edu.co.calendarioFifa.entity.Schedule;

/**
 * The Interface ScheduleDAO.
 */
public interface ScheduleDAO {

/**
 * Save.
 *
 * @param calendario the calendario
 */
public void save(Schedule calendario);
	
	/**
	 * Gets the schedule.
	 *
	 * @param id the id
	 * @return the schedule
	 */
	public Schedule getSchedule(long id);
	
	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<Schedule> list();
	
	/**
	 * Removes the.
	 *
	 * @param calendario the calendario
	 */
	public void remove(Schedule calendario);
	
	/**
	 * Update.
	 *
	 * @param calendario the calendario
	 */
	public void update(Schedule calendario);

	Schedule verificarFecha(Date gameDate);
}
