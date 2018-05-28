package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

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
	private boolean verifica = false;
	/**
	 * ATRIBUTOS PARA EL USUARIO FUNCIONAL
	 */
	private Goalscorer goleador;
	private New noticia;
	private Referee arbitro;
	private Schedule calendario;
	private Stadium estadio;
	private Team equipo;

	final static Logger log = Logger.getLogger(UserBean.class);

	/**
	 * METODOS PARA LOS USUARIOS EN GENERAL (ADMINISTRADOR, FUNCIONAL, CLIENTE)
	 * 
	 * @return
	 */

	public String validarUsuario() {

		contrasenia = Util.getStringMessageDigest(contrasenia, Util.MD5);
		UserService us = new UserService();
		usuario = us.verificarUsuario(ingresarUsuario);
		ingresarUsuario = "";
		ParameterService ps = new ParameterService();
		int ingresos = 0;
		long diasDif = 0;
		if (usuario != null) {
			Parameter pa = ps.verificarParametros(usuario.getId() + "");

			if (usuario.getPassword().compareTo(contrasenia) != 0) {
				if (usuario.getUserType().equals("cliente") || usuario.getUserType().equals("FUNCIONAL")) {
					INTENTOS++;
					if (INTENTOS == pa.getNumberValue()) {
						usuario.setActive("I");
						us.update(usuario);
						INTENTOS = 0;
						auditoria.setUserId(usuario.getId());
						auditoria.setOperation("B");
						auditoria.setTableId(usuario.getId());
						auditoria.setCreateDate(new Date());
						auditoria.setTableName("User");
						auditService.save(auditoria);
						log.error("USUARIO:" + usuario.getUserName() + " BLOQUEADO");
						return "/Error/ErrorLogin";
					}
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR DE AUTENTICACIÓN",
							"CONTRASEÑA INCORRECTA");
					FacesContext.getCurrentInstance().addMessage(null, message);
					log.error("CONTRASEÑA INCORRECTA");
				}
				return "/Principal/login";
			} else if (usuario.getActive().equals("A")) {

				if (usuario.getUserType().equals("ADMIN")) {
					if (log.isInfoEnabled()) {
						log.info("INGRESÓ SATISFACTORIAMENTE USUARIO: " + usuario.getUserName() + " TIPO: "
								+ usuario.getUserType());
					}
					return "/Administrador/administrador";
				} else if (usuario.getUserType().equals("FUNCIONAL")) {

					diasDif = DiferenciaFechas.DifeenciaFechas(new Date(), usuario.getDateLastPassword());
					ingresos = Integer.parseInt(pa.getParameterCode());
					if (ingresos == 0 || diasDif >= Integer.parseInt(pa.getParameterType())) {
						pa.setParameterCode((ingresos + 1) + "");
						ps.update(pa);

						if (log.isDebugEnabled()) {
							log.debug("CAMBIO DE CONTRASEÑA OBLIGATORIO");
						}
						if (log.isInfoEnabled()) {
							log.info("INGRESÓ SATISFACTORIAMENTE USUARIO: " + usuario.getUserName() + " TIPO: "
									+ usuario.getUserType());
						}
						return "/UserFuncional/funcional";

					} else if (usuario.getUserType().equals("cliente")) {
						diasDif = DiferenciaFechas.DifeenciaFechas(new Date(), usuario.getDateLastPassword());
						ingresos = Integer.parseInt(pa.getParameterCode());
						if (ingresos == 0 || diasDif >= Integer.parseInt(pa.getParameterType())) {
							pa.setParameterCode((ingresos + 1) + "");
							ps.update(pa);

							if (log.isDebugEnabled()) {
								log.debug("CAMBIO DE CONTRASEÑA OBLIGATORIO");
							}
							return "/User/nuevaContraseña";
						}
						pa.setParameterCode((ingresos + 1) + "");
						ps.update(pa);
						setVerifica(true);
						if (log.isInfoEnabled()) {
							log.info("INGRESÓ SATISFACTORIAMENTE USUARIO: " + usuario.getUserName() + " TIPO: "
									+ usuario.getUserType());
						}
						return "/User/paginaInicio";

					}
					auditoria.setUserId(usuario.getId());
					auditoria.setOperation("E");
					auditoria.setTableId(usuario.getId());
					auditoria.setCreateDate(new Date());
					auditService.save(auditoria);
					if (log.isInfoEnabled()) {
						log.info("SE GENERÓ AUDITORIA");
					}
				} else {

					log.error("USUARIO BLOQUEADO O ELIMINADO");
					return "/Error/ErrorLogin";
				}
			}
		}
		contrasenia = "";
		ingresarUsuario = "";
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR DE AUTENTICACIÓN",
				"USUARIO NO EXISTE");
		FacesContext.getCurrentInstance().addMessage(null, message);
		log.error("USUARIO NO EXISTE");
		return "/Principal/registro";
	}

	public String prepararAdicionarUsuario() {
		usuario = new User();
		usuario.setActive("A");
		usuario.setDateLastPassword(new Date());
		usuario.setUserType("cliente");
		usuario.setPhoneNumber(" ");
		if (log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR USUARIO");
		}
		return "/Principal/registro";
	}

	public String prepararAdicionarUsuarioFuncional() {
		usuario = new User();
		usuario.setActive("A");
		usuario.setDateLastPassword(new Date());
		usuario.setUserType("FUNCIONAL");
		usuario.setPhoneNumber(" ");
		if (log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR USUARIO FUNCIONAL");
		}
		return "agregarFuncional";
	}

	public String adicionarUsuarioFuncional() {

		UserService dao = new UserService();
		User existe = dao.verificarUsuario(usuario.getUserName());

		if (existe == null) {
			String contra = DiferenciaFechas.getGenerarContrasenia();
			usuario.setPassword(Util.getStringMessageDigest(contra, Util.MD5));
			dao.save(usuario);

			String de = "calendario.fifa.uelbosque@gmail.com";
			String clave = "patatafrita";
			String asunto = "CONFIRMACION REGISTRO CALENDARIO FIFA";
			String mensaje = "CALENDARIO FIFA 2018 \n" + "\n" + "\n" + "BIENVENIDO " + usuario.getFullName() + "\n"
					+ "\n" + "\n" + "\n" + "tu cuenta se ha generado exitosamente como usuario funcional \n" + "\n"
					+ "\n    " + "usuario: " + usuario.getUserName() + "\n    clave: " + contra + "\n " + "\n" + "\n"
					+ "\n" + "Te solicitamos que una vez ingrese, cambie su contraseña.\n" + "\n" + "\n" + "\n" + "\n"
					+ "Att: administrador CalendarioFIFA" + "\n" + "Por favor no contestes este correo";

			Correo.enviarCorreo(de, usuario.getEmailAddress(), clave, asunto, mensaje);

			AuditService as = new AuditService();

			auditoria.setUserId(usuario.getId());
			auditoria.setOperation("C");
			auditoria.setTableName("user");
			auditoria.setTableId(usuario.getId());
			auditoria.setCreateDate(new Date());
			as.save(auditoria);
			if (log.isDebugEnabled()) {
				log.debug("SE AGREGO USUARIO FUNCIONAL");
			}

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

			if (log.isDebugEnabled()) {
				log.debug("AGREGÓ LA AUDITORIA");
			}

			return "/Administrador/administrador";
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR DE AUTENTICACIÓN",
				"ESTE USUARIO YA EXISTE");
		FacesContext.getCurrentInstance().addMessage(null, message);
		log.error("USUARIO YA EXISTE");
		return "/Principal/inicio";

	}

	public String adicionarUsuario() {
//		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");
//        FacesContext.getCurrentInstance().addMessage(null, msg);

		UserService dao = new UserService();
		User existe = dao.verificarUsuario(usuario.getUserName());
		if (existe == null) {
			String contra = DiferenciaFechas.getGenerarContrasenia();
			usuario.setPassword(Util.getStringMessageDigest(contra, Util.MD5));
			dao.save(usuario);
			String de = "calendario.fifa.uelbosque@gmail.com";
			String clave = "patatafrita";
			String asunto = "CONFIRMACION REGISTRO CALENDARIO FIFA";
			String mensaje = "CALENDARIO FIFA 2018 \n" + "\n" + "\n" + "BIENVENIDO " + usuario.getFullName() + "\n"
					+ "\n" + "\n" + "\n" + "tu cuenta se ha generado exitosamente \n" + "\n" + "\n    " + "usuario: "
					+ usuario.getUserName() + "\n    clave: " + contra + "\n " + "\n" + "\n" + "\n"
					+ "Te solicitamos que una vez ingrese, cambie su contraseña.\n" + "\n" + "\n" + "\n" + "\n"
					+ "Att: administrador CalendarioFIFA" + "\n" + "Por favor no contestes este correo";

			Correo.enviarCorreo(de, usuario.getEmailAddress(), clave, asunto, mensaje);

			AuditService as = new AuditService();

			if (log.isInfoEnabled()) {
				log.info("SE AGREGO USUARIO");
			}
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

			if (log.isDebugEnabled()) {
				log.debug("AGREGÓ LA AUDITORIA");
			}
			return "inicio";
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR DE AUTENTICACIÓN",
				"ESTE USUARIO YA EXISTE");
		FacesContext.getCurrentInstance().addMessage(null, message);
		log.error("USUARIO NO EXISTE");
		return "registro";
	}

	/**
	 * METODOS PARA EL USUARIO FUNCIONAL
	 * 
	 * @return
	 */
	public String prepararModificarUsuario() {
		usuario = (User) (listaUsuarios.getRowData());
		if (log.isDebugEnabled()) {
			log.debug("PREPARAR PARA MODIFICAR USUARIO");
		}
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
		if (log.isDebugEnabled()) {
			log.debug("USUARIO ELIMINADO");
		}

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
		Parameter p = ps.getParametroPorUsuario(usuario.getId());
		if (p != null) {
			p.setParameterCode("0");
			ps.update(p);
		}

		if (log.isDebugEnabled()) {
			log.debug("USUARIO MODIFICADO");
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
		if (log.isDebugEnabled()) {
			log.debug("AGREGÓ GOLEADOR");
		}

	}

	public String prepararModificarGoleador() {
		List<Goalscorer> goalscorer = new GoalscorerService().list();
		DataModel listaGoleadores = new ListDataModel(goalscorer);
		goleador = (Goalscorer) (listaGoleadores.getRowData());
		if (log.isDebugEnabled()) {
			log.debug(" PREPARAR MODIFICAR GOLEADOR");
		}
		return "editarUsuario";
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
		if (log.isDebugEnabled()) {
			log.debug("MODIFICAR GOLEADOR");
		}
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
		if (log.isDebugEnabled()) {
			log.debug("PREPARAR MODIFICAR NOTICIA");
		}
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

		if (log.isDebugEnabled()) {
			log.debug("MODIFICAR NOTICIA");
		}
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
		if (log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR ARBITRO");
		}
	}

	public String prepararModificarArbitro() {
		List<Referee> referee = new RefereeService().list();
		DataModel listaArbitros = new ListDataModel(referee);
		arbitro = (Referee) (listaArbitros.getRowData());
		if (log.isDebugEnabled()) {
			log.debug("PREPARAR PARA MODIFICAR ARBITRO");
		}
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
		if (log.isDebugEnabled()) {
			log.debug("MODIFICAR ARBITRO");
		}
	}

	public String prepararAdicionarCalendario() {
		calendario = new Schedule();
		if (log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR CALENDARIO");
		}
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
		if (log.isDebugEnabled()) {
			log.debug("ADICIONAR CALENDARIO");
		}
	}

	public String prepararModificarCalendario() {
		List<Schedule> schedule = new ScheduleService().list();
		DataModel listaCalendario = new ListDataModel(schedule);
		calendario = (Schedule) (listaCalendario.getRowData());
		if (log.isDebugEnabled()) {
			log.debug("PREPARAR PARA MODIFICAR CALENDARIO");
		}
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
		if (log.isDebugEnabled()) {
			log.debug("MODIFICAR CALENDARIO");
		}
	}

	public String prepararAdicionarEstadio() {
		estadio = new Stadium();
		if (log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR ESTADIO");
		}
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
		if (log.isDebugEnabled()) {
			log.debug("ADICIONAR ESTADIO");
		}
	}

	public String prepararModificarEstadio() {
		List<Stadium> stadium = new StadiumService().list();
		DataModel listaEstadios = new ListDataModel(stadium);
		estadio = (Stadium) (listaEstadios.getRowData());
		if (log.isDebugEnabled()) {
			log.debug("PREPARAR PARA MODIFICAR ESTADIO");
		}
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
		if (log.isDebugEnabled()) {
			log.debug("MODIFICAR ESTADIO");
		}
	}

	public String prepararAdicionarEquipo() {
		equipo = new Team();
		if (log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR EQUIPO");
		}
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
		if (log.isDebugEnabled()) {
			log.debug("ADICIONAR EQUIPO");
		}
	}

	public String prepararModificarEquipo() {
		List<Team> team = new TeamService().list();
		DataModel listaEquipos = new ListDataModel(team);
		equipo = (Team) (listaEquipos.getRowData());
		if (log.isDebugEnabled()) {
			log.debug("PREPARAR MODIFICAR EQUIPO");
		}
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
		if (log.isDebugEnabled()) {
			log.debug("MODIFICAR EQUIPO");
		}
	}

	public User getUsuario() {
		return usuario;
	}

	public DataModel getListarUsuario() {
		List<User> lista = new UserService().list();
		listaUsuarios = new ListDataModel(lista);
		if (log.isDebugEnabled()) {
			log.debug("LISTAR USUARIOS");
		}
		return listaUsuarios;
	}

	public String getSolicitarContrasenia() {
		String de = "calendario.fifa.uelbosque@gmail.com";
		String clave = "patatafrita";
		String asunto = "DESBLOQUEO DE USUARIO, CALENDARIO FIFA";
		String mensaje = "Para desbloquear al usuario :" + usuario.getUserName();

		Correo.enviarCorreo(de, de, clave, asunto, mensaje);

		if (log.isDebugEnabled()) {
			log.debug("SOLICITAR CONTRASEÑA");
		}
		return "/Principal/login";
	}

	public String prepararModificarUsuarioNormal() {
		usuario = (User) (listaUsuarios.getRowData());
		if (log.isDebugEnabled()) {
			log.debug("PREPARAR PARA MODIFICAR USUARIO");
		}
		return "editarUsuario";
	}

	public String modificarUsuarioNormal() {
		UserService dao = new UserService();
		dao.update(usuario);
		if (log.isDebugEnabled()) {
			log.debug("MODIFICAR USUARIO");
		}
		return "paginaInicio";
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
		if (log.isDebugEnabled()) {
			log.debug("OLVIDO CONTRASEÑA");
		}

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

	public boolean isVerifica() {
		return verifica;
	}

	public void setVerifica(boolean verifica) {
		this.verifica = verifica;
	}

}