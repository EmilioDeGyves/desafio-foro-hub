-- Eliminar la restricción de clave foránea
ALTER TABLE Usuario DROP FOREIGN KEY usuario_ibfk_1;

-- Eliminar la columna 'perfiles'
ALTER TABLE Usuario DROP COLUMN perfiles;