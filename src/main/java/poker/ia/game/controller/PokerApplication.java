package poker.ia.game.controller;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import poker.ia.game.utils.JoueurUtils;

public class PokerApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(PokerApplication.class);

	private static final String SEPARATEUR = "----------------------------------";

	private final CombinaisonService combinaisonUtil = new CombinaisonService();
	private final ActionService serviceAction = new ActionService();

	public void run(final Setup setup) throws InterruptedException {

		final Partie partie = new Partie();
		// creation des joueurs
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

		while (JoueurUtils.isPlusDUnJoueurAvecJetons(joueurs)) {

			JoueurUtils.definirDealer(joueurs);

			this.reinitialiserLesCartesEtMainsDesJoueurs(setup, joueurs, jeuDeCartes, cartesVisibles);

			// augmentation des blinds
			if (tour > 10) {
				partie.setSmallBlind(partie.getSmallBlind() * 2);
				partie.setBigBlind(partie.getBigBlind() * 2);
			}

			// PRE-FLOP - distribution des cartes aux joueurs
			for (int i = 0; i < 2; i++) {
				joueurs.forEach(j -> {
					j.getCartes().add(Iterables.getLast(jeuDeCartes));
					jeuDeCartes.remove(Iterables.getLast(jeuDeCartes));
				});
			}

			// definir le premier joueur (reordonner la liste des joueurs en
			// fonction)
			JoueurUtils.ordonnerJoueursAPArtirDuPremierJoueur(joueurs, partie);
			// small bling / big blind
			this.poserSmallBlingEtBigBlind(joueurs, partie);

			// affichage des mains des joueurs
			this.afficherMainDesJoueurs(joueurs, cartesVisibles);

			// mises des joueurs
			this.misesDesJoueurs(joueurs, cartesVisibles, partie);
			// affichage du pot
			this.affichagePot(partie.getPot());

			// FLOP
			LOGGER.info(SEPARATEUR);
			LOGGER.info("------- Le FLOP --------");
			LOGGER.info(SEPARATEUR);
			for (int i = 0; i < 3; i++) {
				cartesVisibles.add(Iterables.getLast(jeuDeCartes));
				jeuDeCartes.remove(Iterables.getLast(jeuDeCartes));
			}

			// definir qui doit jouer
			JoueurUtils.definirPremierJoueurApresDealer(joueurs);
			// affichage des cartes et des mains des joueurs
			this.afficherCarte(cartesVisibles);
			this.afficherMainDesJoueurs(joueurs, cartesVisibles);

			// mises des joueurs
			this.misesDesJoueurs(joueurs, cartesVisibles, partie);
			// affichage du pot
			this.affichagePot(partie.getPot());

			// TURN
			LOGGER.info(SEPARATEUR);
			LOGGER.info("------- La TURN --------");
			LOGGER.info(SEPARATEUR);
			cartesVisibles.add(Iterables.getLast(jeuDeCartes));
			jeuDeCartes.remove(jeuDeCartes.size() - 1);

			// definir qui doit jouer
			JoueurUtils.definirPremierJoueurApresDealer(joueurs);
			// affichage des cartes et des mains des joueurs
			this.afficherCarte(cartesVisibles);
			this.afficherMainDesJoueurs(joueurs, cartesVisibles);

			// mises des joueurs
			this.misesDesJoueurs(joueurs, cartesVisibles, partie);
			// affichage du pot
			this.affichagePot(partie.getPot());

			// RIVIERE
			LOGGER.info(SEPARATEUR);
			LOGGER.info("------- La RIVIERE --------");
			LOGGER.info(SEPARATEUR);
			cartesVisibles.add(Iterables.getLast(jeuDeCartes));
			jeuDeCartes.remove(jeuDeCartes.size() - 1);

			// definir qui doit jouer
			JoueurUtils.definirPremierJoueurApresDealer(joueurs);
			// affichage des cartes et des mains des joueurs
			this.afficherCarte(cartesVisibles);
			this.afficherMainDesJoueurs(joueurs, cartesVisibles);

			// mises des joueurs
			this.misesDesJoueurs(joueurs, cartesVisibles, partie);
			// affichage du pot
			this.affichagePot(partie.getPot());

			// sortie du jeu
			final Joueur gagnantDuPot = this.combinaisonUtil.determinerJoueurAvecLaMeilleureMain(joueurs, cartesVisibles);

			if (nonNull(gagnantDuPot)) {
				LOGGER.info(SEPARATEUR);
				LOGGER.info("{} a remporté le pot. ({} jetons)", gagnantDuPot.getNom(), partie.getPot());
				LOGGER.info(SEPARATEUR);
				// le joueur gagne le pot
				gagnantDuPot.recupererLePot(partie.getPot());
				// totaux
				joueurs.forEach(j -> {
					LOGGER.info("{} a {} jetons", j.getNom(), j.getJetons());
					LOGGER.info(SEPARATEUR);
				});
				this.pause(3);
			}
			tour++;

		}

		final Joueur gagnantDuGame = joueurs.stream().filter(j -> j.getJetons() > 0).collect(Collectors.toList()).get(0);
		LOGGER.info(SEPARATEUR);
		LOGGER.info("{} a remporté la partie.", gagnantDuGame.getNom());
		LOGGER.info(SEPARATEUR);

	}

	private void poserSmallBlingEtBigBlind(final List<Joueur> joueurs, final Partie partie) {

		// poser la small blind / big blind
		LOGGER.info(SEPARATEUR);
		LOGGER.info("---------- SMALL BLIND -----------");
		LOGGER.info(SEPARATEUR);
		this.serviceAction.miseSimple(joueurs.get(0), partie.getSmallBlind());
		joueurs.get(0).setAction(joueurs.get(0).getAction().actionSuivante(ActionJoueurEnum.MISER_SMALL_BLIND));
		joueurs.get(0).setDoitJouer(false);
		LOGGER.info(SEPARATEUR);
		LOGGER.info("----------- BIG BLIND ------------");
		LOGGER.info(SEPARATEUR);
		this.serviceAction.miseSimple(joueurs.get(1), partie.getBigBlind());
		joueurs.get(1).setAction(joueurs.get(1).getAction().actionSuivante(ActionJoueurEnum.MISER_BIG_BLIND));
		joueurs.get(1).setDoitJouer(false);
		LOGGER.info(SEPARATEUR);

		final int indexJoueurSuivant = joueurs.size() > 2 ? 2 : 0;
		joueurs.get(indexJoueurSuivant).setDoitJouer(true);
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

	private void affichagePot(final int pot) throws InterruptedException {

		LOGGER.info(SEPARATEUR);
		LOGGER.info("Total du pot : {}", pot);
		LOGGER.info(SEPARATEUR);
		this.pause(1);
	}

	private void misesDesJoueurs(final List<Joueur> joueurs, final List<Carte> cartesVisibles, final Partie partie) {

		while (joueurs.stream().filter(j -> (j.getAction() ==  ActionJoueurEnum.CHECKER 
				|| j.getAction() == ActionJoueurEnum.SUIVRE)).count() != 4) {

			joueurs.stream().filter(Joueur::doitJouer).findFirst().ifPresent( joueurQuiDoitJouer -> { 

				final int indexJoueurPrecedent = this.getIndexJoueurPrecedent(joueurs, joueurQuiDoitJouer);
				final ActionJoueurEnum actionPrecedente = joueurs.get(indexJoueurPrecedent).getAction(); 

				joueurQuiDoitJouer.decider(actionPrecedente, cartesVisibles, partie);

				LOGGER.info("{} a choisi de {}", joueurQuiDoitJouer.getNom(), joueurQuiDoitJouer.getAction());

				if (joueurQuiDoitJouer.getJetons() >= partie.getBigBlind()) {
					final Joueur dernierJoueurQuiAMise = this.getDernierJoueurQuiAMise(joueurs, indexJoueurPrecedent);
					this.serviceAction.miseComplexe(joueurQuiDoitJouer, dernierJoueurQuiAMise, partie.getBigBlind());
				}
				LOGGER.info(SEPARATEUR);
				joueurQuiDoitJouer.setDoitJouer(false);
				final int indexJoueurSuivant = joueurs.indexOf(joueurQuiDoitJouer) < (joueurs.size() - 1)
						? joueurs.indexOf(joueurQuiDoitJouer) + 1
								: 0;
				joueurs.get(indexJoueurSuivant).setDoitJouer(true);

				try {
					this.pause(1);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			});
		}

		// etat suivant des joueurs
		joueurs.forEach(j -> j.setAction(j.getAction().actionSuivante(null)));
		// on prend les mises des joueurs pour les mettre dans le pot
		partie.setPot(partie.getPot() + joueurs.stream().map(Joueur::getMise).reduce(0, Integer::sum));
		// remise à 0 des mises des joueurs et mise en attente des joueurs
		joueurs.forEach(j -> {
			j.setMise(0);
			if(j.getAction() != ActionJoueurEnum.A_TERMINER_DE_JOUER) {
				j.setAction(ActionJoueurEnum.ATTENDRE);
			}
		});

	}

	private Joueur getDernierJoueurQuiAMise(final List<Joueur> joueurs, final int indexJoueur) {

		final Joueur dernierJoueurQuiAMise = joueurs.get(indexJoueur);
		if(dernierJoueurQuiAMise.getAction() != ActionJoueurEnum.PASSER) {
			return dernierJoueurQuiAMise;
		}else {
			return this.getDernierJoueurQuiAMise(joueurs, indexJoueur - 1 >= 0 ? indexJoueur - 1 : joueurs.size() - 1);
		}
	}

	private int getIndexJoueurPrecedent(final List<Joueur> joueurs, final Joueur joueurQuiDoitJouer) {
		return joueurs.indexOf(joueurQuiDoitJouer) != 0 
				? joueurs.indexOf(joueurQuiDoitJouer) - 1
						: joueurs.size() - 1;
	}

	private void pause(final long seconde) throws InterruptedException {

		try {
			Thread.sleep(seconde * 1000);
		} catch (final InterruptedException e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
	}

	private void afficherMainDesJoueurs(final List<Joueur> joueurs, final List<Carte> cartesVisibles) {

		for (final Joueur joueur : joueurs) {
			final CarteCombinaison carteCombinaison = this.combinaisonUtil.getMeilleureCombinaison(joueur.getCartes(), cartesVisibles);
			LOGGER.info("Main de {} : {}", joueur.getNom(), joueur.getCartes().stream()
					.map(c -> c.getCarteEnum().name().concat(" ").concat(c.getCouleur().name()))
					.collect(Collectors.joining("|")));


			joueur.setCarteCombinaison(carteCombinaison);

			if (carteCombinaison.getCombinaison() == CombinaisonEnum.HAUTEUR) {
				LOGGER.info("{} {}", carteCombinaison.getCombinaison(), carteCombinaison.getHauteur());
			} else {
				if (nonNull(carteCombinaison.getCartes()[0])) {
					LOGGER.info("{} de {}", carteCombinaison.getCombinaison(), carteCombinaison.getCartes()[0]);
					if (nonNull(carteCombinaison.getCartes()[1])) {
						LOGGER.info(" et de {}", carteCombinaison.getCartes()[1]);
					}
				}
				if(nonNull(carteCombinaison.getHauteur())) {
					LOGGER.info(" HAUTEUR {}", carteCombinaison.getHauteur());
				}
			}
			LOGGER.info(SEPARATEUR);
		}
	}

	private void afficherCarte(final List<Carte> cartes) {

		final String cartesAAfficher = cartes.stream().map(Carte::toString).collect(Collectors.joining("|"));
		LOGGER.info(cartesAAfficher);
		LOGGER.info(SEPARATEUR);
	}

}
