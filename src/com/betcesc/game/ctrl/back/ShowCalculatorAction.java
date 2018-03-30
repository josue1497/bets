package com.betcesc.game.ctrl.back;

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
import com.betcesc.game.facade.UsuarioFacade;
import com.betcesc.game.interfaces.UsuarioIF;

public class ShowCalculatorAction extends Action {

	private static final Log log = LogFactory.getLog(ListUserAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			UsuarioIF usuario = Constants.getUserSession(request);

			String saldo = usuario.getMonto();
			double nSaldo = Double.parseDouble(saldo);

			if (usuario.getIdRol().equals(Constants.ROL_JUGADOR)) {
				UsuarioFacade usuarioFacade = new UsuarioFacade(request);
				
				saldo = usuarioFacade.consultarSaldoUsuarioFacade(usuario, false);
				nSaldo = Double.parseDouble(saldo);
			}
			
			request.setAttribute("saldo", Constants.formatNumber(nSaldo));
			request.setAttribute("saldoNumerico", String.valueOf(nSaldo));
			
			return mapping.findForward("success");

		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
	}
}
