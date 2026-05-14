/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-12.2.2-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: valkyria
-- ------------------------------------------------------
-- Server version	12.2.2-MariaDB-ubu2404

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
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist_images`
--

SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES `artist_images` WRITE;
/*!40000 ALTER TABLE `artist_images` DISABLE KEYS */;
INSERT INTO `artist_images` VALUES
(1,'08879063-dc9f-42aa-bb1f-c48ccdc4a6a5',1),
(2,'ae82aee5-7bc7-4a13-b250-6c2f37a62e12',2),
(3,'75693dab-e7c2-4892-8ddc-5925d1ba741c',3),
(4,'4213b8e6-7227-46ba-8b91-1db91c29bb0b',4),
(5,'2b37fd16-439b-43f5-b8be-5876010ef980',5),
(6,'3e07fa67-a574-493b-ba78-35d004e52943',6),
(7,'809949dd-1946-4d43-a94e-53d1d752ebbd',7),
(8,'050c7d4f-2057-42ad-a041-113604d14a25',9),
(9,'bb3284fc-6863-415f-be76-d485335269a6',8),
(10,'b60c3313-bbbb-42bd-b1ec-4e78843cfc2f',10),
(11,'738cf030-e1c0-4ad1-a0ea-a355eedfaa39',11),
(12,'64eb760d-5715-4be6-bc5e-9330aaf66229',12),
(13,'0ff9c647-774a-435b-9f87-3e5d882955a9',13),
(14,'352e5e55-53ee-47fa-810c-70a6fa0ac340',14),
(15,'1c748de7-f998-4601-8683-714ab3be19d8',15),
(16,'dfd51ed4-901f-4286-abf0-5ca71e3acd4b',16),
(17,'84295655-a8d9-45c3-8eb0-2f4f41dc6353',17),
(18,'1244bfed-b37a-4ccc-bbaf-e4084c9e1b4e',18),
(19,'454af6b4-c0c0-4234-ab61-51b338bc6f68',19),
(20,'80863672-05f8-4195-9788-4efbcce2f160',20),
(21,'c940444f-b8df-498f-a8c2-6c3ce978e44d',21),
(22,'e3e39b06-eb65-4439-9ce2-361af137180f',22),
(23,'0c10b39a-7e0c-4e74-8001-ca1bf22709cd',23),
(24,'b767479c-954a-4fd0-ba69-97638454f205',24),
(25,'d25b5921-1fac-4c05-a258-10dba9149929',25),
(26,'def97002-ebec-4056-874d-760e98746b18',26),
(27,'f6dabbc7-eec0-4cdb-800a-fcb63150cb15',27),
(28,'d6c7d233-c208-4c6a-aa9f-99df74cd7089',28),
(29,'d3b1f04f-1062-4b29-873e-a88b36683c34',29),
(30,'38572cc9-9472-418c-b6a5-9e9c8cc06000',30),
(31,'7cfe774e-5a54-49a6-82fa-b12b0576adb4',31),
(32,'c3ba5459-5ff1-4017-b55b-a049f1c4ad9c',32),
(33,'207b481b-cf98-4992-9d3f-e91bfd8f4b10',33),
(34,'9beb2ba2-b354-4e28-8c45-67c2a41b9211',34),
(35,'0481eb30-1f79-4d1c-bf04-e127cf2f54d0',35),
(36,'c72e4bb2-02c4-448c-ab2b-ee5d9590e4e6',36),
(37,'0cd4ced9-7a1c-4906-b781-5b0e34201ce8',37),
(38,'699e4b58-a0a5-470b-b94e-0729f60a617c',38),
(39,'66e3fd1f-e3ad-4990-9c32-6a977237652e',39);
/*!40000 ALTER TABLE `artist_images` ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=@OLD_AUTOCOMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artists`
--

SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES `artists` WRITE;
/*!40000 ALTER TABLE `artists` DISABLE KEYS */;
INSERT INTO `artists` VALUES
(1,'Gojira','+33 1 42 67 00 00','contact@gojira-music.com','Progressive Death Metal','France','Taking their name from the original Japanese pronunciation of Godzilla, French heavy metal quartet Gojira have gone from the utmost obscurity in the first half of their career to widespread global recognition in the second. Combining elements of thrash, death, math, groove, progressive, and post-metal with philosophical and environment-themed lyrics, the band found mainstream favor in 2012 with the release of their fifth long-player, L\'Enfant Sauvage, and doubled-down on that success with 2016\'s Grammy-nominated Magma and 2021\'s hard-hitting and versatile Fortitude. In 2024, Gojira became the first heavy metal act to perform at an Olympic opening ceremony. ','8f460969-b0b5-4a7d-80f0-8c7a52d23721','https://www.gojira-music.com/','https://www.instagram.com/gojiraofficial','https://www.tiktok.com/@gojiraband','https://www.youtube.com/gojira','https://tidal.com/artist/3883303','https://open.spotify.com/embed/track/2pZsQqXFgcY03vRyZxSQhU?utm_source=generator'),
(2,'Loathe','+44 20 7946 0000','info@loathe.uk','Metalcore','UK','Loathe (sometimes stylized as LOATHE) are an English nu metalcore band from Liverpool. The band\'s music makes use of elements of metalcore and nu metal, while also incorporating more experimental aspects from genres like shoegaze, progressive metal and industrial music. Formed in 2014, the group consists of lead vocalist Kadeem France, guitarist and second vocalist Erik Bickerstaffe, drummer Sean Radcliffe and bassist Feisal El-Khazragi. Loathe have been nominated for Heavy Music Awards best UK breakthrough band and Metal Hammer Golden Gods Awards best new band in 2018. In a 2020 poll by Revolver magazine, they were voted the third most likely contemporary band to break through into the mainstream.','c4dae7d0-a787-4992-bfac-32918c423e87','https://loatheasone.co.uk/','https://www.instagram.com/loatheasone','','https://www.youtube.com/watch?v=pRzlroDyvXo','https://tidal.com/artist/3712384','https://open.spotify.com/embed/track/4OmlsAT8r4q9vPFBvfYgyZ?utm_source=generator'),
(3,'Rammstein','+49 30 2091 0000','management@rammstein.de','Industrial Metal','Germany','Over the course of three decades, Rammstein have shaped heavy music with their unique blend of industrial sound, provocative aesthetics, and spectacular live shows. Formed in Berlin in 1994, the line-up has remained unchanged: Till Lindemann, Richard Z. Kruspe, Paul Landers, Oliver Riedel, Christoph Schneider, and Flake Lorenz. Eight studio albums, including two German No.1 singles (“Pussy”, “Deutschland\") and their most recent release “Zeit\" (2022), have earned them multiple gold and platinum awards worldwide as well as Grammy nominations. Known for uncompromising creativity, iconic videos, and incendiary stage productions, Rammstein continue to defy convention and sell out stadiums across the globe – writing one of the most influential and unique chapters in rock history.','192743ce-c57e-4dea-b762-fc14437ac7bc','https://www.rammstein.de/en/','https://www.instagram.com/rammsteinofficial/','https://www.tiktok.com/@rammstein','https://www.youtube.com/channel/UCYp3rk70ACGXQ4gFAiMr1SQ','https://tidal.com/artist/24972','https://open.spotify.com/embed/track/3gVhsZtseYtY1fMuyYq06F?utm_source=generator'),
(4,'Faetooth','+1 213 555 0198','faetoothband@gmail.com','Doom Metal','USA','Emerging from Los Angeles, Faetooth has quickly carved out a unique space with their self-described \"fairy doom.\" The trio blends the crushing, sludge-heavy weight of doom metal with ethereal, shoegaze-inspired melodies, creating a sound that feels both ancient and refreshingly modern. Characterized by their haunting vocal harmonies and tectonic-shifting riffs, the band explores themes of folklore, nature, and introspection. With their debut album Remnants of the Vessel, Faetooth proved they are masters of contrast—balancing delicate, atmospheric beauty with a raw, swampy grit. They represent a new generation of heavy music, where the mystical meets the massive, cementing their place as one of the most captivating and innovative bands in the underground scene today.','4ee92796-7185-4bc6-826c-ef80e151f9c6','','https://www.instagram.com/faetooth/','https://www.tiktok.com/@faetooth','https://www.youtube.com/@Faetooth','https://tidal.com/artist/17871929','https://open.spotify.com/embed/track/7hwngx8lZypzVtHeKAxi4F?utm_source=generator'),
(5,'Deftones','+1 916 555 0123','contact@deftones.com','Alternative Metal','USA','Deftones have always defined boundless creativity in the music space. Across nine studio albums, they have carved out an unmistakable sonic identity — ferocious yet dreamlike, while making space for constant refinement and surprise. Now, decades on from the groove-forward sound of their era-defining debut, Adrenaline, and following a long line of masterpieces including 2000’s White Pony, 2010’s Diamond Eyes and 2020’s Ohms — an album that earned them their second and third Grammy nominations — they return with one of the most focused statements of their career: private music. Joining the band’s creative core of Chino Moreno, Stephen Carpenter, Abe Cunningham and Frank Delgado (as well as touring bassist Fred Sablan, who appears on the album) is producer Nick Raskulinecz, who previously worked on Diamond Eyes and 2012’s riveting Koi No Yokan. The result is a lean, masterfully paced 11-song set that plays like a new Deftones benchmark.','eee6a9ac-f592-46db-abbc-4bcdab5b8cf8','https://www.deftones.com/','https://www.instagram.com/deftones/','https://www.tiktok.com/@deftones','https://www.youtube.com/@deftones','https://tidal.com/artist/15349','https://open.spotify.com/embed/track/51c94ac31swyDQj9B3Lzs3?utm_source=generator'),
(6,'Behemoth','+48 22 654 00 00','behemoth.mgmt@new-east.pl','Blackened Death Metal','Poland','Resting on their laurels was never in consideration as far as Behemoth were concerned. The Shit Ov God is emblematic of that. Here\'s a band that, 34 years in, is releasing its most inflammatory and extreme record to date. 8 songs that go into the fathoms of humanity, divinity and what defiance means in an age where individuality is prized but everyone is clinging to their saviours. Musically, politically, or otherwise. The title of the album is in line with those sensibilities. Intentionally polarizing, founder and frontman Nergal, says of the title \" We chose this provocative title deliberately, rejecting subtlety in favor of a direct and polarizing statement. It\'s a defiant plunge into the depths, daring to seek the absolute even in the gutter.\"','64165fa2-f282-4f86-bdfe-86fcc4c37567','https://www.behemoth.pl/','https://www.instagram.com/behemothofficial/','https://www.tiktok.com/@behemothofficial','https://www.youtube.com/user/Behemothofficial','https://tidal.com/artist/24058','https://open.spotify.com/embed/track/2FsDybzWgntIa8M3Y7AenT?utm_source=generator'),
(7,'Fit For An Autopsy','+1 201 555 0144','ffaa@metal-management.com','Deathcore','USA','The crushing music of Fit For An Autopsy is for any fan of extreme metal, its sound and fury is absolutely unflinching in purpose. The band expertly blends excessive, force-fueled death metal with atmospheric groove and impassioned personal diatribes. The Nothing That Is, Fit For An Autopsy’s seventh and latest album (third for Nuclear Blast), bears no sign whatsoever of commercial concession or rehashed past glories. Instead, the ten-song album finds the band exploring moodier textures and deeper emotions that add new power and dynamics to their already brutal and complex music, while also completely tuning out the ambient noise of passing musical trends and the expectations of the outside world.','e1fe03de-567a-4d7e-9190-1d76baff91e6','https://fitforanautopsy.co/','https://www.instagram.com/fitforanautopsy/','https://www.tiktok.com/@fitforanautopsyofficial','https://www.youtube.com/@FitForAnAutopsy','https://tidal.com/artist/3985809','https://open.spotify.com/embed/track/79yvAWlqVukArNOxtyyKCI?utm_source=generator'),
(8,'Lorna Shore','+1 732 555 0166','lornashore@touring.com','Deathcore','USA','Call them heroes or hell-bringers, it doesn’t matter to Lorna Shore. On their fifth album—I Feel The Everblack Festering Within Me—the New Jersey quintet puts every metal subgenre on notice, forcing listeners to wonder what took the genre so long to evolve. Andrew O’Connor’s orchestral arrangements provide an epic, cinematic backdrop for the band’s stentorian power. Adam De Micco shifts seamlessly from light-speed black metal to \'70s-style guitar phrasing, while Will Ramos proves himself the heir to Mike Patton’s vocal madness. Driven by the propulsive terror of Yager and Archey, the album transcends deathcore, leaning into grandiose prog-rock territory through sheer extremity. By torching the rulebook to divine their own truth, Lorna Shore has created a physical onslaught that demands other genres step up their game.','b10e2abb-4812-48bb-a27d-98141e6fcd5c','https://lornashoreband.com/','https://www.instagram.com/lornashore','https://www.tiktok.com/@lornashoreofficial','https://www.youtube.com/@LornaShore','https://tidal.com/artist/5269283','https://open.spotify.com/embed/track/0O26gtfjuscAOnQobjNPPL?utm_source=generator'),
(9,'Opeth','+46 8 123 456 00','management@opeth.com','Progressive Metal','Sweden','More than three decades into their career, Opeth have trained their admirers to expect the unexpected. But even by their own standards, the Swedish progressive titans have conjured something extraordinary this time around. The band’s 14th studio exploration, The Last Will & Testament, is the darkest and heaviest record they have made in decades, it is also the most fearlessly progressive. A concept album recounting the reading of one recently deceased man’s will to an audience of his surviving family members, it brims with haunting melodrama, shocking revelations and some of the wildest and most unpredictable music that songwriter/frontman Mikael Åkerfeldt has ever written. Proud adherents to a progressive ethos, Opeth have never repeated themselves, and The Last Will & Testament is every bit as revelatory and adventurous as its 13 predecessors. But one thing is undeniable: Mikael Akerfeldt’s peerless death metal growls are back, for the first time since Watershed in 2008. After three decades of dazzling the world, Opeth have made their most daring creative leap yet. The Last Will & Testament is a progressive and dramatic triumph, and yet more proof that expecting the unexpected is the only way forward for fans of Sweden’s finest.','23df5770-e321-4eec-b0f8-2e00a57cff51','https://opeth.com/','https://www.instagram.com/officialopeth','https://www.tiktok.com/@opethofficial','https://www.youtube.com/@opeth','https://tidal.com/artist/37348','https://open.spotify.com/embed/track/0ppdt8zRZOHIKh4ZDB0Zp9?utm_source=generator'),
(10,'Jinjer','+380 44 234 5678','jinjer.booking@gmail.com','Progressive Metal','Ukraine','A versatile, progressive groove metal unit based out of Ukraine, Jinjer have found success both in their Eastern European homeland and abroad with their punitive blend of post-hardcore and death/progressive/nu-metal. Drawing from a wide array of influences, including R&B, soul, hip-hop, and the full spectrum of heavy metal, the band formed in 2009 and features a lineup consisting of Tatiana Shmailyuk (vocals), Roman Ibramkhalilov (guitar), Eugene Abdiukhanov (bass), and Vladislav Ulasevich (drums). Jinjer issued a pair of EPs, 2009\'s OIMACTTA and 2012\'s Inhale, Do Not Breathe, before breaking big at home with the release of a full-length version of the latter album in 2013. The group\'s sophomore long-player, 2014\'s Cloud Factory, caught the attention of heavy metal institution Napalm, which signed Jinjer and released their third full-length effort, King of Everything, in 2016. In early 2019, the band returned with the Micro EP, again on Napalm. By the end of the year, its natural counterpoint, Macro, arrived.','227b7839-4435-4cc2-b8a0-746f8c175b81','https://jinjer-metal.com/','https://www.instagram.com/jinjer_official/','https://www.tiktok.com/@jinjer_official','https://www.youtube.com/watch?v=SQNtGoM3FVU','https://tidal.com/artist/4930355','https://open.spotify.com/embed/track/5t8NXa2fugcTPsTfhVILmS?utm_source=generator'),
(11,'Metallica','+1 415 555 0101','hq@metallica.com','Thrash Metal','USA','Metallica formed in 1981 by drummer Lars Ulrich and guitarist and vocalist James Hetfield and has become one of the most influential and commercially successful rock bands in history, having sold 120 million albums worldwide and generating more than 15 billion streams while playing to millions of fans on literally all seven continents. They have scored several multi-platinum albums, including 1991’s Metallica (commonly referred to as The Black Album), with sales of nearly 18 million copies in the United States alone, making it the best-selling album in the history of Soundscan. Metallica has also garnered numerous awards and accolades, including nine Grammy Awards, two American Music Awards, and multiple MTV Video Music Awards, and were inducted into the Rock and Roll Hall of Fame and Museum in 2009. In December 2013, Metallica made history when they performed a rare concert in Antarctica, becoming the first act to ever play all seven continents all within a year, and earning themselves a spot in the Guinness Book of World Records. Metallica’s twelfth studio album was released on April 14, 2023 on Metallica’s own Blackened Recordings record label, and the band is currently on the M72 Tour—a 2-year, continent spanning tour with two nights in each market and no repeat sets.','ba0bdf91-4dd2-4074-898d-2ab104edde7a','https://www.metallica.com/','https://www.instagram.com/metallica/','https://www.tiktok.com/@metallica','https://www.youtube.com/@metallica','https://tidal.com/artist/8405','https://open.spotify.com/embed/track/2MuWTIM3b0YEAskbeeFE1i?utm_source=generator'),
(12,'Mastodon','+1 404 555 0188','info@mastodonrocks.com','Sludge Metal','USA','Mastodon is an American heavy metal band from Atlanta, Georgia. Formed in 2000, the band\'s lineup of Troy Sanders (bass/vocals), Brent Hinds (lead guitar/vocals), Bill Kelliher (rhythm guitar/backing vocals), and Brann Dailor (drums/vocals) remained unchanged for 24 years, until Hinds\'s departure in March 2025. Mastodon has released eight studio albums, as well as a number of other releases. Atlanta\'s Mastodon are one of the most original and influential American metal bands to appear in the 21st century. Their wide-angle progressive approach encompasses stoner and sludge metal, punishing hardcore and metalcore, neo-psych, death metal, and more. The group\'s playing style incorporates technically complex guitar riffs, lyric hooks, long, melodic instrumental passages, and intricate, jazz-influenced drumming with syncopated time signatures. In 2016, the staff of Loudwire named them the 20th-best metal band of all time.','8e55696d-62b5-4782-9832-e695b0572c33','https://www.mastodonrocks.com/','https://www.instagram.com/mastodonrocks/','https://www.tiktok.com/@mastodonrocks','https://www.youtube.com/channel/UCR7Ls5FuT6UKTcsMkcwgCUA','https://tidal.com/artist/15346','https://open.spotify.com/embed/track/3jagGO7eHHuaD53ibehkux?utm_source=generator'),
(13,'Evanescence','+1 501 555 0155','mgmt@evanescence.com','Alternative Metal','USA','Evanescence is an American rock band founded in 1994 by singer and keyboardist Amy Lee and guitarist Ben Moody in Little Rock, Arkansas. After releasing independent EPs and a demo CD as a duo in the late 1990s, Evanescence released their debut studio album, Fallen, on Wind-up Records in 2003. Propelled by the success of hit singles like \"Bring Me to Life\" and \"My Immortal\", Fallen sold more than four million copies in the US by January 2004, garnering Evanescence two Grammy Awards out of six nominations. They released their first live album and concert DVD, Anywhere but Home, in 2004, which sold over one million copies worldwide.  Evanescence will release the 20th anniversary edition of their multi-platinum debut album Fallen on November 17th. The anniversary will see remastered tracks from the original album, previously unheard demos, and alternate versions of some of their most iconic songs. In Amy’s words, “20 years later, this album has never meant more. Fallen has been the soundtrack to first loves, epic heartbreak, self-realization, wedding days, last goodbyes, friendships, and countless other moments in so many lives…not to mention my own. I am forever humbled and grateful to be a part of it.”','21265009-0652-41a0-9d20-f84d2c1cf013','https://www.evanescence.com/','https://www.instagram.com/evanescenceofficial/','https://www.tiktok.com/@evanescence','https://www.youtube.com/channel/UCJeH7gl6PbDVV4DTldIOPOA','https://tidal.com/artist/4853','https://open.spotify.com/embed/track/0COqiPhxzoWICwFCS4eZcp?utm_source=generator'),
(14,'Tool','+1 310 555 0199','toolband@management.com','Progressive Metal','USA','Tool established themselves as one of America\'s most enduring and unpredictable acts with an ever-evolving brand of muscular but mind-altering sonics, a wry sense of humor, and a mystical aesthetic that attracted a cult-like following of devoted fans with just a handful of albums spread across decades. Their greatest breakthrough was to meld dark underground metal with the ambition of art rock, crafting multi-sectioned, layered songs as if they were classical composers. While embracing the artsy, they also paid musical homage to the relentlessly bleak visions of grindcore, death metal, and thrash. Even with their post-punk influences, they executed their music with the sound and feel of prog rock, alternating between long, detailed instrumental interludes and lyrical rants in their songs. Debuting in the early \'90s with Undertow, they were initially lumped in with the nu-metal contemporaries of the time, which made them a hit on rock radio with their sophomore effort, 1996\'s Ænima. However, they soon broke away from those associations, evolving beyond the confines of traditional song structures and song lengths, crafting epics that often clocked in past the ten-minute mark on LP head-trips Lateralus (2001) and 10,000 Days (2006). After a lengthy 13-year hiatus, they returned to the fold in 2019 with their fifth opus, the chart-topping, Grammy-nominated Fear Inoculum. In 2022, they celebrated their 30th anniversary with \"Opiate²,\" a re-recorded version of their debut single. ','da8b101e-f822-4520-9b5e-3ce37b4422b8','https://www.toolband.com','https://www.instagram.com/toolmusic/','https://www.tiktok.com/@tool','https://www.youtube.com/channel/UC1wUo-29zS7m_Jp-U_xYcFQ','https://tidal.com/artist/3850668','https://open.spotify.com/embed/track/55mJleti2WfWEFNFcBduhc?utm_source=generator'),
(15,'Whitechapel','+1 865 555 0177','whitechapel@metalblade.com','Deathcore','USA','Whitechapel is an American deathcore band from Knoxville, Tennessee. The band is named after the Whitechapel district in east London, England, where Jack the Ripper committed a series of murders. The group comprises vocalist Phil Bozeman, guitarists Ben Savage, Alex Wade, and Zach Householder, bassist Gabe Crisp and drummer Brandon Zackey. Their core lineup, with the exception of the drummer, has remained consistent since Householder replaced original guitarist Brandon Cagle in 2007. Founded in 2006 by Bozeman and Savage, the band has released nine studio albums and twenty-two music videos, and is currently signed to Metal Blade Records. Whitechapel\'s 2010 album A New Era of Corruption, sold around 10,600 copies in the United States in its first week of release and debuted at position No. 43 on the Billboard 200 chart. The band\'s self-titled fourth album was released in 2012 and debuted at No. 47 on the Billboard 200, selling roughly 9,200 copies in its first week. In 2014 the band released their fifth full-length album, Our Endless War to generally positive reviews. The album sold roughly 16,000 copies in its first week and debuted at No. 10 on the Billboard 200. They released their sixth full-length album Mark of the Blade in 2016 to greater critical acclaim, selling roughly 8,000 copies in the first week of its release. In 2019, Whitechapel released their seventh album, The Valley, which debuted at No. 143 on the Billboard 200 also to critical acclaim. Their newest album, Hymns in Dissonance, was released on March 7, 2025. ','b2add995-822b-4596-b61a-a59092deea87','https://whitechapelband.com/','https://www.instagram.com/whitechapelband/','https://www.tiktok.com/@whitechapelmusic','https://www.youtube.com/@WhitechapelTV','https://tidal.com/artist/3767580','https://open.spotify.com/embed/track/4CuCHhr5zHzEwcflWD2jUT?utm_source=generator'),
(16,'Deafheaven','+1 415 555 0122','info@deafheaven.com','Blackgaze','USA','Deafheaven is an American blackgaze band formed in 2010. Originally based in San Francisco, the group began as a two-piece with singer George Clarke and guitarist Kerry McCoy, who recorded and self-released a demo album together. Following its release, Deafheaven recruited three new members and began to tour. Before the end of 2010, the band signed to Deathwish Inc. and later released their debut album Roads to Judah, in April 2011. They popularized a unique style blending black metal, shoegaze, and post-rock, among other influences, later called blackgaze by reviewers. Deafheaven\'s second album, Sunbather, was released in 2013 to wide critical acclaim, becoming one of the best reviewed albums of the year in the US. In 2015 the band followed up with New Bermuda and in 2018 with Ordinary Corrupt Human Love. Their 2021 album, Infinite Granite, drastically reduced the presence of screamed vocals. In 2025, they released Lonely People with Power, which featured a heavier and more aggressive sound.','7f7650a0-db4e-4c02-92dc-964e70cf2135','https://deafheaven.com/','https://www.instagram.com/deafheavenband/','https://www.tiktok.com/@deafheavenband','https://www.youtube.com/channel/UC97UMkZzchbn0uO-KFyq8ww','https://tidal.com/artist/3938220','https://open.spotify.com/embed/track/5vWfIEWXevuwFSVqgrItHC?utm_source=generator'),
(17,'Judas Priest','+44 20 7946 0555','mgmt@judaspriest.com','Heavy Metal','UK','Judas Priest are one of the most influential and long-lasting heavy metal groups of all time. Emerging at the dawn of the New Wave of British Heavy Metal, Priest combined straightforward rock & roll muscle with a more theatrically minded performance presence. This sound was made more unique by the dynamic banshee wail of Rob Halford and the vicious dual-lead guitar attack of Glenn Tipton and K.K. Downing. While issuing metal anthems like \"Breaking the Law,” “Living After Midnight,” and “You\'ve Got Another Thing Coming,\" Judas Priest set the pace for the genre from 1975 until 1985 with iconic albums like British Steel (1980), Screaming for Vengeance (1982), and Defenders of the Faith (1984), and helped lay the groundwork for speed and death metal. The group struggled after Halford\'s departure in the early \'90s but were restored to prominence in the 2000s upon his return, issuing a string of acclaimed efforts -- Angel of Retribution (2005), Nostradamus (2010), Redeemer of Souls (2014), and the U.S. and U.K. Top Five-charting Firepower (2018) -- that skillfully married melody, pageantry, and force. Judas Priest were inducted into the Rock & Roll Hall of Fame in 2022, two years before the arrival of Invincible Shield, their 19th studio LP. ','9951fc3b-545f-43a5-abd6-c655092712e4','https://judaspriest.com/home/','https://instagram.com/judaspriest','https://www.tiktok.com/@judaspriest','https://www.youtube.com/@judaspriest','https://tidal.com/artist/701','https://open.spotify.com/embed/track/0L7zm6afBEtrNKo6C6Gj08?utm_source=generator'),
(18,'Pantera','+1 817 555 0199','info@pantera.com','Groove Metal','USA','One of the preeminent metal bands of the \'90s, Texan powerhouse Pantera put to rest any and all remnants of the \'80s metal scene, almost single-handedly demolishing any notion that hair metal, speed metal, power metal, et al., were anything but passé. Loathe to admit it, the Texas band had in fact been one of those \'80s metal bands, releasing fairly unsuccessful (and later disowned) glam-inspired music throughout much of the decade. The about-face came in 1986 with the addition of vocalist Phil Anselmo, who joined the classic, core lineup of bassist Rex Brown, drummer Vinnie Paul, and guitarist Dimebag Darrell. After the release of 1988\'s Power Metal, the band pushed their sound to a new extreme with their major-label debut, Cowboys from Hell (1990). Pantera\'s mainstream breakthrough came next with Vulgar Display of Power (1992), their second major-label album, which thrust the band to the forefront of the metal scene, alongside such veteran bands as metallica, Megadeth, Slayer, and Anthrax, as well as fellow up-and-comers Sepultura and White Zombie. By the time Pantera unleashed Far Beyond Driven (1994), after two long years of touring, they were the most popular metal band in the land: the new album debuted atop the Billboard Top 200 as its lead single, \"I\'m Broken,\" was getting massive airplay. ','8eb7dbc8-3d44-4898-a9c0-90e9668da859','https://pantera.com/','https://www.instagram.com/panteraofficial','https://www.tiktok.com/@pantera','https://www.youtube.com/channel/UChTDORxN3YPmasEurM6kRoA','https://tidal.com/artist/6373','https://open.spotify.com/embed/track/7fcfNW0XxTWlwVlftzfDOR?utm_source=generator'),
(19,'Danheim','+45 70 10 20 30','contact@danheimmusic.com','Neofolk','Denmark','Danheim is a Nordic folk/Viking inspired project from the Copenhagen-based Danish producer Reidar Schæfer Olsen, a man with 10 years of experience in electronic and ambient music – focusing on Nordic Folk and Viking age inspired music with a certain Nordic authenticity or mood. Danheim’s music is often composed of ideas and stories based on the darker side of the Viking period, inspired or consisting of Nordic Mythology, old Danish folklore, and a vivid imagination. Danheim hit 1 billion streams across streaming platforms in 2022, has 585.000 subscribers on YouTube, with music featured in multiple seasons of the popular TV show Vikings. Singer-songwriter Reidar Schæfer Olsen, (born in 1985, Brøndby, Denmark), transitioned to focus on music in the early 2000s and has been composing music almost continuously ever since, alongside doing radio shows and starting the indie label Fimbul Records. With a passion for songwriting running through his veins from an early age, Reidar Olsen’s Viking-inspired music and dark melodies have broadened the Nordic folk genre and inspired millions of listeners around the world, bringing them back in time with a blend of his imagination and creativity.','7b4d8989-fc9d-4cd1-9f18-4a2de19462be','https://danheimmusic.com/','','','https://www.youtube.com/@Danheim','https://tidal.com/artist/8412655','https://open.spotify.com/embed/track/1L6qOVwLb1QIdcb9dtFPJ2?utm_source=generator'),
(20,'Wardruna','+47 55 12 34 56','info@wardruna.com','Nordic Folk','Norway','From the deep woods now emerges Birna, Wardruna’s sixth studio album. Through his never resting dialogue with nature, main composer Einar Selvik has been searching for the voice of the bear, our lost sister of the forest. Resulting in this upcoming release, scheduled for January 24, 2025 through Sony Music and By Norse. Birna – the she-bear in Old Norse – is a work of art dedicated to the warden of the forest, nature’s caretaker, and her battles here on earth. Slowly driven out of her habitat by modern day societies, she has entered a stage of permanent hibernation. As a result, the forest is gradually dying, longing for its pulse and heart – its shepherd. Birna calls for her return.“Where the previous album Kvitravn was a step conceptually from the past to the present, Birna even more so seeks to address the here and now and the way forward,” states composer Einar Selvik. From the Runaljod trilogy (2009, 2013, 2016), exploring old Norse myths, traditions, and language through the 24 proto-Norse runes, the sound of Wardruna evolved and blended into the stripped down, acoustic compositions on Skald (2018). Kvitravn (White-Raven) (2021) explores Northern sorcery, spirit-animals, animism, and the act of creation – the ever-interchanging prolific relationship between the skaldic poet and nature itself. The record charted in 13 countries, including #1 in Canada and Austria, and #2 in Germany. ','3e174263-6f80-4f67-b1e2-036213c0ed97','https://wardruna.com/','https://www.instagram.com/wardruna/','','https://www.youtube.com/channel/UCoK2X3nnuG7ug-Kk75r5rJQ','https://tidal.com/artist/3565132','https://open.spotify.com/embed/track/0OXEH9T6QSQCrmnjdtpf4P?utm_source=generator'),
(21,'Igorrr','+33 1 40 20 50 50','igorrr.contact@gmail.com','Avant-garde Metal','France','Igorrr is the project of French composer and producer Gautier Serre, renowned for pushing extreme music far beyond traditional genre boundaries. Blending breakcore, black and death metal, baroque classical music, electronic chaos, and surreal experimentation, Igorrr’s sound is intentionally jarring yet meticulously composed. The result is music that feels both violently unhinged and strangely elegant, shifting without warning from blast beats and distorted screams to harpsichords, operatic vocals, and absurd melodic twists. Early releases like Poisson Soluble and Nostril established the project’s chaotic identity, fusing frenetic electronic rhythms with extreme metal and warped classical motifs. Hallelujah expanded the scope with richer production, baroque choirs, and a stronger sense of contrast between beauty and brutality. With Savage Sinusoid, Igorrr leaned further into metal, incorporating black metal vocals and heavier structures while retaining its surreal edge. Spirituality and Distortion pushed the project to its most ambitious scale, exploring global influences, ritualistic themes, and expansive atmospheres. Each Igorrr album feels like a carefully controlled descent into madness—unpredictable, theatrical, and unmistakably unique.','010ac405-9293-4e22-aca3-b1c9dfbe454c','https://igorrr.com/','https://instagram.com/igorrr_music','https://www.tiktok.com/@igorrr_official','https://www.youtube.com/@IgorrrOfficial','https://tidal.com/artist/3949100','https://open.spotify.com/embed/track/6DUvpC2bFViefEhZ65NGgk?utm_source=generator'),
(22,'Linkin Park','+1 310 555 0110','contact@linkinpark.com','Nu Metal','USA','LINKIN PARK is the magnetic hub of an emotional and cultural community—staggering in scope, intimate in connection, and wholly unique. Blending sonic and visual inspiration under the name Xero, later Hybrid Theory, before finally settling on LINKIN PARK, Mike Shinoda, Chester Bennington, Brad Delson, Joseph Hahn, Rob Bourdon, and Dave “Phoenix” Farrell had no idea they were about to become the biggest rock band of their generation. In 2000, they released their first full-length, Hybrid Theory. Certified Diamond, it became “the bestselling debut of the 21st century.” Seven seminal albums followed: Meteora, Collision Course, Minutes To Midnight, A Thousand Suns, LIVING THINGS, The Hunting Party, and One More Light. LINKIN PARK has received multiple GRAMMY Awards, sold over 100 million albums worldwide, and notched five #1 Billboard debuts. After the tragic loss of Bennington in 2017, the band came to a devastating halt; their future obscured by grief and unanswered questions. Friendships led the way. Mike, Brad, Phoenix, and Joe began making music together again. They met Emily Armstrong and Colin Brittain, jam sessions organically morphed into recording, and LP quietly crafted a collection of songs channeling the open-hearted spontaneity of starting over: FROM ZERO. Earning some of the highest critical praise of their career, FROM ZERO peaked at #1 in 14 countries, igniting the triumphant FROM ZERO WORLD TOUR and LINKIN PARK’s continuing artistic evolution.','41455c61-9787-4672-b850-1af5736c7ea3','https://linkinpark.com/','','https://www.tiktok.com/@linkinpark','https://www.youtube.com/channel/UCZU9T1ceaOgwfLRq7OKFU4Q','https://tidal.com/artist/14123','https://open.spotify.com/embed/track/60a0Rd6pjrkxjPbaKzXjfq?utm_source=generator'),
(23,'Imperial Triumphant','+1 718 555 0144','it@gileadmedia.net','Avant-garde Black Metal','USA','Imperial Triumphant are one of the most distinctive forces in modern extreme metal, fusing avant-garde black metal, dissonant death metal, and free-jazz chaos into a vision of New York City as a monument to excess and decay. Formed in the late 2000s, the band uses the city’s architecture, capitalism, and moral rot as both aesthetic and philosophy, translating urban dread into towering, technical brutality. Their sound is dense and suffocating—jagged riffs, unorthodox song structures, sudden tempo shifts, and improvisational brass passages collide in music that feels both ritualistic and grotesquely opulent. Their early releases, Abominamentvm and Abyssal Gods, established a murky black-metal foundation steeped in atmosphere and dissonance. Vile Luxury marked a turning point, introducing overt jazz influences and an art-deco visual identity that framed NYC as a decadent empire in decline. This concept was fully realized on Alphaville, a sprawling, chaotic album that sharpened their technicality while expanding the band’s sonic palette. With Spirit of Ecstasy, Imperial Triumphant pushed even further, incorporating swing rhythms, collaborations with jazz musicians, and moments of surreal grandeur without sacrificing heaviness. Each album acts as a chapter in a larger narrative—an escalating portrait of civilization collapsing under its own gilded weight.','189e78ed-5a0c-4325-9d55-6634f98c2344','https://www.imperial-triumphant.com/','https://www.instagram.com/imperialtriumphant/','https://www.tiktok.com/@imperialtriumphant','https://www.youtube.com/channel/UCGAewAu26qdx3IwJn8S_uww','https://tidal.com/artist/6046541','https://open.spotify.com/embed/track/2jV8ASwqzFdy1Xmdjmvt1R?utm_source=generator'),
(24,'Slipknot','+1 515 555 0166','mgmt@slipknot1.com','Nu Metal','USA','Released on June 29th, 1999, Slipknot’s self-titled debut was a smash, becoming a hit with critics and an instant classic with fans. It was, and is, a molotov cocktail signaling a revolution– embracing a creative direction that eschewed metal traditions in favor of fresh ideas. While the jaw-clenching riffs coupled with Ross Robinson’s venomous production were crucial to the album’s success, it was the care and attention to detail in the songcraft that moved the needle from “great” to “benchmark.” Not too shabby for a bunch of guys that only hoped to be heard. “We loved what we did with the first record but we didn\'t even know if there was gonna be anything after that,” recalls guitarist Jim Root. “We just wanted people to hear it. Selling out of records and shows, having successful tours– those are goals for any band. But the dream scenario in some ways, came true for us.” The infection spreads deeper and wider into 2024 and 2025, but Slipknot are not giving details as to what to expect. But it wouldn’t be Slipknot if they didn’t exceed fan expectations. “We have five OG members and lots of family in Slipknot,” recalls Clown. “We want to acknowledge what we are, where we came from and what we accomplished. We\'re just Slipknot. We\'re here to be. And we’re still walking uphill– it hasn’t leveled off. But you can definitely believe we’re already thinking about what’s next and next after that.”','dff40943-8fdc-4633-a605-8e52edfa6872','https://slipknot1.com/','https://www.instagram.com/slipknot','https://www.tiktok.com/@slipknot','https://www.youtube.com/slipknot','https://tidal.com/artist/37277','https://open.spotify.com/embed/track/61mWefnWQOLf90gepjOCb3?utm_source=generator'),
(25,'Alice in Chains','+1 206 555 0188','info@aliceinchains.com','Grunge','USA','Alice in Chains carved out a unique space in the 1990s Seattle music scene by drawing from heavy metal rather than punk. Formed in 1987, their debut album Facelift (1990) arrived before Nirvana’s Nevermind brought grunge into the mainstream. Despite their metal roots, their sound—heavy, minor-key riffs paired with dark, introspective lyrics—resonated within the alternative rock wave. Hits like \"Man in the Box\" and \"Would?\" defined their grunge presence, while \"No Excuses\" and \"I Stay Away\" showed their acoustic versatility. Following the release of the Jar of Flies EP (1994), which debuted at number one on the Billboard charts, Alice in Chains continued to build their legacy with their self-titled album (1995) and a notable MTV Unplugged performance in 1996. However, the band faced challenges after the passing of original frontman Layne Staley in 2002. The band reunited in 2005 for a benefit concert and, with new lead singer William DuVall released Black Gives Way to Blue (2009), marking their comeback. Their follow-up album The Devil Put Dinosaurs Here (2013) solidified their resurgence, and they continue to be a dominant force in rock music, having released Rainier Fog in 2018.','2a6f1120-b52d-4f4b-b6e5-1ec68824d699','https://aliceinchains.com/','https://www.instagram.com/aliceinchains/','https://www.tiktok.com/@aliceinchains','https://www.youtube.com/channel/UCK9X9JACEsonjbqaewUtICA','https://www.tiktok.com/@aliceinchains','https://open.spotify.com/embed/track/5sFDReWLrZHLFZFjHsjUTS?utm_source=generator'),
(26,'Spiritbox','+1 250 555 0133','spiritbox@palechord.com','Progressive Metalcore','Canada','Spiritbox is a Canadian metal band formed in 2017 in Victoria, British Columbia, by vocalist Courtney LaPlante and guitarist Mike Stringer after their departure from the band Iwrestledabearonce. Joined by bassist Bill Crook and drummer Zev Rose, Spiritbox quickly gained attention for blending progressive metal, metalcore, ambient textures, and electronic elements into a distinctive and modern sound. The band initially built momentum through independently released singles, accompanied by visually striking music videos that helped them reach a global audience online. Their early EPs, Spiritbox (2017) and Singles Collection (2019), showcased their dynamic range, shifting seamlessly between atmospheric passages and crushing heaviness. In 2021, Spiritbox released their debut full-length album, Eternal Blue, to widespread critical acclaim, praising LaPlante’s versatile vocals and the band’s innovative songwriting. The album debuted strongly on international charts and solidified Spiritbox as one of the most influential new acts in modern metal. Known for their genre-defying approach and intense live performances, Spiritbox continues to push creative boundaries while reshaping the sound and image of contemporary heavy music.',NULL,'https://spiritbox.com/','https://www.instagram.com/spiritboxmusic/','https://www.tiktok.com/@spiritboxband','https://www.youtube.com/channel/UCmWxquutalJ1ZjdmWLdlhSg','https://tidal.com/artist/9163057','https://open.spotify.com/embed/track/6IdyYbGg1jxiWhfwm2Ykjn?utm_source=generator'),
(27,'Amira Elfeky','+1 213 555 0177','amira.elfeky@mgmt.com','Nu-gaze','USA','Amira Elfeky is a contemporary singer and songwriter who has gained attention for her intense and emotionally charged approach within alternative rock and dark pop. Her music is defined by a dark, immersive atmosphere where distorted guitars, melancholic melodies, and a deep, expressive voice come together to convey feelings of vulnerability, anger, and self-discovery. Through her lyrics, Amira explores themes such as identity, mental health, toxic relationships, and inner struggle, connecting especially with a young audience that finds catharsis and honesty in her songs. Beyond her sound, her visual aesthetic and social media presence reinforce a coherent artistic image rooted in alternative and introspective sensibilities. With an authentic, unapologetic style, Amira Elfeky is emerging as a powerful voice with the potential to leave a meaningful mark on today’s music scene.','63853edf-66d6-487a-a7ca-6cc3919b4312','https://www.amiraelfeky.com/','https://www.instagram.com/amiraelfeky/','https://www.tiktok.com/@amiraelfeky','https://www.youtube.com/@amiraelfeky','https://tidal.com/artist/21561655','https://open.spotify.com/embed/track/6p5duPGjAdVYLq2kOGXYUD?utm_source=generator'),
(28,'Acid Bath','+1 504 555 0122','acidbath@rotten.com','Sludge Metal','USA','Acid Bath began where the Earth ends, deep in the swamp of the Louisiana bayou. Formed in 1991, the band’s sound blended elements of Southern gothic, psychedelic rock, sludge and death metal, with lyrics invoking surreal, pagan and psycho imagery. Wild boys too full of blood for their own good, with a ferocious stage presence and a notorious reputation in the heart of Cajun Hell. They exploded onto the New Orleans metal scene, outsiders who quickly gained a cult status that echoes to this day. Acid Bath’s influence on the modern metal scene is undeniable. They disbanded in 1997, heartbroken after the death of bassist Audie Pitre, killed by a drunk driver on a Sunday afternoon. Sammy Duet went on to form Goatwhore while Dax Riggs and Mike Sanchez did Agents of Oblivion. Riggs became deadboy and the Elephantmen and now releases music under his own name.','f9d6cccd-469c-4ab9-9a99-d840f1e861bb','https://www.acidbathofficial.com/','https://www.instagram.com/acidbath.official',NULL,'https://www.youtube.com/channel/UCh3pghWXTXX3cBJS1kwqDmw','https://tidal.com/artist/3888263','https://open.spotify.com/embed/track/4reyAmWUdNFX6F70rX3ZoS?utm_source=generator'),
(29,'Meshuggah','+46 90 123 45 67','meshuggah@management.se','Extreme Progressive Metal','Sweden','MAVERICKS. PIONEERS. UNIQUE. IMMUTABLE. Destined to be one of the most hailed and talked-about releases, \"IMMUTABLE\" once again showcases MESHUGGAH’s collective brilliance, including some of the most jaw-dropping ensemble performances they have ever executed. Since forming in 1987, the Swedish sound architects known as Meshuggah developed into one of the most technically complex and virtuosic group of players the metal realm has ever encountered, their distinctly unique brand of progressive groove-infused death metal inadvertently launching an entirely new sub genre. Recorded in Sweden’s \"Sweetspot Studios“, their ninth full-length album \"Immutable“ is the follow-up to their highly acclaimed and GRAMMY NOMINATED ALBUM, \"The Violent Sleep Of Reason“. The new album and tour will see the return of Fredrik Thordendal. ','7e1f9c84-71b1-4203-886f-63d62a446f8d','https://www.meshuggah.net/','https://www.instagram.com/meshuggah','https://www.tiktok.com/@meshuggahofficial','https://www.youtube.com/@meshuggah','https://tidal.com/artist/3542781','https://open.spotify.com/embed/track/3s7ClWpUeQH4B30r2N64Y2?utm_source=generator'),
(30,'Derby Motoretas Burrito Kachimba','+34 954 12 34 56','kinkidelia@derbymotoretas.com','Psychedelic Rock','Spain','Close your eyes and imagine Led Zeppelin drinking beers and gazpacho during the boiling summer of Seville. Picture The Stooges drunk on sherry and fantasize about Tommy Iommi on acid waking up among the Andalucia’s wildest gypsies , traveling together in the Spanish Caravan that The Doors dreamed once. Okay, now open your eyes. You don’t need to daydream anymore because all of that... already exists. And on top of that with a name that you couldn’t ever have come up with in your wildest dreams: Derby Motoreta’s Burrito Kachimba. Psychedelia, southern bohemia, stoner textures, carnival garage, glam eyeliner, the most bizarre and crazy videos made by and for freaks...','2ccd6cea-866d-4b52-8841-d8cbed79368d','https://derbymotoretasburritokachimba.com/','https://www.instagram.com/derbymotoretasburritokachimba/','https://www.tiktok.com/@dmbk_kbmd','https://www.youtube.com/channel/UCx7RE5CmDl2VWiNDzp_Eu1A','https://tidal.com/artist/10864040','https://open.spotify.com/embed/track/1zbACBLOhtXNCdZsUBRcak?utm_source=generator'),
(31,'Slaughter to Prevail','+7 495 123 45 67','alex.terrible@stp.com','Deathcore','Russia','Slaughter To Prevail is pushing heavy music to new extremes, delivering unapologetically hard modern metal while smashing expectations, and becoming one of the most talked about bands in heavy music in the process. Slaughter To Prevail’s journey began in the most unlikely of ways: two musicians from completely different worlds. Alex Terrible, forging his monstrous vocals from a small bedroom in the cold, rural Russian city of Yekaterinburg, crossed paths online with guitarist Jack Simmons, who sharpened his craft in a quiet town on the outskirts of Essex, UK. What started as a distant collaboration quickly turned into a brotherhood, as their exchanges of ideas took shape, songs were formed and it became apparent to Jack and Alex that they were on to something special. All of this momentum leads to “Grizzly”, their most anticipated release yet. Featuring the already released singles \'Conflict,\' \'Viking,\' \'1984,\' \'Behelit,\' and \'Kid of Darkness,\' the album is a relentless assault, balancing raw aggression with massive, unforgettable catchiness. This is Slaughter To Prevail’s moment. “Grizzly” is the culmination of everything they have built, a defining record that cements their place at the top of modern heavy music.','334c13d3-a90a-48b4-9fc3-34de51af7079','https://www.slaughtertoprevail.com/','https://www.instagram.com/slaughtertoprevailofficial','https://www.tiktok.com/@alexterribleenthusiast','https://www.youtube.com/channel/UCCCUZQwb0I_ILsZaLducWBg','https://tidal.com/artist/6929660','https://open.spotify.com/embed/track/3qCsbEofGjZGRkXaJhLUxw?utm_source=generator'),
(32,'Lamb of God','+1 804 555 0111','log@epicrecords.com','Groove Metal','USA','With over 25 years at the forefront of heavy music, Lamb of God has evolved from underground legends into one of metal’s most enduring forces. From their Richmond roots to five Grammy nominations, the band has maintained a fierce punk ethos while dominating the global stage.\n\nTheir latest work finds them embracing their status as veterans with nothing to prove. Fusing signature groove-driven aggression with new sonic textures, guitarist Mark Morton describes their current era as creatively unrestrained—honoring their past while pushing forward without limits.\n\nLyrically, Randy Blythe confronts themes of social decay and personal responsibility. Whether delivering apocalyptic anthems or atmospheric experiments like \"El Vacio,\" Lamb of God remains a powerful statement of creative freedom: evolving, dangerous, and essential.','68b373d7-5f7d-4999-99f2-c75bd22d4ccc','https://www.lamb-of-god.com/','https://www.instagram.com/lambofgod/','https://www.tiktok.com/@lambofgod','https://www.youtube.com/@lambofgodofficial/videos','https://tidal.com/artist/3139','https://open.spotify.com/embed/track/1WZVnw0YWc7OLkVijEargx?utm_source=generator'),
(33,'Knocked Loose','+1 502 555 0122','knockedloose@purenoise.net','Hardcore Punk','USA','Emerging from the Kentucky hardcore scene, Knocked Loose has become the modern face of heavy music\'s sonic evolution. Known for their bone-crushing breakdowns and the piercing, unmistakable vocals of frontman Bryan Garris, the band has bridged the gap between underground DIY ethics and mainstream festival dominance.\n\nTheir latest material finds them at their most experimental yet terrifyingly heavy, incorporating industrial textures and cinematic horror influences. By pushing the boundaries of hardcore and death metal, Knocked Loose continues to redefine what it means to be \"heavy,\" proving they are the uncompromising future of the genre.','e7aecfc4-272f-4337-90cf-517049326fbb','https://www.knockedloose.eu/','https://www.instagram.com/knockedloosehc/','https://www.tiktok.com/@knockedloosehc','https://www.youtube.com/channel/UCWF6QBQeAM23vAUKT6b1aSg','https://tidal.com/artist/6928332','https://open.spotify.com/embed/track/6PXYOVPBzO3xojFhQAvmde?utm_source=generator'),
(34,'Ghost','+46 58 123 45 67','tobias.forge@lomavista.com','Occult Rock','Sweden','Led by the theatrical genius of Tobias Forge, Ghost has ascended from a cult Swedish occult-rock project to a global arena phenomenon. Blending heavy metal foundations with infectious pop sensibilities and a majestic, cathedral-sized aesthetic, they have redefined the boundaries of theatrical rock.\n\nCentered around the ever-evolving lineage of Papa Emeritus and his Nameless Ghouls, the band crafts high-concept records that explore themes of power, religion, and humanity\'s downfall. With a sound that balances vintage riffs with cinematic grandeur, Ghost has become a modern icon—proving that mystery, satire, and melody can conquer the world of heavy music.','c2024524-2f05-4bfa-8e55-dfbe864304e2','https://ghost-official.com/','https://www.instagram.com/thebandghost/','https://www.tiktok.com/@thebandghost','https://www.youtube.com/channel/UCAOiVaJJlH0Oduv48NN0mMA','https://tidal.com/artist/3913625','https://open.spotify.com/embed/track/5TNhjanmvwvmjCz2WYwSAv?utm_source=generator'),
(35,'Bring Me The Horizon','+44 114 555 0199','bmth@rawpower-mgmt.com','Alternative Metal','UK','Starting as deathcore pioneers in Sheffield, Bring Me the Horizon has undergone one of the most radical transformations in rock history. Led by the visionary Oli Sykes, the band has fearlessly transcended genres, evolving from raw underground aggression to a polished, world-conquering fusion of metal, electronic, and alternative pop.\n\nWith every era, they redefine the cutting edge of modern music. By blending heavy riffs with glitchy production and deeply personal lyrics, they have become the voice of a generation that refuses to be labeled. Whether headline-topping or chart-breaking, Bring Me the Horizon remains the definitive proof that evolution is the key to survival in the heavy music landscape.','a0a89eb3-da44-4265-809b-82cd4b974ebb','https://www.bmthofficial.com/','https://www.instagram.com/bringmethehorizon/','https://www.tiktok.com/@officialbmth','https://www.youtube.com/BMTHchannel/videos','https://tidal.com/artist/19137','https://open.spotify.com/embed/track/0WSa1sucoNRcEeULlZVQXj?utm_source=generator'),
(36,'Three Days Grace','+1 416 555 0177','info@threedaysgrace.com','Alternative Rock','Canada','As titans of the post-grunge and alternative metal era, Three Days Grace have spent over two decades crafting the soundtrack to modern angst and resilience. With a record-breaking number of #1 rock radio singles, the Canadian powerhouse has mastered the art of combining massive, anthemic choruses with raw, driving riffs.\n\nTheir journey—marked by the successful transition from original frontman Adam Gontier to the powerhouse vocals of Matt Walst—is a testament to their enduring songwriting and connection with their fans. By tackling themes of internal struggle, isolation, and perseverance, Three Days Grace remains a cornerstone of the genre, delivering high-energy performances and relatable anthems that continue to resonate across generations of rock fans worldwide.','f70afb60-228a-43ef-9a14-d8657da42100','https://threedaysgrace.com/','https://www.instagram.com/threedaysgraceofficial/','https://www.tiktok.com/@threedaysgrace','https://www.youtube.com/ThreeDaysGraceVideos','https://tidal.com/artist/35937','https://open.spotify.com/embed/track/0M955bMOoilikPXwKLYpoi?utm_source=generator'),
(37,'Iron Maiden','+44 20 8946 0000','phantom@ironmaiden.com','Heavy Metal','UK','As the undisputed legends of the New Wave of British Heavy Metal, Iron Maiden have spent over four decades defining the genre\'s DNA. Led by the galloping bass of Steve Harris and the soaring, operatic vocals of Bruce Dickinson, the band is a global institution known for epic storytelling, dual-guitar harmonies, and their iconic mascot, Eddie.\n\nBeyond the music, their influence is unparalleled—from sold-out stadium tours in their custom Boeing 747, \"Ed Force One,\" to complex conceptual albums that dive into history, mythology, and science fiction. With a career built on unwavering loyalty to their craft and their fans, Iron Maiden remains the gold standard for heavy metal, proving that timeless melodies and theatrical grandeur never go out of style.','900d5dd7-daec-4d11-ae7d-d6d99d823cc2','https://www.ironmaiden.com/','https://www.instagram.com/ironmaiden/','https://www.tiktok.com/@ironmaiden','https://www.youtube.com/ironmaiden','https://tidal.com/artist/5925491','https://open.spotify.com/embed/track/4OROzZUy6gOWN4UGQVaZMF?utm_source=generator'),
(38,'Power Trip','+1-214-555-0198','booking@powertrip.band','Crossover Thrash','USA','Rising from the Dallas hardcore scene to become the vanguard of modern thrash, Power Trip revitalized the genre with a raw, crossover intensity that felt both classic and revolutionary. Driven by the late Riley Gale’s commanding presence and sociopolitical lyricism, the band traded in razor-sharp riffs and punishing, mid-tempo grooves that united metalheads and punks alike.\n\nTheir landmark album, Nightmare Logic, propelled them to the top of every \"best of\" list, proving that thrash could still be dangerous, relevant, and undeniably heavy. Despite the tragic loss of Gale, Power Trip’s legacy as a bridge between underground grit and mainstream recognition remains unshakable, cementing their status as one of the most respected and influential forces in 21st-century heavy music.','7298e099-cea4-4edd-a5ce-2f548b599d53','https://powertrip-official.com/','https://www.instagram.com/powertriptx/',NULL,'https://www.youtube.com/watch?v=FOWf8uqGf8A','https://tidal.com/artist/3795725','https://open.spotify.com/embed/track/1kbwEcixFWVj5dFO9Kso3J?utm_source=generator'),
(39,'Porcupine Tree','+44 161 496 0234','info@porcupinetree.com','Progressive Rock','United Kingdom','Led by the visionary Steven Wilson, Porcupine Tree has spent decades as the premier architects of modern progressive rock. Originally a solo psychedelic project, the band evolved into a formidable quartet known for their unparalleled ability to blend atmospheric melancholy, heavy metal riffs, and cinematic soundscapes into cohesive, intellectual epics.\n\nAfter a long hiatus, their return solidified their status as masters of the genre, proving their intricate compositions and flawless production remain lightyears ahead of the curve. Exploring themes of technology, alienation, and the human psyche, Porcupine Tree creates immersive musical journeys that challenge the listener while maintaining a haunting, melodic beauty. They are the definitive bridge between classic prog ambition and contemporary sonic innovation.',NULL,'https://porcupinetree.com/','https://www.instagram.com/porcupinetreeofficial/','https://www.tiktok.com/@porcupinetreeofficial','https://www.youtube.com/@PorcupineTreeOfficial/videos','https://tidal.com/artist/14967','https://open.spotify.com/embed/track/5RYbKJ3psLCFGKEqyJ931Y?utm_source=generator');
/*!40000 ALTER TABLE `artists` ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=@OLD_AUTOCOMMIT;

--
-- Table structure for table `camping_types`
--

DROP TABLE IF EXISTS `camping_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `camping_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `name_en` varchar(50) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `stock_total` int(11) NOT NULL,
  `stock_available` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `camping_types`
--

SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES `camping_types` WRITE;
/*!40000 ALTER TABLE `camping_types` DISABLE KEYS */;
INSERT INTO `camping_types` VALUES
(1,'Sombra de Yggdrasil (Camping Sombra)','Yggdrasil\'s Shadow (Shaded Camping)',30.00,500,496),
(2,'Morada de Freya (Glamping de Lujo)','Freya\'s Dwelling (Luxury Glamping)',150.00,50,47),
(3,'Drakkar sobre Ruedas (Zona Caravanas)','Drakkar on Wheels (Caravan Zone)',80.00,100,98);
/*!40000 ALTER TABLE `camping_types` ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=@OLD_AUTOCOMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campings`
--

SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES `campings` WRITE;
/*!40000 ALTER TABLE `campings` DISABLE KEYS */;
INSERT INTO `campings` VALUES
(1,'Nacho','Muñoz','DNI','23456781A','1996-01-27','qr-c-0001','ACTIVE',1,1),
(2,'Nacho','Muñoz','DNI','23456781A','1996-01-27','qr-c-0002','ACTIVE',3,4),
(3,'Javier','Sánchez','DNI','78901236G','1985-09-12','qr-c-0003','ACTIVE',2,7),
(4,'Garik','Asatryan','DNI','12345678B','1996-12-30','qr-c-0004','ACTIVE',2,10),
(5,'Elena','Morales','DNI','22334455M','1991-05-08','qr-c-0005','ACTIVE',1,11),
(6,'Pablo','Jiménez','DNI','33445566N','1989-10-25','qr-c-0006','ACTIVE',1,11),
(7,'Nacho','Muñoz','DNI','23456781A','1996-01-27','qr-c-0007','ACTIVE',1,12),
(8,'Nacho','Muñoz','DNI','23456781A','1996-01-27','qr-c-0008','ACTIVE',3,14),
(9,'Antonio','Vega','DNI','99001122U','1983-04-05','qr-c-0009','ACTIVE',2,15);
/*!40000 ALTER TABLE `campings` ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=@OLD_AUTOCOMMIT;

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
  `guest_email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES
(1,'2026-01-10 10:30:00',125.00,'PAID',3,NULL),
(2,'2026-01-20 16:45:00',250.00,'PAID',1,NULL),
(3,'2026-01-28 11:00:00',190.00,'PAID',NULL,'pedro.garcia@gmail.com'),
(4,'2026-02-05 14:20:00',135.00,'PAID',3,NULL),
(5,'2026-02-14 09:15:00',120.00,'PAID',NULL,'carlos.ruiz@hotmail.com'),
(6,'2026-02-22 18:00:00',95.00,'PAID',3,NULL),
(7,'2026-03-08 12:30:00',400.00,'PAID',NULL,'javier.sanchez@email.es'),
(8,'2026-03-15 20:00:00',90.00,'PAID',3,NULL),
(9,'2026-03-25 17:45:00',285.00,'PAID',NULL,'grupo.vikingo@gmail.com'),
(10,'2026-04-03 11:00:00',400.00,'PAID',1,NULL),
(11,'2026-04-12 15:30:00',170.00,'PAID',NULL,'elena.morales@outlook.com'),
(12,'2026-04-20 09:00:00',125.00,'PAID',3,NULL),
(13,'2026-05-02 13:15:00',380.00,'PAID',NULL,'grupo.amigos@gmail.com'),
(14,'2026-05-07 19:30:00',200.00,'PAID',3,NULL),
(15,'2026-05-10 22:00:00',400.00,'PAID',NULL,'antonio.vega@email.com'),
(16,'2026-05-11 10:00:00',190.00,'PENDING',3,NULL),
(17,'2026-05-11 11:30:00',250.00,'CANCELLED',1,NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=@OLD_AUTOCOMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performances`
--

SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES `performances` WRITE;
/*!40000 ALTER TABLE `performances` DISABLE KEYS */;
INSERT INTO `performances` VALUES
(1,'2026-08-06 21:00:00','2026-08-06 21:50:00',38,1),
(2,'2026-08-06 22:20:00','2026-08-06 23:20:00',18,1),
(3,'2026-08-06 23:50:00','2026-08-07 01:00:00',37,1),
(4,'2026-08-07 01:30:00','2026-08-07 03:00:00',11,1),
(5,'2026-08-06 21:30:00','2026-08-06 22:30:00',12,2),
(6,'2026-08-06 23:00:00','2026-08-07 00:15:00',25,2),
(7,'2026-08-07 00:45:00','2026-08-07 02:15:00',17,2),
(8,'2026-08-06 21:30:00','2026-08-06 22:30:00',30,3),
(9,'2026-08-06 23:00:00','2026-08-07 00:15:00',28,3),
(10,'2026-08-07 00:45:00','2026-08-07 02:00:00',32,3),
(11,'2026-08-06 21:30:00','2026-08-06 22:30:00',27,4),
(12,'2026-08-06 23:00:00','2026-08-07 00:15:00',19,4),
(13,'2026-08-07 00:45:00','2026-08-07 02:00:00',20,4),
(14,'2026-08-07 21:00:00','2026-08-07 21:50:00',26,1),
(15,'2026-08-07 22:20:00','2026-08-07 23:20:00',35,1),
(16,'2026-08-07 23:50:00','2026-08-08 01:00:00',22,1),
(17,'2026-08-08 01:30:00','2026-08-08 03:00:00',24,1),
(18,'2026-08-07 21:30:00','2026-08-07 22:30:00',36,2),
(19,'2026-08-07 23:00:00','2026-08-08 00:15:00',5,2),
(20,'2026-08-08 00:45:00','2026-08-08 02:15:00',13,2),
(21,'2026-08-07 21:30:00','2026-08-07 22:30:00',2,3),
(22,'2026-08-07 23:00:00','2026-08-08 00:15:00',10,3),
(23,'2026-08-08 00:45:00','2026-08-08 02:00:00',33,3),
(24,'2026-08-07 21:30:00','2026-08-07 22:30:00',4,4),
(25,'2026-08-07 23:00:00','2026-08-08 00:15:00',16,4),
(26,'2026-08-08 00:45:00','2026-08-08 02:00:00',31,4),
(27,'2026-08-08 21:00:00','2026-08-08 21:50:00',29,1),
(28,'2026-08-08 22:20:00','2026-08-08 23:20:00',1,1),
(29,'2026-08-08 23:50:00','2026-08-09 01:00:00',14,1),
(30,'2026-08-09 01:30:00','2026-08-09 03:00:00',3,1),
(31,'2026-08-08 21:30:00','2026-08-08 22:30:00',39,2),
(32,'2026-08-08 23:00:00','2026-08-09 00:15:00',9,2),
(33,'2026-08-09 00:45:00','2026-08-09 02:15:00',34,2),
(34,'2026-08-08 21:30:00','2026-08-08 22:30:00',7,3),
(35,'2026-08-08 23:00:00','2026-08-09 00:15:00',8,3),
(36,'2026-08-09 00:45:00','2026-08-09 02:00:00',6,3),
(37,'2026-08-08 21:30:00','2026-08-08 22:30:00',15,4),
(38,'2026-08-08 23:00:00','2026-08-09 00:15:00',23,4),
(39,'2026-08-09 00:45:00','2026-08-09 02:00:00',21,4);
/*!40000 ALTER TABLE `performances` ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=@OLD_AUTOCOMMIT;

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

SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES
(1,'ADMIN'),
(2,'MANAGER'),
(3,'USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=@OLD_AUTOCOMMIT;

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

SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES `sponsor_stage` WRITE;
/*!40000 ALTER TABLE `sponsor_stage` DISABLE KEYS */;
INSERT INTO `sponsor_stage` VALUES
(1,1),
(4,1),
(5,1),
(7,1),
(2,2),
(6,2),
(10,2),
(8,3),
(3,4),
(9,4);
/*!40000 ALTER TABLE `sponsor_stage` ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=@OLD_AUTOCOMMIT;

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

SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES `sponsors` WRITE;
/*!40000 ALTER TABLE `sponsors` DISABLE KEYS */;
INSERT INTO `sponsors` VALUES
(1,'Alhambra','+34 954 979 000','sponsorship@alhambracervezas.es',60000.00,'efe14295-d746-4bc0-8155-b5be60d581c6'),
(2,'Thomann Music','+49 9546 9223','export@thomann.de',25000.00,'22a2d408-3262-436f-840b-44ee02abfdda'),
(3,'Impericon','+34 911 875 528','marketing@impericon.es',20000.00,'dd64d72c-755a-42c8-978c-2b54d4143d0f'),
(4,'Monster Energy','+1 800 426 737','info@monsterenergy.com',45000.00,'53cdd82a-e327-4420-a3cc-6506b7facf58'),
(5,'Jägermeister','+49 5331 810','espana@jaegermeister.de',30000.00,'88f79dc4-41cb-44f8-a4b2-d374e9c1ec34'),
(6,'Marshall Amplification','+44 1908 375411','sponsorship@marshall.com',15000.00,'e3350e35-0f0b-4127-8be8-a9c9be576fef'),
(7,'Jack Daniels','+1 888 551 5225','events@jackdaniels.com',28000.00,'28f3db1b-cb44-4a5e-87de-6e2213ae6425'),
(8,'Tanqueray','+34 981 901 906','patrocinios@tanqueray.es',35000.00,'2e251ca5-b98c-44b7-84a2-5de83b4849ad'),
(9,'Vans España','+34 932 203 100','marketing@vans.es',18000.00,'8890a917-d83f-4324-9b7b-354a5b0fd208'),
(10,'Fnac España','+34 902 100 632','comunicacion@fnac.es',12000.00,'4a0b743e-23a3-4136-999e-eff6faf3e67c');
/*!40000 ALTER TABLE `sponsors` ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=@OLD_AUTOCOMMIT;

--
-- Table structure for table `stages`
--

DROP TABLE IF EXISTS `stages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `stages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `name_en` varchar(100) DEFAULT NULL,
  `capacity` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stages`
--

SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES `stages` WRITE;
/*!40000 ALTER TABLE `stages` DISABLE KEYS */;
INSERT INTO `stages` VALUES
(1,'Asgard del Sur','Southern Asgard',15000),
(2,'Valhalla de Triana','Triana Valhalla',10000),
(3,'Fenrir del Al-Ándalus','Al-Ándalus Fenrir',8000),
(4,'Drakkar de Guadalquivir','Guadalquivir Drakkar',5000);
/*!40000 ALTER TABLE `stages` ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=@OLD_AUTOCOMMIT;

--
-- Table structure for table `ticket_types`
--

DROP TABLE IF EXISTS `ticket_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `name_en` varchar(50) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `stock_total` int(11) NOT NULL,
  `stock_available` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_types`
--

SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES `ticket_types` WRITE;
/*!40000 ALTER TABLE `ticket_types` DISABLE KEYS */;
INSERT INTO `ticket_types` VALUES
(1,'Senda del Guerrero (Abono General)','Warrior\'s Path (General Pass)',95.00,5000,4986),
(2,'Banquete en el Valhalla (Experiencia VIP)','Valhalla Banquet (VIP Experience)',250.00,200,196),
(3,'Miércoles - Entrada de Día','Wednesday - Day Pass',45.00,1000,998),
(4,'Jueves - Entrada de Día','Thursday - Day Pass',45.00,1000,1000),
(5,'Viernes - Entrada de Día','Friday - Day Pass',55.00,1000,997),
(6,'Sábado - Entrada de Día','Saturday - Day Pass',60.00,1000,996);
/*!40000 ALTER TABLE `ticket_types` ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=@OLD_AUTOCOMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES
(1,'Nacho','Muñoz','DNI','23456781A','1996-01-27','qr-t-0001','ACTIVE',1,1),
(2,'Garik','Asatryan','DNI','12345678B','1996-12-30','qr-t-0002','ACTIVE',2,2),
(3,'Pedro','García','DNI','34567892C','1990-03-15','qr-t-0003','ACTIVE',1,3),
(4,'María','López','DNI','45678903D','1992-07-20','qr-t-0004','ACTIVE',1,3),
(5,'Nacho','Muñoz','DNI','23456781A','1996-01-27','qr-t-0005','ACTIVE',5,4),
(6,'Carlos','Ruiz','DNI','56789014E','1988-11-05','qr-t-0006','ACTIVE',6,5),
(7,'Ana','Martínez','DNI','67890125F','1994-04-30','qr-t-0007','ACTIVE',6,5),
(8,'Nacho','Muñoz','DNI','23456781A','1996-01-27','qr-t-0008','ACTIVE',1,6),
(9,'Javier','Sánchez','DNI','78901236G','1985-09-12','qr-t-0009','ACTIVE',2,7),
(10,'Nacho','Muñoz','DNI','23456781A','1996-01-27','qr-t-0010','ACTIVE',3,8),
(11,'Laura','Fernández','DNI','89012347H','1998-02-14','qr-t-0011','ACTIVE',3,8),
(12,'Roberto','Díaz','DNI','90123458J','1987-06-22','qr-t-0012','ACTIVE',1,9),
(13,'Sofía','Hernández','DNI','01234569K','1995-12-01','qr-t-0013','ACTIVE',1,9),
(14,'Miguel','Torres','DNI','11223344L','1993-08-18','qr-t-0014','ACTIVE',1,9),
(15,'Garik','Asatryan','DNI','12345678B','1996-12-30','qr-t-0015','ACTIVE',2,10),
(16,'Elena','Morales','DNI','22334455M','1991-05-08','qr-t-0016','ACTIVE',5,11),
(17,'Pablo','Jiménez','DNI','33445566N','1989-10-25','qr-t-0017','ACTIVE',5,11),
(18,'Nacho','Muñoz','DNI','23456781A','1996-01-27','qr-t-0018','ACTIVE',1,12),
(19,'Diego','Romero','DNI','44556677P','1986-03-30','qr-t-0019','ACTIVE',1,13),
(20,'Carmen','Alonso','DNI','55667788Q','1996-07-11','qr-t-0020','ACTIVE',1,13),
(21,'Sergio','Navarro','DNI','66778899R','1993-01-04','qr-t-0021','ACTIVE',1,13),
(22,'Isabel','Ramos','DNI','77889900S','1990-09-17','qr-t-0022','ACTIVE',1,13),
(23,'Nacho','Muñoz','DNI','23456781A','1996-01-27','qr-t-0023','ACTIVE',6,14),
(24,'Paula','Martín','DNI','88990011T','1995-08-24','qr-t-0024','ACTIVE',6,14),
(25,'Antonio','Vega','DNI','99001122U','1983-04-05','qr-t-0025','ACTIVE',2,15),
(26,'Nacho','Muñoz','DNI','23456781A','1996-01-27','qr-t-0026','ACTIVE',1,16),
(27,'Nacho','Muñoz','DNI','23456781A','1996-01-27','qr-t-0027','ACTIVE',1,16),
(28,'Garik','Asatryan','DNI','12345678B','1996-12-30','qr-t-0028','CANCELLED',2,17);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=@OLD_AUTOCOMMIT;

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

SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES
(1,1),
(2,2),
(3,3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=@OLD_AUTOCOMMIT;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT 1,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT current_timestamp(),
  `auth_provider` varchar(20) DEFAULT 'LOCAL',
  `provider_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES
(1,'garik@email.com','$2a$12$8LssFTKG6GY.pl7Xd.K8DOHFHWwUUm1wOzQ5L4LT9uoIB2XbXYw9a',1,'Garik','Asatryan','1996-12-30','666666666','2026-05-14 19:07:39','LOCAL',NULL),
(2,'paula@email.com','$2a$12$D7fBGvd3cCwHCr57fgOcPuj.tC8V.4YSDosNV/1wtyA4En9lBJF5O',1,'Paula','Martín','1995-08-24','777777777','2026-05-14 19:07:39','LOCAL',NULL),
(3,'nacho@email.com','$2a$12$hE8vq6Ng0aKvV60dpP8gRecFR5fT5G7mTjP61ZnnjIOAa2n0mbDjC',1,'Nacho','Muñoz','1996-01-27','676767676','2026-05-14 19:07:39','LOCAL',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=@OLD_AUTOCOMMIT;

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

SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES `verification_tokens` WRITE;
/*!40000 ALTER TABLE `verification_tokens` DISABLE KEYS */;
/*!40000 ALTER TABLE `verification_tokens` ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=@OLD_AUTOCOMMIT;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2026-05-14 19:27:13
