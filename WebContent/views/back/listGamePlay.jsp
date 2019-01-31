<jsp:directive.page import="com.betcesc.game.common.Constants"/>
<%@ include file="/views/common/taglibs.jsp"%>
<%@ include file="/views/common/meta.jsp"%>
<%@ page import="com.betcesc.game.ctrl.back.RegisterGameActionForm" %>
<%@ page import="com.betcesc.game.form.CalculadoraForm" %>
<%@ page import="java.util.ArrayList,java.util.Properties,java.util.HashMap" %>
<% UsuarioIF supervisor = (UsuarioIF)usuario.getSupervisor(); %>
<% CachedRowSet listaDeporte = (CachedRowSet)request.getAttribute("listaDeporte"); %>
<% CachedRowSet listaJuego = null;
com.betcesc.game.bean.DominioBean dom = Constants.getDominio(request); 
if(request.getAttribute("listaJuego")!=null){
        listaJuego = (CachedRowSet)((CachedRowSet)request.getAttribute("listaJuego")).clone();
}
HashMap listaJuego5 = (HashMap)request.getAttribute("listaJuego5"); 
HashMap listaJuego1 = (HashMap)request.getAttribute("listaJuego1"); 
HashMap listaJuego0 = (HashMap)request.getAttribute("listaJuego0"); 
Properties prop5 = null;
Properties prop1 = null;
Properties prop0 = null;

boolean isActiveSRL = usuario.getActivarSuperRunline().equals("1");
int logros = Integer.parseInt(usuario.getLogrosCalc());
int logrosMin = Integer.parseInt(usuario.getLogrosMin());
logrosMin = (logrosMin<1?1:logrosMin);
boolean is9no = false;
boolean is5to = false;
boolean is1ro = false;
boolean is0ro = false;
boolean isPitcher = false;
boolean isTeaser = false;

boolean pntTit9 = true;
boolean pntTit5 = true;
boolean pntTit1 = true;
boolean pntTit0 = true;

String padre = ""; 
String listaIdJuegoAbierto = (String)request.getAttribute("listaIdJuegoAbierto");
boolean isBeisbol = false;
ArrayList lista5to=new ArrayList();
ArrayList lista5toPadre=new ArrayList();
String tipo = "";

ArrayList lista1ro=new ArrayList();
ArrayList lista1roPadre=new ArrayList();

ArrayList lista0ro=new ArrayList();
ArrayList lista0roPadre=new ArrayList();

StringBuffer tmp = new StringBuffer();
String sep="";
int ind = 0; 
String style="";
String saldo = String.valueOf(request.getAttribute("saldo"));
String saldoFree = String.valueOf(request.getAttribute("saldoFree"));
double saldoNumerico = Double.parseDouble(String.valueOf(request.getAttribute("saldoNumerico")));
double saldoFreeNumerico = Double.parseDouble(String.valueOf(request.getAttribute("saldoFreeNumerico")));
boolean isTaquilla = false;
if(usuario.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA)) {
	isTaquilla = true;
	saldo=Constants.formatNumber(usuario.getMonto());
	saldoNumerico=Double.parseDouble(usuario.getMonto());
	
}
%>
<style type="text/css">
.left {
	text-align:left;
}

.fondoTransparente
{
	/*Div que ocupa toda la pantalla*/
	position:absolute;
	top:0px;
	left:0px;
	width:100%;
	height:100%;
	background-color:#fff;
	/*IE*/
	filter: alpha(opacity=50);
	/*FireFox Opera*/
	opacity: .5;
}
.centrado
{
	position: absolute;
	/*nos posicionamos en el centro del navegador*/
	top:25%;
	left:50%;
	/*determinamos una anchura*/
	width:400px;
	/*indicamos que el margen izquierdo, es la mitad de la anchura*/
	margin-left:-200px;
	/*determinamos una altura*/
	height:100px;
	/*indicamos que el margen superior, es la mitad de la altura*/
	margin-top:-150;
	border:1px solid #808080;
	background-color:#fff;
	padding:5px;
}

input[type=checkbox]{
	background-color:#000;
	height:20px;
	width:20px;
	border:0px;
}
.cuadro td {
	font-family:Tahoma;
	font-size:10px;
	color:white;
}
.tituloLiga td {
	font-family:Tahoma;
	font-size:10px;
	color:#ffffff;
	background-color:<%=dom.getColorLine()%>;
}
.titulo1 td {
	font-family:Tahoma;
	font-size:9px;
	color:lightgreen;
	border-bottom:1px solid #000077;
	border-top:1px solid #000055;
	background-color:#000033;
}
.titulo1 ML {
	font-family:Tahoma;
	font-size:12px;
	color:blue;
}

.fechaJuego {
	font-family:Tahoma;
	font-size:10px;
	color:yellow;
}
</style>
<script Language="JavaScript">
   if(window.history.forward(1) != null) {
        window.history.forward(1);
   }
</script>
<script>
String.prototype.trim = function() { return this.replace(/^\s*|\s*$/g,""); };

var isTaquilla = <%=isTaquilla%>;
var logrosCalc = new Array();
var acumulatedSports = new Array();
var gameSel = false;
var animSel = false;
var saldoActual = <%=saldoNumerico%>;
var saldoFreeActual = <%=saldoFreeNumerico%>;
var reglasPago = <%=Constants.getReglasPagoJS()%>;
var listaReferencia = <%=request.getAttribute("listaReferencia")%>;


window.habilitado = true;
function setPuertoTicket(){
		var c = getCookie("puertoTicket");
		var puerto = prompt("Por favor, indique el puerto de la impresora",typeof (c)!='undefined'?c:"lpt1");
		if(!puerto){
			return;
		}
		hacerCookie(puerto);
}

function send(forma) {
	<%if(usuario.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA)){%>
	if(typeof(getCookie("puertoTicket"))=='undefined') {
		setPuertoTicket();
		if(typeof(getCookie("puertoTicket"))=='undefined') {
			alert("El puerto no fue grabado, habilite las cookies en el navegador");
			return false;
		}
		return true;
	}
	<%}%>

	if(isEmptyInt(forma.montoApostar,"El Monto Apostar",2,50)){return false;}
	
	if(logrosCalc.length<=0) {
		alert("No hay apuestas");
		return false;
	}

	if(logrosCalc.length<<%=logrosMin%>) {
		alert("Debe seleccionar al menos <%=logrosMin%> combinaciones para efectuar la apuesta.");
		return false;
	}
	
	
	
	if(parseInt(forma.montoPremio.value)<100) {
		alert("La apuesta minima es de 100");
		return false;
	}
	
	if(parseInt(forma.montoPremio.value)==0) {
		alert("No hay premio para esta apuesta");
		return false;
	}
	
	if(<%=Constants.parseInt(usuario.getPagoVeces())%> > 0) {
	  var apuesta = parseInt(forma.montoApostar.value);
	  var premio = parseInt(forma.montoPremio.value);
	  
	  if(premio > (apuesta*<%=Constants.parseInt(usuario.getPagoVeces())%>)) {
	  	alert("El monto del premio no puede superar <%=Constants.parseInt(usuario.getPagoVeces())%> veces el valor de la apuesta");
	  	return false;
	  }
	}
	
	// validamos que el monto no sea una referencia
	var montoApostadoRef = 'R'+forma.montoApostar.value;
	
	var num = document.getElementsByTagName("input");
	for (var ind = 0;ind < num.length;ind++){
		if(num[ind].type == "checkbox" && num[ind].name.charAt(0)=='R') {
			if(num[ind].name==montoApostadoRef){
				if(!confirm("ADVERTENCIA : Selecciono una referencia como monto de la apuesta. Desea continuar?")) {
					return false;
				} else {
					break;
				}
			}
		}
	}
	
	if(isTaquilla || document.forms[0].isFree[0].checked) {
		if(forma.montoApostar.value>saldoActual) {
			<%if (usuario.getIdRol().equals(Constants.ROL_JUGADOR) ) {%>
				alert("El saldo no es suficiente para cubrir la apuesta.");
			<%} else {%>
				alert("Disculpe, el monto apostar supera el limite por apuesta asignado por el administrador.");
			<%}%>
			return false;
		}
	} else {
		// validamos que la jugada sea en parley
		if(logrosCalc.length<2) {
			alert("El Free-Play solo puede ser aplicado a jugadas en parley");
			return false;
		}
		// revisamos los minimo y maximo de logros
		var minLogFree = -200;
		var maxLogFree = 500;
		for(var k=0;k<logrosCalc.length;k++){
			if(logrosCalc[k].logro<minLogFree || logrosCalc[k].logro>maxLogFree) {
				alert("Topes minimo y maximo en parley para apuestas con Free-Play (min:-200 -  max:+500)");
				return false;
			} 
		}
	
		if(forma.montoApostar.value>saldoFreeActual) {
			alert("Los puntos de Free-Play no son suficientes para cubrir la apuesta.");
			return false;
		}
	}
	
	//if(parseInt(forma.montoPremio.value)>30000) {
	//	alert("Monto maximo para los premios hasta 30.000 BS.F. por Ticket.\n\nPor favor reformule su apuesta.");
	//	return false;
	//}
	
	// validamos por referencia
	for(var k=0;k<logrosCalc.length;k++){
		var ref=logrosCalc[k].referencia;
		var tipo=logrosCalc[k].tipo;
		for(var i=0;i<logrosCalc.length;i++){
			if(k!=i){
				if(logrosCalc[i].referencia==ref && logrosCalc[i].tipo==tipo){
					alert('la referencia esta duplicada');
					vaciar();
					return false;
				}
			}
		}
	}
	
	forma.teaser.value='false';
	
	if(forma.teaser.value=='true' && (logrosCalc.length<3 || logrosCalc.length>7)) {
		alert("Las jugadas teaser deben poseer un minimo de 3 y un maximo de 7 combinaciones.");
		return false;
	}
	
	if(window.habilitado) {
		forma.agregar.value="true";

		// inutiliza la pantalla hasta que imprima
		document.getElementById('MASCARA').style.display='';
		document.getElementById('MASCARA_MENSAJE').style.display='';

		//forma.submit();
		// registramos la apuesta
		
		window.habilitado = false;

		return true;
	}
	return false;
}

function habilitarPantalla() {
	// habilita la pantalla
	document.getElementById('MASCARA').style.display='none';
	document.getElementById('MASCARA_MENSAJE').style.display='none';
	window.habilitado = true;
	document.forms[0].numeroRef.focus();
	logrosCalc = new Array();
}



function hacerCookie(puerto) {
	var d = new Date();
	d.setYear(d.getYear()+5);
	setCookie("puertoTicket",puerto,d.toGMTString());
}

function verDetalle(valor) {
	var d = new Date();
	d.setYear(d.getYear()+5);
	setCookie("detalleApuesta",valor,d.toGMTString());
	if(valor==true) {
		document.getElementById("tablaTitulo").style.display="";	
		document.getElementById("tablaDetalle").style.display="";	
	} else {
		document.getElementById("tablaTitulo").style.display="none";	
		document.getElementById("tablaDetalle").style.display="none";	
	}
}



function cargar(idDeporte) {
	//document.forms[0].idDeporte.value=idDeporte;
	//alert(2);
	//document.forms[0].submit();
	var f = document.getElementsByTagName('fieldset');
	for(var i=0; i<f.length; i++) {
		f[i].style.display=(idDeporte==0 || f[i].id=='deporte_'+idDeporte?'':'none');
	}
	
}
function iniciar(id,tipo,cantidad,ref,logro,nombre,juego,numero,padre,deporte) {
	logrosCalc[logrosCalc.length]={"codigo":id,"tipo":tipo,"cantidad":cantidad,"referencia":ref,"logro":logro,"equipo":nombre,"juego":juego,"numero":numero,"padre":padre,"deporte":deporte};
	with(window.frames.frmCalculadora.document.forms[0]) {
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
		var ele = document.getElementById(id+'_'+ref);
		ele.onclick();
	} catch(e) {
		//alert("error iniciar");
		return;
	}
}

