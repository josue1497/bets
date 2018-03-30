<%@ include file="/views/common/taglibs.jsp"%>
<% CachedRowSet listaStatus = (CachedRowSet)request.getAttribute("listaStatus"); %>
<% CachedRowSet listaDeporte = (CachedRowSet)request.getAttribute("listaDeporte"); 
boolean isActiveSRL = usuario.getActivarSuperRunline().equals("1");
%>
<script>
var lista = new Array();
function send() {
	document.getElementById('listaLiga').style.display="none";
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
function openLiga(id,desc) {
	document.getElementById('listaLiga').style.display="";
	window.open("<%=basePath%>liga.do?idDeporte="+id+"&descDeporte="+desc,"liga");
}
</script> 
<form name="forma" method="POST" action="listGame.do">

<table align="center" width="98%" height="100%" cellpadding="30">
	<caption class="tituloTablaSup"></caption>
	<tr>
		<td class="tituloTablaSup " width="75%" >
				<table align="center" width="98%" height="100%" border="0">
					<tr>
						<td width="40%">
							LISTADO DE LOGROS
						</td>
					</tr>
				</table>
				<br/>
				<center>
				<div style="border:1px solid white;width:300px;background-color:white;">
					<a class="enlaceBoton" style="background-color:white;color:#000000;" href="javascript:send();"	>
						<img src="<%=basePath%>images/printer.png" border="0"/>
						Logros Seleccionados del D&iacute;a
					</a>
				</div>
				<br/>
				<br/>
				<div>
					<table align="center" border="0">
						<tr>
							<td valign="top">
								<table align="center" width="300" cellspacing="5" cellpadding="5" style="padding-left:5px;text-align:left;" border="0" >
								<tr>
									<td class="tituloTablaSup"	>
										POR FECHA
									</td>
								</tr>
								<%while(listaDeporte.next()) {
									if( !listaDeporte.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL_5) && !listaDeporte.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL_1) && !listaDeporte.getString("id_deporte").equals(Constants.ID_EQUIPO_SOCCER_MITAD) ) {%>
								<tr>
									<td class="lineaResaltadaDeporte"	>
										<input type="checkbox" name="idDeporte" value="<%=listaDeporte.getString("id_deporte")%>" onclick="agregar(this)">
										<span style="cursor:pointer;" onclick="document.getElementById('listaLiga').style.display='none';reportePorCentro('<%=listaDeporte.getString("id_deporte")%>','<%=listaDeporte.getString("desc_deporte")%>')">
											<%=listaDeporte.getString("desc_deporte")%>
										</span> 
									</td>
								</tr>
								  <%}
								}%>
								</table>
							</td>
							<td>
							&nbsp;&nbsp;&nbsp;
							</td>
							<td valign="top">
								<table align="center" width="300" cellspacing="5" cellpadding="5" style="padding-left:5px;text-align:left;" border="0" >
								<tr>
									<td class="tituloTablaSup"	>
										POR LIGA
									</td>
								</tr>
								<%listaDeporte.beforeFirst();
								while(listaDeporte.next()) {
									if( listaDeporte.getString("id_deporte").equals(Constants.ID_EQUIPO_SOCCER) || listaDeporte.getString("id_deporte").equals(Constants.ID_EQUIPO_BASKETBALL) || listaDeporte.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL) ) {%>
								<tr>
									<td class="lineaResaltadaDeporte"	>
										<span style="cursor:pointer;" onclick="reportePorCentroLiga('<%=listaDeporte.getString("id_deporte")%>','<%=listaDeporte.getString("desc_deporte")%>')">
											<%=listaDeporte.getString("desc_deporte")%>
										</span> 
									</td>
								</tr>
								  <%}
								}%>
								</table>
							</td>
						</tr>
						<tr>
							<td height="300px;" colspan="3" align="center" valign="top">
								<div id="listaLiga" style="display:none;">
								<br><br>
								<iframe name="liga" id="liga" style="width:600px; height:300px;">
								</iframe>
								</div>
							</td>
						</tr>
					</table>
				</div>
				</center>
		</td>
	</tr>
</table>
</form>

