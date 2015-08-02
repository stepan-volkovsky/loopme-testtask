create database loopme;
create user 'loopme'@'localhost' identified by 'loopme';
grant all privileges on loopme.* to 'loopme'@'localhost';
use loopme;

CREATE TABLE `Creatives` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `os` varchar(255) DEFAULT NULL,
  `countries` varchar(255) DEFAULT NULL,
  `excluded_os` varchar(255) DEFAULT NULL,
  `excluded_countries` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
