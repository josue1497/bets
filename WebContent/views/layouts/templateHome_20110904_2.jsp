<%@ page import="com.betcesc.game.interfaces.DeporteIF"%>
<% ArrayList listaDeporte = (ArrayList)request.getAttribute("listaDeporte");  
DeporteIF deporte = null;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 

    
<%-- Include common set of tag library declarations for each layout --%>
<%@ include file="/views/common/taglibs.jsp"%>

<html:html xhtml="true" locale="true">
<head>
  <%-- Include common set of meta tags for each layout --%>
  <%@ include file="/views/common/meta.jsp" %>
  
  <title><tiles:getAsString name="title"/></title>
<script language="Javascript">
	<!-- Begin
	function disableselect(e){
		return false
	}
	function reEnable(){
	return true
	}
	document.onselectstart=new Function ("return true")
	if (window.sidebar){
	document.onmousedown=disableselect
	document.onclick=reEnable
	}
	
	function right(e) {
	var msg = "Opcion no habilitada";
	if (navigator.appName == 'Netscape' && e.which == 3) {
	alert(msg);  // Delete this line to disable but not alert user
	return false;
	}
	else
	if (navigator.appName == 'Microsoft Internet Explorer' && event.button==2) {
	alert(msg); // Delete this line to disable but not alert user
	return false;
	}
	return true;
	}
	//document.onmousedown = right;
	
	// End -->
	
	function onLoad() {
		history.go(+1);
	}
</script>  
</head>

