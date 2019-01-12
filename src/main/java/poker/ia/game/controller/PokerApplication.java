package poker.ia.game.controller;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import poker.ia.game.controller.decision.impl.DecisionSimple;
import poker.ia.game.model.ActionJoueurEnum;
import poker.ia.game.model.Carte;
import poker.ia.game.model.CarteCombinaison;
import poker.ia.game.model.CombinaisonEnum;
import poker.ia.game.model.Joueur;
import poker.ia.game.model.Partie;
import poker.ia.game.services.ActionService;
import poker.ia.game.services.CombinaisonService;

public class PokerApplication {

	private static final String SEPARATEUR = "-----------------------------------";

	private static final String AFFICHAGE_JETONS_JOUEUR = "%s a misé %d jetons. Il reste %d jetons\n";

	final CombinaisonService combinaisonUtil = new CombinaisonService();
	final ActionService serviceAction = new ActionService();

	public void run(final Setup setup) {

		// creation d'un joueur
		final Partie partie = new Partie();
		final Joueur renaud = new Joueur("Renaud", new DecisionSimple());
		final Joueur gilbert = new Joueur("Gilbert", new DecisionSimple());
		final Joueur philippe = new Joueur("Philippe", new DecisionSimple());
		final Joueur jerome = new Joueur("Jérome", new DecisionSimple());
		final List<Joueur> joueurs = Lists.newArrayList(renaud, gilbert, philippe, jerome);

		final List<Carte> jeuDeCartes = Lists.newArrayList();
		final List<Carte> cartesVisibles = Lists.newArrayList();

		int tour = 1;
		partie.setSmallBlind(10);
		partie.setBigBlind(partie.getSmallBlind() * 2);

		while (isPlusDUnJoueurAvecJetons(joueurs)) {

			this.definirDealer(joueurs, partie);

			this.reinitialiserLesCartesEtMainsDesJoueurs(setup, joueurs, jeuDeCartes, cartesVisibles);

			// augmentation des blinds
			if (tour > 10) {
				partie.setSmallBlind(partie.getSmallBlind() * 2);
				partie.setBigBlind(partie.getBigBlind() * 2);
			}

			// PRE-FLOP
			System.out.println(SEPARATEUR);
			System.out.println("------- PRE-FLOP --------");
			System.out.println(SEPARATEUR);
			for (int i = 0; i < 2; i++) {
				for (final Joueur j : joueurs) {
					j.getCartes().add(Iterables.getLast(jeuDeCartes));
					jeuDeCartes.remove(jeuDeCartes.size() - 1);
				}
			}

			// definir le premier joueur (reordonner la liste des joueurs en
			// fonction)
			this.ordonnerJoueursAPArtirDuPremierJoueur(joueurs, partie);
			// small bling / big blind
			this.poserSmallBlingEtBigBlind(joueurs, partie);

			// affichage des cartes et des mains des joueurs
			this.affichageCartesJoueurs(joueurs);
			this.afficherMainDesJoueurs(joueurs, cartesVisibles);

			// mises des joueurs
			partie.setPot(partie.getPot() + this.misesDesJoueurs(joueurs, cartesVisibles, partie));
			// affichage du pot
			this.affichagePot(partie.getPot());

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
			partie.setPot(partie.getPot() + this.misesDesJoueurs(joueurs, cartesVisibles, partie));
			// affichage du pot
			this.affichagePot(partie.getPot());

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
			partie.setPot(partie.getPot() + this.misesDesJoueurs(joueurs, cartesVisibles, partie));
			// affichage du pot
			this.affichagePot(partie.getPot());

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
			partie.setPot(partie.getPot() + this.misesDesJoueurs(joueurs, cartesVisibles, partie));
			// affichage du pot
			this.affichagePot(partie.getPot());

			// sortie du jeu
			final Joueur gagnantDuPot = this.combinaisonUtil.determinerJoueurAvecLaMeilleureMain(joueurs, cartesVisibles);

			if (nonNull(gagnantDuPot)) {
				System.out.println(SEPARATEUR);
				System.out.println(gagnantDuPot.getNom() + " a remporté le pot.");
				System.out.println(SEPARATEUR);
				// le joueur gagne le pot
				gagnantDuPot.recupererLePot(partie.getPot());
				this.pause(3);
			}
			tour++;

		}

		final Joueur gagnantDuGame = joueurs.stream().filter(j -> j.getJetons() > 0).collect(Collectors.toList()).get(0);
		System.out.println(SEPARATEUR);
		System.out.println(gagnantDuGame.getNom() + " a remporté la partie.");
		System.out.println(SEPARATEUR);

	}

	private void poserSmallBlingEtBigBlind(final List<Joueur> joueurs, final Partie partie) {

		// poser la small blind / big blind
		this.serviceAction.miser(partie, joueurs.get(0), partie.getSmallBlind());
		joueurs.get(0).setAction(joueurs.get(0).getAction().actionSuivante(ActionJoueurEnum.RELANCER));
		joueurs.get(0).setDoitJouer(false);
		this.serviceAction.miser(partie, joueurs.get(1), partie.getBigBlind());
		joueurs.get(1).setAction(joueurs.get(1).getAction().actionSuivante(ActionJoueurEnum.RELANCER));
		joueurs.get(1).setDoitJouer(false);

		final int indexJoueurSuivant = joueurs.size() > 2 ? 2 : 0;
		joueurs.get(indexJoueurSuivant).setDoitJouer(true);
	}

