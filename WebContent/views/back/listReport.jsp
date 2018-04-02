<%@ include file="/views/common/taglibs.jsp"%>
<script language="JavaScript" src="<%=basePath%>scripts/Calendario_2.js"></script>
<script language="javascript">
function send(nForm) {
	with(document.forms[nForm]) {
		window.open(action+"?fecha="+fecha.value+"&fecha2="+fecha2.value+"&jugador="+jugador.value,"_blank","width=700,height=600,status=no,resizable=yes,top=50,left=100,scrollbars=yes");
	}
}
function listado(n) {
	with(document.forms[0]){
	switch(n) {
		case 0:
			action="listReportTicket.do";
			submit();
			break;
		<% if (!usuario.getIdRol().equals(Constants.ROL_JUGADOR)){%>
		case 1:
			action="listReportApuesta.do";
			submit();
			break;
		<%}%>
		case 3:
			action="listReportResultado.do";
			submit();
			break;
		<% if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) || usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA) || usuario.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA)){%>
		case 4:
			action="listReportApuestaPorEquipo.do";
			submit();
			break;
		<%}%>
		<% if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {%>
		case 5:
			action="listReportResultadoVendedor.do";
			submit();
			break;
		<%}%>
	}
	}
}
</script>
<form action="">
<table align="center" width="60%" cellpadding="5" cellspacing="5" class="detalleTablaLeft" class="none" style="background-image:url(images/fondoReporte.jsp);background-repeat:no-repeat;">
	<tr>
		<td>
<table align="center" width="70%" cellpadding="15" cellspacing="5" class="tablaCaption"  border="1" >
			<caption class="tituloCaption">Reportes</caption>
<tr>
	<td>
		<table align="center" width="70%" cellpadding="15" cellspacing="5"  border="0" >
			<tr>
				<td onclick="listado(0)" onmouseover="this.className='detalleTablaLeft tablaConBorde'" onmouseout="this.className='detalleTablaLeft'">
					<table border="0" cellpadding="5">
						<tr>
							<td>
								<img src="images/hoja.gif"/>
							</td>
							<td>
								Listado de Tickets
							</td>
						</tr>
					</table> 
				</td>
			</tr>
			<% if (!usuario.getIdRol().equals(Constants.ROL_JUGADOR)){%>
			<tr>
				<td onclick="listado(1)" onmouseover="this.className='detalleTablaLeft tablaConBorde'" onmouseout="this.className='detalleTablaLeft'">
					<table border="0" cellpadding="5">
						<tr>
							<td>
								<img src="images/hoja.gif"/>
							</td>
							<td>
								Resumen de Tickets por Fecha
							</td>
						</tr>
					</table> 
				</td>
			</tr>
			<%}%>
			<tr>
				<td onclick="listado(3)" onmouseover="this.className='detalleTablaLeft tablaConBorde'" onmouseout="this.className='detalleTablaLeft'">
					<table border="0" cellpadding="5">
						<tr>
							<td>
								<img src="images/hoja.gif"/>
							</td>
							<td>
								Resultados por Fecha
							</td>
						</tr>
					</table> 
				</td>
			</tr>
			<% if (usuario.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR) || usuario.getApuestaEquipo().equals("1")){%>
			<tr>
				<td onclick="listado(4)" onmouseover="this.className='detalleTablaLeft tablaConBorde'" onmouseout="this.className='detalleTablaLeft'">
					<table border="0" cellpadding="5">
						<tr>
							<td>
								<img src="images/hoja.gif"/>
							</td>
							<td>
								Apuestas por Equipo
							</td>
						</tr>
					</table> 
				</td>
			</tr>
			<%}%>
			<% if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)){%>
			<tr>
				<td onclick="listado(5)" onmouseover="this.className='detalleTablaLeft tablaConBorde'" onmouseout="this.className='detalleTablaLeft'">
					<table border="0" cellpadding="5">
						<tr>
							<td>
								<img src="images/hoja.gif"/>
							</td>
							<td>
								Resultado Vendedores
							</td>
						</tr>
					</table> 
				</td>
			</tr>
			<%}%>
		</table>
	</td>
</tr>
</table>
<script language="javascript">
	
	//listado(0);
</script>







