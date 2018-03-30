/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.CuentaJugadorIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo CuentaJugadorTO serializables.
 */

public class CuentaJugadorTO implements Serializable, CuentaJugadorIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4145506672813096667L;
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
	
	/**
	 * @return Returns the idCuenta.
	 */
	public String getIdCuenta() {
		return idCuenta;
	}

	/**
	 * @param idCuenta
	 *            The idCuenta to set.
	 */
	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}

	/**
	 * @return Returns the fechaSis.
	 */
	public String getFechaSis() {
		return fechaSis;
	}

	/**
	 * @param fechaSis
	 *            The fechaSis to set.
	 */
	public void setFechaSis(String fechaSis) {
		this.fechaSis = fechaSis;
	}

	/**
	 * @return Returns the referencia.
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * @param referencia
	 *            The referencia to set.
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * @return Returns the operacion.
	 */
	public String getOperacion() {
		return operacion;
	}

	/**
	 * @param operacion
	 *            The operacion to set.
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	/**
	 * @return Returns the monto.
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * @param monto
	 *            The monto to set.
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}

	/**
	 * @return Returns the concepto.
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * @param concepto
	 *            The concepto to set.
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
	 * @return Returns the idUsuario.
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario
	 *            The idUsuario to set.
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
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
