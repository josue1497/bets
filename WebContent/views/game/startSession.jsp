<%@ include file="/views/common/taglibs.jsp"%>
<script language="javascript">
function send(forma) {
	document.forms[0].submit();
}
</script>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td style="width:200px">&nbsp;</td>
		<td>
<html:form action="/registerSession" method="POST" >
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" background="<%=basePath%>images/logo3.jpg" style="height:500px;background-repeat:no-repeat">
  <tr>
    <td align="center">&nbsp;</td>
    <td valign="top" align="right" >
		<table width="40%" cellpadding="3" cellspacing="3" border="0">
		  <tr>
			<td width="100%" align="left" colspan="2">
			<font color="white">
				<html:errors/>
			</font>
			</td>
		  </tr>
		  <tr>
			<td width="70%" class="etiqueta"><bean:message key="usuario.usuario"/> : </td>
			<td width="30%"><html:text property="usuario" maxlength="20"/></td>
		  </tr>
		  <tr>
			<td class="etiqueta"><bean:message key="usuario.clave"/> : </td>
			<td><html:password property="clave" maxlength="20" onkeyup="if(event.keyCode==13){send(this);}"/></td>
		  </tr>
		  <tr>
			<td align="right" colspan="2">
				<a class="enlaceBoton" href="#" onclick="send(this.form)" ><bean:message key="boton.entrar"/></a>
				&nbsp;&nbsp;&nbsp;
				<a class="enlaceBoton" href="<%=basePath%>start.do"	><bean:message key="boton.salir"/></a>&nbsp;
			</td>
		  </tr>
		  <tr>
			<td align="center">&nbsp;
			</td>
			<td align="center">&nbsp;
			</td>
		  </tr>
		</table>	
	</td>
    <td align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  </tr>
</table>
</html:form>
		</td>
	</tr>
</table>
<script language="javascript">
	document.forms[0].elements[0].focus();
</script>