<%--
<table align="center" width="40%" cellpadding="10" cellspacing="5" >
	<tr>
		<td>
	  		<fieldset>
	  		<form action="listSellDay.do" method="POST">
  			<legend class="tituloTabla">&nbsp;Listado de Jugadas del D&iacute;a&nbsp;</legend>
			<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="5">
				<tr>
					<td class="detalleTablaLeft"  width="50%">
						&nbsp;&nbsp;&nbsp;fecha Desde :
					</td>
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
				<tr>
					<td class="detalleTablaLeft"  width="50%">
						&nbsp;&nbsp;&nbsp;fecha Hasta :
					</td>
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
				<tr style="display:<%=usuario.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA)?"none":""%>">
					<td class="detalleTablaLeft"  width="50%">
						&nbsp;&nbsp;&nbsp;Seleccione el Jugador : 
					</td>
					<td colspan="2">
						<select name="jugador" style="width:150px">
							<option value="">Todos</option>
							<%while(listaJugador.next()) {%>
							<option value="<%=listaJugador.getString("id_usuario")%>"><%=listaJugador.getString("usuario")%></option>
							<%}%>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="3">
						<a class="enlaceBoton" href="#" onclick="send(0)" >Ver Reporte</a>
					</td>
				</tr>
			</table>
			</form>
			</fielset>
		</td>
	<tr>
	<tr>
		<td >
	  		<fieldset>
	  		<form action="listPayDay.do" method="POST">
  			<legend class="tituloTabla">&nbsp;Listado de Pagos del D&iacute;a&nbsp;</legend>
			<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="5">
				<tr>
					<td class="detalleTablaLeft"  width="50%">
						&nbsp;&nbsp;&nbsp;fecha Desde :
					</td>
					<td width="10%">
			  			<input type="text" name="fecha" 
			  					value="<%=request.getAttribute("fecha")%>"
								maxlength="10" size="12" 
								onblur="DateFormat(this,this.value,event,true,'3')"  
								onkeyup="DateFormat(this,this.value,event,false,'3')" onfocus="javascript:vDateType='3'" />
					</td>
					<td width="10%" align="left">
				        <a href="javascript:show_calendar('forms[1].fecha', 'forms[1].fecha', 'forms[1].fecha');" 
						onmouseover="window.status='Seleccione la fecha';return true;" 
						onmouseout="window.status='';return true;">
			            <img src="<%=basePath%>images/calendario.gif" border="0" title="Seleccionar Fecha"></a>
					</td>
				</tr>
				<tr>
					<td class="detalleTablaLeft"  width="50%">
						&nbsp;&nbsp;&nbsp;fecha Hasta :
					</td>
					<td width="10%">
			  			<input type="text" name="fecha2" 
			  					value="<%=request.getAttribute("fecha2")%>"
								maxlength="10" size="12" 
								onblur="DateFormat(this,this.value,event,true,'3')"  
								onkeyup="DateFormat(this,this.value,event,false,'3')" onfocus="javascript:vDateType='3'" />
					</td>
					<td width="10%" align="left">
				        <a href="javascript:show_calendar('forms[1].fecha', 'forms[1].fecha', 'forms[1].fecha');" 
						onmouseover="window.status='Seleccione la fecha';return true;" 
						onmouseout="window.status='';return true;">
			            <img src="<%=basePath%>images/calendario.gif" border="0" title="Seleccionar Fecha"></a>
					</td>
				</tr>
				<tr style="display:<%=usuario.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA)?"none":""%>">
					<td class="detalleTablaLeft"  width="50%">
						&nbsp;&nbsp;&nbsp;Seleccione el Jugador : 
					</td>
					<td colspan="2">
						<select name="jugador" style="width:150px">
							<option value="">Todos</option>
							<%listaJugador.beforeFirst();
							while(listaJugador.next()) {%>
							<option value="<%=listaJugador.getString("id_usuario")%>"><%=listaJugador.getString("usuario")%></option>
							<%}%>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="3">
						<a class="enlaceBoton" href="#" onclick="send(1)" >Ver Reporte</a>
					</td>
				</tr>
			</table>
			</form>
			</fielset>
		</td>
	<tr>
</table>
		</td>
	<tr>
</table>
</form>
--%>
