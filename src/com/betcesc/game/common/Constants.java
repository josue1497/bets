package com.betcesc.game.common;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.bean.DominioBean;
import com.betcesc.game.bean.PuntosAltaBajaBean;
import com.betcesc.game.exceptions.SessionDuplicateException;
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.facade.UsuarioFacade;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.ListaJuegoTO;
import com.betcesc.game.to.UsuarioTO;
import com.jade.util.Date;
import com.jade.util.lbda.EjecutorSql;

/**
 * @author jairo
 */
public class Constants {

	private static transient HashMap<String, UsuarioIF> usuarios = new HashMap<String, UsuarioIF>();
	private static transient HashMap<String, DominioBean> dominio = null;

	private static Logger log = Logger.getLogger(Constants.class);

	public static final String VISTA_BODY = "0";
	public static final String VISTA_HEADER = "1";
	public static final String VISTA_ATENCION = "2";
	public static final String VISTA_PUBLICIDAD = "3";

	public static String NOMBRE_IMAGEN_HEADER_ORIGINAL = "./images/head1.jpg";
	public static String NOMBRE_IMAGEN_SOPORTE_ORIGINAL = "./images/soporte.jpg";
	public static String NOMBRE_IMAGEN_HEADER = "./images/head1.jpg";
	public static String NOMBRE_IMAGEN_SOPORTE = "./images/soporte.jpg";

	public static String ID_USUARIO_MUNECO_PERMISO_ELIMINAR = "1111";
	public static String ID_USUARIO_SERGIO_PERMISO_ELIMINAR = "1031";

	public static final String COMBINACIONES_NO_VALIDAS = "1-2,1-7,1-8,1-4,1-5,1-10,1-11,2-7,2-8,2-4,2-5,2-10,2-11,2-3,2-6,2-9,2-12,3-9,3-6,3-12,3-5,3-8,3-11,4-7,4-8,4-5,4-10,4-11,5-7,5-8,5-10,5-11,5-6,5-12,6-9,6-12,7-8,7-10,7-11,7-13,7-16,8-10,8-11,8-13,8-16,8-9,8-12,9-12,9-13,9-16,9-11,10-11,10-13,10-16,11-13,11-16,11-12,12-13,12-16,13-16";

	public static TreeMap ULTIMA_JUGADA = new TreeMap();

	public static final String COMA = ",";

	public static final String ALTA = "A";
	public static final String BAJA = "B";

	public static final int ADM_GENERAL = 0;
	public static final int ADM_LISTERO = 1;

	public static CachedRowSet MENSAJES_POR_USUARIO = null;

	public static final int CANTIDAD_EQUIPOS = 100;

	public static final String ID_STATUS_JUGADA_SUSPENDIDA = "6";
	public static final String ID_STATUS_JUGADA_PENDIENTE = "3";

	public static final String PATRON_CADENA = "[^--9@-z������� ]";
	public static final String PATRON_ENTERO = "[^0-9]";
	public static final ActionForward FORWARD_START = new ActionForward("/endSession.do");

	public static final String PASSWORD_MIN_LENGTH = "5";

	public static final String CUENTA_JUGADOR_TIPO_SIMPLE = "S";
	public static final String CUENTA_JUGADOR_TIPO_JUGADA = "J";
	public static final String CUENTA_JUGADOR_TIPO_PREMIO = "P";

	public static final long TIMEOUT = 10 * 60 * 1000;
	public static final long TIMEOUT_CHECK = 10 * 1000;

	public static final String ROL_ADMINISTRADOR = "1";
	public static final String ROL_ADMINISTRADOR_DE_TAQUILLA = "2";
	public static final String ROL_JUGADOR_DE_TAQUILLA = "3";
	public static final String ROL_JUGADOR = "4";
	public static final String ROL_AUTO_JUGADOR = "5";

	public static final String[] PERFIL = new String[] { "", "Administrador", "Administrador de Taquilla", "Jugador Taquilla", "Jugador", "Auto Jugador" };

	public static final String STATUS_DEPORTE_ACTIVO = "1";
	public static final String STATUS_DEPORTE_INACTIVO = "2";

	public static final String STATUS_USUARIO_ACTIVO = "1";
	public static final String STATUS_USUARIO_INACTIVO = "2";

	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdf4 = new SimpleDateFormat("HH");
	public static SimpleDateFormat sdf5 = new SimpleDateFormat("mm");
	public static SimpleDateFormat sdf6 = new SimpleDateFormat("hh:mm:ss a");
	public static SimpleDateFormat sdf7 = new SimpleDateFormat("MM");
	public static SimpleDateFormat sdf8 = new SimpleDateFormat("yyyy");
	public static SimpleDateFormat sdf9 = new SimpleDateFormat("hh:mma");
	public static SimpleDateFormat sdf10 = new SimpleDateFormat("dd/MM/yyyy hh:mma");

