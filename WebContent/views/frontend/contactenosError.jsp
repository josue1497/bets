<%@ include file="/views/common/taglibs.jsp"%>
<script language="javascript" >
function regresarPpal() {
	document.location.href="start.do";
}
</script>
<link href="../../../css/style<%=Constants.getDominio(request).getIdDominio()%>.css" rel="stylesheet" type="text/css" />
<table  width="100%" class="borderFicha" cellspacing="1" cellpadding="5" > <!-- tabla 3-->
	<TR><TD width="100%" align="left" class="main_b_textmenu">
	<TABLE cellpadding="5" cellspacing="5" background="images/mensajes.jpg" class="borderFicha" width="100%">
		<TR>
		  <TD colspan="2" class="detalleTablaLeft">
	      <div align="center">Lo sentimos, su correo no pudo ser enviado, intente nuevamente </div>
		  </TD>
		  
		  </TR>
		
		<TR>
		  <TD colspan="2" class="textoFicha"><div align="center">
			<a class="enlaceBoton cursorClick" onclick="regresarPpal()">Salir</a>
			 </div></TD>
		  </TR>
	</TABLE>
	</TD>
	</TR>
</TABLE>
