<jsp:directive.page import="com.betcesc.game.common.Constants"/><%@ include file="/views/common/taglibs.jsp"%>
<%@ page import="com.betcesc.game.ctrl.back.RegisterGameActionForm" %>
<% RegisterGameActionForm forma = (RegisterGameActionForm) request.getAttribute("juego"); 
boolean is1ro = forma.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL_1);
String is1roNone = (is1ro?"none":"");
boolean isAdmin = usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR);
boolean isAdministrador = usuario.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR);
int cantidadEquipos = Constants.CANTIDAD_EQUIPOS;%>
<script language="JavaScript" src="<%=basePath%>scripts/Calendario_2.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/ajax.js"></script>
<script language="javascript">
var accion = "";
var opcion = "";
var opcionLanzador = "";
var listaDeporteEmpate = <%=request.getAttribute("listaDeporteEmpate")%>;

function send(){
	with(document.forms[0]){
		if(isEmptyInt(_MoneyLine[0],"El MoneyLine Uno")) { return false; }

		if(isEmptyInt(_MoneyLine[1],"El MoneyLine Dos")) { return false; }
		
		submit();
	}
}

function limpiar() {
	document.forms[0].abrir.value='false';
	document.forms[0].cerrar.value='false';
	document.forms[0].eliminar.value='false';
}

function eliminar() {
	limpiar();
	document.forms[0].eliminar.value='true';
	send();
}
function abrir() {
	limpiar();
	document.forms[0].abrir.value='true';
	send();
}

