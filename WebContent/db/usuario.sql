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
     , comision_venta DOUBLE NOT NULL
     , otros_gastos DOUBLE NOT NULL
     , tipo INT NOT NULL
     , mac_address VARCHAR(255)
     , validar_mac_address INT NOT NULL DEFAULT 0
     , UNIQUE UQ_usuario_correo (correo)
     , UNIQUE UQ_usuario_cedula (cedula)
     , PRIMARY KEY (id_usuario)
)TYPE=InnoDB;
CREATE UNIQUE INDEX IX_usuario_clave ON usuario (usuario ASC, clave ASC);

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

