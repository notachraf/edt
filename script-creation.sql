CREATE DATABASE `emploi_du_temps` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;


-- emploi_du_temps.Module definition

CREATE TABLE `Module` (
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


-- emploi_du_temps.Professeur definition

CREATE TABLE `Professeur` (
  `prof_id` int NOT NULL AUTO_INCREMENT,
  `prof_nom` varchar(50) NOT NULL,
  PRIMARY KEY (`prof_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Table des professeurs';


-- emploi_du_temps.Professeur_Module definition

CREATE TABLE `Professeur_Module` (
  `prof_id` int NOT NULL,
  `mod_id` int NOT NULL,
  UNIQUE KEY `Professeur_Module_UN` (`prof_id`,`mod_id`),
  KEY `Professeur_Module_FK` (`mod_id`),
  CONSTRAINT `Professeur_Module_FK` FOREIGN KEY (`mod_id`) REFERENCES `Module` (`mod_id`),
  CONSTRAINT `Professeur_Module_FK_1` FOREIGN KEY (`prof_id`) REFERENCES `Professeur` (`prof_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- emploi_du_temps.Salle definition

CREATE TABLE `Salle` (
  `salle_id` int NOT NULL AUTO_INCREMENT,
  `salle_nom` text NOT NULL,
  `salle_capacite` int NOT NULL,
  `salle_type` varchar(30) NOT NULL,
  PRIMARY KEY (`salle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- emploi_du_temps.Promotion definition

CREATE TABLE `Promotion` (
  `promo_id` int NOT NULL AUTO_INCREMENT,
  `promo_nb_eleves` int NOT NULL,
  `promo_nb_groupes` int NOT NULL,
  `promo_nom` varchar(50) NOT NULL,
  PRIMARY KEY (`promo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- emploi_du_temps.Promotion_Module definition

CREATE TABLE `Promotion_Module` (
  `promo_id` int NOT NULL,
  `mod_id` int NOT NULL,
  UNIQUE KEY `Promotion_Module_UN` (`mod_id`,`promo_id`),
  KEY `Promotion_Module_FK_1` (`promo_id`),
  CONSTRAINT `Promotion_Module_FK` FOREIGN KEY (`mod_id`) REFERENCES `Module` (`mod_id`),
  CONSTRAINT `Promotion_Module_FK_1` FOREIGN KEY (`promo_id`) REFERENCES `Promotion` (`promo_id`)
) ENGINE=InnoDB D



