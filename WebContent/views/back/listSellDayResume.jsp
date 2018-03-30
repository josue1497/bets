<%@ page import="java.util.TreeMap" %>
<%@ include file="/views/common/taglibs.jsp"%>
<%@ include file="/views/common/meta.jsp"%>
<% boolean viewUser = ( usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) || usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA) ); 
boolean showVencido = usuario.getVencidoReporte().equals("1"); 
%>
<% CachedRowSet lista = (CachedRowSet)request.getAttribute("lista");  %>
<% TreeMap listaVencido = (TreeMap)request.getAttribute("vencido");  %>
<% String resumido = ( String.valueOf(request.getAttribute("resumido")).equals("1") ? "none" : "" );  %>
<% int cont=0; 
double td1=0;
double td2=0;
double td3=0;
double td4=0;
double td5=0;
double td6=0;
double td7=0;

double tp1=0;
double tp2=0;
double tp3=0;
double tp4=0;
double tp5=0;
double tp6=0;
double tp7=0;

double t1=0;
double t2=0;
double t3=0;
double t4=0;
double t5=0;
double t6=0;
double t6_venc=0;
double t7=0;

double comVtaDer=0;
double comVtaPar=0;
double comVta=0;

double otrGasDer=0;
double otrGasPar=0;
double otrGas=0;

double std=0;
double stp=0;
double st=0;
String color="#ffffff";

double vencido = 0;
%>

<script language="JavaScript" src="<%=basePath%>scripts/Calendario_2.js"></script>

<fieldset>
<table align="center" width="100%" cellspacing="3" cellpadding="0">
	<tr>
		<td class="tituloTablaLeftBW" >RESUMEN DE TICKETS POR FECHA</td>
		<td class="tituloTablaRigthBW" width="10%">Fecha :</td>
		<td class="tituloTablaLeftBW" width="10%"><%=Constants.getFechaCorta()%></td>
	</tr>
	<tr>
		<td class="tituloTablaLeftBW" >
			Desde <%=request.getAttribute("fecha")%> Hasta <%=request.getAttribute("fecha2")%>
			<%if(request.getAttribute("dominioList")!=null && !request.getAttribute("dominioList").equals("")) {%>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Dominio : <%=Constants.getDomainName(request.getAttribute("dominioList").toString())%>
			<%}%>  
		</td>
		<td class="tituloTablaRigthBW" width="10%">Hora :</td>
		<td class="tituloTablaLeftBW" width="10%"><%=Constants.getHora()%></td>
	</tr>
