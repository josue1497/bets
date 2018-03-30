<jsp:directive.page import="com.betcesc.game.common.Constants"/><%@ include file="/views/common/taglibs.jsp"%>
<table cellpadding="0" cellspacing="0" style="height:6px" border="0" width="100%">
	<tr>
		<td>
			<font face="Tahoma" size="-1" color="#1f1f1f">
				&nbsp;&nbsp;&nbsp;<%=Constants.getFechaLarga()%>&nbsp;&nbsp;<span id="reloj" style="font-weight:bold;"></span>
			</font>
		</td>
		<td align="right">
			<table cellpadding="0" cellspacing="0" style="height:6px" border="0">
				<tr>
					<logic:present name="opcion" scope="session">
						<logic:iterate id="m" name="opcion">
							<td><img src="<%=basePath%>images/separador.gif"  width="6" height="17" /></td>
								<td style="cursor:pointer" onclick="document.location='<%=basePath%><bean:write name="m" property="accion"/>'" nowrap>
									<font face="Tahoma" size="-2" color="#815a06">&nbsp;<b>
										<bean:write name="m" property="descMenu"/>
									</b>&nbsp;
								</font></td>
						</logic:iterate>
					</logic:present>		
				</tr>
			</table>	
		</td>
	</tr>
</table>	
<script language="javascript">
function fnc(){
	var reloj=new Date();
	var str="";
	str=reloj.getHours()+":"+reloj.getMinutes()+":"+reloj.getSeconds();
	document.getElementById("reloj").innerHTML=str;
	window.setTimeout("fnc()",1000);
}
fnc();
</script> 
