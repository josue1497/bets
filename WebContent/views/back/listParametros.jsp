<%@ include file="/views/common/taglibs.jsp"%>
<script language="javascript">
function send(orden) {
	document.forms[0].orden.value=orden;
	document.forms[0].submit();
}
function imagenes() {
	window.open("<%=basePath%>listImages.do","imagenes","width=1000,height=600,status=yes,resizable=yes,top=50,left=100,scrollbars=yes");
}
</script>
<form name="forma" action="listUser.do">
<input type="hidden" name="orden" value="0" /><br/><br/><br/>
<table align="center" width="68%"  style="border:1px solid #afafaf;" cellspacing="0" cellpadding="10">
<caption class="tituloTablaSup">LISTA DE PARAMETROS</caption>
	<tr style="height:22px;color:#ffffff;text-align:center;">
		<td>Nombre</td>
		<td>Valor</td>
	</tr>	
	<logic:present name="lista" scope="request">
	<% CachedRowSet lista = (CachedRowSet)request.getAttribute("lista"); 
	while(lista.next()) {%>
	<tr onmouseover="this.className='lineaResaltada'" onmouseout="this.className=''" onclick="document.location='<%=basePath%>registerParametros.do?nombre=<%=lista.getString("nombre")%>'">
		<td class="detalleTablaLeft" style="height:80px;border:1px solid #afafaf;"><%=lista.getString("nombre")%></td>
		<td class="detalleTablaLeft" style="height:80px;border:1px solid #afafaf;"><%=lista.getString("valor")%></td>
	</tr>	
	<%}%>
	</logic:present>
</table>
</form>
<br/>
<center><a href="#" onclick="imagenes()" style="color:white;" >Incluir/Modificar Imagenes del Inicio</a></center>	


