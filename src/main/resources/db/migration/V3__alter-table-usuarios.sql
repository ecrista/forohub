ALTER TABLE `usuarios`
ADD COLUMN `activo` TINYINT NOT NULL AFTER `contrasena`;
UPDATE usuarios set activo = 1;