<%@ include file="/views/common/taglibs.jsp"%>
<%@ page import="com.betcesc.game.interfaces.ParametrosIF" %>
<script language="javascript">
function send(){
	with(document.forms[0]){
		if((clave.value=="") || (clave.value.length<5)){
			alert("Debe ingresar la clave actual no menor a 5(cinco) digitos");
			clave.focus();
			return;
		}
		if((clave1.value=="") || (clave1.value.length<5)){
			alert("Debe ingresar la nueva clave no menor a 5(cinco) digitos");
			clave1.focus();
			return;
		}
		if((clave2.value=="") || (clave2.value.length<5)){
			alert("Confirme la nueva clave no menor a 5(cinco) digitos");
			clave2.focus();
			return;
		}
		if((clave1.value!=clave2.value)){
			alert("La confirmación de la clave es diferente");
			clave2.focus();
			return;
		}
		submit();
	}
}

</script>

<form action="<%=basePath%>changePasswordSave.do" method="POST" ><br/><br/><br/>
<table align="center" width="60%" height="100%" cellpadding="3" cellspacing="3" border="0" >
<caption class="tituloTablaSup">CAMBIO DE CLAVE</caption> 
  <tr>
	<td width="100%" align="left" colspan="3">
	<font color="white">
		<html:errors/>
	</font>
	</td>
  </tr>
  <tr>
  	<td align="center" width="49%" valign="top" colspan="3">
		<table width="100%" height="100%" border="0" cellpadding="10" cellspacing="5" class="tablaCaption" style="background-repeat:no-repeat;background-position:top right;" bgcolor="#000000">
			<caption class="tituloCaption">INGRESE LOS DATOS A CONTINUACION</caption>
		  	<tr>
			  	<td class="etiqueta" width="50%" >Clave Actual</td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><input type="password"  name="clave" size="15" maxlength="20" /></td>
		  	</tr>
		  	<tr>
			  	<td class="etiqueta" >Nueva Clave</td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><input type="password"  name="clave1" size="15" maxlength="20" /></td>
		  	</tr>
		  	<tr>
			  	<td class="etiqueta" >Confirme la Nueva Clave</td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="etiquetaLeft" ><input type="password"  name="clave2" size="15" maxlength="20" /></td>
		  	</tr>
  		</table>
  	</td>
  </tr>
</table>	
<form>
<center>
<a class="enlaceBoton" href="#" onClick="send()"><bean:message key="boton.modificar"/></a>
&nbsp;&nbsp;&nbsp;
<a class="enlaceBoton" href="<%=basePath%>inicio.do"	><bean:message key="boton.cancelar"/></a>
</center>
