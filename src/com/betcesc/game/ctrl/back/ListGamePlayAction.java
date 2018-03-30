package com.betcesc.game.ctrl.back;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.common.Constants;
import com.betcesc.game.common.Validator;
import com.betcesc.game.exceptions.GameClosedException;
import com.betcesc.game.exceptions.LogroIsEmptyException;
import com.betcesc.game.exceptions.LogrosChangeException;
import com.betcesc.game.exceptions.MountPayInvalidException;
import com.betcesc.game.exceptions.PlayNotValidException;
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.exceptions.TopePorCombinacionAdministradorExcedidoException;
import com.betcesc.game.exceptions.TopePorCombinacionExcedidoException;
import com.betcesc.game.exceptions.TopePorDerechoExcedidoException;
import com.betcesc.game.exceptions.TopePorPagarCombinacionAdministradorExcedidoException;
import com.betcesc.game.exceptions.TopePorPagarCombinacionExcedidoException;
import com.betcesc.game.exceptions.UserWithOutCreditException;
import com.betcesc.game.facade.JuegoFacade;
import com.betcesc.game.facade.ParametrosFacade;
import com.betcesc.game.facade.UsuarioFacade;
import com.betcesc.game.form.CalculadoraForm;
import com.betcesc.game.interfaces.JuegoEquipoIF;
import com.betcesc.game.interfaces.JugadaIF;
import com.betcesc.game.interfaces.JugadaJuegoEquipoIF;
import com.betcesc.game.interfaces.ParametrosIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.DeporteTO;
import com.betcesc.game.to.JuegoEquipoTO;
import com.betcesc.game.to.JugadaJuegoEquipoTO;
import com.betcesc.game.to.ParametrosTO;

public class ListGamePlayAction extends Action {

