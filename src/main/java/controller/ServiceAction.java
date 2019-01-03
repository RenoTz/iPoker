package controller;

import model.Joueur;
import model.Partie;

public class ServiceAction {

	public void miser(final Partie partie, final Joueur joueur, final int mise) {

		joueur.setJetons(joueur.getJetons() - mise);
		partie.setMisesDesJoueurs(partie.getMisesDesJoueurs() + mise);
	}
}
