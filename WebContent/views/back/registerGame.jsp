<jsp:directive.page import="com.betcesc.game.common.Constants"/><%@ include file="/views/common/taglibs.jsp"%>
<%@ page import="com.betcesc.game.ctrl.back.RegisterGameActionForm" %>
<% RegisterGameActionForm forma = (RegisterGameActionForm) request.getAttribute("juego"); 
%>

<script language="JavaScript" src="<%=basePath%>scripts/Calendario_2.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/ajax.js"></script>
<script language="javascript">
var accion = "";
var opcion = "";
var opcionLanzador = "";
var listaDeporteEmpate = <%=request.getAttribute("listaDeporteEmpate")%>;
var isEmpate = "false";
var runline = 0;
var meses = ['Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic'];
var mesesUS = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];

var mes0 = ['ene','feb','mar','abr','may','jun','jul','ago','sep','oct','nov','dic'];
var mes1 = ['jan','feb','mar','apr','may','jun','jul','aug','sep','oct','nov','dec'];

var lan0 = '';
var lan1 = '';

function send(){
	with(document.forms[0]){
		if(isEmpty(idDeporte,"El Deporte",1)) { return false; }
		if(isEmpty(fechaIni,"La Fecha de Inicio de Juego")) { return false; }
		if(isEmpty(idLiga,"La Liga",1)) { return false; }
		//if(isEmpty(nombre,"El Campeonato")) { return false; }
		if(trim(nombre.value)==""){
			nombre.value="Serie Regular";
			idCampeonato.value="2";
		} 

		if(isEmptyInt(_Referencia[0],"La Referencia Uno")) { return false; }
		if(isEmpty(_IdEquipo[0],"El Equipo Uno")) { return false; } 
		if(isEmptyInt(_IdEquipo[0],"El Equipo Dos")) { return false; }
		//if(idDeporte.value==<%=Constants.ID_EQUIPO_BEISBOL%>) {
		//	if(trim(_IdLanzador[0].value)==="") { alert("Por Favor ingrese el Lanzador del Equipo Uno"); _IdLanzador[0].focus(); return false; }
		//}
		if(isEmpty(_MoneyLine[0],"El MoneyLine Uno")) { return false; }
		if(isEmpty(_Spread[0],"El RunLine Uno")) { return false; }
		if(isEmpty(_SpreadLogro[0],"El Logro del RunLine Uno")) { return false; }
		if(isEmpty(_Total[0],"la Alta")) { return false; }
		if(isEmpty(_TotalLogro[0],"El Logro la Alta")) { return false; }

		if(isEmptyInt(_Referencia[1],"La Referencia Dos")) { return false; }
		if(_Referencia[0].value===_Referencia[1].value) { 
			alert("Las referencias no pueden ser iguales");	_Referencia[1].focus(); return false; 
		}
		if(isEmpate==="true") {
			if(_Referencia[0].value===_Referencia[2].value || _Referencia[1].value===_Referencia[2].value) { 
				alert("Las referencias no pueden ser iguales");	_Referencia[1].focus(); return false; 
			}
		}
		
		if(isEmpty(_IdEquipo[1],"El Equipo Dos")) { return false; }
		if(isEmptyInt(_IdEquipo[1],"El Equipo Dos")) { return false; }

		if(_IdEquipo[0].value===_IdEquipo[1].value) { alert("Los Equipos uno y dos no pueden ser iguales");	_IdEquipo[1].focus(); return false; }

		//if(idDeporte.value==<%=Constants.ID_EQUIPO_BEISBOL%>) {
		//	if(trim(_IdLanzador[1].value)==="") { alert("Por Favor ingrese el Lanzador del Equipo Dos"); _IdLanzador[1].focus(); return false; }
		//}
		if(isEmpty(_MoneyLine[1],"El MoneyLine Dos")) { return false; }
		if(isEmpty(_Spread[1],"El RunLine Dos")) { return false; }
		if(isEmpty(_SpreadLogro[1],"El Logro del RunLine Dos")) { return false; }
		if(isEmpty(_Total[1],"la baja")) { return false; }
		if(isEmpty(_TotalLogro[1],"El Logro del la baja")) { return false; }
		
		if(_Referencia[2].style.display="") {
			if(isEmptyInt(_Referencia[2],"La Referencia Tres")) { return false; }
		}
		
		document.getElementById('botones').style.display="none";

		submit();
	}
}
function eliminar() {
	document.forms[0].eliminar.value='true';
	send();
}
function abrir() {
	with(document.forms[0]){
		if(isEmptyInt(_MoneyLine[0],"El MoneyLine Uno")) { return false; }
		//if(isEmptyInt(_Spread[0],"El RunLine Uno")) { return false; }
		//if(isEmptyInt(_SpreadLogro[0],"El Logro del RunLine Uno")) { return false; }
		//if(isEmptyInt(_Total[0],"la Alta")) { return false; }
		//if(isEmptyInt(_TotalLogro[0],"El Logro la Alta")) { return false; }

		if(isEmptyInt(_MoneyLine[1],"El MoneyLine Dos")) { return false; }
		//if(isEmptyInt(_Spread[1],"El RunLine Dos")) { return false; }
		//if(isEmptyInt(_SpreadLogro[1],"El Logro del RunLine Dos")) { return false; }
		//if(isEmptyInt(_Total[1],"la Baja")) { return false; }
		//if(isEmptyInt(_TotalLogro[1],"El Logro de la baja")) { return false; }
	}

	document.forms[0].abrir.value='true';
	send();
}


function llenar(pos,lanzador) {
	accion="llenar";
	opcion = pos;
	opcionLanzador = lanzador;
	var cadena = "";
	with(document.forms[0]) {
		mostrarSRL();
	
		cadena = (pos==1?idDeporte.value:cadena);
		cadena = (pos==2?idLiga.value:cadena);
		cadena = (pos==3?document.getElementById("idEquipo"+lanzador).value:cadena);
		http = new Ajax("<%=basePath%>llenarAjax.do?table="+pos+"&id="+cadena);
		http.setMetodo("POST");
		http.start();
	}
	if(pos==1) {
		mostrarEmpate();
		document.forms[0].nombre.value='';
	}
}

function mostrarSRL() {
	var mostrarSuper = (document.forms[0].idDeporte.value=='<%=Constants.ID_EQUIPO_BEISBOL%>'?'':'none');
	document.getElementById("titSRL").style.display=mostrarSuper;
	document.getElementById("colSRLA1").style.display=mostrarSuper;
	document.getElementById("colSRLA2").style.display=mostrarSuper;
	document.getElementById("colSRLB1").style.display=mostrarSuper;
	document.getElementById("colSRLB2").style.display=mostrarSuper;
	document.getElementById("colSRLC1").style.display=mostrarSuper;
	document.getElementById("colSRLC2").style.display=mostrarSuper;
	
	if(mostrarSuper=='none'){
		with(document.forms[0]) {
			_SuperSpread[0].value="0";
			_SuperSpread[1].value="0";
		
			_SuperSpreadDecimal[0].value="0";
			_SuperSpreadDecimal[1].value="0";
			
			_SuperSpreadLogro[0].value="0";
			_SuperSpreadLogro[1].value="0";
		}
	}
}

function mostrarEmpate() {
	try{
		var visible="none";
		isEmpate="false";
		if(listaDeporteEmpate.length>0) {
			for(var x=0; x<listaDeporteEmpate.length; x++) {	
				if(document.forms[0].idDeporte.value==listaDeporteEmpate[x].idDeporte) {
					visible="";
					break;
				}
			}
		}
		if(visible=="none") {
			document.forms[0]._MoneyLine[2].value=0;
			isEmpate="false";
		} else {
			isEmpate="true";
		}
		document.getElementById("rowDraw").style.display=visible;
	} catch(e) {}
}

