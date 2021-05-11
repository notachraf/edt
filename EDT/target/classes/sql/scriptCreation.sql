DROP DATABASE IF EXISTS emploi_du_temps;

CREATE DATABASE IF NOT EXISTS emploi_du_temps;
USE emploi_du_temps;


CREATE TABLE IF NOT EXISTS `Module` (
  `mod_id` int NOT NULL AUTO_INCREMENT,
  `mod_nom` varchar(100) NOT NULL,
  `mod_nb_td` int DEFAULT NULL,
  `mod_nb_tp` varchar(100) DEFAULT NULL,
  `mod_nb_cm` varchar(100) DEFAULT NULL,
  `mod_duree_td` float DEFAULT NULL,
  `mod_duree_tp` float DEFAULT NULL,
  `mod_duree_cm` float DEFAULT NULL,
  PRIMARY KEY (`mod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `Professeur` (
  `prof_id` int NOT NULL AUTO_INCREMENT,
  `prof_nom` varchar(50) NOT NULL,
  `prof_cours` varchar(1000) NOT NULL,
  PRIMARY KEY (`prof_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Table des professeurs';


CREATE TABLE IF NOT EXISTS `Salle` (
  `salle_id` int NOT NULL AUTO_INCREMENT,
  `salle_nom` text NOT NULL,
  `salle_capacite` int NOT NULL,
  `salle_type` varchar(30) NOT NULL,
  PRIMARY KEY (`salle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `Promotion` (
  `promo_id` int NOT NULL AUTO_INCREMENT,
  `promo_nb_eleves` int NOT NULL,
  `promo_nb_groupes` int NOT NULL,
  `promo_nom` varchar(50) NOT NULL,
  `promo_cours` varchar(1000) NOT NULL,
  `promo_date` date NOT NULL,
  PRIMARY KEY (`promo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO Module VALUES
(1,"IN100", 11,  0, 12, 180,   0, 90 ),
(2,"IN101", 12, 10, 21, 180, 110, 80 ),
(3,"IN102", 21,  9, 12, 120, 110, 90 ),
(4,"IN103", 23, 11,  3, 120, 110, 100 ),
(5,"IN104", 9 ,  0,  4, 180,   0, 90 ),
(6,"IN105", 7 ,  3,  7, 180, 110, 90 ),
(7,"B100", 4 ,  1, 11, 120, 110, 90 ),
(8,"B101", 10,  0,  7, 180,   0, 60 ),
(9,"B102", 18,  0, 19, 180,   0, 50 ),
(10,"B103", 19, 11, 18, 120, 110, 90 ),
(12,"B104", 12,  8, 11, 180, 110, 60 ),
(13,"B105", 14,  0, 14, 180,   0, 90 ),
(14,"M100", 4 ,  1, 11, 120, 110, 90 ),
(15,"M101", 10,  0,  7, 180,   0, 60 ),
(16,"M102", 18,  0, 19, 180,   0, 50 ),
(17,"M103", 19, 11, 18, 120, 110, 90 ),
(18,"M104", 12,  8, 11, 180, 110, 60 ),
(19,"M105", 14,  0, 14, 180,   0, 90 ),
(20,"C100", 4 ,  1, 11, 120, 110, 90 ),
(21,"C101", 10,  0,  7, 180,   0, 60 ),
(22,"C102", 18,  0, 19, 180,   0, 50 ),
(23,"C103", 19, 11, 18, 120, 110, 90 ),
(24,"C104", 12,  8, 11, 180, 110, 60 ),
(25,"C105", 14,  0, 14, 180,   0, 90 ),
(26,"P100", 4 ,  1, 11, 120, 110, 90 ),
(27,"P101", 10,  0,  7, 180,   0, 60 ),
(28,"P102", 18,  0, 19, 180,   0, 50 ),
(29,"P103", 19, 11, 18, 120, 110, 90 ),
(30,"P104", 12,  8, 11, 180, 110, 60 ),
(31,"P105", 14,  0, 14, 180,   0, 90 ),
(32,"IN200", 11,  0, 12, 180,   0, 90 ),
(33,"IN201", 12, 10, 21, 180, 110, 80 ),
(34,"IN202", 21,  9, 12, 120, 110, 90 ),
(35,"IN203", 23, 11,  3, 120, 110, 100 ),
(36,"IN204", 9 ,  0,  4, 180,   0, 90 ),
(37,"IN205", 7 ,  3,  7, 180, 110, 90 ),
(38,"B200", 4 ,  1, 11, 120, 110, 90 ),
(39,"B201", 10,  0,  7, 180,   0, 60 ),
(40,"B202", 18,  0, 19, 180,   0, 50 ),
(41,"B203", 19, 11, 18, 120, 110, 90 ),
(42,"B204", 12,  8, 11, 180, 110, 60 ),
(43,"B205", 14,  0, 14, 180,   0, 90 ),
(44,"M200", 4 ,  1, 11, 120, 110, 90 ),
(45,"M201", 10,  0,  7, 180,   0, 60 ),
(46,"M202", 18,  0, 19, 180,   0, 50 ),
(47,"M203", 19, 11, 18, 120, 110, 90 ),
(48,"M204", 12,  8, 11, 180, 110, 60 ),
(49,"M205", 14,  0, 14, 180,   0, 90 ),
(50,"C200", 4 ,  1, 11, 120, 110, 90 ),
(51,"C201", 10,  0,  7, 180,   0, 60 ),
(52,"C202", 18,  0, 19, 180,   0, 50 ),
(53,"C203", 19, 11, 18, 120, 110, 90 ),
(54,"C204", 12,  8, 11, 180, 110, 60 ),
(55,"C205", 14,  0, 14, 180,   0, 90 ),
(56,"P200", 4 ,  1, 11, 120, 110, 90 ),
(57,"P201", 10,  0,  7, 180,   0, 60 ),
(58,"P202", 18,  0, 19, 180,   0, 50 ),
(59,"P203", 19, 11, 18, 120, 110, 90 ),
(60,"P204", 12,  8, 11, 180, 110, 60 ),
(61,"P205", 14,  0, 14, 180,   0, 90 );

INSERT INTO Salle VALUES (1, "Buffon", "32", "TD"),
                         (2, "Descartes", "32", "TD"),
                         (3, "Bertin", "200", "CM"),
                         (4, "Centre", "32", "TD"),
                         (5, "Fermat", "32", "TD"),
                         (6, "A", "20", "TP"),
                         (7, "B", "32", "TD"),
                         (8, "C", "32", "TP"),
                         (9, "D", "150", "CM"),
                         (10, "E", "160", "CM"),
                         (11, "F", "32", "TD"),
                         (12, "G", "32", "TP"),
                         (13, "H", "32", "TD"),
                         (14, "I", "200", "CM"),
                         (15, "J", "32", "TD"),
                         (16, "K", "100", "CM"),
                         (17, "L", "32", "TD"),
                         (18, "M", "200", "CM"),
                         (19, "N", "32", "TD"),
                         (20, "O", "200", "CM"),
                         (21, "P", "32", "TP"),
                         (22, "Q", "32", "TP"),
                         (23, "R", "200", "TD"),
                         (24, "S", "32", "TD"),
                         (25, "T", "200", "TD"),
                         (26, "U", "32", "TD"),
                         (27, "V", "32", "TP"),
                         (28, "W", "32", "TD"),
                         (29, "X", "32", "TD"),
                         (30, "Y", "32", "TD"),
                         (31, "Z", "32", "TD");

INSERT INTO Promotion VALUES (1, 200, 9, "L1 INFO", "IN100, IN101, IN102, IN103, IN104, IN105", "2021-02-01"),
                             (2, 180, 6, "L1 BIO", "B100, B101, B102, B103, B104, B105", "2021-02-01" );



INSERT INTO Professeur VALUES
(1,"Lemoine", "IN101, IN100, IN203, IN200"),
(2,"Delaunay", "B101, B100, B203, IN200"),
(3,"Martin", "B101, B100, IN203, P200"),
(4,"Bernard", "IN101, M100, M203, M200"),
(5,"Thomas", "C101, IN200, B103, IN200"),
(6,"Petit", "C101, C100, M203, M200"),
(7,"Colin", "P101, P100, P203, P200"),
(8,"Robert", "P101, P100, P102, P200"),
(9,"Picard", "M201, M204, M203, M205"),
(10,"Roger", "C201, C202, C203, C200"),
(11,"Dumas", "C101, C205, C204, IN202"),
(12,"Millet", "P100, P101, P102, P103, P104, P105"),
(13,"Collet", "P200, P201, P202, P203, P204, P205"),
(14,"Bailly", "M100, M101, M102, M103, M104, M105"),
(15,"Fabre", "IN100, IN101, IN102, IN103, IN104, IN105"),
(16,"Payet", "M200, M201, M202, M203, M204, M205"),
(17,"Rousseau", "IN200, IN201, IN202, IN203, IN204, IN205"),
(18,"Faure", "C100, C101, C102, C103, C104, C105"),
(19,"Andre", "C200, C201, C202, C203, C204, C205"),
(20,"Mercier", "B101, B200, IN103, P205"),
(21,"Lopez", "IN101, B100, IN203, M200"),
(22,"Lecompte", "C101, C100, C203, P105"),
(23,"Garcia", "B105, B103, IN203, P203"),
(24,"Clement", "B201, B200, P203, P200"),
(25,"Huet", "C101, M100, M203, P200"),
(26,"Marechal", "B100, B105, IN103, P104"),
(27,"Antoine", "B201, B104, M203, P103"),
(28,"Masson", "B105, B101, IN203, P202"),
(29,"Adam", "B101, B100, B203, IN200"),
(30,"Perrin", "B100, B200, IN200, P200");




