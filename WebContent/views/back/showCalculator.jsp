<%@page import="javax.sound.midi.SysexMessage"%>
<%@ include file="/views/common/taglibs.jsp"%>
<%@ include file="/views/common/meta.jsp"%>
<style>
input {
	font-size:12px;
}
</style>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevent caching at the proxy server

if(session.getAttribute("sendErrores")!=null) {
	out.println("<html><head>"); 
	out.println("<script>");
	//out.println("alert(document.location);");
	out.println("window.parent.location.href=document.location.href;");
	out.println("</script>");
	out.println("</head></html>");
} else {
	int logros = Integer.parseInt(usuario.getLogrosCalc());
	int minJugada = usuario.getJugadaMinima();
	
	
	%>
	<html>
	<head>
	<script>
	String.prototype.trim = function() { return this.replace(/^\s*|\s*$/g,""); };
	String.prototype.endsWith = function(str){return (this.match(str+"$")==str)}
	String.prototype.startsWith = function(str){return (this.match("^"+str)==str)}
	
	var logrosCalc = new Array();
	var reglasPago = null;
	<%if(Constants.isNull(usuario.getPagoVeces(),"").equals("")){%>
		var pagoVeces = new Array();
	<%} else {%>
		var pagoVeces = '<%=Constants.isNull(usuario.getPagoVeces(),"")%>'.split(',');
	<%}%>
	
	
	function llenarCalculadora(logrosCalcPadre,reglasPagoPadre) {
		logrosCalc=logrosCalcPadre;
		reglasPago=reglasPagoPadre;
		limpiar();
		with(document.forms[0]) {
			premio(null,null);
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
	
				// si es juego teaser ocultamos el logro
				logro[k].style.color='#fff';
				if(logrosCalc[k].referencia.charAt(0)=='T') {
					logro[k].style.color='#000';
				} 
	
				premio(null,null);
				
			}
		}
	}
	function premio(key,obj) {
		if(obj!=null && (typeof obj)!='undefined') {
			obj.value = obj.value.replace(/[^0-9]/g,'');
			obj.value = obj.value.replace(/(^0)/g,'');
		}
	
		with(document.forms[0]) {
			montoPremio.value = 0;
			var cuenta = false;
			var apuesta = 0;
			try {
				var cuenta = (montoApostar.value!='' && parseInt(montoApostar.value)>0?true:false);
				apuesta = parseInt(montoApostar.value);
			} catch(e) {
			}
			for(var y=0; y<logrosCalc.length; y++) {
				if(cuenta) {
					if(logro[y].value>0) {
						apuesta = apuesta+(apuesta*(logro[y].value/100));
					} else if(logro[y].value<0) {
						apuesta = apuesta+(apuesta/((logro[y].value*-1)/100));
					}
					montoPremio.value = Math.round(apuesta);
				}
			}
			
			// aplicamos las reglas
			montoApuesta=parseInt(montoApostar.value);
			montoMaximo=0;
			totalPremio=montoPremio.value;
			montoCalculo.value=montoPremio.value;
			if(reglasPago!=null && reglasPago.length>0){
				for(var k=0; reglasPago!=null && k<reglasPago.length; k++) {		
					montoMaximo = Math.max(reglasPago[k].montoMaximo,montoMaximo);
					if(montoApuesta>=reglasPago[k].rangoIni && montoApuesta<=reglasPago[k].rangoFin) {
						totalPremio = Math.min(totalPremio,(montoApuesta*reglasPago[k].multiplo));
						break;
					}
				}
				montoPremio.value=Math.min(montoMaximo,totalPremio);
			}
			// validamos el monto del premio con las veces  establecidas
			montoPremio.value = calcularPagoVeces(montoPremio.value);
			
			if(key && key.keyCode==13){
			
				if (montoApuesta< <%=minJugada%>)
				{
			 		alert("El Monto Minimo a jugar es de <%=minJugada%>");
			 		return;
				}
				
				if(confirm("Desea crear el ticket","Mensaje")) {
					
					
					
					if(window.parent.send(document.forms[0])) {
						document.forms[0].isFree.value=(window.parent.isFree()?1:0);
						window.parent.desmarcar();
						document.forms[0].submit();
					}
				} else {
					try {
						document.forms[0].numeroRef.focus();
					} catch(e){}
				}
			}
		}
	}
	
	function enviar() {
		
		
		montoApuesta=parseInt(document.forms[0].montoApostar.value);
		if (montoApuesta< <%=minJugada%>)
		{
	 		alert("El Monto Minimo a jugar es de <%=minJugada%>");
	 		return;
		}
		
		var valido = window.parent.send(document.forms[0]);
		if(valido) {
			document.forms[0].isFree.value=(window.parent.isFree()?1:0);
			window.parent.desmarcar();
			document.forms[0].submit();
		} else {
			try {
				document.forms[0].numeroRef.focus();
			} catch(e){}
		}
	}
	function limpiar() {
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
		}
	}
	
	function imprimir() {
		window.open("<%=basePath%>printTicket.do","ventana","toolbar=no,location=no,status=no,menubar=no,rezisable=no,width=300px,height=500px,alwaysRaised=yes");
	}
	function imprimirJava() {
		window.open("<%=basePath%>printTicketJava.do","ventana","toolbar=no,location=no,status=no,menubar=no,rezisable=no,width=300px,height=500px,alwaysRaised=yes");
	}

	
	function calcularPagoVeces(premio) {
		if(pagoVeces.length>0 && document.forms[0].montoApostar.value!='') {
			var nLogro = logrosCalc.length;
			var rango;
			var montoJugada=parseInt(document.forms[0].montoApostar.value);
			var limite = 0;
			var resto = 0;
			var num = 0;
			for(var i=0; i<pagoVeces.length; i++) {
				rango = pagoVeces[i].split('-');
				if(rango[0]==nLogro) {
					//alert("pago =logros="+nLogro+" nVeces => "+rango[1]);
					limite = parseInt(rango[1])*montoJugada;
					
					if(premio>limite) {
						limite = Math.min(premio,limite);
		
						var sLim = ""+limite;
						var factor = 1;
						for(var k=(sLim.length-1); k>0; k--) {
							if(sLim.charAt(k)==0) {
								factor = factor*10;
							} else {
								break;
							}
						}
		
						// agregamos el resto				
						if(factor>9) {
						    limite = limite + (premio%parseInt(factor));
						}
						
						premio = Math.min(premio,limite);
					}
					break;	
				}
			}
		}
		return premio;
	}
	
	</script>
	</head>
	<body style="background-color:#000;">
	<table align="center" width="100%"  cellspacing="0" cellpadding="0" border="0">
	  <tr>
		<td width="100%" align="left" colspan="3">
		<font color="white">
			<html:errors/>
		</font>
		</td>
	  </tr>
	</table>
	<form action="listGamePlay.do" method="POST" style="margin:0px" onSubmit='return false;'>
	<input type="hidden" name="agregar" value="false"/>
	<input type="hidden" name="teaser" value="false"/>
	<input type="hidden" name="isFree" value="0"/>
	<input type="hidden" name="simple" value="true"/>
	<fieldset>
			<legend class="tituloTablaSup">CALCULO DE LA JUGADA</legend>
			<table align="center" width="100%"  border="0">
				<tr>
					<td class="calculadora" colspan="5">
						<table width="100%" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td class="calculadora" >Monto de la apuesta&nbsp;:</td>
								<td  align="right" style="color:#ffffff;">
									<%=Constants.getDominio(request).getMoneda()%>&nbsp;
									<input type="text" name="montoApostar" maxlength="9" size="9"  style="text-align:right;font-size:18px;font-weight:bold;" onkeyup="premio(event,this)"
										style="background:#c0c0c0;" 
										onfocus="this.style.background='yellow'" 
										onblur="this.style.background='#c0c0c0'" >
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="calculadora" colspan="5">&nbsp;</td>
				</tr>
				<tr>
					<td class="calculadora" width="1%" colspan="2">&nbsp;Tipo</td>
					<td class="calculadora" width="15%" >&nbsp;Ref</td>
					<td class="calculadora" width="49%" >&nbsp;Equipo</td>
					<td class="calculadora" width="25%" >&nbsp;Logro</td>
				</tr>
				<%for(int i=0; i<logros;i++) {%>
				<tr>
					<td class="calculadora" ><input type="hidden" name="codigo" class="inputTextSingleCalc"/><input type="hidden" name="padre" class="inputTextSingleCalc"/>
						<input type="hidden" name="deporte" /><input type="text" name="tipo" class="inputTextSingleCalc"  readOnly="yes" style="width:20px"/></td>
					<td class="calculadora" ><input type="text" name="cantidad" class="inputTextSingleCalc" style="width:30px"/  readOnly="yes"></td>
					<td class="calculadora" ><input type="text" name="referencia" class="inputTextSingleLeftCalc" style="width:60px" readOnly="yes"/></td>
					<td class="calculadora"><input type="text" name="equipo" class="inputTextSingleCalc" style="width:70px;text-align:left;"  readOnly="yes"/></td>
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
							<tr style="display: none">
								<td class="calculadora" >Monto del Calculo&nbsp;:</td>
								<td align="right" valign="middle"  style="color:#ffffff;"><%=Constants.getDominio(request).getMoneda()%>&nbsp;<input type="text" name="montoCalculo" size="9" class="inputTextSingleCalc" style="text-align:right"  readOnly="yes"/></td>
							</tr>
							<tr>
								<td class="calculadora" >Monto del Premio&nbsp;:</td>
								<td align="right" valign="middle"  style="color:#ffffff;"><%=Constants.getDominio(request).getMoneda()%>&nbsp;<input type="text" name="montoPremio" size="6" class="montoPremio" style="text-align:right;"  readOnly="yes"/></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<br/>
			<center>
			<span id="installOK">
			<%if(usuario.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA)){%>
		      <a class="enlaceBoton" href="#" onclick="enviar()" style="width:200px;"><bean:message key="boton.agregarImprimir"/></a> 

		      <br/>
		      <br/>
			  <%if(session.getAttribute("bloqueoPantalla")==null){%>	
		      <a class="enlaceBoton" href="#" onclick="window.parent.setPuertoTicket()"  style="width:200px;">Seleccione el puerto para imprimir</a>
		      <%}%> 
		      	<%} else {%>
			  <%if(session.getAttribute("bloqueoPantalla")==null){%>	
		      <a class="enlaceBoton" href="#" onclick="enviar()"  style="width:200px;"><bean:message key="boton.agregar"/></a> 
		      <%}%> 
	      	<%}%>
			</span>
			<br/><br/>
			<%if(usuario.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA)){%>
				<%if(session.getAttribute("bloqueoPantalla")==null){%>
				<span style="font-size:8px;text-transform:none"><a href="<%=basePath%>views/common/hacer.jsp" target="_blank">Instalar ActiveX de impresi&oacute;n.</a></span>
				<br>
				<%}%>
	      	<%}%>
			</center>
		</fieldset>
	<iframe name="ventana" id="ventana"  style="width:0px;height:0px" class="none"></iframe>
	</form>
	</body>
	</html>
	<script>
	window.parent.habilitarPantalla();
	<%if (usuario.getIdRol().equals(Constants.ROL_JUGADOR)) {%>
		if(window.parent.document.getElementById('saldoJugador')) {
			window.parent.document.getElementById('saldoJugador').innerHTML="<%=request.getAttribute("saldo")%>";
			window.parent.setSaldoActual(<%=request.getAttribute("saldoNumerico")%>);
		}
	<%}%>
	<%if(request.getAttribute("imp")!=null) {%>
	
	  
	  if (navigator.appVersion.indexOf("Chrome")>-1)
	  {
		
		  imprimirJava();
	  }
	  else{
		
		  //aqui ponemos el codigo a ejecutar o escribimos en pantalla
		imprimir();
    }
	
	
	<%}%>
	</script>
<%}%>
