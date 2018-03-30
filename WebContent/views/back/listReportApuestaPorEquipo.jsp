<%@ include file="/views/common/taglibs.jsp"%>
<%@ page import="com.betcesc.game.to.EquipoTO" %>
<% CachedRowSet listaJugador = (CachedRowSet)request.getAttribute("listaJugador");  %>
<% CachedRowSet listaStatusJugada = (CachedRowSet)request.getAttribute("listaStatusJugada");  %>
<% ArrayList equipos = (ArrayList)request.getAttribute("equipos"); 
EquipoTO equipoTO; %>
<script language="JavaScript" src="<%=basePath%>scripts/Calendario_2.js"></script>
<script language="javascript">
function send() {
	with(document.forms[0]) {
		var cad = "";
		var sep = "";
		var coma = ",";
		for(var i=0;i<idEquipo.length;i++) {
			if(idEquipo[i].selected){
				cad += sep;
				cad += idEquipo[i].value;
				sep = coma; 
			}
		}
		var url = action+"?fecha="+fecha.value;
		if(cad!=""){
			url += "&idEquipo="+cad;
		}
		window.open(url,"_blank","width=1000,height=600,status=no,resizable=yes,top=50,left=100,scrollbars=yes");
	}
}

</script>
<table align="center" width="80%" cellpadding="10" cellspacing="5" >
	<tr>
		<td>
	  		<fieldset>
	  		<form action="<%=basePath%>listApuestaPorEquipo.do" method="POST">
  			<legend class="tituloTablaSup">&nbsp;Apuestas por Equipo&nbsp;</legend>
			<table width="80%" height="100%" border="0" cellpadding="0" cellspacing="5" align="center">
				<tr>
					<td class="detalleTablaLeft">
						Fecha de Juego... 
					</td>
					<td>
						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="100">
						  			<input type="text" name="fecha" 
						  					value="<%=request.getAttribute("fecha")%>"
											maxlength="10" size="12" 
											onblur="DateFormat(this,this.value,event,true,'3')"  
											onkeyup="DateFormat(this,this.value,event,false,'3')" onfocus="javascript:vDateType='3'" />
								</td>
								<td width="100" align="left">
							        <a href="javascript:show_calendar('forms[0].fecha', 'forms[0].fecha', 'forms[0].fecha');" 
									onmouseover="window.status='Seleccione la fecha';return true;" 
									onmouseout="window.status='';return true;">
						            <img src="<%=basePath%>images/calendario.gif" border="0" title="Seleccionar Fecha"></a>
								</td>
							</tr>
						</table>
					</td>
				</tr>

				<tr style="display:none;">
					<td class="detalleTablaLeft" valign="top">
						Equipos... 
					</td>
					<td>
						<select name="idEquipo" size="25" style="width:400px;" multiple>
							<option value="">Todos</option>
							<%for(int i=0;i<equipos.size();i++){
								equipoTO = (EquipoTO)equipos.get(i);%>
								<option value="<%=equipoTO.getIdEquipo()%>"><%=equipoTO.getDescEquipo()%></option>
							<%}%>
						</select>
					</td>
				</tr>

				<tr>
					<td align="center" colspan="3">
						<a class="enlaceBoton" href="#" onclick="send()" >Ver Reporte</a>
					</td>
				</tr>
			</table>
			</form>
			</fielset>
		</td>
	<tr>
</table>
