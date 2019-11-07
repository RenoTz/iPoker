package poker.ia.game.utils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import poker.ia.game.model.Joueur;
import poker.ia.game.model.Partie;

public class JoueurUtils {

	public static void definirDealer(final List<Joueur> joueurs) {

		final Optional<Joueur> dealerOpt = joueurs.stream().filter(Joueur::isDealer).findFirst();
		joueurs.forEach(j -> j.setDealer(false));

		if(dealerOpt.isPresent()) {
			final Joueur dealer = dealerOpt.get();
			final Joueur newDealer = joueurs.indexOf(dealer) != joueurs.size() -1 ? joueurs.get(joueurs.indexOf(dealer) + 1) : joueurs.get(0);
			newDealer.setDealer(true);
		}else {
			joueurs.get(0).setDealer(true);
		}
	}

	public static void ordonnerJoueursAPArtirDuPremierJoueur(final List<Joueur> joueurs, final Partie partie) {

		final int indexDealer = joueurs.indexOf(partie.getDealer());
		final int indexPremierJoueur = indexDealer < (joueurs.size() - 1) ? indexDealer + 1 : 0;

		// definir le premier joueur
		joueurs.forEach(j -> j.setDoitJouer(false));
		joueurs.get(indexPremierJoueur).setDoitJouer(true);

		final List<Joueur> joueursAvantLePremierJoueur = Lists.newArrayList();
		final List<Joueur> joueursAPartirDuPremierJoueur = Lists.newArrayList();

		for (final Joueur joueur : joueurs) {
			if (joueurs.indexOf(joueur) >= indexPremierJoueur) {
				joueursAPartirDuPremierJoueur.add(joueur);
			} else {
				joueursAvantLePremierJoueur.add(joueur);
			}
		}

		joueurs.clear();
		joueurs.addAll(joueursAPartirDuPremierJoueur);
		joueurs.addAll(joueursAvantLePremierJoueur);
	}

	public static boolean isPlusDUnJoueurAvecJetons(final List<Joueur> joueurs) {

		return joueurs.stream().filter(j -> j.getJetons() > 0).collect(Collectors.toList()).size() > 1;
	}

	public static void definirPremierJoueurApresDealer(final List<Joueur> joueurs) {

		joueurs.stream().filter(Joueur::isDealer).findFirst().ifPresent(j -> {
			if(joueurs.indexOf(j) != joueurs.size() - 1) {
				joueurs.get(joueurs.indexOf(j) + 1).setDoitJouer(true);
			}else {
				joueurs.get(0).setDoitJouer(true);			}
		});
	}

}
