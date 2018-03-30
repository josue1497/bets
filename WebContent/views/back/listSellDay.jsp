<%@ include file="/views/common/taglibs.jsp"%>
<%@ include file="/views/common/meta.jsp"%>
<% boolean viewUser = ( usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) || usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA) ); %>
<% CachedRowSet lista = (CachedRowSet)request.getAttribute("lista");  %>
<% CachedRowSet listaVencida = (CachedRowSet)request.getAttribute("listaVencida");  %>
<% String resumido = ( String.valueOf(request.getAttribute("resumido")).equals("1") ? "none" : "" );  %>
<% int cont=0; %>

<script language="JavaScript" src="<%=basePath%>scripts/Calendario_2.js"></script>
<style>
.rojo {
	color:red;
}
</style>
<% double t1=0;
   double t2=0;
   double t3=0;
   double t4=0;
   double t5=0;
   double vencido=0;
   double comisionVenta=0;
   double comisionGanancia=0;
   double subTotal = 0;
%>
<fieldset>
<table align="center" width="100%" cellspacing="3" cellpadding="0">
	<tr>
		<td class="tituloTablaLeftBW" >LISTADO DE TICKETS HIPICOS</td>
		<td class="tituloTablaRigthBW" width="10%">Fecha :</td>
		<td class="tituloTablaLeftBW" width="10%"><%=Constants.getFechaCorta()%></td>
	</tr>
	<tr>
		<td class="tituloTablaLeftBW" >Desde <%=request.getAttribute("fecha")%> Hasta <%=request.getAttribute("fecha2")%> - ( STATUS : <%=request.getAttribute("status")%> )</td>
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
	<% 
	   if(!lista.getString("id_status_jugada").equals(Constants.STATUS_JUGADA_ELIMINADA)) {
		   t1 += lista.getDouble("monto_apostado");
		   if(lista.getString("id_status_jugada").equals(Constants.STATUS_JUGADA_SUSPENDIDA)) {
		   		t3 += lista.getDouble("monto_apostado");
		   } else {
			   t2 += lista.getDouble("monto_pagado");
		   }
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


	<logic:present name="listaVencida" scope="request">
	<!-- VENCIDAS -->
	<%if(request.getAttribute("status").toString().toLowerCase().equals("todos")){
		while(listaVencida.next()) {%>
		<tr>
			<%++cont;%>
			<td class="detalleTablaCenterBW top rojo" ><%=Constants.numero(listaVencida.getString("id_jugada"),10)%></td>
			<td class="detalleTablaCenterBW top rojo"><%=listaVencida.getString("dia_sis")%></td>
			<td class="detalleTablaLeftBW top rojo" style="width:300px"><%=Constants.isNull(listaVencida.getString("detalle_equipo"),"")%></td>
			<td class="detalleTablaRightBW top rojo"><%=Constants.formatNumber(listaVencida.getString("monto_apostado"))%></td>
			<td class="detalleTablaRightBW top rojo"><%=Constants.formatNumber(listaVencida.getString("monto_premio"))%></td>
			<td class="detalleTablaRightBW top rojo"><%=Constants.formatNumber(listaVencida.getString("monto_pagado"))%></td>
			<td class="detalleTablaLeftBW top rojo">&nbsp;<%=listaVencida.getString("desc_jugada")%></td>
			<%if(viewUser) {%>
			<td class="detalleTablaLeftBW top rojo">&nbsp;<%=listaVencida.getString("usuario")%></td>
			<%}%>
		</tr>	
		<% 
		   vencido += listaVencida.getDouble("monto_pagado");
		}
	}%>
	
	</logic:present>

</table>
<hr width="98%"/>
<%
comisionVenta = Double.parseDouble(usuario.getComisionVenta());
comisionGanancia = Double.parseDouble(usuario.getOtrosGastos());

subTotal = (usuario.getIdRol().equals(Constants.ROL_JUGADOR)?-1:1)*(t1-t3-t2);

t4=t1*(comisionVenta/100);
if(subTotal>0 ){
	t5=(subTotal-t4)*(comisionGanancia/100);
}

%>
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
		<td class="detalleTablaRightBW" >S u b - t o t a l :&nbsp;</td>
		<td class="detalleTablaRightBW" width="10%"><%= Constants.formatNumber(subTotal) %></td>
	</tr>
	<tr>
 		<td class="detalleTablaRightBW" >% de Venta (%<%=comisionVenta%>):&nbsp;</td>
		<td class="detalleTablaRightBW" width="10%"><%= Constants.formatNumber(t4)%></td>
	</tr>
	<tr style="display:<%=t5!=0?"":"none"%>">
 		<td class="detalleTablaRightBW" >C o m i s i o n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;d e&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;l a&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;g a n a n c i a (%<%=comisionGanancia%>):&nbsp;</td>
		<td class="detalleTablaRightBW" width="10%"><%= Constants.formatNumber(t5)%></td>
	</tr>
	<tr >
 		<td class="detalleTablaRightBW" >P a g o s&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;V e n c i d o s :&nbsp;</td>
		<td class="detalleTablaRightBW" ><%= Constants.formatNumber(vencido)%></td>
	</tr>
	<tr >
 		<td class="detalleTablaRightBW" >B a l a n c e&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;t o t a l :&nbsp;</td>
		<td class="detalleTablaRightBW" width="10%"><%= Constants.formatNumber((subTotal-t4-t5)+vencido)%></td>
	</tr>
</table>
</fieldset>

