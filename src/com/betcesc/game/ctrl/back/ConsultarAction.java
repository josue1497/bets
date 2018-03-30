package com.betcesc.game.ctrl.back;

/*
 * Created on 27-abr-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.common.Constants;
import com.betcesc.game.interfaces.UsuarioIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * @author jrivero
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ConsultarAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {


		String tabla = null;
		StringBuffer consulta = new StringBuffer();
		
		UsuarioIF usuario = Constants.getUserSession(request);
		

		//if (request.getMethod().equals("POST")) {
			if (request.getParameter("table") == null || request.getParameter("table").trim().equals("")) {
				return null;
			}
			tabla = request.getParameter("table");
		//}
		if (tabla == null || tabla.trim().equals("")) {
			return null;
		}
		try {
			EjecutorSql oEjecutorSql = new EjecutorSql();
			CachedRowSet cache;
			ArrayList parametros = new ArrayList();
			StringBuffer sb = new StringBuffer();

			// seteamos el primer parametro id
			parametros.add(request.getParameter("id"));
			
			
			if (tabla.equals("0")) {
				// consultamos el deporte
				sb.setLength(0);
	            sb.append("SELECT id_deporte, desc_deporte, empate, " );
	            sb.append("id_status_deporte, if(referencia_inicio is null,0,referencia_inicio) As referencia_inicio, ");
	            sb.append("if(runline_inicio is null,0,runline_inicio) As runline_inicio, " );
	            sb.append("if(runline_logro_inicio0 is null,0,runline_logro_inicio0) As runline_logro_inicio0, " );
	            sb.append("if(runline_logro_inicio1 is null,0,runline_logro_inicio1) As runline_logro_inicio1, " );
	            sb.append("if(altabaja_logro_inicio0 is null,0,altabaja_logro_inicio0) As altabaja_logro_inicio0, " );
	            sb.append("if(altabaja_logro_inicio1 is null,0,altabaja_logro_inicio1) As altabaja_logro_inicio1, " );
	            sb.append("if(combinacion is null,'',combinacion) As combinacion, " );
	            sb.append("if(items is null,'0',items) As items " );
	            sb.append("FROM deporte ");
	            sb.append("WHERE id_deporte = ? ");

				cache = oEjecutorSql.ejecutaQuery(sb.toString(), parametros);
				if (cache.next()) {
					String arr = "{idDeporte:'01?',descDeporte:'02?',empate:'03?',idStatusDeporte:'04?',referenciaInicio:'05?'";
					arr += ",runlineInicio:'06?',combinacion:'07?',items:'08?'";
					arr += ",runlineLogroInicio0:'09?',runlineLogroInicio1:'10?'";
					arr += ",altabajaLogroInicio0:'11?',altabajaLogroInicio1:'12?'";
					arr += "}";
					arr = arr.replaceAll("01\\?",cache.getString("id_deporte"));
					arr = arr.replaceAll("02\\?",cache.getString("desc_deporte"));
					arr = arr.replaceAll("03\\?",cache.getString("empate"));
					arr = arr.replaceAll("04\\?",cache.getString("id_status_deporte"));
					arr = arr.replaceAll("05\\?",cache.getString("referencia_inicio"));
					arr = arr.replaceAll("06\\?",cache.getString("runline_inicio"));
					arr = arr.replaceAll("07\\?",cache.getString("combinacion"));
					arr = arr.replaceAll("08\\?",cache.getString("items"));
					arr = arr.replaceAll("09\\?",cache.getString("runline_logro_inicio0"));
					arr = arr.replaceAll("10\\?",cache.getString("runline_logro_inicio1"));
					arr = arr.replaceAll("11\\?",cache.getString("altabaja_logro_inicio0"));
					arr = arr.replaceAll("12\\?",cache.getString("altabaja_logro_inicio1"));
					consulta.append(arr);
				}
			} else if (tabla.equals("1")) {
				// consultamos la liga
				sb.setLength(0);
				sb.append("SELECT id_liga, desc_liga, iniciales, id_deporte ");
				sb.append("FROM liga ");
				sb.append("WHERE id_liga = ? ");

				cache = oEjecutorSql.ejecutaQuery(sb.toString(), parametros);
				if (cache.next()) {
					String arr = "{idLiga:'1?',descLiga:'2?',iniciales:'3?',idDeporte:'4?'}";
					arr = arr.replaceAll("1\\?",cache.getString("id_liga"));
					arr = arr.replaceAll("2\\?",cache.getString("desc_liga"));
					arr = arr.replaceAll("3\\?",cache.getString("iniciales"));
					arr = arr.replaceAll("4\\?",cache.getString("id_deporte"));
					consulta.append(arr);
				}
			} else if (tabla.equals("2")) {
				// consultamos el equipo
				sb.setLength(0);
				sb.append("SELECT id_equipo, desc_equipo, abreviatura, desc_corta ");
				sb.append("FROM equipo ");
				sb.append("WHERE id_equipo = ? ");
				
				cache = oEjecutorSql.ejecutaQuery(sb.toString(), parametros);
				if (cache.next()) {
					String arr = "{idEquipo:'1?',descEquipo:'2?',abreviatura:'3?',descCorta:'4?'}";
					arr = arr.replaceAll("1\\?",cache.getString("id_equipo"));
					arr = arr.replaceAll("2\\?",cache.getString("desc_equipo"));
					arr = arr.replaceAll("3\\?",cache.getString("abreviatura"));
					arr = arr.replaceAll("4\\?",cache.getString("desc_corta"));
					consulta.append(arr);
				}
			} else if (tabla.equals("3")) {
				// consultamos el lanzador
				sb.setLength(0);
				sb.append("SELECT id_lanzador, nombre_lanzador, id_equipo ");
				sb.append("FROM lanzador ");
				sb.append("WHERE id_lanzador = ? ");
				
				cache = oEjecutorSql.ejecutaQuery(sb.toString(), parametros);
				if (cache.next()) {
					String arr = "{idLanzador:'1?',nombreLanzador:'2?',idEquipo:'3?'}";
					arr = arr.replaceAll("1\\?",cache.getString("id_lanzador"));
					arr = arr.replaceAll("2\\?",cache.getString("nombre_lanzador"));
					arr = arr.replaceAll("3\\?",cache.getString("id_equipo"));
					consulta.append(arr);
				}
			} else {
				response.getWriter().print("");
			}
		} catch (Exception e) {
			response.getWriter().print("");
		}

		request.setAttribute("consulta", consulta.toString());
		return mapping.findForward("success");
	}

}
