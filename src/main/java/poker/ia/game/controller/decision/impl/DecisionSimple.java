package poker.ia.game.controller.decision.impl;

import static java.util.Objects.nonNull;

import java.util.List;

import poker.ia.game.controller.decision.InterfaceDecision;
import poker.ia.game.model.ActionJoueurEnum;
import poker.ia.game.model.Carte;
import poker.ia.game.model.CombinaisonEnum;
import poker.ia.game.model.Joueur;
import poker.ia.game.model.Partie;

public class DecisionSimple implements InterfaceDecision {

	@Override
	public void decider(final Joueur joueur, final ActionJoueurEnum actionPrecedente, final List<Carte> cartesVisibles,
			final Partie partie) {

		switch (actionPrecedente) {
		case RELANCER :
			this.suivreOuRelancerOuPasser(partie, joueur);
			break;
		case SURRELANCER :
			this.suivreOuPasser(joueur);
			break;
		case CHECKER :
			this.checkerOuRelancer(partie, joueur);
			break;
		case SUIVRE :
			this.checkerOuSuivreOuRelancerOuPasser(partie, joueur);
			break;
		case ATTENDRE :
			this.checkerOuRelancer(partie, joueur);
			break;
		default :

		}

	}

	private void checkerOuSuivreOuRelancerOuPasser(final Partie partie, final Joueur joueur) {

		if ((partie.getRelanceRestante() > 0) && nonNull(joueur.getCarteCombinaison().getCombinaison())) {
			if (joueur.getCarteCombinaison().getCombinaison().getValeur() > 3) {
				this.relancer(partie, joueur);
			} else {
				this.suivre(joueur);
			}
		} else {
			this.passer(joueur);
		}
	}

	private void suivreOuPasser(final Joueur joueur) {

		if (joueur.getCarteCombinaison().getCombinaison() != CombinaisonEnum.HAUTEUR) {
			this.suivre(joueur);
		} else {
			this.passer(joueur);
		}
	}

	private void suivreOuRelancerOuPasser(final Partie partie, final Joueur joueur) {

		if ((partie.getRelanceRestante() > 0) && nonNull(joueur.getCarteCombinaison().getCombinaison())
				&& (joueur.getCarteCombinaison().getCombinaison().getValeur() > 2)) {
			this.relancer(partie, joueur);
		} else if (joueur.getCarteCombinaison().getCombinaison() != CombinaisonEnum.HAUTEUR) {
			this.suivre(joueur);
		} else {
			this.suivre(joueur);
			//			this.passer(joueur);
		}
	}

	private void checkerOuRelancer(final Partie partie, final Joueur joueur) {

		if ((partie.getRelanceRestante() > 0) && nonNull(joueur.getCarteCombinaison().getCombinaison())
				&& (joueur.getCarteCombinaison().getCombinaison().getValeur() > 2)) {
			this.relancer(partie, joueur);
		} else {
			this.checker(joueur);
		}
	}

	private void checker(final Joueur joueur) {

		joueur.setAction(joueur.getAction().actionSuivante(ActionJoueurEnum.CHECKER));
	}

	private void suivre(final Joueur joueur) {

		joueur.setAction(joueur.getAction().actionSuivante(ActionJoueurEnum.SUIVRE));
	}

	private void passer(final Joueur joueur) {

		joueur.setAction(joueur.getAction().actionSuivante(ActionJoueurEnum.PASSER));
	}

	private void relancer(final Partie partie, final Joueur joueur) {

		joueur.setAction(joueur.getAction().actionSuivante(ActionJoueurEnum.RELANCER));
		partie.setRelanceRestante(partie.getRelanceRestante() - 1);
	}

}
