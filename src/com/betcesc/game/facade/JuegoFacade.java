/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.facade;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.bean.DominioBean;
import com.betcesc.game.common.Constants;
import com.betcesc.game.ctrl.back.RegisterGameActionForm;
import com.betcesc.game.dao.CampeonatoDAO;
import com.betcesc.game.dao.CombinacionDAO;
import com.betcesc.game.dao.CuentaJugadorDAO;
import com.betcesc.game.dao.DeporteDAO;
import com.betcesc.game.dao.EquipoDAO;
import com.betcesc.game.dao.JuegoDAO;
import com.betcesc.game.dao.JuegoEquipoDAO;
import com.betcesc.game.dao.JuegoEquipoLanzadorDAO;
import com.betcesc.game.dao.JugadaDAO;
import com.betcesc.game.dao.JugadaJuegoEquipoDAO;
import com.betcesc.game.dao.StatusJuegoDAO;
import com.betcesc.game.dao.StatusJugadaDAO;
import com.betcesc.game.dao.UsuarioDAO;
import com.betcesc.game.dao.UsuarioJuegoDAO;
import com.betcesc.game.dao.UsuarioJuegoEquipoDAO;
import com.betcesc.game.exceptions.GameClosedException;
import com.betcesc.game.exceptions.GameOpenException;
import com.betcesc.game.exceptions.NotValidReferenceException;
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.exceptions.TopePorCombinacionExcedidoException;
import com.betcesc.game.exceptions.UserWithOutCreditException;
import com.betcesc.game.form.CalculadoraForm;
import com.betcesc.game.interfaces.CampeonatoIF;
import com.betcesc.game.interfaces.JuegoEquipoIF;
import com.betcesc.game.interfaces.JuegoEquipoLanzadorIF;
import com.betcesc.game.interfaces.JuegoIF;
import com.betcesc.game.interfaces.JugadaIF;
import com.betcesc.game.interfaces.JugadaJuegoEquipoIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.interfaces.UsuarioJuegoEquipoIF;
import com.betcesc.game.interfaces.UsuarioJuegoIF;
import com.betcesc.game.to.CampeonatoTO;
import com.betcesc.game.to.CombinacionTO;
import com.betcesc.game.to.CuentaJugadorTO;
import com.betcesc.game.to.DeporteTO;
import com.betcesc.game.to.DeporteUserTO;
import com.betcesc.game.to.JuegoEquipoTO;
import com.betcesc.game.to.JuegoTO;
import com.betcesc.game.to.JugadaJuegoEquipoTO;
import com.betcesc.game.to.JugadaTO;
import com.betcesc.game.to.StatusJugadaTO;
import com.betcesc.game.to.UsuarioJuegoEquipoTO;
import com.betcesc.game.to.UsuarioJuegoTO;
import com.betcesc.game.to.UsuarioTO;
import com.jade.util.Date;
import com.jade.util.lbda.ConexionManejaError;
import com.jade.util.lbda.EjecutorSql;

/**
 * 
 * @author jrivero
 * 
 */

public class JuegoFacade {
	private static final Log log = LogFactory.getLog(JuegoFacade.class);

	HttpServletRequest request = null;

	/**
	 * Constructor.
	 */
	public JuegoFacade(HttpServletRequest request) {
		super();
		this.request = request;
	}

	/*
	 * ...........................JUEGO...........................
	 */

