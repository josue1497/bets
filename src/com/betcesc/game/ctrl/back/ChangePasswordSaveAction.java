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

import com.betcesc.game.common.Constants;
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.facade.ParametrosFacade;
import com.betcesc.game.facade.UsuarioFacade;
import com.betcesc.game.interfaces.ParametrosIF;
import com.betcesc.game.interfaces.UsuarioIF;

public class ChangePasswordSaveAction extends Action {

	private static final Log log = LogFactory.getLog(ChangePasswordSaveAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		ActionErrors errores = new ActionErrors();
		
		try {
			UsuarioIF usuario = Constants.getUserSession(request);

			UsuarioFacade oUsuarioFacade = new UsuarioFacade(request);
			
			String clave = (String)request.getParameter("clave");
			String clave1 = (String)request.getParameter("clave1");
			String clave2 = (String)request.getParameter("clave2");
			
			if(clave!=null && clave1!=null && clave2!=null) {
				if(clave.length()>4 && clave1.length()>4 && clave1.equals(clave2)) {
					oUsuarioFacade.actualizarUsuarioSoloClaveFacade(usuario, clave, clave1);
				}
			}
			
		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		} catch(Exception e) {
			errores.add("error",new ActionError("error.sistema.general"));
			saveErrors(request, errores);
			return mapping.findForward("error");
		}

		return mapping.findForward("success");
	}

}
