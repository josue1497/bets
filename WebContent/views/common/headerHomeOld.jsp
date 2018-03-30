<%@ include file="/views/common/taglibs.jsp"%>
<script language="javascript"> 
function sendLogin(forma) {
	document.forms[0].submit();
}
</script>
<table width="100%" border="0" cellpadding="0" cellspacing="0" >
	<td width="6">
		<img height="66px" width="469" 
			onclick="javascript:document.location.href='<%=basePath%>endSession.do'" 
			style="cursor: pointer;" src="<%=basePath%>images/header1.jpg">	
	</td>
	<td background="<%=basePath%>images/header2.jpg"  style="background-repeat:repeat-x" align="left">
				<table border="0" cellSpacing="0" cellPadding="0" >
					<tr>
<%if(request.getSession().getAttribute("usuario")!=null) {%>	
					<td>
						<font face="Tahoma" size="-1" color="#4f4f4f">
							<b><%=Constants.PERFIL[Integer.parseInt(usuario.getIdRol())]%>&nbsp;&nbsp;&nbsp;(</b>
							<b><span style="color:white;"><%=usuario.getUsuario()%></span>&nbsp;)</b>
							<%if(Constants.ROL_JUGADOR.equals(usuario.getIdRol())){%>
							:&nbsp;<font color="#ffffff" ><b><%= usuario.getUsuario() %></b></font>
							<%}%>
						</font>
						<BR/>
						<font face="Tahoma" size="-1" color="#ffffff" style="position:absolute;">
							<%=Constants.getFechaLarga()%>&nbsp;&nbsp;&nbsp;
						</font>
					</td>
<%}%>					
					<td>
<%if(request.getSession().getAttribute("usuario")==null && false) {%>	
		<html:form action="/registerSession" method="POST" >
				<table border="0" cellSpacing="1" cellPadding="0" >
					<tr>
						<td class="letraBold" >Usuario&nbsp;:&nbsp;</td>
						<td ><html:text property="usuario" maxlength="20" size="10" style="height:11px"/></td>
					</tr>
					<tr>
						<td class="letraBold" >Clave&nbsp;:&nbsp;</td>
						<td ><html:password property="clave" maxlength="20" onkeyup="if(event.keyCode==13){sendLogin(this);}" size="10" style="height:11px"/></td>
					</tr>
					<tr align="right">
						<td class="letraBold" colspan="2"><a href="javascript:sendLogin(this.form)"><span class="letra">Entrar</span></a></td>
					</tr>
					<!--
					<tr>
						<td class="letraBold" colspan="2" valign="bottom"><a href="<%=basePath%>registerUserPlayer.do"><span class="letra">Registrarse</span></a></td>
					</tr>
					-->
				</table>
				<span id="error" style="position:absolute;left:255px;top:24px;"><html:errors/></span>
		</html:form>
<%} else {%>		
		&nbsp;
<%}%>		
					</td>
				</tr>
		</table>
	</td>
	<td width="306px" align="right" style="background-repeat:no-repeat">
			<img src="<%=basePath%>/images/header3.gif" style="cursor:pointer"  onclick="javascript:document.location.href='<%=basePath%>endSession.do'"/>
	</td>
</table>
<script language="javascript">
	if(document.registerSession){
		document.registerSession.usuario.value="";
		document.registerSession.clave.value="";
	}
</script>
