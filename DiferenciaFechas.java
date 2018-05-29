package com.unbosque.edu.co.calendarioFifa.util;

import java.util.Date;

public class DiferenciaFechas {

	
	public static long DifeenciaFechas(Date ultima, Date inicial) {
		
		return (ultima.getTime()-inicial.getTime())/ (1000 * 60 * 60 * 24);
		
	}
	
	public  static String getGenerarContrasenia() {

		String contrasenia = "";
		String caracteres = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz";
		String numeros = "1234567890";
		for (int i = 0; i < 8; i++) {
			char a = caracteres.charAt((int) (Math.random() * caracteres.length()));
			
			if(i>4) {
				a = numeros.charAt((int) (Math.random() * numeros.length()));
			}
			
			contrasenia += a;
			

		}

		return contrasenia;
	}

}
