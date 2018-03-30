package com.betcesc.game.ctrl.front;

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
import com.betcesc.game.facade.JuegoFacade;

public class ResultadosAction extends Action {

	private static final Log log = LogFactory.getLog(ResultadosAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");

		JuegoFacade oJuegoFacade = new JuegoFacade(request);
		
		ArrayList lista = null;
		
		lista = Constants.CACHE_LISTA_RESULTADOS;
		if(lista==null || lista.size()==0) {
			lista = oJuegoFacade.consultarResultadosFacade(null);
			Constants.CACHE_LISTA_RESULTADOS=lista;
		}
		
		
		request.setAttribute("lista", lista);
		
		return mapping.findForward("success");
	}
}
