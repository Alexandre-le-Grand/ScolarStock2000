# To-Do List pour le développement de la plateforme de gestion d'événements et de rendez-vous

## 1. Contexte et objectif
- [ ] Analyser les besoins de gestion de stock des fournitures.
- [ ] Analyser les besoins de gestion des dossiers d’inscription et des rendez-vous.

## 2. Expression des besoins
### 2.1 Gestion des rôles et utilisateurs
- [ ] Définir les rôles : Secrétaire, Professeur, Gestionnaire de stock.
- [ ] Créer un système d’identification des utilisateurs (nom, prénom, email).

### 2.2 Gestion des inscriptions des étudiants
- [ ] Créer la fonctionnalité de création de fiche étudiant par la secrétaire.
- [ ] Définir les informations nécessaires pour la fiche étudiant.
- [ ] Créer la fonctionnalité de création de dossier d’inscription.
- [ ] Créer un système de gestion des rendez-vous entre professeur et étudiant.
- [ ] Implémenter la gestion des disponibilités des salles et conflits de réservation.

### 2.3 Gestion des fournitures
- [ ] Créer un formulaire de demande de fournitures par les professeurs.
- [ ] Implémenter la validation des demandes par le gestionnaire de stock.
- [ ] Créer un système de gestion des stocks de fournitures.
- [ ] Ajouter une fonctionnalité de réapprovisionnement des stocks.

### 2.4 Cybersécurité et RGPD
- [ ] Mettre en place un contrôle des accès avec mots de passe forts.
- [ ] Implémenter la journalisation des connexions (date et heure).
- [ ] Créer un système de gestion des autorisations selon les rôles.
- [ ] Implémenter l’enregistrement des actions des utilisateurs.
- [ ] Mettre en place des mesures pour garantir la conformité au RGPD.

## 3. Organisation du projet
### 3.1 Livrables attendus
- [ ] Créer un dépôt Git pour le suivi du code source.
- [ ] Rédiger la documentation technique et fonctionnelle.
- [ ] Développer et tester l'application fonctionnelle.

### 3.2 Contraintes de temps
- [ ] Définir une date de rendu final pour le projet.
- [ ] Mettre en place un système de suivi du développement avec Git.
- [ ] Organiser des revues régulières avec l'informaticien.

### 3.3 Méthodologie et outils
- [ ] Organiser des réunions hebdomadaires pour suivre l’avancement du projet.
- [ ] Planifier les livraisons incrémentales des fonctionnalités.
- [ ] Choisir l'IDE (IntelliJ IDEA, Eclipse ou NetBeans).
- [ ] Mettre en place la gestion de version avec GitHub ou GitLab.
- [ ] Configurer la base de données MySQL.
- [ ] Développer l'interface utilisateur avec JavaFX ou Swing.

## 4. BONUS et pistes de réflexion
### 4.1 Pour pousser le projet plus loin
- [ ] Implémenter un module de reporting et statistiques (inscriptions, demandes de fournitures, occupation des salles).
- [ ] Ajouter des alertes automatiques pour les stocks critiques et notifications pour les rendez-vous.
- [ ] Ajouter la fonctionnalité d’exportation des documents (PDF).

### 4.2 Pistes de réflexion technique
- [ ] Implémenter un système pour gérer les créneaux disponibles des salles de rendez-vous.
- [ ] Développer une interface graphique ergonomique (par exemple, calendrier interactif pour les rendez-vous).
- [ ] Optimiser la gestion des données pour un grand nombre d’utilisateurs et de fournitures.
- [ ] Ajouter une fonctionnalité de recherche avancée pour les fiches d’étudiants et les fournitures.
- [ ] Mettre en place des améliorations en cybersécurité (double authentification, tests de pénétration).
- [ ] Ajouter une barre de recherche intelligente pour retrouver des dossiers ou des produits.

### 4.3 Contraintes techniques
- [ ] Utiliser JavaFX pour le développement de l'application lourde.
- [ ] Mettre en place la base de données relationnelle MySQL.
- [ ] Organiser l'architecture du projet avec le modèle MVC ou MVVM.
