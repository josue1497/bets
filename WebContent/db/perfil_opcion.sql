CREATE TABLE perfil_opcion (
       id_opcion INT NOT NULL
     , id_perfil INT NOT NULL
     , users VARCHAR(4000) NOT NULL
     , PRIMARY KEY (id_opcion, id_perfil)
)TYPE=InnoDB;

ALTER TABLE perfil_opcion
  ADD CONSTRAINT FK_rol_menu_2
      FOREIGN KEY (id_opcion)
      REFERENCES opcion (id_opcion);

ALTER TABLE perfil_opcion
  ADD CONSTRAINT FK_rol_menu_3
      FOREIGN KEY (id_perfil)
      REFERENCES perfil (id_perfil);

