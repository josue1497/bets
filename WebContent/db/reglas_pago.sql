CREATE TABLE reglas_pago (
       id_reglas_pago INT NOT NULL
     , rango_ini DOUBLE NOT NULL
     , rango_fin DOUBLE NOT NULL
     , multiplo INT NOT NULL
     , monto_maximo DOUBLE NOT NULL
     , PRIMARY KEY (id_reglas_pago)
);

