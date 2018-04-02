<jsp:directive.page import="com.betcesc.game.common.Constants"/>
<%@ include file="/views/common/taglibs.jsp"%>
<%@ page import="com.betcesc.game.ctrl.back.RegisterGameActionForm" %>
<%@ page import="com.betcesc.game.form.CalculadoraForm" %>
<%@ page import="java.util.ArrayList" %>
<% UsuarioIF supervisor = (UsuarioIF)usuario.getSupervisor(); %>
<% CachedRowSet lista = (CachedRowSet)request.getAttribute("lista"); %>
<% CachedRowSet listaDeporte = (CachedRowSet)request.getAttribute("listaDeporte"); %> 
<% 
int logros = Integer.parseInt(usuario.getLogrosCalc());
int logrosMin = Integer.parseInt(usuario.getLogrosMin());
logrosMin = (logrosMin<1?1:logrosMin);
boolean is5to = false;
boolean is1ro = false;
String padre = "";
boolean isBeisbol = false;
ArrayList lista5to=new ArrayList();
ArrayList lista5toPadre=new ArrayList();
String tipo = "";

ArrayList lista1ro=new ArrayList();
ArrayList lista1roPadre=new ArrayList();

StringBuffer tmp = new StringBuffer();
String sep="";
int ind = 0; 
String style="";
String saldo = String.valueOf(request.getAttribute("saldo"));
double saldoNumerico = Double.parseDouble(String.valueOf(request.getAttribute("saldoNumerico")));
if(usuario.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA)) {
	saldo=Constants.formatNumber(usuario.getMonto());
	saldoNumerico=Double.parseDouble(usuario.getMonto());
}
%>
<script>
String.prototype.trim = function() { return this.replace(/^\s*|\s*$/g,""); };

var logrosCalc = new Array();
var saldoActual = <%=saldoNumerico%>;
var reglasPago = <%=Constants.getReglasPagoJS()%>;

function setPuertoTicket(){
		var c = getCookie("puertoTicket");
		var puerto = prompt("Por favor, indique el puerto de la impresora",typeof (c)!='undefined'?c:"lpt1");
		if(!puerto){
			return;
		}
		hacerCookie(puerto);
}

function send(forma) {
<%-- 	<%if(usuario.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA)){%> --%>
// 	if(typeof(getCookie("puertoTicket"))=='undefined') {
// 		setPuertoTicket();
// 		if(typeof(getCookie("puertoTicket"))=='undefined') {
// 			alert("El puerto no fue grabado, habilite las cookies para este si");
// 			return;
// 		}
// 	}
<%-- 	<%}%> --%>
	if(isEmptyInt(document.forms[0].montoApostar,"El Monto Apostar",2,50)){return false;}
	if(logrosCalc.length<=0) {
		alert("No hay jugadas");
		return false;
	} 
	if(logrosCalc.length<<%=logrosMin%>) {
		alert("Debe seleccionar al menos <%=logrosMin%> combinaciones para efectuar la jugada.");
		return false;
	}
	
	if(parseInt(document.forms[0].montoPremio.value)==0) {
		alert("No hay premio para esta jugada");
		return false;
	}
	if(document.forms[0].montoApostar.value>saldoActual) {
		<%if (usuario.getIdRol().equals(Constants.ROL_JUGADOR)) {%>
			alert("El saldo no es suficiente para cubrir la jugada.");
		<%} else {%>
			alert("Disculpe, el monto apostar supera el limite por jugada asignado por el administrador.");
		<%}%>
		return false;
	}
	if(parseInt(document.forms[0].montoPremio.value)>30000) {
		alert("Monto maximo para los premios hasta 30.000 BS.F. por Ticket.\n\nPor favor reformule su jugada.");
		return false;
	}
	document.forms[0].agregar.value="true";
	document.forms[0].submit();
}

