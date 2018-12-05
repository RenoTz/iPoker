package utils;

import java.util.Collections;
import java.util.List;

import model.Carte;

public class CarteUtils {

	/*
	 * Permet de mélanger le jeu de cartes 3 fois
	 */
	public static void melangerJeuDeCartes(final List<Carte> jeuDeCartes) {

		Collections.shuffle(jeuDeCartes);
		Collections.shuffle(jeuDeCartes);
		Collections.shuffle(jeuDeCartes);
	}

}
