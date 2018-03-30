<%@ include file="/views/common/taglibs.jsp"%>
<%@ page import="com.betcesc.game.to.VistaTO"%>
<% ArrayList lista = (ArrayList)request.getAttribute("lista"); 
VistaTO vistaTO = null;%> 
<html>
<head>
	<title>www.betcesc.com</title>
	<script>
	function agregarImagen() {
		with(document.forma){
			idVista.value="0";
			title.value="";
			content.value="";
			status.checked=true;
		}		
		document.getElementById('caja').style.display='';		
	}
	function modificarImagen(codigo,titulo,contenido,estado,tipoImg) {
		with(document.forma){
			idVista.value=codigo;
			title.value=titulo;
			content.value=contenido;
			status.checked=(estado=="1");
			tipo.value=tipoImg;
		}		
		document.getElementById('caja').style.display='';		
	}
	function eliminarImages(codigo,titulo) {
		document.getElementById('caja').style.display='';		
		with(document.forma){
			idVista.value=codigo;
			title.value=titulo;
			if(confirm("Desea eliminar la imagen "+titulo)) {
				document.forma.eliminar.value="true";
				document.forma.submit();
			}
		}		
		document.getElementById('caja').style.display='none';		
	}
	function enviar() {
		with(document.forma){
			if(idVista.value=="0") {
				if(!title.value){
					alert("Por favor ingrese el titulo de la imagen");
					return;
				}
				if(!content.value){
					alert("Por favor ingrese el texto que estara contenido de la imagen");
					return;
				}
				if(!image.value){
					alert("Por favor seleccione una imagen con 990 pixeles de ancho y 391 pixeles de alto");
					return;
				}
			}
		}
		document.forma.submit();
	}
	</script>
</head>
<body>
<table align="center" width="940" border="0" style="border-collapse:collapse;border:1px solid #efefef;" cellspacing="3"  >
<caption class="tituloTablaSup">IMAGENES PARA EL HOME (990x391)<br/><a href="#" onclick="agregarImagen()">agregar</a></caption>
<tr style="background-color:blue;color:white;">
	<th>Titulo</th>
	<th>Contenido</th>
	<th>Imagen</th>
	<th>Status</th>
</tr>
<% 
	if(lista!=null) {
		for(int i=0; i<lista.size(); i++ ){
			vistaTO = (VistaTO)lista.get(i);%>
<tr style="background-color:#afafaf;">
	<td ><%=vistaTO.getTitle()%></td>
	<td ><%=vistaTO.getContent()%></td>
	<td width="250" height="98"><img src="<%=basePath%>dbimg/<%=vistaTO.getImage()%>" width="250" height="98"/></td>
	<td align="center">
		<%=vistaTO.getStatus().equals("1")?"Activo":"Inactivo"%><br>
		<span style="font-size:9pt;"><a href="#" onclick="modificarImagen('<%=vistaTO.getIdVista()%>','<%=vistaTO.getTitle()%>','<%=vistaTO.getContent()%>','<%=vistaTO.getStatus()%>','<%=vistaTO.getTipo()%>')">Modificar</a> / <a href="#" onclick="eliminarImages('<%=vistaTO.getIdVista()%>','<%=vistaTO.getTitle()%>','<%=vistaTO.getTipo()%>')">Eliminar</a></span>
	</td>
</tr>
<%		}
	} 
%>

</table>
<div id="caja" style="position:absolute;width:800;height:300;left:100;top:100;background-color:#eeeef0;border:3px double #0f0f0f;padding:10px;display:none;">
<div style="background-color:navy;color:white;position:absolute;left:0;top:0;padding:5;width:800;font-weight:bold;font-family:Tahoma,Helvetica,Serif;">Datos de la imagen</div>
	<br/>
	<br/>
	<form name="forma" action="<%=basePath%>processImages.do" enctype="multipart/form-data" method="post">
	<input type="hidden" name="idVista" value="0">
	<input type="hidden" name="eliminar" value="false">
	<b>Imagen</b><br>
	<input type="file" name="image" style="width:780"><br/>
	<b>Titulo</b><br>
	<input type="text" name="title" style="width:580">&nbsp;&nbsp;<input type="checkbox" name="status" value="1"><b>Activo</b><br/>
	<b>Tipo</b><br>
	<select type="text" name="tipo" style="width:580">
		<option value="0">Cuerpo (990px x 391px)</option>
		<option value="1">Encabezado (990px x 120px)</option>
		<option value="2">Atencion al cliente (389px x 142px)</option>
		<option value="3">publicidad (200px x 200px)</option>
	</select>
	<br/>
	<b>Contenido</b><br>
	<textarea style="width:780" name="content" rows="10"></textarea></br><br/>
	<input type="button" value="Guardar" onclick="enviar()">&nbsp;&nbsp;&nbsp;
	<input type="button" value="Cancelar" onclick="document.getElementById('caja').style.display='none';">
	</form>
</div>
</body>
</html>