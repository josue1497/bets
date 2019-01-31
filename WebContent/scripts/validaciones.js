// elimina los espacios en blanco de ambos lados de la cadena
var sessionJugador = false;
var ie = false;
var ns = false;
if ( window.ActiveXObject ) {
	ie = true;
} else {
	ns = true;
}

String.prototype.trim = function() { return this.replace(/^\s*|\s*$/g,""); };
String.prototype.endsWith = function(str){return (this.match(str+"$")==str)};
String.prototype.startsWith = function(str){return (this.match("^"+str)==str)};

function trim(s) {
	if (typeof s!="undefined") {
		s = ""+s;
		return s.replace( /^\s*/, "" ).replace( /\s*$/, "" );
	} else {
		return "";
	}
}

// devuelve true si es una direccion valida de correo
function isEmail(email) {
	invalidChars = " ~\'^\`\"*+=\\|][(){}$&!#%/:,;";

	if (email==="") {
		return true;
	}

	for (i=0; i<invalidChars.length; i=i+1) {
		badChar = invalidChars.charAt(i);
		if (email.indexOf(badChar,0) > -1) {
			return false;
		}
	}
	lengthOfEmail = email.length;
	if ((email.charAt(lengthOfEmail - 1) == ".") || (email.charAt(lengthOfEmail - 2) == ".")) {
		return false;
	}
	Pos = email.indexOf("@",1);
	if (email.charAt(Pos + 1) == ".") {
		return false;
	}
	while ((Pos < lengthOfEmail) && ( Pos != -1)) {
		Pos = email.indexOf(".",Pos);
		if (email.charAt(Pos + 1) == ".") {
			return false;
		}
		if (Pos != -1) {
			Pos=Pos+1;
		}
	}
	atPos = email.indexOf("@",1);
	if (atPos == -1) {
		return false;
	}
	if (email.indexOf("@",atPos+1) != -1) {
		return false;
	}

	periodPos = email.indexOf(".",atPos);
	if (periodPos == -1) {
		return false;
	}
	if (periodPos+3 > email.length) {
		return false;
	}
	return true;
}

// envia un alert si el objeto esta vacio y retorna false
function vacio(valor) {
	return ( trim(valor)=="" );
}

function estaVacio(valor) {
	return ( trim(valor)=="" );
}

function estaVacioNumero(valor) {
	cad = limpiarNumero(valor);
	if ( (cad=="") || (eval(cad)<=0) ) return true;
	return false;
}


// envia un alert si el objeto esta vacio y retorna false
function empty(objeto,mensaje,minimo) {
	return ( trim(objeto.value)=="" );
}

function isEmpty(objeto,mensaje,minimo) {
	if ( trim(objeto.value)=="" ) {
		alert("Usted debe ingresar "+mensaje);
        objeto.focus();
		return true;
	}
	if ( trim(objeto.value).indexOf("'")!=-1 || trim(objeto.value).indexOf('"')!=-1 ) {
		alert("Las comillas no son un caracter valido para "+mensaje);
        objeto.focus();
		return true;
	}
    if (minimo) {
        if ( trim(objeto.value).length < minimo ) {
			alert(mensaje+" debe tener como minimo "+minimo+" caracteres");
	        objeto.focus();
			return true;
        }
    }
	return false;
}

