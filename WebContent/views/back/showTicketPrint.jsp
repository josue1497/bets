<%@ page import="sun.jdbc.rowset.CachedRowSet" %>
<%@ page import="com.betcesc.game.interfaces.UsuarioIF" %>
<%@ page import="com.betcesc.game.common.Constants" %>

<%
String path1 = request.getContextPath();
String basePath1 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path1+"/";
UsuarioIF us = (UsuarioIF) request.getSession().getAttribute("usuario"); 
boolean isMulti = false;
boolean isAnimalito = false;
%>
<link href="<%=basePath1%>css/style<%=Constants.getDominio(request).getDominio()%>.css" rel="stylesheet" type="text/css" />

<% CachedRowSet ticket = (CachedRowSet) request.getAttribute("ticket");
String idJugada = "";
String premio = "";
String pagado = "";
String status = "";%>
<table width="85%" border="1" cellpadding="5" cellspacing="5" class="detalleTablaLeftBW" align="center" style="border-collapse:collapse">
<tr>
<td>
<%if(ticket.next()) {
	idJugada = ticket.getString("id_jugada");
	premio = ticket.getString("premio");
	pagado = ticket.getString("pagado");
	status = ticket.getString("status_jugada");
	isAnimalito = ticket.getString("id_deporte").equals(Constants.ID_DEPORTE_ANIMALITOS);%>
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
</script>
<table align="center" width="100%" height="100%" >
	<tr>
		<td class="tituloTablaBW tablaConBordeBW">Tipo</td>
		<td class="tituloTablaBW tablaConBordeBW">Cant.</td>
		<td class="tituloTablaBW tablaConBordeBW">Referencia</td>
		<td class="tituloTablaBW tablaConBordeBW">Equipo</td>
		<td class="tituloTablaBW tablaConBordeBW">Logro</td>
		<td class="tituloTablaBW tablaConBordeBW">Status</td>
		<td class="tituloTablaBW tablaConBordeBW" style="display:<%=isAnimalito?"":"none"%>">Loteria</td>
		<td class="tituloTablaBW tablaConBordeBW"style="display:<%=isAnimalito?"":"none"%>">Hora Sorteo</td>
	</tr>	
	<%do {
		isMulti = ticket.getString("empate").equals(Constants.JUEGO_MULTIPLE_EQUIPO);
	%>
	<tr>
		<td class="detalleTablaLeftBW"><%=Constants.getTipoLargo(ticket.getString("tipo"))%></td>
		<td class="detalleTablaLeftBW"><%=Constants.tipoFraccion(ticket.getString("cantidad"),!ticket.getString("tipo").equals(Constants.TIPO_ALTA) && !ticket.getString("tipo").equals(Constants.TIPO_BAJA))%></td>
		<td class="detalleTablaCenterBW"><%=ticket.getString("referencia").toUpperCase()%></td>
		
		<td class="detalleTablaLeftBW">
			<%if(ticket.getString("desc_equipo").equalsIgnoreCase("Empate")) {%>
				<%=ticket.getString("desc_equipo").concat(" : ").concat(ticket.getString("equipo1")).concat(isMulti?"":" Vs ".concat(ticket.getString("equipo2")))%>
			<%} else {%>
				<%=ticket.getString("desc_equipo")%> 
				<%if(!isMulti) {%>
					Vs <%=ticket.getString("equipo2")%>
				<%}%>
			<%}%>
		</td>
		
		<td class="detalleTablaRightBW"><%=ticket.getString("logro").toUpperCase()%></td>
		<td class="detalleTablaLeftBW"><%=ticket.getString("desc_jugada")%></td>
		<td class="detalleTablaLeftBW" style="display:<%=isAnimalito?"":"none"%>">
			<%=ticket.getString("desc_campeonato")%>
		</td>
		<td class="detalleTablaLeftBW" style="display:<%=isAnimalito?"":"none"%>">
			<%=ticket.getString("hora_sorteo")%>
		</td>
	</tr>	
	<%} while(ticket.next());%>
</table>
<hr width="100%" color="#efefef" align="center"/>
Premio <%=Constants.getDominio(request).getMoneda()%> <%=premio%><br/><br/><br/>
<center>
<font color="#000000" face="Arial" size="+1">MONTO <%=(status.equals(Constants.STATUS_JUGADA_PAGADA)?"PAGADO":"A PAGAR")%> : <%=Constants.getDominio(request).getMoneda()%> <%=pagado%></font>
</center>
<%}%>
</td>
</tr>
</table>
