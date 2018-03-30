/*
 * Created on Jun 08, 2006
 */

package com.betcesc.game.ctrl.front;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author jrivero
 */

public class SecureStartAction extends Action {
    
    public static final Log log = LogFactory.getLog(SecureStartAction.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ServletContext context = request.getSession().getServletContext();
        
        String puertoSeguro = context.getInitParameter("puerto_seguro");
        
        String basePath = "https://".concat(request.getServerName()).concat(":").concat(puertoSeguro)+"/game/";
        
        log.info("Entrada Segura por SSl ");
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location",basePath);
        return null;
    }
}
