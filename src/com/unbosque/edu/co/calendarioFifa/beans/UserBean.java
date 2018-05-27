package com.unbosque.edu.co.calendarioFifa.beans;

import javax.faces.application.FacesMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.context.FacesContext;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.primefaces.event.FlowEvent;

import com.unbosque.edu.co.calendarioFifa.entity.*;
import com.unbosque.edu.co.calendarioFifa.service.*;
import com.unbosque.edu.co.calendarioFifa.util.*;

@ManagedBean
@SessionScoped
public class UserBean {

	private List<String> images;

	/**
	 * ATRIBUTOS PARA EL USUARIO EN GENERAL.
	 */
	private static int INTENTOS = 0;
	private User usuario;
	private DataModel listaUsuarios;
	private String ingresarUsuario;
	private String contrasenia;
	private String contraseniaNueva;
	private Audit auditoria = new Audit();
	private AuditService auditService = new AuditService();
	/**
	 * ATRIBUTOS PARA EL USUARIO FUNCIONAL
	 */
	private Goalscorer goleador;
	private New noticia;
	private Referee arbitro;
	private Schedule calendario;
	private Stadium estadio;
	private Team equipo;

	private static Logger log = Logger.getLogger(UserBean.class);

	// public UserBean()
	// {
	// images = new ArrayList<String>();
	// for (int i = 1; i <= 4; i++) {
	// images.add("noti" + i + ".jpg");
	// }
	//
	// }

	/**
	 * METODOS PARA LOS USUARIOS EN GENERAL (ADMINISTRADOR, FUNCIONAL, CLIENTE)
	 * 
	 * @return
	 */

