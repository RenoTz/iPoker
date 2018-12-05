package controller;

import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import model.Carte;
import model.Joueur;

public class Main {

	private static final String SEPARATEUR = "-----------------------------------";

	public static void main(String[] args) {

		// creation d'un joueur
		final Joueur joueur = new Joueur("Renaud");
		final List<Joueur> joueurs = Lists.newArrayList(joueur);

		// creation du jeu de cartes
		final Setup setup = new Setup();
		final List<Carte> jeuDeCartes = setup.creerJeuDeCartes();
		final List<Carte> cartesVisibles = Lists.newArrayList();

		while (!joueur.isWon()) {

			// distribution de 2 cartes à chaque joueur
			for (int i = 0; i < 2; i++) {
				joueurs.forEach(j -> j.getCartes().add(Iterables.getLast(jeuDeCartes)));
				jeuDeCartes.remove(jeuDeCartes.size() - 1);
				System.out.println();
			}

			affichageCartesJoueurs(joueurs);

			// premier tour : on pose 3 cartes
			System.out.println(SEPARATEUR);
			System.out.println("------- Le FLOP --------");
			System.out.println(SEPARATEUR);
			for (int i = 0; i < 3; i++) {
				cartesVisibles.add(Iterables.getLast(jeuDeCartes));
				jeuDeCartes.remove(jeuDeCartes.size() - 1);
			}

			afficherCarte(cartesVisibles);

			System.out.println(SEPARATEUR);
			System.out.println("------- La TURN --------");
			System.out.println(SEPARATEUR);
			cartesVisibles.add(Iterables.getLast(jeuDeCartes));
			jeuDeCartes.remove(jeuDeCartes.size() - 1);

			afficherCarte(cartesVisibles);

			System.out.println(SEPARATEUR);
			System.out.println("------- La RIVIERE --------");
			System.out.println(SEPARATEUR);
			cartesVisibles.add(Iterables.getLast(jeuDeCartes));
			jeuDeCartes.remove(jeuDeCartes.size() - 1);

			afficherCarte(cartesVisibles);

			// sortie du jeu
			joueur.setWon(true);
		}

	}

	private static void affichageCartesJoueurs(final List<Joueur> joueurs) {

		System.out.println(SEPARATEUR);
		System.out.println("------- Cartes des joueurs --------");
		System.out.println(SEPARATEUR);
		for (final Joueur j : joueurs) {
			System.out.print(j.getNom() + " : ");
			afficherCarte(j.getCartes());
		}
	}

	private static void afficherCarte(final List<Carte> cartes) {

		for (final Carte c : cartes) {
			System.out.print(c.getCode() + " " + c.getCouleur());
			if (cartes.indexOf(c) != cartes.size() - 1) {
				System.out.print(" | ");
			}
		}
		System.out.println();
	}

}
