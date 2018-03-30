
/*
EJECUTAR EN EL SERVIDOR WEB
---------------------------
--grant all privileges on *.* to apuesta identified by 'a147puesta';
*/


-- insert para la tabla perfil
insert into perfil  values(1,'Administrador');
insert into perfil  values(2,'Administrador de Taquilla');
insert into perfil  values(3,'Jugador de Taquilla');
insert into perfil  values(4,'Jugador');

-- insert para la tabla tipo_cuenta
insert into tipo_cuenta  values(1,'Corriente');
insert into tipo_cuenta  values(2,'Ahorro');

-- insert para la status_apuesta
insert into status_apuesta values(1,'En Juego');
insert into status_apuesta values(2,'Eliminada');
insert into status_apuesta values(3,'Pendiente');
insert into status_apuesta values(4,'Pagada');
insert into status_apuesta values(5,'Vencida');
insert into status_apuesta values(6,'Suspendida');
insert into status_apuesta values(7,'Gano');
insert into status_apuesta values(8,'Perdio');
insert into status_apuesta values(9,'Perdedor');

-- insert para la tabla equipo
insert into equipo  values(0,'Empate','EMP','Empate');

-- insert para la tabla status
insert into status  values(1,'Activo');
insert into status  values(2,'Inactivo');

-- insert para la tabla opcion
insert into opcion values(1,'Inicio','5','inicio.do','house.png');
insert into opcion values(2,'Deportes','10','deportes.do','images.png');
insert into opcion values(3,'Juegos','15','listGame.do','sport_football.png');
insert into opcion values(4,'Usuarios','20','listUser.do','user_suit.png');
insert into opcion values(5,'Historico','25','listPlay.do','database_table.png');
insert into opcion values(6,'Apuestas','30','listGamePlay.do','money.png');
insert into opcion values(7,'Cuenta','35','listUserPlayDetail.do','page_white_add.png');
insert into opcion values(8,'Saldos','40','listUserPlay.do','money_dollar.png');
insert into opcion values(9,'Reportes','45','listReport.do','printer.png');
insert into opcion values(10,'Logros','50','listLogroPorCentro.do','medal_bronze_3.png');
insert into opcion values(11,'Parametros','50','listParametros.do','report_edit.png');
insert into opcion values(12,'Ganadores','55','listPlayPending.do','rosette.png');
insert into opcion values(13,'Seguridad','60','changePassword.do','key.png');

-- insert para la tabla perfil_opcion
insert into perfil_opcion values(1,1);
insert into perfil_opcion values(2,1);
insert into perfil_opcion values(3,1);
insert into perfil_opcion values(4,1);
insert into perfil_opcion values(5,1);
insert into perfil_opcion values(8,1);
insert into perfil_opcion values(9,1);
insert into perfil_opcion values(11,1);
insert into perfil_opcion values(13,1);

insert into perfil_opcion values(1,2);
insert into perfil_opcion values(3,2);
insert into perfil_opcion values(5,2);
insert into perfil_opcion values(9,2);
insert into perfil_opcion values(10,2);
insert into perfil_opcion values(13,2);

insert into perfil_opcion values(1,3);
insert into perfil_opcion values(6,3);
insert into perfil_opcion values(5,3);
insert into perfil_opcion values(9,3);
insert into perfil_opcion values(10,3);
insert into perfil_opcion values(12,3);
insert into perfil_opcion values(13,3);

insert into perfil_opcion values(1,4);
insert into perfil_opcion values(6,4);
insert into perfil_opcion values(5,4);
insert into perfil_opcion values(7,4);
insert into perfil_opcion values(13,4);

-- insert para la tabla usuario
insert into usuario values(1,'10871555','admin','Admin','Admin','e10adc3949ba59abbe56e057f20f883e','a@cantv.net',3,'','','123456','789654','banesco','01234567890123456789','Titular',110,8,1000000,1,1,1,1,'***Gracias por su compra***');
insert into usuario values(2,'10871556','admtaq','admtaq','admtaq','e10adc3949ba59abbe56e057f20f883e','b@cantv.net',3,'Centro Hipico Las Acacias','J0123456789','123456','789654','banesco','01234567890123456789','Titular',110,4,1000000,2,1,1,1,'***Gracias por su compra***');
insert into usuario values(3,'10871557','jugtaq','jugtaq','jugtaq','e10adc3949ba59abbe56e057f20f883e','c@cantv.net',3,'','','123456','789654','banesco','01234567890123456789','Titular',110,4,1000000,3,1,1,2,'***Gracias por su compra***');
insert into usuario values(4,'10871558','jugador','jugador','jugador','e10adc3949ba59abbe56e057f20f883e','d@cantv.net',3,'','','123456','789654','banesco','01234567890123456789','Titular',110,4,1000000,4,1,1,1,'***Gracias por su compra***');


