<!-- listLogroReport.jsp -->
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>

<%@ include file="/views/common/taglibs.jsp"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>

<%@ include file="/views/common/meta.jsp"%>
<% CachedRowSet listaStatus = (CachedRowSet)request.getAttribute("listaStatus"); %>
<% CachedRowSet listaDeporte = (CachedRowSet)request.getAttribute("listaDeporte"); 
HashMap cambiar = new HashMap();
boolean entrar = false;
String color1="ffffff";
String color2="efefef";  
String color=color2;
boolean isPitcher=true;

int contador=0;
int mitad=0;
boolean isMitad=false;
boolean entro=false;
%> 

<style>
.logrosRed{
	font-size:10px;
	color:red;
}
.borderA {
	border-bottom:0px;
}
.borderEmpate {
	border-top:0px;
}
.borderEmpateGray {
	border-top:1px solid gray;
	border-bottom:0px solid gray;
}
.borderEmpateVacio {
	border-top:0px;
	border-bottom:0px;
}

.borderB {
	/*border-top:1px dotted;*/
	border-top:0px solid gray;
}
.colorLogro5to {
	font-size:9px;
	color:#0000ff;
}
.tit {
	font-family:Tahoma;
	font-size:9px;
	color:#000;
	font-weight:bold;
	text-align:center;
	background-color:#A9E2F3;
	/*background:url(images/fondoTitulo.jpg);*/
}
.det {
	font-family:Tahoma;
	font-size:8px;
	color:#000000;
	/*font-weight:bold;*/
	text-align:center;

}
.final {
	font-family:Tahoma;
	font-size:10px;
	color:#000000;
	/*font-weight:bold;*/
	text-align:center;
}
.cinco {
	background-color:#dfdfdf;
}

</style>
<table align="center" width="98%" cellspacing="0" cellpadding="0" border="0" >
	<tr>
		<%if(request.getAttribute("centro")!=null) {%>
		<td rowspan="2" align="center">
			<img src="<%=Constants.getDominio(request).getPathImages()%><%=request.getAttribute("titulo").toString().toLowerCase().replaceAll(" ","_")%>.gif">
		</td>
		<%}%>
		<td class="tituloTablaBig">
			<%if(request.getAttribute("centro")!=null) {%>
				<%=request.getAttribute("centro")%>
			<%} else {%>
				<img src="<%=Constants.getDominio(request).getPathImages()%>logoLogro.jpg"/>
			<%}%>
		</td>
		<%if(request.getAttribute("centro")!=null) {%>
		<td rowspan="2" align="center">
			<img src="<%=Constants.getDominio(request).getPathImages()%><%=request.getAttribute("titulo").toString().toLowerCase().replaceAll(" ","_")%>.gif">
		</td>
		<%}%>
	</tr>
	<tr>
		<td class="tituloTablaBig">
			<%=request.getAttribute("titulo")%>
		</td>
	</tr>
</table>
<table width="100%" border="0">
	<tr>
		<td id="COL_0" valign="top" width="50%">
		</td>
		<td id="COL_1" valign="top" width="50%">
		</td>
	</tr>
</table>
	<logic:present name="lista" scope="request">
	<% CachedRowSet lista = (CachedRowSet)request.getAttribute("lista");  
		if(lista.size()>=3){  
		   	mitad=(lista.size()/3);
		   	mitad=mitad>1?(mitad/2):mitad;
	    	mitad=mitad*3;
	    }%>
