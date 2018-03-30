<%@ include file="/views/common/taglibs.jsp"%>
<% CachedRowSet listaStatus = (CachedRowSet)request.getAttribute("listaStatus"); %>
<% CachedRowSet listaDeporte = (CachedRowSet)request.getAttribute("listaDeporte"); %> 
<form name="forma" method="POST" action="listGame.do">

<table align="center" width="989" height="100%" cellpadding="3" cellspacing="3" >
<caption class="tituloTablaSup"></caption>
	<tr>
		<td class="tituloTablaSup tablaConBorde" width="75%" rowspan="2">
		Deportes
			<table align="center" width="98%" height="100%" >
				<tr>
				<%while(listaDeporte.next()) {
					if( !listaDeporte.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL_5) && !listaDeporte.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL_1) && !listaDeporte.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL_0) && !listaDeporte.getString("id_deporte").equals(Constants.ID_EQUIPO_BASKETBALL_MITAD) ) {%>
					<td class="tituloTabla tablaConBorde" onmouseover="this.className='lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"
					onclick="reporte('<%=listaDeporte.getString("id_deporte")%>','<%=listaDeporte.getString("desc_deporte")%>')"
					>
						<%=listaDeporte.getString("desc_deporte")%>
					</td>
				  <%}
				}%>
				</tr>
			</table>
		</td>
	</tr>	
</table>

<table align="center" width="98%" height="100%" >
<caption class="tituloTabla">LISTA DE JUEGOS ABIERTOS</caption>
	<logic:present name="lista" scope="request">
	<% CachedRowSet lista = (CachedRowSet)request.getAttribute("lista");  
	String idJuego = null;
	String titulo = null;
	String uo = Constants.ALTA;
	String estilo="";
	while(lista.next()) {
		estilo="borderTopBlack";
		if(idJuego==null || !idJuego.equals(lista.getString("id_juego"))) {
			uo=Constants.ALTA;
			idJuego=lista.getString("id_juego");
			
			if(titulo==null || !titulo.equals(lista.getString("dia").concat(lista.getString("desc_liga")))) {%>
				<tr>
					<td class="detalleTablaLeftTitulo borderWhite">
						<table align="center" width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="60%" >&nbsp;<%=lista.getString("desc_liga")%> (<%=lista.getString("iniciales")%>) - <%=lista.getString("dia")%></td>
								<td width="20%" >&nbsp;</td>
								<td width="20%" align="right">&nbsp;</td>
								<td width="20%" align="right">&nbsp;</td>
							</tr>
						</table>
					</td>
				</tr>
			<%} else {
			estilo="borderTopWhite";
				%>
<!-- 
				<tr style="font-size: xx-small">
					<td style="font-size: xx-small">
						<hr width="100%" color="#efefef" style="height:1px"/>
					</td>
				</tr>
 -->							
			<%}
			titulo = lista.getString("dia").concat(lista.getString("desc_liga"));
		}%>
	<tr class="detalleTablaLeftEquipo" >
		<td colspan="2" onmouseover="this.className='lineaResaltada'" onmouseout="this.className=''" >
			<table align="center" width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class="<%=estilo%>">
				<%if(uo.equals(Constants.ALTA)){%>
	 				<tr class="detalleTablaLeftEquipo">
						<td width="4%">&nbsp;</td>
						<td width="3%">&nbsp;</td>
						<td width="3%">&nbsp;</td>
						<td width="20%">&nbsp;</td>
						<td width="5%" class="red">MoneyLine</td>
						<td width="5%" class="red">RunLine</td>
						<td width="5%" class="red">Alta/Baja</td>
					</tr>
				<%}%>
					<tr>
					<td width="4%" <%=uo.equals(Constants.BAJA)?
							(lista.getString("status_real").equals("Abierto/Taq.")?"class='yellow'":(lista.getString("status_real").startsWith("Totalizado")?"class='blue'":(lista.getString("status_real").startsWith("Borrador")?"class='green'":(lista.getString("status_real").startsWith("Abierto/Bloq.")?"class='agua'":"class='red'"))))
							:""%>>
						<%=(uo.equals(Constants.ALTA)?lista.getString("hora"):(uo.equals(Constants.BAJA)?"":""))%>
					</td>
					<td width="3%"><%=lista.getString("referencia")%> </td>
					<td width="3%"><%=lista.getString("abreviatura")%></td>
					<td width="20%" class="detalleTablaLeftUpperCase"><%=lista.getString("desc_equipo")%><span class="detalleTablaLeftCapitalize"><%=Constants.isNull(lista.getString("nombre_lanzador"),"",", Lanzador:")%></span></td>
					<td width="5%"><%=Constants.signo(lista.getString("money_line"))%></td>
					<%if(!lista.getString("id_equipo").equals(Constants.ID_EQUIPO_EMPATE)) {%>
						<td width="5%">
							&nbsp;
							<%if((!Constants.tipoFraccion(lista.getString("spread")).equals("") || !Constants.signo(lista.getString("spread_logro")).equals("")) && !Constants.signo(lista.getString("spread_logro")).equals("0")){%>
							<%=Constants.tipoFraccion(lista.getString("spread"))%><%=Constants.signo(lista.getString("spread_logro"))%>
							<%}%>
						</td>
						<td width="5%">
							<%if(!Constants.tipoFraccion(lista.getString("total"),false).equals("") && !Constants.signo(lista.getString("total_logro")).equals("0")){%>
							<%=uo%>&nbsp;<%=Constants.tipoFraccion(lista.getString("total"),false)%><%=Constants.signo(lista.getString("total_logro"))%>
							<%}%>
						</td>
					<%} else {%>
						<td width="5%">&nbsp;</td>
						<td width="5%">&nbsp;</td>
					<%}%>
				</tr>	
			</table>
		</td>
	</tr>	
	<%	
		uo=(uo.equals(Constants.ALTA)?Constants.BAJA:(uo.equals(Constants.BAJA)?"":""));
	}%> 
	</logic:present>
</table>
<hr width="98%" color="#efefef"/>
</form>
<center>
<a class="enlaceBoton" href="<%=basePath%>start.do"	><bean:message key="boton.salir"/></a> 
</center>

