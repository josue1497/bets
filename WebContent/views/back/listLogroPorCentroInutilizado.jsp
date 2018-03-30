<%@ include file="/views/common/taglibs.jsp"%>
<% CachedRowSet listaStatus = (CachedRowSet)request.getAttribute("listaStatus"); %>
<% CachedRowSet listaDeporte = (CachedRowSet)request.getAttribute("listaDeporte"); 
boolean isActiveSRL = usuario.getActivarSuperRunline().equals("1");
%>
<script>
var lista = new Array();
function send() {
	var forma = document.forma;
	ids = lista.join("-");
	
	if(ids.length>0) {
		reportePorCentro(ids,'Logros',true);
	} else {
		alert("Seleccione los deportes a mostrar.");
	}
}
function agregar(obj) {
	var n = findArray(lista,obj.value);
	if(n>=0) {
		lista.splice(n,1);
	} else {
		lista.push(obj.value);
	}
}
</script> 
<form name="forma" method="POST" action="listGame.do">

<table align="center" width="98%" height="100%" >
<caption class="tituloTablaSup"></caption>
	<tr>
		<td class="tituloTablaSup tablaConBorde" width="75%" rowspan="2">
				<table align="center" width="98%" height="100%" >
					<tr>
						<td width="30%">
						</td>
						<td width="40%">
							Deportes
						</td>
						<td width="30%">
							<a class="enlaceBoton" href="javascript:send();"	>
								<img src="<%=basePath%>images/printer.png" border="0"/>
								Logros Seleccionados del D&iacute;a xx
							</a>
						</td>
					</tr>
				</table>
				<table align="center" width="98%" height="100%" >
				<tr>
				<%while(listaDeporte.next()) {
					if( !listaDeporte.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL_5) && !listaDeporte.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL_1) ) {%>
					<td class="tituloTabla tablaConBorde" onmouseover="this.className='lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"	>
						<input type="checkbox" name="idDeporte" value="<%=listaDeporte.getString("id_deporte")%>" onclick="agregar(this)">
						<span style="cursor:pointer;" onclick="reportePorCentro('<%=listaDeporte.getString("id_deporte")%>','<%=listaDeporte.getString("desc_deporte")%>')">
							<%=listaDeporte.getString("desc_deporte")%>
						</span> 
					</td>
				  <%}
				}%>
				</tr>
			</table>
		</td>
	</tr>	
</table>

<table align="center" width="98%" height="100%" >
<caption class="tituloTablaSup">LISTA DE JUEGOS ABIERTOS</caption>
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
						<%if(isActiveSRL){%>
						<td width="5%" class="red">Super RunLine</td>
						<%}%>
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
						<td width="5%"><%=Constants.tipoFraccion(lista.getString("spread"))%><%=Constants.signo(lista.getString("spread_logro"))%></td>
						<%if(isActiveSRL){%>
						<td width="5%"><%=Constants.tipoFraccion(lista.getString("super_spread"))%><%=Constants.signo(lista.getString("super_spread_logro"))%></td>
						<%}%>
						<td width="5%"><%=uo%>&nbsp;<%=Constants.tipoFraccion(lista.getString("total"),false)%><%=Constants.signo(lista.getString("total_logro"))%></td>
					<%} else {%>
						<td width="5%">&nbsp;</td>
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
<a class="enlaceBoton" href="<%=basePath%>inicio.do"	><bean:message key="boton.salir"/></a> 
</center>

