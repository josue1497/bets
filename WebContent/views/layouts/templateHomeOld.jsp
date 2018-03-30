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
	document.onmousedown = right;
	
	// End -->
	
	function onLoad() {
	}
</script>  
</head>

<body onload="onLoad();" style="font-family:tahoma,helvetica,Arial,verdana,sans-serif;background-repeat:repeat-x;" background="<%=basePath%>images/back.jpg" leftmargin="10" topmargin="10" marginwidth="10" marginheight="10" vlink="#29613c" link="#29613c" bgcolor="#000000" >
<table width="1024" height="100%" border="0" cellpadding="0" cellspacing="0" align="center" style="background: url(<%=basePath%>images/home3.jpg) left top;">
    <tr>
	  	<td style="height:66px">
            <tiles:insert attribute="header"/>                
        </td>
    </tr>    
    <tr>
        <td>
            <tiles:insert attribute="body"/>	    
        </td>
    </tr>
    <tr>
        <td align="center">
            <img src="<%=basePath%>images/footer.jpg" border="0">
        </td>
    </tr>
</table>
</body>
</html:html>
