<%@ page language="java" pageEncoding="iso-8859-1" contentType="text/html; charset=iso-8859-1" import="org.apache.struts.util.MessageResources, org.apache.struts.Globals, java.util.Locale"%>
<%@ taglib uri="/WEB-INF/tlds/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/pagina.tld" prefix="pg" %>
<%@ page import="com.betcesc.game.common.Constants" %>
<%@ page import="com.betcesc.game.interfaces.UsuarioIF" %>
<%@ page import="sun.jdbc.rowset.CachedRowSet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.TreeMap" %>

<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>


<%	UsuarioIF usuario = (UsuarioIF) request.getSession().getAttribute("usuario"); 
	boolean isAdminGeneral = false;
	boolean isAdminListero = false;
	if(usuario!=null){
		isAdminGeneral = usuario.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR);
	}
	if(usuario!=null){
		isAdminListero = usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) && usuario.getTipo().equals("1");
	}
%>

<%
/*
		String ip = "";
        if (request.getHeader("HTTP_X_FORWARDED_FOR") == null){
            ip = request.getRemoteAddr();
        }else{
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        log.info("IP*********="+ip);
*/
%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