function responseText(texto) {
	if (accion=="llenar") {
	   	if(opcion=="1") {
		   	texto = (texto!="[]"?"[{value:'',text:'Seleccione una liga...'},"+texto.substring(1):"[{value:'',text:'Seleccione un deporte...'}]");
	   		llenarCombo("idLiga",texto);
	   		// ahora buscamos la ultima referencia
			accion="referencia";
			http = new Ajax("<%=basePath%>referenciaAjax.do?idDeporte="+document.forms[0].idDeporte.value);
			http.setMetodo("POST");
			http.start();
	   	} else if(opcion=="2") {
	   		cadena = (texto!="[]"?"[{value:'',text:'Seleccione el equipo...'},"+texto.substring(1):"[{value:'',text:'Seleccione una liga...'}]");
	   		for(j=0;j<document.forms[0]._IdEquipo.length-1;j++) {
		   		llenarCombo("idEquipo"+j,cadena);
		   	}
	   	} else if(opcion=="3") {
		   	texto = (texto!="[]"?"[{value:'',text:'Seleccione el lanzador...'},"+texto.substring(1):"[{value:'',text:'Seleccione un equipo...'}]");
	   		llenarCombo("idLanzador"+opcionLanzador,texto);
	   		if(opcionLanzador==0) {
	   			llenar(3,1);
	   		}
	   		if(opcionLanzador==1) {
	   			if(lan0!='') {
	   				cargarLanzador(0,lan0);
	   			}
	   			if(lan1!='') {
	   				cargarLanzador(1,lan1);
	   			}
	   		}
	   	}
   	} else if (accion=="guardar") {
		try {
			document.forms[0].idCampeonato.value=parseInt(texto);
		} catch(e){
			document.forms[0].idCampeonato.value = "0";
			document.forms[0].nombre.value="";
		}
   	} else if (accion=="buscar") {
		if ( texto != "" ) {
			llenarComboAjax("lista",texto,"&&&");
			if (document.getElementById("lista").length > 0 ) {
				document.getElementById("lista").style.display = "";
			}
		} else {
			document.getElementById("lista").style.display = "none";
		}
   	} else if (accion=="referencia") {
   		with(document.forms[0]) {
   			if(trim(texto)!=='') {
   			var valores = eval("("+texto+")");
   			if(valores.referencia!=='') {
	   			var n = parseInt(valores.referencia);
	   			_Referencia[0].value = n+1;
	   			_Referencia[1].value = n+2;
	   			if(isEmpate=="true") {
	   				_Referencia[2].value = n+3;
	   			} else {
	   				try {
	   					_Referencia[2].value = "0";
	   				}catch(e){alert("error");}
	   			}
	   			
   			}
   			if(valores.runline!=='' && valores.runline!=='0') {
   				runline = parseFloat(valores.runline);
	   			_Spread[0].value = parseInt(valores.runline);
	   			_Spread[1].value = parseInt(valores.runline)*(-1);
	   			_SpreadDecimal[0].value = (parseFloat(valores.runline)%1)*10;
	   			_SpreadDecimal[1].value = (parseFloat(valores.runline)%1)*10;
	   			
	   			_SpreadLogro[0].value = parseInt(valores.runlineLogro0);
	   			_SpreadLogro[1].value = parseInt(valores.runlineLogro1);
	   			
	   			_TotalLogro[0].value = parseInt(valores.altabajaLogro0);
	   			_TotalLogro[1].value = parseInt(valores.altabajaLogro1);
	   		}
	   		}
   		}
	}
   	
}


function numerar() {
	with(document.forms[0]) {
		try {
			if(trim(_Referencia[0].value)!=='') {
				_Referencia[1].value = "0";
				_Referencia[1].value = parseInt(_Referencia[0].value)+1;
	   			if(isEmpate=="true") {
					_Referencia[2].value = parseInt(_Referencia[1].value)+1;
	   			} else {
	   				try {
	   					_Referencia[2].value = "0";
	   				}catch(e){}
	   			}
			}
		}catch(e){}
	}
}

