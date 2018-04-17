<jsp:directive.page import="com.betcesc.game.common.Constants"/><%@ include file="/views/common/taglibs.jsp"%>
<%@ page import="com.betcesc.game.ctrl.back.RegisterGameActionForm" %>
<% RegisterGameActionForm forma = (RegisterGameActionForm) request.getAttribute("juego"); 
boolean is1ro = forma.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL_1);
String is1roNone = (is1ro?"none":"");%>
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
		if(isEmptyInt(_Spread[0],"El RunLine Uno")) { return false; }
		if(isEmptyInt(_SpreadLogro[0],"El Logro del RunLine Uno")) { return false; }
		if(isEmptyInt(_Total[0],"la Alta")) { return false; }
		if(isEmptyInt(_TotalLogro[0],"El Logro la Alta")) { return false; }

		if(isEmptyInt(_MoneyLine[1],"El MoneyLine Dos")) { return false; }
		if(isEmptyInt(_Spread[1],"El RunLine Dos")) { return false; }
		if(isEmptyInt(_SpreadLogro[1],"El Logro del RunLine Dos")) { return false; }
		if(isEmptyInt(_Total[1],"la Baja")) { return false; }
		if(isEmptyInt(_TotalLogro[1],"El Logro de la baja")) { return false; }
		
		submit();
	}
}

function totalizar() {
	if(validar()) {
		if(confirm("Verifique bien los datos, el proceso no se puede revertir \n\n\nDesea continuar con la totalizacion?")) {
			document.forms[0].totalizar.value='true';
			document.forms[0].suspender.value='false';
			document.forms[0].submit();
		}
	}
}

function suspender() {
	if(confirm("Verifique bien los datos, el proceso no se puede revertir \n\n\nDesea continuar con la suspension?")) {
		document.forms[0].suspender.value='true';
		document.forms[0].totalizar.value='false';
		document.forms[0].submit();
	}
}

function abrir() {
	if(confirm("Desea abrir nuevamente el juego?")) {
		document.forms[0].abrir.value='true';
		document.forms[0].cerrar.value='false';
		document.forms[0].suspender.value='false';
		document.forms[0].totalizar.value='false';
		document.forms[0].submit();
	}
}

function cerrar() {
	if(confirm("Desea cerrar nuevamente el juego?")) {
		document.forms[0].abrir.value='false';
		document.forms[0].cerrar.value='true';
		document.forms[0].suspender.value='false';
		document.forms[0].totalizar.value='false';
		document.forms[0].submit();
	}
}

function evaluar(obj) {
	var opc = parseInt(obj.value);
	var sel = obj;
	
	for(var x=0; x<document.forms[0]._Ganador.length; x++) {
		document.forms[0]._Ganador[x].value=0;
	}
	sel.value=opc;
}

