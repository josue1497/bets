package com.betcesc.game.ctrl.back;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.betcesc.game.bean.PuntosAltaBajaBean;
import com.betcesc.game.common.Constants;
import com.betcesc.game.dao.JuegoEquipoDAO;
import com.betcesc.game.dao.LogrosDAO;
import com.betcesc.game.exceptions.GameOpenException;
import com.betcesc.game.exceptions.NotValidReferenceException;
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.facade.JuegoFacade;
import com.betcesc.game.interfaces.JuegoIF;
import com.betcesc.game.interfaces.UsuarioIF;

public class RegisterGameSaveAction extends Action {

	private static final Log log = LogFactory.getLog(RegisterGameSaveAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		ActionErrors errores = new ActionErrors();
		boolean agregar = false;
		boolean isMultiEquipo = request.getParameter("multiEquipo")!=null;
		ArrayList<String> listaIdJuegoGenerados = new ArrayList<String>();

		try {
			if(!request.getMethod().toUpperCase().equals("POST")){
				throw new SessionInvalidException();
			}
			
			UsuarioIF usuario = Constants.getUserSession(request);
			
			if(!usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) || usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA)) {
				return mapping.findForward("end");
			}
			
			RegisterGameActionForm forma = (RegisterGameActionForm) form;

			JuegoFacade oJuegoFacade = new JuegoFacade(request);
			request.getSession().removeAttribute("forma");
			
			
			if(request.getParameter("automatico")!=null) {
				request.setAttribute("automatico","1");
			}
			if(request.getParameter("diferencia")!=null) {
				request.setAttribute("diferencia",request.getParameter("diferencia"));
			}
			 

