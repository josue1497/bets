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

