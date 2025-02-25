

/*
-------ScolarStock2000-------

-- Utilisateur
    .id_utilisateur
    *nom
    *prenom
    *email
    *mot_de_passe
    *role

-- Etudiant
    .id_etudiant
    *nom
    *prenom
    *dernier_diplome_obtenu
    *email
    *telephone
    *adresse
    #ref_utilisateur

-- Dossier_Inscription
    .id_dossier
    *date
    *heure
    *filiere
    *motivations
    #ref_etudiant
    #ref_secretaire

-- Salle
    .id_salle
    *nom
    *capacite INT NOT NULL

-- Rendezvous
    .id_rendezvous
    *date
    *heure
    #ref_professeur
    #ref_etudiant
    #ref_salle
    *statut

-- Fournisseur
    .id_fournisseur
    *nom
    *adresse
    *contact

-- Fourniture
    .id_fourniture
    *libelle
    *description
    *quantite_disponible

-- Fourniture_Fournisseur (relation n-n entre Fourniture et Fournisseur)
    #ref_fournisseur
    #ref_fourniture
    *prix

-- Demande_Fourniture
    .id_demande
    #ref_professeur
    #ref_fourniture
    *quantite
    *raison_demande
    *status_demande
    #ref_gestionnaire

-- Connexion (pour enregistrer les connexions des utilisateurs)
    .id_connexion
    *date_heure_connexion
    #ref_utilisateur

-- Historique_Action (pour enregistrer les actions des utilisateurs)
    .id_action
    *date_heure
    *action
    #ref_utilisateur

---------ScolarStock2000--------
*/

CREATE DATABASE IF NOT EXISTS ScolarStock2000;


-- Creation de la table Utilisateur
CREATE TABLE Utilisateur (
    id_utilisateur INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL,
    role ENUM('Secretaire', 'Professeur', 'Gestionnaire de stock') NOT NULL
);

-- Creation de la table Etudiant
CREATE TABLE Etudiant (
    id_etudiant INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    dernier_diplome_obtenu VARCHAR(100),
    email VARCHAR(150) UNIQUE NOT NULL,
    telephone VARCHAR(15),
    adresse TEXT,
    ref_utilisateur INT
);

-- Creation de la table Dossier_Inscription
CREATE TABLE Dossier_Inscription (
    id_dossier INT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    heure TIME NOT NULL,
    filiere VARCHAR(100),
    motivations TEXT,
    ref_etudiant INT,
    ref_secretaire INT
);

-- Creation de la table Salle
CREATE TABLE Salle (
    id_salle INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    capacite INT NOT NULL
);

-- Creation de la table Rendezvous
CREATE TABLE Rendezvous (
    id_rendezvous INT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    heure TIME NOT NULL,
    ref_professeur INT,
    ref_etudiant INT,
    ref_salle INT,
    statut ENUM('Confirme', 'En attente', 'Annule') DEFAULT 'En attente'
);

-- Creation de la table Fournisseur
CREATE TABLE Fournisseur (
    id_fournisseur INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    adresse TEXT,
    contact VARCHAR(100)
);

-- Creation de la table Fourniture
CREATE TABLE Fourniture (
    id_fourniture INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL,
    description TEXT,
    quantite_disponible INT DEFAULT 0
);

-- Creation de la table Fourniture_Fournisseur (relation n-n entre Fourniture et Fournisseur)
CREATE TABLE Fourniture_Fournisseur (
    id_fourniture INT,
    ref_fournisseur INT,
    prix DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id_fourniture, id_fournisseur)
);

-- Creation de la table Demande_Fourniture
CREATE TABLE Demande_Fourniture (
    id_demande INT AUTO_INCREMENT PRIMARY KEY,
    ref_professeur INT,
    ref_fourniture INT,
    quantite INT NOT NULL,
    raison_demande TEXT,
    status_demande ENUM('Validee', 'Refusee') DEFAULT 'Refusee',
    ref_gestionnaire INT
);

-- Creation de la table Connexion (pour enregistrer les connexions des utilisateurs)
CREATE TABLE Connexion (
    id_connexion INT AUTO_INCREMENT PRIMARY KEY,
    date_heure_connexion DATETIME NOT NULL,
    ref_utilisateur INT
);

-- Creation de la table Historique_Action (pour enregistrer les actions des utilisateurs)
CREATE TABLE Historique_Action (
    id_action INT AUTO_INCREMENT PRIMARY KEY,
    date_heure DATETIME NOT NULL,
    action TEXT NOT NULL,
    ref_utilisateur INT
);

-- Declaration des cles etrangeres via ALTER TABLE

-- Cles etrangeres pour la table Etudiant
ALTER TABLE Etudiant
    ADD CONSTRAINT fk_etudiant_utilisateur FOREIGN KEY (ref_utilisateur) REFERENCES Utilisateur(id_utilisateur) ON DELETE CASCADE;

-- Cles etrangeres pour la table Dossier_Inscription
ALTER TABLE Dossier_Inscription
    ADD CONSTRAINT fk_dossier_etudiant FOREIGN KEY (ref_etudiant) REFERENCES Etudiant(id_etudiant) ON DELETE CASCADE,
    ADD CONSTRAINT fk_dossier_secretaire FOREIGN KEY (ref_secretaire) REFERENCES Utilisateur(id_utilisateur) ON DELETE CASCADE;

-- Cles etrangeres pour la table Rendezvous
ALTER TABLE Rendezvous
    ADD CONSTRAINT fk_rendezvous_professeur FOREIGN KEY (ref_professeur) REFERENCES Utilisateur(id_utilisateur) ON DELETE CASCADE,
    ADD CONSTRAINT fk_rendezvous_etudiant FOREIGN KEY (ref_etudiant) REFERENCES Etudiant(id_etudiant) ON DELETE CASCADE,
    ADD CONSTRAINT fk_rendezvous_salle FOREIGN KEY (ref_salle) REFERENCES Salle(id_salle) ON DELETE CASCADE;

-- Cles etrangeres pour la table Fourniture_Fournisseur
ALTER TABLE Fourniture_Fournisseur
    ADD CONSTRAINT fk_fourniture FOREIGN KEY (id_fourniture) REFERENCES Fourniture(id_fourniture) ON DELETE CASCADE,
    ADD CONSTRAINT fk_fournisseur FOREIGN KEY (ref_fournisseur) REFERENCES Fournisseur(id_fournisseur) ON DELETE CASCADE;

-- Cles etrangeres pour la table Demande_Fourniture
ALTER TABLE Demande_Fourniture
    ADD CONSTRAINT fk_demande_professeur FOREIGN KEY (ref_professeur) REFERENCES Utilisateur(id_utilisateur) ON DELETE CASCADE,
    ADD CONSTRAINT fk_demande_fourniture FOREIGN KEY (ref_fourniture) REFERENCES Fourniture(id_fourniture) ON DELETE CASCADE,
    ADD CONSTRAINT fk_demande_gestionnaire FOREIGN KEY (ref_gestionnaire) REFERENCES Utilisateur(id_utilisateur) ON DELETE CASCADE;

-- Cles etrangeres pour la table Connexion
ALTER TABLE Connexion
    ADD CONSTRAINT fk_connexion_utilisateur FOREIGN KEY (ref_utilisateur) REFERENCES Utilisateur(id_utilisateur) ON DELETE CASCADE;

-- Cles etrangeres pour la table Historique_Action
ALTER TABLE Historique_Action
    ADD CONSTRAINT fk_action_utilisateur FOREIGN KEY (ref_utilisateur) REFERENCES Utilisateur(id_utilisateur) ON DELETE CASCADE;
