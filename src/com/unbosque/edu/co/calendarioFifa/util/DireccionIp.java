package com.unbosque.edu.co.calendarioFifa.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class DireccionIp {

	public static String getRemoteAddress() {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	String ipAddress = request.getHeader("X-FORWARDED-FOR");
	if (ipAddress == null) {
		ipAddress = request.getRemoteAddr();
	}
	
	return ipAddress;
	}
	
}
