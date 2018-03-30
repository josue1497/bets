<%@ include file="/views/common/taglibs.jsp"%>
<%
	boolean viewUser = (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) || usuario.getIdRol().equals(
			Constants.ROL_ADMINISTRADOR_DE_TAQUILLA));
%>
<%
	CachedRowSet lista = (CachedRowSet) request.getAttribute("lista");
%>
<%
	CachedRowSet listaJugador = (CachedRowSet) request.getAttribute("listaJugador");
%>
<%
	int cont = 0;
%>

<script language="JavaScript" src="<%=basePath%>scripts/Calendario_2.js"></script>

<script language="javascript">
function send(orden) {
	document.forms[0].action="listPlay.do";
	document.forms[0].orden.value=orden;
	document.forms[0].submit();
}
function abrir(jugada) {
	//document.forms[0].action="showTicket.do";
	//document.forms[0].idJugada.value=jugada;
	//document.forms[0].target="_blank";
	//document.forms[0].submit();
	window.open("showTicketSimple.do?idJugada="+jugada+"&titulo=Ticket&ancho=100","_blank","width=500,height=500,status=no,resizable=yes,top=50,left=100,scrollbars=no");
}

</script>
<form name="forma" action="listPlay.do">
	<input type="hidden" name="orden" value="0" /> <input type="hidden"
		name="idJugada" value="" />
	<%
		double total1 = 0;
		double total2 = 0;
		double total3 = 0;
		double totalBs =0;
		
		double total1Pesos = 0;
		double total2Pesos = 0;
		double total3Pesos = 0;
		double totalPesos =0;
	%>
	<table align="center" width="98%" height="100%">
		<tr>
			<td width="100%" align="left"><font color="white"> <html:errors />
			</font></td>
		</tr>
	</table>
	<table align="center" width="98%" height="100%">

		<tr>
			<td colspan="<%=viewUser ? 9 : 8%>">
				<table align="center" width="100%" height="100%" border="0"
					cellspacing="0" cellpadding="0">
					<tr>
						<td align="left">
							<%
								if (viewUser) {
							%>
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td class="detalleTablaLeft">Jugador :&nbsp;</td>
									<td class="detalleTablaLeft"><select name="jugador"
										style="width: 200px">
											<option value="">Todos</option>
											<%
												while (listaJugador.next()) {
											%>
											<option value="<%=listaJugador.getString("id_usuario")%>"><%=listaJugador.getString("usuario")%></option>
											<%
												}
											%>
									</select></td>
								</tr>
							</table> <%
 	}
 %>
						</td>
						<td>&nbsp;</td>
						<td align="left">
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td class="detalleTablaLeft">Estado :&nbsp;</td>
									<td class="detalleTablaLeft"><select name="estado"
										style="width: 100px">
											<option value="">Todos</option>
											<option value="1">En Juego</option>
											<option value="2">Eliminada</option>
											<option value="3">Pendiente</option>
											<option value="4">Pagada</option>
											<option value="5">Vencida</option>
											<option value="6">Suspendida</option>
											<option value="9">Perdedor</option>
									</select></td>
								</tr>
							</table>
						</td>
						<td width="20%" class="tituloTablaSup" align="center">LISTA
							DE JUGADAS</td>
						<td width="40%" align="right">
							<table height="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td class="detalleTablaRight">Fecha :&nbsp;</td>
									<td><input type="text" name="fechaJugada" size="10"
										maxlength="10"
										value="<%=session.getAttribute("fechaJugada")%>" /></td>
									<td><a
										href="javascript:show_calendar('forms[0].fechaJugada', 'forms[0].fechaJugada', 'forms[0].fechaJugada');"
										onmouseover="window.status='Seleccione la fecha';return true;"
										onmouseout="window.status='';return true;"> <img
											src="<%=basePath%>images/calendario.gif" border="0"
											title="Seleccionar Fecha"></a></td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td><a class="enlaceBoton" href="#"
										onclick="document.forms[0].submit()">Listar</a></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="tituloTabla tablaConBorde">Reg.</td>
			<td class="tituloTabla tablaConBorde" onclick="send(1)"
				onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'"
				onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message
					key="jugada.idJugada" /></td>
			<td class="tituloTabla tablaConBorde" onclick="send(2)"
				onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'"
				onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message
					key="jugada.fechaSis" /></td>
			<%
				if (!usuario.getIdRol().equals(Constants.ROL_JUGADOR)) {
			%>
			<td class="tituloTabla tablaConBorde" onclick="send(3)"
				onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'"
				onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message
					key="jugada.fechaExp" /></td>
			<%
				}
			%>
			<td class="tituloTabla tablaConBorde"
				onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'"
				onmouseout="this.className='tituloTabla tablaConBorde'">Fecha
				Pago</td>
			<td class="tituloTabla tablaConBorde" onclick="send(6)"
				onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'"
				onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message
					key="jugada.montoApostado" /></td>
			<td class="tituloTabla tablaConBorde" onclick="send(7)"
				onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'"
				onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message
					key="jugada.montoPremio" /></td>
			<td class="tituloTabla tablaConBorde" onclick="send(8)"
				onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'"
				onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message
					key="jugada.montoPagado" /></td>
			<td class="tituloTabla tablaConBorde" onclick="send(4)"
				onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'"
				onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message
					key="jugada.idStatusJugada" /></td>
			<%
				if (viewUser) {
			%>
			<td class="tituloTabla tablaConBorde" onclick="send(5)"
				onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'"
				onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message
					key="jugada.idUsuario" /></td>
			<%
				}
			%>
		</tr>
		<logic:present name="lista" scope="request">
			<pg:paging url='<%=basePath + "listPlay.do"%>' pageSize='200'
				indexSize="30">
				<%
					while (lista.next()) {
				%>
				<pg:item>
					<tr onmouseover="this.className='lineaResaltada'"
						onmouseout="this.className=''"
						onclick="abrir('<%=lista.getString("id_jugada")%>')">
						<td class="detalleTablaCenter"><%=++cont%></td>
						<td class="detalleTablaCenter"><%=Constants.numero(lista.getString("id_jugada"), 10)%></td>
						<td class="detalleTablaCenter"><%=lista.getString("dia_sis")%>
							- <%=lista.getString("hora_sis")%></td>
						<%
							if (!usuario.getIdRol().equals(Constants.ROL_JUGADOR)) {
						%>
						<td class="detalleTablaCenter"><%=lista.getString("dia_exp")%>
							- <%=lista.getString("hora_exp")%></td>
						<%
							}
						%>
						<td class="detalleTablaCenter"><%=lista.getString("dia_pago")%>
							- <%=lista.getString("hora_pago")%></td>
						<td class="detalleTablaRight"><%=lista.getString("f_monto_apostado")%></td>
						<td class="detalleTablaRight"><%=("1".indexOf(lista.getString("id_status_jugada")) != -1
									? lista.getString("f_monto_premio")
									: ("3_6_".indexOf(lista.getString("id_status_jugada")) != -1 ? lista
											.getString("f_monto_pagado") : ""))%></td>
						<td class="detalleTablaRight"><%=("4".indexOf(lista.getString("id_status_jugada")) != -1 ? lista
									.getString("f_monto_pagado") : "")%></td>
						<td
							class="detalleTablaLeft <%=Constants.getColorStatusJugada(lista.getString("id_status_jugada"))%>">&nbsp;
							<%=lista.getString("desc_jugada")%><%=Integer.parseInt(lista.getString("bono")) > 0 ? " (BONO)" : ""%>
						</td>
						<%
							if (viewUser) {
						%>
						<td class="detalleTablaLeft">&nbsp;<%=lista.getString("usuario")%></td>
						<%
							}
						%>
					</tr>
					<%
						String moneda = lista.getString("moneda");
					
										if (!lista.getString("id_status_jugada").equals(Constants.STATUS_JUGADA_ELIMINADA)) {

											if (moneda.equalsIgnoreCase("Bs.")) {
												total1 += lista.getDouble("monto_apostado");
											} else {
												total1Pesos += lista.getDouble("monto_apostado");
											}

											if ("1".indexOf(lista.getString("id_status_jugada")) != -1) {

												if (moneda.equalsIgnoreCase("Bs.")) {
													total2 += lista.getDouble("monto_premio");
												} else {
													total2Pesos += lista.getDouble("monto_premio");
												}
												//total2 += lista.getDouble("monto_premio");
											}
											if ("3_6".indexOf(lista.getString("id_status_jugada")) != -1) {
												if (moneda.equalsIgnoreCase("Bs.")) {
													total2 += lista.getDouble("monto_pagado");
												} else {
													total2Pesos += lista.getDouble("monto_pagado");
												}
												//total2 += lista.getDouble("monto_pagado");
											}
											if (lista.getString("id_status_jugada").equals("4")) {
												if (moneda.equalsIgnoreCase("Bs.")) {
													total3 += lista.getDouble("monto_pagado");
												} else {
													total3Pesos += lista.getDouble("monto_pagado");
												}
											//	total3 += lista.getDouble("monto_pagado");
											}
										}
					%>
				</pg:item>
				<%
					}
				totalPesos = total1Pesos + total2Pesos + total3Pesos;
				totalBs = total1 + total2 + total3;
				
				%>
				<tr>
					<td colspan="<%=viewUser ? 9 : 8%>">
						<table align="center" cellpadding="4" border="0" width="100%">
							<tr align="center">
								<td
									style="font-family: Sans-serif, Helvetica, Arial, Verdana; color: #fff; font-size: 13px;">
									<pg:index title="Ir a la pagina ">
										<pg:prevPages><<</pg:prevPages>
										<pg:page><%=thisPage%></pg:page>
										<pg:nextPages>>></pg:nextPages>
									</pg:index>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</pg:paging>
		</logic:present>
	</table>
	<hr width="98%" color="#efefef" />
	<div class="detalleTablaRight">
		<table width="50%" align="center">
			<tr>
				<td style="text-align: center;">MONTO JUGADO</td>
				<td style="text-align: center;">MONTO PAGAR</td>
				<td style="text-align: center;">MONTO CANCELADO</td>
			</tr>
			
			<%if (totalBs>0){ %>
			
			
			<tr>
				<td style="text-align: center;">Bs. <%=Constants.formatNumber(total1)%></td>
				<td style="text-align: center;">Bs. <%=Constants.formatNumber(total2)%></td>
				<td style="text-align: center;">Bs. <%=Constants.formatNumber(total3)%></td>
			</tr>
			<%} 
			if (totalPesos>0){ %>
			
			<tr>
				<td style="text-align: center;">Pesos <%=Constants.formatNumber(total1Pesos)%></td>
				<td style="text-align: center;">Pesos <%=Constants.formatNumber(total2Pesos)%></td>
				<td style="text-align: center;">Pesos <%=Constants.formatNumber(total3Pesos)%></td>
			</tr>
			<%}%>
		</table>
		<%--	<b>TOTALES A LA FECHA <%=session.getAttribute("fechaJugada")%>: </b> --%>
	</div>
</form>
<script language="javascript">
document.forms[0].estado.value='<%=session.getAttribute("estado")%>';
<%if (viewUser) {%>
	document.forms[0].jugador.value='<%=session.getAttribute("jugador")%>
	';
<%}%>
	
</script>

