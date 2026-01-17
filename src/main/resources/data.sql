INSERT IGNORE INTO users (email, password, enabled, first_name, last_name, birth_date, phone)
VALUES ('garik@email.com', '$2a$12$8LssFTKG6GY.pl7Xd.K8DOHFHWwUUm1wOzQ5L4LT9uoIB2XbXYw9a', true, 'Garik', 'Asatryan',
        '1996-12-30', '666666666'),
       ('paula@email.com', '$2a$12$jKTxYa0fSqWzb2UrhHxR8uDn5/ArJrh4VQ1zNxJHCU7.LsZmEUE/.', true, 'Paula', 'Martín',
        '1995-08-24', '777777777'),
       ('nacho@email.com', '$2a$12$hE8vq6Ng0aKvV60dpP8gRecFR5fT5G7mTjP61ZnnjIOAa2n0mbDjC', true, 'Nacho', 'Muñoz',
        '1996-01-27', '676767676');

INSERT IGNORE INTO roles (name)
VALUES ('ADMIN'),
       ('MANAGER'),
       ('USER');

INSERT IGNORE INTO user_role (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

INSERT IGNORE INTO ticket_types (name, price, stock_total, stock_available)
VALUES ('Senda del Guerrero (Abono General)', 95.00, 5000, 5000),
       ('Banquete en el Valhalla (Experiencia VIP)', 250.00, 200, 200),
       ('Miércoles - Entrada de Día', 45.00, 1000, 1000),
       ('Jueves - Entrada de Día', 45.00, 1000, 1000),
       ('Viernes - Entrada de Día', 55.00, 1000, 1000),
       ('Sábado - Entrada de Día', 60.00, 1000, 1000);

INSERT IGNORE INTO camping_types (name, price, stock_total, stock_available)
VALUES ('Sombra de Yggdrasil (Camping Sombra)', 30.00, 500, 500),
       ('Morada de Freya (Glamping de Lujo)', 150.00, 50, 50),
       ('Drakkar sobre Ruedas (Zona Caravanas)', 80.00, 100, 100);


INSERT INTO sponsors (name, phone, email, contribution)
VALUES ('Cruzcampo', '+34 954 979 000', 'sponsorship@heinekenespana.es', 60000.00),
       ('Thomann Music', '+49 9546 9223', 'export@thomann.de', 25000.00),
       ('EMP Mailorder', '+34 911 875 528', 'marketing@emp-online.es', 20000.00),
       ('Monster Energy', '+1 800 426 737', 'info@monsterenergy.com', 45000.00),
       ('Jägermeister', '+49 5331 810', 'espana@jaegermeister.de', 30000.00),
       ('Marshall Amplification', '+44 1908 375411', 'sponsorship@marshall.com', 15000.00),
       ('Jack Daniels', '+1 888 551 5225', 'events@jackdaniels.com', 28000.00),
       ('Estrella Galicia', '+34 981 901 906', 'patrocinios@estrellagalicia.es', 35000.00),
       ('Vans España', '+34 932 203 100', 'marketing@vans.es', 18000.00),
       ('Fnac España', '+34 902 100 632', 'comunicacion@fnac.es', 12000.00);

INSERT INTO stages (name, capacity)
VALUES ('Asgard del Sur', 15000),
       ('Valhalla de Triana', 10000),
       ('Fenrir del Al-Ándalus', 8000),
       ('Drakkar de Guadalquivir', 5000);

INSERT INTO sponsor_stage (sponsor_id, stage_id)
VALUES (1, 1),
       (2, 2),
       (3, 4),
       (4, 1),
       (6, 2),
       (8, 3);

INSERT IGNORE INTO artists (name, phone, email, genre, country, description, logo, official_url, instagram_url,
                            tiktok_url,
                            youtube_url, tidal_url, spotify_url)
VALUES ('Gojira', '+33 1 42 67 00 00', 'contact@gojira-music.com', 'Progressive Death Metal', 'France',
        'Taking their name from the original Japanese pronunciation of Godzilla, French heavy metal quartet Gojira have gone from the utmost obscurity in the first half of their career to widespread global recognition in the second. Combining elements of thrash, death, math, groove, progressive, and post-metal with philosophical and environment-themed lyrics, the band found mainstream favor in 2012 with the release of their fifth long-player, L\'Enfant Sauvage, and doubled-down on that success with 2016\'s Grammy-nominated Magma and 2021\'s hard-hitting and versatile Fortitude. In 2024, Gojira became the first heavy metal act to perform at an Olympic opening ceremony. ',
        '8f460969-b0b5-4a7d-80f0-8c7a52d23721', 'https://www.gojira-music.com/',
        'https://www.instagram.com/gojiraofficial', 'https://www.tiktok.com/@gojiraband',
        'https://www.youtube.com/gojira', 'https://tidal.com/artist/3883303',
        'https://open.spotify.com/embed/track/2pZsQqXFgcY03vRyZxSQhU?utm_source=generator'),
       ('Loathe', '+44 20 7946 0000', 'info@loathe.uk', 'Metalcore', 'UK',
        'Loathe (sometimes stylized as LOATHE) are an English nu metalcore band from Liverpool. The band\'s music makes use of elements of metalcore and nu metal, while also incorporating more experimental aspects from genres like shoegaze, progressive metal and industrial music. Formed in 2014, the group consists of lead vocalist Kadeem France, guitarist and second vocalist Erik Bickerstaffe, drummer Sean Radcliffe and bassist Feisal El-Khazragi. Loathe have been nominated for Heavy Music Awards best UK breakthrough band and Metal Hammer Golden Gods Awards best new band in 2018. In a 2020 poll by Revolver magazine, they were voted the third most likely contemporary band to break through into the mainstream.',
        'c4dae7d0-a787-4992-bfac-32918c423e87', 'https://loatheasone.co.uk/', 'https://www.instagram.com/loatheasone',
        '', 'https://www.youtube.com/watch?v=pRzlroDyvXo', 'https://tidal.com/artist/3712384',
        'https://open.spotify.com/embed/track/4OmlsAT8r4q9vPFBvfYgyZ?utm_source=generator'),
       ('Rammstein', '+49 30 2091 0000', 'management@rammstein.de', 'Industrial Metal', 'Germany',
        'Over the course of three decades, Rammstein have shaped heavy music with their unique blend of industrial sound, provocative aesthetics, and spectacular live shows. Formed in Berlin in 1994, the line-up has remained unchanged: Till Lindemann, Richard Z. Kruspe, Paul Landers, Oliver Riedel, Christoph Schneider, and Flake Lorenz. Eight studio albums, including two German No.1 singles (“Pussy”, “Deutschland\") and their most recent release “Zeit\" (2022), have earned them multiple gold and platinum awards worldwide as well as Grammy nominations. Known for uncompromising creativity, iconic videos, and incendiary stage productions, Rammstein continue to defy convention and sell out stadiums across the globe – writing one of the most influential and unique chapters in rock history.',
        '192743ce-c57e-4dea-b762-fc14437ac7bc', 'https://www.rammstein.de/en/',
        'https://www.instagram.com/rammsteinofficial/', 'https://www.tiktok.com/@rammstein',
        'https://www.youtube.com/channel/UCYp3rk70ACGXQ4gFAiMr1SQ', 'https://tidal.com/artist/24972',
        'https://open.spotify.com/embed/track/3gVhsZtseYtY1fMuyYq06F?utm_source=generator'),
       ('Faetooth', '+1 213 555 0198', 'faetoothband@gmail.com', 'Doom Metal', 'USA',
        'Spawning from Los Angeles, Faetooth forges fairy-doom: an eclectic amalgamation of doom metal, sludge, and shoegaze. From spellbinding melodies to guttural shrieks, Faetooth’s music conjures a looming atmosphere offering you a glimpse into their mystifying realm.',
        '4ee92796-7185-4bc6-826c-ef80e151f9c6', '', 'https://www.instagram.com/faetooth/',
        'https://www.tiktok.com/@faetooth', 'https://www.youtube.com/@Faetooth', 'https://tidal.com/artist/17871929',
        'https://open.spotify.com/embed/track/7hwngx8lZypzVtHeKAxi4F?utm_source=generator'),
       ('Deftones', '+1 916 555 0123', 'contact@deftones.com', 'Alternative Metal', 'USA',
        'Deftones have always defined boundless creativity in the music space. Across nine studio albums, they have carved out an unmistakable sonic identity — ferocious yet dreamlike, while making space for constant refinement and surprise. Now, decades on from the groove-forward sound of their era-defining debut, Adrenaline, and following a long line of masterpieces including 2000’s White Pony, 2010’s Diamond Eyes and 2020’s Ohms — an album that earned them their second and third Grammy nominations — they return with one of the most focused statements of their career: private music. Joining the band’s creative core of Chino Moreno, Stephen Carpenter, Abe Cunningham and Frank Delgado (as well as touring bassist Fred Sablan, who appears on the album) is producer Nick Raskulinecz, who previously worked on Diamond Eyes and 2012’s riveting Koi No Yokan. The result is a lean, masterfully paced 11-song set that plays like a new Deftones benchmark.',
        'eee6a9ac-f592-46db-abbc-4bcdab5b8cf8', 'https://www.deftones.com/', 'https://www.instagram.com/deftones/',
        'https://www.tiktok.com/@deftones', 'https://www.youtube.com/@deftones', 'https://tidal.com/artist/15349',
        'https://open.spotify.com/embed/track/51c94ac31swyDQj9B3Lzs3?utm_source=generator'),
       ('Behemoth', '+48 22 654 00 00', 'behemoth.mgmt@new-east.pl', 'Blackened Death Metal', 'Poland',
        'Resting on their laurels was never in consideration as far as Behemoth were concerned. The Shit Ov God is emblematic of that. Here\'s a band that, 34 years in, is releasing its most inflammatory and extreme record to date. 8 songs that go into the fathoms of humanity, divinity and what defiance means in an age where individuality is prized but everyone is clinging to their saviours. Musically, politically, or otherwise. The title of the album is in line with those sensibilities. Intentionally polarizing, founder and frontman Nergal, says of the title \" We chose this provocative title deliberately, rejecting subtlety in favor of a direct and polarizing statement. It\'s a defiant plunge into the depths, daring to seek the absolute even in the gutter.\"',
        '64165fa2-f282-4f86-bdfe-86fcc4c37567', 'https://www.behemoth.pl/',
        'https://www.instagram.com/behemothofficial/', 'https://www.tiktok.com/@behemothofficial',
        'https://www.youtube.com/user/Behemothofficial', 'https://tidal.com/artist/24058',
        'https://open.spotify.com/embed/track/2FsDybzWgntIa8M3Y7AenT?utm_source=generator'),
       ('Fit For An Autopsy', '+1 201 555 0144', 'ffaa@metal-management.com', 'Deathcore', 'USA',
        'The crushing music of Fit For An Autopsy is for any fan of extreme metal, its sound and fury is absolutely unflinching in purpose. The band expertly blends excessive, force-fueled death metal with atmospheric groove and impassioned personal diatribes. The Nothing That Is, Fit For An Autopsy’s seventh and latest album (third for Nuclear Blast), bears no sign whatsoever of commercial concession or rehashed past glories. Instead, the ten-song album finds the band exploring moodier textures and deeper emotions that add new power and dynamics to their already brutal and complex music, while also completely tuning out the ambient noise of passing musical trends and the expectations of the outside world.',
        'e1fe03de-567a-4d7e-9190-1d76baff91e6', 'https://fitforanautopsy.co/',
        'https://www.instagram.com/fitforanautopsy/', 'https://www.tiktok.com/@fitforanautopsyofficial',
        'https://www.youtube.com/@FitForAnAutopsy', 'https://tidal.com/artist/3985809',
        'https://open.spotify.com/embed/track/79yvAWlqVukArNOxtyyKCI?utm_source=generator'),
       ('Lorna Shore', '+1 732 555 0166', 'lornashore@touring.com', 'Deathcore', 'USA',
        'Call them heroes or hell-bringers, it doesn’t matter to Lorna Shore. On their fifth album—I Feel The Everblack Festering Within Me—the New Jersey quintet puts every metal subgenre on notice, forcing listeners to wonder what took the genre so long to evolve. Andrew O’Connor’s orchestral arrangements provide an epic, cinematic backdrop for the band’s stentorian power. Adam De Micco shifts seamlessly from light-speed black metal to \'70s-style guitar phrasing, while Will Ramos proves himself the heir to Mike Patton’s vocal madness. Driven by the propulsive terror of Yager and Archey, the album transcends deathcore, leaning into grandiose prog-rock territory through sheer extremity. By torching the rulebook to divine their own truth, Lorna Shore has created a physical onslaught that demands other genres step up their game.',
        'b10e2abb-4812-48bb-a27d-98141e6fcd5c', 'https://lornashoreband.com/', 'https://www.instagram.com/lornashore',
        'https://www.tiktok.com/@lornashoreofficial', 'https://www.youtube.com/@LornaShore',
        'https://tidal.com/artist/5269283',
        'https://open.spotify.com/embed/track/0O26gtfjuscAOnQobjNPPL?utm_source=generator'),
       ('Opeth', '+46 8 123 456 00', 'management@opeth.com', 'Progressive Metal', 'Sweden',
        'More than three decades into their career, Opeth have trained their admirers to expect the unexpected. But even by their own standards, the Swedish progressive titans have conjured something extraordinary this time around. The band’s 14th studio exploration, The Last Will & Testament, is the darkest and heaviest record they have made in decades, it is also the most fearlessly progressive. A concept album recounting the reading of one recently deceased man’s will to an audience of his surviving family members, it brims with haunting melodrama, shocking revelations and some of the wildest and most unpredictable music that songwriter/frontman Mikael Åkerfeldt has ever written. Proud adherents to a progressive ethos, Opeth have never repeated themselves, and The Last Will & Testament is every bit as revelatory and adventurous as its 13 predecessors. But one thing is undeniable: Mikael Akerfeldt’s peerless death metal growls are back, for the first time since Watershed in 2008. After three decades of dazzling the world, Opeth have made their most daring creative leap yet. The Last Will & Testament is a progressive and dramatic triumph, and yet more proof that expecting the unexpected is the only way forward for fans of Sweden’s finest.',
        '23df5770-e321-4eec-b0f8-2e00a57cff51', 'https://opeth.com/', 'https://www.instagram.com/officialopeth',
        'https://www.tiktok.com/@opethofficial', 'https://www.youtube.com/@opeth', 'https://tidal.com/artist/37348',
        'https://open.spotify.com/embed/track/0ppdt8zRZOHIKh4ZDB0Zp9?utm_source=generator'),
       ('Jinjer', '+380 44 234 5678', 'jinjer.booking@gmail.com', 'Progressive Metal', 'Ukraine',
        'A versatile, progressive groove metal unit based out of Ukraine, Jinjer have found success both in their Eastern European homeland and abroad with their punitive blend of post-hardcore and death/progressive/nu-metal. Drawing from a wide array of influences, including R&B, soul, hip-hop, and the full spectrum of heavy metal, the band formed in 2009 and features a lineup consisting of Tatiana Shmailyuk (vocals), Roman Ibramkhalilov (guitar), Eugene Abdiukhanov (bass), and Vladislav Ulasevich (drums). Jinjer issued a pair of EPs, 2009\'s OIMACTTA and 2012\'s Inhale, Do Not Breathe, before breaking big at home with the release of a full-length version of the latter album in 2013. The group\'s sophomore long-player, 2014\'s Cloud Factory, caught the attention of heavy metal institution Napalm, which signed Jinjer and released their third full-length effort, King of Everything, in 2016. In early 2019, the band returned with the Micro EP, again on Napalm. By the end of the year, its natural counterpoint, Macro, arrived.',
        '227b7839-4435-4cc2-b8a0-746f8c175b81', 'https://jinjer-metal.com/',
        'https://www.instagram.com/jinjer_official/', 'https://www.tiktok.com/@jinjer_official',
        'https://www.youtube.com/watch?v=SQNtGoM3FVU', 'https://tidal.com/artist/4930355',
        'https://open.spotify.com/embed/track/5t8NXa2fugcTPsTfhVILmS?utm_source=generator'),
       ('Metallica', '+1 415 555 0101', 'hq@metallica.com', 'Thrash Metal', 'USA',
        'Metallica formed in 1981 by drummer Lars Ulrich and guitarist and vocalist James Hetfield and has become one of the most influential and commercially successful rock bands in history, having sold 120 million albums worldwide and generating more than 15 billion streams while playing to millions of fans on literally all seven continents. They have scored several multi-platinum albums, including 1991’s Metallica (commonly referred to as The Black Album), with sales of nearly 18 million copies in the United States alone, making it the best-selling album in the history of Soundscan. Metallica has also garnered numerous awards and accolades, including nine Grammy Awards, two American Music Awards, and multiple MTV Video Music Awards, and were inducted into the Rock and Roll Hall of Fame and Museum in 2009. In December 2013, Metallica made history when they performed a rare concert in Antarctica, becoming the first act to ever play all seven continents all within a year, and earning themselves a spot in the Guinness Book of World Records. Metallica’s twelfth studio album was released on April 14, 2023 on Metallica’s own Blackened Recordings record label, and the band is currently on the M72 Tour—a 2-year, continent spanning tour with two nights in each market and no repeat sets.',
        'ba0bdf91-4dd2-4074-898d-2ab104edde7a', 'https://www.metallica.com/', 'https://www.instagram.com/metallica/',
        'https://www.tiktok.com/@metallica', 'https://www.youtube.com/@metallica', 'https://tidal.com/artist/8405',
        'https://open.spotify.com/embed/track/2MuWTIM3b0YEAskbeeFE1i?utm_source=generator'),
       ('Mastodon', '+1 404 555 0188', 'info@mastodonrocks.com', 'Sludge Metal', 'USA',
        'Mastodon is an American heavy metal band from Atlanta, Georgia. Formed in 2000, the band\'s lineup of Troy Sanders (bass/vocals), Brent Hinds (lead guitar/vocals), Bill Kelliher (rhythm guitar/backing vocals), and Brann Dailor (drums/vocals) remained unchanged for 24 years, until Hinds\'s departure in March 2025. Mastodon has released eight studio albums, as well as a number of other releases. Atlanta\'s Mastodon are one of the most original and influential American metal bands to appear in the 21st century. Their wide-angle progressive approach encompasses stoner and sludge metal, punishing hardcore and metalcore, neo-psych, death metal, and more. The group\'s playing style incorporates technically complex guitar riffs, lyric hooks, long, melodic instrumental passages, and intricate, jazz-influenced drumming with syncopated time signatures. In 2016, the staff of Loudwire named them the 20th-best metal band of all time.',
        '8e55696d-62b5-4782-9832-e695b0572c33', 'https://www.mastodonrocks.com/',
        'https://www.instagram.com/mastodonrocks/', 'https://www.tiktok.com/@mastodonrocks',
        'https://www.youtube.com/channel/UCR7Ls5FuT6UKTcsMkcwgCUA', 'https://tidal.com/artist/15346',
        'https://open.spotify.com/embed/track/3jagGO7eHHuaD53ibehkux?utm_source=generator'),
       ('Evanescence', '+1 501 555 0155', 'mgmt@evanescence.com', 'Alternative Metal', 'USA',
        'Evanescence is an American rock band founded in 1994 by singer and keyboardist Amy Lee and guitarist Ben Moody in Little Rock, Arkansas. After releasing independent EPs and a demo CD as a duo in the late 1990s, Evanescence released their debut studio album, Fallen, on Wind-up Records in 2003. Propelled by the success of hit singles like \"Bring Me to Life\" and \"My Immortal\", Fallen sold more than four million copies in the US by January 2004, garnering Evanescence two Grammy Awards out of six nominations. They released their first live album and concert DVD, Anywhere but Home, in 2004, which sold over one million copies worldwide.  Evanescence will release the 20th anniversary edition of their multi-platinum debut album Fallen on November 17th. The anniversary will see remastered tracks from the original album, previously unheard demos, and alternate versions of some of their most iconic songs. In Amy’s words, “20 years later, this album has never meant more. Fallen has been the soundtrack to first loves, epic heartbreak, self-realization, wedding days, last goodbyes, friendships, and countless other moments in so many lives…not to mention my own. I am forever humbled and grateful to be a part of it.”',
        '21265009-0652-41a0-9d20-f84d2c1cf013', 'https://www.evanescence.com/',
        'https://www.instagram.com/evanescenceofficial/', 'https://www.tiktok.com/@evanescence',
        'https://www.youtube.com/channel/UCJeH7gl6PbDVV4DTldIOPOA', 'https://tidal.com/artist/4853',
        'https://open.spotify.com/embed/track/0COqiPhxzoWICwFCS4eZcp?utm_source=generator'),
       ('Tool', '+1 310 555 0199', 'toolband@management.com', 'Progressive Metal', 'USA',
        'Tool established themselves as one of America\'s most enduring and unpredictable acts with an ever-evolving brand of muscular but mind-altering sonics, a wry sense of humor, and a mystical aesthetic that attracted a cult-like following of devoted fans with just a handful of albums spread across decades. Their greatest breakthrough was to meld dark underground metal with the ambition of art rock, crafting multi-sectioned, layered songs as if they were classical composers. While embracing the artsy, they also paid musical homage to the relentlessly bleak visions of grindcore, death metal, and thrash. Even with their post-punk influences, they executed their music with the sound and feel of prog rock, alternating between long, detailed instrumental interludes and lyrical rants in their songs. Debuting in the early \'90s with Undertow, they were initially lumped in with the nu-metal contemporaries of the time, which made them a hit on rock radio with their sophomore effort, 1996\'s Ænima. However, they soon broke away from those associations, evolving beyond the confines of traditional song structures and song lengths, crafting epics that often clocked in past the ten-minute mark on LP head-trips Lateralus (2001) and 10,000 Days (2006). After a lengthy 13-year hiatus, they returned to the fold in 2019 with their fifth opus, the chart-topping, Grammy-nominated Fear Inoculum. In 2022, they celebrated their 30th anniversary with \"Opiate²,\" a re-recorded version of their debut single. ',
        'da8b101e-f822-4520-9b5e-3ce37b4422b8', 'https://www.toolband.com', 'https://www.instagram.com/toolmusic/',
        'https://www.tiktok.com/@tool', 'https://www.youtube.com/channel/UC1wUo-29zS7m_Jp-U_xYcFQ',
        'https://tidal.com/artist/3850668',
        'https://open.spotify.com/embed/track/55mJleti2WfWEFNFcBduhc?utm_source=generator'),
       ('Whitechapel', '+1 865 555 0177', 'whitechapel@metalblade.com', 'Deathcore', 'USA',
        'Whitechapel is an American deathcore band from Knoxville, Tennessee. The band is named after the Whitechapel district in east London, England, where Jack the Ripper committed a series of murders. The group comprises vocalist Phil Bozeman, guitarists Ben Savage, Alex Wade, and Zach Householder, bassist Gabe Crisp and drummer Brandon Zackey. Their core lineup, with the exception of the drummer, has remained consistent since Householder replaced original guitarist Brandon Cagle in 2007. Founded in 2006 by Bozeman and Savage, the band has released nine studio albums and twenty-two music videos, and is currently signed to Metal Blade Records. Whitechapel\'s 2010 album A New Era of Corruption, sold around 10,600 copies in the United States in its first week of release and debuted at position No. 43 on the Billboard 200 chart. The band\'s self-titled fourth album was released in 2012 and debuted at No. 47 on the Billboard 200, selling roughly 9,200 copies in its first week. In 2014 the band released their fifth full-length album, Our Endless War to generally positive reviews. The album sold roughly 16,000 copies in its first week and debuted at No. 10 on the Billboard 200. They released their sixth full-length album Mark of the Blade in 2016 to greater critical acclaim, selling roughly 8,000 copies in the first week of its release. In 2019, Whitechapel released their seventh album, The Valley, which debuted at No. 143 on the Billboard 200 also to critical acclaim. Their newest album, Hymns in Dissonance, was released on March 7, 2025. ',
        'b2add995-822b-4596-b61a-a59092deea87', 'https://whitechapelband.com/',
        'https://www.instagram.com/whitechapelband/', 'https://www.tiktok.com/@whitechapelmusic',
        'https://www.youtube.com/@WhitechapelTV', 'https://tidal.com/artist/3767580',
        'https://open.spotify.com/embed/track/4CuCHhr5zHzEwcflWD2jUT?utm_source=generator'),
       ('VOLA', '+45 31 12 34 56', 'contact@volaband.com', 'Progressive Metal', 'Denmark',
        'Vola (stylized as VOLA) are a Danish progressive metal band formed in Copenhagen in 2006. Having gone through a number of lineup changes earlier in their career, the band currently consists of three Danes, guitarist/vocalist Asger Mygind, keyboardist Martin Werner and bassist Nicolai Mogensen; and one Swede, drummer Adam Janzi. The Danish-Swedish quartet continues to defy conventions, combining electronic elements with a progressive and pop-infused metal sound. Their fourth album, \'Friend Of A Phantom\', was released on November 1st, 2024. ',
        '9338a1be-06f0-4d59-9e70-311cca2f4d93', 'https://www.volaband.com/', 'https://www.instagram.com/volaband/',
        'https://www.tiktok.com/@volaband', 'https://www.youtube.com/@volaband', 'https://tidal.com/artist/6476261',
        'https://open.spotify.com/embed/track/6WTioFyIEiYB5Ge0SJO8Rd?utm_source=generator'),
       ('Deafheaven', '+1 415 555 0122', 'info@deafheaven.com', 'Blackgaze', 'USA',
        'Deafheaven is an American blackgaze band formed in 2010. Originally based in San Francisco, the group began as a two-piece with singer George Clarke and guitarist Kerry McCoy, who recorded and self-released a demo album together. Following its release, Deafheaven recruited three new members and began to tour. Before the end of 2010, the band signed to Deathwish Inc. and later released their debut album Roads to Judah, in April 2011. They popularized a unique style blending black metal, shoegaze, and post-rock, among other influences, later called blackgaze by reviewers. Deafheaven\'s second album, Sunbather, was released in 2013 to wide critical acclaim, becoming one of the best reviewed albums of the year in the US. In 2015 the band followed up with New Bermuda and in 2018 with Ordinary Corrupt Human Love. Their 2021 album, Infinite Granite, drastically reduced the presence of screamed vocals. In 2025, they released Lonely People with Power, which featured a heavier and more aggressive sound.',
        '7f7650a0-db4e-4c02-92dc-964e70cf2135', 'https://deafheaven.com/', 'https://www.instagram.com/deafheavenband/',
        'https://www.tiktok.com/@deafheavenband', 'https://www.youtube.com/channel/UC97UMkZzchbn0uO-KFyq8ww',
        'https://tidal.com/artist/3938220',
        'https://open.spotify.com/embed/track/5vWfIEWXevuwFSVqgrItHC?utm_source=generator'),
       ('Judas Priest', '+44 20 7946 0555', 'mgmt@judaspriest.com', 'Heavy Metal', 'UK',
        'Judas Priest are one of the most influential and long-lasting heavy metal groups of all time. Emerging at the dawn of the New Wave of British Heavy Metal, Priest combined straightforward rock & roll muscle with a more theatrically minded performance presence. This sound was made more unique by the dynamic banshee wail of Rob Halford and the vicious dual-lead guitar attack of Glenn Tipton and K.K. Downing. While issuing metal anthems like \"Breaking the Law,” “Living After Midnight,” and “You\'ve Got Another Thing Coming,\" Judas Priest set the pace for the genre from 1975 until 1985 with iconic albums like British Steel (1980), Screaming for Vengeance (1982), and Defenders of the Faith (1984), and helped lay the groundwork for speed and death metal. The group struggled after Halford\'s departure in the early \'90s but were restored to prominence in the 2000s upon his return, issuing a string of acclaimed efforts -- Angel of Retribution (2005), Nostradamus (2010), Redeemer of Souls (2014), and the U.S. and U.K. Top Five-charting Firepower (2018) -- that skillfully married melody, pageantry, and force. Judas Priest were inducted into the Rock & Roll Hall of Fame in 2022, two years before the arrival of Invincible Shield, their 19th studio LP. ',
        '9951fc3b-545f-43a5-abd6-c655092712e4', 'https://judaspriest.com/home/', 'https://instagram.com/judaspriest',
        'https://www.tiktok.com/@judaspriest', 'https://www.youtube.com/@judaspriest', 'https://tidal.com/artist/701',
        'https://open.spotify.com/embed/track/0L7zm6afBEtrNKo6C6Gj08?utm_source=generator'),
       ('Pantera', '+1 817 555 0199', 'info@pantera.com', 'Groove Metal', 'USA',
        'One of the preeminent metal bands of the \'90s, Texan powerhouse Pantera put to rest any and all remnants of the \'80s metal scene, almost single-handedly demolishing any notion that hair metal, speed metal, power metal, et al., were anything but passé. Loathe to admit it, the Texas band had in fact been one of those \'80s metal bands, releasing fairly unsuccessful (and later disowned) glam-inspired music throughout much of the decade. The about-face came in 1986 with the addition of vocalist Phil Anselmo, who joined the classic, core lineup of bassist Rex Brown, drummer Vinnie Paul, and guitarist Dimebag Darrell. After the release of 1988\'s Power Metal, the band pushed their sound to a new extreme with their major-label debut, Cowboys from Hell (1990). Pantera\'s mainstream breakthrough came next with Vulgar Display of Power (1992), their second major-label album, which thrust the band to the forefront of the metal scene, alongside such veteran bands as metallica, Megadeth, Slayer, and Anthrax, as well as fellow up-and-comers Sepultura and White Zombie. By the time Pantera unleashed Far Beyond Driven (1994), after two long years of touring, they were the most popular metal band in the land: the new album debuted atop the Billboard Top 200 as its lead single, \"I\'m Broken,\" was getting massive airplay. ',
        '8eb7dbc8-3d44-4898-a9c0-90e9668da859', 'https://pantera.com/', 'https://www.instagram.com/panteraofficial',
        'https://www.tiktok.com/@pantera', 'https://www.youtube.com/channel/UChTDORxN3YPmasEurM6kRoA',
        'https://tidal.com/artist/6373',
        'https://open.spotify.com/embed/track/7fcfNW0XxTWlwVlftzfDOR?utm_source=generator'),
       ('Danheim', '+45 70 10 20 30', 'contact@danheimmusic.com', 'Neofolk', 'Denmark',
        'Danheim is a Nordic folk/Viking inspired project from the Copenhagen-based Danish producer Reidar Schæfer Olsen, a man with 10 years of experience in electronic and ambient music – focusing on Nordic Folk and Viking age inspired music with a certain Nordic authenticity or mood. Danheim’s music is often composed of ideas and stories based on the darker side of the Viking period, inspired or consisting of Nordic Mythology, old Danish folklore, and a vivid imagination. Danheim hit 1 billion streams across streaming platforms in 2022, has 585.000 subscribers on YouTube, with music featured in multiple seasons of the popular TV show Vikings. Singer-songwriter Reidar Schæfer Olsen, (born in 1985, Brøndby, Denmark), transitioned to focus on music in the early 2000s and has been composing music almost continuously ever since, alongside doing radio shows and starting the indie label Fimbul Records. With a passion for songwriting running through his veins from an early age, Reidar Olsen’s Viking-inspired music and dark melodies have broadened the Nordic folk genre and inspired millions of listeners around the world, bringing them back in time with a blend of his imagination and creativity.',
        '7b4d8989-fc9d-4cd1-9f18-4a2de19462be', 'https://danheimmusic.com/', '', '', 'https://www.youtube.com/@Danheim',
        'https://tidal.com/artist/8412655',
        'https://open.spotify.com/embed/track/1L6qOVwLb1QIdcb9dtFPJ2?utm_source=generator'),
       ('Wardruna', '+47 55 12 34 56', 'info@wardruna.com', 'Nordic Folk', 'Norway',
        'From the deep woods now emerges Birna, Wardruna’s sixth studio album. Through his never resting dialogue with nature, main composer Einar Selvik has been searching for the voice of the bear, our lost sister of the forest. Resulting in this upcoming release, scheduled for January 24, 2025 through Sony Music and By Norse. Birna – the she-bear in Old Norse – is a work of art dedicated to the warden of the forest, nature’s caretaker, and her battles here on earth. Slowly driven out of her habitat by modern day societies, she has entered a stage of permanent hibernation. As a result, the forest is gradually dying, longing for its pulse and heart – its shepherd. Birna calls for her return.“Where the previous album Kvitravn was a step conceptually from the past to the present, Birna even more so seeks to address the here and now and the way forward,” states composer Einar Selvik. From the Runaljod trilogy (2009, 2013, 2016), exploring old Norse myths, traditions, and language through the 24 proto-Norse runes, the sound of Wardruna evolved and blended into the stripped down, acoustic compositions on Skald (2018). Kvitravn (White-Raven) (2021) explores Northern sorcery, spirit-animals, animism, and the act of creation – the ever-interchanging prolific relationship between the skaldic poet and nature itself. The record charted in 13 countries, including #1 in Canada and Austria, and #2 in Germany. ',
        '3e174263-6f80-4f67-b1e2-036213c0ed97', 'https://wardruna.com/', 'https://www.instagram.com/wardruna/', '',
        'https://www.youtube.com/channel/UCoK2X3nnuG7ug-Kk75r5rJQ', 'https://tidal.com/artist/3565132',
        'https://open.spotify.com/embed/track/0OXEH9T6QSQCrmnjdtpf4P?utm_source=generator'),
       ('Allt', '+46 8 505 123 00', 'alltband@gmail.com', 'Progressive Metalcore', 'Sweden',
        'Formed in 2020, Allt is a progressive metalcore band from Karlskoga, Sweden. Known for their audiovisual storytelling and innovative approach to metalcore, Allt has released their highly anticipated debut album, \"From The New World.\" in October 2024. The name Allt, which means \"everything\" in Swedish, reflects the band\'s philosophy towards music: never to be confined within genre boundaries and continually delivering conceptually rich compositions inspired by the world around us and the stories and myths told through the ages. Following the release of \"From The New World\", Allt supported Imminence, presenting their new album live for the first time, followed by their first-ever headline tour around Europe, selling out 13 of 18 shows in March 2025. Energized by this success, Allt made their way to North American shores for the first time in May 2025, opening for Invent Animate & Silent Planet, culminating in a slot at the prestigious Welcome To Rockville festival. ',
        'a3b7d2fd-43fc-47b3-80c1-aa64d2a0006b', 'https://shop.alltband.com/', 'https://www.instagram.com/alltband/',
        'https://www.tiktok.com/@alltband', 'https://www.youtube.com/@allt', 'https://tidal.com/artist/21078379',
        'https://open.spotify.com/embed/track/49LZHvYu1hUWIlR5Di3LWJ?utm_source=generator'),
       ('Soen', '+46 70 123 45 67', 'management@soenmusic.com', 'Progressive Metal', 'Sweden',
        'Initially formed in 2004, it was in May 2010 that the formation of Soen was officially announced. Founding member drummer Martin Lopez describes Soen’s music as “melodic, heavy and intricate”.   The group’s first song, ‘Fraccions‘, was released in October 2010 and their debut album, Cognitive, was released on 15 February 2012 receiving positive critical reception.   Since Lopez and Joel Ekelöf formed the group, Soen have never shied away from exploration and analysis of self or society, both as musicians and expressionists.   SOEN are: Joel Ekelöf – Vocals Martin Lopez – Drums, Percussion Cody Ford – Guitar Lars Åhlund – Keys, Guitar Stefan Stenberg – Bass',
        '0f037677-4b9a-4fd4-b87a-86454d3b913b', 'https://www.soenmusic.com/', 'https://www.instagram.com/soenmusic/',
        '', 'https://www.youtube.com/@SoenOfficial', 'https://tidal.com/artist/4373811',
        'https://open.spotify.com/embed/track/6lThiyFluc0jY2O8SYg3II?utm_source=generator'),
       ('Igorrr', '+33 1 40 20 50 50', 'igorrr.contact@gmail.com', 'Avant-garde Metal', 'France',
        'Igorrr is the project of French composer and producer Gautier Serre, renowned for pushing extreme music far beyond traditional genre boundaries. Blending breakcore, black and death metal, baroque classical music, electronic chaos, and surreal experimentation, Igorrr’s sound is intentionally jarring yet meticulously composed. The result is music that feels both violently unhinged and strangely elegant, shifting without warning from blast beats and distorted screams to harpsichords, operatic vocals, and absurd melodic twists. Early releases like Poisson Soluble and Nostril established the project’s chaotic identity, fusing frenetic electronic rhythms with extreme metal and warped classical motifs. Hallelujah expanded the scope with richer production, baroque choirs, and a stronger sense of contrast between beauty and brutality. With Savage Sinusoid, Igorrr leaned further into metal, incorporating black metal vocals and heavier structures while retaining its surreal edge. Spirituality and Distortion pushed the project to its most ambitious scale, exploring global influences, ritualistic themes, and expansive atmospheres. Each Igorrr album feels like a carefully controlled descent into madness—unpredictable, theatrical, and unmistakably unique.',
        '010ac405-9293-4e22-aca3-b1c9dfbe454c', 'https://igorrr.com/', 'https://instagram.com/igorrr_music',
        'https://www.tiktok.com/@igorrr_official', 'https://www.youtube.com/@IgorrrOfficial',
        'https://tidal.com/artist/3949100',
        'https://open.spotify.com/embed/track/6DUvpC2bFViefEhZ65NGgk?utm_source=generator'),
       ('Devin Townsend', '+1 604 555 0192', 'info@hevydevy.com', 'Progressive Metal', 'Canada',
        'Devin Townsend’s career is one of many distinct eras. He’s been the leader of Strapping Young Lad, the lynchpin of the Devin Townsend Project and the co-architect of country duo Casualties of Cool, all while maintaining his prolific and lauded solo project. Now, the polymath’s newest era starts with PowerNerd: a succinct but still progressive record that pulls from its mastermind’s childhood love of vintage rock. From the moment the title track ignites the record with a roar of “PowerNerd!”, Devin’s 28th studio project is a gallop of melody, noise and emotion. The opener and “Knuckledragger” are all-out, high-speed rampages, their impact intensified by their central figure’s signature ‘wall of sound’ production style. However, that aggression is far from the only thing to come from Powernerd’s 11 episodic tracks. “Dreams of Light”, by comparison, is an evocative and dynamic four-minute ballad. “Younger Lover” calms down from an explosive opening to lush verses of synths and singing, whereas “Falling Apart” and “Jainism” add texture after texture on top of dulcet, acoustic introductions. With each song also having an irresistible hook at its heart, Devin has undeniably crafted a soulful instant classic of a rock record.',
        NULL, 'https://hevydevy.com/', 'https://www.instagram.com/dvntownsend/', 'https://www.tiktok.com/@dvntownsend',
        'https://www.youtube.com/@dvntownsend', 'https://tidal.com/artist/3658981',
        'https://open.spotify.com/embed/track/3hXtTHo8l8XEPwufhz3rTk?utm_source=generator'),
       ('Linkin Park', '+1 310 555 0110', 'contact@linkinpark.com', 'Nu Metal', 'USA',
        'LINKIN PARK is the magnetic hub of an emotional and cultural community—staggering in scope, intimate in connection, and wholly unique. Blending sonic and visual inspiration under the name Xero, later Hybrid Theory, before finally settling on LINKIN PARK, Mike Shinoda, Chester Bennington, Brad Delson, Joseph Hahn, Rob Bourdon, and Dave “Phoenix” Farrell had no idea they were about to become the biggest rock band of their generation. In 2000, they released their first full-length, Hybrid Theory. Certified Diamond, it became “the bestselling debut of the 21st century.” Seven seminal albums followed: Meteora, Collision Course, Minutes To Midnight, A Thousand Suns, LIVING THINGS, The Hunting Party, and One More Light. LINKIN PARK has received multiple GRAMMY Awards, sold over 100 million albums worldwide, and notched five #1 Billboard debuts. After the tragic loss of Bennington in 2017, the band came to a devastating halt; their future obscured by grief and unanswered questions. Friendships led the way. Mike, Brad, Phoenix, and Joe began making music together again. They met Emily Armstrong and Colin Brittain, jam sessions organically morphed into recording, and LP quietly crafted a collection of songs channeling the open-hearted spontaneity of starting over: FROM ZERO. Earning some of the highest critical praise of their career, FROM ZERO peaked at #1 in 14 countries, igniting the triumphant FROM ZERO WORLD TOUR and LINKIN PARK’s continuing artistic evolution.',
        '41455c61-9787-4672-b850-1af5736c7ea3', 'https://linkinpark.com/', '', 'https://www.tiktok.com/@linkinpark',
        'https://www.youtube.com/channel/UCZU9T1ceaOgwfLRq7OKFU4Q', 'https://tidal.com/artist/14123',
        'https://open.spotify.com/embed/track/60a0Rd6pjrkxjPbaKzXjfq?utm_source=generator'),
       ('Imperial Triumphant', '+1 718 555 0144', 'it@gileadmedia.net', 'Avant-garde Black Metal', 'USA',
        'Imperial Triumphant are one of the most distinctive forces in modern extreme metal, fusing avant-garde black metal, dissonant death metal, and free-jazz chaos into a vision of New York City as a monument to excess and decay. Formed in the late 2000s, the band uses the city’s architecture, capitalism, and moral rot as both aesthetic and philosophy, translating urban dread into towering, technical brutality. Their sound is dense and suffocating—jagged riffs, unorthodox song structures, sudden tempo shifts, and improvisational brass passages collide in music that feels both ritualistic and grotesquely opulent. Their early releases, Abominamentvm and Abyssal Gods, established a murky black-metal foundation steeped in atmosphere and dissonance. Vile Luxury marked a turning point, introducing overt jazz influences and an art-deco visual identity that framed NYC as a decadent empire in decline. This concept was fully realized on Alphaville, a sprawling, chaotic album that sharpened their technicality while expanding the band’s sonic palette. With Spirit of Ecstasy, Imperial Triumphant pushed even further, incorporating swing rhythms, collaborations with jazz musicians, and moments of surreal grandeur without sacrificing heaviness. Each album acts as a chapter in a larger narrative—an escalating portrait of civilization collapsing under its own gilded weight.',
        '189e78ed-5a0c-4325-9d55-6634f98c2344', 'https://www.imperial-triumphant.com/',
        'https://www.instagram.com/imperialtriumphant/', 'https://www.tiktok.com/@imperialtriumphant',
        'https://www.youtube.com/channel/UCGAewAu26qdx3IwJn8S_uww', 'https://tidal.com/artist/6046541',
        'https://open.spotify.com/embed/track/2jV8ASwqzFdy1Xmdjmvt1R?utm_source=generator'),
       ('Slipknot', '+1 515 555 0166', 'mgmt@slipknot1.com', 'Nu Metal', 'USA', NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL),
       ('Alice in Chains', '+1 206 555 0188', 'info@aliceinchains.com', 'Grunge', 'USA', NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL),
       ('Sepultura', '+55 11 3060 0000', 'contact@sepultura.com.br', 'Groove Metal', 'Brazil', NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL),
       ('Periphery', '+1 301 555 0122', 'periphery.mgmt@gmail.com', 'Progressive Metal', 'USA', NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL),
       ('Spiritbox', '+1 250 555 0133', 'spiritbox@palechord.com', 'Progressive Metalcore', 'Canada', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL),
       ('Amira Elfeky', '+1 213 555 0177', 'amira.elfeky@mgmt.com', 'Nu-gaze', 'USA', NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL),
       ('HEALTH', '+1 323 555 0144', 'health@youwillloveeachother.com', 'Industrial Rock', 'USA', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL),
       ('Bad Omens', '+1 804 555 0155', 'badomens@sumerianrecords.com', 'Alternative Metal', 'USA', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL),
       ('Jutes', '+1 416 555 0199', 'jutes.mgmt@gmail.com', 'Alt Rock', 'Canada', NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL),
       ('In Mourning', '+46 23 123 45 00', 'info@inmourning.net', 'Progressive Melodic Death Metal', 'Sweden', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('Alcest', '+33 4 91 12 34 56', 'alcest.band@gmail.com', 'Blackgaze', 'France', NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL),
       ('Acid Bath', '+1 504 555 0122', 'acidbath@rotten.com', 'Sludge Metal', 'USA', NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL),
       ('Meshuggah', '+46 90 123 45 67', 'meshuggah@management.se', 'Extreme Progressive Metal', 'Sweden', NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL),
       ('Derby Motoretas Burrito Kachimba', '+34 954 12 34 56', 'kinkidelia@derbymotoretas.com', 'Psychedelic Rock',
        'Spain', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('Limp Bizkit', '+1 904 555 0100', 'bizkit.mgmt@gmail.com', 'Nu Metal', 'USA', NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL),
       ('Slaughter to Prevail', '+7 495 123 45 67', 'alex.terrible@stp.com', 'Deathcore', 'Russia', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL),
       ('Leprous', '+47 35 55 66 77', 'management@leprous.net', 'Progressive Rock', 'Norway', NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL),
       ('Poppy', '+1 617 555 0188', 'poppy@sumerianrecords.com', 'Avant-pop / Metal', 'USA', NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL),
       ('Lamb of God', '+1 804 555 0111', 'log@epicrecords.com', 'Groove Metal', 'USA', NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL),
       ('Knocked Loose', '+1 502 555 0122', 'knockedloose@purenoise.net', 'Hardcore Punk', 'USA', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL),
       ('Bambie Thug', '+353 1 800 1234', 'bambie@ouijapop.com', 'Ouija-pop', 'Ireland', NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL),
       ('Ghost', '+46 58 123 45 67', 'tobias.forge@lomavista.com', 'Occult Rock', 'Sweden', NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL),
       ('Batushka', '+48 85 123 45 67', 'batushkaband@gmail.com', 'Black Metal', 'Poland', NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL),
       ('Bring Me The Horizon', '+44 114 555 0199', 'bmth@rawpower-mgmt.com', 'Alternative Metal', 'UK', NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL),
       ('Three Days Grace', '+1 416 555 0177', 'info@threedaysgrace.com', 'Alternative Rock', 'Canada', NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL),
       ('Iron Maiden', '+44 20 8946 0000', 'phantom@ironmaiden.com', 'Heavy Metal', 'UK', NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL),
       ('Wheel', '+358 40 123 4567', 'management@wheelband.net', 'Progressive Metal', 'Finland', NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL),
       ('Haken', '+44 20 7946 0123', 'info@hakenmusic.com', 'Progressive Metal', 'United Kingdom', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL),
       ('Sleep Token', '+44 20 8123 4567', 'ritual@sleep-token.com', 'Alternative Metal', 'United Kingdom', NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL),
       ('Arch Enemy', '+46 8 123 456 78', 'contact@archenemy.net', 'Melodic Death Metal', 'Sweden', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL),
       ('Power Trip', '+1-214-555-0198', 'booking@powertrip.band', 'Crossover Thrash', 'USA', NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL),
       ('Cannibal Corpse', '+1 813 555 0144', 'info@cannibalcorpse.net', 'Death Metal', 'USA', NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL),
       ('Mayhem', '+47 22 12 34 56', 'order@thetruemayhem.com', 'Black Metal', 'Norway', NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL),
       ('Porcupine Tree', '+44 161 496 0234', 'info@porcupinetree.com', 'Progressive Rock', 'United Kingdom', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('A Day to Remember', '+1 352 555 0120', 'management@adtr.com', 'Metalcore', 'USA', NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL),
       ('Bala', '+34 600 000 000', 'balaband@gmail.com', 'Grunge/Stoner Rock', 'Spain', NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL),
       ('A.N.I.M.A.L.', '+54 11 4321 0000', 'prensa@animal.com.ar', 'Groove Metal', 'Argentina', NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL),
       ('Sôber', '+34 912 000 000', 'contratacion@soberweb.com', 'Alternative Metal', 'Spain', NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL),
       ('Molotov', '+52 55 5123 4567', 'contacto@molotov.com.mx', 'Rap Rock', 'Mexico', NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL),
       ('Biznaga', '+34 611 222 333', 'biznagapunk@gmail.com', 'Punk Rock', 'Spain', NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL),
       ('Diamante Eléctrico', '+57 310 123 4567', 'info@diamanteelectrico.com', 'Indie Rock', 'Colombia', NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL),
       ('Hamlet', '+34 913 444 555', 'info@hamlet1.com', 'Alternative Metal', 'Spain', NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL),
       ('Angelus Apatrida', '+34 967 111 222', 'management@angelusapatrida.com', 'Thrash Metal', 'Spain', NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL),
       ('El Cuarteto de Nos', '+598 2 900 0000', 'info@cuartetodenos.com.uy', 'Alternative Rock', 'Uruguay', NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL),
       ('Mon Laferte', '+56 2 2123 4567', 'contacto@monlaferte.com', 'Alternative Pop/Rock', 'Chile', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL),
       ('Bunbury', '+34 910 888 777', 'oficina@enriquebunbury.com', 'Rock', 'Spain', NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL),
       ('Fito & Fitipaldis', '+34 944 000 111', 'info@fitoyfitipaldis.com', 'Rock and Roll', 'Spain', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL),
       ('Soziedad Alkoholika', '+34 945 123 456', 'info@soziedadalkoholika.com', 'Thrash Metal/Crossover', 'Spain',
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('Toundra', '+34 655 444 333', 'toundraband@gmail.com', 'Post-Rock', 'Spain', NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL),
       ('Babasónicos', '+54 11 4777 8888', 'management@babasonicos.com', 'Alternative Rock', 'Argentina', NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL),
       ('Vetusta Morla', '+34 918 222 333', 'info@vetustamorla.com', 'Indie Rock', 'Spain', NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL),
       ('Ska-P', '+34 915 666 777', 'contratacion@ska-p.com', 'Ska Punk', 'Spain', NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL);

INSERT INTO artist_images (image_url, artist_id)
VALUES ('08879063-dc9f-42aa-bb1f-c48ccdc4a6a5', 1),
       ('ae82aee5-7bc7-4a13-b250-6c2f37a62e12', 2),
       ('75693dab-e7c2-4892-8ddc-5925d1ba741c', 3),
       ('4213b8e6-7227-46ba-8b91-1db91c29bb0b', 4),
       ('2b37fd16-439b-43f5-b8be-5876010ef980', 5),
       ('3e07fa67-a574-493b-ba78-35d004e52943', 6),
       ('809949dd-1946-4d43-a94e-53d1d752ebbd', 7),
       ('050c7d4f-2057-42ad-a041-113604d14a25', 9),
       ('bb3284fc-6863-415f-be76-d485335269a6', 8),
       ('b60c3313-bbbb-42bd-b1ec-4e78843cfc2f', 10),
       ('738cf030-e1c0-4ad1-a0ea-a355eedfaa39', 11),
       ('64eb760d-5715-4be6-bc5e-9330aaf66229', 12),
       ('0ff9c647-774a-435b-9f87-3e5d882955a9', 13),
       ('352e5e55-53ee-47fa-810c-70a6fa0ac340', 14),
       ('1c748de7-f998-4601-8683-714ab3be19d8', 15),
       ('ca7573d7-7359-419b-947d-c5a410c75a40', 16),
       ('0d24dc09-c764-4412-9ecf-c8f846eda05c', 17),
       ('6bbc9be6-783d-4f17-8be3-b669ae6d86be', 18),
       ('e5e6730d-3ad8-4257-9044-03763a4550b1', 19),
       ('7061e559-2339-4319-b367-792d01e2110f', 20),
       ('93757731-ec6f-41d5-9b32-e47079168bfc', 21),
       ('de18ff10-f6d5-46cb-8277-698d8eb1fa38', 22),
       ('906354e9-dd50-4679-ab5c-32c7e8e89aed', 27),
       ('3925bf50-370a-4f22-93c4-e263b9bdd000', 23),
       ('5bf4bbb5-88f2-4c8a-905a-dae972a9cec6', 24),
       ('f1b619cf-0e7b-4837-bb76-4c15a857a437', 25),
       ('2bf57511-4f81-4777-b95f-af034dbcdc6f', 26);

-- MIÉRCOLES 5 DE AGOSTO DE 2026
-- Escenario: Asgard del Sur (Mainstream/Headliners)
INSERT INTO performances (start_time, end_time, artist_id, stage_id)
VALUES ('2026-08-05 19:00:00', '2026-08-05 20:00:00', 52, 1), -- Three Days Grace
       ('2026-08-05 20:30:00', '2026-08-05 21:45:00', 13, 1), -- Evanescence
       ('2026-08-05 22:30:00', '2026-08-06 00:30:00', 11, 1), -- Metallica
       ('2026-08-06 01:15:00', '2026-08-06 02:30:00', 42, 1);
-- Limp Bizkit

-- Escenario: Valhalla de Triana (Prog/Alt)
INSERT INTO performances (start_time, end_time, artist_id, stage_id)
VALUES ('2026-08-05 18:30:00', '2026-08-05 19:30:00', 54, 2), -- Wheel
       ('2026-08-05 19:45:00', '2026-08-05 20:45:00', 44, 2), -- Leprous
       ('2026-08-05 21:15:00', '2026-08-05 22:30:00', 23, 2), -- Soen
       ('2026-08-05 23:15:00', '2026-08-06 00:45:00', 14, 2), -- Tool
       ('2026-08-06 01:15:00', '2026-08-06 02:30:00', 24, 2);
-- Igorrr

-- Escenario: Fenrir del Al-Andalus (Extreme)
INSERT INTO performances (start_time, end_time, artist_id, stage_id)
VALUES ('2026-08-05 18:30:00', '2026-08-05 19:45:00', 50, 3), -- Batushka
       ('2026-08-05 20:15:00', '2026-08-05 21:30:00', 17, 3), -- Deafheaven
       ('2026-08-05 21:45:00', '2026-08-05 23:00:00', 43, 3), -- Slaughter to Prevail
       ('2026-08-05 23:30:00', '2026-08-06 00:45:00', 8, 3),  -- Lorna Shore
       ('2026-08-06 01:00:00', '2026-08-06 02:15:00', 6, 3);
-- Behemoth


-- Escenario: Drakkar de Guadalquivir (Español & Latam)
INSERT INTO performances (start_time, end_time, artist_id, stage_id)
VALUES ('2026-08-05 19:00:00', '2026-08-05 20:00:00', 63, 4), -- Bala
       ('2026-08-05 20:30:00', '2026-08-05 21:45:00', 64, 4), -- A.N.I.M.A.L.
       ('2026-08-05 22:15:00', '2026-08-05 23:30:00', 65, 4), -- Sôber
       ('2026-08-06 00:00:00', '2026-08-06 01:30:00', 66, 4);
-- Molotov

-- JUEVES 6 DE AGOSTO DE 2026
-- Escenario: Asgard del Sur
INSERT INTO performances (start_time, end_time, artist_id, stage_id)
VALUES ('2026-08-06 19:00:00', '2026-08-06 20:00:00', 19, 1), -- Pantera
       ('2026-08-06 20:30:00', '2026-08-06 21:45:00', 18, 1), -- Judas Priest
       ('2026-08-06 22:30:00', '2026-08-07 00:30:00', 53, 1), -- Iron Maiden
       ('2026-08-07 01:15:00', '2026-08-07 02:30:00', 30, 1);
-- Sepultura

-- Escenario: Valhalla de Triana
INSERT INTO performances (start_time, end_time, artist_id, stage_id)
VALUES ('2026-08-06 18:30:00', '2026-08-06 19:30:00', 55, 2), -- Haken
       ('2026-08-06 19:45:00', '2026-08-06 20:45:00', 12, 2), -- Mastodon
       ('2026-08-06 21:15:00', '2026-08-06 22:30:00', 1, 2),  -- Gojira
       ('2026-08-06 23:00:00', '2026-08-07 00:30:00', 9, 2),  -- Opeth
       ('2026-08-07 01:00:00', '2026-08-07 02:15:00', 40, 2);
-- Meshuggah

-- Escenario: Fenrir del Al-Andalus
INSERT INTO performances (start_time, end_time, artist_id, stage_id)
VALUES ('2026-08-06 20:00:00', '2026-08-06 21:00:00', 7, 3),  -- Fit For An Autopsy
       ('2026-08-06 21:30:00', '2026-08-06 22:45:00', 15, 3), -- Whitechapel
       ('2026-08-06 23:30:00', '2026-08-07 01:00:00', 46, 3), -- Lamb of God
       ('2026-08-07 01:30:00', '2026-08-07 02:45:00', 47, 3);
-- Knocked Loose

-- Escenario: Drakkar de Guadalquivir
INSERT INTO performances (start_time, end_time, artist_id, stage_id)
VALUES ('2026-08-06 19:00:00', '2026-08-06 20:00:00', 67, 4), -- Biznaga
       ('2026-08-06 20:30:00', '2026-08-06 21:30:00', 68, 4), -- Diamante Eléctrico
       ('2026-08-06 22:00:00', '2026-08-06 23:15:00', 69, 4), -- Hamlet
       ('2026-08-06 23:45:00', '2026-08-07 01:15:00', 70, 4), -- Angelus Apatrida
       ('2026-08-07 01:30:00', '2026-08-07 02:45:00', 71, 4);
-- El Cuarteto de Nos

-- VIERNES 7 DE AGOSTO DE 2026
-- Escenario: Asgard del Sur
INSERT INTO performances (start_time, end_time, artist_id, stage_id)
VALUES ('2026-08-07 19:00:00', '2026-08-07 20:00:00', 51, 1), -- BMTH
       ('2026-08-07 20:30:00', '2026-08-07 21:45:00', 49, 1), -- Ghost
       ('2026-08-07 22:30:00', '2026-08-08 00:30:00', 3, 1),  -- Rammstein
       ('2026-08-08 01:15:00', '2026-08-08 02:30:00', 35, 1);
-- Bad Omens

-- Escenario: Valhalla de Triana
INSERT INTO performances (start_time, end_time, artist_id, stage_id)
VALUES ('2026-08-07 18:00:00', '2026-08-07 19:00:00', 56, 2), -- Sleep Token
       ('2026-08-07 19:30:00', '2026-08-07 20:30:00', 32, 2), -- Spiritbox
       ('2026-08-07 21:00:00', '2026-08-07 22:15:00', 10, 2), -- Jinjer
       ('2026-08-07 23:00:00', '2026-08-08 00:30:00', 5, 2),  -- Deftones
       ('2026-08-08 01:00:00', '2026-08-08 02:15:00', 34, 2);
-- HEALTH

-- Escenario: Fenrir del Al-Andalus
INSERT INTO performances (start_time, end_time, artist_id, stage_id)
VALUES ('2026-08-07 19:30:00', '2026-08-07 20:30:00', 57, 3), -- Arch Enemy
       ('2026-08-07 21:00:00', '2026-08-07 22:15:00', 58, 3), -- Power Trip
       ('2026-08-07 23:00:00', '2026-08-08 00:30:00', 59, 3), -- Cannibal Corpse
       ('2026-08-08 01:00:00', '2026-08-08 02:15:00', 60, 3);
-- Mayhem

-- Escenario: Drakkar de Guadalquivir
INSERT INTO performances (start_time, end_time, artist_id, stage_id)
VALUES ('2026-08-07 18:30:00', '2026-08-07 19:30:00', 41, 4), -- Derby Motoreta's
       ('2026-08-07 20:00:00', '2026-08-07 21:15:00', 72, 4), -- Mon Laferte
       ('2026-08-07 22:00:00', '2026-08-07 23:30:00', 73, 4), -- Bunbury
       ('2026-08-08 00:00:00', '2026-08-08 01:30:00', 74, 4), -- Fito & Fitipaldis
       ('2026-08-08 01:45:00', '2026-08-08 03:00:00', 75, 4);
-- Soziedad Alkoholika

-- SÁBADO 8 DE AGOSTO DE 2026
-- Escenario: Asgard del Sur
INSERT INTO performances (start_time, end_time, artist_id, stage_id)
VALUES ('2026-08-08 19:00:00', '2026-08-08 20:15:00', 29, 1), -- Alice in Chains
       ('2026-08-08 21:00:00', '2026-08-08 22:30:00', 28, 1), -- Slipknot
       ('2026-08-08 23:30:00', '2026-08-09 01:30:00', 26, 1), -- Linkin Park
       ('2026-08-09 01:45:00', '2026-08-09 03:00:00', 62, 1);
-- A Day To Remember

-- Escenario: Valhalla de Triana
INSERT INTO performances (start_time, end_time, artist_id, stage_id)
VALUES ('2026-08-08 18:15:00', '2026-08-08 19:15:00', 31, 2), -- Periphery
       ('2026-08-08 19:45:00', '2026-08-08 20:45:00', 16, 2), -- VOLA
       ('2026-08-08 21:15:00', '2026-08-08 22:30:00', 25, 2), -- Devin Townsend
       ('2026-08-08 23:15:00', '2026-08-09 00:45:00', 61, 2), -- Porcupine Tree
       ('2026-08-09 01:15:00', '2026-08-09 02:30:00', 2, 2);
-- Loathe

-- Escenario: Fenrir del Al-Andalus
INSERT INTO performances (start_time, end_time, artist_id, stage_id)
VALUES ('2026-08-08 20:00:00', '2026-08-08 21:00:00', 48, 3), -- Bambie Thug
       ('2026-08-08 21:30:00', '2026-08-08 22:30:00', 45, 3), -- Poppy
       ('2026-08-08 23:15:00', '2026-08-09 00:30:00', 27, 3), -- Imperial Triumphant
       ('2026-08-09 01:00:00', '2026-08-09 02:15:00', 39, 3);
-- Acid Bath

-- Escenario: Drakkar de Guadalquivir
INSERT INTO performances (start_time, end_time, artist_id, stage_id)
VALUES ('2026-08-08 19:00:00', '2026-08-08 20:00:00', 76, 4), -- Toundra
       ('2026-08-08 20:30:00', '2026-08-08 21:45:00', 77, 4), -- Babasónicos
       ('2026-08-08 22:15:00', '2026-08-08 23:45:00', 78, 4), -- Vetusta Morla
       ('2026-08-09 00:15:00', '2026-08-09 01:45:00', 79, 4); -- Ska-P