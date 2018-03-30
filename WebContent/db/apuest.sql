CREATE TABLE status (
       id_status INT NOT NULL
     , desc_status VARCHAR(30) NOT NULL
     , UNIQUE UQ_status_1 (desc_status)
     , PRIMARY KEY (id_status)
)TYPE=InnoDB;

CREATE TABLE tipo_cuenta (
       id_tipo_cuenta INT NOT NULL
     , desc_tipo_cuenta VARCHAR(30) NOT NULL
     , PRIMARY KEY (id_tipo_cuenta)
)TYPE=InnoDB;

CREATE TABLE equipo (
       id_equipo INT NOT NULL AUTO_INCREMENT
     , desc_equipo VARCHAR(100) NOT NULL
     , abreviatura VARCHAR(10) NOT NULL
     , desc_corta VARCHAR(100) NOT NULL
     , UNIQUE UQ_equipo_1 (desc_equipo)
     , PRIMARY KEY (id_equipo)
)TYPE=InnoDB;

CREATE TABLE campeonato (
       id_campeonato INT NOT NULL AUTO_INCREMENT
     , desc_campeonato VARCHAR(200) NOT NULL
     , UNIQUE UQ_campeonato_1 (desc_campeonato)
     , PRIMARY KEY (id_campeonato)
)TYPE=InnoDB;

CREATE TABLE status_juego (
       id_status_juego INT NOT NULL
     , desc_status_juego VARCHAR(30)
     , UNIQUE UQ_status_juego_1 (desc_status_juego)
     , PRIMARY KEY (id_status_juego)
)TYPE=InnoDB;

CREATE TABLE opcion (
       id_opcion INT NOT NULL
     , desc_opcion VARCHAR(100) NOT NULL
     , orden INT NOT NULL
     , accion VARCHAR(200)
     , imagen VARCHAR(200)
     , UNIQUE UQ_menu_1 (desc_opcion)
     , PRIMARY KEY (id_opcion)
)TYPE=InnoDB;

CREATE TABLE status_apuesta (
       id_status_apuesta INT NOT NULL AUTO_INCREMENT
     , desc_apuesta VARCHAR(30) NOT NULL
     , UNIQUE UQ_status_apuesta_1 (desc_apuesta)
     , PRIMARY KEY (id_status_apuesta)
)TYPE=InnoDB;

CREATE TABLE status_deporte (
       id_status_deporte INT NOT NULL
     , desc_status_deporte VARCHAR(30)
     , UNIQUE UQ_status_deporte_1 (desc_status_deporte)
     , PRIMARY KEY (id_status_deporte)
)TYPE=InnoDB;

CREATE TABLE logros (
       favorito INT NOT NULL
     , hembra INT NOT NULL
     , PRIMARY KEY (favorito, hembra)
);

CREATE TABLE reglas_pago (
       id_reglas_pago INT NOT NULL
     , rango_ini DOUBLE NOT NULL
     , rango_fin DOUBLE NOT NULL
     , multiplo INT NOT NULL
     , monto_maximo DOUBLE NOT NULL
     , PRIMARY KEY (id_reglas_pago)
);

CREATE TABLE parametros (
       nombre VARCHAR(20) NOT NULL
     , valor VARCHAR(250)
     , PRIMARY KEY (nombre)
);

CREATE TABLE perfil (
       id_perfil INT NOT NULL
     , desc_perfil VARCHAR(50) NOT NULL
     , UNIQUE UQ_rol_1 (desc_perfil)
     , PRIMARY KEY (id_perfil)
)TYPE=InnoDB;

CREATE TABLE usuario (
       id_usuario INT NOT NULL AUTO_INCREMENT
     , cedula VARCHAR(10) NOT NULL
     , apellido VARCHAR(50) NOT NULL
     , nombre VARCHAR(50) NOT NULL
     , usuario VARCHAR(20) NOT NULL
     , clave VARCHAR(50) NOT NULL
     , correo VARCHAR(100) NOT NULL
     , dias_venc_ticket INT NOT NULL
     , centro_hipico VARCHAR(100)
     , rif VARCHAR(12)
     , telefono VARCHAR(12)
     , celular VARCHAR(12)
     , banco VARCHAR(50)
     , numero_cuenta VARCHAR(20)
     , titular_cuenta VARCHAR(50)
     , logros_alta_baja INT
     , logros_calc INT NOT NULL
     , monto DOUBLE
     , id_perfil INT NOT NULL
     , id_status INT NOT NULL
     , id_tipo_cuenta INT NOT NULL
     , id_supervisor INT NOT NULL
     , ticket_nota VARCHAR(1000)
     , UNIQUE UQ_usuario_correo (correo)
     , UNIQUE UQ_usuario_cedula (cedula)
     , PRIMARY KEY (id_usuario)
)TYPE=InnoDB;
CREATE UNIQUE INDEX IX_usuario_clave ON usuario (usuario ASC, clave ASC);

