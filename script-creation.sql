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
  PRIMARY KEY (`prof_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Table des professeurs';


CREATE TABLE IF NOT EXISTS `Salle` (
  `salle_id` int NOT NULL AUTO_INCREMENT,
  `salle_nom` text NOT NULL,
  `salle_capacite` int NOT NULL,
  `salle_type` varchar(30) NOT NULL,
  PRIMARY KEY (`salle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS Promotion (
  `promo_id` int NOT NULL AUTO_INCREMENT,
  `promo_nb_eleves` int NOT NULL,
  `promo_nb_groupes` int NOT NULL,
  `promo_nom` varchar(50) NOT NULL,
  PRIMARY KEY (`promo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO Promotion VALUES (1, 200, 9, "L1 INFO"),
                             (2, 180, 6, "L1 BIO"),
                             (3, 160, 6, "L1 MATH"),
                             (4, 200, 9, "L1 CHIMIE"),
                             (5, 180, 6, "L1 PHYS"),
                             (6, 90, 4, "L2 INFO"),
                             (7, 85, 4, "L2 BIO"),
                             (8, 59, 2, "L2 MATH"),
                             (9, 58, 2, "L2 CHIMIE"),
                             (10, 81, 4, "L2 PHYS"),
                             (11, 86, 4, "L3 INFO"),
                             (12, 80, 4, "L3 BIO"),
                             (13, 59, 2, "L3 MATH"),
                             (14, 80, 2, "L3 CHIMIE"),
                             (15, 80, 4, "L3 PHYS"),
                             (16, 55, 2, "M1 INFO"),
                             (17, 69, 2, "M1 BIO"),
                             (18, 50, 2, "M1 MATH"),
                             (19, 60, 2, "M1 CHIMIE"),
                             (20, 81, 4, "M1 PHYS");

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



                              /*(3, 160, 6, "L1 MATH", "M100, M101, M102, M103, M104, M105"),
                             (4, 200, 9, "L1 CHIMIE", "C100, C101, C102, C103, C104, C105"),
                             (5, 180, 6, "L1 PHYS", "P100, P101, P102, P103, P104, P105");


                      
                             (6, 90, 4, "L2 INFO", "IN200, IN201, IN202, IN203, IN202, IN205"),
                             (7, 85, 4, "L2 BIO", "B200, B201, B202, B203, B204, B205"),
                             (8, 59, 2, "L2 MATH", "M200, M201, M202, M203, M204, M205"),
                             (9, 58, 2, "L2 CHIMIE", "C200, C201, C202, C203, C204, C205"),
                             (10, 81, 4, "L2 PHYS", "P200, P201, P202, P203, P204, P205");
                             */

