<%@ include file="/views/common/taglibs.jsp"%>
<%@ page import="com.betcesc.game.bean.ResultadosBean"%>
<% ArrayList lista = (ArrayList)request.getAttribute("lista"); 
ArrayList lista1 = new ArrayList();
ArrayList lista2 = new ArrayList();
ResultadosBean r = null;
String color="#ffffff";
String juego="";
boolean pos = false;
for(int i=0; i<lista.size();i++) {
	r = (ResultadosBean)lista.get(i);
	if(String.valueOf(r.getEquipo()).toLowerCase().equals("empate")) {
		continue;
	}
	
	pos = (!juego.equals(r.getJuego())?!pos:pos);
	juego = r.getJuego();
	
	if(pos) {
		lista1.add(lista.get(i));
	} else {
		lista2.add(lista.get(i));
	}
}
for(int i=0;lista1.size()>lista2.size();i++) {
	lista2.add(new ResultadosBean());
}
%> 
<html>
<head>
<%@ include file="/views/common/meta.jsp"%>
</head>
<body>
<table cellSpacing="0" cellPadding="0" align="center" border="0" width="100%" class="inicio" align="center">
<CAPTION class="tituloTablaBW">RESULTADOS</CAPTION>
<tr>
	<td width="100%">
		<table cellSpacing="0" cellPadding="0" align="center" border="0" width="100%"  class="inicio"  align="left">
		<%
		String aux1 = null;
		String dep1 = null;
		boolean print1 = true;
		boolean printDeporte1 = true;

		String aux2 = null;
		String dep2 = null;
		boolean print2 = true;
		boolean printDeporte2 = true;
		
		for(int i=0; i<lista1.size();i++) {
			r = (ResultadosBean)lista1.get(i);
			print1 = (aux1==null || !aux1.equals(r.getJuego()));
			printDeporte1 = (dep1==null || !dep1.equals(r.getDeporte())); 
			aux1 = r.getJuego();
			dep1 = r.getDeporte();
			%>
			<%if(printDeporte1){%>
			<tr>
				<td colspan="11" style="border-bottom:0px solid black;font-size:1pt;color:red;">&nbsp;</td>
			</tr>
			<%}%>
			<%if(print1){%>
				<tr>
					<td colspan="11" style="border-bottom:0px solid black;font-size:<%=printDeporte1?14:1%>pt;color:red;"><%=printDeporte1?r.getDeporte():"&nbsp;"%></td>
				</tr>
				<tr style="display:<%=printDeporte1?"":"none"%>">
					<td colspan="5">
						<table width="100%" border="0" cellpadding="0" cellspacing="1" style="border:1px solid black">
							<tr>
								<td class="tituloTablaLeftSmallBW " width="120px">Fecha</td>
								<td class="tituloTablaLeftSmallBW ">Equipo</td>
								<td class="tituloTablaCenterSmallBW borderBlack" width="25">Final</td>
								<td class="tituloTablaCenterSmallBW borderBlack" width="25">5to</td>
								<td class="tituloTablaCenterSmallBW borderBlack" width="25">1ro</td>
							</tr>
						</table>
					</td>
					
					<td class="tituloTablaLeftSmallBW">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	
					<td colspan="5">
						<table width="100%" border="0" cellpadding="0" cellspacing="1" style="border:1px solid black">
							<tr>
								<td class="tituloTablaLeftSmallBW " width="120px">Fecha</td>
								<td class="tituloTablaLeftSmallBW ">Equipo</td>
								<td class="tituloTablaCenterSmallBW borderBlack" width="25">Final</td>
								<td class="tituloTablaCenterSmallBW borderBlack" width="25">5to</td>
								<td class="tituloTablaCenterSmallBW borderBlack" width="25">1ro</td>
							</tr>
						</table>
					</td>
				</tr>
			<%}%>
			<tr>
				<td colspan="5">
					<table width="100%" border="0" cellpadding="0" cellspacing="1" style="border:0px solid black">
						<tr style="background-color:<%=color%>;">
							<td class="tituloTablaLeftSmallNormalBW " width="120px"><%=print1?r.getFecha():""%></td>
							<td class="tituloTablaLeftSmallNormalBW "><%=r.getEquipo().replaceAll("&ntilde;","&ntilde;").replaceAll("Ñ","&Ntilde;")%></td>
							<td class="tituloTablaCenterSmallBW borderBlack" width="25">&nbsp;<%=r.getPuntos()!=null?r.getPuntos():""%></td>
							<td class="tituloTablaCenterSmallBW borderBlack" width="25">&nbsp;<%=r.getPuntos5to()%></td>
							<td class="tituloTablaCenterSmallBW borderBlack" width="25">&nbsp;<%=print1?(r.getPuntos1er().equals("1")?"SI":r.getPuntos1er().equals("0")?"NO":""):""%></td>
						</tr>
					</table>
				</td>
				
				<td class="tituloTablaLeftSmallBW">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<%
				r = (ResultadosBean)lista2.get(i);
				print2 = (aux2==null || !aux2.equals(r.getJuego()));
				printDeporte2 = (dep2==null || !dep2.equals(r.getDeporte())); 
				aux2 = r.getJuego();
				dep2 = r.getDeporte();
				%>
				<td colspan="5">
					<table width="100%" border="0" cellpadding="0" cellspacing="1" style="border:0px solid black;display:<%=r.getJuego().equals("")?"none":""%>" >
						<tr style="background-color:<%=color%>;">
							<td class="tituloTablaLeftSmallNormalBW " width="120px"><%=print2?r.getFecha():""%></td>
							<td class="tituloTablaLeftSmallNormalBW "><%=r.getEquipo().replaceAll("&ntilde;","&ntilde;").replaceAll("Ñ","&Ntilde;")%></td>
							<td class="tituloTablaCenterSmallBW borderBlack" width="25">&nbsp;<%=r.getPuntos()!=null?r.getPuntos():""%></td>
							<td class="tituloTablaCenterSmallBW borderBlack" width="25">&nbsp;<%=r.getPuntos5to()%></td>
							<td class="tituloTablaCenterSmallBW borderBlack" width="25">&nbsp;<%=print2?(r.getPuntos1er().equals("1")?"SI":r.getPuntos1er().equals("0")?"NO":""):""%></td>
						</tr>
					</table>
				</td>
				
			</tr>
		<%color=(color.equals("#ffffff")?"#efefef":"#ffffff");
		}%>
		</table>
	</td>
</tr>
</table>
</body>
</html>
