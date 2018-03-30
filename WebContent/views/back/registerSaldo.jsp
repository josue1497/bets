<%@ include file="/views/common/taglibs.jsp"%>
<script language="javascript">
function send(pos){
	with(document.forms[0]){
		if(pos==2) {
			action="listUserPlayDetail.do";
		} else {
			if(isEmptyInt(monto,"El Monto",2,50,100000000)) return false;
			if(isEmpty(concepto,"El motivo o concepto de la operacion")) return false;

		}
		submit();
	}
}
</script>

<html:form action="/registerSaldoSave" method="POST" >
<html:hidden name="cuenta" property="idJugador"/>
<html:hidden name="cuenta" property="idUsuario"/>
<table align="center" width="98%" height="100%" cellpadding="3" cellspacing="3" border="0">
<caption class="tituloTablaSup">REGISTRO EN CUENTA DE JUGADOR</caption>
  <tr>
	<td width="100%" align="left">
	<font color="white">
		<html:errors/>
	</font>
	</td>
  </tr>
  <tr>
  	<td align="center" width="100%" valign="top">
		<table width="50%" height="100%" border="0" cellpadding="3" cellspacing="5" align="center" class="tablaCaption">
			<caption class="tituloCaption">Datos del Movimiento</caption>
			<tr>
			  	<td class="etiquetaLeft" width="5%"><bean:message key="cuentaJugador.idJugador" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%"><bean:write name="cuenta" property="nombreJugador"/></td>
		  	</tr>
			<tr>
			  	<td class="etiquetaLeft" width="5%"><bean:message key="cuentaJugador.operacion" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%">
			  		<html:select property="operacion" >
			  			<html:option value="I">Ingreso</html:option>
			  			<html:option value="E">Egreso</html:option>
			  		</html:select>
			  	</td>
		  	</tr>
			<tr>
			  	<td class="etiquetaLeft" width="5%"><bean:message key="cuentaJugador.referencia" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%"><html:text property="referencia" size="15" maxlength="10" /></td>
		  	</tr>
			<tr>
			  	<td class="etiquetaLeft" width="5%"><bean:message key="cuentaJugador.monto" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%"><html:text property="monto" size="15" maxlength="10" /></td>
		  	</tr>
			<tr>
			  	<td class="etiquetaLeft" width="5%"><bean:message key="cuentaJugador.concepto" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%"><html:text property="concepto" size="60" maxlength="100" /></td>
		  	</tr>
  		</table>
  	</td>
  </tr>
</table>	
</html:form>
<center>
<a class="enlaceBoton" href="#" onClick="send(2)"><bean:message key="boton.detalle"/></a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a class="enlaceBoton" href="#" onClick="send(1)"><bean:message key="boton.agregar"/></a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a class="enlaceBoton" href="<%=basePath%>listUserPlay.do"	><bean:message key="boton.cancelar"/></a>
</center>
