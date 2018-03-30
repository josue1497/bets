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
import com.betcesc.game.facade.DeporteFacade;
import com.betcesc.game.interfaces.UsuarioIF;

public class DeportesAction extends Action {

	private static final Log log = LogFactory.getLog(DeportesAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		
		try {
			UsuarioIF usuario = Constants.getUserSession(request);
			
			if(!usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				return mapping.findForward("end");
			}
			
			return mapping.findForward("success");

		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
	}
	
}