	public static final String STATUS_JUEGO_BORRADOR = "1";
	public static final String STATUS_JUEGO_ABIERTO = "2";
	public static final String STATUS_JUEGO_SUSPENDIDO = "3";
	public static final String STATUS_JUEGO_CERRADO = "4";
	public static final String STATUS_JUEGO_TOTALIZADO = "5";

	public static final String STATUS_BLOQUEADO = "Bloq./Abierto";

	public static final String STATUS_JUGADA_EN_JUEGO = "1";
	public static final String STATUS_JUGADA_ELIMINADA = "2";
	public static final String STATUS_JUGADA_PENDIENTE = "3";
	public static final String STATUS_JUGADA_PAGADA = "4";
	public static final String STATUS_JUGADA_VENCIDA = "5";
	public static final String STATUS_JUGADA_SUSPENDIDA = "6";
	public static final String STATUS_JUGADA_GANADOR = "7";
	public static final String STATUS_JUGADA_PERDEDOR = "8";
	public static final String STATUS_JUGADA_ANULADA = "9"; // perdedor

	public static final String ID_USUARIO_ADMINISTRADOR = "1";

	public static final String ID_EQUIPO_EMPATE = "1";

	public static final String JUEGO_MULTIPLE_EQUIPO = "2";

	public static final String TIPO_ALTA = "A";
	public static final String TIPO_BAJA = "B";
	public static final String TIPO_RUNLINE = "RL";
	public static final String TIPO_SUPER_RUNLINE = "SR";
	public static final String TIPO_MONEYLINE = "ML";
	public static final String TIPO_SI = "SI";
	public static final String TIPO_NO = "NO";
	public static final String TIPO_EMPATE = "E";
	public static final String TIPO_ANOTAPRIMERO = "AP";

	public static final String OPERACION_INGRESO = "I";
	public static final String OPERACION_EGRESO = "E";

	public static final String CONCEPTO_JUGADA = "Jugada";

	public static final String ID_EQUIPO_BASKETBALL = "6";
	public static final String ID_EQUIPO_BASKETBALL_MITAD = "20";

	public static final String ID_EQUIPO_SOCCER = "1";
	public static final String ID_EQUIPO_SOCCER_MITAD = "21";

	public static final String ID_EQUIPO_BEISBOL = "4";
	public static final String ID_EQUIPO_BEISBOL_5 = "7";
	public static final String ID_EQUIPO_BEISBOL_1 = "8";
	public static final String ID_EQUIPO_BEISBOL_0 = "18";
	
	public static final String ID_DEPORTE_ANIMALITOS= "26";

	public static final String GANADOR_NO = "0";
	public static final String GANADOR_SI = "1";
	public static final String GANADOR_EMPATE = "2";
	public static final String GANADOR_SUSPENDIDO = "3";

	public static final int TIPO_JUGADA_MONEYLINE = 1;
	public static final int TIPO_JUGADA_RUNLINE = 2;
	public static final int TIPO_JUGADA_ALTABAJA = 3;
	public static final int TIPO_JUGADA_SI_NO = 4;
	public static final int TIPO_JUGADA_SUPER_RUNLINE = 5;
	public static final int TIPO_APUESTA_ANOTAPRIMERO = 6;

	public static final String VIEW_GAMES = "VIEW_GAMES";

	public static final String PAGO_CLAVE_NO = "0";
	public static final String PAGO_CLAVE_SI = "1";

	// variables de cache
	public static HashMap CACHE_LISTA_JUEGO_ABIERTO = new HashMap();
	public static HashMap CACHE_LISTA_JUEGO_PARA_JUGADA = new HashMap();

	public static ArrayList CACHE_LISTA_RESULTADOS = new ArrayList();

	public static String LOGROS_MINIMO_AUTOREGISTRO = "4";

	public static TreeMap puntosPorDefecto = null;

	public static synchronized void inicializarCache() {
		CACHE_LISTA_JUEGO_ABIERTO = new HashMap();
		CACHE_LISTA_JUEGO_PARA_JUGADA = new HashMap();
	}
	
	public static final String TIME_ZONE="America/La_Paz";

	public static synchronized void inicializarCacheResultado() {
		CACHE_LISTA_RESULTADOS = new ArrayList();
	}

	public static HashMap getUsuarios() {
		return usuarios;
	}

	public static UsuarioTO getUserSession(HttpServletRequest request) throws SessionInvalidException {
		if (usuarios.size() == 0 || request.getSession().getAttribute("usuario") == null) {
			throw new SessionInvalidException();
		}
		UsuarioTO user = (UsuarioTO) request.getSession().getAttribute("usuario");

		if (usuarios.containsKey(user.getIdUsuario())) {
			user.setTime(new Date().getTime());

			// consultaremos si el usuario tiene mensajes por revisar
			if (Constants.MENSAJES_POR_USUARIO == null) {
				UsuarioFacade oUsuarioFacade = new UsuarioFacade(request);
				Constants.MENSAJES_POR_USUARIO = oUsuarioFacade.listaMensajeUserFacade();
			}
			if (Constants.MENSAJES_POR_USUARIO != null) {
				UsuarioFacade.obtenerMensajes(request, user);
			}

			return user;
		} else {
			throw new SessionInvalidException();
		}
	}

