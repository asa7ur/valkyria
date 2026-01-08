/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-12.1.2-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: valkyria
-- ------------------------------------------------------
-- Server version	12.1.2-MariaDB-ubu2404

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `artist_images`
--

DROP TABLE IF EXISTS `artist_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `artist_images` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) NOT NULL,
  `artist_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `artist_id` (`artist_id`),
  CONSTRAINT `1` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist_images`
--

LOCK TABLES `artist_images` WRITE;
/*!40000 ALTER TABLE `artist_images` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `artist_images` VALUES
(1,'08879063-dc9f-42aa-bb1f-c48ccdc4a6a5',1),
(3,'ae82aee5-7bc7-4a13-b250-6c2f37a62e12',2),
(4,'75693dab-e7c2-4892-8ddc-5925d1ba741c',3),
(6,'4213b8e6-7227-46ba-8b91-1db91c29bb0b',4),
(7,'2b37fd16-439b-43f5-b8be-5876010ef980',5),
(8,'3e07fa67-a574-493b-ba78-35d004e52943',6),
(9,'809949dd-1946-4d43-a94e-53d1d752ebbd',7),
(11,'050c7d4f-2057-42ad-a041-113604d14a25',9),
(12,'bb3284fc-6863-415f-be76-d485335269a6',8);
/*!40000 ALTER TABLE `artist_images` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `artists`
--

DROP TABLE IF EXISTS `artists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `artists` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `genre` varchar(100) NOT NULL,
  `country` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `official_url` varchar(255) DEFAULT NULL,
  `instagram_url` varchar(255) DEFAULT NULL,
  `tiktok_url` varchar(255) DEFAULT NULL,
  `youtube_url` varchar(255) DEFAULT NULL,
  `tidal_url` varchar(255) DEFAULT NULL,
  `spotify_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artists`
--