CREATE TABLE liga (
       id_liga INT NOT NULL AUTO_INCREMENT
     , desc_liga VARCHAR(100) NOT NULL
     , iniciales VARCHAR(20) NOT NULL
     , id_deporte INT NOT NULL
     , UNIQUE UQ_liga_1 (desc_liga)
     , PRIMARY KEY (id_liga)
)TYPE=InnoDB;

CREATE TABLE juego (
       id_juego INT NOT NULL AUTO_INCREMENT
     , fecha_sis DATETIME NOT NULL
     , fecha_ini DATETIME NOT NULL
     , fecha_fin DATETIME
     , minutos_cierre INT NOT NULL
     , id_campeonato INT NOT NULL
     , id_status_juego INT NOT NULL
     , id_usuario INT NOT NULL
     , id_deporte INT NOT NULL
     , id_liga INT NOT NULL
     , spread_activo INT(1) NOT NULL DEFAULT 1
     , total_activo INT(1) NOT NULL DEFAULT 1
     , money_activo INT(1) NOT NULL DEFAULT 1
     , id_juego_padre INT
     , id_usuario_creador INT NOT NULL
     , PRIMARY KEY (id_juego)
)TYPE=InnoDB;

CREATE TABLE juego_equipo (
       id_juego_equipo INT NOT NULL AUTO_INCREMENT
     , id_juego INT NOT NULL
     , id_equipo INT NOT NULL
     , ganador INT
     , puntos INT
     , referencia INT NOT NULL
     , referencia_runline INT NOT NULL
     , referencia_ab VARCHAR(10) NOT NULL
     , PRIMARY KEY (id_juego_equipo)
)TYPE=InnoDB;

CREATE TABLE apuesta (
       id_apuesta INT NOT NULL AUTO_INCREMENT
     , fecha_sis DATETIME NOT NULL
     , fecha_exp DATETIME
     , monto_apostado DOUBLE NOT NULL
     , monto_premio DOUBLE NOT NULL
     , monto_pagado DOUBLE
     , id_usuario INT NOT NULL
     , id_status_apuesta INT NOT NULL
     , dias_expira INT NOT NULL
     , fecha_pago DATETIME
     , detalle_equipo VARCHAR(1000)
     , PRIMARY KEY (id_apuesta)
)TYPE=InnoDB;

CREATE TABLE deporte (
       id_deporte INT NOT NULL AUTO_INCREMENT
     , desc_deporte VARCHAR(50) NOT NULL
     , empate INT NOT NULL
     , id_status_deporte INT NOT NULL
     , referencia_inicio INT
     , runline_inicio DOUBLE
     , UNIQUE UQ_deporte_1 (desc_deporte)
     , PRIMARY KEY (id_deporte)
)TYPE=InnoDB;

CREATE TABLE lanzador (
       id_lanzador INT NOT NULL AUTO_INCREMENT
     , nombre_lanzador VARCHAR(50) NOT NULL
     , id_equipo INT NOT NULL
     , PRIMARY KEY (id_lanzador)
)TYPE=InnoDB;

CREATE TABLE usuario_juego_equipo (
       id_usuario_juego_equipo INT NOT NULL AUTO_INCREMENT
     , id_usuario INT NOT NULL
     , id_juego_equipo INT NOT NULL
     , fecha_sis DATETIME NOT NULL
     , spread DOUBLE
     , spread_logro INT
     , total DOUBLE
     , total_logro INT
     , money_line INT
     , id_status_juego INT NOT NULL
     , PRIMARY KEY (id_usuario_juego_equipo)
)TYPE=InnoDB;

