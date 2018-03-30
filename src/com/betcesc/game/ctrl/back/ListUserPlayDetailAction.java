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
import com.betcesc.game.to.CuentaJugadorTO;

public class ListUserPlayDetailAction extends Action {

	private static final Log log = LogFactory.getLog(ListUserAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		UsuarioFacade usuarioFacade;
		CachedRowSet lista;
		CuentaJugadorTO oCuentaJugadorTO = null;

		try {
			UsuarioIF usuario = Constants.getUserSession(request);
			String idJugador = usuario.getIdUsuario();
			if(usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				idJugador = request.getParameter("idJugador");
			}

			usuarioFacade = new UsuarioFacade(request);
			
			oCuentaJugadorTO = new CuentaJugadorTO();
			oCuentaJugadorTO.setIdJugador(idJugador);

			lista = usuarioFacade.listaCuentaJugadorFacade(oCuentaJugadorTO);

			request.setAttribute("lista", lista);

			return mapping.findForward("success");

		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
	}

}
