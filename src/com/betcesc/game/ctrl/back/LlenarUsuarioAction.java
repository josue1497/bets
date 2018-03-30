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
public class LlenarUsuarioAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Constants.getUserSession(request);

		String idRol = null;

		if (request.getMethod().equals("POST")) {
			if (request.getParameter("idRol") == null || request.getParameter("idRol").trim().equals("")) {
				return null;
			}
			idRol = request.getParameter("idRol");
		}
		if (idRol == null) {
			return null;
		}
		try {
			EjecutorSql oEjecutorSql = new EjecutorSql();
			CachedRowSet cache;
			StringBuffer sb = new StringBuffer();

			sb.append("SELECT id_usuario As value, usuario As text ");
			sb.append("FROM usuario ");
			sb.append("WHERE id_rol>?  ");
			sb.append("ORDER BY upper(usuario) ");

			cache = oEjecutorSql.ejecutaQuery(sb.toString(), null);
			String sep = "";

			response.getWriter().print("[");
			while (cache.next()) {
				response.getWriter().print(sep);
				response.getWriter().print("{value:'");
				response.getWriter().print(cache.getString("value").replaceAll("'", ""));
				response.getWriter().print("',text:'");
				response.getWriter().print(cache.getString("text").replaceAll("'", ""));
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
