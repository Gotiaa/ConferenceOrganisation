## Projet conférence de Londres de Tiago Goncalves S09

# ConferenceOrganisation

## Consignes
Dessiner un avion dans le terminal

## Réalisation
J'ai réalisé le projet en Kotlin pour progresser dans ce language. J'ai donc crée mon premier projet en koltin/native en codant sur intelliJ.
J'ai fais un semblant de clean archi pour le beau jeu.

## Fait/pasFait

Fait : 
- Trouver un avion compatible parmit les vols dans les fichiers Json
- Trouver une solution possible composée de 18 vols (aller + retour pour chaque conférencier)
- Calculer le coût d'une solution en prenant en compte l'attente
- Optimiser la solution en utilisant le hill climbing

Pas fait :( :
- algo simulated annealing
- algo génétique


## Run

Quelques pré-requis :

Le plus simple est d'utiliser gradle pour build donc pour l'installer avec snap par exemple: 

$ sudo snap install --classic gradle

puis pour lancer le build depuis la racine du projet : 

$ gradle nativeBinaries

ensuite pour le lancer le fichier de build généré toujours depuis la racine du projet :

$ ./build/bin/native/debugExecutable/conference.kexe

et tada... ça marche (j'espère)
