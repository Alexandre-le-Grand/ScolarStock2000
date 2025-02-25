CREATE DATABASE IF NOT EXISTS ScolarStock2000;


-- Création de la table Utilisateur
CREATE TABLE IF NOT EXISTS Utilisateur (
    id_utilisateur INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prénom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL,
    role ENUM('Secrétaire', 'Professeur', 'Gestionnaire de stock') NOT NULL
);

-- Création de la table Etudiant
CREATE TABLE IF NOT EXISTS Etudiant (
    id_etudiant INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prénom VARCHAR(100) NOT NULL,
    dernier_diplome_obtenu VARCHAR(100),
    email VARCHAR(150) UNIQUE NOT NULL,
    téléphone VARCHAR(15),
    adresse TEXT,
    ref_utilisateur INT
);

-- Création de la table Dossier_Inscription
CREATE TABLE IF NOT EXISTS Dossier_Inscription (
    id_dossier INT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    heure TIME NOT NULL,
    filière VARCHAR(100),
    motivations TEXT,
    ref_etudiant INT,
    ref_secrétaire INT
);

-- Création de la table Salle
CREATE TABLE IF NOT EXISTS Salle (
    id_salle INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    capacité INT NOT NULL
);

-- Création de la table Rendezvous
CREATE TABLE IF NOT EXISTS Rendezvous (
    id_rendezvous INT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    heure TIME NOT NULL,
    ref_professeur INT,
    ref_etudiant INT,
    ref_salle INT,
    statut ENUM('Confirmé', 'En attente', 'Annulé') DEFAULT 'En attente'
);

-- Création de la table Fournisseur
CREATE TABLE IF NOT EXISTS Fournisseur (
    id_fournisseur INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    adresse TEXT,
    contact VARCHAR(100)
);

-- Création de la table Fourniture
CREATE TABLE IF NOT EXISTS Fourniture (
    id_fourniture INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL,
    description TEXT,
    quantite_disponible INT DEFAULT 0
);

-- Création de la table Fourniture_Fournisseur (relation n-n entre Fourniture et Fournisseur)
CREATE TABLE IF NOT EXISTS Fourniture_Fournisseur (
    ref_fourniture INT,
    ref_fournisseur INT,
    prix DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (ref_fourniture, ref_fournisseur)
);

-- Création de la table Demande_Fourniture
CREATE TABLE IF NOT EXISTS Demande_Fourniture (
    id_demande INT AUTO_INCREMENT PRIMARY KEY,
    ref_professeur INT,
    ref_fourniture INT,
    quantite INT NOT NULL,
    raison_demande TEXT,
    status_demande ENUM('Validée', 'Refusée') DEFAULT 'Refusée',
    ref_gestionnaire INT
);

-- Création de la table Connexion (pour enregistrer les connexions des utilisateurs)
CREATE TABLE IF NOT EXISTS Connexion (
    id_connexion INT AUTO_INCREMENT PRIMARY KEY,
    date_heure_connexion DATETIME NOT NULL,
    ref_utilisateur INT
);

-- Création de la table Historique_Action (pour enregistrer les actions des utilisateurs)
CREATE TABLE IF NOT EXISTS Historique_Action (
    id_action INT AUTO_INCREMENT PRIMARY KEY,
    date_heure DATETIME NOT NULL,
    action TEXT NOT NULL,
    ref_utilisateur INT
);

-- Déclaration des clés étrangères via ALTER TABLE

-- Clés étrangères pour la table Etudiant
ALTER TABLE Etudiant
    ADD CONSTRAINT fk_etudiant_utilisateur FOREIGN KEY (ref_utilisateur) REFERENCES Utilisateur(id_utilisateur) ON DELETE CASCADE;

-- Clés étrangères pour la table Dossier_Inscription
ALTER TABLE Dossier_Inscription
    ADD CONSTRAINT fk_dossier_etudiant FOREIGN KEY (ref_etudiant) REFERENCES Etudiant(id_etudiant) ON DELETE CASCADE,
    ADD CONSTRAINT fk_dossier_secretaire FOREIGN KEY (ref_secrétaire) REFERENCES Utilisateur(id_utilisateur) ON DELETE CASCADE;

-- Clés étrangères pour la table Rendezvous
ALTER TABLE Rendezvous
    ADD CONSTRAINT fk_rendezvous_professeur FOREIGN KEY (ref_professeur) REFERENCES Utilisateur(id_utilisateur) ON DELETE CASCADE,
    ADD CONSTRAINT fk_rendezvous_etudiant FOREIGN KEY (ref_etudiant) REFERENCES Etudiant(id_etudiant) ON DELETE CASCADE,
    ADD CONSTRAINT fk_rendezvous_salle FOREIGN KEY (ref_salle) REFERENCES Salle(id_salle) ON DELETE CASCADE;

-- Clés étrangères pour la table Fourniture_Fournisseur
ALTER TABLE Fourniture_Fournisseur
    ADD CONSTRAINT fk_fourniture FOREIGN KEY (ref_fourniture) REFERENCES Fourniture(id_fourniture) ON DELETE CASCADE,
    ADD CONSTRAINT fk_fournisseur FOREIGN KEY (ref_fournisseur) REFERENCES Fournisseur(id_fournisseur) ON DELETE CASCADE;

-- Clés étrangères pour la table Demande_Fourniture
ALTER TABLE Demande_Fourniture
    ADD CONSTRAINT fk_demande_professeur FOREIGN KEY (ref_professeur) REFERENCES Utilisateur(id_utilisateur) ON DELETE CASCADE,
    ADD CONSTRAINT fk_demande_fourniture FOREIGN KEY (ref_fourniture) REFERENCES Fourniture(id_fourniture) ON DELETE CASCADE,
    ADD CONSTRAINT fk_demande_gestionnaire FOREIGN KEY (ref_gestionnaire) REFERENCES Utilisateur(id_utilisateur) ON DELETE CASCADE;

-- Clés étrangères pour la table Connexion
ALTER TABLE Connexion
    ADD CONSTRAINT fk_connexion_utilisateur FOREIGN KEY (ref_utilisateur) REFERENCES Utilisateur(id_utilisateur) ON DELETE CASCADE;

-- Clés étrangères pour la table Historique_Action
ALTER TABLE Historique_Action
    ADD CONSTRAINT fk_action_utilisateur FOREIGN KEY (ref_utilisateur) REFERENCES Utilisateur(id_utilisateur) ON DELETE CASCADE;
