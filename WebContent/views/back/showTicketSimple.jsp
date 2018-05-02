<%@page import="javax.sound.midi.SysexMessage"%>
<%@ page import="sun.jdbc.rowset.CachedRowSet" %>
<%@ page import="com.betcesc.game.interfaces.UsuarioIF" %>
<%@ page import="com.betcesc.game.common.Constants" %>
<%@ page import="com.jade.util.Encriptor" %>

<%@ include file="/views/common/taglibs.jsp"%>
<%
String path1 = request.getContextPath();
String basePath1 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path1+"/";
UsuarioIF us = (UsuarioIF) request.getSession().getAttribute("usuario"); 
boolean eliminada = false;
boolean isMulti = false;
boolean isAnimalito= false;
%>
<% CachedRowSet ticket = (CachedRowSet) request.getAttribute("ticket");
String idJugada = "";
String premio = "";
String pagado = "";
String status = "";
String diaJugada = "";
String diaActual = "";
String horaJuego = "";


%>
<html>
<head>
<title><%=request.getParameter("titulo")%></title>
<!-- HTTP 1.1 -->
<meta http-equiv="Cache-Control" content="no-store"/>
<!-- HTTP 1.0 -->
<meta http-equiv="Pragma" content="no-cache"/>
<!-- Prevents caching at the Proxy Server -->
<meta http-equiv="Expires" content="0"/>

<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />

<script type="text/javascript" src="<%=basePath1%>scripts/validaciones.js"></script>
<link href="<%=basePath1%>css/style<%=Constants.getDominio(request).getDominio()%>.css" rel="stylesheet" type="text/css" />
</head>
<body bgcolor="#000000">
<table align="center" width="100%" cellspacing="0" cellpadding="0">
  <tr>
	<td width="100%" align="center" colspan="3">
	<font color="#00ff00" face="Helvetica, Arial, serif, Verdana" size="4">
		<%=request.getParameter("error")!=null?request.getParameter("error"):""%>
	</font>
	</td>
  </tr>
</table>
<table width='100%' border="1" cellpadding="5" cellspacing="5" class="detalleTablaLeft" align="center" style="border-collapse:collapse">
<tr>
<td>
<%if(ticket.next()) {
	idJugada = ticket.getString("id_jugada");
	premio = ticket.getString("premio");
	pagado = ticket.getString("pagado");
	status = ticket.getString("status_jugada");
	diaJugada = ticket.getString("dia");
	diaActual = ticket.getString("dia_actual");
	eliminada = ticket.getString("id_status_jugada").equals(Constants.STATUS_JUGADA_ELIMINADA);
	isAnimalito = ticket.getString("id_deporte").equals(Constants.ID_DEPORTE_ANIMALITOS);
	%>
<%=ticket.getString("local").toUpperCase()%><br/>
<%=Constants.isNull(ticket.getString("rif_supervisor"))?"":"RIF:".concat(ticket.getString("rif_supervisor").toUpperCase())%>
<br/><br/>
Ticket Nro.:<%=Constants.numero(ticket.getString("id_jugada"),10)%><br/>
Fecha:<%=ticket.getString("dia")%><br/>
Hora:<%=ticket.getString("hora")%><br/><br/>
Jugada <%=Constants.getDominio(request).getMoneda()%> <%=ticket.getString("jugada")%>
Clave:<%=Encriptor.getSID(ticket.getString("id_jugada"))%>
<br/><br/>
<script language="javascript">
function send(orden) {
	document.forms[0].orden.value=orden;
	document.forms[0].submit();
}
function imp() {
	window.open("showTicketPrint.do?idJugada=<%=idJugada%>","_blank","width=700,height=600,status=no,resizable=yes,top=50,left=100");
}
function eliminar(enl) {

	<%if((isAdminGeneral || us.getEliminarJugada().equals("1")) ||( (us.getIdRol().equals(Constants.ROL_ADMINISTRADOR) || us.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA)) && status.equals(Constants.STATUS_JUGADA_EN_JUEGO))) {%>
	var SID=true;
	<%} else {%>
	var SID = window.prompt("Por favor, ingrese el codigo de seguridad","");
	<%}%>
	
	if(SID) {
		<logic:notPresent name="pendiente">
			enl.href="<%=basePath1%>deletePlay.do?reload=true&idJugada=<%=idJugada%>&SID="+SID;
		</logic:notPresent>
		<logic:present name="pendiente">
			enl.href="<%=basePath1%>deletePlay.do?reload=true&pendiente=true&idJugada=<%=idJugada%>&SID="+SID;
		</logic:present>
	}
}
function pagarTicket(enl) {

	<%if(us.getPagoClave().equals(Constants.PAGO_CLAVE_NO)) {%>
	var SID=true;
	<%} else {%>
	var SID = window.prompt("Por favor, ingrese el codigo de seguridad","");
	<%}%>
	if(SID) {
		<logic:notPresent name="pendiente">
			enl.href="<%=basePath1%>payPlaySimple.do?reload=true&idJugada=<%=idJugada%>&SID="+SID;
			
		</logic:notPresent>
		<logic:present name="pendiente">
			enl.href="<%=basePath1%>payPlay.do?pendiente=true&idJugada=<%=idJugada%>&SID="+SID;
		</logic:present>
	}
}