	/**
	 * Insertar registros en la tabla Juego.
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public int insertarJuegoFacade(JuegoIF oJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoFacade.insertarJuegoFacade");
		int act = 0;

		JuegoDAO oJuegoDAO = new JuegoDAO();

		try {
			act = oJuegoDAO.insertarJuegoDAO(oJuegoTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoFacade.insertarJuegoFacade");
			log.error("Error:" + e.getMessage());
		}
		return (act);
	}

	/**
	 * Actualizar registros en la tabla Juego.
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public int actualizarJuegoFacade(JuegoIF oJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoFacade.actualizarJuegoFacade");
		int act = 0;
		JuegoDAO oJuegoDAO = new JuegoDAO();
		try {
			act = oJuegoDAO.actualizarJuegoDAO(oJuegoTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoFacade.actualizarJuegoFacade");
			log.error("Error:" + e.getMessage());
		}
		return (act);
	}

	/**
	 * Eliminar registros en la tabla Juego.
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public void eliminarJuegoFacade(JuegoIF oJuegoTO) throws GameOpenException, Exception {
		log.info("Iniciando ejecucion de JuegoFacade.eliminarJuegoFacade");
		EjecutorSql oEjecutorSql = new EjecutorSql();
		JuegoDAO oJuegoDAO = new JuegoDAO(oEjecutorSql);
		boolean isError = false;
		try {
			oEjecutorSql.getConnection().setAutoCommit(false);
			if (oJuegoTO.getIdStatusJuego() != null && !oJuegoTO.getIdStatusJuego().equals("")) {
				oJuegoDAO.eliminarDetalleJuegoDAO(oJuegoTO);
				oJuegoDAO.eliminarJuegoDAO(oJuegoTO);
			} else {
				throw new GameOpenException("El documento no se puede eliminar porque no es borrador");
			}
		} catch (GameOpenException e) {
			log.info("Error en la ejecucion de JuegoFacade.eliminarJuegoFacade");
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			isError = true;
			log.info("Error en la ejecucion de JuegoFacade.eliminarJuegoFacade");
			e.printStackTrace();
			throw new Exception("Ocurrio un error al intentar eliminar el registro");
		} finally {
			try {
				if (isError) {
					oEjecutorSql.rollback();
				}
				oEjecutorSql.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Abre el juego para que este disponible para las jugadas
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public void abrirJuegoFacade(JuegoIF oJuegoTO) throws GameOpenException, Exception {
		log.info("Iniciando ejecucion de JuegoFacade.abrirJuegoFacade");
		EjecutorSql oEjecutorSql = new EjecutorSql();
		JuegoDAO oJuegoDAO = new JuegoDAO(oEjecutorSql);
		boolean isError = false;
		try {
			oEjecutorSql.getConnection().setAutoCommit(false);
			oJuegoTO.setIdStatusJuego(Constants.STATUS_JUEGO_ABIERTO);
			oJuegoDAO.actualizarStatusJuegoDAO(oJuegoTO);

		} catch (Exception e) {
			isError = true;
			log.info("Error en la ejecucion de JuegoFacade.abrirJuegoFacade");
			log.error("Error:" + e.getMessage());
			throw new Exception("Ocurrio un error al intentar abrir el juego");
		} finally {
			try {
				if (isError) {
					oEjecutorSql.rollback();
				}
				oEjecutorSql.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Abre el juego para que este disponible para las jugadas
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public void reabrirAdmTaqJuegoFacade(JuegoIF oJuegoTO, UsuarioIF oUsuarioTO) throws GameOpenException, Exception {
		log.info("Iniciando ejecucion de JuegoFacade.abrirJuegoFacade");
		EjecutorSql oEjecutorSql = new EjecutorSql();
		JuegoDAO oJuegoDAO = new JuegoDAO(oEjecutorSql);
		UsuarioJuegoDAO oUsuarioJuegoDAO = new UsuarioJuegoDAO(oEjecutorSql);

		UsuarioJuegoTO oUsuarioJuegoTO = new UsuarioJuegoTO();

		boolean isError = false;
		try {
			oEjecutorSql.getConnection().setAutoCommit(false);
			
			oUsuarioJuegoTO.setIdUsuario(oUsuarioTO.getIdUsuario());
			oUsuarioJuegoTO.setIdJuego(oJuegoTO.getIdJuego());
			
			oUsuarioJuegoDAO.eliminarUsuarioJuegoDAO(oUsuarioJuegoTO);

		} catch (Exception e) {
			isError = true;
			log.info("Error en la ejecucion de JuegoFacade.abrirJuegoFacade");
			log.error("Error:" + e.getMessage());
			throw new Exception("Ocurrio un error al intentar abrir el juego");
		} finally {
			try {
				if (isError) {
					oEjecutorSql.rollback();
				}
				oEjecutorSql.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * cierra el juego para que este disponible para las jugadas
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public void cerrarJuegoFacade(JuegoIF oJuegoTO) throws GameOpenException, Exception {
		log.info("Iniciando ejecucion de JuegoFacade.cerrarJuegoFacade");
		EjecutorSql oEjecutorSql = new EjecutorSql();
		JuegoDAO oJuegoDAO = new JuegoDAO(oEjecutorSql);
		boolean isError = false;
		try {
			oEjecutorSql.getConnection().setAutoCommit(false);
			oJuegoTO.setIdStatusJuego(Constants.STATUS_JUEGO_CERRADO);
			oJuegoDAO.actualizarStatusJuegoDAO(oJuegoTO);

		} catch (Exception e) {
			isError = true;
			log.info("Error en la ejecucion de JuegoFacade.cerrarJuegoFacade");
			log.error("Error:" + e.getMessage());
			throw new Exception("Ocurrio un error al intentar cerrar el juego");
		} finally {
			try {
				if (isError) {
					oEjecutorSql.rollback();
				}
				oEjecutorSql.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

	/**
	 * Abre el juego para que este disponible para las jugadas
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public void cerrarJuegoFacade(JuegoIF oJuegoTO, UsuarioIF oUsuarioTO) throws GameOpenException, Exception {
		log.info("Iniciando ejecucion de JuegoFacade.abrirJuegoFacade");
		EjecutorSql oEjecutorSql = new EjecutorSql();
		JuegoDAO oJuegoDAO = new JuegoDAO(oEjecutorSql);
		UsuarioJuegoDAO oUsuarioJuegoDAO = new UsuarioJuegoDAO(oEjecutorSql);
		UsuarioJuegoTO oUsuarioJuegoTO = new UsuarioJuegoTO();
		boolean isError = false;
		try {
			oEjecutorSql.getConnection().setAutoCommit(false);
			oJuegoTO.setIdStatusJuego(Constants.STATUS_JUEGO_CERRADO);

			oUsuarioJuegoTO.setIdUsuario(oJuegoTO.getIdUsuario());
			oUsuarioJuegoTO.setIdJuego(oJuegoTO.getIdJuego());
			oUsuarioJuegoTO.setIdStatusJuego(oJuegoTO.getIdStatusJuego());

			if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				oJuegoDAO.actualizarStatusJuegoDAO(oJuegoTO);
				oUsuarioJuegoDAO.eliminarUsuarioJuegoCompletoDAO(oUsuarioJuegoTO);
			} else {
				oUsuarioJuegoDAO.insertarUsuarioJuegoDAO(oUsuarioJuegoTO);
			}
		} catch (Exception e) {
			isError = true;
			log.info("Error en la ejecucion de JuegoFacade.abrirJuegoFacade");
			log.error("Error:" + e.getMessage());
			throw new Exception("Ocurrio un error al intentar eliminar el registro");
		} finally {
			try {
				if (isError) {
					oEjecutorSql.rollback();
				}
				oEjecutorSql.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Totaliza el juego
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public void totalizarJuegoFacade(RegisterGameActionForm forma, UsuarioIF oUsuarioTO) throws GameOpenException, Exception {
		log.info("Iniciando ejecucion de JuegoFacade.totalizarJuegoFacade");
		EjecutorSql oEjecutorSql = new EjecutorSql();
		JuegoEquipoDAO oJuegoEquipoDAO = new JuegoEquipoDAO(oEjecutorSql);
		JuegoDAO oJuegoDAO = new JuegoDAO(oEjecutorSql);
		JugadaJuegoEquipoDAO oJugadaJuegoEquipoDAO = new JugadaJuegoEquipoDAO(oEjecutorSql);
		JugadaDAO oJugadaDAO = new JugadaDAO(oEjecutorSql);
		JuegoEquipoTO oJuegoEquipoTO = null;
		JuegoTO oJuegoTO = new JuegoTO();
		JugadaJuegoEquipoTO oJugadaJuegoEquipoTO = new JugadaJuegoEquipoTO();
		JugadaTO oJugadaTO = new JugadaTO();
		boolean isError = false;
		int sumaPuntos = 0;
		String idJuegoEquipo = "";
		int indice = 0;
		ArrayList idJugadas = new ArrayList();
		int tipoJugada = 0;
		boolean isEmpatados = false;
		try {
			// ajustamos los varlores de los tipos de juego activos
			
			
			// buscaremos las jugadas del juego para marcalas como ganadoras o
			// perdedoras
			CachedRowSet jugadas = oJuegoEquipoDAO.listaJugadaPendientePorJuegoEquipoDAO(forma.get_IdJuegoEquipo()); 

			oEjecutorSql.getConnection().setAutoCommit(false);

			if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				for (int i = 0; i < forma.get_IdJuegoEquipo().length; i++) {
					if(Constants.isNullEntero(forma.get_IdJuegoEquipo()[i])) {
						break;
					}
					oJuegoEquipoTO = new JuegoEquipoTO();
					oJuegoEquipoTO.setIdJuegoEquipo(forma.get_IdJuegoEquipo()[i]);
					oJuegoEquipoTO.setGanador(forma.get_Ganador()[i]);
					oJuegoEquipoTO.setPuntos(forma.get_Puntos()[i]);
					oJuegoEquipoDAO.actualizarGanadorJuegoEquipoDAO(oJuegoEquipoTO);
					sumaPuntos += Integer.parseInt(forma.get_Puntos()[i]); 
				}
				isEmpatados = Integer.parseInt(forma.get_Puntos()[0]) == Integer.parseInt(forma.get_Puntos()[1]);
				
				oJuegoTO.setIdJuego(forma.getIdJuego());
				oJuegoTO.setIdDeporte(forma.getIdDeporte());
				oJuegoTO.setIdStatusJuego(Constants.STATUS_JUEGO_TOTALIZADO);
				oJuegoTO.setSpreadActivo(Constants.isNull(forma.getSpreadActivo(),"1"));
				oJuegoTO.setSuperSpreadActivo(Constants.isNull(forma.getSuperSpreadActivo(),"1"));
				oJuegoTO.setTotalActivo(Constants.isNull(forma.getTotalActivo(),"1"));
				oJuegoTO.setMoneyActivo(Constants.isNull(forma.getMoneyActivo(),"1"));
				oJuegoTO.setIdUsuarioTotaliza(oUsuarioTO.getIdUsuario());
				oJuegoDAO.actualizarStatusJuegoDAO(oJuegoTO);
				
				while(jugadas.next()) {
					oJugadaJuegoEquipoTO.setIdJugadaJuegoEquipo(jugadas.getString("id_jugada_juego_equipo"));
					if(oJugadaJuegoEquipoTO.getIdJugadaJuegoEquipo().equals("308338")) {
						int kj=0;
					}
					oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_PERDEDOR);
					
					if(!idJugadas.contains(jugadas.getString("id_jugada"))) {
						if(jugadas.getString("id_jugada")!=null) {
							idJugadas.add(jugadas.getString("id_jugada"));
						}
					}

					if(jugadas.getString("tipo").equals(Constants.TIPO_ALTA)) {
						tipoJugada = Constants.TIPO_JUGADA_ALTABAJA;
						if(sumaPuntos>jugadas.getDouble("cantidad")) {
							oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_GANADOR);
						} else if(sumaPuntos==jugadas.getDouble("cantidad")) {
							oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_SUSPENDIDA);
						}
					} else if(jugadas.getString("tipo").equals(Constants.TIPO_BAJA)) {
						tipoJugada = Constants.TIPO_JUGADA_ALTABAJA;
						if(sumaPuntos<jugadas.getDouble("cantidad")) {
							oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_GANADOR);
						} else if(sumaPuntos==jugadas.getDouble("cantidad")) {
							oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_SUSPENDIDA);
						}
					} else if(jugadas.getString("tipo").equals(Constants.TIPO_SI)) {
						tipoJugada = Constants.TIPO_JUGADA_SI_NO;
						if(sumaPuntos>0) {
							oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_GANADOR);
						}
					} else if(jugadas.getString("tipo").equals(Constants.TIPO_NO)) {
						tipoJugada = Constants.TIPO_JUGADA_SI_NO;
						if(sumaPuntos==0) {
							oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_GANADOR);
						}
					} else {
						
						//if(jugadas.getString("id_jugada").equals("53945")) {
						//	String a="b";
						//}
						
						indice = -1;
						for(int x=0; x<forma.get_IdJuegoEquipo().length;x++) {
							if(jugadas.getString("id_juego_equipo").equals(forma.get_IdJuegoEquipo()[x])) {
								indice = x;
							}
						}
						
						idJuegoEquipo = forma.get_IdJuegoEquipo()[indice];

						if(jugadas.getString("tipo").equals(Constants.TIPO_MONEYLINE)) {
							tipoJugada = Constants.TIPO_JUGADA_MONEYLINE;
							if(forma.get_Ganador()[indice].equals(Constants.GANADOR_SI) || (forma.get_Ganador()[indice].equals(Constants.GANADOR_EMPATE) && jugadas.getString("id_equipo").equalsIgnoreCase(Constants.ID_EQUIPO_EMPATE)) ) {
								if(forma.get_Ganador()[indice].equals(Constants.GANADOR_SI)) {
									oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_GANADOR);
								} else {
									double puntos1 = Double.parseDouble(forma.get_Puntos()[0]);
									double puntos2 = Double.parseDouble(forma.get_Puntos()[1]);
									if(puntos1==puntos2) {
										oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_GANADOR);
									}
								}
							}
						} else if(jugadas.getString("tipo").equals(Constants.TIPO_RUNLINE)) {
							tipoJugada = Constants.TIPO_JUGADA_RUNLINE;
							double puntos1 = Double.parseDouble(forma.get_Puntos()[indice])+jugadas.getDouble("cantidad");
							double puntos2 = Double.parseDouble(forma.get_Puntos()[indice==0?1:0]);
							if(puntos1>puntos2) {
								oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_GANADOR);
							} else if(puntos1==puntos2) {
								oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_SUSPENDIDA);
							}
						} else if(jugadas.getString("tipo").equals(Constants.TIPO_SUPER_RUNLINE)) {
							tipoJugada = Constants.TIPO_JUGADA_SUPER_RUNLINE;
							double puntos1 = Double.parseDouble(forma.get_Puntos()[indice])+jugadas.getDouble("cantidad");
							double puntos2 = Double.parseDouble(forma.get_Puntos()[indice==0?1:0]);
							if(puntos1>puntos2) {
								oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_GANADOR);
							} else if(puntos1==puntos2) {
								oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_SUSPENDIDA);
							}
						} else if(jugadas.getString("tipo").equals(Constants.TIPO_ANOTAPRIMERO)) {
							tipoJugada = Constants.TIPO_APUESTA_ANOTAPRIMERO;
							if(forma.get_Ganador()[indice].equals(Constants.GANADOR_SI)) {
								if(forma.get_Ganador()[indice].equals(Constants.GANADOR_SI)) {
									oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_GANADOR);
								}
							}
						}
					}
					
					// esto dira si la jugada es valida segun los parametros del juego
					switch(tipoJugada) {
					case Constants.TIPO_JUGADA_MONEYLINE:
						//if(!oJuegoTO.getMoneyActivo().equals("1") || (oJuegoTO.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL_5) && isEmpatados) || (oJuegoTO.getIdDeporte().equals(Constants.ID_EQUIPO_BASKETBALL_MITAD) && isEmpatados) || (oJuegoTO.getIdDeporte().equals(Constants.ID_EQUIPO_SOCCER_MITAD) && isEmpatados) ) {
						if(!oJuegoTO.getMoneyActivo().equals("1") || (oJuegoTO.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL_5) && isEmpatados) || (oJuegoTO.getIdDeporte().equals(Constants.ID_EQUIPO_BASKETBALL_MITAD) && isEmpatados) ) {
							oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_SUSPENDIDA);
						} else if(forma.get_Ganador()[indice].equals(Constants.GANADOR_SUSPENDIDO)) {
							oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_SUSPENDIDA);
						}
						break;
					case Constants.TIPO_JUGADA_RUNLINE:
						if(!oJuegoTO.getSpreadActivo().equals("1")) {
							oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_SUSPENDIDA);
						}
						break;
					case Constants.TIPO_JUGADA_ALTABAJA:
						if(!oJuegoTO.getTotalActivo().equals("1")) {
							oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_SUSPENDIDA);
						}
						break;
					case Constants.TIPO_JUGADA_SI_NO:
						if(!oJuegoTO.getMoneyActivo().equals("1")) {
							oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_SUSPENDIDA);
						}
						break;
					case Constants.TIPO_JUGADA_SUPER_RUNLINE:
						if(!oJuegoTO.getSuperSpreadActivo().equals("1")) {
							oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_SUSPENDIDA);
						}
						break;
					case Constants.TIPO_APUESTA_ANOTAPRIMERO:
						if(!oJuegoTO.getMoneyActivo().equals("1")) {
							oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_SUSPENDIDA);
						}
						break;
					}
					
					oJugadaJuegoEquipoDAO.actualizarJugadaJuegoEquipoStatusDAO(oJugadaJuegoEquipoTO);
				}
				
				// Ahora vamos a totalizar las jugadas y establecer el pago de
				// cada una
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				TreeMap usuarios = usuarioDAO.listaUsuarioDAO();
				for (int i = 0; i < idJugadas.size(); i++) {
					oJugadaTO.setIdJugada(String.valueOf(idJugadas.get(i)));
					oJugadaDAO.totalizarJugadaDAO(oJugadaTO,usuarios,oUsuarioTO);
				}
				
				// actualizamos el status de las jugadas vencidas
				oJugadaDAO.actualizarStatuJugadaVencidaJugadaDAO();
			} else {
				throw new Exception("Solo el administrador puede totalizar el juego");
			}
		} catch (Exception e) {
			isError = true;
			log.info("Error en la ejecucion de JuegoFacade.totalizarJuegoFacade");
			log.error("Error:" + e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrio un error al intentar totalizar el juego");
		} finally {
			try {
				if (isError) {
					oEjecutorSql.rollback();
				}
				oEjecutorSql.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Suspender el juego
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public void suspenderJuegoFacade(RegisterGameActionForm forma, UsuarioIF oUsuarioTO) throws GameOpenException, Exception {
		log.info("Iniciando ejecucion de JuegoFacade.suspenderJuegoFacade");
		EjecutorSql oEjecutorSql = new EjecutorSql();
		
		JuegoTO oJuegoTO = new JuegoTO();
		JuegoEquipoTO oJuegoEquipoTO = null;
		JugadaJuegoEquipoTO oJugadaJuegoEquipoTO = new JugadaJuegoEquipoTO();
		JugadaTO oJugadaTO = new JugadaTO();

		JuegoDAO oJuegoDAO = new JuegoDAO(oEjecutorSql);
		JuegoEquipoDAO oJuegoEquipoDAO = new JuegoEquipoDAO(oEjecutorSql);
		JugadaJuegoEquipoDAO oJugadaJuegoEquipoDAO = new JugadaJuegoEquipoDAO(oEjecutorSql); 
		JugadaDAO oJugadaDAO = new JugadaDAO(oEjecutorSql);
		boolean isError = false;
		ArrayList idJugadas = new ArrayList();
		try {

			CachedRowSet jugadas = oJuegoEquipoDAO.listaJugadaPorJuegoEquipoDAO(forma.get_IdJuegoEquipo()); 

			oEjecutorSql.getConnection().setAutoCommit(false);

			
			if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
					for (int i = 0; i < forma.get_IdJuegoEquipo().length; i++) {
						if(forma.get_IdJuegoEquipo()[i]==null || forma.get_IdJuegoEquipo()[i].trim().equals("") ) {
							break;
						}
						oJuegoEquipoTO = new JuegoEquipoTO();
						oJuegoEquipoTO.setIdJuegoEquipo(forma.get_IdJuegoEquipo()[i]);
						oJuegoEquipoTO.setGanador("0");
						oJuegoEquipoTO.setPuntos("0");
						oJuegoEquipoDAO.actualizarGanadorJuegoEquipoDAO(oJuegoEquipoTO);
					}
					
					oJuegoTO.setIdJuego(forma.getIdJuego());
					oJuegoTO.setIdStatusJuego(Constants.STATUS_JUEGO_SUSPENDIDO);
					oJuegoDAO.actualizarStatusJuegoDAO(oJuegoTO);
					
					while(jugadas.next()) {
						if(!idJugadas.contains(jugadas.getString("id_jugada"))) {
							idJugadas.add(jugadas.getString("id_jugada"));
						}
						oJugadaJuegoEquipoTO.setIdJugadaJuegoEquipo(jugadas.getString("id_jugada_juego_equipo"));
						oJugadaJuegoEquipoTO.setIdStatusJugada(Constants.STATUS_JUGADA_SUSPENDIDA);
						oJugadaJuegoEquipoDAO.actualizarJugadaJuegoEquipoStatusDAO(oJugadaJuegoEquipoTO);
					}
				
					// Ahora vamos a totalizar las jugadas y establecer el pago de
					// cada una
					UsuarioDAO usuarioDAO = new UsuarioDAO();
					TreeMap usuarios = usuarioDAO.listaUsuarioDAO();
					for (int i = 0; i < idJugadas.size(); i++) {
						oJugadaTO.setIdJugada(String.valueOf(idJugadas.get(i)));
						
						oJugadaDAO.totalizarJugadaDAO(oJugadaTO,usuarios, oUsuarioTO);
						
					}
					
					// actualizamos el status de las jugadas vencidas
					oJugadaDAO.actualizarStatuJugadaVencidaJugadaDAO();

			} else {
				throw new Exception("Solo el administrador puede suspender el juego");
			}
		} catch (Exception e) {
			isError = true;
			log.info("Error en la ejecucion de JuegoFacade.suspenderJuegoFacade");
			log.error("Error:" + e.getMessage());
			throw new Exception("Ocurrio un error al intentar suspender el juego");
		} finally {
			try {
				if (isError) {
					oEjecutorSql.rollback();
				}
				oEjecutorSql.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Cargar registros un registro de la tabla Juego.
	 * 
	 * @return JuegoTO.
	 */
	public JuegoTO consultarJuegoFacade(JuegoIF oJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoFacade.cargarJuegoFacade");
		int act = 0;

		JuegoDAO oJuegoDAO = new JuegoDAO();
		JuegoTO juegoTO = new JuegoTO();
		juegoTO.setIdUsuario(oJuegoTO.getIdUsuario());

		try {
			oJuegoDAO.cargarJuegoDAO(juegoTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoFacade.cargarJuegoFacade");
			log.error("Error:" + e.getMessage());
			log.error("Error:" + e.getStackTrace());
		}
		return juegoTO;
	}

	/**
	 * Cargar registros un registro de la tabla Juego.
	 * 
	 * @return JuegoTO.
	 */
	public CampeonatoTO consultarCampeonatoFacade(CampeonatoIF oCampeonatoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoFacade.consultarCampeonatoFacade");
		int act = 0;

		CampeonatoDAO oCampeonatoDAO = new CampeonatoDAO();
		CampeonatoTO campeonatoTO = new CampeonatoTO();
		campeonatoTO.setIdCampeonato(oCampeonatoTO.getIdCampeonato());

		try {
			oCampeonatoDAO.cargarCampeonatoDAO(campeonatoTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoFacade.consultarCampeonatoFacade");
			log.error("Error:" + e.getMessage());
			log.error("Error:" + e.getStackTrace());
		}
		return campeonatoTO;
	}

	/**
	 * Cargar registros un registro de la tabla Juego.
	 * 
	 * @return JuegoTO.
	 */
	public RegisterGameActionForm consultarJuegoCompletoFacade(RegisterGameActionForm juegoIF, UsuarioIF oUsuarioTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoFacade.consultarJuegoCompletoFacade");
		int act = 0;

		RegisterGameActionForm juego = new RegisterGameActionForm();
		juego.setIdJuego(juegoIF.getIdJuego());
		juego.setIdUsuario(juegoIF.getIdUsuario());

		JuegoDAO oJuegoDAO = new JuegoDAO();
		boolean find = false;

		try {
			find = oJuegoDAO.cargarJuegoCompletoDAO(juego, oUsuarioTO);

			// ajustaremos la visualizacion de algunos attributos
			if(find) {
				juego.setHora(Constants.getHoraSqlToHtml(juego.getFechaIni()));
				juego.setMinuto(Constants.getMinutoSqlToHtml(juego.getFechaIni()));
				juego.setFechaIni(Constants.getFechaCortaSqlToHtml(juego.getFechaIni()));
			}

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoFacade.consultarJuegoCompletoFacade");
			log.error("Error:" + e.getMessage());
			e.printStackTrace();
		}
		return (find ? juego : null);
	}

	/**
	 * Construye la lista de los registros de la tabla juego
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public ArrayList listaJuegoFacade(UsuarioIF oUsuarioTO, JuegoIF oJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoFacade.listaJuegoFacade");
		ArrayList lista = new ArrayList();
		JuegoDAO oJuegoDAO = new JuegoDAO();
		try {
			lista = oJuegoDAO.listaJuegoConEquipoNewDAO(oUsuarioTO, oJuegoTO,false,false);
		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoFacade.listaJuegoFacade");
			log.error("Error:" + e.getMessage());
		}
		return lista;

	}
	
	/**
	 * Construye la lista de los registros de la tabla juego
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaJuegoParaJugadaFacade(UsuarioIF oUsuarioTO, JuegoIF oJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoFacade.listaJuegoFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		JuegoDAO oJuegoDAO = new JuegoDAO();
		try {
			oCachedRowSet = oJuegoDAO.listaJuegoParaJugadaConEquipoDAO(oUsuarioTO, oJuegoTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoFacade.listaJuegoFacade");
			log.error("Error:" + e.getMessage());
		}
		return oCachedRowSet;

	}
	
	
	/**
	 * Construye la lista de los registros de la tabla juego
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaLogroFacade(UsuarioIF oUsuarioTO, JuegoIF oJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoFacade.listaLogroFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		JuegoDAO oJuegoDAO = new JuegoDAO();
		try {
			oCachedRowSet = oJuegoDAO.listaJuegoConEquipoDAO(oUsuarioTO, oJuegoTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoFacade.listaLogroFacade");
			log.error("Error:" + e.getMessage());
		}
		return oCachedRowSet;

	}
	
	/**
	 * Construye la lista de los registros de la tabla juego
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaLogroPorCentroFacade(UsuarioIF oUsuarioTO, JuegoIF oJuegoTO, boolean isLiga) throws Exception {
		log.info("Iniciando ejecucion de JuegoFacade.listaLogroPorCentroFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		JuegoDAO oJuegoDAO = new JuegoDAO();
		try {
			if(isLiga) {
				oCachedRowSet = oJuegoDAO.listaJuegoConEquipoPorCentroLigaDAO(oUsuarioTO, oJuegoTO);
			} else {
				oCachedRowSet = oJuegoDAO.listaJuegoConEquipoPorCentroDAO(oUsuarioTO, oJuegoTO);
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoFacade.listaLogroPorCentroFacade");
			log.error("Error:" + e.getMessage());
		}
		return oCachedRowSet;

	}
	
	

	/**
	 * Devuelve una lista de objetos de la tabla deporte
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public ArrayList listaDeporteMultipleFacade(ArrayList lista, boolean isMultiple) {
		ArrayList temp = new ArrayList();
		
		DeporteTO oDeporteTO = null;
		for(int i=0;i<lista.size();i++) {
			oDeporteTO = (DeporteTO) lista.get(i);
			if(isMultiple) {
				if(!oDeporteTO.getEmpate().equals(Constants.JUEGO_MULTIPLE_EQUIPO)) {
					temp.add(lista.get(i));
				}
			} else {
				if(oDeporteTO.getEmpate().equals(Constants.JUEGO_MULTIPLE_EQUIPO)) {
					temp.add(lista.get(i));
				}
			}
		}
		
		lista.removeAll(temp);
		return lista;
	}
	public ArrayList listaDeporteFacade() throws Exception {
		return listaDeporteFacade(true);
	}
	public ArrayList listaDeporteFacade(boolean show5toInning) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaDeporteFacade");
		ArrayList lista = new ArrayList();
		CachedRowSet oCachedRowSet = new CachedRowSet();
		DeporteDAO oDeporteDAO = new DeporteDAO();
		DeporteTO oDeporteTO = new DeporteTO();
		try {
			oCachedRowSet = oDeporteDAO.listaDeporteDAO(true,show5toInning);
			while (oCachedRowSet.next()) {
				oDeporteTO = new DeporteTO();
				oDeporteTO.setIdDeporte(oCachedRowSet.getString("id_deporte"));
				oDeporteTO.setDescDeporte(oCachedRowSet.getString("desc_deporte"));
				oDeporteTO.setEmpate(oCachedRowSet.getString("empate"));
				lista.add(oDeporteTO);
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaDeporteFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return lista;
	}

	/**
	 * Devuelve una lista de objetos de la tabla status
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaDeporteActivoFacade(boolean listar5toIning) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaDeporteActivoFacade");
		ArrayList lista = new ArrayList();
		CachedRowSet oCachedRowSet = new CachedRowSet();
		DeporteDAO oDeporteDAO = new DeporteDAO();
		DeporteTO oDeporteTO = new DeporteTO();
		try {
			oCachedRowSet = oDeporteDAO.listaDeporteDAO(true,listar5toIning);
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaDeporteActivoFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		log.info("Finalizado ejecucion de UsuarioFacade.listaDeporteActivoFacade");
		return oCachedRowSet;
	}
	
	/**
	 * Devuelve una lista de objetos de la tabla status
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaDeporteCombinacionActivoFacade(boolean listar5toIning) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaDeporteActivoFacade");
		ArrayList lista = new ArrayList();
		CachedRowSet oCachedRowSet = new CachedRowSet();
		DeporteDAO oDeporteDAO = new DeporteDAO();
		DeporteTO oDeporteTO = new DeporteTO();
		UsuarioIF usuario = Constants.getUserSession(request);
		try {
			oCachedRowSet = oDeporteDAO.listaDeporteCombinacionDAO(true,listar5toIning,usuario);
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaDeporteActivoFacade");
			e.printStackTrace();
			throw e;
		}
		log.info("Finalizado ejecucion de UsuarioFacade.listaDeporteActivoFacade");
		return oCachedRowSet;
	}
	

	/**
	 * Devuelve una lista de objetos de la tabla status
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public ArrayList listaDeporteConJuegoAbiertoFacade(boolean listar5toIning) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaDeporteConJuegoAbiertoFacade");
		ArrayList lista = new ArrayList();
		DeporteDAO oDeporteDAO = new DeporteDAO();
		DeporteTO oDeporteTO = new DeporteTO();
		try {
			 lista = oDeporteDAO.listaDeporteConJuegoAbiertoDAO(true,listar5toIning);
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaDeporteConJuegoAbiertoFacade");
			log.error("Error:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		log.info("Finalizado ejecucion de UsuarioFacade.listaDeporteConJuegoAbiertoFacade");
		return lista;
	}
	
	/**
	 * Devuelve una lista de objetos de la tabla status
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaJuegoAbiertoFacade(UsuarioIF oUsuarioTO, DeporteTO oDeporteTO, boolean isOnlyTotal) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaJuegoAbiertoFacade");
		ArrayList lista = new ArrayList();
		CachedRowSet oCachedRowSet = new CachedRowSet();
		JuegoDAO oJuegoDAO = new JuegoDAO();
		RegisterGameActionForm juego = null;
		String liga = "";
		try {
			oCachedRowSet = oJuegoDAO.cargarListaJuegoApuestaCompletoDAO(oUsuarioTO, isOnlyTotal);
			/*
			oCachedRowSet = oJuegoDAO.listaJuegoAbiertoDAO(oUsuarioTO, oDeporteTO,false);
			
			while (oCachedRowSet.next()) {
			
				juego = new RegisterGameActionForm();

				juego.setIdJuego(oCachedRowSet.getString("id_juego"));
				juego.setIdUsuario(oUsuarioTO.getIdUsuario());
				
				if(oCachedRowSet.getRow()>=52){
					int x=0;
				}
				juego = consultarJuegoCompletoFacade(juego, oUsuarioTO);

				if (juego != null) {
					System.out.print(" - ");
					System.out.print(oCachedRowSet.getRow());
					System.out.print("\n");
					if (!liga.equals(oCachedRowSet.getString("id_liga"))) {
						juego.setPrimero(true);
					}
					liga = oCachedRowSet.getString("id_liga");
					if (oCachedRowSet.next()) {
						if (!liga.equals(oCachedRowSet.getString("id_liga"))) {
							juego.setUltimo(true);
						}
					} else {
						juego.setUltimo(true);
					}
					oCachedRowSet.previous();

					juego.setHora(oCachedRowSet.getString("hora"));
					lista.add(juego);
				}
			}
			*/
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaJuegoAbiertoFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}
	
	/**
	 * Devuelve una lista de objetos de la tabla status
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaJuegoAbiertoHoyFacade(UsuarioIF oUsuarioTO, DeporteTO oDeporteTO, boolean isOnlyTotal) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaJuegoAbiertoFacade");
		ArrayList lista = new ArrayList();
		CachedRowSet oCachedRowSet = new CachedRowSet();
		JuegoDAO oJuegoDAO = new JuegoDAO();
		RegisterGameActionForm juego = null;
		String liga = "";
		try {
			oCachedRowSet = oJuegoDAO.cargarListaJuegoApuestaCompletoHoyDAO(oUsuarioTO, isOnlyTotal);
			/*
			oCachedRowSet = oJuegoDAO.listaJuegoAbiertoDAO(oUsuarioTO, oDeporteTO,false);
			
			while (oCachedRowSet.next()) {
			
				juego = new RegisterGameActionForm();

				juego.setIdJuego(oCachedRowSet.getString("id_juego"));
				juego.setIdUsuario(oUsuarioTO.getIdUsuario());
				
				if(oCachedRowSet.getRow()>=52){
					int x=0;
				}
				juego = consultarJuegoCompletoFacade(juego, oUsuarioTO);

				if (juego != null) {
					System.out.print(" - ");
					System.out.print(oCachedRowSet.getRow());
					System.out.print("\n");
					if (!liga.equals(oCachedRowSet.getString("id_liga"))) {
						juego.setPrimero(true);
					}
					liga = oCachedRowSet.getString("id_liga");
					if (oCachedRowSet.next()) {
						if (!liga.equals(oCachedRowSet.getString("id_liga"))) {
							juego.setUltimo(true);
						}
					} else {
						juego.setUltimo(true);
					}
					oCachedRowSet.previous();

					juego.setHora(oCachedRowSet.getString("hora"));
					lista.add(juego);
				}
			}
			*/
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaJuegoAbiertoFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}
	
