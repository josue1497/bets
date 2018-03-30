<%@ include file="/views/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=basePath%>scripts/ajax.js"></script>
<style>
input {
	background-color:#afafaf;
	border:1px solid #815a06;
}
select {
	background-color:#dfdfdf;
	border:1px solid #815a06;
/*	background-image: url(<%=basePath%>images/barraTit1.gif); */
}
.divCenter0 {position: absolute;
       margin-left: auto;
       margin-right: auto;
       left: 0;
       right: 0;
       width: 650px;
       height: 400px;
       padding:20px;
       /*border: 1px solid yellow;*/ 
}
.divCenter1 {position: absolute;
       margin-left: auto;
       margin-right: auto;
       left: 0;
       right: 0;
       width: 400px;
       height: 200px;
       padding:20px;
       /*border: 1px solid yellow;*/ 
}
.divCenter2 {position: absolute;
       margin-left: auto;
       margin-right: auto;
       left: 0;
       right: 0;
       width: 450px;
       height: 200px;
       padding:20px;
       /*border: 1px solid yellow;*/ 
}
.divCenter3 {position: absolute;
       margin-left: auto;
       margin-right: auto;
       left: 0;
       right: 0;
       width: 250px;
       height: 100px;
       /*border: 1px solid yellow;*/ 
}

