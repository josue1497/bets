<!-- listLogroReport.jsp -->
<%@ include file="/views/common/taglibs.jsp"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>

<%@ include file="/views/common/meta.jsp"%>
<% CachedRowSet listaStatus = (CachedRowSet)request.getAttribute("listaStatus"); %>
<% CachedRowSet listaDeporte = (CachedRowSet)request.getAttribute("listaDeporte"); 
boolean isActiveSRL=false;
if(usuario!=null){
	isActiveSRL = usuario.getActivarSuperRunline().equals("1");
}

HashMap cambiar = new HashMap();
boolean entrar = false;
String color1="ffffff";
String color2="efefef";
String color=color2;
boolean isPitcher=true;%> 

<style>
.logrosRed{
	font-size:10px;
	color:red;
}
.borderA {
	border-bottom:0px;
}
.borderB {
	/*border-top:1px dotted;*/
	border-top:1px solid gray;
}
.colorLogro5to {
	font-size:9px;
	color:#0000ff;
}
.tit {
	font-family:Tahoma;
	font-size:9px;
	color:#ffffff;
	font-weight:bold;
	text-align:center;
	background-color:#0000CD;
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
			* * * <%=request.getAttribute("titulo")%> * * *
		</td>
	</tr>
</table>
<table align="center" width="98%" cellspacing="0" cellpadding="0" border="1" BORDERCOLOR="#000" >
	<logic:present name="lista" scope="request">
	<% CachedRowSet lista = (CachedRowSet)request.getAttribute("lista");  
	String idJuego = null;
	String titulo = null;
	String uo = Constants.ALTA;
	String estilo="";
	boolean subtitulo = false;
	while(lista.next()) {
		subtitulo = false;
		estilo="borderWhite";
		if(idJuego==null || !idJuego.equals(lista.getString("id_juego"))) {
			uo=Constants.ALTA;
			idJuego=lista.getString("id_juego");
			
			if(titulo==null || !titulo.equals(lista.getString("dia").concat(lista.getString("desc_liga")))) {
				subtitulo = true;
			%>
				<tr> 
					<td class="tit" colspan="<%=isActiveSRL?21:18%>">
						&nbsp;<%=lista.getString("desc_liga")%> (<%=lista.getString("iniciales")%>) - <%=lista.getString("dia")%>
					</td>
				</tr>
			<%} else {
			estilo="borderTopBlackReport";
				%>
			<%}
			titulo = lista.getString("dia").concat(lista.getString("desc_liga"));
	}%>
		<%if(subtitulo && uo.equals(Constants.ALTA)){
			isPitcher = lista.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL) || lista.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL_5);
		%>
			<tr >
				<td class="tit" rowspan="3">Hora</td>
				<td class="tit" rowspan="3">Equipo</td>
				<td class="tit" colspan="4">Moneyline</td>
				<td class="tit" colspan="6">Runline/Spread</td>
				<%if(isActiveSRL){%>
				<td class="tit" colspan="3">Super Runline</td>
				<%}%>
				<td class="tit" colspan="4">Alta o Baja</td>
				<td class="tit" colspan="2">Si y No</td>
			</tr>
			<tr >
				<td class="tit" colspan="2">FINAL</td>
				<td class="tit" colspan="2">5 INNINGS</td>
				<td class="tit" colspan="3">FINAL</td>
				<td class="tit" colspan="3">5 INNINGS</td>
				<%if(isActiveSRL){%>
				<td class="tit" colspan="3">FINAL</td>
				<%}%>
				<td class="tit" colspan="2">FINAL</td>
				<td class="tit" colspan="2">5 INNINGS</td>
				<td class="tit" colspan="2">1er.INN.</td>
			</tr>
			<tr >
				<td class="tit">Ref</td>
				<td class="tit">Logro</td>
				<td class="tit">Ref</td>
				<td class="tit">Logro</td>
				<td class="tit">Ref</td>
				<td class="tit">Factor</td>
				<td class="tit">Logro</td>
				<td class="tit">Ref</td>
				<td class="tit">Factor</td>
				<td class="tit">Logro</td>
				<%if(isActiveSRL){%>
				<td class="tit">Ref</td>
				<td class="tit">Factor</td>
				<td class="tit">Logro</td>
				<%}%>
				<td class="tit">Ref</td>
				<td class="tit">Logro</td>
				<td class="tit">Ref</td>
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
					<%=(uo.equals(Constants.ALTA)?lista.getString("hora"):(uo.equals(Constants.BAJA)?"":""))%>
				</td>
				<%} else if(!uo.equals(Constants.BAJA) && !lista.getString("id_deporte").equals("1") ) {%>
					<td>&nbsp;</td>
				<%}%>
				<td class="border<%=uo%>" align="left">
					<%=lista.getString("desc_equipo")%><%=isPitcher?"</br>":""%><i><%=Constants.isNull(lista.getString("nombre_lanzador"),"","")%></i>
				</td>
				<td class="border<%=uo%>"><b><%=lista.getString("referencia")%></b></td>
				<td class="border<%=uo%> printLogro"><%=Constants.signo(lista.getString("money_line"))%></td>
				<td id="A_<%=uo+idJuego%>" class="border<%=uo%> cinco">&nbsp;</td>
				<td id="B_<%=uo+idJuego%>" class="border<%=uo%> colorLogro5to  cinco">&nbsp;</td>
				<td class="border<%=uo%>"><b>&nbsp;
				<%entrar=((!Constants.tipoFraccion(lista.getString("spread")).equals("") || !Constants.signo(lista.getString("spread_logro")).equals("")) && !Constants.signo(lista.getString("spread_logro")).equals("0"));
					if(entrar){%>
						<%=lista.getString("referencia_runline")%>
					<%}%>
					</b>
				</td>
				
				<%if(!lista.getString("id_equipo").equals(Constants.ID_EQUIPO_EMPATE)) {%>
					<td class="border<%=uo%> printLogro">
						&nbsp;
						<%if(entrar){%>
						<%=Constants.tipoFraccion(lista.getString("spread")).equals("")?"0":Constants.tipoFraccion(lista.getString("spread"))%>
						<%}%>
					</td>
					<td class="border<%=uo%> printLogro">&nbsp;
						<%if(entrar){%>
						<%=Constants.signo(lista.getString("spread_logro"))%>
						<%}%>
					</td>
					<td id="C_<%=uo+idJuego%>" class="border<%=uo%> cinco">&nbsp;</td>
					<td id="D_<%=uo+idJuego%>" class="border<%=uo%> colorLogro5to cinco">&nbsp;</td> 
					<td id="E_<%=uo+idJuego%>" class="border<%=uo%> printLogro cinco">&nbsp;</td>

					<%if(isActiveSRL){%>
					<!--Super runline-->
					<td class="border<%=uo%>"><b>
					<%entrar=((!Constants.tipoFraccion(lista.getString("super_spread")).equals("") || !Constants.signo(lista.getString("super_spread_logro")).equals("")) && !Constants.signo(lista.getString("super_spread_logro")).equals("0"));
						if(entrar){%>
							<%=lista.getString("referencia_super_runline")%>
						<%} else {out.print("&nbsp;");}%>
						</b>
					</td>
					<td class="border<%=uo%> printLogro">
						<%if(entrar){%>
						<%=Constants.tipoFraccion(lista.getString("super_spread")).equals("")?"0":Constants.tipoFraccion(lista.getString("super_spread"))%>
						<%} else {out.print("&nbsp;");}%>
					</td>
					<td class="border<%=uo%> printLogro">&nbsp;
						<%if(entrar){%>
						<%=Constants.signo(lista.getString("super_spread_logro"))%>
						<%} else {out.print("&nbsp;");}%>
					</td>
					<%}%>
					
					<%if(uo.equals(Constants.ALTA)){%>
						<td colspan="2" align="center" style="border-bottom:0px;" valign="bottom">
							&nbsp;
							<%entrar=(!Constants.tipoFraccion(lista.getString("total"),false).equals("") && !Constants.signo(lista.getString("total_logro")).equals("0"));
							if(entrar){%>
								<b><%=Constants.tipoFraccion(lista.getString("total"),false)%></b>
								</br><b><%=lista.getString("referencia_ab")%>:</b><span class="printLogro"><%=Constants.signo(lista.getString("total_logro"))%></span>
							<%}%>
						</td>
					<%} else if(uo.equals(Constants.BAJA)) {%>
						<td colspan="2" style="border-top:0px;" valign="top">
							&nbsp;
							<%entrar=(!Constants.tipoFraccion(lista.getString("total"),false).equals("") && !Constants.signo(lista.getString("total_logro")).equals("0"));
							if(entrar){%>
								<b><%=lista.getString("referencia_ab")%>:</b><span class="printLogro"><%=Constants.signo(lista.getString("total_logro"))%></span>
							<%}%>
						</td>
					<%} else if(!uo.equals(Constants.BAJA)) {%>
						<td >&nbsp;</td>
					<%}%>

					<%if(uo.equals(Constants.ALTA)){%>
						<td id="F_<%=uo+idJuego%>" colspan="2" class="cinco" align="center" style="border-bottom:0px;" valign="bottom">&nbsp;</td>
					<%} else if(uo.equals(Constants.BAJA)) {%>
						<td id="G_<%=uo+idJuego%>" colspan="2" class="cinco" style="border-top:0px;" valign="top">&nbsp;</td>
					<%} else if(!uo.equals(Constants.BAJA)) {%>
						<td>&nbsp;</td>
					<%}%>
					<td id="H_<%=uo+idJuego%>" style="<%=uo.equals(Constants.BAJA)?"border-top:0px;vertical-align:top;":"border-bottom:0px;vertical-align:bottom;"%>">&nbsp;</td>
					<td id="I_<%=uo+idJuego%>" style="<%=uo.equals(Constants.BAJA)?"border-top:0px;vertical-align:top;":"border-bottom:0px;vertical-align:bottom;"%>">&nbsp;</td>
				<%} else {%>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td colspan="2">&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<%if(isActiveSRL){%>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<%}%>
				<%}%>
			</tr>	
			<%uo=(uo.equals(Constants.ALTA)?Constants.BAJA:(uo.equals(Constants.BAJA)?"":""));
		}%> 
	</logic:present>
	
	
