package com.betcesc.game.ctrl.back;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.betcesc.game.common.Constants;
import com.betcesc.game.interfaces.DeporteIF;
import com.betcesc.game.interfaces.JuegoEquipoIF;
import com.betcesc.game.interfaces.JuegoEquipoLanzadorIF;
import com.betcesc.game.interfaces.JuegoIF;
import com.betcesc.game.interfaces.StatusJuegoIF;
import com.betcesc.game.interfaces.UsuarioJuegoEquipoIF;

public class RegisterGameActionForm extends ActionForm implements JuegoIF, JuegoEquipoIF, UsuarioJuegoEquipoIF, JuegoEquipoLanzadorIF, StatusJuegoIF, DeporteIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7055568574493162679L;

	private static final Log log = LogFactory.getLog(RegisterGameActionForm.class);

	/* JuegoTO */
	private String idJuego = null;
    private String fechaSis = null;
    private String fechaIni = null;
    private String fechaFin = null;
    private String minutosCierre = null;
    private String idCampeonato = null;
    private String idStatusJuego = null;
    private String idUsuario = null;
    private String idDeporte = null;
    private String idLiga = null;
    private String spreadActivo = null;
    private String superSpreadActivo = null;
    private String totalActivo = null;
    private String moneyActivo = null;
	private String idJuegoPadre = null;
    private String idUsuarioCreador = null;
    private String dominioActual = null;
    private String dominioAnterior = null;
    private String idUsuarioTotaliza = null;

    /* JuegoEquipoTO */
	private String idJuegoEquipo = null;
    private String idEquipo = null;
    private String ganador = null;
    private String puntos = null;
    private String referencia = null;
    private String referenciaRunline = null;
    private String referenciaSuperRunline = null;
    private String referenciaAB = null;
    private String[] _Ganador = null;
    private String[] _Puntos = null;
    
    /* DeporteTO */
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

    /* UsuarioJuegoEquipoTO */
	private String idUsuarioJuegoEquipo = null;
    private String spread = null;
    private String spreadLogro = null;
    private String superSpread = null;
    private String superSpreadLogro = null;
    private String total = null;
    private String totalLogro = null;
    private String moneyLine = null;
    private String desactivado = null;

    /* JuegoEquipoLanzadorTO */
	private String idLanzador = null;

	/* Vectores para la forma */
	private String[] _IdUsuario = null;
	private String[] _IdUsuarioJuegoEquipo = null;
	private String[] _IdJuegoEquipo = null;
	private String[] _Referencia = null;
	private String[] _ReferenciaRunline = null;
	private String[] _ReferenciaSuperRunline = null;
	private String[] _ReferenciaAB = null;
	private String[] _IdEquipo = null;
	private String[] _Abreviatura = null;
	private String[] _DescEquipo = null;
	private String[] _IdLanzador = null;
	private String[] _NombreLanzador = null;
    private String[] _Spread = null;
    private String[] _SpreadDecimal = null;
    private String[] _SpreadLogro = null;
    private String[] _SuperSpread = null;
    private String[] _SuperSpreadDecimal = null;
    private String[] _SuperSpreadLogro = null;
    private String[] _Total = null;
    private String[] _TotalDecimal = null;
    private String[] _TotalLogro = null;
    private String[] _MoneyLine = null;

	private String hora = null;
	private String minuto = null;
	private String nombre = null;

	private String descStatusJuego = null;
	private String descLiga = null;
	private String iniciales = null;
	
	private boolean primero = false;
	private boolean ultimo = false;
	
	public RegisterGameActionForm() {
		super();
		_IdUsuario = iniciar(_IdUsuario,"0");
		_IdUsuarioJuegoEquipo = iniciar(_IdUsuarioJuegoEquipo,"0");
		_IdJuegoEquipo = iniciar(_IdJuegoEquipo,"0");
		_Referencia = iniciar(_Referencia,"0");
		_ReferenciaRunline = iniciar(_ReferenciaRunline,"0");
		_ReferenciaSuperRunline = iniciar(_ReferenciaSuperRunline,"0");
		_ReferenciaAB = iniciar(_ReferenciaAB,"0");
		_IdEquipo = iniciar(_IdEquipo,"0");
		_Abreviatura = iniciar(_Abreviatura,"");
		_DescEquipo = iniciar(_DescEquipo,"");
		_IdLanzador = iniciar(_IdLanzador,"0");
		_NombreLanzador = iniciar(_NombreLanzador,"0");
		_Spread = iniciar(_Spread,"0");
		_SpreadDecimal = iniciar(_SpreadDecimal,"0");
		_SpreadLogro = iniciar(_SpreadLogro,"0");
		_SuperSpread = iniciar(_SuperSpread,"0");
		_SuperSpreadDecimal = iniciar(_SuperSpreadDecimal,"0");
		_SuperSpreadLogro = iniciar(_SuperSpreadLogro,"0");
		_Total = iniciar(_Total,"0");
		_TotalDecimal = iniciar(_TotalDecimal,"0");
		_TotalLogro = iniciar(_TotalLogro,"0");
		_MoneyLine = iniciar(_MoneyLine,"0");

	    _Ganador = iniciar(_Ganador,"0");
	    _Puntos = iniciar(_Puntos,"0");
	}
	private String[] iniciar(String[] arr,String valor) {
		arr = new String[Constants.CANTIDAD_EQUIPOS];
		for(int i=0; i < Constants.CANTIDAD_EQUIPOS; i++) {
			arr[i]=valor;
		}
		return arr;
	}
    
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		log.info("Iniciando reset()");
		idJuego = null;
	    fechaSis = null;
	    fechaIni = null;
	    fechaFin = null;
	    minutosCierre = null;
	    idCampeonato = null;
	    idStatusJuego = null;
	    idUsuario = null;
	    idDeporte = null;
	    idLiga = null;
	    spreadActivo = null;
	    superSpreadActivo = null;
	    totalActivo = null;
	    moneyActivo = null;

		idJuegoEquipo = null;
	    idEquipo = null;
	    ganador = null;

		idUsuarioJuegoEquipo = null;
	    spread = null;
	    spreadLogro = null;
	    superSpread = null;
	    superSpreadLogro = null;
	    total = null;
	    totalLogro = null;
	    moneyLine = null;
	    
	    idLanzador = null;
	    
	    _IdUsuario = iniciar(_IdUsuario,"0");
	    _IdUsuarioJuegoEquipo = iniciar(_IdUsuarioJuegoEquipo,"0");
	    _IdJuegoEquipo = iniciar(_IdJuegoEquipo,"0");
	    _Referencia = iniciar(_Referencia,"0");
	    _ReferenciaRunline = iniciar(_ReferenciaRunline,"0");
	    _ReferenciaSuperRunline = iniciar(_ReferenciaSuperRunline,"0");
	    _ReferenciaAB = iniciar(_ReferenciaAB,"0");
	    _IdEquipo = iniciar(_IdEquipo,"0");
	    _Abreviatura = iniciar(_Abreviatura,"");
	    _IdLanzador = iniciar(_IdLanzador,"0");
	    _Spread = iniciar(_Spread,"0");
	    _SpreadDecimal = iniciar(_SpreadDecimal,"0");
	    _SpreadLogro = iniciar(_SpreadLogro,"0");
	    _SuperSpread = iniciar(_SuperSpread,"0");
	    _SuperSpreadDecimal = iniciar(_SuperSpreadDecimal,"0");
	    _SuperSpreadLogro = iniciar(_SuperSpreadLogro,"0");
	    _Total = iniciar(_Total,"0");
	    _TotalDecimal = iniciar(_TotalDecimal,"0");
	    _TotalLogro = iniciar(_TotalLogro,"0");
	    _MoneyLine = iniciar(_MoneyLine,"0");

	    _Ganador = iniciar(_Ganador,"0");
	    _Puntos = iniciar(_Puntos,"0");
	    
		hora = null;
		minuto = null;
		nombre = null;
	}

	public ActionErrors validate(ActionMapping arg0, HttpServletRequest request) {
		log.info("Iniciando validate()");

		ActionErrors errores = new ActionErrors();
		
		// ajustaremos algunos valores por defecto
		spreadActivo = Constants.isNull(spreadActivo, "0"); 
		totalActivo = Constants.isNull(totalActivo, "0"); 
		moneyActivo = Constants.isNull(moneyActivo, "0");
		superSpreadActivo = Constants.isNull(superSpreadActivo, "0"); 

		

		return errores;
	}


	
	/* GET and SET */
	
	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}

	public String getFechaSis() {
		return fechaSis;
	}

	public void setFechaSis(String fechaSis) {
		this.fechaSis = fechaSis;
	}

	public String getIdCampeonato() {
		return idCampeonato;
	}

	public void setIdCampeonato(String idCampeonato) {
		this.idCampeonato = idCampeonato;
	}

	public String getIdDeporte() {
		return idDeporte;
	}

	public void setIdDeporte(String idDeporte) {
		this.idDeporte = idDeporte;
	}

	public String getIdJuego() {
		return idJuego;
	}

	public void setIdJuego(String idJuego) {
		this.idJuego = idJuego;
	}

	public String getIdStatusJuego() {
		return idStatusJuego;
	}

	public void setIdStatusJuego(String idStatusJuego) {
		this.idStatusJuego = idStatusJuego;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getMinutosCierre() {
		return minutosCierre;
	}

	public void setMinutosCierre(String minutosCierre) {
		this.minutosCierre = minutosCierre;
	}

	public String getGanador() {
		return ganador;
	}

	public void setGanador(String ganador) {
		this.ganador = ganador;
	}

	public String getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getIdJuegoEquipo() {
		return idJuegoEquipo;
	}

	public void setIdJuegoEquipo(String idJuegoEquipo) {
		this.idJuegoEquipo = idJuegoEquipo;
	}

	public String getIdUsuarioJuegoEquipo() {
		return idUsuarioJuegoEquipo;
	}

	public void setIdUsuarioJuegoEquipo(String idUsuarioJuegoEquipo) {
		this.idUsuarioJuegoEquipo = idUsuarioJuegoEquipo;
	}

	public String getMoneyLine() {
		return moneyLine;
	}

	public void setMoneyLine(String moneyLine) {
		this.moneyLine = moneyLine;
	}

	public String getSpread() {
		return spread;
	}

	public void setSpread(String spread) {
		this.spread = spread;
	}

	public String getSpreadLogro() {
		return spreadLogro;
	}

	public void setSpreadLogro(String spreadLogro) {
		this.spreadLogro = spreadLogro;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getTotalLogro() {
		return totalLogro;
	}

	public void setTotalLogro(String totalLogro) {
		this.totalLogro = totalLogro;
	}

	public String getIdLanzador() {
		return idLanzador;
	}

	public void setIdLanzador(String idLanzador) {
		this.idLanzador = idLanzador;
	}

	public String[] get_IdEquipo() {
		return _IdEquipo;
	}

	public void set_IdEquipo(String[] idEquipo) {
		_IdEquipo = idEquipo;
	}

	public String[] get_IdLanzador() {
		return _IdLanzador;
	}

	public void set_IdLanzador(String[] idLanzador) {
		_IdLanzador = idLanzador;
	}

	public String[] get_MoneyLine() {
		return _MoneyLine;
	}

	public void set_MoneyLine(String[] moneyLine) {
		_MoneyLine = moneyLine;
	}

	public String[] get_Spread() {
		return _Spread;
	}

	public void set_Spread(String[] spread) {
		_Spread = spread;
	}

	public String[] get_SpreadLogro() {
		return _SpreadLogro;
	}

	public void set_SpreadLogro(String[] spreadLogro) {
		_SpreadLogro = spreadLogro;
	}

	public String[] get_Total() {
		return _Total;
	}

	public void set_Total(String[] total) {
		_Total = total;
	}

	public String[] get_TotalLogro() {
		return _TotalLogro;
	}

	public void set_TotalLogro(String[] totalLogro) {
		_TotalLogro = totalLogro;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getMinuto() {
		return minuto;
	}

	public void setMinuto(String minuto) {
		this.minuto = minuto;
	}

	public String[] get_SpreadDecimal() {
		return _SpreadDecimal;
	}

	public void set_SpreadDecimal(String[] spreadDecimal) {
		_SpreadDecimal = spreadDecimal;
	}

	public String[] get_TotalDecimal() {
		return _TotalDecimal;
	}

	public void set_TotalDecimal(String[] totalDecimal) {
		_TotalDecimal = totalDecimal;
	}

	public String getIdLiga() {
		return idLiga;
	}

	public void setIdLiga(String idLiga) {
		this.idLiga = idLiga;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescStatusJuego() {
		return descStatusJuego;
	}

	public void setDescStatusJuego(String descStatusJuego) {
		this.descStatusJuego = descStatusJuego;
	}

	public String[] get_IdJuegoEquipo() {
		return _IdJuegoEquipo;
	}

	public void set_IdJuegoEquipo(String[] idJuegoEquipo) {
		_IdJuegoEquipo = idJuegoEquipo;
	}
	
	public String[] get_Referencia() {
		return _Referencia;
	}

	public void set_Referencia(String[] referencia) {
		_Referencia = referencia;
	}

	public String getDescLiga() {
		return descLiga;
	}

	public void setDescLiga(String descLiga) {
		this.descLiga = descLiga;
	}

	public String getIniciales() {
		return iniciales;
	}

	public void setIniciales(String iniciales) {
		this.iniciales = iniciales;
	}

	public String[] get_DescEquipo() {
		return _DescEquipo;
	}

	public void set_DescEquipo(String[] equipo) {
		_DescEquipo = equipo;
	}

	public boolean isUltimo() {
		return ultimo;
	}

	public void setUltimo(boolean ultimo) {
		this.ultimo = ultimo;
	}

	public boolean isPrimero() {
		return primero;
	}

	public void setPrimero(boolean primero) {
		this.primero = primero;
	}

	public String[] get_NombreLanzador() {
		return _NombreLanzador;
	}

	public void set_NombreLanzador(String[] nombreLanzador) {
		_NombreLanzador = nombreLanzador;
	}

	public String getPuntos() {
		return puntos;
	}

	public void setPuntos(String puntos) {
		this.puntos = puntos;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String[] get_IdUsuarioJuegoEquipo() {
		return _IdUsuarioJuegoEquipo;
	}

	public void set_IdUsuarioJuegoEquipo(String[] idUsuarioJuegoEquipo) {
		_IdUsuarioJuegoEquipo = idUsuarioJuegoEquipo;
	}

	public String[] get_IdUsuario() {
		return _IdUsuario;
	}

	public void set_IdUsuario(String[] idUsuario) {
		_IdUsuario = idUsuario;
	}

	public String[] get_Ganador() {
		return _Ganador;
	}

	public void set_Ganador(String[] ganador) {
		_Ganador = ganador;
	}

	public String[] get_Puntos() {
		return _Puntos;
	}

	public void set_Puntos(String[] puntos) {
		_Puntos = puntos;
	}

	public String[] get_Abreviatura() {
		return _Abreviatura;
	}

	public void set_Abreviatura(String[] abreviatura) {
		_Abreviatura = abreviatura;
	}

	public String getMoneyActivo() {
		return moneyActivo;
	}

	public void setMoneyActivo(String moneyActivo) {
		this.moneyActivo = moneyActivo;
	}

	public String getSpreadActivo() {
		return spreadActivo;
	}

	public void setSpreadActivo(String spreadActivo) {
		this.spreadActivo = spreadActivo;
	}

	public String getTotalActivo() {
		return totalActivo;
	}

	public void setTotalActivo(String totalActivo) {
		this.totalActivo = totalActivo;
	}

	public String getIdJuegoPadre() {
		return idJuegoPadre;
	}

	public void setIdJuegoPadre(String idJuegoPadre) {
		this.idJuegoPadre = idJuegoPadre;
	}
	
	public String getIdUsuarioCreador() {
		return idUsuarioCreador;	}

	public void setIdUsuarioCreador(String idUsuarioCreador) {
		this.idUsuarioCreador = idUsuarioCreador;
	}

	public String[] get_ReferenciaAB() {
		return _ReferenciaAB;
	}

	public void set_ReferenciaAB(String[] referenciaAB) {
		_ReferenciaAB = referenciaAB;
	}

	public String[] get_ReferenciaRunline() {
		return _ReferenciaRunline;
	}

	public void set_ReferenciaRunline(String[] referenciaRunline) {
		_ReferenciaRunline = referenciaRunline;
	}

	public String getReferenciaAB() {
		return referenciaAB;
	}

	public void setReferenciaAB(String referenciaAB) {
		this.referenciaAB = referenciaAB;
	}

	public String getReferenciaRunline() {
		return referenciaRunline;
	}

	public void setReferenciaRunline(String referenciaRunline) {
		this.referenciaRunline = referenciaRunline;
	}
	public String[] get_ReferenciaSuperRunline() {
		return _ReferenciaSuperRunline;
	}
	public void set_ReferenciaSuperRunline(String[] referenciaSuperRunline) {
		_ReferenciaSuperRunline = referenciaSuperRunline;
	}
	public String[] get_SuperSpread() {
		return _SuperSpread;
	}
	public void set_SuperSpread(String[] superSpread) {
		_SuperSpread = superSpread;
	}
	public String[] get_SuperSpreadDecimal() {
		return _SuperSpreadDecimal;
	}
	public void set_SuperSpreadDecimal(String[] superSpreadDecimal) {
		_SuperSpreadDecimal = superSpreadDecimal;
	}
	public String[] get_SuperSpreadLogro() {
		return _SuperSpreadLogro;
	}
	public void set_SuperSpreadLogro(String[] superSpreadLogro) {
		_SuperSpreadLogro = superSpreadLogro;
	}
	public String getReferenciaSuperRunline() {
		return referenciaSuperRunline;
	}
	public void setReferenciaSuperRunline(String referenciaSuperRunline) {
		this.referenciaSuperRunline = referenciaSuperRunline;
	}
	public String getSuperSpread() {
		return superSpread;
	}
	public void setSuperSpread(String superSpread) {
		this.superSpread = superSpread;
	}
	public String getSuperSpreadActivo() {
		return superSpreadActivo;
	}
	public void setSuperSpreadActivo(String superSpreadActivo) {
		this.superSpreadActivo = superSpreadActivo;
	}
	public String getSuperSpreadLogro() {
		return superSpreadLogro;
	}
	public void setSuperSpreadLogro(String superSpreadLogro) {
		this.superSpreadLogro = superSpreadLogro;
	}
	public String getDescDeporte() {
		return descDeporte;
	}
	public void setDescDeporte(String descDeporte) {
		this.descDeporte = descDeporte;
	}
	public String getEmpate() {
		return empate;
	}
	public void setEmpate(String empate) {
		this.empate = empate;
	}
	public String getIdStatusDeporte() {
		return idStatusDeporte;
	}
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
	
	public String getDominioActual() {
		return dominioActual;
	}

	public void setDominioActual(String dominioActual) {
		this.dominioActual = dominioActual;
	}

	public String getDominioAnterior() {
		return dominioAnterior;
	}

	public void setDominioAnterior(String dominioAnterior) {
		this.dominioAnterior = dominioAnterior;
	}

	public String getDesactivado() {
		return desactivado;
	}

	public void setDesactivado(String desactivado) {
		this.desactivado = desactivado;
	}

	public String getIdUsuarioTotaliza() {
		return idUsuarioTotaliza;
	}

	public void setIdUsuarioTotaliza(String idUsuarioTotaliza) {
		this.idUsuarioTotaliza = idUsuarioTotaliza;
	}

	}