	private static final Log	log	= LogFactory.getLog(ListGamePlayAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		log.info("Iniciando excecute()");
		JuegoFacade juegoFacade;
		UsuarioFacade usuarioFacade;
		CachedRowSet lista1;
		CachedRowSet listaDeporte;
		CachedRowSet listaJuego = null;
		StringBuffer listaReferencia = new StringBuffer();
		HashMap listaJuego5 = new HashMap();
		HashMap listaJuego1 = new HashMap();
		HashMap listaJuego0 = new HashMap();

		DeporteTO oDeporte = new DeporteTO();
		ActionErrors errores = new ActionErrors();
		String[][] error = new String[100][];

		// para recalcular la apuesta
		double apuesta = 0;
		double montoPremio = 0;
		double montoPremioTicket = 0;
		double nLogro = 0;

		try {

			UsuarioIF usuario = Constants.getUserSession(request);
			HttpSession session = request.getSession();

			if (!usuario.getIdRol().equals(Constants.ROL_JUGADOR) && !usuario.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA) && !usuario.getIdRol().equals(Constants.ROL_AUTO_JUGADOR)) {
				log.info("El usuario no es Jugador Saldra.");
				return mapping.findForward("end");
			}

			if (usuario.getIdRol().equals(Constants.ROL_AUTO_JUGADOR))
			{
				usuario.setIdRol(Constants.ROL_JUGADOR_DE_TAQUILLA);
			}
				
			if (request.getParameter("bloqueoPantalla") != null) {
				request.getSession().setAttribute("bloqueoPantalla", request.getParameter("bloqueoPantalla"));
			}

			// INI consultamos los juegos bloqueados
			ParametrosFacade parametrosFacade;
			ParametrosIF oParametros = new ParametrosTO();
			String juegosBloqueados = "";
			parametrosFacade = new ParametrosFacade(request);
			oParametros.setNombre("JUEGOS_BLOQUEADOS");
			juegosBloqueados = parametrosFacade.consultarParametrosFacade(oParametros).getValor();
			request.setAttribute("JUEGOS_BLOQUEADOS", juegosBloqueados != null ? juegosBloqueados : "");
			// FIN consultamos los juegos bloqueados

			// clave del cache supervisor + perfildelUsuario
			String claveCached = (new StringBuffer(usuario.getIdSupervisor()).append("_").append(usuario.getIdRol())).toString();

			juegoFacade = new JuegoFacade(request);
			usuarioFacade = new UsuarioFacade(request);

			listaDeporte = juegoFacade.listaDeporteCombinacionActivoFacade(false);

			while (listaDeporte.next()) {
				error[listaDeporte.getInt("id_deporte")] = Validator.isEmpty(listaDeporte.getString("combinacion"), "").split(",");
			}

			/* vamos almacenar la apuesta */
			boolean vacia = true;
			if (request.getParameterValues("codigo") != null && request.getParameterValues("codigo").length > 0) {
				ArrayList listaJugada = new ArrayList();
				session.setAttribute("apuesta", listaJugada);

				String[] juego = request.getParameterValues("juego");
				String[] numero = request.getParameterValues("numero");
				String[] codigo = request.getParameterValues("codigo");
				String[] tipo = request.getParameterValues("tipo");
				String[] cantidad = request.getParameterValues("cantidad");
				String[] referencia = request.getParameterValues("referencia");
				String[] equipo = request.getParameterValues("equipo");
				String[] logro = request.getParameterValues("logro");
				String[] padre = request.getParameterValues("padre");
				String[] idDeporte = request.getParameterValues("deporte");

				int numeroLogro = 0;
				double montoOriginalJugada = 0;

				try {
					apuesta = Double.parseDouble(request.getParameter("montoApostar"));
				}
				catch (java.lang.NumberFormatException e) {
					apuesta = 0;
				}

				montoOriginalJugada = apuesta;

				CalculadoraForm calc = null;
				for (int i = 0; i < codigo.length; i++) {
					if (codigo[i] != null && !codigo[i].trim().equals("")) {
						vacia = false;
						calc = new CalculadoraForm();
						calc.setJuego(juego[i]);
						calc.setNumero(numero[i]);
						calc.setCodigo(codigo[i]);
						calc.setTipo(tipo[i]);
						calc.setCantidad(cantidad[i]);
						calc.setReferencia(referencia[i]);
						calc.setEquipo(equipo[i]);
						calc.setLogro(logro[i]);
						calc.setPadre(padre[i]);
						calc.setDeporte(idDeporte[i]); // request.getParameter("deporte"));
						calc.setMontoApostar(request.getParameter("montoApostar"));
						calc.setMontoPremio(request.getParameter("montoPremio"));
						listaJugada.add(calc);

						nLogro = Double.parseDouble(logro[i]);
						if (nLogro > 0) {
							apuesta = apuesta + (apuesta * (nLogro / 100));
						}
						else if (nLogro < 0) {
							apuesta = apuesta + (apuesta / ((nLogro * -1) / 100));
						}
						montoPremio = Math.round(apuesta);

						numeroLogro++;
					}
				}

				// aplicamos las reglas de pago x veces
				if (apuesta > 0) {
					montoPremio = JuegoFacade.calcularPagoVeces(numeroLogro, montoOriginalJugada, montoPremio, usuario);
				}

				if (request.getParameter("agregar") != null && request.getParameter("agregar").equals("true")) {
					/* grabamos la apuesta */
					double montoPorDerecho = 0;
					double topePorDerecho = 0;

					try {
						// validamos el premio de la apuesta
						montoPremioTicket = Double.parseDouble(request.getParameter("montoPremio"));
						if (montoPremioTicket != montoPremio) {
							throw new MountPayInvalidException("El monto del premio no corresponde con el monto de la apuesta");
						}

						if (listaJugada.size() > 0) {
							calc = (CalculadoraForm) listaJugada.get(0);
							if (Integer.parseInt(calc.getMontoApostar()) <= 0) {
								throw new NumberFormatException("El monto debe ser mayor a cero");
							}
							// evaluamos si las apuestas son validas
							ArrayList jug = new ArrayList();

							String antN = null;
							String cad = null;
							String cad2 = null;
							int con = 0;
							int deporte = 0;

							int size = listaJugada.size();

							// validamos que los logros no esten repetidos
							ArrayList<String> repetido = new ArrayList<String>();
							for (int i = 0; i < size; i++) {
								calc = (CalculadoraForm) listaJugada.get(i);
								if (repetido.contains(calc.toString())) {
									vacia = true;
									throw new PlayNotValidException("La combinacion de la jugada no es valida.");
								}
								repetido.add(calc.toString());
							}

							for (int i = 0; i < listaJugada.size(); i++) {
								calc = (CalculadoraForm) listaJugada.get(i);

								try {
									if (Integer.parseInt(calc.getLogro().replaceAll("\\+", "")) == 0) {
										throw new LogroIsEmptyException("El logro no puede estar en cero.");
									}
								}
								catch (Exception e) {
									throw new LogroIsEmptyException("El logro no puede estar en cero o en blanco.");
								}

								if (size == 1) { // es jugada por derecho
									// validamos el tope para este tipo de
									// jugadas
									JuegoEquipoIF oJuegoEquipoTO = new JuegoEquipoTO();
									oJuegoEquipoTO.setIdJuego(calc.getJuego());
									oJuegoEquipoTO.setReferencia(calc.getReferencia());

									JugadaJuegoEquipoIF oJugadaJuegoEquipoTO = new JugadaJuegoEquipoTO();
									oJugadaJuegoEquipoTO.setTipo(calc.getTipo());

									montoPorDerecho = juegoFacade.consultarMontoJugadoPorDerechoFacade(oJuegoEquipoTO, oJugadaJuegoEquipoTO, usuario);
									double montoJugada = Double.parseDouble(calc.getMontoApostar());
									topePorDerecho = Double.parseDouble(usuario.getTopePorDerecho());

									if (topePorDerecho > 0 && (montoPorDerecho + montoJugada) > topePorDerecho) {
										StringBuffer sb = new StringBuffer();
										sb.append("El monto apostado sobrepasa el tope de apuestas por derecho. Tope maximo = ");
										sb.append(topePorDerecho);
										sb.append(", Monto acumulado = ");
										sb.append(montoPorDerecho);
										throw new TopePorDerechoExcedidoException(sb.toString(), topePorDerecho, montoPorDerecho);
									}
								}

								deporte = Integer.parseInt(calc.getDeporte());
								deporte = (calc.getDeporte().equals(Constants.ID_EQUIPO_BEISBOL_5) || calc.getDeporte().equals(Constants.ID_EQUIPO_BEISBOL_1) || calc.getDeporte().equals(Constants.ID_EQUIPO_BEISBOL_0) ? Integer.parseInt(Constants.ID_EQUIPO_BEISBOL) : deporte);
								deporte = (calc.getDeporte().equals(Constants.ID_EQUIPO_BASKETBALL_MITAD) ? Integer.parseInt(Constants.ID_EQUIPO_BASKETBALL) : deporte);

								con = 0;
								antN = "0";
								for (int p = 0; p < jug.size(); p++) {
									// if (((CalculadoraForm)
									// jug.get(p)).getJuego().equals(calc.getJuego())) {
									if (((CalculadoraForm) jug.get(p)).getJuego().equals(calc.getPadre())) {
										antN = ((CalculadoraForm) jug.get(p)).getNumero();
										con++;
									}
								}
								if (con == 0) {
									jug.add(calc);
									continue;
								}
								if (con >= 2) {
									throw new PlayNotValidException("La combinacion de la apuesta no es valida.");
								}
								cad = antN.concat("-").concat(calc.getNumero());
								cad2 = calc.getNumero().concat("-").concat(antN);

								if (error[deporte].length == 0) {
									throw new PlayNotValidException("La combinacion de la apuesta no es valida.");
								}

								for (int x = 0; x < error[deporte].length; x++) {
									if (error[deporte][x].trim().equals("")) {
										throw new PlayNotValidException("La combinacion de la apuesta no es valida.");
									}
									if (error[deporte][x].equals(cad) || error[deporte][x].equals(cad2)) {
										throw new PlayNotValidException("La combinacion de la apuesta no es valida.");
									}
								}

								jug.add(calc);
							}

							JugadaIF oJugadaTO = juegoFacade.insertarJugadaFacade(listaJugada, usuario);
							vacia = true;
							if (usuario.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA)) {
								// colocamos en session el ticket a imprimir
								CachedRowSet crs = juegoFacade.ultimaJugadaFacade(usuario, oJugadaTO);

								request.getSession().setAttribute("ULTIMO_TICKET", crs);
								request.setAttribute("imp", "true");
							}
						}
						else {
							errores.add("error", new ActionError("error.apuesta.vacia"));
						}
					}
					catch (GameClosedException e) {
						errores.add("error", new ActionError("error.apuesta.gameClosed"));
						listaJugada = new ArrayList();
						session.removeAttribute("apuesta");

						// eliminamos el cache ya que algunos juegos ya no estan
						// disponibles
						Constants.inicializarCache();
					}
					catch (MountPayInvalidException e) {
						errores.add("error", new ActionError("error.apuesta.MontoPremioInvalido"));
					}
					catch (TopePorDerechoExcedidoException e) {
						errores.add("error", new ActionError("error.apuesta.topePorDerechoExcedido", new Double[] { new Double(e.getMaximo()), new Double(e.getAcumulado()) }));
					}
					catch (TopePorPagarCombinacionExcedidoException e) {
						errores.add("error", new ActionError("error.apuesta.topePorPagarCombinacionExcedido", new Double[] { new Double(e.getMaximo()), new Double(e.getAcumulado()) }));
					}
					catch (TopePorPagarCombinacionAdministradorExcedidoException e) {
						errores.add("error", new ActionError("error.apuesta.topePorPagarCombinacionAdministradorExcedido", new Double[] { new Double(e.getMaximo()), new Double(e.getAcumulado()) }));
					}
					catch (TopePorCombinacionExcedidoException e) {
						errores.add("error", new ActionError("error.apuesta.topePorCombinacionExcedido", new Double[] { new Double(e.getMaximo()), new Double(e.getAcumulado()) }));
					}
					catch (TopePorCombinacionAdministradorExcedidoException e) {
						errores.add("error", new ActionError("error.apuesta.topePorCombinacionAdministradorExcedido", new Double[] { new Double(e.getMaximo()), new Double(e.getAcumulado()) }));
					}
					catch (LogroIsEmptyException e) {
						errores.add("error", new ActionError("error.apuesta.logroIsEmpty"));
					}
					catch (LogrosChangeException e) {
						errores.add("error", new ActionError("error.apuesta.logroChange"));
						session.removeAttribute("apuesta");
					}
					catch (PlayNotValidException e) {
						errores.add("error", new ActionError("error.jugada.combinacionIncorrecta"));
					}
					catch (UserWithOutCreditException e) {
						errores.add("error", new ActionError("error.usuario.notCredit"));
					}
					catch (NumberFormatException e) {
						errores.add("error", new ActionError("error.jugada.monto"));
					}
					catch (Exception e) {
						errores.add("error", new ActionError("error.jugada.insertar"));
					}
				}
			}
			if (vacia) {
				session.removeAttribute("apuesta");
			}

