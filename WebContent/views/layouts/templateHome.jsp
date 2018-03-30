<%@ page import="com.betcesc.game.interfaces.DeporteIF"%>
<%@ page import="com.betcesc.game.to.VistaTO"%>
<%
com.betcesc.game.bean.DominioBean dom = Constants.getDominio(request); 
ArrayList listaDeporte = (ArrayList)request.getAttribute("listaDeporte");
ArrayList listaPublicidad = (ArrayList)request.getAttribute("publicidad");
VistaTO publicidadTO = null;
DeporteIF deporte = null;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

    
<%-- Include common set of tag library declarations for each layout --%>
<%@ include file="/views/common/taglibs.jsp"%>

<html:html xhtml="true" locale="true">
<head>
  <%-- Include common set of meta tags for each layout --%>
  <%@ include file="/views/common/meta.jsp" %>
  
  <title><%=dom.getNombre()%></title>
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

<body onload="onLoad();" style="font-family:tahoma,helvetica,Arial,verdana,sans-serif;" leftmargin="10" topmargin="10" marginwidth="10" marginheight="10" vlink="#29613c" link="#29613c" bgcolor="<%=dom.getColor()%>" align="center">
<table width="990" border="0" cellpadding="0" cellspacing="0" align="center" style="background-color:<%=dom.getColorFront()%>;border-collapse:collapse;">
    <tr>
	    <td style="background-color:<%=dom.getColorFront()%>;background-repeat:no-repeat;font-size:2px;">
            <tiles:insert attribute="headerName"/> 	    
        </td>
    </tr>    
    <tr>
	  	<td style="height:120px;border:0px solid white;" >
            <tiles:insert attribute="header"/>                
        </td>
    </tr>    
    <tr>
	    <td  style="height:350px" bgcolor="<%=dom.getColorFront()%>">
            <tiles:insert attribute="body"/>	    
        </td>
    </tr>
    <%if(request.getAttribute("VISTA_HOME")!=null){%>
    <tr>
	    <td align="center">
			<table border="0" cellSpacing="0" cellPadding="0" width="100%">
				<tr>
					<td style="height:112px;text-align:left;" >
						<%
							if(dom.getIdDominio().equals("000")) {
						%> 
						<img src="<%=Constants.NOMBRE_IMAGEN_SOPORTE%>" border="0"></a>
						<%
							} else {
						%>
						<img src="<%=dom.getPathImages().concat("soporte.jpg")%>" border="0"></a>
						<%
							}
						%>
					</td>
					<td width="340px" style="background-image:url(<%=dom.getPathImages()%>logros.jpg);background-repeat:no-repeat;height:142px;" valign="top">
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
								for(int k=0;listaDeporte!=null && k<listaDeporte.size();k++) {
													deporte = (DeporteIF)listaDeporte.get(k);
							%>
								<tr>
									<td>&nbsp;
									</td>
									<td class="inicioMenu4" 
										onmouseover="this.className='inicioMenu22'" 
										onmouseout="this.className='inicioMenu4'"
										onclick="reporte('<%=deporte.getIdDeporte()%>','<%=deporte.getDescDeporte()%>')">
										<%=deporte.getDescDeporte()%>
									</td>
									<%
										if((k+1)<listaDeporte.size()){
																	deporte = (DeporteIF)listaDeporte.get(++k);
									%>
										<td class="inicioMenu4" 
											onmouseover="this.className='inicioMenu22'" 
											onmouseout="this.className='inicioMenu4'"
											onclick="reporte('<%=deporte.getIdDeporte()%>','<%=deporte.getDescDeporte()%>')">
											<%=deporte.getDescDeporte()%>
										</td> 
									<%
 										} else {
 									%>
										<td>&nbsp;</td>
									<%
										}
									%>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								</tr>
							<%
								}
							%>
						</table>
					</td>
					<td style="width:5px;height:142px;">
					</td>
					<td align="center" style="background-image:url(<%=dom.getPathImages()%>complementos.jpg);background-repeat:no-repeat;width:320px;height:142px;">
						<table border="0" width="100%" cellSpacing="0" cellPadding="0" style="color:afafaf;border:solid 0 #5f5f5f;border-collapse:collapse">
							<tr>
								<td colspan="3" style="font-size:12pt;">&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;
								</td>
								<td class="inicioMenu4" style="background-image:url(images/fondoItem.jpg);background-repeat:no-repeat;width:179px;height:18px;color:white;"
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
								<td class="inicioMenu4" style="background-image:url(images/fondoItem.jpg);background-repeat:no-repeat;width:179px;height:18px;color:white;"
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
								<td class="inicioMenu4" style="background-image:url(images/fondoItem.jpg);background-repeat:no-repeat;width:179px;height:18px;color:white;"
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
								<td class="inicioMenu4" style="background-image:url(images/fondoItem.jpg);background-repeat:no-repeat;width:179px;height:18px;color:white;"
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
    <%
    	}
    %>
    <tr>
	    <td align="center" style="background-color:<%=dom.getColorFront()%>;">
			<table border="0" cellSpacing="0" cellPadding="0" width="100%" >
				<tr>
					<td width="200px" align="left" >
						<img src="./images/ie.gif"/>
					</td>
					<td width="30px" align="left" style="font-size:10pt;color:white;font-weight:bold;">
						+18
					</td>
					<td align="center" style="font-size:10pt;color:#afafaf;font-weight:bold;">
						Contacto: <%=dom.getMail()%><br>
						Telf: <%=dom.getTelefono()%>
					</td>
					<td width="120px" align="left" style="font-size:10pt;color:#afafaf;font-weight:bold;">
						S&iacute;guenos por 
					</td>
					<td align="center" style="width:35px;cursor:pointer;" onclick="window.open('<%=dom.getTwitter()%>')">
						<img src="./images/twiter.gif"/>
					</td>
					<td align="center" style="width:13px;">
						&nbsp;
					</td>
					<td align="center" style="width:33px;cursor:pointer;display:<%=dom.getIdDominio().equals("000")?"":"none"%>;">
						<img src="./images/facebook.jpg"/>
					</td>
					<td align="center" style="width:15px;">
						&nbsp;
					</td>
				</tr>
			</table>
        </td>
    </tr>
	<%if(listaPublicidad!=null && listaPublicidad.size()>0) {%>
    <tr>
	    <td  style="height:200px" >
            <table border="0" width="100%" cellpadding="10">
            	<tr>
						<%for(int i=0; i<listaPublicidad.size(); i++ ){
							publicidadTO = (VistaTO)listaPublicidad.get(i);%>
            			<td align="center"><img src="dbimg/<%=publicidadTO.getImage()%>"></td>
            			<%}%>
            	</tr>
            </table>	    
        </td>
    </tr>
	<%}%>
</table>
</body>
</html:html>
