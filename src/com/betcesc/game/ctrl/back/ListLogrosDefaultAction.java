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
import com.betcesc.game.dao.LogrosDAO;
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.facade.JuegoFacade;
import com.betcesc.game.facade.LogrosFacade;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.LogrosTO;

public class ListLogrosDefaultAction extends Action {

	private static final Log log = LogFactory.getLog(ListLogroReportAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		LogrosFacade logrosFacade;
		CachedRowSet lista;

		try {
			UsuarioIF usuario = Constants.getUserSession(request);
			
			logrosFacade = new LogrosFacade(request);
			
			if(request.getParameter("ML1")!=null) {
				LogrosDAO logrosDAO = new LogrosDAO();
				LogrosTO logrosTO = new LogrosTO();

				logrosTO.setIdDeporte(Constants.parseInt(request.getParameter("id_deporte")));
				
				logrosTO.setFavorito(Constants.parseInt(request.getParameter("ML1")));
				logrosTO.setHembra(Constants.parseInt(request.getParameter("ML2")));

				logrosTO.setFRL0(Constants.parseDouble(request.getParameter("FRL1")));
				logrosTO.setLRL0(Constants.parseInt(request.getParameter("LRL1")));
				logrosTO.setFRL1(Constants.parseDouble(request.getParameter("FRL2")));
				logrosTO.setLRL1(Constants.parseInt(request.getParameter("LRL2")));

				logrosTO.setFSRL0(Constants.parseDouble(request.getParameter("FSRL1")));
				logrosTO.setLSRL0(Constants.parseInt(request.getParameter("LSRL1")));
				logrosTO.setFSRL1(Constants.parseDouble(request.getParameter("FSRL2")));
				logrosTO.setLSRL1(Constants.parseInt(request.getParameter("LSRL2")));

				logrosTO.setFAB0(Constants.parseDouble(request.getParameter("FAB1")));
				logrosTO.setLAB0(Constants.parseInt(request.getParameter("LAB1")));
				logrosTO.setFAB1(Constants.parseDouble(request.getParameter("FAB2")));
				logrosTO.setLAB1(Constants.parseInt(request.getParameter("LAB2")));

				logrosTO.setLAP0(Constants.parseInt(request.getParameter("LAP1")));
				logrosTO.setLAP1(Constants.parseInt(request.getParameter("LAP2")));
				
				if(Integer.parseInt(logrosTO.getFavorito())!=0) {
					logrosDAO.actualizarLogrosDAO(logrosTO);
				}
			}
			
			lista = logrosFacade.listaLogrosFacade();
			
			// consultamos la lista de deportes
			JuegoFacade juegoFacade = new JuegoFacade(request);
			CachedRowSet listaDeporte = juegoFacade.listaDeporteActivoFacade(true);
			

			request.setAttribute("lista", lista);
			request.setAttribute("listaDeporte", listaDeporte);
		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
		return mapping.findForward("success");
	}
}