CREATE TABLE equipo_liga (
       id_equipo INT NOT NULL
     , id_liga INT NOT NULL
     , PRIMARY KEY (id_liga, id_equipo)
)TYPE=InnoDB;

CREATE TABLE apuesta_juego_equipo (
       id_apuesta_juego_equipo INT NOT NULL AUTO_INCREMENT
     , id_apuesta INT NOT NULL
     , id_usuario_juego_equipo INT NOT NULL
     , tipo VARCHAR(2) NOT NULL
     , id_status_apuesta INT NOT NULL
     , PRIMARY KEY (id_apuesta_juego_equipo)
)TYPE=InnoDB;

CREATE TABLE usuario_juego (
       id_usuario INT NOT NULL
     , id_juego INT NOT NULL
     , fecha_sis DATETIME NOT NULL
     , id_status_juego INT NOT NULL
     , PRIMARY KEY (id_usuario, id_juego)
)TYPE=InnoDB;

CREATE TABLE juego_equipo_lanzador (
       id_juego_equipo INT NOT NULL
     , id_lanzador INT NOT NULL
     , PRIMARY KEY (id_juego_equipo, id_lanzador)
)TYPE=InnoDB;

CREATE TABLE cuenta_jugador (
       id_cuenta INT NOT NULL AUTO_INCREMENT
     , fecha_sis DATETIME NOT NULL
     , referencia VARCHAR(10)
     , operacion VARCHAR(1) NOT NULL
     , monto DOUBLE NOT NULL
     , concepto VARCHAR(100)
     , id_jugador INT
     , id_usuario INT NOT NULL
     , tipo VARCHAR(1)
     , PRIMARY KEY (id_cuenta)
)TYPE=InnoDB;

CREATE TABLE perfil_opcion (
       id_opcion INT NOT NULL
     , id_perfil INT NOT NULL
     , PRIMARY KEY (id_opcion, id_perfil)
)TYPE=InnoDB;

ALTER TABLE usuario
  ADD CONSTRAINT FK_usuario_1
      FOREIGN KEY (id_perfil)
      REFERENCES perfil (id_perfil);

ALTER TABLE usuario
  ADD CONSTRAINT FK_usuario_2
      FOREIGN KEY (id_status)
      REFERENCES status (id_status);

ALTER TABLE usuario
  ADD CONSTRAINT FK_usuario_3
      FOREIGN KEY (id_tipo_cuenta)
      REFERENCES tipo_cuenta (id_tipo_cuenta);

ALTER TABLE usuario
  ADD CONSTRAINT FK_usuario_supervisor
      FOREIGN KEY (id_supervisor)
      REFERENCES usuario (id_usuario);

ALTER TABLE liga
  ADD CONSTRAINT FK_liga_1
      FOREIGN KEY (id_deporte)
      REFERENCES deporte (id_deporte);

ALTER TABLE juego
  ADD CONSTRAINT FK_juego_1
      FOREIGN KEY (id_campeonato)
      REFERENCES campeonato (id_campeonato);

ALTER TABLE juego
  ADD CONSTRAINT FK_juego_2
      FOREIGN KEY (id_status_juego)
      REFERENCES status_juego (id_status_juego);

ALTER TABLE juego
  ADD CONSTRAINT FK_juego_3
      FOREIGN KEY (id_usuario)
      REFERENCES usuario (id_usuario);

ALTER TABLE juego
  ADD CONSTRAINT FK_juego_4
      FOREIGN KEY (id_deporte)
      REFERENCES deporte (id_deporte);

ALTER TABLE juego
  ADD CONSTRAINT FK_juego_5
      FOREIGN KEY (id_liga)
      REFERENCES liga (id_liga);

ALTER TABLE juego
  ADD CONSTRAINT FK_juego_padre
      FOREIGN KEY (id_juego_padre)
      REFERENCES juego (id_juego);

ALTER TABLE juego_equipo
  ADD CONSTRAINT FK_juego_equipo_1
      FOREIGN KEY (id_juego)
      REFERENCES juego (id_juego);

ALTER TABLE juego_equipo
  ADD CONSTRAINT FK_juego_equipo_2
      FOREIGN KEY (id_equipo)
      REFERENCES equipo (id_equipo);

ALTER TABLE apuesta
  ADD CONSTRAINT FK_apuesta_3
      FOREIGN KEY (id_usuario)
      REFERENCES usuario (id_usuario);

