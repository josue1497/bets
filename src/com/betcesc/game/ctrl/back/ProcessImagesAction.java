package com.betcesc.game.ctrl.back;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.betcesc.game.common.Constants;
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.facade.VistaFacade;
import com.betcesc.game.interfaces.UsuarioIF;

public class ProcessImagesAction extends Action {

	private static final Log log = LogFactory.getLog(ProcessImagesAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		VistaFacade oVistaFacade = new VistaFacade(request);
		ArrayList lista = null;
		try {
			UsuarioIF usuario = Constants.getUserSession(request);

			// agregamos el archivo
			oVistaFacade.actualizarVistaFacade();
			
			// para la proxima vista
			lista = oVistaFacade.listaVistaArrayFacade();
			request.setAttribute("lista", lista);
			
		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}

		return mapping.findForward("success");
	}

}
 