</style>
<script language="javascript">
<!--
	// para el campo de texto de autocompletar
	var actual = -1;
	var http = null;
	var focoSelect = 0;
	var opcionAjax = -1;
	var accion = "";
	var listaCombos=["listaDeporte","listaLiga","listaEquipo","listaLanzador"];
	var isModificar = false;
	function buscar(valor,evento,obj) {
		for(var i = 0; i < document.forma.nombre.length; i++) {
			if(obj == document.forma.nombre[i]) {
				actual = i;
			}
		}
		if ( actual >= 0 ) {
			combo = "cbo_"+actual;
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
				http = new Ajax("<%=basePath%>buscarAjax.do?table="+actual+"&nombre="+valor);
				http.setMetodo("POST");
				http.start();
			}
		}
	}
	
	function responseText(texto) {
		focoSelect = 0;
		if (accion=="buscar") {
			if ( texto != "" ) {
				llenarComboAjax("lista",texto,"&&&");
				if (document.getElementById("lista").length > 0 ) {
					document.getElementById("lista").style.display = "";
					//document.getElementById("lista").style.top = ""+((ie?238:228)+(actual*(ie?26:23)))+"px";
				}
			} else {
				document.getElementById("lista").style.display = "none";
			}
		} else if(accion=="guardar") {
		    if(opcionAjax==0) {
				document.getElementById("mensaje_"+opcionAjax).innerHTML = error(texto);
				document.forma.desc_deporte.value="";
				try{document.forma.desc_deporte.focus();}catch(e){}
				if(document.forma.id_deporte.value!="0") {
					salir(opcionAjax);
				}
			} else if(opcionAjax==1) {
				document.getElementById("mensaje_"+opcionAjax).innerHTML = error(texto);
				document.forma.desc_liga.value="";
				document.forma.iniciales.value="";
				try{document.forma.desc_liga.focus();}catch(e){}
				if(document.forma.id_liga.value!="0") {
					salir(opcionAjax);
				}
			} else if(opcionAjax==2) {
				document.getElementById("mensaje_"+opcionAjax).innerHTML = error(texto);
				document.forma.id_equipo.value="";
				document.forma.desc_equipo.value="";
				document.forma.abreviatura.value="";
				document.forma.desc_corta.value="";
				try{document.forma.abreviatura.focus();}catch(e){}
				if(isModificar) {
					salir(opcionAjax);
				}
			} else if(opcionAjax==3) {
				document.getElementById("mensaje_"+opcionAjax).innerHTML = error(texto);
				document.forma.nombre_lanzador.value="";
				try{document.forma.nombre_lanzador.focus();}catch(e){}
				if(document.forma.id_lanzador.value!="0") {
					salir(opcionAjax);
				}
			}
		} else if(accion=="eliminar") {
			if(texto!=""){alert(texto);}
			llenar(opcionAjax);
		} else if(accion=="modificar") {
			if ( texto != "" ) {
			    if(opcionAjax==0) {
					var deporte = eval("("+texto+")");
					with(document.forma) {
						id_deporte.value = deporte.idDeporte;					
						desc_deporte.value = deporte.descDeporte;					
						empate.value = deporte.empate;					
						id_status_deporte.value = deporte.idStatusDeporte;
						referencia_inicio.value = deporte.referenciaInicio;
						runline_inicio.value = deporte.runlineInicio;
						combinacion.value = deporte.combinacion;
						items.value = deporte.items;
						runline_logro_inicio0.value = deporte.runlineLogroInicio0;
						runline_logro_inicio1.value = deporte.runlineLogroInicio1;
						altabaja_logro_inicio0.value = deporte.altabajaLogroInicio0;
						altabaja_logro_inicio1.value = deporte.altabajaLogroInicio1;
					}
					ficha(opcionAjax);
				} else if(opcionAjax==1) {
					var liga = eval("("+texto+")");
					with(document.forma) {
						id_liga.value = liga.idLiga;					
						desc_liga.value = liga.descLiga;
						iniciales.value = liga.iniciales;
						id_deporte.value = liga.idDeporte;
					}
					ficha(opcionAjax);
				} else if(opcionAjax==2) {
					var equipo = eval("("+texto+")");
					with(document.forma) {
						id_equipo.value = equipo.idEquipo;					
						desc_equipo.value = equipo.descEquipo;
						abreviatura.value = equipo.abreviatura;
						desc_corta.value = equipo.descCorta;
						id_liga.value = equipo.idLiga;
					}
					ficha(opcionAjax);
				} else if(opcionAjax==3) {
					var lanzador = eval("("+texto+")");
					with(document.forma) {
						id_lanzador.value = lanzador.idLanzador;					
						nombre_lanzador.value = lanzador.nombreLanzador;
						id_equipo.value = lanzador.idEquipo;
					}
					ficha(opcionAjax);
				}
			}
		} else if(accion=="llenar") {
		    if(opcionAjax==0) {
		    	llenarCombo("listaDeporte",texto);
		    	llenarCombo("listaLiga","[{value:'',text:'Seleccione un deporte...'}]");
		    	llenarCombo("listaEquipo","[{value:'',text:'Seleccione una liga...'}]");
		    	llenarCombo("listaLanzador","[{value:'',text:'Seleccione un equipo...'}]");
		    } else if(opcionAjax==1) {
		    	texto = (texto!="[]"?texto:"[{value:'',text:'Seleccione un deporte...'}]");
		    	llenarCombo("listaLiga",texto);
		    	llenarCombo("listaEquipo","[{value:'',text:'Seleccione una liga...'}]");
		    	llenarCombo("listaLanzador","[{value:'',text:'Seleccione un equipo...'}]");
		    } else if(opcionAjax==2) {
		    	texto = (texto!="[]"?texto:"[{value:'',text:'Seleccione una liga...'}]");
		    	llenarCombo("listaEquipo",texto);
		    	llenarCombo("listaLanzador","[{value:'',text:'Seleccione un equipo...'}]");
		    } else if(opcionAjax==3) {
		    	texto = (texto!="[]"?texto:"[{value:'',text:'Seleccione un equipo...'}]");
		    	llenarCombo("listaLanzador",texto);
		    }
		}
	}
	
	
	function error(texto) {
		if ( texto != "" ) {
			return "<font color='red' face='Arial, Verdana'>"+texto+"</font>"
		}
		return "";
	}

	function agregarClick(evento,obj) {
		document.forma.nombre[actual].value = obj.options[obj.selectedIndex].text;
		ocultar();
	}

	function seleccionado(obj) {
		focoSelect = 1;
		if ( obj.selectedIndex > 0 ) {
			document.forma.nombre[actual].value = obj.options[obj.selectedIndex].text;
		}
	}
	
	function agregar(evento,obj) {
		try {
			document.forma.nombre[actual].value = obj.options[obj.selectedIndex].text;
			if ( evento.keyCode == 13 ) {
				ocultar();
			}
		} catch(e) { }
	}
	
	function ocultar() {
		document.getElementById("lista").style.display = "none";
		try {
			document.forma.nombre[actual].focus();
			document.forma.nombre[actual].select;
		} catch (e) {
		}
	}
	
	function esconder() {
		//setTimeout("ocultar()","300");
	}

	function ocultarCombo(pos) {
		actual = pos;
		document.getElementById("lista").style.display = "none";
	}
	
	function addCombo(field,event,pos,idLista) {
		isModificar=true;
		if(event.keyCode!=13 || trim(field.value)=="") return;
		// buscamos en el combo
		var sel = document.getElementById(listaCombos[pos]);
		for(var i=0; i<sel.length ;i++) {
			var item = sel.options[i].text;
			if(item.toLowerCase()==field.value.toLowerCase()) {
				sel.value = sel.options[i].value;
				field.value="";
				field.focus();
				if(pos>-1 && pos<4) {
					llenar(pos+1);
				}
				return;
			}
		}
		// agregaremos al combo
		if(document.forma.lista.length>0) {
			for(var i=0; i<document.forma.lista.length ;i++) {
				//alert(document.forma.lista.options[i].text+" / "+field.value);
				if(document.forma.lista.options[i].text==field.value) {
					field.value="";
					with(document.forma) {
						if(pos==0) {
							id_deporte.value = document.forma.lista.options[i].value;					
							id_status_deporte.value = "1";
						} else if(pos==1) {
							id_liga.value = document.forma.lista.options[i].value;					
							id_deporte.value = document.forma.listaDeporte.value;
						} else if(pos==2) {
							id_equipo.value = document.forma.lista.options[i].value;					
							id_liga.value = document.forma.listaLiga.value;
						} else if(pos==3) {
							id_lanzador.value = document.forma.lista.options[i].value;					
							id_equipo.value = document.forma.listaEquipo.value;
						}
					}
					guardar(pos,false);
					return;
				}
			}
		}
		//document.forma.desc_deporte.value = field.value;
		//field.value="";
		//ficha(pos);
		
	}
	
	function ficha(pos) {
		for(var x=0;x<4;x++) {
			if(x!=pos) mostrarLista(x);
		}
		if(pos==1 && document.getElementById(listaCombos[0]).value=="") { alert("Por favor Seleccione un deporte antes de registrar una liga");return;}
		if(pos==2 && document.getElementById(listaCombos[1]).value=="") { alert("Por favor Seleccione una liga antes de registrar un equipo");return;}
		if(pos==3 && document.getElementById(listaCombos[2]).value=="") { alert("Por favor Seleccione un equipo antes de registrar un lanzador");return;}
		document.getElementById("mensaje_"+pos).innerHTML = "";
		document.getElementById("txt_"+pos).style.display="none";
		document.getElementById("frm_"+pos).style.display="";
		document.getElementById("sel_"+pos).style.display="none";
		
		document.getElementById("frm_"+pos).style.position="absolute";
		document.getElementById("frm_"+pos).style.left="0px";
		document.getElementById("frm_"+pos).style.top="130px";
		//document.getElementById("frm_"+pos).style.margin="0px auto";
		//document.getElementById("frm_"+pos).style.marginLeft="200px";
		//document.getElementById("frm_"+pos).style.marginRight="200px";
		document.getElementById("tabla").style.display="none";
		
	}
	
	function eliminar(pos) {
		accion="eliminar";
		opcionAjax=pos;
		hacer = false;
		with(document.forma) {
			if(pos==1) { //liga
				if(listaLiga.value!="") {
					http = new Ajax("<%=basePath%>eliminarAjax.do?table=1&id_liga="+listaLiga.value+"&id_deporte="+listaDeporte.value);
					hacer=true;
				}
			} else if(pos==2) { //equipo
				if(listaEquipo.value!="") {
					http = new Ajax("<%=basePath%>eliminarAjax.do?table=2&id_equipo="+listaEquipo.value+"&id_liga="+listaLiga.value);
					hacer=true;
				}
			} else if(pos==3) { //lanzador
				if(listaLanzador.value!="") {
					http = new Ajax("<%=basePath%>eliminarAjax.do?table=3&id_lanzador="+listaLanzador.value+"&id_equipo="+listaEquipo.value);
					hacer=true;
				}
			}
		}
		if(hacer) {
			http.setMetodo("POST");
			http.start();
		}
	}

	function mostrarLista(pos) {
		document.getElementById("txt_"+pos).style.display="";
		document.getElementById("frm_"+pos).style.display="none";
		document.getElementById("sel_"+pos).style.display="";
	}

	function salir(pos) {
		isModificar = false;
		document.getElementById("txt_"+pos).style.display="";
		document.getElementById("frm_"+pos).style.display="none";
		document.getElementById("sel_"+pos).style.display="";
		document.getElementById("tabla").style.display="";

		if(pos==0) { //deporte
			with(document.forma) {
				id_deporte.value = "0";					
				desc_deporte.value = "";
				empate.value = "0";
				id_status_deporte.value = "1";
			}
			llenar(pos);
		} else if(pos==1) { //liga
			with(document.forma) {
				id_liga.value = "0";					
				desc_liga.value = "";
				iniciales.value = "";
				id_deporte.value = "0";					
			}
			llenar(pos);
		} else if(pos==2) { //equipo
			with(document.forma) {
				id_equipo.value = "";
				desc_equipo.value = "";
				abreviatura.value = "";
				desc_corta.value = "";
				id_liga.value = "0";
			}
			llenar(pos);
		} else if(pos==3) { //lanzador
			with(document.forma) {
				id_lanzador.value = "0";					
				nombre_lanzador.value = "";
				id_equipo.value = "";
			}
			llenar(pos);
		}
	}
	
	function guardar(pos,validar) {
		validar=(typeof validar == 'undefined'?true:validar);
		accion="guardar";
		opcionAjax=pos;
		with(document.forma) {
			if(pos==0) { //deporte
				if(validar) {
					if(isEmpty(desc_deporte,"El nombre del deporte") ) { return false;}
				}
				var cad = "<%=basePath%>agregarAjax.do?table=0&id_deporte="+id_deporte.value;
				cad += "&desc_deporte="+desc_deporte.value+"&empate="+empate.value+"&id_status_deporte="+id_status_deporte.value;
				cad += "&referencia_inicio="+referencia_inicio.value+"&runline_inicio="+runline_inicio.value;
				cad += "&combinacion="+combinacion.value+"&items="+items.value;
				cad += "&runline_logro_inicio0="+runline_logro_inicio0.value;
				cad += "&runline_logro_inicio1="+runline_logro_inicio1.value;
				cad += "&altabaja_logro_inicio0="+altabaja_logro_inicio0.value;
				cad += "&altabaja_logro_inicio1="+altabaja_logro_inicio1.value;
				http = new Ajax(cad);
			} else if(pos==1) { //liga
				if(validar) {
					if(isEmpty(desc_liga,"El nombre de la liga") ) { return false;}
					if(isEmpty(id_liga,"Las iniciales de la liga") ) { return false;}
				}
				http = new Ajax("<%=basePath%>agregarAjax.do?table=1&id_liga="+id_liga.value+"&desc_liga="+desc_liga.value+"&iniciales="+iniciales.value+"&id_deporte="+listaDeporte.value);
			} else if(pos==2) { //equipo
				if(validar) {
					if(isEmpty(abreviatura,"La abreviatura del equipo") ) { return false;}
					if(isEmpty(desc_equipo,"El nombre del equipo") ) { return false;}
					if(isEmpty(desc_corta,"El nombre corto del equipo") ) { return false;}
				}
				http = new Ajax("<%=basePath%>agregarAjax.do?table=2&id_equipo="+id_equipo.value+"&desc_equipo="+desc_equipo.value+"&abreviatura="+abreviatura.value+"&desc_corta="+desc_corta.value+"&id_liga="+listaLiga.value);
			} else if(pos==3) { //lanzador
				if(validar) {
					if(isEmpty(nombre_lanzador,"El nombre del lanzador") ) { return false;}
				}
				http = new Ajax("<%=basePath%>agregarAjax.do?table=3&id_lanzador="+id_lanzador.value+"&nombre_lanzador="+nombre_lanzador.value+"&id_equipo="+listaEquipo.value);
			}
		}
		http.setMetodo("POST");
		http.start();
	}
	
	function activar(pos) {
		accion="activar";
		if(pos==0) { //deporte
			with(document.forma) {
				opcionAjax=pos;
				http = new Ajax("<%=basePath%>activarAjax.do?table=0&id_deporte="+id_deporte.value+"&id_status_deporte=1");
				http.setMetodo("POST");
				http.start();
			}
		}
	}
	
	function modificar(obj,pos) {
		isModificar = true;
		accion="modificar";
		opcionAjax=pos;
		http = new Ajax("<%=basePath%>consultarAjax.do?table="+pos+"&id="+obj.value);
		http.setMetodo("POST");
		http.start();
	}

	function llenar(pos) {
		accion="llenar";
		opcionAjax = pos;
		var cadena = "";
		with(document.forma) {
			if(listaDeporte.value!="<%=Constants.ID_EQUIPO_BEISBOL%>" && listaDeporte.value!="<%=Constants.ID_EQUIPO_BEISBOL_5%>" ) {
				document.getElementById("masLanzador").style.display=("none");
				document.getElementById("menosLanzador").style.display=("none");
				document.getElementById("listaLanzador").disabled=true;
				nombre[3].readOnly = true;
			} else {
				document.getElementById("masLanzador").style.display=("");
				document.getElementById("menosLanzador").style.display=("");
				document.getElementById("listaLanzador").disabled=false;
				nombre[3].readOnly = false;
			}
			if(pos==4) {
				if(listaDeporte.value=="4") {
					for(var x=0;x<4;x++) mostrarLista(x);
				}
			} else {
				for(var x=0;x<4;x++) mostrarLista(x);
			}
			
			cadena = (pos==1?listaDeporte.value:cadena);
			cadena = (pos==2?listaLiga.value:cadena);
			cadena = (pos==3?listaEquipo.value:cadena);
			http = new Ajax("<%=basePath%>llenarAjax.do?table="+pos+"&id="+cadena);
			http.setMetodo("POST");
			http.start();
		}
	}