function hacerCookie(puerto) {
	var d = new Date();
	d.setYear(d.getYear()+5);
	setCookie("puertoTicket",puerto,d.toGMTString());
}

function imprimir() {
	window.open("<%=basePath%>printTicket.do","ventana","toolbar=no,location=no,status=no,menubar=no,rezisable=no,width=300px,height=500px,alwaysRaised=yes");
}

function cargar(idDeporte) {
	document.forms[0].idDeporte.value=idDeporte;
	document.forms[0].submit();
}
function iniciar(id,tipo,cantidad,ref,logro,nombre,juego,numero,padre,deporte) {
	logrosCalc[logrosCalc.length]={"codigo":id,"tipo":tipo,"cantidad":cantidad,"referencia":ref,"logro":logro,"equipo":nombre,"juego":juego,"numero":numero,"padre":padre,"deporte":deporte};
	with(document.forms[0]) {
		for(var k=0; k<logrosCalc.length; k++) {
				codigo[k].value=logrosCalc[k].codigo;
				tipo[k].value=logrosCalc[k].tipo;
				referencia[k].value=logrosCalc[k].referencia;
				cantidad[k].value=logrosCalc[k].cantidad;
				logro[k].value=logrosCalc[k].logro;
				equipo[k].value=logrosCalc[k].equipo;
				juego[k].value=logrosCalc[k].juego;
				numero[k].value=logrosCalc[k].numero;
				padre[k].value=logrosCalc[k].padre;
				deporte[k].value=logrosCalc[k].deporte;
		}
	}
	try {
		var chk = document.getElementById(id+tipo);
		chk.checked=true;
	} catch(e) {
		return;
	}
}

function isValido(jug,j,n,nombre,ref,deporte) {
	if(deporte==<%=Constants.ID_EQUIPO_BEISBOL_5%> || deporte==<%=Constants.ID_EQUIPO_BEISBOL_1%>){
		deporte=<%=Constants.ID_EQUIPO_BEISBOL%>
	}
	var error = new Array();
	<%listaDeporte.beforeFirst();while(listaDeporte.next()) {%>
	error[<%=listaDeporte.getString("id_deporte")%>]="<%=listaDeporte.getString("combinacion")%>".split(",");<%}%>
	
	var con=0;
	var conDos=0;
	var antN = "0";
	for(var p=0; p < jug.length; p++) {
		//alert(jug[p].padre+" === "+j+"  "+(parseInt(jug[p].padre)===parseInt(j)));
		if(parseInt(jug[p].padre)===parseInt(j)) {
			antN=jug[p].numero;
			con++;
		}
	}
	//alert(con);
	if(con===0) {
		return true;
	}
	if(con>=2) {
		return false;
	}
	var cad=antN+"-"+n;
	var cad2=n+"-"+antN;
	
	//alert(cad);
	if(error[deporte].length==0) {
		return false;
	}
	for(var x=0;x<error[deporte].length;x++) {
		if(error[deporte][x].trim()=='') return false;
		if(error[deporte][x]==cad || error[deporte][x]==cad2) {
			return false;
		}
	}
	return true;
}

