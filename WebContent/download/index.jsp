<%@ include file="/views/common/taglibs.jsp"%>
<% com.betcesc.game.bean.DominioBean dom = Constants.getDominio(request); %>
<html>
<body>
<h2>Descargas</h2>
<ul style="display:<%=dom.getIdDominio().equals("000")?"":"none"%>" >
	<li><a href="./CALCOMANIA BETCESC JUEGA PARLAY.doc">CALCOMANIA BETCESC JUEGA PARLAY.doc</a></li>
	<li><a href="./Como Jugar BETCESC.doc">Como Jugar BETCESC.doc</a></li>
	<li><a href="./Reglas Parlay BETCESC.doc">Reglas Parlay BETCESC.doc</a></li>
	<li><a href="./Reglas Parlay Hipico BETCESC.doc">Reglas Parlay Hipico BETCESC.doc</a></li>
	<li><a href="./REGLAS PARLEY HIPICO ROJO.doc">Reglas Parlay Caballos Rojo Negro</a></li>
</ul>
<ul style="display:<%=!dom.getIdDominio().equals("000")?"":"none"%>" >
	<li><a href="./Reglas Parlay.doc">Reglas Parlay</a></li>
	<li><a href="./REGLAS PARLEY HIPICO ROJO2.doc">Reglas Parlay Caballos Rojo Negro</a></li>
</ul>
</body>
</html>