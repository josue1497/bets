package com.betcesc.game.ctrl.back;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.betcesc.game.interfaces.CuentaJugadorIF;

public class RegisterSaldoActionForm extends ActionForm implements CuentaJugadorIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 249923751960603218L;

	private static final Log log = LogFactory.getLog(RegisterSaldoActionForm.class);

	private String idCuenta = null;
	private String fechaSis = null;
	private String referencia = null;
	private String operacion = null;
	private String monto = null;
	private String concepto = null;
	private String idJugador = null;
	private String idUsuario = null;
	private String tipo = null;

	private String nombreJugador = null;

	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		log.info("Iniciando reset()");
	}

	public ActionErrors validate(ActionMapping arg0, HttpServletRequest request) {
		log.info("Iniciando validate()");

		ActionErrors errores = new ActionErrors();

		return errores;
	}

	/* GET and SET */

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getFechaSis() {
		return fechaSis;
	}

	public void setFechaSis(String fechaSis) {
		this.fechaSis = fechaSis;
	}

	public String getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(String idJugador) {
		this.idJugador = idJugador;
	}

	public String getNombreJugador() {
		return nombreJugador;
	}

	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	

}
