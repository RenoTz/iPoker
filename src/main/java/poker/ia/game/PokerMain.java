package poker.ia.game;

import poker.ia.game.controller.PokerApplication;
import poker.ia.game.controller.Setup;

public class PokerMain {

	public static void main(final String[] args) {

		// chargement des données
		final Setup setup = new Setup();
		// lancement du jeu
		final PokerApplication app = new PokerApplication();
		app.run(setup);

	}

}
