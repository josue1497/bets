package com.betcesc.game.form;

public class CalculadoraForm {

	private String juego = null;
	private String numero = null;
	private String codigo = null;
	private String tipo = null;
	private String cantidad = null;
	private String referencia = null;
	private String equipo = null;
	private String logro = null;
	private String padre = null;
	private String montoApostar = null;
	private String montoPremio = null;
	private String deporte = null;
	private String horaJuego = null;
	private String campeonato = null;

	public String getJuego() {
		return juego;
	}

	public void setJuego(String juego) {
		this.juego = juego;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public String getLogro() {
		return logro;
	}

	public void setLogro(String logro) {
		this.logro = logro;
	}

	public String getMontoApostar() {
		return montoApostar;
	}

	public void setMontoApostar(String montoApostar) {
		this.montoApostar = montoApostar;
	}

	public String getMontoPremio() {
		return montoPremio;
	}

	public void setMontoPremio(String montoPremio) {
		this.montoPremio = montoPremio;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPadre() {
		return padre;
	}

	public void setPadre(String padre) {
		this.padre = padre;
	}

	public String getDeporte() {
		return deporte;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}

	public String getHoraJuego() {

		return horaJuego;
	}

	public void setHoraJuego(String horaJuego) {
		this.horaJuego = horaJuego;
	}

	public String getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(String campeonato) {
		this.campeonato = campeonato;
	}

	public String toString() {
		StringBuffer cad = new StringBuffer();
		String sep = "|";
		cad.append(juego).append(sep);
		cad.append(numero).append(sep);
		cad.append(codigo).append(sep);
		cad.append(tipo).append(sep);
		cad.append(cantidad).append(sep);
		cad.append(referencia).append(sep);
		cad.append(equipo).append(sep);
		cad.append(logro).append(sep);
		cad.append(padre).append(sep);
		cad.append(montoApostar).append(sep);
		cad.append(montoPremio).append(sep);
		cad.append(deporte).append(sep);
		cad.append(horaJuego).append(sep).append(campeonato);

		return cad.toString();
	}

}