</table>
</fieldset>
<table align="center" width="98%" cellspacing="0" cellpadding="0" style="display:<%=resumido%>" border="0">
	<tr>
		<td colspan="8" class="tituloTablaLeftBW" style="text-align:center;">
			<% if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)){%>
				Administrador: <%=usuario.getUsuario()%>
			<%} else if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA)){%>
				Administrador de taquillas: <%=usuario.getUsuario()%>
			<%} else {%>
				Taquilla: <%=usuario.getUsuario()%>
			<%}%>
		</td>
	</tr>
	<tr>
		<td class="tituloTablaBW tablaConBordeBW">NOMBRE DE TAQUILLA</td>
		<td class="tituloTablaBW tablaConBordeBW">TIPO</td>
		<td class="tituloTablaBW tablaConBordeBW">JUGADO</td>
		<td class="tituloTablaBW tablaConBordeBW">DEVOLUCION</td>
		<td class="tituloTablaBW tablaConBordeBW">GANADORES</td>
		<td class="tituloTablaBW tablaConBordeBW">SUB-TOTAL</td>
		<td class="tituloTablaBW tablaConBordeBW">% DE VENTA</td>
		<td class="tituloTablaBW tablaConBordeBW">COMISION</td>
		<%if(showVencido){%>
		<td class="tituloTablaBW tablaConBordeBW" >VENCIDO</td>
		<%}%>
		<td class="tituloTablaBW tablaConBordeBW">BAL. TOTAL</td>
	</tr>	
	<tr>	
		<td colspan="<%=(viewUser?10:8)%>">
			<hr width="100%"/>
		</td>
	</tr>	
	<logic:present name="lista" scope="request">
	<%
	while(lista.next()) {
		//if(usuario.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR) && lista.getString("tipo").equalsIgnoreCase("(jug-taq)")){
		//	continue;
		//}
	%>
	<tr style="background-color:efefef;">
		<%
		++cont;

		comVtaDer=0;
		comVtaPar=0;
		comVta=0;
		
		otrGasDer=0;
		otrGasPar=0;
		otrGas=0;
		
		comVtaDer = (lista.getDouble("jugado_derecho")-lista.getDouble("devolucion_derecho"))*(lista.getDouble("comision_venta")/100);
		comVtaPar = (lista.getDouble("jugado_parley")-lista.getDouble("devolucion_parley"))*(lista.getDouble("comision_venta_parley")/100);
		comVta = comVtaDer+comVtaPar;

		if(false || (lista.getDouble("subtotal_derecho")>=0 && lista.getDouble("subtotal_parley")>=0) ) { // si el calculo es con perdida y ganancias
			otrGasDer = lista.getDouble("subtotal_derecho")*(lista.getDouble("otros_gastos")/100);
			otrGasPar = lista.getDouble("subtotal_parley")*(lista.getDouble("otros_gastos_parley")/100);

		} else if(lista.getDouble("subtotal_derecho")>=0 && lista.getDouble("subtotal_parley")<0) { // si derecho positivo y parley negativo
			double mtoDer = lista.getDouble("subtotal_derecho")+lista.getDouble("subtotal_parley");
			if(mtoDer>0) {
				otrGasDer = mtoDer*(lista.getDouble("otros_gastos")/100);
				otrGasPar = 0;
			}

		} else if(lista.getDouble("subtotal_parley")>=0 && lista.getDouble("subtotal_derecho")<0) { // si parley positivo y derecho negativo
			double mtoPar = lista.getDouble("subtotal_parley")+lista.getDouble("subtotal_derecho");
			if(mtoPar>0) {
				otrGasDer = 0;
				otrGasPar = mtoPar*(lista.getDouble("otros_gastos_parley")/100);
			}

		} else if(td4<=0) { // no hay comision
			otrGasDer = 0;
			otrGasPar = 0;
		}
		otrGas = otrGasDer+otrGasPar;

		std=lista.getDouble("subtotal_derecho")-comVtaDer-otrGasDer;
		stp=lista.getDouble("subtotal_parley")-comVtaPar-otrGasPar;

		vencido = 0; 
		if(showVencido && listaVencido.containsKey(lista.getString("id_usuario"))) {
			vencido = Double.parseDouble((String)listaVencido.get(lista.getString("id_usuario")));
		}
		
		st=std+stp+vencido;
		%>
		<td class="detalleTablaLeftBW top"><b><%=lista.getString("usuario")%></b>&nbsp;<%=lista.getString("tipo")%></td>
		<td class="detalleTablaLeftBW top">Derecho</td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("jugado_derecho"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("devolucion_derecho"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("ganadores_derecho"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("subtotal_derecho"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(String.valueOf(comVtaDer))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(String.valueOf(otrGasDer))%></td>
		<%if(showVencido){%>
		<td class="detalleTablaRightBW top">&nbsp;</td>
		<%}%>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(String.valueOf(std))%></td>
	</tr>	
	<tr>	
		<td class="detalleTablaLeftBW top">&nbsp;</td>
		<td class="detalleTablaLeftBW top">Parley</td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("jugado_parley"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("devolucion_parley"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("ganadores_parley"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("subtotal_parley"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(String.valueOf(comVtaPar))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(String.valueOf(otrGasPar))%></td>
		<%if(showVencido){%>
		<td class="detalleTablaRightBW top">&nbsp;</td>
		<%}%>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(String.valueOf(stp))%></td>
	</tr>	
	<tr style="background-color:afefef;">
		<td class="detalleTablaLeftBW top">&nbsp;</td>
		<td class="detalleTablaLeftBW top">Total</td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("jugado"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("devolucion"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("ganadores"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("subtotal"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(String.valueOf(comVta))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(String.valueOf(otrGas))%></td>
		<%if(showVencido){%>
		<td class="detalleTablaRightBW top"><%=vencido%></td>
		<%}%>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(String.valueOf(st))%></td>
	</tr>
	<%
		td1+=lista.getDouble("jugado_derecho");
		td2+=lista.getDouble("devolucion_derecho");
		td3+=lista.getDouble("ganadores_derecho");
		td4+=lista.getDouble("subtotal_derecho");
		td5+=comVtaDer;
		td6+=otrGasDer;
		td7+=std;

		tp1+=lista.getDouble("jugado_parley");
		tp2+=lista.getDouble("devolucion_parley");
		tp3+=lista.getDouble("ganadores_parley");
		tp4+=lista.getDouble("subtotal_parley");
		tp5+=comVtaPar;
		tp6+=otrGasPar;
		tp7+=stp;

		t1+=lista.getDouble("jugado");
		t2+=lista.getDouble("devolucion");
		t3+=lista.getDouble("ganadores");
		t4+=lista.getDouble("subtotal");
		t5+=comVta;
		t6+=otrGas;
		t6_venc+=vencido;
		t7+=st;
	%>	
	<%}%>
	</logic:present>
	<tr>
		<td class="tituloTablaBW tablaConBordeBW">TOTAL DERECHO</td>
		<td class="tituloTablaBW tablaConBordeBW">&nbsp;</td>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(td1)%></b></td>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(td2)%></b></td>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(td3)%></b></td>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(td4)%></b></td>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(td5)%></b></td>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(td6)%></b></td>
		<%if(showVencido){%>
		<td class="detalleTablaRightTotalBW">&nbsp;</td>
		<%}%>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(td7)%></b></td>
	</tr>
	<tr>
		<td class="tituloTablaBW tablaConBordeBW">TOTAL PARLEY</td>
		<td class="tituloTablaBW tablaConBordeBW">&nbsp;</td>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(tp1)%></b></td>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(tp2)%></b></td>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(tp3)%></b></td>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(tp4)%></b></td>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(tp5)%></b></td>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(tp6)%></b></td>
		<%if(showVencido){%>
		<td class="detalleTablaRightTotalBW">&nbsp;</td>
		<%}%>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(tp7)%></b></td>
	</tr>
	<tr>
		<td class="tituloTablaBW tablaConBordeBW">TOTAL GENERAL</td>
		<td class="tituloTablaBW tablaConBordeBW">&nbsp;</td>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(t1)%></b></td>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(t2)%></b></td>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(t3)%></b></td>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(t4)%></b></td>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(t5)%></b></td>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(t6)%></b></td>
		<%if(showVencido){%>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(t6_venc)%></b></td>
		<%}%>
		<td class="detalleTablaRightTotalBW"><b><%=Constants.formatNumber(t7)%></b></td>
	</tr>
</table>

