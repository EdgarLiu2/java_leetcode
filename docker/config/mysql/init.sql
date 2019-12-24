CREATE DATABASE test;
USE test;
CREATE TABLE `test`.`item` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `count` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

insert into `test`.`item`(name, count) values("item1", 10);
insert into `test`.`item`(name, count) values("item2", 20);

