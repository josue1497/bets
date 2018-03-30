<%@ page import="sun.jdbc.rowset.CachedRowSet" %>
<%@ page import="com.betcesc.game.common.Constants" %>
<% CachedRowSet ticket = (CachedRowSet) request.getAttribute("ticket");
String premio = "";
String nota = "";%>
<html>
<%if(ticket.next()) {
	premio = ticket.getString("premio");
	nota = ticket.getString("ticket_nota");%>
<head>
	<title>Ticket Nro. <%=Constants.numero(ticket.getString("id_jugada"),10)%></title>
<script language="Javascript">
	<!-- Begin
	function disableselect(e){
		return true;
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
		//factory.printing.Print(false,window.parent.top);
		try {
			factory.printing.Print(false);
			//window.print();
		} catch(e) {
			alert("El Activex de Impresion no esta instalado");
		}
	}
</script>  

</head>
<body style="font-family:Tahoma;font-size:12px" onload="onLoad()">
<!-- ini MeadCo ScriptX Control -->
<object id="factory" style="display:none" viewastext 
classid="clsid:1663ED61-23EB-11D2-B92F-008048FDD814" 
codebase="http://192.168.10.36:8085/qwebds4/smsx.cab#Version=6,3,435,20">
</object>
<!-- fin MeadCo ScriptX Control -->
<br/>
Nota: expira a los (<%=ticket.getString("dias_expira")%>) dias<br/>
<%=ticket.getString("centro_hipico").toUpperCase()%><br/>
RIF: <%=ticket.getString("rif").toUpperCase()%>
<br/><br/>
Ticket Nro.:<%=Constants.numero(ticket.getString("id_jugada"),10)%><br/>
Fecha:<%=ticket.getString("dia")%><br/>
Hora:<%=ticket.getString("hora")%><br/>
SID:<%=request.getAttribute("SID")%><br/><br/>
Jugada <%=Constants.getDominio(request).getMoneda()%> <%=ticket.getString("jugada")%>

<br/><br/>
-------------------------------------
<table width="52%" border="0" cellpadding="0" cellspacing="0" style="font-family:Tahoma;font-size:11px">
	<tr>
		<td>T</td>
		<td>Can.</td>
		<td>Ref.</td>
		<td>Equipo</td>
		<td align="right">Logro</td>
	</tr>
	<tr>
		<td colspan="5">
-------------------------------------
		</td>
	</tr>
<% boolean is5to=false;
   boolean isAltaBaja=false;
do {
	is5to=ticket.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL_5);
	isAltaBaja=ticket.getString("tipo").toUpperCase().equals("A") || ticket.getString("tipo").toUpperCase().equals("B");%>
	<tr>
		<td><%=ticket.getString("tipo").toUpperCase()%></td>
		<td><%=Constants.tipoFraccion(ticket.getString("cantidad"),!ticket.getString("tipo").equals(Constants.TIPO_ALTA) && !ticket.getString("tipo").equals(Constants.TIPO_BAJA),false)%></td>
		<td>(<%=ticket.getString("referencia").toUpperCase()%>)</td>
		<td>
			<%if(ticket.getString("desc_equipo").equalsIgnoreCase("Empate")) {%>
				<%=ticket.getString("desc_equipo").concat(":").concat(ticket.getString("equipo1")).concat(" Vs ").concat(ticket.getString("equipo2"))%>
			<%} else {%>
				<%--ticket.getString("desc_equipo").concat("                        ").substring(0,14).trim().concat(" Vs ").concat(ticket.getString("equipo2").concat("    ").substring(0,4).trim()).concat("........................").substring(0,22)--%>
				<%=ticket.getString("desc_equipo").trim().concat("                        ").substring(0,20)%>
			<%}%>
		</td>
		<td align="right"><%=ticket.getString("logro").toUpperCase()%></td>
	</tr>
	<tr>
		<td colspan="2"></td>
		<td >Vs.</td>
		<td >
				<%=ticket.getString(is5to?"equipo2":"desc_equipo2").trim().concat(is5to?" (5to inning)":"").concat("........................").substring(0,22)%>
		</td>
		<td ></td>
	</tr>
	<%if(isAltaBaja) {%>
	<tr style="display:none">
		<td colspan="2"></td>
		<td >Pitc.</td>
		<td >
				<%=(ticket.getString("nombre_lanzador")==null?"":ticket.getString("nombre_lanzador")).trim().concat("                        ").substring(0,22)%>
		</td>
		<td ></td>
	</tr>
	<%}%>
	
	<%} while(ticket.next());%>
	
</table>
-------------------------------------<br/>
Premio <%=Constants.getDominio(request).getMoneda()%> <%=premio%><br/><br/><br/>
<%=Constants.justificar(nota,40)%>
<br/><br/><br/>
<br/><br/><br/>
<br/><br/><br/>
<br/>
</body>
<%} else {%>
<head>
	<title>Error - </title>
</head>
<body>
	El Ticket no fue encontrado.<br/><br/><br/>
</body>
<%}%>
</html>
