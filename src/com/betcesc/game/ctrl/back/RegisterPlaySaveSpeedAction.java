package com.betcesc.game.ctrl.back;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.betcesc.game.common.Constants;
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.interfaces.UsuarioIF;

public class RegisterPlaySaveSpeedAction {

	private static final Log log = LogFactory.getLog(RegisterGameAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		try {
			UsuarioIF oUsuarioIF = Constants.getUserSession(request);

			RegisterGameActionForm juego = new RegisterGameActionForm();

			return mapping.findForward("success");
		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
	}
}
