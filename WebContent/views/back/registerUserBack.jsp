<!-- registerUser.jsp -->
<%@page import="com.betcesc.game.bean.DominioBean"%>
<%@ include file="/views/common/taglibs.jsp"%>
<% UsuarioIF user=(UsuarioIF)request.getAttribute("user"); 
boolean isAdmin = user.getIdUsuario()!=null && user.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR);
boolean isAdmGen = user.getIdRol().equals(Constants.ROL_ADMINISTRADOR);
boolean isAdmTaq = user.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA);
boolean isJugTaq = user.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA);
boolean isJugNat = user.getIdRol().equals(Constants.ROL_JUGADOR);
%>
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
			correo.value=usuario.value+"@betcesc.com";
			//alert("<bean:message key="registerUser.correo"/>");
			//correo.focus();
			//return;
		}
		if((numeroCuenta.value!="")&& isNaN(numeroCuenta.value)){
			alert("<bean:message key="registerUser.numeroCuenta"/>");
			return;
		}
		submit();
	}
}

function refrescar(rol) {
	if(rol=='<%=Constants.ROL_ADMINISTRADOR%>'){
		document.location="<%=basePath%>registerUser.do";
	} else {
		document.location="<%=basePath%>registerUser.do?rol_actual="+rol;
	}
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
function seguridad() {
	document.forms[0].action='<%=basePath%>seguridad.do';
	send();
}

function combinaciones() {
	combinacion('<%=user.getIdUsuario()%>','Combinaciones por Usuario');
}
function correoDefault(valor) {
	with(document.forms[0]) {
		if((correo.value=="") || (!(isNaN(correo.value))) || (correo.value.length<3) || (correo.value.indexOf("@")==-1 || correo.value.indexOf(".")==-1)){
			correo.value=usuario.value+"@betcesc.com";
		}
	}
}
</script>

<html:form action="/registerUserSave" method="POST" >
<html:hidden name="user" property="idUsuario" />
<input type="hidden" name="eliminar" value="false"/>
<input type="hidden" name="cerrarSession" value="false"/>
<table align="center" width="700" height="100%" cellpadding="3" cellspacing="3" border="0" >
<caption class="tituloTablaSup">REGISTRO DE USUARIO<%--request.getAttribute("rol")--%></caption> 
  <tr>
	<td width="100%" align="left">
	<font color="white">
		<html:errors/>
	</font>
	</td>
  </tr>
  <tr>
  	<td align="center" width="89%" valign="top" >
		<table width="100%" height="100%" border="0" cellpadding="5" cellspacing="5" class="tablaCaption">
			<caption class="tituloCaption">Datos Personales</caption>
		  	<tr>
	  			<td class="etiqueta" ><bean:message key="usuario.idRol" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" >
			  		<html:select name="user" property="idRol" style='<%=Constants.isNull(user.getIdUsuario())?"":"display:none"%>' onchange="refrescar(this.value)" >
			  			<html:optionsCollection name="listaRol" value="idRol" label="descRol"/>
			  		</html:select>
			  		<span id="nombreUsuario" class="detalleTablaLeft">&nbsp;</span>
			  	</td>
		  	</tr>
		  	<tr style='<%=!Constants.isNull(user.getIdUsuario()) && user.getIdRol().equals(Constants.ROL_ADMINISTRADOR)?"":"display:none"%>'> 
			  	<td class="etiqueta">Tipo</td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft">
			  		<html:select name="user" property="tipo">
			  			<html:option value="0">Administrador general</html:option>
			  			<html:option value="1">Administrador Listero</html:option>
			  		</html:select>
			  	</td>
		  	</tr>
			<tr>
			  	<td class="etiqueta" width="15%"><bean:message key="usuario.cedula" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%"><html:text name="user" property="cedula" size="15" maxlength="10" /></td>
		  	</tr>
		  	<tr>
			  	<td class="etiqueta" ><bean:message key="usuario.nombre" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="nombre" size="40" maxlength="40" /></td>
		  	</tr>
		  	<tr>
	  			<td class="etiqueta" ><bean:message key="usuario.apellido" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="apellido" size="40" maxlength="40" /></td>
		  	</tr>
		  	<tr>
	  			<td class="etiqueta" ><bean:message key="usuario.usuario" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="usuario" size="15" maxlength="20" onblur="correoDefault(this.value)" /></td>
		  	</tr>
		  	<tr>
	  			<td class="etiqueta" ><bean:message key="usuario.clave" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:password  name="user" property="clave" size="15" maxlength="10" /></td>
		  	</tr>
		  	<tr>
	  			<td class="etiqueta" ><bean:message key="usuario.idStatus" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" >
			  		<html:select name="user" property="idStatus" >
			  			<html:optionsCollection name="listaStatus" value="idStatus" label="descStatus"/>
			  		</html:select>
			  	</td>
		  	</tr>
			<tr>
			  	<td class="etiqueta"><bean:message key="usuario.telefono" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%"><html:text name="user" property="telefono" size="20" maxlength="12" /></td>
		  	</tr>
		  	<tr>
			  	<td class="etiqueta"><bean:message key="usuario.celular" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%"><html:text name="user" property="celular" size="20" maxlength="12" /></td>
		  	</tr>
		  	<tr>
			  	<td class="etiqueta"><bean:message key="usuario.correo" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%"><html:text name="user" property="correo" size="40" maxlength="100" /></td>
		  	</tr>
		  	<tr style="display:<%=isJugTaq?"":"none"%>">
			  	<td class="etiqueta" width="15%"><bean:message key="usuario.centroHipico" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%"><html:text  name="user" property="centroHipico" size="40" maxlength="100" /></td>
		  	</tr>
		  	<tr>
			  	<td class="etiqueta" ><bean:message key="usuario.rif" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="rif" size="15" maxlength="12" /></td>
		  	</tr>
			<tr>
			  	<td class="etiqueta"><bean:message key="usuario.diasVencTicket" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft"><html:text name="user" property="diasVencTicket" size="5" maxlength="5" /></td>
		  	</tr>
		  	<tr>
			  	<td class="etiqueta"><bean:message key="usuario.logrosAltaBaja" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft"><html:text name="user" property="logrosAltaBaja" size="10" maxlength="4" /></td>
		  	</tr>
		  	<tr> 
			  	<td class="etiqueta"><bean:message key="usuario.logrosCalc" /></td>
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
			  		&nbsp;&nbsp;&nbsp;<span class="etiqueta">Pago:</span>
			  		<html:text name="user" property="pagoVeces" size="50" />
			  	</td>
		  	</tr>
		  	<tr> 
			  	<td class="etiqueta"><bean:message key="usuario.logrosMin" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft">
			  		<html:select name="user" property="logrosMin">
			  			<html:option value="1">1</html:option>
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
		  	<tr>
			  	<td class="etiqueta"><bean:message key="usuario.monto" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft"><html:text name="user" property="monto" size="10" maxlength="9" /></td>
		  	</tr>
		  	<tr>
			  	<td class="etiqueta">Tope por derecho</td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft"><html:text name="user" property="topePorDerecho" size="10" maxlength="9" /></td>
		  	</tr>
		  	<tr>
			  	<td class="etiqueta" nowrap nowrap>Tope por combinaci&oacute;n</td>
			  	<td class="etiqueta" nowrap nowrap>&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft"><html:text name="user" property="topePorCombinacion" size="10" maxlength="10" /></td>
		  	</tr>
		  	<tr>
	  			<td class="etiqueta" ><bean:message key="usuario.idSupervisor" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" >
			  		<html:select name="user" property="idSupervisor" >
			  			<html:optionsCollection name="listaSupervisor" value="idUsuario" label="usuario"/>
			  		</html:select>
			  	</td>
		  	</tr>
		  	<tr>
	  			<td class="etiqueta" ><bean:message key="usuario.ticketNota" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft"><html:textarea name="user" property="ticketNota" cols="40" rows=""></html:textarea>
		  	</tr>
		  	<tr style="display:<%=isAdmTaq?"":"none"%>">
			  	<td class="etiqueta" width="15%"><bean:message key="usuario.banco" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" width="35%" ><html:text  name="user" property="banco" size="40" maxlength="50" /></td>
		  	</tr>
		  	<tr style="display:<%=isAdmTaq?"":"none"%>">
			  	<td class="etiqueta" ><bean:message key="usuario.numeroCuenta" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="numeroCuenta" size="20" maxlength="20" /></td>
		  	</tr>
		  	<tr style="display:<%=isAdmTaq?"":"none"%>">
			  	<td class="etiqueta" ><bean:message key="usuario.idTipoCuenta" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" >
			  		<html:select name="user" property="idTipoCuenta" >
			  			<html:optionsCollection name="listaTipoCuenta" value="idTipoCuenta" label="descTipoCuenta"/>
			  		</html:select>
			  	</td>
		  	</tr>
			<tr style="display:<%=isAdmTaq?"":"none"%>">
			  	<td class="etiqueta"><bean:message key="usuario.titularCuenta" /></td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="titularCuenta" size="40" maxlength="50" /></td>
		  	</tr>
			<tr>
			  	<td class="etiqueta">% Com. venta<br>(Derecho)</td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="comisionVenta" size="10" maxlength="6" /></td>
		  	</tr>
			<tr>
			  	<td class="etiqueta">% Com. venta<br>(Parley)</td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="comisionVentaParley" size="10" maxlength="6" /></td>
		  	</tr>
			<tr>
			  	<td class="etiqueta">% Comis. Ganancia<br>(Derecho)</td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="otrosGastos" size="10" maxlength="6" /></td>
		  	</tr>
			<tr>
			  	<td class="etiqueta">% Comis. Ganancia<br>(Parley)</td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="otrosGastosParley" size="10" maxlength="6" /></td>
		  	</tr>
		  	<tr style='<%=!Constants.isNull(usuario.getIdUsuario()) && usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) && usuario.getTipo().equals("0")?"":"display:none"%>'> 
			  	<td class="etiqueta">Validar Maquina</td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft">
			  		<html:select name="user" property="validarMacAddress">
			  			<html:option value="0">No</html:option>
			  			<html:option value="1">Si</html:option>
			  		</html:select>
			  	</td>
		  	</tr>
		  	<tr> 
			  	<td class="etiqueta">Vencidos en reporte</td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft">
			  		<html:select name="user" property="vencidoReporte">
			  			<html:option value="0">No</html:option>
			  			<html:option value="1">Si</html:option>
			  		</html:select>
			  	</td>
		  	</tr>
		  	<tr style='display:none'> 
			  	<td class="etiqueta">Maquina</td>
			  	<td class="etiqueta">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><html:text  name="user" property="macAddress" size="50" maxlength="255" readonly="true" /></td>
		  	</tr>
		  	<tr>
	  			<td class="etiqueta" >Pago con clave</td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" >
			  		<html:select name="user" property="pagoClave" >
			  			<html:option value="0">Deshabilitado</html:option>
			  			<html:option value="1">Habilitado</html:option>
			  		</html:select>
			  	</td>
		  	</tr>
		  	<%if(!Constants.isNull(user.getIdRol()) && user.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA)) {%>
		  	<tr>
	  			<td class="etiqueta" >Abrir juego automatico</td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" >
			  		<html:select name="user" property="abrirJuego" >
			  			<html:option value="0">Deshabilitado</html:option>
			  			<html:option value="1">Habilitado</html:option>
			  		</html:select>
			  	</td>
		  	</tr>
		  	<%}%>
		  	<%if(!Constants.isNull(user.getIdRol()) && user.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA)) {%>
		  	<tr>
	  			<td class="etiqueta" >Super Runline</td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" >
			  		<html:select name="user" property="activarSuperRunline" >
			  			<html:option value="0">Deshabilitado</html:option>
			  			<html:option value="1">Habilitado</html:option>
			  		</html:select>
			  	</td>
		  	</tr>
		  	<tr>
	  			<td class="etiqueta" >Bono Perdedores</td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" >
			  		<html:select name="user" property="activarBono" >
			  			<html:option value="0">Deshabilitado</html:option>
			  			<html:option value="2">Parlay mayor o igual a 2</html:option>
			  			<html:option value="3">Parlay mayor o igual a 3</html:option>
			  			<html:option value="4">Parlay mayor o igual a 4</html:option>
			  			<html:option value="5">Parlay mayor o igual a 5</html:option>
			  			<html:option value="6">Parlay mayor o igual a 6</html:option>
			  			<html:option value="7">Parlay mayor o igual a 7</html:option>
			  			<html:option value="8">Parlay mayor o igual a 8</html:option>
			  			<html:option value="9">Parlay mayor o igual a 9</html:option>
			  		</html:select>
			  	</td>
		  	</tr>
		  	<%}%>
		  	<tr>
	  			<td class="etiqueta" >Eliminar jugadas</td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" >
			  		<html:select name="user" property="eliminarJugada" >
			  			<html:option value="0">Hasta una hora para eliminar</html:option>
			  			<html:option value="1">Puede eliminar cualquier jugada</html:option>
			  		</html:select> (Solo para los listeros)
			  	</td>
		  	</tr>
		  	<tr>
	  			<td class="etiqueta" >Apuestas por Equipo</td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" >
			  		<html:select name="user" property="apuestaEquipo" >
			  			<html:option value="0">Deshabilitado</html:option>
			  			<html:option value="1">Habilitado</html:option>
			  		</html:select>
			  	</td>
		  	</tr>
		  	<tr style="display:<%=isAdminGeneral?"":"none"%>" >
	  			<td class="etiqueta" >Dominio</td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" >
			  		<html:select name="user" property="dominio" >
			  			