<!-- 5to inning -->	

	<logic:present name="lista5to" scope="request">
	<table style="display:none">
	<% CachedRowSet lista = (CachedRowSet)request.getAttribute("lista5to");  
	String idJuego = null;
	String titulo = null;
	String uo = Constants.ALTA;
	String estilo="";
	boolean subtitulo = false;
	String idColumna = null;
	while(lista.next()) {
		subtitulo = false;
		estilo="borderWhite";
		if(idJuego==null || !idJuego.equals(lista.getString("id_juego"))) {
			uo=Constants.ALTA;
			idJuego=lista.getString("id_juego");
			
			if(titulo==null || !titulo.equals(lista.getString("dia").concat(lista.getString("desc_liga")))) {
				subtitulo = true;
			%>
			<%} else {
			estilo="borderTopBlackReport";
				%>
			<%}
			titulo = String.valueOf(lista.getString("dia")).concat(String.valueOf(lista.getString("desc_liga")));
		}
		idColumna=uo.concat(String.valueOf(lista.getString("id_juego_padre")));
		%>
		<%if(subtitulo && uo.equals(Constants.ALTA)){
			isPitcher = lista.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL) || lista.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL_5);
			%>

			<%color=color2;
		}%>
			<tr  bgcolor="<%=color%>" class="det"><!--class="detalleTablaCenterBW"> -->
			<!-- PADRE:<%=lista.getString("id_juego_padre")%> -->
				<%if(uo.equals(Constants.ALTA)){%>
				<%} else if(!uo.equals(Constants.BAJA) && !lista.getString("id_deporte").equals("1") ) {%>
				<%}%>
				<%cambiar.put("A_"+idColumna,"5A_"+idColumna);%>
				<td id="<%="5A_".concat(idColumna)%>" ><b><%=lista.getString("referencia")%></b></td>
				
				<%cambiar.put("B_".concat(idColumna),"5B_".concat(idColumna));%>
				<td id="<%="5B_".concat(idColumna)%>" ><%=Constants.signo(lista.getString("money_line"))%></td>
				
				
				<td id="<%="5C_".concat(idColumna)%>"><b><%=lista.getString("referencia_runline")%></b></td>
				
				<%if(!lista.getString("id_equipo").equals(Constants.ID_EQUIPO_EMPATE)) {%>
					<%cambiar.put("D_"+idColumna,"5D_"+idColumna);%>
					<td id="<%="5D_".concat(idColumna)%>">
						&nbsp;
						<%entrar=((!Constants.tipoFraccion(lista.getString("spread")).equals("") || !Constants.signo(lista.getString("spread_logro")).equals("")) && !Constants.signo(lista.getString("spread_logro")).equals("0"));
						if(entrar){%>
							<%cambiar.put("C_"+idColumna,"5C_"+idColumna);%>
							<%=Constants.tipoFraccion(lista.getString("spread")).equals("")?"0":Constants.tipoFraccion(lista.getString("spread"))%>
						<%}%>
					</td>

					<%cambiar.put("E_"+idColumna,"5E_"+idColumna);%>
					<td id="<%="5E_".concat(idColumna)%>">&nbsp;
						<%if(entrar){%>
						<%=Constants.signo(lista.getString("spread_logro"))%>
						<%}%>
					</td>
					
					<%if(uo.equals(Constants.ALTA)){%>
						<%cambiar.put("F_"+idColumna,"5F_"+idColumna);%>
						<td id="<%="5F_".concat(idColumna)%>" colspan="2" align="center" style="border-bottom:0px;" valign="bottom">
							&nbsp;
							<%entrar=(!Constants.tipoFraccion(lista.getString("total"),false).equals("") && !Constants.signo(lista.getString("total_logro")).equals("0"));
							if(entrar){%>
								<b><%=Constants.tipoFraccion(lista.getString("total"),false)%></b>
								</br><b><%=lista.getString("referencia_ab")%>:</b><span class="colorLogro5to"><%=Constants.signo(lista.getString("total_logro"))%></span>
							<%}%>
						</td>
					<%} else if(uo.equals(Constants.BAJA)) {%>
						<%cambiar.put("G_"+idColumna,"5G_"+idColumna);%>
						<td id="<%="5G_".concat(idColumna)%>" colspan="2" style="border-top:0px;" valign="top">
							&nbsp;
							<%entrar=(!Constants.tipoFraccion(lista.getString("total"),false).equals("") && !Constants.signo(lista.getString("total_logro")).equals("0"));
							if(entrar){%>
								<b><%=lista.getString("referencia_ab")%>:</b><span class="colorLogro5to"><%=Constants.signo(lista.getString("total_logro"))%><span>
							<%}%>
						</td>
					<%} else if(!uo.equals(Constants.BAJA)) {%>
						<td>&nbsp;</td>
					<%}%>
					
				<%}%>
			</tr>	
			<%uo=(uo.equals(Constants.ALTA)?Constants.BAJA:(uo.equals(Constants.BAJA)?"":""));
		}%> 
		
	</table>
	</logic:present>
	
	
