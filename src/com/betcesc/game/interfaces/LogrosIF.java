/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.interfaces;


/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero 
 * 
 * Permite la creacion de objetos de tipo LanzadorTO serializables.
 */

public interface LogrosIF 
{

	public String getFavorito();
	public void setFavorito(String favorito);
	
	public String getHembra();
	public void setHembra(String hembra);
	
	public String getFRL0();
	public void setFRL0(String fRL0);
	public String getLRL0();
	public void setLRL0(String lRL0);
	public String getFRL1();
	public void setFRL1(String fRL1);
	public String getLRL1();
	public void setLRL1(String lRL1);
	public String getFSRL0();
	public void setFSRL0(String fSRL0);
	public String getLSRL0();
	public void setLSRL0(String lSRL0);
	public String getFSRL1();
	public void setFSRL1(String fSRL1);
	public String getLSRL1();
	public void setLSRL1(String lSRL1);
	public String getFAB0();
	public void setFAB0(String fAB0);
	public String getLAB0();
	public void setLAB0(String lAB0);
	public String getFAB1();
	public void setFAB1(String fAB1);
	public String getLAB1();
	public void setLAB1(String lAB1);
	public String getLAP0();
	public void setLAP0(String lAP0);
	public String getLAP1();
	public void setLAP1(String lAP1);
	
	public String getIdDeporte();
	public void setIdDeporte(String idDeporte);
}
