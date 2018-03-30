package com.betcesc.game.ctrl.back;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.betcesc.game.common.Constants;
import com.betcesc.game.dao.MensajeUserDAO;
import com.betcesc.game.facade.UsuarioFacade;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.MensajeUserTO;

/**
 * @author jrivero
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class MensajeAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsuarioIF usuario = Constants.getUserSession(request);
		String idMensaje = null;

		if (request.getMethod().equals("POST")) {
			if (request.getParameter("idMensaje") == null || request.getParameter("idMensaje").trim().equals("")) {
				return null;
			}
			idMensaje = request.getParameter("idMensaje");
		}
		if (idMensaje == null || idMensaje.equals("undefined")) {
			return null;
		}
		try {
			MensajeUserTO mensajeUserTO = new MensajeUserTO();
			mensajeUserTO.setIdMensaje(idMensaje);
			
			MensajeUserDAO mensajeUserDAO = new MensajeUserDAO();
			mensajeUserDAO.actualizarMensajeUserDAO(mensajeUserTO);
			
			// eliminamos los mensajes de la session
			ArrayList listaMensaje = (ArrayList) request.getSession().getAttribute("MENSAJES_POR_USUARIO");
			MensajeUserTO msg = null;
			for(int i=0;i<listaMensaje.size();i++) {
				msg = (MensajeUserTO) listaMensaje.get(i);
				if(msg.getIdMensaje().equals(idMensaje)) {
					listaMensaje.remove(i);
					break;
				}
			}
        	UsuarioFacade oUsuarioFacade = new UsuarioFacade(request);
        	Constants.MENSAJES_POR_USUARIO = oUsuarioFacade.listaMensajeUserFacade();

		} catch (Exception e) {
			response.getWriter().print("");
		}

		return null;
	}

}