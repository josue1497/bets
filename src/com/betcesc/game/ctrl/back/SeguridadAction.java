package com.betcesc.game.ctrl.back;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.common.Constants;
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.facade.UsuarioFacade;
import com.betcesc.game.interfaces.UsuarioIF;

public class SeguridadAction extends Action {

	private static final Log log = LogFactory.getLog(SeguridadAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		try {
			UsuarioIF usuario = Constants.getUserSession(request);
			
			UsuarioFacade oUsuarioFacade = new UsuarioFacade(request);
			
			if(!usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				throw new SessionInvalidException("Usuario no autorizado");
			}
			if(request.getParameter("idUsuario")==null) {
				throw new SessionInvalidException("Usuario no puede ser null");
			}
			
			CachedRowSet menues = oUsuarioFacade.listaMenuUsuarioFacade(request.getParameter("idUsuario"));
			
			CachedRowSet listaUsuarios = oUsuarioFacade.listaUsuarioFacade(usuario,0,usuario.getIdUsuario());
			
			
			request.setAttribute("menues", menues);
			request.setAttribute("listaUsuarios", listaUsuarios);

			return mapping.findForward("success");
		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
	}
	
}
