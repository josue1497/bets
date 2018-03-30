package com.betcesc.game.ctrl.back;

/*
 * Created on 27-abr-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.betcesc.game.common.Validator;
import com.betcesc.game.facade.CampeonatoFacade;
import com.betcesc.game.facade.DeporteFacade;
import com.betcesc.game.facade.EquipoFacade;
import com.betcesc.game.facade.LanzadorFacade;
import com.betcesc.game.facade.LigaFacade;
import com.betcesc.game.to.CampeonatoTO;
import com.betcesc.game.to.DeporteTO;
import com.betcesc.game.to.EquipoLigaTO;
import com.betcesc.game.to.EquipoTO;
import com.betcesc.game.to.LanzadorTO;
import com.betcesc.game.to.LigaTO;

/**
 * @author jrivero
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AgregarAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String cadena = null;
		String tabla = null;

		if (request.getMethod().equals("POST")) {
			if (request.getParameter("table") == null || request.getParameter("table").trim().equals("")) {
				return null;
			}
			tabla = request.getParameter("table");
		}
		if (tabla == null || tabla.trim().equals("")) {
			return null;
		}
		try {
			response.getWriter().print("");

			int act = 0;
			if (tabla.equals("0")) {
				// agregaremos el deporte
				DeporteFacade deporteFacade = new DeporteFacade(request);
				DeporteTO oDeporteTO = new DeporteTO();
				oDeporteTO.setIdDeporte(request.getParameter("id_deporte"));
				if (!oDeporteTO.getIdDeporte().equals("0")) {
					oDeporteTO = deporteFacade.consultarDeporteFacade(oDeporteTO);
				}
				oDeporteTO.setDescDeporte(Validator.isEmptyParameter(request,"desc_deporte",oDeporteTO.getDescDeporte()));
				oDeporteTO.setEmpate(Validator.isEmptyParameter(request,"empate",oDeporteTO.getEmpate()));
				oDeporteTO.setIdStatusDeporte(Validator.isEmptyParameter(request,"id_status_deporte",oDeporteTO.getIdStatusDeporte()));
				oDeporteTO.setReferenciaInicio(Validator.isEmptyParameter(request,"referencia_inicio",oDeporteTO.getReferenciaInicio()));
				oDeporteTO.setRunlineInicio(Validator.isEmptyParameter(request,"runline_inicio",oDeporteTO.getRunlineInicio()));
				oDeporteTO.setCombinacion(Validator.isNullParameter(request,"combinacion",oDeporteTO.getCombinacion()));
				oDeporteTO.setItems(Validator.isNullParameter(request,"items",oDeporteTO.getItems()));

				oDeporteTO.setRunlineLogroInicio0(Validator.isEmptyParameter(request,"runline_logro_inicio0",oDeporteTO.getRunlineLogroInicio0()));
				oDeporteTO.setRunlineLogroInicio1(Validator.isEmptyParameter(request,"runline_logro_inicio1",oDeporteTO.getRunlineLogroInicio1()));
				oDeporteTO.setAltabajaLogroInicio0(Validator.isEmptyParameter(request,"altabaja_logro_inicio0",oDeporteTO.getAltabajaLogroInicio0()));
				oDeporteTO.setAltabajaLogroInicio1(Validator.isEmptyParameter(request,"altabaja_logro_inicio1",oDeporteTO.getAltabajaLogroInicio1()));
				
				if (oDeporteTO.getIdDeporte().equals("0")) {
					if(!oDeporteTO.getDescDeporte().trim().equals("")) {
						act = deporteFacade.insertarDeporteFacade(oDeporteTO);
					}
				} else {
					act = deporteFacade.actualizarDeporteFacade(oDeporteTO);
				}
			} else if (tabla.equals("1")) {
				// agregaremos el liga
				LigaFacade ligaFacade = new LigaFacade(request);
				LigaTO oLigaTO = new LigaTO();
				oLigaTO.setIdLiga(request.getParameter("id_liga"));
				if (!oLigaTO.getIdLiga().equals("0")) {
					oLigaTO = ligaFacade.consultarLigaFacade(oLigaTO);
				}
				oLigaTO.setDescLiga(Validator.isEmptyParameter(request,"desc_liga",oLigaTO.getDescLiga()));
				oLigaTO.setIniciales(Validator.isEmptyParameter(request,"iniciales",oLigaTO.getIniciales()));
				oLigaTO.setIdDeporte(Validator.isEmptyParameter(request,"id_deporte",oLigaTO.getIdDeporte()));
				
				if (oLigaTO.getIdLiga().equals("0")) {
					if(!oLigaTO.getDescLiga().trim().equals("")) {
						act = ligaFacade.insertarLigaFacade(oLigaTO);
					}
				} else {
					act = ligaFacade.actualizarLigaFacade(oLigaTO);
				}
			} else if (tabla.equals("2")) {
				// agregaremos el equipo
				EquipoFacade equipoFacade = new EquipoFacade(request);
				EquipoTO oEquipoTO = new EquipoTO();
				EquipoLigaTO oEquipoLigaTO = new EquipoLigaTO();
				oEquipoTO.setIdEquipo(request.getParameter("id_equipo"));
				if (!oEquipoTO.getIdEquipo().equals("0")) {
					oEquipoTO = equipoFacade.consultarEquipoFacade(oEquipoTO);
				}
				oEquipoTO.setDescEquipo(Validator.isEmptyParameter(request,"desc_equipo",oEquipoTO.getDescEquipo()));
				oEquipoTO.setAbreviatura(request.getParameter("abreviatura"));
				oEquipoTO.setDescCorta(Validator.isEmptyParameter(request,"desc_corta",oEquipoTO.getDescCorta()));
				oEquipoLigaTO.setIdEquipo(oEquipoTO.getIdEquipo());
				oEquipoLigaTO.setIdLiga(request.getParameter("id_liga"));
				
				
				act = equipoFacade.insertarEquipoFacade(oEquipoTO);
				
				oEquipoLigaTO.setIdEquipo(oEquipoTO.getIdEquipo());
				equipoFacade.insertarEquipoLigaFacade(oEquipoLigaTO);
				
			} else if (tabla.equals("3")) {
				// agregaremos el lanzador
				LanzadorFacade lanzadorFacade = new LanzadorFacade(request);
				LanzadorTO oLanzadorTO = new LanzadorTO();
				oLanzadorTO.setIdLanzador(request.getParameter("id_lanzador"));
				if (!oLanzadorTO.getIdLanzador().equals("0")) {
					oLanzadorTO = lanzadorFacade.consultarLanzadorFacade(oLanzadorTO);
				}
				oLanzadorTO.setNombreLanzador(Validator.isEmptyParameter(request,"nombre_lanzador",oLanzadorTO.getNombreLanzador()));
				oLanzadorTO.setIdEquipo(Validator.isEmptyParameter(request,"id_equipo",oLanzadorTO.getIdEquipo()));
				
				if (oLanzadorTO.getIdLanzador().equals("0")) {
					if(!oLanzadorTO.getNombreLanzador().trim().equals("")) {
						act = lanzadorFacade.insertarLanzadorFacade(oLanzadorTO);
					}
				} else {
					act = lanzadorFacade.actualizarLanzadorFacade(oLanzadorTO);
				}
			} else if (tabla.equals("4")) {
				// agregaremos el campeonato
				CampeonatoFacade campeonatoFacade = new CampeonatoFacade(request);
				CampeonatoTO oCampeonatoTO = new CampeonatoTO();
				oCampeonatoTO.setIdCampeonato(request.getParameter("id_campeonato"));
				if (!oCampeonatoTO.getIdCampeonato().equals("0")) {
					oCampeonatoTO = campeonatoFacade.consultarCampeonatoFacade(oCampeonatoTO);
				}
				oCampeonatoTO.setDescCampeonato(Validator.isEmptyParameter(request,"nombre_campeonato",oCampeonatoTO.getDescCampeonato()));
				
				if (oCampeonatoTO.getIdCampeonato().equals("0")) {
					if(!oCampeonatoTO.getDescCampeonato().trim().equals("")) {
						act = campeonatoFacade.insertarCampeonatoFacade(oCampeonatoTO);
					}
				} else {
					act = campeonatoFacade.actualizarCampeonatoFacade(oCampeonatoTO);
				}
				response.getWriter().print(oCampeonatoTO.getIdCampeonato());
			}
			
			if (act==0) {
				response.getWriter().print("Error al guardar el registro");
			}
		} catch (Exception e) {
			response.getWriter().print("Error al guardar el registro");
		}

		return null;
	}

}
