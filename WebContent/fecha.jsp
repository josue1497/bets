<%
java.util.Date fecha = new java.util.Date();
com.jade.util.Date fecha2 = new com.jade.util.Date();

%>
Fecha java.util.Date :
<%=fecha%><br/>

Fecha com.jade.util.Date :
<%=fecha2%><br/>

Hora :
<%=com.betcesc.game.common.Constants.getHora()%><br>

<%
java.io.File file = new java.io.File("");
java.io.File file2 = new java.io.File(file.getAbsolutePath()+"/webapps/play/fecha.jsp");
%>

ruta:
<%=file2.getAbsolutePath()%>
Existe:<%=file2.exists()%>


