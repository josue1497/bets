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
import com.betcesc.game.exceptions.SecurityIDNotValidException;
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.facade.JuegoFacade;
import com.betcesc.game.interfaces.JugadaIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.JugadaTO;
import com.jade.util.Encriptor;

public class DeletePlayAction extends Action {

	private static final Log log = LogFactory.getLog(DeletePlayAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		JuegoFacade juegoFacade;
		ActionErrors errores = new ActionErrors();
		JugadaIF oJugadaTO = new JugadaTO();

		try {
			UsuarioIF usuario = Constants.getUserSession(request);
			
			boolean isAdmin = usuario.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR);
			boolean isAdminRol = usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR);
			boolean isAdminTaq = usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA);
			boolean isJugTaq = usuario.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA);
			
			
			if(!isJugTaq && !isAdminRol && !isAdminTaq) {
				throw new SessionInvalidException("El usuario no puede realizar esta operacion");
			}

			juegoFacade = new JuegoFacade(request);

			oJugadaTO.setIdJugada(request.getParameter("idJugada"));
			
			// validamos el codigo SID de la jugada
			if(!isAdminRol && !isAdminTaq) {
				if(!Encriptor.getSID(request.getParameter("idJugada")).equals(request.getParameter("SID"))) {
					throw new SecurityIDNotValidException("El SID no es correcto, no puede eliminar la jugada");
				}
			}

			if(!juegoFacade.eliminarJugadaFacade(oJugadaTO,usuario)) {
				errores.add("error", new ActionError("error.sistema.taquilla"));
			}
			
			
			if(request.getParameter("reload")!=null) {
				request.setAttribute("reload", new Boolean(true));
				ActionForward forward = new ActionForward("/showTicketSimple.do");
				return forward;
			}

		} catch (SessionInvalidException e) {
			e.printStackTrace();
			return Constants.FORWARD_START;
		} catch (SecurityIDNotValidException e) {
			errores.add("error", new ActionError("error.sistema.sid"));
		} catch (Exception e) {
			errores.add("error", new ActionError("error.sistema.general"));
		}
		saveErrors(request, errores);
		if(request.getParameter("pendiente")!=null && request.getParameter("pendiente").equals("true")) {
			return mapping.findForward("successPending");
		} 
		Integer pageNum = new Integer(1);
		if(request.getSession().getAttribute("pageNum")!=null) {
			pageNum = (Integer)request.getSession().getAttribute("pageNum");
		}
		ActionForward forward = new ActionForward("/listPlay.do?pageNum="+pageNum);
		return forward;
		//return mapping.findForward("success");
	}

}
