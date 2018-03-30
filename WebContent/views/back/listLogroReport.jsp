<!-- listLogroReport.jsp -->
<%if(request.getParameter("idDeporte").equals("4")) {%>  <!-- beisbol -->
	<jsp:include page="listLogroReportBeisbol.jsp" />
<%} else if(request.getAttribute("idDeporte").equals("6")) {%>  <!-- basket -->
	<jsp:include page="listLogroReportBasket.jsp" />
<%} else if(request.getAttribute("idDeporte").equals("1")) {%>  <!-- soccer -->
	<jsp:include page="listLogroReportSoccer.jsp" />
<%} else {%>  <!-- todos -->
	El juego no es valido para este formato de pagina
<%}%>