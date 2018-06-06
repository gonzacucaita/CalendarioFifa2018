package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import com.unbosque.edu.co.calendarioFifa.entity.Schedule;
import com.unbosque.edu.co.calendarioFifa.service.ScheduleService;
import com.unbosque.edu.co.calendarioFifa.util.DireccionIp;

/**
 * The Class ScheduleBean.
 */
@ManagedBean
@SessionScoped
public class ScheduleBean {

	/** The calendario. */
	private Schedule calendario;
	
	/** The lista calendario. */
	private DataModel listaCalendario;
	
	/** The Constant log. */
	final static Logger log = Logger.getLogger(ScheduleBean.class);
	
	@ManagedProperty("#{userBean}")
	private UserBean userBean;
	
	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	@ManagedProperty("#{auditBean}")
	private AuditBean auditBean;
	
	
	
	public AuditBean getAuditBean() {
		return auditBean;
	}

	public void setAuditBean(AuditBean auditBean) {
		this.auditBean = auditBean;
	}

	
	/**
	 * Gets the calendario.
	 *
	 * @return the calendario
	 */
	public Schedule getCalendario() {
		return calendario;
	}
	
	/**
	 * Sets the calendario.
	 *
	 * @param shedule the new calendario
	 */
	public void setCalendario(Schedule shedule) {
		this.calendario = shedule;
	}
	
	
	/**
	 * Preparar adicionar calendario.
	 *
	 * @return the string
	 */
	public String prepararAdicionarCalendario() {
		calendario = new Schedule();
		calendario.setState("A");
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR CALENDARIO");
		}
		return "scheduleAgregar";
	}
	
	/**
	 * Preparar modificar calendario.
	 *
	 * @return the string
	 */
	public String prepararModificarCalendario() {
		calendario = (Schedule) (listaCalendario.getRowData());
		if(log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR CALENDARIO");
		}
		return "schedule";
	}
	
	/**
	 * Eliminar calendario.
	 *
	 * @return the string
	 */
	public String eliminarCalendario() {
		calendario = (Schedule)(listaCalendario.getRowData());
		ScheduleService dao = new ScheduleService();
		calendario.setState("I");;
		dao.update(calendario);
		auditBean.bloquearAuditoria(userBean.getUsuario().getId(), "Schedule", calendario.getId(), DireccionIp.getRemoteAddress());
		if(log.isDebugEnabled()) {
			log.debug("ELIMINAR CALENDARIO");
		}
		return "inicio";
	}
	
	/**
	 * Adicionar calendario.
	 *
	 * @return the string
	 */
	public String adicionarCalendario() {
		ScheduleService dao = new ScheduleService();
		
		
		Schedule existe = dao.verificarFecha(calendario.getGameDate());
		if(existe == null) {
		dao.save(calendario);
		auditBean.adicionarAuditoria(userBean.getUsuario().getId(), "Schedule", calendario.getId(), DireccionIp.getRemoteAddress());
		if(log.isDebugEnabled()) {
			log.debug("ADICIONAR CALENDARIO");
		}
		return "funcional";
		}
		return "scheduleAgregar";
	}
	
	/**
	 * Modificar calendario.
	 *
	 * @return the string
	 */
	public String modificarCalendario() {
		ScheduleService dao = new ScheduleService();
		dao.update(calendario);
		auditBean.actualizarAuditoria(userBean.getUsuario().getId(), "Schedule", calendario.getId(), DireccionIp.getRemoteAddress());
		if(log.isDebugEnabled()) {
			log.debug("MODIFICAR CALENDARIO");
		}
		return "funcional";
	}
	
	/**
	 * Gets the listar calendarios.
	 *
	 * @return the listar calendarios
	 */
	public DataModel getListarCalendarios() {
		List<Schedule> lista = new ScheduleService().list();
		listaCalendario = new ListDataModel(lista);
		if(log.isDebugEnabled()) {
			log.debug("LISTAR CALENDARIOS");
		}
		return listaCalendario;
	}
}