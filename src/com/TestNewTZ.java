package com;

import java.util.*;

import org.apache.log4j.Logger;

/*
 Si el cambio ha sido exitoso deberá mostrarse una salida como la que sigue:
 -------------------------------------------
 OffSet ACTUAL en minutos con relacion a GMT: -270
 -------------------------------------------
 OffSet ANTERIOR en minutos para el 06 de Noviembre a las 02:00 horas: -240
 -------------------------------------------
 */

public class TestNewTZ {
	static Logger log =Logger.getLogger(TestNewTZ.class);
	public static void main(String[] args) {
		int CurrentOffSet = TimeZone.getTimeZone("America/Caracas").getRawOffset();
		CurrentOffSet = CurrentOffSet / 60000;
		
		log.info("-------------------------------------------");
		System.out.print("OffSet ACTUAL en minutos con relacion a GMT: ");
		log.info(CurrentOffSet);

		int ComingOffSet1 = TimeZone.getTimeZone("America/Caracas").getOffset(1, 2007, 11, 6, 1, 7200000);
		ComingOffSet1 = ComingOffSet1 / 60000;
		log.info("-------------------------------------------");
		System.out.print("OffSet ANTERIOR en minutos para el 06 de Noviembre a las 02:00 horas: ");
		log.info(ComingOffSet1);
		log.info("-------------------------------------------");

	}
}
