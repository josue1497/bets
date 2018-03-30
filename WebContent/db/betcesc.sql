CREATE TABLE equipo (
       id_equipo INT(11) NOT NULL AUTO_INCREMENT
     , desc_equipo VARCHAR(100) NOT NULL
     , abreviatura VARCHAR(10) NOT NULL
     , desc_corta VARCHAR(100) NOT NULL
     , PRIMARY KEY (id_equipo)
)TYPE=InnoDB;
CREATE UNIQUE INDEX UQ_equipo_1 ON equipo (desc_equipo ASC);

CREATE TABLE logros (
       favorito INT(11) NOT NULL
     , hembra INT(11) NOT NULL
     , PRIMARY KEY (favorito, hembra)
);

CREATE TABLE menu (
       id_menu INT(11) NOT NULL
     , desc_menu VARCHAR(100) NOT NULL
     , orden INT(11) NOT NULL
     , accion VARCHAR(200)
     , imagen VARCHAR(200)
     , PRIMARY KEY (id_menu)
)TYPE=InnoDB;
CREATE UNIQUE INDEX UQ_menu_1 ON menu (desc_menu ASC);

CREATE TABLE parametros (
       nombre VARCHAR(20) NOT NULL
     , valor VARCHAR(250)
     , PRIMARY KEY (nombre)
);

CREATE TABLE rol (
       id_rol INT(11) NOT NULL
     , desc_rol VARCHAR(50) NOT NULL
     , PRIMARY KEY (id_rol)
)TYPE=InnoDB;
CREATE UNIQUE INDEX UQ_rol_1 ON rol (desc_rol ASC);

CREATE TABLE reglas_pago (
       id_reglas_pago INT(11) NOT NULL
     , rango_ini DOUBLE NOT NULL
     , rango_fin DOUBLE NOT NULL
     , multiplo INT(11) NOT NULL
     , monto_maximo DOUBLE NOT NULL
     , PRIMARY KEY (id_reglas_pago)
);

CREATE TABLE status (
       id_status INT(11) NOT NULL
     , desc_status VARCHAR(30) NOT NULL
     , PRIMARY KEY (id_status)
)TYPE=InnoDB;
CREATE UNIQUE INDEX UQ_status_1 ON status (desc_status ASC);

CREATE TABLE status_deporte (
       id_status_deporte INT(11) NOT NULL
     , desc_status_deporte VARCHAR(30)
     , PRIMARY KEY (id_status_deporte)
)TYPE=InnoDB;
CREATE UNIQUE INDEX UQ_status_deporte_1 ON status_deporte (desc_status_deporte ASC);

CREATE TABLE status_juego (
       id_status_juego INT(11) NOT NULL
     , desc_status_juego VARCHAR(30)
     , PRIMARY KEY (id_status_juego)
)TYPE=InnoDB;
CREATE UNIQUE INDEX UQ_status_juego_1 ON status_juego (desc_status_juego ASC);

CREATE TABLE status_jugada (
       id_status_jugada INT(11) NOT NULL AUTO_INCREMENT
     , desc_jugada VARCHAR(30) NOT NULL
     , PRIMARY KEY (id_status_jugada)
)TYPE=InnoDB;
CREATE UNIQUE INDEX UQ_status_jugada_1 ON status_jugada (desc_jugada ASC);

CREATE TABLE tipo_cuenta (
       id_tipo_cuenta INT(11) NOT NULL
     , desc_tipo_cuenta VARCHAR(30) NOT NULL
     , PRIMARY KEY (id_tipo_cuenta)
)TYPE=InnoDB;

CREATE TABLE campeonato (
       id_campeonato INT(11) NOT NULL AUTO_INCREMENT
     , desc_campeonato VARCHAR(200) NOT NULL
     , PRIMARY KEY (id_campeonato)
)TYPE=InnoDB;
CREATE UNIQUE INDEX UQ_campeonato_1 ON campeonato (desc_campeonato ASC);

