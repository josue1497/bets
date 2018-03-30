package com.betcesc.game.ctrl.back;

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

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.common.Constants;
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.facade.JuegoFacade;
import com.betcesc.game.interfaces.UsuarioIF;
import com.jade.util.Encriptor;

public class PrintTicketAction extends Action {

	private static final Log log = LogFactory.getLog(ListGamePlayAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		JuegoFacade juegoFacade;
		ActionErrors errores = new ActionErrors();

		try {

			UsuarioIF usuario = Constants.getUserSession(request);

			juegoFacade = new JuegoFacade(request);
			
			CachedRowSet crs = juegoFacade.ultimaJugadaFacade(usuario,null);
			
			if(crs.next()){
				request.setAttribute("SID",Encriptor.getSID(crs.getString("id_jugada")));
			}

			crs.beforeFirst();
			request.setAttribute("ticket",crs);
			
		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		} catch (Exception e) {
			errores.add("error",new ActionError("error.sistema.general"));
		}
		saveErrors(request, errores);
		return mapping.findForward("success");
	}

}
