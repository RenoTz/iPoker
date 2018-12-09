package controller;

import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import model.Carte;
import model.CarteCombinaison;
import model.CombinaisonEnum;
import model.Joueur;
import utils.CombinaisonUtil;

public class PokerMain {

	private static final String SEPARATEUR = "-----------------------------------";

	public static void main(final String[] args) {

		// creation d'un joueur
		final Joueur joueur = new Joueur("Renaud");
		final Joueur adversaire = new Joueur("Gilbert");
		final List<Joueur> joueurs = Lists.newArrayList(joueur, adversaire);

		// creation du jeu de cartes
		final Setup setup = new Setup();
		final List<Carte> jeuDeCartes = setup.creerJeuDeCartes();
		final List<Carte> cartesVisibles = Lists.newArrayList();

		while (!joueurs.stream().filter(j -> j.isWon()).anyMatch(j -> j.isWon())) {

			// PRE-FLOP
			for (int i = 0; i < 2; i++) {

				for (final Joueur j : joueurs) {
					j.getCartes().add(Iterables.getLast(jeuDeCartes));
					jeuDeCartes.remove(jeuDeCartes.size() - 1);
				}
			}

			affichageCartesJoueurs(joueurs);
			afficherMainDesJoueurs(joueurs, cartesVisibles);

			// FLOP
			System.out.println(SEPARATEUR);
			System.out.println("------- Le FLOP --------");
			System.out.println(SEPARATEUR);
			for (int i = 0; i < 3; i++) {
				cartesVisibles.add(Iterables.getLast(jeuDeCartes));
				jeuDeCartes.remove(jeuDeCartes.size() - 1);
			}

			afficherCarte(cartesVisibles);
			afficherMainDesJoueurs(joueurs, cartesVisibles);

			// TURN
			System.out.println(SEPARATEUR);
			System.out.println("------- La TURN --------");
			System.out.println(SEPARATEUR);
			cartesVisibles.add(Iterables.getLast(jeuDeCartes));
			jeuDeCartes.remove(jeuDeCartes.size() - 1);

			afficherCarte(cartesVisibles);
			afficherMainDesJoueurs(joueurs, cartesVisibles);

			// RIVIERE
			System.out.println(SEPARATEUR);
			System.out.println("------- La RIVIERE --------");
			System.out.println(SEPARATEUR);
			cartesVisibles.add(Iterables.getLast(jeuDeCartes));
			jeuDeCartes.remove(jeuDeCartes.size() - 1);

			afficherCarte(cartesVisibles);
			afficherMainDesJoueurs(joueurs, cartesVisibles);

			// sortie du jeu
			joueur.setWon(true);
		}

	}

	private static void afficherMainDesJoueurs(final List<Joueur> joueurs, final List<Carte> cartesVisibles) {

		final List<Carte> cartesJoueursPlusCartesVisibles = Lists.newArrayList();
		for (final Joueur joueur : joueurs) {
			cartesJoueursPlusCartesVisibles.addAll(joueur.getCartes());
			cartesJoueursPlusCartesVisibles.addAll(cartesVisibles);
			final CarteCombinaison carteCombinaison = CombinaisonUtil
					.getMeilleureCombinaison(cartesJoueursPlusCartesVisibles);
			System.out.print("Main de " + joueur.getNom() + " : " + carteCombinaison.getCombinaison());

			if (carteCombinaison.getCombinaison() == CombinaisonEnum.HAUTEUR) {
				System.out.println(" " + carteCombinaison.getHauteur());
			} else {
				System.out.print(" de " + carteCombinaison.getCarte().name());
				System.out.println(" hauteur " + carteCombinaison.getHauteur());
			}

			cartesJoueursPlusCartesVisibles.clear();
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
			System.out.print(c.getCarteEnum().name() + " " + c.getCouleur());
			if (cartes.indexOf(c) != cartes.size() - 1) {
				System.out.print(" | ");
			}
		}
		System.out.println();
	}

}
