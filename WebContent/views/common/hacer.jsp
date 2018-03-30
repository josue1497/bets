<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<style>
	code { white-space: pre; }
	
	p.sinsaltos { white-space: nowrap; }
	
	p.normal { white-space: normal; }
	
	body {
		font-family:tahoma;
		font-size:12px;
		white-space: normal;
	}
</style>

</head>
<body>

<h1>Activar el servicio de Impresi&oacute;n</h1>
<ol>
<li>Cree una carpeta llamada printer en el dicos C:
<br><br>
<code>
    <b>C:\printer</b>
</code>
<br><br>
</li>


<li>Descargue y copie el archivo <a href="<%=basePath%>printer.exe">printer.exe</a> dentro de la carpeta c:\printer
</li>

</ol>

<h1>Habilitar el Activex en el Internet Explorer</h1>
<ol>
<li>Coloque la pagina <b>http://www.betcesc.com</b> como <b>Sitios de confianza</b> dentro del navegador
<i>(herramientas -> opciones de internet -> seguridad)</i>
<br><br>
</li>

<li>Haga click en el boton <b>Nivel personalizado</b> de <b>Sitios de confianza</b> y habilite la casilla
que corresponde a <b>Iniciar y generar scripts de los controles Activex no marcados como seguros para scripts</b> 
dentro de los <b>Controles y complementos Activex</b>
<br><br>
</li>
</ol>

</body>
</html>
