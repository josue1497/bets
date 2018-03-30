CREATE TABLE usuario_admin (
       id_usuario INT NOT NULL
     , id_admin INT NOT NULL
     , PRIMARY KEY (id_usuario, id_admin)
)TYPE=InnoDB;

ALTER TABLE usuario_admin
  ADD CONSTRAINT FK_usuario_admin_1
      FOREIGN KEY (id_usuario)
      REFERENCES usuario (id_usuario);

