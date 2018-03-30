<%@ include file="/views/common/taglibs.jsp"%>
<%@ page import="com.betcesc.game.to.VistaTO"%>
<% ArrayList listaVista = (ArrayList)request.getAttribute("vista");
 
VistaTO vistaTO = null;
if(true || listaVista==null || listaVista.size()==0) {
	listaVista = new ArrayList();
	vistaTO=new VistaTO();
	vistaTO.setIdVista("0");
	vistaTO.setTitle("");
	vistaTO.setContent("");
	vistaTO.setImage("home003.jpg");
	vistaTO.setStatus("1");
	listaVista.add(vistaTO);
}
%> 

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/estilo.css" rel="stylesheet"  type="text/css" />
<style>
BODY { 
scrollbar-face-color:#000000; 
scrollbar-highlight-color:#F57331; 
scrollbar-3dlight-color:#6D1A82; 
scrollbar-darkshadow-color:#6D1A82; 
scrollbar-shadow-color:#F57331; 
scrollbar-arrow-color:#F57331; 
scrollbar-track-color:#000000; 
} 
</style>
<script type="text/javascript" src="js/jquery-1.3.1.min.js"></script>
<script type="text/javascript">
function send(forma) {
	document.forms[0].submit();
}
function ventana(url){
	window.open(url,'resultado',"width=700,height=600,status=no,resizable=yes,top=50,left=100,scrollbars=yes");
}
 
function resultados() {
	window.open("<%=basePath%>resultados.do","resultados","width=600,height=600,status=yes,resizable=yes,top=50,left=100,scrollbars=yes");
}
 
<%if(listaVista!=null && listaVista.size()>0){%> 
$(document).ready(function() {	
	
	//Execute the slideShow
	slideShow();
 
});
<%}%>
 
function slideShow() {
 
	//Set the opacity of all images to 0
	$('#gallery a').css({opacity: 0.0});
	
	//Get the first image and display it (set it to full opacity)
	$('#gallery a:first').css({opacity: 1});
	
	//Set the caption background to semi-transparent
	$('#gallery .caption').css({opacity: 0.8});
 
	//Resize the width of the caption according to the image width
	$('#gallery .caption').css({width: $('#gallery a').find('img').css('width')});
	
	//Get the caption of the first image from REL attribute and display it
	$('#gallery .content').html($('#gallery a:first').find('img').attr('rel'))
	.animate({opacity: 0.7}, 400);
	
	//Call the gallery function to run the slideshow, 6000 = change to next image after 6 seconds
	<%if(listaVista!=null && listaVista.size()>1){%> 
		setInterval('gallery()',<%=request.getAttribute("segundos")!=null?request.getAttribute("segundos").toString().replaceAll("[^0-9]",""):"6"%>000); 
	<%}%>
	
}

function gallery() {
	
	//if no IMGs have the show class, grab the first image
	var current = ($('#gallery a.show')?  $('#gallery a.show') : $('#gallery a:first'));
 
	//Get next image, if it reached the end of the slideshow, rotate it back to the first image
	var next = ((current.next().length) ? ((current.next().hasClass('caption'))? $('#gallery a:first') :current.next()) : $('#gallery a:first'));	
	
	//Get next image caption
	var caption = next.find('img').attr('rel');	
	
	//Set the fade in effect for the next image, show class has higher z-index
	next.css({opacity: 0.0})
	.addClass('show')
	.animate({opacity: 1.0}, 1000);
 
	//Hide the current image
	current.animate({opacity: 0.0}, 1000)
	.removeClass('show');

	//Set the opacity to 0 and height to 1px
	$('#gallery .caption').animate({opacity: 0.0}, { queue:false, duration:0 }).animate({height: '1px'}, { queue:true, duration:300 });	

	if(caption && caption!=''){	
		//Animate the caption, opacity to 0.7 and heigth to 100px, a slide up effect
		$('#gallery .caption').animate({opacity: 0.8},100 ).animate({height: '100px'},500 );
		
		//Display the content
		$('#gallery .content').html(caption);
	}
	
}

function getMacAddress() {
	try {
      var locator = new ActiveXObject ("WbemScripting.SWbemLocator");
      var service = locator.ConnectServer(".");
      var properties = service.ExecQuery("SELECT * FROM Win32_NetworkAdapter");
      var e = new Enumerator (properties);
      var sep="";
      document.forms[0].macAddress.value="";
      var notValid1 = "Adaptador asínc. de RAS";
      var notValid2 = "Minipuerto del administrador de paquetes";
      var notValid3 = "Deterministic Network Enhancer Miniport";
      for (;!e.atEnd();e.moveNext ()) {
            var p = e.item ();
            if(p.MACAddress!=null && p.Caption.indexOf(notValid1)==-1 && p.Caption.indexOf(notValid2)==-1 && p.Caption.indexOf(notValid3)==-1) {
	            document.forms[0].macAddress.value+=sep+p.MACAddress;
	            sep="|";
	         }
	  }
	} catch(e) {}
}

</script>
 
</script>
</head>
 
<body>
<div id="gallery">

<% 
	boolean isShowFirst=false;
	if(listaVista!=null) {
		boolean isShow=false;
		for(int i=0; i<listaVista.size(); i++ ){
			vistaTO = (VistaTO)listaVista.get(i);
			if(i == 0){ 
				out.write("<a href='#' class='show' style='position:absolute;left:0;top:0;'>");
			} else {
				out.write("<a href='#' style='position:absolute;left:0;top:0;'>");
			} 
			isShow=vistaTO.getTitle()!=null && !vistaTO.getTitle().trim().equals("") || vistaTO.getContent()!=null && !vistaTO.getContent().trim().equals("");
			if(i==0) {
				isShowFirst=isShow;
			}
%>
			<img src="dbimg/<%=vistaTO.getImage()%>" width="990" height="391" alt="<%=vistaTO.getTitle()%>"  title="" alt="" 
			<%if(isShow){%>
				rel="<h3><%=vistaTO.getTitle()%></h3><%=vistaTO.getContent()%>"
			<%} else {%>
				rel=""
			<%}%>
			/>
			</a>
<%	
		}
	} 
%>

 
 
	<div class="caption" style='position:absolute;left:0;display:<%=isShowFirst?"":"none"%>'><div class="content"></div></div>
</div>
</body>
</html>

