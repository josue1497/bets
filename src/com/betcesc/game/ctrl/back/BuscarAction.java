/*
 * Created on 27-abr-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.betcesc.game.ctrl.back;

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
public class BuscarAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String cadena = null;
		String table = null;

		UsuarioIF usuario = Constants.getUserSession(request);
		
		if (request.getMethod().equals("POST")) {
			if (request.getParameter("table") == null || request.getParameter("table").trim().equals("")) {
				return null;
			}
			if (request.getParameter("nombre") == null || request.getParameter("nombre").trim().equals("")) {
				return null;
			}
			table = request.getParameter("table");
			cadena = request.getParameter("nombre");
		}
		if (table == null) {
			return null;
		}
		if (cadena == null || cadena.trim().length() < 2) {
			return null;
		}
		try {
			EjecutorSql oEjecutorSql = new EjecutorSql();
			CachedRowSet cache;
			StringBuffer sb = new StringBuffer();
			if (table.equals("0")) {
				sb.append("SELECT concat(id_deporte,':',desc_deporte) item ");
				sb.append("FROM deporte ");
				sb.append("WHERE desc_deporte like '%" + cadena + "%'  ");
				sb.append("ORDER BY upper(desc_deporte) ");
			} else if (table.equals("1")) {
				sb.append("SELECT concat(id_liga,':',desc_liga) item ");
				sb.append("FROM liga ");
				sb.append("WHERE desc_liga like '%" + cadena + "%'  ");
				sb.append("ORDER BY upper(desc_liga) ");
			} else if (table.equals("2")) {
				sb.append("SELECT concat(id_equipo,':',desc_equipo) item ");
				sb.append("FROM equipo ");
				sb.append("WHERE desc_equipo like '%" + cadena + "%'  ");
				sb.append("ORDER BY upper(desc_equipo) ");
			} else if (table.equals("3")) {
				sb.append("SELECT concat(id_lanzador,':',nombre_lanzador) item ");
				sb.append("FROM lanzador ");
				sb.append("WHERE nombre_lanzador like '%" + cadena + "%'  ");
				sb.append("ORDER BY upper(nombre_lanzador) ");
			} else if (table.equals("4")) {
				sb.append("SELECT concat(id_campeonato,':',desc_campeonato) item ");
				sb.append("FROM campeonato ");
				sb.append("WHERE desc_campeonato like '%" + cadena + "%'  ");
				sb.append("ORDER BY upper(desc_campeonato) ");
			}

			cache = oEjecutorSql.ejecutaQuery(sb.toString(), null);

			String sep = "";

			while (cache.next()) {
				response.getWriter().print(sep);
				response.getWriter().print(cache.getString("item"));
				sep = "&&&";
			}
		} catch (Exception e) {
			response.getWriter().print("");
		}

		return null;
	}

}
