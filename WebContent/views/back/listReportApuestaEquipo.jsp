<%@ include file="/views/common/taglibs.jsp"%>
<%@ include file="/views/common/meta.jsp"%>
<% boolean viewUser = ( usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) || usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA) ); %>
<% CachedRowSet lista = (CachedRowSet)request.getAttribute("lista");  %>
<% String resumido = ( String.valueOf(request.getAttribute("resumido")).equals("1") ? "none" : "" );  %>
<% int cont=0; %>

<script language="JavaScript" src="<%=basePath%>scripts/Calendario_2.js"></script>

<% double t1=0;
   double t2=0;
   double t3=0;
%>
<fieldset>
<table align="center" width="100%" cellspacing="3" cellpadding="0">
	<tr>
		<td class="tituloTablaLeftBW" >LISTADO DE USUARIOS</td>
		<td class="tituloTablaRigthBW" width="10%">Fecha :</td>
		<td class="tituloTablaLeftBW" width="10%"><%=Constants.getFechaCorta()%></td>
	</tr>
	<tr>
		<td class="tituloTablaLeftBW" >STATUS : <%=request.getAttribute("status")%></td>
		<td class="tituloTablaRigthBW" width="10%">Hora :</td>
		<td class="tituloTablaLeftBW" width="10%"><%=Constants.getHora()%></td>
	</tr>
</table>
</fieldset>
<table align="center" width="98%" cellspacing="0" cellpadding="0" style="display:<%=resumido%>">
	<tr>
		<td class="tituloTablaBW tablaConBordeBW">TICKET</td>
		<td class="tituloTablaBW tablaConBordeBW">FECHA</td>
		<td class="tituloTablaBW tablaConBordeBW">JUGADAS</td>
		<td class="tituloTablaBW tablaConBordeBW"><bean:message key="jugada.montoApostado"/></td>
		<td class="tituloTablaBW tablaConBordeBW"><bean:message key="jugada.montoPremio"/></td>
		<td class="tituloTablaBW tablaConBordeBW" ><bean:message key="jugada.montoPagado"/></td>
		<td class="tituloTablaBW tablaConBordeBW">ESTATUS</td>
		<%if(viewUser) {%>
		<td class="tituloTablaBW tablaConBordeBW">JUGADOR</td>
		<%}%>
	</tr>	
	<tr>	
		<td colspan="<%=(viewUser?8:7)%>">
			<hr width="100%"/>
		</td>
	</tr>	
	<logic:present name="lista" scope="request">
	<%while(lista.next()) {%>
	<tr>
		<%++cont;%>
		<td class="detalleTablaCenterBW top" ><%=Constants.numero(lista.getString("id_jugada"),10)%></td>
		<td class="detalleTablaCenterBW top"><%=lista.getString("dia_sis")%></td>
		<td class="detalleTablaLeftBW top" style="width:200px"><%=Constants.isNull(lista.getString("detalle_equipo"),"")%></td>
		<td class="detalleTablaRightBW top"><%=lista.getString("f_monto_apostado")%></td>
		<td class="detalleTablaRightBW top"><%=lista.getString("f_monto_premio")%></td>
		<td class="detalleTablaRightBW top"><%=lista.getString("f_monto_pagado")%></td>
		<td class="detalleTablaLeftBW top">&nbsp;<%=lista.getString("desc_jugada")%></td>
		<%if(viewUser) {%>
		<td class="detalleTablaLeftBW top">&nbsp;<%=lista.getString("usuario")%></td>
		<%}%>
	</tr>	
	<% t1 += lista.getDouble("monto_apostado");
	   if(lista.getString("id_status_jugada").equals(Constants.STATUS_JUGADA_SUSPENDIDA)) {
	   		t3 += lista.getDouble("monto_apostado");
	   } else {
		   t2 += lista.getDouble("monto_pagado");
	   }
	}%>
	<!--
	<tr>	
		<td colspan="<%=(viewUser?8:7)%>">
			<hr width="100%"/>
		</td>
	</tr>	
	<tr>
		<td class="detalleTablaCenterBW">TOTALES</td> 
		<td class="detalleTablaCenterBW">&nbsp;</td>
		<td class="detalleTablaCenterBW">&nbsp;</td>
		<td class="detalleTablaRightBW"><%= Constants.formatNumber(t1) %></td>
		<td class="detalleTablaRightBW">&nbsp;</td>
		<td class="detalleTablaRightBW"><%= Constants.formatNumber(t2) %></td>
		<td class="detalleTablaLeftBW">&nbsp;</td>
		<%if(viewUser) {%>
		<td class="detalleTablaLeftBW">&nbsp;</td>
		<%}%>
	</tr>	
	-->
	
	</logic:present>
</table>
<hr width="98%"/>
<fieldset>
<legend class="tituloTablaBW">TOTALES</legend>
<table align="center" width="90%" cellspacing="3" cellpadding="0">
	<tr>
		<td class="detalleTablaRightBW" >M o n t o&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;J u g a d o :&nbsp;</td>
		<td class="detalleTablaRightBW" width="10%"><%= Constants.formatNumber(t1) %></td>
	</tr>
	<tr>
		<td class="detalleTablaRightBW" >D e v o l u c i &oacute; n :&nbsp;</td>
		<td class="detalleTablaRightBW" width="10%"><%= Constants.formatNumber(t3) %></td>
	</tr>
	<tr>
		<td class="detalleTablaRightBW" >G a n a d o r e s :&nbsp;</td>
		<td class="detalleTablaRightBW" width="10%"><%= Constants.formatNumber(t2) %></td>
	</tr>
	<tr>
		<td class="detalleTablaRightBW" >B a l a n c e :&nbsp;</td>
		<td class="detalleTablaRightBW" width="10%"><%= Constants.formatNumber(t1-t3-t2) %></td>
	</tr>
</table>
</fieldset>

