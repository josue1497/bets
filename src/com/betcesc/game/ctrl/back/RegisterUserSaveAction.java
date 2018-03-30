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

public class RegisterUserSaveAction extends Action {

	private static final Log log = LogFactory.getLog(RegisterUserAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		ActionErrors errores = new ActionErrors();
		
		try {
			UsuarioIF usuario = Constants.getUserSession(request);
		
			UsuarioFacade oUsuarioFacade = new UsuarioFacade(request); 

			UsuarioIF oUsuarioTO = (UsuarioIF) form;
			
			if(oUsuarioTO.getIdUsuario()==null || oUsuarioTO.getIdUsuario().equals("")) {
				oUsuarioFacade.insertarUsuarioFacade(oUsuarioTO);
				
				DominioBean dom = Constants.getDominio(request);
				if(!dom.getIdDominio().equals("000")) {
					// agregamos el usuario en el administrador del dominio asignado
					oUsuarioFacade.actualizarUsuarioAdminFacade(oUsuarioTO.getIdUsuario(),dom.getIdAdmin());
				} else if((!usuario.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR) && usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) && usuario.getTipo().equals("0"))) {
					// agregamos el usuario en el administrador del dominio asignado
					oUsuarioFacade.actualizarUsuarioAdminFacade(oUsuarioTO.getIdUsuario(),usuario.getIdUsuario());
				}
			} else {
				if(usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) && request.getParameter("eliminar")!=null && request.getParameter("eliminar").equals("true")) {
					oUsuarioFacade.eliminarUsuarioFacade(oUsuarioTO);
				} else if(usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) && request.getParameter("cerrarSession")!=null && request.getParameter("cerrarSession").equals("true")) {
					Constants.deleteUserInSession(oUsuarioTO);
				}
				else {
					oUsuarioFacade.actualizarUsuarioFacade(oUsuarioTO);
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
