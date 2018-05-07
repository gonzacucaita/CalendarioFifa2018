package com.unbosque.edu.co.calendarioFifa.dao;

import java.util.List;

import com.unbosque.edu.co.calendarioFifa.entity.Schedule;


public interface ScheduleDAO {

public void save(Schedule calendario);
	
	public Schedule getSchedule(long id);
	
	public List<Schedule> list();
	
	public void remove(Schedule calendario);
	
	public void update(Schedule calendario);
}
