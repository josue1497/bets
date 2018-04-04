<jsp:directive.page import="com.betcesc.game.common.Constants"/><%@ include file="/views/common/taglibs.jsp"%>
<%@ page import="com.betcesc.game.ctrl.back.RegisterGameActionForm" %>
<% RegisterGameActionForm forma = (RegisterGameActionForm) request.getAttribute("juego"); 
int cantidadEquipos = Constants.CANTIDAD_EQUIPOS;%>

<script language="JavaScript" src="<%=basePath%>scripts/Calendario_2.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/ajax.js"></script>
<script language="javascript">
var accion = "";
var opcion = "";
var opcionLanzador = "";
var listaDeporteEmpate = <%=request.getAttribute("listaDeporteEmpate")%>;
var isEmpate = "false";
var runline = 0;

function send(){
	with(document.forms[0]){
		if(isEmpty(idDeporte,"El Deporte",1)) { return false; }
		if(isEmpty(fechaIni,"La Fecha de Inicio de Juego")) { return false; }
		if(isEmpty(idLiga,"La Liga",1)) { return false; }
		if(isEmpty(nombre,"El Campeonato")) { return false; }

		if(isEmptyInt(_Referencia[0],"La Referencia Uno")) { return false; }
		if(isEmpty(_IdEquipo[0],"El Equipo Uno")) { return false; } 
		//if(idDeporte.value==<%=Constants.ID_EQUIPO_BEISBOL%>) {
		//	if(trim(_IdLanzador[0].value)==="") { alert("Por Favor ingrese el Lanzador del Equipo Uno"); _IdLanzador[0].focus(); return false; }
		//}
		if(isEmpty(_MoneyLine[0],"El MoneyLine Uno")) { return false; }

		if(isEmptyInt(_Referencia[1],"La Referencia Dos")) { return false; }
		if(_Referencia[0].value===_Referencia[1].value) { 
			alert("Las referencias no pueden ser iguales");	_Referencia[1].focus(); return false; 
		}
		if(isEmpate==="true") {
			if(_Referencia[0].value===_Referencia[2].value || _Referencia[1].value===_Referencia[2].value) { 
				alert("Las referencias no pueden ser iguales");	_Referencia[1].focus(); return false; 
			}
		}
		
		var equipos = "-";
		for(var k=0;k<_IdEquipo.length;k++) {
			if(_IdEquipo[k].value=='') {
				break;
			}
			if(equipos.indexOf("-"+_IdEquipo[k].value+"-")==-1) {
				equipos += _IdEquipo[k].value + "-";
			} else {
				alert(equipos);
				alert(_IdEquipo[k].value);
				alert("Los equipos no se pueden repetir. Por favor, revise los duplicados.");
				return false;
			}
		}
		
		//if(idDeporte.value==<%=Constants.ID_EQUIPO_BEISBOL%>) {
		//	if(trim(_IdLanzador[1].value)==="") { alert("Por Favor ingrese el Lanzador del Equipo Dos"); _IdLanzador[1].focus(); return false; }
		//}
		if(isEmpty(_MoneyLine[1],"El MoneyLine Dos")) { return false; }
		
		if(_Referencia[2].style.display="") {
			if(isEmptyInt(_Referencia[2],"La Referencia Tres")) { return false; }
		}
		
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

		if(isEmptyInt(_MoneyLine[1],"El MoneyLine Dos")) { return false; }
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
		cadena = (pos==1?idDeporte.value:cadena);
		cadena = (pos==2?idLiga.value:cadena);
		cadena = (pos==3?document.getElementById("idEquipo"+lanzador).value:cadena);
		http = new Ajax("<%=basePath%>llenarAjax.do?table="+pos+"&id="+cadena);
		http.setMetodo("POST");
		http.start();
	}
}

function llenarAnimalitos(cadena){
	var datos = cadena;

	var data = eval(datos);
	data.splice(0,1);
	data.sort();
	
	var ordenados= ordenarAnimalitos(data);
	
	if(datos!="") {
		
		for (var k = 0; k < ordenados.length; k++) {
			
			var select = document.getElementById("idEquipo"+k);
			
			while(select.hasChildNodes()) {
				select.removeChild(select.childNodes[0]);
			}
		   	   		    
		    var option = document.createElement("option");
		    option.setAttribute("value", ordenados[k].value);
		    var text = document.createTextNode(ordenados[k].text);
		    option.appendChild(text);
		    
		    select.appendChild(option);
		    
		    document.getElementById("linea"+k).style.display='';
		    with(document.forms[0]) {
				_MoneyLine[k].value='1';
			}
		    
			
		}
	}
}

