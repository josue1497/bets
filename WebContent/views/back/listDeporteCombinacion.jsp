<!-- listDeporteCombinacion.jsp -->
<%@ include file="/views/common/taglibs.jsp"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>

<%@ include file="/views/common/meta.jsp"%>
<% CachedRowSet lista = (CachedRowSet)request.getAttribute("lista"); %>
<script>
function main(){
	<%if(session.getAttribute("MENSAJE")!=null){%>
		alert('<%=session.getAttribute("MENSAJE")%>');
	<%  session.removeAttribute("MENSAJE");
	}%>
}
</script>
<body bgcolor="black" style="color:white;font-family:Helvetica,Arial,Verdana;font-weight:bold;" onload="main()">
<form name="forma" action="./registerDeporteCombinacion.do">
<table width="100%" border="0" style="border:1px solid #000;">
<caption>Lista de Combinaciones para los Deportes</caption>
	<tr>
		<th>Deporte</th>
		<th>Combinaci&oacute;n</th>
	</tr>
	<%while(lista.next()){%>
		<input type="hidden" name="idDeporte" value="<%=lista.getString("id_deporte")%>" />
		<input type="hidden" name="idUsuario" value="<%=request.getParameter("idUsuario")%>" />
	<tr>
		<td width="25%"><%=lista.getString("desc_deporte")%></td>
		<td width="75%"><input type="text" size="120" name="combinacion" maxlength="700" value="<%=lista.getString("combinacion_user")!=null?lista.getString("combinacion_user"):""%>" /></td>
	</tr>
	<%}%>
</table>
<br/>
<center>
	<a class="enlaceBoton" href="javascript:void(0);" onClick="submit()">Aplicar Cambios</a>
</center>
</form>
</body>