function cerrar() {
	limpiar();
	document.forms[0].cerrar.value='true';
	send();
}
function modificar() {
	limpiar();
	send();
}
function agregar() {
	limpiar();
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

function responseText(texto) {
	if (accion=="llenar") {
	   	if(opcion=="1") {
		   	texto = (texto!="[]"?"[{value:'',text:'Seleccione una liga...'},"+texto.substring(1):"[{value:'',text:'Seleccione un deporte...'}]");
	   		llenarCombo("idLiga",texto);
	   	} else if(opcion=="2") {
	   		cadena = (texto!="[]"?"[{value:'',text:'Seleccione el equipo...'},"+texto.substring(1):"[{value:'',text:'Seleccione una liga...'}]");
	   		for(j=0;j<document.forms[0]._IdEquipo.length-1;j++) {
		   		llenarCombo("idEquipo"+j,cadena);
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

</script>
<html:form action="/registerGameSave" method="POST" >
<html:hidden name="juego" property="idJuego"/>
<html:hidden name="juego" property="idStatusJuego"/>
<html:hidden name="juego" property="idCampeonato"/>
<input type="hidden" name="eliminar" value="false"/>
<input type="hidden" name="abrir" value="false"/>
<input type="hidden" name="cerrar" value="false"/>
<select name="lista" 
	id="lista" 
	size="7" style="display:none; width:100%;position:absolute" 
	onClick="agregarClick(event,this)" 
	onFocus="seleccionado(this)" 
	onblur="this.style.display = 'none'" 
	onkeyup="agregar(event,this)"></select>
<table align="center" width="98%" height="100%" cellpadding="3" cellspacing="3">
<caption class="tituloTablaSup">REGISTRO DE JUEGO</caption>
  <tr>
	<td width="100%" align="left" >
	<font color="white">
		<html:errors/>
	</font>
	</td>
  </tr>
  <tr>
  	<td align="center" width="49%" valign="top">
  		<fieldset>
  		<legend class="etiqueta">Datos del Juego</legend>
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="5">
			<tr>
			  	<td class="etiquetaLeft" width="5%"><bean:message key="juego.idDeporte" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="detalleTablaLeft" ><div id="txtIdDeporte"></div>
			  		<html:select styleId="idDeporte" name="juego" property="idDeporte" onchange="llenar(1)" style="display:none">
			  			<option>Seleccione un deporte...</option>
			  			<html:optionsCollection name="listaDeporte" value="idDeporte" label="descDeporte"/>
			  		</html:select>
			  	</td>

			  	<td class="etiqueta" ><bean:message key="juego.fechaIni" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="detalleTablaLeft" width="25%">
			  		<%if(isAdministrador){%>
						<table  border="0" cellpadding="0" cellspacing="0">
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
			  		<%} else {%>
					  	<bean:write name="juego" property="fechaIni" />
			  		<%}%>
			  	</td>

			  	<td class="etiqueta" ><bean:message key="juego.horaJuego" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="detalleTablaLeft" >
			  		<%if(isAdministrador){%>
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
			  		<%} else {%>
			  		<bean:write name="juego" property="hora" />:<bean:write name="juego" property="minuto" />
			  		<%}%>
			  	</td>

		  	</tr>
			<tr>
			  	<td class="etiquetaLeft" width="5%"><bean:message key="liga.idLiga" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="detalleTablaLeft" ><div id="txtIdLiga"></div>
			  		<html:select styleId="idLiga" name="juego" property="idLiga"  onchange="llenar(2)" style="width:250px; display:none">
			  			<html:option value="0">Seleccione un deporte...</html:option>
			  			<logic:present name="listaLiga">
			  			<html:optionsCollection name="listaLiga" value="idLiga" label="descLiga" />
			  			</logic:present>
			  		</html:select>
			  	</td>

			  	<td class="etiqueta" ><bean:message key="juego.idCampeonato" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="detalleTablaLeft" >
					<bean:write name="juego" property="nombre" />
					<div id="cbo"></div>				  	
			  	</td>

			  	<td class="etiqueta" ><bean:message key="juego.minutosCierre" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="detalleTablaLeft" >
			  		<bean:write name="juego" property="minutosCierre" />
			  	</td>
		  	</tr>
  		</table>
  		<fieldset>
  		<legend class="etiqueta">Configuracion Juego</legend>
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="5">
			<tr>
			  	<td class="tituloTabla tablaConBorde" style="width:5%" ><bean:message key="juegoEquipo.referencia" /></td>
			  	<td class="tituloTabla tablaConBorde" style="width:50%" ><bean:message key="equipo.descEquipo" /></td>
			  	<td class="tituloTabla tablaConBorde" ><%=is1ro?"LOGRO (por anotacion de carreras)":"MONEYLINE"%></td>
		  	</tr>
		  	<%for(int x=0;x<cantidadEquipos;x++){
		  		String identificador = "idEquipo".concat(String.valueOf(x));
		  		String javascript = "mostrar("+String.valueOf(x+1)+")";%>
			<tr style="display:<%=(x==0?"":"none")%>;" id="linea<%=x%>">
			  	<td class="detalleTablaCenter" ><html:text value="" styleClass="inputTextSingle" property="_Referencia" size="5" maxlength="5" /></td>
			  	<td class="detalleTablaLeft" >
			  		<table border="0" cellppading="0" cellspacing="0">
			  			<td style="width:30px"><div id="txtIdEquipoCodigo<%=x%>" style=""></div></td>
			  			<td><div id="txtIdEquipo<%=x%>" style=""></div></td>
				  	</table>
				  	<div id="txtIdEquipo<%=x%>"></div>
					<html:hidden property="_IdJuegoEquipo" value="" />			  	
			  		<html:select value="0" styleId='<%=identificador%>' property="_IdEquipo" onchange="llenar(3)" style="width:95%;display:none">
			  			<html:option value="0">Seleccione una liga...</html:option>
			  			<logic:present name="listaEquipo">
			  			<html:optionsCollection name="listaEquipo" value="idEquipo" label="descEquipo" />
			  			</logic:present>
			  		</html:select>
			  	</td>
			  	<td class="detalleTablaCenter" ><b><%=is1ro?"SI&nbsp;&nbsp;:&nbsp;":""%></b><html:text value="0" property="_MoneyLine" size="4" maxlength="6" /></td>
		  	</tr>
		  	<%}%>
			
  		</table>
  		</fieldset>
  		</fieldset>
  		<br/>
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
	<a class="enlaceBoton" href="#" onClick="cerrar()"><bean:message key="boton.cerrar"/></a>
	&nbsp;&nbsp;&nbsp;
	<a class="enlaceBoton" href="#" onClick="modificar()"><bean:message key="boton.modificar"/></a>
</logic:equal>
<logic:notEqual name="modificar" value="true">
	<a class="enlaceBoton" href="#" onClick="agregar()"><bean:message key="boton.agregar"/></a>
</logic:notEqual>
&nbsp;&nbsp;&nbsp;
<a class="enlaceBoton" href="<%=basePath%>listGame.do"	><bean:message key="boton.cancelar"/></a>
</center>
<logic:present name="listaEquipo">

<script language="javascript">
function onLoad() {
	with(document.forms[0]) {
		<% for(int i=0; i<forma.get_Referencia().length; i++) {
			if(!forma.get_IdEquipo()[i].equals("0")) {%>
				_Referencia[<%=i%>].value='<%=forma.get_Referencia()[i]%>';
				_IdJuegoEquipo[<%=i%>].value='<%=forma.get_IdJuegoEquipo()[i]%>';
				_IdEquipo[<%=i%>].value='<%=forma.get_IdEquipo()[i]%>';
				_MoneyLine[<%=i%>].value='<%=forma.get_MoneyLine()[i]%>'; 
				document.getElementById("txtIdEquipoCodigo<%=i%>").innerHTML="<font color='red'><%=forma.get_Abreviatura()[i]%></font>&nbsp;";
				document.getElementById("txtIdEquipo<%=i%>").innerHTML=document.getElementById("idEquipo<%=i%>").options[document.getElementById("idEquipo<%=i%>").selectedIndex].text;
				mostrar(<%=i%>);
			<%} else {%>
				_IdEquipo[<%=i%>].value='';
			<%}%>
		<%}%>
		document.getElementById("txtIdDeporte").innerHTML=idDeporte.options[idDeporte.selectedIndex].text;
		document.getElementById("txtIdLiga").innerHTML=idLiga.options[idLiga.selectedIndex].text;

	}
	
}
</logic:present>	
</script>
