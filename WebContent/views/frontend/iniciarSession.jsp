<%@ include file="/views/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Documento sin t&iacute;tulo</title>
</head>

<body bgcolor="#000000">
<table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0">
  <tr>
  	<td style="height:66px" colspan="3">
		<table width="100%" border="1" cellpadding="0" cellspacing="0">
		<td width="469"><img src="<%=basePath%>/images/header1.jpg" width="469" height="66" /></td>
		<td background="<%=basePath%>images/header2.jpg">&nbsp;</td>
		<td width="457"><img src="<%=basePath%>images/header3.jpg" width="457" height="66" /></td>
		</table>
	</td>
  </tr>
  <tr>
    <td style="height:6px" background="<%=basePath%>images/barra1.jpg" style="background-repeat:repeat-x" colspan="3" align="right" >
		<table cellpadding="0" cellspacing="0" style="height:6px">
			<tr>
				<td><img src="<%=basePath%>images/separador.gif"  width="6" height="17" /></td>
				<td><font face="Tahoma" size="-1" color="#0000cd">&nbsp;&nbsp;&nbsp;<b>Inicio</b>&nbsp;&nbsp;&nbsp;</font></td>
				<td><img src="<%=basePath%>images/separador.gif"  width="6" height="17" /></td>
				<td><font face="Tahoma" size="-1" color="#0000cd">&nbsp;&nbsp;&nbsp;<b>Registro</b>&nbsp;&nbsp;&nbsp;</font></td>
				<td><img src="<%=basePath%>images/separador.gif"  width="6" height="17" /></td>
				<td><font face="Tahoma" size="-1" color="#0000cd">&nbsp;&nbsp;&nbsp;<b>Saldos</b>&nbsp;&nbsp;&nbsp;</font></td>
				<td><img src="<%=basePath%>images/separador.gif"  width="6" height="17" /></td>
				<td><font face="Tahoma" size="-1" color="#0000cd">&nbsp;&nbsp;&nbsp;<b>Jugadas</b>&nbsp;&nbsp;&nbsp;</font></td>
				<td><img src="<%=basePath%>images/separador.gif"  width="6" height="17" /></td>
				<td><font face="Tahoma" size="-1" color="#0000cd">&nbsp;&nbsp;&nbsp;<b>Juegos</b>&nbsp;&nbsp;&nbsp;</font></td>
				<td><img src="<%=basePath%>images/separador.gif"  width="6" height="17" /></td>
				<td><font face="Tahoma" size="-1" color="#0000cd">&nbsp;&nbsp;&nbsp;<b>Logros</b>&nbsp;&nbsp;&nbsp;</font></td>
				<td><img src="<%=basePath%>images/separador.gif"  width="6" height="17" /></td>
				<td style="cursor:pointer" onclick="document.location='<%=basePath%>start.do'"><font face="Tahoma" size="-1" color="#0000cd">&nbsp;&nbsp;&nbsp;<b>Salir</b>&nbsp;&nbsp;&nbsp;</font></td>
				<td><img src="<%=basePath%>images/separador.gif"  width="6" height="17" /></td>
			</tr>
		</table>	</td>
  </tr>
  <tr style="height:6px">
    <td background="<%=basePath%>images/barra2.jpg" style="background-repeat:repeat-x" colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td align="center"><img src="<%=basePath%>images/logro3.jpg" width="275" height="400" /></td>
    <td>
		<table width="100%" cellpadding="3" cellspacing="3" border="1">
		  <tr>
			<td width="50%" align="right"><font face="Tahoma" size="-1" color="#0000cd"><b>Usuario :</b>&nbsp;</font></td>
			<td width="50%"><input type="text" /></td>
		  </tr>
		  <tr>
			<td align="right"><font face="Tahoma" size="-1" color="#0000cd"><b>Clave :</b>&nbsp;</font></td>
			<td><input type="password" /></td>
		  </tr>
		  <tr>
			<td align="center" colspan="2">
				&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;
					<a href="#" 
						style="width:80px;font-family:Tahoma;font-weight:bold;font-size:13px;color:#0000cd;background-color:#efefef;text-decoration:none">
						&nbsp;&nbsp;&nbsp;Entrar&nbsp;&nbsp;&nbsp;
					</a>
				&nbsp;&nbsp;&nbsp;
					<a href="<%=basePath%>start.do"
						style="width:80px;font-family:Tahoma;font-weight:bold;font-size:13px;color:#0000cd;background-color:#efefef;text-decoration:none">
						&nbsp;&nbsp;&nbsp;&nbsp;Salir&nbsp;&nbsp;&nbsp;&nbsp;
					</a>
			</td>
		  </tr>
		  <tr>
			<td align="center">&nbsp;
			</td>
			<td align="center">&nbsp;
			</td>
		  </tr>
		  <tr>
			<td align="right" colspan="2">
				<img src="<%=basePath%>images/entrada.gif" width="500" height="258" />
			</td>
		  </tr>
		</table>	
	</td>
  </tr>
</table>
</body>
</html>