//-->
</script>
<table id="tabla" align="center" width="98%" height="100%" border="0" cellpadding="5">
<form name="forma" style="margin:0pt;padding:0;">
<input type="hidden" name="id_deporte" value="0"/>
<input type="hidden" name="id_liga" value="0"/>
<input type="hidden" name="id_equipo" value="0"/>
<input type="hidden" name="id_lanzador" value="0"/>
<select name="lista" 
	id="lista" 
	size="7" style="display:none; width:100%;position:absolute" 
	onClick="agregarClick(event,this)" 
	onFocus="seleccionado(this)" 
	onblur="this.style.display = 'none'" 
	onkeyup="agregar(event,this)"></select>
	<%if(isAdminGeneral){%>
	<tr>
		<td>
		</td>
		<td colspan="2">
			<table id="tabla" align="center" width="98%" border="0" cellpadding="0">
				<tr>
					<td class="titulo" style="border:2px solid #afafaf;cursor:pointer;background-color:white;color:black;" onclick="ventanaLogros()"><div >LOGROS PRECONFIGURADOS PARA LOS JUEGOS</div>
					</td>
				</tr>
			</table>
		</td>
		<td>
		</td>
	</tr>
	<%}%>
	<tr>
		<td class="etiquetaLeft" style="width:25%" valign="top">
			<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="5" class="tablaCaption">
				<caption class="tituloCaption">
					<table width="100%" cellspacing="0" cellpadding="0" border="0">
						<tr>
							<td width="30%"></td>
							<td>Deportes</td>
							<td width="30%" align="right"><img src="<%=basePath%>icons/accept.png"	onclick="ficha(0)">&nbsp;</td>
						</tr>
					</table>
				</caption>
				<tr>
				  	<td width="5%" valign="top">
					  	<div id="txt_0">
						<table width="100%" height="100%" border="0" cellpadding="1" cellspacing="0">
							<tr>
							  	<td width="100%" valign="middle">
								  	<input type="text" name="nombre" style="width:99%" onblur="esconder()" onkeyup="javascript:buscar(this.value,event,this);addCombo(this,event,0);" onfocus="ocultarCombo(0)">
							  	</td>
						  	</tr>
						</table>
						<div id="cbo_0"></div>				  	
						</div>
				  	</td>
			  	</tr>
				<tr>
				  	<td class="etiquetaLeft" width="5%" valign="top">
				  	<div id="sel_0">
				  	<select id="listaDeporte" name="listaDeporte" size="25" height="100%" style="width:100%;" ondblclick="modificar(this,0);"  onchange="llenar(1);">
				  	</select>
				  	</div>
				  	</td>
			  	</tr>
	  		</table>
		</td>
		<td class="etiquetaLeft" style="width:25%">
			<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="5" class="tablaCaption">
				<caption class="tituloCaption">
					<table width="100%" cellspacing="0" cellpadding="0" border="0">
						<tr>
							<td width="30%"></td>
							<td>Ligas</td>
							<td width="30%" align="right">
									<img src="<%=basePath%>icons/accept.png"	onclick="ficha(1)">&nbsp;
									<img src="<%=basePath%>icons/delete.png"	onclick="eliminar(1)">&nbsp;
							</td>
						</tr>
					</table>
				</caption>
				<tr>
				  	<td width="5%" valign="middle">
					  	<div id="txt_1">
						<table width="100%" height="100%" border="0" cellpadding="1" cellspacing="0">
							<tr>
							  	<td width="100%" valign="middle">
					  				<input type="text" name="nombre" style="width:99%" onblur="esconder()" onkeyup="javascript:buscar(this.value,event,this);addCombo(this,event,1);" onfocus="ocultarCombo(1)">
							  	</td>
						  	</tr>
						</table>
						<div id="cbo_1"></div>				  	
						</div>
				  	</td>
			  	</tr>
				<tr>
				  	<td class="etiquetaLeft" width="5%" valign="top">
				  	<div id="sel_1">
				  	<select id="listaLiga" name="listaLiga" size="25" style="width:100%" ondblclick="modificar(this,1);" onchange="llenar(2);">
				  	</select>
				  	</div>
				  	</td>
			  	</tr>
	  		</table>
		</td>
		<td class="etiquetaLeft" style="width:25%">
			<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="5" class="tablaCaption">
				<caption class="tituloCaption">
					<table width="100%" cellspacing="0" cellpadding="0" border="0">
						<tr>
							<td width="30%"></td>
							<td>Equipos</td>
							<td width="30%" align="right">
								<img src="<%=basePath%>icons/accept.png"	onclick="ficha(2)">&nbsp;
								<img src="<%=basePath%>icons/delete.png"	onclick="eliminar(2)">&nbsp;
							</td>
						</tr>
					</table>
				</caption>
				<tr>
				  	<td width="5%" valign="top">
					  	<div id="txt_2">
						<table width="100%" height="100%" border="0" cellpadding="1" cellspacing="0">
							<tr>
							  	<td width="100%" valign="middle">
								  	<input type="text" name="nombre" style="width:99%" onblur="esconder()" onkeyup="javascript:buscar(this.value,event,this);addCombo(this,event,2);" onfocus="ocultarCombo(2)">
							  	</td>
						  	</tr>
						</table>
						<div id="cbo_2"></div>				  	
						</div>
				  	</td>
			  	</tr>
				<tr>
				  	<td class="etiquetaLeft" width="5%" valign="top">
				  	<div id="sel_2">
				  	<select id="listaEquipo" name="listaEquipo" size="25" style="width:100%" ondblclick="modificar(this,2);" onchange="llenar(3);">
				  	</select>
				  	</div>
				  	</td>
			  	</tr>
	  		</table>
		</td>
		<td id="columnaLanzador" class="etiquetaLeft" style="width:25%">
			<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="5" class="tablaCaption">
				<caption class="tituloCaption">
					<table width="100%" cellspacing="0" cellpadding="0" border="0">
						<tr>
							<td width="30%"></td>
							<td>Lanzadores</td>
							<td width="30%" align="right">
							  		<span id="masLanzador">
										<img src="<%=basePath%>icons/accept.png"	onclick="ficha(3)">
									</span>
									&nbsp;
							  		<span id="menosLanzador">
										<img src="<%=basePath%>icons/delete.png"	onclick="eliminar(3)">
									</span>&nbsp;
							</td>
						</tr>
					</table>
				</caption>
				<tr>
				  	<td width="5%" valign="top">
					  	<div id="txt_3">
						<table width="100%" height="100%" border="0" cellpadding="1" cellspacing="0">
							<tr>
							  	<td width="100%" valign="middle">
								  	<input type="text" name="nombre" style="width:99%" onblur="esconder()" onkeyup="javascript:buscar(this.value,event,this);addCombo(this,event,3);" onfocus="ocultarCombo(3)">
							  	</td>
						  	</tr>
						</table>
						<div id="cbo_3"></div>				  	
						</div>
				  	</td>
			  	</tr>
				<tr>
				  	<td class="etiquetaLeft" width="5%" valign="top">
				  	<div id="sel_3">
				  	<select id="listaLanzador" name="listaLanzador" size="25" style="width:100%" ondblclick="modificar(this,3);">
				  	</select>
				  	</div>
				  	</td>
			  	</tr>
	  		</table>
		</td>
	</tr>	