function isValido(jug,j,n,nombre,ref,deporte) {

	//alert(jug+' '+j+' '+n+' '+nombre+' '+ref+' '+deporte);
	// validamos por id de juego
	var bloqueados = ',<%=request.getAttribute("JUEGOS_BLOQUEADOS")%>,';
	for(var p=0; p < jug.length; p++) {
		var r1 = ","+j+"-"+jug[p].juego+",";
		var r2 = ","+jug[p].juego+"-"+j+",";
		if(bloqueados.indexOf(r1)!=-1 || bloqueados.indexOf(r2)!=-1) {
			return -2;
		} 
	}

	if(deporte==<%=Constants.ID_EQUIPO_BEISBOL_5%> || deporte==<%=Constants.ID_EQUIPO_BEISBOL_1%> || deporte==<%=Constants.ID_EQUIPO_BEISBOL_0%>){
		deporte=<%=Constants.ID_EQUIPO_BEISBOL%>
	} else if(deporte==<%=Constants.ID_EQUIPO_BASKETBALL_MITAD%>){
		deporte=<%=Constants.ID_EQUIPO_BASKETBALL%>
	}
	
	var error = new Array();
	<%listaDeporte.beforeFirst();while(listaDeporte.next()) {%>
	error[<%=listaDeporte.getString("id_deporte")%>]="<%=listaDeporte.getString("combinacion").endsWith(",")?listaDeporte.getString("combinacion").substring(0,listaDeporte.getString("combinacion").length()-2):listaDeporte.getString("combinacion")%>".split(",");<%}%>

	// verificamos si hay jugada teaser
	var refTeaser = false;
	window.frames.frmCalculadora.document.forms[0].teaser.value='false';

	if(refTeaser || ref.charAt(0)=='T' && logrosCalc.length==0) {
		if(ref.charAt(0)!='T') {
			return -1;
		}
		var refA = ref.substring(0,ref.length-2);
		var refAZ = ref.substring(ref.length-2,ref.length-1);
		var refNO;
		var nRef=parseInt(ref.substring(1,ref.length-1));
		var refNO="T"+(nRef%2==0?nRef-1:nRef+1)+".";
		//alert(refNO);
		// buscamos la referencia no aceptada
		for(var x=0;x<logrosCalc.length;x++){
			//alert(logrosCalc[x].referencia.substring(0,logrosCalc[k].referencia.length-1));
			if(refNO==logrosCalc[x].referencia){
				return -1;
			}
		}
		window.frames.frmCalculadora.document.forms[0].teaser.value='true';
		
		return 0;
	} else {
		if(ref.charAt(0)=='T') {
			return -1;
		}
	}

	if(error[deporte].length==0) {
		return -1;
	}
	
	if(deporte==<%=Constants.ID_DEPORTE_ANIMALITOS%> &&  !gameSel){
		animSel=true;
	}
	if(deporte!=<%=Constants.ID_DEPORTE_ANIMALITOS%> && !animSel){
		gameSel=true;
	}
	
	if(deporte!=<%=Constants.ID_DEPORTE_ANIMALITOS%> && animSel )
		return -2;
	if(deporte==<%=Constants.ID_DEPORTE_ANIMALITOS%> && gameSel)
		return -2;
	
	var con=0;
	var todos = n;
	var sep="-";
	for(var p=0; p < jug.length; p++) {
		//alert(parseInt(jug[p].padre)+"  "+parseInt(j));
		if(parseInt(jug[p].padre)===parseInt(j) && deporte!=<%=Constants.ID_DEPORTE_ANIMALITOS%>) {
			todos += sep + jug[p].numero;
			con++;
			}
	}
	if(con===0) {
		return 0;
	}

	var todos = permuta(todos.split('-'));
	todos = ','+todos+',';	
	for(var x=0;x<error[deporte].length;x++) {
		if(error[deporte][x].trim()=='') return -1;
		if(todos.indexOf(','+error[deporte][x]+',')!=-1) {
			return -1;
		}
	}
	return 0;
}

function permuta(letras) {
  var perm = new Array();
  for(var i=0;i<letras.length;i++){
    for(var k=0;k<letras.length;k++){
      if(letras[i]!=letras[k]){
        perm[perm.length]=letras[i]+'-'+letras[k];
      }
    }
  }
  return perm.join(",");
}


//function calcular(id,tipo,cantidad,ref,logro,nombre,juego,numero,padre,deporte) {
function calcular(checked, val) {
	var v = val.split(",");

	var id=v[0];
	var tipo=v[1];
	var cantidad=v[2];
	var ref=v[3];
	var logro=v[4]>0?'+'+v[4]:v[4];
	var nombre=v[5];
	var juego=v[6];
	var numero=v[7];
	var padre=v[8];
	var deporte=v[9];
	
	// ajustamos la referencia
	if(tipo=='A' || tipo=='B' || tipo=='RL') {
		ref = ref.substring(1);
	}
	
	if(parseInt(logro)==0){
		alert("El logro esta en cero.");
		return false;
	}
	
	if(checked) {
		if(logrosCalc.length>=<%=logros%>){
			alert("No puede exceder la cantidad de logros por apuesta");
			return false;
		}		
		var isValid = isValido(logrosCalc,padre,numero,nombre,ref,deporte);
		if(isValid==-1){
			alert("La combinacion elegida no es valida");
			return false;
		} else if(isValid==-2) {
			alert("Los juegos seleccionados no se pueden combinar en una misma apuesta");
			return false;
		}
		
		// verificamos que no este duplicado el logro
		for(var m=0; m<logrosCalc.length; m++) {
			if(logrosCalc[m].codigo==id && logrosCalc[m].tipo==tipo) {
				return false;
			}
		}

		logrosCalc[logrosCalc.length]={"codigo":id,"tipo":tipo,"cantidad":cantidad,"referencia":ref,"logro":logro,"equipo":nombre,"juego":juego,"numero":numero,"padre":padre,"deporte":deporte};
	} else {
		for(var m=0; m<logrosCalc.length; m++) {
			if(logrosCalc[m].codigo==id && logrosCalc[m].tipo==tipo) {
				logrosCalc.splice(m,1);
				break;
			}
		}
	}
	//alert(logrosCalc.length);
	
	var frm = window.frames.frmCalculadora;
	frm.llenarCalculadora(logrosCalc,reglasPago);
	
	return true;
}
function vaciar() {
        logrosCalc = new Array();
        var frm = window.frames.frmCalculadora.document.forms[0];
        for(var f=0; f<<%=logros%>; f++) {
            var c = eval("document.forms[0].R"+frm.referencia[f].value);
            //c.onclick();
            frm.codigo[f].value="";
            frm.tipo[f].value="";
            frm.cantidad[f].value="";
            frm.referencia[f].value="";
            frm.equipo[f].value="";
            frm.logro[f].value="";
            frm.juego[f].value="";
            frm.numero[f].value="";
            frm.padre[f].value="";
            frm.deporte[f].value="";
            if(c){
            	c.checked=false;
            }
        }
        frm.montoApostar.value="";
        frm.montoPremio.value=0;
        frm.resultCadena="";
        teclaPulsada.length=0;
        document.getElementById('franjaErrores').innerHTML='';
        desmarcar();
}
function desmarcar() {
	var ele = "";
	for(var i=0; i<listaReferencia.length; i++) {
		ele = document.getElementById(listaReferencia[i].cod);
		if(ele) { 
			changeCellOut(ele);
			function out(elemento) {
				var x = elemento;
				elemento.onmouseout=function() {
					changeCellOut(x);
				}
			}
			out(ele);
		}
	}
	try{
		document.getElementById('franjaErrores').innerHTML='';
		animSel=false;
		gameSel=false;
		acumulatedSports.length=0;
		
	}catch(e){
		window.alert('error1');
	}
}
function findReferencia(numRef) {
	var ele = "";
	for(var i=0; i<listaReferencia.length; i++) {
		if(listaReferencia[i].ref==numRef) {
			ele = document.getElementById(listaReferencia[i].cod);
			if(ele) { 
				changeCellClick(ele);
				return ele;
			}
		}
	}
}

