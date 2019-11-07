package poker.ia.game.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import poker.ia.game.model.ActionJoueurEnum;
import poker.ia.game.model.Joueur;

public class ActionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActionService.class);

	public void miseComplexe(final Joueur joueur, final Joueur dernierJoueurQuiAMise, final int bigBlind) {

		int mise = 0;
		if(joueur.getAction() == ActionJoueurEnum.SUIVRE) {
			mise = dernierJoueurQuiAMise.getMise() - joueur.getMise();
		} else if (joueur.getAction() == ActionJoueurEnum.RELANCER) {
			mise = dernierJoueurQuiAMise.getMise() + bigBlind;
		} else if ((joueur.getJetons() >= (bigBlind * 2))
				&& joueur.getAction() == ActionJoueurEnum.SURRELANCER) {
			mise = dernierJoueurQuiAMise.getMise() + (bigBlind * 2);
		} 

		joueur.setJetons(joueur.getJetons() - mise);
		joueur.setMise(joueur.getMise() + mise);
		LOGGER.info("{} mise {}", joueur.getNom(), mise);
	}

	public void miseSimple(final Joueur joueur, final int mise) {
		joueur.setJetons(joueur.getJetons() - mise);
		joueur.setMise(joueur.getMise() + mise);
		LOGGER.info("{} mise {}", joueur.getNom(), mise);
	}


}
