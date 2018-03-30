<%@page import="com.betcesc.game.bean.DominioBean"%>
<%@ include file="/views/common/taglibs.jsp"%>
<%
	String verDominio = Constants.getDominio(request).getIdDominio().equals("000")?"":"none"; 
String dom = (session.getAttribute("dominioList")!=null?(String)session.getAttribute("dominioList"):"000");
%>
<script language="JavaScript" src="<%=basePath%>scripts/Calendario_2.js"></script>
<script language="javascript">
function send() {
	with(document.forms[0]) {
		window.open(action+"?fecha="+fecha.value+"&fecha2="+fecha2.value+"&dominio="+dominioList.value,"_blank","width=1000,height=600,status=no,resizable=yes,top=50,left=100,scrollbars=yes");
	}
}

</script>
<table align="center" width="80%" cellpadding="10" cellspacing="5">
	<tr>
		<td>
	  		<fieldset>
	  		<form action="listSellDayResume.do" method="POST">
  			<legend class="tituloTablaSup">&nbsp;Resumen de Tickets por Fecha&nbsp;</legend>
			<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="5">
				<tr>
					<td class="detalleTablaLeft"  width="33%">
						&nbsp;
					</td>
					<td class="tituloTablaLeft" width="33%">
						DESDE
					</td>
					<td class="tituloTablaLeft" width="33%">
						HASTA
					</td>
				</tr>
			
				<tr>
					<td class="detalleTablaLeft">
						Fecha de Emision... 
					</td>
					<td>
						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="10%">
						  			<input type="text" name="fecha" 
						  					value="<%=request.getAttribute("fecha")%>"
											maxlength="10" size="12" 
											onblur="DateFormat(this,this.value,event,true,'3')"  
											onkeyup="DateFormat(this,this.value,event,false,'3')" onfocus="javascript:vDateType='3'" />
								</td>
								<td width="10%" align="left">
							        <a href="javascript:show_calendar('forms[0].fecha', 'forms[0].fecha', 'forms[0].fecha');" 
									onmouseover="window.status='Seleccione la fecha';return true;" 
									onmouseout="window.status='';return true;">
						            <img src="<%=basePath%>images/calendario.gif" border="0" title="Seleccionar Fecha"></a>
								</td>
							</tr>
						</table>
					</td>
					<td>
						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="10%">
						  			<input type="text" name="fecha2" 
						  					value="<%=request.getAttribute("fecha2")%>"
											maxlength="10" size="12" 
											onblur="DateFormat(this,this.value,event,true,'3')"  
											onkeyup="DateFormat(this,this.value,event,false,'3')" onfocus="javascript:vDateType='3'" />
								</td>
								<td width="10%" align="left">
							        <a href="javascript:show_calendar('forms[0].fecha2', 'forms[0].fecha2', 'forms[0].fecha2');" 
									onmouseover="window.status='Seleccione la fecha';return true;" 
									onmouseout="window.status='';return true;">
						            <img src="<%=basePath%>images/calendario.gif" border="0" title="Seleccionar Fecha"></a>
								</td>
							</tr>
						</table>
					</td>
				</tr>

				<%if(usuario.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR)){%>
				<tr >
					<td class="detalleTablaLeft">
						Dominio...
					</td>
					<td>
						<select name="dominioList" style="width:200px">
							<option value="">TODOS</option>
							<option value="000" <%=dom.equals("000")?"selected":""%>>betcesc.com</option>
							<option value="001" <%=dom.equals("001")?"selected":""%>>apuestas222.com</option>
							<option value="002" <%=dom.equals("002")?"selected":""%>>apuestacaliente.com</option>
							<option value="003" <%=dom.equals("003")?"selected":""%>>betsports04.com</option>
                            <option value="004" <%=dom.equals("004")?"selected":""%>>parleynikols.com</option>
                            <option value="005" <%=dom.equals("005")?"selected":""%>>motilonsports.com</option>
                            <option value="006" <%=dom.equals("006")?"selected":""%>>lagranapuesta.com</option>
                            <option value="007" <%=dom.equals("007")?"selected":""%>> tumundoparley.com</option>
                            <option value="008" <%=dom.equals("008")?"selected":""%>>apuestabogota.com</option>
                            <option value="009" <%=dom.equals("009")?"selected":""%>>apuestagold.com</option>
                            <option value="010" <%=dom.equals("010")?"selected":""%>>ganarsport.com</option>
                            <option value="011" <%=dom.equals("011")?"selected":""%>>parleycolombia.com</option>
                            <option value="012" <%=dom.equals("012")?"selected":""%>>bcashonline.com</option>
                            <option value="013" <%=dom.equals("013")?"selected":""%>>apuestafinal.com</option>
                            <option value="014" <%=dom.equals("014")?"selected":""%>>parleydetodos.com</option>
                           <option value="015" <%=dom.equals("015")?"selected":""%>>goldparley.com</option>
                           <option value="016" <%=dom.equals("016")?"selected":""%>>tugranjugada.com</option>
						   <option value="017" <%=dom.equals("017")?"selected":""%>>ZonaParley.com</option>
						   <option value="018" <%=dom.equals("018")?"selected":""%>>reyapuesta.net</option>
						</select>
					</td>
					<td>
					</td>
				</tr>
				<%} else {%>
					<input type="hidden" name="dominioList" value=""/>
				<%}%>
				<tr>
					<td align="center" colspan="3">
						<a class="enlaceBoton" href="#" onclick="send()" >Ver Reporte</a>
					</td>
				</tr>
			</table>
			</form>
			</fielset>
		</td>
	<tr>
</table>
