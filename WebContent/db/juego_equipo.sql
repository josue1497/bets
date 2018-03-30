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

ALTER TABLE juego_equipo
  ADD CONSTRAINT FK_juego_equipo_1
      FOREIGN KEY (id_juego)
      REFERENCES juego (id_juego);

ALTER TABLE juego_equipo
  ADD CONSTRAINT FK_juego_equipo_2
      FOREIGN KEY (id_equipo)
      REFERENCES equipo (id_equipo);

