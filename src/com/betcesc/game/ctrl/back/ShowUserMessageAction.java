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
import com.betcesc.game.facade.UsuarioFacade;
import com.betcesc.game.interfaces.ApuestaIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.ApuestaTO;

public class ShowUserMessageAction extends Action {

	private static final Log log = LogFactory.getLog(ListGamePlayAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		JuegoFacade juegoFacade;
		ActionErrors errores = new ActionErrors();
		ApuestaIF oApuestaTO = new ApuestaTO();

		try {

			UsuarioIF usuario = Constants.getUserSession(request);

			UsuarioFacade usuarioFacade = new UsuarioFacade(request);
			
			int orden = 0;
			try {
				orden = Integer.parseInt(request.getParameter("orden"));
			} catch(Exception e) {
				
			}
			
			CachedRowSet crs = usuarioFacade.listaUsuarioFacade(usuario, orden, usuario.getIdUsuario());
			
			if(request.getParameter("enviar")!=null && request.getParameter("enviar").equals("true")) {
				String mensaje=request.getParameter("mensaje");
				String[] usuarios=request.getParameterValues("usuario");
				if(mensaje!=null && usuarios!=null) {
					usuarioFacade.actualizarMensajeUsuarioFacade(mensaje,usuarios,usuario.getIdUsuario());
				}
			}
			
			request.setAttribute("lista",crs);
			
		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		} catch (Exception e) {
			errores.add("error",new ActionError("error.sistema.general"));
		}
		saveErrors(request, errores);
		return mapping.findForward("success");
	}

}