CREATE TABLE deporte (
       id_deporte INT(11) NOT NULL AUTO_INCREMENT
     , desc_deporte VARCHAR(50) NOT NULL
     , empate INT(11) NOT NULL
     , id_status_deporte INT(11) NOT NULL
     , referencia_inicio INT(11)
     , runline_inicio DOUBLE
     , combinacion VARCHAR(700)
     , items INT(11) NOT NULL
     , PRIMARY KEY (id_deporte)
     , INDEX (id_status_deporte)
     , CONSTRAINT FK_deporte_1 FOREIGN KEY (id_status_deporte)
                  REFERENCES status_deporte (id_status_deporte) ON DELETE NO ACTION ON UPDATE NO ACTION
)TYPE=InnoDB;
CREATE UNIQUE INDEX UQ_deporte_1 ON deporte (desc_deporte ASC);

CREATE TABLE usuario (
       id_usuario INT(11) NOT NULL AUTO_INCREMENT
     , cedula VARCHAR(10) NOT NULL
     , apellido VARCHAR(50) NOT NULL
     , nombre VARCHAR(50) NOT NULL
     , usuario VARCHAR(20) NOT NULL
     , clave VARCHAR(50) NOT NULL
     , correo VARCHAR(100) NOT NULL
     , dias_venc_ticket INT(11) NOT NULL
     , centro_hipico VARCHAR(100)
     , rif VARCHAR(12)
     , telefono VARCHAR(12)
     , celular VARCHAR(12)
     , banco VARCHAR(50)
     , numero_cuenta VARCHAR(20)
     , titular_cuenta VARCHAR(50)
     , logros_alta_baja INT(11)
     , logros_calc INT(11) NOT NULL
     , monto DOUBLE
     , id_rol INT(11) NOT NULL
     , id_status INT(11) NOT NULL
     , id_tipo_cuenta INT(11) NOT NULL
     , id_supervisor INT(11) NOT NULL
     , ticket_nota VARCHAR(1000)
     , comision_venta DOUBLE NOT NULL
     , otros_gastos DOUBLE NOT NULL
     , tipo INT(11) NOT NULL
     , mac_address VARCHAR(255)
     , validar_mac_address INT(11) NOT NULL DEFAULT 0
     , logros_min INT(11) NOT NULL
     , PRIMARY KEY (id_usuario)
     , INDEX (id_supervisor)
     , CONSTRAINT FK_usuario_supervisor FOREIGN KEY (id_supervisor)
                  REFERENCES usuario (id_usuario) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_rol)
     , CONSTRAINT FK_usuario_1 FOREIGN KEY (id_rol)
                  REFERENCES rol (id_rol) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_status)
     , CONSTRAINT FK_usuario_2 FOREIGN KEY (id_status)
                  REFERENCES status (id_status) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_tipo_cuenta)
     , CONSTRAINT FK_usuario_3 FOREIGN KEY (id_tipo_cuenta)
                  REFERENCES tipo_cuenta (id_tipo_cuenta) ON DELETE NO ACTION ON UPDATE NO ACTION
)TYPE=InnoDB;
CREATE UNIQUE INDEX UQ_usuario_correo ON usuario (correo ASC);
CREATE UNIQUE INDEX UQ_usuario_cedula ON usuario (cedula ASC);
CREATE UNIQUE INDEX IX_usuario_clave ON usuario (usuario ASC, clave ASC);

CREATE TABLE liga (
       id_liga INT(11) NOT NULL AUTO_INCREMENT
     , desc_liga VARCHAR(100) NOT NULL
     , iniciales VARCHAR(20) NOT NULL
     , id_deporte INT(11) NOT NULL
     , PRIMARY KEY (id_liga)
     , INDEX (id_deporte)
     , CONSTRAINT FK_liga_1 FOREIGN KEY (id_deporte)
                  REFERENCES deporte (id_deporte) ON DELETE NO ACTION ON UPDATE NO ACTION
)TYPE=InnoDB;
CREATE UNIQUE INDEX UQ_liga_1 ON liga (desc_liga ASC);

