package com.betcesc.game.ctrl.front;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ContactenosActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5675304225982131054L;
	private String nombre = "";
	private String email = "";
	private String mensaje = "";

	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
		nombre = "";
		email = "";
		mensaje = "";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