	private void ordonnerJoueursAPArtirDuPremierJoueur(final List<Joueur> joueurs, final Partie partie) {

		final int indexDealer = joueurs.indexOf(partie.getDealer());
		final int indexPremierJoueur = indexDealer < (joueurs.size() - 1) ? indexDealer + 1 : 0;

		// definir le premier joueur
		joueurs.forEach(j -> j.setDoitJouer(false));
		joueurs.get(indexPremierJoueur).setDoitJouer(true);

		final List<Joueur> joueursAvantLePremierJoueur = Lists.newArrayList();
		final List<Joueur> joueursAPartirDuPremierJoueur = Lists.newArrayList();

		for (final Joueur joueur : joueurs) {
			if (joueurs.indexOf(joueur) >= indexPremierJoueur) {
				joueursAPartirDuPremierJoueur.add(joueur);
			} else {
				joueursAvantLePremierJoueur.add(joueur);
			}
		}

		joueurs.clear();
		joueurs.addAll(joueursAPartirDuPremierJoueur);
		joueurs.addAll(joueursAvantLePremierJoueur);
	}

	private void definirDealer(final List<Joueur> joueurs, final Partie partie) {

		partie.setDealer(nonNull(partie.getDealer()) && (joueurs.indexOf(partie.getDealer()) < (joueurs.size() - 1))
				? joueurs.get(joueurs.indexOf(partie.getDealer()) + 1)
				: joueurs.get(0));
	}

	private void reinitialiserLesCartesEtMainsDesJoueurs(final Setup setup, final List<Joueur> joueurs, final List<Carte> jeuDeCartes,
			final List<Carte> cartesVisibles) {

		cartesVisibles.clear();
		jeuDeCartes.clear();
		jeuDeCartes.addAll(setup.creerJeuDeCartes());
		joueurs.forEach(j -> {
			j.getCartes().clear();
			j.setCarteCombinaison(new CarteCombinaison());
		});
	}

	private void affichagePot(final int pot) {

		System.out.println(SEPARATEUR);
		System.out.println("Total du pot : " + pot);
		System.out.println(SEPARATEUR);
		this.pause(1);
	}

	private int misesDesJoueurs(final List<Joueur> joueurs, final List<Carte> cartesVisibles, final Partie partie) {

		final int totalDesMises = 0;

		while (!(joueurs.stream().allMatch(j -> j.getAction() == ActionJoueurEnum.CHECKER))
				|| !(joueurs.stream().allMatch(j -> j.getAction() == ActionJoueurEnum.SUIVRE))) {

			final Optional<Joueur> optional = joueurs.stream().filter(Joueur::doitJouer).findFirst();
			final Joueur joueurQuiDoitJouer = optional.orElse(optional.get());

			final int indexJoueurPrecedent = joueurs.indexOf(joueurQuiDoitJouer) != 0
					? joueurs.indexOf(joueurQuiDoitJouer) - 1
					: joueurs.size() - 1;
			final ActionJoueurEnum actionPrecedente = joueurs.get(indexJoueurPrecedent).getAction();

			joueurQuiDoitJouer.decider(actionPrecedente, cartesVisibles, partie);
			System.out.println(joueurQuiDoitJouer.getNom() + " a choisi de " + joueurQuiDoitJouer.getAction());
			if ((joueurQuiDoitJouer.getJetons() >= partie.getBigBlind()) && ((joueurQuiDoitJouer.getAction() == ActionJoueurEnum.SUIVRE)
					|| (joueurQuiDoitJouer.getAction() == ActionJoueurEnum.RELANCER))) {
				this.serviceAction.miser(partie, joueurQuiDoitJouer, partie.getBigBlind());
				System.out.println(SEPARATEUR);
				System.out.format(AFFICHAGE_JETONS_JOUEUR, joueurQuiDoitJouer.getNom(), partie.getBigBlind(),
						joueurQuiDoitJouer.getJetons());
				System.out.println(SEPARATEUR);
			} else if ((joueurQuiDoitJouer.getJetons() >= (partie.getBigBlind() * 2))
					&& (joueurQuiDoitJouer.getAction() == ActionJoueurEnum.SURRELANCER)) {
				this.serviceAction.miser(partie, joueurQuiDoitJouer, partie.getBigBlind() * 2);
				System.out.println(SEPARATEUR);
				System.out.format(AFFICHAGE_JETONS_JOUEUR, joueurQuiDoitJouer.getNom(), partie.getBigBlind() * 2,
						joueurQuiDoitJouer.getJetons());
				System.out.println(SEPARATEUR);
			} else {
				System.out.println(SEPARATEUR);
				System.out.println(joueurQuiDoitJouer.getNom() + " n'a rien fait");
				System.out.println(SEPARATEUR);
			}
			joueurQuiDoitJouer.setDoitJouer(false);
			final int indexJoueurSuivant = joueurs.indexOf(joueurQuiDoitJouer) < (joueurs.size() - 1)
					? joueurs.indexOf(joueurQuiDoitJouer) + 1
					: 0;
			joueurs.get(indexJoueurSuivant).setDoitJouer(true);

			this.pause(1);
		}

		// etat suivant des joueurs
		joueurs.forEach(j -> j.setAction(j.getAction().actionSuivante(null)));
		// on prend les mises des joueurs pour les mettre dans le pot
		partie.setPot(partie.getPot() + partie.getMisesDesJoueurs());
		partie.setMisesDesJoueurs(0);

		return totalDesMises;
	}

	private void pause(final long seconde) {

		try {
			Thread.sleep(seconde * 1000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static boolean isPlusDUnJoueurAvecJetons(final List<Joueur> joueurs) {

		return joueurs.stream().filter(j -> j.getJetons() > 0).collect(Collectors.toList()).size() > 1;
	}

	private void afficherMainDesJoueurs(final List<Joueur> joueurs, final List<Carte> cartesVisibles) {

		for (final Joueur joueur : joueurs) {
			final CarteCombinaison carteCombinaison = this.combinaisonUtil.getMeilleureCombinaison(joueur.getCartes(), cartesVisibles);
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
