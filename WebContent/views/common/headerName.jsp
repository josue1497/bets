<jsp:directive.page import="com.betcesc.game.common.Constants"/><%@ include file="/views/common/taglibs.jsp"%>
<table cellpadding="0" cellspacing="0" style="height:18px;background-image: url(<%=Constants.getDominio(request).getPathImages()%>barraTit1.gif);" border="0" width="100%">
	<tr>
		<td>&nbsp;
		</td>
		<td>
			<table cellpadding="0" cellspacing="0" border="0" align="right">
				<tr><logic:present name="menu" scope="session"> 
						<logic:iterate id="m" name="menu">
							<td><img src="<%=basePath%>images/separador.gif" /></td>
							<td style="cursor:pointer;border-bottom:0px solid #afafaf;"" onclick="document.location='<%=basePath%><bean:write name="m" property="accion"/>?isModulo=true'">
								<font face="Tahoma" size="-1" color="#ffffff">&nbsp;&nbsp;<b>
									<bean:write name="m" property="descMenu"/>
								</b>&nbsp;&nbsp;
							</font></td>
						</logic:iterate>
					</logic:present>
							<td><img src="<%=basePath%>images/separador.gif" /></td>
							<td style="cursor:pointer;border-right:1px solid #afafaf;border-bottom:0px solid #afafaf;"" onclick="document.location='<%=basePath%>endSession.do'">
								<font face="Tahoma" size="-1" color="#ffffff">&nbsp;&nbsp;&nbsp;<b>
									Salir
								</b>&nbsp;&nbsp;&nbsp;
								</font>
							</td>
				</tr>
			</table>	
		</td>
	</tr>
</table>	
			
