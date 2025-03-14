Projet ABC - RAPPORT
=================================


Architecture
--------------------




### View Model

Utilisation d'un unique ```ViewModel``` pour les ```Book``` et ```Author```

### Recycler view

Utilisation de recycler view pour l'affichage des titres des ```Book```, nom et prénom des ```Author``` ainsi que pour l'affichage des ```Comment``` (```Rating``` inclus)

### Model

Utilisation d'objet ```Author```, ```Book```, ```Comment``` et ```Tag```

### Repository

Utilisation d'un repository ```APIRequest``` effectuant les requêtes volley pour l'ensemble de l'application


### Lien vers l'api
Afin de faire fonctionner l'application, il est impératif de changer le lien
présent dans ```APIRequest``` avec le bon port
```
    public APIRequest() {
        this.view = view;
        this.apiBaseName = "http://192.168.123.131:3000"; // mettre l'ip vers l'api
    }
```


Fonctionnalités
--------------------

### Fonctionnelles

* Afficher la liste des livres
    * Affiche les tags, la note moyenne et les commentaires dans les details du livre
* Afficher la liste des auteurs
    * Afficher la liste des titres des livres qu'il a écrit
        * Un clic sur l'un des livres affiche ses détails
* Créer un livre à partir d'un formulaire
    * Avec FAB
* Créer un auteur à partir d'un formulaire
    * Avec FAB
* Supprimer un livre depuis sa page de description

* Supprimer un auteur depuis sa page de description
    * Supprime ses livres associés à sa suppression

### Bonus Hors Bonus
* Affichage de la note des commentaires (S'affiche mais clignote)

### Non Fonctionnelles

 * Rien

### Non Implémenté

* Converture des livres (Bonus)
* L'ajout de commentaire (avec note) (Bonus Hors Bonus)
* Ajout de tag à un livre (Bonus Hors Bonus)
* Partie utilisateur couplé avec les commentaires (Bonus Hors Bonus)




Difficultés
--------------------

* L'implémentation du spinner pour les auteurs un peu laborieuse
* Ajout des livres initialement (pour les liés avec l'auteur correctement)

* L'implémentation des notes, puisque celles-ci ne sont pas lié au commentaires (mais aux livre et utilisateur, donc peut intuitif)




