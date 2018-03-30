<%@ include file="/views/common/taglibs.jsp"%>
<center>
<style>
.upload {
	position:relative;
	width:0px;
}
.realupload {
	position:absolute;
	top:0;
	right:0;

	/* start of transparency styles */
	opacity:0;
	-moz-opacity:0;
	filter:alpha(opacity:0);
	/* end of transparency styles */

	z-index:2; /* bring the real upload interactivity up front */
	width:270px;
}
</style>
<script>
function enviar() {
	document.cargar.action="<%=basePath%>loadImage.do?load=true";
	document.cargar.submit();
}
</script>
<form name="cargar" enctype="multipart/form-data" method="post" style="margin:0px;">
<input type="hidden" name="load" value="true" />
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td style="height:391px;text-align:center;" valign="middle">
			<br/>
			<div style="height:391px;width:990;border:1px solid blue;text-align:center;">
			<%if(session.getAttribute("image")!=null){%>
				<img src="<%=basePath%><%=session.getAttribute("image")%>" border="0" width="990" height="391">
			<%} else {%>
				<img src="<%=basePath%>dbimg/home<%=Constants.getDominio(request).getIdDominio()%>.jpg" border="0" width="990" height="391">
			<%}%>
			</div>
		</td>
	</tr>
	<tr>
		<td align="center">
				<label for="realupload" style="background-color:#0f0f0f;color:white;padding:5px;cursor:pointer;">Haga click para cambiar la imagen</label>
				<input type="file" name="upload" id="realupload" class="realupload" onchange="enviar();" />
		</td>
	</tr>
	<tr>
		<td align="center">
			<h5>(tama&ntilde;o de la imagen debe ser 990 x 391 PIXELS y con extension jpg)</h5>
		</td>
	</tr>
</table>
</form>
</center>