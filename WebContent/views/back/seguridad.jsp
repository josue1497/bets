<%@ include file="/views/common/taglibs.jsp"%>
<%@ page import="com.betcesc.game.interfaces.ParametrosIF" %>
<% CachedRowSet menu = (CachedRowSet)request.getAttribute("menues");%>
<script language="javascript">
function send(){
	with(document.forms[0]){
		menues.value="";
		menu[0].checked=true;
		var s="";
		for(var i=0; i<menu.length;i++) {
			if(menu[i].checked){
				menues.value+=s+menu[i].value;
				s=",";
			}
		}

		s="";		
		usuarios.value="";
		var usuario = document.frames[0].document.forms[0].usuario;
		for(var k=0; k<usuario.length;k++) {
			if(usuario[k].checked){
				usuarios.value+=s+usuario[k].value;
				s=",";
			}
		}

		submit();
	}
		
}

</script>

<form action="<%=basePath%>seguridadSave.do" name="seguridad" method="POST" >
<input type="hidden" name="menues"/>
<input type="hidden" name="idUsuario" value="<%=request.getParameter("idUsuario")%>">
<input type="hidden" name="usuarios" value="">
<table align="center" width="100%" height="100%" cellpadding="3" cellspacing="3" border="0" >
<caption class="tituloTablaSup">CONFIGURACION DE ACCESOS PARA EL ADMINISTRADOR<br/>(<%=request.getParameter("usuario")%>)</caption> 
  <tr>
	<td width="100%" align="left">
	<font color="white">
		<html:errors/>
	</font>
	</td>
  </tr>
  <tr>
  	<td align="center" width="49%" valign="top">
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="5" class="tablaCaption" style="background-repeat:no-repeat;background-position:top right;" >
			<caption class="tituloCaption">SELECCIONE LOS PERMISOS PARA EL USUARIO</caption>
		  	<tr>
			<%while(menu!=null && menu.next()){%>
			  	<td class="etiqueta">
			  		<input type="checkbox" name="menu" value="<%=menu.getString("id_menu")%>" <%=!menu.getString("activo").equals("0") || menu.getString("id_menu").equals("1")?"checked":""%>/>
			  	</td>
			  	<td class="etiquetaLeft"><%=menu.getString("desc_menu")%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		  	<%}%>
		  	</tr>
  		</table>
  	</td>
  </tr>
  <tr>
  	<td align="center" width="49%" valign="top">
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="tablaCaption" style="background-repeat:no-repeat;background-position:top right;" >
			<caption class="tituloCaption">ASIGNE LOS USUARIOS QUE MANEJA EL ADMINISTRADOR</caption>
		  	<tr>
		  		<td>
		  			<iframe src="<%=basePath%>listUserFrame.do?security=true&idUsuario=<%=request.getParameter("idUsuario")%>&tipo=<%=request.getParameter("tipo")%>" width="100%" height="350"></iframe>
		  		</td>
		  	</tr>
  		</table>
  	</td>
  </tr>
</table>	
<form>
<center>
<a class="enlaceBoton" href="#" onClick="send()"><bean:message key="boton.modificar"/></a>
&nbsp;&nbsp;&nbsp;
<a class="enlaceBoton" href="<%=basePath%>listUser.do"	><bean:message key="boton.cancelar"/></a>
</center>
