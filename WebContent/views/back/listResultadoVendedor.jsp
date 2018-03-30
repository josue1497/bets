<%@ include file="/views/common/taglibs.jsp"%>
<%@ include file="/views/common/meta.jsp"%>
<%@ page import="com.jade.util.tools.StringUtils" %>
<% CachedRowSet lista = (CachedRowSet)request.getAttribute("lista");  
double renglon = 0;
double t1 = 0;
double t2 = 0;
double t3 = 0;
double t4 = 0;
%>

<style>
body {
	font-family:monospace;
	margin:0;
}
th {
	font-family:monospace;
	border-bottom:1px dashed #000;
}
</style>
<%--Constants.formatNumber(t1)--%>
<textarea style="width:100%;height:100%">
RESULTADO VENDEDORES
DESDE: <%=request.getAttribute("fecha")%> HASTA: <%=request.getAttribute("fecha2")%>
------------------------------------------------------------------------------------------------
ID          VENDEDOR                              VENTA   COMISION        PREMIOS          TOTAL
------------------------------------------------------------------------------------------------
<logic:present name="lista" scope="request"><%
while(lista.next()) {
renglon = lista.getDouble(3)-lista.getDouble(4)-lista.getDouble(5);
t1 += lista.getDouble(3);
t2 += lista.getDouble(4);
t3 += lista.getDouble(5);
t4 += renglon;
%><%=StringUtils.lpad(lista.getString(1),'0',4)%>        <%=StringUtils.rpad(lista.getString(2),' ',30)%> <%=StringUtils.lpad(Constants.formatNumber(lista.getDouble(3)),' ',12)%> <%=StringUtils.lpad(Constants.formatNumber(lista.getDouble(4)),' ',10)%> <%=StringUtils.lpad(Constants.formatNumber(lista.getDouble(5)),' ',14)%> <%=StringUtils.lpad(Constants.formatNumber(renglon),' ',14)%>
<%}
%></logic:present>------------------------------------------------------------------------------------------------
TOTALES:                                   <%=StringUtils.lpad(Constants.formatNumber(t1),' ',12)%> <%=StringUtils.lpad(Constants.formatNumber(t2),' ',10)%> <%=StringUtils.lpad(Constants.formatNumber(t3),' ',14)%> <%=StringUtils.lpad(Constants.formatNumber(t4),' ',14)%>
</textarea>
