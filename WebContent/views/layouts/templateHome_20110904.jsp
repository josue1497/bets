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
<table width="989" border="1" cellpadding="0" cellspacing="0" align="center">
    <tr>
	  	<td style="height:88px;border:0px solid white;">
            <tiles:insert attribute="header"/>                
        </td>
    </tr>    
    <tr>
	    <td style="height:22px" background="<%=basePath%>images/head2.jpg" style="background-repeat:no-repeat;" >
            <tiles:insert attribute="headerName"/>	    
        </td>
    </tr>    
    <tr>
	    <td  style="height:564px" background="<%=basePath%>images/head3.jpg" style="background-repeat:no-repeat;" >
            <tiles:insert attribute="body"/>	    
        </td>
    </tr>
</table>
</body>
</html:html>