// envia un alert si el objeto no es un entero y retorna false
function isEmptyInt(objeto,mensaje,minimo,rangoMinimo,rangoMaximo) {
	try {
		var cadena = limpiarNumeroInt(objeto.value);
		if ( trim(cadena)=="" || parseInt(cadena) ==0 || trim(cadena).length!=replaceAll(objeto.value,",","").length ) {
			alert("Usted debe ingresar un valor numerico en "+mensaje);
    	    objeto.focus();
			return true;
		}
		objeto.value = cadena;
	} catch (e) {
		alert("Usted debe ingresar un valor numerico en "+mensaje);
        objeto.focus();
		return true;
	}
    if (minimo) {
        if ( trim(objeto.value).length < minimo ) {
			alert(mensaje+" debe tener como minimo "+minimo+" digitos");
	        objeto.focus();
			return true;
        }
    }
    if (rangoMinimo) {
        if ( parseInt(objeto.value) < rangoMinimo ) {
			alert(mensaje+" debe ser mayor o igual que "+rangoMinimo);
	        objeto.focus();
			return true;
        }
    }
    if (rangoMaximo) {
        if ( parseInt(objeto.value) > rangoMaximo ) {
			alert(mensaje+" debe ser menor o igual que "+rangoMaximo);
	        objeto.focus();
			return true;
        }
    }
	return false;
}

// envia un alert si el objeto no es un entero y retorna false
function isEmptyDec(objeto,mensaje,minimo,rangoMinimo,rangoMaximo) {
	try {
		var cadena = limpiarNumero(objeto.value);
		if ( trim(cadena).length!=replaceAll(objeto.value,",","").length ) {
			alert("Usted debe ingresar un valor numerico en "+mensaje);
    	    objeto.focus();
			return true;
		}
		objeto.value = cadena;
	} catch (e) {
		alert("Usted debe ingresar un valor numerico en : "+mensaje);
        objeto.focus();
		return true;
	}
    if (minimo) {
        if ( trim(objeto.value).length < minimo ) {
			alert(mensaje+" debe tener como minimo "+minimo+" digitos");
	        objeto.focus();
			return true;
        }
    }
    if (rangoMinimo) {
        if ( parseInt(objeto.value) < rangoMinimo ) {
			alert(mensaje+" debe ser mayor o igual que "+rangoMinimo);
	        objeto.focus();
			return true;
        }
    }
    if (rangoMaximo) {
        if ( parseInt(objeto.value) > rangoMaximo ) {
			alert(mensaje+" debe ser menor o igual que "+rangoMaximo);
	        objeto.focus();
			return true;
        }
    }
	return false;
}


// envia un alert si el objeto no contiene una direcci�n valida de correo y retorna false
function isEmptyMail(objeto,mensaje) {
	if ( trim(objeto.value)=="" || !isEmail(objeto.value) ) {
		if ( !isEmail(objeto.value) ) {
			alert("Usted debe ingresar un correo valido para "+mensaje);
		} else {
			alert("Usted debe ingresar "+mensaje);
		}
        objeto.focus();
		return true;
	}
	return false;
}

function sumarCampo(campo,campo1) {
	return sumarValor(campo.value,campo1.value);
}
function sumarValor(valor,valor1) {
	n1 = limpiarNumero(valor);
	n2 = limpiarNumero(valor1);
	return (eval(n1)+eval(n2));
}

function limpiarNumero(valor) {
	if (trim(valor)=="") return "";
    var sep = ".";
    var sep2 = ","; //separador para quitar
    var cadena = "";
    var patron = "0123456789.";
    for(var k=0;k<valor.length;k=k+1) {
    	if ( patron.indexOf(valor.charAt(k))!=-1 ) {
    		cadena += valor.charAt(k);
    	}
    }
    valor = cadena;
	return valor;
}

function replaceAll(cadena,strOld,strNew) {
	if (trim(cadena)=="") return "";
    var _cuentaItem = 0;
    while(cadena.indexOf(strOld)!=-1 && _cuentaItem < 1000) {
    	_cuentaItem = _cuentaItem + 1;
    	cadena = cadena.replace(strOld,strNew);
    }
	return cadena;
}


function limpiarNumeroInt(valor) {
	if (trim(valor)=="") return "";
    var sep = ".";
    var sep2 = ","; //separador para quitar
    var cadena = "";
    var patron = "-0123456789";
    for(var k=0;k<valor.length;k=k+1) {
    	if ( patron.indexOf(valor.charAt(k))!=-1 ) {
    		cadena += valor.charAt(k);
    	}
    }
    valor = cadena;
	return valor;
}