			if (forma.getIdJuego() == null || forma.getIdJuego().equals("")) {
				// guardamos en session algunas variables
				request.getSession().setAttribute("forma", forma);

				String fechaIni = forma.getFechaIni();
				oJuegoFacade.agregarJuegoFacade(form,false,isMultiEquipo);
				
				// Si el juego es beisbol lo duplicaremos a un juego de 5 ining
				if(forma.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL)) {
					
					// los moneyline del juego padre
					String moneyLine0 = forma.get_MoneyLine()[0];
					String moneyLine1 = forma.get_MoneyLine()[1];
					
					// logros por defecto para 5to inning
					PuntosAltaBajaBean puntosAB = null;
					int totalItems = forma.get_Total().length;
					String puntos = forma.get_Total()[0];
					if(!forma.get_TotalDecimal()[0].equals("0")) {
						puntos = puntos.concat(".").concat(forma.get_TotalDecimal()[0]);
					}
					puntosAB = Constants.getPuntosAltaBajaPorDefecto(puntos);

					
					forma.setIdDeporte(Constants.ID_EQUIPO_BEISBOL_5);
					String idJuegoAnt = forma.getIdJuego();
					String fechaIniAnt = forma.getFechaIni();
					String referenciaA = forma.get_Referencia()[0];
					String referenciaB = forma.get_Referencia()[1];
					forma.setIdJuego(null);
					forma.setFechaIni(fechaIni);
					forma.setIdJuegoPadre(idJuegoAnt);  // agui indicamos cual es el juego padre que lo genero
					
					//oJuegoFacade
					JuegoEquipoDAO oJuegoEquipoDAO = new JuegoEquipoDAO(); 
					//String referencia =  oJuegoEquipoDAO.getReferenciaDAO(forma.getFechaIni(), forma.getIdDeporte());
					//int ref = Integer.parseInt(Constants.isNull(referencia,"0"));
					forma.get_Referencia()[0] = "5".concat(referenciaA);
					forma.get_Referencia()[1] = "5".concat(referenciaB);
					
					
					// Crearemos un vector vacio
					String[] vacio = new String[forma.get_Spread().length];
					for(int i=0;i<vacio.length;i++) {
						vacio[i]="0";
					}
					String[] mitad = new String[forma.get_Spread().length];
					for(int i=0;i<mitad.length;i++) {
						mitad[i]=(i<2?"5":"0");
					}

					// el runline va en cero par el  5to ining
					forma.get_Spread()[0] = forma.get_Spread()[0].indexOf("-")!=-1?"-0":"0";
					forma.get_Spread()[1] = forma.get_Spread()[1].indexOf("-")!=-1?"-0":"0";
					forma.set_SpreadDecimal(mitad);
					
					int[] logro5to = Constants.getLogro5toInning(forma.get_SpreadLogro()[0],forma.get_SpreadLogro()[1]);
					
					boolean isFirstNeg = forma.get_SpreadLogro()[0].indexOf("-")!=-1;
					forma.get_SpreadLogro()[isFirstNeg?0:1] = String.valueOf(logro5to[0]);
					forma.get_SpreadLogro()[isFirstNeg?1:0] = String.valueOf(logro5to[1]);
					
					// el super runline va en cero par el  5to ining
					forma.set_SuperSpread(vacio);
					forma.set_SuperSpreadDecimal(vacio);
					forma.set_SuperSpreadLogro(vacio);

					
					try {
						//hacemos una pausa de 1 segundos
						// antes de insertar el otro juego
						Thread.sleep(1000); 
					} catch (Exception e) {
					  e.printStackTrace();
					}

					// punto por defecto para alta y baja
					for(int i=0; i<totalItems && i<2; i++) {
						forma.get_Total()[i] = puntosAB.getPuntos5to();
						forma.get_TotalDecimal()[i] = puntosAB.getPuntos5toDecimal();
					}
					
					oJuegoFacade.agregarJuegoFacade(form);
					
					// tambien crearemos un juego para el 1er inning
					forma.setIdDeporte(Constants.ID_EQUIPO_BEISBOL_1);
					forma.setIdJuego(null);
					forma.setFechaIni(fechaIni);
					forma.setIdJuegoPadre(idJuegoAnt);  // agui indicamos cual es el juego padre que lo genero
					forma.get_Referencia()[0] = "9".concat(referenciaA);
					forma.get_Referencia()[1] = "9".concat(referenciaB);

					//llevamos a cero los que no son moneyline
					forma.set_Spread(vacio);
					forma.set_SpreadDecimal(vacio);
					forma.set_SpreadLogro(vacio);
					
					forma.set_SuperSpread(vacio);
					forma.set_SuperSpreadDecimal(vacio);
					forma.set_SuperSpreadLogro(vacio);

					forma.set_Total(vacio);
					forma.set_TotalDecimal(vacio);
					forma.set_TotalLogro(vacio);
					
					
					try {
						//hacemos una pausa de 1 segundos
						// antes de insertar el otro juego
						Thread.sleep(1000); 
					} catch (Exception e) {
					  e.printStackTrace();
					}

					// punto por defecto para alta y baja en el si y no
					forma.get_MoneyLine()[0] = puntosAB.getPuntosSi();
					forma.get_MoneyLine()[1] = puntosAB.getPuntosNo();

					oJuegoFacade.agregarJuegoFacade(form);
					
					
					// tambien crearemos un juego para quien anota primero 
					forma.setIdDeporte(Constants.ID_EQUIPO_BEISBOL_0);
					forma.setIdJuego(null);
					forma.setFechaIni(fechaIni);
					forma.setIdJuegoPadre(idJuegoAnt);  // agui indicamos cual es el juego padre que lo genero
					forma.get_Referencia()[0] = "7".concat(referenciaA);
					forma.get_Referencia()[1] = "7".concat(referenciaB);

					//llevamos a cero los que no son moneyline
					vacio = new String[forma.get_Spread().length];
					for(int i=0;i<vacio.length;i++) {
						vacio[i]="0";
					}
					
					forma.set_Spread(vacio);
					forma.set_SpreadDecimal(vacio);
					forma.set_SpreadLogro(vacio);
					
					forma.set_Total(vacio);
					forma.set_TotalDecimal(vacio);
					forma.set_TotalLogro(vacio);
					
					// punto por defecto para alta y baja en el anota primero
					// buscamos los puntos en la tabla de logros
					// puntos por defecto
					forma.get_MoneyLine()[0] = "-115";
					forma.get_MoneyLine()[1] = "-115";

					LogrosDAO oLogrosDAO = new LogrosDAO();
					String[] logros = oLogrosDAO.getAnotaPrimeroLogrosDAO(moneyLine0,true); 
					
					if(logros!=null) {
						forma.get_MoneyLine()[0] = logros[0];
						forma.get_MoneyLine()[1] = logros[1];
					} else {
						logros = oLogrosDAO.getAnotaPrimeroLogrosDAO(moneyLine1,false); 
						
						if(logros!=null) {
							forma.get_MoneyLine()[0] = logros[1];
							forma.get_MoneyLine()[1] = logros[0];
						}
					}

					try {
						//hacemos una pausa de 1 segundos
						// antes de insertar el otro juego
						Thread.sleep(1000); 
					} catch (Exception e) {
					  e.printStackTrace();
					}
					
					oJuegoFacade.agregarJuegoFacade(form);
				
					// volvemos a colocar el deporte anterior
					forma.setIdDeporte(Constants.ID_EQUIPO_BEISBOL);
					forma.setIdJuego(idJuegoAnt);
					forma.setFechaIni(fechaIniAnt);
					forma.get_Referencia()[0] = referenciaA;
					forma.get_Referencia()[1] = referenciaB;
				} else if(forma.getIdDeporte().equals(Constants.ID_EQUIPO_BASKETBALL)) {// Si el juego es basket lo duplicaremos a un juego de basket mitad
					// logros por defecto para la mitad del juego
					PuntosAltaBajaBean puntosAB = null;
					int totalItems = forma.get_Total().length;
					String puntos = forma.get_Total()[0];
					if(!forma.get_TotalDecimal()[0].equals("0")) {
						puntos = puntos.concat(".").concat(forma.get_TotalDecimal()[0]);
					}
					puntosAB = Constants.getPuntosAltaBajaPorDefecto(puntos);

					
					forma.setIdDeporte(Constants.ID_EQUIPO_BASKETBALL_MITAD);
					String idJuegoAnt = forma.getIdJuego();
					String fechaIniAnt = forma.getFechaIni();
					String referenciaA = forma.get_Referencia()[0];
					String referenciaB = forma.get_Referencia()[1];
					forma.setIdJuego(null);
					forma.setFechaIni(fechaIni);
					forma.setIdJuegoPadre(idJuegoAnt);  // agui indicamos cual es el juego padre que lo genero
					//oJuegoFacade
					JuegoEquipoDAO oJuegoEquipoDAO = new JuegoEquipoDAO(); 
					//String referencia =  oJuegoEquipoDAO.getReferenciaDAO(forma.getFechaIni(), forma.getIdDeporte());
					//int ref = Integer.parseInt(Constants.isNull(referencia,"0"));
					forma.get_Referencia()[0] = "5".concat(referenciaA);
					forma.get_Referencia()[1] = "5".concat(referenciaB);

					forma.get_MoneyLine()[0] = "0";
					forma.get_MoneyLine()[1] = "0";
					
					// Puntos por defecto para el runline del la mitad del baskquet
					double spread0 = Double.parseDouble(Constants.concatDecimal(forma.get_Spread()[0], forma.get_SpreadDecimal()[0]));
					double spread1 = Double.parseDouble(Constants.concatDecimal(forma.get_Spread()[1], forma.get_SpreadDecimal()[1]));
					
					//spread0 = (spread0>0?Math.ceil(spread0)/2:Math.floor(spread0)/2);
					//spread1 = (spread1>0?Math.ceil(spread1)/2:Math.floor(spread1)/2);
					
					spread0 = Constants.logroBaskquetMitad(spread0, "RL");
					spread1 = Constants.logroBaskquetMitad(spread1, "RL");

					
					forma.get_Spread()[0] = Constants.getEntero(spread0);
					forma.get_SpreadDecimal()[0] = Constants.getDecimal(spread0);
					forma.get_Spread()[1] = Constants.getEntero(spread1);
					forma.get_SpreadDecimal()[1] = Constants.getDecimal(spread1);
					
					// punto por defecto para alta y baja
					double total0 = Double.parseDouble(Constants.concatDecimal(forma.get_Total()[0], forma.get_TotalDecimal()[0]));
					double total1 = Double.parseDouble(Constants.concatDecimal(forma.get_Total()[1], forma.get_TotalDecimal()[1]));
					
					//total0 = (total0>0?Math.ceil(total0)/2:Math.floor(total0)/2);
					//total1 = (total1>0?Math.ceil(total1)/2:Math.floor(total1)/2);
					
					total0 = Constants.logroBaskquetMitad(total0, "AB");
					total1 = Constants.logroBaskquetMitad(total1, "AB");
					
					forma.get_Total()[0] = Constants.getEntero(total0);
					forma.get_TotalDecimal()[0] = Constants.getDecimal(total0);
					forma.get_Total()[1] = Constants.getEntero(total1);
					forma.get_TotalDecimal()[1] = Constants.getDecimal(total1);
					
					try {
						//hacemos una pausa de 2 segundos
						// antes de insertar el otro juego
						Thread.sleep(2000); 
					} catch (Exception e) {
					  e.printStackTrace();
					}
					
					oJuegoFacade.agregarJuegoFacade(form);
					
					// volvemos a colocar el deporte anterior
					forma.setIdDeporte(Constants.ID_EQUIPO_BASKETBALL);
					forma.setIdJuego(idJuegoAnt);
					forma.setFechaIni(fechaIniAnt);
					forma.get_Referencia()[0] = referenciaA;
					forma.get_Referencia()[1] = referenciaB;
				    } else if (forma.getIdDeporte().equals(Constants.ID_EQUIPO_SOCCER) && false) // Se deshabilita el HalfSoccer
				    {// Si el juego es soccer lo duplicaremos a un juego de soccer mitad
					// logros por defecto para la mitad del juego
					PuntosAltaBajaBean puntosAB = null;
					int totalItems = forma.get_Total().length;
					String puntos = forma.get_Total()[0];
					if(!forma.get_TotalDecimal()[0].equals("0")) {
						puntos = puntos.concat(".").concat(forma.get_TotalDecimal()[0]);
					}
					puntosAB = Constants.getPuntosAltaBajaPorDefecto(puntos);

					
					forma.setIdDeporte(Constants.ID_EQUIPO_SOCCER_MITAD);
					String idJuegoAnt = forma.getIdJuego();
					String fechaIniAnt = forma.getFechaIni();
					String referenciaA = forma.get_Referencia()[0];
					String referenciaB = forma.get_Referencia()[1];
					String referenciaC = forma.get_Referencia()[2];
					forma.setIdJuego(null);
					forma.setFechaIni(fechaIni);
					forma.setIdJuegoPadre(idJuegoAnt);  // agui indicamos cual es el juego padre que lo genero
					//oJuegoFacade
					JuegoEquipoDAO oJuegoEquipoDAO = new JuegoEquipoDAO(); 
					//String referencia =  oJuegoEquipoDAO.getReferenciaDAO(forma.getFechaIni(), forma.getIdDeporte());
					//int ref = Integer.parseInt(Constants.isNull(referencia,"0"));
					forma.get_Referencia()[0] = "5".concat(referenciaA);
					forma.get_Referencia()[1] = "5".concat(referenciaB);
					forma.get_Referencia()[2] = "5".concat(referenciaC);

					forma.get_MoneyLine()[0] = "-115";
					forma.get_MoneyLine()[1] = "-115";
					forma.get_MoneyLine()[2] = "-115";
					
					// Puntos por defecto para el runline del la mitad del baskquet
					double spread0 = 0;
					double spread1 = 0;
					double spread2 = 0;
					
					//spread0 = (spread0>0?Math.ceil(spread0)/2:Math.floor(spread0)/2);
					//spread1 = (spread1>0?Math.ceil(spread1)/2:Math.floor(spread1)/2);
					
					spread0 = 0;
					spread1 = 0;
					spread2 = 0;

					
					forma.get_Spread()[0] = Constants.getEntero(spread0);
					forma.get_SpreadDecimal()[0] = Constants.getDecimal(spread0);
					forma.get_Spread()[1] = Constants.getEntero(spread1);
					forma.get_SpreadDecimal()[1] = Constants.getDecimal(spread1);
					forma.get_Spread()[2] = Constants.getEntero(spread2);
					forma.get_SpreadDecimal()[2] = Constants.getDecimal(spread2);
					
					// punto por defecto para alta y baja
					double total0 = Double.parseDouble(Constants.concatDecimal(forma.get_Total()[0], forma.get_TotalDecimal()[0]));
					double total1 = Double.parseDouble(Constants.concatDecimal(forma.get_Total()[1], forma.get_TotalDecimal()[1]));
					double total2 = 0;
					
					//total0 = (total0>0?Math.ceil(total0)/2:Math.floor(total0)/2);
					//total1 = (total1>0?Math.ceil(total1)/2:Math.floor(total1)/2);
					
					total0 = 0;
					total1 = 0;
					total2 = 0;
					
					forma.get_Total()[0] = Constants.getEntero(total0);
					forma.get_TotalDecimal()[0] = Constants.getDecimal(total0);
					forma.get_Total()[1] = Constants.getEntero(total1);
					forma.get_TotalDecimal()[1] = Constants.getDecimal(total1);
					forma.get_Total()[2] = Constants.getEntero(total2);
					forma.get_TotalDecimal()[2] = Constants.getDecimal(total2);
					
					try {
						//hacemos una pausa de 2 segundos
						// antes de insertar el otro juego
						Thread.sleep(2000); 
					} catch (Exception e) {
					  e.printStackTrace();
					}
					
					oJuegoFacade.agregarJuegoFacade(form);
					
					// volvemos a colocar el deporte anterior
					forma.setIdDeporte(Constants.ID_EQUIPO_SOCCER);
					forma.setIdJuego(idJuegoAnt);
					forma.setFechaIni(fechaIniAnt);
					forma.get_Referencia()[0] = referenciaA;
					forma.get_Referencia()[1] = referenciaB;
					forma.get_Referencia()[2] = referenciaC;
				}
				