CREATE TABLE juego (
       id_juego INT(11) NOT NULL AUTO_INCREMENT
     , fecha_sis DATETIME NOT NULL
     , fecha_ini DATETIME NOT NULL
     , fecha_fin DATETIME
     , minutos_cierre INT(11) NOT NULL
     , id_campeonato INT(11) NOT NULL
     , id_status_juego INT(11) NOT NULL
     , id_usuario INT(11) NOT NULL
     , id_deporte INT(11) NOT NULL
     , id_liga INT(11) NOT NULL
     , spread_activo INT(1) NOT NULL DEFAULT 1
     , total_activo INT(1) NOT NULL DEFAULT 1
     , money_activo INT(1) NOT NULL DEFAULT 1
     , id_juego_padre INT(11)
     , id_usuario_creador INT(11) NOT NULL
     , PRIMARY KEY (id_juego)
     , INDEX (id_juego_padre)
     , CONSTRAINT FK_juego_padre FOREIGN KEY (id_juego_padre)
                  REFERENCES juego (id_juego) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_campeonato)
     , CONSTRAINT FK_juego_1 FOREIGN KEY (id_campeonato)
                  REFERENCES campeonato (id_campeonato) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_status_juego)
     , CONSTRAINT FK_juego_2 FOREIGN KEY (id_status_juego)
                  REFERENCES status_juego (id_status_juego) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_usuario)
     , CONSTRAINT FK_juego_3 FOREIGN KEY (id_usuario)
                  REFERENCES usuario (id_usuario) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_deporte)
     , CONSTRAINT FK_juego_4 FOREIGN KEY (id_deporte)
                  REFERENCES deporte (id_deporte) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_liga)
     , CONSTRAINT FK_juego_5 FOREIGN KEY (id_liga)
                  REFERENCES liga (id_liga) ON DELETE NO ACTION ON UPDATE NO ACTION
)TYPE=InnoDB;


CREATE TABLE jugada (
       id_jugada INT(11) NOT NULL AUTO_INCREMENT
     , fecha_sis DATETIME NOT NULL
     , fecha_exp DATETIME
     , monto_apostado DOUBLE NOT NULL
     , monto_premio DOUBLE NOT NULL
     , monto_pagado DOUBLE
     , id_usuario INT(11) NOT NULL
     , id_status_jugada INT(11) NOT NULL
     , dias_expira INT(11) NOT NULL
     , fecha_pago DATETIME
     , detalle_equipo VARCHAR(1000)
     , cancelada INT(11)
     , PRIMARY KEY (id_jugada)
     , INDEX (id_status_jugada)
     , CONSTRAINT FK_jugada_5 FOREIGN KEY (id_status_jugada)
                  REFERENCES status_jugada (id_status_jugada) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_usuario)
     , CONSTRAINT FK_jugada_3 FOREIGN KEY (id_usuario)
                  REFERENCES usuario (id_usuario) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_usuario)
     , CONSTRAINT FK_jugada_4 FOREIGN KEY (id_usuario)
                  REFERENCES usuario (id_usuario) ON DELETE NO ACTION ON UPDATE NO ACTION
)TYPE=InnoDB;

CREATE TABLE lanzador (
       id_lanzador INT(11) NOT NULL AUTO_INCREMENT
     , nombre_lanzador VARCHAR(50) NOT NULL
     , id_equipo INT(11) NOT NULL
     , PRIMARY KEY (id_lanzador)
     , INDEX (id_equipo)
     , CONSTRAINT FK_lanzador_1 FOREIGN KEY (id_equipo)
                  REFERENCES equipo (id_equipo) ON DELETE NO ACTION ON UPDATE NO ACTION
)TYPE=InnoDB;


