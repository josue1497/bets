package com.betcesc.game.ctrl.front;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.betcesc.game.dao.RolDAO;
import com.betcesc.game.dao.StatusDAO;
import com.betcesc.game.dao.TipoCuentaDAO;
import com.betcesc.game.exceptions.DomainException;
import com.betcesc.game.exceptions.InformationMachineNotFoundException;
import com.betcesc.game.exceptions.SessionDuplicateException;
import com.betcesc.game.exceptions.UserNotFoundException;
import com.betcesc.game.exceptions.UserNotValidMachineException;
import com.betcesc.game.facade.UsuarioFacade;
import com.betcesc.game.interfaces.UsuarioIF;

public class RegisterSessionAction extends Action {

	private static final Log log = LogFactory.getLog(EndSessionAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excute()");
		  
		UsuarioIF user = null;
		UsuarioFacade usuarioFacade = new UsuarioFacade(request);
		ActionErrors errores = new ActionErrors();
		
		RolDAO oRolDAO = new RolDAO();
		StatusDAO oStatusDAO = new StatusDAO();
		TipoCuentaDAO oTipoCuentaDAO = new TipoCuentaDAO();

		try {
			user = (UsuarioIF) form;
			
			HttpSession session = request.getSession();
			
			Enumeration enu = session.getAttributeNames();
			while(enu.hasMoreElements()) {
				String atributo = (String)enu.nextElement();
				if(atributo.indexOf(".")==-1) {
					session.removeAttribute(atributo);
				}
			}

			usuarioFacade.registrarUsuarioEnSession(user);
			
			return mapping.findForward("success");
		} catch (DomainException e) {
			errores.add("usuario", new ActionError("error.usuario.notFoundDomain"));
		} catch (UserNotFoundException e) {
			errores.add("usuario", new ActionError("error.usuario.notFound"));
		} catch (SessionDuplicateException e) {
			errores.add("usuario", new ActionError("error.usuario.inSession"));
		} catch (UserNotValidMachineException e) {
			errores.add("usuario", new ActionError("error.usuario.notValidMachine"));
		} catch (InformationMachineNotFoundException e) {
			errores.add("usuario", new ActionError("error.usuario.notValidInformation"));
		} catch (Exception e) {
			errores.add("usuario", new ActionError("error.sistema.general"));
			e.printStackTrace();
		}

		saveErrors(request, errores);
		return mapping.findForward("error");
	}
}
