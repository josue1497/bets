<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

    
<%-- Include common set of tag library declarations for each layout --%>
<%@ include file="/views/common/taglibs.jsp"%>
<html:html xhtml="true" locale="true">
<head>
  <%-- Include common set of meta tags for each layout --%>
  <%@ include file="/views/common/meta.jsp" %>
  
  <title><%=Constants.getDominio(request).getNombre()%></title>
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
</script>  
</head>

<body style="font-family:tahoma,helvetica,Arial,verdana,sans-serif" bgcolor="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" vlink="#29613c" link="#29613c">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
	  	<td style="height:66px">
            <tiles:insert attribute="header"/>                
        </td>
    </tr>    
	<tr style="height:6px">
    	<td background="<%=basePath%>images/barra2.jpg" style="background-repeat:repeat-x" colspan="3">&nbsp;</td>
	</tr>
    <tr>
        <td>
            <tiles:insert attribute="body"/>	    
        </td>
    </tr>
</table>
</body>
</html:html>