	public static void initializeUsuariosInSession() {
		usuarios = new HashMap();

	}

	public static boolean isUserInSession(UsuarioIF oUsuarioTO) throws SessionInvalidException {
		if (usuarios.size() == 0) {
			throw new SessionInvalidException();
		}
		return usuarios.containsKey(oUsuarioTO.getIdUsuario());
	}

	public static boolean isUserInSession(String idUsuario) throws SessionInvalidException {
		if (usuarios.size() == 0) {
			return false;
		}
		return usuarios.containsKey(idUsuario);
	}

	public static void RegisterUserInSession(UsuarioIF oUsuarioTO) throws SessionDuplicateException {
		if (usuarios.containsKey(oUsuarioTO.getIdUsuario())) {
			throw new SessionDuplicateException();
		}
		oUsuarioTO.setTime(new Date().getTime());
		usuarios.put(oUsuarioTO.getIdUsuario(), oUsuarioTO);
	}

	public static void deleteUserInSession(UsuarioIF oUsuarioTO) throws SessionDuplicateException {
		if (oUsuarioTO != null && usuarios.containsKey(oUsuarioTO.getIdUsuario())) {
			UsuarioIF usuario = (UsuarioIF) usuarios.get(oUsuarioTO.getIdUsuario());
			try {
				usuario.getSession().removeAttribute("usuario");
				usuario.getSession().removeAttribute("menu");
			} catch (java.lang.IllegalStateException e) {
				// no hacemos nada
			}
			usuarios.remove(oUsuarioTO.getIdUsuario());
		}
	}

	public static void validUserInSession() {
		/*
		 * recorremos el arreglo de usuario para verificar quien ya no es valido
		 */

		Date tiempo = new Date();

		for (Iterator iter = usuarios.keySet().iterator(); iter.hasNext();) {
			String idUsuario = (String) iter.next();

			UsuarioIF usuario = (UsuarioIF) usuarios.get(idUsuario);

			if (usuario != null) {
				log.info("tiempo : " + (usuario.getTime() + Constants.TIMEOUT) + " <= " + tiempo.getTime());
				if ((usuario.getTime() + Constants.TIMEOUT) <= tiempo.getTime()) {
					try {
						usuario.getSession().removeAttribute("usuario");
						usuario.getSession().removeAttribute("menu");
					} catch (java.lang.IllegalStateException e) {
						// no hacemos nada
					}

					usuarios.remove(idUsuario);

					log.info("Logout for ".concat(usuario.getNombre()));
				}
			}
		}
	}

	/**
	 * Devuelve la fecha en espa�ol ejemplo: Domingo, 17 Septiembre de 2006
	 */
	public static String getFechaLarga() {
		Date fecha = null;
		return getFechaLarga(fecha);
	}

	public static String getFechaLargaSinAno() {
		Date fecha = null;
		return getFechaLargaSinAno(fecha);
	}