<body onload="onLoad();" style="font-family:tahoma,helvetica,Arial,verdana,sans-serif;" leftmargin="10" topmargin="10" marginwidth="10" marginheight="10" vlink="#29613c" link="#29613c" bgcolor="#000000" align="center">
<table width="989" border="0" cellpadding="0" cellspacing="0" align="center">
    <tr>
	    <td style="background-color:#000;background-repeat:no-repeat;font-size:2px;" >
            <tiles:insert attribute="headerName"/>	    
        </td>
    </tr>    
    <tr>
	  	<td style="height:120px;border:0px solid white;">
            <tiles:insert attribute="header"/>                
        </td>
    </tr>    
    <tr>
	    <td  style="height:350px" background="<%=basePath%>images/head3.jpg" style="background-repeat:no-repeat;" >
            <tiles:insert attribute="body"/>	    
        </td>
    </tr>
    <tr>
	    <td align="center">
			<table border="0" cellSpacing="0" cellPadding="0" width="100%">
				<tr>
					<td style="height:112px;text-align:left;" >
						<img src="<%=basePath%>images/soporte.jpg" border="0">
					</td>
					<td style="width:5px;height:142px;">
					</td>
					<td width="314px" style="background-image:url(images/logros.jpg);background-repeat:no-repeat;height:142px;" valign="top">
						<table border="0" width="100%" cellSpacing="1" cellPadding="0" style="color:afafaf;border:solid 0 #5f5f5f;border-collapse:collapse">
								<tr>
									<td height="50">&nbsp;
									</td>
									<td>&nbsp;
									</td>
									<td>&nbsp;
									</td>
									<td>&nbsp;
									</td>
								</tr>
							<%
							for(int k=0;k<listaDeporte.size();k++) {
								deporte = (DeporteIF)listaDeporte.get(k);%>
								<tr>
									<td>&nbsp;
									</td>
									<td class="inicioMenu4" 
										onmouseover="this.className='inicioMenu22'" 
										onmouseout="this.className='inicioMenu2'"
										onclick="reporte('<%=deporte.getIdDeporte()%>','<%=deporte.getDescDeporte()%>')">
										<%=deporte.getDescDeporte()%>
									</td>
									<%if((k+1)<listaDeporte.size()){
										deporte = (DeporteIF)listaDeporte.get(++k);
										%>
										<td class="inicioMenu4" 
											onmouseover="this.className='inicioMenu22'" 
											onmouseout="this.className='inicioMenu2'"
											onclick="reporte('<%=deporte.getIdDeporte()%>','<%=deporte.getDescDeporte()%>')">
											<%=deporte.getDescDeporte()%>
										</td> 
									<%} else {%>
										<td>&nbsp;</td>
									<%}%>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								</tr>
							<%}%>
						</table>
					</td>
					<td style="width:5px;height:142px;">
					</td>
					<td align="center" style="background-image:url(images/complementos.jpg);background-repeat:no-repeat;width:198px;height:142px;">
						<table border="0" width="100%" cellSpacing="0" cellPadding="0" style="color:afafaf;border:solid 0 #5f5f5f;border-collapse:collapse">
							<tr>
								<td colspan="3" style="font-size:12pt;">&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;
								</td>
								<td class="inicioMenu4" style="background-image:url(images/fondoItem.jpg);background-repeat:no-repeat;width:179px;height:18px;"
									onclick="ventana('<%=basePath%>resultados.do')">&nbsp;
									RESULTADOS DEL DIA ANTERIOR
								</td>
								<td>&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan="3" style="font-size:4pt;">&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;
								</td>
								<td class="inicioMenu4" style="background-image:url(images/fondoItem.jpg);background-repeat:no-repeat;width:179px;height:18px;"
									onclick="javascript:document.location.href='<%=basePath%>download/manualoperador.doc'">&nbsp;
									MANUAL DEL OPERADOR
								</td>
								<td>
								</td>
							</tr>
							<tr>
								<td colspan="3" style="font-size:4pt;">&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;
								</td>
								<td class="inicioMenu4" style="background-image:url(images/fondoItem.jpg);background-repeat:no-repeat;width:179px;height:18px;"
									onclick="javascript:document.location.href='<%=basePath%>download/TeamViewer_Setup_es.exe'">&nbsp;
									ASISTENCIA REMOTA
								</td>
								<td>&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan="3" style="font-size:4pt;">&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;
								</td>
								<td class="inicioMenu4" style="background-image:url(images/fondoItem.jpg);background-repeat:no-repeat;width:179px;height:18px;"
									onclick="ventana('<%=basePath%>download/index.jsp')">&nbsp;
									DESCARGAS
								</td>
								<td>&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
        </td>
    </tr>
    <tr>
	    <td align="center">
			<table border="0" cellSpacing="0" cellPadding="0" width="100%" >
				<tr>
					<td width="200px" align="left" >
						<img src="./images/ie.jpg"/>
					</td>
					<td width="30px" align="left" style="font-size:10pt;color:white;font-weight:bold;">
						+18
					</td>
					<td align="center" style="font-size:10pt;color:#afafaf;font-weight:bold;">
						Todos los derechos reservados www.betcesc.com
					</td>
					<td width="120px" align="left" style="font-size:10pt;color:#afafaf;font-weight:bold;">
						S&iacute;guenos por
					</td>
					<td align="center" style="width:35px;cursor:pointer;" onclick="window.open('http://twitter.com/BetCesc')">
						<img src="./images/twiter.jpg"/>
					</td>
					<td align="center" style="width:13px;">
						&nbsp;
					</td>
					<td align="center" style="width:33px;cursor:pointer;">
						<img src="./images/facebook.jpg"/>
					</td>
					<td align="center" style="width:15px;">
						&nbsp;
					</td>
				</tr>
			</table>
        </td>
    </tr>
    <tr>
	    <td  style="height:200px" >
            <table border="0" width="100%" height="100%" cellpadding="10">
            	<tr>
            		<td align="center" width="25%"><img src="./publicidad/publicidad1.jpg"></td>
            		<td align="center" width="25%"><img src="./publicidad/publicidad2.jpg"></td>
            		<td align="center" width="25%"><img src="./publicidad/publicidad3.jpg"></td>
            		<td align="center" width="25%"><img src="./publicidad/publicidad4.jpg"></td>
            	</tr>
            </table>	    
        </td>
    </tr>
</table>
</body>
</html:html>