function calcular(id,tipo,cantidad,ref,logro,nombre,juego,numero,padre,deporte) {
	//alert(id+" - "+tipo+" - "+cantidad+" - "+ref+" - "+logro+" - "+nombre+" - "+juego+" - "+numero+" - "+padre+"  -  "+deporte);
	var chk = document.getElementById(id+tipo);
	
	try {
		chk.checked;
	} catch(e) {
		return;
	}
	if(chk.checked) {
		if(logrosCalc.length>=<%=logros%>){
			alert("No puede exceder la cantidad de logros por jugada");
			chk.checked=false;
			return false;
		}		
		if(!isValido(logrosCalc,padre,numero,nombre,ref,deporte)){
			alert("La combinacion elegida no es valida");
			chk.checked=false;
			return false;
		}		
		
		logrosCalc[logrosCalc.length]={"codigo":id,"tipo":tipo,"cantidad":cantidad,"referencia":ref,"logro":chk.value,"equipo":nombre,"juego":juego,"numero":numero,"padre":padre,"deporte":deporte};
	} else {
		for(var m=0; m<logrosCalc.length; m++) {
			if(logrosCalc[m].codigo==id && logrosCalc[m].tipo==tipo) {
				logrosCalc.splice(m,1);
				break;
			}
		}
	}
	//alert(logrosCalc.length);
	with(document.forms[0]) {
		for(var f=0; f<<%=logros%>; f++) {
			codigo[f].value="";
			tipo[f].value="";
			cantidad[f].value="";
			referencia[f].value="";
			equipo[f].value="";
			logro[f].value="";
			juego[f].value="";
			numero[f].value="";
			padre[f].value="";
			deporte[f].value="";
		}
		premio();
		for(var k=0; k<logrosCalc.length; k++) {
			codigo[k].value=logrosCalc[k].codigo;
			tipo[k].value=logrosCalc[k].tipo;
			referencia[k].value=logrosCalc[k].referencia;
			cantidad[k].value=logrosCalc[k].cantidad;
			logro[k].value=logrosCalc[k].logro;
			equipo[k].value=logrosCalc[k].equipo;
			juego[k].value=logrosCalc[k].juego;
			numero[k].value=logrosCalc[k].numero;
			padre[k].value=logrosCalc[k].padre;
			deporte[k].value=logrosCalc[k].deporte;

			premio();
		}
	}
}
function premio(key) {
	if(key && key.keyCode==13){
		

	
		if(confirm("Desea crear el ticket","Mensaje")) {
			send(document.forms[0]);
		} else {
			try {
				document.forms[0].numeroRef.focus();
			} catch(e){}
		}
	}

	with(document.forms[0]) {
		montoPremio.value = 0;
		var cuenta = false;
		var jugada = 0;
		try {
			var cuenta = (montoApostar.value!='' && parseInt(montoApostar.value)>0?true:false);
			jugada = parseInt(montoApostar.value);
		} catch(e) {
		}
		for(var y=0; y<logrosCalc.length; y++) {
			if(cuenta) {
				if(logro[y].value>0) {
					jugada = jugada+(jugada*(logro[y].value/100));
				} else if(logro[y].value<0) {
					jugada = jugada+(jugada/((logro[y].value*-1)/100));
				}
				montoPremio.value = Math.round(jugada);
			}
		}
		
		// aplicamos las reglas
		montoJugada=parseInt(montoApostar.value);
		montoMaximo=0;
		totalPremio=montoPremio.value;
		montoCalculo.value=montoPremio.value;
		for(var k=0; k<reglasPago.length; k++) {		
			montoMaximo = Math.max(reglasPago[k].montoMaximo,montoMaximo);
			if(montoJugada>=reglasPago[k].rangoIni && montoJugada<=reglasPago[k].rangoFin) {
				totalPremio = Math.min(totalPremio,(montoJugada*reglasPago[k].multiplo));
				break;
			}
		}
		montoPremio.value=Math.min(montoMaximo,totalPremio);
		
	}
}
function marcar(key,obj) {
	obj.value=obj.value.toUpperCase();
	if(key.keyCode==13){
		try {
			if(obj.value.trim()=="") {
				try {
					document.forms[0].montoApostar.select();
					document.forms[0].montoApostar.focus();
				} catch(e) {}
			} else {
				var c = eval("document.forms[0].R"+obj.value);
				c.checked=!c.checked;
				c.onclick();
				obj.value="";
			}
		}catch(e){
			alert("La referencia no es valida");
		}
	}
}

</script>

