<%@ include file="/views/common/taglibs.jsp"%>
<%@ include file="/views/common/meta.jsp"%>
<%@ page import="com.betcesc.game.bean.ResultadosBean"%>
<% ArrayList lista = (ArrayList)request.getAttribute("lista"); 
ArrayList lista1 = new ArrayList();
ResultadosBean r = null;
int color=0;
String juego="";
boolean pos = false;
for(int i=0; i<lista.size();i++) {
	r = (ResultadosBean)lista.get(i);
	if(String.valueOf(r.getEquipo()).toLowerCase().equals("empate")) {
		continue;
	}
	lista1.add(lista.get(i));
}
%> 
<html>
<head>
<%@ include file="/views/common/meta.jsp"%>
<style>
.line0 {
	background-color:white;
	color:black;
}
.line1 {
	background-color:#efefef;
	color:black;
}
.tit {
	background-color:white;
	color:#000000;
}
.borderGray {
	border:1px solid #efefef;
}

body {
scrollbar-face-color: #0000cd; 
font-size: xx-small;
scrollbar-highlight-color: #ffffff;
scrollbar-shadow-color: #e8e8e8;
scrollbar-3dlight-color: #d1d7dc;
scrollbar-arrow-color: white;
scrollbar-track-color: black;
scrollbar-darkshadow-color: #98aab1;
}
</style>
</head>
<body bgcolor="#ffffff">
<table cellSpacing="0" cellPadding="0" align="center" border="0" width="100%" class="inicio" align="center">
<CAPTION class="tituloTablaBW" style="color:#0000cd">RESULTADOS DEL DIA ANTERIOR</CAPTION>
<tr>
	<td width="100%">
		<table cellSpacing="3" cellPadding="0" align="center" border="0" width="100%"  class="inicio"  align="left">
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
				<td colspan="5" style="border-bottom:0px solid black;font-size:1pt;color:red;">&nbsp;</td>
			</tr>
			<%}%>
			<%if(print1){%>
				<tr>
					<td colspan="5" style="border-bottom:0px solid black;font-size:<%=printDeporte1?14:1%>pt;color:#0000cd;"><%=printDeporte1?r.getDeporte():"&nbsp;"%></td>
				</tr>
				<tr style="display:<%=printDeporte1?"":"none"%>">
					<td colspan="5">
						<table width="100%" border="0" cellpadding="0" cellspacing="1" style="border:1px solid white">
							<tr>
								<td class="tituloTablaLeftSmallBW tit" width="140px">Fecha</td>
								<td class="tituloTablaLeftSmallBW tit">Equipo</td>
								<td class="tituloTablaCenterSmallBW borderGray tit" width="25">Final</td>
								<td class="tituloTablaCenterSmallBW borderGray tit" width="25">5to</td>
								<td class="tituloTablaCenterSmallBW borderGray tit" width="25">1ro</td>
							</tr>
						</table>
					</td>
				</tr>
			<%}%>
			<tr>
				<td colspan="5">
					<table width="100%" border="0" cellpadding="0" cellspacing="1" style="border:0px solid black">
						<tr clas="line<%=color%>">
							<td class="tituloTablaLeftSmallNormalBW line<%=color%>" style="font-size:7pt;" width="140px"><%=print1?r.getFecha():""%></td>
							<td class="tituloTablaLeftSmallNormalBW line<%=color%>" style="font-size:7pt;"><%=r.getEquipo().replaceAll("&ntilde;","&ntilde;").replaceAll("Ñ","&Ntilde;")%></td>
							<td class="tituloTablaCenterSmallBW borderGray line<%=color%>" width="25">&nbsp;<%=r.getPuntos()!=null?r.getPuntos():""%></td>
							<td class="tituloTablaCenterSmallBW borderGray line<%=color%>" width="25">&nbsp;<%=r.getPuntos5to()%></td>
							<td class="tituloTablaCenterSmallBW borderGray line<%=color%>" width="25">&nbsp;<%=print1?(r.getPuntos1er().equals("1")?"SI":r.getPuntos1er().equals("0")?"NO":""):""%></td>
						</tr>
					</table>
				</td>
			</tr>
		<%color=(color==0?1:0);
		}%>
		</table>
	</td>
</tr>
</table>
</body>
</html>
