package com.jade.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.betcesc.game.common.Constants;

public class Date extends java.util.Date {
	
	private static final long serialVersionUID = 7389533587116518453L;
	static Logger log =Logger.getLogger(Date.class);

	public Date() {
		super.setTime(getDate(Date.getCalendar()).getTime());
	}
	
	public Date(java.util.Date date) {
		super.setTime(date.getTime());
	}

	public Date(Calendar cal) {
		super.setTime(getDate(cal).getTime());
	}
	
	public static Calendar getCalendar() {
		Locale l = new Locale("es", "VE");
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(Constants.TIME_ZONE),l);

		// se resta el currentoffset si no esta actualizada la hora
	//	cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)+getCurrentOffSet());
		
		return cal;
	}
	
	public java.util.Date getDate(Calendar cal) {
		StringBuffer cad = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		int an = cal.get(Calendar.YEAR);
		int me = cal.get(Calendar.MONTH)+1;
		int di = cal.get(Calendar.DATE);
		int ho = cal.get(Calendar.HOUR_OF_DAY);
		int mi = cal.get(Calendar.MINUTE);
		int ss = cal.get(Calendar.SECOND);
		
		cad.append(an).append("-").append(me<10?"0":"");
		cad.append(me).append("-").append(di<10?"0":"");
		cad.append(di).append(" ").append(ho<10?"0":"");
		cad.append(ho).append(":").append(mi<10?"0":"");
		cad.append(mi).append(":").append(ss<10?"0":"");
		cad.append(ss);
		
		//log.info(cad.toString());
		java.util.Date fecha;
		try {
			fecha = sdf.parse(cad.toString());
		} catch (ParseException e) {
			fecha = new java.util.Date(an,me,di,ho,mi,ss);
			e.printStackTrace();
		}
		
		return fecha;
	}
	
	public static Date convert(java.util.Date date) {
		return new Date(date);
	}
	
	public static Date convert(Calendar cal) {
		return new Date(cal); 
	}
	
	
	public static int getCurrentOffSet() {
		int CurrentOffSet = TimeZone.getTimeZone(Constants.TIME_ZONE).getRawOffset();
		CurrentOffSet = CurrentOffSet / 60000;

		//log.info("----------- debe ser -270 --------------------------------");
		//System.out.print("OffSet ACTUAL en minutos con relacion a GMT: ");
		//log.info(CurrentOffSet);

		int ComingOffSet1 = TimeZone.getTimeZone(Constants.TIME_ZONE).getOffset(1, 2007, 11, 6, 1, 7200000);
		ComingOffSet1 = ComingOffSet1 / 60000;
		//log.info("----------- debe ser -240 --------------------------------");
		//System.out.print("OffSet ANTERIOR en minutos para el 06 de Noviembre a las 02:00 horas: ");
		//log.info(ComingOffSet1);
		//log.info("-------------------------------------------");
		
		return (CurrentOffSet==-240?-30:0); 
	}
	
	public static void main(String[] args) {
		Locale l = new Locale("es", "VE");
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(Constants.TIME_ZONE),l);
		StringBuffer cad = new StringBuffer();

		cad.append(cal.get(Calendar.DATE));
		cad.append("-");
		cad.append(cal.get(Calendar.MONTH)+1);
		cad.append("-");
		cad.append(cal.get(Calendar.YEAR)); 
		cad.append(" ");
		cad.append(cal.get(Calendar.HOUR_OF_DAY));
		cad.append(":");
		cad.append(cal.get(Calendar.MINUTE));
		cad.append(":");
		cad.append(cal.get(Calendar.SECOND));
		
		log.info("Calendar: " + cal.getTimeZone().getDisplayName() + " -> "+ cad.toString());
		
		java.util.Date f = new java.util.Date();
		com.jade.util.Date d = new com.jade.util.Date();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		int an = cal.get(Calendar.YEAR);
		int me = cal.get(Calendar.MONTH);
		int di = cal.get(Calendar.DATE);
		int ho = cal.get(Calendar.HOUR_OF_DAY);
		int mi = cal.get(Calendar.MINUTE);
		int ss = cal.get(Calendar.SECOND);
		java.util.Date fecha = new java.util.Date(an,me,di,ho,mi,ss);
		
		log.info("Fecha Venezuela calendar : "+sdf.format(fecha));
		log.info("Fecha Venezuela (calculada): "+sdf.format(d));
		log.info("Fecha programa : "+sdf.format(f));
		log.info("getCurrentOffSet : "+com.jade.util.Date.getCurrentOffSet());
		
		log.info("");
		log.info(sdf.format(Date.convert(fecha)));
		log.info(sdf.format(new java.util.Date(fecha.getTime())));
		
	}

}
