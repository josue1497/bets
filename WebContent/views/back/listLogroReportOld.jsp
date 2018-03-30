<!-- listLogroReportOld.jsp -->
<%if(request.getParameter("idDeporte").equals("1") || request.getParameter("idDeporte").equals("21")) {%>  <!-- soccer -->
	<jsp:include page="listLogroReportSoccer.jsp" />
<%} else if(request.getAttribute("idDeporte").equals("3")) {%>  <!-- hockey -->
	<jsp:include page="listLogroReportHockey.jsp" />
<%} else {%>  <!-- todos -->
	<jsp:include page="listLogroReportTodos.jsp" />
<%}%>