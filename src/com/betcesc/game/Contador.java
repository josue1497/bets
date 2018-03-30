package com.betcesc.game;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.betcesc.game.common.Constants;
import com.betcesc.game.common.ValidSessionTread;
import com.betcesc.game.exceptions.SessionDuplicateException;
import com.betcesc.game.to.UsuarioTO;

public class Contador implements HttpSessionListener {

	public static ValidSessionTread	valid	= null;

	static {
		Constants.initializeUsuariosInSession();
	}

	public void sessionCreated(HttpSessionEvent se)
	{

	}

	public void sessionDestroyed(HttpSessionEvent se)
	{
		HttpSession session = se.getSession();
		// destruir session
		UsuarioTO oUsuarioTO = (UsuarioTO) session.getAttribute("usuario");
		try {
			Constants.deleteUserInSession(oUsuarioTO);
		}
		catch (SessionDuplicateException e) {
			e.printStackTrace();
		}
	}

	public static int getActivas()
	{
		return 0;
	}

}
