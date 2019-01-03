package controller.decision;

import java.util.List;

import model.ActionJoueurEnum;
import model.Carte;
import model.Joueur;
import model.Partie;

public interface InterfaceDecision {

	void decider(Joueur joueur, final ActionJoueurEnum actionPrecedente, final List<Carte> cartesVisibles,
			final Partie partie);

}
