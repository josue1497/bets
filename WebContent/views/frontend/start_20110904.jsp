<%@ include file="/views/common/taglibs.jsp"%>
<html>
<head>
	<title>www.betcesc.com</title>
	<style>
	</style>
	<script language="javascript"> 
	function send(forma) {
		document.forms[0].submit();
	}
	function ventana(url){
		window.open(url,'resultado',"width=700,height=600,status=no,resizable=yes,top=50,left=100,scrollbars=yes");
	}
	</script>
</head>
<body bgcolor="#000000">
<center >
<table border="0" width="100%" height="100%" style="display:none">
<tr>
	<td width="35%" rowspan="3">&nbsp; 
	</td>
	<td width="30%" height="35%" >
		<table border="0" width="100%" height="100%">
			<tr valign="bottom">
			<td style="cursor:pointer" width="33%" align="center" onclick="document.location='<%=basePath%>listLogro.do'"><font color="white" face="Tahoma" size="-1"><b>Logros</b></font></td>
			<td style="cursor:pointer" width="34%" align="center" onclick="document.location='<%=basePath%>contactenos.do'"><font color="white" face="Tahoma" size="-1"><b>Contactenos</b></font></td>
			<td style="cursor:pointer" width="33%" align="center" onclick="document.location='<%=basePath%>startSession.do'"><font color="white" face="Tahoma" size="-1"><b>Iniciar Sessi&oacute;n</b></font></td>
			</tr>
		</table>
	</td>
	<td width="35%" rowspan="3">&nbsp;
	</td>
</tr>
<tr>
	<td align="center" height="30%">
		<img src="<%=basePath%>images/logo2.jpg" border="0">
	</td>
</tr>
<tr>
	<td height="35%">&nbsp;
	</td>
</tr>
</table>

<table cellSpacing="1" cellPadding="3" align="center" border="0" width="100%"  height="100%" class="inicio">
<tr>
	<td colspan="4">
		<table border="0" width="100%" cellSpacing="1" cellPadding="3">
			<tr>
				<td>
					<a href="<%=basePath%>registerUserPlayer.do"><img src="images/registrese.jpg" border="0"/></a>
				</td>
			</tr>
			<tr>
				<td width="130" height="200">
				</td>
				<td>
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>

</center>
</body>
</html>
<script language="javascript">
function fnc(){
	var reloj=new Date();
	var str="";
	str=reloj.getHours()+":"+reloj.getMinutes();
	document.getElementById("reloj").innerHTML=str;
	window.setTimeout("fnc()",1000);
}
fnc();
</script> 