function marcar(key,obj) {
    if(key.keyCode==27){
            vaciar();
            animSel=false;
    		gameSel=false;
    		acumulatedSports.length=0;
            return;
    }
	var ant = obj.value;
	obj.value=obj.value.toUpperCase();
	if(key.keyCode==13){
		try {
			if(obj.value.trim()=="") {
				try {
					window.frames.frmCalculadora.document.forms[0].montoApostar.select();
					window.frames.frmCalculadora.document.forms[0].montoApostar.focus();
				} catch(e) {}
			} else {
				try {
					var ele = findReferencia(obj.value);
					ele.onclick();
					obj.value="";
				}catch(e){
					if(obj.value.charAt(0)=='A' || obj.value.charAt(0)=='B') {
						obj.value=obj.value.charAt(0)+(parseInt(obj.value.substring(1))-1);
						var ele = findReferencia(obj.value);
						ele.onclick();
						obj.value="";
					} else {
						alert("La referencia no es valida.");
					}
				}
			}
		}catch(e){
			obj.value=ant;
			alert("La referencia no es valida");
		}
	}
}
function isFree() {
	<%if(usuario.getIdRol().equals(Constants.ROL_JUGADOR)) {%>
		return !document.forms[0].isFree[0].checked;
	<%} else {%>
		return false;
	<%}%>
}
function setSaldoActual(monto) {
	saldoActual=monto;
}
function setSaldoFreeActual(monto) {
	saldoFreeActual=monto;
}



if (!Array.prototype.indexOf) {
    Array.prototype.indexOf = function(obj, start) {
         for (var i = (start || 0), j = this.length; i < j; i++) {
             if (this[i] === obj) { return i; }
         }
         return -1;
    }
}

function changeCellClick(el) {
	if(el.onclick==null){
		el.onclick=function() {
			var hacer = calcular(el.onmouseout!=null,el.className);
			//alert(hacer);
			if(hacer) {
				if(el.onmouseout==null) {
					changeCellOut(el);
					window.frames.frmCalculadora.document.forms[0].montoApostar.value='';
					window.frames.frmCalculadora.document.forms[0].montoPremio.value='';
					el.onmouseout=function() {
						el.style.background="";
						el.style.color="#ffffff";
						var pos =acumulatedSports.indexOf(el);
						acumulatedSports.splice(pos,1);
						if(acumulatedSports.length==0){
							animSel = false;
							gameSel= false;
						}
					}
					el.onmouseout();
				} else {
					el.onmouseout=null;
					window.frames.frmCalculadora.document.forms[0].montoApostar.value='';
					window.frames.frmCalculadora.document.forms[0].montoPremio.value='';
					el.style.background="#ffff33";
					el.style.color="#000000";
					acumulatedSports.push(el);
				}
			}
		}
	}
}
function changeCell(el) {
	if(el.innerHTML.replace(/ /g,'')!=''){
		el.style.background="#ffff33";
		el.style.cursor='pointer';
		el.style.color="#000000";
		changeCellClick(el);
	}
}
function changeCellOut(el) {
	el.style.color='';el.style.background='';
}
</script>
<%if(session.getAttribute("bloqueoPantalla")!=null){%>	
<meta http-equiv="refresh" content="2500;url=<%=basePath%>listGamePlay.do">
<%}%>
<form name="frmListGamePlay" action="/game/listGamePlay.do" method="POST" style="margin:0px" onSubmit='return false;'>
<input type="hidden" name="idDeporte" value=""/>
<input type="hidden" name="agregar" value="false"/>
<input type="hidden" name="teaser" value="false"/>
<input type="hidden" name="AnimalitoSeleccionado" id="AnimalitoSeleccionado" value="false"/>
<input type="hidden" name="JuegoSeleccionado" id="JuegoSeleccionado" value="false"/>
<input type="hidden" name="dominioActual" value="<%=usuario.getDominio()%>"/>

<table align="center" width="100%"  cellspacing="0" cellpadding="0" border="0">
  <tr>
	<td id="franjaErrores" width="100%" align="left" colspan="3">
	<font color="white">
		<html:errors/>
	</font>
	</td>
  </tr>
