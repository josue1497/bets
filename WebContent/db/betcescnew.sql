-- MySQL dump 10.10
--
-- Host: localhost    Database: betcescnew
-- ------------------------------------------------------
-- Server version	5.0.24a-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `campeonato`
--

DROP TABLE IF EXISTS `campeonato`;
CREATE TABLE `campeonato` (
  `id_campeonato` int(11) NOT NULL auto_increment,
  `desc_campeonato` varchar(200) NOT NULL,
  PRIMARY KEY  (`id_campeonato`),
  UNIQUE KEY `UQ_campeonato_1` (`desc_campeonato`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `campeonato`
--


/*!40000 ALTER TABLE `campeonato` DISABLE KEYS */;
LOCK TABLES `campeonato` WRITE;
INSERT INTO `campeonato` VALUES (42,'Amistosos Internacionales'),(22,'ATP-Matchups'),(39,'Australian Open'),(10,'Basket Olimpico MENS'),(38,'CABALLO CONTRA CABALLO'),(14,'Calcio Serie A'),(32,'CHAMPIONSHIP GAME'),(46,'Clasico Mundial'),(49,'Confederaciones'),(33,'Copa del Rey'),(35,'Copa Italia'),(41,'Copa Libertadores'),(29,'Copa Maltin'),(43,'Copa Uefa'),(50,'Eliminatorias Europeas 2010'),(16,'Eliminatorias Sudafrica 2010'),(4,'Euro Campeon'),(36,'FA Copa'),(30,'FIESTA BOWL'),(61,'formula 1'),(31,'GMAC BOWL'),(24,'Gran Premio De Brasil'),(18,'La Liga'),(15,'La Liga EspaÃ±ola'),(54,'LBPV'),(21,'Liga Beisbol Venezolano'),(56,'Liga Mexicana De Beisbol'),(47,'Ligero del CBM'),(28,'LPBV'),(27,'LVBP'),(26,'MGM GRAND, LAS VEGAS, NV WELTERWEIGHT'),(5,'MLB'),(9,'MLB 5 Ining'),(53,'MLB Play Off 2009'),(45,'Mundial de beisbol 2009'),(55,'Mundial de boxeo'),(52,'Mundial SUB20'),(59,'mundial sudafrica'),(6,'NBA'),(48,'NBA Play Offs 2009'),(20,'NBA Pre Temporada'),(34,'NCAA'),(8,'NFL'),(12,'NFL Pretemporada'),(60,'NFL Pretemporada 2010'),(58,'nh'),(7,'NHL'),(44,'Pre Temporada MLB'),(13,'Premier Inglesa'),(1,'Serie del Caribe'),(23,'Serie mundial'),(3,'Serie Profesional'),(2,'Serie Regular'),(37,'Staples Center'),(40,'Suramericano Sub 20'),(51,'Tenis US Open 2009'),(19,'The Carling Cup'),(17,'UEFA Champions League'),(25,'Uefa Cup'),(57,'UEFA Europa League'),(11,'VolleyBall Pekin 2008');
UNLOCK TABLES;
/*!40000 ALTER TABLE `campeonato` ENABLE KEYS */;

--
-- Table structure for table `cuenta_jugador`
--

DROP TABLE IF EXISTS `cuenta_jugador`;
CREATE TABLE `cuenta_jugador` (
  `id_cuenta` int(11) NOT NULL auto_increment,
  `fecha_sis` datetime NOT NULL,
  `referencia` varchar(10) default NULL,
  `operacion` varchar(1) NOT NULL,
  `monto` double NOT NULL,
  `concepto` varchar(100) default NULL,
  `id_jugador` int(11) default NULL,
  `id_usuario` int(11) NOT NULL,
  `tipo` varchar(1) default NULL,
  PRIMARY KEY  (`id_cuenta`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_jugador` (`id_jugador`),
  CONSTRAINT `FK_cuenta_jugador_1` FOREIGN KEY (`id_jugador`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_cuenta_jugador_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cuenta_jugador`
--


/*!40000 ALTER TABLE `cuenta_jugador` DISABLE KEYS */;
LOCK TABLES `cuenta_jugador` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `cuenta_jugador` ENABLE KEYS */;

--
-- Table structure for table `deporte`
--

DROP TABLE IF EXISTS `deporte`;
CREATE TABLE `deporte` (
  `id_deporte` int(11) NOT NULL auto_increment,
  `desc_deporte` varchar(50) NOT NULL,
  `empate` int(11) NOT NULL,
  `id_status_deporte` int(11) NOT NULL,
  `referencia_inicio` int(11) default NULL,
  `runline_inicio` double default NULL,
  `combinacion` varchar(700) default NULL,
  `items` int(11) NOT NULL,
  PRIMARY KEY  (`id_deporte`),
  UNIQUE KEY `UQ_deporte_1` (`desc_deporte`),
  KEY `id_status_deporte` (`id_status_deporte`),
  CONSTRAINT `FK_deporte_1` FOREIGN KEY (`id_status_deporte`) REFERENCES `status_deporte` (`id_status_deporte`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `deporte`
--


/*!40000 ALTER TABLE `deporte` DISABLE KEYS */;
LOCK TABLES `deporte` WRITE;
INSERT INTO `deporte` VALUES (1,'Soccer',1,1,0,0,'',0),(2,'Basquet',0,2,700,0,'',0),(3,'Hockey',0,1,0,0,'1-2,1-4,1-5,2-3,2-4,2-5,2-6,3-5,3-6,4-5,5-6',0),(4,'Beisbol',0,1,900,1.5,'1-2,1-7,1-8,1-4,1-5,1-10,1-11,2-7,2-8,2-4,2-5,2-10,2-11,2-3,2-6,2-9,2-12,3-9,3-6,3-12,3-5,3-8,3-11,3-13,3-16,4-7,4-8,4-5,4-10,4-11,5-7,5-8,5-9,5-10,5-11,5-6,5-12,6-8,6-9,6-11,6-12,6-13,6-16,7-8,7-10,7-11,7-13,7-16,8-10,8-11,8-13,8-16,8-9,8-12,9-12,9-13,9-16,9-11,10-11,10-13,10-16,11-13,11-16,11-12,12-13,12-16,13-16',0),(5,'Futbol Americano',0,1,0,0,'1-2,1-4,1-5,2-4,2-5,4-5,3-6',0),(6,'Basketball',0,1,499,0,'1-2,1-4,1-5,2-4,2-5,4-5,3-6',0),(7,'Beisbol 5 Inning',0,1,1000,0,'1-2,1-7,1-8,1-4,1-5,1-10,1-11,2-7,2-8,2-4,2-5,2-10,2-11,2-3,2-6,2-9,2-12,3-9,3-6,3-12,3-5,3-8,3-11,4-7,4-8,4-5,4-10,4-11,5-7,5-8,5-10,5-11,5-6,5-12,6-9,6-12,7-8,7-10,7-11,7-13,7-16,8-10,8-11,8-13,8-16,8-9,8-12,9-12,9-13,9-16,9-11,10-11,10-13,10-16,11-13,11-16,11-12,12-13,12-16,13-16',0),(8,'Beisbol 1 Inning',0,1,2000,0,'1-2,1-7,1-8,1-4,1-5,1-10,1-11,2-7,2-8,2-4,2-5,2-10,2-11,2-3,2-6,2-9,2-12,3-9,3-6,3-12,3-5,3-8,3-11,4-7,4-8,4-5,4-10,4-11,5-7,5-8,5-10,5-11,5-6,5-12,6-9,6-12,7-8,7-10,7-11,7-13,7-16,8-10,8-11,8-13,8-16,8-9,8-12,9-12,9-13,9-16,9-11,10-11,10-13,10-16,11-13,11-16,11-12,12-13,12-16,13-16',0),(9,'Tenis',0,1,4000,NULL,'',0),(10,'Formula 1',2,1,218001,0,'',0),(11,'Caballos',1,2,0,0,'',0),(12,'Caballo contra Caballo',0,2,0,0,'',0),(13,'Triplazo',1,2,700,-120,'',0),(14,'Tenis Resultado',1,2,0,0,'',0),(15,'Boxeo',0,1,11200,0,'',0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `deporte` ENABLE KEYS */;

--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
CREATE TABLE `equipo` (
  `id_equipo` int(11) NOT NULL auto_increment,
  `desc_equipo` varchar(100) NOT NULL,
  `abreviatura` varchar(10) NOT NULL,
  `desc_corta` varchar(100) NOT NULL,
  PRIMARY KEY  (`id_equipo`),
  UNIQUE KEY `UQ_equipo_1` (`desc_equipo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `equipo`
--


/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
LOCK TABLES `equipo` WRITE;
INSERT INTO `equipo` VALUES (1,'Empate','EMP',''),(2,'Arizona Diamonbacks.','ARI',''),(3,'Atlanta Falcons.','ATL',''),(4,'Austria.','AUT',''),(5,'Baltimore Orioles','BAL',''),(6,'Boston Red Soxs.','BOS',''),(7,'Buffalo Bills.','BUF',''),(8,'Carolina Panthers.','CAR',''),(9,'Chicago Bears.','CHI',''),(10,'Cincinnati Reds','CIN',''),(11,'Cleveland Indians','CLE',''),(12,'Colorado Rockies.','COL',''),(13,'Croacia.','CRO',''),(14,'Chicago Cubs.','CUBS',''),(15,'Chicago White Sox.','CWS',''),(16,'Dallas Cowboys.','DAL',''),(17,'Denver Broncos.','DEN',''),(18,'Detroit Tigers','DET',''),(19,'Empate.','EMP',''),(20,'EspaÃ±a','ESP',''),(21,'Florida Marlins.','FLO',''),(22,'Francia.','FRA',''),(23,'Green Bay Packers.','GBP',''),(24,'Grecia.','GRE',''),(25,'Holanda.','HOL',''),(26,'Houston Astros','HOU',''),(27,'Indianapolis Colts.','IND',''),(28,'Italia.','ITA',''),(29,'Jacksonville Jaguars.','JAC',''),(30,'Kansas City Royals.','KC',''),(31,'Kansas City Chiefs.','KCC',''),(32,'Los Angeles Anaheim.','LAA',''),(33,'Los Angeles Dodgers.','LAD',''),(34,'Leones Del Caracas','LEO',''),(35,'Miami Dolphins.','MIA',''),(36,'Milwaukee Brewers.','MIL',''),(37,'Minnesota Twins','MIN',''),(38,'Navegantes Del Magallanes','NAV',''),(39,'New England Patriots.','NEWP',''),(40,'New Orleans Saints.','NOS',''),(41,'New York Giants.','NYG',''),(42,'New York Jets.','NYJ',''),(43,'New York Mets.','NYM',''),(44,'New York Yankees.','NYY',''),(45,'Oakland Atlhetics','OAK',''),(46,'Philadelphia Phillies','PHI',''),(47,'Pittsburgh Piratas','PIT',''),(48,'Polonia.','POL',''),(49,'Portugal.','POR',''),(50,'Republica Checa.','RCH',''),(51,'Rumania.','RUM',''),(52,'Rusia.','RUS',''),(53,'San Diego Chargers.','SAD',''),(54,'San Francisco 49ers.','SAF',''),(55,'San Diego Padres.','SD',''),(56,'Seattle Mariners.','SEA',''),(57,'San Francisco Giants.','SF',''),(58,'St. Louis Cardinals','STL',''),(59,'Suecia.','SUE',''),(60,'Suiza.','SUI',''),(61,'Tampa Bay Buccaneers.','TAM',''),(62,'Tampa Bay Ray','TB',''),(63,'Tennessee Titans.','TEN',''),(64,'Texas Rangers.','TEX',''),(65,'Toronto Blue Jays.','TOR',''),(66,'Turquia.','TUR',''),(67,'Washington Nationals.','WAS',''),(68,'Atlanta Hawks','ATL',''),(69,'Boston Celtics','BOS',''),(70,'New Orleans Hornets','NOH',''),(71,'Chicago Bulls','CHI',''),(72,'Cleveland Cavaliers','CLE',''),(73,'Dallas Mavericks','DAL',''),(74,'Denver Nuggets','DEN',''),(75,'Detroit Pistons','DET',''),(76,'Golden State Warriors','GOL',''),(77,'Houston Rockets','HOU',''),(78,'Indiana Pacers','IND',''),(79,'Los Angeles Lakers','LOS',''),(80,'Los Angeles Clippers','CLI',''),(81,'Miami Heat','MIA',''),(82,'Milwauke Bucks','MIL',''),(83,'Minessota Timberwolves','MIN',''),(84,'Memphis Grizzlies','MEM',''),(85,'New York Knicks','NYN',''),(86,'New Jersey Nets','NJN',''),(87,'Orlando Magic','ORL',''),(88,'Philadelphia Sixers','PHI',''),(89,'Phoenixs Sun','PHO',''),(90,'Portland Trail Blazers','POR',''),(91,'Sacramento Kings','SAC',''),(92,'San Antonio Spurs','SAN',''),(93,'Seatle Super Sonic','SEA',''),(94,'Toronto Raptors','TOR',''),(95,'Utah Jazz','UTH',''),(96,'Washington Wizards','WAS',''),(98,'Alemania','ALE',''),(99,'National Football League','NFL',''),(100,'Baltimore Ravens','BAL',''),(101,'Cincinnati Bengals','CIN',''),(102,'Cleveland Browns','CLE',''),(103,'Pittsburgh Steelers','PIT',''),(104,'Houston Texans','HOU',''),(105,'Indianapolis Colts','IND',''),(106,'Jacksonville Jaguars','JAC',''),(107,'Tennessee Titans','TEN',''),(108,'Miami Dolphins','MIA',''),(109,'Buffalo Bills','BUF',''),(110,'New England Patriots','NEP',''),(111,'New York Jets','NYJ',''),(112,'Denver Broncos','DEN',''),(113,'Kansas City Chiefs','KAN',''),(114,'Oakland Raiders','OAK',''),(115,'San Diego Chargers','SAD',''),(116,'Chicago Bears','CHI',''),(117,'Detroit Lions','DET',''),(118,'Green Bay Packers','GBP',''),(119,'Minnesota Vikings','MIN',''),(120,'Atlanta Falcons','ATL',''),(121,'Carolina Panthers','CAR',''),(122,'New Orleans Saints','NOS',''),(123,'Tampa Bay Buccaneers','TAM',''),(124,'Dallas Cowboys','DAL',''),(125,'New York Giants','NYG',''),(126,'Philadelphia Eagles','PHI',''),(127,'Washington Redskins','WAS',''),(128,'Arizona Cardinals','ARI',''),(129,'St. Louis Rams','STL',''),(130,'San Francisco 49erÂ´s','SAN',''),(131,'Seattle Seahawks','SEA',''),(132,'Tiburones De la Guaira','TIB',''),(133,'Cardenales De Lara','CAR',''),(134,'Caribes De Anzoategui','CAR',''),(135,'Bravos De Margarita.','BRA',''),(136,'Aguilas Del Zulia','AGU',''),(137,'Tigres De Aragua','TIG',''),(138,'New Jersey Devils','NJD',''),(139,'New York Islanders','NYI',''),(140,'New York Rangers','NYR',''),(141,'Philadelphia Flyers','PHI',''),(142,'Pittsburgh Penguins','PIT',''),(143,'Boston Bruins','BOS',''),(144,'Buffalo Sabres','BUF',''),(145,'Montreal Canadiens','MON',''),(146,'Ottawa Senators','OTT',''),(147,'Toronto Maple Leafs','TOR',''),(148,'Atlanta Thrashers','ATL',''),(149,'Carolina Hurricanes','CAR',''),(150,'Florida Panthers','FLO',''),(151,'Tampa Bay Lightning','TAM',''),(152,'Washington Capitals','WAS',''),(153,'Chicago Blackhawks','CHI',''),(154,'Columbus Blue Jackets','CLO',''),(155,'Detroit Red Wings','DET',''),(156,'Nashville Predators','NAS',''),(157,'St. Louis Blues','STL',''),(158,'Calgary Flames','CAL',''),(159,'Colorado Avalanche','COL',''),(160,'Edmonton Oilers','EDM',''),(161,'Minnesota Wild','MIN',''),(162,'Vancouver Canucks','VAN',''),(163,'Anaheim Ducks','ANA',''),(164,'Dallas Stars','DAL',''),(165,'Los Angeles Kings','LOS',''),(166,'Phoenix Coyotes','PHO',''),(167,'San Jose Sharks','SAN',''),(168,'Yankees 5 Ining','NYY',''),(169,'Red Soxs 5 Inig','BOS',''),(170,'Diamondback 5 Inning','ARI',''),(171,'Orioles 5 ining','BAL',''),(172,'Cubs 5 Ining','CUB',''),(173,'White Soxs 5 Ining','CHI',''),(174,'Reds 5 Ining','CIN',''),(175,'Indians 5 Ining','CLE',''),(176,'Rockies 5 Ining','COL',''),(177,'Tigers 5 Ining','DET',''),(178,'Marlins 5 Ining','FLA',''),(179,'Astros 5 Ining','HOU',''),(180,'Royals 5 Ining','KAN',''),(181,'Anaheim 5 Ining','ANA',''),(182,'Dodgers 5 Ining','LOS',''),(183,'Brewers 5 Ining','MIL',''),(184,'Mets 5 Ining','NYM',''),(185,'Twins 5 Ining','MIN',''),(186,'Philiies 5 Ining','PHI',''),(187,'Piratas 5 Ining','PIT',''),(188,'Athletics 5 Ining','AOK',''),(189,'Padres 5 Ining','SDG',''),(190,'Giants 5 Ining','SFC',''),(191,'Mariners 5 Ining','SEA',''),(192,'Cardinals 5 Ining','STL',''),(193,'Ray 5 Ining','TAM',''),(194,'Rangers 5 Ining','TEX',''),(195,'Blue Jay 5 Ining','TOR',''),(196,'Nationals 5 Ining','WAS',''),(197,'Atlanta Braves','ATL',''),(198,'Washintong Nationals GM 2','WAS G2',''),(199,'Colorado Rockies Gm 2','COL GM 2',''),(200,'Argentina','ARG',''),(201,'Australia','AUS',''),(202,'Iran','IRI',''),(203,'Lituania','LTU',''),(204,'Rusia','RUS',''),(205,'Croacia','CRO',''),(206,'Angola','ANG',''),(207,'China','CHN',''),(208,'Estados Unidos','USA',''),(209,'Grecia','GRE',''),(210,'Japon','JAP',''),(211,'Italia','ITA',''),(212,'Venezuela','VEN',''),(213,'Serbia','SER',''),(214,'Egypto','EGY',''),(215,'Brasil','BRA',''),(216,'Bulgaria','BUL',''),(217,'Polonia','POL',''),(218,'Atlanta Braves GM2','ATL GM2',''),(219,'Chicago Cubs GM2','CUBS GM2',''),(220,'Reggina Calcio','REG',''),(221,'AC Chievo Verona','CHI',''),(222,'Juventus Turin','JUV',''),(223,'AC Fiorentina','FIO',''),(224,'Bologna FC','BOL',''),(225,'AC Milan','MIL',''),(226,'SSC Napoli','NAP',''),(227,'AS Roma','ROM',''),(228,'AC Siena','SIE',''),(229,'Atalanta Bergamo','ATA',''),(230,'SS Lazio','LAZ',''),(231,'Cagliari','CAG',''),(232,'Genoa CFC','GEN',''),(233,'Catania','CAT',''),(234,'US Lecce','LEC',''),(235,'FC Torino','TOR',''),(236,'Inter Milano','INT',''),(237,'UC Sampdoria','SAM',''),(238,'US Palermo','PAL',''),(239,'Udinese','UDI',''),(240,'Almeria','ALM',''),(241,'ATL Bilbao','BIL',''),(242,'CF Malaga','MAL',''),(243,'ATL Madrid','ATLM',''),(244,'Recreativo Huelva','RECRE',''),(245,'Betis','BET',''),(246,'VILLAREAL','VIL',''),(247,'Osasuna','OSA',''),(248,'BARCELONA','BAR',''),(249,'Numancia','NUM',''),(250,'RCD Mallorca','MALL',''),(251,'Valencia','VAL',''),(252,'Real Madrid','RMD',''),(253,'Deportivo La CoruÃ±a','DEP',''),(254,'FC Sevilla','SEV',''),(255,'Racing Santander','RAC',''),(256,'Valladolid','VALL',''),(257,'RCD Espanyol','ESP',''),(258,'CF Getafe','GET',''),(259,'Sporting Gijon','SPO',''),(260,'Middlesbrough FC','MID',''),(261,'LIVERPOOL','LIV',''),(262,'Bolton Wanderers','BOL',''),(263,'Newcastle UNT','NEW',''),(264,'Aston Villa.','AST',''),(265,'Stoke City','STK',''),(266,'Sunderland','SUN',''),(267,'Tottenham','TOT',''),(268,'Everton FC','EVE',''),(269,'West Bromwichalbion','WBR',''),(270,'Hull City','HUL',''),(271,'Blackburn Rover','BLK',''),(272,'ARSENAL','ARS',''),(273,'Fullham','FUL',''),(274,'CHELSEA','CHE',''),(275,'Wigan Athletic','WIG',''),(276,'West Ham UNT','WHD',''),(277,'Manchester City','MAC',''),(278,'MANCHESTER UNITED','MANU',''),(279,'Portsmouth FC','POT',''),(280,'Girondins Burdeos','BOR',''),(281,'CFR Cluj','CFR',''),(282,'Werder Bremen','BRE',''),(283,'Panathinaikos','PAN',''),(284,'Anorthosis Famagusta','ANO',''),(285,'Sporting de Portugal','SPO',''),(286,'Basilea','BAS',''),(287,'Shakhtar Donetsk','SHA',''),(288,'PSV Eindhoven','PSV',''),(289,'Olympique de Marsella','MAR',''),(290,'Celtic','CEL',''),(291,'AaB Aalborg','AAL',''),(292,'Olympique de Lyon','LYO',''),(293,'BAYER DE MUNICH','BAY',''),(294,'Steaua Bucarest','STE',''),(295,'PORTO','POR',''),(296,'Fenerbahce','FEN',''),(297,'Dinamo de Kiev','KIV',''),(298,'Zenit St. Petersburgo','ZEN',''),(299,'BATE Borisov','BOR',''),(300,'Philadelphia Phillis GM2','PHI GM2',''),(301,'Kansas City GM 2','KAN GM 2',''),(302,'Paraguay','PAR',''),(303,'Ecuador','ECU',''),(304,'Bolivia','BOL',''),(305,'Chile','CHI',''),(306,'Peru','PER',''),(307,'Uruguay','URU',''),(308,'Colombia','COL',''),(309,'Baltimore Orioles GM 2','BAL GM2',''),(310,'New York Mets GM2','NYM GM2',''),(311,'Chicago White Soxs GM2','CWS GM 2',''),(312,'Toronto Blue Jays GM2','TOR GM2',''),(313,'Kansas City Royals GM2','KC GM2',''),(314,'Cleveland Indians GM2','CLE GM2',''),(315,'Boston Red Soxs GM2','BOS GM2',''),(316,'Tampa Bay Rays','TB',''),(317,'New York Yankees GM2','NYY2',''),(318,'Baltimore Orioles GM2','BAL2',''),(319,'Minnesota Twins GM2','MIN2',''),(320,'Atalta Braves GM2','ATL2',''),(321,'Detroit Tigers GM2','DET2',''),(322,'Portugal','POR',''),(323,'Suecia','SUE',''),(324,'Republica Checa','CZE',''),(325,'Estonia','EST',''),(326,'Rumania','RUM',''),(327,'Philipp Kohlschreiber','PHK',''),(328,'Stanislas Wawrinka','STW',''),(329,'Roger Federer','RGF',''),(330,'Randek Stepanek','RANS',''),(331,'Tommy Robredo','TOMR',''),(332,'Andy Roddick','ANDR',''),(333,'Feliciano Lopez','FEL',''),(334,'Gael Monfils','GAM',''),(335,'Richard Gasquet','RCHG',''),(336,'Rafael Nadal','RNAD',''),(337,'Jo-Wilfried Tsonga','JWTSO',''),(338,'Ivo Karlovic','IVKAR',''),(339,'Novak  Djokovic','NDJOK',''),(340,'Charlotte Bobcats','CHA',''),(341,'Phillis gana la Serie Mundial','PHI',''),(342,'Tampa gana la Serie Mundial','TB',''),(343,'Oklahoma City','OKLH',''),(344,'Felipe Massa','MAS',''),(345,'Lewis Hamilton','HAM',''),(346,'Kimi Raikkonen','RAK',''),(347,'Fernando Alonso','ALO',''),(348,'Heikki Kovalainen','KOV',''),(349,'Robert Kubica','KUB',''),(350,'Aguilas del Zulia GM2','AGUI2',''),(351,'Bravos de Margarita GM2','BRA2',''),(352,'Cardenales de Lara GM2','CAR2',''),(353,'Caribes de Anzoategui GM2','CARI2',''),(354,'Andy Murray','MUR',''),(355,'Gilles Simon','SIM',''),(356,'Shalke 04','SHC',''),(357,'Paris Sant Germain','PSG',''),(358,'Nec Nijmegen','NEC',''),(359,'Wolfburgo','WOLF',''),(360,'Sporting Braga','SPB',''),(361,'Ajax Amnsterdan','AJX',''),(362,'Hamburger Sv','HAM',''),(363,'Rosenborg Tromdeim','ROS',''),(364,'Feyernoord','FEY',''),(365,'Twente Enshede','TWE',''),(366,'Galatasaray','GAL',''),(367,'Hertha BSC Berlin','HER',''),(368,'Olympique Marseille','OLI',''),(369,'Metalist Kharkiv','MET',''),(370,'Partizan Betgrad','PAB',''),(371,'Sevilla','SEV',''),(372,'Sampdoria','SAM',''),(373,'Stardard Liege','SLIE',''),(374,'Sc Heerenveen','SCH',''),(375,'Msk Zilina','MSK',''),(376,'Slavia Praha','SLV',''),(377,'Club Brugge','CLU',''),(378,'Kopenhagen','KOP',''),(379,'Cska Moscow','CSK',''),(380,'AS Nancy Lorraine','NAN',''),(381,'Lech Poznan','LEC',''),(382,'Oscar De La Hoya','ODH',''),(383,'Manny Pacquiao','MP',''),(384,'Tiburones de la Guaira GM2.','TIB2',''),(385,'Bravos de Margarita','BRA',''),(386,'Leones del Caracas GM2','LEO2',''),(387,'Caribes De Anzoat. GM2','CAR',''),(388,'Bravos de Margarita.GM2','BRA2',''),(389,'Tigres de Aragua.GM2','TIG2',''),(390,'AS San Etiene','ASE',''),(391,'Benfica','BEN',''),(392,'VFC Stuttgart','VFC',''),(393,'Udinese calcio','UDI',''),(394,'Spartak Moscou','SPAR',''),(395,'Anzoategui','ANZ',''),(396,'Union Atletico Maracaibo','UAM',''),(397,'Bravos de Margarita. Juego2','BRA2',''),(398,'Leones del Caracas. Juego2','LEO2',''),(399,'Aguilas GM 2','AGU',''),(400,'Tigres GM2','TIG',''),(401,'Leones GM2','LEO',''),(402,'Tiburones GM2','TIB2',''),(403,'Ohio State','OHS',''),(404,'Texas','TEX',''),(405,'Ball State','BST',''),(406,'Tulsa','TUL',''),(407,'Florida','FLO',''),(408,'Oklahoma','OKL',''),(409,'Burnley FC','BUR',''),(410,'Derby County','DER',''),(411,'Real Union de Irun','REU',''),(412,'Polideportivo Ejido','POL',''),(413,'Southend United','SOU',''),(414,'Antonio Margarito','MARG',''),(415,'Shane Mosley','MOSL',''),(416,'SHADDRAK','SHA',''),(417,'Cruznel','CRU',''),(418,'001 al 333','1ER',''),(419,'334 al 666','2DO',''),(420,'667 al 999','3RO',''),(421,'REY SEDUCTOR','RES',''),(422,'CHARDONNAY','CHR',''),(423,'SUPER JORGE','SPJ',''),(424,'PRADO DEL REY','PDR',''),(425,'EAGLE','EAG',''),(426,'GARROTIN','GRR',''),(427,'VEDA HADASSAH','VHD',''),(428,'BARBARA','BBR',''),(429,'WILL TO WIN','WTW',''),(430,'MY GOLDEN DREAM','MGD',''),(431,'PIRLO','PIR',''),(432,'SPACE MUSIC','SPM',''),(433,'SAN NICOLAS','SNC',''),(434,'MISTER MALAVE','MML',''),(435,'IZAR','IZR',''),(436,'SILVER CELEBRITY','SCB',''),(437,'PIA PRINCESS','PIP',''),(438,'CHAMPAGNE','CHM',''),(439,'EXEPTIONALLY','EXE',''),(440,'CRISTO FUE','CFE',''),(441,'TUMBAO','TUM',''),(442,'REY SANTON','RST',''),(443,'MADRE ROSA','MRS',''),(444,'MORELLA Z','MOZ',''),(445,'AMERICAN IDOL','AMI',''),(446,'BRIBON','BRB',''),(447,'FINLANDIA','FIN',''),(448,'IBIZA','IBI',''),(449,'CRISTAL JAK','CRJ',''),(450,'SANTANELLA','SNT',''),(451,'SECRET LADY','SLD',''),(452,'TEXAN RIDE','TXR',''),(453,'PERDEDOR 1 JUEGO','PER',''),(454,'SI ANOTAN 1 INNING','S1I',''),(455,'NO ANOTAN 1 INNING','N1I',''),(456,'SI ANOTAN 2 INNING','S2I',''),(457,'NO ANOTAN 2 INNING','N2I',''),(458,'NO ANOTAN 5 INNING','N3I',''),(459,'SI ANOTAN 5 INNING','S5I',''),(460,'SI ANOTAN 7 INNING','S7I',''),(461,'NO ANOTAN 7 INNING','N7I',''),(462,'CARACAS 4 1/2 CARRERAS','CAC',''),(463,'ARAGUA 4 1/2 CARRERAS','ARC',''),(464,'CARACAS 4 CARRERAS','CAC',''),(465,'ARAGUA 4 CARRERAS','ACA',''),(466,'MAS PONCHES CARACAS','KCC',''),(467,'MA SPONCHES ARAGUA','KAR',''),(468,'CARACAS MAS DE 4 CARRERAS','CM4',''),(469,'CARACAS MENOS DE 4 CARRERAS','CM5',''),(470,'ARAGUA MAS DE 4 CARRERAS','AM5',''),(471,'ARAGUA MENOS DE 4 CARRERAS','AM5',''),(472,'ANOTA PRIMERO ARAGUA','A1A',''),(473,'ANOTA PRIMERO CARACAS','A1C',''),(474,'FEDERER GANA 3-0','F30',''),(475,'NADAL GANA 3-0','N30',''),(476,'FEDERER GANA 3 - 1','F31',''),(477,'NADAL GANA 3-1','N31',''),(478,'FEDERER GANA 3-2','F32',''),(479,'NADAL GANA 3-2','N32',''),(480,'FEDERER GANA 3-1','F31',''),(481,'FEDERER GANA 3 A 0','F30',''),(482,'FEDERER GANA 3 A 1','F31',''),(483,'FEDERER GANA 3 A 2','F32',''),(484,'NADAL GANA 3 A 0','N30',''),(485,'NADAL GANA 3 A 1','N31',''),(486,'NADAL GANA 3 A 2','N32',''),(487,'Venezuela.','VZLA',''),(488,'Rep. Dominicana','RDM',''),(489,'Puerto Rico','PTR',''),(490,'Mexico','MEX',''),(491,'ANOTA PRIMERO VENEZUELA','APV',''),(492,'ANOTA PRIMERO MEXICO','APM',''),(493,'ANOTA PRIMERO PUERTO RICO','APP',''),(494,'ANOTA PRIMERO DOMINICANA','APD',''),(495,'SI ANOTAN 1 INNING. DOM-PRI','S1I',''),(496,'SI ANOTAN 1 INNING VEN-MEX','SI1',''),(497,'NO ANOTAN 1 INNING DOM-PRI','NA1',''),(498,'NO ANOTAN 1 INNING VEN-MEX','NA1',''),(499,'SI ANOTAN 1 INNING PRI-VEN','S1I',''),(500,'NO ANOTAN 1 INNING PRI-VEN','NA1',''),(501,'SI ANOTAN 1 INNING DOM-MEX','SA1',''),(502,'NO ANOTAN 1 INNING DOM-MEX','NA1',''),(503,'Deportivo Tachira (VZL)','TACH',''),(504,'Guarani Asuncion','GUA',''),(505,'America De Cali (COL)','AME',''),(506,'Defensor Sporting','SPOR',''),(507,'Boyaca Chico','BOY',''),(508,'Club Aurora','AUR',''),(509,'Chivas De Guadalajara (MEX)','CHIV',''),(510,'Atl Lanus','LAN',''),(511,'Universitario','UNIV',''),(512,'Libertad Asuncion','LASU',''),(513,'San Luis','SANL',''),(514,'San Lorenzo','SLOZ',''),(515,'Dep San Martin','SMAR',''),(516,'Nacional (URU)','NAC',''),(517,'Nacional (PAR)','NAC',''),(518,'River Plate','RIV',''),(519,'Inglaterra','ING',''),(520,'Guatemala','GUA',''),(521,'Costa De Marfil','MAF',''),(522,'Turkia','TUR',''),(523,'Noruega','NOR',''),(524,'EAST','EAS',''),(525,'WEST','WES',''),(526,'Caracas FC (VZL)','CCS',''),(527,'Palmeiras','PAL',''),(528,'LDU de Quito','LDU',''),(529,'DEP Cuenca (ECU)','CUE',''),(530,'Boca Juniors (ARG)','BOC',''),(531,'Sport Recife','REC',''),(532,'Colo Colo (CHI)','COL',''),(533,'Sao Paulo (BRA)','SPA',''),(534,'IND Medellin (COL)','MED',''),(535,'Cruzeiro (BRA)','CRU',''),(536,'Est La Plata (ARG)','EST',''),(537,'Fiorentina','FIR',''),(538,'Shakhtar Donestk','SHT',''),(539,'U De Chile','UCH',''),(540,'U De Sucre (BOL)','USC',''),(541,'Libertad (PAR)','LIB',''),(542,'Taipei','TAI',''),(543,'Panama','PAN',''),(544,'Korea','KOR',''),(545,'Canada','CAN',''),(546,'Sudafrica','SUD',''),(547,'Cuba','CUB',''),(548,'Deportivo Quito','DQU',''),(549,'Ucrania','UCR',''),(550,'Irlanda','IRL',''),(551,'Gales','GAL',''),(552,'Fernando Verdasco','FER',''),(553,'Radek Stepanek','RAD',''),(554,'Fernando Gonzalez','FGO',''),(555,'Edwin Valero','EV',''),(556,'Antonio Pitalua','AP',''),(557,'Dynamo Kiev','DYN',''),(558,'Werder Bremen.','WBR',''),(559,'Seattle Mariners GM2','SEA2',''),(560,'Chicago White Sox GM2','CHW2',''),(561,'Gremio','GRE',''),(562,'Philadelphia Phillies GM2','PHI2',''),(563,'Washington Nationals GM2','WAS2',''),(564,'Arizona Diamonbacks GM2','ARZ2',''),(565,'Florida Marlins GM2','FLO2',''),(566,'Texas Rangers Game2','TEX',''),(567,'Oaklnad Athletics','OAK',''),(568,'Oakland Athletics Game2','OAK',''),(569,'Dinamarca','DIM',''),(570,'Albania','ALB',''),(571,'San Francisco Giants GM2','SF2',''),(572,'Washington Nationals. GM2','WAS2',''),(573,'Lakers gana el campeonato','LACAM',''),(574,'Orlando gana el campeonato','ORCAM',''),(575,'Detroit Tigers (Juego 2)','DET2',''),(576,'Chicago White Sox (Juego 2)','CHW2',''),(577,'EspaÃ±a.','EPÃ‘',''),(578,'Nueva Zelanda.','NZL',''),(579,'Egipto','EGP',''),(580,'Iraq','IRQ',''),(581,'Nva Zelanda','NVZ',''),(582,'Sur Africa','SFC',''),(583,'PITCHEO AME PONCHA MAS','PMK',''),(584,'PITCHEO NAC PONCHA MAS','PNK',''),(585,'SI ANOTAN PRIMER INNING','SPI',''),(586,'NO ANOTAN PRIMER INNING','NPI',''),(587,'AMERICANA ANOTA PRIMERO','AAP',''),(588,'NACIONAL ANOTA PRIMERO','NAP',''),(589,'BATEA MAS HR AMERICANA','MHN',''),(590,'BATEA MAS HR NACIONAL','MHN',''),(591,'SI ANOTAN CUARTO INNING','SA4',''),(592,'NO ANOTAN CUARTO INNING','NA4',''),(593,'SI ANOTAN SEPTIMO INNING','SAS',''),(594,'NO ANOTAN SEPTIMO INNING','NAS',''),(595,'MENOS DE 1.5 ERRORES ENTRE LOS 2','EME',''),(596,'MAS DE 1.5 ERRORES ENTRE LOS DOS','MAE',''),(597,'LIGA AMERICANA','AML',''),(598,'LIGA NACIONAL','NAL',''),(599,'LIGA AMERICANA 2 MITAD','A2H',''),(600,'LIGA NACIONAL 2 MITAD','LN2',''),(601,'St. Luis Cardinals Game2','ST2',''),(602,'Chicago Cubs Game2','CC2',''),(603,'Los Angeles Anaheim  Game1','LAA',''),(604,'Kansas City Royals Game1','KCR',''),(605,'Chicago White Sox GM1','CWS1',''),(606,'Detroit Tigers GM1','DET1',''),(607,'Colorado Game2','CO2',''),(608,'Mets Game 2','ME2',''),(609,'Colorado Rockies Game2','COL2',''),(610,'Florida Marlins Game2','FL2',''),(611,'Birmingham ','BIR',''),(612,'Pitsburg Piratas GM2','PIT2',''),(613,'Cincinnati Reds GM2','CIN2',''),(614,'Belgica','BEL',''),(615,'Tampa Bay GM2','TAMP2',''),(616,'hungria','HUG',''),(617,'Escocia','ESC',''),(618,'Juan Martin Del Potro','JMP',''),(619,'Juan Carlos Ferrero','JCF',''),(620,'CD XEREZ','XER',''),(621,'FC PARMA','PAR',''),(622,'Maccabi Haifa','MCC',''),(623,'Besiktas JK','BJK',''),(624,'Zurich FC','ZFC',''),(625,'Apoel Nicosia','APL',''),(626,'Debreseni VSC','DEB',''),(627,'Rubin Kazan','RUK',''),(628,'Unirea Urziceni','UUZ',''),(629,'Glasgow Ranger','GLR',''),(630,'Stuttgart VFB','STT',''),(631,'AZ Alkmaar','AZL',''),(632,'Olimpiakos Pireus','OLM',''),(633,'Standar Liege','STL',''),(634,'Chris Jhon','CJH',''),(635,'Rocky Juarez','RJZ',''),(636,'Floyd Mayweather JR','FMJ',''),(637,'Juan Manuel Marquez','JMM',''),(638,'AS.Livorno','ASL',''),(639,'Trinidad y Tobago','TYT',''),(640,'Nigeria','NIG',''),(641,'Tahiti','TAI',''),(642,'Camerun','CAM',''),(643,'Uzbekistan','UZB',''),(644,'Ghana','GHA',''),(645,'Costa Rica','COR',''),(646,'Emiratos Arabes Unidos','EAU',''),(647,'Honduras','HON',''),(648,'CD.Tenerife','TEN',''),(649,'Bosnia','BO',''),(650,'Malta','MA',''),(651,'Austria','AU',''),(652,'Chipre','CH',''),(653,'Real.Zaragoza','ZA',''),(654,'Bravos de Margarita Juego2','BRA',''),(655,'Tigres de Aragua Juego2','TIG',''),(656,'Agulia Del Zulia Juego2','AGUI',''),(657,'Caribes De Oriente Juego2','CAR',''),(658,'Cardenales De Lara Juego2','CARD',''),(659,'Caribes De Anzoategui Juego2','CARB',''),(660,'Tiburones De La Guaira Juego2','TIB',''),(661,'M A Cotto','COTTO',''),(662,'Holanda','HOL',''),(663,'Wolverhampton','WOL',''),(664,'Aguilas CibaeÃ±as','ACIB',''),(665,'Estrellas Orientales','EOR',''),(666,'Gigantes del Cibao','GCI',''),(667,'Leones del Escogido','LEC',''),(668,'Tigres del Licey','LIC',''),(669,'Toros Del Este','TOR',''),(670,'Aguilas de Mexicali','AMEX',''),(671,'Naranjeros de Hermosillo','HER',''),(672,'Yanquis de Obregon','OBR',''),(673,'Mayos de Navojoa','NAV',''),(674,'Caneros de los Mochis','MOC',''),(675,'Algodoneros de Gausave','GSV',''),(676,'Tomateros de Culiacan','CUL',''),(677,'Venados de Mazatlan','MAZ',''),(678,'Orientales','ORI',''),(679,'Occidentales','OCI',''),(680,'Anota Primero Pt Rico','APT',''),(681,'Recibe Mas Boletos Dominicana','BDOM',''),(682,'Recibe Mas Boletos Venezuela','BVZL',''),(683,'Recibe Mas Boletos Mexico','BMEX',''),(684,'Recibe Mas Boletos PT Rico','BPTR',''),(685,'Poncha Mas El Pitcher Pt Rico','PONCH',''),(686,'Poncha Mas El Pitcher De VZL','PONCH',''),(687,'Poncha Mas El Pitcher De DOM','PONCH',''),(688,'Poncha Mas El Pitcher De MEX','PONCH',''),(689,'Mas Errores Venezuela','EVZL',''),(690,'Mas Errores Pt Rico','EPTR',''),(691,'Mas Errores Mexico','EMEX',''),(692,'Mas Errores Dominicana','EDOM',''),(693,'R.Irlanda','R.I',''),(694,'Lille osc','LILLE',''),(695,'Anderlecht','ANDER',''),(696,'sporting de lisboa','SPORT',''),(697,'Alianza Lima (PER)','ALIZ',''),(698,'Juan Aurich (PER)','JACH',''),(699,'Banfield (ARG)','BANF',''),(700,'Monarcas Morelia (MEX)','MOMO',''),(701,'Velez Sarfield (ARG)','VSR',''),(702,'Corinthians (BRA)','CORT',''),(703,'Cerro PorteÃ±o (PAR)','CPOR',''),(704,'Once Caldas (COL)','OCEL',''),(705,'Monterrey (MEX)','MONT',''),(706,'Flamengo (BRA)','FLA',''),(707,'U Catolica (CHI)','UCAT',''),(708,'R C Montevideo (URU)','RCM',''),(709,'Internacional (BRA)','INTB',''),(710,'CA Cerro','CACRR',''),(711,'Blooming (BOL)','BLOO',''),(712,'Dep Italia (VZL)','DITA',''),(713,'Rep De Corea','RCO',''),(714,'Argelia','ARG',''),(715,'Eslovenia','ESL',''),(716,'Eslovaquia','EQI',''),(717,'RDP de Corea','RDP',''),(718,'San Diego Padres Game2','SAN ','');
UNLOCK TABLES;
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;

--
-- Table structure for table `equipo_liga`
--

DROP TABLE IF EXISTS `equipo_liga`;
CREATE TABLE `equipo_liga` (
  `id_equipo` int(11) NOT NULL,
  `id_liga` int(11) NOT NULL,
  PRIMARY KEY  (`id_equipo`,`id_liga`),
  KEY `id_equipo` (`id_equipo`),
  KEY `id_liga` (`id_liga`),
  CONSTRAINT `FK_equipo_liga_2` FOREIGN KEY (`id_liga`) REFERENCES `liga` (`id_liga`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_equipo_liga_3` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id_equipo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `equipo_liga`
--


/*!40000 ALTER TABLE `equipo_liga` DISABLE KEYS */;
LOCK TABLES `equipo_liga` WRITE;
INSERT INTO `equipo_liga` VALUES (2,7),(5,7),(6,7),(10,7),(11,7),(12,7),(14,7),(15,7),(18,7),(20,12),(20,19),(20,33),(20,40),(21,7),(22,19),(22,33),(22,40),(25,19),(25,34),(26,7),(30,7),(32,7),(33,7),(34,1),(36,7),(37,7),(38,1),(43,7),(44,7),(45,7),(46,7),(47,7),(51,33),(55,7),(56,7),(57,7),(58,7),(60,40),(62,7),(64,7),(65,7),(67,7),(68,10),(69,10),(70,10),(71,10),(72,10),(73,10),(74,10),(75,10),(76,10),(77,10),(78,10),(79,10),(80,10),(81,10),(82,10),(83,10),(84,10),(85,10),(86,10),(87,10),(88,10),(89,10),(90,10),(91,10),(92,10),(93,10),(94,10),(95,10),(96,10),(98,12),(98,19),(98,33),(98,37),(98,40),(100,3),(101,3),(102,3),(103,3),(104,3),(105,3),(106,3),(107,3),(108,3),(109,3),(110,3),(111,3),(112,3),(113,3),(114,3),(115,3),(116,3),(117,3),(118,3),(119,3),(120,3),(121,3),(122,3),(123,3),(124,3),(125,3),(126,3),(127,3),(128,3),(129,3),(130,3),(131,3),(132,1),(133,1),(134,1),(135,1),(136,1),(137,1),(138,4),(139,4),(140,4),(141,4),(142,4),(143,4),(144,4),(145,4),(146,4),(147,4),(148,4),(149,4),(150,4),(151,4),(152,4),(153,4),(154,4),(155,4),(156,4),(157,4),(158,4),(159,4),(160,4),(161,4),(162,4),(163,4),(164,4),(165,4),(166,4),(167,4),(168,11),(169,11),(170,11),(171,11),(172,11),(173,11),(174,11),(175,11),(176,11),(177,11),(178,11),(179,11),(180,11),(181,11),(182,11),(183,11),(184,11),(185,11),(186,11),(187,11),(188,11),(189,11),(190,11),(191,11),(192,11),(193,11),(194,11),(195,11),(196,11),(197,7),(200,12),(200,18),(200,33),(200,40),(201,12),(201,34),(201,37),(201,40),(202,12),(203,12),(203,19),(204,12),(204,19),(205,12),(205,19),(206,12),(207,12),(207,33),(207,34),(208,12),(208,33),(208,34),(208,36),(208,37),(208,40),(209,12),(209,40),(210,33),(210,34),(210,40),(211,19),(211,33),(211,34),(211,36),(211,37),(211,40),(212,18),(212,37),(213,19),(213,40),(215,18),(215,33),(215,36),(215,37),(215,40),(216,19),(217,19),(217,33),(220,14),(221,14),(222,14),(222,17),(222,23),(223,14),(223,17),(224,14),(225,14),(225,17),(225,23),(226,14),(227,14),(228,14),(229,14),(230,14),(231,14),(232,14),(233,14),(234,14),(235,14),(236,14),(236,17),(237,14),(238,14),(239,14),(240,15),(241,15),(242,15),(243,15),(243,17),(243,23),(244,15),(245,15),(246,15),(246,17),(247,15),(248,15),(248,17),(249,15),(250,15),(251,15),(251,23),(252,15),(252,17),(253,15),(253,23),(254,15),(255,15),(255,23),(256,15),(257,15),(258,15),(259,15),(260,16),(261,16),(261,17),(261,23),(262,16),(263,16),(264,16),(264,23),(265,16),(266,16),(267,16),(267,23),(268,16),(268,32),(269,16),(270,16),(271,16),(272,16),(272,17),(273,16),(273,23),(274,16),(274,17),(275,16),(276,16),(277,16),(277,23),(278,16),(278,17),(279,16),(279,23),(280,17),(283,23),(289,17),(292,17),(293,17),(295,17),(297,17),(302,18),(302,33),(302,37),(302,40),(303,18),(304,18),(305,18),(305,33),(305,40),(306,18),(306,33),(307,18),(307,37),(307,40),(308,18),(321,7),(322,19),(322,33),(322,40),(323,19),(324,19),(324,37),(325,19),(326,19),(327,20),(328,20),(329,20),(330,20),(331,20),(332,20),(333,20),(334,20),(335,20),(336,20),(337,20),(338,20),(339,20),(340,10),(343,10),(344,22),(345,22),(346,22),(347,22),(348,22),(349,22),(354,20),(355,20),(356,23),(357,23),(358,23),(359,17),(359,23),(360,23),(361,23),(362,23),(363,23),(364,23),(365,23),(366,23),(367,23),(368,23),(369,23),(370,23),(371,17),(371,23),(372,23),(373,23),(374,23),(375,23),(376,23),(377,23),(378,23),(379,17),(379,23),(380,23),(381,23),(382,13),(383,13),(390,23),(391,23),(392,23),(393,23),(394,23),(395,24),(396,24),(403,25),(404,25),(405,25),(406,25),(407,25),(408,25),(409,16),(410,16),(411,15),(412,15),(413,16),(414,13),(415,13),(416,26),(417,26),(418,28),(418,29),(419,28),(419,29),(420,28),(420,29),(421,27),(422,27),(423,27),(424,27),(425,27),(426,27),(427,27),(428,27),(429,27),(430,27),(431,27),(432,27),(433,27),(434,27),(435,27),(436,27),(437,27),(438,27),(439,27),(440,27),(441,27),(442,27),(443,27),(444,27),(445,27),(446,27),(447,19),(447,27),(447,33),(448,27),(449,27),(450,27),(451,27),(452,27),(474,20),(475,20),(476,20),(477,20),(478,20),(479,20),(480,30),(481,30),(482,30),(483,30),(484,30),(485,30),(486,30),(487,31),(487,33),(487,34),(488,31),(488,34),(489,31),(489,34),(490,31),(490,33),(490,34),(490,40),(491,31),(492,31),(493,31),(494,31),(503,32),(504,32),(505,32),(506,32),(507,32),(508,32),(509,32),(510,32),(511,32),(512,32),(513,32),(514,32),(515,32),(516,32),(517,32),(518,32),(519,19),(519,33),(519,37),(519,40),(520,33),(521,33),(521,40),(522,19),(522,33),(523,33),(526,32),(527,32),(528,32),(529,32),(530,32),(531,32),(532,32),(533,32),(534,32),(535,32),(536,32),(537,23),(538,23),(539,32),(540,32),(541,32),(542,34),(543,33),(543,34),(544,33),(544,34),(544,37),(545,34),(546,34),(546,40),(547,34),(548,32),(549,19),(550,19),(551,19),(552,20),(553,20),(554,20),(555,35),(556,35),(557,23),(558,23),(560,7),(561,32),(569,19),(569,33),(569,40),(570,19),(573,10),(574,10),(577,36),(577,37),(578,36),(578,40),(579,33),(579,36),(579,37),(580,36),(582,33),(582,36),(582,37),(597,41),(598,41),(611,16),(614,19),(616,19),(616,37),(617,19),(618,20),(619,20),(620,15),(621,14),(622,17),(623,17),(624,17),(625,17),(626,17),(627,17),(627,23),(628,17),(629,17),(630,17),(631,17),(632,17),(633,17),(634,13),(635,13),(636,13),(637,13),(638,14),(639,37),(640,37),(640,40),(641,37),(642,33),(642,37),(642,40),(643,37),(644,37),(644,40),(645,37),(646,37),(647,37),(647,40),(648,15),(649,19),(650,19),(651,19),(652,19),(653,15),(654,1),(655,1),(656,1),(657,1),(658,1),(659,1),(660,1),(661,13),(662,33),(662,40),(663,16),(664,38),(665,38),(666,38),(667,38),(668,38),(669,38),(670,39),(671,39),(672,39),(673,39),(674,39),(675,39),(676,39),(677,39),(678,1),(679,1),(681,31),(682,31),(683,31),(684,31),(685,31),(686,31),(687,31),(688,31),(689,31),(690,31),(691,31),(692,31),(693,33),(694,23),(695,23),(696,23),(697,32),(698,32),(699,32),(700,32),(701,32),(702,32),(703,32),(704,32),(705,32),(706,32),(707,32),(708,32),(709,32),(710,32),(711,32),(712,32),(713,40),(714,40),(715,40),(716,40),(717,40);
UNLOCK TABLES;
/*!40000 ALTER TABLE `equipo_liga` ENABLE KEYS */;

--
-- Table structure for table `juego`
--

DROP TABLE IF EXISTS `juego`;
CREATE TABLE `juego` (
  `id_juego` int(11) NOT NULL auto_increment,
  `fecha_sis` datetime NOT NULL,
  `fecha_ini` datetime NOT NULL,
  `fecha_fin` datetime default NULL,
  `minutos_cierre` int(11) NOT NULL,
  `id_campeonato` int(11) NOT NULL,
  `id_status_juego` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_deporte` int(11) NOT NULL,
  `id_liga` int(11) NOT NULL,
  `spread_activo` int(1) NOT NULL default '1',
  `total_activo` int(1) NOT NULL default '1',
  `money_activo` int(1) NOT NULL default '1',
  `id_juego_padre` int(11) default NULL,
  `id_usuario_creador` int(11) NOT NULL,
  PRIMARY KEY  (`id_juego`),
  KEY `id_juego_padre` (`id_juego_padre`),
  KEY `id_campeonato` (`id_campeonato`),
  KEY `id_status_juego` (`id_status_juego`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_deporte` (`id_deporte`),
  KEY `id_liga` (`id_liga`),
  CONSTRAINT `FK_juego_1` FOREIGN KEY (`id_campeonato`) REFERENCES `campeonato` (`id_campeonato`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_juego_2` FOREIGN KEY (`id_status_juego`) REFERENCES `status_juego` (`id_status_juego`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_juego_3` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_juego_4` FOREIGN KEY (`id_deporte`) REFERENCES `deporte` (`id_deporte`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_juego_5` FOREIGN KEY (`id_liga`) REFERENCES `liga` (`id_liga`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_juego_padre` FOREIGN KEY (`id_juego_padre`) REFERENCES `juego` (`id_juego`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `juego`
--


/*!40000 ALTER TABLE `juego` DISABLE KEYS */;
LOCK TABLES `juego` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `juego` ENABLE KEYS */;

--
-- Table structure for table `juego_equipo`
--

DROP TABLE IF EXISTS `juego_equipo`;
CREATE TABLE `juego_equipo` (
  `id_juego_equipo` int(11) NOT NULL auto_increment,
  `id_juego` int(11) NOT NULL,
  `id_equipo` int(11) NOT NULL,
  `ganador` int(11) default NULL,
  `puntos` int(11) default NULL,
  `referencia` int(11) NOT NULL,
  `referencia_runline` int(11) NOT NULL,
  `referencia_ab` varchar(10) NOT NULL,
  PRIMARY KEY  (`id_juego_equipo`),
  KEY `id_equipo` (`id_equipo`),
  KEY `id_juego` (`id_juego`),
  CONSTRAINT `FK_juego_equipo_1` FOREIGN KEY (`id_juego`) REFERENCES `juego` (`id_juego`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_juego_equipo_2` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id_equipo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `juego_equipo`
--


/*!40000 ALTER TABLE `juego_equipo` DISABLE KEYS */;
LOCK TABLES `juego_equipo` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `juego_equipo` ENABLE KEYS */;

--
-- Table structure for table `juego_equipo_lanzador`
--

DROP TABLE IF EXISTS `juego_equipo_lanzador`;
CREATE TABLE `juego_equipo_lanzador` (
  `id_juego_equipo` int(11) NOT NULL,
  `id_lanzador` int(11) NOT NULL,
  PRIMARY KEY  (`id_juego_equipo`,`id_lanzador`),
  KEY `id_lanzador` (`id_lanzador`),
  KEY `id_juego_equipo` (`id_juego_equipo`),
  CONSTRAINT `FK_juego_equipo_lanzador_1` FOREIGN KEY (`id_juego_equipo`) REFERENCES `juego_equipo` (`id_juego_equipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_juego_equipo_lanzador_2` FOREIGN KEY (`id_lanzador`) REFERENCES `lanzador` (`id_lanzador`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `juego_equipo_lanzador`
--


/*!40000 ALTER TABLE `juego_equipo_lanzador` DISABLE KEYS */;
LOCK TABLES `juego_equipo_lanzador` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `juego_equipo_lanzador` ENABLE KEYS */;

--
-- Table structure for table `jugada`
--

DROP TABLE IF EXISTS `jugada`;
CREATE TABLE `jugada` (
  `id_jugada` int(11) NOT NULL auto_increment,
  `fecha_sis` datetime NOT NULL,
  `fecha_exp` datetime default NULL,
  `monto_apostado` double NOT NULL,
  `monto_premio` double NOT NULL,
  `monto_pagado` double default NULL,
  `id_usuario` int(11) NOT NULL,
  `id_status_jugada` int(11) NOT NULL,
  `dias_expira` int(11) NOT NULL,
  `fecha_pago` datetime default NULL,
  `detalle_equipo` varchar(1000) default NULL,
  `cancelada` int(11) default NULL,
  PRIMARY KEY  (`id_jugada`),
  KEY `id_status_jugada` (`id_status_jugada`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_usuario_2` (`id_usuario`),
  CONSTRAINT `FK_jugada_3` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_jugada_4` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_jugada_5` FOREIGN KEY (`id_status_jugada`) REFERENCES `status_jugada` (`id_status_jugada`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jugada`
--


/*!40000 ALTER TABLE `jugada` DISABLE KEYS */;
LOCK TABLES `jugada` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `jugada` ENABLE KEYS */;

--
-- Table structure for table `jugada_juego_equipo`
--

DROP TABLE IF EXISTS `jugada_juego_equipo`;
CREATE TABLE `jugada_juego_equipo` (
  `id_jugada_juego_equipo` int(11) NOT NULL auto_increment,
  `id_jugada` int(11) NOT NULL,
  `id_usuario_juego_equipo` int(11) NOT NULL,
  `tipo` varchar(2) NOT NULL,
  `id_status_jugada` int(11) NOT NULL,
  PRIMARY KEY  (`id_jugada_juego_equipo`),
  KEY `id_status_jugada` (`id_status_jugada`),
  KEY `id_usuario_juego_equipo` (`id_usuario_juego_equipo`),
  KEY `id_jugada` (`id_jugada`),
  CONSTRAINT `FK_jugada_juego_equipo_2` FOREIGN KEY (`id_usuario_juego_equipo`) REFERENCES `usuario_juego_equipo` (`id_usuario_juego_equipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_jugada_juego_equipo_3` FOREIGN KEY (`id_jugada`) REFERENCES `jugada` (`id_jugada`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_jugada_juego_equipo_4` FOREIGN KEY (`id_status_jugada`) REFERENCES `status_jugada` (`id_status_jugada`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jugada_juego_equipo`
--


/*!40000 ALTER TABLE `jugada_juego_equipo` DISABLE KEYS */;
LOCK TABLES `jugada_juego_equipo` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `jugada_juego_equipo` ENABLE KEYS */;

--
-- Table structure for table `lanzador`
--

DROP TABLE IF EXISTS `lanzador`;
CREATE TABLE `lanzador` (
  `id_lanzador` int(11) NOT NULL auto_increment,
  `nombre_lanzador` varchar(50) NOT NULL,
  `id_equipo` int(11) NOT NULL,
  PRIMARY KEY  (`id_lanzador`),
  KEY `id_equipo` (`id_equipo`),
  CONSTRAINT `FK_lanzador_1` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id_equipo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lanzador`
--


/*!40000 ALTER TABLE `lanzador` DISABLE KEYS */;
LOCK TABLES `lanzador` WRITE;
INSERT INTO `lanzador` VALUES (1,'D. Matzuzaka (D)',6),(3,'C. Zambrano (D)',14),(4,'F. Liriano (Z)',37),(5,'C. M .Wang (D)',44),(7,'A. Pettitte (Z)',44),(8,'K. Igawa (D)',44),(9,'P. Hughes (D)',44),(10,'M. Mussina (D)',44),(11,'T. Wakefield (D)',6),(12,'C. Buchholz (D)',6),(13,'J. Lester (Z)',315),(14,'G. Olson (Z)',5),(15,'T. Patton',5),(16,'B. Burres (D)',5),(17,'S. Trachsel',5),(18,'J. Guthrie (D)',5),(19,' A. Loewen',5),(20,'D. Cabrera (D)',5),(21,'S. Kazmir (Z)',32),(22,'J. Hammel (D)',62),(23,'E. Jackson (D)',62),(24,'A. Sonnanstin (D)',62),(25,'J. Shields (D)',62),(26,'M. Garza (D)',62),(27,'R. Halladay (D)',46),(28,'A.J. Burnett (D)',65),(29,'D. McGowan',65),(30,'J. Litsch (D)',65),(31,'S. Marcum (D)',65),(32,'C. Janssen (D)',65),(33,'J. Vazquez (D)',44),(34,'G. Floyd (D)',15),(35,'M. Buehrle (Z)',15),(36,'J. Contreras (D)',15),(37,'J. Danks (Z)',15),(38,'C. Lee (Z)',56),(39,'P. Byrd (D)',11),(40,'J. Westbrook',11),(42,'F. Carmona (D)',11),(43,'A. Laffey (Z)',11),(44,'K. Rogers (Z)',18),(45,'N. Robertson (Z)',21),(46,'J. Bonderman (D)',321),(47,'D. Willis (Z)',2),(48,'J. Verlander (D)',18),(49,'A. Galarraga (D)',18),(50,'B. Bannister (D)',30),(51,'L. Hochevar (D)',30),(52,'Z. Greinke (D)',30),(53,'B. Tomko (D)',30),(54,'G. Meche (D)',30),(55,'J. Bale (D)',30),(56,'L. Hudson (D)',30),(57,'L. Hernandez (D)',37),(58,'S. Baker (D)',37),(59,'G. Perkins (Z)',37),(60,'B. Bonser',37),(61,'N. Blackburn (D)',37),(62,'K. Slowey (D)',37),(63,'N. Adenhart',32),(65,'D. Moseley (D)',32),(66,'E. Santana (D)',32),(67,'J. Saunders (Z)',32),(68,'Jer. Weaver (D)',32),(69,'J. Lackey (D)',6),(70,'J. Garland (D)',32),(71,'G. Smith (Z)',12),(72,'J. Duchscherer (D)',45),(73,'J. Blanton (Z)',45),(74,'D. Eveland (Z)',47),(77,'M. Batista (D)',56),(78,'J. Washburn (Z)',56),(79,'C. Silva (D)',14),(80,'E. Bedard (Z)',56),(81,'F. Hernandez (D)',56),(82,'K. Millwood (D)',5),(84,'V. Padilla (D)',64),(85,'J. Jennings (D)',64),(86,'B. McCarthy (D)',64),(87,'K. Gabbard',64),(88,'T. Glavine',3),(89,'J. Smoltz',3),(90,'M. Hampton',3),(91,'T. Hudson',3),(92,'C. James',3),(93,'Jo-Jo Reyes',3),(94,'B. Badenhop (D)',21),(95,'A. Sanchez (D)',610),(96,'R. Nolasco (D)',21),(97,'J. Johnson (D)',21),(98,'S. Olsen (Z)',21),(99,'A. Miller (Z)',21),(100,'S. Mitre',21),(101,'M. Hendrickson (Z)',21),(102,'P. Martinez (D)',43),(103,'O. Hernandez',43),(104,'N. Figueroa (D)',46),(105,'J. Santana (Z)',43),(106,'O. Perez (Z)',43),(107,'M. Pelfrey (D)',43),(108,'J. Maine (D)',43),(109,'A. Eaton',46),(110,'J. Moyer (Z)',46),(111,'B. Myers (D)',26),(112,'C. Hamels (Z)',46),(113,'S. Mathieson',46),(114,'K. Kendrick (D)',46),(115,'O. Perez (Z)',67),(116,'T. Redding (D)',43),(117,'M. Chico',67),(118,'S. Hill',67),(119,'J. Lannan (Z)',67),(120,'T. Lilly (Z)',14),(121,'J. Marquis (D)',67),(122,' R. Dempster (D)',14),(123,'J. Lieber',14),(124,'A. Harang (D)',10),(125,'M. Belisle',10),(126,'B. Arroyo (D)',10),(127,'J. Cueto (D)',613),(128,'B. Livingston',10),(129,'E. Volquez (D)',10),(130,'W. Rodriguez (Z)',26),(131,'C. Sampson (D)',26),(132,'B. Backe (D)',26),(133,'R. Oswalt (D)',26),(134,'S. Chacon',26),(135,'F. Paulino (D)',26),(136,'D. Borkowski (D)',26),(138,'C. Capuano (D)',36),(140,'C. Villanueva',36),(141,'J. Suppan (D)',58),(142,'D. Bush (D)',36),(143,'B. Sheets (D)',45),(149,'C. Carpenter (D)',58),(150,'B. Looper (D)',58),(151,'M. Mulder (D)',58),(152,'J. Pineiro (D)',32),(153,'A. Wainwright (D)',58),(154,'T. Wellemeyer (D)',57),(155,'M. Clement (D)',58),(156,'K. Lohse (D)',58),(157,'R. Johnson (Z)',2),(158,'D. Davis (Z)',36),(159,'B. Webb (D)',2),(160,'E. Gonzalez',2),(161,'D. Haren (D)',2),(162,'M. Owings',2),(163,'M. Scherzer (D)',18),(164,'J. Hirsh',12),(165,'U. Jimenez (D)',12),(166,'G. Reynolds (D)',12),(167,'A. Cook (D)',609),(168,'J. Francis (Z)',12),(169,'J. De La Rosa (Z)',12),(170,'C. Billingsley (D)',33),(171,'J.Ely (D)',33),(172,'B. Penny (D)',33),(173,'J. Schmidt (D)',33),(174,'H. Kuroda (D)',33),(175,'Hong-Chih Kuo (Z)',33),(176,'R. Wolf',55),(177,'G. Maddux (D)',55),(178,'M. Prior',55),(179,'J. Peavy (D)',55),(180,'J. Germano (D)',55),(181,'C. Young (D)',55),(182,'C. Hensley',55),(183,'T. Stauffer (D)',55),(184,'P. Misch (D)',57),(185,'T. Lincecum (D)',57),(186,'J. Sanchez (Z)',57),(187,'B. Zito (Z)',57),(188,'K. Correia (D)',57),(189,'N. Lowry (D)',57),(190,'M. Cain (D)',57),(191,'I Snell (D)',47),(192,' Z. Duke (Z)',47),(193,'P. Maholm (Z)',612),(194,'T. Gorzelanny (D)',47),(195,'C.C. Sabathia (Z)',36),(196,'T. Hudson',197),(197,'J. Campillo (D)',197),(198,'J. Jurrjens (D)',197),(199,'JJ. Reyes (Z)',197),(200,'J. Tavarez (D)',197),(201,'M. Gonzalez (Z)',197),(202,'B. Carlyle (D)',197),(203,'M. Moehler (D)',26),(204,'G. Rusch (Z)',12),(205,'J.Beckett (D)',6),(206,'J. Fogg (D)',10),(207,'Y. Herrera',47),(208,'M. Harrison (Z)',64),(209,'J. Parrish (Z)',65),(210,'C. Baek (D)',55),(211,'J. Garcia (Z)',58),(212,'M. Parra (Z)',36),(213,'S. Mcclung',36),(214,'S. Ponson (D)',44),(215,'R. Liz (D)',5),(216,'C. Volstad (D)',21),(217,'J. Banks (D)',55),(218,'H. Bailey (D)',10),(219,'Van Benschoten',47),(220,'Ru. Hernandez',26),(221,'E. Hurley (D)',64),(222,'Z. Miner (D)',18),(229,'R. Harden (D)',14),(230,'S. Stults (Z)',33),(231,'K. Weel',12),(232,'D. Rasner (D)',44),(233,'J. Blanton (D)',46),(234,'D. Branden (Z)',45),(235,'J. Cassell',26),(236,'L. Mendoza (D)',64),(237,'K. Davies (D)',30),(238,'Y. Petit (D)',2),(239,'C. Kershaw (Z)',33),(240,'M. Ginter',11),(241,'R. Dickey (D)',56),(242,'J. Bergmann (D)',321),(243,'C. Morton (D)',197),(244,'Vandenhurk (D)',21),(245,'C. Richard (D)',15),(246,'C. Balester (D)',67),(247,'J. Chamberlain (D)',44),(248,'M. Boggs (D)',58),(249,'S. Gallagher (D)',45),(250,'M. Hampton (Z)',197),(251,'B. Knight',43),(252,'J. Sowers (Z)',11),(253,'R. Wolf (Z)',36),(254,'S. Feldman (D)',64),(255,'J. Johnson (D)',33),(256,'D. Sarfate (D)',5),(257,'S. Richmond (D)',65),(258,'C. James',197),(259,'J Karstens (D)',47),(260,'De Los Santos',12),(261,'D. Purcey (Z)',65),(262,'C. Waters (Z)',5),(263,'G. Gonzalez (Z)',45),(264,'T Hunter (D)',64),(265,'O. Perez',198),(266,'U. Jimenez',199),(267,'T. PeÃ±a (D)',2),(268,'An Reyes (D)',11),(269,'I. Kennedy',2),(270,'B. Stokes (D)',43),(271,'B. Giese (D)',44),(272,'D. Meyer (Z)',45),(273,'Rolanw-Smith (Z)',56),(274,'L. Hernandez (D)',12),(275,'J. Davies (D)',47),(276,'G. Mock (D)',67),(277,'C. Zink (D)',6),(278,'R. Harden (D)',64),(279,'J Campillo (D)',218),(280,'L. Broadway (D)',15),(281,'T. Glavine. (Z)',197),(282,'Z Jackson (Z)',11),(283,'P. Byrd (D)',6),(284,'B. Thompson (D)',58),(285,'S. Marshall (Z)',14),(286,'C Reineke (D)',55),(287,'Feierabend (Z)',56),(288,'O Nippert (D)',64),(289,'G Maddux (D)',33),(290,'D Hayhurst (D)',55),(291,'C Pavano (D)',44),(292,'C Lambert (D)',18),(293,'M Palmer (D)',57),(294,'Ra. Ramirez (D)',10),(295,'J. Geer (D)',55),(296,'B Duckwort (D)',30),(297,'D Pauley (D)',6),(298,'J.Niese (Z)',571),(299,'W. Leblanc (Z)',55),(300,'R Ohlendorf (D)',47),(301,'L Cormier (D)',5),(302,'S. Estes (Z)',55),(303,'K Davies (D)',301),(304,'D Mayer (Z)',300),(305,'B. Morrow (D)',65),(306,'J Outman (Z)',300),(307,'D Cabrera (D)',309),(308,'C. Hamels (Z)',300),(309,'Jo. Santana (Z)',310),(310,'C. Richard (Z)',311),(311,'J Litchs (D)',312),(312,'A Aceves (D)',44),(313,'J Parr (D)',197),(314,'Hennessey (D)',57),(315,'C Jimenez (Z)',56),(316,'S Lewis (Z)',11),(317,'R Tejada (D)',313),(318,'Bullingtong (D)',314),(319,'B Colon(D)',315),(320,'G.Olson (D)',318),(321,'L. Broadway (D)',311),(322,'G. Perkins (D)',319),(323,'J. Niese (D)',43),(324,'S. Ponson (D)',317),(325,'J. Shields (D)',615),(326,'Z. Miner (D)',321),(327,'A. Arias (D)',26),(328,'J. Outman (Z)',45),(329,'K. Saarloos (D)',45),(331,'Barthmaier (D)',47),(332,'J. Happ (Z)',46),(333,'D. Price (Z)',62),(334,'B. Bass (D)',5),(335,'F. Garcia (D)',18),(336,'S Martis (D)',67),(337,'Y Gallardo (D)',36),(338,'Alex  Herrera (Z)',134),(339,'Carlos  Rivas (Z)',34),(340,'Harold  Eckert (D)',132),(341,'Victor  Zambrano (D)',38),(342,'Heath Totten (Z)',136),(343,'Horacio Estrada (Z)',137),(344,'Mike Romano (Z)',133),(345,'Carlos Carrasco (D)',34),(346,'Amalio Diaz (D)',134),(347,'Giovanni Carrara (D)',133),(348,'Les Walrond (Z)',38),(349,'Carlos Monasterios (D)',135),(350,'Rosman Garcia (D)',137),(352,'B.J Lamura (D)',136),(353,'Henry Bonilla (D)',132),(354,'Michael Connolly (Z)',136),(355,'Kristhian Linares (Z)',135),(356,'Travis Chick (D)',38),(357,'Brian Gordon (D)',132),(358,'Juan C. Gutierrez (D)',34),(359,'Tony Armas Jr. (D)',34),(360,'Yorman Bazardo (D)',137),(361,'Victor Moreno (D)',137),(362,'Gustavo Chacin (Z)',133),(363,'Ivan Blanco (D)',133),(364,'Franklin Morales (Z)',34),(365,'Enrique Gonzalez (D)',132),(366,'Yusmeiro Petit (D)',38),(367,'Freddy Garcia (D)',38),(368,'Wilfredo Ledezma (Z)',135),(369,'Jose Ortegano (Z)',34),(370,'Michael Tejera (D)',134),(371,'Edgar Martinez (D)',135),(372,'Stephen Randolph (Z)',132),(373,'Chris Jakubauskas (D)',133),(374,'Matt Maloney (D)',38),(375,'Ismael Ramirez (D)',136),(376,'Rich Hill (Z)',137),(377,'Enrique Gonzalez (D)',132),(378,'David Austin (D)',136),(379,'Jose Sanchez (D)',38),(380,'Kyle Middleton (D)',137),(381,'Andruw Baldwin (D)',133),(382,'Jesus Silva (D)',135),(383,'Ben Hendrickson (D)',34),(384,'Matt de Salvo (D)',134),(385,'Jeff Kennard (D)',135),(386,'Angel Guzman (D)',38),(387,'Kasey Olenberguer (D)',135),(388,'Jonathan Rouwenhorst (Z)',136),(389,'Tracy Thorpe (D)',133),(390,'Mike Smith (D)',134),(391,'Joshua Miller (D)',134),(392,'J.R Mathis (Z)',34),(393,'J.Carlos Granados (Z)',136),(394,'J.Carlos Granados',350),(395,'Kristian Linares',351),(396,'Jonas Cuotto (D)',134),(397,'Ruben Quevedo (D)',137),(398,'Giovanni Carrara',352),(399,'Cesar Jimenez',352),(400,'Alex Herrera',353),(401,'Mike Smith',353),(402,'Reny Duarte',352),(403,'Justin  Mallet (D)',385),(404,'Luis Ramirez',135),(405,'Anthony Ortega',384),(406,'Alberto Bastardo',135),(407,'Jesus Delgado (D)',137),(408,'CÃ©sar JimÃ©nez (Z)',133),(409,'Katsuhiko Maekawa',134),(410,'Carlos HernÃ¡ndez',38),(411,'Ryan Drese (D)',137),(412,'Dave Mckae (D)',137),(413,'Anibal Sanchez (D)',38),(414,'J Sequea (D)',134),(415,'F. Ballestas (D)',136),(416,'R. Ramirez (D)',38),(417,'J. Farnsworth',353),(418,'I. Ramirez',350),(419,'K. Bouknight',134),(420,'Rayner Oliveros',34),(421,'T. Harikkala',487),(422,'K. Middlenton',389),(423,'Justin Mallet (D)',135),(424,'J. Farnsworth',134),(425,'B. Holliday',135),(426,'R. Mackae',389),(427,'V. Santos',38),(428,'B. Knox (D)',487),(429,'A. Lorraine',133),(430,'A. Ortega (D)',132),(431,'R. Orta',133),(432,'D. Guerra (D)',38),(433,'G. Norrito (D)',135),(434,'C. Monasterios (D)',33),(435,'A. Bastardo (Z)',46),(436,'J.C Gutierrez (D)',398),(437,'Tony Armas Jr. (D)',398),(438,'T Harikkala',135),(439,'T Harikkala',135),(440,'J C Granados',399),(441,'D McKae',400),(442,'Julio Mateo (D)',34),(443,'K Bouknight',136),(444,'H Totten',134),(445,'J C Gutierrez',401),(446,'G Norrito',351),(447,'J. Garcia',400),(448,'Harold Heckert',402),(449,'Raul Rivero',133),(450,'Vladimir NuÃ±ez',133),(451,'T Harikkala',137),(452,'A Herrera',132),(453,'Alberto Bastardo (Z)',136),(454,'E. McLane',137),(455,'J Mallet',34),(456,'J Farnsworth',487),(457,'C Galva',136),(458,'Ramon Ramirez (D)',136),(459,'V. Zambrano',133),(460,'Justin Lehr',132),(461,'Julian Tavarez (D)',133),(462,'L Villareal',34),(463,'N. Figueroa (D)',34),(464,'H. Totten (D)',137),(465,'Vladimir NuÃ±ez (D)',34),(466,'P. Ortega',490),(467,'J. Sosa',488),(468,'G. Alvarado',490),(469,'Hector Mercado (z)',489),(470,'Nerio Rodriguez',488),(471,'C Sabathia (Z)',44),(472,'D Lowe (D)',197),(473,'S. Olsen (Z)',67),(474,'B. Tallet (Z)',312),(475,'E. Jackson  (D)',2),(476,'T. Cahill (D)',45),(477,'R. Wolf (Z)',33),(478,'D Cabrera (D)',67),(479,'J Vasquez (R)',197),(480,'F Morales (Z)',12),(481,'K Uehara (D)',5),(482,'B. Moehler (D)',26),(483,'W Silva (D)',55),(484,'R Johnson (Z)',57),(485,'R Porcello (D)',18),(486,'R Romero (Z)',65),(487,'A Burnett (D)',317),(488,'A Simon (D)',5),(489,'C Pavano (D)',11),(490,'K Correia (D)',55),(491,'B Anderson (Z)',45),(492,'B. Looper (D)',36),(493,'M. Hampton (Z)',26),(494,'S. Hill (D)',55),(495,'K. Benson (D)',64),(496,'S. Ponson (D)',30),(497,'P. Hendrikson (Z)',5),(498,'R. Dickey (D)',43),(499,'J. Marquis (D)',12),(500,'J. Mcdonald (D)',33),(501,'J. Garland (D)',718),(502,'A. Eaton (D)',5),(503,'C. Park (D)',46),(504,'S. Loux (D)',32),(505,'M. Owings (D)',10),(506,'M. Owings (D)',10),(507,'K. Kawakami (D)',197),(508,'R. Ortiz (D)',33),(509,'J. Niemann (D)',62),(510,'C. Jakaubaskas (D)',47),(511,'P. Walters (D)',58),(512,'B. Penny (D)',58),(513,'L. Hernandez (D)',43),(514,'B. Colon (D)',15),(515,'D. Oliver (Z)',32),(516,'P. Masterson (D)',6),(517,'J. Zimmermann (D)',67),(518,'B Bergesen (D)',5),(519,'B. Penny (D)',315),(522,'F Liriano (Z)',319),(523,'M Palmer (D)',32),(524,'A. Ortega (D)',32),(525,'B Burres (Z)',65),(526,'G. Taylor (Z)',21),(527,'A. Martis (D)',67),(528,'J. Hammel (D)',12),(529,'J. Danks (Z)',560),(530,'F. Hernandez (D)',559),(531,'C Gaudin (D)',55),(532,'R. Ray (D)',65),(533,'B. Cecil (Z)',65),(534,'Dan Giese (D)',45),(535,'R. Wells (D)',14),(536,'J. Koronka (Z)',21),(537,'J. Vargas (Z)',56),(538,'Augenstain (D)',2),(539,'A. Carpenter (D)',562),(540,'D. Cabrera (D)',563),(541,'E. Milton (Z)',33),(542,'R. Hill (Z)',5),(543,'G. Olsen (Z)',56),(544,'D. Huff (Z)',11),(545,'R. Detwiler (Z)',67),(546,'Augenstein (D)',564),(547,'H. Penn (D)',565),(548,'J. Weaver (D)',33),(549,'C. Stammen (D)',67),(550,'K. Medlen (D)',197),(551,'B Buckner (D)',2),(552,'D Holland (Z)',566),(553,'A. Swarzak (D)',37),(554,'Ed. Gonzalez (D)',45),(555,'S. West (Z)',21),(556,'J. Berken (D)',5),(557,'D Hernandez (D)',5),(558,'Ed. Gonzalez (D)',568),(559,'S Feldman (D)',566),(560,'V Mazzaro (D)',45),(561,'R. Detwiler (Z)',572),(562,'M. Cain (D)',571),(563,'M. Maloney (Z)',10),(564,'K. Escobar (D)',32),(565,'T. Hanson (D)',197),(566,'J. Contreras (D)',576),(567,'J. Bonderman (D)',575),(568,'D Mathis (D)',64),(569,'T Okha (D)',11),(570,'F Nieve (D)',43),(571,'S.OÂ´Sullivan (D)',32),(572,'B. Mills (Z)',65),(573,'A. Figaro (D)',18),(574,'C. Morton (D)',47),(575,'M Burls (D)',36),(576,'J Smoltz (D)',6),(577,'V Vazques (D)',47),(578,'B. Chen (Z)',30),(579,'R. Lopez (D)',2),(580,'R. Sadowski (D)',57),(581,'L. French (Z)',18),(582,'M. Rzepczynski (Z)',65),(583,'K. Hart (D)',14),(584,'M. Latos (D)',55),(585,'J Martin (D)',67),(586,'S Mitre (D)',44),(587,'O Sulivan (D)',603),(588,'B Chen (Z)',604),(589,'S PONSON',604),(590,'E SANTANA',603),(591,'C Torres (D)',560),(592,'E Bonine (D)',18),(593,'J. Contreras',605),(594,'J. Verlander',606),(595,'C Tillman (D)',5),(596,'J Hammel',609),(597,'J Niese (z)',608),(598,'De La Rosa',607),(599,'J Lehr (D)',10),(600,'C Lee (Z)',46),(601,'C. Richard (Z)',55),(602,'B Norris (D)',26),(603,'I Snell',56),(604,'J Washburn (Z)',18),(605,'B Matusz (Z)',5),(606,'Gorzelanny (Z)',14),(607,'J. Martinez (D)',57),(608,'L. French (Z)',56),(609,'C. Reineke (D)',45),(610,'K Hart (D)',47),(611,'D. Fister (D)',56),(612,'J. Chacin (D)',12),(613,'J. Tazawa (D)',6),(614,'P. Martinez (D)',562),(615,'J. Samardzi (D)',14),(616,'T. Bell (D)',32),(617,'B. Parnell (D)',43),(618,'P. Masterson (D)',11),(619,'De La Rosa (Z)',609),(620,'Vandenhurk (D)',610),(621,'C. Haeger (D)',33),(622,'B. Tomko (D)',45),(623,'Y. Bazardo (D)',26),(624,'C. Gaudin (D)',44),(625,'B. Duensing (Z)',37),(626,'J. Smoltz (D)',58),(627,'C. Carrillo (D)',55),(628,'C. Pavano (D)',37),(629,'A. Gabino (D)',37),(630,'F. Garcia (D)',15),(631,'K. Wells (D)',10),(632,'J. Fogg (D)',12),(633,'V. Padilla (D)',33),(634,'P.Misch (Z)',43),(635,'B. Maccarthy (D)',566),(636,'P. Sonnanstine (D)',615),(637,'D Mccutchen (D)',47),(638,'B. Penny (D)',57),(639,'J Manship (D)',37),(640,'C Carrasco (D)',11),(641,'B Penny (D)',57),(642,'R Tejada (D)',30),(643,'J.Garland',55),(644,'J.Contreras (D)',12),(645,'L.Hernandez (D)',67),(646,'L.Dinardo (Z)',30),(647,'E.Mujica (D)',55),(648,'B.Tallet (Z)',65),(649,'C.Mortensen (D)',45),(650,'J.Lehr (D)',10),(651,'J.Cueto (D)',10),(652,'M.Estrada (D)',67),(653,'K.Mulvey (D)',2),(654,'E.Rogers (D)',12),(655,'A.Burnett (D)',44),(656,'W.Davis (D)',65),(657,'W.Davis (D)',62),(658,'T. Redding (D)',310),(659,'P.MAHOLM (Z)',47),(660,'J.Martin (D)',67),(661,'C.Narvenson (Z)',36),(662,'P.Martinez (D)',46),(663,'J.Lester (Z)',6),(664,'D.Hughes (Z)',30),(665,'J.Peavy',15),(666,'D.Holland (Z)',64),(667,'D.Hudson (D)',15),(668,'A.Lerew (D)',30),(669,'W.Lopez (D)',26),(670,'A.Sanchez (D)',21),(671,'Materson',314),(672,'J.Karstens (D)',612),(673,'S.Baker (D)',47),(674,'D.Cabrera (D)',2),(675,'C.Ramos (Z)',55),(676,'H Rondon (D)',34),(677,'R Brodway (D)',34),(678,'Kyle Parker',133),(679,'Joseph Durbin',132),(680,'Jason Jones',137),(681,'Carlos Castillo',38),(682,'F.Bello',134),(683,'S.Shell',133),(684,'F.Doubront',6),(685,'Seth.Etherton',137),(686,'Lance.Broadway',34),(687,'Wiston.Marquez',133),(688,'Jason.Schmidt',136),(689,'Nice.Ungs',135),(690,'Sean.Gallagher',38),(691,'Y. Herrera (D)',38),(692,'Ch. Manson',132),(693,'J Silva',134),(694,'J Jhones',137),(695,'K. Parker',134),(696,'Ch. Nicoll',134),(697,'D, Salinas',38),(698,'M Tejera',135),(699,'Yoan Pino',137),(700,'Bobby Livingston',132),(701,'Steven sharp',134),(702,'Heath Totten',136),(703,'Carlos Monasterio',135),(704,'R.Castillo',133),(705,'Ch.Mason',132),(706,'J.Chacin',34),(707,'D.HANKINS',133),(708,'W.Ledezma',137),(709,'C.Monasterio',654),(710,'Kyle Middlenton',655),(711,'Giov.Carrara',658),(712,'Juna.Granados',656),(713,'JuanC.Granados',656),(714,'E.Wordeckemper',136),(715,'J.Schmidt',656),(716,'Tim.Harikala',654),(717,'M. Alvarez',38),(718,'Tony Armas',134),(719,'G. Chacin',34),(720,'O. Hernadez',135),(721,'T. Thorpe',134),(722,'O. Poveda',137),(723,'C. Bowers',38),(724,'J. Browers',134),(725,'A. Johnson',132),(726,'D. Pollok',136),(727,'E. Barios',132),(728,'R. Palma',136),(729,'R. Palma',136),(730,'C.hernadez',34),(731,'F.Nieve',134),(732,'O.Poveda',137),(733,'B.Knox',34),(734,'A.Galarraga (D)',34),(735,'R.Ramirez (D)',132),(736,'J.Santiago',136),(737,'F.Nieve',38),(738,'C.Wilson',64),(739,'C.lewis',64),(740,'M.Talbot',11),(741,'M.Leake (D)',10),(742,'C.Valdez (D)',2),(743,'L.Atilano (D)',67),(744,'A.Cook (D)',12),(745,'B Burres (Z)',47),(746,'T.Ross (D)',45),(747,'S,Lecure (D)',10),(748,'C.Strasburg (D)',67),(749,'H.Takahashi (Z)',43),(750,'A.Ottavino (D)',58),(751,'B.Lincoln (D)',47),(752,'J.Arrieta (D)',5),(753,'A.Oliver (Z)',18),(754,'K.Hawksworth (D)',58),(755,'J.Banks (D)',26),(756,'M.Bumgarner (Z)',57),(757,'D.Thomas',14),(758,'D.Thomas',14),(759,'Diamond',14),(760,'J.Saunders',2),(761,'T.Lilly',33),(762,'D.Moseley',44),(763,'E.Jackson',15),(764,'JP.Happ',26),(765,'SO.Sullivan',30),(766,'J.Tomlin',11),(767,'D.Haren',32),(768,'R.Oswalt',46),(769,'MC.Donald',47),(770,'B.Enright',2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `lanzador` ENABLE KEYS */;

--
-- Table structure for table `liga`
--

DROP TABLE IF EXISTS `liga`;
CREATE TABLE `liga` (
  `id_liga` int(11) NOT NULL auto_increment,
  `desc_liga` varchar(100) NOT NULL,
  `iniciales` varchar(20) NOT NULL,
  `id_deporte` int(11) NOT NULL,
  PRIMARY KEY  (`id_liga`),
  UNIQUE KEY `UQ_liga_1` (`desc_liga`),
  KEY `id_deporte` (`id_deporte`),
  CONSTRAINT `FK_liga_1` FOREIGN KEY (`id_deporte`) REFERENCES `deporte` (`id_deporte`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `liga`
--


/*!40000 ALTER TABLE `liga` DISABLE KEYS */;
LOCK TABLES `liga` WRITE;
INSERT INTO `liga` VALUES (1,'Liga Profesional de Beisbol Venezolano','LPBV',4),(2,'Liga Profesional de Basquet','LPB',2),(3,'Futbol Americano','NFL',5),(4,'Hockey','NHL',3),(7,'Major League Baseball','MLB',4),(8,'NBA','NBA',2),(9,'Eurocopa 2008','EURO',1),(10,'National Basket Asotiation','NBA',6),(11,'MLB 5 Ining','MLB 5 INIG',7),(12,'Baket Olimpico','PEKIM 2008',6),(13,'MGM GRAND, LAS VEGAS, NV ','MGM',8),(14,'Italia Serie A','SERIE A',1),(15,'La Liga EspaÃ±a','LPFE',1),(16,'The Premier Inglesa','PREMIER',1),(17,'UEFA Champions League','UEFA',1),(18,'Eliminatorias Sudamericanas 2010','CONMEBOL',1),(19,'Eliminatorias Europeas 2010','UEFA',1),(20,'ATP','ATP',9),(21,'SERIE MUNDIAL','SM',4),(22,'Gran Premio Brazil','GPB',10),(23,'UEFA Cup','UEFA',1),(24,'Venezuela Copa Maltin','VZLA MALTIN',1),(25,'College Football','NCAA',5),(26,'La Rinconada','LR',11),(27,'LaRinconada','LR',12),(28,'Chance A','CH.A',13),(29,'Chance B','CHB',13),(30,'A T P','ATP',14),(31,'Serie del Caribe','SC',4),(32,'Copa Libertadores','CLIB',1),(33,'Amistosos Internacionales','FIFA',1),(34,'Clasico Mundial 2009','MUN',4),(35,'Ligero del CBM','LCMB',8),(36,'Copa Confederaciones','CONF',1),(37,'Campeonato Mundial SUB20','SUB20',1),(38,'Liga Dominicana De Beisbol','LIDOM',4),(39,'Liga Mexicana De Beisbol','LMPB',4),(40,'Mundial Sudafrica 2010','M2010',1),(41,'All Stars Game','ALSG',4);
UNLOCK TABLES;
/*!40000 ALTER TABLE `liga` ENABLE KEYS */;

--
-- Table structure for table `logros`
--

DROP TABLE IF EXISTS `logros`;
CREATE TABLE `logros` (
  `favorito` int(11) NOT NULL,
  `hembra` int(11) NOT NULL,
  PRIMARY KEY  (`favorito`,`hembra`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `logros`
--


/*!40000 ALTER TABLE `logros` DISABLE KEYS */;
LOCK TABLES `logros` WRITE;
INSERT INTO `logros` VALUES (-350,230),(-340,230),(-335,225),(-330,220),(-325,220),(-320,220),(-315,220),(-310,220),(-305,210),(-300,210),(-290,210),(-285,210),(-280,210),(-275,210),(-270,210),(-265,200),(-260,200),(-255,200),(-250,200),(-245,195),(-240,190),(-235,190),(-230,190),(-225,190),(-220,190),(-215,185),(-210,180),(-205,175),(-200,170),(-195,170),(-190,170),(-185,165),(-180,160),(-175,155),(-170,150),(-165,145),(-160,140),(-155,135),(-150,130),(-145,125),(-140,120),(-135,115),(-130,110),(-125,105),(-120,100),(-115,-105),(-110,-110),(-105,-115);
UNLOCK TABLES;
/*!40000 ALTER TABLE `logros` ENABLE KEYS */;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id_menu` int(11) NOT NULL,
  `desc_menu` varchar(100) NOT NULL,
  `orden` int(11) NOT NULL,
  `accion` varchar(200) default NULL,
  `imagen` varchar(200) default NULL,
  PRIMARY KEY  (`id_menu`),
  UNIQUE KEY `UQ_menu_1` (`desc_menu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--


/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
LOCK TABLES `menu` WRITE;
INSERT INTO `menu` VALUES (1,'Inicio',5,'inicio.do','house.png'),(2,'Deportes',10,'deportes.do','images.png'),(3,'Juegos',15,'listGame.do','sport_football.png'),(4,'Usuarios',20,'listUser.do','user_suit.png'),(5,'Historico',25,'listPlay.do','database_table.png'),(6,'Apuestas',30,'listGamePlay.do','money.png'),(7,'Cuenta',35,'listUserPlayDetail.do','page_white_add.png'),(8,'Saldos',40,'listUserPlay.do','money_dollar.png'),(9,'Reportes',45,'listReport.do','printer.png'),(10,'Logros',50,'listLogroPorCentro.do','medal_bronze_3.png'),(11,'Parametros',50,'listParametros.do','report_edit.png'),(12,'Ganadores',50,'listPlayPending.do','rosette.png'),(13,'Seguridad',60,'changePassword.do','key.png');
UNLOCK TABLES;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;

--
-- Table structure for table `menu_user`
--

DROP TABLE IF EXISTS `menu_user`;
CREATE TABLE `menu_user` (
  `id_menu` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY  (`id_menu`,`id_usuario`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_menu` (`id_menu`),
  CONSTRAINT `FK_opcion_user_1` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`id_menu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_opcion_user_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu_user`
--


/*!40000 ALTER TABLE `menu_user` DISABLE KEYS */;
LOCK TABLES `menu_user` WRITE;
INSERT INTO `menu_user` VALUES (1,13),(3,13),(13,13);
UNLOCK TABLES;
/*!40000 ALTER TABLE `menu_user` ENABLE KEYS */;

--
-- Table structure for table `parametros`
--

DROP TABLE IF EXISTS `parametros`;
CREATE TABLE `parametros` (
  `nombre` varchar(20) NOT NULL,
  `valor` varchar(250) default NULL,
  PRIMARY KEY  (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parametros`
--


/*!40000 ALTER TABLE `parametros` DISABLE KEYS */;
LOCK TABLES `parametros` WRITE;
INSERT INTO `parametros` VALUES ('AVISO_HOME','Bienvenido al nuevo Betcesc'),('URL_VIDEO_HOME','http://www.pepecoco.es.tl/');
UNLOCK TABLES;
/*!40000 ALTER TABLE `parametros` ENABLE KEYS */;

--
-- Table structure for table `reglas_pago`
--

DROP TABLE IF EXISTS `reglas_pago`;
CREATE TABLE `reglas_pago` (
  `id_reglas_pago` int(11) NOT NULL,
  `rango_ini` double NOT NULL,
  `rango_fin` double NOT NULL,
  `multiplo` int(11) NOT NULL,
  `monto_maximo` double NOT NULL,
  PRIMARY KEY  (`id_reglas_pago`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reglas_pago`
--


/*!40000 ALTER TABLE `reglas_pago` DISABLE KEYS */;
LOCK TABLES `reglas_pago` WRITE;
INSERT INTO `reglas_pago` VALUES (1,0,100000,1000,1000000);
UNLOCK TABLES;
/*!40000 ALTER TABLE `reglas_pago` ENABLE KEYS */;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
CREATE TABLE `rol` (
  `id_rol` int(11) NOT NULL,
  `desc_rol` varchar(50) NOT NULL,
  PRIMARY KEY  (`id_rol`),
  UNIQUE KEY `UQ_rol_1` (`desc_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rol`
--


/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
LOCK TABLES `rol` WRITE;
INSERT INTO `rol` VALUES (1,'Administrador'),(2,'Administrador de Taquilla'),(4,'Jugador'),(3,'Jugador de Taquilla');
UNLOCK TABLES;
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;

--
-- Table structure for table `rol_menu`
--

DROP TABLE IF EXISTS `rol_menu`;
CREATE TABLE `rol_menu` (
  `id_menu` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL,
  PRIMARY KEY  (`id_menu`,`id_rol`),
  KEY `id_rol` (`id_rol`),
  KEY `id_menu` (`id_menu`),
  CONSTRAINT `FK_rol_menu_2` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`id_menu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_rol_menu_3` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rol_menu`
--


/*!40000 ALTER TABLE `rol_menu` DISABLE KEYS */;
LOCK TABLES `rol_menu` WRITE;
INSERT INTO `rol_menu` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(8,1),(9,1),(11,1),(13,1),(1,2),(3,2),(5,2),(9,2),(10,2),(13,2),(1,3),(5,3),(6,3),(9,3),(10,3),(12,3),(13,3),(1,4),(5,4),(6,4),(7,4),(13,4);
UNLOCK TABLES;
/*!40000 ALTER TABLE `rol_menu` ENABLE KEYS */;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
CREATE TABLE `status` (
  `id_status` int(11) NOT NULL,
  `desc_status` varchar(30) NOT NULL,
  PRIMARY KEY  (`id_status`),
  UNIQUE KEY `UQ_status_1` (`desc_status`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `status`
--


/*!40000 ALTER TABLE `status` DISABLE KEYS */;
LOCK TABLES `status` WRITE;
INSERT INTO `status` VALUES (1,'Activo'),(2,'Inactivo'),(3,'temporal');
UNLOCK TABLES;
/*!40000 ALTER TABLE `status` ENABLE KEYS */;

--
-- Table structure for table `status_deporte`
--

DROP TABLE IF EXISTS `status_deporte`;
CREATE TABLE `status_deporte` (
  `id_status_deporte` int(11) NOT NULL,
  `desc_status_deporte` varchar(30) default NULL,
  PRIMARY KEY  (`id_status_deporte`),
  UNIQUE KEY `UQ_status_deporte_1` (`desc_status_deporte`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `status_deporte`
--


/*!40000 ALTER TABLE `status_deporte` DISABLE KEYS */;
LOCK TABLES `status_deporte` WRITE;
INSERT INTO `status_deporte` VALUES (1,'Activo'),(2,'Inactivo');
UNLOCK TABLES;
/*!40000 ALTER TABLE `status_deporte` ENABLE KEYS */;

--
-- Table structure for table `status_juego`
--

DROP TABLE IF EXISTS `status_juego`;
CREATE TABLE `status_juego` (
  `id_status_juego` int(11) NOT NULL,
  `desc_status_juego` varchar(30) default NULL,
  PRIMARY KEY  (`id_status_juego`),
  UNIQUE KEY `UQ_status_juego_1` (`desc_status_juego`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `status_juego`
--


/*!40000 ALTER TABLE `status_juego` DISABLE KEYS */;
LOCK TABLES `status_juego` WRITE;
INSERT INTO `status_juego` VALUES (2,'Abierto'),(1,'Borrador'),(4,'Cerrado'),(3,'Suspendido'),(5,'Totalizado');
UNLOCK TABLES;
/*!40000 ALTER TABLE `status_juego` ENABLE KEYS */;

--
-- Table structure for table `status_jugada`
--

DROP TABLE IF EXISTS `status_jugada`;
CREATE TABLE `status_jugada` (
  `id_status_jugada` int(11) NOT NULL auto_increment,
  `desc_jugada` varchar(30) NOT NULL,
  PRIMARY KEY  (`id_status_jugada`),
  UNIQUE KEY `UQ_status_jugada_1` (`desc_jugada`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `status_jugada`
--


/*!40000 ALTER TABLE `status_jugada` DISABLE KEYS */;
LOCK TABLES `status_jugada` WRITE;
INSERT INTO `status_jugada` VALUES (2,'Eliminada'),(1,'En Juego'),(7,'Gano'),(4,'Pagada'),(3,'Pendiente'),(9,'Perdedor'),(8,'Perdio'),(6,'Suspendida'),(5,'Vencida');
UNLOCK TABLES;
/*!40000 ALTER TABLE `status_jugada` ENABLE KEYS */;

--
-- Table structure for table `tipo_cuenta`
--

DROP TABLE IF EXISTS `tipo_cuenta`;
CREATE TABLE `tipo_cuenta` (
  `id_tipo_cuenta` int(11) NOT NULL,
  `desc_tipo_cuenta` varchar(30) NOT NULL,
  PRIMARY KEY  (`id_tipo_cuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipo_cuenta`
--


/*!40000 ALTER TABLE `tipo_cuenta` DISABLE KEYS */;
LOCK TABLES `tipo_cuenta` WRITE;
INSERT INTO `tipo_cuenta` VALUES (1,'Corriente'),(2,'Ahorro');
UNLOCK TABLES;
/*!40000 ALTER TABLE `tipo_cuenta` ENABLE KEYS */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL auto_increment,
  `cedula` varchar(10) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `usuario` varchar(20) NOT NULL,
  `clave` varchar(50) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `dias_venc_ticket` int(11) NOT NULL,
  `centro_hipico` varchar(100) default NULL,
  `rif` varchar(12) default NULL,
  `telefono` varchar(12) default NULL,
  `celular` varchar(12) default NULL,
  `banco` varchar(50) default NULL,
  `numero_cuenta` varchar(20) default NULL,
  `titular_cuenta` varchar(50) default NULL,
  `logros_alta_baja` int(11) default NULL,
  `logros_calc` int(11) NOT NULL,
  `monto` double default NULL,
  `id_rol` int(11) NOT NULL,
  `id_status` int(11) NOT NULL,
  `id_tipo_cuenta` int(11) NOT NULL,
  `id_supervisor` int(11) NOT NULL,
  `ticket_nota` varchar(1000) default NULL,
  `comision_venta` double NOT NULL,
  `otros_gastos` double NOT NULL,
  `tipo` int(11) NOT NULL,
  `mac_address` varchar(255) default NULL,
  `validar_mac_address` int(11) NOT NULL default '0',
  `logros_min` int(11) NOT NULL,
  PRIMARY KEY  (`id_usuario`),
  UNIQUE KEY `UQ_usuario_correo` (`correo`),
  UNIQUE KEY `IX_usuario_clave` (`usuario`,`clave`),
  KEY `id_supervisor` (`id_supervisor`),
  KEY `id_rol` (`id_rol`),
  KEY `id_status` (`id_status`),
  KEY `id_tipo_cuenta` (`id_tipo_cuenta`),
  CONSTRAINT `FK_usuario_1` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_usuario_2` FOREIGN KEY (`id_status`) REFERENCES `status` (`id_status`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_usuario_3` FOREIGN KEY (`id_tipo_cuenta`) REFERENCES `tipo_cuenta` (`id_tipo_cuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_usuario_supervisor` FOREIGN KEY (`id_supervisor`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--


/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
LOCK TABLES `usuario` WRITE;
INSERT INTO `usuario` VALUES (1,'10871671','11111a','Admin','Admin','fcea920f7412b5da7be0cf42b8c93759','admin@cantv.net',3,'','','123456','789654','banesco','01234567890123456789','Titular',110,8,1000000,1,1,1,1,'***Gracias por su compra***',0,0,0,'',0,0),(2,'10871672','22222a','ataquilla','ataquilla','fcea920f7412b5da7be0cf42b8c93759','bebecito@cantv.net',3,'BETCESC.com','','123456','789654','banesco','01234567890123456789','Titular',0,4,1000000,2,1,1,1,'Revise su ticket antes de retirarse de la taquilla, debe presentar el boleto para cobrar  ***SUERTE***',0,0,0,'',0,0),(3,'10871673','jugtaq','jugtaq','jugtaq','fcea920f7412b5da7be0cf42b8c93759','cggvhbh@cantv.net',3,'','','123456','789654','banesco','01234567890123456789','Titular',110,5,1000000,3,1,1,2,'***Gracias por su compra***',0,0,0,'',0,1),(4,'10871674','jugador','jugador','jugador','fcea920f7412b5da7be0cf42b8c93759','dbgbyh@cantv.net',3,'','','123456','789654','banesco','01234567890123456789','Titular',110,4,1000000,4,1,1,1,'***Gracias por su compra***',0,0,0,'',0,0),(5,'17765239','albert','alberto','sportm1414','fcea920f7412b5da7be0cf42b8c93759','diego_armando710@hotmail.com',3,'SpoortsBook','','04142351504','','','','',0,2,0,2,1,2,1,'Revise su ticket ',0,0,0,'',0,0),(7,'64775985','nico','Nico','nico','fcea920f7412b5da7be0cf42b8c93759','nicleooo100@hotmail.com',0,'','','5364874','04142377237','banesco','425435','nico',0,5,0,4,2,1,1,'',0,0,0,'',0,0),(8,'11558661','dos santos','Junior','esquinazo','fcea920f7412b5da7be0cf42b8c93759','juniorscenteno2@hotmail.com',0,'Tasca Restaurant El Esquinazo','J-00287091-5','02128612014','04127234398','','','',0,8,1000,3,1,2,219,'',0,0,0,'',0,0),(9,'3978471','................','vacio','vacio','fcea920f7412b5da7be0cf42b8c93759','ksdjj@hotmail.com',5,'LW1','','02125501247','04127185743','','','',-120,2,0,2,2,2,1,'    *****Suerte*****',0,0,0,'',0,0),(10,'6037740','mendoza','argenis','moron','fcea920f7412b5da7be0cf42b8c93759','jdjdjd@hotmail.com',0,'','','567890789','34567845678','','','',0,6,1000,3,1,2,2,'',0,0,0,'',0,0),(11,'16922718','suarez','ronnie','carora','25d55ad283aa400af464c76d713c07ad','yiri2@hotmail.com',0,'','','02124253005','04241885982','','','',0,8,2000,3,2,2,2,'',0,0,0,'',0,0),(12,'16922718','vasconia','Eduardo','vasconia','f9f16d97c90d8c6f2cab37bb6d1f1992','vasconia@hotmail.com',0,'','','02123526728','04243429801','','','',0,5,1000,3,1,2,2,'',0,0,0,'',0,0),(13,'65765756','11111b','cesar','kingcesc','16f03bf77753867770a595eeffad3a81','kingcesc@hotmail.com',0,'','','02126319124','04123891361','','','',0,2,0,1,1,2,1,'',0,0,0,'',0,0),(14,'26637763','11111h','juan','juancho','e60408e9a55027070e3caf0550d2b4df','juancho1682@hotmail.com',0,'','','04241589193','04122026524','','','',0,2,0,1,1,2,1,'',0,0,0,'',0,0),(15,'14566377','daniel','daniel','daniel','6ebe76c9fb411be97b3b0d48b791a7c9','oldaniel_14@hotmail.com',0,'','','01235667','04241372802','','','',0,6,1000,3,1,2,37,'',0,0,0,'',0,0),(16,'16087342','jose','Jose','jose','f717c413cebc560ff181002dbc323431','mnbhbhbv@hotmail.com',0,'','','','04122590695','','','',0,6,1000,3,1,2,37,'',0,0,0,'',0,0),(17,'76152376','11111g','Jairo','Jairo','2beac3b10d49338dd9644063e3cb27e6','whbhhbbe@hotmail.com',0,'','','23423','3424','','','',0,2,0,1,1,2,1,'',0,0,0,'',0,0),(18,'23451462','11111f','alberto','alberto','2e92c9ac61d2a6e306056cefad27c86a','dubdkso@hotmail.com',0,'','','1321323213','123123213','','','',0,2,0,1,1,2,1,'',0,0,0,'',0,0),(19,'15675589','osmel','osmel','osmel','25f9e794323b453885f5181f1b624d0b','ertvgvgvgyu@hotmail.com',0,'','','041432455627','01425437833','','','',0,6,1000,3,1,2,37,'',0,0,0,'',0,0),(20,'16431330','perez','jorge','acarigua','dd4b21e9ef71e1291183a46b913ae6f2','yeyepollito@hotmail.com',0,'','','24532432','041228489','','','',0,6,5000,3,1,2,2,'',0,0,0,'',0,0),(21,'15765541','acacias','robin','robin','dd4b21e9ef71e1291183a46b913ae6f2','j0hnm@hotmail.com',0,'','','02126310807','04142659225','','','',0,6,3000,3,2,2,2,'',0,0,0,'',0,0),(22,'14122409','ruiz','alberto','alberto','695d056e0f1601d13d3d9ca89a3ec8a0','elpibe2180@hotmail.com',3,'','J30939050-3','02122856264','04241146077','','','',0,2,0,2,1,2,1,'Es indispensable presentar este ticket para la cancelacion del  premio\r\n        ***** Suerte *****',0,0,0,'',0,0),(23,'17077462','clasico','rene','mrpool','dd4b21e9ef71e1291183a46b913ae6f2','j0hhhbhnm@hotmail.com',0,'','','02126310581','04166331084','','','',0,5,5000,3,1,2,2,'',0,0,0,'',0,0),(24,'13759808','lisnam uchire','Nestor','lisnam','6db2f51227456681a7c91747a854f011','parra361@hotmail.com',0,'','','02815351453','','','','',0,5,1000,3,2,2,22,'',0,0,0,'',0,0),(25,'14123914','diana','dianas','diana','8a6552ac3f802e39a4b4ec5be16ee881','elo_numero11@hotmai.com',0,'','','','','','','',0,5,1000,3,1,2,2,'',0,0,0,'',0,0),(27,'54646546','taketicket cementerio','Carlos','taketicket','96e79218965eb72c92a549dd5a330112','jhgbgkmkmkh@hotmail.com',0,'','','02126323116','04169220526','','','',0,5,1000,3,2,2,2,'',0,0,0,'',0,0),(28,'56499866','helton','jairo','helton','c33367701511b4f6020ec61ded352059','bhnkmkmkmkub@hotmail.com',0,'','','765755666','75676575','','','',0,5,1000,3,2,2,22,'',0,0,0,'',0,0),(29,'26553451','osmel2','osmel','osmel2','25f9e794323b453885f5181f1b624d0b','hgfkmkmkmkds@hotmail.com',0,'','','152773889','827748873','','','',0,6,1000,3,1,2,37,'',0,0,0,'',0,0),(30,'13160717','dos santos','winer','winners','66f3e49acea559e7d2217ea1fd36845b','angibel827@hotmail.com',0,'Inversiones WinnerÂ´s 101010','J-31423213-4','02125730310','04242350762','','','',0,8,1000,3,1,2,219,'',0,0,0,'',0,0),(31,'13452243','22222b','Yiri','Gaspar','25f9e794323b453885f5181f1b624d0b','sdjnjsdv@hotmail.com',5,'BetCESC','','04133256333','01442443663','','','',0,2,0,2,1,2,1,'Registrate con nosotros en BETCESC.COM y disfruta todos los juegos de NBA  en VIVO SUERTE-',0,0,0,'',0,0),(32,'13425337','betgaspar','Gaspar','betgaspar','e6097a33cec64a70995149b870652bf5','kjfrno@hotmail.com',0,'','','6432864386','8432983298','','','',0,6,1000,3,1,2,31,'',0,0,0,'',0,0),(33,'64367655','cay','mara','paty','c33367701511b4f6020ec61ded352059','edmkmd@hotmail.com',0,'','','75474745354','35453455553','','','',0,5,1000,3,1,2,2,'',0,0,0,'',0,0),(34,'12345567','maiquetia','Adolfo','fito0962','fcea920f7412b5da7be0cf42b8c93759','fito_0962@hotmail.com',5,'Del Mar Racing SportsBooks','','274764774763','2342432432','','','',-120,2,0,2,2,2,1,'Revise su jugada, presentar este boleto para cobrar su premio ***SUERTE****',0,0,0,'',0,0),(36,'62776362','inv02','jhoni','inv02','b821ff07ae538a16f5b34388298dc7bc','kikka@hotmail.com',0,'','','02128607620','04123640560','','','',0,5,1000,3,1,2,45,'',0,0,0,'',0,0),(37,'12365498','22222i','odlaniel','odlaniel14','fa155286f317e0a68c4fae6d3745cd7d','ogvgvgvgdl@hotmail.com',3,'\"La O de Oro\"','','','','','','',0,2,0,2,1,2,1,'***REVISE SU TICKET***\r\n********SUERTE*******',0,0,0,'',0,0),(38,'3456255','delmar','delmar','delmar','e10adc3949ba59abbe56e057f20f883e','anairam388@hotmail.com',0,'','','02125766515','04241308549','','','',0,5,1000,3,1,2,34,'',0,0,0,'',0,0),(39,'12456890','sportm','carlos','sportm14','93d9b95d123a739631e3f724e9d9e137','pppdrepp@hotmail.com',0,'SportM','','0212354266','123123213','','','',0,6,1000,3,1,2,5,'',0,0,0,'',0,0),(40,'12312312','nahomydad','daniel nahomy','nahomy','827ccb0eea8a706c4c34a16891f84e7b','yyyyyyy@hotmail.com',0,'','','134123213213','345435435','','','',0,5,1000,3,1,2,37,'',0,0,0,'',0,0),(41,'15488627','11111c','Nicola','nicleo100','d7ac59ce6272b64051927904eeb0bfb7','nicleo@cantv.net',0,'','','','','','','',0,2,0,1,2,2,1,'',0,0,0,'',0,0),(43,'14955578','estrella Passos','Jose','estrella05','19e98c92a7b98f74cafbcaafd98cce56','joseodspassos@hotmail.com',0,'','','','','','','',0,5,0,4,2,2,1,'',0,0,0,'',0,0),(44,'42553663','migueljr','Miguel','migueljr','25f9e794323b453885f5181f1b624d0b','miguel_reyes6@hotmail.com',0,'','','02125536654','04142472462','','','',0,5,0,4,2,2,1,'',0,0,0,'',0,0),(45,'9095652','taquilla','..........','taquilla','25f9e794323b453885f5181f1b624d0b','fag@yahoo.com',3,'SportsBook','','02124615581','04149100285','','','',0,2,0,2,1,2,1,'Revise su ticket antes de retirase de la taquilla, debe presentar el boleto para cobrar,  ***SUERTE***',0,0,0,'',0,0),(46,'72383476','espia','carlos','espia','e10adc3949ba59abbe56e057f20f883e','hdhdhd@hotmail.com',0,'','','02127654231','04125833035','','','',0,6,1000,3,2,2,37,'',0,0,0,'',0,0),(66,'11111111','buenavista san juan','librasygramos','buenavista','7da9bd418d411beac9506acdf25ab324','atatata@hotmail.com',0,'','','','','','','',0,5,2000,3,1,2,2,'',0,0,0,'',0,0),(83,'11111111','inv04','Joan','inv04','e10adc3949ba59abbe56e057f20f883e','aespÃ±Ã±@hotmail.com',0,'','','','','','','',0,5,1000,3,1,2,45,'',0,0,0,'',0,0),(93,'11111111','orlando','orlando','orlando','670b14728ad9902aecba32e22fa4f6bd','acaca@hotmail.com',0,'','','02123621187','04164080459','','','',0,5,1000,3,1,2,363,'',0,0,0,'',0,0),(97,'22222222','cipriano','cipriano','cipriano','beba95cd288d0c94e5ebe13ad5dfba10','dudamel@hotmail.com',0,'','','','','','','',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(98,'16114237','muÃ±eco','alvaro','muÃ±eco','e10adc3949ba59abbe56e057f20f883e','elpotro181626@hotmail.com',0,'','','02123456788','04168038855','','','',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(99,'13230857','prueba2','Henry','prueba2','25f9e794323b453885f5181f1b624d0b','henrymorenog@hotmail.com',0,'','','8606308','','','','',0,5,3000,3,1,2,313,'',0,0,0,'',0,0),(100,'81993260','11111d','Adrian','gringo','ac6b09365b6d0f1155119c67b496a66f','adrian_140@hotmail.com',0,'','','','04142553151','','','',0,2,0,1,2,2,1,'',0,0,0,'',0,0),(101,'3546576','josemoros','jose','josemoros','745624167e3a2ec42a6123475b125f0a','jhgjhgjhg@hotmail.com',0,'','','02128587321','04123915947','','','',0,5,3000,3,2,2,2,'',0,0,0,'',0,0),(102,'15910953','jjgmirabal Garcia','Javier','jjgmirabal','b0e1df1f22fab785a8757c8b14e44bbc','titogarciam54@hotmail.com',0,'','','02126314595','0412346633','','','',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(103,'14157351','antivero','Angel','antivero','4b2cf380ad4f43cf550605f819a21798','angelcanache@hotmail.com',0,'','','02123455334','04149052251','','','',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(104,'16896855','Osorio','Oscar','Osorio','24e70b97d6a25069628493aeaf4abbc5','yeufhjv@hotmail.com',0,'','','023416777333','04143263733','','','',0,5,0,4,2,2,1,'',0,0,0,'',0,0),(105,'14576557','Rivero','Pedro','riverop','7edd15e71f96e9774281b27e1ae4993b','udhu@hotmail.com',0,'','','0212774774','04123118418','','','',0,5,0,4,2,2,1,'',0,0,0,'',0,0),(106,'3456278','tercio1','Yhonny','tercio1','f25a4a69b04aff15e41036c26b7ff708','ytrppp@hotmail.com',0,'','','0212344366','04121678603','','','',0,4,0,4,2,2,1,'',0,0,0,'',0,0),(107,'13888675','edgarh','Edgar','edgarh','124af9885569763c8dc012f39905098c','condorrasta@hotmail.com',0,'','','','04127189280','','','',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(108,'13425662','darwin','Darwin','darwin','25f9e794323b453885f5181f1b624d0b','ttttrrr@hotmail.com',0,'','','02123435563','04123536633','','','',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(109,'6323392','yan','Yohnny','yan','6d5473ab9ffb729867047def0bc88378','jjbbhhh@hotmail.com',0,'','','02126322853','','','','',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(110,'12345678','wilson','Wilson','wilson','abd7372bba55577590736ef6cb3533c6','jjskmkmak@hotmail.com',0,'','','2565895','','','','',0,5,0,4,2,2,1,'',0,0,0,'',0,0),(111,'5263652','suntai','Miguel','suntai','6c84cbd30cf9350a990bad2bcc1bec5f','lltgvgvg@hotmail.com',0,'','','6939065','','','','',0,5,1000,3,2,2,2,'',0,0,0,'',0,0),(112,'58431256','ruiz','Miguel','madrid','25d55ad283aa400af464c76d713c07ad','llbhbbhhd@hotmail.com',0,'','','04125686717','04125686717','','','',0,8,30000,3,1,2,22,'',0,0,0,'',0,0),(116,'12345678','oliver naveda','Oliver','oliver2008','9b8806c499652eb86c2b4867eefecc8d','jvggvgvgvgvj@hotmail.com',0,'','','55566789','','','','',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(117,'14139126','dsanchez','deibys','dsanchez','8990c492a1c08414a5d93b6e73dbbeb2','deibyssanchez@gmail.com',0,'','','04241442379','','','','',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(118,'6323392','venus','romano','venus','95b2dd04d0759ed65a7c55c55252c198','pillapon@hotmail.com',0,'','','02126322853','04140345267','','','',0,6,1000,3,1,2,211,'',0,0,0,'',0,0),(119,'5469874','europa','Tomas','europa','e10adc3949ba59abbe56e057f20f883e','potgygt@hotmail.com',0,'','','02124916514','123456789','','','',0,5,1000,3,1,2,34,'',0,0,0,'',0,0),(120,'4521589','perez','filipo','anatole','dd4b21e9ef71e1291183a46b913ae6f2','rtgvgvg@hotmail.com',0,'','','456789123','','','','',0,6,3000,3,1,2,2,'',0,0,0,'',0,0),(121,'16083250','edgar0428','edgar','edgar0428','1403bfef98cb9a19a98d30830ba83657','edgarsteward@hotmail.com',0,'','','','04143899839','','','',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(123,'7925718','abc112244','nestor','abc112244','955f8ebd6c18b6e13d7bdc3b66b3153c','nodol112244@yahoo.es',0,'','','','','','','',0,4,0,4,2,2,1,'',0,0,0,'',0,0),(124,'15965189','hcito','hernan','hcito','97feb118da24f0b3a50a2f83ce583f8e','hernanantonio35@hotmail.com',0,'','','04128512645','','','','',0,4,0,4,2,2,1,'',0,0,0,'',0,0),(125,'12365479','mork22','kevork','mork22','fcea920f7412b5da7be0cf42b8c93759','gvgvgvgj@hotmail.com',0,'','','','','','','',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(126,'98765432','jmartinez','Junior','jmartinez','ba6b05673da2ed568fc82279ac429e2e','daddy-junior@hotmail.com',0,'','','','04169024436','','','',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(127,'18023209','peluche','Peluche','peluche','c9027dd42fc6ad4bd871934637ba3e65','pololo@hotmail.com',0,'','','','04242348207','','','',0,4,0,4,2,2,1,'',0,0,0,'',0,0),(128,'13192273','ramirez','raimundo','raysunny','2b5164185d757ab4ce08f2410e98caed','raimundo@sunnyinternacional.net',0,'','','04149719161','','','','',0,4,0,4,1,2,1,'',0,0,0,'',0,0),(132,'17868518','gochin','jorge','gochin','6596622cf8cb53bd637ff8aa40036436','jorge00_2004@hotmail.com',0,'','','','04128205552','venezuela','01020525520000087887','maria abreu',0,4,0,4,1,1,1,'',0,0,0,'',0,0),(134,'15573888','migsonn','miguel','migsonn','920794cb543ebc01421af0126d5f5ba6','migsonn@hotmail.com',0,'','','','04140123896','fondo comun','01510024936012063704','miguel sonnessa',0,5,0,4,2,2,1,'',0,0,0,'',0,0),(135,'10343716','aosorio','ANGEL NOE','aosorio','a5bc45b7dba9c61a640ce011c1df74e7','aosorio31@hotmail.com',0,'','','2446355998','4144599214','MERCANTIL','1051558336','ANGEL NOE OSORIO ALAMO',0,5,0,4,1,1,1,'',0,0,0,'',0,0),(142,'18589607','messi','elias','messi','8b123b7a7cf86f5aa9424d1f379384d8','elo_numero11@hotmail.com',0,'','','04242456444','04242456444','banesco','','',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(143,'10428551','mago23','albert','mago23','44d0fa921884362d3420d6b2fd477aff','mago23@cantv.net',0,'','','','','venezuela','','',0,4,0,4,2,2,1,'',0,0,0,'',0,0),(151,'17287416','gabo19','jhonny','Gabo1908','99d323c317a67d0133c668c525389800','perezgabo@hotmail.com',0,'','','02126902165','04143979852','banco exterior','01150038284000659616','jhonny perez',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(156,'34534534','22222f','genaro','maiquetia','1380cb1de2140b7d45a574a616caedb5','mcgwire@hotmail.com',3,'Maiquetia SportsBook','','0212637378','04140246035','','','',0,2,0,2,1,2,1,'www.BETCESC.com\r\nPresenta tu ticket \r\npara cobra el premio',0,0,0,'',0,0),(159,'13452656','maiquetia2','jorge','maiquetia2','25f9e794323b453885f5181f1b624d0b','yytytyyt@hotmail.com',0,'','','02314233344','0298846647','','','',0,8,5000,3,1,2,156,'',0,0,0,'',0,0),(161,'15589607','............','...........','rene533','c0b793c63f7ed98a24670d86b6868859','ghuhuhu@hotmail.com',5,'','','02128621680','04165340043','','','',0,2,0,2,1,2,1,'Revice su ticket antes de retirarse de la taquilla, el boleto es indispensable para cobrar  ***SUERTE***',0,0,0,'',0,0),(162,'15151454','nj2000','caricuao','nj2000','25fa6d621c43c0c3698dfb95ef7ec9c1','ygygy@hotmail.com',0,'','','02125562541','04145655896','','','',0,5,1000,3,1,2,2,'',0,0,0,'',0,0),(163,'84848456','pazetti','alemania','alemania','6ebe76c9fb411be97b3b0d48b791a7c9','tftftt@hotmail.com',0,'','','02125653265','04145856595','','','',0,8,1000,3,2,2,2,'',0,0,0,'',0,0),(164,'18325062','raysunny2','raimundo','raysunny2','7d1987a856ba19832b4ddb48315c2125','raimundoramirez@sunnyinternacional.net',0,'','','','','','','',0,4,0,4,2,2,1,'',0,0,0,'',0,0),(165,'45678987','11111e','Usuario','betcesc','25d55ad283aa400af464c76d713c07ad','bjfgs@hotmail.com',0,'','','23454321','24576435','','','',0,2,0,1,1,2,1,'',0,0,0,'',0,0),(169,'10525229','dos santos','helton','elmendero','e86cc4e08b54c9227f41ca6c72e7de16','tterttert@hotmail.com',0,'Ag. de Lot. Ele Mendero del Milenio ','','02128737733','04145344567','','','',0,8,1000,3,1,2,219,'',0,0,0,'',0,0),(171,'16887498','MARTINEZ','FRANCISCO','MRPANCHI','0f67503882e2d541b2431ec930aaa897','mr.panchi@hotmail.com',0,'','','02123229936','04241970300','BANESCO','01340035170352152897','FRANCISCO MARTINEZ',0,4,0,4,2,2,1,'',0,0,0,'',0,0),(177,'23423423','osmel26','osmele','osmel26','1086d84722b3a28125ff7678e4ef5918','owowowowowow@hotmail.com',0,'','','021234453487','04142366789','','','',0,6,1000,3,1,2,37,'',0,0,0,'',0,0),(179,'17385804','jonas9224','jonas','jonas9224','bcbdc60fe288c39e28b567fa56e82987','jonas9224@hotmail.com',0,'','','02128600635','04123850052','fondo comun','01510006186004471083','jonas alcala',0,4,0,4,1,2,1,'',0,0,0,'',0,0),(181,'6303171','chaza','HECTOR','chaza','b8e4d59ba7263d489b224a4d9759e39f','chaza.l@hotmail.com',0,'','','02126314359','04129390859','MERCANTIL','01050191190191107506','SI',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(183,'98765342','lrujano','Luis','lrujano','ded0b0351b2efd66149ddd75948f6acd','lrujano@hotmail.com',0,'','','4323456','0414876543','','','',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(184,'7867662','bonilla','bonilla','bonilla','50a202e60b2f9f6451ff0ed5326da05c','joffffdo@hotmail.com',3,'TEAM SPORTS','00000000000','02123456378','04143289776','','','',-110,2,0,2,2,2,1,'Revise Su Ticket\r\n    \"SUERTE\"',0,0,0,'',0,0),(185,'32544256','jesika','jesika','jesika','486c1eadad24bd41298df3192db5f01d','terrados_43@hotmail.com',0,'Gladys Sports','','02124998765','04167265309','','','',0,8,1000,3,1,2,5,'',0,0,0,'',0,0),(186,'6543221','inv01','pedro','inv01','ca46fb61b5414a21a4434f4e03a0155f','tnerba@hotmail.com',0,'','','02123456370','04142349851','','','',0,5,1000,3,1,2,45,'',0,0,0,'',0,0),(187,'26763130','la nnn','jhonaismar','la nnn','8b4cf0258846b23e0a8272bee22c38dd','jhoniamarbastidas@hotmail.com',0,'','','04141303436','2548962','mercantir','182003225990114582','mitranf',0,2,0,4,1,1,1,'',0,0,0,'',0,0),(188,'4888194','elprimo','Wilfredo','elprimo','6212b15d046c23589a33e9aee52ed1a1','robramos_25974@hotmail.com',0,'','','02124331979','04242327964','','','',0,5,1000,3,1,2,189,'',0,0,0,'',0,0),(189,'8225788','..........','...........','Elias','25f9e794323b453885f5181f1b624d0b','german@hotmail.com',3,'\"Billares Diana\"','','04143365878','','','','',0,2,0,2,1,2,1,'Revise su ticket antes de retirarse de la taquilla, debe presentar el boleto para cobrar, www.Betcesc.com ***SUERTE***',0,0,0,'',0,1),(190,'13487055','alexdom','Alexander','ejemplo','25f9e794323b453885f5181f1b624d0b','alexanderdominguez143@hotmail.com',0,'','','02125085756','04141065570','banesco','01340335003353025340','Alexander Dominguez',0,5,0,4,1,1,1,'',0,0,0,'',0,0),(191,'56456546','acacias','juan','churuata','735f47a869fdd13ebbe2a375ac8093d2','gdgdgd@hotmail.com',0,'','','02123453678','04142070923','','','',0,5,3000,3,2,2,2,'',0,0,0,'',0,0),(195,'48881942','elprimo2','wilfredo','elprimo2','6212b15d046c23589a33e9aee52ed1a1','primo@hotmail.com',0,'','','02124331979','','','','',0,5,1000,3,1,2,189,'',0,0,0,'',0,0),(196,'10786474','Merola','Gianni','meromari','3de45f4fcc805d03d3cd5dfdb2c12d4a','merola626@hotmail.com',0,'','','02122640255','','','','',0,5,1000,3,2,2,2,'',0,0,0,'',0,0),(197,'12738444','pcastillo','Pedro Rogelio','pcastillo','0496946e7208884b3cfebeca7868a50d','pcastillo3006@hotmail.com',0,'','','02126824134','04163051804','Mercantil','01050051610132267381','Pedro Rogelio Castillo Rojas',0,4,0,4,1,2,1,'',0,0,0,'',0,0),(200,'65465465','teques','jovani','teques','25f9e794323b453885f5181f1b624d0b','uhuhuh@hotmail.com',0,'','','02123325633','04146596523','','','',0,5,1000,3,2,2,2,'',0,0,0,'',0,0),(207,'18325062','rarara','raimundo','rarara','258d713054129a4abaacacee257476de','rarara@rarara.com',0,'','','','','','','',0,4,0,4,2,2,1,'',0,0,0,'',0,0),(209,'23456789','yerson','yerson','yerson','885c566e5a2c9b877d9c3c3e61013867','hdfjfue@hotmail.com',0,'','','123456789','23456789','','','',0,4,0,4,1,2,1,'',0,0,0,'',0,0),(211,'12345635','22222g','administrador','admin10','25f9e794323b453885f5181f1b624d0b','tetyey2q@hotmail.com',3,'Tickets De Prueba','','02123455267','04243415809','','','',0,2,0,2,1,2,1,'',0,0,0,'',0,0),(214,'45353663','..........','..........','brasil','25f9e794323b453885f5181f1b624d0b','conejo166@hotmail.com',0,'Prueba Sports','','02126319966','04167270948','','','',0,5,1000,3,1,2,257,'',0,0,0,'',0,1),(215,'13717357','duran','darwin','djduran79','e242c8d4fad8890820d68b3b5814bfbb','djduran79@hotmail.com',0,'','','02128888220','04123997822','mercantil','7034024855','darwin duran',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(216,'10866064','ayuda','ayuda','ayuda','25f9e794323b453885f5181f1b624d0b','gvieiraber@hotmail.com',0,'','','02126814687','04143306892','','','',0,6,5000,3,1,2,306,'',0,0,0,'',0,0),(217,'25654123','recreo','Jose','recreo','8a546590f9b1a4dc90c93c5e8e211da6','jdhdghd@hotmail.com',0,'','','0212','04143263781','','','',0,5,1000,3,1,2,22,'',0,0,0,'',0,0),(218,'65757455','elbar','daniel','elbar','25a672979d8ca0feeda7c1ef29c44545','sfgdhjkds@hotmail.com',0,'','','84756393','76543213456','','','',0,6,1000,3,1,2,37,'',0,0,0,'',0,0),(219,'32567433','Winers','Winers','gwinners','e5b93d656aaab417fcc5794ddd332f0e','gvr@hotmail.com',3,'Grupo Winers','','8765434567','87654456','','','',0,2,0,2,2,2,1,'Revise su ticket ',0,0,0,'',0,0),(220,'87654335','iguazu2','Gustavo','iguazu2','d0776b5d1794af7182258fdf682ae6bb','jgkfkl@hotmail.com',0,'','','8533456767','4567733345','','','',0,6,5000,3,1,2,219,'',0,0,0,'',0,0),(222,'16243256','11111i','hector','cindy','25f9e794323b453885f5181f1b624d0b','mr.panwchi@hotmal.com',0,'','','02124357689','04142349870','','','',0,2,0,1,2,2,1,'',0,0,0,'',0,0),(224,'16431330','betyeye2','Dayirlen','betyeye2','1237d0292ae5c2961f7c37af94f4707e','dayirlen@yahoo.com',0,'','','02125774923','04122848916','banesco','01340796717963010185','Dayirlen Sosa',0,5,0,4,2,1,1,'',0,0,0,'',0,0),(225,'11406164','ravell','Kelly','ravell','4bf50b715ebc63697af83dadc1f2ddb2','kelly_keps_21@hotmail.com',0,'','','02126711698','04126011225','','','',0,5,1000,3,2,2,2,'',0,0,0,'',0,0),(227,'10802079','wilcary ','dierisis','dierisis','25d55ad283aa400af464c76d713c07ad','gfhfj@hotmail.com',0,'','','02128732002','01422699441','','','',0,5,1000,3,1,2,257,'',0,0,0,'',0,0),(229,'34567833','.........','.............','aqui','25f9e794323b453885f5181f1b624d0b','sabanagrande@hotmail.com',0,'','','02123453782','04243567333','','','',0,5,1000,3,1,2,2,'',0,0,0,'',0,0),(230,'15267728','dos santos','luis','poolga','e8394345e8cf9a11a8316fc35dd4f19a','chacao@hotmail.com',0,'Entretenimientos Pool G.A., ','J-31044246-0','02124357289','04265242789','','','',0,8,1000,3,1,2,219,'',0,0,0,'',0,0),(231,'24208509','laesquina','yeye','laestrella','a2b7c2ae552eb925c7c2ba79a1b0c9a2','ejac1977@hotmail.com',0,'laestrella','','02128646504','04129787092','','','',0,6,10000,3,1,2,2,'',0,0,0,'',0,0),(232,'4512456','hermoz','danielo','wiston','57c87819289f821000c68c55dd5a0e2c','ftred@hotmail.com',0,'','','02123456797','04243457321','','','',0,6,1000,3,1,2,37,'',0,0,0,'',0,0),(235,'17082046','toni5310','antonio','toni5310','f00233bf843d6704d3b4077717559545','castiglioni.antonio@hotmail.com',0,'','','04242505880','04120121107','banesco','01340339223393171958','antonio castiglioni',0,4,0,4,1,1,1,'',0,0,0,'',0,0),(237,'17755416','dos santos','Suerte','suerte77','2b8c3291c8e86746507f209399ca80ce','elbambino321@hotmail.com',0,'Agencia de LoterÃ­as Mucha Suerte 77 ','','02126321511','04122023194','','','',0,8,1000,3,1,2,219,'',0,0,0,'',0,0),(239,'9959361','trattoria chacao','giacinto','trattoria','6d071901727aec1ba6d8e2497ef5b709','giancinton@hotmail.com',0,'','','02122679248','04143029081','','','',0,5,3001,3,1,2,22,'',0,0,0,'',0,0),(240,'11075770','MARQUEZ','ELIO','01AD','6c389999ca7a250aacd523400e6dcbfd','ELIOMARQUEZDIAZ@HOTMAIL.COM',0,'','','04141718068','04141718068','BANESCO','1111111','ELIO MARQUEZ',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(241,'10548944','zidel carrizal','Sugey','zidel','5c9d6141c1db600d34fba09131e40d95','gotopo66@hotmail.com',0,'','','02126324209','04142168344','','','',0,6,2000,3,1,2,257,'',0,0,0,'',0,0),(242,'10548944','latorre2','latorre2','latorre2','91183786e5c5bbfb2983823b8d02bd92','gotopo66@hotmeit.com',0,'','','6324209','04142768344','','','',0,5,0,4,2,2,1,'',0,0,0,'',0,0),(243,'15148737','joseph','Joseph','joseph','60ef237c78d693ada7b4237bf576a0c9','josephbakhos@hotmail.com',0,'','','','04142584627','Mercantil','01050103237103025932','Joseph Bakhos',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(253,'10784138','vijuyeve','julio','vijuyeve','5c3e1ec33269ac29356ece76bd2fe98d','vijuyeve@hotmail.com',0,'','','','04141501314','banesco','01340541785411057757','julio carrasco',0,4,0,4,1,1,1,'',0,0,0,'',0,0),(256,'13993518','mandalay','Andres','mandalay','256e5ea5bcf733f8a23694878a879911','andreshenriquez77@hotmail.com',0,'','','13993518','13993518','Banesco','01340041590412083105','Andres Henriquez',0,4,0,4,1,2,1,'',0,0,0,'',0,0),(257,'45454545','goncalves','hector','hector','25f9e794323b453885f5181f1b624d0b','losltosonline@hotmail.com',5,'','','02124154436','04142475046','','','',-120,2,0,2,1,2,1,'Revise su Ticket antes de retirarse de la taquilla. Suerte.-',0,0,0,'',0,0),(258,'74185296','22222r','hhhhhh','lavega','25f9e794323b453885f5181f1b624d0b','joe@hotmail.com',5,'LA VEGA','','321466987','321456987','','','',-110,2,0,2,2,2,1,'REVISE SUTICKET ANTES DE RETIRARSE DE LA TAQUILLA SUERTE..',0,0,0,'',0,0),(259,'85231456','22222q','solo','solo','935973a439831cdb519a68dc852f6b26','ebddsffer@hotmail.com',5,'SPECIAL BOOK','J-5252534-5','789654123','321456987','','','',-120,2,0,2,2,2,1,'REVISE SU TICKET ANTES DE RETIRARSE DE LA TAQUILLA',0,0,0,'',0,0),(260,'85231467','22222s','jairo pto ordaz','JAIRO12','1f5da7d22e8e1692c36ccae9cbd81aa4','mmmm@hotmail.com',3,'\"JAIRO\"','','52231478','233485621','','','',0,2,0,2,1,2,1,'\"REVISE SU TICKET ANTES DE RETIRARSE DE LA TAQUILLA\"\r\nDEBE PRESENTAR ESTE BOLETO PARA COBRAR, SUERTE...',0,0,0,'',0,0),(261,'65478932','piscitello','franco','piscitello','c33367701511b4f6020ec61ded352059','amdddd@hotmail.com',0,'','','32145879','325466555','','','',0,6,2000,3,1,2,257,'',0,0,0,'',0,0),(262,'55448793','losaltos','yusmary','losaltos','2e10fa4715774f45c1a8839e852c445a','dle@hotmail.com',0,'','','02123333331','04242135033','','','',0,6,2000,3,1,2,257,'',0,0,0,'',0,0),(263,'85521475','numero1','numero1','numero1','6cf82ee1020caef069e753c67a97a70d','barbarail@hotmail.com',0,'','','65654789','222245623','','','',0,6,2000,3,1,2,257,'',0,0,0,'',0,0),(264,'45522121','enero','galpon','galpon','25f9e794323b453885f5181f1b624d0b','l1@hotmail.com',0,'Galpon Sportsbook','','0212333333','04269044501','','','',0,6,2000,3,1,2,37,'',0,0,0,'',0,0),(265,'6633147','chispita','chispita','chispita','e10adc3949ba59abbe56e057f20f883e','chis@hotmail.com',0,'','','5542322','62125454','','','',0,5,1000,3,1,2,258,'',0,0,0,'',0,0),(266,'54455455','rios','rios','riosrios','e10adc3949ba59abbe56e057f20f883e','rios@hotmail.com',0,'','','45454545','45454545','','','',0,5,1000,3,1,2,258,'',0,0,0,'',0,0),(267,'64645454','nilkita','nilkita','nilkita','e10adc3949ba59abbe56e057f20f883e','nilki@hotmail.com',0,'','','466565656','56565656656','','','',0,5,1000,3,1,2,258,'',0,0,0,'',0,0),(268,'4454456','lapo','lapo','lapropia','e10adc3949ba59abbe56e057f20f883e','lapo@hotmail.com',0,'','','774165663','565656656','','','',0,5,1000,3,1,2,258,'',0,0,0,'',0,0),(269,'45456566','goleador1','maribel','goleador1','133c73b9e55f1ec613c99f10b7cc1a54','xxxxxxxx@hotmail.com',0,'','','444545454','454454545','','','',0,6,2000,3,1,2,257,'',0,0,0,'',0,0),(270,'44656565','ag10000 lacasona','ag10000','prueba','25f9e794323b453885f5181f1b624d0b','yyyyyy@hotmail.com',0,'','','4545656565','565655688','','','',0,6,2000,3,1,2,2,'',0,0,0,'',0,0),(271,'45454544','zzzz','mibombon','zzzz','c33367701511b4f6020ec61ded352059','zdzdsr@hotmail.com',0,'','','113232323','232323233','','','',0,5,1000,3,1,2,258,'',0,0,0,'',0,0),(272,'45445645','casimil','casimil','casimil','61eb0dd56791dddff0b00287e897fb43','gjfgfghjghj@hotmail.com',0,'','','445454','7545445','','','',0,6,2000,3,1,2,257,'',0,0,0,'',0,0),(273,'65655656','elplacer','plapla','elplacer','e10adc3949ba59abbe56e057f20f883e','mmiju@hotmail.com',0,'','','4545454','5454455','','','',0,5,1000,3,1,2,260,'',0,0,0,'',0,0),(274,'45565564','montebello','montebello','montebello','e10adc3949ba59abbe56e057f20f883e','mmkdhtyhy@hotmail.com',0,'','','15665656','564565656','','','',0,5,1000,3,1,2,260,'',0,0,0,'',0,0),(276,'44646464','goleador','maribel','goleador','7d0710824ff191f6a0086a7e3891641e','jkkjkj@hotmail.com',0,'','','8778985678','65645224','','','',0,6,2000,3,1,2,257,'',0,0,0,'',0,0),(278,'33366699','22222o','juan carlos','admgiummo','e10adc3949ba59abbe56e057f20f883e','admgaby@hotmail.com',5,'giummo','344478952','225588777','44455555','','','',-110,2,0,2,2,2,1,'Revise su ticket antes de retirarse. Vence a los cinco dias. SUERTE',0,0,0,'',0,0),(279,'22332212','giummo','JUAN','GIUMMO','dc1b2932b05d22e17387496e4eb2c939','GIUMMOJUAN@HOTMAIL.COM',0,'','','04143045266','04142561754','','','',0,5,1000,3,1,2,2,'',0,0,0,'',0,0),(280,'12358475','22222u','Marcos','margarita2','25f9e794323b453885f5181f1b624d0b','hhysgte@hotmail.com',5,'Margarita Betcesc','','02125688995','04142589761','','','',0,2,0,2,1,2,1,'Revise su ticket antes de retirarse de la taquilla.  \r\n   .......suerte......',0,0,0,'',0,0),(281,'15874458','mingo','maria','mingo','f05f358d7c6a0605795fc42bc54ca32a','trampi_c313@yahoo.com',0,'','','02952639406','04169962888','','','',0,5,1000,3,1,2,282,'',0,0,0,'',0,0),(282,'48745754','22222t','Cesar','mirtha','01eca7dbe92e0ef89f635fe1f6bb5b3a','jfghg@hotmail.com',5,'Margarita Betcesc','0000000000','34567864','233487654','','','',-120,2,0,2,1,2,1,'Revise su ticket antes de retirarse de la taquilla...SUERTE...',0,0,0,'',0,0),(283,'15487785','lagunita','raiza','lagunita','0a1f41a605ac89a7c78a8a66fccf7099','rahyzak@hotmail.com',0,'','','02956116156','04147924811','','','',0,5,1000,3,1,2,280,'',0,0,0,'',0,0),(286,'14562377','11111j','chispita','chispita','1d9dabb6dcda71735c6b9c54051f0911','fghfdhfg@hotmail.com',0,'','','444444444','54546576','intermundial','55555555555555555555','sin nombre',0,2,0,1,2,1,1,'',0,0,0,'',0,0),(288,'15675281','4Marcano','katty','charallave','25d55ad283aa400af464c76d713c07ad','kfkfk@hotmail.com',0,'D MODAS NIKE','J297450742','02123456543','04248800083','','','',0,8,30000,3,1,2,22,'',0,0,0,'',0,0),(290,'12557959','otrabanda','lino','otrabanda','df09808d31a62cab767d91b6a1ae5ec1','cgts@hotmai.com',0,'','','02125488796','04248492257','','','',0,5,1000,3,2,2,280,'',0,0,0,'',0,0),(291,'84202089','cano','brasil','cananay2','25f9e794323b453885f5181f1b624d0b','hddhd@hotmail.com',0,'','','02952422032','6855958','','','',0,5,1000,3,1,2,257,'',0,0,0,'',0,0),(292,'52346262','eltriple','dayana','eltriple','c71c2f775c404551dc715d4907eabb5c','tetet@hotmail.com',0,'','','02952636305','04144588782','','','',0,5,1000,3,1,2,282,'',0,0,0,'',0,0),(293,'15099823','jrivero','Johan Alexander','jrivero','73b197105b5366d300bcab1aba35fb9b','rjohan3001@hotmail.com',0,'','','02126628567','04122127403','Banco Mercantil','01050102040192098098','Johan Rivero',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(294,'13273135','lagranmina','yeye','yeye','1bbd886460827015e5d605ed44252251','sssss@hotmail.com',0,'W1','','04149951534','02952534619','','','',0,6,10000,3,1,2,2,'',0,0,0,'',0,0),(295,'21322072','elparaiso','Orlemys','elparaiso','79a402185248690bdee40163a140fde4','kgjghrfbn@hotmail.com',0,'','','02952530260','04264870640','','','',0,5,1000,3,1,2,280,'',0,0,0,'',0,0),(296,'12548879','elturquitoarcano','robert','elturquito','bda7229f91a409c085c5068047f88f51','elnegociador_24@hotmail.com',0,'','','02125488796','04120946720','','','',0,5,1000,3,1,2,282,'',0,0,0,'',0,0),(297,'5454445','piedra','amarilys','piedra','c33367701511b4f6020ec61ded352059','gghgh@hotmail.com',0,'','','21454454','121212121','','','',0,5,1000,3,1,2,2,'',0,0,0,'',0,0),(299,'82563149','rogmari','Beatriz','rogmari','d964173dc44da83eeafa3aebbee9a1a0','kffy@hotmail.com',0,'','','04266877640','04147932898','','','',0,5,1000,3,1,2,367,'',0,0,0,'',0,0),(301,'11075770','mdeamdea','ELIO','MDEAMDEA','447b832e0adced233d8b17d576e8b539','ELIO.MARQUEZ@CANTV.NET',0,'','','04141718068','04141718068','BANESCO','01340213252132166822','ELIO MARQUEZ',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(302,'5454554','dos santos','tamara','rocinante','c8b2a3c7a6ef12ec2e962135fea66890','ghgh@hotmail.com',0,'Centro Hipico Rocinante','','02128922299','04142222222','','','',0,8,1000,3,1,2,219,'',0,0,0,'',0,0),(303,'44545566','soyyo','carolina','soyyo','bd567847cd36364a6a2693d7ffc039c8','hhghghg@hotmail.com',0,'','','02124729207','04120326442','','','',0,5,1000,3,1,2,258,'',0,0,0,'',0,0),(305,'7676767','perez','laguaira','laguaira','dd4b21e9ef71e1291183a46b913ae6f2','uuu@yahoo.com',0,'','','02127164418','04167195084','','','',0,8,30000,3,1,2,22,'',0,0,0,'',0,0),(306,'23548522','help','help','help','25f9e794323b453885f5181f1b624d0b','GHGHGFHH@YAHOO.COM',5,'SPORTS BOOK','45789314-5','02128706731','04242756224','','','',-110,2,0,2,1,2,1,'Revise su Ticket antes de retirarse de la taquilla. SUERTE',0,0,0,'',0,0),(307,'89978878','supercatia','SAUL','supercatia','c33367701511b4f6020ec61ded352059','HBJGHJ@YAHOO.COM',0,'','','02128706731','04242756224','','','',0,6,2000,3,2,2,306,'',0,0,0,'',0,0),(308,'55544551','fpetit','freddy','fpetit','73d94ca09de7d23b853273b035cbc752','ghugh@yahoo.com',0,'','','04121545454','54546565656','','','',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(311,'55645454','lachispa','daibelys','lachispa','90673ae0f44c8fafebe0df3cd2cdd8a7','ghghg@yahoo.com',0,'','','02124713804','04142417348','','','',0,5,1500,3,1,2,2,'',0,0,0,'',0,0),(312,'6719900','..........','....','valencia','25d55ad283aa400af464c76d713c07ad','hghgg@hotmail.com',0,'valencia','','04166951440','04166951440','','','',0,5,3000,3,1,2,2,'',0,0,0,'',0,0),(313,'11098983','22222w','Orlando','laprueba','25f9e794323b453885f5181f1b624d0b','orlando_0207@hotmail.com',5,'Ag Loterias naikel','','02123234011','04142349812','','','',-120,2,0,2,1,2,1,'Revise su ticket antes de retirarse, Suerte',0,0,0,'',0,0),(314,'23423423','zulia','rixon','rixon','db812499d89cc1955242a93d735c0451','rixon2010@hotmail.com',0,'Rixon Sports','','02125487856','04142736508','','','',0,5,1000,3,2,2,2,'',0,0,0,'',0,0),(315,'45448788','naikel1','carlos','naikel1','670b14728ad9902aecba32e22fa4f6bd','sfdf@hotmail.com',0,'','','02125487858','04242586895','','','',0,5,1000,3,1,2,313,'',0,0,0,'',0,0),(316,'15487889','naikel25','mairia','naikel25','670b14728ad9902aecba32e22fa4f6bd','rdewws@hotmail.com',0,'','','02124587556','04141372809','','','',0,5,1000,3,1,2,313,'',0,0,0,'',0,0),(317,'15448752','vigilante','carlos','vigilante','db95e3b34b7f125d34bfc629b66ec06c','oipp@hotmail.com',0,'','','02125547885','04143891076','','','',0,5,1000,3,1,2,313,'',0,0,0,'',0,0),(318,'87654334','chacao','maru','maru','25d55ad283aa400af464c76d713c07ad','hgfhgfd@hotmail.com',0,'Maru SportsBook','','876543456','34568765','','','',0,5,1000,3,1,2,257,'',0,0,0,'',0,0),(320,'11563466','brito','danny','pepe','25d55ad283aa400af464c76d713c07ad','tthays@hotmail.com',0,'centro hipico caribe','','02952621166','04147894480','','','',0,5,5000,3,1,2,2,'',0,0,0,'',0,0),(321,'54545454','apuestas','daniel','lasorpresa','8ddcff3a80f4189ca1c9d4d902c3c909','bjhgh@yahoo.com',0,'','','02952628322','04166817359','','','',0,8,2000,3,1,2,2,'',0,0,0,'',0,0),(322,'17587598','jonagut','jonathan','jonagut','755a94a151a746f3767e44959f89aab7','jonathan__gutierrez@hotmail.com',0,'','','8617719','04128059877','Banesco','6012887211661882','jonathan gutierrez',0,5,0,4,1,1,1,'',0,0,0,'',0,0),(327,'25345543','taketicket2 cementerio','carlos','tt2','96e79218965eb72c92a549dd5a330112','hjsf@hotmail.com',0,'','','23456543','2345654','','','',0,5,1000,3,2,2,2,'',0,0,0,'',0,0),(328,'65396465','BANCAPLAZA1','Pto ordaz','BPLAZA1','d6fbde5b1974dffc74c9b819e4490064','SDHF@hotmail.com',0,'','','8763256','7543257','','','',0,6,10000,3,1,2,260,'',0,0,0,'',0,0),(329,'76542467','BANCAPLAZA2','pto ordaz','BPLAZA2','85358934f39ec96e96014cddb65857e0','D@HOTMAIL.COM',0,'','','765433456','3456543','','','',0,6,10000,3,1,2,260,'',0,0,0,'',0,0),(330,'23456543','JJT','PTO ORDAZ','JJT','9c8d7c47a7de38f67a17f88f0ea05024','GJGQQ@HOTMAIL.COM',0,'','','2468544','2456677','','','',0,6,1000,3,1,2,260,'',0,0,0,'',0,0),(331,'98765478','CLARITA','PTO ORDAZ','CLARITA','dc24f560e7426e92cc75fa2eb487e447','JGJ@HOTMAIL.COM',0,'','','34567654','24732345','','','',0,6,5000,3,1,2,260,'',0,0,0,'',0,0),(332,'98765434','PENDIENTE','PTO ORDAZ','PENDIENTE','e10adc3949ba59abbe56e057f20f883e','GFF@HOTMAIL.COM',0,'','','76543345','3456787654','','','',0,6,1000,3,1,2,260,'',0,0,0,'',0,0),(336,'76543455','mendes','cesar','mendes','7cfe557c2db19bbf0f5ba6a3aaa6199b','sdfff@hotmail.com',0,'','','65434565','34565445676','','','',0,5,1000,3,1,2,282,'',0,0,0,'',0,0),(337,'5666544','video','reinaldo','video','8aed70d21ad0baab37d16e4f0e0cdb1d','jiuhytg@hotmail.com',0,'','','0212334567','04167653413','','','',0,5,1000,3,1,2,282,'',0,0,0,'',0,0),(339,'10666094','perez','alexis','netplace','a21be02a91a183e96257ecf9d7ad5c2e','teamcebra23@hotmail.com',0,'','','02435538373','04120376343','','','',0,5,1500,3,2,2,2,'',0,0,0,'',0,0),(342,'13862249','niÃ±o2','andrea','niÃ±o2','c33367701511b4f6020ec61ded352059','jhjh@yahoo.com',0,'','','04125126908','445454254554','','','',0,5,1500,3,1,2,368,'',0,0,0,'',0,0),(344,'14907114','Frias Calderon','Israel Jose','elchumi','33519f1e9ec76c0d7c9c84bf8f6d0442','israelfrias@hotmail.com',0,'','','02123710229','04166116798','Banco del Caribe','01140150301500400540','Israel Frias',0,2,0,4,1,1,1,'',0,0,0,'',0,0),(345,'25144008','marcano maecano','helijeo','jesus','56b8025076c983066e6e960fb52fcc4e','mata_polo_02@hotmail.com',0,'','','02952623739','04147864654','banfoandes','24568791012481478542','hola',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(349,'33322211','acuario','acuario','acuario','c33367701511b4f6020ec61ded352059','aaass@yahoo.com',0,'','','02123836724','04142406622','','','',0,6,2000,3,1,2,257,'',0,0,0,'',0,0),(350,'5789654','losaltos1','monica','losaltos1','c5c3fc0964d1950aeacfb43d98672adf','efdhygy@hotmail.com',0,'','','357654467','4567899','','','',0,6,2000,3,1,2,257,'',0,0,0,'',0,0),(351,'12343211','manuel41','marlong','manuel41','acfd95e9901860edd4d84b1840e05431','manjue@hotmail.com',0,'','','02123422244','04145522367','','','',0,5,500,3,2,2,2,'',0,0,0,'',0,0),(352,'17944251','leocheo solorzano','Jose Angel','leocheo','d9a77c8634cadeb29975dc5fe466ec99','jasc16@hotmail.com',0,'','','02443861958','04166116369','banesco','013409862998620','Jose Solorzano',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(353,'12121111','maiquetia','jorge','maiquetia3','acda505c4be47708472bbdc1242b2948','rttttggg@hotmail.com',0,'Maiquetia SportsBook','','02124723046','04141953338','','','',0,8,10000,3,1,2,156,'',0,0,0,'',0,0),(354,'10666094','perez','jose ','valencia2','dd4b21e9ef71e1291183a46b913ae6f2','eeeesfsdfd@yahoo.com',0,'','','02435538373','04163477940','','','',0,5,5000,3,1,2,2,'',0,0,0,'',0,0),(362,'45678987','carloss','cesar','carloss','0b61ce5b2194aca9fb261715123972ca','fgh@hotmail.com',0,'','','348765','345678909876','','','',0,5,3000,3,1,2,259,'',0,0,0,'',0,0),(363,'34567876','22222p','carolina','carolina','5f3bc5221626b2f8d66261fb07339462','jd@hotmail.com',3,'BETCESC.COM','','76543456765','567654567','','','',0,2,0,2,2,2,1,'\"Revise su ticket antes de retirarse de la taquilla, este boleto es indispensable para cobrar\"',0,0,0,'',0,0),(364,'76543456','albalux','prueba10','prueba10','25f9e794323b453885f5181f1b624d0b','ghgf@hotmail.com',0,'Tickets De Prueba','','87654456','34567543','','','',0,5,1000,3,1,2,211,'',0,0,0,'',0,0),(367,'14789632','2nelson mgta','nelson mgta','nelson','a4e360681676c770121e891f8c407572','22jjj@yaho.com',5,'MARGARITA SPORT','J-7453219-6','023145698521','041422222222','','','',-120,2,0,2,2,2,1,'Revise su ticket antes de retirarse de la taquilla',0,0,0,'',0,0),(368,'56981433','2aragua','aragua','aragua','972dc6d32ac66c7a13d3ce4d85f539ac','vjre4@espn.com',5,'ARAGUA SPORTS','000000000','6575766868','657587980','','','',-120,2,0,2,2,2,1,'Revise su ticket antes de retirarse de las taquillas',0,0,0,'',0,0),(372,'12354698','morochos','morochos','morochos','6e20826c2cc302af643ad513d0874781','pokj@hotmail.com',0,'','','02128735673','04241850281','','','',0,5,1000,3,1,2,2,'',0,0,0,'',0,0),(373,'11478525','efectivo','efectivo','efectivo','50a202e60b2f9f6451ff0ed5326da05c','ffffjjkj@yahoo.com',0,'','','02128931357','04129931754','','','',0,5,2000,3,1,2,184,'',0,0,0,'',0,0),(374,'54644564','rodriguez','antonio','joedoan','a2cfaac9470f824c427f4d4ee05c846b','hghuy@hotmail.com',0,'','','02123213925','04142574624','','','',0,6,1000,3,1,2,257,'',0,0,0,'',0,0),(375,'21313213','lopez','charbel','charbel','e10adc3949ba59abbe56e057f20f883e','cgftr@hotmail.com',0,'','','02123244567','04146578090','','','',0,6,1000,3,1,2,257,'',0,0,0,'',0,0),(376,'33366694','coromoto','coromoto','coromoto','3de5703c792553b4311416e718c0dc11','xxauyh@yahoo.com',0,'','','02124616605','04125969194','','','',0,5,1500,3,1,2,2,'',0,0,0,'',0,0),(378,'11114447','jahm','jahm','jahm','fccaae3ddbd1a2c06c0b3ec049b086c4','hhftet@yaho.com',0,'','','02125244353','04143107440','','','',0,5,2000,3,1,2,257,'',0,0,0,'',0,0),(380,'54545454','elparamo','elparamo','elparamo','ab62bbe85a76207e90445f63b974e1f4','hguyt@yahoo.com',0,'','','02123834191','04143093816','','','',0,5,2000,3,1,2,257,'',0,0,0,'',0,0),(381,'12546652','yaleska','oscar torrealba','yaleska','05ab9a4fa54858c701819c57f455b279','anacelso1967@hotmail.com',0,'','','02124439844','04129140624','','','',0,6,1500,3,1,2,258,'',0,0,0,'',0,0),(382,'15489656','ibarra','erlinda','greinamar','7cc4220b0ed0822b3d31acf09484c3fc','aliomar_c@hotmail.com',0,'','','02124425672','04142592734','','','',0,6,1000,3,1,2,258,'',0,0,0,'',0,0),(383,'23423444','lajygua','maria','lajygua','187a281bd72f5e3965f214f59d2396d5','hhsyy@hotmail.com',0,'','','02123663378','04142553678','','','',0,5,1000,3,1,2,257,'',0,0,0,'',0,0),(384,'26773882','cata','miriam','lajygua2','a740dc9578bcaf411461870ea2698ec2','uuta@hotmail.com',0,'','','02128830982','04142556278','','','',0,5,1000,3,1,2,257,'',0,0,0,'',0,0),(385,'76575676','margarita','angel','elange','df780a97b7d6a8f779f14728bccd3c4c','cyggy@hotmail.com',0,'','','0323444555','04143223467','','','',0,5,1000,3,1,2,280,'',0,0,0,'',0,0),(386,'54545497','loto4','loto4','loto4','3a9401d025209e7a41b3315d640ce5f0','jhjghggh@hotmail.com',0,'','','02129523097','04166323343','','','',0,5,2000,3,1,2,257,'',0,0,0,'',0,0),(387,'44554117','loto04','loto04','loto04','4d8da22b4aa44aa696909c6937ed7def','ghygty@hotmail.com',0,'','','02984564554','241544564594','','','',0,5,2000,3,1,2,257,'',0,0,0,'',0,0),(388,'65677877','arco','arco','arco','4ce1b7689636443ace6e74fb296682d8','11jhh@yahoo.com',0,'','','02126380969','111111111111','','','',0,5,2000,3,1,2,257,'',0,0,0,'',0,0),(391,'15667829','criby','orlando','lasuerte','73b197105b5366d300bcab1aba35fb9b','gyuuh76@hotmail.com',0,'','','02123355687','04142336209','','','',0,5,1000,3,1,2,313,'',0,0,0,'',0,0),(392,'45545457','elangel','elangel','elangel','df780a97b7d6a8f779f14728bccd3c4c','jhgh@yahoo.com',0,'','','04129468443','11111111111','','','',0,5,1500,3,1,2,367,'',0,0,0,'',0,0),(397,'11892092','dos santos','orlando','riogrande','7cbe8407874e7b07aac279cd3d4be09e','tequeeeejs2@hotmail.com',0,'Agencia de LoterÃ­as RÃ­o Grande','','02123994033','04145267289','','','',0,8,1000,3,1,2,219,'',0,0,0,'',0,0),(398,'11852721','lopez','luis','ve1972','73f078d991ce9ac5423f703812689ddb','lgls1972@gmail.com',0,'','','04142416068','04142416068','provincial','','luis lopez',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(422,'11943651','daza','jorge','jdaza','fca3012ae560b15258550b8589e0d93a','jorferda@yahoo.com',0,'','','04122421846','04142987017','','','',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(434,'16152867','damelio','omar ','maglio','73b197105b5366d300bcab1aba35fb9b','ogds30@hotmail.com',0,'','','0242','04141483495','bod','56614210','omar damelio',0,5,0,4,1,1,1,'',0,0,0,'',0,0),(439,'14235432','urbina','antonio','robin1','dd4b21e9ef71e1291183a46b913ae6f2','jeis@hotmai.com',0,'','','02123456075','04142345905','banesco','01340447014477334164','antonio urbina',0,6,3000,3,2,1,2,'',0,0,0,'',0,0),(442,'16114237','zabala','darwin','darwin','1bbd886460827015e5d605ed44252251','luise@hotmail.com',0,'','','04141240558','04141240558','mercantil','01050948302048570238','luis escobar',0,6,5000,3,1,2,2,'',0,0,0,'',0,0),(443,'16114237','macarao','javier','ovalo','dd4b21e9ef71e1291183a46b913ae6f2','javier181626@hotmail.com',0,'ovalo hipico','j 29779771-8','02126334334','04149516332','banesco','01347895034024384020','javier macarao',0,5,5000,3,1,2,446,'',0,0,0,'',0,0),(444,'16114237','perez','windali','nuevaepoca','1bbd886460827015e5d605ed44252251','windali@hotmail.com',0,'tribuna de traqueos','j54679558-9','02126337443','04149516332','banesco','01340447988606596890','windani perez',0,5,5000,3,1,2,2,'',0,0,0,'',0,0),(446,'18298028','gasperi','ronald','ronald','25d55ad283aa400af464c76d713c07ad','ronald@hotmail.com',3,'todos','183080382023','02123485834','04143049595','banesco','01340580803082230182','ronald gasperi',-120,2,0,2,1,2,1,'',0,0,0,'',0,0),(449,'4644263','GONZALEZ','CELIS ','CELIS8','227b519e83a8b99329302ad2d37d0bbb','GONZALEZCELIS8@GMAIL.COM',0,'','','2475915','04169685277','VENEZUELA','01020351150003351138','CELIS GONZALEZ',0,5,0,4,1,1,1,'',0,0,0,'',0,0),(450,'15447654','fgjkjk','poolelimar2','poolelimar','dd4b21e9ef71e1291183a46b913ae6f2','elpotro126@hotmail.com',0,'','','02125745552','04149516332','','','',0,8,30000,3,1,2,22,'',0,0,0,'',0,0),(451,'81346548','GONCALVES OLIVEIRA','CARLOS MANUEL','shogun','0803892fd53fd605973b7ee733d796ac','inv.confiables@hotmail.com',0,'','','02127612835','04142802031','BANESCO','01340359703593024856','CARLOS GONCALVES',0,5,0,4,1,1,1,'',0,0,0,'',0,0),(452,'24181904','correia','reniel','reniel','f24713a1dc64ecc5505b49e6b7b91f15','renielelpapa@hotmail.com',0,'','','02123112884','04128182226','','','',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(453,'16115996','fernandez','Jader','jader_fc','1bc49dbf9a39d80379220fc5ed42985c','jaderick13@hotmail.com',0,'','','02128319837','04142936598','banesco','01340014830142159589','Jader fernandez',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(454,'16472093','sanabria ramirez','angel eduardo','asanab','51b1c4de73f995027af2a45d19854b08','asanab_r20@hotmail.com',0,'','','02127424291','04126337847','banesco','01340372433722141860','angel sanabria',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(455,'18589201','Lizarazo Acevedo','yeisy Andreina','eder_2509','15341338f4deff3045b579e1643ac73f','eder_2509@hotmail.com',0,'','','0212643231','04142479618','provincial','5895240105245061283','',0,2,0,4,1,1,1,'',0,0,0,'',0,0),(456,'17026710','ortega','yhorman','yhorman26','d4c0c19682687ec0da3909c467266e65','ortega737@gmail.com',0,'','','02423642994','04163414769','provincial','01080135450200090734','yhorman ortega',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(457,'9584874','Vera Vargas','Jesus Arquimedes','Chichivera','e5cce516569eb436563b89c64a3e0e66','chipelotero@yahoo.com',0,'','','02698493122','04166420777','BOD','01160112010184148596','Jesus Vera',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(458,'16135148','linares','derwin','deryen','d1df532cc2e62b258688f2c4001561b1','dlinares@bancomercantil.com',0,'','','02124512385','04129640608','mercantil','01050077060081067453','derwin linares',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(459,'6367923','urribarri','ronald','ronaldu','67de3b48bfab6ec93be8f010fa3c477f','RONALDU33@HOTMAIL.COM',0,'','','02682512287','','mercantil','0160061288','Ronald urribarri',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(460,'14045517','quijada','johan','boris5778','ff609d38e62f47505405c4627c4abab5','johanq23@hotmail.com',0,'','','04161933118','04161933118','mercantil','0638114233','johan quijada',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(461,'16265191','osal','asdrubal','asdru2484','2a8acd1bb3116ee18eb28243a6f5d3f5','asdru_osal@hotmail.com',0,'','','','','','','',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(462,'14674009','lugo','julio cesar','juliolc_25','517093152210b848d0e47d3baa9fa9f3','juliolc_25@hotmail.com',0,'','','02123230069','04120903059','','','',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(463,'14062844','hevia','emerson','emerhevia','3fcc3e238bcd40b94f9e593c96aa238e','emerhevia@hotmail.com',0,'','','02128723125','04262170686','mercantil','01050016811016272340','emerson hevia',0,2,0,4,1,1,1,'',0,0,0,'',0,0),(464,'11556483','Martinez','Mayerling ','mayemarti','7f90c7cb9fc9e022a02c00840fdbae12','mayerlingjw@hotmail.com',0,'','','','04168001727','banesco','01340440234401036785','martinez mayerling',0,2,0,4,1,1,1,'',0,0,0,'',0,0),(465,'6700905','sanchez','victor','victor','d9ea9bc94cd5c276ea033642e5fac8fe','victorsanchez138@gmail.com',0,'','','442773','04143308820','','','',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(466,'6155482','silva','antonio','pelotero','96a081d6dc0c879673d52cb87765d3e1','antoniosilva052@hotmail.com',0,'','','9441847','04242243086','banesco','01340420814203014509','antonio silva',0,2,0,4,1,1,1,'',0,0,0,'',0,0),(467,'18815871','MARTINEZ FUENMAYOR','JOLIVER ELIAS','GALAHIPICA','2ac3fb237646483583a2bdd99342a296','GALAHIPICA@HOTMAIL.COM',0,'','','02124813599','04168159749','BANCO VENEZUELA','48515008485874958','JOLIVER MARTINEZ',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(468,'12746057','arteaga marquez','luis daniel','dani','1be7bee98095925f6a0eebef9c3012a8','luisdaniel2021@yahoo.es',0,'','','04120426249','','','','',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(469,'18830896','perez','Alvaro','123456','e10adc3949ba59abbe56e057f20f883e','APO@CANTV.NET',0,'','','02122125232','02122125232','mercantil','124512454112','Alvaro',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(470,'13828342','lara','jose ','lack','25f9e794323b453885f5181f1b624d0b','josephlara2002@hotmail.com',0,'','','','','','','',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(471,'14450792','rodriguez','pablo','pablocesar','460a7fb36f4c205f2840e7e1c00b7ce4','pablocesar25330@hotmail.com',0,'','','04129638369','04129638369','banesco','01340370893702071799','pablo cesar rodriguez rodriguez',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(472,'22221112','ddettg','gyyk  nuu','zzzzzzz','b0baee9d279d34fa1dfd71aadb908c3f','drhuuo@hotmail.com',0,'','','5678908','014345677','','','',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(473,'13818782','colina','buenaventura ','el matador','25d55ad283aa400af464c76d713c07ad','andrioc1@hotmail.com',0,'','','02424155676','04148081083','banesco','01340437204371011896','',0,2,0,4,1,1,1,'',0,0,0,'',0,0),(474,'13818782','colina','yumer','dato100%','25d55ad283aa400af464c76d713c07ad','yumerc04@hotmail.com',0,'','','02424155676','04124585254','banesco','01340437204371018865','si',0,2,0,4,1,1,1,'',0,0,0,'',0,0),(475,'18389605','torres','alfredo','craker','85738572271b1cedfd4f27f8a5654335','alfredo-cenon@hotmail.com',0,'','','','04129945443','de  venezuela','01020213280107702995','angelica garcia',0,4,0,4,1,2,1,'',0,0,0,'',0,0),(476,'22516111','vivas','javier','javi','04e2889dec90be70277a727ef6de8da8','javierv14@hotmail.com',0,'','','','','','','',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(477,'21457863','Torres','Luis','Ramon1211','e93b384e9b4b1337d8c5ae4455774589','Ramontorbello@hotmail.com',0,'','','04168524569','04169536842','Central','28897539231823581389','Luis Torres',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(478,'17558742','MILLOR','julia ','268000','670b14728ad9902aecba32e22fa4f6bd','JULIAMILLOR@HOTMAIL.COM',0,'','','','','','','',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(479,'7302738','navas','jorge','jorgenavas','b24a3ae871a1e67901b7f33cd456bce8','navas738@gmail.com',0,'','','02763482858','04161294322','mercantil','01050116480116106409','jorge navas',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(480,'9747157','ROMERO','EDIXON','AGUILA1968','24ae5d84dd0da9b2eb9b10336b2fe517','edixon_rc@hotmail.com',0,'','','04146597243','04146597243','banco de venezuela','01020386410100012290','edixon romero',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(481,'15643148','vargas','carlos ','cevr28','f8655c2a3e82c3e8ff8725b189d12f04','cevr28@hotmail.com',0,'','','02423720264','04121997550','','','',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(482,'14639539','Martinez','Uvaldo','Uvaldo23','886f3858ef418cf56abd3b59cbbc595c','uvaldo23tsu@yahoo.es',0,'','','','04145261984','Provincial','01080087160200466475','Uvaldo MartÃ­nez GonzÃ¡lez',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(483,'13252239','Belisario G','Johsman E.','Vebelisajo','f03bc0c21fb24601d56d28b5b30c987e','johsmanbel@hotmail.com',0,'','','','04125696365','','','',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(484,'14242871','marchena','domingo','dominik21','dc0fa7df3d07904a09288bd2d2bb5f40','domingo_marchena@hotmail.com',0,'','','02423617953','04165418985','mercantil','01050073771073294579','domingo marchena',0,2,0,4,1,1,1,'',0,0,0,'',0,0),(485,'12753435','hernandez','johnmer','mome','ff864f984f9c773a2220e7f25f88f3c1','hernandezjohj@gmail.com',0,'','','','04168478022','','','',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(486,'19296648','alfonzo','jofranluis','jofran','67897ab8c751cc0845e17e0685dc790a','jofranluis.alfonzo@hotmail.com',0,'','','','','','','',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(487,'20665832','gonzalez','wilimbher','williboys','9af50ece41eedb30b22fdeae34fc5a71','wilimbher@hotmail.com',0,'','','02423616021','04162946787','','','',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(488,'17488486','Aponte','Anderson','anderson','3566b48ae4edf01a755f64d58f043ab7','arturo_anderson@hotmail.com',0,'','','02125778689','04125915479','','','',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(489,'18107713','ginez','jorge','jorge3628','51f99bf71d8b0c6fef489a7afdc5db08','jorgelgc88@hotmail.com',0,'','','','','','','',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(490,'10968996','HOMES','IVAN','IVANHOMES','bde3a89fbb667ff12251f28964bafd19','ivanrafaelhomes@yahoo.com',0,'','','02692453279','04265674394','provincial','01080258080100048','IVAN HOMES',0,5,0,4,1,1,1,'',0,0,0,'',0,0),(491,'11161960','hernandez','concepcion ','sportm ','93d9b95d123a739631e3f724e9d9e137','sportmillions@hotmail.com',0,'','','04122923556','','banesco','01340329543295059981','concepcion hernandez',0,5,0,4,1,2,1,'',0,0,0,'',0,0),(492,'11925504','goncalves','juan','jgoncalves','e0b7225349b8c5eed25355ddea5c1f2f','juangoncalves1976@hotmail.com',0,'','','02126825321','04142705083','mercantil','01050078850078206162','juan goncalves',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(493,'25536553','padron ','carlos ','cjpiÃ±a_18','db49e560e98f26ffad75d55c8954594b','el_lucho2004@hotmail.com',0,'','','02423702556','04121396794','','','',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(494,'10974540','FLORES','GREGORIO','FLORESGJ','11eaa2c31c1e45247f5a1f8bf201d48d','flores.gj@hotmail.com',0,'','','02694142591','04141699008','PROVINCIAL','01080137180100038116','GREGORIO FLORES',0,5,0,4,1,1,1,'',0,0,0,'',0,0),(495,'15141382','MENDEZ','FRANKLIN','MENDEZFJ','1ecbafb25458cd231cf1769dfc4aae77','MENDEZFW@GMAIL.COM',0,'','','','04160608699','BANESCO','01340946340001003446','FRANKLIN MENDEZ',0,5,0,4,1,1,1,'',0,0,0,'',0,0),(496,'19192843','mora','alfredo','1235789','cc70e9369e9f87a660812e08d76a828e','kenny_6464@hotmail.com',0,'','','02424215048','04127802025','banesco','00000254545454','',0,2,0,4,1,2,1,'',0,0,0,'',0,0),(497,'11111111','treviso','italo','vanju','1bbd886460827015e5d605ed44252251','elpotro@yahoo.com',0,'van ju','','16416131313','031131313131','','','',0,6,5000,3,1,2,2,'',0,0,0,'',0,0),(498,'11111111','sosa','yeye','yeye1','1bbd886460827015e5d605ed44252251','elpotro162623@hotmail.com',0,'andresbello','','15651131313','166161616161','','','',0,6,10000,3,1,2,2,'',0,0,0,'',0,0),(499,'22202020','sosa','junior','junior','1bbd886460827015e5d605ed44252251','junior@hotmail.com',0,'losvenezolanos','','303030303030','202033049449','','','',0,6,10000,3,1,2,2,'',0,0,0,'',0,0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

--
-- Table structure for table `usuario_admin`
--

DROP TABLE IF EXISTS `usuario_admin`;
CREATE TABLE `usuario_admin` (
  `id_usuario` int(11) NOT NULL,
  `id_admin` int(11) NOT NULL,
  PRIMARY KEY  (`id_admin`,`id_usuario`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `FK_usuario_admin_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario_admin`
--


/*!40000 ALTER TABLE `usuario_admin` DISABLE KEYS */;
LOCK TABLES `usuario_admin` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `usuario_admin` ENABLE KEYS */;

--
-- Table structure for table `usuario_juego`
--

DROP TABLE IF EXISTS `usuario_juego`;
CREATE TABLE `usuario_juego` (
  `id_usuario` int(11) NOT NULL,
  `id_juego` int(11) NOT NULL,
  `fecha_sis` datetime NOT NULL,
  `id_status_juego` int(11) NOT NULL,
  PRIMARY KEY  (`id_juego`,`id_usuario`),
  KEY `id_status_juego` (`id_status_juego`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_juego` (`id_juego`),
  CONSTRAINT `FK_usuario_juego_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_usuario_juego_2` FOREIGN KEY (`id_juego`) REFERENCES `juego` (`id_juego`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_usuario_juego_3` FOREIGN KEY (`id_status_juego`) REFERENCES `status_juego` (`id_status_juego`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario_juego`
--


/*!40000 ALTER TABLE `usuario_juego` DISABLE KEYS */;
LOCK TABLES `usuario_juego` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `usuario_juego` ENABLE KEYS */;

--
-- Table structure for table `usuario_juego_equipo`
--

DROP TABLE IF EXISTS `usuario_juego_equipo`;
CREATE TABLE `usuario_juego_equipo` (
  `id_usuario_juego_equipo` int(11) NOT NULL auto_increment,
  `id_usuario` int(11) NOT NULL,
  `id_juego_equipo` int(11) NOT NULL,
  `fecha_sis` datetime NOT NULL,
  `spread` double default NULL,
  `spread_logro` int(11) default NULL,
  `total` double default NULL,
  `total_logro` int(11) default NULL,
  `money_line` int(11) default NULL,
  `id_status_juego` int(11) NOT NULL,
  PRIMARY KEY  (`id_usuario_juego_equipo`),
  KEY `id_status_juego` (`id_status_juego`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_juego_equipo` (`id_juego_equipo`),
  CONSTRAINT `FK_usuario_juego_equipo_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_usuario_juego_equipo_2` FOREIGN KEY (`id_juego_equipo`) REFERENCES `juego_equipo` (`id_juego_equipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_usuario_juego_equipo_3` FOREIGN KEY (`id_status_juego`) REFERENCES `status_juego` (`id_status_juego`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario_juego_equipo`
--


/*!40000 ALTER TABLE `usuario_juego_equipo` DISABLE KEYS */;
LOCK TABLES `usuario_juego_equipo` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `usuario_juego_equipo` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