//formatea un campo con separadores de miles
function formatearCampo(campo,n) {
	n = ( n == null ? 2 : n );
	campo.value = formatearValor(campo.value,n);
	campo.style.textAlign = "right";
}
function formatearValor2(valor,n) {
	return formatearValor(valor,n).replace(/,/g,"x").replace(/\./g,",").replace(/x/g,".");
}
//formatea un valor con separadores de miles
function formatearValor(valor,n) {
	n = ( n == null ? 2 : n );
    var sep = ".";
    var sep2 = ","; //separador para quitar
    var negativo = ( valor.length > 1 && valor.substring(0,1)=="-" ? true : false );

	valor = limpiarNumero(valor);
	valor = (vacio(valor) ? "0"+sep+"00" : valor);
	if ( valor.indexOf(sep) == -1 ) {
		valor = valor+sep+"00";
	}
    valor = valor.substr(0,valor.indexOf(sep)) + (valor+"0000000000000000000").substr(valor.indexOf(sep), n+1 );
    
    var cadena = valor.substr(0,valor.indexOf("."))
    var decimas = valor.substr(valor.indexOf("."));

    valor = cadena;

    var largo = valor.length
    var res = "";
    var cont = 0;
    var sep = "";
    for( var i = largo; i >= 0 ; i=i-1 ) {
      res = valor.substr(i,1) + sep + res;
      if ( cont == 3 ) {
	      cont = 0;
	      sep = ","
      } else {
		  sep = "";
      }
      cont=cont+1; 
    }
    valor = res+( n > 0 ? decimas : "" );
	if ( negativo == true ) {
		valor = "-"+valor;
	}
    return valor;
}

// valida la fecha de un campo de texto

function DateFormat(vDateName, vDateValue, e, dateCheck, dateType)  {   
    try {
    vDateType = dateType;
    while ( vDateName.value!="" && "0123456789/".indexOf(vDateName.value.substr((vDateValue.length-1)))==-1) {
        vDateName.value = vDateName.value.substr(0, (vDateValue.length-1));
    }
    if ( vDateName.value!="" && "0123456789/".indexOf(vDateName.value.substr((vDateValue.length-1)))==-1 ) {
	return false;
    }
    sep1="";
    sep2="";
    dd="";
    mm="";
    yy="";
    temp="";
    valor = vDateName.value;
    cont=0;
    isAno = 0;
    if ( valor.length>=5 ) {
	cad=valor.substring(valor.length-5);
    	isAno = ( cad.substring(0,1)=="/" ? 1 : 0);
    }
	//alert(isAno);
    while(valor!="" && valor.indexOf("/")!=-1) {
        valor = valor.replace("/","");
		cont=cont+1;
    }
    var key = (window.Event) ? e.which : e.keyCode;
    if ( ",8,36,37,39,46,".indexOf(","+key+",")==-1 && ( ((cont<2 && !isAno)||valor.length==8 ) || dateCheck) ) {
        for(var xx=0;  xx < valor.length; xx=xx+1) {
            letra=valor.charAt(xx);
            temp += letra + (xx==1 || xx==3 ?"/":"") ;
	    if ( xx==0 || xx==1 ) {
                dd+=letra;
	    } else if ( xx==2 || xx==3 ) {
                mm+=letra;
            } else {
                yy+=letra;
            }
        }
	if ( eval(yy) && yy!="" && eval(yy) < 1900 && yy.length<=3 && dateCheck) {
            n = eval(yy);
            if ( n >= 100 ) { 
		yy = n+1000;
            } else {
                yy = ( n <= 35 ? 2000+n : 1900+n);
            }
        temp = dd+"/"+mm+"/"+yy;
        }
        vDateName.value = temp;
    }
    if(dateCheck) {
      if (!dateValid(vDateName.value)) {
         //alert("Fecha no v�lida\nIntroduzcala de nuevo "+err);
         vDateName.value = "";
         alert("Fecha no v�lida\nIntroduzcala nuevamente ");
         vDateName.focus();
         vDateName.select();
         return false;
      }
      return true;
   }
   } catch(e) {
       alert("El formato de fecha es dd/mm/yyyy ejemplo: 01/02/2006");
       vDateName.value = "";
   }

}