			// preguntamos el total de juegos abiertos
			int totalAbiertos = juegoFacade.listaJuegoAbiertoTotalFacade(usuario, oDeporte);

			listaJuego = null;
			if (Constants.CACHE_LISTA_JUEGO_PARA_JUGADA.containsKey(claveCached)) {
				listaJuego = (CachedRowSet) Constants.CACHE_LISTA_JUEGO_PARA_JUGADA.get(claveCached);
				log.debug("total en cached = " + listaJuego.size());
			}
			


			// listaJuego=null; // ANULA EL CACHE
			if (listaJuego == null || totalAbiertos != listaJuego.size()) {
				listaJuego = juegoFacade.listaJuegoAbiertoFacade(usuario, oDeporte, false);
				log.debug("total listados = " + listaJuego.size());
			}

			Constants.CACHE_LISTA_JUEGO_PARA_JUGADA.put(claveCached, listaJuego);
			listaJuego.beforeFirst();

			if (listaJuego.size() > 0) {

				StringBuffer idJuegosAbiertos = new StringBuffer("-");
				RegisterGameActionForm item = null;
				// for(int i=0; i<listaJuego.size();i++) {
				int idJuego = 0;
				int i = -1;
				String sep = "";

				listaReferencia.append("[");
				while (listaJuego.next()) {
					if (listaJuego.getInt("id_juego") != idJuego) {
						idJuegosAbiertos.append(idJuego).append("-");
						i = -1;
					}
					i++;
					idJuego = listaJuego.getInt("id_juego");

					listaReferencia.append(sep);
					listaReferencia.append("{'cod':'P1','ref':'P2'}".replaceAll("P1", listaJuego.getString("id_usuario_juego_equipo").concat("_").concat(listaJuego.getString("referencia"))).replaceAll("P2", listaJuego.getString("referencia")));
					if (!listaJuego.getString("referencia").equals("0")) {
						listaReferencia.append(",{'cod':'P1','ref':'P2'}".replaceAll("P1", listaJuego.getString("id_usuario_juego_equipo").concat("_").concat(listaJuego.getString("referencia_runline"))).replaceAll("P2", listaJuego.getString("referencia_runline")));
					}
					if (!listaJuego.getString("referencia").equals("0")) {
						listaReferencia.append(",{'cod':'P1','ref':'P2'}".replaceAll("P1", listaJuego.getString("id_usuario_juego_equipo").concat("_").concat(listaJuego.getString("referencia_super_runline"))).replaceAll("P2", listaJuego.getString("referencia_super_runline")));
					}
					if (!listaJuego.getString("referencia").equals("0")) {
						listaReferencia.append(",{'cod':'P1','ref':'P2'}".replaceAll("P1", listaJuego.getString("id_usuario_juego_equipo").concat("_").concat(listaJuego.getString("referencia_ab"))).replaceAll("P2", listaJuego.getString("referencia_ab")));
					}
					sep = Constants.COMA;

					Properties pro = null;
					if (listaJuego.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL_5) || listaJuego.getString("id_deporte").equals(Constants.ID_EQUIPO_BASKETBALL_MITAD)) {
						// llenamos los juegos del mitad
						pro = new Properties();
						pro.setProperty("id_deporte", listaJuego.getString("id_deporte"));
						pro.setProperty("id_juego", listaJuego.getString("id_juego"));
						pro.setProperty("id_juego_padre", listaJuego.getString("id_juego_padre"));
						pro.setProperty("id_usuario_juego_equipo", listaJuego.getString("id_usuario_juego_equipo"));
						pro.setProperty("referencia", listaJuego.getString("referencia"));
						pro.setProperty("referencia_runline", listaJuego.getString("referencia_runline"));
						pro.setProperty("referencia_super_runline", listaJuego.getString("referencia_super_runline"));
						pro.setProperty("referencia_ab", listaJuego.getString("referencia_ab"));
						pro.setProperty("money_line", listaJuego.getString("money_line"));
						pro.setProperty("spread", listaJuego.getString("spread"));
						pro.setProperty("spread_logro", listaJuego.getString("spread_logro"));
						pro.setProperty("super_spread", listaJuego.getString("super_spread"));
						pro.setProperty("super_spread_logro", listaJuego.getString("super_spread_logro"));
						pro.setProperty("total", listaJuego.getString("total"));
						pro.setProperty("total_logro", listaJuego.getString("total_logro"));

						listaJuego5.put(listaJuego.getString("id_juego_padre") + "_" + i, pro);
					}
					if (listaJuego.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL_1)) {
						// llenamos los juegos del mitad
						pro = new Properties();
						pro.setProperty("id_deporte", listaJuego.getString("id_deporte"));
						pro.setProperty("id_juego", listaJuego.getString("id_juego"));
						pro.setProperty("id_juego_padre", listaJuego.getString("id_juego_padre"));
						pro.setProperty("id_usuario_juego_equipo", listaJuego.getString("id_usuario_juego_equipo"));
						pro.setProperty("referencia", listaJuego.getString("referencia"));
						pro.setProperty("money_line", listaJuego.getString("money_line"));

						listaJuego1.put(listaJuego.getString("id_juego_padre") + "_" + i, pro);
					}
					if (listaJuego.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL_0)) {
						// llenamos los juegos del mitad
						pro = new Properties();
						pro.setProperty("id_deporte", listaJuego.getString("id_deporte"));
						pro.setProperty("id_juego", listaJuego.getString("id_juego"));
						pro.setProperty("id_juego_padre", listaJuego.getString("id_juego_padre"));
						pro.setProperty("id_usuario_juego_equipo", listaJuego.getString("id_usuario_juego_equipo"));
						pro.setProperty("referencia", listaJuego.getString("referencia"));
						pro.setProperty("money_line", listaJuego.getString("money_line"));

						listaJuego0.put(listaJuego.getString("id_juego_padre") + "_" + i, pro);
					}

				}
				listaReferencia.append("]");

				listaJuego.beforeFirst();

				request.setAttribute("listaJuego", listaJuego);
				request.setAttribute("listaJuego5", listaJuego5);
				request.setAttribute("listaJuego1", listaJuego1);
				request.setAttribute("listaJuego0", listaJuego0);
				request.setAttribute("listaIdJuegoAbierto", idJuegosAbiertos.toString());
				request.setAttribute("listaReferencia", listaReferencia);
			}
			else {
				request.setAttribute("listaIdJuegoAbierto", " ");
			}

			// request.setAttribute("lista", lista);

			listaDeporte.beforeFirst();
			request.setAttribute("listaDeporte", listaDeporte);

			if (usuario.getIdRol().equals(Constants.ROL_JUGADOR)) {
				String saldo = usuarioFacade.consultarSaldoUsuarioFacade(usuario, false);

				double nSaldo = Double.parseDouble(saldo);

				request.setAttribute("saldo", Constants.formatNumber(nSaldo));
				request.setAttribute("saldoNumerico", String.valueOf(nSaldo));

				request.setAttribute("saldoFree", "0.00");
				request.setAttribute("saldoFreeNumerico", "0.00");
			}
			else {
				request.setAttribute("saldo", "0.00");
				request.setAttribute("saldoNumerico", "0.00");
				request.setAttribute("saldoFree", "0.00");
				request.setAttribute("saldoFreeNumerico", "0.00");
			}

		}
		catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
		catch (Exception e) {
			errores.add("error", new ActionError("error.sistema.general"));
			e.printStackTrace();
		}
		saveErrors(request, errores);

		// previene el cache
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		if (request.getParameter("simple") != null && request.getParameter("simple").equals("true")) {
			if (errores.size() > 0) {
				request.getSession().setAttribute("sendErrores", errores);
			}
			return mapping.findForward("successCalculadora");
		}
		else {
			if (request.getSession().getAttribute("sendErrores") != null) {
				errores = (ActionErrors) request.getSession().getAttribute("sendErrores");
				saveErrors(request, errores);
			}
			return mapping.findForward("success");
		}
	}
}
