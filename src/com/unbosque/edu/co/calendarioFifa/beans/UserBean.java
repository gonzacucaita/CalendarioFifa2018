package com.unbosque.edu.co.calendarioFifa.beans;

import javax.faces.application.FacesMessage;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.primefaces.event.FlowEvent;

import com.unbosque.edu.co.calendarioFifa.entity.Audit;
import com.unbosque.edu.co.calendarioFifa.entity.Parameter;
import com.unbosque.edu.co.calendarioFifa.entity.User;
import com.unbosque.edu.co.calendarioFifa.service.AuditService;
import com.unbosque.edu.co.calendarioFifa.service.ParameterService;
import com.unbosque.edu.co.calendarioFifa.service.UserService;
import com.unbosque.edu.co.calendarioFifa.util.Correo;
import com.unbosque.edu.co.calendarioFifa.util.Util;

@ManagedBean
@SessionScoped
public class UserBean {
	private static int INTENTOS = 0;
	private User usuario;
	private Parameter parameter = new Parameter();
	private ParameterService parameterService = new ParameterService();
	private DataModel listaUsuarios;
	private String ingresarUsuario;
	private String contrasenia;

	private static Logger log = Logger.getLogger(UserBean.class);

	public String getIngresarUsuario() {
		return ingresarUsuario;
	}

	public void setIngresarUsuario(String usuario) {
		this.ingresarUsuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String validarUsuario() {

		BasicConfigurator.configure();
		String respuesta = "registro";
		Iterator<User> aux = getListarUsuario().iterator();
		boolean existe = false;

		FacesMessage msg = new FacesMessage("Exito", "Bienvenido :" + usuario.getUserName());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		contrasenia = Util.getStringMessageDigest(contrasenia, Util.MD5);

		while (aux.hasNext() && existe == false) {

			usuario = aux.next();

			boolean contra = usuario.getPassword().equals(contrasenia);

			boolean use = usuario.getUserName().equals(ingresarUsuario);

			int contador = 0;

			if (contra && use && usuario.getActive().equals("A")) {
				if (usuario.getUserType().equals("ADMIN")) {
					respuesta = "/Administrador/administrador";
				} else if (usuario.getUserType().equals("FUNCIONAL")) {
					respuesta = "/UserFuncional/funcional";
				} else {
					respuesta = "/User/cliente";
				}

				Audit auditoria = new Audit();
				AuditService as = new AuditService();

				auditoria.setUserId(usuario.getId());
				auditoria.setOperation("E");
				auditoria.setTableName("user");
				auditoria.setTableId(1);
				auditoria.setCreateDate(new Date());
				as.save(auditoria);
				if (log.isInfoEnabled()) {
					log.info("Ingreso de usuario correcto : Usuario: " + usuario.getUserName() + " tipo: "
							+ usuario.getUserType());
				}
				existe = true;

			} else if (use && contra == false && usuario.getActive().equals("A")) {
				INTENTOS++;
				System.out.println(INTENTOS);
				List<Parameter> lista = parameterService.list();
				// for (int i = 0; i < lista.size() && existe==false; i++) {
				if (INTENTOS == 3) {
					System.out.println("entra");
					usuario.setActive("I");
					respuesta = "/Error/ErrorLogin";
					existe = true;
				} else {
					System.out.println("Hola");
					respuesta = "/Principal/login";
				}
				// }

			}
		}
		contrasenia = "";
		ingresarUsuario = "";
		return respuesta;
	}

	public String prepararAdicionarUsuario() {
		usuario = new User();
		usuario.setActive("A");
		usuario.setDateLastPassword(new Date());
		usuario.setUserType("cliente");
		return "registro";
	}

	public String prepararModificarUsuario() {
		usuario = (User) (listaUsuarios.getRowData());
		return "registro";
	}

	public String eliminarUsuario() {
		User usuarioTemp = (User) (listaUsuarios.getRowData());
		UserService dao = new UserService();
		usuarioTemp.setActive("I");
		// dao.remove(usuario);
		dao.update(usuarioTemp);
		Audit auditoria = new Audit();
		AuditService as = new AuditService();
		auditoria.setUserId(usuario.getId());
		auditoria.setOperation("D");
		auditoria.setTableName("user");
		auditoria.setTableId(1);
		auditoria.setCreateDate(new Date());
		as.update(auditoria);
		auditoria.setId(usuario.getId());
		return "inicio";
	}

	public String adicionarUsuario() {
		UserService dao = new UserService();
		String contra = generarContrasenia();
		usuario.setPassword(Util.getStringMessageDigest(contra, Util.MD5));
		dao.save(usuario);
		String de = "calendario.fifa.uelbosque@gmail.com";
		String clave = "patatafrita";
		String asunto = "CONFIRMACION REGISTRO CALENDARIO FIFA";
		String mensaje = "CALENDARIO FIFA 2018 \n" + "\n" + "\n" + "Usuario: " + usuario.getFullName() + "\n" + "\n"
				+ "\n" + "\n" + "su cuenta se ha generado exitosamente \n" + "\n" + "\n    " + "usuario: "
				+ usuario.getUserName() + "\n    clave: " + contra + "\n " + "\n" + "\n" + "\n"
				+ "Le solicitamos que una vez ingrese, cambie su contraseña.\n" + "\n" + "\n" + "\n" + "\n"
				+ "Att: administrador CalendarioFIFA";

		 Correo.enviarCorreo(de, usuario.getEmailAddress(), clave, asunto, mensaje);
		Audit auditoria = new Audit();
		AuditService as = new AuditService();

		auditoria.setUserId(usuario.getId());
		auditoria.setOperation("C");
		auditoria.setTableName("user");
		auditoria.setTableId(1);
		auditoria.setCreateDate(new Date());
		as.save(auditoria);

		parameter.setParameterType(usuario.getId() + "");
		parameter.setTextValue("5");
		parameter.setNumberValue(3);

		parameterService.save(parameter);

		return "inicio";
	}

	public String modificarUsuario() {
		UserService dao = new UserService();
		Audit auditoria = new Audit();
		AuditService as = new AuditService();

		auditoria.setUserId(usuario.getId());
		auditoria.setOperation("U");
		auditoria.setTableName("user");
		auditoria.setTableId(1);
		auditoria.setCreateDate(new Date());
		as.update(auditoria);
		dao.update(usuario);
		return "inicio";
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public DataModel getListarUsuario() {
		List<User> lista = new UserService().list();
		listaUsuarios = new ListDataModel(lista);
		return listaUsuarios;
	}

	public String generarContrasenia() {

		String contrasenia = "";
		String caracteres = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz1234567890";

		for (int i = 0; i < 8; i++) {
			char a = caracteres.charAt((int) (Math.random() * caracteres.length()));
			contrasenia += a;

		}

		return contrasenia;
	}

}