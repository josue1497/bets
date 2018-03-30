<!-- registerUserPlayer.jsp -->
<%@ include file="/views/common/taglibs.jsp"%>
<% UsuarioIF user=(UsuarioIF)request.getAttribute("user"); %>
<script language="javascript">
function sendInfo(){
	with(document.registerUser){
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
		
		if (!reglas.checked) { alert("Antes de registrarse debe aceptar las reglas de juego");	
			reglas.focus();
			return false;
		}
		submit();
	}
}

function plantilla(tipo) {
		document.registerUser.action="registerUser.do";
		document.registerUser.rol_actual.value=tipo;
		document.registerUser.submit();
}

function llenar(pos,lanzador) {
	accion="llenar";
	opcion = pos;
	opcionLanzador = lanzador;
	var cadena = "";
	with(document.registerUser) {
		cadena = (pos==1?idDeporte.value:cadena);
		cadena = (pos==2?idLiga.value:cadena);
		cadena = (pos==3?document.getElementById("idEquipo"+lanzador).value:cadena);
		http = new Ajax("<%=basePath%>llenarAjax.do?table="+pos+"&id="+cadena);
		http.setMetodo("POST");
		http.start();
	}
}

</script>

<html:form action="/registerUserSavePlayer" method="POST" >
<html:hidden name="user" property="idUsuario" />
<input type="hidden" name="rol_actual" value=""/>
<input type="hidden" name="eliminar" value="false"/>
<input type="hidden" name="cerrarSession" value="false"/>
<table align="center" width="989px" height="100%" cellpadding="3" cellspacing="3">
<tr>
<td>
<table align="center" width="530" height="100%" cellpadding="3" cellspacing="3" border="0">
<caption class="tituloTablaSup">REGISTRO DE USUARIO JUGADOR<br><span style="font-size:10px;text-transform:lower;">(*) datos obligatorios</span></caption>
  <tr>
	<td width="100%" align="left" colspan="3">
	<font color="white">
		<html:errors/>
	</font>
	</td>
  </tr>
  <tr>
  	<td align="center" width="100%" valign="top">
		<table width="100%" height="100%" border="0" cellpadding="2" cellspacing="5" class="tablaCaption" >
			<caption class="tituloCaption">Datos Personales</caption>
		  	<tr class="none">
	  			<td class="etiqueta" ><bean:message key="usuario.idRol" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" >
			  		<html:hidden name="user" property="idRol" value="<%=Constants.ROL_JUGADOR%>"/>
			  		<span id="nombreUsuario" class="detalleTablaLeft">&nbsp;</span>
			  	</td>
		  	</tr>
			<tr>
			  	<td class="etiqueta" width="5%">(*) <bean:message key="usuario.cedula" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%"><html:text name="user" property="cedula" size="15" maxlength="10" />
	                <span class="help">Por favor, introduzca su cedula.<span class="help-point">&nbsp;</span></span>
			  	</td>
		  	</tr>
		  	<tr>
			  	<td class="etiqueta" >(*) <bean:message key="usuario.nombre" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="nombre" size="40" maxlength="40" />
	                <span class="help">Por favor, introduzca su nombre.<span class="help-point">&nbsp;</span></span>
			  	</td>
		  	</tr>
		  	<tr>
	  			<td class="etiqueta" >(*) <bean:message key="usuario.apellido" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="apellido" size="40" maxlength="40" />
	                <span class="help">Por favor, introduzca su apellido.<span class="help-point">&nbsp;</span></span>
			  	</td>
		  	</tr>
		  	<tr>
	  			<td class="etiqueta" >(*) <bean:message key="usuario.usuario" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="usuario" size="15" maxlength="10" />
	                <span class="help">Por favor, introduzca el alias que usara en el sistema.<span class="help-point">&nbsp;</span></span>
			  	</td>
		  	</tr>
		  	<tr>
	  			<td class="etiqueta" >(*) <bean:message key="usuario.clave" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:password  name="user" property="clave" size="15" maxlength="10" />
	                <span class="help">Por favor, introduzca una clave no menor a <%=Constants.PASSWORD_MIN_LENGTH%> digitos.<span class="help-point">&nbsp;</span></span>
			  	</td>
		  	</tr>
		  	<tr class="none">
	  			<td class="etiqueta" ><bean:message key="usuario.idStatus" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" >
			  		<html:hidden name="user" property="idStatus" value='1'/>Activo
			  	</td>
		  	</tr>
		  	<tr class="none">
	  			<td class="etiqueta" ><bean:message key="usuario.idSupervisor" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" >
			  		<html:hidden name="user" property="idSupervisor" value='2' />
			  	</td>
		  	</tr>
			<tr>
			  	<td class="etiqueta" width="5%"><bean:message key="usuario.telefono" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%"><html:text name="user" property="telefono" size="20" maxlength="12" />
	                <span class="help">Por favor, introduzca un n&uacute;mero de telefono local de contacto.<span class="help-point">&nbsp;</span></span>
			  	</td>
		  	</tr>
		  	<tr>
			  	<td class="etiqueta" width="5%"><bean:message key="usuario.celular" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%"><html:text name="user" property="celular" size="20" maxlength="12" />
	                <span class="help">Por favor, introduzca un n&uacute;mero de telefono celular de contacto.<span class="help-point">&nbsp;</span></span>
			  	</td>
		  	</tr>
		  	<tr>
			  	<td class="etiqueta" width="5%">(*) <bean:message key="usuario.correo" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%"><html:text name="user" property="correo" size="40" maxlength="100" />
	                <span class="help">Por favor, introduzca un correo electronico valido.<span class="help-point">&nbsp;</span></span>
			  	</td>
		  	</tr>
		  	<tr style="display:none">
			  	<td class="etiqueta" width="15%"><bean:message key="usuario.centroHipico" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%"><html:text  name="user" property="centroHipico" size="40" maxlength="100" /></td>
		  	</tr>
		  	<tr style="display:none">
			  	<td class="etiqueta" ><bean:message key="usuario.rif" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="rif" size="15" maxlength="12" /></td>
		  	</tr>
			<tr style="display:none">
			  	<td class="etiqueta"><bean:message key="usuario.diasVencTicket" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft"><html:text name="user" property="diasVencTicket" size="3" maxlength="2" /></td>
		  	</tr>
		  	<tr style="display:none">
			  	<td class="etiqueta"><bean:message key="usuario.logrosAltaBaja" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft"><html:text name="user" property="logrosAltaBaja" size="10" maxlength="3" /></td>
		  	</tr>
		  	<tr class="none"> 
			  	<td class="etiqueta"><bean:message key="usuario.logrosCalc" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft">
			  		<html:hidden name="user" property="logrosCalc" value="4"/>
			  	</td>
		  	</tr>
		  	<tr style="display:none">
			  	<td class="etiqueta"><bean:message key="usuario.monto" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft"><html:text name="user" property="monto" size="10" maxlength="5" /></td>
		  	</tr>
		  	<tr style="display:none">
	  			<td class="etiqueta" ><bean:message key="usuario.ticketNota" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft"><html:textarea name="user" property="ticketNota" cols="40" rows=""></html:textarea>
		  	</tr>
		  	<tr>
			  	<td class="etiqueta" width="15%"><bean:message key="usuario.banco" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%" ><html:text  name="user" property="banco" size="40" maxlength="50" />
	                <span class="help">Por favor, introduzca el nombre del banco<br>donde posee una cuenta bancaria este<br>sera utilizado en las futuras transferencias <br>a su cuenta.<span class="help-point">&nbsp;</span></span>
			  	</td>
		  	</tr>
		  	<tr>
			  	<td class="etiqueta" ><bean:message key="usuario.numeroCuenta" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="numeroCuenta" size="20" maxlength="20" />
	                <span class="help">Por favor, introduzca su numero de cuenta bancaria <br>para fururos reembolsos.<br><br>No realizamos debitos automaticos a su cuenta<br> ya que no tenemos conexion directa con<br> ninguno de los bancos, esta informaci&oacute;n<br> es solo referencial<span class="help-point">&nbsp;</span></span>
			  	</td>
		  	</tr>
		  	<tr>
			  	<td class="etiqueta" ><bean:message key="usuario.idTipoCuenta" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" >
			  		<html:select name="user" property="idTipoCuenta" >
			  			<html:optionsCollection name="listaTipoCuenta" value="idTipoCuenta" label="descTipoCuenta"/>
			  		</html:select>
	                <span class="help">Por favor, seleccione el tipo de cuenta.<span class="help-point">&nbsp;</span></span>
			  	</td>
		  	</tr>
			<tr>
			  	<td class="etiqueta"><bean:message key="usuario.titularCuenta" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="titularCuenta" size="40" maxlength="50" />
	                <span class="help">Por favor, ingrese el titular de la cuenta.<span class="help-point">&nbsp;</span></span>
			  	</td>
		  	</tr>
	        <tr>
	          <td colspan="3" class="etiqueta" style="padding-left:20px;padding-right:20px;padding-bottom:10px;">
	          	<div align="left" >
	              <input name="reglas" type="checkbox" id="reglas" value="checkbox" onkeyup="nextFocus(this,event)" />
	            	Al reg&iacute;strarme aqu&iacute; declaro haber le&iacute;do las <a href="<%=basePath%>reglas.do"><font color="white">Reglas del Juego</font></a> y declaro ser mayor de edad y estar de acuerdo con las Reglas. </div>
	           </td>
	        </tr>
		  	
  		</table>
  	</td>
  </tr>