function cambiarRunLine(indice, onlySuperRunline) {
	with(document.forms[0]) {
		if(!onlySuperRunline) {
			var r0 = parseInt(_Spread[0].value);
			var r1 = parseInt(_Spread[1].value);
			var d0 = parseInt(_SpreadDecimal[0].value);
			var d1 = parseInt(_SpreadDecimal[1].value);
			var m0 = parseInt(_MoneyLine[0].value);
			var m1 = parseInt(_MoneyLine[1].value);
			if(m0<m1) {
				_Spread[0].value= (r0<r1?r0:r1);
				_SpreadDecimal[0].value= (r0<r1?d0:d1);
				_Spread[1].value= (r0<r1?r1:r0);
				_SpreadDecimal[1].value= (r0<r1?d1:d0);
			} else {
				_Spread[0].value= (r0<r1?r1:r0);
				_SpreadDecimal[0].value= (r0<r1?d1:d0);
				_Spread[1].value= (r0<r1?r0:r1);
				_SpreadDecimal[1].value= (r0<r1?d0:d1);
			}
		}
		
		// logros para el moneyline 
		if(indice==0 || indice==1) {
			h = (indice==0?1:0);
			
			if(idDeporte.value!='8') {
				var obj =  findLogros(_MoneyLine[indice].value)
				if(obj!=null){
					_MoneyLine[h].value=obj.hembra;

					if(onlySuperRunline) {
						_SuperSpread[indice].value=getEntero(obj.FSRL0);
						_SuperSpread[h].value=getEntero(obj.FSRL1);
						_SuperSpreadDecimal[indice].value=getDecimal(obj.FSRL0);
						_SuperSpreadDecimal[h].value=getDecimal(obj.FSRL1);
						_SuperSpreadLogro[indice].value=getEntero(obj.LSRL0);
						_SuperSpreadLogro[h].value=getEntero(obj.LSRL1);
					} else {
					
					//if(idDeporte.value=='4' || idDeporte.value=='3') {
						_Spread[indice].value=getEntero(obj.FRL0);
						_Spread[h].value=getEntero(obj.FRL1);
						_SpreadDecimal[indice].value=getDecimal(obj.FRL0);
						_SpreadDecimal[h].value=getDecimal(obj.FRL1);
						_SpreadLogro[indice].value=getEntero(obj.LRL0);
						_SpreadLogro[h].value=getEntero(obj.LRL1);

						_SuperSpread[indice].value=getEntero(obj.FSRL0);
						_SuperSpread[h].value=getEntero(obj.FSRL1);
						_SuperSpreadDecimal[indice].value=getDecimal(obj.FSRL0);
						_SuperSpreadDecimal[h].value=getDecimal(obj.FSRL1);
						_SuperSpreadLogro[indice].value=getEntero(obj.LSRL0);
						_SuperSpreadLogro[h].value=getEntero(obj.LSRL1);
	
						_Total[indice].value=getEntero(obj.FAB0);
						_Total[h].value=getEntero(obj.FAB1);
						_TotalDecimal[indice].value=getDecimal(obj.FAB0);
						_TotalDecimal[h].value=getDecimal(obj.FAB1);
						_TotalLogro[indice].value=getEntero(obj.LAB0);
						_TotalLogro[h].value=getEntero(obj.LAB1);
					//}
					}
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		
	}
}

function igualarTotal(ind) {
	with(document.forms[0]) {
		_Total[(ind==0?1:0)].value = _Total[ind].value;
		_TotalDecimal[(ind==0?1:0)].value = _TotalDecimal[ind].value;
	}
}

function igualarRunLine(ind) {
	with(document.forms[0]) {
		if(!isNaN(parseInt(_Spread[ind].value))) {
			_Spread[(ind==0?1:0)].value = parseInt(_Spread[ind].value)*(-1);
			_SpreadDecimal[(ind==0?1:0)].value = _SpreadDecimal[ind].value;
		}
	}
}

function igualarSuperRunLine(ind) {
	with(document.forms[0]) {
		if(!isNaN(parseInt(_Spread[ind].value))) {
			_SuperSpread[(ind==0?1:0)].value = parseInt(_SuperSpread[ind].value)*(-1);
			_SuperSpreadDecimal[(ind==0?1:0)].value = _SuperSpreadDecimal[ind].value;
		}
	}
}

	function buscar(valor,evento,obj) {
		for(var i = 0; i < document.forms[0].nombre.length; i++) {
			if(obj == document.forms[0].nombre[i]) {
				actual = i;
			}
		}
		combo = "cbo";
		obj = document.getElementById(combo);
		obj.appendChild(document.getElementById("lista")); 
		if ( evento.keyCode == 13 ) {
			ocultar();
		} else if ( evento.keyCode == 40 ) {
			try {
			if ( document.getElementById("lista").style.display == "" ) {
				var obj = document.getElementById("lista");
				obj.selectedIndex = 0;
				obj.focus();
			} 
			} catch(e) {}
		} else if ( trim(valor)!="" ) {  // && valor.length > 2
			accion = "buscar";
			opcionAjax = 10;
			http = new Ajax("<%=basePath%>buscarAjax.do?table=4&nombre="+valor);
			http.setMetodo("POST");
			http.start();
		}
	}
	
	function guardarCampeonato() {
		accion="guardar";
		with(document.forms[0]) {
			http = new Ajax("<%=basePath%>agregarAjax.do?table=4&id_campeonato="+idCampeonato.value+"&nombre_campeonato="+nombre.value);
		}
		http.setMetodo("POST");
		http.start();
	}
	
	
	function addCombo(field,event) {
		if(trim(field.value)=="" || document.getElementById("lista").style.display=="" ) return;
		// buscamos en el combo
		var sel = document.getElementById("lista");
		document.forms[0].idCampeonato.value = "0";
		for(var i=0; i<sel.length ;i++) {
			var item = sel.options[i].text;
			//alert(item.toLowerCase()+" == "+field.value.toLowerCase());
			if(item.toLowerCase()==field.value.toLowerCase()) {
				document.forms[0].idCampeonato.value = sel.options[i].value;
				field.value=sel.options[i].text;
				return;
			}
		}

		with(document.forms[0]) {
			if(idCampeonato.value=="0") {
				if(confirm("El campeonato no esta registrado\n\n¿Desea registrarlo en el sistema?")) {
					guardarCampeonato();
				} else {
					document.forms[0].idCampeonato.value = "0";
					field.value="";
				}
			}
		}
	}
	
	function ocultarCombo() {
		document.getElementById("lista").style.display = "none";
	}
	
	function esconder() {
		//setTimeout("ocultar()","300");
	}


	function agregarClick(evento,obj) {
		document.forms[0].idCampeonato.value = obj.options[obj.selectedIndex].value;
		document.forms[0].nombre.value = obj.options[obj.selectedIndex].text;
		ocultar();
	}

	function seleccionado(obj) {
		focoSelect = 1;
		if ( obj.selectedIndex > 0 ) {
			document.forms[0].idCampeonato.value = obj.options[obj.selectedIndex].value;
			document.forms[0].nombre.value = obj.options[obj.selectedIndex].text;
		}
	}
	
	function agregar(evento,obj) {
		try {
			document.forms[0].idCampeonato.value = obj.options[obj.selectedIndex].value;
			document.forms[0].nombre.value = obj.options[obj.selectedIndex].text;
			if ( evento.keyCode == 13 ) {
				ocultar();
			}
		} catch(e) { }
	}
	
	function ocultar() {
		document.getElementById("lista").style.display = "none";
		try {
			document.forms[0].nombre.focus();
		} catch (e) {
		}
	}
	
	String.prototype.trim = function() { return this.replace(/^\s*|\s*$/g,""); };
	String.prototype.endsWith = function(str){return (this.match(str+"$")==str)};
	String.prototype.startsWith = function(str){return (this.match("^"+str)==str)};
	
	function analizar() {
		var text = document.getElementById("dataJuegos");
		var cad = text.value.trim();
		cad = cad.replace(/\;/g,'');
		//cad = cad.replace(/ : /g,'');
		cad = cad.replace(/,/g,'');
		cad = cad.replace(/\'/g,'');
		cad = cad.replace(/\´/g,'');
		var lar=cad.length;
		var nueva = "";
		for(var i=0; i<lar; i++) {
			if(cad.charCodeAt(i)!=10 && cad.charCodeAt(i)!=13) {
				nueva=nueva+cad.charAt(i);
			} else {
				nueva=nueva+' ';
			}
		}
		while(nueva.indexOf('  ')!=-1) { // dos espacios
			nueva = nueva.replace(/  /g,' '); // dos espacios por uno
		}
		cad = nueva;
		
		if(cad.length>10){
			var mes = '';
			var entro=false;
			for(var i=0; i<meses.length; i++) {
				//var mes = cad.substring(0,4);
				mes = meses[i];
				if(cad.indexOf(mes)==-1){
					mes = mesesUS[i];
				}
				cad = cad.replace(new RegExp( '\n'+mes, "g" ),mes);
				cad = cad.replace(new RegExp( mes, "g" ),'\n'+mes);
				entro=true;
			}
			cad = cad.substring(1);
			text.value=cad;
		}
	}
	
	var linea="";	
	function omitirPrimero() {
		var text = document.getElementById("dataJuegos");
		var cad = text.value.trim();
		var lista=cad.split('\n');
		linea = lista[0].trim();

		lista.splice(0,1); // eliminamos el primer elemento
		
		document.getElementById("dataJuegos").value=lista.join('\n');
		cargarPrimero();
	}
	function cargarPrimero() {
		if(document.registerGame.idJuego.value || document.registerGame.idJuego.value.trim()!='') {
			return; // estamos solo modificando
		}
		// inicializamos
		var ref0 = '';
		var equ0 = '';
		var rlf0 = '0';
		var rlfn0 = '0';
		var rlfd0 = '0';
		var rll0 = '0';
		var alta = '0';
		var altaEntero = '0';
		var altaDecimal = '0';
		var altaLogro = '0';
		var ml0 = '0';

		var ref1 = '';
		var equ1 = '';
		var rlf1 = '0';
		var rlfn1 = '0';
		var rlfd1 = '0';
		var rll1 = '0';
		var baja = '0';
		var bajaEntero = '0';
		var bajaDecimal = '0';
		var bajaLogro = '0';
		var ml1 = '0';

		var ref2 = '';
		var equ2 = '';
		var ml2 = '0';

		// analizamos
		var text = document.getElementById("dataJuegos");
		var cad = text.value.trim();
		var lista=cad.split('\n');
		linea = lista[0].trim();
		if(linea.indexOf(' EV ')!=-1){
			linea = linea.replace(/ EV /g,' +100 ');
		}
		if(linea.endsWith(' EV')){
			linea = linea.substring(0,linea.length-3)+' +100';
		}
		
		//analizamos los datos
		var mes = getMes(cortarLinea(3,4));
		var dia = zero(cortarLinea(2));
		var hh = zero(cortarHora());
		var mm = zero(cortarMinuto());
		var AM = cortarLinea(2,3).toLowerCase();
		
		var proxRef = "";
		
		if(linea.indexOf('+')!=-1 || linea.indexOf('-')!=-1){
			ref0 = cortarLineaRef();
			proxRef = parseInt(ref0)+1;

			var aux = 'EV'+proxRef;
			linea = linea.replace(aux,'+100'+proxRef);

			equ0 = cortarLineaEquipo();
			lan0 = cortarLanzador();
			
			var arr = linea.split(' ');
			var isOnlyMoneyLine = false;

			if(typeof arr[1]!='undefined') {
				if(arr[0].replace(/[^\+\-]/g,'').length<2) {
					isOnlyMoneyLine = arr[1]==proxRef; // evaluara solo el money line
				}
			}

			if(!isOnlyMoneyLine) {
				if(arr[0].endsWith(proxRef) && !arr[0].endsWith('-'+proxRef) && !arr[0].endsWith('+'+proxRef)) {
					isOnlyMoneyLine=true;
				}
			}

			if(!isOnlyMoneyLine) {
				if(linea.charAt(0)!='o' && linea.charAt(0)!='u'){
					rlf0 = cortarLineaRunLine();
					rlfn0 = getEntero(rlf0);
					rlfd0 = getDecimal(rlf0);
					rll0 = cortarLineaLogro();
				}
				if(linea.indexOf('o')!=-1) {
					alta = cortarLineaNumero().replace('o','');
					altaEntero = getEntero(alta);
					altaDecimal = getDecimal(alta);
					altaLogro = cortarLineaLogro();
				}
			}
			ml0 = cortarLineaMoneyLine(ref0);
	
			ref1 = cortarLineaRef();
			proxRef = parseInt(ref1)+1;

			equ1 = cortarLineaEquipo();
			lan1 = cortarLanzador();
			
			if(!isOnlyMoneyLine) {			
				if(linea.charAt(0)!='o' && linea.charAt(0)!='u'){
					rlf1 = cortarLineaRunLine();
					rlfn1 = getEntero(rlf1);
					rlfd1 = getDecimal(rlf1);
					rll1 = cortarLineaLogro();
				}
				if(linea.indexOf('u')!=-1) {
					baja = cortarLineaNumero().replace('u','');
					bajaEntero = getEntero(baja);
					bajaDecimal = getDecimal(baja);
					bajaLogro = cortarLineaLogro();
				}
			}
			//ml1 = cortarLineaMoneyLine(linea.toLowerCase().indexOf('draw')!=-1?parseInt(ref0)+1:'');
			ml1 = cortarLineaMoneyLine(""+(parseInt(ref0)+1));
			
			ref2 = cortarLineaRef();
			equ2 = cortarLineaEquipo();
			ml2 = cortarLineaMoneyLine(""+(parseInt(ref0)+2));
		} else {
			ref0 = cortarLineaRef();
			equ0 = cortarLineaEquipo();

			ref1 = cortarLineaRef();
			equ1 = cortarLineaEquipo();

			ref2 = cortarLineaRef();
			equ2 = cortarLineaEquipo();
		}
		
		if(false) {
			prueba('','clear');
			prueba('mes',mes);
			prueba('dia',dia);
			prueba('hh',hh);
			prueba('mm',mm);
			prueba('AM',AM);

			prueba('<br>ref0',ref0);
			prueba('equ0',equ0);
			prueba('lan0',lan0);
			prueba('rlf0',rlf0);
			prueba('rlfn0',rlfn0);
			prueba('rlfd0',rlfd0);
			prueba('rll0',rll0);
			prueba('alta',alta);
			prueba('altaEntero',altaEntero);
			prueba('altaDecimal',altaDecimal);
			prueba('altaLogro',altaLogro);
			prueba('ml0',ml0);
	
			prueba('<br>ref1',ref1);
			prueba('equ1',equ1);
			prueba('lan1',lan1);
			prueba('rlf1',rlf1);
			prueba('rlfn1',rlfn1);
			prueba('rlfd1',rlfd1);
			prueba('rll1',rll1);
			prueba('baja',baja);
			prueba('bajaEntero',bajaEntero);
			prueba('bajaDecimal',bajaDecimal);
			prueba('bajaLogro',bajaLogro);
			prueba('ml1',ml1);

			prueba('<br>ref2',ref2);
			prueba('equ2',equ2);
			prueba('ml2',ml2);
		}
		
		// agregamos el juego
		with(document.registerGame){
			var fecha = new Date();
			fechaIni.value=dia+'/'+mes+'/'+fecha.getYear();

			prueba('HORA',zero(AM=='am'?hh:(toInt(hh)+12)));
			var horaNueva = zero(AM=='am'? ( hh==12 ? (toInt(hh)+12) : hh ) :( hh=='12' ? hh : (toInt(hh)+12) ));
			horaNueva=(horaNueva=='24'?'00':horaNueva);
			
			hora.value = horaNueva;
			minuto.value=mm;
			
			// validamos la fecha nuevamente si hay que sumarle horas y/o minutos
			if(diferencia.value!='') {
				var newHour = new Date(fecha.getFullYear(),toInt(mes)-1,toInt(dia),toInt(hora.value),toInt(minuto.value));
				var cad = diferencia.value;
				var factor = (cad.charAt(0)=='-'?-1:1);
				
				if(cad.indexOf(":")==-1) {
					cad = cad+':0';
				}

				cad = cad.split(':');
				
				var horaDif = toInt(cad[0]);
				var minDif = toInt(cad[1])*factor;
				
				newHour.setHours(newHour.getHours()+horaDif);
				newHour.setMinutes(newHour.getMinutes()+minDif);

				fechaIni.value=zero(newHour.getDate())+'/'+zero(newHour.getMonth()+1)+'/'+newHour.getFullYear();
				
				hora.value = zero(newHour.getHours());
				minuto.value= zero(newHour.getMinutes());
			}
			
			_Referencia[0].value=ref0;
			_Referencia[1].value=ref1;
			_Referencia[2].value=ref2;
			
			
			// los equipos
			if(_IdEquipo[0].options.length>0){
				var x=_IdEquipo[0].options.length;
				_IdEquipo[0].selectedIndex=0;
				_IdEquipo[1].selectedIndex=0;

				// buscamos un equipo similar para el primer equipo
				for(var i=0; i<x; i++) {
					if(_IdEquipo[0].options[i].text.toLowerCase().replace(/\'/g,'').replace(/\´/g,'').replace(/\+/g,'').replace(/\-/g,'').replace(/ /g,'')==equ0){
						_IdEquipo[0].selectedIndex=i;
						break;
					}
				}
				if(_IdEquipo[0].selectedIndex==0){
					for(var i=0; i<x; i++) {
						if(_IdEquipo[0].options[i].text.toLowerCase().replace(/\'/g,'').replace(/\´/g,'').replace(/\+/g,'').replace(/\-/g,'').replace(/ /g,'').indexOf(equ0)!=-1){
							_IdEquipo[0].selectedIndex=i;
							break;
						}
					}
				}
				
				// buscamos un equipo similar para el segundo equipo
				for(var i=0; i<x; i++) {
					if(_IdEquipo[1].options[i].text.toLowerCase().replace(/\'/g,'').replace(/\´/g,'').replace(/\+/g,'').replace(/\-/g,'').replace(/ /g,'')==equ1){
						_IdEquipo[1].selectedIndex=i;
						break;
					}
				}
				if(_IdEquipo[1].selectedIndex==0){
					for(var i=0; i<x; i++) {
						if(_IdEquipo[1].options[i].text.toLowerCase().replace(/\'/g,'').replace(/\´/g,'').replace(/\+/g,'').replace(/\-/g,'').replace(/ /g,'').indexOf(equ1)!=-1){
							_IdEquipo[1].selectedIndex=i;
							break;
						}
					}
				}
				
				llenar(3,0);
				
				if(equ2=='empate'){
					_IdEquipo[2].selectedIndex=0;
				}
			}
			if(_IdEquipo.length>2 && _IdEquipo[2].options.length>0){
				if(equ2.toLowerCase()=='draw') {
					_IdEquipo[2].selectedIndex=0;
				}
			}
			
			_MoneyLine[0].value=ml0;
			_MoneyLine[1].value=ml1;
			_MoneyLine[2].value=ml2;
			
			_Spread[0].value=rlfn0;
			_SpreadDecimal[0].value=rlfd0;
			_Spread[1].value=rlfn1;
			_SpreadDecimal[1].value=rlfd1;
			
			_SpreadLogro[0].value=rll0;			
			_SpreadLogro[1].value=rll1;			
			
			_Total[0].value=altaEntero;
			_TotalDecimal[0].value=altaDecimal;
			_Total[1].value=bajaEntero;
			_TotalDecimal[1].value=bajaDecimal;
			_TotalLogro[0].value=altaLogro;
			_TotalLogro[1].value=bajaLogro;
			
			//alert(1);
			if(!cambiarRunLine(0,true)) {
				cambiarRunLine(1,true);
			}
		}
		
		<%=request.getAttribute("automatico")!=null?"setTimeout('send()',500);":""%>
	}	
	function prueba(tipo,valor){
		if(valor=='clear'){
			document.getElementById("prueba").style.display='';
			document.getElementById("prueba").innerHTML='';
		} else {
			document.getElementById("prueba").innerHTML=document.getElementById("prueba").innerHTML+' |'+tipo+'='+valor;
		}
	}
	function toInt(valor){
		if(valor){
			while(valor.length>1 && valor.charAt(0)=='0'){
				valor=valor.substring(1);
			}
			return parseInt(valor);
		} else {
			return 0;
		}
	}
	function getEntero(valor) {
		if(valor.indexOf('.')!=-1) {
			valor = valor.substring(0,valor.indexOf('.'));
		}
		return valor.replace(/[^0-9\-\+]/g,'');
	}
	function getDecimal(valor) {
		if(valor.indexOf('.')!=-1) {
			valor = valor.substring(valor.indexOf('.')+1);
			return valor;
		} else {
			valor = valor.replace(/[^½]/g,'');
			return (valor=='½'?5:0);
		}
	}
	function zero(valor){
		valor = ''+valor;
		valor = valor.trim();
		return valor.length==1?'0'+valor:valor;
	}
	function getMes(nombreMes) {
		nombreMes = nombreMes.toLowerCase();
		for(var i=0; i<mes0.length; i++) {
			if(mes0[i]==nombreMes || mes1[i]==nombreMes) {
				return i<10?'0'+(i+1):(i+1);
			}
		}
	}
	function cortarLinea(largo,corte) {
		corte = !corte ? largo : corte;
		var retorno = linea.trim().substring(0,largo);		
		linea = linea.trim().substring(corte);		
		return retorno;
	}
	function cortarHora() {
		var hora = linea.substring(0,linea.indexOf(":"));
		linea = linea.substring(hora.length);
		if(linea.charAt(0)==":") {
			linea = linea.substring(1);		
		}
		return hora;
	}
	function cortarMinuto() {
		var hora = linea.substring(0,2);
		linea = linea.substring(hora.length);
		return hora;
	}
	function cortarLineaRef(proxReferencia) {
		var ref = "";
		for(var i=0; i<linea.length; i++){
			if(/[0-9]/.test(linea.charAt(i))) { 
				ref = ref + linea.charAt(i); 
			} else {
				break;
			}
		}
		linea = linea.trim().substring(ref.length).trim();
		return ref.trim();
	}
	function cortarLineaEquipo() {
		var equ = linea.charAt(0);

		for(var i=1; i<linea.length; i++){
			if( /[A-Zempate0-9 \.\(\)]/.test(linea.charAt(i)) || /[A-Z]/.test(linea.charAt(i+1)) ) { 
				equ = equ + linea.charAt(i); 
			} else {
				break;
			}
		}
		equ=equ.trim();
		//alert('*'+equ+'*');
		if(equ.toUpperCase().endsWith('PKEV')){
			equ=equ.substring(0,equ.length-4);
		} else if(equ.toUpperCase().endsWith('EVPK')){
			equ=equ.substring(0,equ.length-4);
		} else if(equ.toUpperCase().endsWith('PKPK')){
			equ=equ.substring(0,equ.length-4);
		} else if(equ.toUpperCase().endsWith('PK')){
			equ=equ.substring(0,equ.length-2);
		} 
		//alert('*'+equ+'*');
		linea = linea.trim().substring(equ.length).trim();
		//alert(linea);
		return equ.toLowerCase().trim().replace(/\+/g,'').replace(/\-/g,'').replace(/ /g,'');
	}
	
	function cortarLanzador() {
		if(linea.charAt(0)==':') {
			var lan = linea.charAt(0);
			for(var i=1; i<linea.length; i++){
				if( /[A-Zempate0-9 \.\(\)]/.test(linea.charAt(i)) || /[A-Z]/.test(linea.charAt(i+1)) ) { 
					lan = lan + linea.charAt(i); 
				} else {
					break;
				}
			}
			lan=lan.trim();
			linea = linea.trim().substring(lan.length).trim();
		
			if(lan.indexOf('-')!=-1) {
				lan = lan.substring(0,lan.indexOf('-'));
			}
			
			lan = lan.toLowerCase().trim().replace(/:/g,'').replace(/\+/g,'').replace(/\-/g,'').replace(/ /g,'');

			//alert('*'+lan.trim()+'*');
			return lan.trim();
		} else {
			return '';
		}
	}
	
	function cortarLineaRunLine() {
		if(linea.startsWith('PK')) {
			linea = linea.substring(2).trim();
			return '0';
		}
		if(linea.startsWith('EV')) {
			linea = linea.substring(2).trim();
			return '0';
		}
		var log = linea.charAt(0);
		for(var i=1; i<linea.length; i++){
			if('0123456789½'.indexOf(linea.charAt(i))!=-1) {
				log = log + linea.charAt(i); 
			} else {
				break;
			}
		}
		linea = linea.substring(log.length).trim();
		return log;
	}
	function cortarLineaNumero() {
		if(linea.startsWith('PK')) {
			linea = linea.substring(2).trim();
			return '0';
		}
		var log = linea.charAt(0);
		for(var i=1; i<linea.length; i++){
			//if(linea.charAt(i)!='+' && linea.charAt(i)!='-' && linea.charAt(i)!=' ' && linea.charAt(i)!='o' && linea.charAt(i)!='u') {
			if('0123456789½'.indexOf(linea.charAt(i))!=-1) {
				log = log + linea.charAt(i); 
			} else {
				break;
			}
		}
		linea = linea.substring(log.length).trim();		
		return log;
	}
	function cortarLineaLogro() {
		if(linea.startsWith('EV')) {
			linea = linea.substring(2).trim();
			return '100';
		}
		if(linea.startsWith('PK')) {
			linea = linea.substring(2).trim();
			return '-110';
		}
		var log = linea.charAt(0);
		for(var i=1; i<linea.length; i++){
			if(linea.charAt(i)!='+' && linea.charAt(i)!='-' && linea.charAt(i)!=' ' && linea.charAt(i)!='o' && linea.charAt(i)!='u') {
				log = log + linea.charAt(i); 
			} else {
				break;
			}
		}
		linea = linea.substring(log.length).trim();		
		return log.replace(/\+/,'');
	}
	function cortarLineaMoneyLine(ref) {
		if(linea.startsWith('EV')) {
			linea = linea.substring(2).trim();
			return '100';
		}
		var log = linea.charAt(0);
		for(var i=1; i<linea.length; i++){
			if('0123456789'.indexOf(linea.charAt(i))==-1) {
				break;
			}
			log = log + linea.charAt(i); 
		}
		if(ref.length>0){
			ref=""+(parseInt(ref)+1);
			if(log.endsWith(ref)){
				log = log.substring(0,(log.length-ref.length));
			}
		}
		linea = linea.trim().substring(log.length).trim();
		return log==''?'0':log.replace(/\+/,'');
	}
	
	function cargarLanzador(num,valor) {
		// buscamos un lanzador para el equipo 0
		with(document.forms[0]) {
			var xLan0=_IdLanzador[num].options.length;
			var valorOption = "";
			for(var i=0; i<xLan0; i++) {
				valorOption = _IdLanzador[num].options[i].text.toLowerCase();
				if(valorOption.indexOf("(")!=-1) {
					valorOption = valorOption.substring(0, valorOption.indexOf("(") ).trim();
				}
				valorOption = valorOption.replace(/\./g,'').replace(/\'/g,'').replace(/\´/g,'').replace(/\+/g,'').replace(/\-/g,'').replace(/ /g,'');
				if(valorOption==valor){
					_IdLanzador[num].selectedIndex=i;
					break;
				}
				
			}
		}
	}
	

</script>
<html:form action="/registerGameSave" method="POST" >
<input type="hidden" name="dominio" value="Constants.getDominio(request)" />
<html:hidden name="juego" property="idJuego"/>
<html:hidden name="juego" property="idStatusJuego"/>
<html:hidden name="juego" property="idCampeonato"/>
<input type="hidden" name="eliminar" value="false"/>
<input type="hidden" name="abrir" value="false"/>
<select name="lista" 
	id="lista" 
	size="7" style="display:none; width:430px;position:absolute" 
	onClick="agregarClick(event,this)" 
	onFocus="seleccionado(this)" 
	onblur="this.style.display = 'none'" 
	onkeyup="agregar(event,this)"></select>
<table align="center" width="98%" height="100%" cellpadding="3" cellspacing="3">
  <tr>
	<td width="100%" align="left" colspan="3">
	<font color="white">
		<html:errors/>
	</font>
	</td>
  </tr>
  <tr>
  	<td align="center" width="100%" valign="top">
		<table width="100%" height="100%" border="0" cellpadding="5" cellspacing="5" class="tablaCaption">
			<caption class="tituloCaption">
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
					<tr>
						<td class="etiquetaCenter">DATOS DEL JUEGO&nbsp;&nbsp;<font color="#efefef">(<bean:write name="juego" property="descStatusJuego" />)</font></td>
					</tr>
				</table>
			</caption>
			<tr>
			  	<td class="etiquetaLeft" rowspan="4">
			  		<bean:message key="juego.idDeporte" />:<br/>
			  		<html:select styleId="idDeporte" name="juego" property="idDeporte" onchange="llenar(1)" size="7" style="width:300px" >
			  			<html:optionsCollection name="listaDeporte" value="idDeporte" label="descDeporte"/>
			  		</html:select>
			  	</td>
			  	<td class="etiquetaLeft" rowspan="4">
				  	<bean:message key="liga.idLiga" />:<br/>
			  		<html:select styleId="idLiga" name="juego" property="idLiga" style="width:300px" onchange="llenar(2)" size="7">
			  			<logic:present name="listaLiga">
			  			<html:optionsCollection name="listaLiga" value="idLiga" label="descLiga" />
			  			</logic:present>
			  		</html:select>
			  	</td>
			  	<td class="etiquetaLeft" width="40%">
			  		<table>
			  			<tr>
			  				<td>
						  		<bean:message key="juego.fechaIni" />:
								<table height="100%" border="0" cellpadding="0" cellspacing="0">
								  <tr>
									<td>
						  			<html:text  name="juego" property="fechaIni" 
											maxlength="10" size="12" 
											onblur="DateFormat(this,this.value,event,true,'3')"  
											onkeyup="DateFormat(this,this.value,event,false,'3')" onfocus="javascript:vDateType='3'" />
									</td>
									<td>	
									        <a href="javascript:show_calendar('forms[0].fechaIni', 'forms[0].fechaIni', 'forms[0].fechaIni');" 
											onmouseover="window.status='Seleccione la fecha';return true;" 
											onmouseout="window.status='';return true;">
								            <img src="<%=basePath%>images/calendario.gif" border="0" title="Seleccionar Fecha"></a>
								    </td>
								  </tr>
								</table>					
							</td>
							<td>&nbsp;&nbsp;&nbsp;
							</td>
							<td>
						  		<bean:message key="juego.minutosCierre" />:<br/>
						  		<html:select name="juego" property="minutosCierre" >
						  			<html:option value="5">5</html:option>
						  			<html:option value="10">10</html:option>
						  			<html:option value="15">15</html:option>
						  			<html:option value="20">20</html:option>
						  			<html:option value="25">25</html:option>
						  			<html:option value="30">30</html:option>
						  			<html:option value="35">35</html:option>
						  			<html:option value="40">40</html:option>
						  			<html:option value="45">45</html:option>
						  			<html:option value="50">50</html:option>
						  			<html:option value="55">55</html:option>
						  			<html:option value="60">60</html:option>
						  		</html:select>
							</td>
						</tr>
					</table>
			  	</td>
		  	</tr>
		  	<tr>
			  	<td class="etiquetaLeft" >
			  		<bean:message key="juego.horaJuego" />:<br/>
			  		<html:select name="juego" property="hora">
			  			<html:option value="00">00</html:option>
			  			<html:option value="01">01</html:option>
			  			<html:option value="02">02</html:option>
			  			<html:option value="03">03</html:option>
			  			<html:option value="04">04</html:option>
			  			<html:option value="05">05</html:option>
			  			<html:option value="06">06</html:option>
			  			<html:option value="07">07</html:option>
			  			<html:option value="08">08</html:option>
			  			<html:option value="09">09</html:option>
			  			<html:option value="10">10</html:option>
			  			<html:option value="11">11</html:option>
			  			<html:option value="12">12</html:option>
			  			<html:option value="13">13</html:option>
			  			<html:option value="14">14</html:option>
			  			<html:option value="15">15</html:option>
			  			<html:option value="16">16</html:option>
			  			<html:option value="17">17</html:option>
			  			<html:option value="18">18</html:option>
			  			<html:option value="19">19</html:option>
			  			<html:option value="20">20</html:option>
			  			<html:option value="21">21</html:option>
			  			<html:option value="22">22</html:option>
			  			<html:option value="23">23</html:option>
			  		</html:select>
			  		:
			  		<html:select name="juego" property="minuto">
			  			<html:option value="00">00</html:option>
			  			<html:option value="01">01</html:option>
			  			<html:option value="02">02</html:option>
			  			<html:option value="03">03</html:option>
			  			<html:option value="04">04</html:option>
			  			<html:option value="05">05</html:option>
			  			<html:option value="06">06</html:option>
			  			<html:option value="07">07</html:option>
			  			<html:option value="08">08</html:option>
			  			<html:option value="09">09</html:option>
			  			<html:option value="10">10</html:option>
			  			<html:option value="11">11</html:option>
			  			<html:option value="12">12</html:option>
			  			<html:option value="13">13</html:option>
			  			<html:option value="14">14</html:option>
			  			<html:option value="15">15</html:option>
			  			<html:option value="16">16</html:option>
			  			<html:option value="17">17</html:option>
			  			<html:option value="18">18</html:option>
			  			<html:option value="19">19</html:option>
			  			<html:option value="20">20</html:option>
			  			<html:option value="21">21</html:option>
			  			<html:option value="22">22</html:option>
			  			<html:option value="23">23</html:option>
			  			<html:option value="24">24</html:option>
			  			<html:option value="25">25</html:option>
			  			<html:option value="26">26</html:option>
			  			<html:option value="27">27</html:option>
			  			<html:option value="28">28</html:option>
			  			<html:option value="29">29</html:option>
			  			<html:option value="30">30</html:option>
			  			<html:option value="31">31</html:option>
			  			<html:option value="32">32</html:option>
			  			<html:option value="33">33</html:option>
			  			<html:option value="34">34</html:option>
			  			<html:option value="35">35</html:option>
			  			<html:option value="36">36</html:option>
			  			<html:option value="37">37</html:option>
			  			<html:option value="38">38</html:option>
			  			<html:option value="39">39</html:option>
			  			<html:option value="40">40</html:option>
			  			<html:option value="41">41</html:option>
			  			<html:option value="42">42</html:option>
			  			<html:option value="43">43</html:option>
			  			<html:option value="44">44</html:option>
			  			<html:option value="45">45</html:option>
			  			<html:option value="46">46</html:option>
			  			<html:option value="47">47</html:option>
			  			<html:option value="48">48</html:option>
			  			<html:option value="49">49</html:option>
			  			<html:option value="50">50</html:option>
			  			<html:option value="51">51</html:option>
			  			<html:option value="52">52</html:option>
			  			<html:option value="53">53</html:option>
			  			<html:option value="54">54</html:option>
			  			<html:option value="55">55</html:option>
			  			<html:option value="56">56</html:option>
			  			<html:option value="57">57</html:option>
			  			<html:option value="58">58</html:option>
			  			<html:option value="59">59</html:option>
			  		</html:select>
			  	</td>
		  	</tr>
			<tr style="display:none;">
			  	<td class="etiquetaLeft" >
			  		<bean:message key="juego.idCampeonato" />:<br/>
					<html:text name="juego" property="nombre" style="width:95%" onkeyup="javascript:buscar(this.value,event,this);" onfocus="ocultarCombo()" onblur="addCombo(this,event)" />
					<div id="cbo"></div>				  	
			  	</td>
		  	</tr>
  		</table>
		<table width="100%" height="100%" border="0" cellpadding="5" cellspacing="5" class="tablaCaption">
			<tr>
			  	<td class="tituloTablaSmall tablaConBorde" style="width:5%" ><bean:message key="juegoEquipo.referencia" /></td>
			  	<td class="tituloTablaSmall tablaConBorde" style="width:25%" ><bean:message key="equipo.descEquipo" /></td>
			  	<td class="tituloTablaSmall tablaConBorde" style="width:20%" ><bean:message key="juegoEquipoLanzador.idLanzador" /></td>
			  	<td class="tituloTablaSmall tablaConBorde" ><bean:message key="usuarioJuegoEquipo.moneyLine" /></td>
			  	<td class="tituloTablaSmall tablaConBorde" colspan="2"><bean:message key="usuarioJuegoEquipo.spread" /></td>
			  	<td class="tituloTablaSmall tablaConBorde" colspan="2" id="titSRL">Super Runline</td>
			  	<td class="tituloTablaSmall tablaConBorde" colspan="2"><bean:message key="usuarioJuegoEquipo.total" /></td>
		  	</tr>
			<tr>
			  	<td class="detalleTablaCenter" ><html:text value="0" property="_Referencia" size="5" maxlength="5" onkeyup="numerar()"/></td>
			  	<td class="detalleTablaCenter" >
			  		<html:select value="0" styleId="idEquipo0" property="_IdEquipo" onchange="llenar(3,0)" style="width:95%">
			  			<logic:present name="listaEquipo">
			  			<html:optionsCollection name="listaEquipo" value="idEquipo" label="descEquipo" />
			  			</logic:present>
			  		</html:select>
			  	</td>
			  	
			  	<td class="detalleTablaCenter" >
			  		<html:select value="0" styleId="idLanzador0" property="_IdLanzador" style="width:95%">
			  			<html:option value="0">Seleccione un equipo...</html:option>
			  			<logic:present name="listaLanzador0">
			  			<html:optionsCollection name="listaLanzador0" value="idLanzador" label="nombreLanzador" />
			  			</logic:present>
				  	</html:select>
			  	</td>
			  	<td class="detalleTablaCenter" ><html:text value="0" property="_MoneyLine" size="4" maxlength="6" onkeyup="cambiarRunLine(0)"/></td>
			  	<td class="detalleTablaCenter" ><html:text value="0" property="_Spread" size="3" maxlength="6" onkeyup="igualarRunLine(0)" /><html:select property="_SpreadDecimal" onchange="igualarRunLine(0)"><html:option value="0">0</html:option><html:option value="5">&#189;</html:option></html:select></td>
			  	<td class="detalleTablaCenter" ><html:text value="0" property="_SpreadLogro" size="3" maxlength="6" /></td>
			  	<td class="detalleTablaCenter" id="colSRLA1"><html:text value="0" property="_SuperSpread" size="3" maxlength="6" onkeyup="igualarSuperRunLine(0)" /><html:select property="_SuperSpreadDecimal" onchange="igualarSuperRunLine(0)"><html:option value="0">0</html:option><html:option value="5">&#189;</html:option></html:select></td>
			  	<td class="detalleTablaCenter" id="colSRLA2"><html:text value="0" property="_SuperSpreadLogro" size="3" maxlength="6" /></td>
			  	<td class="detalleTablaCenter" ><html:text value="0" property="_Total" size="3" maxlength="6" onkeyup="igualarTotal(0)"/><html:select property="_TotalDecimal" onchange="igualarTotal(0)"><html:option value="0">0</html:option><html:option value="5">&#189;</html:option></html:select></td>
			  	<td class="detalleTablaCenter" ><html:text value="0" property="_TotalLogro" size="3" maxlength="6" /></td>
		  	</tr>
			<tr>
			  	<td class="detalleTablaCenter" ><html:text value="0" property="_Referencia" size="5" maxlength="5" readonly="true"/></td>
			  	<td class="detalleTablaCenter" >
			  		<html:select value="" styleId="idEquipo1" property="_IdEquipo" onchange="llenar(3,1)" style="width:95%">
			  			<html:option value="0">Seleccione una liga...</html:option>
			  			<logic:present name="listaEquipo">
			  			<html:optionsCollection name="listaEquipo" value="idEquipo" label="descEquipo" />
			  			</logic:present>
			  		</html:select>
			  	</td>
			  	<td class="detalleTablaCenter" >
			  		<html:select value="0" styleId="idLanzador1" property="_IdLanzador" style="width:95%">
			  			<html:option value="0">Seleccione un equipo...</html:option>
			  			<logic:present name="listaLanzador1">
			  			<html:optionsCollection name="listaLanzador1" value="idLanzador" label="nombreLanzador" />
			  			</logic:present>
				  	</html:select>
			  	</td>
			  	<td class="detalleTablaCenter" ><html:text value="0" property="_MoneyLine" size="4" maxlength="6" onkeyup="cambiarRunLine(1)"/></td>
			  	<td class="detalleTablaCenter" ><html:text value="0" property="_Spread" size="3" maxlength="6" onkeyup="igualarRunLine(1)"/><html:select property="_SpreadDecimal" onchange="igualarRunLine(1)"><html:option value="0">0</html:option><html:option value="5">&#189;</html:option></html:select></td>
			  	<td class="detalleTablaCenter" ><html:text value="0" property="_SpreadLogro" size="3" maxlength="6" /></td>
			  	<td class="detalleTablaCenter" id="colSRLB1"><html:text value="0" property="_SuperSpread" size="3" maxlength="6" onkeyup="igualarSuperRunLine(1)"/><html:select property="_SuperSpreadDecimal" onchange="igualarSuperRunLine(1)"><html:option value="0">0</html:option><html:option value="5">&#189;</html:option></html:select></td>
			  	<td class="detalleTablaCenter" id="colSRLB2"><html:text value="0" property="_SuperSpreadLogro" size="3" maxlength="6" /></td>
			  	<td class="detalleTablaCenter" ><html:text value="0" property="_Total" size="3" maxlength="6" onkeyup="igualarTotal(1)"/><html:select property="_TotalDecimal" onchange="igualarTotal(1)"><html:option value="0">0</html:option><html:option value="5">&#189;</html:option></html:select></td>
			  	<td class="detalleTablaCenter" ><html:text value="0" property="_TotalLogro" size="3" maxlength="6" /></td>
		  	</tr>
			<tr id="rowDraw">
			  	<td class="detalleTablaCenter" ><html:text value="0" property="_Referencia" size="5" maxlength="5" readonly="true"/></td>
			  	<td class="detalleTablaCenter" >
			  		<html:select value="0" styleId="idEquipo2" property="_IdEquipo" onchange="llenar(3,2)" style="width:95%">
			  			<logic:present name="equipoEmpate">
			  			<html:optionsCollection name="equipoEmpate" value="idEquipo" label="descEquipo" />
			  			</logic:present>
			  		</html:select>
			  	</td>
			  	<td class="detalleTablaCenter" >
			  		<html:select value="0" styleId="idLanzador2" property="_IdLanzador"  style="display:none;width:95%">
			  			<html:option value="0">Seleccione un equipo...</html:option>
			  			<logic:present name="listaLanzador2">
			  			<html:optionsCollection name="listaLanzador2" value="idLanzador" label="nombreLanzador" />
			  			</logic:present>
				  	</html:select>
			  	</td>
			  	<td class="detalleTablaCenter" ><html:text value="0" property="_MoneyLine" size="4" maxlength="6" /></td>
			  	<td class="detalleTablaCenter" ><html:text value="0" property="_Spread" size="3" maxlength="6" style="display:none"/><html:select property="_SpreadDecimal" style="display:none"><html:option value="0">0</html:option><html:option value="5">&#189;</html:option></html:select></td>
			  	<td class="detalleTablaCenter" ><html:text value="0" property="_SpreadLogro" size="3" maxlength="6" style="display:none"/></td>
			  	<td class="detalleTablaCenter" id="colSRLC1"><html:text value="0" property="_SuperSpread" size="3" maxlength="6" style="display:none"/><html:select property="_SuperSpreadDecimal" style="display:none"><html:option value="0">0</html:option><html:option value="5">&#189;</html:option></html:select></td>
			  	<td class="detalleTablaCenter" id="colSRLC2"><html:text value="0" property="_SuperSpreadLogro" size="3" maxlength="6" style="display:none"/></td>
			  	<td class="detalleTablaCenter" ><html:text value="0" property="_Total" size="3" maxlength="6" style="display:none"/><html:select property="_TotalDecimal" style="display:none"><html:option value="0">0</html:option><html:option value="5">&#189;</html:option></html:select></td>
			  	<td class="detalleTablaCenter" ><html:text value="0" property="_TotalLogro" size="3" maxlength="6" style="display:none"/></td>
		  	</tr>
  		</table>
  	</td>
  </tr>
</table>	

<center id="botones">
<logic:equal name="juego" property="idStatusJuego" value='<%= Constants.STATUS_JUEGO_BORRADOR %>' >
	<logic:equal name="juego" property="idUsuario" value="1">
		<a class="enlaceBoton" href="#" onClick="abrir()"><bean:message key="boton.abrir"/></a>
		&nbsp;&nbsp;&nbsp;
		<a class="enlaceBoton" href="#" onClick="eliminar()"><bean:message key="boton.eliminar"/></a>
		&nbsp;&nbsp;&nbsp;
	</logic:equal>
</logic:equal>
<logic:equal name="juego" property="idStatusJuego" value='<%= Constants.STATUS_JUEGO_ABIERTO %>' >
</logic:equal>
<logic:equal name="modificar" value="true">
	<a class="enlaceBoton" href="#" onClick="send()"><bean:message key="boton.modificar"/></a>
</logic:equal>
<logic:notEqual name="modificar" value="true">
	<a class="enlaceBoton" href="#" onClick="send()"><bean:message key="boton.agregar"/></a>
</logic:notEqual>
&nbsp;&nbsp;&nbsp;
<a class="enlaceBoton" href="<%=basePath%>listGame.do"	><bean:message key="boton.cancelar"/></a>
</center>
<center>
<br>
<div id="prueba" style="color:white;tex-align:left;display:none;"></div>
<div style="color:#ffffff;font-weight:bold;">
	<center>Carga automatica de juegos</center>
	<textarea id="dataJuegos" name="dataJuegos" style="width:96%;height:100px;" onchange="analizar()"><%=session.getAttribute("dataJuegos")!=null?session.getAttribute("dataJuegos"):""%></textarea>
	<span class="etiquetaLeft"><br/>Hora de diferencia : </span><input type="text" name="diferencia" value="<%=request.getAttribute("diferencia")!=null?request.getAttribute("diferencia"):request.getAttribute("DIFERENCIA_HORARIO")%>" onchange="cargarPrimero()" size="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a class="enlaceBoton1" href="javascript:cargarPrimero()" style="width:300px;">Cargar el primer juego</a>
	&nbsp;&nbsp;&nbsp;
	<a class="enlaceBoton1" href="javascript:omitirPrimero()" style="width:300px;">Omitir primer juego</a>
	&nbsp;&nbsp;&nbsp;
	<input name="automatico" type="checkbox" value="1" <%=request.getAttribute("automatico")!=null?"checked":""%>> <span class="etiquetaLeft">Carga Automatica</span>
</div>
</center>
</html:form>

<logic:present name="listaEquipo">
<script language="javascript">
function onLoad() {
	with(document.forms[0]) {
		<% for(int i=0; i<3; i++) {%>
			_Referencia[<%=i%>].value='<%=forma.get_Referencia()[i]%>';
			_IdEquipo[<%=i%>].value='<%=forma.get_IdEquipo()[i]%>';
			
			_IdLanzador[<%=i%>].value='<%=forma.get_IdLanzador()[i]%>';
			
			_Spread[<%=i%>].value='<%=forma.get_Spread()[i]%>';
			_SpreadDecimal[<%=i%>].value='<%=forma.get_SpreadDecimal()[i]%>';
			_SpreadLogro[<%=i%>].value='<%=forma.get_SpreadLogro()[i]%>';
			
			_SuperSpread[<%=i%>].value='<%=forma.get_SuperSpread()[i]%>';
			_SuperSpreadDecimal[<%=i%>].value='<%=forma.get_SuperSpreadDecimal()[i]%>';
			_SuperSpreadLogro[<%=i%>].value='<%=forma.get_SuperSpreadLogro()[i]%>';

			_Total[<%=i%>].value='<%=forma.get_Total()[i]%>';
			_TotalDecimal[<%=i%>].value='<%=forma.get_TotalDecimal()[i]%>';
			_TotalLogro[<%=i%>].value='<%=forma.get_TotalLogro()[i]%>';

			_MoneyLine[<%=i%>].value='<%=forma.get_MoneyLine()[i]%>';
		<%}%>
	}
	mostrarEmpate();
}
</logic:present>	
</script>
<logic:present name="forma" scope="session">
<script language="javascript">
	// ahora buscamos la ultima referencia
	accion="referencia";
	http = new Ajax("<%=basePath%>referenciaAjax.do?idDeporte="+document.forms[0].idDeporte.value);
	http.setMetodo("POST");
	http.start();
</script>
</logic:present>
<script language="javascript">
	var logros = <%=request.getAttribute("logros")%>;

	mostrarSRL();
	
	function findLogros(favorito) {
		if(favorito=='') {
			return null;
		}
		if(document.getElementById("rowDraw").style.display!='none') {
			return null;
		}
		
		for(var x=0; x<logros.length; x++) {
			//alert(logros[x].favorito+"=="+favorito+" && "+logros[x].idDeporte+"=="+document.forms[0].idDeporte.value);
			if(logros[x].favorito==favorito && logros[x].idDeporte==document.forms[0].idDeporte.value){
				return logros[x];
			}
		}
		return null;
	}

	<%=session.getAttribute("dataJuegos")!=null?"setTimeout('cargarPrimero()',500);":""%>

</script>

