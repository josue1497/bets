/*
 * Created on 27-abr-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.betcesc.game.ctrl.back;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sun.jdbc.rowset.CachedRowSet;

import com.jade.util.lbda.EjecutorSql;

/**
 * @author jrivero
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ReferenciaAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idDeporte = null;

		if (request.getMethod().equals("POST")) {
			if (request.getParameter("idDeporte") == null || request.getParameter("idDeporte").trim().equals("")) {
				return null;
			}
			idDeporte = request.getParameter("idDeporte");
		}
		if (idDeporte == null) {
			return null;
		}
		try {
			EjecutorSql oEjecutorSql = new EjecutorSql();
			CachedRowSet cache;
			ArrayList parametros = new ArrayList();
			StringBuffer sb = new StringBuffer();

			String referencia = "0";
			String runline = "0";
			String runlineLogro0 = "0";
			String runlineLogro1 = "0";
			String altabajaLogro0 = "0";
			String altabajaLogro1 = "0";

			parametros.add(idDeporte);
			sb.append("select referencia from juego_equipo x, juego y where x.id_juego=y.id_juego and y.id_deporte=? order by x.id_juego_equipo desc limit 1");

			cache = oEjecutorSql.ejecutaQuery(sb.toString(), parametros);

			if (cache.next()) {
				referencia = cache.getString(1);
			}

			sb.setLength(0);
			sb.append("SELECT if(referencia_inicio is null,0,referencia_inicio) As referencia, ");
			sb.append("if(runline_inicio is null,0,runline_inicio) As runline, ");
			sb.append("if(runline_logro_inicio0 is null,0,runline_logro_inicio0) As runline_logro_inicio0, ");
			sb.append("if(runline_logro_inicio1 is null,0,runline_logro_inicio1) As runline_logro_inicio1, ");
			sb.append("if(altabaja_logro_inicio0 is null,0,altabaja_logro_inicio0) As altabaja_logro_inicio0, ");
			sb.append("if(altabaja_logro_inicio1 is null,0,altabaja_logro_inicio1) As altabaja_logro_inicio1 ");
			sb.append("FROM deporte ");
			sb.append("WHERE id_deporte = ? ");
			cache = oEjecutorSql.ejecutaQuery(sb.toString(), parametros);

			if (cache.next()) {
				if(referencia.equals("0")) {
					referencia = cache.getString(1);
				}
				runline = cache.getString(2);
				runlineLogro0 = cache.getString(3);
				runlineLogro1 = cache.getString(4);
				altabajaLogro0 = cache.getString(5);
				altabajaLogro1 = cache.getString(6);
			}

			response.getWriter().print("{");
			response.getWriter().print("referencia:'");
			response.getWriter().print(referencia);
			response.getWriter().print("',runline:'");
			response.getWriter().print(runline);
			response.getWriter().print("',runlineLogro0:'");
			response.getWriter().print(runlineLogro0);
			response.getWriter().print("',runlineLogro1:'");
			response.getWriter().print(runlineLogro1);
			response.getWriter().print("',altabajaLogro0:'");
			response.getWriter().print(altabajaLogro0);
			response.getWriter().print("',altabajaLogro1:'");
			response.getWriter().print(altabajaLogro1);
			response.getWriter().print("'}");

		} catch (Exception e) {
			response.getWriter().print("");
		}

		return null;
	}

}
