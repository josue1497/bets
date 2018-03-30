<%@ page import="sun.jdbc.rowset.CachedRowSet" %>
<%@ page import="com.betcesc.game.interfaces.UsuarioIF" %>
<%@ page import="com.betcesc.game.common.Constants" %>
<%
UsuarioIF usuario = (UsuarioIF) request.getSession().getAttribute("usuario");
boolean	isAdministradores = usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR) && usuario.getTipo().equals("0");

CachedRowSet lista = (CachedRowSet) request.getAttribute("lista");
%>
<html>
<head>
	<title><%=request.getParameter("titulo")%></title> 
	<meta http-equiv="Cache-Control" content="no-store"/>
	<meta http-equiv="Pragma" content="no-cache"/>
	<meta http-equiv="Expires" content="0"/>
	<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
	
	<script type="text/javascript" src="./scripts/validaciones.js"></script>
	<link href="./css/style<%=Constants.getDominio(request).getDominio()%>.css" rel="stylesheet" type="text/css" />
	<script>
		window.perfil=new Array();
		function send(orden) {
			document.forms[0].orden.value=orden;
			document.forms[0].submit();
		}
		function enviarMensaje() {
			if(document.forma.mensaje.value=='' || document.forma.mensaje.value.replace(/^\s*|\s*$/g,"")=='') {
				alert("Escriba el mensaje que desea enviar");
				return;
			}
			var sel = false;
			var n = document.forma.usuario.length;
			for(var i=0;i<n;i++){
				if(document.forma.usuario[i].checked){
					sel = true;
					break;
				}
			}
			if(!sel){
				alert("Seleccione el o los usuarios que recibiran el mensaje");
				return;
			}
			document.forma.mensaje.value = document.forma.mensaje.value.replace(/\r/g,'');
			document.forma.mensaje.value = document.forma.mensaje.value.replace(/\n/g,'<br/>');
			document.forma.enviar.value="true";
			document.forma.submit();
		}
		function main() {
			<%if(request.getAttribute("MENSAJE")!=null){%>
				alert("<%=request.getAttribute("MENSAJE")%>");
			<%}%>
		}
		function seleccionarTodos() {
			var chk = document.forma.todos.checked;
			var n = document.forma.usuario.length;
			for(var i=0;i<n;i++){
				document.forma.usuario[i].checked = chk;
			}
		}
		function seleccionarLote(id) {
			var chk = eval('document.forma.perfil'+id+'.checked');
			var n = document.forma.usuario.length;
			for(var i=0;i<n;i++){
				if(window.perfil[i]==id) {
					document.forma.usuario[i].checked = chk;
				}
			}
		}
	</script>
</head>
<body bgcolor="#000000" color="#ffffff" onload="main()">
<form name="forma" action="./showUserMessage.do" method="post">
	<input type="hidden" name="enviar" value="false" />
	<input type="hidden" name="orden" value="0" />
	<table width='100%' height="100%" border="1" cellpadding="5" cellspacing="0" class="detalleTablaLeft" align="center" style="border-collapse:collapse">
		<tr>
			<th height="1%" align="center">
				Envio de Mensajes
			</th>
		</tr>
		<tr>
			<td height="1%" align="center">Mensaje</td>
		</tr>
		<tr>
			<td height="20%" width="50%">
				<div style="border:1px solid #afafaf;height:100%;">
					<textarea name="mensaje" style="height:100%;width:100%"></textarea>
				</div>
			</td>
		</tr>
		<tr>
			<td height="1%" align="center">
			<%if(isAdministradores){%>
			<input type="checkbox" name="perfil1" onclick="seleccionarLote(1)" /> Administrador &nbsp;&nbsp;
			<input type="checkbox" name="perfil2" onclick="seleccionarLote(2)" /> Adm. de Taquilla &nbsp;&nbsp;
			<%}%>
			<input type="checkbox" name="perfil3" onclick="seleccionarLote(3)" /> Jugador de Taquilla &nbsp;&nbsp;
			<input type="checkbox" name="perfil4" onclick="seleccionarLote(4)" /> Jugador
			</td>
		</tr>
		<tr>
			<td  height="88%">
				<div style="overflow:auto;border:1px solid #afafaf;height:100%;">
					<table border="0" width="100%" cellspacing="3" cellpadding="0">
						<tr>
							<td  class="tituloTabla tablaConBorde" ><input type="checkbox" name="todos" onclick="seleccionarTodos()"/></td>
							<td class="tituloTabla tablaConBorde" onclick="send(1)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'">Apellido</td>
							<td class="tituloTabla tablaConBorde" onclick="send(2)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'">Nombre</td>
							<td class="tituloTabla tablaConBorde" onclick="send(5)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'">Usuario</td>
							<td class="tituloTabla tablaConBorde cursorClick" width="200px" onclick="send(3)" onmouseover="this.className='tituloTabla tablaConBorde lineaResaltadaDeporte'" onmouseout="this.className='tituloTabla tablaConBorde'">Rol</td>
						</tr>
						<%while(lista.next()){%>
						<tr onmouseover="this.className='lineaResaltada'" onmouseout="this.className=''">
							<td align="center"><input type="checkbox" name="usuario" value="<%=lista.getString("id_usuario")%>"/></td>
							<td class="detalleTablaCenter" >&nbsp;<%=lista.getString("apellido")%></td>
							<td class="detalleTablaCenter" >&nbsp;<%=lista.getString("nombre")%></td>
							<td class="detalleTablaCenter" >&nbsp;<%=lista.getString("usuario")%></td>
							<td class="detalleTablaCenter" >&nbsp;<%=lista.getString("desc_rol")%><%=lista.getString("tipo").equals("0")?"":"-<span style='color:yellow;'>Listero</span>"%></td>
							<script>window.perfil[window.perfil.length]=<%=lista.getString("id_rol")%>;
							</script>
						</tr>
						<%}%>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<th height="1%" align="center">
				<a class="enlaceBoton" href="#" onclick="enviarMensaje()" >Enviar Mensaje Instantaneo</a>
			</th>
		</tr>
	</table>
</form>
</body>
</html>