</script>
<table align="center" width="100%" height="100%" >
	<tr>
		<td class="tituloTabla tablaConBorde">Tipo</td>
		<td class="tituloTabla tablaConBorde">Cant.</td>
		<td class="tituloTabla tablaConBorde">Referencia</td>
		<td class="tituloTabla tablaConBorde">Equipo</td>
		<td class="tituloTabla tablaConBorde">Logro</td>
		<td class="tituloTabla tablaConBorde" style="display:<%=!eliminada?"":"none"%>">Status</td>
		<td class="tituloTabla tablaConBorde" style="display:<%=isAnimalito?"":"none"%>">Loteria</td>
		<td class="tituloTabla tablaConBorde" style="display:<%=isAnimalito?"":"none"%>">Hora Sorteo</td>
	</tr>	
	<%do {
		isMulti = ticket.getString("empate").equals(Constants.JUEGO_MULTIPLE_EQUIPO);
	%>
	<tr>
		<td class="detalleTablaLeft"><%=Constants.getTipoLargo(ticket.getString("tipo"))%></td>
		<td class="detalleTablaLeft"><%=Constants.tipoFraccion(ticket.getString("cantidad"),!ticket.getString("tipo").equals(Constants.TIPO_ALTA) && !ticket.getString("tipo").equals(Constants.TIPO_BAJA))%></td>
		<td class="detalleTablaCenter"><%=ticket.getString("referencia").toUpperCase()%></td>
		
		<td class="detalleTablaLeft">
			<%if(ticket.getString("desc_equipo").equalsIgnoreCase("Empate")) {%>
				<%=ticket.getString("desc_equipo").concat(" : ").concat(ticket.getString("equipo1")).concat(isMulti?"":" Vs ".concat(ticket.getString("equipo2")))%>
			<%} else {%>
				<%=ticket.getString("desc_equipo")%> 
				<%if(!isMulti) {%>
					Vs <%=ticket.getString("equipo2")%>
				<%}%>
			<%}%>
		</td>
		
		<td class="detalleTablaRight"><%=ticket.getString("logro").toUpperCase()%>	
		</td>
		<td class="detalleTablaLeft" style="display:<%=!eliminada?"":"none"%>">
			<%=ticket.getString("desc_jugada")%>
		</td>
		<td class="detalleTablaLeft" style="display:<%=isAnimalito?"":"none"%>">
			<%=ticket.getString("desc_campeonato")%>
		</td>
		<td class="detalleTablaLeft" style="display:<%=isAnimalito?"":"none"%>">
			<%=ticket.getString("hora_sorteo")%>
		</td>
	</tr>	
	<%} while(ticket.next());%>
</table>
<hr width="100%" color="#efefef" align="center"/>
Premio <%=Constants.getDominio(request).getMoneda()%> <%=premio%><br/><br/><br/>
<center>
<font color="white" face="Arial" size="+1">MONTO <%=(status.equals(Constants.STATUS_JUGADA_PAGADA)?"PAGADO":"A PAGAR")%> : <%=Constants.getDominio(request).getMoneda()%> <%=pagado%></font>
</center>
<%}%>
</td>
</tr>
</table>
<center>


<%
	

if(((isAdminGeneral || us.getEliminarJugada().equals("1"))  && !status.equals(Constants.STATUS_JUGADA_ELIMINADA)) || ((us.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA) || us.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA) || us.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) && status.equals(Constants.STATUS_JUGADA_EN_JUEGO))) {%>
	<%if(isAdminGeneral || us.getEliminarJugada().equals("1") || diaJugada.equals(diaActual)){%>
<a class="enlaceBoton" style="width:100" href="#" onclick="eliminar(this)">Eliminar</a>
	<%}%>
<%}%>
<%if(us.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA) && status.equals(Constants.STATUS_JUGADA_PENDIENTE)) {%>
	<a class="enlaceBoton" style="width:100" href="#" onclick="pagarTicket(this)">Pagar</a>
<%}%>
&nbsp;&nbsp;&nbsp;&nbsp;
<logic:present name="pendiente">
	<a class="enlaceBoton" style="width:100" href="<%=basePath1%>listPlayPending.do"	>Regresar</a>
</logic:present>
&nbsp;&nbsp;&nbsp;
<a class="enlaceBoton" style="width:100" href="#" onclick="imp()">Imprimible</a>
</center>
</body>
</html>
<logic:present name="reload">
<script type="text/javascript">
	try {
		window.opener.document.location.reload();
	} catch(e){}
</script>
</logic:present>
