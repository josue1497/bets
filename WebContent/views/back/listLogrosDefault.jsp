<%@ include file="/views/common/taglibs.jsp"%>
<html>
<head>
<%@ include file="/views/common/meta.jsp"%>
<% boolean viewUser = ( usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) || usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA) ); %>
<% CachedRowSet lista = (CachedRowSet)request.getAttribute("lista");  %>
<% CachedRowSet listaDeporte = (CachedRowSet)request.getAttribute("listaDeporte");  %>
<% String resumido = ( String.valueOf(request.getAttribute("resumido")).equals("1") ? "none" : "" );  %>
<% int cont=0; 
String color="";%>
<style>
input {
	width:30;
	height:20;
	font-size:9pt;
	text-align:center;
	border:1px solid #afafaf;
}
.tit{
	border:1px solid black;
	background-color:white;
	width:60;
}
.det{
	background-color:white;
	width:60;
	text-align:center;
}
.ml{
	background-color:#efefef;
	width:60;
}
.rl{
	background-color:#dfdfdf;
	width:60;
}
.srl{
	background-color:#efefef;
	width:60;
}
.ab{
	background-color:#dfdfdf;
	width:60;
}
.resaltado{
	color:#fff;
	background-color:#000;
}
</style>
<script>
function fill(vML1,vML2,vFRL1,vLRL1,vFRL2,vLRL2,vFSRL1,vLSRL1,vFSRL2,vLSRL2,vFAB1,vLAB1,vFAB2,vLAB2,vLAP1,vLAP2,idDeporte) {
	with(document.logros){
		ML1.value= vML1;
		ML2.value= vML2;
		FRL1.value=vFRL1;
		LRL1.value=vLRL1;
		FRL2.value=vFRL2;
		LRL2.value=vLRL2;
		FSRL1.value=vFSRL1;
		LSRL1.value=vLSRL1;
		FSRL2.value=vFSRL2;
		LSRL2.value=vLSRL2;
		FAB1.value=vFAB1;
		LAB1.value=vLAB1;
		FAB2.value=vFAB2;
		LAB2.value=vLAB2;
		LAP1.value=vLAP1;
		LAP2.value=vLAP2;
		id_deporte.value=idDeporte;
	}
	
	
}
</script>
</head>
<body>
<table align="center" width="860" cellspacing="0" cellpadding="0" >
<caption>
	Logros Preconfigurados para los Juegos
