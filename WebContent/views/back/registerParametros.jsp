<%@ include file="/views/common/taglibs.jsp"%>
<%@ page import="com.betcesc.game.interfaces.ParametrosIF" %>
<% ParametrosIF parametros=(ParametrosIF)request.getAttribute("parametros"); %>
<script language="javascript">
function send(){
	with(document.forms[0]){
		if((nombre.value=="") || (!(isNaN(nombre.value))) || (nombre.value.length<3)){
			alert("<bean:message key="registerParametros.nombre"/>");
			nombre.focus();
			return;
		}
		//if((valor.value=="") || (!(isNaN(valor.value))) || (valor.value.length<3)){
		//	alert("<bean:message key="registerParametros.valor"/>");
		//	valor.focus();
		//	return;
		//}
		submit();
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
</script>
<center>
<html:form action="/registerParametrosSave" method="POST" >
<input type="hidden" name="eliminar" value="false"/>
<input type="hidden" name="cerrarSession" value="false"/>
<table align="center" width="60%" height="100%" cellpadding="3" cellspacing="3" >
<caption class="tituloTablaSup">REGISTRO DE PARAMETROS</caption> 
  <tr>
	<td width="100%" align="left" >
	<font color="white">
		<html:errors/>
	</font>
	</td>
  </tr>
  <tr>
  	<td align="center" width="49%" valign="top">
		<table width="100%" height="100%" border="0" cellpadding="3" cellspacing="5" class="tablaCaption" style="background-repeat:no-repeat;background-position:top right;" bgcolor="#000000">
			<caption class="tituloCaption">Configuraci&oacute;n</caption>
		  	<tr>
			  	<td class="etiqueta" >Nombre</td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" >
			  		<html:text  name="parametros" property="nombre" maxlength="40" readonly="true" style="width:700px;"/>
			  	</td>
		  	</tr>
		  	<%if(parametros.getNombre().startsWith("IMAGEN_")){%>
			  	<tr>
				  	<td class="etiqueta" >Imagen</td>
				  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
				  	<td class="etiquetaLeft" >subir una imagen</td>
			  	</tr>
		  	<%} else {%>
			  	<tr>
				  	<td class="etiqueta" >Valor</td>
				  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
				  	<td class="etiquetaLeft" ><html:textarea  name="parametros" property="valor" rows="20" cols="80" style="width:700px;" /></td>
			  	</tr>
		  	<%}%>
  		</table>
  	</td>
  </tr>
</table>	
</html:form>

<a class="enlaceBoton" href="#" onClick="send()"><bean:message key="boton.modificar"/></a>
&nbsp;&nbsp;&nbsp;
<a class="enlaceBoton" href="<%=basePath%>listParametros.do"	><bean:message key="boton.cancelar"/></a>
</center>
