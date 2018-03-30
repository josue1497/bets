<%@ include file="/views/common/taglibs.jsp"%>
<% CachedRowSet listaJugador = (CachedRowSet)request.getAttribute("listaJugador");  %>
<% CachedRowSet listaStatusJugada = (CachedRowSet)request.getAttribute("listaStatusJugada");  %>
<% CachedRowSet listaTaquilla = (CachedRowSet)request.getAttribute("listaTaquilla");  %>
<script language="JavaScript" src="<%=basePath%>scripts/Calendario_2.js"></script>
<script language="javascript">
function send() {
	with(document.forms[0]) {
		window.open(action+"?fecha="+fecha.value+"&fecha2="+fecha2.value+"&jugador="+jugador.value+"&jugador2="+jugador2.value+"&status="+status.value+"&resumido="+resumido.value+"&taquilla="+taquilla.value,"_blank","width=800,height=600,status=no,resizable=yes,top=50,left=100,scrollbars=yes");
	}
}

</script>
<table align="center" width="80%" cellpadding="10" cellspacing="5">
	<tr>
		<td>
	  		<form action="listSellDay.do" method="POST">
			<table width="100%" height="100%" border="0" cellpadding="5" cellspacing="5" class="tablaCaption">
				<caption class="tituloCaption">Listado de Tickets</caption>
				<tr>
					<td class="detalleTablaLeft"  width="33%">
						&nbsp;
					</td>
					<td class="tituloTablaLeft" width="33%">
						DESDE
					</td>
					<td class="tituloTablaLeft" width="33%">
						HASTA
					</td>
				</tr>
			
				<tr>
					<td class="detalleTablaLeft">
						Fecha de Emision... 
					</td>
					<td>
						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="10%">
						  			<input type="text" name="fecha" 
						  					value="<%=request.getAttribute("fecha")%>"
											maxlength="10" size="12" 
											onblur="DateFormat(this,this.value,event,true,'3')"  
											onkeyup="DateFormat(this,this.value,event,false,'3')" onfocus="javascript:vDateType='3'" />
								</td>
								<td width="10%" align="left">
							        <a href="javascript:show_calendar('forms[0].fecha', 'forms[0].fecha', 'forms[0].fecha');" 
									onmouseover="window.status='Seleccione la fecha';return true;" 
									onmouseout="window.status='';return true;">
						            <img src="<%=basePath%>images/calendario.gif" border="0" title="Seleccionar Fecha"></a>
								</td>
							</tr>
						</table>
					</td>
					<td>
						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="10%">
						  			<input type="text" name="fecha2" 
						  					value="<%=request.getAttribute("fecha2")%>"
											maxlength="10" size="12" 
											onblur="DateFormat(this,this.value,event,true,'3')"  
											onkeyup="DateFormat(this,this.value,event,false,'3')" onfocus="javascript:vDateType='3'" />
								</td>
								<td width="10%" align="left">
							        <a href="javascript:show_calendar('forms[0].fecha2', 'forms[0].fecha2', 'forms[0].fecha2');" 
									onmouseover="window.status='Seleccione la fecha';return true;" 
									onmouseout="window.status='';return true;">
						            <img src="<%=basePath%>images/calendario.gif" border="0" title="Seleccionar Fecha"></a>
								</td>
							</tr>
						</table>
					</td>
				</tr>

				<tr style="display:<%=usuario.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA) || usuario.getIdRol().equals(Constants.ROL_JUGADOR)?"none":""%>">
					<td class="detalleTablaLeft">
						Jugador...
					</td>
					<td>
						<select name="jugador" style="width:150px">
							<option value="">Todos</option>
							<%listaJugador.beforeFirst();
							while(listaJugador.next()) {%>
							<option value="<%=listaJugador.getString("id_usuario")%>"><%=listaJugador.getString("usuario")%></option>
							<%}%>
						</select>
					</td>
					<td>
						<select name="jugador2" style="width:150px;display:none;">
							<option value="">Todos</option>
							<%listaJugador.beforeFirst();
							while(listaJugador.next()) {%>
							<option value="<%=listaJugador.getString("id_usuario")%>"><%=listaJugador.getString("usuario")%></option>
							<%}%>
						</select>
					</td>
				</tr>
				
				<tr style="display:<%=!usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)?"none":""%>">
					<td class="detalleTablaLeft">
						Taquilla...
					</td>
					<td colspan="2">
						<select name="taquilla" style="width:425px">
							<option value="">Todos</option>
							<%listaTaquilla.beforeFirst();
							while(listaTaquilla.next()) {%>
							<option value="<%=listaTaquilla.getString("id_usuario")%>"><%=listaTaquilla.getString("usuario")%></option>
							<%}%>
						</select>
					</td>
				</tr>

				<tr >
					<td class="detalleTablaLeft">
						Status...
					</td>
					<td>
						<select name="status" style="width:150px">
							<option value="">Todos</option>
							<%listaStatusJugada.beforeFirst();
							while(listaStatusJugada.next()) {%>
							<option value="<%=listaStatusJugada.getString("id_status_jugada")%>"><%=listaStatusJugada.getString("desc_jugada")%></option>
							<%}%>
						</select>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				
				<tr >
					<td class="detalleTablaLeft">
						Resumido...
					</td>
					<td>
						<select name="resumido" style="width:150px">
							<option value="0">No</option>
							<option value="1">Si</option>
						</select>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>

				<tr>
					<td align="center" colspan="3">
						<a class="enlaceBoton" href="#" onclick="send()" >Ver Reporte</a>
					</td>
				</tr>
			</table>
			</form>
		</td>
	<tr>
</table>
