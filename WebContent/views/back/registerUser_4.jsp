<!-- registerUser_4.jsp -->
<%@ include file="/views/common/taglibs.jsp"%>
<% UsuarioIF user=(UsuarioIF)request.getAttribute("user"); %>
<script language="javascript">
function send(){
	with(document.forms[0]){
		if((cedula.value=="")||(isNaN(cedula.value))||(parseInt(cedula.value)<3000000)||(cedula.value.length<=3)){
			alert("<bean:message key="registerUser.cedula"/>");
			cedula.focus();
			return;
		}
		if((nombre.value=="") || (!(isNaN(nombre.value))) || (nombre.value.length<3)){
			alert("<bean:message key="registerUser.nombre"/>");
			nombre.focus();
			return;
		}
		if((apellido.value=="") || (!(isNaN(apellido.value))) || (apellido.value.length<3)){
			alert("<bean:message key="registerUser.apellido"/>");
			apellido.focus();
			return;
		}
		if((usuario.value=="") || (usuario.value.length<3)){
			alert("<bean:message key="registerUser.usuario"/>");
			usuario.focus();
			return;
		}
		if(clave.value ==""){
			alert("<bean:message key="registerUser.clave"/>");
			if(clave.value.length<<%=Constants.PASSWORD_MIN_LENGTH%>){
				alert("<bean:message key="registerUser.clave1"/>");
				clave.focus();
			}
			clave.focus();
			return;
		}
		if(isNaN(logrosAltaBaja.value)){
			alert("<bean:message key="registerUser.logrosAltaBaja"/>");
			logrosAltaBaja.focus();
			return;
		}
		if(isNaN(monto.value)){
			alert("<bean:message key="registerUser.monto"/>");
			monto.focus();
			return;
		}
		if(isNaN(telefono.value)){
			alert("<bean:message key="registerUser.telefono"/>");
			telefono.focus();
			return;
		}
		if(isNaN(celular.value)){
			alert("<bean:message key="registerUser.celular"/>");
			celular.focus();
			return;
		}
		if((correo.value=="") || (!(isNaN(correo.value))) || (correo.value.length<3) || (correo.value.indexOf("@")==-1 || correo.value.indexOf(".")==-1)){
			alert("<bean:message key="registerUser.correo"/>");
			correo.focus();
			return;
		}
		if((numeroCuenta.value!="")&& isNaN(numeroCuenta.value)){
			alert("<bean:message key="registerUser.numeroCuenta"/>");
			return;
		}
		submit();
	}
}

function plantilla(tipo) {
		document.forms[0].action="registerUser.do";
		document.forms[0].rol_actual.value=tipo;
		document.forms[0].submit();
}

function llenar(pos,lanzador) {
	accion="llenar";
	opcion = pos;
	opcionLanzador = lanzador;
	var cadena = "";
	with(document.forms[0]) {
		cadena = (pos==1?idDeporte.value:cadena);
		cadena = (pos==2?idLiga.value:cadena);
		cadena = (pos==3?document.getElementById("idEquipo"+lanzador).value:cadena);
		http = new Ajax("<%=basePath%>llenarAjax.do?table="+pos+"&id="+cadena);
		http.setMetodo("POST");
		http.start();
	}
}

function eliminar() {
	document.forms[0].eliminar.value='true';
	send();
}
function cerrarSession() {
	document.forms[0].cerrarSession.value='true';
	send();
}
</script>

