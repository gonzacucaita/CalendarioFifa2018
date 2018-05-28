package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import com.unbosque.edu.co.calendarioFifa.entity.Schedule;
import com.unbosque.edu.co.calendarioFifa.service.ScheduleService;

@ManagedBean
@SessionScoped
public class ScheduleBean {

	private Schedule calendario;
	private DataModel listaCalendario;
	final static Logger log = Logger.getLogger(ScheduleBean.class);
	
	
	public Schedule getCalendario() {
		return calendario;
	}
	public void setCalendario(Schedule shedule) {
		this.calendario = shedule;
	}
	
	
	public String prepararAdicionarCalendario() {
		calendario = new Schedule();
		calendario.setState("A");
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR CALENDARIO");
		}
		return "schedule";
	}
	
	public String prepararModificarCalendario() {
		calendario = (Schedule) (listaCalendario.getRowData());
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR CALENDARIO");
		}
		return "schedule";
	}
	
	public String eliminarCalendario() {
		Schedule scheduleTemp = (Schedule)(listaCalendario.getRowData());
		ScheduleService dao = new ScheduleService();
		scheduleTemp.setState("I");;
		dao.update(scheduleTemp);
		if(log.isDebugEnabled()) {
			log.debug("ELIMINAR CALENDARIO");
		}
		return "inicio";
	}
	
	public String adicionarCalendario() {
		ScheduleService dao = new ScheduleService();
		dao.save(calendario);
		
		if(log.isDebugEnabled()) {
			log.debug("ADICIONAR CALENDARIO");
		}
		return "inicio";
	}
	
	public String modificarCalendario() {
		ScheduleService dao = new ScheduleService();
		dao.update(calendario);
		if(log.isDebugEnabled()) {
			log.debug("MODIFICAR CALENDARIO");
		}
		return "inicio";
	}
	
	public DataModel getListarCalendarios() {
		List<Schedule> lista = new ScheduleService().list();
		listaCalendario = new ListDataModel(lista);
		if(log.isDebugEnabled()) {
			log.debug("LISTAR CALENDARIOS");
		}
		return listaCalendario;
	}
}