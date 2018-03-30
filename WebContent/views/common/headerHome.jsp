<%@ include file="/views/common/taglibs.jsp"%>
<script>
function sendLogin(forma) {
	document.forms[0].macAddress.value = document.locatorMac.document.body.innerHTML;
	document.forms[0].submit();
}
</script>
<table border="0" cellpadding="0" cellspacing="0" style="width:990;height:120;margin:0px;background:url(<%=Constants.getDominio(request).getPathImages()%>head1.jpg) top left;" >
	<td width="989" style="cursor:pointer;height:120px;">
		<table align="right">
			<html:form action="/registerSession" method="POST" >
				<input type="hidden" name="dominio" value="<%=Constants.isNull(request.getParameter("dominio"),"")%>"/>
				<input type="hidden" name="macAddress" value="" /> 
			<tr>
				<td align="right" style="font-size:8pt;;font-face:Helvetica,Tahoma,Arial;color:#afafaf;" valign="top">
					<%=Constants.getFechaLargaSinAno()%>&nbsp;&nbsp;&nbsp;&nbsp;<span id="reloj" style="font-weight:bold;"></span>
				</td>
				<td align="Right" class="inicio" nowrap>
					<table border="0" height="65" cellSpacing="0" cellPadding="0" >
						<tr>
							<td style="width:5px;"></td>
							<td style="width:80px;font-weight:bold;font-size:16px;" align="center">Usuario</td>
							<td style="width:8px;"></td>
							<td style="width:80px;font-weight:bold;font-size:16px;" align="center" >Contrase&ntilde;a</td>
							<td style="width:25px;"></td>
							<td style="width:80px;"></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="7"  align="left">
								<table cellSpacing="0" cellPadding="0" border="0" height="35"
									style="background-image:url(images/login.png);background-repeat:no-repeat;">
								<tr >
									<td style="width:14px;"></td>
									<td style="width:80px;" align="left"><html:text property="usuario" maxlength="20" size="9" style="height:18px;border:1px solid white;"/></td>
									<td style="width:27px;"></td>
									<td style="width:80px;" align="left" ><html:password property="clave" maxlength="20" onkeyup="if(event.keyCode==13){sendLogin(this.form);}" size="9" style="height:18px;border:1px solid white;"/></td>
									<td style="width:18px;"></td>
									<td style="width:120px;" onclick="sendLogin(this.form)" style="cursor:pointer;"></td>
									<td></td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="7"  align="left">
								<font color="white">
									<html:errors/>
								</font>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			</html:form>
		
		</table>

	</td>
</table>
<iframe id='locatorMac' name='locatorMac' style="width:0;height:0;display:none;" src="<%=basePath%>views/common/locator.html" ></iframe>