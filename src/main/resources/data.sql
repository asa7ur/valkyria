INSERT IGNORE INTO users (email, password, enabled, first_name, last_name, birth_date, phone)
VALUES ('garik@email.com', '$2a$12$5ML1PTu9DvomnxZP.TDen.nc/5.L0AU2x2A82Yt1BVv1eWenkuO7q', true, 'Garik', 'Asatryan',
        '1996-12-30', '666666666');

INSERT IGNORE INTO roles (name)
VALUES ('Admin'),
       ('Manager'),
       ('User');

INSERT IGNORE INTO user_role (user_id, role_id)
VALUES (1, 1);

INSERT IGNORE INTO artists (name, phone, email, genre, country)
VALUES ('Gojira', '+33 1 42 67 00 00', 'contact@gojira-music.com', 'Progressive Death Metal', 'France'),
       ('Loathe', '+44 20 7946 0000', 'info@loathe.uk', 'Metalcore', 'UK'),
       ('Rammstein', '+49 30 2091 0000', 'management@rammstein.de', 'Industrial Metal', 'Germany'),
       ('Faetooth', '+1 213 555 0198', 'faetoothband@gmail.com', 'Doom Metal', 'USA'),
       ('Deftones', '+1 916 555 0123', 'contact@deftones.com', 'Alternative Metal', 'USA'),
       ('Behemoth', '+48 22 654 00 00', 'behemoth.mgmt@new-east.pl', 'Blackened Death Metal', 'Poland'),
       ('Fit For An Autopsy', '+1 201 555 0144', 'ffaa@metal-management.com', 'Deathcore', 'USA'),
       ('Lorna Shore', '+1 732 555 0166', 'lornashore@touring.com', 'Deathcore', 'USA'),
       ('Opeth', '+46 8 123 456 00', 'management@opeth.com', 'Progressive Metal', 'Sweden'),
       ('Jinjer', '+380 44 234 5678', 'jinjer.booking@gmail.com', 'Progressive Metal', 'Ukraine'),
       ('Metallica', '+1 415 555 0101', 'hq@metallica.com', 'Thrash Metal', 'USA'),
       ('Mastodon', '+1 404 555 0188', 'info@mastodonrocks.com', 'Sludge Metal', 'USA'),
       ('Evanescence', '+1 501 555 0155', 'mgmt@evanescence.com', 'Alternative Metal', 'USA'),
       ('Tool', '+1 310 555 0199', 'toolband@management.com', 'Progressive Metal', 'USA'),
       ('Whitechapel', '+1 865 555 0177', 'whitechapel@metalblade.com', 'Deathcore', 'USA'),
       ('VOLA', '+45 31 12 34 56', 'contact@volaband.com', 'Progressive Metal', 'Denmark'),
       ('Deafheaven', '+1 415 555 0122', 'info@deafheaven.com', 'Blackgaze', 'USA'),
       ('Judas Priest', '+44 20 7946 0555', 'mgmt@judaspriest.com', 'Heavy Metal', 'UK'),
       ('Pantera', '+1 817 555 0199', 'info@pantera.com', 'Groove Metal', 'USA'),
       ('Danheim', '+45 70 10 20 30', 'contact@danheimmusic.com', 'Neofolk', 'Denmark'),
       ('Wardruna', '+47 55 12 34 56', 'info@wardruna.com', 'Nordic Folk', 'Norway'),
       ('Allt', '+46 8 505 123 00', 'alltband@gmail.com', 'Progressive Metalcore', 'Sweden'),
       ('Soen', '+46 70 123 45 67', 'management@soenmusic.com', 'Progressive Metal', 'Sweden'),
       ('Igorrr', '+33 1 40 20 50 50', 'igorrr.contact@gmail.com', 'Avant-garde Metal', 'France'),
       ('Devin Townsend', '+1 604 555 0192', 'info@hevydevy.com', 'Progressive Metal', 'Canada'),
       ('Linkin Park', '+1 310 555 0110', 'contact@linkinpark.com', 'Nu Metal', 'USA'),
       ('Imperial Triumphant', '+1 718 555 0144', 'it@gileadmedia.net', 'Avant-garde Black Metal', 'USA'),
       ('Slipknot', '+1 515 555 0166', 'mgmt@slipknot1.com', 'Nu Metal', 'USA'),
       ('Alice in Chains', '+1 206 555 0188', 'info@aliceinchains.com', 'Grunge', 'USA'),
       ('Sepultura', '+55 11 3060 0000', 'contact@sepultura.com.br', 'Groove Metal', 'Brazil'),
       ('Periphery', '+1 301 555 0122', 'periphery.mgmt@gmail.com', 'Progressive Metal', 'USA'),
       ('Spiritbox', '+1 250 555 0133', 'spiritbox@palechord.com', 'Progressive Metalcore', 'Canada'),
       ('Amira Elfeky', '+1 213 555 0177', 'amira.elfeky@mgmt.com', 'Nu-gaze', 'USA'),
       ('HEALTH', '+1 323 555 0144', 'health@youwillloveeachother.com', 'Industrial Rock', 'USA'),
       ('Bad Omens', '+1 804 555 0155', 'badomens@sumerianrecords.com', 'Alternative Metal', 'USA'),
       ('Jutes', '+1 416 555 0199', 'jutes.mgmt@gmail.com', 'Alt Rock', 'Canada'),
       ('In Mourning', '+46 23 123 45 00', 'info@inmourning.net', 'Progressive Melodic Death Metal', 'Sweden'),
       ('Alcest', '+33 4 91 12 34 56', 'alcest.band@gmail.com', 'Blackgaze', 'France'),
       ('Acid Bath', '+1 504 555 0122', 'acidbath@rotten.com', 'Sludge Metal', 'USA'),
       ('Meshuggah', '+46 90 123 45 67', 'meshuggah@management.se', 'Extreme Progressive Metal', 'Sweden'),
       ('Derby Motoreta\'s Burrito Kachimba', '+34 954 12 34 56', 'kinkidelia@derbymotoretas.com', 'Psychedelic Rock',
        'Spain'),
       ('Limp Bizkit', '+1 904 555 0100', 'bizkit.mgmt@gmail.com', 'Nu Metal', 'USA'),
       ('Slaughter to Prevail', '+7 495 123 45 67', 'alex.terrible@stp.com', 'Deathcore', 'Russia'),
       ('Leprous', '+47 35 55 66 77', 'management@leprous.net', 'Progressive Rock', 'Norway'),
       ('Poppy', '+1 617 555 0188', 'poppy@sumerianrecords.com', 'Avant-pop / Metal', 'USA'),
       ('Lamb of God', '+1 804 555 0111', 'log@epicrecords.com', 'Groove Metal', 'USA'),
       ('Knocked Loose', '+1 502 555 0122', 'knockedloose@purenoise.net', 'Hardcore Punk', 'USA'),
       ('Bambie Thug', '+353 1 800 1234', 'bambie@ouijapop.com', 'Ouija-pop', 'Ireland'),
       ('Ghost', '+46 58 123 45 67', 'tobias.forge@lomavista.com', 'Occult Rock', 'Sweden'),
       ('Batushka', '+48 85 123 45 67', 'batushkaband@gmail.com', 'Black Metal', 'Poland'),
       ('Bring Me The Horizon', '+44 114 555 0199', 'bmth@rawpower-mgmt.com', 'Alternative Metal', 'UK'),
       ('Three Days Grace', '+1 416 555 0177', 'info@threedaysgrace.com', 'Alternative Rock', 'Canada'),
       ('Iron Maiden', '+44 20 8946 0000', 'phantom@ironmaiden.com', 'Heavy Metal', 'UK');

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