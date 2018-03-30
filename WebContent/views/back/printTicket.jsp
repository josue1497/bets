<%@ page import="sun.jdbc.rowset.CachedRowSet" %>
<%@ page import="com.betcesc.game.common.Constants" %>
<%@ page import="com.jade.util.tools.StringUtils" %>
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
	function getCookie(nombre) {
		a = document.cookie.substring(document.cookie.indexOf( nombre + '=') + nombre.length + 1,document.cookie.length);
		if(a.indexOf(';') != -1)a = a.substring(0,a.indexOf(';'))
		return a;
	} 
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
	//document.onmousedown = right;
	
	// End -->

	function onLoad() {
		var total="";
		var cad = document.forma.texto.value;
		cad = cad.replace(/\r/gi,""); //.replace(/\n/gi,"");
		cad = cad.split("\n");
		//alert(cad.length);
		for(var i=0;i<cad.length;i++){
			cad[i] = cad[i]==''?" ":cad[i];
			total=total+"_"+cad[i];
		}
		for(var k=0;k<7;k++){
			total=total+"_"+" ";
		}
		
		//alert(total);
		var consola = new ActiveXObject("WScript.Shell");
		//consola.run('c:\\printer\\hacer.bat '+total+' ');
		//alert('c:\\printer\\printer.exe \"'+total+'\" '+getCookie("puertoTicket"));
		consola.run('c:\\printer\\printer.exe \"'+total+'\" '+getCookie("puertoTicket"));
		
	}
</script>  

</head>
<body  onload="onLoad()">
<form name="forma">
<textarea name="texto" cols="50" rows="100">
Nota: expira a los (<%=ticket.getString("dias_expira")%>) dias
<%=ticket.getString("centro_hipico").toUpperCase()%>
RIF: <%=ticket.getString("rif").toUpperCase()%>

Ticket Nro.:<%=Constants.numero(ticket.getString("id_jugada"),10)%>
Fecha:<%=ticket.getString("dia")%> - Hora:<%=ticket.getString("hora")%>
Clave:<%=request.getAttribute("SID")%>

Jugada <%=Constants.getDominio(request).getMoneda()%> <%=ticket.getString("jugada")%>
---------------------------------------
T  Cant Ref.   Equipo             Logro
---------------------------------------
<% boolean is5to=false;
   boolean isMitad=false;
   boolean isAltaBaja=false;
   String tipo="";
   String cant="";
   String refe="";
   String equi="";
   String logr="";
do {
	is5to=ticket.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL_5);
	isMitad=ticket.getString("id_deporte").equals(Constants.ID_EQUIPO_SOCCER_MITAD) || ticket.getString("id_deporte").equals(Constants.ID_EQUIPO_BASKETBALL_MITAD);
	isAltaBaja=ticket.getString("tipo").toUpperCase().equals("A") || ticket.getString("tipo").toUpperCase().equals("B");
	tipo=ticket.getString("tipo").toUpperCase();
	cant=Constants.tipoFraccion(ticket.getString("cantidad"),!ticket.getString("tipo").equals(Constants.TIPO_ALTA) && !ticket.getString("tipo").equals(Constants.TIPO_BAJA),false);
	refe="(".concat(ticket.getString("referencia").toUpperCase()).concat(")");
	if(ticket.getString("desc_equipo").equalsIgnoreCase("empate")) {
		//equi=ticket.getString("desc_equipo").concat("-").concat(ticket.getString("equipo1"));
		equi=(isMitad?"Emp(MT)":"Empate").concat(":").concat(ticket.getString("equipo1")).concat(" Vs ".concat(ticket.getString("equipo2")));
		if(!ticket.getString("empate").equals(Constants.JUEGO_MULTIPLE_EQUIPO)){
			equi=equi.concat(" Vs ").concat(ticket.getString("equipo2"));
		}
	} else {
		equi=StringUtils.rpad(ticket.getString(is5to || isMitad?"equipo1":"desc_equipo"),' ',10).substring(0,10).trim();
		if(!ticket.getString("empate").equals(Constants.JUEGO_MULTIPLE_EQUIPO)){
			equi=equi.concat(" Vs ").concat(StringUtils.rpad(ticket.getString("equipo2"),' ',4)).trim().concat(is5to?"(5 Inn.)":"").concat(isMitad?"(MT)":"");
		}
	}
	logr=ticket.getString("logro").toUpperCase();
	
	tipo=StringUtils.rpad(tipo,' ',2).substring(0,2);
	cant=StringUtils.lpad(cant,' ',5).substring(0,5);
	refe=StringUtils.rpad(refe,' ',6).substring(0,6);
	equi=StringUtils.rpad(equi,' ',18).substring(0,18);
	logr=StringUtils.lpad(logr,' ',5).substring(0,5);
	
	out.println(tipo.concat(cant).concat(" ").concat(refe).concat(" ").concat(equi).concat(" ").concat(logr));
} while(ticket.next());
%>---------------------------------------
Premio <%=Constants.getDominio(request).getMoneda()%> <%=premio%>

<%=Constants.justificar(nota,40)%>
</textarea>
</form>
</body>
<%} else {%>
<head>
	<title>Error - </title>
</head>
<body>
	El Ticket no fue encontrado.<br><br><br>
</body>
<%}%>
</html>
