[![Build Status](https://travis-ci.org/Nelgamix/Devops-data-analyse.svg?branch=master)](https://travis-ci.org/Nelgamix/Devops-data-analyse)

# Progression
## Construction d'un Dataframe
* A partir de structures de données simples: OK
* A partir d'un fichier CSV:                 OK

## Affichage d'un Dataframe
* Affichage complet: OK
* Affichage seulement les premières lignes:   OK
* Affichage seulement les premières colonnes: OK

## Sélection dans un Dataframe:
* A partir de l'index des lignes: OK
* A partir des colonnes (noms):   OK

## Statistiques sur un Dataframe:
* Minimum: OK
* Maximum: OK
* Moyenne: OK

# Code
Création de colonnes (`Series`):
```java
// Création de Series avec un tableau d'Integer
Series s = SeriesFactory.createSeries(new Integer[]{0, 1, 2, 3});
// Création de Series avec un tableau de String
Series s = SeriesFactory.createSeries(new String[]{"Data 0", "Data 1", "Data 2", "Data 3"});

// Spécification des noms
Series s = SeriesFactory.createSeries("Prix", new Integer[]{0, 1, 2, 3});
Series s = SeriesFactory.createSeries("Détails", new String[]{"Data 0", "Data 1", "Data 2", "Data 3"});

// Création de Series vides initialement
Series s = SeriesFactory.createSeries(Integer.class);
Series s = SeriesFactory.createSeries(String.class);
Series s = SeriesFactory.createSeries("Prix", Integer.class);
Series s = SeriesFactory.createSeries("Détails", String.class);
```

Types disponibles et implémentés:
* Integer
* String

Création de tableau (`Dataframe`):
```java
// Création de Dataframe vide
Dataframe df = new Dataframe();

// Dataframe avec deux Series
Series s1 = SeriesFactory.createSeries("Prix", new Integer[]{0, 1, 2, 3});
Series s2 = SeriesFactory.createSeries("Détails", new String[]{"Data 0", "Data 1", "Data 2", "Data 3"});
Dataframe df = new Dataframe(s1, s2);
```

Méthodes sur les `Series`:
* `add(T d)`: Ajoute la donnée d à la fin
* `get(int i)`: Renvoie la i-ème donnée
* `getSize()`: Renvoie le nombre d'éléments
* `getName()`: Renvoie le nom de la colonne
* `getData()`: Renvoie les données (sous forme de List<T>)
* `setName(String n)`: Set le nom de la colonne
* `calculateMin()`: Calcule le minimum dans les données
* `calculateMax()`: Calcule le maximum dans les données
* `calculateAvg()`: Calcule la moyenne des données

Méthodes sur les `Dataframes`:
* `addSeries(Series s)`: Ajoute une nouvelle colonne
* `getSize()`: Renvoie la taille du Dataframe (le nombre d'éléments max de toutes les colonnes)
* `printAll()`: Affiche la totalité du Dataframe
* `printFirstLines(int n)`: Affiche seulement les n premières lignes
* `printLastLines(int n)`: Affiche seulement les n dernières lignes
* `selectLines(int min, int max)`: Créé un nouveau Dataframe à partir des données des lignes min à max
* `selectSeries(String[] names)`: Crée un nouveau Dataframe à partir des données des Series identifiées par leur nom (names)
