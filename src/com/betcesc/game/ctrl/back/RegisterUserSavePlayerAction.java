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

import com.betcesc.game.bean.DominioBean;
import com.betcesc.game.common.Constants;
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.facade.UsuarioFacade;
import com.betcesc.game.interfaces.UsuarioIF;

public class RegisterUserSavePlayerAction extends Action {

	private static final Log log = LogFactory.getLog(RegisterUserSavePlayerAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		ActionErrors errores = new ActionErrors();
		 
		try {
			UsuarioFacade oUsuarioFacade = new UsuarioFacade(request); 

			UsuarioIF oUsuarioTO = (UsuarioIF) form;
			
			
			DominioBean dom = Constants.getDominio(request);
			// asignamos el administrador de taquilla para el nuevo usuario
			if(dom.getIdAdminTaquilla().indexOf(",")!=-1) {
				String[] ids = dom.getIdAdminTaquilla().split(",");
				oUsuarioTO.setIdSupervisor(ids[0]);
			} else {
				oUsuarioTO.setIdSupervisor(dom.getIdAdminTaquilla());
			}
			// asignamos el dominio del usuario nuevo
			oUsuarioTO.setDominio(dom.getIdDominio());
			
			oUsuarioFacade.insertarUsuarioFacade(oUsuarioTO);
			
			// agregamos el usuario en el administrador del dominio asignado
			oUsuarioFacade.actualizarUsuarioAdminFacade(oUsuarioTO.getIdUsuario(),dom.getIdAdmin());
			
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
