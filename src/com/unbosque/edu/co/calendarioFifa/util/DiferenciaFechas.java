package com.unbosque.edu.co.calendarioFifa.util;

import java.util.Date;

public class DiferenciaFechas {

	
	public static long DifeenciaFechas(Date ultima, Date inicial) {
		
		return (ultima.getTime()-inicial.getTime())/ (1000 * 60 * 60 * 24);
		
	}
}