function ordenarAnimalitos(cadena){
	
	var data = new Array(cadena.length);
			
	for (var k = 0; k < cadena.length; k++) {
		switch(cadena[k].text){
	case "0 DELFIN 0":
		data[0]=cadena[k];
		break;
	case "00 TUCAN 00":
		data[1]=cadena[k];
		break;
	case "1 CARNERO 1":
		data[2]=cadena[k];
		break;
	case "2 TORO 2":
		data[3]=cadena[k];
		break;
	case "3 CIEMPIES 3":
		data[4]=cadena[k];
		break;
	case "4 ALACRAN 4":
		data[5]=cadena[k];
		break;
	case "5 LEON 5":
		data[6]=cadena[k];
		break;
	case "6 RANA 6":
		data[7]=cadena[k];
		break;
	case "7 PERICO 7":
		data[8]=cadena[k];
		break;
	case "8 RATON 8":
		data[9]=cadena[k];
		break;
	case "9 AGUILA 9":
		data[10]=cadena[k];
		break;
	case "10 TIGRE 10":
		data[11]=cadena[k];
		break;
	case "11 GATO 11":
		data[12]=cadena[k];
		break;
	case "12 CABALLO 12":
		data[13]=cadena[k];
		break;
	case "13 MONO 13":
		data[14]=cadena[k];
		break;
	case "14 PALOMA 14":
		data[15]=cadena[k];
		break;
	case "15 ZORRO 15":
		data[16]=cadena[k];
		break;
	case "16 OSO 16":
		data[17]=cadena[k];
		break;
	case "17 PAVO 17":
		data[18]=cadena[k];
		break;
	case "18 BURRO 18":
		data[19]=cadena[k];
		break;
	case "19 CHIVO 19":
		data[20]=cadena[k];
		break;
	case "20 COCHINO 20":
		data[21]=cadena[k];
		break;
	case "21 GALLO 21":
		data[22]=cadena[k];
		break;
	case "22 CAMELLO 22":
		data[23]=cadena[k];
		break;
	case "23 CEBRA 23":
		data[24]=cadena[k];
		break;
	case "24 IGUANA 23":
		data[25]=cadena[k];
		break;
	case "24 IGUANA 24":
		data[26]=cadena[k];
		break;
	case "25 GALLINA 25":
		data[27]=cadena[k];
		break;
	case "26 VACA 26":
		data[28]=cadena[k];
		break;
	case "27 PERRO 27":
		data[29]=cadena[k];
		break;
	case "28 ZAMURO 28":
		data[30]=cadena[k];
		break;
	case "29 ELEFANTE 29":
		data[31]=cadena[k];
		break;
	case "30 CAIMAN 30":
		data[32]=cadena[k];
		break;
	case "31 MURCIELAGO 31":
		data[33]=cadena[k];
		break;
	case "32 ARDILLA 32":
		data[34]=cadena[k];
		break;
	case "33 PESCADO 33":
		data[35]=cadena[k];
		break;
	case "34 VENADO 34":
		data[36]=cadena[k];
		break;
	case "35 JIRAFA 35":
		data[37]=cadena[k];
		break;
	case "36 CULEBRA 36":
		data[38]=cadena[k];
		break;
		}
		
				
	}
	
	return data;
	
	
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
	   		if(document.getElementById("idDeporte").value=="26"){	   			
	   			llenarAnimalitos(cadena);
	   		}else{
	   			
	   		cadena = (texto!="[]"?"[{value:'',text:'Seleccione el equipo...'},"+texto.substring(1):"[{value:'',text:'Seleccione una liga...'}]");
	   		
	   		for(j=0;j<document.forms[0]._IdEquipo.length;j++) {
		   		llenarCombo("idEquipo"+j,cadena);
		   		ocultarLinea();
		   		
	   		}
	   		}
	   	} else if(opcion=="3") {
		   	texto = (texto!="[]"?"[{value:'',text:'Seleccione el lanzador...'},"+texto.substring(1):"[{value:'',text:'Seleccione un equipo...'}]");
	   		llenarCombo("idLanzador"+opcionLanzador,texto);
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
		   			for(var x=0; x<_Referencia.length;x++){
			   			_Referencia[x].value = ++n;
		   			}
	   			}
	   		}
   		}
	}
   	
}

