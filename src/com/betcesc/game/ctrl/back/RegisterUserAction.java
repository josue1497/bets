package com.betcesc.game.ctrl.back;

import java.util.ArrayList;

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
import com.betcesc.game.to.UsuarioTO;

public class RegisterUserAction extends Action {

	private static final Log log = LogFactory.getLog(RegisterUserAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		try {
			UsuarioIF usuario = Constants.getUserSession(request);

			if(!usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				return mapping.findForward("end");
			}
			
			UsuarioIF oUsuarioTO = new UsuarioTO();
			oUsuarioTO.setIdRol(usuario.getIdRol());
			oUsuarioTO.setIdUsuario(usuario.getIdUsuario());
			boolean modificar = false;
			
			request.setAttribute("modificar", new Boolean(false));
			UsuarioFacade oUsuarioFacade = new UsuarioFacade(request);
			
			if(!Constants.isNull(request.getParameter("idUsuario"))) {
				
				oUsuarioTO.setIdUsuario(request.getParameter("idUsuario"));
				oUsuarioTO = oUsuarioFacade.consultarUsuarioFacade(oUsuarioTO);
				request.setAttribute("modificar", new Boolean(true));
				modificar=true;
			}
			String rol = "1";
			if(!Constants.isNull(request.getParameter("rol_actual"))) {
				if(!modificar) {
					oUsuarioTO.setIdRol(request.getParameter("rol_actual").trim());
				}
			}
			rol = oUsuarioTO.getIdRol();

			request.setAttribute("user", oUsuarioTO);
			/* vamos a cargar algunas tablas de uso general */
			if(modificar) {
				request.setAttribute("listaRol",oUsuarioFacade.listaRolFacade(oUsuarioTO,usuario));
			} else {
				request.setAttribute("listaRol",oUsuarioFacade.listaRolModificarFacade(oUsuarioTO,usuario));
			}
			request.setAttribute("listaStatus",oUsuarioFacade.listaStatusFacade());
 			request.setAttribute("listaTipoCuenta",oUsuarioFacade.listaTipoCuentaFacade());
			if(usuario.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR)) {
				request.setAttribute("listaSupervisor",oUsuarioFacade.listaUsuarioSupervisorAdminFacade(oUsuarioTO));
			} else if(usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) && !usuario.getDominio().trim().equals("") && !usuario.getDominio().trim().equals("000")) {
				// los administradores de cada dominio
				request.setAttribute("listaSupervisor",oUsuarioFacade.listaUsuarioSupervisorDominioFacade(oUsuarioTO,usuario));
			} else {
				request.setAttribute("listaSupervisor",oUsuarioFacade.listaUsuarioSupervisorFacade(oUsuarioTO,usuario));
			}

			request.setAttribute("rol",rol);
			
			if(!modificar) {
				oUsuarioTO.setIdUsuario(null);
		 		oUsuarioTO.setDominio(Constants.getDominio(request).getIdDominio());
			}
			return mapping.findForward("success");
		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
	}
	
}