</table>
<!-- editor 0 -->
				  	<div id="frm_0" class="divCenter0" style="display:none;" >
						<div id="mensaje_0" class="etiquetaLeft"></div>
						<table width="100%" height="100%" border="0" cellpadding="5" cellspacing="5" class="tablaCaption">
							<caption class="tituloCaption">
								<table width="100%" cellspacing="0" cellpadding="0" border="0">
									<tr>
										<td class="etiquetaCenter">Registro de Deporte</td>
									</tr>
								</table>
							</caption>
							<tr>
								<td class="etiquetaLeft">Nombre</td>
						  	</tr>
							<tr>
						  		<td><input type="text" name="desc_deporte" style="width:98%" maxlength="50"/></td>
						  	</tr>
							<tr>
								<td class="etiquetaLeft">Puede Empatar</td>
						  	</tr>
							<tr>
						  		<td><select name="empate" style="width:200px" ><option value="0">No puede empatar</option><option value="1">Si se permite el empate</option><option value="2">Deporte con mas de dos equipos</option></select></td>
						  	</tr>
							<tr>
								<td class="etiquetaLeft">Status</td>
						  	</tr>
							<tr>
						  		<td><select name="id_status_deporte" ><option value="1">Activo</option><option value="2">Inactivo</option></select></td>
						  	</tr>
							<tr>
								<td class="etiquetaLeft">Referencia</td>
						  	</tr>
							<tr>
						  		<td><input type="text" name="referencia_inicio" size="5" maxlength="50"/></td>
						  	</tr>
							<tr>
								<td class="etiquetaLeft">Runline</td>
						  	</tr>
							<tr>
						  		<td><input type="text" name="runline_inicio" size="5" maxlength="50"/></td>
						  	</tr>
							<tr>
								<td class="etiquetaLeft">Runline Logro</td>
						  	</tr>
							<tr>
						  		<td><input type="text" name="runline_logro_inicio0" size="5" maxlength="50"/>&nbsp;
						  			<input type="text" name="runline_logro_inicio1" size="5" maxlength="50"/></td>
						  	</tr>
							<tr>
								<td class="etiquetaLeft">Alta/Baja Logro</td>
						  	</tr>
							<tr>
						  		<td><input type="text" name="altabaja_logro_inicio0" size="5" maxlength="50"/>&nbsp;
						  		    <input type="text" name="altabaja_logro_inicio1" size="5" maxlength="50"/></td>
						  	</tr>
							<tr>
								<td class="etiquetaLeft">Combinaci&oacute;n de jugadas no validas para este deporte</td>
						  	</tr>
							<tr>
						  		<td><input type="text" name="combinacion"  maxlength="700" size="90"/></td>
						  	</tr>
							<tr class="none">
								<td class="etiquetaLeft">Numero de equipos a ingresar</td>
						  	</tr>
							<tr class="none">
						  		<td><input type="hidden" name="items"  maxlength="2" size="4" value="0"/></td>
						  	</tr>
							<tr>
								<td>&nbsp;</td>
						  	</tr>
							<tr>
								<td>
									<a class="enlaceBoton" href="#" onclick="guardar(0)" ><bean:message key="boton.guardar"/>&nbsp;</a>
									&nbsp;&nbsp;
									<a class="enlaceBoton" href="#" onclick="salir(0)"	><bean:message key="boton.salir"/>&nbsp;</a>
								</td>
						  	</tr>
				  		</table>
				  	</div>
