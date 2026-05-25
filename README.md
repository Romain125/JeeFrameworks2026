**# JeeFrameworks2026

# Projet : Run or Die 🧟

Une association d'événementiel sportif et décalé organise des **zombie runs** : des courses où des coureurs doivent traverser un parcours infesté de zombies bénévoles. L'objectif est de développer le backend d'une application pour gérer les éditions de l'événement, les affectations de zombies et les inscriptions des coureurs.

---

## Rôles et fonctionnalités

### Organisateur

L'organisateur gère les **éditions** de l'événement.

Une **édition** possède :
- Un nom (ex : *Run or Die — Édition Automne*)
- Une date
- Une heure de début et une heure de fin
- Un lieu
- Une capacité maximale de coureurs
- Une capacité maximale de zombies

L'organisateur peut créer, visualiser et supprimer des éditions.

Contraintes :
- La date d'une édition doit être dans le futur
- L'heure de fin doit être après l'heure de début
- Deux éditions ne peuvent pas se chevaucher dans le temps
- Une édition ne peut pas être supprimée s'il y a des inscrits — il faut d'abord l'annuler, ce qui déclenche l'envoi d'un mail à tous les inscrits (bouchonné par un `System.out.println`)

---

### Utilisateurs

- Les utilisateurs s'identifient sur la plateforme avec un email et un mot de passe. Une fois identifiés, ils peuvent s'inscrire à des événements en tant que Zombie ou Coureur.  

---

### Zombie (bénévole)

Les zombies s'affectent eux-mêmes à une édition pour jouer les monstres sur le parcours.

Contraintes :
- Un zombie ne peut s'affecter qu'à des éditions futures
- Un zombie ne peut pas s'affecter deux fois à la même édition
- Une édition ne peut pas accueillir plus de zombies que sa capacité maximale
- Un zombie possède un **niveau de maquillage** : `DÉBUTANT`, `CONFIRMÉ` ou `EXPERT`. Chaque édition définit un niveau minimum requis — un zombie ne peut pas s'affecter à une édition si son niveau est insuffisant
- PS : Il sera sans doute nécessaire d'ajouter des API différentes (i.e : l'affichage des 

---

### Coureur

Les coureurs s'inscrivent aux éditions.

Ils peuvent :
- Visualiser la liste des éditions à venir avec le nombre de places restantes
- S'inscrire à une édition

Contraintes :
- Un coureur ne peut pas s'inscrire à une édition passée
- Un coureur ne peut pas s'inscrire deux fois à la même édition
- Le nombre total de coureurs inscrits ne peut pas dépasser la capacité du lieu
- La **première inscription** d'un coureur est toujours **gratuite**
- Pour toute inscription suivante, le coureur doit être **licencié** auprès de la fédération. Une API de vérification de licence sera bouchonnée par un `System.out.println`. Il n'est pas possible de prendre une licence plus d'une fois.

---

## Utilisateurs

| Username        | Password   | Rôle                      |
|-----------------|------------|---------------------------|
| `organisateur`  | `brains`   | Organisateur              |
| `zombie1`       | `grrrr`    | Zombie (niveau CONFIRMÉ)  |
| `zombie2`       | `raaaah`   | Zombie (niveau EXPERT)    |
| `coureur1`      | `vite`     | Coureur (non licencié)    |
| `coureur2`      | `plusvite` | Coureur (licencié)        |

> Un même utilisateur peut cumuler les rôles de zombie et de coureur. L'organisateur est également considéré comme zombie et coureur s'il le souhaite.

---

## Jeu de données initial

L'application doit contenir au démarrage **2 éditions** avec quelques affectations et inscriptions déjà existantes, de façon à ce qu'une majorité de scénarios soient testables immédiatement.

---

## Technologies

- Java 21 + Spring Boot > 3.0
- Spring Web, Spring Data JPA, Spring Security
- Maven
- Base de données en mémoire H2
- Architecture monolithique en **4 couches distinctes**

---

## Rendu

- Lien vers un dépôt Git contenant le code source (accès lecture pour le correcteur obligatoire)
- Les participants listés dans le `pom.xml` à l'emplacement approprié
- Un fat-jar auto-exécutable dans un dossier `/jar` à la racine du projet
- L'URL du Swagger UI (ou une documentation d'API explicite)
- Un fichier `readme.md` à la racine avec toute information utile au correcteur

---

## Points d'attention

Le sujet est volontairement simple techniquement. L'accent est mis sur la **qualité du code et sa maintenabilité** :

- Nommage clair des classes, variables et méthodes
- Méthodes courtes, claires et auto-portantes
- Application des principes SOLID
- Peu de duplication (DRY)
- Pas de code mort (YAGNI)
- Bonnes pratiques de conception d'API REST

Les tests automatisés et la Javadoc ne sont pas requis. Le code doit être suffisamment lisible pour se passer d'explications.

**Tout projet suspicieusement similaire sera sanctionné.**
**
