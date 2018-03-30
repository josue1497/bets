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
 * Permite la creacion de objetos de tipo DeporteTO serializables.
 */

public interface DeporteIF { 

	/**
	 * @return Returns the idDeporte.
	 */
	public String getIdDeporte();

	/**
	 * @param idDeporte
	 *            The idDeporte to set.
	 */
	public void setIdDeporte(String idDeporte);

	/**
	 * @return Returns the descDeporte.
	 */
	public String getDescDeporte();

	/**
	 * @param descDeporte
	 *            The descDeporte to set.
	 */
	public void setDescDeporte(String descDeporte);

	/**
	 * @return Returns the empate.
	 */
	public String getEmpate();

	/**
	 * @param empate
	 *            The empate to set.
	 */
	public void setEmpate(String empate);

	/**
	 * @return Returns the idStatusDeporte.
	 */
	public String getIdStatusDeporte();

	/**
	 * @param idStatusDeporte
	 *            The idStatusDeporte to set.
	 */
	public void setIdStatusDeporte(String idStatusDeporte);

	public String getReferenciaInicio();

	public void setReferenciaInicio(String referenciaInicio);
	
	public String getRunlineInicio();

	public void setRunlineInicio(String runlineInicio);

	public String getCombinacion();

	public void setCombinacion(String combinacion);
	
	public String getItems();

	public void setItems(String items);

	public String getRunlineLogroInicio0();
	
	public void setRunlineLogroInicio0(String runlineLogroInicio0);

	public String getRunlineLogroInicio1();

	public void setRunlineLogroInicio1(String runlineLogroInicio1);

	public String getAltabajaLogroInicio0();

	public void setAltabajaLogroInicio0(String altabajaLogroInicio0);

	public String getAltabajaLogroInicio1();

	public void setAltabajaLogroInicio1(String altabajaLogroInicio1);
}
