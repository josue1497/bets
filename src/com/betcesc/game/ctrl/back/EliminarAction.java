package com.betcesc.game.ctrl.back;

/*
 * Created on 27-abr-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.betcesc.game.common.Validator;
import com.betcesc.game.facade.DeporteFacade;
import com.betcesc.game.facade.EquipoFacade;
import com.betcesc.game.facade.LanzadorFacade;
import com.betcesc.game.facade.LigaFacade;
import com.betcesc.game.to.DeporteTO;
import com.betcesc.game.to.EquipoLigaTO;
import com.betcesc.game.to.EquipoTO;
import com.betcesc.game.to.LanzadorTO;
import com.betcesc.game.to.LigaTO;

/**
 * @author jrivero
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class EliminarAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String cadena = null;
		String tabla = null;

		if (request.getMethod().equals("POST")) {
			if (request.getParameter("table") == null || request.getParameter("table").trim().equals("")) {
				return null;
			}
			tabla = request.getParameter("table");
		}
		if (tabla == null || tabla.trim().equals("")) {
			return null;
		}
		try {
			int act = 0;
			if (tabla.equals("1")) {
				// eliminamos la asociacion de deporte con liga
				LigaFacade ligaFacade = new LigaFacade(request);
				LigaTO oLigaTO = new LigaTO();
				oLigaTO.setIdLiga(request.getParameter("id_liga"));
				
				act = ligaFacade.eliminarLigaFacade(oLigaTO);
			} else if (tabla.equals("2")) {
				// agregaremos el equipo
				EquipoFacade equipoFacade = new EquipoFacade(request);
				EquipoTO oEquipoTO = new EquipoTO();
				EquipoLigaTO oEquipoLigaTO = new EquipoLigaTO();

				oEquipoLigaTO.setIdEquipo(request.getParameter("id_equipo"));
				oEquipoLigaTO.setIdLiga(request.getParameter("id_liga"));
				
				act = equipoFacade.eliminarEquipoLigaFacade(oEquipoLigaTO);
				
			} else if (tabla.equals("3")) {
				// agregaremos el lanzador
				LanzadorFacade lanzadorFacade = new LanzadorFacade(request);
				LanzadorTO oLanzadorTO = new LanzadorTO();
				oLanzadorTO.setIdLanzador(request.getParameter("id_lanzador"));
				act = lanzadorFacade.eliminarLanzadorFacade(oLanzadorTO);
			}
			if(act==0) {
				response.getWriter().print("Error al eliminar el registro:\n\nPuede ser que el item este asociado\no este en uso en algún juego.");
			}
			
		} catch (Exception e) {
			response.getWriter().print("Error al eliminar el registro, verifique que este vacio");
		}

		return null;
	}

}