CREATE TABLE juego_equipo (
       id_juego_equipo INT(11) NOT NULL AUTO_INCREMENT
     , id_juego INT(11) NOT NULL
     , id_equipo INT(11) NOT NULL
     , ganador INT(11)
     , puntos INT(11)
     , referencia INT(11) NOT NULL
     , referencia_runline INT(11) NOT NULL
     , referencia_ab VARCHAR(10) NOT NULL
     , PRIMARY KEY (id_juego_equipo)
     , INDEX (id_equipo)
     , CONSTRAINT FK_juego_equipo_2 FOREIGN KEY (id_equipo)
                  REFERENCES equipo (id_equipo) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_juego)
     , CONSTRAINT FK_juego_equipo_1 FOREIGN KEY (id_juego)
                  REFERENCES juego (id_juego) ON DELETE NO ACTION ON UPDATE NO ACTION
)TYPE=InnoDB;

CREATE TABLE usuario_juego_equipo (
       id_usuario_juego_equipo INT(11) NOT NULL AUTO_INCREMENT
     , id_usuario INT(11) NOT NULL
     , id_juego_equipo INT(11) NOT NULL
     , fecha_sis DATETIME NOT NULL
     , spread DOUBLE
     , spread_logro INT(11)
     , total DOUBLE
     , total_logro INT(11)
     , money_line INT(11)
     , id_status_juego INT(11) NOT NULL
     , PRIMARY KEY (id_usuario_juego_equipo)
     , INDEX (id_status_juego)
     , CONSTRAINT FK_usuario_juego_equipo_3 FOREIGN KEY (id_status_juego)
                  REFERENCES status_juego (id_status_juego) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_usuario)
     , CONSTRAINT FK_usuario_juego_equipo_1 FOREIGN KEY (id_usuario)
                  REFERENCES usuario (id_usuario) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_juego_equipo)
     , CONSTRAINT FK_usuario_juego_equipo_2 FOREIGN KEY (id_juego_equipo)
                  REFERENCES juego_equipo (id_juego_equipo) ON DELETE NO ACTION ON UPDATE NO ACTION
)TYPE=InnoDB;

CREATE TABLE equipo_liga (
       id_equipo INT(11) NOT NULL
     , id_liga INT(11) NOT NULL
     , PRIMARY KEY (id_equipo, id_liga)
     , INDEX (id_equipo)
     , CONSTRAINT FK_equipo_liga_3 FOREIGN KEY (id_equipo)
                  REFERENCES equipo (id_equipo) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_liga)
     , CONSTRAINT FK_equipo_liga_2 FOREIGN KEY (id_liga)
                  REFERENCES liga (id_liga) ON DELETE NO ACTION ON UPDATE NO ACTION
)TYPE=InnoDB;

CREATE TABLE juego_equipo_lanzador (
       id_juego_equipo INT(11) NOT NULL
     , id_lanzador INT(11) NOT NULL
     , PRIMARY KEY (id_juego_equipo, id_lanzador)
     , INDEX (id_lanzador)
     , CONSTRAINT FK_juego_equipo_lanzador_2 FOREIGN KEY (id_lanzador)
                  REFERENCES lanzador (id_lanzador) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_juego_equipo)
     , CONSTRAINT FK_juego_equipo_lanzador_1 FOREIGN KEY (id_juego_equipo)
                  REFERENCES juego_equipo (id_juego_equipo) ON DELETE NO ACTION ON UPDATE NO ACTION
)TYPE=InnoDB;

CREATE TABLE jugada_juego_equipo (
       id_jugada_juego_equipo INT(11) NOT NULL AUTO_INCREMENT
     , id_jugada INT(11) NOT NULL
     , id_usuario_juego_equipo INT(11) NOT NULL
     , tipo VARCHAR(2) NOT NULL
     , id_status_jugada INT(11) NOT NULL
     , PRIMARY KEY (id_jugada_juego_equipo)
     , INDEX (id_status_jugada)
     , CONSTRAINT FK_jugada_juego_equipo_4 FOREIGN KEY (id_status_jugada)
                  REFERENCES status_jugada (id_status_jugada) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_usuario_juego_equipo)
     , CONSTRAINT FK_jugada_juego_equipo_2 FOREIGN KEY (id_usuario_juego_equipo)
                  REFERENCES usuario_juego_equipo (id_usuario_juego_equipo) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_jugada)
     , CONSTRAINT FK_jugada_juego_equipo_3 FOREIGN KEY (id_jugada)
                  REFERENCES jugada (id_jugada) ON DELETE NO ACTION ON UPDATE NO ACTION
)TYPE=InnoDB;

