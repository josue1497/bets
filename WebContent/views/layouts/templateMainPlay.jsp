<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.betcesc.game.to.MensajeUserTO"%>
<%
String saldo = String.valueOf(request.getAttribute("saldo"));
boolean isViewGames = (session.getAttribute(Constants.VIEW_GAMES)!=null && session.getAttribute(Constants.VIEW_GAMES).equals("true"));
%>

    
<%-- Include common set of tag library declarations for each layout --%>
<%@ include file="/views/common/taglibs.jsp"%>
<html:html xhtml="true" locale="true">
<head>
  <script type="text/javascript" src="<%=basePath%>scripts/ajax.js"></script>
  <%-- Include common set of meta tags for each layout --%>
  <%@ include file="/views/common/meta.jsp" %>
  <%
  ArrayList listaMensaje = (ArrayList) session.getAttribute("MENSAJES_POR_USUARIO");
  %>
  <title><%=Constants.getDominio(request).getNombre()%></title>
<style type="text/css">
body {
	background-color:#000;
}
.fondoTransparente
{
	/*Div que ocupa toda la pantalla*/
	position:absolute;
	top:0px;
	left:0px;
	width:100%;
	height:100%;
	background-color:#fff;
	/*IE*/
	filter: alpha(opacity=50);
	/*FireFox Opera*/
	opacity: .5;
}
.centrado
{
	position: absolute;
	/*nos posicionamos en el centro del navegador*/
	top:25%;
	left:50%;
	/*determinamos una anchura*/
	width:600px;
	/*indicamos que el margen izquierdo, es la mitad de la anchura*/
	margin-left:-300px;
	/*determinamos una altura*/
	height:300px;
	/*indicamos que el margen superior, es la mitad de la altura*/
	margin-top:-150;
	border:1px solid blue;
	background-color:#fff;
	padding:5px;
}
</style>
<script language="Javascript">
	<!-- Begin
	function disableselect(e){
		return false
	}
	function reEnable(){
	return true
	}
	document.onselectstart=new Function ("return true")
	if (window.sidebar){
	document.onmousedown=disableselect
	document.onclick=reEnable
	}
	
	function right(e) {
	var msg = "Opcion no habilitada";
	if (navigator.appName == 'Netscape' && e.which == 3) {
	alert(msg);  // Delete this line to disable but not alert user
	return false;
	}
	else
	if (navigator.appName == 'Microsoft Internet Explorer' && event.button==2) {
	alert(msg); // Delete this line to disable but not alert user
	return false;
	}
	return true;
	}
	//document.onmousedown = right;
	
	// End -->

	window.http=null;
	function ocultarMensaje(num, idMensaje) {
		document.getElementById('TEXTO_MENSAJE_ADMIN'+num).style.display='none';
		document.getElementById('FONDO_MENSAJE_ADMIN'+num).style.display='none';
		
		http = new Ajax("<%=basePath%>mensajeAjax.do?idMensaje="+idMensaje);
		http.setMetodo("POST");
		http.start();
		
		if(document.getElementById('TEXTO_MENSAJE_ADMIN'+(num+1))) {
		document.getElementById('TEXTO_MENSAJE_ADMIN'+(num+1)).style.display='';
		document.getElementById('FONDO_MENSAJE_ADMIN'+(num+1)).style.display='';
		}
	}
	
	function mostrarMensaje() {
		
	}
	
	function onLoad() {
		history.go(+1);
	}
</script>  
</head>

<body onload="onLoad();" style="font-family:tahoma,helvetica,Arial,verdana,sans-serif;" leftmargin="10" topmargin="10" marginwidth="10" marginheight="10" vlink="#29613c" link="#29613c" bgcolor="#000000">
<form name="formaPlay" action="listGamePlay.do" method="post">
<table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0">
    <tr>
	  	<td style="height:66px" align="right">
            <tiles:insert attribute="header"/>
        </td>
	  	<td rowspan="2" style="height:66px;width:285px;" align="right">
            <table>
            	<tr>
					<%if(usuario.getIdRol().equals(Constants.ROL_JUGADOR)) {%>
						<td nowrap id="calc1" class="tituloTabla tablaConBorde" width="25%">Saldo: <span class="saldo"><%=Constants.getDominio(request).getMoneda()%> <%=saldo%></span></span></td>
					<%} else {%>
						<td nowrap  id="calc1" class="tituloTabla tablaConBorde" width="25%">Maximo: <span class="saldo"><%=Constants.getDominio(request).getMoneda()%> <%=saldo%></span>&nbsp;&nbsp;
							<img id="imgFlecha" src="<%=basePath%>icons/<%=(isViewGames?"arrow_out.png":"arrow_in.png")%>" title="<%=(isViewGames?"Maximizar la calculadora":"Minimizar la calculadora")%>"" style="cursor:pointer;" onclick="mostrarGames()"/>
						</td>
					<%}%>
            	</tr>
				<tr>
					<td align="center">
						<font color="white">Ref.:</font>&nbsp;<input type="text" name="numeroRef" size="8" maxlength="8" onkeyup="marcar(event,this)" class="numeroRef">
					</td>
				</tr>
				<tr>
					<td align="center">
							<table width="100%" cellspacing="1" cellpadding="0" border="0">
								<tr>
									<td class="calculadora" >Monto de la jugada&nbsp;:</td>
									<td  align="right"><%=Constants.getDominio(request).getMoneda()%>&nbsp;<input type="text" name="montoApostar" maxlength="5" size="9"  style="text-align:right;width:120px;" onkeyup="premio(event,this)"/></td>
								</tr>
							</table>
					</td>
				</tr>



            </table>
        </td>
    </tr>    
    <tr>
	    <td style="background-color:black;height:24px;">
            <tiles:insert attribute="headerName"/>	    
        </td>
    </tr>    
    <tr>
	    <!--<td style="background-color:#1b1b1b;height:24px;">-->
	    <td colspan="2" valign="top">
           	<tiles:insert attribute="body"/>
        </td>
    </tr>
</table>
<%if(listaMensaje!=null && listaMensaje.size()>0){
	MensajeUserTO msg = null;
	for(int i=0; i<listaMensaje.size();i++){
		msg = (MensajeUserTO) listaMensaje.get(i);
		%>
		<div id="FONDO_MENSAJE_ADMIN<%=i%>" class='fondoTransparente' style="display:<%=(i==0?"":"none")%>"></div>
		<div id="TEXTO_MENSAJE_ADMIN<%=i%>" class='centrado' style="display:<%=(i==0?"":"none")%>;border:2px solid blue;">
			<table border="0" width="100%" height="100%">
				<tr>
					<td height="1%" align="center">
						<b>MENSAJE DEL ADMINISTRADOR</b>
					</td>
				</tr>
				<tr>
					<td align="center">
						<span id="MENSAJE" style="color:blue;font-weight:bold;"><%=msg.getMensaje()%></span>
					</td>
				</tr>
				<tr>
					<td height="1%" align="center">
						<a href="javascript:void(0)" onclick="ocultarMensaje(<%=i%>,<%=msg.getIdMensaje()%>)" class="enlaceBoton">Cerrar el mensaje</a>
					</td>
				</tr>
			</table>
		</div>
	<%}%>
<%}%>
</form>
</body>
</html:html>
