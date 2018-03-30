/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.interfaces;

import javax.servlet.http.HttpSession;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo UsuarioTO serializables.
 */

public interface UsuarioIF {

	/**
	 * @return Returns the idUsuario.
	 */
	public String getIdUsuario();

	/**
	 * @param idUsuario
	 *            The idUsuario to set.
	 */
	public void setIdUsuario(String idUsuario);

	/**
	 * @return Returns the cedula.
	 */
	public String getCedula();

	/**
	 * @param cedula
	 *            The cedula to set.
	 */
	public void setCedula(String cedula);

	/**
	 * @return Returns the apellido.
	 */
	public String getApellido();

	/**
	 * @param apellido
	 *            The apellido to set.
	 */
	public void setApellido(String apellido);

	/**
	 * @return Returns the nombre.
	 */
	public String getNombre();

	/**
	 * @param nombre
	 *            The nombre to set.
	 */
	public void setNombre(String nombre);

	/**
	 * @return Returns the usuario.
	 */
	public String getUsuario();

	/**
	 * @param usuario
	 *            The usuario to set.
	 */
	public void setUsuario(String usuario);

	/**
	 * @return Returns the clave.
	 */
	public String getClave();

	/**
	 * @param clave
	 *            The clave to set.
	 */
	public void setClave(String clave);

	/**
	 * @return Returns the correo.
	 */
	public String getCorreo();

	/**
	 * @param correo
	 *            The correo to set.
	 */
	public void setCorreo(String correo);

	/**
	 * @return Returns the diasVencTicket.
	 */
	public String getDiasVencTicket();

	/**
	 * @param diasVencTicket
	 *            The diasVencTicket to set.
	 */
	public void setDiasVencTicket(String diasVencTicket);

	/**
	 * @return Returns the centroHipico.
	 */
	public String getCentroHipico();

	/**
	 * @param centroHipico
	 *            The centroHipico to set.
	 */
	public void setCentroHipico(String centroHipico);

	/**
	 * @return Returns the rif.
	 */
	public String getRif();

	/**
	 * @param rif
	 *            The rif to set.
	 */
	public void setRif(String rif);

	/**
	 * @return Returns the telefono.
	 */
	public String getTelefono();

	/**
	 * @param telefono
	 *            The telefono to set.
	 */
	public void setTelefono(String telefono);

	/**
	 * @return Returns the celular.
	 */
	public String getCelular();

	/**
	 * @param celular
	 *            The celular to set.
	 */
	public void setCelular(String celular);

	/**
	 * @return Returns the banco.
	 */
	public String getBanco();

	/**
	 * @param banco
	 *            The banco to set.
	 */
	public void setBanco(String banco);

	/**
	 * @return Returns the numeroCuenta.
	 */
	public String getNumeroCuenta();

	/**
	 * @param numeroCuenta
	 *            The numeroCuenta to set.
	 */
	public void setNumeroCuenta(String numeroCuenta);

	/**
	 * @return Returns the titularCuenta.
	 */
	public String getTitularCuenta();

	/**
	 * @param titularCuenta
	 *            The titularCuenta to set.
	 */
	public void setTitularCuenta(String titularCuenta);

	/**
	 * @return Returns the logrosAltaBaja.
	 */
	public String getLogrosAltaBaja();

	/**
	 * @param logrosAltaBaja
	 *            The logrosAltaBaja to set.
	 */
	public void setLogrosAltaBaja(String logrosCalc);

	/**
	 * @return Returns the logrosAltaBaja.
	 */
	public String getLogrosCalc();

	/**
	 * @param logrosAltaBaja
	 *            The logrosAltaBaja to set.
	 */
	public void setLogrosCalc(String logrosCalc);
	
	/**
	 * @return Returns the monto.
	 */
	public String getMonto();

	/**
	 * @param monto
	 *            The monto to set.
	 */
	public void setMonto(String monto);

	/**
	 * @return Returns the idRol.
	 */
	public String getIdRol();

	/**
	 * @param idRol
	 *            The idRol to set.
	 */
	public void setIdRol(String idRol);

	/**
	 * @return Returns the idStatus.
	 */
	public String getIdStatus();

	/**
	 * @param idStatus
	 *            The idStatus to set.
	 */
	public void setIdStatus(String idStatus);

	/**
	 * @return Returns the idTipoCuenta.
	 */
	public String getIdTipoCuenta();

	/**
	 * @param idTipoCuenta
	 *            The idTipoCuenta to set.
	 */
	public void setIdTipoCuenta(String idTipoCuenta);

	/**
	 * @return Returns the idSupervisor.
	 */
	public String getIdSupervisor();

	/**
	 * @param idSupervisor
	 *            The idSupervisor to set.
	 */
	public void setIdSupervisor(String idSupervisor);

	public long getTime(); 

	public void setTime(long time);
	
	public HttpSession getSession();

	public void setSession(HttpSession session);
	
	public UsuarioIF getSupervisor();

	public void setSupervisor(UsuarioIF supervisor);
	
	public String getTicketNota();
	public void setTicketNota(String ticketNota);
	
	public String getComisionVenta();

	public void setComisionVenta(String comisionVenta);

	public String getOtrosGastos();

	public void setOtrosGastos(String otrosGastos);
	
	public String getTipo();

	public void setTipo(String tipo);
	
	public String getMacAddress();
	public void setMacAddress(String macAddress);
	public String getValidarMacAddress();
	public void setValidarMacAddress(String validarMacAddress);
	public String getLogrosMin();
	public void setLogrosMin(String logrosMin);	

	public String getActivarSuperRunline();

	public void setActivarSuperRunline(String activarSuperRunline);

	public String getTopePorDerecho();

	public void setTopePorDerecho(String topePorDerecho);

	public String getActivarBono();

	public void setActivarBono(String activarBono);

	public String getApuestaEquipo();

	public void setApuestaEquipo(String apuestaEquipo);
	
	public String getComisionVentaParley();

	public void setComisionVentaParley(String comisionVentaParley);

	public String getOtrosGastosParley();

	public void setOtrosGastosParley(String otrosGastosParley);


	public String getTopePorCombinacion();

	public void setTopePorCombinacion(String topePorCombinacion);

	public String getEliminarJugada();

	public void setEliminarJugada(String eliminarJugada);

	public String getPagoVeces();

	public void setPagoVeces(String pagoVeces);

	public String getDominio();

	public void setDominio(String dominio);

	public String getPagoClave();

	public void setPagoClave(String pagoClave);
	
	public String getAbrirJuego();

	public void setAbrirJuego(String abrirJuego);
	
	public String getVencidoReporte();

	public void setVencidoReporte(String vencidoReporte);

	public int getJugadaMinima();

	public void setJugadaMinima(int monto);

}
