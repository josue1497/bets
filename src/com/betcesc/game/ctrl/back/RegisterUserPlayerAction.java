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

public class RegisterUserPlayerAction extends Action {

	private static final Log log = LogFactory.getLog(RegisterUserPlayerAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		try {
			//UsuarioTO usuario = (UsuarioTO) request.getSession().getAttribute("usuario");

			UsuarioIF oUsuarioTO = new UsuarioTO();

			oUsuarioTO.setIdRol(Constants.ROL_ADMINISTRADOR);
			boolean modificar = false;
			
			UsuarioFacade oUsuarioFacade = new UsuarioFacade(request);
			
			String rol = Constants.ROL_JUGADOR;

			oUsuarioTO.setIdRol(rol);
			rol = oUsuarioTO.getIdRol();

			request.setAttribute("user", oUsuarioTO);
			/* vamos a cargar algunas tablas de uso general */
			request.setAttribute("listaRol",oUsuarioFacade.listaRolFacade(oUsuarioTO,null));
			request.setAttribute("listaStatus",oUsuarioFacade.listaStatusFacade());
			request.setAttribute("listaTipoCuenta",oUsuarioFacade.listaTipoCuentaFacade());
			request.setAttribute("listaSupervisor",oUsuarioFacade.listaUsuarioSupervisorAdminFacade(oUsuarioTO));

			return mapping.findForward("success_".concat(rol));
		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
	}
	
}