	public static String getFechaLarga(String fechaTrabajo) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if (fechaTrabajo != null && !fechaTrabajo.endsWith("null")) {
				return getFechaLarga(Date.convert(sdf.parse(fechaTrabajo)));
			} else {
				return getFechaLarga();
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String getFechaLarga(Date fechaTrabajo) {
		StringBuffer f = new StringBuffer();
		try {
			String[] dias = { "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" };
			String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
			Calendar fecha = Date.getCalendar();
			if (fechaTrabajo != null) {
				fecha.setTime(fechaTrabajo);
			}
			f.append(dias[fecha.get(Calendar.DAY_OF_WEEK) - 1]);
			f.append(", ");
			f.append(fecha.get(Calendar.DAY_OF_MONTH));
			f.append(" ");
			f.append(meses[fecha.get(Calendar.MONTH)]);
			f.append(" de ");
			f.append(fecha.get(Calendar.YEAR));
		} catch (Exception e) {
			f.setLength(0);
			e.printStackTrace();
		}
		// log.info("Metodo fecha = " + f.toString());
		return f.toString();
	}

	public static String getFechaLargaSinAno(Date fechaTrabajo) {
		StringBuffer f = new StringBuffer();
		try {
			String[] dias = { "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" };
			String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
			Calendar fecha = Date.getCalendar();
			if (fechaTrabajo != null) {
				fecha.setTime(fechaTrabajo);
			}
			f.append(dias[fecha.get(Calendar.DAY_OF_WEEK) - 1]);
			f.append(" ");
			f.append(fecha.get(Calendar.DAY_OF_MONTH));
			f.append(" ");
			f.append(meses[fecha.get(Calendar.MONTH)]);
		} catch (Exception e) {
			f.setLength(0);
			e.printStackTrace();
		}
		// log.info("Metodo fecha = " + f.toString());
		return f.toString();
	}

	public static String getFechaLargaWithHour(Date fechaTrabajo) {
		StringBuffer f = new StringBuffer();
		try {
			String[] dias = { "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" };
			String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
			Calendar fecha = Date.getCalendar();
			if (fechaTrabajo != null) {
				fecha.setTime(fechaTrabajo);
			}
			f.append(dias[fecha.get(Calendar.DAY_OF_WEEK) - 1]);
			f.append(", ");
			f.append(fecha.get(Calendar.DAY_OF_MONTH));
			f.append(" ");
			f.append(meses[fecha.get(Calendar.MONTH)]);
			f.append(" de ");
			f.append(fecha.get(Calendar.YEAR));
			f.append(" ");
			f.append(fecha.get(Calendar.HOUR));
			f.append(":");
			f.append(fecha.get(Calendar.MINUTE));
			f.append(" ");
			f.append(fecha.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM");
		} catch (Exception e) {
			f.setLength(0);
			e.printStackTrace();
		}
		// log.info("Metodo fecha = " + f.toString());
		return f.toString();
	}

	public static String getFechaCorta() {
		return getFechaCorta(null);
	}

	public static String getHora() {
		return getHora(null);
	}

	public static String getFechaCorta(Date fechaTrabajo) {
		StringBuffer f = new StringBuffer();
		try {
			Calendar fecha = Date.getCalendar();
			if (fechaTrabajo != null) {
				fecha.setTime(fechaTrabajo);
			}
			f.append((fecha.get(Calendar.DAY_OF_MONTH) < 10 ? "0" : "") + fecha.get(Calendar.DAY_OF_MONTH));
			f.append("/");
			f.append(((fecha.get(Calendar.MONTH) + 1) < 10 ? "0" : "") + (fecha.get(Calendar.MONTH) + 1));
			f.append("/");
			f.append(fecha.get(Calendar.YEAR));

		} catch (Exception e) {
			f.setLength(0);
			e.printStackTrace();
		}
		return f.toString();
	}

	public static String getHora(Date fechaTrabajo) {
		StringBuffer f = new StringBuffer();
		try {
			fechaTrabajo = new Date();
			f.append(sdf6.format(fechaTrabajo));

		} catch (Exception e) {
			f.setLength(0);
			e.printStackTrace();
		}
		return f.toString();
	}

	public static String getFechaLargaSQL(Date fecha) {
		return sdf3.format(fecha);
	}

	public static String getFechaLargaSQL() {
		
		log.info("FECHA "  + new Date());
		return sdf3.format(new Date());
	}

	public static String getFechaLargaSQL(String fecha) throws ParseException {
		return sdf3.format(sdf.parse(fecha));
	}

	public static synchronized Date getFechaLargaSqlToJava(String fecha) throws ParseException {
		return Date.convert(sdf3.parse(fecha));
	}

	public static String getFechaLargaSQL(String fecha, int dias) throws ParseException {
		Calendar c = Date.getCalendar();
		c.setTime(sdf3.parse(fecha));
		if (dias > 0) {
			c.add(Calendar.DATE, dias);
		}
		return sdf3.format(Date.convert(c));
	}

	public static String getFechaLargaSQL(Date fecha, int dias) throws ParseException {
		Calendar c = Date.getCalendar();
		c.setTime(fecha);
		c.add(Calendar.DATE, dias);
		return sdf3.format(Date.convert(c));
	}

	public static String getFechaCortaSQL(String fecha) throws ParseException {
		return sdf2.format(sdf.parse(fecha));
	}

	public static synchronized String getFechaLargaSqlToHtml(String fecha) throws ParseException {
		if (fecha != null && fecha.trim().length() > 0)
			return sdf10.format(sdf3.parse(fecha));
		else
			return null;
	}

	public static String getFechaCortaHtmlToSql(String fecha) throws ParseException {
		if (fecha != null && fecha.trim().length() > 0) {

			try {

				return sdf3.format(sdf.parse(fecha));
			} catch (Exception e) {
				log.error("Error intentando parsear la fecha" + fecha);
				return null;
			}

		} else
			return null;
	}

	public static synchronized String getFechaCortaSqlToHtml(String fecha) throws ParseException {

		String strReturn = "";

		if (fecha.trim() == "") {
			log.warn("Llego la fecha vacia, se retorna vacio");
			return strReturn;
		}
		try {

			if (fecha.length() == 21) {
				fecha = fecha.substring(0, fecha.length() - 2);
			}

			strReturn = sdf.format(sdf3.parse(fecha));
		} catch (Exception e) {
			log.error("Error intentando parsear la fecha" + fecha);
			return "Error de fecha";
		}

		return strReturn;
	}

	public static String getHoraSqlToHtml(String fecha) throws ParseException {
		return sdf4.format(sdf3.parse(fecha));
	}

	public static String getMinutoSqlToHtml(String fecha) throws ParseException {
		return sdf5.format(sdf3.parse(fecha));
	}

	public static String parseInt(String valor) {
		if (valor == null || valor.trim().equals(""))
			return "0";

		try {
			valor = valor.replace('+', ' ').trim();
			valor = (valor.startsWith("-") && Integer.parseInt(valor) == 0 ? "-" : "").concat(String.valueOf(Integer.parseInt(valor)));
		} catch (NumberFormatException e) {
			return "0";
		}

		return valor;
	}

	public static String parseDouble(String valor) {
		if (valor == null || valor.trim().equals(""))
			return "0";

		try {
			valor = valor.replace('+', ' ').trim();
			valor = String.valueOf(Double.parseDouble(valor));
		} catch (NumberFormatException e) {
			return "0";
		}

		return valor;
	}

	public static String concatDecimal(String entero, String decimal) {
		return (parseDouble(entero.concat(".").concat(decimal)));
	}

	public static String signo(String valor) {
		return (Integer.parseInt(parseInt(valor)) > 0 ? "+".concat(valor) : valor);
	}

	public static String fraccion(String valor) {
		if (Integer.parseInt(parseInt(valor)) != 0) {
			return "&#189;";
		}
		return "";
	}

	public static String tipoFraccion(String valor) {
		return tipoFraccion(valor, true);
	}

	public static String tipoFraccion(String valor, boolean isSigno) {
		return tipoFraccion(valor, isSigno, true);
	}

	public static String tipoFraccion(String valor, boolean isSigno, boolean showMedio) {
		StringBuffer cadena = new StringBuffer();
		;
		if (Double.parseDouble(valor) != 0) {
			double numero = Double.parseDouble(valor);
			double decimal = numero % 1;
			int entero = (int) (numero - decimal);

			if (isSigno) {
				cadena.append(entero > 0 ? "+" : "");
			}
			if (decimal < 0 && entero == 0) {
				cadena.append("-");
			}
			cadena.append(entero);
			if (decimal != 0) {
				cadena.append(showMedio ? "&#189;" : ".5");
			}
		}
		return cadena.toString();
	}

	public static boolean isEmpty(Object valor) {
		String v = String.valueOf(valor);
		return (v == null || v.trim().equals("") || v.trim().equals("null"));
	}

	public static boolean isNull(Object valor) {
		return (valor == null);
	}

	public static boolean isNull(String valor) {
		return (valor == null || valor.trim().equals("") || valor.trim().equals("null"));
	}

	public static boolean isNullEntero(String valor) {
		return (valor == null || valor.trim().equals("") || valor.trim().equals("null") || valor.trim().equals("0"));
	}

	public static String isNull(String valor, String porDefecto) {
		return (valor == null || valor.trim().equals("") || valor.trim().equals("null") ? porDefecto : valor);
	}

	public static String isNull(String valor, String porDefecto, String prefijo) {
		return (valor == null || valor.trim().equals("") || valor.trim().equals("null") ? porDefecto : (prefijo != null ? prefijo : "").concat(valor));
	}

	public static String toJavaScript(ArrayList lista) {
		StringBuffer cadena = new StringBuffer();
		String sep = "";
		cadena.append("[");
		for (int i = 0; i < lista.size(); i++) {
			cadena.append(sep);
			cadena.append("{");
			cadena.append("{");
			cadena.append("}");
			sep = ",";

		}
		cadena.append("]");

		return cadena.toString();
	}

	public static String numero(String valor, int n) {
		return rellenar(valor, n, "0");
	}

	public static String rellenar(String valor, int n, String relleno) {
		if (valor != null) {
			StringBuffer cadena = new StringBuffer();
			for (int i = 0; i < (n - valor.trim().length()); i++) {
				cadena.append(relleno);
			}
			valor = cadena.append(valor.trim()).toString();
		}
		return valor;
	}

	public static String getTipo(String tipo) {
		if (tipo.equals(Constants.TIPO_RUNLINE))
			return "RL";
		if (tipo.equals(Constants.TIPO_SUPER_RUNLINE))
			return "SR";
		if (tipo.equals(Constants.TIPO_MONEYLINE))
			return "ML";
		if (tipo.equals(Constants.TIPO_ALTA))
			return "A";
		if (tipo.equals(Constants.TIPO_BAJA))
			return "B";
		if (tipo.equals(Constants.TIPO_EMPATE))
			return "EMP";
		return tipo;
	}

	public static String getTipoLargo(String tipo) {
		if (tipo.equals(Constants.TIPO_RUNLINE))
			return "RunLine";
		if (tipo.equals(Constants.TIPO_SUPER_RUNLINE))
			return "SuperRunLine";
		if (tipo.equals(Constants.TIPO_MONEYLINE))
			return "MoneyLine";
		if (tipo.equals(Constants.TIPO_ALTA))
			return "Alta";
		if (tipo.equals(Constants.TIPO_BAJA))
			return "Baja";
		if (tipo.equals(Constants.TIPO_EMPATE))
			return "Empate";
		return tipo;
	}

	public static String justificar(String cadena, int limite) {
		if (cadena == null)
			return "<br/>";
		String[] lineas = cadena.split(" ");
		StringBuffer parrafo = new StringBuffer();
		String space = "";
		String salto = "<br/>";
		int c = 0;

		for (int i = 0; i < lineas.length; i++) {
			if (c + space.length() + lineas[i].trim().length() > limite) {
				c = 0;
				parrafo.append(salto);
				space = "";
			}
			c += space.length() + lineas[i].trim().length();
			parrafo.append(space).append(lineas[i].trim());
			if (parrafo.toString().endsWith(salto)) {
				c = 0;
			}
			space = (c == 0 ? "" : " ");
		}

		return parrafo.toString();
	}

	public static String formatNumber(String valor) {
		if (Constants.isNull(valor))
			valor = "0";
		return formatNumber(Double.parseDouble(valor));
	}

	public static String formatNumber(double valor) {
		NumberFormat n = NumberFormat.getInstance(new Locale("EN"));
		n.setGroupingUsed(true);
		n.setMinimumFractionDigits(2);
		n.setMaximumFractionDigits(2);
		return n.format(valor);
	}

	public static String getColorStatus(String status) {
		String color = "class='red'";
		if (status == null) {
			return color;
		}
		if (status.startsWith("Borrador")) {
			color = "class='green'";
		} else if (status.startsWith("Abierto/Adm.")) {
			color = "class='yellow'";
		} else if (status.startsWith("Abierto/Taq.")) {
			color = "class='blue'";
		} else if (status.startsWith("Abierto/Bloq.")) {
			color = "class='agua'";
		} else if (status.startsWith("Cerrado")) {
			color = "class='red'";
		} else if (status.startsWith("Totalizado")) {
			color = "class='morado'";
		} else if (status.startsWith("Suspendido")) {
			color = "class='carne'";
		} else if (status.startsWith("Eliminado")) {
			color = "class='marron'";
		}
		return color;
	}

	public static String getColorStatusJugada(String status) {
		String color = "red";
		if (status == null) {
			return color;
		}

		if (status.equals(STATUS_JUGADA_EN_JUEGO)) {
			color = "green";
		} else if (status.equals(STATUS_JUGADA_ELIMINADA)) {
			color = "yellow";
		} else if (status.equals(STATUS_JUGADA_PENDIENTE)) {
			color = "blue";
		} else if (status.equals(STATUS_JUGADA_PAGADA)) {
			color = "red";
		} else if (status.equals(STATUS_JUGADA_VENCIDA)) {
			color = "morado";
		} else if (status.equals(STATUS_JUGADA_SUSPENDIDA)) {
			color = "carne";
		} else if (status.equals(STATUS_JUGADA_GANADOR)) {
			color = "marron";
		} else if (status.equals(STATUS_JUGADA_PERDEDOR)) {
			color = "agua";
		} else if (status.equals(STATUS_JUGADA_ANULADA)) {
			color = "grama";
		}

		return color;
	}

	public static double getMontoMaximoPago(String montoJugada, String montoPremio) {
		return getMontoMaximoPago(Double.parseDouble(montoJugada), Double.parseDouble(montoPremio));
	}

	public static double getMontoMaximoPago(double montoJugada, double montoPremio) {
		double montoMaximo = 0;
		EjecutorSql oEjecutorSql = new EjecutorSql();
		try {
			CachedRowSet reglas = oEjecutorSql.ejecutaQuery("select * from reglas_pago order by id_reglas_pago", new ArrayList());
			while (reglas.next()) {
				montoMaximo = Math.max(reglas.getDouble("monto_maximo"), montoMaximo);
				if (montoJugada >= reglas.getDouble("rango_ini") && montoJugada <= reglas.getDouble("rango_fin")) {
					montoPremio = Math.min(montoPremio, (montoJugada * reglas.getDouble("multiplo")));
					break;
				}
			}
			montoPremio = Math.min(montoMaximo, montoPremio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return montoPremio;
	}

	public static String getReglasPagoJS() {
		StringBuffer cadena = new StringBuffer();
		EjecutorSql oEjecutorSql = new EjecutorSql();
		String sep = "";
		String coma = ",";
		try {
			CachedRowSet reglas = oEjecutorSql.ejecutaQuery("select * from reglas_pago order by id_reglas_pago", new ArrayList());

			cadena.append("[");
			while (reglas.next()) {
				cadena.append(sep);
				cadena.append("{'rangoIni':").append(reglas.getString("rango_ini"));
				cadena.append(",'rangoFin':").append(reglas.getString("rango_fin"));
				cadena.append(",'multiplo':").append(reglas.getString("multiplo"));
				cadena.append(",'montoMaximo':").append(reglas.getString("monto_maximo"));
				cadena.append("}");
				sep = coma;
			}
			cadena.append("]");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cadena.toString();
	}

	public static int[] getLogro5toInning(String l1, String l2) {
		int n = 0;
		int n1 = Integer.parseInt(l1);
		int n2 = Integer.parseInt(l2);

		n = (n1 < 0 ? n1 : n2);

		if (n == -115)
			return new int[] { 105, -125 };
		else if (n == -120)
			return new int[] { 100, -120 };
		else if (n == -125)
			return new int[] { -105, -115 };
		else if (n == -130)
			return new int[] { -105, -115 };
		else if (n == -135)
			return new int[] { -110, -110 };
		else if (n == -140)
			return new int[] { -110, -110 };
		else if (n == -145)
			return new int[] { -115, -105 };
		else if (n == -150)
			return new int[] { -115, -105 };
		else if (n == -155)
			return new int[] { -120, 100 };
		else if (n == -160)
			return new int[] { -120, 100 };
		else if (n == -165)
			return new int[] { -130, 110 };
		else if (n == -170)
			return new int[] { -135, 105 };
		else if (n == -175)
			return new int[] { -135, 105 };
		else if (n == -180)
			return new int[] { -140, 110 };
		else if (n == -185)
			return new int[] { -140, 110 };
		else if (n == -190)
			return new int[] { -145, 115 };
		else if (n == -195)
			return new int[] { -145, 115 };
		else if (n == -200)
			return new int[] { -150, 120 };
		else if (n >= -205)
			return new int[] { -150, 120 };

		return new int[] { n1, n2 };

	}

	public static PuntosAltaBajaBean getPuntosAltaBajaPorDefecto(String valor) {
		if (puntosPorDefecto == null) {
			puntosPorDefecto = new TreeMap();
			puntosPorDefecto = new TreeMap();
			puntosPorDefecto.put("5.5", new PuntosAltaBajaBean("5.5", "3", "", "165", "-215"));
			puntosPorDefecto.put("6", new PuntosAltaBajaBean("6", "3", "", "150", "-200"));
			puntosPorDefecto.put("6.5", new PuntosAltaBajaBean("6.5", "3", "5", "150", "-190"));
			puntosPorDefecto.put("7", new PuntosAltaBajaBean("7", "3", "5", "145", "-180"));
			puntosPorDefecto.put("7.5", new PuntosAltaBajaBean("7.5", "4", "", "145", "-175"));
			puntosPorDefecto.put("8", new PuntosAltaBajaBean("8", "4", "", "140", "-170"));
			puntosPorDefecto.put("8.5", new PuntosAltaBajaBean("8.5", "4", "5", "135", "-165"));
			puntosPorDefecto.put("9", new PuntosAltaBajaBean("9", "5", "", "100", "-130"));
			puntosPorDefecto.put("9.5", new PuntosAltaBajaBean("9.5", "5", "", "-115", "-115"));
			puntosPorDefecto.put("10", new PuntosAltaBajaBean("10", "5", "5", "-125", "-105"));
			puntosPorDefecto.put("10.5", new PuntosAltaBajaBean("10.5", "5", "5", "-145", "115"));
			puntosPorDefecto.put("11", new PuntosAltaBajaBean("11", "6", "", "-155", "125"));
			puntosPorDefecto.put("11.5", new PuntosAltaBajaBean("11.5", "6", "", "-160", "130"));
			puntosPorDefecto.put("12", new PuntosAltaBajaBean("12", "6", "5", "-165", "135"));
			puntosPorDefecto.put("12.5", new PuntosAltaBajaBean("12.5", "6", "5", "-170", "140"));
			puntosPorDefecto.put("13", new PuntosAltaBajaBean("13", "7", "", "-180", "150"));
			puntosPorDefecto.put("13.5", new PuntosAltaBajaBean("13.5", "7", "5", "-180", "150"));
			puntosPorDefecto.put("14", new PuntosAltaBajaBean("14", "7", "5", "-200", "160"));
		}

		if (puntosPorDefecto.containsKey(valor)) {
			return (PuntosAltaBajaBean) puntosPorDefecto.get(valor);
		} else {
			return new PuntosAltaBajaBean("", "", "", "", "");
		}
	}

	public static String findLastPlay(String idUsuario) {
		if (ULTIMA_JUGADA.containsKey(idUsuario)) {
			return "La ultima jugada fue el dia " + ULTIMA_JUGADA.get(idUsuario);
		} else {
			return "No tiene registrada ninguna jugada";
		}
	}

	public static String getEntero(double valor) {
		String v = String.valueOf(valor);
		return v.substring(0, v.indexOf("."));
	}

	public static String getDecimal(double valor) {
		String v = String.valueOf(valor);
		return v.substring(v.indexOf(".") + 1);
	}

	public static String getStatusGame(String uo, ListaJuegoTO lista) {
		StringBuffer cad = new StringBuffer();
		if (uo.equals(Constants.ALTA)) {
			cad.append(lista.getHora());
			if (lista.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL_5)) {
				cad.append(" <b class='blue'>5 INING</b>");
			} else if (lista.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL_1)) {
				cad.append(" <b class='yellow'>1 INING</b>");
			} else if (lista.getIdDeporte().equals(Constants.ID_EQUIPO_BASKETBALL_MITAD)) {
				cad.append(" <b class='red'>MITAD</b>");
			} else if (lista.getIdDeporte().equals(Constants.ID_EQUIPO_SOCCER_MITAD)) {
				cad.append(" <b class='red'>MITAD</b>");
			} else if (lista.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL_0)) {
				cad.append(" <b class='morado'>ANOTA</b>");
			}
		} else if (uo.equals(Constants.BAJA)) {
			cad.append(lista.getStatusReal());
		}

		return cad.toString();

	}

	public static String getIdDeporte(String idDeporte) {
		if (idDeporte.equals(Constants.ID_EQUIPO_BEISBOL_5) || idDeporte.equals(Constants.ID_EQUIPO_BEISBOL_1) || idDeporte.equals(Constants.ID_EQUIPO_BEISBOL_0)) {
			return Constants.ID_EQUIPO_BEISBOL;
		} else if (idDeporte.equals(Constants.ID_EQUIPO_BASKETBALL_MITAD)) {
			return Constants.ID_EQUIPO_BASKETBALL;
		} else if (idDeporte.equals(Constants.ID_EQUIPO_SOCCER_MITAD)) {
			return Constants.ID_EQUIPO_SOCCER;
		}

		return idDeporte;
	}

	public static double logroBaskquetMitad(double valor, String tipo) {
		double mitad = (valor > 0 ? Math.ceil(valor) / 2 : Math.floor(valor) / 2); // calculamos
		// la
		// mitad
		double valorAbs = Math.abs(valor);

		if (tipo.equals("AB")) {
			if (valor <= 130) {
				mitad = mitad + 0.0;
			} else if (valor <= 160) {
				mitad = mitad + 0.5;
			} else if (valor <= 190) {
				mitad = mitad + 1.0;
			} else if (valor <= 210) {
				mitad = mitad + 1.5;
			} else {
				mitad = mitad + 2.0;
			}
		} else if (tipo.equals("RL")) {
			if (valorAbs <= 12) {
				mitad = mitad + (valor >= 0 ? 0.5 : -0.5);
			} else if (valorAbs <= 18) {
				mitad = mitad + (valor >= 0 ? 1.0 : -1.0);
			} else if (valorAbs <= 24) {
				mitad = mitad + (valor >= 0 ? 1.5 : -1.5);
			} else {
				mitad = mitad + (valor >= 0 ? 2.0 : -2.0);
			}
		}

		return mitad;
	}

	public static DominioBean getDominio(HttpServletRequest request) {

		DominioBean dom = new DominioBean("ss", request.getServerName(), new Date().getTime());

		log.info("Cargando Datos del Sito :" + request.getServerName());

		if (dom == null) {
			log.debug("No se encontro del dominio definido en el bean se cargara el Default");
			// dom = dominio.get("betcesc.com");
		} else {
			log.debug("Dominio:" + dom.toString());
		}

		return dom;
	}

	public static ActionForward getForwardStart(HttpServletRequest request) {
		DominioBean dom = getDominio(request);
		if (dom.getIdDominio().equals("001") || dom.getIdDominio().equals("002") || dom.getIdDominio().equals("003") || dom.getIdDominio().equals("004") || dom.getIdDominio().equals("005") || dom.equals("006") || dom.equals("007") || dom.getIdDominio().equals("008")) {
			return new ActionForward("/endSession.do?dominio=" + dom);
		} else {
			return new ActionForward("/endSession.do?dominio=000");
		}
	}

	public static String getFechaFormatoSQL(String fecha, boolean inicio) throws ParseException {
		if (fecha != null && fecha.trim().length() > 0)
			return sdf2.format(sdf.parse(fecha)).concat((inicio ? " 00:00:00" : " 23:59:59"));
		else
			return null;
	}

	public static String f0(String valor) {
		return valor.replaceAll("\\.5", "&frac12;").replaceAll("\\.0", "");
	}

	public static String f1(String valor) {
		return (valor.charAt(0) != '-' ? "+".concat(valor) : valor).replaceAll("\\.5", "&frac12;").replaceAll("\\.0", "");
	}

	public static String getProperty(Properties prop, String name) {
		return (prop != null ? isNull(prop.getProperty(name), "") : "");
	}

	public static boolean isPrinterPlay(String idJuego) {
		if (idJuego.equals(Constants.ID_EQUIPO_BEISBOL_5) || idJuego.equals(Constants.ID_EQUIPO_BEISBOL_1) || idJuego.equals(Constants.ID_EQUIPO_BEISBOL_0) || idJuego.equals(Constants.ID_EQUIPO_BASKETBALL_MITAD)) {
			return false;
		}

		return true;
	}

	public static String arrayToString(ArrayList<String> arr) {
		StringBuffer sb = new StringBuffer();
		String sep = "";

		for (int i = 0; i < arr.size(); i++) {
			sb.append(sep).append(arr.get(i));
			sep = Constants.COMA;
		}

		return sb.toString();
	}

	public static String getDomainName(String id) {

		return DominioBean.nombreDominios.get(id);

	}

}
