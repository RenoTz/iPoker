package poker.ia.game.services;

import poker.ia.game.model.Joueur;
import poker.ia.game.model.Partie;

public class ActionService {

	public void miser(final Partie partie, final Joueur joueur, final int mise) {

		joueur.setJetons(joueur.getJetons() - mise);
		partie.setMisesDesJoueurs(partie.getMisesDesJoueurs() + mise);
	}
}
