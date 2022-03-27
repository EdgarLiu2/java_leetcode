CREATE DATABASE IF NOT EXISTS mybatis DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
USE mybatis;

CREATE TABLE IF NOT EXISTS `mybatis`.`tbl_user`
(
    `id`       INT UNSIGNED AUTO_INCREMENT,
    `username` VARCHAR(20) NOT NULL,
    `password` VARCHAR(20),
    `age`      TINYINT,
    `sex`      TINYINT COMMENT '0：未知，1：男，2：女',
    `email`    VARCHAR(50),
    PRIMARY KEY (`id`),
    INDEX `idx_username_password` (`username`, `password`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

EXPLAIN
select *
from tbl_user
where username = '何志泽';
EXPLAIN
select count(*)
from tbl_user;

DROP TABLE `mybatis`.`tbl_employee`;
CREATE TABLE IF NOT EXISTS `mybatis`.`tbl_employee`
(
    `id`      INT UNSIGNED AUTO_INCREMENT,
    `name`    VARCHAR(50) NOT NULL,
    `age`     TINYINT,
    `sex`     TINYINT COMMENT '0：未知，1：男，2：女',
    `email`   VARCHAR(50),
    `dept_id` INT UNSIGNED,
    PRIMARY KEY (`id`),
    INDEX `idx_name` (`name`),
    INDEX `idx_dept_id` (`dept_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

DROP TABLE `mybatis`.`tbl_department`;
CREATE TABLE IF NOT EXISTS `mybatis`.`tbl_department`
(
    `id`   INT UNSIGNED AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `idx_name` (`name`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;