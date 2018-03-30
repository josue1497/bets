<%@ include file="/views/common/taglibs.jsp"%>
<script language="javascript" >
function regresarPpal() {
	document.location.href="start.do";
}

function validarDatos() {
	with(document.Contactenos) {
		if(isEmpty(nombre,"el nombre")) { return false;}
		if(isEmptyMail(email,"el correo electronico")) { return false;}
		if(isEmpty(mensaje,"el mensaje y el numero de telefono que desea enviar")) { return false;}
		if (confirm("¿Está seguro de enviar este mensaje?")) {
			if (mensaje.value.length>=1000) mensaje.value = mensaje.value.substring(0,999);
			submit();
		}
	} 
}
function init() {
	document.Contactenos.nombre.focus();
}
window.onload=init;
	
</script>
<html:form action="/contactenosEnviar" method="POST">
<table align="center" width="58%" height="100%" cellpadding="3" cellspacing="3">
<tr>
<td>
<fieldset>
<legend class="etiqueta" >Concatenos</legend>
	<table width="100%" border="0" cellpadding="2" cellspacing="0" align="center">
		<tr height="7" valign="top">
			<td align="center"><html:errors /></td>
		</tr>
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="2" cellspacing="0" align="center">
		    <tr>
		    <TD>
		<table width="100%" border="0" cellspacing="1" cellpadding="1" class="borderFicha">
		<tr>
		<td>
		<table width="97%" border="0" cellpadding="0" cellspacing="0" align="center">
		    <tr>
		        <td align="justify" class="detalleTablaLeft">
		            <p align="justify">
		            Comun&iacute;cate con nosotros por correo para cualquier observaci&oacute;n o sugerencia sobre nuestro servicio o si deseas,
		            directamente por los telefonos 0414-925.72.59 / 0426-519.46.12 
		            <br><br>
		            </p>
		        </td>
		    </tr>
		</table>						
		<table width="100%" border="0" cellspacing="1" cellpadding="1">
				<tr>
					<td valign="top" colspan="4" align="left">
						<table width="100%" border="0" cellpadding="1" cellspacing="0" align="center">
							<tr>
								<td align="right" class="detalleTablaRight">
									Nombre :&nbsp;
								</td>
								<td align="left">
									<html:text property="nombre" size="40" maxlength="250" value="" onkeyup="nextFocus(this,event)" />&nbsp;<span class="campo_obligatorio">(*)</span>
							   	</td>
							</tr>

							<tr>
								<td align="right" class="detalleTablaRight">
									Su Correo Electr&oacute;nico :&nbsp;
								</td>
								<td align="left">
									<html:text property="email" size="40" maxlength="150" value="" onkeyup="nextFocus(this,event)" />&nbsp;<span class="campo_obligatorio">(*)</span>
							 	</td>
							</tr>

		                    <tr>
						      	<td align="right" class="detalleTablaRight" valign="top">
						      		Mensaje y n&uacute;mero de telefono para comunicarnos inmediatamente :&nbsp;
						      	</td>
								<td align="left">
									<html:textarea property="mensaje" cols="30" rows="5" value="" onkeydown="if(this.value.length >= 1000){ this.value = this.value.substring(0,999); return false; }"  />
									&nbsp;<span class="campo_obligatorio">(*)</span>
								</td>
			 		        </tr>
						</table>
					</td>
				</tr>
				<TD class="detalleTablaRight"><div align="center">&nbsp;</div>
	        </TD>
	    </TR>
		
		</TABLE>&nbsp;
		<CENTER>
		&nbsp;&nbsp;
		<a class="enlaceBoton cursorClick" onclick="validarDatos()" >Enviar</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a class="enlaceBoton cursorClick" onclick="regresarPpal()">Cancelar</a>
					&nbsp;&nbsp;		
		</CENTER>
		<br>
			</tr></td>
		</table>
    </TD></TR></TABLE>
</TD></TR></TABLE>
</fieldset>
</TD></TR></TABLE>
</html:form>
