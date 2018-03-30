
<jsp:directive.page import="com.betcesc.game.common.Constants"/><%@ include file="/views/common/taglibs.jsp"%>
<% CachedRowSet lista = (CachedRowSet)request.getAttribute("lista"); %>

<script language="javascript">
function send(orden) {
	document.forms[0].orden.value=orden;
	document.forms[0].submit();
}
</script>
<form name="forma" action="listUser.do">
<input type="hidden" name="orden" value="0" />
<table align="center" width="98%" height="100%" cellpadding="3" cellspacing="3">
  <tr>
	<td width="100%" align="left" colspan="3">
	<font color="white">
		<html:errors/>
	</font>
	</td>
  </tr>
</table>
<table align="center" width="98%" height="100%" >
<caption class="tituloTablaSup">DETALLE DE LA CUENTA <%=(usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) && lista.next()?"DEL JUGADOR ".concat(lista.getString("nombreJugador")):"")%></caption>
	<tr>
		<td class="tituloTabla tablaConBorde"><bean:message key="cuentaJugador.fechaSis"/> - Hora</td>
		<td class="tituloTabla tablaConBorde"><bean:message key="cuentaJugador.referencia"/></td>
		<td class="tituloTabla tablaConBorde"><bean:message key="cuentaJugador.concepto"/></td>
		<td class="tituloTabla tablaConBorde">Creditos <%=Constants.getDominio(request).getMoneda()%></td>
		<td class="tituloTabla tablaConBorde">Debitos <%=Constants.getDominio(request).getMoneda()%></td>
		<td class="tituloTabla tablaConBorde">Saldo <%=Constants.getDominio(request).getMoneda()%></td>
		<%if(usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)){%>
		<td class="tituloTabla tablaConBorde">Responsable</td>
		<%}%>
	</tr>	
	<logic:present name="lista" scope="request">
	<%lista.beforeFirst();
	while(lista.next()) {%>
	<tr onmouseover="this.className='lineaResaltada'" onmouseout="this.className=''" >
		<td class="detalleTablaCenter"><%=lista.getString("dia")%> - <%=lista.getString("hora")%></td>
		<td class="detalleTablaCenter"><%=lista.getString("referencia")%></td>
		<td class="detalleTablaLeft"><%=lista.getString("concepto")%></td>
		<td class="detalleTablaRight"><%=(Constants.isNull(lista.getString("ingreso"),"").equals("0.00")?(lista.getString("tipo").equals("P")?"0.00":""):Constants.isNull(lista.getString("ingreso"),""))%></td>
		<td class="detalleTablaRight"><%=(Constants.isNull(lista.getString("egreso"),"").equals("0.00")?"":Constants.isNull(lista.getString("egreso"),""))%></td>
		<td class="detalleTablaRight"><%=lista.getString("acumulado")%></td>
		<%if(usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)){%>
		<td class="detalleTablaCenter"><%=lista.getString("usuario")%></td>
		<%}%>
	</tr>	
	<%}%>
	</logic:present>
</table>
<hr width="98%" color="#efefef"/>
<%if(usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)){%>
<center>
<a class="enlaceBoton" href="#" onclick="history.back()"	><bean:message key="boton.anterior"/></a>
</center>
<%}%>
</form>