</table>
<table align="center" width="100%" border="0" cellspacing="0" cellpadding="0" >
	<tr>
		<td id="tablaTitulo" class="tituloTablaSup tablaConBorde" width="75%" rowspan="2" >
		<font size="4" face="Tahoma,Arial"><%= usuario.getCentroHipico() %></font><br/>
		Deportes
			<table align="center" width="100%"  cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="tituloTabla tablaConBorde" style="cursor:pointer;" onclick="cargar(0)"
					>
						todos
					</td>
				<%listaDeporte.beforeFirst();
				int nDep=0;
				while(listaDeporte.next()) {%>
					<td class="tituloTabla tablaConBorde" style="cursor:pointer;" onclick="cargar(<%=listaDeporte.getString("id_deporte")%>)"
					>
						<%=listaDeporte.getString("desc_deporte")%>
					</td>
					<%nDep++;
					if(nDep>6) {
						nDep=0;%>
					</tr>
					<tr>
					<%}
				}%>
				</tr>
			</table>
		</td>
		<%if(usuario.getIdRol().equals(Constants.ROL_JUGADOR)) {%>
			<td class="tituloTabla tablaConBorde" width="25%" style="text-transform:none;">
				<input type="radio" name="isFree" value="0" checked style="<%=saldoFreeNumerico>0?"":"display:none"%>">
				Saldo <%=Constants.getDominio(request).getMoneda()%>: <span class="saldo" id="saldoJugador"><%=saldo%></span>
				&nbsp;
				<input type="radio" name="isFree" value="1" style="<%=saldoFreeNumerico>0?"":"display:none"%>">
				<%if(saldoFreeNumerico>0){%>
				Free-Play: <span class="saldo" id="saldoFreeJugador"><%=saldoFree%></span>
				<%}%>
			</td>
		<%} else {%>
			<td class="tituloTabla tablaConBorde" width="25%" >Maximo: <span class="saldo"><%=Constants.getDominio(request).getMoneda()%> <%=saldo%></span></td>
		<%}%>
	</tr>	
	<tr>
		<td class="tituloTabla1 tablaConBorde" width="25%">
			<table border="0" cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<td align="right">
					<font color="white">Ref.:</font>&nbsp;<input type="text" name="numeroRef" size="8" maxlength="8" onkeyup="marcar(event,this)" class="numeroRef"
							style="background:#c0c0c0;" 
							onfocus="this.style.background='yellow'" 
							onblur="this.style.background='#c0c0c0'" >
				</td>
			</tr>
			</table>
		</td>
	</tr>	
	<tr>
		<td id="tablaDetalle" class="tituloTablaSup tablaConBorde" valign="top" nowrap  >
		
		
			<table align="center" width="100%"   cellspacing="0" cellpadding="0" border="0">
			<caption class="tituloTablaSup">LISTA DE JUEGOS</caption>
				<%if(listaJuego!=null){
					listaJuego.beforeFirst();
				    String grupo = null;
				    int idJuego = 0;
				    int i=0;
				    boolean isIni = false;
				    boolean isLiga = false;
				    boolean isAnimalito=false;
				    String liga = "";
				    String cadLiga = "";
				    String color0 = "#000;";
				    String color1 = "#000;";
				    String fondo = "";
				    int count=1;
				    
				    boolean isLogRL = false;
				    boolean isLogRL5 = false;

				    boolean isLogAB = false;
				    boolean isLogAB5 = false;
				    
				    String[] AB = new String[]{"<b class='red'>A </b>","<b class='red'>B </b>"};
				    String[] ML1 = new String[]{"<b class='red'>SI</b> ","<b class='red'>NO</b> "};
				    String letra = "";
				    String letra1 = "";

					%>
					<tr>
						<td>
						<%while(listaJuego.next()){
							cadLiga = Constants.getFechaCortaSqlToHtml(listaJuego.getString("fecha_ini")).concat(" - ").concat(listaJuego.getString("desc_liga"));
						
							isIni = idJuego!=listaJuego.getInt("id_juego");
							isLiga = !liga.equals(cadLiga);							
							
							isBeisbol = listaJuego.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL);
							isAnimalito = listaJuego.getString("id_deporte").equals(Constants.ID_DEPORTE_ANIMALITOS);
							
							i = (isIni?-1:i);
							i++;
							prop5 = (Properties)listaJuego5.get(listaJuego.getString("id_juego").concat("_").concat(String.valueOf(i)));
							prop1 = (Properties)listaJuego1.get(listaJuego.getString("id_juego").concat("_").concat(String.valueOf(i)));
							prop0 = (Properties)listaJuego0.get(listaJuego.getString("id_juego").concat("_").concat(String.valueOf(i)));
							
							if(!Constants.isPrinterPlay(listaJuego.getString("id_deporte"))) {
								continue;
							}
							
							is5to = prop5!=null; 
							is1ro = prop1!=null; 
							is0ro = prop0!=null;
							
							isLogRL = (!listaJuego.getString("spread_logro").equals("0")); 
							isLogRL5 =(is5to && !prop5.getProperty("spread_logro").equals("0"));

							isLogAB = (!listaJuego.getString("total_logro").equals("0")); 
							isLogAB5 =(is5to && !prop5.getProperty("total_logro").equals("0"));
							 
							letra = i<2?AB[i]:"";
							letra1 = i<2?ML1[i]:"";
							if(!isAnimalito){
							if(isIni){
								ind=0;
								if(isLiga){
									fondo = (fondo.equals(color1)?color0:color1);
										if(idJuego!=0){%>
												</table>
											</fieldset>
										<%}%>
						<fieldset id="deporte_<%=Constants.getIdDeporte(listaJuego.getString("id_deporte"))%>">
							<legend class="tituloTablaSup"><%=Constants.getFechaCortaSqlToHtml(listaJuego.getString("fecha_ini"))%> - <%=listaJuego.getString("desc_liga")%> (<%=listaJuego.getString("iniciales")%>)&nbsp;</legend>
						<table align="center" width="100%" class="cuadro"  cellspacing="1" cellpadding="1" border="0">
							<tr class="tituloLiga" style="background-color:<%=fondo%>">
								<td colspan="<%=isActiveSRL && isBeisbol?6:5%>" style="border-right:4px solid #000">Juego Completo</td>
								<%=!isAnimalito ? "<td colspan=\"3\" style=\"border-right:4px solid #000\">5to Inning o Primera Mitad</td>" : "" %>
								<%=!isAnimalito ? "<td colspan=\"1\" style=\"border-right:4px solid #000\">1er Inning</td>" : "" %>
								<%=!isAnimalito ? "<td colspan=\"1\">Anota 1ro</td>" : "" %>
							</tr>
								<%}%>
							<tr class="titulo1" style="background-color:<%=fondo%>">
								<td width="45px">Ref.</td>
								<td><span  class="fechaJuego"><%=Constants.getFechaLargaSqlToHtml(listaJuego.getString("fecha_ini"))%> <%=isAnimalito ? " - "+listaJuego.getString("campeonato"): "" %></span></td>
								<td width="50" >M.Line</td>
								<%=!isAnimalito ? "<td width=\"60\" >R.Line</td>" : "" %>
								<%=isActiveSRL && isBeisbol?"<td width='60' >S.R.Line</td>":""%>
								<%=!isAnimalito ? "<td width=\"60\" >A/B</td>" : "" %>
								<%=!isAnimalito ? "<td width=\"60\" >M.Line</td>" : "" %>
								<%=!isAnimalito ? "<td width=\"60\" >R.Line</td>" : "" %>
								<%=!isAnimalito ? "<td width=\"60\" >A/B</td>" : "" %>
								<%=!isAnimalito ? "<td width=\"60\" >M.Line</td>" : "" %>
								<%=!isAnimalito ? "<td width=\"60\" >M.Line</td>" : "" %>
							</tr>
							<%}%>
							<tr  style="background-color:<%=fondo%>">
								<td width="30px"><%= listaJuego.getString("referencia") %></td>
								<td class="left"><%=listaJuego.getString("desc_equipo")%></td>
								<td id="<%=listaJuego.getString("id_usuario_juego_equipo").concat("_").concat(listaJuego.getString("referencia"))%>" onmouseover="changeCell(this)" onmouseout="changeCellOut(this)" class="<%=listaJuego.getInt("id_usuario_juego_equipo")%>,<%=Constants.TIPO_MONEYLINE%>, ,<%=listaJuego.getString("referencia")%>,<%=listaJuego.getString("money_line")%>,<%=listaJuego.getString("desc_equipo")%>,<%=listaJuego.getInt("id_juego")%>,<%=listaJuego.getInt("id_equipo")==1?25:1+(i*4)%>,<%=listaJuego.getInt("id_juego")%>,<%=listaJuego.getInt("id_deporte")%>"
									><%=Constants.signo(listaJuego.getString("money_line"))%></td>
									
								<td id="<%=listaJuego.getString("id_usuario_juego_equipo").concat("_").concat(listaJuego.getString("referencia_runline"))%>" onmouseover="changeCell(this)" onmouseout="changeCellOut(this)" class="<%=listaJuego.getInt("id_usuario_juego_equipo")%>,<%=Constants.TIPO_RUNLINE%>,<%=isLogRL?Constants.f1(listaJuego.getString("spread")):""%>,<%=listaJuego.getString("referencia_runline")%>,<%=listaJuego.getString("spread_logro")%>,<%=listaJuego.getString("desc_equipo")%>,<%=listaJuego.getInt("id_juego")%>,<%=2+(i*4)%>,<%=listaJuego.getInt("id_juego")%>,<%=listaJuego.getInt("id_deporte")%>"
									><%=isLogRL?Constants.f1(listaJuego.getString("spread")):""%> <%=isLogRL?Constants.signo(listaJuego.getString("spread_logro")):""%></td>
									
								<%if(isActiveSRL && isBeisbol){%>	
								<td id="<%=listaJuego.getString("id_usuario_juego_equipo").concat("_").concat(listaJuego.getString("referencia_super_runline"))%>" onmouseover="changeCell(this)" onmouseout="changeCellOut(this)" class="<%=listaJuego.getInt("id_usuario_juego_equipo")%>,<%=Constants.TIPO_SUPER_RUNLINE%>,<%=isLogRL?Constants.f1(listaJuego.getString("super_spread")):""%>,<%=listaJuego.getString("referencia_super_runline")%>,<%=listaJuego.getString("super_spread_logro")%>,<%=listaJuego.getString("desc_equipo")%>,<%=listaJuego.getInt("id_juego")%>,<%=3+(i*4)%>,<%=listaJuego.getInt("id_juego")%>,<%=listaJuego.getInt("id_deporte")%>"
									><%=isLogRL?Constants.f1(listaJuego.getString("super_spread")):""%> <%=isLogRL?Constants.signo(listaJuego.getString("super_spread_logro")):""%></td>
								<%}%>
								
								<td id="<%=listaJuego.getString("id_usuario_juego_equipo").concat("_").concat(listaJuego.getString("referencia_ab"))%>" onmouseover="changeCell(this)" onmouseout="changeCellOut(this)" class="<%=listaJuego.getInt("id_usuario_juego_equipo")%>,<%=(i==0?Constants.TIPO_ALTA:(i==1?Constants.TIPO_BAJA:""))%>,<%=isLogAB?Constants.f0(listaJuego.getString("total")):"" %>,<%=listaJuego.getString("referencia_ab")%>,<%=listaJuego.getString("total_logro")%>,<%=listaJuego.getString("desc_equipo")%>,<%=listaJuego.getInt("id_juego")%>,<%=4+(i*4)%>,<%=listaJuego.getInt("id_juego")%>,<%=listaJuego.getInt("id_deporte")%>"
									><%=isLogAB?letra.concat(Constants.f0(listaJuego.getString("total"))):"" %> <%=isLogAB?Constants.signo(listaJuego.getString("total_logro")):""%></td>
									
								<td id="<%=Constants.getProperty(prop5,"id_usuario_juego_equipo").concat("_").concat(Constants.getProperty(prop5,"referencia"))%>" onmouseover="changeCell(this)" onmouseout="changeCellOut(this)" class="<%=Constants.getProperty(prop5,"id_usuario_juego_equipo")%>,<%=Constants.TIPO_MONEYLINE%>, ,<%=Constants.getProperty(prop5,"referencia")%>,<%=Constants.getProperty(prop5,"money_line")%>,<%=listaJuego.getString("desc_equipo")%>,<%=Constants.getProperty(prop5,"id_juego")%>,<%=9+(i*4)%>,<%=Constants.getProperty(prop5,"id_juego_padre")%>,<%=Constants.getProperty(prop5,"id_deporte")%>" 
									><%=is5to?Constants.signo(prop5.getProperty("money_line")):""%></td>
									
								<td id="<%=Constants.getProperty(prop5,"id_usuario_juego_equipo").concat("_").concat(Constants.getProperty(prop5,"referencia_runline"))%>" onmouseover="changeCell(this)" onmouseout="changeCellOut(this)" class="<%=Constants.getProperty(prop5,"id_usuario_juego_equipo")%>,<%=Constants.TIPO_RUNLINE%>,<%=is5to && isLogRL5?Constants.f1(prop5.getProperty("spread")):""%>,<%=Constants.getProperty(prop5,"referencia_runline")%>,<%=Constants.getProperty(prop5,"spread_logro")%>,<%=listaJuego.getString("desc_equipo")%>,<%=Constants.getProperty(prop5,"id_juego")%>,<%=10+(i*4)%>,<%=Constants.getProperty(prop5,"id_juego_padre")%>,<%=Constants.getProperty(prop5,"id_deporte")%>" 
									><%=is5to && isLogRL5?Constants.f1(prop5.getProperty("spread")):""%> <%=is5to && isLogRL5?Constants.signo(prop5.getProperty("spread_logro")):""%></td>
									
								<td id="<%=Constants.getProperty(prop5,"id_usuario_juego_equipo").concat("_").concat(Constants.getProperty(prop5,"referencia_ab"))%>" onmouseover="changeCell(this)" onmouseout="changeCellOut(this)" class="<%=Constants.getProperty(prop5,"id_usuario_juego_equipo")%>,<%=(i==0?Constants.TIPO_ALTA:(i==1?Constants.TIPO_BAJA:""))%>,<%=is5to && isLogAB5?Constants.f0(prop5.getProperty("total")):"" %>,<%=Constants.getProperty(prop5,"referencia_ab")%>,<%=Constants.getProperty(prop5,"total_logro")%>,<%=listaJuego.getString("desc_equipo")%>,<%=Constants.getProperty(prop5,"id_juego")%>,<%=12+(i*4)%>,<%=Constants.getProperty(prop5,"id_juego_padre")%>,<%=Constants.getProperty(prop5,"id_deporte")%>"
									><%=is5to && isLogAB5?letra.concat(Constants.f0(prop5.getProperty("total"))):"" %> <%=is5to && isLogAB5?Constants.signo(prop5.getProperty("total_logro")):""%></td>
									
								<td id="<%=Constants.getProperty(prop1,"id_usuario_juego_equipo").concat("_").concat(Constants.getProperty(prop1,"referencia"))%>" onmouseover="changeCell(this)" onmouseout="changeCellOut(this)" class="<%=Constants.getProperty(prop1,"id_usuario_juego_equipo")%>,<%=(i==0?Constants.TIPO_SI:(i==1?Constants.TIPO_NO:""))%>, ,<%=Constants.getProperty(prop1,"referencia")%>,<%=Constants.getProperty(prop1,"money_line")%>,<%=listaJuego.getString("desc_equipo")%>,<%=Constants.getProperty(prop1,"id_juego")%>,<%=17+(i*4)%>,<%=Constants.getProperty(prop1,"id_juego_padre")%>,<%=Constants.getProperty(prop1,"id_deporte")%>"
									><%=is1ro?letra1.concat(Constants.signo(prop1.getProperty("money_line"))):""%></td>
									
								<td id="<%=Constants.getProperty(prop0,"id_usuario_juego_equipo").concat("_").concat(Constants.getProperty(prop0,"referencia"))%>" onmouseover="changeCell(this)" onmouseout="changeCellOut(this)" class="<%=Constants.getProperty(prop0,"id_usuario_juego_equipo")%>,<%=Constants.TIPO_ANOTAPRIMERO%>, ,<%=Constants.getProperty(prop0,"referencia")%>,<%=Constants.getProperty(prop0,"money_line")%>,<%=listaJuego.getString("desc_equipo")%>,<%=Constants.getProperty(prop0,"id_juego")%>,<%=19+(i*4)%>,<%=Constants.getProperty(prop0,"id_juego_padre")%>,<%=Constants.getProperty(prop0,"id_deporte")%>"
									><%=is0ro?Constants.signo(prop0.getProperty("money_line")):""%></td>
							</tr>
							<%idJuego=listaJuego.getInt("id_juego");
							liga=cadLiga;
						}else{
							
						
							if(isIni){
								ind=0;
								if(isLiga){
									fondo = (fondo.equals(color1)?color0:color1);
										if(idJuego!=0){%>
												</table>
											</fieldset>
										<%}%>
						<fieldset id="deporte_<%=Constants.getIdDeporte(listaJuego.getString("id_deporte"))%>">
							<legend class="tituloTablaSup"><%=Constants.getFechaCortaSqlToHtml(listaJuego.getString("fecha_ini"))%> - <%=listaJuego.getString("desc_liga")%> (<%=listaJuego.getString("iniciales")%>)&nbsp;</legend>
						<table align="center" width="100%" class="cuadro"  cellspacing="1" cellpadding="1" border="0">
							<tr class="tituloLiga" style="background-color:<%=fondo%>">
								<td colspan="15" >Juego Completo</td>
							</tr>
								<%}%>
							<tr class="titulo1" style="background-color:<%=fondo%>">
								<td colspan="15" style="border-right:4px solid #000"><span  class="fechaJuego"><%=Constants.getFechaLargaSqlToHtml(listaJuego.getString("fecha_ini"))%> <%=isAnimalito ? " - "+listaJuego.getString("campeonato"): "" %></span></td>
							</tr>
							<tr class="titulo1" style="background-color:<%=fondo%>">
								<td width="45px">Ref.</td>
								<td width="45px">Item.</td>
								<td width="50" style="border-right:4px solid #000">M.Line</td>
								<td width="45px">Ref.</td>
								<td width="45px">Item.</td>
								<td width="50" style="border-right:4px solid #000">M.Line</td>
								<td width="45px">Ref.</td>
								<td width="45px">Item.</td>
								<td width="50" style="border-right:4px solid #000">M.Line</td>
								<td width="45px">Ref.</td>
								<td width="45px">Item.</td>
								<td width="50" style="border-right:4px solid #000">M.Line</td>
							</tr>
							<%}
							if(count==1){%>										
								<tr  style="background-color:<%=fondo%>">
								<%} %>
								<td width="30px"><%= listaJuego.getString("referencia") %></td>
								<td class="left"><%=listaJuego.getString("desc_equipo")%></td>
								<td id="<%=listaJuego.getString("id_usuario_juego_equipo").concat("_").concat(listaJuego.getString("referencia"))%>" onmouseover="changeCell(this)" onmouseout="changeCellOut(this)" class="<%=listaJuego.getInt("id_usuario_juego_equipo")%>,<%=Constants.TIPO_MONEYLINE%>, ,<%=listaJuego.getString("referencia")%>,<%=listaJuego.getString("money_line")%>,<%=listaJuego.getString("desc_equipo")%>,<%=listaJuego.getInt("id_juego")%>,<%=listaJuego.getInt("id_equipo")==1?25:1+(i*4)%>,<%=listaJuego.getInt("id_juego")%>,<%=listaJuego.getInt("id_deporte")%>"
									style="border-right:4px solid #fff"><%=Constants.signo(listaJuego.getString("money_line"))%></td>
									
								<td style="display:none;" id="<%=listaJuego.getString("id_usuario_juego_equipo").concat("_").concat(listaJuego.getString("referencia_runline"))%>" onmouseover="changeCell(this)" onmouseout="changeCellOut(this)" class="<%=listaJuego.getInt("id_usuario_juego_equipo")%>,<%=Constants.TIPO_RUNLINE%>,<%=isLogRL?Constants.f1(listaJuego.getString("spread")):""%>,<%=listaJuego.getString("referencia_runline")%>,<%=listaJuego.getString("spread_logro")%>,<%=listaJuego.getString("desc_equipo")%>,<%=listaJuego.getInt("id_juego")%>,<%=2+(i*4)%>,<%=listaJuego.getInt("id_juego")%>,<%=listaJuego.getInt("id_deporte")%>"
									><%=isLogRL?Constants.f1(listaJuego.getString("spread")):""%> <%=isLogRL?Constants.signo(listaJuego.getString("spread_logro")):""%></td>
									
								<td style="display:none;" id="<%=listaJuego.getString("id_usuario_juego_equipo").concat("_").concat(listaJuego.getString("referencia_ab"))%>" onmouseover="changeCell(this)" onmouseout="changeCellOut(this)" class="<%=listaJuego.getInt("id_usuario_juego_equipo")%>,<%=(i==0?Constants.TIPO_ALTA:(i==1?Constants.TIPO_BAJA:""))%>,<%=isLogAB?Constants.f0(listaJuego.getString("total")):"" %>,<%=listaJuego.getString("referencia_ab")%>,<%=listaJuego.getString("total_logro")%>,<%=listaJuego.getString("desc_equipo")%>,<%=listaJuego.getInt("id_juego")%>,<%=4+(i*4)%>,<%=listaJuego.getInt("id_juego")%>,<%=listaJuego.getInt("id_deporte")%>"
									><%=isLogAB?letra.concat(Constants.f0(listaJuego.getString("total"))):"" %> <%=isLogAB?Constants.signo(listaJuego.getString("total_logro")):""%></td>
									
								<td style="display:none;" id="<%=Constants.getProperty(prop5,"id_usuario_juego_equipo").concat("_").concat(Constants.getProperty(prop5,"referencia"))%>" onmouseover="changeCell(this)" onmouseout="changeCellOut(this)" class="<%=Constants.getProperty(prop5,"id_usuario_juego_equipo")%>,<%=Constants.TIPO_MONEYLINE%>, ,<%=Constants.getProperty(prop5,"referencia")%>,<%=Constants.getProperty(prop5,"money_line")%>,<%=listaJuego.getString("desc_equipo")%>,<%=Constants.getProperty(prop5,"id_juego")%>,<%=9+(i*4)%>,<%=Constants.getProperty(prop5,"id_juego_padre")%>,<%=Constants.getProperty(prop5,"id_deporte")%>" 
									><%=is5to?Constants.signo(prop5.getProperty("money_line")):""%></td>
									
								<td style="display:none;" id="<%=Constants.getProperty(prop5,"id_usuario_juego_equipo").concat("_").concat(Constants.getProperty(prop5,"referencia_runline"))%>" onmouseover="changeCell(this)" onmouseout="changeCellOut(this)" class="<%=Constants.getProperty(prop5,"id_usuario_juego_equipo")%>,<%=Constants.TIPO_RUNLINE%>,<%=is5to && isLogRL5?Constants.f1(prop5.getProperty("spread")):""%>,<%=Constants.getProperty(prop5,"referencia_runline")%>,<%=Constants.getProperty(prop5,"spread_logro")%>,<%=listaJuego.getString("desc_equipo")%>,<%=Constants.getProperty(prop5,"id_juego")%>,<%=10+(i*4)%>,<%=Constants.getProperty(prop5,"id_juego_padre")%>,<%=Constants.getProperty(prop5,"id_deporte")%>" 
									><%=is5to && isLogRL5?Constants.f1(prop5.getProperty("spread")):""%> <%=is5to && isLogRL5?Constants.signo(prop5.getProperty("spread_logro")):""%></td>
									
								<td style="display:none;" id="<%=Constants.getProperty(prop5,"id_usuario_juego_equipo").concat("_").concat(Constants.getProperty(prop5,"referencia_ab"))%>" onmouseover="changeCell(this)" onmouseout="changeCellOut(this)" class="<%=Constants.getProperty(prop5,"id_usuario_juego_equipo")%>,<%=(i==0?Constants.TIPO_ALTA:(i==1?Constants.TIPO_BAJA:""))%>,<%=is5to && isLogAB5?Constants.f0(prop5.getProperty("total")):"" %>,<%=Constants.getProperty(prop5,"referencia_ab")%>,<%=Constants.getProperty(prop5,"total_logro")%>,<%=listaJuego.getString("desc_equipo")%>,<%=Constants.getProperty(prop5,"id_juego")%>,<%=12+(i*4)%>,<%=Constants.getProperty(prop5,"id_juego_padre")%>,<%=Constants.getProperty(prop5,"id_deporte")%>"
									><%=is5to && isLogAB5?letra.concat(Constants.f0(prop5.getProperty("total"))):"" %> <%=is5to && isLogAB5?Constants.signo(prop5.getProperty("total_logro")):""%></td>
									
								<td style="display:none;" id="<%=Constants.getProperty(prop1,"id_usuario_juego_equipo").concat("_").concat(Constants.getProperty(prop1,"referencia"))%>" onmouseover="changeCell(this)" onmouseout="changeCellOut(this)" class="<%=Constants.getProperty(prop1,"id_usuario_juego_equipo")%>,<%=(i==0?Constants.TIPO_SI:(i==1?Constants.TIPO_NO:""))%>, ,<%=Constants.getProperty(prop1,"referencia")%>,<%=Constants.getProperty(prop1,"money_line")%>,<%=listaJuego.getString("desc_equipo")%>,<%=Constants.getProperty(prop1,"id_juego")%>,<%=17+(i*4)%>,<%=Constants.getProperty(prop1,"id_juego_padre")%>,<%=Constants.getProperty(prop1,"id_deporte")%>"
									><%=is1ro?letra1.concat(Constants.signo(prop1.getProperty("money_line"))):""%></td>
									
								<td style="display:none;" id="<%=Constants.getProperty(prop0,"id_usuario_juego_equipo").concat("_").concat(Constants.getProperty(prop0,"referencia"))%>" onmouseover="changeCell(this)" onmouseout="changeCellOut(this)" class="<%=Constants.getProperty(prop0,"id_usuario_juego_equipo")%>,<%=Constants.TIPO_ANOTAPRIMERO%>, ,<%=Constants.getProperty(prop0,"referencia")%>,<%=Constants.getProperty(prop0,"money_line")%>,<%=listaJuego.getString("desc_equipo")%>,<%=Constants.getProperty(prop0,"id_juego")%>,<%=19+(i*4)%>,<%=Constants.getProperty(prop0,"id_juego_padre")%>,<%=Constants.getProperty(prop0,"id_deporte")%>"
									><%=is0ro?Constants.signo(prop0.getProperty("money_line")):""%></td>
					<%
					count++;
					if(count==5){
					 count=1;%>	
							</tr>
								<%} %>
							<%
							
							idJuego=listaJuego.getInt("id_juego");
							liga=cadLiga;
						}
						}%>
								</table>
							</fieldset>
						</td>
					</tr>
				<%} else {%>
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
				<%}%>
			</table>
		
		 
		</td>
		<td class="tituloTablaSup tablaConBorde" valign="top" >
			<iframe id="frmCalculadora" name="frmCalculadora" width="100%" height="550" src="<%=basePath%>showCalculator.do" frameborder="0" scrolling="no"/></iframe>
		</td>
	</tr>	
