<%@ page import="sun.jdbc.rowset.CachedRowSet" %>
<%@ page import="com.betcesc.game.interfaces.UsuarioIF" %>
<%@ page import="com.betcesc.game.common.Constants" %>
<%
String path1 = request.getContextPath();
String basePath1 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path1+"/";
UsuarioIF us = (UsuarioIF) request.getSession().getAttribute("usuario"); 
boolean eliminada = false;
boolean isMulti = false;
%>
<% CachedRowSet ticket = (CachedRowSet) request.getAttribute("ticket");
String idJugada = "";
String premio = "";
String pagado = "";
String status = "";
String cancelada = "";
String diaJugada = "";
String diaActual = "";
%>
<table align="center" width="100%" cellspacing="0" cellpadding="0">
  <tr>
	<td width="100%" align="center" colspan="3">
	<font color="#00ff00" face="Helvetica, Arial, serif, Verdana" size="4">
		<%=request.getParameter("error")!=null?request.getParameter("error"):""%>
	</font>
	</td>
  </tr>
</table>
<table width="60%" border="1" cellpadding="5" cellspacing="5" class="detalleTablaLeft" align="center" style="border-collapse:collapse">
<tr>
<td>
<%if(ticket.next()) {
	idJugada = ticket.getString("id_jugada");
	premio = ticket.getString("premio");
	pagado = ticket.getString("pagado");
	status = ticket.getString("status_jugada");
	cancelada = ticket.getString("cancelada")==null?"0":ticket.getString("cancelada");
	diaJugada = ticket.getString("dia");
	diaActual = ticket.getString("dia_actual");
	eliminada = ticket.getString("id_status_jugada").equals(Constants.STATUS_JUGADA_ELIMINADA);
	%>
<%=ticket.getString("local").toUpperCase()%><br/>
<%=Constants.isNull(ticket.getString("rif_supervisor"))?"":"RIF:".concat(ticket.getString("rif_supervisor").toUpperCase())%>
<br/><br/>
Ticket Nro.:<%=Constants.numero(ticket.getString("id_jugada"),10)%><br/>
Fecha:<%=ticket.getString("dia")%><br/>
Hora:<%=ticket.getString("hora")%><br/><br/>
Jugada <%=Constants.getDominio(request).getMoneda()%> <%=ticket.getString("jugada")%>
<br/><br/>


<%@ include file="/views/common/taglibs.jsp"%>
<script language="javascript">
function send(orden) {
	document.forms[0].orden.value=orden;
	document.forms[0].submit();
}
function imp() {
	window.open("showTicketPrint.do?idJugada=<%=idJugada%>","_blank","width=700,height=600,status=no,resizable=yes,top=50,left=100");
}
function eliminar(enl) {

	<%if(us.getIdRol().equals(Constants.ROL_ADMINISTRADOR) && status.equals(Constants.STATUS_JUGADA_EN_JUEGO)) {%>
	var SID=true;
	<%} else {%>
	var SID = window.prompt("Por favor, ingrese la clave de seguridad","");
	<%}%>
	
	if(SID) {
		<logic:notPresent name="pendiente">
			enl.href="<%=basePath1%>deletePlay.do?idJugada=<%=idJugada%>&SID="+SID;
		</logic:notPresent>
		<logic:present name="pendiente">
			enl.href="<%=basePath1%>deletePlay.do?pendiente=true&idJugada=<%=idJugada%>&SID="+SID;
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
			enl.href="<%=basePath1%>payPlay.do?idJugada=<%=idJugada%>&SID="+SID;
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
		
		<td class="detalleTablaRight"><%=ticket.getString("logro").toUpperCase()%></td>
		<td class="detalleTablaLeft" style="display:<%=!eliminada?"":"none"%>">
			<%=ticket.getString("desc_jugada")%>
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
<%if((us.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA) || us.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) && status.equals(Constants.STATUS_JUGADA_EN_JUEGO)) {%>
	<%if(diaJugada.equals(diaActual)){%>
<a class="enlaceBoton" href="#" onclick="eliminar(this)">Eliminar</a>
	<%}%>
<%}%>
<%if(us.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA) && (status.equals(Constants.STATUS_JUGADA_PENDIENTE) || status.equals(Constants.ID_STATUS_JUGADA_SUSPENDIDA) && cancelada.equals("0")) ) {%>
<a class="enlaceBoton" style="width:100" href="#" onclick="pagarTicket(this)">Pagar</a>
<%}%>
&nbsp;&nbsp;&nbsp;&nbsp;
<logic:notPresent name="pendiente">
	<a class="enlaceBoton" href="<%=basePath1%>listPlay.do?pageNum=<%=session.getAttribute("pageNum")%>"	>Regresar</a>
</logic:notPresent>
<logic:present name="pendiente">
	<a class="enlaceBoton" href="<%=basePath1%>listPlayPending.do"	>Regresar</a>
</logic:present>
&nbsp;&nbsp;&nbsp;
<a class="enlaceBoton" href="#" onclick="imp()">Imprimible</a>
</center>
