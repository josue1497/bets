CREATE TABLE equipo (
       id_equipo INT NOT NULL AUTO_INCREMENT
     , desc_equipo VARCHAR(100) NOT NULL
     , abreviatura VARCHAR(10) NOT NULL
     , desc_corta VARCHAR(100) NOT NULL
     , UNIQUE UQ_equipo_1 (desc_equipo)
     , PRIMARY KEY (id_equipo)
)TYPE=InnoDB;

