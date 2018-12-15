package controller;

public class PokerMain {

	public static void main(final String[] args) {

		// creation du jeu de cartes
		final Setup setup = new Setup();
		final PokerApplication app = new PokerApplication();
		app.run(setup);

	}

}