	/**
	 * Devuelve una lista de objetos de la tabla status
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public int listaJuegoAbiertoTotalFacade(UsuarioIF oUsuarioTO, DeporteTO oDeporteTO) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaJuegoAbiertoTotalFacade");
		ArrayList lista = new ArrayList();
		CachedRowSet oCachedRowSet = new CachedRowSet();
		JuegoDAO oJuegoDAO = new JuegoDAO();
		RegisterGameActionForm juego = null;
		String liga = "";
		try {
			oCachedRowSet = oJuegoDAO.listaJuegoAbiertoDAO(oUsuarioTO, oDeporteTO,true);
			return oCachedRowSet.size();
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaJuegoAbiertoTotalFacade");
			log.error("Error:" + e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}
	

	/**
	 * Construye la consulta de lista de los registros de la tabla juego
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet consultarJuegoListaFacade(JuegoIF oJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoFacade.consultarJuegoListaFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		JuegoDAO oJuegoDAO = new JuegoDAO();
		UsuarioIF oUsuarioTO = null;
		try {
			oCachedRowSet = oJuegoDAO.listaJuegoDAO(oUsuarioTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoFacade.consultarJuegoListaFacade");
			log.error("Error:" + e.getMessage());
		}
		return oCachedRowSet;
	}

	public synchronized boolean  agregarJuegoFacade(ActionForm form) throws NotValidReferenceException, SessionInvalidException, Exception {
		return agregarJuegoFacade(form, false); 
	}
	public synchronized boolean  agregarJuegoFacade(ActionForm form, boolean multiple) throws NotValidReferenceException, SessionInvalidException, Exception {
		return agregarJuegoFacade(form,multiple,false,null);
	}
	public synchronized boolean  agregarJuegoFacade(ActionForm form, boolean multiple, boolean isMultiEquipo) throws NotValidReferenceException, SessionInvalidException, Exception {
		return agregarJuegoFacade(form,multiple,isMultiEquipo,null);
	}
	
	public synchronized boolean  agregarJuegoFacade(ActionForm form, boolean multiple, boolean isMultiEquipo,UsuarioIF oUsuarioIF) throws NotValidReferenceException, SessionInvalidException, Exception {

		RegisterGameActionForm forma = (RegisterGameActionForm) form;
		EjecutorSql oEjecutorSql = new EjecutorSql();

		if(oUsuarioIF==null) {
			oUsuarioIF = Constants.getUserSession(request);
		}

		JuegoIF oJuegoIF = (JuegoIF) form;
		JuegoEquipoIF oJuegoEquipoIF = (JuegoEquipoIF) form;
		UsuarioJuegoEquipoIF oUsuarioJuegoEquipoIF = (UsuarioJuegoEquipoIF) form;
		UsuarioJuegoEquipoIF oUsuarioJuegoEquipoDosIF = null;
		JuegoEquipoLanzadorIF oJuegoEquipoLanzadorIF = (JuegoEquipoLanzadorIF) form;
		UsuarioJuegoIF oUsuarioJuegoTO = new UsuarioJuegoTO();
		
		UsuarioJuegoEquipoIF oUsuarioJuegoEquipoTEMP = new UsuarioJuegoEquipoTO();

		JuegoDAO oJuegoDAO = new JuegoDAO(oEjecutorSql);
		JuegoEquipoDAO oJuegoEquipoDAO = new JuegoEquipoDAO(oEjecutorSql);
		UsuarioJuegoEquipoDAO oUsuarioJuegoEquipoDAO = new UsuarioJuegoEquipoDAO(oEjecutorSql);
		JuegoEquipoLanzadorDAO oJuegoEquipoLanzadorDAO = new JuegoEquipoLanzadorDAO(oEjecutorSql);
		UsuarioJuegoDAO oUsuarioJuegoDAO = new UsuarioJuegoDAO(oEjecutorSql);
		
		boolean isError = false;

		boolean soloLogros = false;

		try {

			oJuegoIF.setIdUsuario(oUsuarioIF.getIdUsuario());

			oEjecutorSql.getConnection().setAutoCommit(false);

			// insertamos el juego
			if (oJuegoIF.getIdJuego() == null || oJuegoIF.getIdJuego().trim().equals("")) {
				oJuegoIF.setFechaIni(Constants.getFechaCortaSQL(oJuegoIF.getFechaIni()).concat(" ").concat(forma.getHora()).concat(":").concat(forma.getMinuto()).concat(":00"));
				oJuegoIF.setDominioActual(oUsuarioIF.getDominio());
				oJuegoIF.setDominioAnterior(oUsuarioIF.getDominio());
				oJuegoDAO.insertarJuegoDAO(oJuegoIF);

			} else {
				oJuegoIF.setDominioActual(oUsuarioIF.getDominio());
				/* Si el juego esta borrador, podemos eliminar sus hijos */
				if (oJuegoIF.getIdStatusJuego().equals(Constants.STATUS_JUEGO_BORRADOR)) {
					oJuegoIF.setFechaIni(Constants.getFechaCortaSQL(oJuegoIF.getFechaIni()).concat(" ").concat(forma.getHora()).concat(":").concat(forma.getMinuto()).concat(":00"));

					oJuegoDAO.actualizarJuegoDAO(oJuegoIF);
					oJuegoDAO.eliminarDetalleJuegoDAO(oJuegoIF);
				} else {
					// Si el juego esta abierto podemos actualizar la fecha y la hora
					if(oUsuarioIF.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR) && oJuegoIF.getIdStatusJuego().equals(Constants.STATUS_JUEGO_ABIERTO)) {
						String fechaIni = oJuegoIF.getFechaIni(); 
						oJuegoIF.setFechaIni(Constants.getFechaCortaSQL(oJuegoIF.getFechaIni()).concat(" ").concat(forma.getHora()).concat(":").concat(forma.getMinuto()).concat(":00"));
						oJuegoDAO.actualizarFechaHoraJuegoDAO(oJuegoIF);
						oJuegoIF.setFechaIni(fechaIni);
					}
					// solo se actualizan los montos y logros
					soloLogros = true;
				}
			}

			if (soloLogros) {
				// bucamos los logros anteriores
				
				CachedRowSet oCachedRowSetLogrosAdmin = null;
				CachedRowSet oCachedRowSet = oJuegoEquipoDAO.listaJuegoEquipoPorJuegoDAO(oJuegoIF);
				for (int i = 0; oCachedRowSet.next(); i++) {
					oUsuarioJuegoEquipoIF.setIdUsuarioJuegoEquipo("0");
					oUsuarioJuegoEquipoIF.setIdUsuario(oUsuarioIF.getIdRol().equals(Constants.ROL_ADMINISTRADOR)?Constants.ID_USUARIO_ADMINISTRADOR:oUsuarioIF.getIdUsuario());
					oUsuarioJuegoEquipoIF.setIdJuegoEquipo(oCachedRowSet.getString("id_juego_equipo"));
					oUsuarioJuegoEquipoIF.setFechaSis(oJuegoIF.getFechaSis());
					
					// si el usuario es administrador bloquea los juegos 
					// modificados por las demas taquillas
					// si no inactiva los logros anteriores del usuario
					oUsuarioJuegoEquipoDAO.cerrarLogrosAbiertosUsuarioJuegoEquipoDAO(oUsuarioJuegoEquipoIF);

					if(!multiple) {
						oUsuarioJuegoEquipoIF.setSpread(Constants.concatDecimal(forma.get_Spread()[i], forma.get_SpreadDecimal()[i]));
						oUsuarioJuegoEquipoIF.setSpreadLogro(Constants.parseInt(forma.get_SpreadLogro()[i]));
						oUsuarioJuegoEquipoIF.setSuperSpread(Constants.concatDecimal(forma.get_SuperSpread()[i], forma.get_SuperSpreadDecimal()[i]));
						oUsuarioJuegoEquipoIF.setSuperSpreadLogro(Constants.parseInt(forma.get_SuperSpreadLogro()[i]));
						oUsuarioJuegoEquipoIF.setTotal(Constants.concatDecimal(forma.get_Total()[i], forma.get_TotalDecimal()[i]));
						oUsuarioJuegoEquipoIF.setTotalLogro(Constants.parseInt(forma.get_TotalLogro()[i]));
						oUsuarioJuegoEquipoIF.setMoneyLine(Constants.parseInt(forma.get_MoneyLine()[i]));
						oUsuarioJuegoEquipoIF.setIdStatusJuego(oJuegoIF.getIdStatusJuego());
						oUsuarioJuegoEquipoIF.setDesactivado("0");
						oUsuarioJuegoEquipoDAO.insertarUsuarioJuegoEquipoDAO(oUsuarioJuegoEquipoIF);

						// insertamos los lanzadores por equipo
						try {
							if (forma.get_IdLanzador()[i] != null && !forma.get_IdLanzador()[i].trim().equals("") && !forma.get_IdLanzador()[i].trim().equals("0")) {
								oJuegoEquipoLanzadorIF.setIdJuegoEquipo(oJuegoEquipoIF.getIdJuegoEquipo());
								oJuegoEquipoLanzadorIF.setIdLanzador(forma.get_IdLanzador()[i]);
								oJuegoEquipoLanzadorDAO.insertarJuegoEquipoLanzadorDAO(oJuegoEquipoLanzadorIF);
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							// no pasa nada
						}
					} else {
						// consultamos los logros del juego del administrador
						oUsuarioJuegoEquipoTEMP.setIdUsuario("0");
						oUsuarioJuegoEquipoTEMP.setIdJuegoEquipo(oUsuarioJuegoEquipoIF.getIdJuegoEquipo());
						oUsuarioJuegoEquipoDAO.listaLogrosAdministradorUsuarioJuegoEquipoPorJuegoDAO(oUsuarioJuegoEquipoTEMP);
						if(oUsuarioJuegoEquipoTEMP.getIdUsuario().equals("1")) {
							oUsuarioJuegoEquipoIF.setSpread(oUsuarioJuegoEquipoTEMP.getSpread());
							oUsuarioJuegoEquipoIF.setSpreadLogro(oUsuarioJuegoEquipoTEMP.getSpreadLogro());
							oUsuarioJuegoEquipoIF.setSuperSpread(oUsuarioJuegoEquipoTEMP.getSuperSpread());
							oUsuarioJuegoEquipoIF.setSuperSpreadLogro(oUsuarioJuegoEquipoTEMP.getSuperSpreadLogro());
							oUsuarioJuegoEquipoIF.setTotal(oUsuarioJuegoEquipoTEMP.getTotal());
							if(oUsuarioJuegoEquipoTEMP.getTotal()!=null && Double.parseDouble(oUsuarioJuegoEquipoTEMP.getTotal())!=0) {
								if(oUsuarioIF.getLogrosAltaBaja()!=null && Integer.parseInt(oUsuarioIF.getLogrosAltaBaja())!=0) {
									oUsuarioJuegoEquipoIF.setTotalLogro(oUsuarioIF.getLogrosAltaBaja());
								} else {
									oUsuarioJuegoEquipoIF.setTotalLogro(oUsuarioJuegoEquipoTEMP.getTotalLogro());
								}
							} else {
								oUsuarioJuegoEquipoIF.setTotalLogro("0");
							}
							oUsuarioJuegoEquipoIF.setMoneyLine(oUsuarioJuegoEquipoTEMP.getMoneyLine());
							oUsuarioJuegoEquipoIF.setIdStatusJuego(oJuegoIF.getIdStatusJuego());
							oUsuarioJuegoEquipoIF.setDesactivado("0");
							oUsuarioJuegoEquipoDAO.insertarUsuarioJuegoEquipoDAO(oUsuarioJuegoEquipoIF);
						} else {
							throw new Exception("No se econtraron los logros del administrador");
						}
					}
					
					
				}

			} else {
				boolean is5to;
				boolean is1ro;
				boolean is0ro;
				for (int i = 0; i < forma.get_IdEquipo().length; i++) {
					// insertamos los equipos
					if(Constants.isNullEntero(forma.get_IdEquipo()[i])) {
						//if (i == 2 && Constants.isNullEntero(forma.get_Referencia()[i])) {
						break;
					}
					if(forma.get_IdEquipo()[i].equals(Constants.ID_EQUIPO_EMPATE)) {
						if(Constants.isNullEntero(forma.get_MoneyLine()[i])) {
							break;
						}
					}
					
					oJuegoEquipoIF.setIdJuegoEquipo("0");
					oJuegoEquipoIF.setIdJuego(oJuegoIF.getIdJuego());
					oJuegoEquipoIF.setIdEquipo(forma.get_IdEquipo()[i]);
					oJuegoEquipoIF.setReferencia(forma.get_Referencia()[i]);
					is5to = oJuegoIF.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL_5) || oJuegoIF.getIdDeporte().equals(Constants.ID_EQUIPO_BASKETBALL_MITAD) || oJuegoIF.getIdDeporte().equals(Constants.ID_EQUIPO_SOCCER_MITAD);
					is1ro = oJuegoIF.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL_1);
					is0ro = oJuegoIF.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL_0);
					if(!isMultiEquipo) {
						oJuegoEquipoIF.setReferenciaRunline((is5to?"2":(is1ro?"8":(is0ro?"4":"1"))).concat((forma.get_Referencia()[i]).substring(is5to || is1ro || is0ro?1:0)));
						oJuegoEquipoIF.setReferenciaSuperRunline("3".concat((forma.get_Referencia()[i])));
						oJuegoEquipoIF.setReferenciaAB("ABC".substring(i,i+1).concat(forma.get_Referencia()[0]));
					} else {
						oJuegoEquipoIF.setReferenciaRunline("0");
						oJuegoEquipoIF.setReferenciaSuperRunline("0");
						oJuegoEquipoIF.setReferenciaAB("0");
					}
					// antes de insertar el juego validamos que la referencia no este incluida para ese dia
					if(oJuegoEquipoDAO.isReferenciaValidEquipoDAO(oJuegoEquipoIF, oJuegoIF.getFechaIni().substring(0,10))) {
						oJuegoEquipoDAO.insertarJuegoEquipoDAO(oJuegoEquipoIF);
					} else {
						throw new NotValidReferenceException("La refencia no es valida");
					}

					// insertamos los valores por equipos de los logros
					oUsuarioJuegoEquipoIF.setIdUsuarioJuegoEquipo("0");
					oUsuarioJuegoEquipoIF.setIdUsuario(oUsuarioIF.getIdRol().equals(Constants.ROL_ADMINISTRADOR)?Constants.ID_USUARIO_ADMINISTRADOR:oUsuarioIF.getIdUsuario());
					oUsuarioJuegoEquipoIF.setIdJuegoEquipo(oJuegoEquipoIF.getIdJuegoEquipo());
					oUsuarioJuegoEquipoIF.setFechaSis(oJuegoIF.getFechaSis());
					if(!isMultiEquipo) {
						oUsuarioJuegoEquipoIF.setSpread(Constants.concatDecimal(forma.get_Spread()[i], forma.get_SpreadDecimal()[i]));
						oUsuarioJuegoEquipoIF.setSpreadLogro(Constants.parseInt(forma.get_SpreadLogro()[i]));
						oUsuarioJuegoEquipoIF.setSuperSpread(Constants.concatDecimal(forma.get_SuperSpread()[i], forma.get_SuperSpreadDecimal()[i]));
						oUsuarioJuegoEquipoIF.setSuperSpreadLogro(Constants.parseInt(forma.get_SuperSpreadLogro()[i]));
						oUsuarioJuegoEquipoIF.setTotal(Constants.concatDecimal(forma.get_Total()[i], forma.get_TotalDecimal()[i]));
						oUsuarioJuegoEquipoIF.setTotalLogro(Constants.parseInt(forma.get_TotalLogro()[i]));
						
					} else {
						oUsuarioJuegoEquipoIF.setSpread("0");
						oUsuarioJuegoEquipoIF.setSpreadLogro("0");
						oUsuarioJuegoEquipoIF.setSuperSpread("0");
						oUsuarioJuegoEquipoIF.setSuperSpreadLogro("0");
						oUsuarioJuegoEquipoIF.setTotal("0");
						oUsuarioJuegoEquipoIF.setTotalLogro("0");
					}
					oUsuarioJuegoEquipoIF.setMoneyLine(Constants.parseInt(forma.get_MoneyLine()[i]));
					oUsuarioJuegoEquipoIF.setIdStatusJuego(oJuegoIF.getIdStatusJuego());
					oUsuarioJuegoEquipoDAO.insertarUsuarioJuegoEquipoDAO(oUsuarioJuegoEquipoIF);

					if(!isMultiEquipo) {
						
						// si es basket y el juego esta en borrador, buscaremos el juego mitad de basket para ajustar 
						// los factores y logros del alta/baja y el runline
						if(oJuegoIF.getIdDeporte().equals(Constants.ID_EQUIPO_BASKETBALL) && oJuegoIF.getIdStatusJuego().equals(Constants.STATUS_JUEGO_BORRADOR)) {  
							
							// buscamos el juego mitad
							oUsuarioJuegoEquipoDosIF = new UsuarioJuegoEquipoTO();
							oUsuarioJuegoEquipoDAO.cargarUsuarioJuegoEquipoDAO(oUsuarioJuegoEquipoDosIF,oJuegoEquipoIF.getIdJuego(),oJuegoEquipoIF.getIdEquipo());
							
							// si lo encuentra
							if(oUsuarioJuegoEquipoDosIF!=null) {
								double spread = Double.parseDouble(oUsuarioJuegoEquipoIF.getSpread());
								spread = Constants.logroBaskquetMitad(spread, "RL");

								double total = Double.parseDouble(oUsuarioJuegoEquipoIF.getTotal());
								total = Constants.logroBaskquetMitad(total, "AB");
								
								
								oUsuarioJuegoEquipoDosIF.setSpread(String.valueOf(spread));
								oUsuarioJuegoEquipoDosIF.setSpreadLogro(oUsuarioJuegoEquipoIF.getSpreadLogro());
								oUsuarioJuegoEquipoDosIF.setSuperSpread("0");
								oUsuarioJuegoEquipoDosIF.setSuperSpreadLogro("0");
								oUsuarioJuegoEquipoDosIF.setTotal(String.valueOf(total));
								oUsuarioJuegoEquipoDosIF.setTotalLogro(oUsuarioJuegoEquipoIF.getTotalLogro());
								oUsuarioJuegoEquipoDAO.actualizarUsuarioJuegoEquipoDAO(oUsuarioJuegoEquipoDosIF);
								
							}
						}
						
						
						// insertamos los lanzadores por equipo
						try {
							if (forma.get_IdLanzador()[i] != null && !forma.get_IdLanzador()[i].trim().equals("") && !forma.get_IdLanzador()[i].trim().equals("0")) {
								oJuegoEquipoLanzadorIF.setIdJuegoEquipo(oJuegoEquipoIF.getIdJuegoEquipo());
								oJuegoEquipoLanzadorIF.setIdLanzador(forma.get_IdLanzador()[i]);
								oJuegoEquipoLanzadorDAO.insertarJuegoEquipoLanzadorDAO(oJuegoEquipoLanzadorIF);
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							// no pasa nada
						}
					}
				}
			}

		} catch (NotValidReferenceException e) {
			isError = true;
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			isError = true;
			e.printStackTrace();
			throw e;
		} catch (ConexionManejaError e) {
			isError = true;
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			isError = true;
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (isError) {
					oEjecutorSql.rollback();
				}
				oEjecutorSql.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	/**
	 * Devuelve una lista de objetos de la tabla equipo
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public ArrayList equipoEmpateFacade() throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.equipoEmpateFacade");
		EquipoDAO oEquipoDAO = new EquipoDAO();
		ArrayList lista = new ArrayList();
		try {
			lista = oEquipoDAO.equipoEmpateDAO();
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.equipoEmpateFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return lista;
	}

	/**
	 * Devuelve una lista de objetos de la tabla equipo
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public ArrayList listaEquipoEmpateFacade() throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaEquipoEmpateFacade");
		EquipoDAO oEquipoDAO = new EquipoDAO();
		ArrayList lista = new ArrayList();
		try {
			lista = oEquipoDAO.listEquipoEmpateDAO();
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaEquipoEmpateFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return lista;
	}

	/**
	 * Devuelve una lista de objetos de la tabla equipo
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public ArrayList listaEquipoFacade() throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaEquipoFacade");
		EquipoDAO oEquipoDAO = new EquipoDAO();
		ArrayList lista = new ArrayList();
		try {
			lista = oEquipoDAO.listEquipoDAO();
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaEquipoFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return lista;
	}

	/**
	 * Devuelve una lista de objetos de la tabla equipo
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public String listaDeporteEmpateJavaScriptFacade() throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaDeporteEmpateJavaScriptFacade");
		DeporteDAO oDeporteDAO = new DeporteDAO();
		String lista = null;
		try {
			lista = oDeporteDAO.listDeporteEmpateJavaScriptDAO();
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaDeporteEmpateJavaScriptFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return lista;
	}

	/**
	 * Devuelve una lista de objetos de la tabla equipo
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaStatusJuegoFacade() throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaStatusJuegoFacade");
		StatusJuegoDAO oStatusJuegoDAO = new StatusJuegoDAO();
		CachedRowSet oCachedRowSet = null;
		try {
			oCachedRowSet = oStatusJuegoDAO.listaStatusJuegoDAO();
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaStatusJuegoFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	
	/**
	 * Devuelve una lista de objetos de la tabla equipo
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaStatusTaquillaJuegoFacade() throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaStatusTaquillaJuegoFacade");
		StatusJuegoDAO oStatusJuegoDAO = new StatusJuegoDAO();
		CachedRowSet oCachedRowSet = null;
		try {
			oCachedRowSet = oStatusJuegoDAO.listaStatusTaquillaJuegoDAO();
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaStatusTaquillaJuegoFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}
	
	/**
	 * Devuelve una lista de objetos de la tabla equipo
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaStatusSoloJugadaFacade() throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaStatusSoloJugadaFacade");
		StatusJugadaDAO oStatusJuegoDAO = new StatusJugadaDAO();
		CachedRowSet oCachedRowSet = null;
		try {
			oCachedRowSet = oStatusJuegoDAO.listaStatusSoloJugadaDAO();
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaStatusSoloJugadaFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}
	

	/**
	 * Abre el juego para que este disponible para las jugadas
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public JugadaTO insertarJugadaFacade(ArrayList listaJugadas, UsuarioIF oUsuarioTO) throws GameClosedException, UserWithOutCreditException, GameOpenException, TopePorCombinacionExcedidoException, Exception {
		log.info("Iniciando ejecucion de JuegoFacade.registrarJugadaFacade");
		EjecutorSql oEjecutorSql = new EjecutorSql();
		JugadaTO oJugadaTO = new JugadaTO();
		CuentaJugadorTO oCuentaJugadorTO = new CuentaJugadorTO();
		CalculadoraForm oCalculadoraForm = null;
		JugadaJuegoEquipoTO oJugadaJuegoEquipoTO = new JugadaJuegoEquipoTO();
		UsuarioJuegoEquipoTO oUsuarioJuegoEquipoTO = new UsuarioJuegoEquipoTO();
		JuegoTO oJuegoTO = new JuegoTO();
		CombinacionTO oCombinacionTO = new CombinacionTO();

		JugadaDAO oJugadaDAO = new JugadaDAO(oEjecutorSql);
		JugadaJuegoEquipoDAO oJugadaJuegoEquipoDAO = new JugadaJuegoEquipoDAO(oEjecutorSql);
		CuentaJugadorDAO oCuentaJugadorDAO = new CuentaJugadorDAO(oEjecutorSql);
		UsuarioJuegoEquipoDAO oUsuarioJuegoEquipoDAO = new UsuarioJuegoEquipoDAO(oEjecutorSql);
		JuegoDAO oJuegoDAO = new JuegoDAO(oEjecutorSql);
		CombinacionDAO oCombinacionDAO = new CombinacionDAO(oEjecutorSql);

		boolean isError = false;
		try {
			Calendar fechaSis = null;
			Date fechaJuego = null;
			Date fechaUltimoJuego = null; 
			
			if (listaJugadas.size() > 0) {
				/*
				 * Pedimos el saldo del jugador y lo comparamos contra la
				 * jugada
				 */
				double saldo = 0;
				if (oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR)) {
					saldo = UsuarioTO.getSaldo(oUsuarioTO.getIdUsuario());
				}

				
				// iniciamos la transaccion
				oEjecutorSql.getConnection().setAutoCommit(false);

				oCalculadoraForm = (CalculadoraForm) listaJugadas.get(0);

				double montoApostar = Double.parseDouble(oCalculadoraForm.getMontoApostar());
				if (oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR)) {
					if (montoApostar > saldo) {
						throw new UserWithOutCreditException("El monto de la jugada es mayor al monto disponible");
					}
				}

				oJugadaTO.getFechaExp();
				oJugadaTO.setMontoApostado(oCalculadoraForm.getMontoApostar());
				oJugadaTO.setMontoPremio(String.valueOf(Constants.getMontoMaximoPago(oCalculadoraForm.getMontoApostar(), oCalculadoraForm.getMontoPremio())));
				oJugadaTO.setMontoPagado("0");
				oJugadaTO.setIdUsuario(oUsuarioTO.getIdUsuario());
				oJugadaTO.setDiasExpira(Integer.parseInt(Constants.parseInt(oUsuarioTO.getDiasVencTicket()))>0?oUsuarioTO.getDiasVencTicket():oUsuarioTO.getSupervisor().getDiasVencTicket());
				StringBuffer detalleEquipo = new StringBuffer();
				String sep = "";
				String sep0 = "(";
				String sep1 = ")";
				String guion = "-";
				for (int i = 0; i < listaJugadas.size(); i++) {
					detalleEquipo.append(sep);
					oCalculadoraForm = (CalculadoraForm) listaJugadas.get(i);
					detalleEquipo.append(oCalculadoraForm.getReferencia());
					detalleEquipo.append(guion);
					detalleEquipo.append(oCalculadoraForm.getEquipo());
					detalleEquipo.append(sep0);
					detalleEquipo.append(oCalculadoraForm.getTipo());
					detalleEquipo.append(sep1);
					sep="; ";
				}
				oJugadaTO.setDetalleEquipo(detalleEquipo.toString());
				
				if(listaJugadas.size()>0) {
					
					// cargamos los juegos en un map
					StringBuffer idJuegos = new StringBuffer();
					String separador = "";
					for (int i = 0; i < listaJugadas.size(); i++) {
						oCalculadoraForm = (CalculadoraForm) listaJugadas.get(i);
						idJuegos.append(separador).append(oCalculadoraForm.getJuego());
						separador = ",";
					}
					HashMap mapa = oJuegoDAO.cargarJuegoPorIdDAO(idJuegos.toString());
					

					// aplicamos las reglas a los juegos jugados
					for (int i = 0; i < listaJugadas.size(); i++) {
						oCalculadoraForm = (CalculadoraForm) listaJugadas.get(i);
						// buscamos el juego para revisar su status
						oJuegoTO = (JuegoTO)mapa.get(oCalculadoraForm.getJuego());

						// revisamos el status
						if(!oJuegoTO.getIdStatusJuego().equals(Constants.STATUS_JUEGO_ABIERTO)) {
							throw new GameClosedException("El juego ya no esta abierto");
						}
						// revisamos la fecha
						fechaSis = Date.getCalendar();
						fechaSis.add(Calendar.MINUTE, Integer.parseInt(Constants.isNull(oJuegoTO.getMinutosCierre(),"0")));
						fechaJuego = Constants.getFechaLargaSqlToJava(oJuegoTO.getFechaIni());

						if(Date.convert(fechaSis).getTime()>=fechaJuego.getTime()) {
							throw new GameClosedException("El juego ya no esta abierto");
						}
					}

					// asignamos los items en esta jugada
					oJugadaTO.setItemsJugada(String.valueOf(listaJugadas.size()));
					

					// INICIO DE VALIDACION DE COMBINACION
					// revisamos la combinacion de las jugadas parley por taquilla
					ArrayList listParley = new ArrayList();
					StringBuffer parley = new StringBuffer();
					for (int i = 0; i < listaJugadas.size(); i++) {
						oCalculadoraForm = (CalculadoraForm) listaJugadas.get(i);

						oJugadaJuegoEquipoTO.setIdJugada(oJugadaTO.getIdJugada());
						oJugadaJuegoEquipoTO.setIdUsuarioJuegoEquipo(oCalculadoraForm.getCodigo());
						oJugadaJuegoEquipoTO.setTipo(oCalculadoraForm.getTipo());
						
						parley.setLength(0);
						parley.append(oJugadaJuegoEquipoTO.getIdUsuarioJuegoEquipo());
						parley.append("-");
						parley.append(oJugadaJuegoEquipoTO.getTipo());
						parley.append("-");
						listParley.add(parley.toString());

					}
					// ordenamos la combinacion
					Collections.sort(listParley);
					parley.setLength(0);
					for (int i = 0; i < listParley.size(); i++) {
						parley.append(listParley.get(i));
					}
					
					//buscamos la combinacion en la tabla de combinaciones
					double topePorCombinacion = 0;

					topePorCombinacion = Double.parseDouble(oUsuarioTO.getTopePorCombinacion());

					oCombinacionTO.setCombinacion(parley.toString());
					oCombinacionTO.setIdUsuario(oJugadaTO.getIdUsuario());
					oCombinacionTO.setMontoApuesta(oJugadaTO.getMontoApostado());
					
					double montoPorCombinacion = oCombinacionDAO.montoCombinacionPorUsuarioDAO(oCombinacionTO)[0];
					double montoApuesta = Double.parseDouble(oJugadaTO.getMontoApostado());
					
					if (listParley.size()>1) {
						if (topePorCombinacion > 0 && (montoPorCombinacion + montoApuesta) > topePorCombinacion) {
							StringBuffer sb = new StringBuffer();
							sb.append("El monto apostado sobrepasa el tope de apuestas por combinacion. Tope maximo = ");
							sb.append(topePorCombinacion);
							sb.append(", Monto acumulado = ");
							sb.append(montoPorCombinacion);
							throw new TopePorCombinacionExcedidoException(sb.toString(),topePorCombinacion, montoPorCombinacion);
						}
					}
					// FIN validacion de combinacion
					
					// insertamos la jugada si paso las reglas
					oJugadaDAO.insertarJugadaDAO(oJugadaTO);

					for (int i = 0; i < listaJugadas.size(); i++) {
						oCalculadoraForm = (CalculadoraForm) listaJugadas.get(i);
						
						// seleccionamos la fecha mayor para luego colocarla en el vencimiento
						if(fechaUltimoJuego==null || fechaUltimoJuego.getTime()<fechaJuego.getTime() ) {
							fechaUltimoJuego = fechaJuego;
						}
						
						oJugadaJuegoEquipoTO.setIdJugada(oJugadaTO.getIdJugada());
						oJugadaJuegoEquipoTO.setIdUsuarioJuegoEquipo(oCalculadoraForm.getCodigo());
						oJugadaJuegoEquipoTO.setTipo(oCalculadoraForm.getTipo());
						
						// validamos que los logros sean los ultimos que estan registrados
						oUsuarioJuegoEquipoTO.setIdJuegoEquipo(oJugadaJuegoEquipoTO.getIdUsuarioJuegoEquipo());
						oUsuarioJuegoEquipoDAO.obtenerUltimoLogroRegistradoDAO(oUsuarioJuegoEquipoTO); // pendiente
						oJugadaJuegoEquipoTO.setIdUsuarioJuegoEquipo(oUsuarioJuegoEquipoTO.getIdUsuarioJuegoEquipo());
						
						oJugadaJuegoEquipoDAO.insertarJugadaJuegoEquipoDAO(oJugadaJuegoEquipoTO);
					}
					
					// actualizamos el monto del premio
					// por si cambiaron los logros
					oJugadaDAO.recalcularPremioJugadaDAO(oJugadaTO, oUsuarioTO);
					
					
					// insertamos la combinacion de la jugada
					oCombinacionTO.setIdJugada(oJugadaTO.getIdJugada());
					oCombinacionDAO.insertarCombinacionDAO(oCombinacionTO);
					
					
				} else {
					// no hay detalle en la jugada
					throw new GameClosedException("No hay detalle de equipos en la jugada");
				}
				
				// colocamos la fecha de vencimiento y actualizamos la jugada
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				oJugadaTO.setFechaExp(Constants.getFechaLargaSQL(sdf.format(fechaUltimoJuego).concat(" 23:59:59"), Integer.parseInt(Constants.isNull(oJugadaTO.getDiasExpira(),"0"))));
				oJugadaDAO.actualizarJugadaDAO(oJugadaTO);
				
				// revisamos la fecha de vencimiento, para que no quede nula desde la base de datos
				oJugadaDAO.actualizarFechaVencimientoJugadaDAO(oJugadaTO);
				

				/* rebajamos el saldo del jugador */
				oCuentaJugadorTO.setReferencia(Constants.numero(oJugadaTO.getIdJugada(), 10));
				oCuentaJugadorTO.setOperacion(Constants.OPERACION_EGRESO);
				oCuentaJugadorTO.setMonto(oCalculadoraForm.getMontoApostar());
				oCuentaJugadorTO.setConcepto(Constants.CONCEPTO_JUGADA);
				oCuentaJugadorTO.setIdJugador(oUsuarioTO.getIdUsuario());
				oCuentaJugadorTO.setIdUsuario(oUsuarioTO.getIdUsuario());
				oCuentaJugadorTO.setTipo(Constants.CUENTA_JUGADOR_TIPO_JUGADA);
				oCuentaJugadorDAO.insertarCuentaJugadorDAO(oCuentaJugadorTO);

			}
		} catch (GameClosedException e) {
			isError = true;
			throw e;
		} catch (UserWithOutCreditException e) {
			isError = true;
			throw e;
		} catch (TopePorCombinacionExcedidoException e) {
			isError = true;
			throw e;
		} catch (Exception e) {
			isError = true;
			log.info("Error en la ejecucion de JuegoFacade.registrarJugadaFacade");
			log.error("Error:" + e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrio un error al intentar eliminar el registro");
		} finally {
			try {
				if (isError) {
					oEjecutorSql.rollback();
				}
				oEjecutorSql.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return oJugadaTO;
	}
	
	/**
	 * Abre el juego para que este disponible para las jugadas
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public JugadaTO insertarJugadaAnimalitosFacade(ArrayList listaJugadas, UsuarioIF oUsuarioTO) throws GameClosedException, UserWithOutCreditException, GameOpenException, TopePorCombinacionExcedidoException, Exception {
		log.info("Iniciando ejecucion de JuegoFacade.registrarJugadaFacade");
		EjecutorSql oEjecutorSql = new EjecutorSql();
		JugadaTO oJugadaTO = new JugadaTO();
		CuentaJugadorTO oCuentaJugadorTO = new CuentaJugadorTO();
		CalculadoraForm oCalculadoraForm = null;
		JugadaJuegoEquipoTO oJugadaJuegoEquipoTO = new JugadaJuegoEquipoTO();
		UsuarioJuegoEquipoTO oUsuarioJuegoEquipoTO = new UsuarioJuegoEquipoTO();
		JuegoTO oJuegoTO = new JuegoTO();
		CombinacionTO oCombinacionTO = new CombinacionTO();

		JugadaDAO oJugadaDAO = new JugadaDAO(oEjecutorSql);
		JugadaJuegoEquipoDAO oJugadaJuegoEquipoDAO = new JugadaJuegoEquipoDAO(oEjecutorSql);
		CuentaJugadorDAO oCuentaJugadorDAO = new CuentaJugadorDAO(oEjecutorSql);
		UsuarioJuegoEquipoDAO oUsuarioJuegoEquipoDAO = new UsuarioJuegoEquipoDAO(oEjecutorSql);
		JuegoDAO oJuegoDAO = new JuegoDAO(oEjecutorSql);
		CombinacionDAO oCombinacionDAO = new CombinacionDAO(oEjecutorSql);

		boolean isError = false;
		try {
			Calendar fechaSis = null;
			Date fechaJuego = null;
			Date fechaUltimoJuego = null; 
			
			if (listaJugadas.size() > 0) {
				/*
				 * Pedimos el saldo del jugador y lo comparamos contra la
				 * jugada
				 */
				double saldo = 0;
				if (oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR)) {
					saldo = UsuarioTO.getSaldo(oUsuarioTO.getIdUsuario());
				}

				
				// iniciamos la transaccion
				oEjecutorSql.getConnection().setAutoCommit(false);

				oCalculadoraForm = (CalculadoraForm) listaJugadas.get(0);

				double montoApostar = Double.parseDouble(oCalculadoraForm.getMontoApostar());
				if (oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR)) {
					if (montoApostar > saldo) {
						throw new UserWithOutCreditException("El monto de la jugada es mayor al monto disponible");
					}
				}

				oJugadaTO.getFechaExp();
				oJugadaTO.setMontoApostado(oCalculadoraForm.getMontoApostar());
				oJugadaTO.setMontoPremio(String.valueOf(Constants.getMontoMaximoPago(oCalculadoraForm.getMontoApostar(), oCalculadoraForm.getMontoPremio())));
				oJugadaTO.setMontoPagado("0");
				oJugadaTO.setIdUsuario(oUsuarioTO.getIdUsuario());
				oJugadaTO.setDiasExpira(Integer.parseInt(Constants.parseInt(oUsuarioTO.getDiasVencTicket()))>0?oUsuarioTO.getDiasVencTicket():oUsuarioTO.getSupervisor().getDiasVencTicket());
				StringBuffer detalleEquipo = new StringBuffer();
				String sep = "";
				String sep0 = "(";
				String sep1 = ")";
				String guion = "-";
				for (int i = 0; i < listaJugadas.size(); i++) {
					detalleEquipo.append(sep);
					oCalculadoraForm = (CalculadoraForm) listaJugadas.get(i);
					detalleEquipo.append(oCalculadoraForm.getReferencia());
					detalleEquipo.append(guion);
					detalleEquipo.append(oCalculadoraForm.getEquipo());
					detalleEquipo.append(sep0);
					detalleEquipo.append(oCalculadoraForm.getTipo());
					detalleEquipo.append("/ Hora: "+oCalculadoraForm.getHoraJuego());
					detalleEquipo.append("/ Loteria: "+oCalculadoraForm.getCampeonato());
					detalleEquipo.append(sep1);
					sep="; ";
				}
				oJugadaTO.setDetalleEquipo(detalleEquipo.toString());
				
				if(listaJugadas.size()>0) {
					
					// cargamos los juegos en un map
					StringBuffer idJuegos = new StringBuffer();
					String separador = "";
					for (int i = 0; i < listaJugadas.size(); i++) {
						oCalculadoraForm = (CalculadoraForm) listaJugadas.get(i);
						idJuegos.append(separador).append(oCalculadoraForm.getJuego());
						separador = ",";
					}
					HashMap mapa = oJuegoDAO.cargarJuegoPorIdDAO(idJuegos.toString());
					

					// aplicamos las reglas a los juegos jugados
					for (int i = 0; i < listaJugadas.size(); i++) {
						oCalculadoraForm = (CalculadoraForm) listaJugadas.get(i);
						// buscamos el juego para revisar su status
						oJuegoTO = (JuegoTO)mapa.get(oCalculadoraForm.getJuego());

						// revisamos el status
						if(!oJuegoTO.getIdStatusJuego().equals(Constants.STATUS_JUEGO_ABIERTO)) {
							throw new GameClosedException("El juego ya no esta abierto");
						}
						// revisamos la fecha
						fechaSis = Date.getCalendar();
						fechaSis.add(Calendar.MINUTE, Integer.parseInt(Constants.isNull(oJuegoTO.getMinutosCierre(),"0")));
						fechaJuego = Constants.getFechaLargaSqlToJava(oJuegoTO.getFechaIni());

						if(Date.convert(fechaSis).getTime()>=fechaJuego.getTime()) {
							throw new GameClosedException("El juego ya no esta abierto");
						}
					}

					// asignamos los items en esta jugada
					oJugadaTO.setItemsJugada(String.valueOf(listaJugadas.size()));
					

					// INICIO DE VALIDACION DE COMBINACION
					// revisamos la combinacion de las jugadas parley por taquilla
					ArrayList listParley = new ArrayList();
					StringBuffer parley = new StringBuffer();
					for (int i = 0; i < listaJugadas.size(); i++) {
						oCalculadoraForm = (CalculadoraForm) listaJugadas.get(i);

						oJugadaJuegoEquipoTO.setIdJugada(oJugadaTO.getIdJugada());
						oJugadaJuegoEquipoTO.setIdUsuarioJuegoEquipo(oCalculadoraForm.getCodigo());
						oJugadaJuegoEquipoTO.setTipo(oCalculadoraForm.getTipo());
						
						parley.setLength(0);
						parley.append(oJugadaJuegoEquipoTO.getIdUsuarioJuegoEquipo());
						parley.append("-");
						parley.append(oJugadaJuegoEquipoTO.getTipo());
						parley.append("-");
						listParley.add(parley.toString());

					}
					// ordenamos la combinacion
					Collections.sort(listParley);
					parley.setLength(0);
					for (int i = 0; i < listParley.size(); i++) {
						parley.append(listParley.get(i));
					}
					
					//buscamos la combinacion en la tabla de combinaciones
					double topePorCombinacion = 0;

					topePorCombinacion = Double.parseDouble(oUsuarioTO.getTopePorCombinacion());

					oCombinacionTO.setCombinacion(parley.toString());
					oCombinacionTO.setIdUsuario(oJugadaTO.getIdUsuario());
					oCombinacionTO.setMontoApuesta(oJugadaTO.getMontoApostado());
					
					double montoPorCombinacion = oCombinacionDAO.montoCombinacionPorUsuarioDAO(oCombinacionTO)[0];
					double montoApuesta = Double.parseDouble(oJugadaTO.getMontoApostado());
					
					if (listParley.size()>1) {
						if (topePorCombinacion > 0 && (montoPorCombinacion + montoApuesta) > topePorCombinacion) {
							StringBuffer sb = new StringBuffer();
							sb.append("El monto apostado sobrepasa el tope de apuestas por combinacion. Tope maximo = ");
							sb.append(topePorCombinacion);
							sb.append(", Monto acumulado = ");
							sb.append(montoPorCombinacion);
							throw new TopePorCombinacionExcedidoException(sb.toString(),topePorCombinacion, montoPorCombinacion);
						}
					}
					// FIN validacion de combinacion
					
					// insertamos la jugada si paso las reglas
					oJugadaDAO.insertarJugadaDAO(oJugadaTO);

					for (int i = 0; i < listaJugadas.size(); i++) {
						oCalculadoraForm = (CalculadoraForm) listaJugadas.get(i);
						
						// seleccionamos la fecha mayor para luego colocarla en el vencimiento
						if(fechaUltimoJuego==null || fechaUltimoJuego.getTime()<fechaJuego.getTime() ) {
							fechaUltimoJuego = fechaJuego;
						}
						
						oJugadaJuegoEquipoTO.setIdJugada(oJugadaTO.getIdJugada());
						oJugadaJuegoEquipoTO.setIdUsuarioJuegoEquipo(oCalculadoraForm.getCodigo());
						oJugadaJuegoEquipoTO.setTipo(oCalculadoraForm.getTipo());
						
						// validamos que los logros sean los ultimos que estan registrados
						oUsuarioJuegoEquipoTO.setIdJuegoEquipo(oJugadaJuegoEquipoTO.getIdUsuarioJuegoEquipo());
						oUsuarioJuegoEquipoDAO.obtenerUltimoLogroRegistradoDAO(oUsuarioJuegoEquipoTO); // pendiente
						oJugadaJuegoEquipoTO.setIdUsuarioJuegoEquipo(oUsuarioJuegoEquipoTO.getIdUsuarioJuegoEquipo());
						
						oJugadaJuegoEquipoDAO.insertarJugadaJuegoEquipoDAO(oJugadaJuegoEquipoTO);
					}
					
					// actualizamos el monto del premio
					// por si cambiaron los logros
					oJugadaDAO.recalcularPremioJugadaDAO(oJugadaTO, oUsuarioTO);
					
					
					// insertamos la combinacion de la jugada
					oCombinacionTO.setIdJugada(oJugadaTO.getIdJugada());
					oCombinacionDAO.insertarCombinacionDAO(oCombinacionTO);
					
					
				} else {
					// no hay detalle en la jugada
					throw new GameClosedException("No hay detalle de equipos en la jugada");
				}
				
				// colocamos la fecha de vencimiento y actualizamos la jugada
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				oJugadaTO.setFechaExp(Constants.getFechaLargaSQL(sdf.format(fechaUltimoJuego).concat(" 23:59:59"), Integer.parseInt(Constants.isNull(oJugadaTO.getDiasExpira(),"0"))));
				oJugadaDAO.actualizarJugadaDAO(oJugadaTO);
				
				// revisamos la fecha de vencimiento, para que no quede nula desde la base de datos
				oJugadaDAO.actualizarFechaVencimientoJugadaDAO(oJugadaTO);
				

				/* rebajamos el saldo del jugador */
				oCuentaJugadorTO.setReferencia(Constants.numero(oJugadaTO.getIdJugada(), 10));
				oCuentaJugadorTO.setOperacion(Constants.OPERACION_EGRESO);
				oCuentaJugadorTO.setMonto(oCalculadoraForm.getMontoApostar());
				oCuentaJugadorTO.setConcepto(Constants.CONCEPTO_JUGADA);
				oCuentaJugadorTO.setIdJugador(oUsuarioTO.getIdUsuario());
				oCuentaJugadorTO.setIdUsuario(oUsuarioTO.getIdUsuario());
				oCuentaJugadorTO.setTipo(Constants.CUENTA_JUGADOR_TIPO_JUGADA);
				oCuentaJugadorDAO.insertarCuentaJugadorDAO(oCuentaJugadorTO);

			}
		} catch (GameClosedException e) {
			isError = true;
			throw e;
		} catch (UserWithOutCreditException e) {
			isError = true;
			throw e;
		} catch (TopePorCombinacionExcedidoException e) {
			isError = true;
			throw e;
		} catch (Exception e) {
			isError = true;
			log.info("Error en la ejecucion de JuegoFacade.registrarJugadaFacade");
			log.error("Error:" + e.getMessage());
			e.printStackTrace();
			throw new Exception("Ocurrio un error al intentar eliminar el registro");
		} finally {
			try {
				if (isError) {
					oEjecutorSql.rollback();
				}
				oEjecutorSql.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return oJugadaTO;
	}

	/**
	 * Devuelve una lista de objetos
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet ultimaJugadaFacade(UsuarioIF oUsuarioTO, JugadaIF oJugadaTO) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.ultimaJugadaFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		JugadaDAO oJugadaDAO = new JugadaDAO();
		try {
			oCachedRowSet = oJugadaDAO.ultimaJugadaDAO(oUsuarioTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.ultimaJugadaFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * Devuelve una lista de objetos
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet getJugadaFacade(JugadaIF oJugadaTO) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.getJugadaFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		JugadaDAO oJugadaDAO = new JugadaDAO();
		try {
			oCachedRowSet = oJugadaDAO.getJugadaDAO(oJugadaTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.getJugadaFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}
	
	
	/**
	 * Devuelve una lista de objetos
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public boolean pagarJugadaFacade(JugadaIF oJugadaTO) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.pagarJugadaFacade");
		JugadaDAO oJugadaDAO = new JugadaDAO();
		try {
			oJugadaTO.setIdStatusJugada(Constants.STATUS_JUGADA_PAGADA);
			oJugadaTO.setFechaPago(Constants.getFechaLargaSQL());
			oJugadaDAO.actualizarStatusPagadoJugadaDAO(oJugadaTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.pagarJugadaFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return true;
	}
	
	/**
	 * Devuelve una lista de objetos
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public boolean eliminarJugadaFacade(JugadaIF oJugadaTO,UsuarioIF usuario) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.eliminarJugadaFacade");
		JugadaDAO oJugadaDAO = new JugadaDAO();
		try {
			// para eliminar una jugada ningun juego participante en la
			// jugada debe estar cerrado o totalizado
			// tampoco el juego debe haber iniciado
			boolean isAdmin = usuario.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR);
			boolean isAdminRol = usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR);
			boolean isAdminTaq = usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA);
			boolean isJugTaq = usuario.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA);
			boolean permiteEliminar =usuario.getEliminarJugada().equals("1");
			
			// se agregaron usuarios que tiene permiso de eliminar como el administrador.
			if(isAdmin ||permiteEliminar || usuario.getIdUsuario().equals(Constants.ID_USUARIO_MUNECO_PERMISO_ELIMINAR)  || usuario.getIdUsuario().equals(Constants.ID_USUARIO_SERGIO_PERMISO_ELIMINAR) ) {
				// el admin no tiene limite de tiempo para eliminar la jugada
				oJugadaTO.setIdStatusJugada(Constants.STATUS_JUGADA_ELIMINADA);
				oJugadaDAO.eliminarJugadaAdminDAO(oJugadaTO);
			} else if(isAdminRol) {  // listeros o otros administradores
				if(!oJugadaDAO.isGameClosedOrTotalizedOrInitialized(oJugadaTO,usuario)) {
					// los administradores tienen limite de tiempo para eliminar la jugada (1 hora)
					oJugadaTO.setIdStatusJugada(Constants.STATUS_JUGADA_ELIMINADA);
					if(usuario.getEliminarJugada().equals("1")) {
						oJugadaDAO.eliminarJugadaAdminDAO(oJugadaTO);
					} else {
						oJugadaDAO.eliminarJugadaAdmTaqDAO(oJugadaTO);
					}
				}
			} else if(isAdminTaq) {
				if(!oJugadaDAO.isGameClosedOrTotalizedOrInitialized(oJugadaTO,usuario)) {
					// los administradores tienen limite de tiempo para eliminar la jugada (1 hora)
					oJugadaTO.setIdStatusJugada(Constants.STATUS_JUGADA_ELIMINADA);
					oJugadaDAO.eliminarJugadaAdmTaqDAO(oJugadaTO);
				}
			} else if(isJugTaq) {
				if(!oJugadaDAO.isGameClosedOrTotalizedOrInitialized(oJugadaTO,usuario)) {
					// los jugadores de taquilla tienen limite de tiempo para eliminar la jugada (20 minutos)
					oJugadaTO.setIdStatusJugada(Constants.STATUS_JUGADA_ELIMINADA);
					oJugadaDAO.eliminarJugadaDAO(oJugadaTO);
				}
			} else {
				return false;
			}
			
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.eliminarJugadaFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return true;
	}
	
	

	/**
	 * Construye la lista de los registros de la tabla usuario
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaJugadaFacade(UsuarioIF oUsuarioTO, JugadaIF oJugadaTO, JugadaIF oJugada2TO, int orden, String taquilla) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaUsuarioFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		JugadaDAO oJugadaDAO = new JugadaDAO();
		try {
			oJugadaDAO.actualizarStatuJugadaVencidaJugadaDAO();
			oCachedRowSet = oJugadaDAO.listaJugadaDAO(oUsuarioTO, oJugadaTO, oJugada2TO, orden, taquilla);
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaUsuarioFacade");
			log.error("Error:" + e.getMessage());
		}
		return oCachedRowSet;
	}
	
	public CachedRowSet listaJugadaVencidaFacade(UsuarioIF oUsuarioTO, JugadaIF oJugadaTO, JugadaIF oJugada2TO, int orden, String taquilla) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaUsuarioFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		JugadaDAO oJugadaDAO = new JugadaDAO();
		try {
			oCachedRowSet = oJugadaDAO.listaJugadaDAO(oUsuarioTO, oJugadaTO, oJugada2TO, orden, taquilla, 1,true);
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaUsuarioFacade");
			log.error("Error:" + e.getMessage());
		}
		return oCachedRowSet;
	}
	
	
	/**
	 * Construye la lista de los registros de la tabla usuario
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaPaginaJugadaFacade(UsuarioIF oUsuarioTO, JugadaIF oJugadaTO, JugadaIF oJugada2TO, int orden, String taquilla) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaUsuarioFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		JugadaDAO oJugadaDAO = new JugadaDAO();
		try {
			oJugadaDAO.actualizarStatuJugadaVencidaJugadaDAO();
			oCachedRowSet = oJugadaDAO.listaJugadaDAO(oUsuarioTO, oJugadaTO, oJugada2TO, orden, taquilla,2,false);
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaUsuarioFacade");
			log.error("Error:" + e.getMessage());
		}
		return oCachedRowSet;
	}

	/**
	 * Cargar registros un registro de la tabla Juego.
	 * 
	 * @return JuegoTO.
	 */
	public CachedRowSet listDeportePorUsuarioFacade(UsuarioIF usuarioTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoFacade.listDeportePorUsuarioFacade");
		int act = 0;

		DeporteDAO oDeporteDAO = new DeporteDAO(); 
		CachedRowSet lista = null;

		try {
			lista = oDeporteDAO.listDeportePorUsuarioDAO(usuarioTO);
		} catch (Exception e) {
			lista = new CachedRowSet();
			log.info("Error en la ejecucion de JuegoFacade.listDeportePorUsuarioFacade");
			e.printStackTrace();
		}
		return lista;
	}

	/**
	 * Cargar registros un registro de la tabla Juego.
	 * 
	 * @return JuegoTO.
	 */
	public void registerDeportePorUsuarioFacade() throws Exception {
		log.info("Iniciando ejecucion de JuegoFacade.registerDeportePorUsuarioFacade");
		int act = 0;

		DeporteDAO oDeporteDAO = new DeporteDAO(); 
		ArrayList lista = new ArrayList();

		try {
			String[] idDeportes = request.getParameterValues("idDeporte");
			String[] idUsuarios = request.getParameterValues("idUsuario");
			String[] combinaciones = request.getParameterValues("combinacion");
			
			DeporteUserTO deporteUserTO = null;
			for(int i=0;i<idDeportes.length;i++){
				deporteUserTO = new DeporteUserTO();
				deporteUserTO.setIdDeporte(idDeportes[i]);
				deporteUserTO.setIdUsuario(idUsuarios[i]);
				deporteUserTO.setCombinacionUser(combinaciones[i]);
				
				lista.add(deporteUserTO);
			}
			
			oDeporteDAO.registerDeportePorUsuarioDAO(lista);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Construye la lista de los registros de la tabla usuario
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaApuestaPorEquipoFacade(UsuarioIF oUsuarioTO, JugadaIF oJugadaTO, JugadaIF oJugada2TO, int orden, String taquilla, String idEquipo) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaApuestaPorEquipoFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		JugadaDAO oJugadaDAO = new JugadaDAO();
		try {
			oCachedRowSet = oJugadaDAO.listaApuestaPorEquipoDAO(oUsuarioTO, oJugadaTO, oJugada2TO, orden, taquilla, idEquipo);
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaApuestaPorEquipoFacade");
			log.error("Error:" + e.getMessage());
		}
		return oCachedRowSet;
	}
	
	/**
	 * Construye la lista de los registros de la tabla usuario
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaResultadoVendedorFacade(UsuarioIF oUsuarioTO, JugadaIF oJugadaTO, JugadaIF oJugada2TO) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaApuestaPorEquipoFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		JugadaDAO oJugadaDAO = new JugadaDAO();
		try {
			oCachedRowSet = oJugadaDAO.listaResultadoVendedorJugadaDAO(oUsuarioTO, oJugadaTO, oJugada2TO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaApuestaPorEquipoFacade");
			log.error("Error:" + e.getMessage());
		}
		return oCachedRowSet;
	}

	/**
	 * Construye la lista de los registros de la tabla usuario
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public TreeMap listaApuestaPorEquipoEmpateFacade(CachedRowSet crs) throws Exception {
		log.info("Iniciando ejecucion de JuegoFacade.listaApuestaPorEquipoEmpateFacade");
		TreeMap lista = new TreeMap();
		JugadaDAO oJugadaDAO = new JugadaDAO();
		try {
			lista = oJugadaDAO.listaApuestaPorEquipoEmpateDAO(crs);
		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoFacade.listaApuestaPorEquipoEmpateFacade");
			log.error("Error:" + e.getMessage());
		}
		return lista;
	}

	/**
	 * Construye la lista de los registros de la tabla usuario
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaJugadaResumidaFacade(UsuarioIF oUsuarioTO, JugadaIF oJugadaTO, JugadaIF oJugada2TO, int orden, String taquilla, String dominio) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaJugadaResumidaFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		JugadaDAO oJugadaDAO = new JugadaDAO();
		try {
			oJugadaDAO.actualizarStatuJugadaVencidaJugadaDAO();
			if(oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				oCachedRowSet = oJugadaDAO.listaJugadaResumidaAdminDAO(oUsuarioTO, oJugadaTO, oJugada2TO, orden, taquilla, dominio);
			} else {
				oCachedRowSet = oJugadaDAO.listaJugadaResumidaDAO(oUsuarioTO, oJugadaTO, oJugada2TO, orden, taquilla);
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaJugadaResumidaFacade");
			log.error("Error:" + e.getMessage());
		}
		return oCachedRowSet;
	}
	
	public TreeMap listaJugadaResumidaVencidaFacade(UsuarioIF oUsuarioTO, JugadaIF oJugadaTO, JugadaIF oJugada2TO, int orden, String taquilla, String[] usuarios) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaJugadaResumidaVencidaFacade");
		JugadaDAO oJugadaDAO = new JugadaDAO();
		TreeMap lista = new TreeMap();
		try {
			lista = oJugadaDAO.listaJugadaResumidaVencidaDAO(oUsuarioTO, oJugadaTO, oJugada2TO, orden, taquilla);
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaJugadaResumidaVencidaFacade");
			e.printStackTrace();
		}
		return lista;
	}

	
	/**
	 * Construye la lista de los registros de la tabla usuario
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaJugadaPagadaFacade(UsuarioIF oUsuarioTO, JugadaIF oJugadaTO, JugadaIF oJugada2TO, int orden) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaJugadaPagadaFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		JugadaDAO oJugadaDAO = new JugadaDAO();
		try {
			oCachedRowSet = oJugadaDAO.listaJugadaPagadaDAO(oUsuarioTO, oJugadaTO, oJugada2TO, orden);
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaJugadaPagadaFacade");
			log.error("Error:" + e.getMessage());
		}
		return oCachedRowSet;
	}
	
	/**
	 * Construye la lista de los registros de la tabla usuario
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public String cargarStatuJugadaFacade(JugadaIF oJugadaTO) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.cargarStatuJugadaFacade");
		StatusJugadaDAO oStatusJugadaDAO = new StatusJugadaDAO();
		StatusJugadaTO oStatusJugadaTO = new StatusJugadaTO();
		try {
			oStatusJugadaTO.setIdStatusJugada(oJugadaTO.getIdStatusJugada());
			oStatusJugadaDAO.cargarStatusJugadaDAO(oStatusJugadaTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.cargarStatuJugadaFacade");
			log.error("Error:" + e.getMessage());
			return null;
		}
		return oStatusJugadaTO.getDescJugada();
	}

	/**
	 * Cargar registros un registro de la tabla Juego.
	 * 
	 * @return JuegoTO.
	 */
	public ArrayList consultarResultadosFacade(String fecha) throws Exception {
		log.info("Iniciando ejecucion de JuegoFacade.consultarResultadosFacade");
		int act = 0;

		JuegoDAO oJuegoDAO = new JuegoDAO();
		ArrayList lista = null;

		try {
			lista = oJuegoDAO.consultarResultadosDAO(fecha);
		} catch (Exception e) {
			lista = new ArrayList();
			log.info("Error en la ejecucion de JuegoFacade.consultarResultadosFacade");
			log.error("Error:" + e.getMessage());
			log.error("Error:" + e.getStackTrace());
		}
		return lista;
	}
	
	/**
	 * Cargar registros un registro de la tabla Juego.
	 * 
	 * @return JuegoTO.
	 */
	/**
	 * Cargar registros un registro de la tabla Juego.
	 * 
	 * @return JuegoTO.
	 */
	public double consultarMontoJugadoPorDerechoFacade(JuegoEquipoIF oJuegoEquipoTO, JugadaJuegoEquipoIF oJugadaJuegoEquipoTO, UsuarioIF oUsuarioTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoFacade.consultarMontoJugadoPorDerechoFacade");
		int act = 0;
		double montoJugado =0;

		JugadaDAO oJugadaDAO = new JugadaDAO();
		ArrayList lista = null;

		try {
			montoJugado = oJugadaDAO.consultarMontoJugadoPorDerechoFacade(oJuegoEquipoTO, oJugadaJuegoEquipoTO, oUsuarioTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoFacade.consultarMontoJugadoPorDerechoFacade");
			e.printStackTrace();
		}
		return montoJugado;
	}
	

	public static double calcularPagoVeces(int numeroLogro, double montoApostar, double premio, UsuarioIF oUsuarioTO) {
		
		if(!Constants.isNull(oUsuarioTO.getPagoVeces(),"").trim().equals("") && montoApostar>0) {
			String[] pagoVeces = oUsuarioTO.getPagoVeces().split(",");

			int nLogro = numeroLogro;
			String[] rango;
			int montoJugada= (int)Math.round(montoApostar);
			int limite = 0;
			int resto = 0;
			int num = 0;
			for(int i=0; i<pagoVeces.length; i++) {
				rango = pagoVeces[i].split("-");
				if(Integer.parseInt(rango[0])==nLogro) {
					//alert("pago =logros="+nLogro+" nVeces => "+rango[1]);
					limite = Integer.parseInt(rango[1])*montoJugada;
					
					if(premio>limite) {
						limite = (int)Math.round(Math.min(premio,limite));
		
						String sLim = String.valueOf(limite);
						int factor = 1;
						for(int k=(sLim.length()-1); k>0; k--) {
							if(sLim.charAt(k)=='0') {
								factor = factor*10;
							} else {
								break;
							}
						}
		
						// agregamos el resto				
						if(factor>9) {
						    limite = (int)Math.round(limite + (premio%factor));
						}
						
						premio = Math.min(premio,limite);
						
					}
					
					break; // salimos del ciclo
					
				}
			}
		}

		return premio;
	}
	
	/**
	 * Abre los juegos almacenados en el arraylist para aquellos administradores
	 * de taquilla que tienen "abrir juego" en su configuracion. 
	 * @param form
	 * @param usuario
	 * @param listaIdJuegoGenerados
	 * @throws Exception
	 */
	public void abriJuegoParaTaquillas(ActionForm form, UsuarioIF usuario, ArrayList<String> listaIdJuegoGenerados) throws Exception {
		// buscamos los administradores de taquilla que abren automaticamente el juego
		UsuarioFacade uf = new UsuarioFacade(request);
		CachedRowSet crs = uf.listaAdministradorTaquillaAbrirJuegoUsuarioFacade();
		UsuarioIF us = new UsuarioTO();
		UsuarioJuegoEquipoDAO usuarioJuegoEquipoDAO = new UsuarioJuegoEquipoDAO();
		if(crs.size()>0) {
			DominioBean dom = Constants.getDominio(request);
			
			while(crs.next()) {
				us.setIdUsuario(crs.getString("id_usuario"));
				if(!usuario.getDominio().equals("000")) { // si no es del dominio principal buscamos el id del admtaq
					if(dom.getIdAdminTaquilla().indexOf(",")!=-1) {
						String[] idUs = dom.getIdAdminTaquilla().split(",");
						boolean hacer = false;
						for(int i=0; i<idUs.length; i++) {
							if(!dom.getIdAdminTaquilla().equals(idUs[i])) {
								hacer=true;
								break;
							}
						}
						if(!hacer) {
							continue;
						}
					} else {
						if(!dom.getIdAdminTaquilla().equals(us.getIdUsuario())) {
							continue;
						}
					}
				}
				
				us = uf.consultarUsuarioFacade(us);
				for(int i=0;i<listaIdJuegoGenerados.size();i++) {
					
					// revisamos si el juego no esta ha sido abierto por la taquilla
					// y lo agregamos / abrimos por el administrador de taquilla que tiene autoabrir
					if(!usuarioJuegoEquipoDAO.isOpenGameUsuarioJuegoEquipoDAO(us.getIdUsuario(), listaIdJuegoGenerados.get(i))) {
						((JuegoIF)form).setFechaSis(Constants.getFechaLargaSQL());
						((JuegoIF)form).setIdJuego(listaIdJuegoGenerados.get(i));
						((JuegoIF)form).setIdStatusJuego(Constants.STATUS_JUEGO_ABIERTO);
						
						agregarJuegoFacade(form, true, false, us); // lo abrimos para la taquilla
					}
				}
			}
		}
	}
	
}