function validar() {
	var maximaPuntuacion = 0;
	var indice = -1;
	for(var x=0; x<document.forms[0]._Ganador.length; x++) {
		if(document.forms[0]._Ganador[x].value==1) {
			indice = x;
			maximaPuntuacion=document.forms[0]._Puntos[x].value;
			break;
		}
	}
	for(var x=0; x<document.forms[0]._Ganador.length; x++) {
		maximaPuntuacion = parseInt(maximaPuntuacion);
		//if(x!=indice && parseInt(document.forms[0]._Puntos[x].value)>=maximaPuntuacion) {
		//	alert("El puntaje mayor debe ir con el ganador");
		//	return false;
		//}
	}
	return true;
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
<html:hidden name="juego" property="idDeporte"/>
<input type="hidden" name="eliminar" value="false"/>
<input type="hidden" name="abrir" value="false"/>
<input type="hidden" name="cerrar" value="false"/>
<input type="hidden" name="suspender" value="false"/>
<input type="hidden" name="totalizar" value="false"/>
<select name="lista" 
	id="lista" 
	size="7" style="display:none; width:100%;position:absolute" 
	onClick="agregarClick(event,this)" 
	onFocus="seleccionado(this)" 
	onblur="this.style.display = 'none'" 
	onkeyup="agregar(event,this)"></select>
<table align="center" width="98%" height="100%" cellpadding="3" cellspacing="3">
<caption class="tituloTablaSup">TOTALIZAR JUEGO MULTIEQUIPO</caption>
  <tr>
	<td width="100%" align="left" colspan="1">
	<font color="white">
		<html:errors/>
	</font>
	</td>
  </tr>
  <tr>
  	<td align="center" width="49%" valign="top">
  		<fieldset>
  		<legend class="etiqueta">Datos del Juego</legend>
  		<br/>
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
					  	<bean:write name="juego" property="fechaIni" />
			  	</td>

			  	<td class="etiqueta" ><bean:message key="juego.horaJuego" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="detalleTablaLeft" ><bean:write name="juego" property="hora" />:<bean:write name="juego" property="minuto" />
			  	</td>
		  	</tr>
			<tr>
			  	<td class="etiquetaLeft" width="5%"><bean:message key="liga.idLiga" /></td>
			  	<td class="etiqueta" width="1%">&nbsp;:&nbsp;</td>
			  	<td class="detalleTablaLeft" ><div id="txtIdLiga"></div>
			  		<html:select styleId="idLiga" name="juego" property="idLiga" onchange="llenar(2)" style="width:250px; display:none">
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
			  	<td class="tituloTabla tablaConBorde" style="width:30%" ><bean:message key="equipo.descEquipo" /></td>
			  	<td class="tituloTabla tablaConBorde" >
			  		<%=is1ro?"LOGRO (por anotacion de carreras)":"MONEYLINE"%>
			  		<input type="checkbox" name="moneyActivo" value="1" checked >
			  	</td>
			  	<td class="tituloTabla tablaConBorde" ><%=is1ro?"Carreras":"Ganador"%></td>
			  	<td class="tituloTabla tablaConBorde <%=is1roNone%>" >Puntos</td>
		  	</tr>
		  	
			<% for(int x=0; x<forma.get_Referencia().length; x++) {
		  		String identificador = "idEquipo".concat(String.valueOf(x));%>
			<tr style="display:<%=(x==0?"":"none")%>;" id="linea<%=x%>">
			  	<td class="detalleTablaCenter" ><html:text value="" styleClass="inputTextSingle" property="_Referencia" size="5" maxlength="5" /></td>
			  	<td class="detalleTablaLeft" >
			  		<table border="0" cellppading="0" cellspacing="0">
			  			<td style="width:30px"><div id="txtIdEquipoCodigo<%=x%>" style=""></div></td>
			  			<td><div id="txtIdEquipo<%=x%>" style=""></div></td>
				  	</table>
				  	<div id="txtIdEquipo0"></div>
					<html:hidden property="_IdJuegoEquipo" value="" />			  	
			  		<html:select value="0" styleId='<%=identificador%>' property="_IdEquipo" onchange="llenar(3)" style="width:95%;display:none">
			  			<html:option value="0">Seleccione una liga...</html:option>
			  			<logic:present name="listaEquipo">
			  			<html:optionsCollection name="listaEquipo" value="idEquipo" label="descEquipo" />
			  			</logic:present>
			  		</html:select>
			  	</td>
			  	<td class="detalleTablaCenter" ><%=Constants.signo(forma.get_MoneyLine()[x])%></td>
			  	<td class="detalleTablaCenter" >
			  		<!--onchange="evaluar(this)"-->
			  		<html:select property="_Ganador">
			  			<html:option value="0">No</html:option>
			  			<html:option value="1">Si</html:option>
			  			<html:option value="3">Suspendido</html:option> 
			  		</html:select>
			  	</td>
			  	<td class="detalleTablaCenter <%=is1roNone%>" ><html:text property="_Puntos" size="4" value="0" readonly="<%=is1ro%>"/></td>
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
<logic:equal name="juego" property="idStatusJuego" value='<%= Constants.STATUS_JUEGO_CERRADO %>' >
<%if(isAdminGeneral){%>
	<a class="enlaceBoton" href="#" onClick="abrir()"><bean:message key="boton.abrir"/></a>
	&nbsp;&nbsp;&nbsp;
<%}%>
</logic:equal>
<logic:equal name="juego" property="idStatusJuego" value='<%= Constants.STATUS_JUEGO_TOTALIZADO %>' >
<%if(isAdminGeneral){%>
	<a class="enlaceBoton" href="#" onClick="cerrar()"><bean:message key="boton.cerrar"/></a>
	&nbsp;&nbsp;&nbsp;
<%}%>
</logic:equal>
<logic:equal name="juego" property="idStatusJuego" value='<%= Constants.STATUS_JUEGO_SUSPENDIDO %>' >
<%if(isAdminGeneral){%>
	<a class="enlaceBoton" href="#" onClick="cerrar()"><bean:message key="boton.cerrar"/></a>
	&nbsp;&nbsp;&nbsp;
<%}%>
</logic:equal>
<logic:equal name="modificar" value="true">
	<a class="enlaceBoton" href="#" onClick="totalizar()"><bean:message key="boton.totalizar"/></a>
	&nbsp;&nbsp;&nbsp;
	<a class="enlaceBoton" href="#" onClick="suspender()"><bean:message key="boton.suspender"/></a>
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
		<% for(int i=0; i<forma.get_Referencia().length; i++) {
			if(!forma.get_IdJuegoEquipo()[i].equals("0")) {%>
				_Referencia[<%=i%>].value='<%=forma.get_Referencia()[i]%>';
				_IdJuegoEquipo[<%=i%>].value='<%=forma.get_IdJuegoEquipo()[i]%>';
				_IdEquipo[<%=i%>].value='<%=forma.get_IdEquipo()[i]%>';
				document.getElementById("txtIdEquipoCodigo<%=i%>").innerHTML="<font color='red'><%=forma.get_IdEquipo()[i]%></font>&nbsp;";
				document.getElementById("txtIdEquipo<%=i%>").innerHTML=document.getElementById("idEquipo<%=i%>").options[document.getElementById("idEquipo<%=i%>").selectedIndex].text;
				mostrar(<%=i%>);
			<%} else {%>
				_IdJuegoEquipo[<%=i%>].value='';
			<%}%>
		<%}%>
		document.getElementById("txtIdDeporte").innerHTML=idDeporte.options[idDeporte.selectedIndex].text;
		document.getElementById("txtIdLiga").innerHTML=idLiga.options[idLiga.selectedIndex].text;

	}
	
}
</logic:present>	
</script>
