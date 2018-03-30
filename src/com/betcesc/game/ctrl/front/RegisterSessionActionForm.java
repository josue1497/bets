package com.betcesc.game.ctrl.front;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.betcesc.game.common.Constants;
import com.betcesc.game.interfaces.UsuarioIF;

public class RegisterSessionActionForm extends ActionForm implements UsuarioIF {

	private static final Log log = LogFactory.getLog(RegisterSessionActionForm.class);

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
	
	private UsuarioIF supervisor = null;

	private long time = 0;
	private HttpSession session;

	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		log.info("Iniciando reset()");
		idUsuario = null;
		cedula = null;
		apellido = null;
		nombre = null;
		usuario = null;
		clave = null;
		correo = null;
		diasVencTicket = null;
		centroHipico = null;
		rif = null;
		telefono = null;
		celular = null;
		banco = null;
		numeroCuenta = null;
		titularCuenta = null;
		logrosAltaBaja = null;
		logrosCalc = null;
		logrosMin = null;
		monto = null;
		idRol = null;
		idStatus = null;
		idTipoCuenta = null;
		idSupervisor = null;
		supervisor = null;
	    comisionVenta = null;
	    otrosGastos = null;
	    tipo=null;
	}

	public ActionErrors validate(ActionMapping arg0, HttpServletRequest request) {
		log.info("Iniciando validate()");

		ActionErrors errores = new ActionErrors();

		if (this.getUsuario() == null || this.getUsuario().trim().equals("")) {
			errores.add("clave", new ActionError("error.field.required", "usuario"));
		}

		if (this.getClave() == null || this.getClave().trim().equals("")) {
			errores.add("clave", new ActionError("error.field.required", "clave"));
		} else if (this.getClave().length() < Integer.parseInt(Constants.PASSWORD_MIN_LENGTH)) {
			errores.add("clave", new ActionError("error.contrasena.minlength", Constants.PASSWORD_MIN_LENGTH));
		}

		return errores;
	}

	/* GET and SET */

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCentroHipico() {
		return centroHipico;
	}

	public void setCentroHipico(String centroHipico) {
		this.centroHipico = centroHipico;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDiasVencTicket() {
		return diasVencTicket;
	}

	public void setDiasVencTicket(String diasVencTicket) {
		this.diasVencTicket = diasVencTicket;
	}

	public String getIdRol() {
		return idRol;
	}

	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}

	public String getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(String idStatus) {
		this.idStatus = idStatus;
	}

	public String getIdSupervisor() {
		return idSupervisor;
	}

	public void setIdSupervisor(String idSupervisor) {
		this.idSupervisor = idSupervisor;
	}

	public String getIdTipoCuenta() {
		return idTipoCuenta;
	}

	public void setIdTipoCuenta(String idTipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogrosAltaBaja() {
		return logrosAltaBaja;
	}

	public void setLogrosAltaBaja(String logrosAltaBaja) {
		this.logrosAltaBaja = logrosAltaBaja;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getRif() {
		return rif;
	}

	public void setRif(String rif) {
		this.rif = rif;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getTitularCuenta() {
		return titularCuenta;
	}

	public void setTitularCuenta(String titularCuenta) {
		this.titularCuenta = titularCuenta;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public UsuarioIF getSupervisor() {
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

	public int getJugadaMinima()
	    {
		return jugadaMinima;
	    }

	public void setJugadaMinima(int paramJugadaMinima)
	    {
		jugadaMinima = paramJugadaMinima;
	    }
}