<!-- editor 1 -->
				  	<div id="frm_1" class="divCenter1" style="display:none">
						<div id="mensaje_1" class="etiquetaLeft"></div>
						<table width="100%" height="100%" border="0" cellpadding="5" cellspacing="5" class="tablaCaption">
							<caption class="tituloCaption">
								<table width="100%" cellspacing="0" cellpadding="0" border="0">
									<tr>
										<td class="etiquetaCenter">Registro de Liga</td>
									</tr>
								</table>
							</caption>
							<tr>
								<td class="etiquetaLeft">Nombre</td>
						  	</tr>
							<tr>
						  		<td><input type="text" name="desc_liga" maxlength="100" style="width:98%"/></td>
						  	</tr>
							<tr>
								<td class="etiquetaLeft">Iniciales o Siglas</td>
						  	</tr>
							<tr>
						  		<td><input type="text" name="iniciales" maxlength="20" onkeyup="this.value=this.value.toUpperCase();"/></td>
						  	</tr>
							<tr>
								<td>&nbsp;</td>
						  	</tr>
							<tr>
								<td>
									<a class="enlaceBoton" href="#" onclick="guardar(1)" ><bean:message key="boton.guardar"/>&nbsp;</a>
									&nbsp;&nbsp;
									<a class="enlaceBoton" href="#" onclick="salir(1)"	><bean:message key="boton.salir"/>&nbsp;</a>
								</td>
						  	</tr>
				  		</table>
				  	</div>