function dateValid(objName) {
      var strDate;
      var strDateArray;
      var strDay;
      var strMonth;
      var strYear;
      var intday;
      var intMonth;
      var intYear;
      var booFound = false;
      var datefield = objName;
      var strSeparatorArray = new Array("-"," ","/",".");
      var intElementNr;
      // var err = 0;
      var strMonthArray = new Array(12);
      strMonthArray[0] = "Jan";
      strMonthArray[1] = "Feb";
      strMonthArray[2] = "Mar";
      strMonthArray[3] = "Apr";
      strMonthArray[4] = "May";
      strMonthArray[5] = "Jun";
      strMonthArray[6] = "Jul";
      strMonthArray[7] = "Aug";
      strMonthArray[8] = "Sep";
      strMonthArray[9] = "Oct";
      strMonthArray[10] = "Nov";
      strMonthArray[11] = "Dec";

      //strDate = datefield.value;
      strDate = objName;

      if (strDate.length < 1) {
         return true;
      }
      for (intElementNr = 0; intElementNr < strSeparatorArray.length; intElementNr++) {
         if (strDate.indexOf(strSeparatorArray[intElementNr]) != -1)
         {
            strDateArray = strDate.split(strSeparatorArray[intElementNr]);
            if (strDateArray.length != 3)
            {
               err = 1;
               return false;
            }
            else
            {
               strDay = strDateArray[0];
               strMonth = strDateArray[1];
               strYear = strDateArray[2];
            }
            booFound = true;
         }
      }
      if (booFound == false) {
         if (strDate.length>5) {
            strDay = strDate.substr(0, 2);
            strMonth = strDate.substr(2, 2);
            strYear = strDate.substr(4);
         }
      }
      //Adjustment for short years entered
      if (strYear.length == 2) {
         strYear = '20' + strYear;
      }
      //strTemp = strDay;
      //strDay = strMonth;
      //strMonth = strTemp;
      intday = parseInt(strDay, 10);
      if (isNaN(intday)) {
         err = 2;
         return false;
      }

      intMonth = parseInt(strMonth, 10);
      if (isNaN(intMonth)) {
         for (i = 0;i<12;i=i+1) {
            if (strMonth.toUpperCase() == strMonthArray[i].toUpperCase()) {
               intMonth = i+1;
               strMonth = strMonthArray[i];
               i = 12;
            }
         }
         if (isNaN(intMonth)) {
            err = 3;
            return false;
         }
      }
      intYear = parseInt(strYear, 10);
      if (isNaN(intYear)) {
         err = 4;
         return false;
      }
      if (intMonth>12 || intMonth<1) {
         err = 5;
         return false;
      }
      if ((intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12) && (intday > 31 || intday < 1)) {
         err = 6;
         return false;
      }
      if ((intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) && (intday > 30 || intday < 1)) {
         err = 7;
         return false;
      }
      if (intMonth == 2) {
         if (intday < 1) {
            err = 8;
            return false;
         }
         if (LeapYear(intYear) == true) {
            if (intday > 29) {
               err = 9;
               return false;
            }
         }
         else {
            if (intday > 28) {
               err = 10;
               return false;
            }
         }
      }
         return true;
}

function LeapYear(intYear) {
      if (intYear % 100 == 0) {
         if (intYear % 400 == 0) { return true; }
      }
      else {
         if ((intYear % 4) == 0) { return true; }
      }
         return false;
}

// redondea un numero segun el parametro

