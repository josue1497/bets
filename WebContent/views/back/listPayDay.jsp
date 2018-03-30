<%@ include file="/views/common/taglibs.jsp"%>
<%@ include file="/views/common/meta.jsp"%>
<% boolean viewUser = ( usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) || usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA) ); %>
<% CachedRowSet lista = (CachedRowSet)request.getAttribute("lista");  %>
<% int cont=0; %>

<script language="JavaScript" src="<%=basePath%>scripts/Calendario_2.js"></script>

<% double total=0;%>
<table align="center" width="98%" cellspacing="0" cellpadding="0">
<caption class="tituloTablaBW">*** LISTADO DE JUGADAS PAGADAS EL DIA <%=request.getAttribute("fecha")%> Hasta <%=request.getAttribute("fecha2")%> ***</caption>
	<tr>
		<td class="tituloTablaBW tablaConBordeBW">Item</td>
		<td class="tituloTablaBW tablaConBordeBW"><bean:message key="jugada.idJugada"/></td>
		<td class="tituloTablaBW tablaConBordeBW"><bean:message key="jugada.fechaSis"/></td>
		<%if(!usuario.getIdRol().equals(Constants.ROL_JUGADOR)) {%>
		<td class="tituloTablaBW tablaConBordeBW"><bean:message key="jugada.fechaExp"/></td>
		<%}%>
		<td class="tituloTablaBW tablaConBordeBW"><bean:message key="jugada.montoApostado"/></td>
		<td class="tituloTablaBW tablaConBordeBW"><bean:message key="jugada.montoPremio"/></td>
		<td class="tituloTablaBW tablaConBordeBW" ><bean:message key="jugada.montoPagado"/></td>
		<td class="tituloTablaBW tablaConBordeBW"><bean:message key="jugada.idStatusJugada"/></td>
		<%if(viewUser) {%>
		<td class="tituloTablaBW tablaConBordeBW"><bean:message key="jugada.idUsuario"/></td>
		<%}%>
	</tr>	
	<tr>	
		<td colspan="<%=(viewUser?9:8)%>">
			<hr width="100%"/>
		</td>
	</tr>	
	<logic:present name="lista" scope="request">
	<%while(lista.next()) {%>
	<tr>
		<td class="detalleTablaCenterBW"><%= ++cont %></td>
		<td class="detalleTablaCenterBW"><%=Constants.numero(lista.getString("id_jugada"),10)%></td>
		<td class="detalleTablaCenterBW"><%=lista.getString("dia_sis")%> - <%=lista.getString("hora_sis")%></td>
		<%if(!usuario.getIdRol().equals(Constants.ROL_JUGADOR)) {%>
		<td class="detalleTablaCenterBW"><%=lista.getString("dia_exp")%> - <%=lista.getString("hora_exp")%></td>
		<%}%>
		<td class="detalleTablaRightBW"><%=lista.getString("f_monto_apostado")%></td>
		<td class="detalleTablaRightBW"><%=lista.getString("f_monto_premio")%></td>
		<td class="detalleTablaRightBW"><%=lista.getString("f_monto_pagado")%></td>
		<td class="detalleTablaLeftBW">&nbsp;<%=lista.getString("desc_jugada")%></td>
		<%if(viewUser) {%>
		<td class="detalleTablaLeftBW">&nbsp;<%=lista.getString("usuario")%></td>
		<%}%>
	</tr>	
	<% total += lista.getDouble("monto_pagado");
	}%>
	</logic:present>
</table>
<hr width="98%"/>
<div class="detalleTablaCenterBW">Total Monto de las jugadas pagadas para la fecha <%=request.getAttribute("fecha")%> <span id="texto"></span>: <%= Constants.getDominio(request).getMoneda() %> <%= Constants.formatNumber(total) %></div>

