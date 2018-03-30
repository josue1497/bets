<%@page import="com.betcesc.game.bean.DominioBean"%>
<%@ include file="/views/common/taglibs.jsp"%>
<%
	String verDominio = Constants.getDominio(request).getIdDominio().equals("000")?"":"none"; 
String dom = (session.getAttribute("dominioList")!=null?(String)session.getAttribute("dominioList"):"000");
%>
<script language="javascript">
function send(orden) {
	document.forms[0].orden.value=orden;
	document.forms[0].submit();
}
</script>
<form name="forma" action="listUser.do">
<input type="hidden" name="orden" value="0" />
<table align="center" width="98%" height="100%" >
<caption class="tituloTablaSup">LISTA DE USUARIOS</caption>
	<tr>
		<td colspan="8">
			<table width="100%">
				<tr>
					<td width="20%" >
						<table border="0" cellpadding="0" cellspacing="0">
						  <tr>
							<td class="detalleTablaLeft" style="display:<%=usuario.getDominio().equals("") || usuario.getDominio().equals("000")?"":"none"%>">
								Dominio: 
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
                            <option value="015" <%=dom.equals("015") ? "selected" : ""%>>goldparley.com</option>
                            <option value="016" <%=dom.equals("016") ? "selected" : ""%>>tugranjugada.com</option>
                            <option value="017" <%=dom.equals("017") ? "selected" : ""%>>ZonaParley.com </option>
                            <option value="018" <%=dom.equals("018") ? "selected" : ""%>>reyapuesta.net</option>
                            
						</select>
							</td>
							<td class="detalleTablaLeft">&nbsp;</td>
							<td class="detalleTablaLeft">
								Perfil : 
								<select name="rol" style="width:150px" >
									<option value="">Todos</option>
									<%if(usuario.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR)){%>
									<option value="1">Administrador</option>
									<option value="2">Administrador de Taquilla</option>
									<%}%>
									<option value="3">Jugador de Taquilla</option>
									<option value="4">Jugador</option>
								</select>
							</td>
							<td width="20%">
								<td class="detalleTablaLeft">
									Usuario: 
									<input type="text" name="nombreUsuario" style="width:100;" onkeyup="document.forma.dominioList.value='';" />
								</td>
							</td>
						  </tr>
						</table>
					</td>
					<td width="20%" style="text-align:right;">
						<a class="enlaceBoton" href="#" onclick="document.forms[0].submit()" style="width:100;">Listar</a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td class="tituloTabla tablaConBorde" onclick="send(1)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="usuario.apellido"/></td>
		<td class="tituloTabla tablaConBorde" onclick="send(2)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="usuario.nombre"/></td>
		<td class="tituloTabla tablaConBorde" onclick="send(5)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="usuario.usuario"/></td>
		<td class="tituloTabla tablaConBorde" onclick="send(6)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'">Supervisor</td>
		<td class="tituloTabla tablaConBorde" onclick="send(7)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'" style="display:<%=verDominio%>">Dominio</td>
		<td class="tituloTabla tablaConBorde cursorClick" width="200px" onclick="send(3)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="usuario.idRol"/></td>
		<td class="tituloTabla tablaConBorde cursorClick" onclick="send(4)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'"><bean:message key="usuario.idStatus"/></td>
		<td class="tituloTabla tablaConBorde" width="1%">&nbsp;Session&nbsp;</td>
	</tr>	
	<logic:present name="lista" scope="request">
	<% CachedRowSet lista = (CachedRowSet)request.getAttribute("lista");  
	while(lista.next()) {%>
	<tr onmouseover="this.className='lineaResaltada';window.status='<%=Constants.findLastPlay(lista.getString("id_usuario"))%>'" onmouseout="this.className=''" onclick="document.location='<%=basePath%>registerUser.do?idUsuario=<%=lista.getString("id_usuario")%>'">
		<td class="detalleTablaLeft"><%=lista.getString("apellido")%></td>
		<td class="detalleTablaLeft"><%=lista.getString("nombre")%></td>
		<td class="detalleTablaCenter"><%=lista.getString("usuario")%></td>
		<td class="detalleTablaCenter"><%=lista.getString("supervisor")%></td>
		<td class="detalleTablaCenter"  style="display:<%=verDominio%>"><%=Constants.getDomainName(lista.getString("dominio"))%></td>
		<td class="detalleTablaCenter" ><%=lista.getString("desc_rol")%><%=lista.getString("tipo").equals("0")?"":"-<span style='color:yellow;'>Listero</span>"%></td>
		<td class="detalleTablaCenter"><%=lista.getString("desc_status")%></td>
		<td class="detalleTablaCenter"><%= Constants.isUserInSession(lista.getString("id_usuario"))?"*":"&nbsp;" %></td>
	</tr>	
	<%}%>
	</logic:present>
</table>
<hr width="98%" color="#efefef"/>
<%if(usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {%>
<center>
<a class="enlaceBoton" href="<%=basePath%>registerUser.do"	><bean:message key="boton.agregar"/></a>
</center>
<%}%>
</form>
<script language="javascript">
document.forms[0].rol.value='<%=session.getAttribute("rol")%>';
</script>