				agregar = true;
				
				request.getSession().removeAttribute("dataJuegos");
				if(request.getParameter("dataJuegos")!=null) {
					String data = (String)request.getParameter("dataJuegos");
					data = request.getParameter("dataJuegos");

					if(data.indexOf("\n")!=-1) {
						data = data.substring(data.indexOf("\n")+1);
						request.getSession().setAttribute("dataJuegos", data);
					}
				}
				
				
			} else {
				if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) && request.getParameter("abrir") != null && request.getParameter("abrir").equals("true")) {
					// abrimos el juego
					JuegoIF oJuegoTO = (JuegoIF)form;
					oJuegoTO.setIdUsuario(usuario.getIdUsuario());
					oJuegoFacade.abrirJuegoFacade(oJuegoTO);

					// abrimos el juego de los administradores de taquilla
					ArrayList<String> listaIdJuegos = new ArrayList<String>();
					listaIdJuegos.add(oJuegoTO.getIdJuego());
					oJuegoFacade.abriJuegoParaTaquillas(form, usuario, listaIdJuegos);
					
					// eliminamos los vectores de cache
					Constants.inicializarCache();
				} else if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA) && request.getParameter("reabrir") != null && request.getParameter("reabrir").equals("true")) {
					// abrimos el juego
					JuegoIF oJuegoTO = (JuegoIF)form;
					oJuegoTO.setIdUsuario(usuario.getIdUsuario());
					oJuegoFacade.reabrirAdmTaqJuegoFacade(oJuegoTO, usuario);

					// eliminamos los vectores de cache
					Constants.inicializarCache();
				} else if ((usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) || usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA)) && request.getParameter("cerrar") != null && request.getParameter("cerrar").equals("true")) {
					// cerramos el juego
					JuegoIF oJuegoTO = (JuegoIF)form;
					oJuegoTO.setIdUsuario(usuario.getIdUsuario());
					oJuegoFacade.cerrarJuegoFacade(oJuegoTO, usuario);

					// eliminamos los vectores de cache
					Constants.inicializarCache();
				} else if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) && request.getParameter("eliminar") != null && request.getParameter("eliminar").equals("true")) {
					// eliminamos el juego
					oJuegoFacade.eliminarJuegoFacade((JuegoIF)form);
				} else if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) && request.getParameter("totalizar") != null && request.getParameter("totalizar").equals("true")) {
					// totalizamos el juego
					oJuegoFacade.totalizarJuegoFacade((RegisterGameActionForm)form, usuario);
					
					// eliminamos el vector de resultados
					Constants.inicializarCacheResultado();
				} else if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) && request.getParameter("suspender") != null && request.getParameter("suspender").equals("true")) {
					// suspendemos el juego
					oJuegoFacade.suspenderJuegoFacade((RegisterGameActionForm)form, usuario);
				} else {
					// actualizamos el juego
					JuegoIF juegoIF = (JuegoIF)form;
					if(request.getParameter("multiple") != null && request.getParameter("multiple").equals("true")) {
						String[] juegos = juegoIF.getIdJuego().split("_");
						for(int i=0;i<juegos.length;i++) {
							((JuegoIF)form).setFechaSis(Constants.getFechaLargaSQL());
							((JuegoIF)form).setIdJuego(juegos[i]);
							oJuegoFacade.agregarJuegoFacade(form, true);
							
							// abrimos el juego de los administradores de taquilla
							ArrayList<String> listaIdJuegos = new ArrayList<String>();
							listaIdJuegos.add(juegos[i]);
							oJuegoFacade.abriJuegoParaTaquillas(form, usuario, listaIdJuegos);
							
						}
					} else {
						((JuegoIF)form).setFechaSis(Constants.getFechaLargaSQL());
						oJuegoFacade.agregarJuegoFacade(form,false,isMultiEquipo);
					}

					if(juegoIF.getIdStatusJuego().equals(Constants.STATUS_JUEGO_ABIERTO)) {
						// eliminamos los vectores de cache
						Constants.inicializarCache();
					}
				}
			}

		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		} catch (NotValidReferenceException e) {
			errores.add("error", new ActionError("error.game.notValidReference"));
			saveErrors(request, errores);
			return mapping.findForward(isMultiEquipo?"errorMulti":"error");
		} catch (GameOpenException e) {
			errores.add("error", new ActionError("error.game.open"));
			saveErrors(request, errores);
			return mapping.findForward(isMultiEquipo?"errorMulti":"error");
		} catch (Exception e) {
			errores.add("error", new ActionError("error.sistema.general"));
			saveErrors(request, errores);
			e.printStackTrace();
			return mapping.findForward(isMultiEquipo?"errorMulti":"error");
		}

		
		if(agregar) {
			return mapping.findForward(isMultiEquipo?"successAddMulti":"successAdd");
		}
		return mapping.findForward("success");
	}

}
