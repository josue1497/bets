package com.betcesc.game.ctrl.back;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.common.Constants;
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.facade.UsuarioFacade;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.UsuarioTO;

public class ListUserAction extends Action {

	private static final Log log = LogFactory.getLog(ListUserAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		UsuarioFacade usuarioFacade;
		CachedRowSet lista;
		String rol;
		String dominioList = "";
		String nombreUsuario = "";
		UsuarioTO usuarioFiltro = null;

		try {
			UsuarioIF usuario = Constants.getUserSession(request);
			if(!usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				throw new SessionInvalidException("Usuario no autorizado");
			}
			
			usuarioFacade = new UsuarioFacade(request);
			HttpSession session = request.getSession();
			
			// perfil seleccionado
			rol = (request.getParameter("rol")!=null?request.getParameter("rol"):(session.getAttribute("rol")!=null?session.getAttribute("rol").toString():Constants.ROL_JUGADOR_DE_TAQUILLA));
			// nombre de usuario
			nombreUsuario = (request.getParameter("nombreUsuario")!=null?request.getParameter("nombreUsuario"):(session.getAttribute("nombreUsuario")!=null?session.getAttribute("nombreUsuario").toString():""));
			// dominio
			dominioList = (request.getParameter("dominioList")!=null?request.getParameter("dominioList"):(session.getAttribute("dominioList")!=null?session.getAttribute("dominioList").toString():usuario.getDominio()));
			
			int orden = 0;
			try {
				orden = Integer.parseInt(request.getParameter("orden"));
			} catch(Exception e) {
				
			}
			if(orden==0 && request.getSession().getAttribute("orden")!=null) {
				try {
					orden = ((Integer)request.getSession().getAttribute("orden")).intValue();
				} catch(Exception e) {
					request.getSession().removeAttribute("orden");
				}
			}
			request.getSession().setAttribute("orden",new Integer(orden));
			
			String idUsuario = request.getParameter("idUsuario"); 
			
			if(request.getParameter("isModulo")==null) {
				
				if(request.getParameter("security")!=null) {
					UsuarioTO oUsuarioTO = new UsuarioTO();
					oUsuarioTO.setIdUsuario(idUsuario);
					oUsuarioTO = usuarioFacade.consultarUsuarioFacade(oUsuarioTO);
					
					usuarioFiltro = new UsuarioTO();
					usuarioFiltro.setDominio(oUsuarioTO.getDominio());
				} else {
					usuarioFiltro = new UsuarioTO();
					usuarioFiltro.setDominio(dominioList);
					usuarioFiltro.setIdRol(rol);
					usuarioFiltro.setUsuario(nombreUsuario);
				}
					
				lista = usuarioFacade.listaPaginaUsuarioFacade(usuario, orden, idUsuario, usuarioFiltro);
			} else {

				// dominio
				dominioList = usuario.getDominio();
				// nombre de usuario
				nombreUsuario = "";

				lista = new CachedRowSet();
			}


			request.setAttribute("lista",lista);
			request.setAttribute("tipo",request.getParameter("tipo"));
			
			session.setAttribute("rol",rol);
			request.getSession().setAttribute("dominioList", dominioList);
			request.getSession().setAttribute("nombreUsuario", nombreUsuario);

			return mapping.findForward("success");

		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
	}
	
}
