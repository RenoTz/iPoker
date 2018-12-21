package controller.decision;

import java.util.List;

import model.ActionJoueurEnum;
import model.Carte;
import model.CombinaisonEnum;
import model.Joueur;

public class DecisionSimple implements InterfaceDecision {

	@Override
	public boolean deciderDeMiser(final List<Joueur> joueurs, final List<Carte> cartesVisibles,
			final boolean premierJoueur) {

		for (final Joueur joueur : joueurs) {
			if (joueur.getActionJoueur() == ActionJoueurEnum.EN_ATTENTE
					&& joueur.getCarteCombinaison().getCombinaison() != CombinaisonEnum.HAUTEUR) {
				joueur.getActionJoueur().actionSuivante(ActionJoueurEnum.SUIVRE, premierJoueur);
				return true;
			}
		}
		return false;
	}

}