	public String validarUsuario() {

		contrasenia = Util.getStringMessageDigest(contrasenia, Util.MD5);
		UserService us = new UserService();
		usuario = us.verificarUsuario(ingresarUsuario);
		ParameterService ps = new ParameterService();
		if (usuario != null) {
			Parameter pa =  ps.verificarParametros(usuario.getId()+"");
			

			if (usuario.getPassword().compareTo(contrasenia) != 0) {
				// mandar mensaje de que se equivoco
				if(usuario.getUserType().equals("cliente")) {
					INTENTOS++;
					System.out.println(INTENTOS);
					if(INTENTOS == pa.getNumberValue() ) {
						usuario.setActive("I");
						us.save(usuario);
						INTENTOS = 0;
						auditoria.setUserId(usuario.getId());
						auditoria.setOperation("B");
						auditoria.setTableId(usuario.getId());
						auditoria.setCreateDate(new Date());
						auditService.save(auditoria);
						return "/Error/ErrorLogin";
					}
					
				}
				return "/Principal/login";
			} else if(usuario.getActive().equals("A")) {
				if (usuario.getUserType().equals("ADMIN")) {

					return "/Administrador/administrador";
				} else if (usuario.getUserType().equals("FUNCIONAL")) {

					return "/UserFuncional/funcional";

				} else if (usuario.getUserType().equals("cliente")) {
					long diasDif = DiferenciaFechas.DifeenciaFechas(new Date(), usuario.getDateLastPassword());
					int ingresos = Integer.parseInt(pa.getParameterCode());
					if(ingresos == 0|| diasDif >= Integer.parseInt(pa.getParameterType())) {
						return "/User/nuevaContraseña"; 
					}
					pa.setParameterCode((ingresos + 1)+"");
					
					return "/User/paginaInicio";

				}
				auditoria.setUserId(usuario.getId());
				auditoria.setOperation("E");
				auditoria.setTableId(usuario.getId());
				auditoria.setCreateDate(new Date());
				auditService.save(auditoria);
			}else {
				return "/Error/ErrorLogin";
			}
		}
		// UserService us = new UserService();
		// BasicConfigurator.configure();
		// String respuesta = "registro";
		// Iterator<User> aux = getListarUsuario().iterator();
		// boolean existe = false;
		// AuditService as = new AuditService();
		//
		//
		// contrasenia = Util.getStringMessageDigest(contrasenia, Util.MD5);
		//
		// while (aux.hasNext() && existe == false) {
		//
		// usuario = aux.next();
		// ParameterService ps = new ParameterService();
		// String idUsuario = "";
		// int ingresos = 0;
		// long idParameter = 0;
		// int intentos = 0;
		// String fechaLimite = "";
		// List<Parameter> listap = ps.list();
		// boolean encontro = false;
		// for (int i = 0; i < listap.size() && encontro == false; i++) {
		//
		// if(listap.get(i).getTextValue().equals(usuario.getId()+"")) {
		// idUsuario = listap.get(i).getTextValue();
		// ingresos = Integer.parseInt(listap.get(i).getParameterCode());
		// idParameter = listap.get(i).getId();
		// intentos = listap.get(i).getNumberValue();
		// fechaLimite = listap.get(i).getParameterType();
		// encontro = true;
		// listap.get(i).setParameterCode((ingresos+1)+"");
		// ps.update(listap.get(i));
		// }
		// }
		//
		//
		// boolean contra = usuario.getPassword().equals(contrasenia);
		//
		// boolean use = usuario.getUserName().equals(ingresarUsuario);
		//
		// if (contra && use && usuario.getActive().equals("A")) {
		// long fechaIngreso = DiferenciaFechas.DifeenciaFechas(new Date(),
		// usuario.getDateLastPassword());
		// if (usuario.getUserType().equals("ADMIN")) {
		// respuesta = "/Administrador/administrador";
		// } else if (usuario.getUserType().equals("FUNCIONAL")) {
		// respuesta = "/UserFuncional/funcional";
		// } else if(usuario.getUserType().equals("cliente")){
		//
		// if(ingresos == 0 || fechaIngreso>= Integer.parseInt(fechaLimite)) {
		// respuesta = "/User/nuevaContraseña";
		// }
		// else {
		//
		// respuesta = "/User/paginaInicio";
		// }
		//
		// }
		//
		//
		// auditoria.setUserId(usuario.getId());
		// auditoria.setOperation("E");
		// auditoria.setTableName("user");
		// auditoria.setTableId(auditoria.getId());
		// auditoria.setCreateDate(new Date());
		// as.save(auditoria);
		// if (log.isInfoEnabled()) {
		// log.info("Ingreso de usuario correcto : Usuario: " + usuario.getUserName() +
		// " tipo: "
		// + usuario.getUserType());
		// }
		// existe = true;
		//
		// for (int i = 0; i < listap.size() && encontro == false; i++) {
		//
		// if(listap.get(i).getTextValue().equals(usuario.getId()+"")) {
		// listap.get(i).setParameterCode((ingresos +1)+"");
		// }
		// }
		// } else if (use && contra == false && usuario.getActive().equals("A")) {
		// INTENTOS++;
		// if (INTENTOS == intentos) {
		// usuario.setActive("I");
		// us.update(usuario);
		// auditoria.setUserId(usuario.getId());
		// auditoria.setOperation("B");
		// auditoria.setTableName("user");
		// auditoria.setTableId(auditoria.getId());
		// auditoria.setCreateDate(new Date());
		// as.save(auditoria);
		// respuesta = "/Error/ErrorLogin";
		// INTENTOS = 0;
		// existe = true;
		// } else {
		// respuesta = "/Principal/login";
		// }
		//
		//
		// }
		// }
		contrasenia = "";
		ingresarUsuario = "";
		return "/Principal/registro";
	}

	public String prepararAdicionarUsuario() {
		usuario = new User();
		usuario.setActive("A");
		usuario.setDateLastPassword(new Date());
		usuario.setUserType("cliente");

		return "/Principal/registro";
	}

	public String adicionarUsuario() {

		UserService dao = new UserService();
		String contra = DiferenciaFechas.getGenerarContrasenia();
		usuario.setPassword(Util.getStringMessageDigest(contra, Util.MD5));
		dao.save(usuario);
		String de = "calendario.fifa.uelbosque@gmail.com";
		String clave = "patatafrita";
		String asunto = "CONFIRMACION REGISTRO CALENDARIO FIFA";
		String mensaje = "CALENDARIO FIFA 2018 \n" + "\n" + "\n" + "BIENVENIDO " + usuario.getFullName() + "\n" + "\n"
				+ "\n" + "\n" + "tu cuenta se ha generado exitosamente \n" + "\n" + "\n    " + "usuario: "
				+ usuario.getUserName() + "\n    clave: " + contra + "\n " + "\n" + "\n" + "\n"
				+ "Te solicitamos que una vez ingrese, cambie su contraseña.\n" + "\n" + "\n" + "\n" + "\n"
				+ "Att: administrador CalendarioFIFA" + "\n" + "Por favor no contestes este correo";

		Correo.enviarCorreo(de, usuario.getEmailAddress(), clave, asunto, mensaje);

		AuditService as = new AuditService();

		auditoria.setUserId(usuario.getId());
		auditoria.setOperation("C");
		auditoria.setTableName("user");
		auditoria.setTableId(usuario.getId());
		auditoria.setCreateDate(new Date());
		as.save(auditoria);

		Parameter parameter = new Parameter();
		ParameterService ps = new ParameterService();

		String a = usuario.getId() + "";
		parameter.setTextValue(a);
		// numero de dias para cambiar contraseña
		parameter.setParameterType("9");
		// no sirve para nada pero no deja agregar si no se llena el campo
		parameter.setDescriptionParameter(" ");
		// numero de ingresos
		parameter.setParameterCode("0");
		// numero de intentos de error
		parameter.setNumberValue(3);

		ps.save(parameter);

		return "inicio";
	}

