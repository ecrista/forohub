-- -----------------------------------------------------
-- Table `cursos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cursos` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `categoria` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `correo_electronico` VARCHAR(100) NOT NULL,
  `contrasena` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `correo_electronico_UNIQUE` (`correo_electronico` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `topicos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `topicos` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(255) NOT NULL,
  `mensaje` VARCHAR(255) NOT NULL,
  `fecha_creacion` DATETIME NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `usuarios_id` BIGINT NOT NULL,
  `cursos_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_topicos_usuarios1_idx` (`usuarios_id` ASC) VISIBLE,
  INDEX `fk_topicos_cursos1_idx` (`cursos_id` ASC) VISIBLE,
  CONSTRAINT `fk_topicos_usuarios1`
    FOREIGN KEY (`usuarios_id`)
    REFERENCES `usuarios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_topicos_cursos1`
    FOREIGN KEY (`cursos_id`)
    REFERENCES `cursos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `respuestas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `respuestas` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `mensaje` VARCHAR(255) NOT NULL,
  `fecha_creacion` DATETIME NOT NULL,
  `solucion` VARCHAR(255) NOT NULL,
  `topicos_id` BIGINT NOT NULL,
  `usuarios_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_respuestas_topicos1_idx` (`topicos_id` ASC) VISIBLE,
  INDEX `fk_respuestas_usuarios1_idx` (`usuarios_id` ASC) VISIBLE,
  CONSTRAINT `fk_respuestas_topicos1`
    FOREIGN KEY (`topicos_id`)
    REFERENCES `topicos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_respuestas_usuarios1`
    FOREIGN KEY (`usuarios_id`)
    REFERENCES `usuarios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `perfiles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `perfiles` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `usuarios_has_perfiles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usuarios_has_perfiles` (
  `usuarios_id` BIGINT NOT NULL,
  `perfiles_id` BIGINT NOT NULL,
  PRIMARY KEY (`usuarios_id`, `perfiles_id`),
  INDEX `fk_usuarios_has_perfiles_perfiles1_idx` (`perfiles_id` ASC) VISIBLE,
  INDEX `fk_usuarios_has_perfiles_usuarios_idx` (`usuarios_id` ASC) VISIBLE,
  CONSTRAINT `fk_usuarios_has_perfiles_usuarios`
    FOREIGN KEY (`usuarios_id`)
    REFERENCES `usuarios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuarios_has_perfiles_perfiles1`
    FOREIGN KEY (`perfiles_id`)
    REFERENCES `perfiles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)