<form action="listGamePlay.do" method="POST">
<input type="hidden" name="idDeporte" value="<bean:write name="idDeporteElejido"/>"/>
<input type="hidden" name="agregar" value="false"/>
<table align="center" width="100%" height="100%" cellspacing="0" cellpadding="0">
  <tr>
	<td width="100%" align="left" colspan="3">
	<font color="white">
		<html:errors/>
	</font>
	</td>
  </tr>
</table>
<table align="center" width="100%" height="100%" >
	<tr>
		<td class="tituloTablaSup tablaConBorde" width="75%" rowspan="2">
		<font size="4" face="Tahoma,Arial"><%= usuario.getSupervisor().getCentroHipico() %></font><br/>
		Deportes
			<table align="center" width="98%" height="100%" >
				<tr>
				<%listaDeporte.beforeFirst();
				while(listaDeporte.next()) {%>
					<td class="tituloTabla tablaConBorde" onmouseover="this.className='lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"
					onclick="cargar(<%=listaDeporte.getString("id_deporte")%>)"
					>
						<%=listaDeporte.getString("desc_deporte")%>
					</td>
				<%}%>
				</tr>
			</table>
		</td>
		<%if(usuario.getIdRol().equals(Constants.ROL_JUGADOR)) {%>
			<td class="tituloTabla tablaConBorde" width="25%" >Saldo: <span class="saldo"><%=Constants.getDominio(request).getMoneda()%> <%=saldo%></span></span></td>
		<%} else {%>
			<td class="tituloTabla tablaConBorde" width="25%" >Maximo: <span class="saldo"><%=Constants.getDominio(request).getMoneda()%> <%=saldo%></span></span></td>
		<%}%>
	</tr>	
	<tr>
		<td class="tituloTabla1 tablaConBorde" width="25%">
			<table border="0" cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<td align="right">
					<font color="white">Ref.:</font>&nbsp;<input type="text" name="numeroRef" size="8" maxlength="8" onkeyup="marcar(event,this)" class="numeroRef">
				</td>
			</tr>
			</table>
		</td>
	</tr>	
	<tr>
		<td class="tituloTablaSup tablaConBorde" valign="top" nowrap>
		
		
			<table align="center" width="100%" height="100%"  cellspacing="0" cellpadding="0">
			<caption class="tituloTablaSup">LISTA DE JUEGOS RAPIDA&nbsp;<bean:write name="descDeporteElejido"/></caption>
				<logic:present name="listaJuego">
				<logic:iterate id="juego" name="listaJuego">
				<% RegisterGameActionForm item = (RegisterGameActionForm) juego; 
					padre = (item.getIdJuegoPadre()==null?item.getIdJuego():item.getIdJuegoPadre());
					is5to = item.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL_5);
					is1ro = item.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL_1);
					isBeisbol = item.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL);
					if(is5to) {
						lista5to.add(item.getIdJuego());
						lista5toPadre.add(item.getIdJuegoPadre());
					} else if(is1ro) {
						lista1ro.add(item.getIdJuego());
						lista1roPadre.add(item.getIdJuegoPadre());
					}
					if(item.isPrimero()) {
						ind = 1;
						%>
				<tr>
					<td nowrap>
						<fieldset>
							<legend class="tituloTablaSup" style="display:<%=!is5to && !is1ro?"":"none"%>"><bean:write name="juego" property="descLiga" /> (<bean:write name="juego" property="iniciales" />)&nbsp;</legend>
					<%}%>
							<table align="center" width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class="tablaConBorde" style="margin-top:5px;display:<%=!is5to && !is1ro?"":"none"%>">
							<tr>
							<td valign="top" nowrap>
							<table id="tabla_<%=item.getIdJuego()%>" cellpadding="1" cellspacing="0" align="center" width="100%" height="100%" border="0" style="display:<%=!is5to && !is1ro?"":"none"%>"  class="tablaCaption" >
								<tr>
									<td colspan="6">
										<table align="center" width="100%" height="100%" cellspacing="0" cellpadding="0" border="0" class="lineaResaltadaJugada">
											<tr>
												<td class="detalleTablaLeftMinima" width="20%"><span style="display:<%=!is5to && !is1ro?"":"none"%>"><bean:write name="juego" property="hora" />&nbsp;&nbsp;&nbsp;<bean:write name="juego" property="fechaIni" /></span></td>
												<td class="detalleTablaCenterMinima" colspan="3"><b class='blue'><%=is5to?"5 Inning, o primera mitad":(is1ro?"1er Inning":(isBeisbol?"9 Inning o juego completo":""))%></b></td>
												<td class="detalleTablaRightMinima" width="20%">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>	
								<tr>
									<%
										ind = (is5to?7:ind);
										ind = (is1ro?13:ind);
									%>
									<td class="detalleTablaLeftMinimaTitulo" width="5%">Ref.</td>
									<td class="detalleTablaLeftMinimaTitulo" style="display:<%=!is5to && !is1ro?"":"none"%>"><bean:message key="juegoEquipo.idEquipo" /></td>
									<td class="detalleTablaLeftMinimaTitulo" style="display:<%=!is5to && !is1ro?"":"none"%>">Pitcher</td>
									<td class="detalleTablaLeftMinimaTitulo" width="17%"><%=is1ro?"Carreras":"M.Line"%></td>
									<td class="detalleTablaLeftMinimaTitulo" width="17%" style="display:<%=is1ro?"none":""%>;">R.Line</td>
									<td class="detalleTablaLeftMinimaTitulo" width="17%" style="display:<%=is1ro?"none":""%>;">A/B</td>
								</tr>	
								<%for(int i=0; i<item.get_Referencia().length; i++) {
									if(item.get_IdEquipo()==null || item.get_IdEquipo()[i].equals("0")) {break;}
									%>
										<%if(i!=2 || i==2 && !item.get_MoneyLine()[i].equals("0")){
											tipo = (is1ro?(i==0?Constants.TIPO_SI:Constants.TIPO_NO):Constants.TIPO_MONEYLINE);
										%>
								<tr>
									<td class="detalleTablaLeftMinimaTitulo" nowrap><%= item.get_Referencia()[i] %>&nbsp;</td>
									<td class="detalleTablaLeftMinima" nowrap style="display:<%=!is5to && !is1ro?"":"none"%>"><%= item.get_DescEquipo()[i] %></td>
									<td class="detalleTablaLeftMinima" nowrap style="color:#0000cd;display:<%=!is5to && !is1ro?"":"none"%>"><%=(item.get_NombreLanzador()[i]!=null?item.get_NombreLanzador()[i]:"")%></td>
									<td class="detalleTablaLeft" nowrap><%=tipo.concat(tipo.equals(Constants.TIPO_SI)?"&nbsp;&nbsp;":"")%>
										<input type="checkbox" class="checkGame" id="<%= item.get_IdUsuarioJuegoEquipo()[i] %><%=tipo%>" name="R<%= item.get_Referencia()[i] %>" value="<%= Constants.signo(item.get_MoneyLine()[i]) %>" onclick="calcular('<%= item.get_IdUsuarioJuegoEquipo()[i] %>','<%=tipo%>','','<%= item.get_Referencia()[i] %>',this.value,'<%= item.get_DescEquipo()[i] %>','<%= item.getIdJuego()%>','<%=ind++%>',<%=padre%>,<%=item.getIdDeporte()%>)"/> <%= Constants.signo(item.get_MoneyLine()[i]) %>
									</td>
									<td class="detalleTablaLeft" nowrap style="display:<%=is1ro?"none":""%>;">
										<%if(i!=2){%>
											<%style=(item.get_Spread()[i].equals("0") && item.get_SpreadLogro()[i].equals("0")?"none":"");%>
										<input type="checkbox" class="checkGame" style="display:<%=style%>" id="<%= item.get_IdUsuarioJuegoEquipo()[i] %><%=Constants.TIPO_RUNLINE%>" name="R<%= item.get_ReferenciaRunline()[i] %>" value="<%= Constants.signo(item.get_SpreadLogro()[i]) %>" onclick="calcular('<%= item.get_IdUsuarioJuegoEquipo()[i] %>','<%=Constants.TIPO_RUNLINE%>','<%= Constants.signo(item.get_Spread()[i]) %><%= Constants.fraccion(item.get_SpreadDecimal()[i]) %>','<%= item.get_Referencia()[i] %>',this.value,'<%= item.get_DescEquipo()[i] %>','<%= item.getIdJuego()%>','<%=ind++%>',<%=padre%>,<%=item.getIdDeporte()%>)"/><span style="display:<%=style%>"><%= Constants.signo(item.get_Spread()[i]) %><%= Constants.fraccion(item.get_SpreadDecimal()[i]) %><%= Constants.signo(item.get_SpreadLogro()[i]) %></span>
										<%}%>
									</td>
									<td class="detalleTablaLeft" nowrap style="display:<%=is1ro?"none":""%>;">
										<%if(i!=2){%>
											<%style=(item.get_Total()[i].equals("0") && item.get_TotalDecimal()[i].equals("0")?"none":"");%>
										<input type="checkbox" class="checkGame" style="display:<%=style%>" id="<%= item.get_IdUsuarioJuegoEquipo()[i] %><%=(i==0?Constants.TIPO_ALTA:(i==1?Constants.TIPO_BAJA:Constants.TIPO_EMPATE))%>" name="R<%= item.get_ReferenciaAB()[i] %>" value="<%= Constants.signo(item.get_TotalLogro()[i]) %>" onclick="calcular('<%= item.get_IdUsuarioJuegoEquipo()[i] %>','<%=(i==0?Constants.TIPO_ALTA:(i==1?Constants.TIPO_BAJA:Constants.TIPO_EMPATE))%>','<%= item.get_Total()[i] %><%= Constants.fraccion(item.get_TotalDecimal()[i]) %>','<%= item.get_Referencia()[i] %>',this.value,'<%= item.get_DescEquipo()[i] %>','<%= item.getIdJuego()%>','<%=ind++%>',<%=padre%>,<%=item.getIdDeporte()%>)"/><span style="display:<%=style%>"><span class="detalleTablaLeftMinimaArial"><%=(i==0?"A":(i==1?"B":""))%></span>&nbsp;<%= item.get_Total()[i] %><%= Constants.fraccion(item.get_TotalDecimal()[i]) %><%= Constants.signo(item.get_TotalLogro()[i]) %></span>
										<%}%>
									</td>
								</tr>	
										<%}%>
								<%}%>
							</table>


							</td>
							<td id="left_<%=item.getIdJuego()%>" valign="top" style="padding-left:2px;display:none;"></td>
							<td id="left1ro_<%=item.getIdJuego()%>" valign="top" style="padding-left:2px;display:none;"></td>
							</tr>
							</table>
						<%if(!item.isUltimo()) {
							ind = 1;
							if(!is5to && !is1ro) {%>
							<!--<hr/>-->
							<%}
						} else {%>
						</fieldset>
					</td>
				</tr>
						<%}%>
				</logic:iterate>
				</logic:present>
				<logic:notPresent name="listaJuego">
				<tr>
					
					<td class="detalleTablaCenter">
						<fieldset>
							<div>&nbsp;</div>
							<div>&nbsp;</div>
							NO HAY JUEGOS REGISTRADOS PARA ESTE DEPORTE
							<div>&nbsp;</div>
							<div>&nbsp;</div>
						</fieldset>
					</td>
					
				</tr>	
				</logic:notPresent>
			</table>
		
		
		</td>
		<td class="tituloTablaSup tablaConBorde" valign="top">
			<fieldset>
				<legend class="tituloTablaSup">CALCULO DE LA JUGADA</legend>
				<table align="center" width="98%" height="100%" border="0">
					<tr>
						<td class="calculadora" colspan="5">
							<table width="100%" cellspacing="0" cellpadding="0" border="0">
								<tr>
									<td class="calculadora" >Monto de la jugada&nbsp;:</td>
									<td  align="right"><%=Constants.getDominio(request).getMoneda()%>&nbsp;<input type="text" name="montoApostar" maxlength="9" size="9"  style="text-align:right" onkeyup="premio(event,this)"/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td class="calculadora" colspan="5">&nbsp;</td>
					</tr>
					<tr>
						<td class="calculadora" width="1%" colspan="2">&nbsp;Tipo</td>
						<td class="calculadora" width="10%" >&nbsp;Ref</td>
						<td class="calculadora" width="54%" >&nbsp;Equipo</td>
						<td class="calculadora" width="25%" >&nbsp;Logro</td>
					</tr>
					<%for(int i=0; i<logros;i++) {%>
					<tr>
						<td class="calculadora" ><input type="hidden" name="codigo" class="inputTextSingleCalc"/><input type="hidden" name="padre" class="inputTextSingleCalc"/>
							<input type="hidden" name="deporte" /><input type="text" name="tipo" class="inputTextSingleCalc"  readOnly="yes" style="width:20px"/></td>
						<td class="calculadora" ><input type="text" name="cantidad" class="inputTextSingleCalc" style="width:30px"/  readOnly="yes"></td>
						<td class="calculadora" ><input type="text" name="referencia" class="inputTextSingleLeftCalc" style="width:30px" readOnly="yes"/></td>
						<td class="calculadora" ><input type="text" name="equipo" class="inputTextSingleCalc" style="width:70px"  readOnly="yes"/></td>
						<td class="calculadora" >
							<input type="text" name="logro" class="inputTextSingleRightCalc" size="3" readOnly="yes"/>
							<input type="hidden" name="juego"/>
							<input type="hidden" name="numero"/>
						</td>
					</tr>
					<%}%>
					<tr>
						<td class="calculadora" colspan="5">
							<table width="100%" cellspacing="0" cellpadding="0" border="0">
								<tr>
									<td class="calculadora" >&nbsp;</td>
									<td align="right" valign="middle"><hr style="width:90px"/></td>
								</tr>
