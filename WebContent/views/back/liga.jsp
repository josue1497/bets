<%@ include file="/views/common/taglibs.jsp"%>
<%@ include file="/views/common/meta.jsp"%>
<% CachedRowSet lista = (CachedRowSet)request.getAttribute("lista"); %>
<script>
function send() {
	document.forma.submit();
}
</script>
<form name="forma" method="POST" action="listLogroReportPorCentro.do">
<input type="hidden" name="idDeporte" value="<%=request.getAttribute("idDeporte")%>"/>
<input type="hidden" name="titulo" value="<%=request.getAttribute("descDeporte")%>"/>
<input type="hidden" name="isDay" value="false"/>
<input type="hidden" name="isLiga" value="true"/>
<table width="100%">
<caption >SELECCIONE LAS LIGAS QUE DESEA IMPRIMIR PARA HOY EN EL <%=request.getAttribute("descDeporte").toString().toUpperCase()%></caption>
<%while(lista!=null && lista.next()){%>
	<tr>
		<td width="3px"><input id="<%=lista.getString("id_liga")	%>" type="checkbox" name="idLiga" value="<%=lista.getString("id_liga")	%>"  /></td>
		<td class="detalleTablaLeftUpperCase"><label for="<%=lista.getString("id_liga")	%>"><%=lista.getString("desc_liga")%></label></td>
	</tr>
<%}%>
</table>
<center>
<a class="enlaceBoton" href="#" onclick="send('<%=request.getAttribute("idDeporte")%>','<%=request.getAttribute("descDeporte")%>',false,true)">aceptar</a>
</center>
</form>