<%--                         <html:option value="000"><%=DominioBean.DOMINIO_000%></html:option> --%>
<%-- 			  			<html:option value="001"><%=DominioBean.DOMINIO_001%></html:option> --%>
<%-- 			  			<html:option value="002"><%=DominioBean.DOMINIO_002%></html:option> --%>
<%-- 			  			<html:option value="003"><%=DominioBean.DOMINIO_003%></html:option> --%>
<%--                         <html:option value="004"><%=DominioBean.DOMINIO_004%></html:option> --%>
<%--                         <html:option value="005"><%=DominioBean.DOMINIO_005%></html:option> --%>
<%--                         <html:option value="006"><%=DominioBean.DOMINIO_006%></html:option> --%>
<%--                         <html:option value="007"><%=DominioBean.DOMINIO_007%></html:option> --%>
<%--                         <html:option value="008"><%=DominioBean.DOMINIO_008%></html:option> --%>
			  		</html:select>
			  	</td>
		  	</tr>
  		</table>
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
		<%if(String.valueOf(request.getAttribute("rol")).equals(Constants.ROL_ADMINISTRADOR) && usuario.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR)){%>
		&nbsp;&nbsp;&nbsp;
		<a class="enlaceBoton" href="#" onClick="seguridad()">Seguridad</a>
		<%}%>
	</logic:notEqual>
	<a class="enlaceBoton" href="#" onClick="combinaciones()">Combinaciones</a>
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
