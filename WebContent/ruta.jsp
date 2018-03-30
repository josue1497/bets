
<%@page import="org.apache.log4j.Logger"%>
<%
Logger log =Logger.getLogger("JSP");
java.io.File file = new java.io.File("");
file = new java.io.File(file.getAbsolutePath());
java.io.File file2 = null;
java.io.File[] lista = null;
java.io.File[] lista2 = null;
if(file.isDirectory()) {
	lista = file.listFiles();
	for(int i=0;i<lista.length;i++){
		log.info(lista[i].getAbsolutePath());
		file2 = new java.io.File(lista[i].getAbsolutePath());
		if(file2.isDirectory()) {
			lista2 = file2.listFiles();
			for(int k=0;k<lista2.length;k++){
				out.print(lista2[k].getAbsolutePath());
				out.print("<br/>");
			}
		}
	}
}
%>
