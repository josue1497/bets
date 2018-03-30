CREATE TABLE opcion_user (
       id_opcion INT NOT NULL
     , id_usuario INT NOT NULL
     , UNIQUE UQ_opcion_user_1 (id_opcion, id_usuario)
     , PRIMARY KEY (id_opcion, id_usuario)
)TYPE=InnoDB;

ALTER TABLE opcion_user
  ADD CONSTRAINT FK_opcion_user_1
      FOREIGN KEY (id_opcion)
      REFERENCES opcion (id_opcion);

ALTER TABLE opcion_user
  ADD CONSTRAINT FK_opcion_user_2
      FOREIGN KEY (id_usuario)
      REFERENCES usuario (id_usuario);

