/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */
package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.DeporteIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo DeporteTO serializables.
 */

public class DeporteTO implements Serializable, DeporteIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3336745743569773859L;
	private String idDeporte = null;
	private String descDeporte = null;
	private String empate = null;
	private String idStatusDeporte = null;
	private String referenciaInicio = null;
	private String runlineInicio = null;
	private String combinacion = null;
	private String items = null;
	private String runlineLogroInicio0 = null;
	private String runlineLogroInicio1 = null;
	private String altabajaLogroInicio0 = null;
	private String altabajaLogroInicio1 = null;
	/**
	 * @return Returns the idDeporte.
	 */
	public String getIdDeporte() {
		return idDeporte;
	}

	/**
	 * @param idDeporte
	 *            The idDeporte to set.
	 */
	public void setIdDeporte(String idDeporte) {
		this.idDeporte = idDeporte;
	}

	/**
	 * @return Returns the descDeporte.
	 */
	public String getDescDeporte() {
		return descDeporte;
	}

	/**
	 * @param descDeporte
	 *            The descDeporte to set.
	 */
	public void setDescDeporte(String descDeporte) {
		this.descDeporte = descDeporte;
	}

	/**
	 * @return Returns the empate.
	 */
	public String getEmpate() {
		return empate;
	}

	/**
	 * @param empate
	 *            The empate to set.
	 */
	public void setEmpate(String empate) {
		this.empate = empate;
	}

	/**
	 * @return Returns the idStatusDeporte.
	 */
	public String getIdStatusDeporte() {
		return idStatusDeporte;
	}

	/**
	 * @param idStatusDeporte
	 *            The idStatusDeporte to set.
	 */
	public void setIdStatusDeporte(String idStatusDeporte) {
		this.idStatusDeporte = idStatusDeporte;
	}

	public String getReferenciaInicio() {
		return referenciaInicio;
	}

	public void setReferenciaInicio(String referenciaInicio) {
		this.referenciaInicio = referenciaInicio;
	}

	public String getRunlineInicio() {
		return runlineInicio;
	}

	public void setRunlineInicio(String runlineInicio) {
		this.runlineInicio = runlineInicio;
	}

	public String getCombinacion() {
		return combinacion;
	}

	public void setCombinacion(String combinacion) {
		this.combinacion = combinacion;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getRunlineLogroInicio0() {
		return runlineLogroInicio0;
	}

	public void setRunlineLogroInicio0(String runlineLogroInicio0) {
		this.runlineLogroInicio0 = runlineLogroInicio0;
	}

	public String getRunlineLogroInicio1() {
		return runlineLogroInicio1;
	}

	public void setRunlineLogroInicio1(String runlineLogroInicio1) {
		this.runlineLogroInicio1 = runlineLogroInicio1;
	}

	public String getAltabajaLogroInicio0() {
		return altabajaLogroInicio0;
	}

	public void setAltabajaLogroInicio0(String altabajaLogroInicio0) {
		this.altabajaLogroInicio0 = altabajaLogroInicio0;
	}

	public String getAltabajaLogroInicio1() {
		return altabajaLogroInicio1;
	}

	public void setAltabajaLogroInicio1(String altabajaLogroInicio1) {
		this.altabajaLogroInicio1 = altabajaLogroInicio1;
	}

	
}
