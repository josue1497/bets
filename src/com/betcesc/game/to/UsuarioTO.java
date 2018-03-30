/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import com.betcesc.game.dao.UsuarioDAO;
import com.betcesc.game.interfaces.UsuarioIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero 
 * 
 * Permite la creacion de objetos de tipo UsuarioTO serializables.
 */

public class UsuarioTO implements Serializable, UsuarioIF 
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8379305860110267093L;
	private String idUsuario = null;
    private String cedula = null;
    private String apellido = null;
    private String nombre = null;
    private String usuario = null;
    private String clave = null;
    private String correo = null;
    private String diasVencTicket = null;
    private String centroHipico = null;
    private String rif = null;
    private String telefono = null;
    private String celular = null;
    private String banco = null;
    private String numeroCuenta = null;
    private String titularCuenta = null;
    private String logrosAltaBaja = null;
    private String logrosCalc = null;
    private String logrosMin = null;
    private String monto = null;
    private String idRol = null;
    private String idStatus = null;
    private String idTipoCuenta = null;
    private String idSupervisor = null;
    private String ticketNota = null;
    private String comisionVenta = null;
    private String otrosGastos = null;
    private String comisionVentaParley = null;
    private String otrosGastosParley = null;
    private String tipo = null;
    private String macAddress = null;
    private String validarMacAddress = null;
    private String activarSuperRunline = null;
    private String topePorDerecho = null;
    private String activarBono = null;
    private String apuestaEquipo = null;
    private String topePorCombinacion = null;
    private String eliminarJugada = null;
    private String pagoVeces = null;
    private String dominio = null;
    private String pagoClave = null;
    private String abrirJuego = null;
    private String vencidoReporte = null;
	private int jugadaMinima = 0;
    
    /* attibuto del sistema */
    private long time = 0;
    private HttpSession session = null;
    
    private UsuarioIF supervisor = null;
    
    
    /* DEVUELVE EL SALDO*/
    public static double getSaldo(String idUsuario) {
    	UsuarioDAO oUsuarioDAO = new UsuarioDAO();
    	UsuarioIF oUsuarioTO = new UsuarioTO();
    	oUsuarioTO.setIdUsuario(idUsuario);
    	try {
			return Double.parseDouble(oUsuarioDAO.saldoJugadorDAO(oUsuarioTO, false));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return 0;
    }
    
    /* GET AND SET */
    /**
     * @return Returns the idUsuario.
     */
    public String getIdUsuario()
    {
        return idUsuario;
    }

    /**
     * @param idUsuario
     *            The idUsuario to set.
     */
    public void setIdUsuario(String idUsuario)
    {
        this.idUsuario = idUsuario;
    }

    
    /**
     * @return Returns the cedula.
     */
    public String getCedula()
    {
        return cedula;
    }

    /**
     * @param cedula
     *            The cedula to set.
     */
    public void setCedula(String cedula)
    {
        this.cedula = cedula;
    }

    
    /**
     * @return Returns the apellido.
     */
    public String getApellido()
    {
        return apellido;
    }

    /**
     * @param apellido
     *            The apellido to set.
     */
    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    
    /**
     * @return Returns the nombre.
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * @param nombre
     *            The nombre to set.
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    
    /**
     * @return Returns the usuario.
     */
    public String getUsuario()
    {
        return usuario;
    }

    /**
     * @param usuario
     *            The usuario to set.
     */
    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    
    /**
     * @return Returns the clave.
     */
    public String getClave()
    {
        return clave;
    }

    /**
     * @param clave
     *            The clave to set.
     */
    public void setClave(String clave)
    {
        this.clave = clave;
    }

    
    /**
     * @return Returns the correo.
     */
    public String getCorreo()
    {
        return correo;
    }

    /**
     * @param correo
     *            The correo to set.
     */
    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    
    /**
     * @return Returns the diasVencTicket.
     */
    public String getDiasVencTicket()
    {
        return diasVencTicket;
    }

    /**
     * @param diasVencTicket
     *            The diasVencTicket to set.
     */
    public void setDiasVencTicket(String diasVencTicket)
    {
        this.diasVencTicket = diasVencTicket;
    }

    
    /**
     * @return Returns the centroHipico.
     */
    public String getCentroHipico()
    {
        return centroHipico;
    }

    /**
     * @param centroHipico
     *            The centroHipico to set.
     */
    public void setCentroHipico(String centroHipico)
    {
        this.centroHipico = centroHipico;
    }

    
    /**
     * @return Returns the rif.
     */
    public String getRif()
    {
        return rif;
    }

    /**
     * @param rif
     *            The rif to set.
     */
    public void setRif(String rif)
    {
        this.rif = rif;
    }

    
    /**
     * @return Returns the telefono.
     */
    public String getTelefono()
    {
        return telefono;
    }

    /**
     * @param telefono
     *            The telefono to set.
     */
    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    
    /**
     * @return Returns the celular.
     */
    public String getCelular()
    {
        return celular;
    }

    /**
     * @param celular
     *            The celular to set.
     */
    public void setCelular(String celular)
    {
        this.celular = celular;
    }

    
    /**
     * @return Returns the banco.
     */
    public String getBanco()
    {
        return banco;
    }

    /**
     * @param banco
     *            The banco to set.
     */
    public void setBanco(String banco)
    {
        this.banco = banco;
    }

    
    /**
     * @return Returns the numeroCuenta.
     */
    public String getNumeroCuenta()
    {
        return numeroCuenta;
    }

    /**
     * @param numeroCuenta
     *            The numeroCuenta to set.
     */
    public void setNumeroCuenta(String numeroCuenta)
    {
        this.numeroCuenta = numeroCuenta;
    }

    
    /**
     * @return Returns the titularCuenta.
     */
    public String getTitularCuenta()
    {
        return titularCuenta;
    }

    /**
     * @param titularCuenta
     *            The titularCuenta to set.
     */
    public void setTitularCuenta(String titularCuenta)
    {
        this.titularCuenta = titularCuenta;
    }

    
    /**
     * @return Returns the logrosAltaBaja.
     */
    public String getLogrosAltaBaja()
    {
        return logrosAltaBaja;
    }

    /**
     * @param logrosAltaBaja
     *            The logrosAltaBaja to set.
     */
    public void setLogrosAltaBaja(String logrosAltaBaja)
    {
        this.logrosAltaBaja = logrosAltaBaja;
    }

    
    /**
     * @return Returns the monto.
     */
    public String getMonto()
    {
        return monto;
    }

    /**
     * @param monto
     *            The monto to set.
     */
    public void setMonto(String monto)
    {
        this.monto = monto;
    }

    
    /**
     * @return Returns the idRol.
     */
    public String getIdRol()
    {
        return idRol;
    }

    /**
     * @param idRol
     *            The idRol to set.
     */
    public void setIdRol(String idRol)
    {
        this.idRol = idRol;
    }

    
    /**
     * @return Returns the idStatus.
     */
    public String getIdStatus()
    {
        return idStatus;
    }

    /**
     * @param idStatus
     *            The idStatus to set.
     */
    public void setIdStatus(String idStatus)
    {
        this.idStatus = idStatus;
    }

    
    /**
     * @return Returns the idTipoCuenta.
     */
    public String getIdTipoCuenta()
    {
        return idTipoCuenta;
    }

    /**
     * @param idTipoCuenta
     *            The idTipoCuenta to set.
     */
    public void setIdTipoCuenta(String idTipoCuenta)
    {
        this.idTipoCuenta = idTipoCuenta;
    }

    
    /**
     * @return Returns the idSupervisor.
     */
    public String getIdSupervisor()
    {
        return idSupervisor;
    }

    /**
     * @param idSupervisor
     *            The idSupervisor to set.
     */
    public void setIdSupervisor(String idSupervisor)
    {
        this.idSupervisor = idSupervisor;
    }

	public long getTime() { 
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public UsuarioIF getSupervisor() {
		UsuarioDAO oUsuarioDAO = new UsuarioDAO();
		try {
			supervisor = new UsuarioTO();
			supervisor.setIdUsuario(this.getIdSupervisor());
			oUsuarioDAO.cargarUsuarioDAO(supervisor);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return supervisor;
	}

	public void setSupervisor(UsuarioIF supervisor) {
		this.supervisor = supervisor;
	}

	public String getLogrosCalc() {
		return logrosCalc;
	}

	public void setLogrosCalc(String logrosCalc) {
		this.logrosCalc = logrosCalc;
	}

	public String getTicketNota() {
		return ticketNota;
	}

	public void setTicketNota(String ticketNota) {
		this.ticketNota = ticketNota;
	}

	public String getComisionVenta() {
		return comisionVenta;
	}

	public void setComisionVenta(String comisionVenta) {
		this.comisionVenta = comisionVenta;
	}

	public String getOtrosGastos() {
		return otrosGastos;
	}

	public void setOtrosGastos(String otrosGastos) {
		this.otrosGastos = otrosGastos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getValidarMacAddress() {
		return validarMacAddress;
	}

	public void setValidarMacAddress(String validarMacAddress) {
		this.validarMacAddress = validarMacAddress;
	}

	public String getLogrosMin() {
		return logrosMin;
	}

	public void setLogrosMin(String logrosMin) {
		this.logrosMin = logrosMin;
	}

	public String getActivarSuperRunline() {
		return activarSuperRunline;
	}

	public void setActivarSuperRunline(String activarSuperRunline) {
		this.activarSuperRunline = activarSuperRunline;
	}

	public String getTopePorDerecho() {
		return topePorDerecho;
	}

	public void setTopePorDerecho(String topePorDerecho) {
		this.topePorDerecho = topePorDerecho;
	}

	public String getActivarBono() {
		return activarBono;
	}

	public void setActivarBono(String activarBono) {
		this.activarBono = activarBono;
	}

	public String getApuestaEquipo() {
		return apuestaEquipo;
	}

	public void setApuestaEquipo(String apuestaEquipo) {
		this.apuestaEquipo = apuestaEquipo;
	}

	public String getComisionVentaParley() {
		return comisionVentaParley;
	}

	public void setComisionVentaParley(String comisionVentaParley) {
		this.comisionVentaParley = comisionVentaParley;
	}

	public String getOtrosGastosParley() {
		return otrosGastosParley;
	}

	public void setOtrosGastosParley(String otrosGastosParley) {
		this.otrosGastosParley = otrosGastosParley;
	}

	public String getTopePorCombinacion() {
		return topePorCombinacion;
	}

	public void setTopePorCombinacion(String topePorCombinacion) {
		this.topePorCombinacion = topePorCombinacion;
	}

	public String getEliminarJugada() {
		return eliminarJugada;
	}

	public void setEliminarJugada(String eliminarJugada) {
		this.eliminarJugada = eliminarJugada;
	}

	public String getPagoVeces() {
		return pagoVeces;
	}

	public void setPagoVeces(String pagoVeces) {
		this.pagoVeces = pagoVeces;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getPagoClave() {
		return pagoClave;
	}

	public void setPagoClave(String pagoClave) {
		this.pagoClave = pagoClave;
	}

	public String getAbrirJuego() {
		return abrirJuego;
	}

	public void setAbrirJuego(String abrirJuego) {
		this.abrirJuego = abrirJuego;
	}

	public String getVencidoReporte() {
		return vencidoReporte;
	}

	public void setVencidoReporte(String vencidoReporte) {
		this.vencidoReporte = vencidoReporte;
	}

	public void setJugadaMinima(int paramMonto)
	    {
		this.jugadaMinima = paramMonto;

	    }

	public int getJugadaMinima()
	    {
		// TODO Auto-generated method stub
		return this.jugadaMinima;
	    }

	
}
