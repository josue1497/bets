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
import com.jade.util.lbda.EjecutorSql;

/**
 * @author jrivero
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class LlenarAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Constants.getUserSession(request);
		
		String table = null;

		if (request.getMethod().equals("POST")) {
			if (request.getParameter("table") == null || request.getParameter("table").trim().equals("")) {
				return null;
			}
			table = request.getParameter("table");
		}
		if (table == null) {
			return null;
		}
		try {
			EjecutorSql oEjecutorSql = new EjecutorSql();
			CachedRowSet cache;
			StringBuffer sb = new StringBuffer();

			if (table.equals("0")) {
				sb.append("SELECT id_deporte As value, desc_deporte As text ");
				sb.append("FROM deporte ");
				sb.append("WHERE id_status_deporte=1  ");
				sb.append("ORDER BY upper(desc_deporte) ");
			} else if (table.equals("1")) {
				String idDeporte = request.getParameter("id");
				
				if(idDeporte.equals(Constants.ID_EQUIPO_BEISBOL_5) || idDeporte.equals(Constants.ID_EQUIPO_BEISBOL_1) || idDeporte.equals(Constants.ID_EQUIPO_BEISBOL_0)) {
					idDeporte = Constants.ID_EQUIPO_BEISBOL;
				} else if(idDeporte.equals(Constants.ID_EQUIPO_BASKETBALL_MITAD)) {
					idDeporte = Constants.ID_EQUIPO_BASKETBALL;
				} else if(idDeporte.equals(Constants.ID_EQUIPO_SOCCER_MITAD)) {
					idDeporte = Constants.ID_EQUIPO_SOCCER;
				}
				
				sb.append("SELECT id_liga As value, desc_liga As text ");
				sb.append("FROM liga ");
				sb.append("WHERE id_deporte='").append(idDeporte).append("' ");
				sb.append("ORDER BY upper(desc_liga) ");
			} else if (table.equals("2")) {
				String idLiga = request.getParameter("id");
				
				sb.append("SELECT a.id_equipo As value, a.desc_equipo As text ");
				sb.append("FROM equipo a, equipo_liga b ");
				sb.append("WHERE a.id_equipo=b.id_equipo ");
				sb.append("AND b.id_liga='").append(idLiga).append("' ");
				sb.append("ORDER BY upper(a.desc_equipo) ");
			} else if (table.equals("3")) {
				String idEquipo = request.getParameter("id");
				
				sb.append("SELECT id_lanzador As value, nombre_lanzador As text ");
				sb.append("FROM lanzador ");
				sb.append("WHERE id_equipo='").append(idEquipo).append("' ");
				sb.append("ORDER BY upper(nombre_lanzador) ");
			}

			cache = oEjecutorSql.ejecutaQuery(sb.toString(), null);
			String sep = "";

			response.getWriter().print("[");
			while (cache.next()) {
				response.getWriter().print(sep);
				response.getWriter().print("{value:'");
				response.getWriter().print(cache.getString("value").replaceAll("'",""));
				response.getWriter().print("',text:'");
				response.getWriter().print(cache.getString("text").replaceAll("'",""));
				response.getWriter().print("'}");
				sep = ",";
			}
			response.getWriter().print("]");
		} catch (Exception e) {
			response.getWriter().print("");
		}

		return null;
	}

}
