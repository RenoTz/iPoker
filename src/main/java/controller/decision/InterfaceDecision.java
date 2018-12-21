package controller.decision;

import java.util.List;

import model.Carte;
import model.Joueur;

public interface InterfaceDecision {

	boolean deciderDeMiser(final List<Joueur> joueurs, final List<Carte> cartesVisibles, final boolean premierJoueur);

}