ALTER TABLE apuesta
  ADD CONSTRAINT FK_apuesta_4
      FOREIGN KEY (id_usuario)
      REFERENCES usuario (id_usuario);

ALTER TABLE apuesta
  ADD CONSTRAINT FK_apuesta_5
      FOREIGN KEY (id_status_apuesta)
      REFERENCES status_apuesta (id_status_apuesta);

ALTER TABLE deporte
  ADD CONSTRAINT FK_deporte_1
      FOREIGN KEY (id_status_deporte)
      REFERENCES status_deporte (id_status_deporte);

ALTER TABLE lanzador
  ADD CONSTRAINT FK_lanzador_1
      FOREIGN KEY (id_equipo)
      REFERENCES equipo (id_equipo);

ALTER TABLE usuario_juego_equipo
  ADD CONSTRAINT FK_usuario_juego_equipo_1
      FOREIGN KEY (id_usuario)
      REFERENCES usuario (id_usuario);

ALTER TABLE usuario_juego_equipo
  ADD CONSTRAINT FK_usuario_juego_equipo_2
      FOREIGN KEY (id_juego_equipo)
      REFERENCES juego_equipo (id_juego_equipo);

ALTER TABLE usuario_juego_equipo
  ADD CONSTRAINT FK_usuario_juego_equipo_3
      FOREIGN KEY (id_status_juego)
      REFERENCES status_juego (id_status_juego);

ALTER TABLE equipo_liga
  ADD CONSTRAINT FK_equipo_liga_2
      FOREIGN KEY (id_liga)
      REFERENCES liga (id_liga);

ALTER TABLE equipo_liga
  ADD CONSTRAINT FK_equipo_liga_3
      FOREIGN KEY (id_equipo)
      REFERENCES equipo (id_equipo);

ALTER TABLE apuesta_juego_equipo
  ADD CONSTRAINT FK_apuesta_juego_equipo_3
      FOREIGN KEY (id_apuesta)
      REFERENCES apuesta (id_apuesta);

ALTER TABLE apuesta_juego_equipo
  ADD CONSTRAINT FK_apuesta_juego_equipo_2
      FOREIGN KEY (id_usuario_juego_equipo)
      REFERENCES usuario_juego_equipo (id_usuario_juego_equipo);

ALTER TABLE apuesta_juego_equipo
  ADD CONSTRAINT FK_apuesta_juego_equipo_4
      FOREIGN KEY (id_status_apuesta)
      REFERENCES status_apuesta (id_status_apuesta);

ALTER TABLE usuario_juego
  ADD CONSTRAINT FK_usuario_juego_1
      FOREIGN KEY (id_usuario)
      REFERENCES usuario (id_usuario);

ALTER TABLE usuario_juego
  ADD CONSTRAINT FK_usuario_juego_2
      FOREIGN KEY (id_juego)
      REFERENCES juego (id_juego);

ALTER TABLE usuario_juego
  ADD CONSTRAINT FK_usuario_juego_3
      FOREIGN KEY (id_status_juego)
      REFERENCES status_juego (id_status_juego);

ALTER TABLE juego_equipo_lanzador
  ADD CONSTRAINT FK_juego_equipo_lanzador_1
      FOREIGN KEY (id_juego_equipo)
      REFERENCES juego_equipo (id_juego_equipo);

ALTER TABLE juego_equipo_lanzador
  ADD CONSTRAINT FK_juego_equipo_lanzador_2
      FOREIGN KEY (id_lanzador)
      REFERENCES lanzador (id_lanzador);

ALTER TABLE cuenta_jugador
  ADD CONSTRAINT FK_cuenta_jugador_1
      FOREIGN KEY (id_jugador)
      REFERENCES usuario (id_usuario);

ALTER TABLE cuenta_jugador
  ADD CONSTRAINT FK_cuenta_jugador_2
      FOREIGN KEY (id_usuario)
      REFERENCES usuario (id_usuario);

ALTER TABLE perfil_opcion
  ADD CONSTRAINT FK_rol_menu_2
      FOREIGN KEY (id_opcion)
      REFERENCES opcion (id_opcion);

ALTER TABLE perfil_opcion
  ADD CONSTRAINT FK_rol_menu_3
      FOREIGN KEY (id_perfil)
      REFERENCES perfil (id_perfil);

