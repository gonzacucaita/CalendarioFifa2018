package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import com.unbosque.edu.co.calendarioFifa.entity.Audit;
import com.unbosque.edu.co.calendarioFifa.entity.Goalscorer;
import com.unbosque.edu.co.calendarioFifa.service.AuditService;
import com.unbosque.edu.co.calendarioFifa.service.GoalscorerService;

/**
 * The Class GoalscorerBean.
 */
@ManagedBean
@SessionScoped
public class GoalscorerBean {

	/** The Constant log. */
	final static Logger log = Logger.getLogger(GoalscorerBean.class);
	
	/** The goleador. */
	private Goalscorer goleador;
	
	/** The lista goleadores. */
	private DataModel listaGoleadores;
	


	/**
	 * Gets the goleador.
	 *
	 * @return the goleador
	 */
	public Goalscorer getGoleador() {
		return goleador;
	}
	
	/**
	 * Sets the goleador.
	 *
	 * @param goleador the new goleador
	 */
	public void setGoleador(Goalscorer goleador) {
		this.goleador = goleador;
	}
	
	/**
	 * Preparar adicionar goleador.
	 *
	 * @return the string
	 */
	public String prepararAdicionarGoleador() {
		goleador = new Goalscorer();
		
		
		return "goalscorerAgregar";
	}
	
	/**
	 * Preparar modificar goleador.
	 *
	 * @return the string
	 */
	public String prepararModificarGoleador() {
		goleador = (Goalscorer) (listaGoleadores.getRowData());
		return "goalscorerModificar";
	}
	
	/**
	 * Eliminar goleador.
	 *
	 * @return the string
	 */
	public String eliminarGoleador() {
		Goalscorer goalscorerTemp = (Goalscorer)(listaGoleadores.getRowData());
		GoalscorerService dao = new GoalscorerService();
		dao.update(goalscorerTemp);
		Audit auditoria = new Audit();
		AuditService as = new AuditService();
//		auditoria.set
		System.out.println();
		return "inicio";
	}
	
	/**
	 * Adicionar goleador.
	 *
	 * @return the string
	 */
	public String adicionarGoleador() {
		GoalscorerService dao = new GoalscorerService();
		dao.save(goleador);
		return "funcional";
	}
	
	/**
	 * Modificar goleador.
	 *
	 * @return the string
	 */
	public String modificarGoleador() {
		GoalscorerService dao = new GoalscorerService();
		dao.update(goleador);
		return "funcional";
	}
	
	/**
	 * Gets the listar goleadores.
	 *
	 * @return the listar goleadores
	 */
	public DataModel getListarGoleadores() {
		List<Goalscorer> lista = new GoalscorerService().list();
		listaGoleadores = new ListDataModel(lista);
		return listaGoleadores;
	}
	
}
