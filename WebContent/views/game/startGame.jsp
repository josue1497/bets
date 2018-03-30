<%@ include file="/views/common/taglibs.jsp"%>
<html>
<head>
	<%@ include file="/views/common/meta.jsp"%>
	<script>
	function modificar() {
		document.getElementById("textoAreaInicial").style.display="";	
		document.getElementById("textoInicial").style.display="none";	
	}
	function guardarTitulo() {
		document.getElementById("textoAreaInicial").style.display="none";	
		document.getElementById("textoInicial").style.display="";
		document.formaInicio.submit();	
	}
	function modificarImage() {
		window.open("views/game/loadImage.jsp","imageSocio",'left=100;top=100;width=1000,height=470');
	}
	</script>
</head>
<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="height:500px;">
  <tr>
    <td valign="top">
		<table width="100%" cellpadding="3" cellspacing="3" border="0">
		  <tr>
			<td width="90%" align="left" colspan="2">
				<font color="white">
					<html:errors/>
				</font>
			</td>
			<td width="10%" align="right" colspan="2" >
				&nbsp;
			</td>
		  </tr>
		</table>	
	</td>
  </tr>
  <tr>
    <td valign="top" align="center">
    	<%if(request.getAttribute("AVISO_HOME")!=null && !String.valueOf(request.getAttribute("AVISO_HOME")).equals("")){%>
		<table width="100%" cellpadding="3" cellspacing="3" border="0">
			<tr>
				<td>
			    	<h3 style="color:<%=Constants.getDominio(request).getColorFont()%>;font-family:Tahoma,Helvetica,Arial,Verdana,Sans">
				    		<%
				    			if(!Constants.getDominio(request).getIdDominio().equals("000") && usuario.getIdUsuario().equals(Constants.getDominio(request).getIdAdmin()) ){
				    		%>
			    				<a href="#" onclick="modificarImage()"><span style="color:yellow">Modificar Imagen del Home</span></a><br/><br/>
				    			<a href="#" onclick="modificar()">NOTIFICACION DEL ADMINISTRADOR</a><br/>
				    			<div id="textoAreaInicial" style="display:none;"><br/>
				    			<center>
				    			<form name="formaInicio" action="<%=basePath%>updateMessage.do" method="post">
				    			<textarea name="aviso_home" style="width:80%;" rows="20"><%=request.getAttribute("AVISO_HOME")%></textarea><br/><br/>
				    			<a class="enlaceBoton" href="#" onclick="guardarTitulo()">Actualizar</a>
				    			</form>
				    			</center>
				    			</div>
				    		<%} else {%>
				    			NOTIFICACION DEL ADMINISTRADOR
				    		<%}%>
			    	</h3>
				</td>
			</tr>
			<tr id="textoInicial">
				<td>
			    	<h4 style="color:#efefef;font-family:Helvetica,Arial,Verdana,Sans">
			    	<%=request.getAttribute("AVISO_HOME")%>
			    	</h4>
				</td>
			</tr>
		</table>
    	<%}%>
    </td>
  </tr>
</table>
		</td>
	</tr>
</table>
</body>