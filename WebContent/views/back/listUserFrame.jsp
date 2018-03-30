<%@ include file="/views/common/taglibs.jsp"%>
<html:html xhtml="true" locale="true">
<head>
  <%-- Include common set of meta tags for each layout --%>
  <%@ include file="/views/common/meta.jsp" %>

<script language="javascript">
function send(orden) {
	if(confirm("Si reordena la lista se perderan los cambios no guardados\n\nDesea Continuar?")){
		document.forms[0].orden.value=orden;
		document.forms[0].submit();
	}
}
</script>
</head>
<body style="font-family:tahoma,helvetica,Arial,verdana,sans-serif;" leftmargin="10" topmargin="10" marginwidth="10" marginheight="10" vlink="#29613c" link="#29613c" bgcolor="#4f4f4f">
<form name="forma" action="listUserFrame.do">
<input type="hidden" name="orden" value="0" />
<input type="hidden" name="security" value="true" />
<input type="hidden" name="retorno" value="frame" />
<input type="hidden" name="idUsuario" value="<%=request.getParameter("idUsuario")%>" />
<table align="center" width="98%" height="100%" >
	<tr>
		<td class="tituloTabla tablaConBorde">&nbsp;</td>
		<td class="tituloTabla tablaConBorde" onclick="send(1)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="usuario.apellido"/></td>
		<td class="tituloTabla tablaConBorde" onclick="send(2)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="usuario.nombre"/></td>
		<td class="tituloTabla tablaConBorde" onclick="send(5)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="usuario.usuario"/></td>
		<td class="tituloTabla tablaConBorde" onclick="send(6)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'">Supervisor</td>
		<td class="tituloTabla tablaConBorde cursorClick" width="200px" onclick="send(3)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="usuario.idRol"/></td>
		<td class="tituloTabla tablaConBorde cursorClick" onclick="send(4)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="usuario.idStatus"/></td>
	</tr>	
	<logic:present name="lista" scope="request">
	<% int tipo = Integer.parseInt(request.getAttribute("tipo")!=null?String.valueOf(request.getAttribute("tipo")):"0"); 
	tipo=1; // solo mostraremos jugadores de taquilla y jugadores
	CachedRowSet lista = (CachedRowSet)request.getAttribute("lista"); 
	boolean cond1 = false;
	boolean cond2 = false;
	while(lista.next()) {
		cond1=(tipo==Constants.ADM_GENERAL && (lista.getString("id_rol").equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA) || lista.getString("id_rol").equals(Constants.ROL_JUGADOR)));
		cond2=(tipo==Constants.ADM_LISTERO && (lista.getString("id_rol").equals(Constants.ROL_JUGADOR_DE_TAQUILLA) || lista.getString("id_rol").equals(Constants.ROL_JUGADOR)  || lista.getString("id_rol").equals(Constants.ROL_AUTO_JUGADOR)));
		if(cond1 || cond2) {%>
	<tr onmouseover="this.className='lineaResaltada'" onmouseout="this.className=''" >
		<td class="detalleTablaLeft" style="text-align:center"><input type="checkbox" name="usuario" value="<%=lista.getString("id_usuario")%>" <%=!lista.getString("activo").equals("0")?"checked":""%> ></td>
		<td class="detalleTablaLeft"><%=lista.getString("apellido")%></td>
		<td class="detalleTablaLeft"><%=lista.getString("nombre")%></td>
		<td class="detalleTablaCenter"><%=lista.getString("usuario")%></td>
		<td class="detalleTablaCenter"><%=lista.getString("supervisor")%></td>
		<td class="detalleTablaCenter"><%=lista.getString("desc_rol")%></td>
		<td class="detalleTablaCenter"><%=lista.getString("desc_status")%></td>
	</tr>	
		<%}
	}%>
	</logic:present>
</table>
<hr width="98%" color="#efefef"/>
</form>
</body>
</html:html>

