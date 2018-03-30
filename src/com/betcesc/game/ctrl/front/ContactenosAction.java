package com.betcesc.game.ctrl.front;

/*
 * Created on Jun 08, 2006
 */


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.betcesc.game.common.Correo;

/**
 * @author jrivero
 */

public class ContactenosAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            //ContactenosActionForm forma = (ContactenosActionForm) form;

            String mensajeUsuario = request.getParameter("mensaje");
            String nombreUsuario = request.getParameter("nombre");
            String emailUsuario = request.getParameter("email");

            mensajeUsuario = (mensajeUsuario == null ? "" : mensajeUsuario);
            nombreUsuario = (nombreUsuario == null ? "" : nombreUsuario);
            emailUsuario = (emailUsuario == null ? "" : emailUsuario);

            StringBuffer cuerpo = new StringBuffer();
            cuerpo.append("Nombre : ");
            cuerpo.append(nombreUsuario);
            cuerpo.append("\n\n");
            cuerpo.append("Correo : ");
            cuerpo.append(emailUsuario);
            cuerpo.append("\n\n");
            cuerpo.append("Mensaje : ");
            cuerpo.append(mensajeUsuario);

            StringBuffer cuerpoHTML = new StringBuffer();

            cuerpoHTML.append("<table width=\"80%\" align=\"center\" style=\"border:1 color #B78009\" cellpadding=\"3\" cellspacing=\"3\">");
            cuerpoHTML.append("<tr>");
            cuerpoHTML.append("<td>");
            cuerpoHTML.append("<table width=\"100%\" align=\"center\" bgcolor=\"#000000\" cellpadding=\"3\" cellspacing=\"3\">");
            cuerpoHTML.append("<tr>");
            cuerpoHTML.append("<td align=\"center\" colspan=\"3\" bgcolor=\"#B78009\"><font size=\"+1\" color=\"#ffffff\">www.betcesc.com (contactenos)</font>");
            cuerpoHTML.append("</td>");
            cuerpoHTML.append("</tr>");
            cuerpoHTML.append("<tr>");
            cuerpoHTML.append("<td align=\"center\" colspan=\"3\" >&nbsp;</td>");
            cuerpoHTML.append("</tr>");
            cuerpoHTML.append("<tr>");
            cuerpoHTML.append("<td align=\"center\" colspan=\"3\" >");
            cuerpoHTML.append("<font size=\"3\" color=\"#ffffff\">");
            cuerpoHTML.append("Un usuario ha solicitado informaci&oacute;n a trav&eacute;s de la p&aacute;gina, sus datos son:");
            cuerpoHTML.append("</font>");
            cuerpoHTML.append("</td>");
            cuerpoHTML.append("</tr>");
            cuerpoHTML.append("<tr>");
            cuerpoHTML.append("<td align=\"right\" width=\"20%\"><b style=\"color:white\">Nombre</b></td>");
            cuerpoHTML.append("<td width=\"1%\" style=\"color:white\">:</td>");
            cuerpoHTML.append("<td><font size=\"2\" color=\"#ffffff\">");
            cuerpoHTML.append(nombreUsuario);
            cuerpoHTML.append("</font></td>");
            cuerpoHTML.append("</tr>");
            cuerpoHTML.append("<tr>");
            cuerpoHTML.append("<td align=\"right\"><b style=\"color:white\">Correo</b></td>");
            cuerpoHTML.append("<td style=\"color:white\">:</td>");
            cuerpoHTML.append("<td><font size=\"2\" color=\"#ffffff\">");
            cuerpoHTML.append(emailUsuario);
            cuerpoHTML.append("</font></td>");
            cuerpoHTML.append("</tr>");
            cuerpoHTML.append("<tr>");
            cuerpoHTML.append("<td align=\"right\" valign=\"top\" ><b style=\"color:white\">Mensaje</b></td>");
            cuerpoHTML.append("<td valign=\"top\" style=\"color:white\">:</td>");
            cuerpoHTML.append("<td><font size=\"2\" color=\"#ffffff\">");
            cuerpoHTML.append(mensajeUsuario.replaceAll("\n","<br>"));
            cuerpoHTML.append("</font><br/><br/></td>");
            cuerpoHTML.append("</tr>");
            cuerpoHTML.append("</table>");
            cuerpoHTML.append("</td>");
            cuerpoHTML.append("</tr>");
            cuerpoHTML.append("</table>");

            Correo oCorreo = new Correo();
            

            oCorreo.setTo("contactenos@betcesc2.com");
            oCorreo.setCc("cesarcorrea_82@hotmail.com");
            oCorreo.setSubject("Solicitud de Información a BETCESC.COM");
            oCorreo.setBcc("jairorivero@cantv.net,delys_wasiligiw@cantv.net");
            oCorreo.setText(cuerpo.toString());
            oCorreo.setTextHtml(cuerpoHTML.toString());

            if (oCorreo.send() > 0 ) {
                return mapping.findForward("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return mapping.findForward("error");
        }

        return mapping.findForward("exito");
    }
}