# Capco CART

## Objectif
Capco CART est un programme qui calcule le montant total d'un cart composé de différents produits.

## Description
Ce projet implémente un système de gestion de cart d'achat permettant de calculer le prix total des produits sélectionnés. Le système prend en compte différents types de clients et de produits.

## Modélisation
Le modèle est composée des éléments suivants :

1. **Client** : Représente un client qui peut être de type Individuel ou Entreprise (Business).
2. **Product** : Représente un product qui peut être un téléphone (Phone) ou un ordinateur portable (Laptop).
3. **PricingService** : Responsable du calcul des prix des produits.
4. **Cart** : Contient les produits sélectionnés avec leurs quantités respectives.

## Fonctionnalités
- Création de différents types de clients (Individuel, Entreprise)
- Ajout de différents types de produits (Téléphone, Ordinateur portable)
- Calcul du prix total du cart

## Comment utiliser
```bash
./gradlew run --console=plain
```
