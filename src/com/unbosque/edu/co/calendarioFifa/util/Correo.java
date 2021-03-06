package com.unbosque.edu.co.calendarioFifa.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * The Class Correo.
 */
public class Correo {
	
	/**
	 * Instantiates a new correo.
	 */
	public Correo() {
		
	}
	
	/**
	 * Enviar correo.
	 *
	 * @param de the de
	 * @param para the para
	 * @param clave the clave
	 * @param asunto the asunto
	 * @param mensaje the mensaje
	 */
	public static void enviarCorreo(String de, String para, String clave, String asunto, String mensaje) {

		try {
			String host = "smtp.gmail.com";
			Properties p = System.getProperties();
			p.put("mail.smtp.starttls.enable", "true");
			p.put("mail.smtp.host", host);
			p.put("mail.smtp.user", de);
			p.put("mail.smtp.password", clave);
			p.put("mail.smtp.port", 587);
			p.put("mail.mtp.auth", "true");

			
			Session sesion = Session.getDefaultInstance(p, null);
			MimeMessage message = new MimeMessage(sesion);
			message.setFrom(new InternetAddress(de));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(para));
			message.setSubject(asunto);
			message.setText(mensaje);
			Transport transport = sesion.getTransport("smtp");
			transport.connect(host, de, clave);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
