<jsp:directive.page import="com.betcesc.game.common.Constants"/><%@ include file="/views/common/taglibs.jsp"%>
<%int i=0;%>
<table cellpadding="0" cellspacing="0" style="height:10px" border="0" width="100%">
	<tr>
		<td align="right">
			<table cellpadding="0" cellspacing="0" style="height:10px;padding-top:4px;" border="0" > 
				<tr>
					<logic:present name="menuInicio" scope="request">
						<logic:iterate id="m" name="menuInicio">
							<%i++;%>
							<td style="font-family:Tahoma;cursor:pointer;font-size:9pt;color:#ffffff;font-weight:plain;"
									onmouseover="this.style.color='red';"  onmouseout="this.style.color='white';"
									onclick="document.location='<%=basePath%><bean:write name="m" property="accion"/>'" nowrap>
									<%=i>1?"<span style='color:#ffffff;'>|</span>":""%>&nbsp;<bean:write name="m" property="descMenu"/>&nbsp;
							</td>
						</logic:iterate>
					</logic:present>		
				</tr>
			</table>	
		</td>
	</tr>
</table>	