<!-- 1ro inning -->	

	<logic:present name="lista1ro" scope="request">
	<table style="display:none">
	<% CachedRowSet lista = (CachedRowSet)request.getAttribute("lista1ro");  
	String idJuego = null;
	String titulo = null;
	String uo = Constants.ALTA;
	String estilo="";
	boolean subtitulo = false;
	String idColumna = null;
	String decision="";
	while(lista.next()) {
		subtitulo = false;
		estilo="borderWhite";
		if(idJuego==null || !idJuego.equals(lista.getString("id_juego"))) {
			uo=Constants.ALTA;
			idJuego=lista.getString("id_juego");
			
			if(titulo==null || !titulo.equals(lista.getString("dia").concat(lista.getString("desc_liga")))) {
				subtitulo = true;
			%>
			<%} else {
			estilo="borderTopBlackReport";
				%>
			<%}
			titulo = String.valueOf(lista.getString("dia")).concat(String.valueOf(lista.getString("desc_liga")));
		}
		idColumna=uo.concat(String.valueOf(lista.getString("id_juego_padre")));
		%>
		<%if(subtitulo && uo.equals(Constants.ALTA)){
			isPitcher = lista.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL) || lista.getString("id_deporte").equals(Constants.ID_EQUIPO_BEISBOL_5);
			%>

			<%color=color2;
		}%>
			<tr  bgcolor="<%=color%>" class="det"><!--class="detalleTablaCenterBW"> -->
			<!-- PADRE:<%=lista.getString("id_juego_padre")%> -->
				
				<%decision="";
				if(uo.equals(Constants.ALTA)){
					decision="<span style='color:blue'>SI:</span>";
				} else if(uo.equals(Constants.BAJA) ) {
					decision="<span style='color:red'>NO:</span>";
				}%>
				<%cambiar.put("H_"+idColumna,"1A_"+idColumna);%>
				<td id="<%="1A_".concat(idColumna)%>" ><%=decision%><b><%=lista.getString("referencia")%></b></td>
				
				<%cambiar.put("I_".concat(idColumna),"1B_".concat(idColumna));%>
				<td id="<%="1B_".concat(idColumna)%>" ><%=Constants.signo(lista.getString("money_line"))%></td>
			</tr>	
			<%uo=(uo.equals(Constants.ALTA)?Constants.BAJA:(uo.equals(Constants.BAJA)?"":""));
		}%> 
		
	</table>
	</logic:present>
	
	
</table>
<center>
<span class="final">
La hoja de logros es referencial, esta sujeta a cambios sin previo aviso 
</span>
</center>
<hr width="98%" color="#efefef"/>
<script language="javascript">
	<%if(cambiar.size()>0){
		Iterator i = cambiar.keySet().iterator();
		while(i.hasNext()) {
			String key = (String)i.next();%>try{document.getElementById("<%=key%>").innerHTML=document.getElementById("<%=cambiar.get(key)%>").innerHTML;} catch(e){}<%}
	}%>
</script>

