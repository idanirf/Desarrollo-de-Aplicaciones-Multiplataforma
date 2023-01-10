
DROP DATABASE IF EXISTS liga;
CREATE DATABASE liga;
USE liga;

--
-- Definition of table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
CREATE TABLE `equipo` (
  `nombre` char(8) NOT NULL DEFAULT '',
  `ciudad` char(8) DEFAULT NULL,
  `puesto` int(2) DEFAULT NULL,
  `pg` int(2) DEFAULT NULL,
  `pp` int(2) DEFAULT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `equipo`
--

INSERT INTO `equipo` (`nombre`,`ciudad`,`puesto`,`pg`,`pp`) VALUES 
 ('bar','barce',3,4,6),
 ('mad','madrid',9,2,8),
 ('cai','zgz',1,10,0),
 ('penas','huesca',10,0,10),
 ('tau','madrid',5,3,7),
 ('juv','null',2,5,2),
 ('ali','alican',9,3,0),
 ('sev',NULL,NULL,NULL,0);



--
-- Definition of table `jugador`
--

DROP TABLE IF EXISTS `jugador`;
CREATE TABLE `jugador` (
  `id_jugador` char(8) NOT NULL DEFAULT '',
  `puntos_total` int(9) DEFAULT NULL,
  `minutos` int(4) DEFAULT NULL,
  `altura` int(5) DEFAULT NULL,
  `equipo` char(8) DEFAULT NULL,
  PRIMARY KEY (`id_jugador`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `jugador`
--

INSERT INTO `jugador` (`id_jugador`,`puntos_total`,`minutos`,`altura`,`equipo`) VALUES 
 ('ant',100,300,200,'cai'),
 ('fel',22,200,185,'cai'),
 ('gen',111,120,199,'cai'),
 ('cas',212,250,210,'cai'),
 ('arc',90,320,200,'cai'),
 ('jav',999,245,198,'mad'),
 ('vic',442,166,205,'mad'),
 ('jan',999,245,206,'mad'),
 ('ars',99,311,190,'mad'),
 ('cor',320,213,193,'mad'),
 ('jua',234,20,198,'bar'),
 ('jai',444,300,198,'bar'),
 ('edu',54,299,179,'bar'),
 ('lui',67,212,188,'bar'),
 ('raf',232,201,199,'bar'),
 ('pac',999,245,198,'tau'),
 ('jau',354,21,167,'tau'),
 ('fra',655,333,209,'tau'),
 ('fal',311,277,213,'tau'),
 ('fab',412,24,187,'tau'),
 ('amt',333,260,190,'penas'),
 ('jor',243,423,177,'penas');

--
-- Definition of table `partido`
--

DROP TABLE IF EXISTS `partido`;
CREATE TABLE `partido` (
  `fecha` date DEFAULT NULL,
  `local` char(8) NOT NULL DEFAULT '',
  `visitante` char(8) NOT NULL DEFAULT '',
  `resultado` varchar(7) DEFAULT NULL,
  `ptos_total` int(11) DEFAULT NULL,
  `max_enc` char(8) DEFAULT NULL,
  PRIMARY KEY (`local`,`visitante`),
  KEY `visitante` (`visitante`),
  KEY `max_enc` (`max_enc`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `partido`
--

INSERT INTO `partido` (`fecha`,`local`,`visitante`,`resultado`,`ptos_total`,`max_enc`) VALUES 
 ('2009-01-01','mad','bar','100-80',180,'jav'),
 ('2009-01-01','mad','cai','67-80',180,'ant'),
 ('2009-01-01','mad','penas','67-80',180,'ant'),
 ('2009-02-08','bar','cai','77-90',280,'edu'),
 ('2009-02-18','bar','penas','77-120',197,'amt'),
 ('2009-02-18','bar','tau','77-50',127,'fab'),
 ('2009-02-18','juv','ali','73-80',127,NULL),
 ('2009-03-18','juv','sev','93-80',127,NULL),
 ('2009-03-18','penas','tau','103-70',173,NULL),
 ('2009-04-18','penas','cai','13-170',183,NULL),
 ('2009-05-18','penas','mad','130-100',233,NULL),
 ('2009-05-18','penas','bar','130-130',260,'jor'),
 ('2009-05-06','cai','bar','130-90',260,'gen'),
 ('2009-05-22','cai','tau','122-110',232,'gen'),
 ('2009-12-22','cai','mad','99-77',232,'cas'),
 ('2009-07-01','cai','juv','99-77',176,'edu'),
 ('2013-02-01','mad','tau','90-70',160,'fal'),
 ('2013-02-02','sev','tau','90-70',160,'fal'),
 ('2013-02-01','tau','mad','90-70',160,'fal');