function mostrar(ind) {
	try{
		document.getElementById("linea"+ind).style.display='';
		with(document.forms[0]) {
			_Referencia[ind].value=parseInt(_Referencia[parseInt(ind)-1].value)+1;
		}
	}catch(e){
		//
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


function igualarTotal(ind) {
	with(document.forms[0]) {
		_Total[(ind==0?1:0)].value = _Total[ind].value;
		_TotalDecimal[(ind==0?1:0)].value = _TotalDecimal[ind].value;
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
	

</script>
<html:form action="/registerGameSave" method="POST" >
<html:hidden property="multiEquipo" value="true"/>
<html:hidden name="juego" property="idJuego"/>
<html:hidden name="juego" property="idStatusJuego"/>
<html:hidden name="juego" property="idCampeonato"/>
<input type="hidden" name="eliminar" value="false"/>
<input type="hidden" name="abrir" value="false"/>
<select name="lista" 
	id="lista" 
	size="7" style="display:none; width:100%;position:absolute" 
	onClick="agregarClick(event,this)" 
	onFocus="seleccionado(this)" 
	onblur="this.style.display = 'none'" 
	onkeyup="agregar(event,this)"></select>
<table align="center" width="98%" height="100%" cellpadding="3" cellspacing="3" border="1">
  <tr>
	<td width="100%" align="left">
	<font color="white">
		<html:errors/>
	</font>
	</td>
  </tr>
  <tr>
  	<td align="center" width="49%" valign="top">
		<table width="100%" height="100%" border="0" cellpadding="3" cellspacing="5" class="tablaCaption">
			<caption class="tituloCaption">
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
					<tr>
						<td class="etiquetaCenter">DATOS DEL JUEGO CON MULTIPLES EQUIPOS&nbsp;&nbsp;<font color="#efefef">(<bean:write name="juego" property="descStatusJuego" />)</font></td>
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
			<tr>
			  	<td class="etiquetaLeft" >
			  		<bean:message key="juego.idCampeonato" />:<br/>
					<html:text name="juego" property="nombre" style="width:95%" onkeyup="javascript:buscar(this.value,event,this);" onfocus="ocultarCombo()" onblur="addCombo(this,event)" />
					<div id="cbo"></div>				  	
			  	</td>
		  	</tr>
  		</table>
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="5" class="tablaCaption">
			<tbody id="detalle">
			<tr>
			  	<td class="tituloTablaSmall tablaConBorde" style="width:5%" ><bean:message key="juegoEquipo.referencia" /></td>
			  	<td class="tituloTablaSmall tablaConBorde" style="width:60%" ><bean:message key="equipo.descEquipo" /></td>
			  	<td class="tituloTablaSmall tablaConBorde" ><bean:message key="usuarioJuegoEquipo.moneyLine" /></td>
		  	</tr>
		  	<%for(int x=0;x<cantidadEquipos;x++){
		  		String identificador = "idEquipo".concat(String.valueOf(x));
		  		String javascript = "mostrar("+String.valueOf(x+1)+")";%>
			<tr style="display:<%=(x==0?"":"none")%>;" id="linea<%=x%>">
			  	<td class="detalleTablaCenter" ><html:text value="0" property="_Referencia" size="5" maxlength="5" onkeyup="numerar()"/></td>
			  	<td class="detalleTablaCenter" >
			  		<html:select value="0" styleId='<%=identificador%>' property="_IdEquipo" style="width:95%">
			  			<logic:present name="listaEquipo">
			  			<html:optionsCollection name="listaEquipo" value="idEquipo" label="descEquipo" />
			  			</logic:present>
			  		</html:select>
			  	</td>
			  	</td>
			  	<td class="detalleTablaCenter" ><html:text value="0" property="_MoneyLine" size="4" maxlength="6" onkeyup='<%=javascript%>'/></td>
		  	</tr>
		  	<%}%>
		  	</tbody>
  		</table>
  	</td>
  </tr>
</table>	
</html:form>
<center>
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
<logic:present name="listaEquipo">
<script language="javascript">
function onLoad() {
	with(document.forms[0]) {
		<% 
		int size = forma.get_Referencia().length;
		for(int i=0; i<size; i++) {
			if(!forma.get_IdEquipo()[i].equals("0")) {%>
				_Referencia[<%=i%>].value='<%=forma.get_Referencia()[i]%>';
				_IdEquipo[<%=i%>].value='<%=forma.get_IdEquipo()[i]%>';
				_MoneyLine[<%=i%>].value='<%=forma.get_MoneyLine()[i]%>';
				mostrar(<%=i%>);
			<%} else {%>
				_IdEquipo[<%=i%>].value='';
			<%}%>
		<%}%>
	}
	
}
</script>
</logic:present>	
<logic:present name="forma" scope="session">
<script language="javascript">
	// ahora buscamos la ultima referencia
	accion="referencia";
	http = new Ajax("<%=basePath%>referenciaAjax.do?idDeporte="+document.forms[0].idDeporte.value);
	http.setMetodo("POST");
	http.start();
</script>
</logic:present>