CREATE TABLE menu_user (
       id_menu INT(11) NOT NULL
     , id_usuario INT(11) NOT NULL
     , PRIMARY KEY (id_menu, id_usuario)
     , INDEX (id_usuario)
     , CONSTRAINT FK_opcion_user_2 FOREIGN KEY (id_usuario)
                  REFERENCES usuario (id_usuario) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_menu)
     , CONSTRAINT FK_opcion_user_1 FOREIGN KEY (id_menu)
                  REFERENCES menu (id_menu) ON DELETE NO ACTION ON UPDATE NO ACTION
)TYPE=InnoDB;

CREATE TABLE rol_menu (
       id_menu INT(11) NOT NULL
     , id_rol INT(11) NOT NULL
     , PRIMARY KEY (id_menu, id_rol)
     , INDEX (id_rol)
     , CONSTRAINT FK_rol_menu_3 FOREIGN KEY (id_rol)
                  REFERENCES rol (id_rol) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_menu)
     , CONSTRAINT FK_rol_menu_2 FOREIGN KEY (id_menu)
                  REFERENCES menu (id_menu) ON DELETE NO ACTION ON UPDATE NO ACTION
)TYPE=InnoDB;

CREATE TABLE usuario_admin (
       id_usuario INT(11) NOT NULL
     , id_admin INT(11) NOT NULL
     , PRIMARY KEY (id_admin, id_usuario)
     , INDEX (id_usuario)
     , CONSTRAINT FK_usuario_admin_1 FOREIGN KEY (id_usuario)
                  REFERENCES usuario (id_usuario) ON DELETE NO ACTION ON UPDATE NO ACTION
)TYPE=InnoDB;

CREATE TABLE usuario_juego (
       id_usuario INT(11) NOT NULL
     , id_juego INT(11) NOT NULL
     , fecha_sis DATETIME NOT NULL
     , id_status_juego INT(11) NOT NULL
     , PRIMARY KEY (id_juego, id_usuario)
     , INDEX (id_status_juego)
     , CONSTRAINT FK_usuario_juego_3 FOREIGN KEY (id_status_juego)
                  REFERENCES status_juego (id_status_juego) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_usuario)
     , CONSTRAINT FK_usuario_juego_1 FOREIGN KEY (id_usuario)
                  REFERENCES usuario (id_usuario) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_juego)
     , CONSTRAINT FK_usuario_juego_2 FOREIGN KEY (id_juego)
                  REFERENCES juego (id_juego) ON DELETE NO ACTION ON UPDATE NO ACTION
)TYPE=InnoDB;

CREATE TABLE cuenta_jugador (
       id_cuenta INT(11) NOT NULL AUTO_INCREMENT
     , fecha_sis DATETIME NOT NULL
     , referencia VARCHAR(10)
     , operacion VARCHAR(1) NOT NULL
     , monto DOUBLE NOT NULL
     , concepto VARCHAR(100)
     , id_jugador INT(11)
     , id_usuario INT(11) NOT NULL
     , tipo VARCHAR(1)
     , PRIMARY KEY (id_cuenta)
     , INDEX (id_usuario)
     , CONSTRAINT FK_cuenta_jugador_2 FOREIGN KEY (id_usuario)
                  REFERENCES usuario (id_usuario) ON DELETE NO ACTION ON UPDATE NO ACTION
     , INDEX (id_jugador)
     , CONSTRAINT FK_cuenta_jugador_1 FOREIGN KEY (id_jugador)
                  REFERENCES usuario (id_usuario) ON DELETE NO ACTION ON UPDATE NO ACTION
)TYPE=InnoDB;