<!-- editor 2 -->
				  	<div id="frm_2" class="divCenter2" style="display:none">
						<div id="mensaje_2" class="etiquetaLeft"></div>
						<table width="100%" height="100%" border="0" cellpadding="5" cellspacing="5" class="tablaCaption">
							<caption class="tituloCaption">
								<table width="100%" cellspacing="0" cellpadding="0" border="0">
									<tr>
										<td class="etiquetaCenter">Registro de Equipo</td>
									</tr>
								</table>
							</caption>
							<tr>
								<td class="etiquetaLeft">Abreviatura</td>
						  	</tr>
							<tr>
						  		<td><input type="text" name="abreviatura" onkeyup="this.value=this.value.toUpperCase();" maxlength="10" size="6"/></td>
						  	</tr>
							<tr>
								<td class="etiquetaLeft">Nombre del equipo</td>
						  	</tr>
							<tr>
						  		<td><input type="text" name="desc_equipo" style="width:98%" maxlength="100"/></td>
						  	</tr>
							<tr>
								<td class="etiquetaLeft">Nombre corto del equipo para el ticket</td>
						  	</tr>
							<tr>
						  		<td><input type="text" name="desc_corta" style="width:98%" maxlength="100"/></td>
						  	</tr>
							<tr>
								<td>&nbsp;</td>
						  	</tr>
							<tr>
								<td>
									<a class="enlaceBoton" href="#" onclick="guardar(2)" ><bean:message key="boton.guardar"/>&nbsp;</a>
									&nbsp;&nbsp;
									<a class="enlaceBoton" href="#" onclick="salir(2)"	><bean:message key="boton.salir"/>&nbsp;</a>
								</td>
						  	</tr>
				  		</table>
				  	</div>
<!-- editor 3 -->
				  	<div id="frm_3" class="divCenter3" style="display:none">
						<div id="mensaje_3" class="etiquetaLeft"></div>
						<table width="100%" height="100%" border="0" cellpadding="5" cellspacing="5" class="tablaCaption">
							<caption class="tituloCaption">
								<table width="100%" cellspacing="0" cellpadding="0" border="0">
									<tr>
										<td class="etiquetaCenter">Registro de Equipo</td>
									</tr>
								</table>
							</caption>
							<tr>
								<td class="etiquetaLeft">Nombre</td>
						  	</tr>
							<tr>
						  		<td><input type="text" name="nombre_lanzador" style="width:98%" maxlength="50"/></td>
						  	</tr>
							<tr>
								<td>&nbsp;</td>
						  	</tr>
							<tr>
								<td>
									<a class="enlaceBoton" href="#" onclick="guardar(3)" ><bean:message key="boton.guardar"/>&nbsp;</a>
									&nbsp;&nbsp;
									<a class="enlaceBoton" href="#" onclick="salir(3)"	><bean:message key="boton.salir"/>&nbsp;</a>
								</td>
						  	</tr>
				  		</table>
				  	</div>
</form>

<script language="javascript">
	llenar(0);
</script>
