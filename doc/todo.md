# To-Do List pour le développement de la plateforme (étapes techniques)

## 1. Mise en place de la base de données (BDD)

- [X] Créer la base de données MySQL.
- [X] Définir les tables nécessaires : utilisateurs, étudiants, inscriptions, fournitures, demandes, rendez-vous, etc.
- [X] Implémenter les relations entre les tables (clés étrangères, etc.).
- [X] Tester la connexion à la base de données.
- [X] Vérifier les scripts SQL de création de tables.

## 2. Mise en place de l'architecture du projet

- [X] Créer la structure de l’application (MVC ou MVVM).
- [X] Définir les packages de l’application (contrôleurs, modèles, vues, etc.).
- [X] Configurer le framework JavaFX pour l'interface graphique.
- [X] Configurer l’accès à la base de données (utilisation de JDBC, Hibernate, etc.).
- [X] Implémenter la gestion des erreurs et des exceptions.

## 3. Création des entités

- [X] Créer l'entité **Utilisateur** (nom, prénom, email, rôle).
- [X] Créer l'entité **Étudiant** (nom, prénom, email, téléphone, adresse, dernier diplôme, etc.).
- [X] Créer l'entité **Inscription** (date, heure, filière, motivations, étudiant associé, etc.).
- [X] Créer l'entité **Fourniture** (libellé, description, fournisseurs, prix, etc.).
- [X] Créer l'entité **DemandeFourniture** (quantité, raison, statut, professeur, etc.).
- [X] Créer l'entité **RendezVous** (date, heure, étudiant, professeur, salle, etc.).

## 4. Création des repositories

- [X] Créer le repository pour l'entité **Utilisateur**.
- [X] Créer le repository pour l'entité **Étudiant**.
- [X] Créer le repository pour l'entité **Inscription**.
- [X] Créer le repository pour l'entité **Fourniture**.
- [X] Créer le repository pour l'entité **DemandeFourniture**.
- [X] Créer le repository pour l'entité **RendezVous**.
- [X] Implémenter des méthodes de recherche et de gestion des entités dans les repositories.

## 5. Création du MainController

- [X] Créer le **MainController** pour gérer les interactions entre l’interface utilisateur et les services.
- [X] Définir les méthodes d'initialisation de l'interface principale.
- [X] Implémenter la logique pour charger la vue principale.

## 6. Création du template Main

- [X] Créer le **template Main** (page d’accueil ou tableau de bord).
- [X] Définir la structure générale de la page (navigation, éléments principaux).
- [X] Intégrer les éléments visuels et interactivité (boutons, liens, tableaux, etc.).

## 7. Création du ConnexionController

- [ ] Créer le **ConnexionController** pour gérer la logique de connexion.
- [ ] Implémenter la validation des informations de connexion (email, mot de passe).
- [ ] Ajouter la logique de redirection après la connexion réussie.

## 8. Création du template Connexion

- [ ] Créer le **template Connexion** (formulaire de connexion).
- [ ] Ajouter les champs pour l'email et le mot de passe.
- [ ] Ajouter des messages d'erreur pour une mauvaise connexion.

## 9. Création de l'InscriptionController

- [ ] Créer le **InscriptionController** pour gérer la logique d'inscription des étudiants.
- [ ] Implémenter la validation des informations (nom, prénom, email, téléphone, adresse, etc.).
- [ ] Ajouter la logique pour enregistrer un nouvel étudiant dans la base de données.

## 10. Création du template Inscription

- [ ] Créer le **template Inscription** (formulaire d'inscription).
- [ ] Ajouter les champs nécessaires pour les informations de l’étudiant.
- [ ] Implémenter des messages d'erreur pour l'inscription.

## 11. Création du OubliMotDePasseController

- [ ] Créer le **OubliMotDePasseController** pour gérer la logique de réinitialisation du mot de passe.
- [ ] Implémenter la fonctionnalité d’envoi d’un lien de réinitialisation à l’email de l'utilisateur.
- [ ] Ajouter la validation pour les demandes de réinitialisation.

## 12. Création du template OubliMotDePasse

- [ ] Créer le **template OubliMotDePasse** (formulaire de réinitialisation de mot de passe).
- [ ] Ajouter le champ pour l’email de l'utilisateur.
- [ ] Ajouter des messages d'erreur pour un email non trouvé.

## 13. Création du ProfesseurController

- [ ] Créer le **ProfesseurController** pour gérer les actions liées aux professeurs.
- [ ] Implémenter la logique pour gérer les demandes de fournitures et les rendez-vous avec les étudiants.

## 14. Création du template Professeur

- [ ] Créer le **template Professeur** (page de gestion des demandes et des rendez-vous).
- [ ] Ajouter les éléments pour la gestion des demandes de fournitures.
- [ ] Ajouter les éléments pour la gestion des rendez-vous avec les étudiants.

## 15. Création du GestionnaireStockController

- [ ] Créer le **GestionnaireStockController** pour gérer les actions liées à la gestion des fournitures.
- [ ] Implémenter la logique pour gérer l'approbation ou le rejet des demandes de fournitures.

## 16. Création du template GestionnaireStock

- [ ] Créer le **template GestionnaireStock** (page de gestion des fournitures).
- [ ] Ajouter des éléments pour gérer les stocks et les demandes de réapprovisionnement.

## 17. Création du RendezVousController

- [ ] Créer le **RendezVousController** pour gérer les rendez-vous entre étudiants et professeurs.
- [ ] Implémenter la logique pour planifier, annuler et gérer les rendez-vous.

## 18. Création du template RendezVous

- [ ] Créer le **template RendezVous** (formulaire de planification de rendez-vous).
- [ ] Ajouter les champs pour choisir les dates et les heures disponibles.
- [ ] Ajouter des éléments pour gérer la confirmation des rendez-vous.

## 19. Tests et validation

- [ ] Tester toutes les pages de l'application.
- [ ] Vérifier les interactions entre les contrôleurs et les vues.
- [ ] Tester la gestion des erreurs et la validation des formulaires.
- [ ] Tester la connexion à la base de données et l'intégrité des données.

## 20. Finalisation et déploiement

- [ ] Finaliser le projet avec les dernières fonctionnalités.
- [ ] Préparer le déploiement de l’application.
- [ ] Documenter le projet pour la mise en production.

si on veut aller plus loin dans la structure pour apprendre à l'utiliser
et savoir que ça existe, il existe l'entitymanager qui nous permettrait
de gérer nos repositories en ne mettant casi pas de sql dedans
