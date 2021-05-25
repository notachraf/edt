DROP TABLE IF EXISTS Module;
CREATE TABLE IF NOT EXISTS Module (
  mod_id INTEGER PRIMARY KEY AUTOINCREMENT,
  mod_nom VARCHAR(100) NOT NULL,
  mod_nb_td INTEGER DEFAULT NULL,
  mod_nb_tp VARCHAR(100) DEFAULT NULL,
  mod_nb_cm VARCHAR(100) DEFAULT NULL,
  mod_duree_td INTEGER DEFAULT NULL,
  mod_duree_tp INTEGER DEFAULT NULL,
  mod_duree_cm INTEGER DEFAULT NULL
);

DROP TABLE IF EXISTS Professeur;
CREATE TABLE IF NOT EXISTS Professeur (
  prof_id INTEGER PRIMARY KEY AUTOINCREMENT,
  prof_nom VARCHAR(50) NOT NULL,
  prof_cours VARCHAR(1000) NOT NULL
);

DROP TABLE IF EXISTS Salle;
CREATE TABLE IF NOT EXISTS Salle (
  salle_id INTEGER PRIMARY KEY AUTOINCREMENT,
  salle_nom text NOT NULL,
  salle_capacite INTEGER NOT NULL,
  salle_type VARCHAR(30) NOT NULL
);

DROP TABLE IF EXISTS Promotion;
CREATE TABLE IF NOT EXISTS Promotion (
  promo_id INTEGER PRIMARY KEY AUTOINCREMENT,
  promo_nb_eleves INTEGER NOT NULL,
  promo_nb_groupes INTEGER NOT NULL,
  promo_nom VARCHAR(50) NOT NULL,
  promo_cours VARCHAR(1000) NOT NULL,
  promo_date text NOT NULL
);

INSERT INTO Module (mod_nom, mod_nb_td, mod_nb_tp, mod_nb_cm, mod_duree_td, mod_duree_tp, mod_duree_cm) VALUES
('IN100', 12,  0, 12, 180,   0, 90 ),
('IN101', 12,  0, 11, 180,   0, 80 ),
('IN102', 12,  0, 12, 120,   0, 90 ),
('IN103', 12, 0,  12, 120,   0, 100 ),
('IN104', 9 ,  0,  4, 180,   0, 90 ),
('IN105', 7 ,  3,  7, 180, 110, 90 );


INSERT INTO Salle (salle_nom, salle_capacite, salle_type) VALUES 
('Buffon', '32', 'TD'),
('Descartes', '32', 'TD'),
('Bertin', '200', 'CM'),
('Centre', '32', 'TD'),
('Fermat', '32', 'TD'),
('A', '20', 'TP'),
('B', '32', 'TD'),
('C', '32', 'TP'),
('D', '150', 'CM'),
( 'E', '160', 'CM'),
( 'F', '32', 'TD'),
( 'G', '32', 'TP');

INSERT INTO Promotion (promo_nb_eleves, promo_nb_groupes, promo_nom, promo_cours, promo_date) VALUES 
(200, 6, 'L1 INFO', 'IN100, IN101, IN102, IN103, IN104, IN105', '2021-02-01');



INSERT INTO Professeur (prof_nom, prof_cours) VALUES
('Lemoine', 'IN101, IN100, IN103, IN100'),
('Delaunay', 'IN101, IN100, IN103'),
('Millet', 'IN100, IN101, IN102, IN103, IN104, IN105'),
('Collet', 'IN100, IN101, IN102, IN103, IN104, IN105'),
('Bailly', 'IN100, IN101'),
('Fabre', 'IN100, IN101, IN102, IN103, IN104, IN105'),
('Payet', 'IN103, IN104, IN105'),
('Rousseau', 'IN100, IN101, IN105'),
('Faure', 'IN100, IN102, IN103, IN104, IN105'),
('Andre', 'IN100, IN105');