-- insert para la tabla status_juego
insert into status_juego  values(1,'Borrador');
insert into status_juego  values(2,'Abierto');
insert into status_juego  values(3,'Suspendido');
insert into status_juego  values(4,'Cerrado');
insert into status_juego  values(5,'Totalizado');

-- insert para la tabla status_deporte
insert into status_deporte  values(1,'Activo');
insert into status_deporte  values(2,'Inactivo');

-- insert para la tabla deporte
insert into deporte  values(1,'Soccer',1,1,0,0);
insert into deporte  values(2,'Basquet',0,1,700,0);
insert into deporte  values(3,'Hockey',0,1,0,0);
insert into deporte  values(4,'Beisbol',0,1,900,0);
insert into deporte  values(5,'Futbol Americano',0,1,0,0);
insert into deporte  values(6,'Basketball',0,1,499,0);
insert into deporte  values(7,'Beisbol 5 Inning',0,1,1000,0);
insert into deporte  values(8,'Beisbol 1 Inning',0,1,2000,0);

-- insert para la tabla liga
insert into liga  values(1,'Liga Profesional de Beisbol Venezolano','LPBV',4);
insert into liga  values(2,'Liga Profesional de Basquet','LPB',2);
insert into liga  values(3,'Futbol Americano','NFL',5);
insert into liga  values(4,'Hockey','NHL',3);
insert into liga  values(5,'Basquet','NBA',2);
insert into liga  values(6,'Federacion Venezolada de Futbol','FVP',1);

-- insert para la tabla campeonato
insert into campeonato values(1,'Serie del Caribe');
insert into campeonato values(2,'Serie Regular');
insert into campeonato values(3,'Serie Profesional');

-- insert para la tabla logros
insert into logros values(-110,-110);
insert into logros values(-115,-105);
insert into logros values(-120,100);
insert into logros values(-125,105);
insert into logros values(-130,110);
insert into logros values(-135,115);
insert into logros values(-140,120);
insert into logros values(-145,125);
insert into logros values(-150,130);
insert into logros values(-155,135);
insert into logros values(160,140);
insert into logros values(-165,145);
insert into logros values(-170,150);
insert into logros values(-180,155);
insert into logros values(-190,160);
insert into logros values(-200,170);
insert into logros values(-220,180);
insert into logros values(-230,190);
insert into logros values(-240,200);
insert into logros values(-250,210);
insert into logros values(-260,220);
insert into logros values(-280,230);
insert into logros values(-300,240);
insert into logros values(-320,240);
insert into logros values(-330,250);
insert into logros values(-340,260);
insert into logros values(-360,260);
insert into logros values(-380,280);
insert into logros values(-400,300);
insert into logros values(-420,320);
insert into logros values(-450,320);
insert into logros values(-480,340);
insert into logros values(-500,350);
insert into logros values(-600,400);
insert into logros values(-700,400);
insert into logros values(-800,450);
insert into logros values(-900,450);
insert into logros values(-1000,500);
insert into logros values(-1100,500);
insert into logros values(-1200,550);
insert into logros values(-1300,550);
insert into logros values(-1400,600);
insert into logros values(-1500,600);

-- insert para la tabla reglas_pago
insert into reglas_pago values(1,0,50,50,2500);
insert into reglas_pago values(2,51,100,40,4000);
insert into reglas_pago values(3,101,200,35,7000);
insert into reglas_pago values(4,201,20000,30,20000);

-- insert para la tabla de parametros
insert into parametros values('URL_VIDEO_HOME','http://www.rojadirecta.com/justin/djdsport');
INSERT INTO parametros VALUES('AVISO_HOME','');
INSERT INTO parametros VALUES('JUEGOS_BLOQUEADOS','');
