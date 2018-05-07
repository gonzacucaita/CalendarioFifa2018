package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.unbosque.edu.co.calendarioFifa.entity.Schedule;
import com.unbosque.edu.co.calendarioFifa.service.ScheduleService;

@ManagedBean
@SessionScoped
public class ScheduleBean {

	private Schedule calendario;
	private DataModel listaCalendario;
	
	public Schedule getCalendario() {
		return calendario;
	}
	public void setCalendario(Schedule shedule) {
		this.calendario = shedule;
	}
	
	
	public String prepararAdicionarCalendario() {
		calendario = new Schedule();
		calendario.setState("A");
		return "schedule";
	}
	
	public String prepararModificarCalendario() {
		calendario = (Schedule) (listaCalendario.getRowData());
		return "schedule";
	}
	
	public String eliminarCalendario() {
		Schedule scheduleTemp = (Schedule)(listaCalendario.getRowData());
		ScheduleService dao = new ScheduleService();
		scheduleTemp.setState("I");;
		dao.update(scheduleTemp);
		return "inicio";
	}
	
	public String adicionarCalendario() {
		ScheduleService dao = new ScheduleService();
		dao.save(calendario);
		return "inicio";
	}
	
	public String modificarCalendario() {
		ScheduleService dao = new ScheduleService();
		dao.update(calendario);
		return "inicio";
	}
	
	public DataModel getListarCalendarios() {
		List<Schedule> lista = new ScheduleService().list();
		listaCalendario = new ListDataModel(lista);
		return listaCalendario;
	}
}