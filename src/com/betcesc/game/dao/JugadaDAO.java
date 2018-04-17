/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.dao;

import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.common.Constants;
import com.betcesc.game.facade.JuegoFacade;
import com.betcesc.game.interfaces.CuentaJugadorIF;
import com.betcesc.game.interfaces.JuegoEquipoIF;
import com.betcesc.game.interfaces.JugadaIF;
import com.betcesc.game.interfaces.JugadaJuegoEquipoIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.CuentaJugadorTO;
import com.betcesc.game.to.UsuarioTO;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla jugada.
 * 
 * @author jrivero
 * 
 *         Esta clase permite la actualizacion de la tabla jugada
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class JugadaDAO {
	private static final Log log = LogFactory.getLog(JugadaDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene par�metros.
	 */
	public JugadaDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	public JugadaDAO(EjecutorSql oEjecutorSql) {
		super();
		this.oEjecutorSql = oEjecutorSql;
	}

	/**
	 * 
	 * Inserta un registro en la tabla jugada
	 * 
	 */
	public int insertarJugadaDAO(JugadaIF oJugadaTO) throws Exception {
		log.info("Iniciando ejecucion de JugadaDAO.insertarJugadaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {
			// iniciar parametros
			oJugadaTO.setIdJugada(oJugadaTO.getIdJugada() == null || oJugadaTO.getIdJugada().trim().equals("") ? "0" : oJugadaTO.getIdJugada());
			oJugadaTO.setFechaSis(Constants.getFechaLargaSQL());
			oJugadaTO.setIdStatusJugada(Constants.STATUS_JUGADA_EN_JUEGO);

			oParametros.add(oJugadaTO.getIdJugada());
			oParametros.add(oJugadaTO.getFechaSis());
			oParametros.add(oJugadaTO.getFechaExp());
			oParametros.add(oJugadaTO.getMontoApostado());
			oParametros.add(oJugadaTO.getMontoPremio());
			oParametros.add(oJugadaTO.getMontoPagado());
			oParametros.add(oJugadaTO.getIdUsuario());
			oParametros.add(oJugadaTO.getIdStatusJugada());
			oParametros.add(oJugadaTO.getDiasExpira());
			oParametros.add(oJugadaTO.getFechaPago());
			oParametros.add(oJugadaTO.getDetalleEquipo());
			oParametros.add(oJugadaTO.getCancelada());
			oParametros.add(oJugadaTO.getItemsJugada());
			oParametros.add(Constants.parseInt(oJugadaTO.getBono()));

			strBuffquery.append("INSERT INTO jugada VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,0)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

			oJugadaTO.setIdJugada(String.valueOf(oEjecutorSql.getGeneratedKey()));
			log.debug("CORRELATIVO TABLA [jugada]:" + oJugadaTO.getIdJugada());
		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaDAO.insertarJugadaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla jugada
	 * 
	 */
	public int actualizarJugadaDAO(JugadaIF oJugadaTO) throws Exception {
		log.info("Iniciando ejecucion de JugadaDAO.actualizarJugadaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oJugadaTO.getFechaExp());
			oParametros.add(oJugadaTO.getMontoApostado());
			oParametros.add(oJugadaTO.getMontoPremio());
			oParametros.add(oJugadaTO.getMontoPagado());
			oParametros.add(oJugadaTO.getIdUsuario());
			oParametros.add(Constants.STATUS_JUGADA_ELIMINADA);
			oParametros.add(oJugadaTO.getIdStatusJugada());
			oParametros.add(oJugadaTO.getDiasExpira());
			oParametros.add(Constants.STATUS_JUGADA_PAGADA);
			oParametros.add(oJugadaTO.getFechaPago());
			oParametros.add(oJugadaTO.getDetalleEquipo());
			oParametros.add(oJugadaTO.getCancelada());
			oParametros.add(oJugadaTO.getItemsJugada());
			oParametros.add(Constants.parseInt(oJugadaTO.getBono()));
			oParametros.add(oJugadaTO.getIdJugada()); // primary key

			strBuffquery.append("UPDATE jugada SET ");
			strBuffquery.append("fecha_exp=?, monto_apostado=?, ");
			strBuffquery.append("monto_premio=? , monto_pagado=?, id_usuario=?, ");
			strBuffquery.append("id_status_jugada=if(id_status_jugada=?,id_status_jugada,?), dias_expira=?, ");
			strBuffquery.append("fecha_pago=if(id_status_jugada!=?,null,?), detalle_equipo=?, cancelada=?, items_jugada=?, bono=? ");
			strBuffquery.append("WHERE  id_jugada = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaDAO.actualizarJugadaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	public int actualizarFechaVencimientoJugadaDAO(JugadaIF oJugadaTO) throws Exception {
		log.info("Iniciando ejecucion de actualizarFechaVencimientoJugadaDAO");
		ArrayList oParametros = new ArrayList();

		int numRegAct = 0;

		try {
			oParametros.add(oJugadaTO.getIdJugada()); // primary key

			strBuffquery.setLength(0);
			strBuffquery.append("UPDATE jugada SET dias_expira=if(dias_expira=0,3,dias_expira), fecha_exp=DATE_FORMAT(ADDDATE(fecha_sis,if(dias_expira=0,3,dias_expira)),'%Y-%m-%d 23:59:59') WHERE  id_jugada = ? ");
			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de actualizarFechaVencimientoJugadaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla jugada
	 * 
	 */
	public int actualizarStatusPagadoJugadaDAO(JugadaIF oJugadaTO) throws Exception {
		log.info("Iniciando ejecucion de actualizarStatusPagadoJugadaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {
			oParametros.add(oJugadaTO.getIdStatusJugada());
			oParametros.add(oJugadaTO.getIdJugada()); // primary key

			strBuffquery.setLength(0);
			strBuffquery.append("UPDATE jugada SET id_status_jugada=?, fecha_pago=now() ");
			strBuffquery.append("WHERE  id_jugada = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de actualizarStatusPagadoJugadaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla jugada
	 * 
	 */
	public int totalizarJugadaDAO(JugadaIF oJugadaTO, TreeMap usuarios, UsuarioIF oUsuarioActualTO) throws Exception {
		log.info("Iniciando ejecucion de JugadaDAO.actualizarJugadaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		String nuevoStatus = Constants.STATUS_JUGADA_PENDIENTE;
		boolean suspendido = false;
		boolean perdedor = false;
		boolean perdedorTodas = false;
		double apostado = 0;
		double logro = 0;
		boolean cerrar = false;
		//String rolSupervisor = "";
		String rolUsuario = "";
		int diasVencimiento = 0;
		boolean jugador = false;
		String idJugador = "";
		UsuarioIF usuarioJugada = new UsuarioTO();

		try {
			// primero cargamos la jugada
			strBuffquery.setLength(0);
			cargarJugadaDAO(oJugadaTO);
			oJugadaTO.setBono("0"); // desactivamos el bono

			// ahora consultamos los detalles de la jugada
			oParametros.add(oJugadaTO.getIdJugada()); // primary key

			strBuffquery.setLength(0);
			strBuffquery.append("SELECT c.id_jugada,c.tipo,c.id_status_jugada,e.id_usuario As jugador_jugada,f.id_supervisor,f.id_rol As rol_usuario,g.id_rol As rol_supervisor, g.dias_venc_ticket As dias_vencimiento, ");
			strBuffquery.append("if(c.tipo='A' or c.tipo='B',b.total,if(c.tipo='RL',b.spread,if(c.tipo='SR',b.super_spread,0))) As cantidad,  ");
			strBuffquery.append("if(c.tipo='A' or c.tipo='B',b.total_logro,if(c.tipo='RL',b.spread_logro,if(c.tipo='SR',b.super_spread_logro,if(c.tipo IN ('ML','E','SI','NO','AP'),b.money_line,0)))) As logro, ");
			strBuffquery.append("f.pago_veces ");
			strBuffquery.append("FROM juego_equipo a, usuario_juego_equipo b, jugada_juego_equipo c, jugada e, usuario f, usuario g ");
			strBuffquery.append("WHERE a.id_juego_equipo = b.id_juego_equipo  ");
			strBuffquery.append("AND b.id_usuario_juego_equipo = c.id_usuario_juego_equipo ");
			strBuffquery.append("AND c.id_jugada = e.id_jugada ");
			strBuffquery.append("AND e.id_usuario = f.id_usuario ");
			strBuffquery.append("AND f.id_supervisor = g.id_usuario ");
			strBuffquery.append("AND c.id_jugada=? ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

			if (oCachedRowSet.size() > 0) {
				// vamos a verificar si las jugadas no han sido totalmente
				// suspendidas
				oCachedRowSet.beforeFirst();
				while (oCachedRowSet.next()) {
					//rolSupervisor = oCachedRowSet.getString("rol_supervisor");
					rolUsuario = oCachedRowSet.getString("rol_usuario");
					diasVencimiento = Integer.parseInt(Constants.isNull(oCachedRowSet.getString("dias_vencimiento"), "0"));
					idJugador = oCachedRowSet.getString("jugador_jugada");
					if (!oCachedRowSet.getString("id_status_jugada").equals(Constants.STATUS_JUGADA_SUSPENDIDA)) {
						suspendido = false;
						break;
					} else {
						suspendido = true;
					}
				}
				// vamos a verificar si ha perdido alguna jugada
				// si es asi pierde la jugada
				oCachedRowSet.beforeFirst();
				while (oCachedRowSet.next()) {
					if (oCachedRowSet.getString("id_status_jugada").equals(Constants.STATUS_JUGADA_PERDEDOR)) {
						perdedor = true;
						break;
					}
				}

				// vamos a verificar si perdio todos los items de la jugada
				// si es asi puede ganar un bono dependiendo de la taquilla
				oCachedRowSet.beforeFirst();
				perdedorTodas = true;
				while (oCachedRowSet.next()) {
					if (!oCachedRowSet.getString("id_status_jugada").equals(Constants.STATUS_JUGADA_PERDEDOR)) {
						perdedorTodas = false;
						break;
					}
				}

				if (!suspendido && !perdedor) {
					boolean gano = false;
					apostado = Double.parseDouble(Constants.isNull(oJugadaTO.getMontoApostado(), "0"));
					double apostadoInicial = apostado;
					int numeroLogro = 0;
					if (apostado > 0) {
						oCachedRowSet.beforeFirst();
						while (oCachedRowSet.next()) {
							usuarioJugada.setPagoVeces(oCachedRowSet.getString("pago_veces"));
							if (oCachedRowSet.getString("id_status_jugada").equals(Constants.STATUS_JUGADA_EN_JUEGO)) {
								oJugadaTO.setIdStatusJugada(Constants.STATUS_JUGADA_EN_JUEGO);
								// no totalizamos porque aun hay jugadas en
								// juego
								cerrar = false;
								break;
							}
							if (oCachedRowSet.getString("id_status_jugada").equals(Constants.STATUS_JUGADA_GANADOR)) {
								gano = true;
								logro = Double.parseDouble(Constants.isNull(oCachedRowSet.getString("logro"), "0"));
								if (logro > 0) {
									apostado += apostado * (logro / 100);
								} else if (logro < 0) {
									apostado += apostado / (((-1) * logro) / 100);
								}
								numeroLogro++;
							}
							cerrar = true;

						}
						if (!gano) {
							apostado = 0;
						} else {
							// recalculamos el premio
							// aplicamos las reglas de pago x veces
							if (apostado > 0) {
								apostado = JuegoFacade.calcularPagoVeces(numeroLogro, apostadoInicial, apostado, usuarioJugada);
							}
						}
					} else {
						oJugadaTO.setIdStatusJugada(Constants.STATUS_JUGADA_SUSPENDIDA);
					}
				} else if (suspendido) {
					oJugadaTO.setIdStatusJugada(Constants.STATUS_JUGADA_SUSPENDIDA);

					apostado = Double.parseDouble(Constants.isNull(oJugadaTO.getMontoApostado(), "0"));

					oJugadaTO.setMontoPagado(oJugadaTO.getMontoApostado());
					//					if (rolSupervisor.equals(Constants.ROL_ADMINISTRADOR)) {
					if (rolUsuario.equals(Constants.ROL_JUGADOR)) {
						jugador = true;
					}
				} else if (perdedor) {
					boolean tieneBono = false;
					if (perdedorTodas) { // se hace acreedor de un bono si la
											// taquilla lo tiene activado
						//
						if (usuarios.containsKey(oJugadaTO.getIdUsuario())) {
							UsuarioTO oUsuarioTO = (UsuarioTO) usuarios.get(oJugadaTO.getIdUsuario());

							// si tiene activo el bono
							int nJugadas = oCachedRowSet.size();
							int nBonoPermitido = Integer.parseInt(oUsuarioTO.getActivarBono());
							if (nBonoPermitido > 0 && nJugadas >= nBonoPermitido) {
								oJugadaTO.setIdStatusJugada(Constants.STATUS_JUGADA_SUSPENDIDA);
								oJugadaTO.setBono("1"); // lo marcamos como pago
														// de un bono
								tieneBono = true;

								apostado = Double.parseDouble(Constants.isNull(oJugadaTO.getMontoApostado(), "0"));

								oJugadaTO.setMontoPagado(oJugadaTO.getMontoApostado());
								//								if (rolSupervisor.equals(Constants.ROL_ADMINISTRADOR)) {
								if (rolUsuario.equals(Constants.ROL_JUGADOR)) {
									jugador = true;
								}
							}
						}
					}
					if (!tieneBono) {
						oJugadaTO.setIdStatusJugada(Constants.STATUS_JUGADA_ANULADA);
						oJugadaTO.setMontoPagado("0");
						//						if (rolSupervisor.equals(Constants.ROL_ADMINISTRADOR)) {
						if (rolUsuario.equals(Constants.ROL_JUGADOR)) {
							jugador = true;
						}
					}
				}
			} else {
				// la cerramos si la jugada no tiene items
				oJugadaTO.setIdStatusJugada(Constants.STATUS_JUGADA_ELIMINADA);
			}

			if (cerrar) {
				//				if (rolSupervisor.equals(Constants.ROL_ADMINISTRADOR)) {
				if (rolUsuario.equals(Constants.ROL_JUGADOR)) {
					jugador = true;
					if (apostado > 0) {
						oJugadaTO.setIdStatusJugada(Constants.STATUS_JUGADA_PAGADA);
						oJugadaTO.setFechaPago(Constants.getFechaLargaSQL());
					} else {
						oJugadaTO.setIdStatusJugada(Constants.STATUS_JUGADA_ANULADA);
					}
				} else {
					oJugadaTO.setIdStatusJugada(Constants.STATUS_JUGADA_PENDIENTE);
					// debemos colocar la fecha de expiracion segun la
					// configuracion
					oJugadaTO.setFechaExp(Constants.getFechaLargaSQL(oJugadaTO.getFechaSis(), diasVencimiento));
				}
				oJugadaTO.setMontoPagado(String.valueOf(Math.round(apostado)));
			}
			// actualizamos el registro en la base de datos
			actualizarJugadaDAO(oJugadaTO);

			if (jugador) {
				// si es jugador tenemos que actualizar el saldo de su cuenta.
				CuentaJugadorIF oCuentaJugadorTO = new CuentaJugadorTO();
				CuentaJugadorDAO oCuentaJugadorDAO = new CuentaJugadorDAO(oEjecutorSql);

				oCuentaJugadorTO.setReferencia(Constants.numero(oJugadaTO.getIdJugada(), 10));
				oCuentaJugadorDAO.cargarCuentaJugadorPorReferenciaJugadaDAO(oCuentaJugadorTO);

				if (Constants.isNull(oCuentaJugadorTO.getIdCuenta(), "").equals("")) {
					oCuentaJugadorTO.setMonto(String.valueOf(apostado));
					oCuentaJugadorTO.setOperacion(Constants.OPERACION_INGRESO);
					if (suspendido) {
						oCuentaJugadorTO.setConcepto("Reintegro suspension de la jugada Nro. ".concat(Constants.numero(oJugadaTO.getIdJugada(), 10)));
					} else {
						oCuentaJugadorTO.setConcepto("Premio de la jugada Nro. ".concat(Constants.numero(oJugadaTO.getIdJugada(), 10)));
					}
					oCuentaJugadorTO.setIdJugador(idJugador);
					oCuentaJugadorTO.setIdUsuario(oJugadaTO.getIdUsuario());
					oCuentaJugadorTO.setTipo(Constants.CUENTA_JUGADOR_TIPO_PREMIO);
					oCuentaJugadorDAO.insertarCuentaJugadorDAO(oCuentaJugadorTO);
				} else {
					oCuentaJugadorTO.setMonto(String.valueOf(apostado));
					oCuentaJugadorDAO.actualizarCuentaJugadorDAO(oCuentaJugadorTO);
					if (suspendido) {
						oCuentaJugadorTO.setConcepto("Reintegro suspension de la jugada Nro. ".concat(Constants.numero(oJugadaTO.getIdJugada(), 10)));
					} else {
						oCuentaJugadorTO.setConcepto("Premio de la jugada Nro. ".concat(Constants.numero(oJugadaTO.getIdJugada(), 10)));
					}
					oCuentaJugadorDAO.actualizarCuentaJugadorDAO(oCuentaJugadorTO);

					oJugadaTO.setFechaPago(oCuentaJugadorTO.getFechaSis());
					actualizarJugadaDAO(oJugadaTO);
				}

			}

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaDAO.actualizarJugadaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla jugada
	 * 
	 */
	public void recalcularPremioJugadaDAO(JugadaIF oJugadaTO, UsuarioIF oUsuarioTO) throws Exception {
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);

		// ahora consultamos los detalles de la jugada
		oParametros.add(oJugadaTO.getIdJugada()); // primary key

		strBuffquery.setLength(0);
		strBuffquery.append("SELECT c.id_jugada,c.tipo,c.id_status_jugada,e.id_usuario As jugador_jugada,f.id_supervisor,g.id_rol As rol_supervisor, g.dias_venc_ticket As dias_vencimiento, ");
		strBuffquery.append("if(c.tipo='A' or c.tipo='B',b.total,if(c.tipo='RL',b.spread,if(c.tipo='SR',b.super_spread,0))) As cantidad,  ");
		strBuffquery.append("if(c.tipo='A' or c.tipo='B',b.total_logro,if(c.tipo='RL',b.spread_logro,if(c.tipo='SR',b.super_spread_logro,if(c.tipo IN ('ML','E','SI','NO','AP'),b.money_line,0)))) As logro, ");
		strBuffquery.append(" h.id_deporte as deporte, e.monto_premio as premio ");
		strBuffquery.append("FROM juego_equipo a, usuario_juego_equipo b, jugada_juego_equipo c, jugada e, usuario f, usuario g, juego h ");
		strBuffquery.append("WHERE a.id_juego_equipo = b.id_juego_equipo  ");
		strBuffquery.append("AND b.id_usuario_juego_equipo = c.id_usuario_juego_equipo ");
		strBuffquery.append("AND c.id_jugada = e.id_jugada ");
		strBuffquery.append("AND e.id_usuario = f.id_usuario ");
		strBuffquery.append("AND f.id_supervisor = g.id_usuario AND h.id_juego = a.id_juego ");
		strBuffquery.append("AND c.id_jugada=? ");

		oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		double logro = 0;
		int numeroLogro = 0;
		double apostado = Double.parseDouble(Constants.isNull(oJugadaTO.getMontoApostado(), "0"));
		if (apostado > 0) {
			oCachedRowSet.beforeFirst();
			while (oCachedRowSet.next()) {
				logro = Double.parseDouble(Constants.isNull(oCachedRowSet.getString("logro"), "0"));
				if (logro > 0) {
					if (26 == oCachedRowSet.getInt("deporte")) {
						apostado = oCachedRowSet.getDouble("premio");
						numeroLogro++;
					} else {
						apostado += apostado * (logro / 100);
						numeroLogro++;
					}
				} else if (logro < 0) {
					apostado += apostado / (((-1) * logro) / 100);
					numeroLogro++;
				}
			}
		}

		// aplicamos las reglas de pago x veces
		apostado = JuegoFacade.calcularPagoVeces(numeroLogro, Double.parseDouble(Constants.isNull(oJugadaTO.getMontoApostado(), "0")), Math.round(apostado), oUsuarioTO);

		oJugadaTO.setMontoPremio(String.valueOf(Math.round(apostado)));

		CachedRowSet reglas = oEjecutorSql.ejecutaQuery("select * from reglas_pago order by id_reglas_pago", new ArrayList());

		if (reglas.size() > 0) {
			// aplicamos las reglas
			double montoJugada = Double.parseDouble(Constants.isNull(oJugadaTO.getMontoApostado(), "0"));
			double montoMaximo = 0;
			double totalPremio = Double.parseDouble(oJugadaTO.getMontoPremio());
			while (reglas.next()) {
				montoMaximo = Math.max(reglas.getDouble("monto_maximo"), montoMaximo);
				if (montoJugada >= reglas.getDouble("rango_ini") && montoJugada <= reglas.getDouble("rango_fin")) {
					totalPremio = Math.min(totalPremio, (montoJugada * reglas.getDouble("multiplo")));
					break;
				}
			}
			oJugadaTO.setMontoPremio(String.valueOf(Math.round(Math.min(montoMaximo, totalPremio))));
		}

		oParametros = new ArrayList();
		oParametros.add(new Double(oJugadaTO.getMontoPremio()));
		oParametros.add(oJugadaTO.getIdJugada());

		strBuffquery.setLength(0);
		strBuffquery.append("UPDATE jugada SET monto_premio=? WHERE id_jugada=? ");

		int act = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
	}

	/**
	 * 
	 * Actualizar un registro en la tabla jugada
	 * 
	 */
	public int actualizarStatuJugadaVencidaJugadaDAO() throws Exception {
		log.info("Iniciando ejecucion de JugadaDAO.actualizarStatuJugadaVencidaJugadaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			//strBuffquery.setLength(0);
			//strBuffquery.append("UPDATE jugada SET fecha_exp=ADDDATE(fecha_sis,5) WHERE DATE_FORMAT(fecha_sis,'%Y%m%d')=DATE_FORMAT(fecha_exp,'%Y%m%d')"); 
			//numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

			oParametros.add(Constants.STATUS_JUGADA_VENCIDA);
			oParametros.add(Constants.STATUS_JUGADA_EN_JUEGO);
			oParametros.add(Constants.STATUS_JUGADA_PENDIENTE);
			oParametros.add(Constants.getFechaLargaSQL());
			strBuffquery.setLength(0);
			strBuffquery.append("UPDATE jugada SET id_status_jugada=? WHERE (id_status_jugada=? || id_status_jugada=?) AND fecha_exp IS NOT NULL AND fecha_exp<? ");

			log.info("Ejecutando actualizacion" + strBuffquery.toString());
			log.info("Fecha considerada " + Constants.getFechaLargaSQL());

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaDAO.actualizarStatuJugadaVencidaJugadaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla jugada
	 * 
	 */
	public int eliminarJugadaDAO(JugadaIF oJugadaTO) throws Exception {
		log.info("Iniciando ejecucion de JugadaDAO.eliminarJugadaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(Constants.STATUS_JUGADA_ELIMINADA);
			oParametros.add(oJugadaTO.getIdJugada());

			strBuffquery.append("UPDATE jugada SET id_status_jugada=?, monto_pagado=monto_apostado ");
			strBuffquery.append("WHERE  id_jugada = ? ");
			strBuffquery.append("AND now()<ADDTIME(fecha_sis,'0:20:0') "); // la
																			// jugada
																			// debe
																			// tener
																			// menos
																			// de
																			// 20
																			// minutos
																			// para
																			// eliminarla

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaDAO.eliminarJugadaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla jugada
	 * 
	 */
	public int eliminarJugadaAdmTaqDAO(JugadaIF oJugadaTO) throws Exception {
		log.info("Iniciando ejecucion de JugadaDAO.eliminarJugadaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(Constants.STATUS_JUGADA_ELIMINADA);
			oParametros.add(oJugadaTO.getIdJugada());

			strBuffquery.append("UPDATE jugada SET id_status_jugada=?, monto_pagado=monto_apostado ");
			strBuffquery.append("WHERE  id_jugada = ? ");
			strBuffquery.append("AND now()<ADDTIME(fecha_sis,'1:0:0') "); // menos de una hora

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaDAO.eliminarJugadaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla jugada
	 * 
	 */
	public int eliminarJugadaAdminDAO(JugadaIF oJugadaTO) throws Exception {
		log.info("Iniciando ejecucion de JugadaDAO.eliminarJugadaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(Constants.STATUS_JUGADA_ELIMINADA);
			oParametros.add(oJugadaTO.getIdJugada());

			strBuffquery.append("UPDATE jugada SET id_status_jugada=?, monto_pagado=monto_apostado ");
			strBuffquery.append("WHERE  id_jugada = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaDAO.eliminarJugadaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla jugada
	 * 
	 */
	public CachedRowSet listaJugadaDAO(UsuarioIF oUsuarioTO, JugadaIF oJugadaTO, JugadaIF oJugada2TO, int orden, String taquilla) throws Exception {
		return listaJugadaDAO(oUsuarioTO, oJugadaTO, oJugada2TO, orden, taquilla, 1, false);
	}

	public CachedRowSet listaJugadaDAO(UsuarioIF oUsuarioTO, JugadaIF oJugadaTO, JugadaIF oJugada2TO, int orden, String taquilla, int tipo, boolean isVencida) throws Exception {
		log.info("Iniciando ejecucion de JugadaDAO.listaJugadaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			switch (tipo) {
			case 1:
				strBuffquery.append("SELECT a.*,b.*,c.*, e.moneda, ");
				strBuffquery.append("format(a.monto_apostado,2) As f_monto_apostado, ");
				strBuffquery.append("format(a.monto_premio,2) As f_monto_premio, ");
				strBuffquery.append("format(a.monto_pagado,2) As f_monto_pagado, ");
				strBuffquery.append("DATE_FORMAT(a.fecha_sis,'%d/%m/%Y') dia_sis, DATE_FORMAT(a.fecha_sis,'%h:%i%p') hora_sis, ");
				strBuffquery.append("if(a.fecha_exp is not null,DATE_FORMAT(a.fecha_exp,'%d/%m/%Y'),'') dia_exp, if(a.fecha_exp is not null,DATE_FORMAT(a.fecha_exp,'%h:%i%p'),'') hora_exp, ");
				strBuffquery.append("if(a.fecha_pago is not null,DATE_FORMAT(a.fecha_pago,'%d/%m/%Y'),'') dia_pago, if(a.fecha_pago is not null,DATE_FORMAT(a.fecha_pago,'%h:%i%p'),'') hora_pago ");
				break;
			case 2:
				strBuffquery.append("SELECT a.id_usuario, a.id_jugada, a.id_status_jugada, a.bono, b.desc_jugada,  e.moneda, ");
				strBuffquery.append("c.usuario, a.monto_apostado, a.monto_premio, a.monto_pagado, ");
				strBuffquery.append("format(a.monto_apostado,2) As f_monto_apostado, ");
				strBuffquery.append("format(a.monto_premio,2) As f_monto_premio, ");
				strBuffquery.append("format(a.monto_pagado,2) As f_monto_pagado, ");
				strBuffquery.append("DATE_FORMAT(a.fecha_sis,'%d/%m/%Y') dia_sis, DATE_FORMAT(a.fecha_sis,'%h:%i%p') hora_sis, ");
				strBuffquery.append("if(a.fecha_exp is not null,DATE_FORMAT(a.fecha_exp,'%d/%m/%Y'),'') dia_exp, if(a.fecha_exp is not null,DATE_FORMAT(a.fecha_exp,'%h:%i%p'),'') hora_exp, ");
				strBuffquery.append("if(a.fecha_pago is not null,DATE_FORMAT(a.fecha_pago,'%d/%m/%Y'),'') dia_pago, if(a.fecha_pago is not null,DATE_FORMAT(a.fecha_pago,'%h:%i%p'),'') hora_pago ");
				break;
			}
			strBuffquery.append("FROM jugada a, status_jugada b, usuario c, VW_USER_DOMAIN e ");
			if ((taquilla != null && !taquilla.equals(""))) {
				strBuffquery.append(", (select id_usuario from usuario where id_supervisor=").append(taquilla).append(") d ");
			}
			if (!oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR) && oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				strBuffquery.append(",usuario_admin d ");
			}
			strBuffquery.append("WHERE a.id_status_jugada=b.id_status_jugada ");
			strBuffquery.append("AND a.id_usuario=c.id_usuario ");
			strBuffquery.append("AND c.usuario=e.usuario ");
			if (!oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR) && oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				oParametros.add(oUsuarioTO.getIdUsuario());
				strBuffquery.append("AND d.id_admin=? ");
				strBuffquery.append("AND (c.id_usuario=d.id_usuario OR c.id_supervisor=d.id_usuario) ");
			}

			if (isVencida) {
				oJugadaTO.setIdStatusJugada(Constants.STATUS_JUGADA_VENCIDA);
			}
			if (oJugadaTO.getIdStatusJugada() != null && !oJugadaTO.getIdStatusJugada().trim().equals("")) {
				if (oJugadaTO.getCancelada() == null || !oJugadaTO.getCancelada().equals("-1")) {
					oParametros.add(oJugadaTO.getIdStatusJugada());
					strBuffquery.append("AND a.id_status_jugada=? ");
				} else {
					oParametros.add(oJugadaTO.getIdStatusJugada());
					oParametros.add(Constants.ID_STATUS_JUGADA_SUSPENDIDA);
					strBuffquery.append("AND (a.id_status_jugada=? OR a.id_status_jugada=? AND a.cancelada IS NULL) ");
				}
			}

			String nombreFecha = (isVencida ? "a.fecha_exp" : "a.fecha_sis");
			if (oJugadaTO.getFechaSis() != null && !oJugadaTO.getFechaSis().trim().equals("") && oJugada2TO.getFechaSis() != null && !oJugada2TO.getFechaSis().trim().equals("")) {
				oParametros.add(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), true));
				oParametros.add(Constants.getFechaFormatoSQL(oJugada2TO.getFechaSis(), false));
				strBuffquery.append("AND ").append(nombreFecha).append(">=? AND ").append(nombreFecha).append("<=? ");
			} else if (oJugadaTO.getFechaSis() != null && !oJugadaTO.getFechaSis().trim().equals("")) {
				oParametros.add(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), true));
				oParametros.add(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), false));
				strBuffquery.append("AND ").append(nombreFecha).append(">=? AND ").append(nombreFecha).append("<=? ");
			}

			if (oJugadaTO.getIdUsuario() != null && !oJugadaTO.getIdUsuario().trim().equals("")) {
				oParametros.add(oJugadaTO.getIdUsuario());
				strBuffquery.append("AND a.id_usuario=? ");
			}

			if (taquilla != null && !taquilla.equals("")) {
				strBuffquery.append("AND a.id_usuario = d.id_usuario ");
			}

			if (!Constants.isNull(oJugadaTO.getIdJugada())) {
				oParametros.add(oJugadaTO.getIdJugada());
				strBuffquery.append("AND a.id_jugada=? ");
			}

			if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				// todos los usuarios
			} else if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA)) {
				oParametros.add(oUsuarioTO.getIdUsuario());
				strBuffquery.append("AND a.id_usuario in (select id_usuario from usuario where id_supervisor=?) ");
			} else {
				// solo el usuario
				oParametros.add(oUsuarioTO.getIdUsuario());
				strBuffquery.append("AND a.id_usuario=? ");
			}

			switch (orden) {
			case 0:
				// sin orden establecido
				strBuffquery.append("ORDER BY a.id_jugada ");
				break;
			case 1:
				strBuffquery.append("ORDER BY a.id_jugada ");
				break;
			case 2:
				strBuffquery.append("ORDER BY a.fecha_sis,a.id_jugada ");
				break;
			case 3:
				strBuffquery.append("ORDER BY a.fecha_exp,a.id_jugada ");
				break;
			case 4:
				strBuffquery.append("ORDER BY b.desc_jugada,a.id_jugada ");
				break;
			case 5:
				strBuffquery.append("ORDER BY a.id_usuario,id_jugada ");
				break;
			case 6:
				strBuffquery.append("ORDER BY a.monto_apostado ");
				break;
			case 7:
				strBuffquery.append("ORDER BY a.monto_premio ");
				break;
			case 8:
				strBuffquery.append("ORDER BY a.monto_pagado ");
				break;
			default:
				strBuffquery.append("ORDER BY a.id_jugada ");
			}

			//oEjecutorSql.printQuery(strBuffquery.toString(), oParametros);
			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaDAO.listaJugadaDAO");
			log.error("Error:" + e.getMessage(), e);
			throw e;
		}
		return oCachedRowSet;

	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla jugada
	 * 
	 */
	public CachedRowSet listaJugadaPagadaDAO(UsuarioIF oUsuarioTO, JugadaIF oJugadaTO, JugadaIF oJugada2TO, int orden) throws Exception {
		log.info("Iniciando ejecucion de JugadaDAO.listaJugadaPagadaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oParametros.add(Constants.STATUS_JUGADA_PAGADA);

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT a.*,b.*,c.*, ");
			strBuffquery.append("format(a.monto_apostado,2) As f_monto_apostado, ");
			strBuffquery.append("format(a.monto_premio,2) As f_monto_premio, ");
			strBuffquery.append("format(a.monto_pagado,2) As f_monto_pagado, ");
			strBuffquery.append("DATE_FORMAT(a.fecha_sis,'%d/%m/%Y') dia_sis, DATE_FORMAT(a.fecha_sis,'%h:%i%p') hora_sis, ");
			strBuffquery.append("if(a.fecha_exp is not null,DATE_FORMAT(a.fecha_exp,'%d/%m/%Y'),'') dia_exp, if(a.fecha_exp is not null,DATE_FORMAT(a.fecha_exp,'%h:%i%p'),'') hora_exp, ");
			strBuffquery.append("if(a.fecha_pago is not null,DATE_FORMAT(a.fecha_pago,'%d/%m/%Y'),'') dia_pago, if(a.fecha_pago is not null,DATE_FORMAT(a.fecha_pago,'%h:%i%p'),'') hora_pago ");
			strBuffquery.append("FROM jugada a, status_jugada b, usuario c ");
			strBuffquery.append("WHERE a.id_status_jugada=b.id_status_jugada ");
			strBuffquery.append("AND a.id_usuario=c.id_usuario ");
			strBuffquery.append("AND a.id_status_jugada=? ");

			if (oJugadaTO.getFechaSis() != null && !oJugadaTO.getFechaSis().trim().equals("") && oJugada2TO.getFechaSis() != null && !oJugada2TO.getFechaSis().trim().equals("")) {
				oParametros.add(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), true));
				oParametros.add(Constants.getFechaFormatoSQL(oJugada2TO.getFechaSis(), false));
				strBuffquery.append("AND a.fecha_pago>=? AND a.fecha_pago<=? ");
			} else if (oJugadaTO.getFechaSis() != null && !oJugadaTO.getFechaSis().trim().equals("")) {
				oParametros.add(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), true));
				oParametros.add(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), false));
				strBuffquery.append("AND a.fecha_pago>=? AND a.fecha_pago<=? ");
			}

			if (oJugadaTO.getIdUsuario() != null && !oJugadaTO.getIdUsuario().trim().equals("")) {
				oParametros.add(oJugadaTO.getIdUsuario());
				strBuffquery.append("AND a.id_usuario=? ");
			}

			if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				// todos los usuarios
			} else if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA)) {
				oParametros.add(oUsuarioTO.getIdUsuario());
				strBuffquery.append("AND a.id_usuario in (select id_usuario from usuario where id_supervisor=?) ");
			} else {
				// solo el usuario
				oParametros.add(oUsuarioTO.getIdUsuario());
				strBuffquery.append("AND a.id_usuario=? ");
			}

			strBuffquery.append("ORDER BY a.id_jugada ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaDAO.listaJugadaPagadaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla jugada
	 * 
	 */
	public boolean cargarJugadaDAO(JugadaIF oJugadaTO) throws Exception {
		log.info("Iniciando ejecucion de JugadaDAO.consultarJugadaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_jugada, fecha_sis, fecha_exp, ");
			strBuffquery.append("monto_apostado, monto_premio, monto_pagado, ");
			strBuffquery.append("id_usuario, id_status_jugada, dias_expira, fecha_pago, detalle_equipo, cancelada, items_jugada, bono ");
			strBuffquery.append("FROM jugada ");

			boolean procesar = false;
			if (!oJugadaTO.getIdJugada().equalsIgnoreCase("")) {
				oParametros.add(oJugadaTO.getIdJugada());
				strBuffquery.append(" WHERE id_jugada = ? ");
				procesar = true;
			} else if (!oJugadaTO.getFechaSis().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(fecha_sis) LIKE '%");
				strBuffquery.append(oJugadaTO.getFechaSis().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oJugadaTO.setIdJugada(oCachedRowSet.getString("id_jugada"));
					oJugadaTO.setFechaSis(oCachedRowSet.getString("fecha_sis"));
					oJugadaTO.setFechaExp(oCachedRowSet.getString("fecha_exp"));
					oJugadaTO.setMontoApostado(oCachedRowSet.getString("monto_apostado"));
					oJugadaTO.setMontoPremio(oCachedRowSet.getString("monto_premio"));
					oJugadaTO.setMontoPagado(oCachedRowSet.getString("monto_pagado"));
					oJugadaTO.setIdUsuario(oCachedRowSet.getString("id_usuario"));
					oJugadaTO.setIdStatusJugada(oCachedRowSet.getString("id_status_jugada"));
					oJugadaTO.setDiasExpira(oCachedRowSet.getString("dias_expira"));
					oJugadaTO.setFechaPago(oCachedRowSet.getString("fecha_pago"));
					oJugadaTO.setDetalleEquipo(oCachedRowSet.getString("detalle_equipo"));
					oJugadaTO.setCancelada(oCachedRowSet.getString("cancelada"));
					oJugadaTO.setItemsJugada(oCachedRowSet.getString("items_jugada"));
					oJugadaTO.setBono(oCachedRowSet.getString("bono"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaDAO.consultarJugadaDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla jugada
	 * 
	 */
	public CachedRowSet ultimaJugadaDAO(UsuarioIF oUsuarioTO) throws Exception {
		log.info("Iniciando ejecucion de JugadaDAO.ultimaJugadaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oParametros.add(Constants.TIPO_ALTA);
			oParametros.add(Constants.TIPO_BAJA);
			oParametros.add(Constants.TIPO_RUNLINE);
			oParametros.add(Constants.TIPO_SUPER_RUNLINE);
			oParametros.add(Constants.ID_EQUIPO_EMPATE);
			oParametros.add(Constants.ID_EQUIPO_EMPATE);
			oParametros.add(Constants.TIPO_ALTA);
			oParametros.add(Constants.TIPO_BAJA);
			oParametros.add(Constants.TIPO_RUNLINE);
			oParametros.add(Constants.TIPO_SUPER_RUNLINE);
			oParametros.add(Constants.TIPO_MONEYLINE);
			oParametros.add(Constants.TIPO_SI);
			oParametros.add(Constants.TIPO_NO);
			oParametros.add(Constants.TIPO_ANOTAPRIMERO);
			oParametros.add(Constants.TIPO_EMPATE);
			oParametros.add(oUsuarioTO.getIdUsuario());

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT c.id_juego,a.tipo, k.empate, e.items_jugada, e.bono, ");
			strBuffquery.append("if(a.tipo=? or a.tipo=?,b.total,if(a.tipo=?,b.spread,if(a.tipo=?,b.super_spread,0))) As cantidad, ");
			strBuffquery.append("c.referencia,if(d.desc_corta IS NULL,d.desc_equipo,d.desc_corta) AS desc_equipo,j.id_deporte, ");
			strBuffquery.append("(select pit.nombre_lanzador from lanzador pit, juego_equipo_lanzador jpit where c.id_juego_equipo=jpit.id_juego_equipo and jpit.id_lanzador=pit.id_lanzador) nombre_lanzador, ");
			strBuffquery.append("d.abreviatura As Equipo1, ");
			strBuffquery.append("(select y.abreviatura from juego_equipo x, equipo y where x.id_equipo=y.id_equipo AND x.id_juego=c.id_juego and x.id_equipo !=? and x.id_equipo!=c.id_equipo ORDER BY x.id_juego_equipo desc LIMIT 1) As Equipo2, ");
			strBuffquery
					.append("(select if(y.desc_corta IS NULL,y.desc_equipo,y.desc_corta) AS desc_equipo from juego_equipo x, equipo y where x.id_equipo=y.id_equipo AND x.id_juego=c.id_juego and x.id_equipo !=? and x.id_equipo!=c.id_equipo ORDER BY x.id_juego_equipo desc LIMIT 1) As desc_equipo2, ");
			strBuffquery.append("if(a.tipo=? or a.tipo=?,b.total_logro,if(a.tipo=?,b.spread_logro,if(a.tipo=?,b.super_spread_logro,if(a.tipo IN (?,?,?,?,?),b.money_line,0)))) As logro, ");
			strBuffquery.append("e.*,f.*,g.centro_hipico As local, g.rif As rif_supervisor, format(e.monto_apostado,2) As jugada, format(e.monto_premio,2) As premio, ");
			strBuffquery.append("DATE_FORMAT(e.fecha_sis,'%d/%m/%Y') dia, DATE_FORMAT(e.fecha_sis,'%h:%i%p') hora, g.ticket_nota As nota, ");
			strBuffquery.append("DATE_FORMAT(e.fecha_pago,'%d/%m/%Y') dia_pago, DATE_FORMAT(e.fecha_pago,'%h:%i%p') hora_pago ");
			strBuffquery.append("FROM jugada_juego_equipo a ,usuario_juego_equipo b, juego_equipo c,equipo d, jugada e, usuario f, usuario g, juego j, deporte k ");
			strBuffquery.append("WHERE a.id_usuario_juego_equipo=b.id_usuario_juego_equipo ");
			strBuffquery.append("AND b.id_juego_equipo=c.id_juego_equipo ");
			strBuffquery.append("AND c.id_equipo=d.id_equipo ");
			strBuffquery.append("AND a.id_jugada=e.id_jugada ");
			strBuffquery.append("AND a.id_jugada = (select max(id_jugada) from jugada where id_usuario=?) ");
			strBuffquery.append("AND e.id_usuario=f.id_usuario ");
			strBuffquery.append("AND f.id_supervisor=g.id_usuario ");
			strBuffquery.append("AND c.id_juego=j.id_juego ");
			strBuffquery.append("AND j.id_deporte=k.id_deporte ");

			//oEjecutorSql.printQuery(strBuffquery.toString(), oParametros);
			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaDAO.ultimaJugadaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla jugada
	 * 
	 */
	public CachedRowSet getJugadaDAO(JugadaIF oJugadaTO) throws Exception {
		log.info("Iniciando ejecucion de JugadaDAO.ultimaJugadaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oParametros.add(Constants.TIPO_ALTA);
			oParametros.add(Constants.TIPO_BAJA);
			oParametros.add(Constants.TIPO_RUNLINE);
			oParametros.add(Constants.TIPO_SUPER_RUNLINE);
			oParametros.add(Constants.ID_EQUIPO_EMPATE);
			oParametros.add(Constants.TIPO_ALTA);
			oParametros.add(Constants.TIPO_BAJA);
			oParametros.add(Constants.TIPO_RUNLINE);
			oParametros.add(Constants.TIPO_SUPER_RUNLINE);
			oParametros.add(Constants.TIPO_MONEYLINE);
			oParametros.add(Constants.TIPO_SI);
			oParametros.add(Constants.TIPO_NO);
			oParametros.add(Constants.TIPO_ANOTAPRIMERO);
			oParametros.add(Constants.TIPO_EMPATE);
			oParametros.add(oJugadaTO.getIdJugada());

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT c.id_juego,a.tipo, k.empate, e.items_jugada, e.bono,  ");
			strBuffquery.append("e.id_status_jugada As status_jugada, "); // adicional
			strBuffquery.append("if(a.tipo=? or a.tipo=?,b.total,if(a.tipo=?,b.spread,if(a.tipo=?,b.super_spread,0))) As cantidad, ");
			strBuffquery.append("c.referencia,d.desc_equipo,j.id_deporte, ");
			strBuffquery.append("(select pit.nombre_lanzador from lanzador pit, juego_equipo_lanzador jpit where c.id_juego_equipo=jpit.id_juego_equipo and jpit.id_lanzador=pit.id_lanzador) nombre_lanzador, ");
			strBuffquery.append("d.abreviatura As Equipo1, ");
			strBuffquery.append("(select y.abreviatura from juego_equipo x, equipo y where x.id_equipo=y.id_equipo AND x.id_juego=c.id_juego and x.id_equipo !=? and x.id_equipo!=c.id_equipo ORDER BY x.id_juego_equipo desc LIMIT 1) As Equipo2, ");
			strBuffquery.append("if(a.tipo=? or a.tipo=?,b.total_logro,if(a.tipo=?,b.spread_logro,if(a.tipo=?,b.super_spread_logro,if(a.tipo IN (?,?,?,?,?),b.money_line,0)) )) As logro, ");
			strBuffquery.append("e.*,f.*,h.*,g.centro_hipico As local, g.rif As rif_supervisor, format(e.monto_apostado,2) As jugada, format(e.monto_premio,2) As premio, format(e.monto_pagado,2) As pagado,");
			strBuffquery.append("DATE_FORMAT(e.fecha_sis,'%d/%m/%Y') dia, DATE_FORMAT(e.fecha_sis,'%h:%i%p') hora, g.ticket_nota As nota, ");
			strBuffquery.append("DATE_FORMAT(e.fecha_pago,'%d/%m/%Y') dia_pago, DATE_FORMAT(e.fecha_pago,'%h:%i%p') hora_pago ");
			strBuffquery.append(", e.detalle_equipo, DATE_FORMAT(now(),'%d/%m/%Y') dia_actual, e.cancelada ");
			strBuffquery.append(" , t.desc_campeonato desc_campeonato, DATE_FORMAT(j.fecha_ini, '%h:%i %p') as hora_sorteo ");// adicional
			strBuffquery.append("FROM jugada_juego_equipo a ,usuario_juego_equipo b, juego_equipo c,equipo d, jugada e, usuario f, usuario g, status_jugada h , juego j, deporte k, campeonato t ");
			strBuffquery.append("WHERE a.id_usuario_juego_equipo=b.id_usuario_juego_equipo ");
			strBuffquery.append("AND b.id_juego_equipo=c.id_juego_equipo ");
			strBuffquery.append("AND c.id_equipo=d.id_equipo ");
			strBuffquery.append("AND a.id_jugada=e.id_jugada ");
			strBuffquery.append("AND a.id_status_jugada=h.id_status_jugada "); // adicional
			strBuffquery.append("AND a.id_jugada = ? ");
			strBuffquery.append("AND e.id_usuario=f.id_usuario ");
			strBuffquery.append("AND f.id_supervisor=g.id_usuario ");
			strBuffquery.append("AND c.id_juego=j.id_juego ");
			strBuffquery.append("AND j.id_deporte=k.id_deporte ");
			strBuffquery.append("AND t.id_campeonato = j.id_campeonato");

			//oEjecutorSql.printQuery(strBuffquery.toString(), oParametros);
			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaDAO.ultimaJugadaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	public boolean isGameClosedOrTotalizedOrInitialized(JugadaIF oJugadaTO, UsuarioIF usuario) {
		boolean resp = false;
		ArrayList parametros = new ArrayList();
		ArrayList parametros2 = new ArrayList();
		StringBuffer query = new StringBuffer();
		StringBuffer select1 = new StringBuffer();
		StringBuffer select2 = new StringBuffer();

		parametros.add(oJugadaTO.getIdJugada());
		parametros.add(Constants.STATUS_JUEGO_CERRADO);
		parametros.add(Constants.STATUS_JUEGO_TOTALIZADO);

		parametros2.add(oJugadaTO.getIdJugada());
		parametros2.add(usuario.getIdSupervisor());

		// busca los juegos cerrados o totalizados por el administrador o si ya
		// el juego se inicio
		select1.append("SELECT e.id_juego,e.id_status_juego ");

		// busca los juegos cerrados o totalizados por el administrador de
		// taquilla
		select2.append("SELECT id_juego, id_status_juego FROM usuario_juego ");
		select2.append("WHERE id_juego in (");
		select2.append("SELECT e.id_juego ");

		query.append("FROM jugada a, jugada_juego_equipo b, usuario_juego_equipo c, juego_equipo d, juego e ");
		query.append("WHERE a.id_jugada=b.id_jugada ");
		query.append("AND b.id_usuario_juego_equipo=c.id_usuario_juego_equipo ");
		query.append("AND c.id_juego_equipo=d.id_juego_equipo ");
		query.append("AND d.id_juego=e.id_juego ");
		query.append("AND a.id_jugada=? ");

		select1.append(query);
		// select1.append("AND (e.id_status_juego IN (?,?) OR now()>date_sub(e.fecha_ini,interval (30+e.minutos_cierre) minute) ) ");
		select1.append("AND (e.id_status_juego IN (?,?) OR now()>date_sub(e.fecha_ini,interval (e.minutos_cierre) minute) ) ");
		select1.append("group by e.id_juego ");

		select2.append(query).append(") AND id_usuario = ? ");
		select2.append("group by id_juego ");

		try {
			// revisamos si hay juegos cerrados o totalizados por el
			// administrador
			resp = oEjecutorSql.ejecutaQuery(select1.toString(), parametros).next();
			if (!resp) {
				// revisamos si hay juegos cerrados o totalizados por el
				// administrador de taquilla(supervisor)
				resp = oEjecutorSql.ejecutaQuery(select2.toString(), parametros2).next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla jugada
	 * 
	 */
	public CachedRowSet listaJugadaResumidaDAO(UsuarioIF oUsuarioTO, JugadaIF oJugadaTO, JugadaIF oJugada2TO, int orden, String taquilla) throws Exception {
		log.info("Iniciando ejecucion de JugadaDAO.listaJugadaResumidaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("select ' ' As tipo, c.usuario,c.centro_hipico,a.id_usuario,c.usuario, ");
			strBuffquery.append("sum(if(a.items_jugada<=1,a.monto_apostado,0)) AS jugado_derecho, ");
			strBuffquery.append("sum(if(a.items_jugada>1,a.monto_apostado,0)) AS jugado_parley, ");
			strBuffquery.append("sum(a.monto_apostado) AS jugado, ");
			strBuffquery.append("sum(if(a.items_jugada<=1 and a.id_status_jugada=6,a.monto_pagado,0)) AS devolucion_derecho, ");
			strBuffquery.append("sum(if(a.items_jugada>1 and a.id_status_jugada=6,a.monto_pagado,0)) AS devolucion_parley, ");
			strBuffquery.append("sum(if(a.id_status_jugada=6,a.monto_pagado,0)) AS devolucion, ");
			strBuffquery.append("sum(if(a.id_status_jugada!=6 && a.items_jugada<=1,a.monto_pagado,0)) AS ganadores_derecho, ");
			strBuffquery.append("sum(if(a.id_status_jugada!=6 && a.items_jugada>1,a.monto_pagado,0)) AS ganadores_parley, ");
			strBuffquery.append("sum(if(a.id_status_jugada!=6,a.monto_pagado,0)) AS ganadores, ");
			strBuffquery.append("sum(if(a.items_jugada<=1,a.monto_apostado-a.monto_pagado,0)) AS subtotal_derecho, ");
			strBuffquery.append("sum(if(a.items_jugada>1,a.monto_apostado-a.monto_pagado,0)) AS subtotal_parley, ");
			strBuffquery.append("sum(a.monto_apostado-a.monto_pagado) AS subtotal, ");
			strBuffquery.append("c.comision_venta, c.comision_venta_parley, ");
			strBuffquery.append("c.otros_gastos, c.otros_gastos_parley ");
			strBuffquery.append("FROM jugada a, status_jugada b, usuario c ");
			strBuffquery.append("WHERE a.id_status_jugada=b.id_status_jugada ");
			strBuffquery.append("AND a.id_usuario=c.id_usuario ");
			strBuffquery.append("AND a.id_status_jugada!=").append(Constants.STATUS_JUGADA_ELIMINADA).append(" ");

			if (oJugadaTO.getFechaSis() != null && !oJugadaTO.getFechaSis().trim().equals("") && oJugada2TO.getFechaSis() != null && !oJugada2TO.getFechaSis().trim().equals("")) {
				oParametros.add(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), true));
				oParametros.add(Constants.getFechaFormatoSQL(oJugada2TO.getFechaSis(), false));
				strBuffquery.append("AND a.fecha_sis>=? AND a.fecha_sis<=? ");
			} else if (oJugadaTO.getFechaSis() != null && !oJugadaTO.getFechaSis().trim().equals("")) {
				oParametros.add(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), true));
				oParametros.add(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), true));
				strBuffquery.append("AND a.fecha_sis>=? AND a.fecha_sis<=? ");
			}

			if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				// todos los usuarios
			} else if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA)) {
				oParametros.add(oUsuarioTO.getIdUsuario());
				strBuffquery.append("AND a.id_usuario in (select id_usuario from usuario where id_supervisor=?) ");
			} else {
				// solo el usuario
				oParametros.add(oUsuarioTO.getIdUsuario());
				strBuffquery.append("AND a.id_usuario=? ");
			}

			strBuffquery.append("GROUP BY tipo, c.usuario, c.centro_hipico ");
			strBuffquery.append("ORDER BY c.usuario ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaDAO.listaJugadaResumidaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla jugada
	 * 
	 */
	public CachedRowSet listaJugadaResumidaAdminDAO(UsuarioIF oUsuarioTO, JugadaIF oJugadaTO, JugadaIF oJugada2TO, int orden, String taquilla, String dominio) throws Exception {
		log.info("Iniciando ejecucion de JugadaDAO.listaJugadaResumidaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();

			if (false && oUsuarioTO.getTipo().equals("0")) {
				strBuffquery.append("select '(Adm)' As tipo,c.id_supervisor,d.id_usuario,d.usuario,c.comision_venta, ");
				strBuffquery.append("sum(if(a.items_jugada<=1,a.monto_apostado,0)) AS jugado_derecho, ");
				strBuffquery.append("sum(if(a.items_jugada>1,a.monto_apostado,0)) AS jugado_parley, ");
				strBuffquery.append("sum(a.monto_apostado) AS jugado, ");
				strBuffquery.append("sum(if(a.items_jugada<=1 and a.id_status_jugada=6,a.monto_pagado,0)) AS devolucion_derecho, ");
				strBuffquery.append("sum(if(a.items_jugada>1 and a.id_status_jugada=6,a.monto_pagado,0)) AS devolucion_parley, ");
				strBuffquery.append("sum(if(a.id_status_jugada=6,a.monto_pagado,0)) AS devolucion, ");
				strBuffquery.append("sum(if(a.id_status_jugada!=6 && a.items_jugada<=1,a.monto_pagado,0)) AS ganadores_derecho, ");
				strBuffquery.append("sum(if(a.id_status_jugada!=6 && a.items_jugada>1,a.monto_pagado,0)) AS ganadores_parley, ");
				strBuffquery.append("sum(if(a.id_status_jugada!=6,a.monto_pagado,0)) AS ganadores, ");
				strBuffquery.append("sum(if(a.items_jugada<=1,a.monto_apostado-a.monto_pagado,0)) AS subtotal_derecho, ");
				strBuffquery.append("sum(if(a.items_jugada>1,a.monto_apostado-a.monto_pagado,0)) AS subtotal_parley, ");
				strBuffquery.append("sum(a.monto_apostado-a.monto_pagado) AS subtotal, ");
				strBuffquery.append("c.comision_venta, c.comision_venta_parley, ");
				strBuffquery.append("c.otros_gastos, c.otros_gastos_parley ");
				strBuffquery.append("FROM jugada a, status_jugada b, usuario c, usuario d ");
				if (!oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR)) {
					strBuffquery.append(", usuario_admin e ");
				}
				strBuffquery.append("WHERE a.id_status_jugada=b.id_status_jugada ");
				if (!oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR)) {
					strBuffquery.append("AND e.id_admin='").append(oUsuarioTO.getIdUsuario()).append("' ");
					strBuffquery.append("AND c.id_usuario=e.id_usuario ");
				}
				strBuffquery.append("AND a.id_status_jugada!=").append(Constants.STATUS_JUGADA_ELIMINADA).append(" ");
				strBuffquery.append("AND c.id_rol!=4 "); // no listamos usuarios
															// jugadores
				strBuffquery.append("AND a.id_usuario=c.id_usuario ");
				strBuffquery.append("AND c.id_supervisor=d.id_usuario ");
				if (oJugadaTO.getFechaSis() != null && !oJugadaTO.getFechaSis().trim().equals("") && oJugada2TO.getFechaSis() != null && !oJugada2TO.getFechaSis().trim().equals("")) {
					oParametros.add(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), true));
					oParametros.add(Constants.getFechaFormatoSQL(oJugada2TO.getFechaSis(), false));
					strBuffquery.append("AND a.fecha_sis>=? AND a.fecha_sis<=? ");
				} else if (oJugadaTO.getFechaSis() != null && !oJugadaTO.getFechaSis().trim().equals("")) {
					oParametros.add(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), true));
					oParametros.add(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), false));
					strBuffquery.append("AND a.fecha_sis>=? AND a.fecha_sis<=? ");
				}
				if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
					// todos los usuarios
				} else if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA)) {
					oParametros.add(oUsuarioTO.getIdUsuario());
					strBuffquery.append("AND a.id_usuario in (select id_usuario from usuario where id_supervisor=?) ");
				} else {
					// solo el usuario
					oParametros.add(oUsuarioTO.getIdUsuario());
					strBuffquery.append("AND a.id_usuario=? ");
				}

				strBuffquery.append("GROUP BY tipo, c.id_supervisor,d.id_usuario,d.usuario ");

				strBuffquery.append("UNION ");
			}

			strBuffquery.append("select CASE c.id_rol WHEN 3 THEN '(Jug-Taq)' WHEN 5 THEN '(Auto Jugador)' ELSE '(Jug)' END AS tipo, c.id_supervisor,c.id_usuario,c.usuario,c.comision_venta, ");
			strBuffquery.append("sum(if(a.items_jugada<=1,a.monto_apostado,0)) AS jugado_derecho, ");
			strBuffquery.append("sum(if(a.items_jugada>1,a.monto_apostado,0)) AS jugado_parley, ");
			strBuffquery.append("sum(a.monto_apostado) AS jugado, ");
			strBuffquery.append("sum(if(a.items_jugada<=1 and a.id_status_jugada=6,a.monto_pagado,0)) AS devolucion_derecho, ");
			strBuffquery.append("sum(if(a.items_jugada>1 and a.id_status_jugada=6,a.monto_pagado,0)) AS devolucion_parley, ");
			strBuffquery.append("sum(if(a.id_status_jugada=6,a.monto_pagado,0)) AS devolucion, ");
			strBuffquery.append("sum(if(a.id_status_jugada!=6 && a.items_jugada<=1,a.monto_pagado,0)) AS ganadores_derecho, ");
			strBuffquery.append("sum(if(a.id_status_jugada!=6 && a.items_jugada>1,a.monto_pagado,0)) AS ganadores_parley, ");
			strBuffquery.append("sum(if(a.id_status_jugada!=6,a.monto_pagado,0)) AS ganadores, ");
			strBuffquery.append("sum(if(a.items_jugada<=1,a.monto_apostado-a.monto_pagado,0)) AS subtotal_derecho, ");
			strBuffquery.append("sum(if(a.items_jugada>1,a.monto_apostado-a.monto_pagado,0)) AS subtotal_parley, ");
			strBuffquery.append("sum(a.monto_apostado-a.monto_pagado) AS subtotal, ");
			strBuffquery.append("c.comision_venta, c.comision_venta_parley, ");
			strBuffquery.append("c.otros_gastos, c.otros_gastos_parley ");
			strBuffquery.append("FROM jugada a, status_jugada b, usuario c ");
			if (!oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR)) {
				strBuffquery.append(", usuario_admin e ");
			}
			strBuffquery.append("WHERE a.id_status_jugada=b.id_status_jugada ");
			strBuffquery.append("AND a.id_usuario=c.id_usuario ");
			if (!oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR)) {
				strBuffquery.append("AND e.id_admin='").append(oUsuarioTO.getIdUsuario()).append("' ");
				strBuffquery.append("AND c.id_usuario=e.id_usuario ");
			}
			if (oJugadaTO.getFechaSis() != null && !oJugadaTO.getFechaSis().trim().equals("") && oJugada2TO.getFechaSis() != null && !oJugada2TO.getFechaSis().trim().equals("")) {
				oParametros.add(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), true));
				oParametros.add(Constants.getFechaFormatoSQL(oJugada2TO.getFechaSis(), false));
				strBuffquery.append("AND a.fecha_sis>=? AND a.fecha_sis<=? ");
			} else if (oJugadaTO.getFechaSis() != null && !oJugadaTO.getFechaSis().trim().equals("")) {
				oParametros.add(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), true));
				oParametros.add(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), false));
				strBuffquery.append("AND a.fecha_sis>=? AND a.fecha_sis<=? ");
			}
			strBuffquery.append("AND a.id_status_jugada!=").append(Constants.STATUS_JUGADA_ELIMINADA).append(" ");
			strBuffquery.append("AND c.id_rol IN (3,4,5) "); // listamos jugadores y jugadores de taquilla
			if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				// todos los usuarios
			} else if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA)) {
				oParametros.add(oUsuarioTO.getIdUsuario());
				strBuffquery.append("AND a.id_usuario in (select id_usuario from usuario where id_supervisor=?) ");
			} else {
				// solo el usuario
				oParametros.add(oUsuarioTO.getIdUsuario());
				strBuffquery.append("AND a.id_usuario=? ");
			}

			if (dominio != null && !dominio.trim().equals("")) {
				oParametros.add(dominio);
				strBuffquery.append("AND c.dominio=? ");
			}

			strBuffquery.append("GROUP BY tipo, c.id_supervisor,c.id_usuario,c.usuario ");
			strBuffquery.append("ORDER BY tipo,usuario ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaDAO.listaJugadaResumidaDAO");
			e.printStackTrace();
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla jugada
	 * 
	 */
	public double consultarMontoJugadoPorDerechoFacade(JuegoEquipoIF oJuegoEquipoTO, JugadaJuegoEquipoIF oJugadaJuegoEquipoTO, UsuarioIF oUsuarioTO) throws Exception {
		log.info("Iniciando ejecucion de JugadaDAO.consultarMontoJugadoPorDerechoFacade");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		double montoPorDerecho = 0;
		try {
			oCachedRowSet = new CachedRowSet();

			oParametros.add(oJuegoEquipoTO.getIdJuego());
			oParametros.add(oJuegoEquipoTO.getReferencia());
			oParametros.add(oJugadaJuegoEquipoTO.getTipo());
			oParametros.add(oUsuarioTO.getIdUsuario());
			oParametros.add(Constants.STATUS_JUGADA_ELIMINADA);

			strBuffquery.append("SELECT sum(a.monto_apostado) As monto_por_derecho ");
			strBuffquery.append("FROM jugada a, jugada_juego_equipo b, usuario_juego_equipo c, juego_equipo d ");
			strBuffquery.append("WHERE a.id_jugada=b.id_jugada ");
			strBuffquery.append("AND b.id_usuario_juego_equipo=c.id_usuario_juego_equipo ");
			strBuffquery.append("AND c.id_juego_equipo=d.id_juego_equipo ");
			strBuffquery.append("AND a.items_jugada=1 ");
			strBuffquery.append("AND d.id_juego=? ");
			strBuffquery.append("AND d.referencia=? ");
			strBuffquery.append("AND b.tipo=? ");
			strBuffquery.append("AND a.id_usuario=? ");
			strBuffquery.append("AND a.id_status_jugada!=? ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			if (oCachedRowSet.next()) {
				if (oCachedRowSet.getString("monto_por_derecho") != null) {
					montoPorDerecho = oCachedRowSet.getDouble("monto_por_derecho");
				}

			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaDAO.consultarMontoJugadoPorDerechoFacade");
			e.printStackTrace();
			throw e;
		}

		return montoPorDerecho;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla jugada
	 * 
	 */
	public CachedRowSet listaApuestaPorEquipoDAO(UsuarioIF oUsuarioTO, JugadaIF oJugadaTO, JugadaIF oJugada2TO, int orden, String taquilla, String idEquipo) throws Exception {
		log.info("Iniciando ejecucion de JugadaDAO.listaJugadaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("select d.id_equipo, ");
			if (idEquipo != null && idEquipo.equals(Constants.ID_EQUIPO_EMPATE)) {
				strBuffquery.append("d.id_juego, ");
			}
			strBuffquery.append("e.desc_equipo,count(d.id_equipo) as tickets, ");
			strBuffquery.append("sum(if(b.items_jugada>1 and a.tipo='ML',b.monto_apostado,0)) As parley_ML, ");
			strBuffquery.append("sum(if(b.items_jugada>1 and a.tipo='RL',b.monto_apostado,0)) As parley_RL, ");
			strBuffquery.append("sum(if(b.items_jugada>1 and a.tipo='SR',b.monto_apostado,0)) As parley_SR, ");
			strBuffquery.append("sum(if(b.items_jugada>1 and a.tipo='SI',b.monto_apostado,0)) As parley_SI, ");
			strBuffquery.append("sum(if(b.items_jugada>1 and a.tipo='NO',b.monto_apostado,0)) As parley_NO, ");
			strBuffquery.append("sum(if(b.items_jugada>1 and a.tipo='A',b.monto_apostado,0)) As parley_A, ");
			strBuffquery.append("sum(if(b.items_jugada>1 and a.tipo='B',b.monto_apostado,0)) As parley_B, ");
			strBuffquery.append("sum(if(b.items_jugada>1,b.monto_apostado,0)) As parley, ");
			strBuffquery.append("sum(if(b.items_jugada<=1 and a.tipo='ML',b.monto_apostado,0)) As derecho_ML, ");
			strBuffquery.append("sum(if(b.items_jugada<=1 and a.tipo='RL',b.monto_apostado,0)) As derecho_RL, ");
			strBuffquery.append("sum(if(b.items_jugada<=1 and a.tipo='SR',b.monto_apostado,0)) As derecho_SR, ");
			strBuffquery.append("sum(if(b.items_jugada<=1 and a.tipo='SI',b.monto_apostado,0)) As derecho_SI, ");
			strBuffquery.append("sum(if(b.items_jugada<=1 and a.tipo='NO',b.monto_apostado,0)) As derecho_NO, ");
			strBuffquery.append("sum(if(b.items_jugada<=1 and a.tipo='A',b.monto_apostado,0)) As derecho_A, ");
			strBuffquery.append("sum(if(b.items_jugada<=1 and a.tipo='B',b.monto_apostado,0)) As derecho_B, ");
			strBuffquery.append("sum(if(b.items_jugada<=1,b.monto_apostado,0)) As derecho, ");
			strBuffquery.append("sum(b.monto_apostado) As apostado ");
			strBuffquery.append("from jugada_juego_equipo a, jugada b,usuario_juego_equipo c,juego_equipo d, equipo e, usuario f ");
			if (!oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR) && oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				strBuffquery.append(",usuario_admin g ");
			}
			strBuffquery.append("where b.fecha_sis>='").append(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), true)).append("' ");
			strBuffquery.append("and b.fecha_sis<='").append(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), false)).append("' ");
			strBuffquery.append("and a.id_jugada=b.id_jugada ");
			strBuffquery.append("and a.id_usuario_juego_equipo=c.id_usuario_juego_equipo ");
			strBuffquery.append("and c.id_juego_equipo=d.id_juego_equipo ");
			strBuffquery.append("and d.id_equipo=e.id_equipo ");
			strBuffquery.append("and b.id_status_jugada NOT IN (").append(Constants.STATUS_JUGADA_ELIMINADA).append(",").append(Constants.STATUS_JUGADA_SUSPENDIDA).append(") ");
			strBuffquery.append("and b.id_usuario=f.id_usuario ");
			if (!oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR) && oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				strBuffquery.append("and g.id_admin=").append(oUsuarioTO.getIdUsuario()).append(" ");
				strBuffquery.append("and (f.id_usuario=g.id_usuario OR f.id_supervisor=g.id_usuario) ");
			} else if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA)) {
				strBuffquery.append("AND f.id_usuario in (select id_usuario from usuario where id_supervisor=").append(oUsuarioTO.getIdUsuario()).append(") ");
			} else if (oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA) || oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR)) {
				strBuffquery.append("AND f.id_usuario=").append(oUsuarioTO.getIdUsuario()).append(" ");
			}
			if (idEquipo != null) {
				if (idEquipo.indexOf(",") != -1) {
					strBuffquery.append("and d.id_equipo IN (").append(idEquipo).append(") ");
				} else {
					strBuffquery.append("and d.id_equipo=").append(idEquipo).append(" ");
				}
			}
			strBuffquery.append("group by d.id_equipo ");
			if (idEquipo != null && idEquipo.equals(Constants.ID_EQUIPO_EMPATE)) {
				strBuffquery.append(",d.id_juego ");
			}
			strBuffquery.append("order by apostado DESC");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaDAO.listaJugadaDAO");
			log.error("Error:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return oCachedRowSet;
	}

	public TreeMap listaApuestaPorEquipoEmpateDAO(CachedRowSet crs) throws Exception {
		log.info("Iniciando ejecucion de JugadaDAO.listaJugadaDAO");
		ArrayList oParametros = new ArrayList();
		TreeMap lista = new TreeMap();
		strBuffquery.setLength(0);
		StringBuffer juegos = new StringBuffer();
		String sep = "";
		String coma = ",";
		boolean hacer = false;
		try {

			crs.beforeFirst();
			while (crs.next()) {
				juegos.append(sep);
				juegos.append(crs.getString("id_juego"));
				sep = coma;
				hacer = true;
			}
			crs.beforeFirst();

			if (hacer) {
				oCachedRowSet = new CachedRowSet();

				strBuffquery.append("select a.id_juego_equipo, a.id_juego,a.id_equipo,c.desc_equipo,c.abreviatura ");
				strBuffquery.append("from juego_equipo a, juego b, equipo c ");
				strBuffquery.append("where a.id_juego=b.id_juego ");
				strBuffquery.append("and a.id_equipo=c.id_equipo ");
				strBuffquery.append("and a.id_juego in (").append(juegos).append(") ");
				strBuffquery.append("and a.id_equipo!=").append(Constants.ID_EQUIPO_EMPATE).append(" ");
				strBuffquery.append("order by a.id_juego_equipo ");

				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

				while (oCachedRowSet.next()) {
					if (lista.containsKey(oCachedRowSet.getString("id_juego"))) {
						lista.put(oCachedRowSet.getString("id_juego"), String.valueOf(lista.get(oCachedRowSet.getString("id_juego"))).concat(oCachedRowSet.getString("desc_equipo")));
					} else {
						lista.put(oCachedRowSet.getString("id_juego"), oCachedRowSet.getString("desc_equipo").concat(" Vs "));
					}
				}

			}

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaDAO.listaJugadaDAO");
			log.error("Error:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return lista;
	}

	public TreeMap listaJugadaResumidaVencidaDAO(UsuarioIF oUsuarioTO, JugadaIF oJugadaTO, JugadaIF oJugada2TO, int orden, String taquilla) throws Exception {
		log.info("Iniciando ejecucion de JugadaDAO.listaJugadaResumidaDAO");
		ArrayList oParametros = new ArrayList();
		TreeMap lista = new TreeMap();
		strBuffquery.setLength(0);
		String tablaJugada = "jugada";
		try {

			if (oJugada2TO.getFechaSis() == null || oJugada2TO.getFechaSis().trim().equals("")) {
				oJugada2TO.setFechaSis(oJugadaTO.getFechaSis());
			}

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("select a.id_usuario, ");
			strBuffquery.append("sum(a.monto_pagado) AS vencido ");
			strBuffquery.append("FROM ").append(tablaJugada).append(" a, usuario c ");
			strBuffquery.append("WHERE a.id_usuario=c.id_usuario ");
			strBuffquery.append("AND a.id_status_jugada=").append(Constants.STATUS_JUGADA_VENCIDA).append(" ");

			if (oJugadaTO.getFechaSis() != null && !oJugadaTO.getFechaSis().trim().equals("") && oJugada2TO.getFechaSis() != null && !oJugada2TO.getFechaSis().trim().equals("")) {
				oParametros.add(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), true));
				oParametros.add(Constants.getFechaFormatoSQL(oJugada2TO.getFechaSis(), false));
				strBuffquery.append("AND a.fecha_exp>=? AND a.fecha_exp<=? ");
			}

			if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				// todos los usuarios
			} else if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA)) {
				oParametros.add(oUsuarioTO.getIdUsuario());
				strBuffquery.append("AND a.id_usuario in (select id_usuario from usuario where id_supervisor=?) ");
			} else {
				// solo el usuario
				oParametros.add(oUsuarioTO.getIdUsuario());
				strBuffquery.append("AND a.id_usuario=? ");
			}

			strBuffquery.append("GROUP BY c.usuario, c.centro_hipico ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

			while (oCachedRowSet.next()) {
				lista.put(oCachedRowSet.getString("id_usuario"), oCachedRowSet.getString("vencido")); // lo agregamos a la lista
			}

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaDAO.listaJugadaResumidaDAO");
			e.printStackTrace();
			throw e;
		}
		return lista;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla jugada
	 * 
	 */
	public CachedRowSet listaResultadoVendedorJugadaDAO(UsuarioIF oUsuarioTO, JugadaIF oJugadaTO, JugadaIF oJugada2TO) throws Exception {
		log.info("Iniciando ejecucion de JugadaDAO.listaResultadoVendedorJugadaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT a.id_usuario, b.usuario, SUM(a.monto_apostado) As apostado, ");
			strBuffquery.append("SUM(if(a.monto_apostado-a.monto_pagado>0,(a.monto_apostado-a.monto_pagado)*(if(a.items_jugada<2,b.otros_gastos, b.otros_gastos_parley)/100), 0 )) As comision, ");
			strBuffquery.append("SUM(a.monto_pagado) As pagado ");
			strBuffquery.append("FROM jugada a, usuario b ");
			strBuffquery.append("WHERE a.id_usuario=b.id_usuario ");
			strBuffquery.append("AND a.id_status_jugada!=").append(Constants.STATUS_JUGADA_ELIMINADA).append(" ");
			strBuffquery.append("AND a.id_status_jugada!=").append(Constants.STATUS_JUGADA_SUSPENDIDA).append(" ");

			if (oJugadaTO.getFechaSis() != null && !oJugadaTO.getFechaSis().trim().equals("") && oJugada2TO.getFechaSis() != null && !oJugada2TO.getFechaSis().trim().equals("")) {
				oParametros.add(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), true));
				oParametros.add(Constants.getFechaFormatoSQL(oJugada2TO.getFechaSis(), false));
				strBuffquery.append("AND a.fecha_sis>=? AND a.fecha_sis<=? ");
			} else if (oJugadaTO.getFechaSis() != null && !oJugadaTO.getFechaSis().trim().equals("")) {
				oParametros.add(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), true));
				oParametros.add(Constants.getFechaFormatoSQL(oJugadaTO.getFechaSis(), false));
				strBuffquery.append("AND a.fecha_sis>=? AND a.fecha_sis<=? ");
			}

			if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				oParametros.add(oUsuarioTO.getIdUsuario());
				strBuffquery.append("AND a.id_usuario in (select id_usuario from usuario_admin where id_admin=?) ");
			} else {
				// solo el usuario
				oParametros.add(oUsuarioTO.getIdUsuario());
				strBuffquery.append("AND a.id_usuario=? ");
			}

			strBuffquery.append("GROUP BY a.id_usuario, b.usuario ");
			strBuffquery.append("ORDER BY b.usuario ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error en la ejecucion de JugadaDAO.listaResultadoVendedorJugadaDAO");
			throw e;
		}
		return oCachedRowSet;
	}
}
