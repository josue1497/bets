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
import com.betcesc.game.facade.LigaFacade;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.LigaTO;

public class LigaAction extends Action {

	private static final Log log = LogFactory.getLog(LigaAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		try {
			UsuarioIF usuario = Constants.getUserSession(request);
			
			LigaFacade oLigaFacade = new LigaFacade(request);
			LigaTO oLigaTO = new LigaTO();
			
			
			oLigaTO.setIdDeporte(request.getParameter("idDeporte"));
			
			CachedRowSet lista = oLigaFacade.consultarListaActivaLigaFacade(oLigaTO);
			
			request.setAttribute("lista", lista);
			request.setAttribute("idDeporte", request.getParameter("idDeporte"));
			request.setAttribute("descDeporte", request.getParameter("descDeporte"));

			return mapping.findForward("success");
		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
	}
	
}
