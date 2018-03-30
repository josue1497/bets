<%@ include file="/views/common/taglibs.jsp"%>
<%@ include file="/views/common/meta.jsp"%>
<% boolean viewUser = ( usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) || usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA) ); %>
<% CachedRowSet lista = (CachedRowSet)request.getAttribute("lista");  %>
<% String resumido = ( String.valueOf(request.getAttribute("resumido")).equals("1") ? "none" : "" );  %>
<% int cont=0;
boolean isEmpate = request.getAttribute("juegosEquipos")!=null;
TreeMap juegosEquipos = (TreeMap) request.getAttribute("juegosEquipos"); 
%>

<script language="JavaScript" src="<%=basePath%>scripts/Calendario_2.js"></script>
<style>
	.tc{
		background-color:#cfcfcf;
	}
</style>

<% int t1=0;
   double t2=0;
   double t3=0;
   double t4=0;
   double t5=0;
   double t6=0;
   double t7=0;
   double t8=0;
   double t9=0;
   double t10=0;
   double t11=0;
   double t12=0;
   double t13=0;
   double t14=0;
   double t15=0;
   double t16=0;
   double t17=0;
   double t18=0;
   String color="#ffffff";
%>
<fieldset>
<table align="center" width="100%" cellspacing="3" cellpadding="0">
	<tr>
		<td class="tituloTablaLeftBW" >LISTADO DE JUGADAS POR EQUIPO</td>
		<td class="tituloTablaRigthBW" width="10%">Fecha :</td>
		<td class="tituloTablaLeftBW" width="10%"><%=Constants.getFechaCorta()%></td>
	</tr>
	<tr>
		<td class="tituloTablaLeftBW" >Fecha <%=request.getAttribute("fecha")%></td>
		<td class="tituloTablaRigthBW" width="10%">Hora :</td>
		<td class="tituloTablaLeftBW" width="10%"><%=Constants.getHora()%></td>
	</tr>
</table>
</fieldset>
<table align="center" width="98%" cellspacing="0" cellpadding="0" style="display:<%=resumido%>">
	<tr>
		<td class="tituloTablaBW tablaConBordeBW">&nbsp;</td>
		<td class="tituloTablaRigthBW tablaConBordeBW">&nbsp;</td>
		<td class="tituloTablaBW tablaConBordeBW tc" colspan="8">PARLEY</td>
		<td class="tituloTablaBW tablaConBordeBW tc" colspan="8">DERECHO</td>
		<td class="tituloTablaRigthBW tablaConBordeBW">&nbsp;</td>
	</tr>	
	<tr>
		<td class="tituloTablaBW tablaConBordeBW">EQUIPO <%=(isEmpate?"*** EMPATE ***":"")%></td>
		<td class="tituloTablaRigthBW tablaConBordeBW">TICKETS</td>
		<td class="tituloTablaRigthBW tablaConBordeBW">ML</td>
		<td class="tituloTablaRigthBW tablaConBordeBW">RL</td>
		<td class="tituloTablaRigthBW tablaConBordeBW">SRL</td>
		<td class="tituloTablaRigthBW tablaConBordeBW">A</td>
		<td class="tituloTablaRigthBW tablaConBordeBW">B</td>
		<td class="tituloTablaRigthBW tablaConBordeBW">SI</td>
		<td class="tituloTablaRigthBW tablaConBordeBW">NO</td>
		<td class="tituloTablaRigthBW tablaConBordeBW">TOTAL</td>
		<td class="tituloTablaRigthBW tablaConBordeBW">ML</td>
		<td class="tituloTablaRigthBW tablaConBordeBW">RL</td>
		<td class="tituloTablaRigthBW tablaConBordeBW">SRL</td>
		<td class="tituloTablaRigthBW tablaConBordeBW">A</td>
		<td class="tituloTablaRigthBW tablaConBordeBW">B</td>
		<td class="tituloTablaRigthBW tablaConBordeBW">SI</td>
		<td class="tituloTablaRigthBW tablaConBordeBW">NO</td>
		<td class="tituloTablaRigthBW tablaConBordeBW">TOTAL</td>
		<td class="tituloTablaRigthBW tablaConBordeBW">JUGADO</td>
	</tr>	
	<logic:present name="lista" scope="request">
	<%while(lista.next()) {%>
	<tr style="background-color:<%=color=(color.equals("#ffffff")?"#efefef":"#ffffff")%>;">
		<%if(lista.getString("id_equipo").equals(Constants.ID_EQUIPO_EMPATE)){%>
			<%if(isEmpate){%>
				<td class="detalleTablaLeftBW top"><%=juegosEquipos.get(lista.getString("id_juego"))%></td>
			<%} else {%>
				<td class="detalleTablaLeftBW top"><a href="./listApuestaPorEquipo.do?idEquipo=<%=Constants.ID_EQUIPO_EMPATE%>&fecha=<%=request.getAttribute("fecha")%>" ><%=lista.getString("desc_equipo")%></a></td>
			<%}%>
		<%} else {%>
			<td class="detalleTablaLeftBW top"><%=lista.getString("desc_equipo")%></td>
		<%}%>
		<td class="detalleTablaRightBW top"><%=lista.getString("tickets")%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("parley_ML"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("parley_RL"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("parley_SR"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("parley_A"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("parley_B"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("parley_SI"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("parley_NO"))%></td>
		<td class="detalleTablaRightBW top tc"><%=Constants.formatNumber(lista.getString("parley"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("derecho_ML"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("derecho_RL"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("derecho_SR"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("derecho_A"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("derecho_B"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("derecho_SI"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("derecho_NO"))%></td>
		<td class="detalleTablaRightBW top tc"><%=Constants.formatNumber(lista.getString("derecho"))%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(lista.getString("apostado"))%></td>
	</tr>
	<%
	t1+=lista.getInt("tickets");
	t2+=lista.getDouble("parley_ML");
	t3+=lista.getDouble("parley_RL");
	t4+=lista.getDouble("parley_SR");
	t5+=lista.getDouble("parley_A");
	t6+=lista.getDouble("parley_B");
	t7+=lista.getDouble("parley_SI");
	t8+=lista.getDouble("parley_NO");
	t9+=lista.getDouble("parley");
	t10+=lista.getDouble("derecho_ML");
	t11+=lista.getDouble("derecho_RL");
	t12+=lista.getDouble("derecho_SR");
	t13+=lista.getDouble("derecho_A");
	t14+=lista.getDouble("derecho_B");
	t15+=lista.getDouble("derecho_SI");
	t16+=lista.getDouble("derecho_NO");
	t17+=lista.getDouble("derecho");
	t18+=lista.getDouble("apostado");
	}%>	
	</logic:present>
	<tr>
		<td class="tituloTablaBW tablaConBordeBW">Totales</td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(t1)%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(t2)%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(t3)%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(t4)%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(t5)%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(t6)%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(t7)%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(t8)%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(t9)%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(t10)%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(t11)%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(t12)%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(t13)%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(t14)%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(t15)%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(t16)%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(t17)%></td>
		<td class="detalleTablaRightBW top"><%=Constants.formatNumber(t18)%></td>
	</tr>	
</table>
<hr width="98%"/>