function round(valor,n) {
	n = ( !n ? 0 : n );
	cad = ""+valor;
	if ( cad.indexOf(".")==-1 && n == 0 ) {
		if ( cad.indexOf(".")!=-1 ) {
			cad = cad.substring(0,cad.indexOf("."));
			valor = eval(cad);
		}
		return valor;
	}

	var p = 0;
	var decimal = cad.indexOf(".");
	var k = "0000000000000000000000000000";

	p = (decimal>0?decimal+n:cad.length+n);

	cad = cad.replace(".","");

	k = "1"+k.substring(0,cad.length-p);

	if ( k.length > 1 ) {
		num = cad;

		resto = cad.substring(cad.length-(cad.length-p));
		resto = (resto==""?"0":resto);

		falta = eval(k) - eval(resto);

		if ( eval(resto) < eval(falta) ) {
			falta = "-"+resto;
		}

		valor = eval(num)+eval(falta);

		cad = ""+valor;

		if ( decimal > 0 ) {
			dec = cad.substring(decimal,decimal+n);
			cad = cad.substring(0,decimal)
			if ( dec.length > 0 ) {
				cad += "."+dec;
			}
		}

		//imp("decimal= "+decimal);
		//imp("p= "+p);
		//imp("k= "+k);
		//imp("num= "+num);
		//imp("resto= "+resto);
		//imp("falta= "+falta);
	} else {
		cad = ""+valor;
	}

	return eval(cad);
}

function nextFocusExecute(field) {
	// debe ser sobreescrita en el codigo
}
// desplaza el focus al siguiente elemento activo en la forma
function nextFocus(field,event) {
	var next=0, found=false;
	var f=field.form;

	if(event.keyCode!=13) return;

	nextFocusExecute(field);
	
	for(var i=0;i<f.length;i=i+1)	{
		if(field==f[i]){
			next=i+1;
			found=true
			break;
		}
	}
	
	while(found){
		try {
			if( f[next].disabled==false &&  f[next].type!='hidden' && f[next].style.display=="" ){
				try {
					f[next].focus();
					break;
				} catch(e) {
					if(next<f.length-1)
						next=next+1;
					else
						break;
				}
			} else {
				if(next<f.length-1)
					next=next+1;
				else
					break;
			}
		} catch(ex) {
			// el objeto no acepta las propiedades
			break;
		}
	}
	
}


function addFlash(ruta,width,height)
{
    document.writeln("<object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0\" width=\"" + width + "\" height=\"" + height + "\">");
    document.writeln("<param name=\"movie\" value=\"" + ruta + "\" />");
    document.writeln("<param name=\"quality\" value=\"high\" />");
    document.writeln("<embed src=\"" + ruta + "\" quality=\"high\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" type=\"application/x-shockwave-flash\" width=\"" + width + "\" height=\"" + height + "\"></embed>");
    document.writeln("</object>");
}

function getFechaSis() {
	var fechaSis=new Date(); 
	var DIA=(fechaSis.getDate()<10?"0":"")+fechaSis.getDate();
	var MES=(fechaSis.getMonth()<10?"0":"")+fechaSis.getMonth();
	var ANO= ( fechaSis.getYear() > 1900 ? fechaSis.getYear() : 1900+fechaSis.getYear());;
	return (DIA+"/"+MES+"/"+ANO);
}

function reporte(id,tit) {
	window.open("listLogroReport.do?idDeporte="+id+"&titulo="+tit,"_blank","width=800,height=600,status=no,resizable=yes,top=50,left=100,scrollbars=yes");
}
function reportePorCentro(id,tit,isDay,isLiga) {
	window.open("listLogroReportPorCentro.do?idDeporte="+id+"&titulo="+tit+"&isDay="+isDay+"&isLiga="+isLiga,"_blank","width=800,height=600,status=no,resizable=yes,top=50,left=100,scrollbars=yes");
}

function reportePorCentroLiga(id,desc) {
	window.open("liga.do?idDeporte="+id+"&descDeporte="+desc,"_blank","width=800,height=600,status=no,resizable=yes,top=50,left=100,scrollbars=yes");
}

