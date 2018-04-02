function Ajax(direccionUrl) {
	this.http = null;
	this.metodo = "GET";
	this.url = direccionUrl;
	this.send = null;

	this.setOnReadyStateChange = setOnReadyStateChange;	
	this.setUrl = setUrl;
	this.setMetodo = setMetodo;
	this.setSend = setSend;
	this.start = start;
	this.crearXMLHttpRequest = crearXMLHttpRequest;
}

function setOnReadyStateChange(valor){
	this.onreadystatechange = valor;
}
function setUrl(valor){
	this.url = valor;
}
function setMetodo(valor){
	this.metodo = valor;
}
function setSend(valor){
	this.send = valor;
}

function crearXMLHttpRequest() {
	if ( window.ActiveXObject ) {
		this.http = new ActiveXObject("Microsoft.XMLHTTP");
	} else {
		this.http = new XMLHttpRequest();
	}
	return this.http;
}

function manejador() {

	if ( http.readyState == 4 ) {
		if ( http.status == 404 ) {
			responseError();
		}
		if ( http.status == 200 ) {
			responseText(http.responseText);
			responseXML(http.responseXML);
		}
	}
}

function start() {
	this.http = crearXMLHttpRequest();
	this.http.onreadystatechange = manejador;
	this.http.open(this.metodo,this.url,true);
	this.http.send(this.send);
}


function responseText(valor) {
}
function responseXML(valor) {
}
function responseError() {
	alert("Error 404 - Pagina no econtrada");
}


function llenarComboAjax(id,datos,separador){
	if ( !separador ) {
		separador = "&";
	}
	var obj = document.getElementById(id);
	var resp = datos.split(separador);
	while(obj.hasChildNodes()) {
		obj.removeChild(obj.childNodes[0]);
	}
	if ( resp.length > 0 && trim(datos)!="" && trim(resp[0])!="" ) {
		for (var i = 0; i < resp.length; i=i+1) {
			var arr = resp[i].split(":");
			obj.options[obj.length] = new Option(arr[1],arr[0]);
		}
	}
}

function llenarCombo(id,datos){
	var obj = document.getElementById(id);

//alert(datos);
	var data = eval(datos);
	while(obj.hasChildNodes()) {
		obj.removeChild(obj.childNodes[0]);
	}
	if(datos!="") {
		for (var k = 0; k < data.length; k=k+1) {
			obj.options[obj.length] = new Option(data[k].text,data[k].value);
		}
	}
}

function ocultarLinea(){
	
	for(var i = 1; i<=100;i++){
		 document.getElementById("linea"+i).style.display='none';
		 with(document.forms[0]) {
			 			
			 while(_IdEquipo[i].hasChildNodes()) {
				 _IdEquipo[i].removeChild(_IdEquipo[i].childNodes[0]);
				}
						_IdEquipo[i].value='';
						_MoneyLine[i].value=0;
						_IdEquipo[i].remove(0);
			}
	}
	
}

function vaciarCombos(items) {
	for(var i = 0; i>=items.length; i++){
		items.remove(i);
	}
}

function xmlTag(nombre,valor) {
	return ("<"+nombre+">"+valor+"<\/"+nombre+">\n");
}
