package com.betcesc.game.common;

import javax.servlet.http.HttpServletRequest;

public class Validator {

	/**
	 * Permite validar si un valor es entero.
	 * 
	 * @param valor
	 * @return true si es un entero
	 */
	public static boolean isInteger(String valor) {
		return (valor!=null && !valor.replaceAll(Constants.PATRON_ENTERO, "").trim().equals("") );
	}
	
	/**
	 * Permite validar si un valor es un string

	 * @param valor
	 * @return true si es un string
	 * 
	 */
	public static boolean isString(String valor) {
		return (valor!=null && !valor.replaceAll(Constants.PATRON_CADENA, "").trim().equals("") );
	}
	
	/**
	 * Permite validar si un valor esta en blanco o null
	 * 
	 * @param valor
	 * @return true si el valor esta vacio
	 */
	public static boolean isEmpty(String valor){
		return (valor==null || valor.trim().equals("") );
	}

	/**
	 * Permite validar si un valor esta en blanco o null y devuelve el valor pasado
	 * 
	 * @param valor
	 * @return true si el valor esta vacio
	 */
	public static String isEmpty(String valorNew,String valorOld){
		return (valorNew==null || valorNew.trim().equals("") || valorNew.trim().toLowerCase().equals("null") ? valorOld : valorNew );
	}
	/**
	 * Permite validar si un valor esta en blanco o null y devuelve el valor pasado
	 * 
	 * @param valor
	 * @return true si el valor esta vacio
	 */
	public static String isEmptyParameter(HttpServletRequest request,String etiqueta,String valorOld){
		return isEmpty(request.getParameter(etiqueta),valorOld);
	}
	
	public static String isNullParameter(HttpServletRequest request,String etiqueta,String valorOld){
		return isNull(request.getParameter(etiqueta),valorOld);
	}

	public static String isNull(String valorNew,String valorOld){
		return (valorNew==null ? valorOld : valorNew );
	}
	
}
