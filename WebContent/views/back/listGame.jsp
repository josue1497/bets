<%@ include file="/views/common/taglibs.jsp"%>
<%@ page import="com.betcesc.game.to.ListaJuegoTO" %>
<%@ page import="com.betcesc.game.bean.DominioBean" %>
<%
	CachedRowSet listaStatus = (CachedRowSet)request.getAttribute("listaStatus"); 
   CachedRowSet listaDeportes = (CachedRowSet)request.getAttribute("listaDeportes");
	String status = "";
	StringBuffer onClick= new StringBuffer();
	DominioBean dom = Constants.getDominio(request);
	boolean isDominioMain = dom.getIdDominio().equals("000");
%>
<style>
.totalizado {
	font-size:11px;
	color:yellow;
	text-transform:Capitalize;
}
</style>
<script language="JavaScript" src="<%=basePath%>scripts/Calendario_2.js"></script>
<script language="javascript">
function post_calendar() {
	//document.forma.submit();
}
var selectAll = true;
function todos(){
	for(var i=0; i<document.forma.elements.length;i++){
		if(document.forma.elements[i].type=="checkbox"){
			document.forma.elements[i].checked=selectAll;
		}
	}
	selectAll=!selectAll;
}
function abrir(){
	document.forma.idJuego.value="";
	var sep="";
	var entro=false;
	for(var i=0; i<document.forma.elements.length;i++){
		if(document.forma.elements[i].type=="checkbox"){
			if(document.forma.elements[i].checked){
				document.forma.idJuego.value=document.forma.idJuego.value+sep+document.forma.elements[i].id
				sep="_"
				entro=true;
			}
		}
	}
	if(entro){
		document.forma.action="registerGameSave.do"
		document.forma.abrir.value="true";
		<logic:equal value="2" name="usuario" property="idRol" scope="session">
		document.forma.idStatusJuego.value="<%=Constants.STATUS_JUEGO_ABIERTO%>";
		document.forma.multiple.value="true";
		</logic:equal>
		document.forma.submit();
	} else {
		<logic:equal value="1" name="usuario" property="idRol" scope="session">
		alert("Marque algunos de los juegos en estatus borrador para abrirlos.");
		</logic:equal>
		<logic:equal value="2" name="usuario" property="idRol" scope="session">
		alert("Marque algunos de los juegos en estatus Abierto/Adm. para abrirlos.");
		</logic:equal>
		return;
	}
}

function eliminar(){
	document.forma.idJuego.value="";
	var sep="";
	var entro=false;
	for(var i=0; i<document.forma.elements.length;i++){
		if(document.forma.elements[i].type=="checkbox"){
			if(document.forma.elements[i].checked){
				document.forma.idJuego.value=document.forma.idJuego.value+sep+document.forma.elements[i].id
				sep="_"
				entro=true;
			}
		}
	}
	if(entro){
		if(confirm("Esta seguro de eliminar los juegos seleccionado")){
			document.forma.action="registerGameSave.do"
			document.forma.eliminar.value="true";
			<logic:equal value="2" name="usuario" property="idRol" scope="session">
			document.forma.idStatusJuego.value="<%=Constants.STATUS_JUEGO_ABIERTO%>";
			document.forma.multiple.value="true";
			</logic:equal>
			document.forma.submit();
		}
	}
}

function cerrar(){
	document.forma.idJuego.value="";
	var sep="";
	var entro=false;
	for(var i=0; i<document.forma.elements.length;i++){
		if(document.forma.elements[i].type=="checkbox"){
			if(document.forma.elements[i].checked){
				document.forma.idJuego.value=document.forma.idJuego.value+sep+document.forma.elements[i].id
				sep="_"
				entro=true;
			}
		}
	}
	if(entro){
		document.forma.action="registerGameSave.do"
		document.forma.cerrar.value="true";
		document.forma.submit();
	} else {
		<logic:equal value="1" name="usuario" property="idRol" scope="session">
		alert("Marque algunos de los juegos en estatus abierto para cerrarlos.");
		</logic:equal>
		return;
	}
}

