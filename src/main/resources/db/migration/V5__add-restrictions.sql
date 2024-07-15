ALTER TABLE Topico ADD CONSTRAINT UC_Titulo UNIQUE (titulo);
ALTER TABLE Topico ADD CONSTRAINT UC_Mensaje UNIQUE (mensaje(255));
alter table topico CHANGE COLUMN fechaCreacion fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
alter table respuesta CHANGE COLUMN fechaCreacion fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP;