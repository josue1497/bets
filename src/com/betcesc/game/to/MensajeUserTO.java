package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.MensajeUserIF;

public class MensajeUserTO implements Serializable, MensajeUserIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7158260429723668285L;
	private String idMensaje;
	private String fechaSis;
	private String idUsuario;
	private String idUserTo;
	private String fechaView;
	private String mensaje;
	
	private String usuario;

	public MensajeUserTO() {

	}

	public String getIdMensaje() {
		return idMensaje;
	}

	public void setIdMensaje(String idMensaje) {
		this.idMensaje = idMensaje;
	}

	public String getFechaSis() {
		return fechaSis;
	}

	public void setFechaSis(String fechaSis) {
		this.fechaSis = fechaSis;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getFechaView() {
		return fechaView;
	}

	public void setFechaView(String fechaView) {
		this.fechaView = fechaView;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getIdUserTo() {
		return idUserTo;
	}

	public void setIdUserTo(String idUserTo) {
		this.idUserTo = idUserTo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	
}
