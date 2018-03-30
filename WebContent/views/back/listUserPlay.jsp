<%@ include file="/views/common/taglibs.jsp"%>
<script language="javascript">
function send(orden) {
	document.forms[0].orden.value=orden;
	document.forms[0].submit();
}
</script>
<form name="forma" action="listUserPlay.do">
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
<caption class="tituloTablaSup">LISTA DE JUGADORES</caption> 
	<tr>
		<td class="tituloTabla tablaConBorde" onclick="send(1)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="usuario.apellido"/></td>
		<td class="tituloTabla tablaConBorde" onclick="send(2)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="usuario.nombre"/></td>
		<td class="tituloTabla tablaConBorde" onclick="send(5)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="usuario.usuario"/></td>
		<td class="tituloTabla tablaConBorde" onclick="send(6)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'">Supervisor</td>
		<td class="tituloTabla tablaConBorde" onclick="send(5)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="usuario.correo"/></td>
		<td class="tituloTabla tablaConBorde cursorClick" width="200px" onclick="send(3)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'">Saldo</td>
		<td class="tituloTabla tablaConBorde cursorClick" onclick="send(4)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="usuario.idStatus"/></td>
		<td class="tituloTabla tablaConBorde" width="1%">&nbsp;Session&nbsp;</td>
	</tr>	
	<logic:present name="lista" scope="request">
	<% CachedRowSet lista = (CachedRowSet)request.getAttribute("lista"); 
	while(lista.next()) {%>
	<tr onmouseover="this.className='lineaResaltada'" onmouseout="this.className=''" onclick="document.location='<%=basePath%>registerSaldo.do?idUsuario=<%=lista.getString("id_usuario")%>'">
		<td class="detalleTablaLeft"><%=lista.getString("apellido")%></td>
		<td class="detalleTablaLeft"><%=lista.getString("nombre")%></td>
		<td class="detalleTablaCenter"><%=lista.getString("usuario")%></td>
		<td class="detalleTablaCenter"><%=lista.getString("supervisor")%></td>
		<td class="detalleTablaCenter"><%=lista.getString("correo")%></td>
		<td class="detalleTablaRight"><%=Constants.isNull(lista.getString("saldo"),"0.00")%></td>
		<td class="detalleTablaCenter"><%=lista.getString("desc_status")%></td>
		<td class="detalleTablaCenter"><%= Constants.isUserInSession(lista.getString("id_usuario"))?"*":"&nbsp;" %></td>
	</tr>	
	<%}%>
	</logic:present>
</table>
<hr width="98%" color="#efefef"/>
</form>

