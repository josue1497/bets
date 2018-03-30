package com.betcesc.game.ctrl.back;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.betcesc.game.common.Constants;
import com.betcesc.game.common.Validator;
import com.betcesc.game.interfaces.ParametrosIF;
import com.betcesc.game.interfaces.UsuarioIF;

public class RegisterParametrosActionForm extends ActionForm implements ParametrosIF {

	private static final long serialVersionUID = 4777760968541305564L;

	private static final Log log = LogFactory.getLog(RegisterParametrosActionForm.class);

	private String nombre = null;
	private String valor = null;

	private long time = 0;
	private HttpSession session;

	private UsuarioIF supervisor = null;

	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		log.info("Iniciando reset()");
		nombre = null;
		valor = null;
	}

	public ActionErrors validate(ActionMapping arg0, HttpServletRequest request) {
		log.info("Iniciando validate()");

		ActionErrors errores = new ActionErrors();

		if (!Validator.isString(this.getNombre()) || this.getNombre().length() < 3) {
			errores.add("nombre", new ActionError("error.field.validate", "nombre"));
		}

		//if (!Validator.isString(this.getValor()) || this.getValor().length() < 0) {
		//	errores.add("valor", new ActionError("error.field.validate", "valor"));
		//}

		return errores;
	}

	/* GET and SET */

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	
	
}