</table>	
</td>
</tr>
</table>
</html:form>
<center>
<logic:equal name="modificar" value="true">
	<a class="enlaceBoton" href="#" onClick="sendInfo()"><bean:message key="boton.modificar"/></a>
	<logic:notEqual name="user" property="idUsuario" value="1">
		&nbsp;&nbsp;&nbsp;
		<a class="enlaceBoton" href="#" onClick="eliminar()"><bean:message key="boton.eliminar"/></a>
		&nbsp;&nbsp;&nbsp;
		<a class="enlaceBoton" href="#" onClick="cerrarSession()"><bean:message key="boton.cerrarSession"/></a>
	</logic:notEqual>
</logic:equal>
<logic:notEqual name="modificar" value="true">
	<a class="enlaceBoton" href="#" onClick="sendInfo()"><bean:message key="boton.agregar"/></a>
</logic:notEqual>
&nbsp;&nbsp;&nbsp;
<a class="enlaceBoton" href="<%=basePath%>listUser.do"	><bean:message key="boton.cancelar"/></a>
</center>
<%if(!Constants.isNull(user.getIdUsuario())) {%>
<script language="javascript">
	document.getElementById("nombreUsuario").innerHTML=document.registerUser.idRol.options[document.registerUser.idRol.selectedIndex].text;
</script>
<%}%>

<script language='javascript'>



window.onload =function(){
	addEventForInput();
}
</script>