function ventanaLogros() {
	window.open("listLogrosDefault.do","_blank","width=900,height=600,status=no,resizable=no,top=50,left=100,scrollbars=no");
}

function addEventForInput() {
	var inputs = document.getElementsByTagName("input");
	for (var i=0; i<inputs.length; i++){
		// test to see if the help span exists first
		if (inputs[i].parentNode.getElementsByTagName("span")[0]) {
			// the span exists!  on focus, show the help
			inputs[i].onfocus = function () {
				this.parentNode.getElementsByTagName("span")[0].style.display = "inline";
			}
			// when the cursor moves away from the field, hide the help
			inputs[i].onblur = function () {
				this.parentNode.getElementsByTagName("span")[0].style.display = "none";
			}
		}
	}
	// repeat the same tests as above for selects
	var selects = document.getElementsByTagName("select");
	for (var k=0; k<selects.length; k++){
		if (selects[k].parentNode.getElementsByTagName("span")[0]) {
			selects[k].onfocus = function () {
				this.parentNode.getElementsByTagName("span")[0].style.display = "inline";
			}
			selects[k].onblur = function () {
				this.parentNode.getElementsByTagName("span")[0].style.display = "none";
			}
		}
	}
	var textareas = document.getElementsByTagName("textarea");
	for (var i=0; i<textareas.length; i++){
		// test to see if the help span exists first
		if (textareas[i].parentNode.getElementsByTagName("span")[0]) {
			// the span exists!  on focus, show the help
			textareas[i].onfocus = function () {
				this.parentNode.getElementsByTagName("span")[0].style.display = "inline";
			}
			// when the cursor moves away from the field, hide the help
			textareas[i].onblur = function () {
				this.parentNode.getElementsByTagName("span")[0].style.display = "none";
			}
		}
	}
}

function setCookie(nombre,valor,tiempo){
	document.cookie=nombre+"="+valor+"; expires="+tiempo;
}
function getCookie(nombre) {
	var a;
	if(document.cookie.indexOf(nombre)!=-1){
		a = document.cookie.substring(document.cookie.indexOf( nombre + '=') + nombre.length + 1,document.cookie.length);
		if(a.indexOf(';') != -1)a = a.substring(0,a.indexOf(';'))
	}
	return a;
} 


function arrayFind(needle, haystack, argStrict) {
    var strict = !!argStrict;
    var key = '';
    for (key in haystack) {
        if ((strict && haystack[key] === needle) || (!strict && haystack[key] == needle)) {
            return key;
        }
    }
    return false;
} 

function findArray(arr,valor) {
	if(!valor || !arr) return -1;
	
	for(var i=0; i<arr.length; i++) {
		if(arr[i] == valor) {
			return i;
		}
	}
	return -1;
}

function combinacion(id,tit) {
	window.open("listDeporteCombinacion.do?idUsuario="+id+"&titulo="+tit,"_blank","width=1000,height=400,status=no,resizable=yes,top=100,left=100,scrollbars=yes");
}

function getMacAddress() {
	macAddress="";
	try {
      var locator = new ActiveXObject ("WbemScripting.SWbemLocator");
      var service = locator.ConnectServer(".");
      var properties = service.ExecQuery("SELECT * FROM Win32_NetworkAdapter");
      var e = new Enumerator (properties);
      var sep="";
      var notValid1 = "Adaptador as�nc. de RAS";
      var notValid2 = "Minipuerto del administrador de paquetes";
      var notValid3 = "Deterministic Network Enhancer Miniport";
      for (;!e.atEnd();e.moveNext ()) {
            var p = e.item ();
            if(p.MACAddress!=null && p.Caption.indexOf(notValid1)==-1 && p.Caption.indexOf(notValid2)==-1 && p.Caption.indexOf(notValid3)==-1) {
	            macAddress+=sep+p.MACAddress;
	            sep="|";
	         }
	  }
	} catch(e) {}
	return macAddress;
}
