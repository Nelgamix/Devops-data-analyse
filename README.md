[![Build Status](https://travis-ci.org/Nelgamix/Devops-data-analyse.svg?branch=master)](https://travis-ci.org/Nelgamix/Devops-data-analyse)

# Progression
## Construction d'un DataFrame
* A partir de structures de données simples: OK
* A partir d'un fichier CSV:                 OK (manque la reco des types)

## Affichage d'un DataFrame
* Affichage complet: OK
* Affichage seulement les premières lignes:   OK
* Affichage seulement les premières colonnes: OK

## Sélection dans un DataFrame:
* A partir de l'index des lignes: OK
* A partir des colonnes (noms):   OK

## Statistiques sur un DataFrame:
* Minimum: OK
* Maximum: OK
* Moyenne: OK

# Choix
* GitHub
* Maven
* JUnit 5
* Travis CI

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
* Double
* String

Création de tableau (`DataFrame`):
```java
// Création de DataFrame vide
DataFrame df = new DataFrame();

// DataFrame avec deux Series
Series s1 = SeriesFactory.createSeries("Prix", new Integer[]{0, 1, 2, 3});
Series s2 = SeriesFactory.createSeries("Détails", new String[]{"Data 0", "Data 1", "Data 2", "Data 3"});
DataFrame df = new DataFrame(s1, s2);

// Création d'un DataFrame à partir d'un fichier CSV
DataFrame df = new DataFrame("test.csv");
```

Méthodes sur les `Series`:
* `add(T d)`: Ajoute la donnée d à la fin
* `get(int i)`: Renvoie la i-ème donnée
* `getSize()`: Renvoie le nombre d'éléments
* `getDataType()`: Renvoie le type des éléments contenus
* `getName()`: Renvoie le nom de la colonne
* `getData()`: Renvoie les données (sous forme de List<T>)
* `setName(String n)`: Set le nom de la colonne
* `calculateMin()`: Calcule le minimum dans les données
* `calculateMax()`: Calcule le maximum dans les données
* `calculateAvg()`: Calcule la moyenne des données

Méthodes sur les `DataFrames`:
* `addSeries(Series s)`: Ajoute une nouvelle colonne
* `getSize()`: Renvoie la taille du DataFrame (le nombre d'éléments max de toutes les colonnes)
* `printAll()`: Affiche la totalité du DataFrame
* `printFirstLines(int n)`: Affiche seulement les n premières lignes
* `printLastLines(int n)`: Affiche seulement les n dernières lignes
* `selectLines(int min, int max)`: Créé un nouveau DataFrame à partir des données des lignes min à max
* `selectSeries(String[] names)`: Crée un nouveau DataFrame à partir des données des Series identifiées par leur nom (names)

# Feedback
