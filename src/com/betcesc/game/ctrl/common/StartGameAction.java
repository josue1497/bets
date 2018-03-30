package com.betcesc.game.ctrl.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.betcesc.game.common.Constants;
import com.betcesc.game.dao.MenuDAO;
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.facade.ParametrosFacade;
import com.betcesc.game.interfaces.ParametrosIF;
import com.betcesc.game.to.ParametrosTO;

public class StartGameAction extends Action {
	private static final Log log = LogFactory.getLog(StartGameAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		try {
			Constants.getUserSession(request);

			ParametrosFacade parametrosFacade;
			ParametrosIF oParametros = new ParametrosTO();
			String avisoHome = "";
			
			parametrosFacade = new ParametrosFacade(request);
			
			
			
			
			if(!Constants.getDominio(request).getIdDominio().equals("000")) {
				oParametros.setNombre("AVISO_HOME_".concat(Constants.getDominio(request).getIdDominio()));
			} else {
				oParametros.setNombre("AVISO_HOME");
			}

			avisoHome = parametrosFacade.consultarParametrosFacade(oParametros).getValor();
			
			request.setAttribute("AVISO_HOME",avisoHome);

			
			
			return mapping.findForward("success");

		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
	}
}
