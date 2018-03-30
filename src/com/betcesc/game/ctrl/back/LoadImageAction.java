package com.betcesc.game.ctrl.back;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.betcesc.game.bean.DominioBean;
import com.betcesc.game.common.Constants;
import com.betcesc.game.to.UsuarioTO;
import com.betcesc.game.util.FileUtil;

public class LoadImageAction extends Action {

	static Logger log = Logger.getLogger(LoadImageAction.class); 
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		UsuarioTO usuario = (UsuarioTO) request.getSession().getAttribute("USUARIO");
		
		log.info("request.getParameter('load')=" + String.valueOf(request.getParameter("load")));
		if (request.getParameter("load") != null) {

			FileUtil fu = new FileUtil();
			StringBuffer name = new StringBuffer();

			ServletContext context = request.getSession().getServletContext();
			String realContextPath = context.getRealPath(File.separator);
			
			DominioBean dom = Constants.getDominio(request);
			
			name.append(realContextPath).append("dbimg/home").append(dom.getIdDominio());
			fu.procesaFicherosAsociado(request, name.toString(), dom.getIdDominio());

			name.setLength(0);
			name.append("dbimg/home").append(dom.getIdDominio()).append(".jpg");

			request.getSession().setAttribute("image", name.toString());
		}

		return mapping.findForward("success");
	}
}