<table id="TABLA_0" align="center" width="98%" cellspacing="0" cellpadding="0" border="1" BORDERCOLOR="#000" >
	<%String idJuego = null;
	String titulo = null;
	String uo = Constants.ALTA;
	String estilo="";
	boolean subtitulo = false;
	boolean isEmpate = false;
	SimpleDateFormat df = new SimpleDateFormat("hh:mmaa");
	while (lista.next()) {	
		
		Date date = df.parse(lista.getString("hora"));
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		if (Constants.getDominio(request).getMoneda().equalsIgnoreCase("Pesos.")) {
			cal.add(Calendar.MINUTE, -30);
			date = cal.getTime();
		}
		String hora = df.format(date);
	
		
		isMitad=lista.getRow()>mitad;
		if(isMitad && !entro) {
			titulo=null;%>
			</table>
			<table id="TABLA_1" align="center" width="98%" cellspacing="0" cellpadding="0" border="1" BORDERCOLOR="#000" >
		<%}
		subtitulo = false;
		estilo="borderWhite";
		if(idJuego==null || !idJuego.equals(lista.getString("id_juego"))) {
			uo=Constants.ALTA;
			idJuego=lista.getString("id_juego");
			
			if(titulo==null || !titulo.equals(lista.getString("dia").concat(lista.getString("desc_liga")))) {
				subtitulo = true;
			%>
				<tr> 
					<td class="tit" colspan="9">
						&nbsp;<%=lista.getString("desc_liga")%> (<%=lista.getString("iniciales")%>) - <%=lista.getString("dia")%>
					</td>
				</tr>
			<%} else {
			estilo="borderTopBlackReport";
				%>
			<%}
			titulo = lista.getString("dia").concat(lista.getString("desc_liga"));
	}%>
		<%if(subtitulo && uo.equals(Constants.ALTA) || isMitad && !entro){
			entro=isMitad;
			isPitcher = lista.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL) || lista.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL_5);
		%>
			<tr >
				<td class="tit" rowspan="2">Hora</td>
				<td class="tit" rowspan="2">Equipo</td>
				<td class="tit" colspan="2">Moneyline</td>
				<td class="tit" colspan="3">Runline/Spread</td>
				<td class="tit" colspan="2">Alta o Baja</td>
			</tr>
			<tr >
				<td class="tit">Ref</td>
				<td class="tit">Logro</td>
				<td class="tit">Ref</td>
				<td class="tit">Factor</td>
				<td class="tit">Logro</td>
				<td class="tit">Ref</td>
				<td class="tit">Logro</td>
			</tr>
			
		<%color=color2;}%>
		<%if(uo.equals(Constants.ALTA)){
			color=(color.equals(color2)?color1:color2);
		}%>
			<tr  bgcolor="<%=color%>" class="det"> <!--class="detalleTablaCenterBW"> -->
				<%if(uo.equals(Constants.ALTA)){%>
				<td width="4%" <%=uo.equals(Constants.BAJA)?
						(lista.getString("status_real").equals("Abierto/Taq.")?"class='yellow'":(lista.getString("status_real").startsWith("Totalizado")?"class='blue'":(lista.getString("status_real").startsWith("Borrador")?"class='green'":(lista.getString("status_real").startsWith("Abierto/Bloq.")?"class='agua'":"class='red'"))))
						:""%> rowspan="<%=lista.getString("id_deporte").equals("1")?"3":"2"%>">
					<%=(uo.equals(Constants.ALTA)?hora:(uo.equals(Constants.BAJA)?"":""))%>
				</td>
				<%} else if(!uo.equals(Constants.BAJA) && !lista.getString("id_deporte").equals("1") ) {%>
					<td>&nbsp;</td>
				<%}%>
				<%isEmpate = lista.getString("id_equipo").equals(Constants.ID_EQUIPO_EMPATE);%>
				<td class="border<%=uo%> <%=isEmpate?"borderEmpate":(uo.equals(Constants.BAJA)?"borderEmpateGray":"")%>" align="left">
					<%=lista.getString("desc_equipo")%><%=isPitcher?"</br>":""%><i><%=Constants.isNull(lista.getString("nombre_lanzador"),"","")%></i>
				</td>
				<td class="border<%=uo%> <%=isEmpate?"borderEmpate":(uo.equals(Constants.BAJA)?"borderEmpateGray":"")%>"><b><%=lista.getString("referencia")%></b></td>
				<td class="border<%=uo%> printLogro <%=isEmpate?"borderEmpate":(uo.equals(Constants.BAJA)?"borderEmpateGray":"")%>"><%=Constants.signo(lista.getString("money_line"))%></td>
				<td class="border<%=uo%> <%=isEmpate?"borderEmpate":(uo.equals(Constants.BAJA)?"borderEmpateGray":"")%>"><b>&nbsp;
				<%entrar=((!Constants.tipoFraccion(lista.getString("spread")).equals("") || !Constants.signo(lista.getString("spread_logro")).equals("")) && !Constants.signo(lista.getString("spread_logro")).equals("0"));
					if(entrar){%>
						<%=lista.getString("referencia_runline")%>
					<%}%>
					</b>
				</td>
				
				<%if(!lista.getString("id_equipo").equals(Constants.ID_EQUIPO_EMPATE)) {%>
					<td class="border<%=uo%> printLogro <%=isEmpate?"borderEmpate":(uo.equals(Constants.BAJA)?"borderEmpateGray":"")%>">
						&nbsp;
						<%if(entrar){%>
						<%=Constants.tipoFraccion(lista.getString("spread")).equals("")?"0":Constants.tipoFraccion(lista.getString("spread"))%>
						<%}%>
					</td>
					<td class="border<%=uo%> printLogro <%=isEmpate?"borderEmpate":(uo.equals(Constants.BAJA)?"borderEmpateGray":"")%>">&nbsp;
						<%if(entrar){%>
						<%=Constants.signo(lista.getString("spread_logro"))%>
						<%}%>
					</td>
					<%if(uo.equals(Constants.ALTA)){%>
						<td colspan="2" rowspan="2" align="center" style="border-bottom:0px;" valign="bottom">
							&nbsp;
							<%entrar=(!Constants.tipoFraccion(lista.getString("total"),false).equals("") && !Constants.signo(lista.getString("total_logro")).equals("0"));
							if(entrar){%>
								<b><%=Constants.tipoFraccion(lista.getString("total"),false)%></b>
								</br><b><%=lista.getString("referencia_ab")%>:</b><span class="printLogro"><%=Constants.signo(lista.getString("total_logro"))%></span>
							<%}%><br>
							<span id="BAJA_<%=++contador%>"></span>
						</td>
					<%} else if(uo.equals(Constants.BAJA)) {%>
						<span id="TXT_BAJA_<%=contador%>" style="display:none;" >
							&nbsp;
							<%entrar=(!Constants.tipoFraccion(lista.getString("total"),false).equals("") && !Constants.signo(lista.getString("total_logro")).equals("0"));
							if(entrar){%>
								<b><%=lista.getString("referencia_ab")%>:</b><span class="printLogro"><%=Constants.signo(lista.getString("total_logro"))%></span>
							<%}%>
						</span>
					<%} else if(!uo.equals(Constants.BAJA)) {%>
						<td >&nbsp;</td>
					<%}%>

				<%} else {%>
					<td class="<%=isEmpate?"borderEmpate":(uo.equals(Constants.BAJA)?"borderEmpateGray":"")%>">&nbsp;</td>
					<td class="<%=isEmpate?"borderEmpate":(uo.equals(Constants.BAJA)?"borderEmpateGray":"")%>">&nbsp; </td>
					<td colspan="2" class="<%=isEmpate?"borderEmpate":(uo.equals(Constants.BAJA)?"borderEmpateGray":"")%>">&nbsp;</td>
				<%}%>
			</tr>	
			<%uo=(uo.equals(Constants.ALTA)?Constants.BAJA:(uo.equals(Constants.BAJA)?"":""));
		}%> 
	</table>
	</logic:present>
<center>
<span class="final">
<%=request.getAttribute("PIE_PAGINA_LOGRO")%>
</span>
</center>
<hr width="98%" color="#efefef"/>
<script>

for(var i=1;i<500;i++) {
	if(document.getElementById('TXT_BAJA_'+i)) {
		document.getElementById('BAJA_'+i).innerHTML=document.getElementById('TXT_BAJA_'+i).innerHTML;
		//document.getElementById('BAJA_'+i).innerHTML=document.getElementById('TXT_BAJA_'+i).innerHTML;
		//alert(document.getElementById('TXT_BAJA_'+i));
		//document.getElementById('TXT_BAJA_'+i).innerHTML="";
	} else {
		break;
	}
}
if(document.getElementById("TABLA_0")) {
	document.getElementById('COL_0').appendChild(document.getElementById('TABLA_0'))
}
if(document.getElementById("TABLA_1")) {
	document.getElementById('COL_1').appendChild(document.getElementById('TABLA_1'))
}

</script>

