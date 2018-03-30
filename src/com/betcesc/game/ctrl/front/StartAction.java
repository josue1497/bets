package com.betcesc.game.ctrl.front;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.betcesc.game.bean.DominioBean;
import com.betcesc.game.common.Constants;
import com.betcesc.game.facade.JuegoFacade;
import com.betcesc.game.facade.ParametrosFacade;
import com.betcesc.game.facade.VistaFacade;
import com.betcesc.game.interfaces.ParametrosIF;
import com.betcesc.game.to.MenuTO;
import com.betcesc.game.to.ParametrosTO;
import com.betcesc.game.to.VistaTO;

public class StartAction extends Action
    {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	    {

		ArrayList lista = new ArrayList();
		ArrayList listaVista = new ArrayList();
		ArrayList listaHeader = new ArrayList();
		ArrayList listaPublicidad = new ArrayList();
		ArrayList listaAtencion = new ArrayList();

		JuegoFacade juegoFacade;
		VistaFacade vistaFacade;
		ParametrosFacade parametrosFacade;

		ArrayList listaDeporte;
		ParametrosIF oParametros = new ParametrosTO();
		String urlVideo = "";
		String segundos = "";

		juegoFacade = new JuegoFacade(request);
		parametrosFacade = new ParametrosFacade(request);
		vistaFacade = new VistaFacade(request);

		lista.add(new MenuTO("Inicio", "start.do"));
		lista.add(new MenuTO("Quienes Somos?", "quienes.do"));
		lista.add(new MenuTO("Politicas", "politica.do"));
		lista.add(new MenuTO("Privacidad", "privacidad.do"));
		lista.add(new MenuTO("Codigos de Conducta", "codigos.do"));
		lista.add(new MenuTO("Cómo Apostar", "jugadas.do"));
		lista.add(new MenuTO("Reglas", "reglas.do"));
		lista.add(new MenuTO("Contactos", "contactenos.do"));

		listaDeporte = juegoFacade.listaDeporteConJuegoAbiertoFacade(true);

		// vistas
		listaHeader = vistaFacade.listaVistaActivaFacade(Constants.VISTA_HEADER);
		if (listaHeader != null && listaHeader.size() > 0)
		    {
			Constants.NOMBRE_IMAGEN_HEADER = "dbimg/".concat(((VistaTO) listaHeader.get(0)).getImage());
		    } else
		    {
			Constants.NOMBRE_IMAGEN_HEADER = Constants.NOMBRE_IMAGEN_HEADER_ORIGINAL;
		    }

		listaVista = vistaFacade.listaVistaActivaFacade(Constants.VISTA_BODY);

		listaAtencion = vistaFacade.listaVistaActivaFacade(Constants.VISTA_ATENCION);
		if (listaAtencion != null && listaAtencion.size() > 0)
		    {
			Constants.NOMBRE_IMAGEN_SOPORTE = "dbimg/".concat(((VistaTO) listaAtencion.get(0)).getImage());
		    } else
		    {
			Constants.NOMBRE_IMAGEN_SOPORTE = Constants.NOMBRE_IMAGEN_SOPORTE_ORIGINAL;
		    }

		listaPublicidad = vistaFacade.listaVistaActivaFacade(Constants.VISTA_PUBLICIDAD);

		oParametros.setNombre("URL_VIDEO_HOME");
		urlVideo = parametrosFacade.consultarParametrosFacade(oParametros).getValor();

		oParametros.setNombre("SEGUNDOS_VISTAS");
		segundos = parametrosFacade.consultarParametrosFacade(oParametros).getValor();

		request.setAttribute("menuInicio", lista);
		request.setAttribute("listaDeporte", listaDeporte);
		request.setAttribute("urlVideoHome", urlVideo);
		request.setAttribute("segundos", segundos);
		request.setAttribute("vista", listaVista);
		request.setAttribute("publicidad", listaPublicidad);

		request.setAttribute("VISTA_HOME", "true");

		// Constants.RegisterDominioInSession(request);
		String forward = "";
		
		DominioBean dom = Constants.getDominio(request);
		
		if (dom != null)
		    forward = "success".concat(dom.getIdDominio());
		else
		    forward = "success_error";

		
		ActionForward fw =mapping.findForward(forward);
		
		if (fw==null)
		{
			throw new Exception("Problema en la configuracion de struts buscnado " + forward);
		}
		
		return fw;
	    }
    }
