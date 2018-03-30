package com.betcesc.game.to;

public class ListaJuegoTO {

	private String idJuego;
	private String descLiga;
	private String iniciales;
	private String idStatusJuego;
	private String idDeporte;
	private String referencia;
	private String abreviatura;
	private String descEquipo;
	private String nombreLanzador;
	private String moneyLine;
	private String idEquipo;
	private String spread;
	private String spreadLogro;
	private String superSpread;
	private String superSpreadLogro;
	private String total;
	private String totalLogro;
	private String puntos;
	private String dia;
	private String hora;
	private String statusReal;
	private String idJuegoPadre;
	private String empate;
	private String dominioActual;
	private String usuarioTotaliza;
	
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	public String getDescEquipo() {
		return descEquipo;
	}
	public void setDescEquipo(String descEquipo) {
		this.descEquipo = descEquipo;
	}
	public String getDescLiga() {
		return descLiga;
	}
	public void setDescLiga(String descLiga) {
		this.descLiga = descLiga;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getIdDeporte() {
		return idDeporte;
	}
	public void setIdDeporte(String idDeporte) {
		this.idDeporte = idDeporte;
	}
	public String getIdEquipo() {
		return idEquipo;
	}
	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
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
	public String getIniciales() {
		return iniciales;
	}
	public void setIniciales(String iniciales) {
		this.iniciales = iniciales;
	}
	public String getMoneyLine() {
		return moneyLine;
	}
	public void setMoneyLine(String moneyLine) {
		this.moneyLine = moneyLine;
	}
	public String getNombreLanzador() {
		return nombreLanzador;
	}
	public void setNombreLanzador(String nombreLanzador) {
		this.nombreLanzador = nombreLanzador;
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
	public String getStatusReal() {
		return statusReal;
	}
	public void setStatusReal(String statusReal) {
		this.statusReal = statusReal;
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
	public String getIdJuegoPadre() {
		return idJuegoPadre;
	}
	public void setIdJuegoPadre(String idJuegoPadre) {
		this.idJuegoPadre = idJuegoPadre;
	}
	public String getEmpate() {
		return empate;
	}
	public void setEmpate(String empate) {
		this.empate = empate;
	}
	public String getSuperSpread() {
		return superSpread==null?"0":superSpread;
	}
	public void setSuperSpread(String superSpread) {
		this.superSpread = superSpread;
	}
	public String getSuperSpreadLogro() {
		return superSpreadLogro==null?"0":superSpreadLogro;
	}
	public void setSuperSpreadLogro(String superSpreadLogro) {
		this.superSpreadLogro = superSpreadLogro;
	}
	public String getDominioActual() {
		return dominioActual;
	}
	public void setDominioActual(String dominioActual) {
		this.dominioActual = dominioActual;
	}
	public String getUsuarioTotaliza() {
		return usuarioTotaliza;
	}
	public void setUsuarioTotaliza(String usuarioTotaliza) {
		this.usuarioTotaliza = usuarioTotaliza;
	}
	
}
