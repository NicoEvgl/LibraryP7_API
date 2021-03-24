# Library_API

Contexte

Ce projet a été développé dans le cadre du cursus "Développeur d'application Java" d'OpenClassrooms et correspond à l'API du projet 7 et 10 "Développez le nouveau système d’information de la bibliothèque d’une grande ville". 

L'API permet d'exposer les informatinos concernant le catalogues et la gestion des prêts d'une bibliothèque.

# Architecture

Version de java : 11 (jdk utilisé : jdk13) 
Maven 3.6 

[ API ] : API Rest https://github.com/NicoEvgl/Library_API.git 'Library_Api'

[ batch ] : Système d’envoi d’e-mails récurent https://github.com/NicoEvgl/Library_Batch.git 'Library_Batch'

[ WebClient ] : Application web https://github.com/NicoEvgl/Library_WebClient.git 'Library_WebClient'

# Frameworks

Maven

Spring boot MVC

Hibernate 5.0

Apache Log4J 2.11.0

Bootstrap 3.3.7

# Persistence

PostgreSQL 10.4

# Installation et déploiement

1.Configuration

Configurer une base PostgresSQL (pré-installation nécessaire) à paramétrer et peupler à l'aide des scripts sql démo disponibles dans le dossier /datasource (script_db_ocp + dataset_db_ocp). 
Les paramètres de connection à la base sont à modifier dans le fichier src\resources\application.properties (spring.datasource.url, spring.datasource.username et spring.datasource.password).

2.Déploiement

Application standalone intégrant un conteneur web (grace à SpringBoot)

Ouvrir le terminal de commande, se placer dans le dossier du projet cloné puis taper les commandes :

mvn clean spring-boot:run

3.Accès

L'application est accessible par http://localhost:9090/ l'application via le JDD fourni contient un compte utilisateur démo (Pseudo : Nicoe, mot de passe : Pass123).
