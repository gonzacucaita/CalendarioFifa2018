package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.unbosque.edu.co.calendarioFifa.entity.Audit;
import com.unbosque.edu.co.calendarioFifa.entity.Goalscorer;
import com.unbosque.edu.co.calendarioFifa.service.AuditService;
import com.unbosque.edu.co.calendarioFifa.service.GoalscorerService;

@ManagedBean
@SessionScoped
public class GoalscorerBean {

	private Goalscorer goleador;
	private DataModel listaGoleadores;

	public Goalscorer getGoleador() {
		return goleador;
	}
	public void setGoleador(Goalscorer goleador) {
		this.goleador = goleador;
	}
	public String prepararAdicionarGoleador() {
		goleador = new Goalscorer();
		return "goalscorer";
	}
	
	public String prepararModificarGoleador() {
		goleador = (Goalscorer) (listaGoleadores.getRowData());
		return "goalscorer";
	}
	
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
	
	public String adicionarGoleador() {
		GoalscorerService dao = new GoalscorerService();
		dao.save(goleador);
		return "inicio";
	}
	
	public String modificarGoleador() {
		GoalscorerService dao = new GoalscorerService();
		dao.update(goleador);
		return "inicio";
	}
	
	public DataModel getListarGoleadores() {
		List<Goalscorer> lista = new GoalscorerService().list();
		listaGoleadores = new ListDataModel(lista);
		return listaGoleadores;
	}
	
}
