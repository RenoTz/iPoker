package poker.ia.game.controller.decision;

import java.util.List;

import poker.ia.game.model.ActionJoueurEnum;
import poker.ia.game.model.Carte;
import poker.ia.game.model.Joueur;
import poker.ia.game.model.Partie;

public interface InterfaceDecision {

	void decider(final Joueur joueur, final ActionJoueurEnum actionPrecedente, final List<Carte> cartesVisibles, final Partie partie);

}