</caption>
	<tr>
		<td class="tituloTablaBW tablaConBordeBW tit">ML1</td>
		<td class="tituloTablaBW tablaConBordeBW tit">ML2</td>

		<td class="tituloTablaBW tablaConBordeBW tit">FRL1</td>
		<td class="tituloTablaBW tablaConBordeBW tit">LRL1</td>
		<td class="tituloTablaBW tablaConBordeBW tit">FRL2</td>
		<td class="tituloTablaBW tablaConBordeBW tit">LRL2</td>

		<td class="tituloTablaBW tablaConBordeBW tit">FSRL1</td>
		<td class="tituloTablaBW tablaConBordeBW tit">LSRL1</td>
		<td class="tituloTablaBW tablaConBordeBW tit">FSRL2</td>
		<td class="tituloTablaBW tablaConBordeBW tit">LSRL2</td>

		<td class="tituloTablaBW tablaConBordeBW tit">FAB1</td>
		<td class="tituloTablaBW tablaConBordeBW tit">LAB1</td>
		<td class="tituloTablaBW tablaConBordeBW tit">FAB2</td>
		<td class="tituloTablaBW tablaConBordeBW tit">LAB2</td>

		<td class="tituloTablaBW tablaConBordeBW tit">LAP1</td>
		<td class="tituloTablaBW tablaConBordeBW tit">LAP2</td>
		<td class="tituloTablaBW tablaConBordeBW tit">DEPORTE</td>
		<td style="width:20;">&nbsp;</td>
	</tr>	
	<tr >
		<td colspan="17">
			<div style="overflow:auto;height:450;">
			<table align="center" width="100%" cellspacing="0" cellpadding="0" >
			<logic:present name="lista" scope="request">
			<%int i=0;
			int idLinea=0;%>
			<%while(lista.next()) {
				if( idLinea!=lista.getInt("id_deporte") ) {
					color=(color.equals("#000000")?"blue":"#000000");					
				}
				idLinea = lista.getInt("id_deporte");
				%>
			<tr>
				<td class="detalleTablaCenterBW top ml" style="cursor:pointer;color:<%=color%>"
				onmouseover="this.style.backgroundColor='#815a06';this.style.color='#ffffff'" 
				onmouseout="this.style.backgroundColor='#efefef';this.style.color='<%=color%>'"
				onclick='fill(<%=lista.getString("favorito")%>,<%=lista.getString("hembra")%>,<%=lista.getString("FRL0")%>,<%=lista.getString("LRL0")%>,<%=lista.getString("FRL1")%>,<%=lista.getString("LRL1")%>,<%=lista.getString("FSRL0")%>,<%=lista.getString("LSRL0")%>,<%=lista.getString("FSRL1")%>,<%=lista.getString("LSRL1")%>,<%=lista.getString("FAB0")%>,<%=lista.getString("LAB0")%>,<%=lista.getString("FAB1")%>,<%=lista.getString("LAB1")%>,<%=lista.getString("LAP0")%>,<%=lista.getString("LAP1")%>,<%=lista.getString("id_deporte")%>)'
				
				><%=lista.getString("favorito")%></td>
				<td class="detalleTablaCenterBW top ml" style="color:<%=color%>"  ><%=lista.getString("hembra")%></td>
				
				<td class="detalleTablaCenterBW top rl" style="color:<%=color%>"  ><%=lista.getString("FRL0")%></td>
				<td class="detalleTablaCenterBW top rl" style="color:<%=color%>"  ><%=lista.getString("LRL0")%></td>
				<td class="detalleTablaCenterBW top rl" style="color:<%=color%>"  ><%=lista.getString("FRL1")%></td>
		 		<td class="detalleTablaCenterBW top rl" style="color:<%=color%>"  ><%=lista.getString("LRL1")%></td>
		
				<td class="detalleTablaCenterBW top srl" style="color:<%=color%>"  ><%=lista.getString("FSRL0")%></td>
				<td class="detalleTablaCenterBW top srl" style="color:<%=color%>"  ><%=lista.getString("LSRL0")%></td>
				<td class="detalleTablaCenterBW top srl" style="color:<%=color%>"  ><%=lista.getString("FSRL1")%></td>
				<td class="detalleTablaCenterBW top srl" style="color:<%=color%>"  ><%=lista.getString("LSRL1")%></td>
		
				<td class="detalleTablaCenterBW top ab" style="color:<%=color%>"  ><%=lista.getString("FAB0")%></td>
				<td class="detalleTablaCenterBW top ab" style="color:<%=color%>"  ><%=lista.getString("LAB0")%></td>
				<td class="detalleTablaCenterBW top ab" style="color:<%=color%>"  ><%=lista.getString("FAB1")%></td>
				<td class="detalleTablaCenterBW top ab" style="color:<%=color%>"  ><%=lista.getString("LAB1")%></td>

				<td class="detalleTablaCenterBW top srl" style="color:<%=color%>"  ><%=lista.getString("LAP0")%></td>
				<td class="detalleTablaCenterBW top srl" style="color:<%=color%>"  ><%=lista.getString("LAP1")%></td>
				<td class="detalleTablaCenterBW top srl" style="color:<%=color%>" >
					<%=lista.getString("desc_deporte")%>
				</td>
			</tr>	
			<%}%>
			</logic:present>
			</table>
			</div>
		</td>
	</tr>
</table>
<hr width="98%"/>
<form name="logros" action="listLogrosDefault.do" style="margin:0">
<table align="center" width="100%" cellspacing="2" cellpadding="3" border="0">
	<tr>
		<td align="center" style="background-color:#afafaf;">
			<select name="id_deporte" style="width:300;">
				<%while(listaDeporte.next()) {%>
				<option value="<%=listaDeporte.getInt("id_deporte")%>"><%=listaDeporte.getString("desc_deporte")%></option>
				<%}%>
			</select> 
		</td>
	</tr>
</table>
<table align="center" width="860" cellspacing="0" cellpadding="0" >
	<tr>
		<td class="det"><input type="text" name="ML1" /></td>
		<td class="det"><input type="text" name="ML2" /></td>

		<td class="det"><input type="text" name="FRL1" /></td>
		<td class="det"><input type="text" name="LRL1" /></td>
		<td class="det"><input type="text" name="FRL2" /></td>
		<td class="det"><input type="text" name="LRL2" /></td>

		<td class="det"><input type="text" name="FSRL1" /></td>
		<td class="det"><input type="text" name="LSRL1" /></td>
		<td class="det"><input type="text" name="FSRL2" /></td>
		<td class="det"><input type="text" name="LSRL2" /></td>

		<td class="det"><input type="text" name="FAB1" /></td>
		<td class="det"><input type="text" name="LAB1" /></td>
		<td class="det"><input type="text" name="FAB2" /></td>
		<td class="det"><input type="text" name="LAB2" /></td>

		<td class="det"><input type="text" name="LAP1" /></td>
		<td class="det"><input type="text" name="LAP2" /></td>

		<td style="width:20;">&nbsp;</td>
	</tr>
</table>
<center>
<a href="javascript:void(0)" onclick="document.forms[0].submit();"/>Guardar</a>
</center>	
</form>
</body>