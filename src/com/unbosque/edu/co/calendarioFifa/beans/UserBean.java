package com.unbosque.edu.co.calendarioFifa.beans;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.unbosque.edu.co.calendarioFifa.entity.*;
import com.unbosque.edu.co.calendarioFifa.service.*;
import com.unbosque.edu.co.calendarioFifa.util.*;

/**
 * The Class UserBean.
 */
@ManagedBean
@SessionScoped
public class UserBean {

	
	/**
	 * ATRIBUTOS PARA EL USUARIO EN GENERAL.
	 */
	private static int INTENTOS = 0;
	
	/** The usuario. */
	private User usuario;
	
	/** The lista usuarios. */
	private DataModel listaUsuarios;
	
	/** The ingresar usuario. */
	private String ingresarUsuario;
	
	/** The contrasenia. */
	private String contrasenia;
	
	private String userOlvido;
	
	/** The contrasenia nueva. */
	private String contraseniaNueva;
	
	@ManagedProperty("#{auditBean}")
	private AuditBean auditBean;
	
	/** The verifica. */
	private boolean verificaAdmin = false;
	
	private boolean verificaCliente = false;
	
	private boolean verificaFuncional = false;
	
	/** The lista arbitros. */
	private DataModel listaArbitros;
	
	/** The lista equipos. */
	private DataModel listaEquipos;
	
	/** The lista estadios. */
	private DataModel listaEstadios;
	
	/** The lista noticias. */
	private DataModel listaNoticias;
	
	/** ATRIBUTOS PARA EL USUARIO FUNCIONAL. */
	private Goalscorer goleador;
	
	/** The noticia. */
	private New noticia;
	
	/** The arbitro. */
	private Referee arbitro;
	
	/** The calendario. */
	private Schedule calendario;
	
	/** The estadio. */
	private Stadium estadio;
	
	/** The equipo. */
	private Team equipo;
	

	/** The Constant log. */
	final static Logger log = Logger.getLogger(UserBean.class);

	/**
	 * METODOS PARA LOS USUARIOS EN GENERAL (ADMINISTRADOR, FUNCIONAL, CLIENTE).
	 *
	 * @return the string
	 */

