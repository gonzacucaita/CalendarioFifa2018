package com.unbosque.edu.co.calendarioFifa.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.unbosque.edu.co.calendarioFifa.dao.impl.AuditDAOImpl;
import com.unbosque.edu.co.calendarioFifa.entity.Audit;
import com.unbosque.edu.co.calendarioFifa.entity.Team;
import com.unbosque.edu.co.calendarioFifa.entity.User;
import com.unbosque.edu.co.calendarioFifa.service.AuditService;
import com.unbosque.edu.co.calendarioFifa.service.TeamService;

@ManagedBean
@SessionScoped
public class AuditBean implements Serializable{

	private Audit auditoria;
	private DataModel listaAuditorias;
	private String auditorias;
	private String usuarios;
	private String salir;
	
	private List<Audit> listt;
	
	public List<Audit> getListt() {
		return listt = new AuditService().list();
	}
	public void setListt(List<Audit> listt) {
		this.listt = listt;
	}
	public Audit getAuditoria() {
		return auditoria;
	}
	public void setAuditoria(Audit auditoria) {
		this.auditoria = auditoria;
	}
	public DataModel getListaAuditorias() {
		return listaAuditorias;
	}
	public void setListaAuditorias(DataModel listaAuditorias) {
		this.listaAuditorias = listaAuditorias;
	}
	public String getAuditorias() {
		return auditorias;
	}
	public void setAuditorias(String auditorias) {
		this.auditorias = auditorias;
	}
	public String getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(String usuarios) {
		this.usuarios = usuarios;
	}
	public String getSalir() {
		return salir;
	}
	public void setSalir(String salir) {
		this.salir = salir;
	}
	public Audit getAudit() {
		return auditoria;
	}
	public void setAudit(Audit audit) {
		this.auditoria = audit;
	}
	
	public String prepararAdicionarAuditoria() {
		auditoria = new Audit();
		return "audit";
	}
	
	public String prepararModificarAuditoria() {
		auditoria = (Audit) (listaAuditorias.getRowData());
		return "audit";
	}
	
	public String eliminarAuditoria() {
		Audit auditoriaTemp = (Audit)(listaAuditorias.getRowData());
		AuditService dao = new AuditService();
		dao.update(auditoriaTemp);
		return "inicio";
	}
	
	public String adicionarAuditoria() {
		AuditService dao = new AuditService();
		dao.save(auditoria);
		return "inicio";
	}
	
	public String modificarAuditoria() {
		AuditService dao = new AuditService();
		dao.update(auditoria);
		return "inicio";
	}
	
	public DataModel getListarAuditorias() {
		List<Audit> lista = new AuditService().list();
		listaAuditorias = new ListDataModel(lista);
		return listaAuditorias;
	}
	
	public String mostrar() {
		String res = "";
		return res;
	}
	
	
}
