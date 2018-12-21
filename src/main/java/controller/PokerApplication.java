package controller;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import model.Carte;
import model.CarteCombinaison;
import model.CombinaisonEnum;
import model.Joueur;
import utils.CombinaisonUtil;

public class PokerApplication {

	private static final String SEPARATEUR = "-----------------------------------";

	private static final String AFFICHAGE_JETONS_JOUEUR = "%s a misé %d jetons. Il reste %d jetons";

	final CombinaisonUtil combinaisonUtil = new CombinaisonUtil();

	public void run(final Setup setup) {

		// creation d'un joueur
		final Joueur joueur = new Joueur("Renaud");
		final Joueur adversaire = new Joueur("Gilbert");
		final List<Joueur> joueurs = Lists.newArrayList(joueur, adversaire);

		final List<Carte> jeuDeCartes = Lists.newArrayList();
		final List<Carte> cartesVisibles = Lists.newArrayList();

		int tour = 1;
		final int smallBlind = 10;
		int bigBling = smallBlind * 2;

		while (isPlusDUnJoueurAvecJetons(joueurs)) {

			this.reinitialiserLesCartesEtMainsDesJoueurs(setup, joueurs, jeuDeCartes, cartesVisibles);

			// augmentation des blinds
			if (tour > 3) {
				bigBling += bigBling;
			}

			int pot = 0;

			// PRE-FLOP
			for (int i = 0; i < 2; i++) {
				for (final Joueur j : joueurs) {
					j.getCartes().add(Iterables.getLast(jeuDeCartes));
					jeuDeCartes.remove(jeuDeCartes.size() - 1);
				}
			}

			// affichage des cartes et des mains des joueurs
			this.affichageCartesJoueurs(joueurs);
			this.afficherMainDesJoueurs(joueurs, cartesVisibles);

			// mises des joueurs
			pot += this.misesDesJoueurs(joueurs, cartesVisibles, bigBling);
			// affichage du pot
			this.affichagePot(pot);

			// FLOP
			System.out.println(SEPARATEUR);
			System.out.println("------- Le FLOP --------");
			System.out.println(SEPARATEUR);
			for (int i = 0; i < 3; i++) {
				cartesVisibles.add(Iterables.getLast(jeuDeCartes));
				jeuDeCartes.remove(jeuDeCartes.size() - 1);
			}

			// affichage des cartes et des mains des joueurs
			this.afficherCarte(cartesVisibles);
			this.afficherMainDesJoueurs(joueurs, cartesVisibles);

			// mises des joueurs
			pot += this.misesDesJoueurs(joueurs, cartesVisibles, bigBling);
			// affichage du pot
			this.affichagePot(pot);

			// TURN
			System.out.println(SEPARATEUR);
			System.out.println("------- La TURN --------");
			System.out.println(SEPARATEUR);
			cartesVisibles.add(Iterables.getLast(jeuDeCartes));
			jeuDeCartes.remove(jeuDeCartes.size() - 1);

			// affichage des cartes et des mains des joueurs
			this.afficherCarte(cartesVisibles);
			this.afficherMainDesJoueurs(joueurs, cartesVisibles);

			// mises des joueurs
			pot += this.misesDesJoueurs(joueurs, cartesVisibles, bigBling);
			// affichage du pot
			this.affichagePot(pot);

			// RIVIERE
			System.out.println(SEPARATEUR);
			System.out.println("------- La RIVIERE --------");
			System.out.println(SEPARATEUR);
			cartesVisibles.add(Iterables.getLast(jeuDeCartes));
			jeuDeCartes.remove(jeuDeCartes.size() - 1);

			// affichage des cartes et des mains des joueurs
			this.afficherCarte(cartesVisibles);
			this.afficherMainDesJoueurs(joueurs, cartesVisibles);

			// mises des joueurs
			pot += this.misesDesJoueurs(joueurs, cartesVisibles, bigBling);
			// affichage du pot
			this.affichagePot(pot);

			// sortie du jeu
			final Joueur gagnantDuPot = this.combinaisonUtil.determinerJoueurAvecLaMeilleureMain(joueurs,
					cartesVisibles);

			if (nonNull(gagnantDuPot)) {
				System.out.println(SEPARATEUR);
				System.out.println(gagnantDuPot.getNom() + " a remporté le pot.");
				System.out.println(SEPARATEUR);
				// le joueur gagne le pot
				gagnantDuPot.recupererLePot(pot);
				this.pause(3);
			}
			tour++;

		}
		final Joueur gagnantDuGame = joueurs.stream().filter(j -> j.getJetons() > 0).collect(Collectors.toList())
				.get(0);
		System.out.println(SEPARATEUR);
		System.out.println(gagnantDuGame.getNom() + " a remporté la partie.");
		System.out.println(SEPARATEUR);

	}

	private void reinitialiserLesCartesEtMainsDesJoueurs(final Setup setup, final List<Joueur> joueurs,
			final List<Carte> jeuDeCartes, List<Carte> cartesVisibles) {

		cartesVisibles.clear();
		jeuDeCartes.clear();
		jeuDeCartes.addAll(setup.creerJeuDeCartes());
		joueurs.forEach(j -> {
			j.getCartes().clear();
			j.setCarteCombinaison(new CarteCombinaison());
		});
	}

	private void affichagePot(int pot) {

		System.out.println(SEPARATEUR);
		System.out.println("Total du pot : " + pot);
		System.out.println(SEPARATEUR);
		this.pause(1);
	}

	private int misesDesJoueurs(final List<Joueur> joueurs, final List<Carte> cartesVisibles, final int bigBling) {

		int totalDesMises = 0;

		for (final Joueur j : joueurs) {
			if (this.combinaisonUtil.getMeilleureCombinaison(j.getCartes(), cartesVisibles)
					.getCombinaison() != CombinaisonEnum.HAUTEUR) {
				final int miseJoueur = j.miser(bigBling);
				totalDesMises += miseJoueur;
				System.out.println(SEPARATEUR);
				System.out.format(AFFICHAGE_JETONS_JOUEUR, j.getNom(), miseJoueur, j.getJetons());
				System.out.println(SEPARATEUR);
			}
		}
		this.pause(1);

		return totalDesMises;
	}

	private void pause(long seconde) {

		try {
			Thread.sleep(seconde * 1000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static boolean isPlusDUnJoueurAvecJetons(List<Joueur> joueurs) {

		return joueurs.stream().filter(j -> j.getJetons() > 0).collect(Collectors.toList()).size() > 1;
	}

	private void afficherMainDesJoueurs(final List<Joueur> joueurs, final List<Carte> cartesVisibles) {

		for (final Joueur joueur : joueurs) {
			final CarteCombinaison carteCombinaison = this.combinaisonUtil.getMeilleureCombinaison(joueur.getCartes(),
					cartesVisibles);
			System.out.print("Main de " + joueur.getNom() + " : " + carteCombinaison.getCombinaison());

			if (carteCombinaison.getCombinaison() == CombinaisonEnum.HAUTEUR) {
				System.out.println(" " + carteCombinaison.getHauteur());
			} else {
				if (nonNull(carteCombinaison.getCartes()[0])) {
					System.out.print(" de " + carteCombinaison.getCartes()[0].name());
					if (nonNull(carteCombinaison.getCartes()[1])) {
						System.out.print(" et de " + carteCombinaison.getCartes()[1].name());
					}
				}
				System.out.println(" hauteur " + carteCombinaison.getHauteur());
			}
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
		System.out.println(SEPARATEUR);
	}

}