	/**
	 * METODOS PARA EL USUARIO FUNCIONAL
	 * 
	 * @return
	 */
	public String prepararModificarUsuario() {
		usuario = (User) (listaUsuarios.getRowData());
		return "registro";
	}

	public String eliminarUsuario() {
		User usuarioTemp = (User) (listaUsuarios.getRowData());
		UserService dao = new UserService();
		usuarioTemp.setActive("I");
		dao.update(usuarioTemp);
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

	public String modificarUsuario() {
		prepararModificarUsuario();
		UserService dao = new UserService();
		AuditService as = new AuditService();

		String contraseniaNueva = DiferenciaFechas.getGenerarContrasenia();
		usuario.setActive("A");
		String de = "calendario.fifa.uelbosque@gmail.com";
		String clave = "patatafrita";
		String asunto = "DESBLOQUEO DE USUARIO, CALENDARIO FIFA";
		String mensaje = "CALENDARIO FIFA 2018 \n" + "\n" + "\n" + "Usuario: " + usuario.getFullName() + "\n" + "\n"
				+ "\n" + "\n" + "Se ha generado su nueva contraseña exitosamente" + "\n" + "\n" + "\n    " + "usuario: "
				+ usuario.getUserName() + "\n" + "Clave: " + contraseniaNueva + "\n " + "\n" + "\n" + "\n"
				+ "Le solicitamos que una vez ingrese y cambie su contraseña.\n" + "\n" + "\n" + "\n" + "\n"
				+ "Att: administrador CalendarioFIFA";

		Correo.enviarCorreo(de, usuario.getEmailAddress(), clave, asunto, mensaje);
		contraseniaNueva = Util.getStringMessageDigest(contraseniaNueva, Util.MD5);
		usuario.setPassword(contraseniaNueva);
		dao.update(usuario);

		auditoria.setUserId(usuario.getId());
		auditoria.setOperation("U");
		auditoria.setTableName("user");
		auditoria.setTableId(usuario.getId());
		auditoria.setCreateDate(new Date());
		as.save(auditoria);

		ParameterService ps = new ParameterService();
		List<Parameter> list = ps.list();
		boolean a = false;
		for (int i = 0; i < list.size() && a == false; i++) {
			if (list.get(i).getTextValue().equals(usuario.getId() + "")) {
				list.get(i).setParameterCode("0");
				ps.update(list.get(i));
				a = true;
			}

		}
		return "/Administrador/administrador";
	}

	public String prepararAdicionarGoleador() {
		goleador = new Goalscorer();
		return "goalscorer";
	}

	public void adicionarGoleador() {

		GoalscorerService gls = new GoalscorerService();
		gls.save(goleador);
		AuditService ad = new AuditService();
		auditoria.setUserId(usuario.getId());
		auditoria.setOperation("C");
		auditoria.setTableName("goalscorer");
		auditoria.setTableId(goleador.getId());
		auditoria.setCreateDate(new Date());
		ad.save(auditoria);

	}

	public String prepararModificarGoleador() {
		List<Goalscorer> goalscorer = new GoalscorerService().list();
		DataModel listaGoleadores = new ListDataModel(goalscorer);
		goleador = (Goalscorer) (listaGoleadores.getRowData());
		return "pagina donde se modifica";
	}

	public void modificarGoleador() {
		GoalscorerService gs = new GoalscorerService();
		gs.update(goleador);

		AuditService ad = new AuditService();
		auditoria.setUserId(usuario.getId());
		auditoria.setOperation("U");
		auditoria.setTableName("goalscorer");
		auditoria.setTableId(goleador.getId());
		auditoria.setCreateDate(new Date());
		ad.save(auditoria);
	}

	public String prepararAdicionarNoticia() {
		noticia = new New();
		return "new";
	}

	public void adicionarNoticia() {
		NewService ns = new NewService();
		ns.save(noticia);
		AuditService ad = new AuditService();
		auditoria.setUserId(usuario.getId());
		auditoria.setOperation("C");
		auditoria.setTableName("news");
		auditoria.setTableId(noticia.getId());
		auditoria.setCreateDate(new Date());
		ad.save(auditoria);
	}

	public String prepararModificarNoticia() {
		List<Goalscorer> goalscorer = new GoalscorerService().list();
		DataModel listaGoleadores = new ListDataModel(goalscorer);
		goleador = (Goalscorer) (listaGoleadores.getRowData());
		return "pagina donde se modifica";
	}

	public void modificarNoticia() {
		NewService ns = new NewService();
		ns.update(noticia);

		AuditService ad = new AuditService();
		auditoria.setUserId(usuario.getId());
		auditoria.setOperation("U");
		auditoria.setTableName("news");
		auditoria.setTableId(noticia.getId());
		auditoria.setCreateDate(new Date());
		ad.save(auditoria);
	}

	public String prepararAdicionarArbitro() {
		arbitro = new Referee();
		return "referee";
	}

	public void adicionarArbitro() {
		RefereeService rs = new RefereeService();
		rs.save(arbitro);
		AuditService ad = new AuditService();
		auditoria.setUserId(usuario.getId());
		auditoria.setOperation("C");
		auditoria.setTableName("referee");
		auditoria.setTableId(arbitro.getId());
		auditoria.setCreateDate(new Date());
		ad.save(auditoria);
	}

	public String prepararModificarArbitro() {
		List<Referee> referee = new RefereeService().list();
		DataModel listaArbitros = new ListDataModel(referee);
		arbitro = (Referee) (listaArbitros.getRowData());
		return "pagina donde se modifica";
	}

	public void modificarArbitro() {
		RefereeService rs = new RefereeService();
		rs.update(arbitro);
		AuditService ad = new AuditService();
		auditoria.setUserId(usuario.getId());
		auditoria.setOperation("U");
		auditoria.setTableName("referee");
		auditoria.setTableId(arbitro.getId());
		auditoria.setCreateDate(new Date());
		ad.save(auditoria);
	}

	public String prepararAdicionarCalendario() {
		calendario = new Schedule();
		return "schedule";
	}

	public void adicionarCalendario() {
		ScheduleService ss = new ScheduleService();
		ss.save(calendario);
		AuditService ad = new AuditService();
		auditoria.setUserId(usuario.getId());
		auditoria.setOperation("C");
		auditoria.setTableName("schedule");
		auditoria.setTableId(calendario.getId());
		auditoria.setCreateDate(new Date());
		ad.save(auditoria);
	}

	public String prepararModificarCalendario() {
		List<Schedule> schedule = new ScheduleService().list();
		DataModel listaCalendario = new ListDataModel(schedule);
		calendario = (Schedule) (listaCalendario.getRowData());
		return "pagina donde se modifica";
	}

	public void modificarCalendario() {
		ScheduleService ss = new ScheduleService();
		ss.update(calendario);
		AuditService ad = new AuditService();
		auditoria.setUserId(usuario.getId());
		auditoria.setOperation("U");
		auditoria.setTableName("schedule");
		auditoria.setTableId(calendario.getId());
		auditoria.setCreateDate(new Date());
		ad.save(auditoria);
	}

	public String prepararAdicionarEstadio() {
		estadio = new Stadium();
		return "stadium";
	}

	public void adicionarEstadio() {
		StadiumService sts = new StadiumService();
		sts.save(estadio);
		AuditService ad = new AuditService();
		auditoria.setUserId(usuario.getId());
		auditoria.setOperation("C");
		auditoria.setTableName("stadium");
		auditoria.setTableId(estadio.getId());
		auditoria.setCreateDate(new Date());
		ad.save(auditoria);
	}

	public String prepararModificarEstadio() {
		List<Stadium> stadium = new StadiumService().list();
		DataModel listaEstadios = new ListDataModel(stadium);
		estadio = (Stadium) (listaEstadios.getRowData());
		return "pagina donde se modifica";
	}

	public void modificarEstadio() {
		StadiumService ss = new StadiumService();
		ss.update(estadio);
		AuditService ad = new AuditService();
		auditoria.setUserId(usuario.getId());
		auditoria.setOperation("U");
		auditoria.setTableName("stadium");
		auditoria.setTableId(estadio.getId());
		auditoria.setCreateDate(new Date());
		ad.save(auditoria);
	}

	public String prepararAdicionarEquipo() {
		equipo = new Team();
		return "team";
	}

	public void adicionarEquipo() {
		TeamService ts = new TeamService();
		ts.save(equipo);
		AuditService ad = new AuditService();
		auditoria.setUserId(usuario.getId());
		auditoria.setOperation("U");
		auditoria.setTableName("team");
		auditoria.setTableId(equipo.getId());
		auditoria.setCreateDate(new Date());
		ad.save(auditoria);
	}

	public String prepararModificarEquipo() {
		List<Team> team = new TeamService().list();
		DataModel listaEquipos = new ListDataModel(team);
		equipo = (Team) (listaEquipos.getRowData());
		return "pagina donde se modifica";
	}

	public void modificarEquipo() {
		TeamService tm = new TeamService();
		tm.update(equipo);
		AuditService ad = new AuditService();
		auditoria.setUserId(usuario.getId());
		auditoria.setOperation("U");
		auditoria.setTableName("team");
		auditoria.setTableId(equipo.getId());
		auditoria.setCreateDate(new Date());
		ad.save(auditoria);
	}

	public User getUsuario() {
		return usuario;
	}

	public DataModel getListarUsuario() {
		List<User> lista = new UserService().list();
		listaUsuarios = new ListDataModel(lista);
		return listaUsuarios;
	}

	public String getSolicitarContrasenia() {
		String de = "calendario.fifa.uelbosque@gmail.com";
		String clave = "patatafrita";
		String asunto = "DESBLOQUEO DE USUARIO, CALENDARIO FIFA";
		String mensaje = "Para desbloquear al usuario :" + usuario.getUserName();

		Correo.enviarCorreo(de, de, clave, asunto, mensaje);

		return "/Principal/login";
	}

	public String getOlvideContrasenia() {
		INTENTOS = 0;
		UserService us = new UserService();
		String nueva = DiferenciaFechas.getGenerarContrasenia();
		String de = "calendario.fifa.uelbosque@gmail.com";
		String clave = "patatafrita";
		String asunto = "NUEVA CONTRASEÑA, CALENDARIO FIFA";
		String mensaje = "CALENDARIO FIFA 2018 \n" + "\n" + "\n" + "Usuario: " + usuario.getFullName() + "\n" + "\n"
				+ "\n" + "\n" + "Se ha generado su nueva contraseña exitosamente" + "\n" + "\n" + "\n    " + "usuario: "
				+ usuario.getUserName() + "\n" + "Clave: " + nueva + "\n " + "\n" + "\n" + "\n"
				+ "Le solicitamos que una vez ingrese y cambie su contraseña.\n" + "\n" + "\n" + "\n" + "\n"
				+ "Att: administrador CalendarioFIFA";

		Correo.enviarCorreo(de, usuario.getEmailAddress(), clave, asunto, mensaje);
		nueva = Util.getStringMessageDigest(nueva, Util.MD5);
		usuario.setPassword(nueva);
		us.update(usuario);
		AuditService as = new AuditService();

		Audit auditoria = new Audit();
		auditoria.setUserId(usuario.getId());
		auditoria.setOperation("U");
		auditoria.setTableName("user");
		auditoria.setTableId(1);
		auditoria.setCreateDate(new Date());
		as.save(auditoria);

		return "parameter";

	}

	public String getContraseniaNueva() {
		return contraseniaNueva;
	}

	public void setContraseniaNueva(String contraseniaNueva) {
		this.contraseniaNueva = Util.getStringMessageDigest(contraseniaNueva, Util.MD5);
		UserService dao = new UserService();
		usuario.setPassword(this.contraseniaNueva);

		dao.update(usuario);
	}

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

	public void setUsuario(User usuario) {

		this.usuario = usuario;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

}