<!-- 								<tr> -->
<!-- 									<td class="calculadora" >Monto del Calculo&nbsp;:</td> -->
<%-- 									<td align="right" valign="middle"><%=Constants.getDominio(request).getMoneda()%>&nbsp;<input type="text" name="montoCalculo" size="9" class="inputTextSingleCalc" style="text-align:right"  readOnly="yes"/></td> --%>
<!-- 								</tr> -->
								<tr>
									<td class="calculadora" >Monto del Premio&nbsp;:</td>
									<td align="right" valign="middle"><%=Constants.getDominio(request).getMoneda()%>&nbsp;<input type="text" name="montoPremio" size="6" class="montoPremio" style="text-align:right;"  readOnly="yes"/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<br/>
				<center>
<div id="installFailure" style="display:none">
	<p>
	   <!--Haga click <a href="smsx.exe">aqui</a> para instalar el ActiveX de impresi&oacute;n. -->
	   Haga click <a href="hacer.bat">aqui</a> para instalar el ActiveX de impresi&oacute;n.
	</p>
</div>
<span id="installOK">
		<%if(usuario.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA)){%>
      <a class="enlaceBoton" href="#" onclick="send(this)" ><bean:message key="boton.agregarImprimir"/></a> 
      <br/>
      <br/>
      <a class="enlaceBoton" href="#" onclick="setPuertoTicket()" ><img src="<%=basePath%>icons/printer.png" border="0"/></a> 
      	<%} else {%>
      <a class="enlaceBoton" href="#" onclick="send(this)" ><bean:message key="boton.agregar"/></a> 
      	<%}%>
