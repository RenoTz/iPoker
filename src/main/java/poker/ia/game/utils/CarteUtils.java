package poker.ia.game.utils;

import java.util.Collections;
import java.util.List;

import poker.ia.game.model.Carte;

public class CarteUtils {

	public static void melangerJeuDeCartes(final List<Carte> jeuDeCartes) {

		Collections.shuffle(jeuDeCartes);
		Collections.shuffle(jeuDeCartes);
		Collections.shuffle(jeuDeCartes);
	}

}