	public String validarUsuario() 
	{

		contrasenia = Util.getStringMessageDigest(contrasenia, Util.MD5);
		UserService us = new UserService();
		usuario = us.verificarUsuario(ingresarUsuario);
		ingresarUsuario = "";
		ParameterService ps = new ParameterService();
		int ingresos = 0;
		long diasDif = 0;
		if (usuario != null) 
		{
			
			Parameter pa = ps.verificarParametros(usuario.getId() + "");

			if (usuario.getPassword().compareTo(contrasenia) != 0) 
			{
				if (usuario.getUserType().equals("cliente") || usuario.getUserType().equals("FUNCIONAL")) 
				{
					INTENTOS++;
					if (INTENTOS == pa.getNumberValue()) 
					{
						usuario.setActive("I");
						us.update(usuario);
						INTENTOS = 0;
						auditBean.bloquearAuditoria(usuario.getId(), "User", usuario.getId(),DireccionIp.getRemoteAddress());
						log.error("USUARIO:" + usuario.getUserName() + " BLOQUEADO");
						return "/Error/ErrorLogin";
					}
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR DE AUTENTICACIÓN",
							"CONTRASEÑA INCORRECTA");
					FacesContext.getCurrentInstance().addMessage(null, message);
					log.error("CONTRASEÑA INCORRECTA");
				}
				return "/Principal/login";
			} else if (usuario.getActive().equals("A")) 
			{
				INTENTOS = 0;
				if (usuario.getUserType().equals("ADMIN")) 
				{
					setVerificaAdmin(true);

					if (log.isInfoEnabled()) 
					{
						log.info("INGRESÓ SATISFACTORIAMENTE USUARIO: " + usuario.getUserName() + " TIPO: "
								+ usuario.getUserType());
					}
										return "/Administrador/administrador";
				} else if (usuario.getUserType().equals("FUNCIONAL")) 
				{
					setVerificaFuncional(true);
					diasDif = DiferenciaFechas.DifeenciaFechas(new Date(), usuario.getDateLastPassword());
					ingresos = Integer.parseInt(pa.getParameterCode());
					if (ingresos == 0 || diasDif >= Integer.parseInt(pa.getParameterType())) 
					{
						if(diasDif >= Integer.parseInt(pa.getParameterType())) {
							usuario.setDateLastPassword(new Date());
						}
						pa.setParameterCode((ingresos + 1) + "");
						ps.update(pa);

						if (log.isDebugEnabled()) 
						{
							log.debug("CAMBIO DE CONTRASEÑA OBLIGATORIO");
						}
						return "/UserFuncional/nuevaContraseñaFun";
					}
					pa.setParameterCode((ingresos + 1) + "");
					ps.update(pa);
					if (log.isInfoEnabled()) 
					{
						log.info("INGRESÓ SATISFACTORIAMENTE USUARIO: " + usuario.getUserName() + " TIPO: "
								+ usuario.getUserType());
					}
					return "/UserFuncional/funcional";

				} else if (usuario.getUserType().equals("cliente")) 
				{
					setVerificaCliente(true);
						diasDif = DiferenciaFechas.DifeenciaFechas(new Date(), usuario.getDateLastPassword());
						ingresos = Integer.parseInt(pa.getParameterCode());
						if (ingresos == 0 || diasDif >= Integer.parseInt(pa.getParameterType())) 
						{
							if(diasDif >= Integer.parseInt(pa.getParameterType())) {
								usuario.setDateLastPassword(new Date());
							}
							pa.setParameterCode((ingresos + 1) + "");
							ps.update(pa);

							if (log.isDebugEnabled()) {
								log.debug("CAMBIO DE CONTRASEÑA OBLIGATORIO");
							}
							return "/User/nuevaContraseña";
						}
						pa.setParameterCode((ingresos + 1) + "");
						ps.update(pa);
						if (log.isInfoEnabled()) 
						{
							log.info("INGRESÓ SATISFACTORIAMENTE USUARIO: " + usuario.getUserName() + " TIPO: "
									+ usuario.getUserType());
						}
						return "/User/paginaInicio";

					}
				auditBean.ingresarAuditoria(usuario.getId(), "User", usuario.getId(),DireccionIp.getRemoteAddress());
					
				} else 
				{

					log.error("USUARIO BLOQUEADO O ELIMINADO");
					return "/Error/ErrorLogin";
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

	/**
	 * Preparar adicionar usuario.
	 *
	 * @return the string
	 */
	public String prepararAdicionarUsuario() {
		usuario = new User();
		usuario.setActive("A");
		usuario.setDateLastPassword(new Date());
		usuario.setUserType("cliente");
		usuario.setPhoneNumber(" ");
		if (log.isDebugEnabled()) {
			log.debug("PREPARAR PARA ADICIONAR USUARIO");
		}
		return "registro";
	}

	/**
	 * Preparar adicionar usuario funcional.
	 *
	 * @return the string
	 */
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

	/**
	 * Adicionar usuario funcional.
	 *
	 * @return the string
	 */
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

			auditBean.adicionarAuditoria(usuario.getId(), "User", usuario.getId(),DireccionIp.getRemoteAddress());
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

	/**
	 * Adicionar usuario.
	 *
	 * @return the string
	 */
	public String adicionarUsuario() {

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
			auditBean.adicionarAuditoria(usuario.getId(), "User", usuario.getId(),DireccionIp.getRemoteAddress());
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
	 * METODOS PARA EL USUARIO FUNCIONAL.
	 *
	 * @return the string
	 */
	public String prepararModificarUsuario() {
		usuario = (User) (listaUsuarios.getRowData());
		if (log.isDebugEnabled()) {
			log.debug("PREPARAR PARA MODIFICAR USUARIO");
		}
		return "registro";
	}

	/**
	 * Eliminar usuario.
	 *
	 * @return the string
	 */
	public String BloquearUsuario() {
		User usuarioTemp = (User) (listaUsuarios.getRowData());
		UserService dao = new UserService();
		usuarioTemp.setActive("I");
		dao.update(usuarioTemp);
		AuditService as = new AuditService();
		auditBean.bloquearAuditoria(usuario.getId(), "User", usuario.getId(),DireccionIp.getRemoteAddress());
		if (log.isDebugEnabled()) {
			log.debug("USUARIO ELIMINADO");
		}

		return "inicio";
	}

	/**
	 * Modificar usuario.
	 *
	 * @return the string
	 */
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

		auditBean.actualizarAuditoria(usuario.getId(), "User", usuario.getId(),DireccionIp.getRemoteAddress());
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

	/**
	 * Preparar adicionar goleador.
	 *
	 * @return the string
	 */
	public String prepararAdicionarGoleador() {
		goleador = new Goalscorer();
		return "goalscorer";
	}

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public User getUsuario() {
		return usuario;
	}

	/**
	 * Gets the listar usuario.
	 *
	 * @return the listar usuario
	 */
	public DataModel getListarUsuario() {
		List<User> lista = new UserService().list();
		listaUsuarios = new ListDataModel(lista);
		if (log.isDebugEnabled()) {
			log.debug("LISTAR USUARIOS");
		}
		return listaUsuarios;
	}

	/**
	 * Solicitar contrasenia.
	 *
	 * @return the string
	 */
	public String solicitarContrasenia() {
		String de = "calendario.fifa.uelbosque@gmail.com";
		String clave = "patatafrita";
		String asunto = "DESBLOQUEO DE USUARIO, CALENDARIO FIFA";
		String mensaje = "Para desbloquear al usuario : " + usuario.getUserName();

		Correo.enviarCorreo(de, de, clave, asunto, mensaje);

		if (log.isDebugEnabled()) {
			log.debug("SOLICITAR CONTRASEÑA");
		}
		return "/Principal/login";
	}

	/**
	 * Preparar modificar usuario normal.
	 *
	 * @return the string
	 */
	public String prepararModificarUsuarioNormal() {
		usuario = (User) (listaUsuarios.getRowData());
		if (log.isDebugEnabled()) {
			log.debug("PREPARAR PARA MODIFICAR USUARIO");
		}
		return "editarUsuario";
	}

	/**
	 * Modificar usuario normal.
	 *
	 * @return the string
	 */
	public String modificarUsuarioNormal() {
		UserService dao = new UserService();
		dao.update(usuario);
		if (log.isDebugEnabled()) {
			log.debug("MODIFICAR USUARIO");
		}
		return "paginaInicio";
	}

	/**
	 * Gets the olvide contrasenia.
	 *
	 * @return the olvide contrasenia
	 */
	public String olvideContrasenia() {
		INTENTOS = 0;
		UserService us = new UserService();
		String nueva = DiferenciaFechas.getGenerarContrasenia();
		User usu = us.verificarUsuario(userOlvido);
		if(usu != null) {
		ParameterService ps = new ParameterService();
		Parameter p = ps.verificarParametros(usu.getId()+"");
		p.setParameterCode("0");
		ps.update(p);
		String de = "calendario.fifa.uelbosque@gmail.com";
		String clave = "patatafrita";
		String asunto = "NUEVA CONTRASEÑA, CALENDARIO FIFA";
		String mensaje = "CALENDARIO FIFA 2018 \n" + "\n" + "\n" + "Usuario: " + usu.getFullName() + "\n" + "\n"
				+ "\n" + "\n" + "Se ha generado su nueva contraseña exitosamente" + "\n" + "\n" + "\n    " + "usuario: "
				+ usu.getUserName() + "\n" + "Clave: " + nueva + "\n " + "\n" + "\n" + "\n"
				+ "Le solicitamos que una vez ingrese y cambie su contraseña.\n" + "\n" + "\n" + "\n" + "\n"
				+ "Att: administrador CalendarioFIFA";

		Correo.enviarCorreo(de, usu.getEmailAddress(), clave, asunto, mensaje);
		nueva = Util.getStringMessageDigest(nueva, Util.MD5);
		usu.setPassword(nueva);
		usu.setDateLastPassword(new Date());
		us.update(usu);
		AuditService as = new AuditService();

		Audit auditoria = new Audit();
		auditoria.setUserId(usu.getId());
		auditoria.setOperation("U");
		auditoria.setTableName("user");
		auditoria.setTableId(usu.getId());
		auditoria.setCreateDate(new Date());
		auditoria.setIp(DireccionIp.getRemoteAddress());
		as.save(auditoria);
		if (log.isDebugEnabled()) {
			log.debug("OLVIDO CONTRASEÑA");
		}
		return "/Principal/login";
		}
		
		return "/Principal/registro";

	}

	/**
	 * Gets the contrasenia nueva.
	 *
	 * @return the contrasenia nueva
	 */
	public String getContraseniaNueva() {
		return contraseniaNueva;
	}

	/**
	 * Sets the contrasenia nueva.
	 *
	 * @param contraseniaNueva the new contrasenia nueva
	 */
	public void setContraseniaNueva(String contraseniaNueva) {
		this.contraseniaNueva = Util.getStringMessageDigest(contraseniaNueva, Util.MD5);
		UserService dao = new UserService();
		usuario.setPassword(this.contraseniaNueva);

		dao.update(usuario);
	}
	
	public String cerrarSesion() {
		usuario = null;
		if(verificaAdmin == true) {
			setVerificaAdmin(false);
		}
		else if(verificaCliente == true) {
			setVerificaCliente(false);
		}
		else if(verificaFuncional == true) {
			setVerificaFuncional(false);
		}
		if(log.isDebugEnabled()) {
		log.debug("CERRAR SESIÓN");
		}
		return "/Principal/inicio";
	}

	/**
	 * Gets the ingresar usuario.
	 *
	 * @return the ingresar usuario
	 */
	public String getIngresarUsuario() {
		return ingresarUsuario;
	}

	/**
	 * Sets the ingresar usuario.
	 *
	 * @param usuario the new ingresar usuario
	 */
	public void setIngresarUsuario(String usuario) {
		this.ingresarUsuario = usuario;
	}

	/**
	 * Gets the contrasenia.
	 *
	 * @return the contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * Sets the contrasenia.
	 *
	 * @param contrasenia the new contrasenia
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario the new usuario
	 */
	public void setUsuario(User usuario) {

		this.usuario = usuario;
	}




	public boolean isVerificaAdmin() {
		return verificaAdmin;
	}

	public void setVerificaAdmin(boolean verificaAdmin) {
		this.verificaAdmin = verificaAdmin;
	}

	public boolean isVerificaCliente() {
		return verificaCliente;
	}

	public void setVerificaCliente(boolean verificaCliente) {
		this.verificaCliente = verificaCliente;
	}

	public boolean isVerificaFuncional() {
		return verificaFuncional;
	}

	public void setVerificaFuncional(boolean verificaFuncional) {
		this.verificaFuncional = verificaFuncional;
	}

	/**
	 * Gets the intentos.
	 *
	 * @return the intentos
	 */
	public static int getINTENTOS() {
		return INTENTOS;
	}

	/**
	 * Sets the intentos.
	 *
	 * @param iNTENTOS the new intentos
	 */
	public static void setINTENTOS(int iNTENTOS) {
		INTENTOS = iNTENTOS;
	}

	/**
	 * Gets the lista usuarios.
	 *
	 * @return the lista usuarios
	 */
	public DataModel getListaUsuarios() {
		return listaUsuarios;
	}

	/**
	 * Sets the lista usuarios.
	 *
	 * @param listaUsuarios the new lista usuarios
	 */
	public void setListaUsuarios(DataModel listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

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
	 * Gets the noticia.
	 *
	 * @return the noticia
	 */
	public New getNoticia() {
		return noticia;
	}

	/**
	 * Sets the noticia.
	 *
	 * @param noticia the new noticia
	 */
	public void setNoticia(New noticia) {
		this.noticia = noticia;
	}

	/**
	 * Gets the arbitro.
	 *
	 * @return the arbitro
	 */
	public Referee getArbitro() {
		return arbitro;
	}

	/**
	 * Sets the arbitro.
	 *
	 * @param arbitro the new arbitro
	 */
	public void setArbitro(Referee arbitro) {
		this.arbitro = arbitro;
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
	 * @param calendario the new calendario
	 */
	public void setCalendario(Schedule calendario) {
		this.calendario = calendario;
	}

	/**
	 * Gets the estadio.
	 *
	 * @return the estadio
	 */
	public Stadium getEstadio() {
		return estadio;
	}

	/**
	 * Sets the estadio.
	 *
	 * @param estadio the new estadio
	 */
	public void setEstadio(Stadium estadio) {
		this.estadio = estadio;
	}

	/**
	 * Gets the equipo.
	 *
	 * @return the equipo
	 */
	public Team getEquipo() {
		return equipo;
	}

	/**
	 * Sets the equipo.
	 *
	 * @param equipo the new equipo
	 */
	public void setEquipo(Team equipo) {
		this.equipo = equipo;
	}

	/**
	 * Gets the log.
	 *
	 * @return the log
	 */
	public static Logger getLog() {
		return log;
	}

	/**
	 * Gets the lista arbitros.
	 *
	 * @return the lista arbitros
	 */
	public DataModel getListaArbitros() {
		List<Referee> referee = new RefereeService().list();
		 listaArbitros = new ListDataModel(referee);
		 if(log.isDebugEnabled()) {
				log.debug("PREPARAR PARA ADICIONAR LA AUDITORIA");
			}
		return listaArbitros;
	}

	/**
	 * Sets the lista arbitros.
	 *
	 * @param listaArbitros the new lista arbitros
	 */
	public void setListaArbitros(DataModel listaArbitros) {
		this.listaArbitros = listaArbitros;
	}

	/**
	 * Gets the lista equipos.
	 *
	 * @return the lista equipos
	 */
	public DataModel getListaEquipos() {
		return listaEquipos;
	}

	/**
	 * Sets the lista equipos.
	 *
	 * @param listaEquipos the new lista equipos
	 */
	public void setListaEquipos(DataModel listaEquipos) {
		this.listaEquipos = listaEquipos;
	}

	/**
	 * Gets the lista estadios.
	 *
	 * @return the lista estadios
	 */
	public DataModel getListaEstadios() {
		return listaEstadios;
	}

	/**
	 * Sets the lista estadios.
	 *
	 * @param listaEstadios the new lista estadios
	 */
	public void setListaEstadios(DataModel listaEstadios) {
		this.listaEstadios = listaEstadios;
	}

	/**
	 * Gets the lista noticias.
	 *
	 * @return the lista noticias
	 */
	public DataModel getListaNoticias() {
		return listaNoticias;
	}

	/**
	 * Sets the lista noticias.
	 *
	 * @param listaNoticias the new lista noticias
	 */
	public void setListaNoticias(DataModel listaNoticias) {
		this.listaNoticias = listaNoticias;
	}

	public String getUserOlvido() {
		return userOlvido;
	}

	public void setUserOlvido(String userOlvido) {
		this.userOlvido = userOlvido;
	}

	public AuditBean getAuditBean() {
		return auditBean;
	}

	public void setAuditBean(AuditBean auditBean) {
		this.auditBean = auditBean;
	}

	
	

}