</span>
				<br/><br/>
				</center>
			</fieldset>
		<%if(usuario.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA)){%>
			<span style="font-size:8px;text-transform:none"><a href="<%=basePath%>views/common/hacer.jsp" target="_blank">Instalar ActiveX de impresi&oacute;n.</a></span>
      	<%}%>
		</td>
	</tr>	
</table>
<iframe name="ventana" id="ventana"  style="width:0px;height:0px" class="none">
</iframe>
</form>
<%if(session.getAttribute("jugada")!=null) {%>
<script language="javascript">
	<% ArrayList jug =  (ArrayList)session.getAttribute("jugada"); 
	for(int i = 0; i < jug.size(); i++) {
		CalculadoraForm cal =  (CalculadoraForm) jug.get(i);%>
		iniciar(<%=cal.getCodigo()%>,'<%=cal.getTipo()%>','<%=cal.getCantidad()%>','<%=cal.getReferencia()%>','<%=cal.getLogro()%>','<%=cal.getEquipo()%>','<%=cal.getJuego()%>','<%=cal.getNumero()%>','<%=cal.getPadre()%>','<%=cal.getDeporte()%>');
		document.forms[0].montoApostar.value='<%=cal.getMontoApostar()%>';
		document.forms[0].montoPremio.value='<%=cal.getMontoPremio()%>';
	<%}%>
</script>
<%}%>
<%if(request.getAttribute("imp")!=null) {%>
<script language="javascript">
	imprimir();
</script>
<%}%>
<script language="javascript">
	<%
		for(int x=0; x<lista5to.size(); x++) {
			tmp.setLength(0);
			tmp.append("try {document.getElementById('left_");
			tmp.append(lista5toPadre.get(x));
			tmp.append("').appendChild(document.getElementById('tabla_");
			tmp.append(lista5to.get(x));
			tmp.append("')); } catch(e) {}");
			out.println(tmp.toString());

			tmp.setLength(0);
			tmp.append("try {document.getElementById('tabla_");
			tmp.append(lista5to.get(x));
			tmp.append("').style.display=''; } catch(e) {}");
			out.println(tmp.toString());

			tmp.setLength(0);
			tmp.append("try { document.getElementById('left_");
			tmp.append(lista5toPadre.get(x));
			tmp.append("').style.width='240px'; } catch(e) {}");
			out.println(tmp.toString());

			tmp.setLength(0);
			tmp.append("try { document.getElementById('left_");
			tmp.append(lista5toPadre.get(x));
			tmp.append("').style.display=''; } catch(e) {}");
			out.println(tmp.toString());
		}
		
		for(int x=0; x<lista1ro.size(); x++) {
			tmp.setLength(0);
			tmp.append("try { document.getElementById('left1ro_");
			tmp.append(lista1roPadre.get(x));
			tmp.append("').appendChild(document.getElementById('tabla_");
			tmp.append(lista1ro.get(x));
			tmp.append("')); } catch(e) {}");
			out.println(tmp.toString());

			tmp.setLength(0);
			tmp.append("try { document.getElementById('tabla_");
			tmp.append(lista1ro.get(x));
			tmp.append("').style.display=''; } catch(e) {}");
			out.println(tmp.toString());

			tmp.setLength(0);
			tmp.append("try { document.getElementById('left1ro_");
			tmp.append(lista1roPadre.get(x));
			tmp.append("').style.width='100px'; } catch(e) {}");
			out.println(tmp.toString());

			tmp.setLength(0);
			tmp.append("try { document.getElementById('left1ro_");
			tmp.append(lista1roPadre.get(x));
			tmp.append("').style.display=''; } catch(e) {}");
			out.println(tmp.toString());
		}
		
	%>
</script>
<script>
	document.forms[0].numeroRef.focus();
</script>