function fijarFecha(objSelect){
	if(objSelect.value!=1 && objSelect.value!=2 && objSelect.value!=4) {
		if(document.forms[0].fecha.value=='') {
			document.forms[0].fecha.value='<%=Constants.getFechaCorta()%>';
		}
	} else {
		document.forms[0].fecha.value='';
	}
}

</script>
<form name="forma" method="POST" action="listGame.do">
	<input type="hidden" name="idJuego" value="">
	<input type="hidden" name="idStatusJuego" value="<%=Constants.STATUS_JUEGO_BORRADOR%>">
	<input type="hidden" name="multiple" value="false">
	<input type="hidden" name="abrir" value="false"/>
	<input type="hidden" name="cerrar" value="false"/>
	<input type="hidden" name="eliminar" value="false"/>
<table align="center" width="98%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td >
			<table align="center" width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="40%" align="center">
						<table  border="0" cellpadding="0" cellspacing="0">
						  <tr>
							<td class="detalleTablaRight">Deporte :&nbsp;</td>
							<td>
				  				<select name="idDeporteListGame" >
				  					<option value=''>Todos</option>
				  					<%while(listaDeportes.next()) {%>
				  					<option value='<%=listaDeportes.getString("id_deporte")%>'><%=listaDeportes.getString("desc_deporte")%></option>
				  					<%}%>
				  				</select>
							</td>
						  </tr>
						</table>
					</td>
					<td width="20%" class="tituloTablaSup" align="center" >LISTA DE JUEGOS</td>
					<td width="40%" align="right">
						<table height="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr>
							<td class="detalleTablaRight">Status :&nbsp;</td>
							<td>
				  				<select name="status" onchange="fijarFecha(this)">
				  					<option value='0'>Todos</option>
				  					<%while(listaStatus.next()) {%>
				  					<option value='<%=listaStatus.getString("id_status_juego")%>'><%=listaStatus.getString("desc_status_juego")%></option>
				  					<%}%>
				  				</select>
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td class="detalleTablaRight">Fecha :&nbsp;</td>
							<td>
				  			<input type="text" name="fecha" size="10" maxlength="10" value="<%=session.getAttribute("fecha")%>" readOnly />
							</td>
							<td>	
							        <a href="javascript:show_calendar('forms[0].fecha', 'forms[0].fecha', 'forms[0].fecha');" 
									onmouseover="window.status='Seleccione la fecha';return true;" 
									onmouseout="window.status='';return true;">
						            <img src="<%=basePath%>images/calendario.gif" border="0" title="Seleccionar Fecha"></a>
						    </td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						    <td><a class="enlaceBoton" href="#" onclick="document.forms[0].submit()" >Listar</a>&nbsp;</td>
						  </tr>
						</table>					
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<logic:present name="lista" scope="request">
	<% ArrayList listas = (ArrayList)request.getAttribute("lista");  
	String idJuego = null;
	String titulo = null;
	String uo = Constants.ALTA;
	String estilo="";
	ListaJuegoTO lista;
	for(int i=0; i<listas.size();i++) {
		lista = (ListaJuegoTO)listas.get(i);
		estilo="borderTopBlack";
		if(idJuego==null || !idJuego.equals(lista.getIdJuego())) {
			uo=Constants.ALTA;
			idJuego=lista.getIdJuego();
			
			if(titulo==null || !titulo.equals(lista.getDia().concat(lista.getDescLiga()))) {%>
				<tr>
					<td class="detalleTablaLeftTitulo borderWhite">
						<table align="center" width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="60%" >&nbsp;<%=lista.getDescLiga()%> (<%=lista.getIniciales()%>) - <%=lista.getDia()%></b> </td>
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
			titulo = lista.getDia().concat(lista.getDescLiga());
		}%>
	<tr class="detalleTablaLeftEquipo" >
		<td colspan="2" onmouseover="this.className='lineaResaltada'" onmouseout="this.className=''" >
			<table align="center" width="100%" height="100%" border="0" cellspacing="0" cellpadding="1" class="<%=estilo%>">
				<%onClick.setLength(0);
				if(isAdminGeneral) {
					onClick.append("onclick=\"document.location='").append(basePath).append("registerGame").append(lista.getEmpate().equals(Constants.JUEGO_MULTIPLE_EQUIPO)?"Multi":"").append(".do?idJuego=").append(lista.getIdJuego()).append("'\"");
				} else if( usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA) && !lista.getStatusReal().equals(Constants.STATUS_BLOQUEADO) && lista.getIdStatusJuego().equals(Constants.STATUS_JUEGO_ABIERTO) ) {
					onClick.append("onclick=\"document.location='").append(basePath).append("registerGame").append(lista.getEmpate().equals(Constants.JUEGO_MULTIPLE_EQUIPO)?"Multi":"").append(".do?idJuego=").append(lista.getIdJuego()).append("'\"");
				} else if( usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) && (lista.getIdStatusJuego().equals(Constants.STATUS_JUEGO_BORRADOR) || lista.getIdStatusJuego().equals(Constants.STATUS_JUEGO_ABIERTO) || lista.getIdStatusJuego().equals(Constants.STATUS_JUEGO_CERRADO)) && usuario.getDominio().equals(lista.getDominioActual()) ) {
					onClick.append("onclick=\"document.location='").append(basePath).append("registerGame").append(lista.getEmpate().equals(Constants.JUEGO_MULTIPLE_EQUIPO)?"Multi":"").append(".do?idJuego=").append(lista.getIdJuego()).append("'\"");
				}%>
				<tr class="detalleTablaLeftEquipo">
					<td NOWRAP <%=onClick%> width="4%" <%=uo.equals(Constants.BAJA)?Constants.getColorStatus(lista.getStatusReal()):""%>>
						<%status=Constants.getStatusGame(uo,lista);%> 
						<%=status%>
					</td>
					<td <%=onClick%>  width="3%"><%=lista.getReferencia()%> </td>
					<td <%=onClick%>  width="3%"><%=lista.getAbreviatura()%></td>
					<td <%=onClick%>  width="20%" class="detalleTablaLeftUpperCase">
						<%=lista.getDescEquipo()%>
							<span class="detalleTablaLeftCapitalize">
								<%=Constants.isNull(lista.getNombreLanzador(),"",", Lanzador:")%>
							</span>&nbsp;<span class="red">
							<%=uo.equals(Constants.ALTA) && isAdminGeneral?"(id="+lista.getIdJuego()+") "+(lista.getDominioActual().equals("000")?"":Constants.getDomainName(lista.getDominioActual())):(uo.equals(Constants.ALTA) && usuario.getDominio().equals(lista.getDominioActual())?"(*)":"")%></span>
							<span class="totalizado"><%=uo.equals(Constants.BAJA) && lista.getIdStatusJuego().equals("5")?"Totalizado por :"+lista.getUsuarioTotaliza():""%></span>
					</td>
					<td <%=onClick%>  width="5%"><%=Constants.signo(lista.getMoneyLine())%></td>
					<%if(!lista.getIdEquipo().equals(Constants.ID_EQUIPO_EMPATE) && !lista.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL_1) && !lista.getSpreadLogro().equals("0")) {%>
						<td width="5%"><%=Constants.tipoFraccion(lista.getSpread())%><%=Constants.signo(lista.getSpreadLogro())%></td>
					<%} else {%>
						<td width="5%">&nbsp;</td>
					<%}%>
					<%if(!lista.getIdEquipo().equals(Constants.ID_EQUIPO_EMPATE) && !lista.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL_1) && !lista.getSuperSpreadLogro().equals("0")) {%>
						<td width="5%"><%=Constants.tipoFraccion(lista.getSuperSpread())%><%=Constants.signo(lista.getSuperSpreadLogro())%></td>
					<%} else {%>
						<td width="5%">&nbsp;</td>
					<%}%>
					<%if(!lista.getIdEquipo().equals(Constants.ID_EQUIPO_EMPATE) && !lista.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL_1) && !lista.getTotalLogro().equals("0")) {%>
						<td width="5%"><%=uo%>&nbsp;<%=Constants.tipoFraccion(lista.getTotal(),false)%><%=Constants.signo(lista.getTotalLogro())%></td>
					<%} else {%>
						<td width="5%">&nbsp;</td>
					<%}%>
					<% 
					   String punto = "";
					   if(lista.getStatusReal().startsWith("Totalizado") && !lista.getIdEquipo().equals(Constants.ID_EQUIPO_EMPATE)) {
					   	   punto = Constants.isNull(lista.getPuntos(),"");
						   if(lista.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL_1)){
						   		punto=(punto.equals("1")?"SI":(punto.equals("0")?"NO":punto));
						   }
					   }
					%>
					
					<td <%=onClick%>  width="1%" class="red"><%=punto%></td>
					<td  width="1%" class="red">
						<logic:equal value="1" name="usuario" property="idRol" scope="session">
						 
						<%if(uo.equals(Constants.BAJA) && ( status.equals("Borrador/Adm.")) && !String.valueOf(session.getAttribute("status")).equals("0")){%>
							<input id="<%=lista.getIdJuego()%>" type="checkbox" style="height:14px;width:14px;border:0px;" >		
						<%}%>
						</logic:equal>
						
						<logic:equal value="2" name="usuario" property="idRol" scope="session">
						<%if(uo.equals(Constants.BAJA) && (status.equals("Abierto/Adm.")) && !String.valueOf(session.getAttribute("status")).equals("0")){%>
							<input id="<%=lista.getIdJuego()%>" type="checkbox" style="height:14px;width:14px;border:0px;" >
						<%}%>
						</logic:equal>
						
						<logic:equal value="1" name="usuario" property="idRol" scope="session">
						<%if(uo.equals(Constants.BAJA) && ( status.equals("Abierto/Adm.")) && !String.valueOf(session.getAttribute("status")).equals("0")){%>
							<input id="<%=lista.getIdJuego()%>" type="checkbox" style="height:14px;width:14px;border:0px;" >		
						<%}%>
						</logic:equal>
					</td>
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
<%if(usuario.getDominio().equals("000") ){%>
<logic:equal value="1" name="usuario" property="idRol" scope="session">
<center>
<a class="enlaceBoton" href="<%=basePath%>registerGame.do"	><bean:message key="boton.agregar"/></a>
<a class="enlaceBoton" href="<%=basePath%>registerGameMulti.do"	>Agregar Multiple</a>
&nbsp;
<%if(String.valueOf(session.getAttribute("status")).equals(Constants.STATUS_JUEGO_BORRADOR)){%>
	<a class="enlaceBoton" href="javascript:abrir();" >Abrir Seleccionados</a>&nbsp;
	&nbsp;
	<a class="enlaceBoton" style="background-color:red;" href="javascript:eliminar();" >Eliminar Seleccionados</a>&nbsp;
	&nbsp;
	<a class="enlaceBoton" href="javascript:todos();" >Seleccionar Todos</a>&nbsp;
<%} else if(String.valueOf(session.getAttribute("status")).equals(Constants.STATUS_JUEGO_ABIERTO)){%>
	<a class="enlaceBoton" href="javascript:cerrar();" >Cerrar Seleccionados</a>&nbsp;
	&nbsp;
	<a class="enlaceBoton" href="javascript:todos();" >Seleccionar Todos</a>&nbsp;
<%}%>
</center>
</logic:equal>
<%} else if( dom.getIdAdmin().equals(usuario.getIdUsuario()) ) {%>
<center>
<a class="enlaceBoton" href="<%=basePath%>registerGame.do"	><bean:message key="boton.agregar"/></a>
</center>
<%}%>

<logic:equal value="2" name="usuario" property="idRol" scope="session">
<center>
<!--<a class="enlaceBoton" href="<%=basePath%>registerGame.do"	><bean:message key="boton.agregar"/></a>--> 
<a class="enlaceBoton" href="javascript:abrir();" >Abrir Seleccionados</a>
&nbsp;
<a class="enlaceBoton" href="javascript:todos();" >Seleccionar Todos</a>&nbsp;
</center>
</logic:equal>


</form>
<%if(!usuario.getDominio().equals("000")){%>
<div style="color:red;">(*) El juego fue agregado por el administrador del dominio</div>
<%}%>
<script language="javascript">
document.forms[0].status.value='<%=session.getAttribute("status")%>';
document.forms[0].idDeporteListGame.value='<%=session.getAttribute("idDeporteListGame")%>';
</script>
