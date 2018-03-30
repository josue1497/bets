<%@ include file="/views/common/taglibs.jsp"%>
<link href="../../../css/style<%=Constants.getDominio(request).getIdDominio()%>.css" rel="stylesheet" type="text/css" />
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr class="tituloFicha">
        <td width="1%" align="left"><img src="images/ficha_left.jpg" width="12" height="25"></td>
        <td width="40%" align="center" background="images/ficha_center.jpg" class="tituloFicha">Error 403</td>
        <td width="1%"><img src="images/ficha_right.gif" width="40" height="25"></td>
        <td width="58%" valign="bottom" align="right" >&nbsp;</td>
    </tr>
</table>
<table  width="100%" class="borderFicha" cellspacing="1" cellpadding="5" > <!-- tabla 3-->
	<TR><TD width="100%" align="left" class="main_b_textmenu">
	<TABLE cellpadding="5" cellspacing="5" background="images/mensajes.jpg" class="borderFicha" width="100%">
		<TR>
		  <TD colspan="2" class="textoFicha">
	      <div align="center">Lo sentimos, la solicitud no puede ser procesada, intente nuevamente </div>
		  </TD>
		  
		  </TR>
		
		<TR>
		  <TD colspan="2" class="textoFicha"><div align="center">
		      <a onclick="document.location='<%=basePath%>start.do'">Aceptar</a>
			 </div></TD>
		  </TR>
	</TABLE>
	</TD>
	</TR>
</TABLE>
