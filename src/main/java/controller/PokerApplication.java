package controller;

import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import model.Carte;
import model.CarteCombinaison;
import model.CombinaisonEnum;
import model.Joueur;
import utils.CombinaisonUtil;

public class PokerApplication {

	private static final String SEPARATEUR = "-----------------------------------";

	final CombinaisonUtil combinaisonUtil = new CombinaisonUtil();

	public boolean run(final Setup setup) {

		// creation d'un joueur
		final Joueur joueur = new Joueur("Renaud");
		final Joueur adversaire = new Joueur("Gilbert");
		final List<Joueur> joueurs = Lists.newArrayList(joueur, adversaire);

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

			this.affichageCartesJoueurs(joueurs);
			this.afficherMainDesJoueurs(joueurs, cartesVisibles);

			// FLOP
			System.out.println(SEPARATEUR);
			System.out.println("------- Le FLOP --------");
			System.out.println(SEPARATEUR);
			for (int i = 0; i < 3; i++) {
				cartesVisibles.add(Iterables.getLast(jeuDeCartes));
				jeuDeCartes.remove(jeuDeCartes.size() - 1);
			}

			this.afficherCarte(cartesVisibles);
			this.afficherMainDesJoueurs(joueurs, cartesVisibles);

			// TURN
			System.out.println(SEPARATEUR);
			System.out.println("------- La TURN --------");
			System.out.println(SEPARATEUR);
			cartesVisibles.add(Iterables.getLast(jeuDeCartes));
			jeuDeCartes.remove(jeuDeCartes.size() - 1);

			this.afficherCarte(cartesVisibles);
			this.afficherMainDesJoueurs(joueurs, cartesVisibles);

			// RIVIERE
			System.out.println(SEPARATEUR);
			System.out.println("------- La RIVIERE --------");
			System.out.println(SEPARATEUR);
			cartesVisibles.add(Iterables.getLast(jeuDeCartes));
			jeuDeCartes.remove(jeuDeCartes.size() - 1);

			this.afficherCarte(cartesVisibles);
			this.afficherMainDesJoueurs(joueurs, cartesVisibles);

			// sortie du jeu
			joueur.setWon(true);
		}
		return true;
	}

	private void afficherMainDesJoueurs(final List<Joueur> joueurs, final List<Carte> cartesVisibles) {

		final List<Carte> cartesJoueursPlusCartesVisibles = Lists.newArrayList();
		for (final Joueur joueur : joueurs) {
			cartesJoueursPlusCartesVisibles.addAll(joueur.getCartes());
			cartesJoueursPlusCartesVisibles.addAll(cartesVisibles);
			final CarteCombinaison carteCombinaison = this.combinaisonUtil.getMeilleureCombinaison(cartesJoueursPlusCartesVisibles);
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

	private void affichageCartesJoueurs(final List<Joueur> joueurs) {

		System.out.println(SEPARATEUR);
		System.out.println("------- Cartes des joueurs --------");
		System.out.println(SEPARATEUR);
		for (final Joueur j : joueurs) {
			System.out.print(j.getNom() + " : ");
			this.afficherCarte(j.getCartes());
		}
	}

	private void afficherCarte(final List<Carte> cartes) {

		for (final Carte c : cartes) {
			System.out.print(c.getCarteEnum().name() + " " + c.getCouleur());
			if (cartes.indexOf(c) != (cartes.size() - 1)) {
				System.out.print(" | ");
			}
		}
		System.out.println();
	}

}
