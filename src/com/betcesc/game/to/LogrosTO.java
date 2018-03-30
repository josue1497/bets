/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.LogrosIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo LogrosTO serializables.
 */

public class LogrosTO implements Serializable, LogrosIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6776994889269458695L;
	private String favorito = null;
	private String hembra = null;
	private String FRL0 = null;
	private String LRL0 = null;
	private String FRL1 = null;
	private String LRL1 = null;
	private String FSRL0 = null;
	private String LSRL0 = null;
	private String FSRL1 = null;
	private String LSRL1 = null;
	private String FAB0 = null;
	private String LAB0 = null;
	private String FAB1 = null;
	private String LAB1 = null;
	private String LAP0 = null;
	private String LAP1 = null;
	private String idDeporte = null;
	
	public String getFavorito() {
		return favorito;
	}
	public void setFavorito(String favorito) {
		this.favorito = favorito;
	}
	public String getHembra() {
		return hembra;
	}
	public void setHembra(String hembra) {
		this.hembra = hembra;
	}
	public String getFRL0() {
		return FRL0;
	}
	public void setFRL0(String fRL0) {
		FRL0 = fRL0;
	}
	public String getLRL0() {
		return LRL0;
	}
	public void setLRL0(String lRL0) {
		LRL0 = lRL0;
	}
	public String getFRL1() {
		return FRL1;
	}
	public void setFRL1(String fRL1) {
		FRL1 = fRL1;
	}
	public String getLRL1() {
		return LRL1;
	}
	public void setLRL1(String lRL1) {
		LRL1 = lRL1;
	}
	public String getFSRL0() {
		return FSRL0;
	}
	public void setFSRL0(String fSRL0) {
		FSRL0 = fSRL0;
	}
	public String getLSRL0() {
		return LSRL0;
	}
	public void setLSRL0(String lSRL0) {
		LSRL0 = lSRL0;
	}
	public String getFSRL1() {
		return FSRL1;
	}
	public void setFSRL1(String fSRL1) {
		FSRL1 = fSRL1;
	}
	public String getLSRL1() {
		return LSRL1;
	}
	public void setLSRL1(String lSRL1) {
		LSRL1 = lSRL1;
	}
	public String getFAB0() {
		return FAB0;
	}
	public void setFAB0(String fAB0) {
		FAB0 = fAB0;
	}
	public String getLAB0() {
		return LAB0;
	}
	public void setLAB0(String lAB0) {
		LAB0 = lAB0;
	}
	public String getFAB1() {
		return FAB1;
	}
	public void setFAB1(String fAB1) {
		FAB1 = fAB1;
	}
	public String getLAB1() {
		return LAB1;
	}
	public void setLAB1(String lAB1) {
		LAB1 = lAB1;
	}
	public String getLAP0() {
		return LAP0;
	}
	public void setLAP0(String lAP0) {
		LAP0 = lAP0;
	}
	public String getLAP1() {
		return LAP1;
	}
	public void setLAP1(String lAP1) {
		LAP1 = lAP1;
	}
	public String getIdDeporte() {
		return idDeporte;
	}
	public void setIdDeporte(String idDeporte) {
		this.idDeporte = idDeporte;
	}
	
}