<html:form action="/registerUserSave" method="POST" >
<html:hidden name="user" property="idUsuario" />
<input type="hidden" name="rol_actual" value=""/>
<input type="hidden" name="eliminar" value="false"/>
<input type="hidden" name="cerrarSession" value="false"/>
<table align="center" width="50%" height="100%" cellpadding="3" cellspacing="3">
<caption class="tituloTablaSup">REGISTRO DE USUARIO JUGADOR</caption>
  <tr>
	<td width="100%" align="left" colspan="3">
	<font color="white">
		<html:errors/>
	</font>
	</td>
  </tr>
  <tr>
  	<td align="center" width="49%" valign="top">
  		<fieldset>
  		<legend class="etiqueta">Datos Personales</legend>
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="5">
		  	<tr>
	  			<td class="etiquetaLeft" ><bean:message key="usuario.idRol" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" >
			  		<html:select name="user" property="idRol" style='<%=Constants.isNull(user.getIdUsuario())?"":"display:none"%>' onchange="plantilla(this.value)">
			  			<html:optionsCollection name="listaRol" value="idRol" label="descRol"/>
			  		</html:select>
			  		<span id="nombreUsuario" class="detalleTablaLeft">&nbsp;</span>
			  	</td>
		  	</tr>
			<tr>
			  	<td class="etiquetaLeft" width="5%"><bean:message key="usuario.cedula" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%"><html:text name="user" property="cedula" size="15" maxlength="10" /></td>
		  	</tr>
		  	<tr>
			  	<td class="etiquetaLeft" ><bean:message key="usuario.nombre" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="nombre" size="40" maxlength="40" /></td>
		  	</tr>
		  	<tr>
	  			<td class="etiquetaLeft" ><bean:message key="usuario.apellido" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="apellido" size="40" maxlength="40" /></td>
		  	</tr>
		  	<tr>
	  			<td class="etiquetaLeft" ><bean:message key="usuario.usuario" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="usuario" size="15" maxlength="10" /></td>
		  	</tr>
		  	<tr>
	  			<td class="etiquetaLeft" ><bean:message key="usuario.clave" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:password  name="user" property="clave" size="15" maxlength="10" /></td>
		  	</tr>
		  	<tr>
	  			<td class="etiquetaLeft" ><bean:message key="usuario.idStatus" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" >
			  		<html:select name="user" property="idStatus" >
			  			<html:optionsCollection name="listaStatus" value="idStatus" label="descStatus"/>
			  		</html:select>
			  	</td>
		  	</tr>
		  	<tr>
	  			<td class="etiquetaLeft" ><bean:message key="usuario.idSupervisor" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" >
			  		<html:select name="user" property="idSupervisor" >
			  			<html:optionsCollection name="listaSupervisor" value="idUsuario" label="usuario"/>
			  		</html:select>
			  	</td>
		  	</tr>
  		</table>
  		</fieldset>
  		<font size="1">A&nbsp;</font>
  		<fieldset>
  		<legend class="etiqueta">Datos de Contacto</legend>
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="5">
			<tr>
			  	<td class="etiquetaLeft" width="5%"><bean:message key="usuario.telefono" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%"><html:text name="user" property="telefono" size="20" maxlength="12" /></td>
		  	</tr>
		  	<tr>
			  	<td class="etiquetaLeft" width="5%"><bean:message key="usuario.celular" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%"><html:text name="user" property="celular" size="20" maxlength="12" /></td>
		  	</tr>
		  	<tr>
			  	<td class="etiquetaLeft" width="5%"><bean:message key="usuario.correo" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%"><html:text name="user" property="correo" size="40" maxlength="100" /></td>
		  	</tr>
  		</table>
  		</fieldset>
  	</td>
  <td align="center" width="2%">
  		&nbsp;
  	</td>
  	<td align="center" valign="top" >
  		<fieldset>
  		<legend class="etiqueta">Configuracion de Juego</legend>
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="5">
		  	<tr style="display:none">
			  	<td class="etiquetaLeft" width="15%"><bean:message key="usuario.centroHipico" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%"><html:text  name="user" property="centroHipico" size="40" maxlength="100" /></td>
		  	</tr>
		  	<tr style="display:none">
			  	<td class="etiquetaLeft" ><bean:message key="usuario.rif" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="rif" size="15" maxlength="12" /></td>
		  	</tr>
			<tr style="display:none">
			  	<td class="etiquetaLeft"><bean:message key="usuario.diasVencTicket" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft"><html:text name="user" property="diasVencTicket" size="3" maxlength="2" /></td>
		  	</tr>
		  	<tr style="display:none">
			  	<td class="etiquetaLeft"><bean:message key="usuario.logrosAltaBaja" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft"><html:text name="user" property="logrosAltaBaja" size="10" maxlength="3" /></td>
		  	</tr>
		  	<tr> 
			  	<td class="etiquetaLeft"><bean:message key="usuario.logrosCalc" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft">
			  		<html:select name="user" property="logrosCalc">
			  			<html:option value="2">2</html:option>
			  			<html:option value="3">3</html:option>
			  			<html:option value="4">4</html:option>
			  			<html:option value="5">5</html:option>
			  			<html:option value="6">6</html:option>
			  			<html:option value="7">7</html:option>
			  			<html:option value="8">8</html:option>
			  			<html:option value="9">9</html:option>
			  			<html:option value="10">10</html:option>
			  			<html:option value="11">11</html:option>
			  			<html:option value="12">12</html:option>
			  			<html:option value="13">13</html:option>
			  			<html:option value="14">14</html:option>
			  			<html:option value="15">15</html:option>
			  			<html:option value="16">16</html:option>
			  			<html:option value="17">17</html:option>
			  			<html:option value="18">18</html:option>
			  			<html:option value="19">19</html:option>
			  			<html:option value="20">20</html:option>
			  		</html:select>
			  	</td>
		  	</tr>
		  	<tr style="display:none">
			  	<td class="etiquetaLeft"><bean:message key="usuario.monto" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft"><html:text name="user" property="monto" size="10" maxlength="5" /></td>
		  	</tr>
		  	<tr style="display:none">
	  			<td class="etiquetaLeft" ><bean:message key="usuario.ticketNota" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft"><html:textarea name="user" property="ticketNota" cols="40" rows=""></html:textarea>
		  	</tr>
  		</table>
  		</fieldset><br/>
  		<fieldset>
  		<legend class="etiqueta">Datos Bancarios</legend>
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="6">
		  	<tr>
			  	<td class="etiquetaLeft" width="15%"><bean:message key="usuario.banco" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%" ><html:text  name="user" property="banco" size="40" maxlength="50" /></td>
		  	</tr>
		  	<tr>
			  	<td class="etiquetaLeft" ><bean:message key="usuario.numeroCuenta" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="numeroCuenta" size="20" maxlength="20" /></td>
		  	</tr>
		  	<tr>
			  	<td class="etiquetaLeft" ><bean:message key="usuario.idTipoCuenta" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" >
			  		<html:select name="user" property="idTipoCuenta" >
			  			<html:optionsCollection name="listaTipoCuenta" value="idTipoCuenta" label="descTipoCuenta"/>
			  		</html:select>
			  	</td>
		  	</tr>
			<tr>
			  	<td class="etiquetaLeft"><bean:message key="usuario.titularCuenta" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="titularCuenta" size="40" maxlength="50" /></td>
		  	</tr>
  		</table>
  		</fieldset>
  	</td>
  </tr>
</table>	
</html:form>
<center>
<logic:equal name="modificar" value="true">
	<a class="enlaceBoton" href="#" onClick="send()"><bean:message key="boton.modificar"/></a>
	<logic:notEqual name="user" property="idUsuario" value="1">
		&nbsp;&nbsp;&nbsp;
		<a class="enlaceBoton" href="#" onClick="eliminar()"><bean:message key="boton.eliminar"/></a>
		&nbsp;&nbsp;&nbsp;
		<a class="enlaceBoton" href="#" onClick="cerrarSession()"><bean:message key="boton.cerrarSession"/></a>
	</logic:notEqual>
</logic:equal>
<logic:notEqual name="modificar" value="true">
	<a class="enlaceBoton" href="#" onClick="send()"><bean:message key="boton.agregar"/></a>
</logic:notEqual>
&nbsp;&nbsp;&nbsp;
<a class="enlaceBoton" href="<%=basePath%>listUser.do"	><bean:message key="boton.cancelar"/></a>
</center>
<%if(!Constants.isNull(user.getIdUsuario())) {%>
<script language="javascript">
	document.getElementById("nombreUsuario").innerHTML=document.forms[0].idRol.options[document.forms[0].idRol.selectedIndex].text;
</script>
<%}%>
