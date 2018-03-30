package com;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.betcesc.game.common.Constants;

public class Test {
static Logger log =Logger.getLogger(Test.class);
	public static void main(String[] args) {
		
		int i=0;
		log.info("ABC".substring(i,i+1));
		i=1;
		log.info("ABC".substring(i,i+1));
		i=2;
		log.info("ABC".substring(i,i+1));
		i=3;
		log.info("ABC".substring(i,i+1));
		i=4;
		log.info("ABC".substring(i,i+1));
		
		Locale l = new Locale("es", "VE");
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Caracas"),l);
		
		//Locale l = new Locale("es","MX");
		//Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"),l);

		log.info(cal.getTimeZone().getDisplayName());
		
		/*
		cad.append(cal.get(Calendar.DATE));
		cad.append("-");
		cad.append(cal.get(Calendar.MONTH));
		cad.append(1);
		cad.append("-");
		cad.append(cal.get(Calendar.YEAR)); 
		cad.append(" ");
		cad.append(cal.get(Calendar.HOUR_OF_DAY));
		cad.append(":");
		cad.append(cal.get(Calendar.MINUTE));
		cad.append(":");
		cad.append(cal.get(Calendar.SECOND));
		log.info("FECHA: " + cad.toString());
		*/
	}

	public static void main7(String[] args) {
		Date f = new Date();
		Locale l = new Locale("es", "VE");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", l);

		log.info(sdf.format(f));
	}

	public static void main6(String[] args) {
		String arr = "{idLiga:'1?',descLiga:'2?',iniciales:'3?',idDeporte:'4?'}";
		arr = arr.replaceFirst("1\\?", "*Primer Campo*");
		arr = arr.replaceFirst("2\\?", "*Segundo Campo*");
		arr = arr.replaceFirst("3\\?", "*Tercer Campo*");

		log.info(arr);
	}

	public static void main5(String[] args) {
		String fecha = "2007-02-01 00:00:00";

		try {
			log.info(Constants.getFechaLargaSQL(fecha, 28));
			log.info(Constants.getFechaLargaSQL(fecha, 29));
			log.info(Constants.getFechaLargaSQL(fecha, 30));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main4(String[] args) {
		ArrayList l = new ArrayList();

		String[] item = { "1", "7", "3", "1", "4", "1", "7", "3", "1", "2", "1" };

		for (int i = 0; i < item.length; i++) {
			if (!l.contains(item[i])) {
				l.add(item[i]);
				log.info("Registre: " + item[i]);
			} else {
				log.info("Ya esta registrado: " + item[i]);
			}
		}

		for (int i = 0; i < l.size(); i++) {
			log.info(i + ") " + l.get(i));
		}

	}

	public static void main3(String[] args) {
		String c = "Revise su Ticket antes de retirarse de la taquilla.<br/> La presentacion de este ticket es indispensable para la cancelacion de los premios.";

		NumberFormat n = NumberFormat.getInstance(new Locale("EN"));
		n.setGroupingUsed(true);
		n.setMinimumFractionDigits(2);
		n.setMaximumFractionDigits(2);

		double monto = 500261;

		log.info(n.format(monto));
		// log.info(Constants.formatNumber(monto));
		// log.info(justificar(c, 40));

	}

	public static String justificar(String cadena, int limite) {
		if (cadena == null)
			return "<br/>";
		String[] lineas = cadena.split(" ");
		StringBuffer parrafo = new StringBuffer();
		String space = "";
		String salto = "<br/>";
		int c = 0;

		for (int i = 0; i < lineas.length; i++) {
			if (c + space.length() + lineas[i].trim().length() > limite) {
				c = 0;
				parrafo.append(salto);
				space = "";
			}
			c += space.length() + lineas[i].trim().length();
			parrafo.append(space).append(lineas[i].trim());
			if (parrafo.toString().endsWith(salto)) {
				c = 0;
			}
			space = (c == 0 ? "" : " ");
		}

		return parrafo.toString();
	}

}
