package com.betcesc.game.ctrl.back;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.betcesc.game.interfaces.JugadaIF;
import com.betcesc.game.interfaces.JugadaJuegoEquipoIF;

public class RegisterPlayActionForm extends ActionForm implements JugadaIF, JugadaJuegoEquipoIF {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8548394465035109391L;

	private static final Log log = LogFactory.getLog(RegisterGameActionForm.class);

	/* JugadaTO */
	private String idJugada = null;
	private String fechaSis = null;
	private String fechaExp = null;
	private String montoApostado = null;
	private String montoPremio = null;
	private String montoPagado = null;
	private String idUsuario = null;
	private String idStatusJugada = null;
	private String diasExpira = null;
	private String fechaPago = null;
	private String detalleEquipo = null;
	private String cancelada = null;
	private String itemsJugada = null;
	private String bono = null;

	/* JugadaJuegoEquipoTO */
    private String idJugadaJuegoEquipo = null;
    private String idUsuarioJuegoEquipo = null;
    private String tipo = null;
	

	public RegisterPlayActionForm() {
		super();
	}

	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		log.info("Iniciando reset()");
	}

	public ActionErrors validate(ActionMapping arg0, HttpServletRequest request) {
		log.info("Iniciando validate()");

		ActionErrors errores = new ActionErrors();

		return errores;
	}

	/* GET and SET */

	public String getFechaExp() {
		return fechaExp;
	}

	public void setFechaExp(String fechaExp) {
		this.fechaExp = fechaExp;
	}

	public String getFechaSis() {
		return fechaSis;
	}

	public void setFechaSis(String fechaSis) {
		this.fechaSis = fechaSis;
	}

	public String getIdJugada() {
		return idJugada;
	}

	public void setIdJugada(String idJugada) {
		this.idJugada = idJugada;
	}

	public String getIdStatusJugada() {
		return idStatusJugada;
	}

	public void setIdStatusJugada(String idStatusJugada) {
		this.idStatusJugada = idStatusJugada;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getMontoApostado() {
		return montoApostado;
	}

	public void setMontoApostado(String montoApostado) {
		this.montoApostado = montoApostado;
	}

	public String getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(String montoPagado) {
		this.montoPagado = montoPagado;
	}

	public String getMontoPremio() {
		return montoPremio;
	}

	public void setMontoPremio(String montoPremio) {
		this.montoPremio = montoPremio;
	}

	public String getIdJugadaJuegoEquipo() {
		return idJugadaJuegoEquipo;
	}

	public void setIdJugadaJuegoEquipo(String idJugadaJuegoEquipo) {
		this.idJugadaJuegoEquipo = idJugadaJuegoEquipo;
	}

	public String getIdUsuarioJuegoEquipo() {
		return idUsuarioJuegoEquipo;
	}

	public void setIdUsuarioJuegoEquipo(String idUsuarioJuegoEquipo) {
		this.idUsuarioJuegoEquipo = idUsuarioJuegoEquipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDiasExpira() {
		return diasExpira;
	}

	public void setDiasExpira(String diasExpira) {
		this.diasExpira = diasExpira;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getDetalleEquipo() {
		return detalleEquipo;
	}

	public void setDetalleEquipo(String detalleEquipo) {
		this.detalleEquipo = detalleEquipo;
	}
	
	public String getCancelada() {
		return cancelada;
	}

	public void setCancelada(String cancelada) {
		this.cancelada = cancelada;
	}

	public String getItemsJugada() {
		return itemsJugada;
	}

	public void setItemsJugada(String itemsJugada) {
		this.itemsJugada = itemsJugada;
	}

	public String getBono() {
		return bono;
	}

	public void setBono(String bono) {
		this.bono = bono;
	}
	
}
