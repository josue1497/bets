package com.betcesc.game.ctrl.back;

import java.util.ArrayList;

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

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.common.Constants;
import com.betcesc.game.common.Validator;
import com.betcesc.game.exceptions.GameClosedException;
import com.betcesc.game.exceptions.PlayNotValidException;
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.exceptions.TopePorCombinacionExcedidoException;
import com.betcesc.game.exceptions.TopePorDerechoExcedidoException;
import com.betcesc.game.exceptions.UserWithOutCreditException;
import com.betcesc.game.facade.JuegoFacade;
import com.betcesc.game.facade.UsuarioFacade;
import com.betcesc.game.form.CalculadoraForm;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.DeporteTO;

public class ListGamePlaySpeedAction extends Action {

	private static final Log log = LogFactory.getLog(ListGamePlaySpeedAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");

		return mapping.findForward("success");
	}

}
