package com.unbosque.edu.co.calendarioFifa.service;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.dao.impl.ScheduleDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.Schedule;

/**
 * The Class ScheduleService.
 */
public class ScheduleService {

/** The dao. */
private ScheduleDAOImpl dao = new ScheduleDAOImpl();
	
	/**
	 * Save.
	 *
	 * @param calendario the calendario
	 */
	public void save(Schedule calendario) {
		dao.save(calendario);
	}
	
	/**
	 * Gets the calendario.
	 *
	 * @param id the id
	 * @return the calendario
	 */
	public Schedule getCalendario(long id) {
		return dao.getSchedule(id);
	}
	
	/**
	 * List.
	 *
	 * @return the list
	 */
	public List<Schedule> list(){
		return dao.list();
	}
	
	/**
	 * Removes the.
	 *
	 * @param calendario the calendario
	 */
	public void remove(Schedule calendario) {
		dao.remove(calendario);
	}
	
	/**
	 * Update.
	 *
	 * @param calendario the calendario
	 */
	public void update(Schedule calendario) {
		dao.update(calendario);
	}
}
