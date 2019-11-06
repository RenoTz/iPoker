package poker.ia.game.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import poker.ia.game.model.Joueur;
import poker.ia.game.model.Partie;

public class ActionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActionService.class);

	public void miser(final Partie partie, final Joueur joueur, final int mise) {

		joueur.setJetons(joueur.getJetons() - mise);
		partie.setMisesDesJoueurs(partie.getMisesDesJoueurs() + mise);
		LOGGER.info("{} mise {}", joueur.getNom(), mise);
	}
}