LOCK TABLES `artists` WRITE;
/*!40000 ALTER TABLE `artists` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `artists` VALUES
(1,'Gojira','+33 1 42 67 00 00','contact@gojira-music.com','Progressive Death Metal','France','Taking their name from the original Japanese pronunciation of Godzilla, French heavy metal quartet Gojira have gone from the utmost obscurity in the first half of their career to widespread global recognition in the second. Combining elements of thrash, death, math, groove, progressive, and post-metal with philosophical and environment-themed lyrics, the band found mainstream favor in 2012 with the release of their fifth long-player, L\'Enfant Sauvage, and doubled-down on that success with 2016\'s Grammy-nominated Magma and 2021\'s hard-hitting and versatile Fortitude. In 2024, Gojira became the first heavy metal act to perform at an Olympic opening ceremony. ','cdcbb431-ad1b-473a-be99-1b9e23e4b426','https://www.gojira-music.com/','https://www.instagram.com/gojiraofficial','https://www.tiktok.com/@gojiraband','https://www.youtube.com/gojira','https://tidal.com/artist/3883303','https://open.spotify.com/embed/track/2pZsQqXFgcY03vRyZxSQhU?utm_source=generator'),
(2,'Loathe','+44 20 7946 0000','info@loathe.uk','Metalcore','UK','Loathe (sometimes stylized as LOATHE) are an English nu metalcore band from Liverpool. The band\'s music makes use of elements of metalcore and nu metal, while also incorporating more experimental aspects from genres like shoegaze, progressive metal and industrial music. Formed in 2014, the group consists of lead vocalist Kadeem France, guitarist and second vocalist Erik Bickerstaffe, drummer Sean Radcliffe and bassist Feisal El-Khazragi. Loathe have been nominated for Heavy Music Awards best UK breakthrough band and Metal Hammer Golden Gods Awards best new band in 2018. In a 2020 poll by Revolver magazine, they were voted the third most likely contemporary band to break through into the mainstream.','c4dae7d0-a787-4992-bfac-32918c423e87','https://loatheasone.co.uk/','https://www.instagram.com/loatheasone','','https://www.youtube.com/watch?v=pRzlroDyvXo','https://tidal.com/artist/3712384','https://open.spotify.com/embed/track/4OmlsAT8r4q9vPFBvfYgyZ?utm_source=generator'),
(3,'Rammstein','+49 30 2091 0000','management@rammstein.de','Industrial Metal','Germany','Over the course of three decades, Rammstein have shaped heavy music with their unique blend of industrial sound, provocative aesthetics, and spectacular live shows. Formed in Berlin in 1994, the line-up has remained unchanged: Till Lindemann, Richard Z. Kruspe, Paul Landers, Oliver Riedel, Christoph Schneider, and Flake Lorenz. Eight studio albums, including two German No.1 singles (“Pussy”, “Deutschland\") and their most recent release “Zeit\" (2022), have earned them multiple gold and platinum awards worldwide as well as Grammy nominations. Known for uncompromising creativity, iconic videos, and incendiary stage productions, Rammstein continue to defy convention and sell out stadiums across the globe – writing one of the most influential and unique chapters in rock history.','192743ce-c57e-4dea-b762-fc14437ac7bc','https://www.rammstein.de/en/','https://www.instagram.com/rammsteinofficial/','https://www.tiktok.com/@rammstein','https://www.youtube.com/channel/UCYp3rk70ACGXQ4gFAiMr1SQ','https://tidal.com/artist/24972','https://open.spotify.com/embed/track/3gVhsZtseYtY1fMuyYq06F?utm_source=generator'),
(4,'Faetooth','+1 213 555 0198','faetoothband@gmail.com','Doom Metal','USA','Spawning from Los Angeles, Faetooth forges fairy-doom: an eclectic amalgamation of doom metal, sludge, and shoegaze. From spellbinding melodies to guttural shrieks, Faetooth’s music conjures a looming atmosphere offering you a glimpse into their mystifying realm.','4ee92796-7185-4bc6-826c-ef80e151f9c6','','https://www.instagram.com/faetooth/','https://www.tiktok.com/@faetooth','https://www.youtube.com/@Faetooth','https://tidal.com/artist/17871929','https://open.spotify.com/embed/track/7hwngx8lZypzVtHeKAxi4F?utm_source=generator'),
(5,'Deftones','+1 916 555 0123','contact@deftones.com','Alternative Metal','USA','Deftones have always defined boundless creativity in the music space. Across nine studio albums, they have carved out an unmistakable sonic identity — ferocious yet dreamlike, while making space for constant refinement and surprise. Now, decades on from the groove-forward sound of their era-defining debut, Adrenaline, and following a long line of masterpieces including 2000’s White Pony, 2010’s Diamond Eyes and 2020’s Ohms — an album that earned them their second and third Grammy nominations — they return with one of the most focused statements of their career: private music. Joining the band’s creative core of Chino Moreno, Stephen Carpenter, Abe Cunningham and Frank Delgado (as well as touring bassist Fred Sablan, who appears on the album) is producer Nick Raskulinecz, who previously worked on Diamond Eyes and 2012’s riveting Koi No Yokan. The result is a lean, masterfully paced 11-song set that plays like a new Deftones benchmark.','eee6a9ac-f592-46db-abbc-4bcdab5b8cf8','https://www.deftones.com/','https://www.instagram.com/deftones/','https://www.tiktok.com/@deftones','https://www.youtube.com/@deftones','https://tidal.com/artist/15349','https://open.spotify.com/embed/track/51c94ac31swyDQj9B3Lzs3?utm_source=generator'),
(6,'Behemoth','+48 22 654 00 00','behemoth.mgmt@new-east.pl','Blackened Death Metal','Poland','Resting on their laurels was never in consideration as far as Behemoth were concerned. The Shit Ov God is emblematic of that. Here\'s a band that, 34 years in, is releasing its most inflammatory and extreme record to date. 8 songs that go into the fathoms of humanity, divinity and what defiance means in an age where individuality is prized but everyone is clinging to their saviours. Musically, politically, or otherwise. The title of the album is in line with those sensibilities. Intentionally polarizing, founder and frontman Nergal, says of the title \" We chose this provocative title deliberately, rejecting subtlety in favor of a direct and polarizing statement. It\'s a defiant plunge into the depths, daring to seek the absolute even in the gutter.\"','64165fa2-f282-4f86-bdfe-86fcc4c37567','https://www.behemoth.pl/','https://www.instagram.com/behemothofficial/','https://www.tiktok.com/@behemothofficial','https://www.youtube.com/user/Behemothofficial','https://tidal.com/artist/24058','https://open.spotify.com/embed/track/2FsDybzWgntIa8M3Y7AenT?utm_source=generator'),
(7,'Fit For An Autopsy','+1 201 555 0144','ffaa@metal-management.com','Deathcore','USA','The crushing music of Fit For An Autopsy is for any fan of extreme metal, its sound and fury is absolutely unflinching in purpose. The band expertly blends excessive, force-fueled death metal with atmospheric groove and impassioned personal diatribes. The Nothing That Is, Fit For An Autopsy’s seventh and latest album (third for Nuclear Blast), bears no sign whatsoever of commercial concession or rehashed past glories. Instead, the ten-song album finds the band exploring moodier textures and deeper emotions that add new power and dynamics to their already brutal and complex music, while also completely tuning out the ambient noise of passing musical trends and the expectations of the outside world.','e1fe03de-567a-4d7e-9190-1d76baff91e6','https://fitforanautopsy.co/','https://www.instagram.com/fitforanautopsy/','https://www.tiktok.com/@fitforanautopsyofficial','https://www.youtube.com/@FitForAnAutopsy','https://tidal.com/artist/3985809','https://open.spotify.com/embed/track/79yvAWlqVukArNOxtyyKCI?utm_source=generator'),
(8,'Lorna Shore','+1 732 555 0166','lornashore@touring.com','Deathcore','USA','Call them heroes or hell-bringers, it doesn’t matter to Lorna Shore. On their fifth album—I Feel The Everblack Festering Within Me—the New Jersey quintet puts every metal subgenre on notice, forcing listeners to wonder what took the genre so long to evolve. Andrew O’Connor’s orchestral arrangements provide an epic, cinematic backdrop for the band’s stentorian power. Adam De Micco shifts seamlessly from light-speed black metal to \'70s-style guitar phrasing, while Will Ramos proves himself the heir to Mike Patton’s vocal madness. Driven by the propulsive terror of Yager and Archey, the album transcends deathcore, leaning into grandiose prog-rock territory through sheer extremity. By torching the rulebook to divine their own truth, Lorna Shore has created a physical onslaught that demands other genres step up their game.','b10e2abb-4812-48bb-a27d-98141e6fcd5c','https://lornashoreband.com/','https://www.instagram.com/lornashore','https://www.tiktok.com/@lornashoreofficial','https://www.youtube.com/@LornaShore','https://tidal.com/artist/5269283','https://open.spotify.com/embed/track/0O26gtfjuscAOnQobjNPPL?utm_source=generator'),
(9,'Opeth','+46 8 123 456 00','management@opeth.com','Progressive Metal','Sweden','More than three decades into their career, Opeth have trained their admirers to expect the unexpected. But even by their own standards, the Swedish progressive titans have conjured something extraordinary this time around. The band’s 14th studio exploration, The Last Will & Testament, is the darkest and heaviest record they have made in decades, it is also the most fearlessly progressive. A concept album recounting the reading of one recently deceased man’s will to an audience of his surviving family members, it brims with haunting melodrama, shocking revelations and some of the wildest and most unpredictable music that songwriter/frontman Mikael Åkerfeldt has ever written. Proud adherents to a progressive ethos, Opeth have never repeated themselves, and The Last Will & Testament is every bit as revelatory and adventurous as its 13 predecessors. But one thing is undeniable: Mikael Akerfeldt’s peerless death metal growls are back, for the first time since Watershed in 2008. After three decades of dazzling the world, Opeth have made their most daring creative leap yet. The Last Will & Testament is a progressive and dramatic triumph, and yet more proof that expecting the unexpected is the only way forward for fans of Sweden’s finest.','23df5770-e321-4eec-b0f8-2e00a57cff51','https://opeth.com/','https://www.instagram.com/officialopeth','https://www.tiktok.com/@opethofficial','https://www.youtube.com/@opeth','https://tidal.com/artist/37348','https://open.spotify.com/embed/track/0ppdt8zRZOHIKh4ZDB0Zp9?utm_source=generator'),
(10,'Jinjer','+380 44 234 5678','jinjer.booking@gmail.com','Progressive Metal','Ukraine',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(11,'Metallica','+1 415 555 0101','hq@metallica.com','Thrash Metal','USA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(12,'Mastodon','+1 404 555 0188','info@mastodonrocks.com','Sludge Metal','USA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(13,'Evanescence','+1 501 555 0155','mgmt@evanescence.com','Alternative Metal','USA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(14,'Tool','+1 310 555 0199','toolband@management.com','Progressive Metal','USA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(15,'Whitechapel','+1 865 555 0177','whitechapel@metalblade.com','Deathcore','USA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(16,'VOLA','+45 31 12 34 56','contact@volaband.com','Progressive Metal','Denmark',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(17,'Deafheaven','+1 415 555 0122','info@deafheaven.com','Blackgaze','USA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(18,'Judas Priest','+44 20 7946 0555','mgmt@judaspriest.com','Heavy Metal','UK',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(19,'Pantera','+1 817 555 0199','info@pantera.com','Groove Metal','USA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(20,'Danheim','+45 70 10 20 30','contact@danheimmusic.com','Neofolk','Denmark',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(21,'Wardruna','+47 55 12 34 56','info@wardruna.com','Nordic Folk','Norway',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(22,'Allt','+46 8 505 123 00','alltband@gmail.com','Progressive Metalcore','Sweden',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(23,'Soen','+46 70 123 45 67','management@soenmusic.com','Progressive Metal','Sweden',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(24,'Igorrr','+33 1 40 20 50 50','igorrr.contact@gmail.com','Avant-garde Metal','France',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(25,'Devin Townsend','+1 604 555 0192','info@hevydevy.com','Progressive Metal','Canada',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(26,'Linkin Park','+1 310 555 0110','contact@linkinpark.com','Nu Metal','USA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(27,'Imperial Triumphant','+1 718 555 0144','it@gileadmedia.net','Avant-garde Black Metal','USA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(28,'Slipknot','+1 515 555 0166','mgmt@slipknot1.com','Nu Metal','USA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(29,'Alice in Chains','+1 206 555 0188','info@aliceinchains.com','Grunge','USA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(30,'Sepultura','+55 11 3060 0000','contact@sepultura.com.br','Groove Metal','Brazil',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(31,'Periphery','+1 301 555 0122','periphery.mgmt@gmail.com','Progressive Metal','USA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(32,'Spiritbox','+1 250 555 0133','spiritbox@palechord.com','Progressive Metalcore','Canada',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(33,'Amira Elfeky','+1 213 555 0177','amira.elfeky@mgmt.com','Nu-gaze','USA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(34,'HEALTH','+1 323 555 0144','health@youwillloveeachother.com','Industrial Rock','USA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(35,'Bad Omens','+1 804 555 0155','badomens@sumerianrecords.com','Alternative Metal','USA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(36,'Jutes','+1 416 555 0199','jutes.mgmt@gmail.com','Alt Rock','Canada',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(37,'In Mourning','+46 23 123 45 00','info@inmourning.net','Progressive Melodic Death Metal','Sweden',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(38,'Alcest','+33 4 91 12 34 56','alcest.band@gmail.com','Blackgaze','France',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(39,'Acid Bath','+1 504 555 0122','acidbath@rotten.com','Sludge Metal','USA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(40,'Meshuggah','+46 90 123 45 67','meshuggah@management.se','Extreme Progressive Metal','Sweden',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(41,'Derby Motoretas Burrito Kachimba','+34 954 12 34 56','kinkidelia@derbymotoretas.com','Psychedelic Rock','Spain',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(42,'Limp Bizkit','+1 904 555 0100','bizkit.mgmt@gmail.com','Nu Metal','USA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(43,'Slaughter to Prevail','+7 495 123 45 67','alex.terrible@stp.com','Deathcore','Russia',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(44,'Leprous','+47 35 55 66 77','management@leprous.net','Progressive Rock','Norway',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(45,'Poppy','+1 617 555 0188','poppy@sumerianrecords.com','Avant-pop / Metal','USA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(46,'Lamb of God','+1 804 555 0111','log@epicrecords.com','Groove Metal','USA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(47,'Knocked Loose','+1 502 555 0122','knockedloose@purenoise.net','Hardcore Punk','USA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(48,'Bambie Thug','+353 1 800 1234','bambie@ouijapop.com','Ouija-pop','Ireland',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(49,'Ghost','+46 58 123 45 67','tobias.forge@lomavista.com','Occult Rock','Sweden',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(50,'Batushka','+48 85 123 45 67','batushkaband@gmail.com','Black Metal','Poland',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(51,'Bring Me The Horizon','+44 114 555 0199','bmth@rawpower-mgmt.com','Alternative Metal','UK',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(52,'Three Days Grace','+1 416 555 0177','info@threedaysgrace.com','Alternative Rock','Canada',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(53,'Iron Maiden','+44 20 8946 0000','phantom@ironmaiden.com','Heavy Metal','UK',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `artists` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `camping_types`
--

DROP TABLE IF EXISTS `camping_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `camping_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `stock_total` int(11) NOT NULL,
  `stock_available` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `camping_types`
--

LOCK TABLES `camping_types` WRITE;
/*!40000 ALTER TABLE `camping_types` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `camping_types` VALUES
(1,'Sombra de Yggdrasil (Camping Sombra)',30.00,500,500),
(2,'Morada de Freya (Glamping de Lujo)',150.00,50,48),
(3,'Drakkar sobre Ruedas (Zona Caravanas)',80.00,100,100);
/*!40000 ALTER TABLE `camping_types` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `campings`
--

DROP TABLE IF EXISTS `campings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `campings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `document_type` enum('DNI','NIE','PASSPORT') NOT NULL,
  `document_number` varchar(20) NOT NULL,
  `birth_date` date NOT NULL,
  `qr_code` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','USED','CANCELLED') DEFAULT 'ACTIVE',
  `camping_type_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `qr_code` (`qr_code`),
  KEY `camping_type_id` (`camping_type_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `1` FOREIGN KEY (`camping_type_id`) REFERENCES `camping_types` (`id`),
  CONSTRAINT `2` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campings`
--

LOCK TABLES `campings` WRITE;
/*!40000 ALTER TABLE `campings` DISABLE KEYS */;
set autocommit=0;
/*!40000 ALTER TABLE `campings` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_date` timestamp NULL DEFAULT current_timestamp(),
  `total_price` decimal(10,2) NOT NULL,
  `status` enum('PENDING','PAID','CANCELLED') DEFAULT 'PENDING',
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
set autocommit=0;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `performances`
--

DROP TABLE IF EXISTS `performances`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `performances` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `artist_id` bigint(20) NOT NULL,
  `stage_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `artist_id` (`artist_id`),
  KEY `stage_id` (`stage_id`),
  CONSTRAINT `1` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`),
  CONSTRAINT `2` FOREIGN KEY (`stage_id`) REFERENCES `stages` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performances`
--

LOCK TABLES `performances` WRITE;
/*!40000 ALTER TABLE `performances` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `performances` VALUES
(1,'2025-08-13 19:00:00','2025-08-13 20:00:00',52,1),
(2,'2025-08-13 20:30:00','2025-08-13 21:45:00',13,1),
(3,'2025-08-13 22:30:00','2025-08-14 00:30:00',11,1),
(4,'2025-08-14 01:15:00','2025-08-14 02:30:00',42,1),
(5,'2025-08-13 19:30:00','2025-08-13 20:30:00',44,4),
(6,'2025-08-13 21:00:00','2025-08-13 22:15:00',23,4),
(7,'2025-08-13 23:00:00','2025-08-14 00:30:00',14,4),
(8,'2025-08-14 01:00:00','2025-08-14 02:15:00',24,4),
(9,'2025-08-13 20:00:00','2025-08-13 21:00:00',43,2),
(10,'2025-08-13 21:30:00','2025-08-13 22:45:00',8,2),
(11,'2025-08-13 23:30:00','2025-08-14 01:00:00',6,2),
(12,'2025-08-14 01:30:00','2025-08-14 02:45:00',50,2),
(13,'2025-08-14 19:00:00','2025-08-14 20:00:00',19,1),
(14,'2025-08-14 20:30:00','2025-08-14 21:45:00',18,1),
(15,'2025-08-14 22:30:00','2025-08-15 00:30:00',53,1),
(16,'2025-08-15 01:15:00','2025-08-15 02:30:00',30,1),
(17,'2025-08-14 19:30:00','2025-08-14 20:30:00',12,4),
(18,'2025-08-14 21:00:00','2025-08-14 22:15:00',1,4),
(19,'2025-08-14 23:00:00','2025-08-15 00:30:00',9,4),
(20,'2025-08-15 01:00:00','2025-08-15 02:15:00',40,4),
(21,'2025-08-14 20:00:00','2025-08-14 21:00:00',7,2),
(22,'2025-08-14 21:30:00','2025-08-14 22:45:00',15,2),
(23,'2025-08-14 23:30:00','2025-08-15 01:00:00',46,2),
(24,'2025-08-15 01:30:00','2025-08-15 02:45:00',47,2),
(25,'2025-08-15 19:00:00','2025-08-15 20:00:00',51,1),
(26,'2025-08-15 20:30:00','2025-08-15 21:45:00',49,1),
(27,'2025-08-15 22:30:00','2025-08-16 00:30:00',3,1),
(28,'2025-08-16 01:15:00','2025-08-16 02:30:00',35,1),
(29,'2025-08-15 19:30:00','2025-08-15 20:30:00',32,4),
(30,'2025-08-15 21:00:00','2025-08-15 22:15:00',10,4),
(31,'2025-08-15 23:00:00','2025-08-16 00:30:00',5,4),
(32,'2025-08-16 01:00:00','2025-08-16 02:15:00',34,4),
(33,'2025-08-15 19:00:00','2025-08-15 20:00:00',41,3),
(34,'2025-08-15 20:30:00','2025-08-15 21:30:00',20,3),
(35,'2025-08-15 22:00:00','2025-08-15 23:30:00',21,3),
(36,'2025-08-16 00:00:00','2025-08-16 01:15:00',4,3),
(37,'2025-08-16 19:00:00','2025-08-16 20:15:00',29,1),
(38,'2025-08-16 21:00:00','2025-08-16 22:30:00',28,1),
(39,'2025-08-16 23:30:00','2025-08-17 01:30:00',26,1),
(40,'2025-08-16 19:30:00','2025-08-16 20:30:00',31,4),
(41,'2025-08-16 21:00:00','2025-08-16 22:15:00',16,4),
(42,'2025-08-16 23:00:00','2025-08-17 00:30:00',25,4),
(43,'2025-08-16 20:00:00','2025-08-16 21:00:00',48,2),
(44,'2025-08-16 21:30:00','2025-08-16 22:30:00',45,2),
(45,'2025-08-16 23:00:00','2025-08-17 00:15:00',2,2),
(46,'2025-08-17 01:00:00','2025-08-17 02:15:00',27,2),
(47,'2025-08-16 19:00:00','2025-08-16 20:00:00',22,3),
(48,'2025-08-16 20:30:00','2025-08-16 21:30:00',37,3),
(49,'2025-08-16 22:00:00','2025-08-16 23:15:00',38,3),
(50,'2025-08-16 23:45:00','2025-08-17 01:00:00',17,3),
(51,'2025-08-17 01:30:00','2025-08-17 02:30:00',39,3),
(52,'2025-08-16 18:00:00','2025-08-16 19:00:00',33,3),
(53,'2025-08-16 17:00:00','2025-08-16 18:00:00',36,3);
/*!40000 ALTER TABLE `performances` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `roles` VALUES
(1,'ADMIN'),
(2,'MANAGER'),
(3,'USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `sponsor_stage`
--

DROP TABLE IF EXISTS `sponsor_stage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `sponsor_stage` (
  `sponsor_id` bigint(20) NOT NULL,
  `stage_id` bigint(20) NOT NULL,
  PRIMARY KEY (`sponsor_id`,`stage_id`),
  KEY `stage_id` (`stage_id`),
  CONSTRAINT `1` FOREIGN KEY (`sponsor_id`) REFERENCES `sponsors` (`id`),
  CONSTRAINT `2` FOREIGN KEY (`stage_id`) REFERENCES `stages` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsor_stage`
--

LOCK TABLES `sponsor_stage` WRITE;
/*!40000 ALTER TABLE `sponsor_stage` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `sponsor_stage` VALUES
(1,1),
(4,1),
(2,2),
(6,2),
(8,3),
(3,4);
/*!40000 ALTER TABLE `sponsor_stage` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `sponsors`
--

DROP TABLE IF EXISTS `sponsors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `sponsors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `contribution` decimal(10,2) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsors`
--

LOCK TABLES `sponsors` WRITE;
/*!40000 ALTER TABLE `sponsors` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `sponsors` VALUES
(1,'Cruzcampo','+34 954 979 000','sponsorship@heinekenespana.es',60000.00,NULL),
(2,'Thomann Music','+49 9546 9223','export@thomann.de',25000.00,NULL),
(3,'EMP Mailorder','+34 911 875 528','marketing@emp-online.es',20000.00,NULL),
(4,'Monster Energy','+1 800 426 737','info@monsterenergy.com',45000.00,NULL),
(5,'Jägermeister','+49 5331 810','espana@jaegermeister.de',30000.00,NULL),
(6,'Marshall Amplification','+44 1908 375411','sponsorship@marshall.com',15000.00,NULL),
(7,'Jack Daniels','+1 888 551 5225','events@jackdaniels.com',28000.00,NULL),
(8,'Estrella Galicia','+34 981 901 906','patrocinios@estrellagalicia.es',35000.00,NULL),
(9,'Vans España','+34 932 203 100','marketing@vans.es',18000.00,NULL),
(10,'Fnac España','+34 902 100 632','comunicacion@fnac.es',12000.00,NULL);
/*!40000 ALTER TABLE `sponsors` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `stages`
--

DROP TABLE IF EXISTS `stages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `stages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `capacity` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stages`
--

LOCK TABLES `stages` WRITE;
/*!40000 ALTER TABLE `stages` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `stages` VALUES
(1,'Asgard del Sur',15000),
(2,'Fenrir del Al-Ándalus',8000),
(3,'Drakkar de Guadalquivir',5000),
(4,'Valhalla de Triana',10000);
/*!40000 ALTER TABLE `stages` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `ticket_types`
--

DROP TABLE IF EXISTS `ticket_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `stock_total` int(11) NOT NULL,
  `stock_available` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_types`
--

LOCK TABLES `ticket_types` WRITE;
/*!40000 ALTER TABLE `ticket_types` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `ticket_types` VALUES
(1,'Senda del Guerrero (Abono General)',95.00,5000,4999),
(2,'Banquete en el Valhalla (Experiencia VIP)',250.00,200,196),
(3,'Miércoles - Entrada de Día',45.00,1000,1000),
(4,'Jueves - Entrada de Día',45.00,1000,1000),
(5,'Viernes - Entrada de Día',55.00,1000,1000),
(6,'Sábado - Entrada de Día',60.00,1000,1000);
/*!40000 ALTER TABLE `ticket_types` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `document_type` enum('DNI','NIE','PASSPORT') NOT NULL,
  `document_number` varchar(20) NOT NULL,
  `birth_date` date NOT NULL,
  `qr_code` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','USED','CANCELLED') DEFAULT 'ACTIVE',
  `ticket_type_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `qr_code` (`qr_code`),
  KEY `ticket_type_id` (`ticket_type_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `1` FOREIGN KEY (`ticket_type_id`) REFERENCES `ticket_types` (`id`),
  CONSTRAINT `2` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
set autocommit=0;
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `user_role` VALUES
(1,1),
(2,2),
(3,3),
(4,3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` tinyint(1) DEFAULT 1,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `birth_date` date NOT NULL,
  `phone` varchar(30) NOT NULL,
  `created_date` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `users` VALUES
(1,'garik@email.com','$2a$12$8LssFTKG6GY.pl7Xd.K8DOHFHWwUUm1wOzQ5L4LT9uoIB2XbXYw9a',1,'Garik','Asatryan','1996-12-30','666666666','2026-01-07 12:55:42'),
(2,'paula@email.com','$2a$12$jKTxYa0fSqWzb2UrhHxR8uDn5/ArJrh4VQ1zNxJHCU7.LsZmEUE/.',1,'Paula','Martín','1995-08-24','777777777','2026-01-07 12:55:42'),
(3,'nacho@email.com','$2a$12$hE8vq6Ng0aKvV60dpP8gRecFR5fT5G7mTjP61ZnnjIOAa2n0mbDjC',1,'Nacho','Muñoz','1996-01-27','676767676','2026-01-07 12:55:42'),
(4,'extremaydura@email.com','$2a$12$hE8vq6Ng0aKvV60dpP8gRecFR5fT5G7mTjP61ZnnjIOAa2n0mbDjC',1,'Paula','Martín','1995-08-24','666666666','2026-01-07 21:12:30');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `verification_tokens`
--

DROP TABLE IF EXISTS `verification_tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `verification_tokens` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `expiry_date` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token` (`token`),
  KEY `fk_token_user` (`user_id`),
  CONSTRAINT `fk_token_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verification_tokens`
--

LOCK TABLES `verification_tokens` WRITE;
/*!40000 ALTER TABLE `verification_tokens` DISABLE KEYS */;
set autocommit=0;
/*!40000 ALTER TABLE `verification_tokens` ENABLE KEYS */;
UNLOCK TABLES;
commit;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2026-01-08 10:48:13
