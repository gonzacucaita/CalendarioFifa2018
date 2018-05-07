package com.unbosque.edu.co.calendarioFifa.service;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.dao.impl.ScheduleDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.Schedule;


public class ScheduleService {
private ScheduleDAOImpl dao = new ScheduleDAOImpl();
	
	public void save(Schedule calendario) {
		dao.save(calendario);
	}
	
	public Schedule getCalendario(long id) {
		return dao.getSchedule(id);
	}
	
	public List<Schedule> list(){
		return dao.list();
	}
	
	public void remove(Schedule calendario) {
		dao.remove(calendario);
	}
	
	public void update(Schedule calendario) {
		dao.update(calendario);
	}
}
