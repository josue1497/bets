<%@ include file="/views/common/taglibs.jsp"%>
<% boolean viewUser = ( usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) || usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA) ); %>
<% CachedRowSet lista = (CachedRowSet)request.getAttribute("lista");  %>
<% CachedRowSet listaJugador = (CachedRowSet)request.getAttribute("listaJugador");  %>
<% int cont=0; %>

<script language="JavaScript" src="<%=basePath%>scripts/Calendario_2.js"></script>

<script language="javascript">
function send(orden) {
	document.forms[0].action="listPlay.do";
	document.forms[0].orden.value=orden;
	document.forms[0].submit();
}
function abrir(jugada) {
	document.forms[0].action="showTicket.do";
	document.forms[0].idJugada.value=jugada;
	document.forms[0].submit();
}

</script>
<form name="forma" action="listPlay.do">
<input type="hidden" name="orden" value="0" />
<input type="hidden" name="idJugada" value="" />
<% double total=0;%>
<table align="center" width="98%" height="100%" >
  <tr>
	<td width="100%" align="left">
	<font color="white">
		<html:errors/>
	</font>
	</td>
  </tr>
</table>
<table align="center" width="98%" height="100%" >

	<tr>
		<td colspan="<%=viewUser?8:7%>">
			<table align="center" width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="40%" align="left">
						<%if(viewUser) {%>
						<table border="0" cellpadding="0" cellspacing="0">
						  <tr>
							<td class="detalleTablaLeft">Jugador :&nbsp;</td>
							<td class="detalleTablaLeft">
								<select name="jugador" style="width:200px">
									<option value="">Todos</option>
									<%while(listaJugador.next()) {%>
									<option value="<%=listaJugador.getString("id_usuario")%>"><%=listaJugador.getString("usuario")%></option>
									<%}%>
								</select>
							</td>
						  </tr>
						</table>
						<%}%>
					</td>
					<td width="20%" class="tituloTablaSup" align="center" >LISTA DE JUGADAS</td>
					<td width="40%" align="right">
						<table height="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr>
							<td class="detalleTablaRight">Fecha :&nbsp;</td>
							<td>
				  			<input type="text" name="fechaJugada" size="10" maxlength="10" value="<%=session.getAttribute("fechaJugada")%>" readOnly />
							</td>
							<td>	
							        <a href="javascript:show_calendar('forms[0].fechaJugada', 'forms[0].fechaJugada', 'forms[0].fechaJugada');" 
									onmouseover="window.status='Seleccione la fecha';return true;" 
									onmouseout="window.status='';return true;">
						            <img src="<%=basePath%>images/calendario.gif" border="0" title="Seleccionar Fecha"></a>
						    </td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						    <td><a class="enlaceBoton" href="#" onclick="document.forms[0].submit()" >Listar</a></td>
						  </tr>
						</table>					
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td class="tituloTabla tablaConBorde">Reg.</td>
		<td class="tituloTabla tablaConBorde" onclick="send(1)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="jugada.idJugada"/></td>
		<td class="tituloTabla tablaConBorde" onclick="send(2)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="jugada.fechaSis"/></td>
		<%if(!usuario.getIdRol().equals(Constants.ROL_JUGADOR)) {%>
		<td class="tituloTabla tablaConBorde" onclick="send(3)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="jugada.fechaExp"/></td>
		<%}%>
		<td class="tituloTabla tablaConBorde" onclick="send(6)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="jugada.montoApostado"/></td>
		<td class="tituloTabla tablaConBorde" onclick="send(7)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="jugada.montoPremio"/></td>
		<td class="tituloTabla tablaConBorde" onclick="send(8)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="jugada.montoPagado"/></td>
		<td class="tituloTabla tablaConBorde" onclick="send(4)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="jugada.idStatusJugada"/></td>
		<%if(viewUser) {%>
		<td class="tituloTabla tablaConBorde" onclick="send(5)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="jugada.idUsuario"/></td>
		<%}%>
	</tr>	
	<logic:present name="lista" scope="request">
	<%while(lista.next()) {%>
	<tr onmouseover="this.className='lineaResaltada'" onmouseout="this.className=''" onclick="abrir('<%=lista.getString("id_jugada")%>')">
		<td class="detalleTablaCenter"><%= ++cont %></td>
		<td class="detalleTablaCenter"><%=Constants.numero(lista.getString("id_jugada"),10)%></td>
		<td class="detalleTablaCenter"><%=lista.getString("dia_sis")%> - <%=lista.getString("hora_sis")%></td>
		<%if(!usuario.getIdRol().equals(Constants.ROL_JUGADOR)) {%>
		<td class="detalleTablaCenter"><%=lista.getString("dia_exp")%> - <%=lista.getString("hora_exp")%></td>
		<%}%>
		<td class="detalleTablaRight"><%=lista.getString("f_monto_apostado")%></td>
		<td class="detalleTablaRight"><%=lista.getString("f_monto_premio")%></td>
		<td class="detalleTablaRight"><%=lista.getString("f_monto_pagado")%></td>
		<td class="detalleTablaLeft <%=Constants.getColorStatusJugada(lista.getString("id_status_jugada"))%>">&nbsp;<%=lista.getString("desc_jugada")%></td>
		<%if(viewUser) {%>
		<td class="detalleTablaLeft">&nbsp;<%=lista.getString("usuario")%></td>
		<%}%>
	</tr>	
	<% 
	   if(!lista.getString("id_status_jugada").equals(Constants.STATUS_JUGADA_ELIMINADA)) {
			total += lista.getDouble("monto_apostado");
	   }
	}%>
	</logic:present>
</table>
<hr width="98%" color="#efefef"/>
<div class="detalleTablaRight">
	<b>TOTAL JUGADAS A LA FECHA <%=session.getAttribute("fechaJugada")%> <span id="texto"></span>: <%= Constants.getDominio(request).getMoneda() %> <%= Constants.formatNumber(total) %></b>
	&nbsp;&nbsp;&nbsp;
</div>
</form>
<script language="javascript">
<%if(viewUser) {%>
	document.forms[0].jugador.value='<%=session.getAttribute("jugador")%>';
	if(document.forms[0].jugador.value!=""){
		document.getElementById("texto").innerHTML="del jugador "+document.forms[0].jugador.options[document.forms[0].jugador.selectedIndex].text;
	}
<%} else {%>
	document.getElementById("texto").innerHTML="del jugador <%=usuario.getUsuario()%>";
<%}%>
</script>