</table>
</form>
<%if(session.getAttribute("apuesta")!=null) {%>
<script language="javascript">
	<% ArrayList jug =  (ArrayList)session.getAttribute("apuesta"); 
	for(int i = 0; i < jug.size(); i++) {
		CalculadoraForm cal =  (CalculadoraForm) jug.get(i);%>
		iniciar(<%=cal.getCodigo()%>,'<%=cal.getTipo()%>','<%=cal.getCantidad()%>','<%=cal.getReferencia()%>','<%=cal.getLogro()%>','<%=cal.getEquipo()%>','<%=cal.getJuego()%>','<%=cal.getNumero()%>','<%=cal.getPadre()%>','<%=cal.getDeporte()%>');
		window.frames.frmCalculadora.document.forms[0].montoApostar.value='<%=cal.getMontoApostar()%>';
		window.frames.frmCalculadora.document.forms[0].montoPremio.value='<%=cal.getMontoPremio()%>';
	<%}%>
	animSel=false;
	gameSel=false;
</script>
<%}%>
<script>
	document.forms[0].numeroRef.focus();

	<%if(session.getAttribute("bloqueoPantalla")!=null){%>	
		window.parent.bloqueoDeMenu(); 
	<%}%>
</script>
<div id="MASCARA" class='fondoTransparente' style="display:none;"></div>
	<div id="MASCARA_MENSAJE" class='centrado' style="display:none;border:2px solid red;">
		<table border="0" width="100%" >
			<tr>
				<td height="1%" align="center">
					<b>No realize niguna acci&oacute;n hasta que el ticket se imprima...</b>
				</td>
			</tr>
			<tr>
				<td height="1%" align="center">
					<img src="./images/await.gif"/>
				</td>
			</tr>
		</table>
	</div>
</div>
<%session.setAttribute("sendErrores",null);%>

