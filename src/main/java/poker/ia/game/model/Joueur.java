package poker.ia.game.model;

import java.util.List;

import com.google.common.collect.Lists;

import poker.ia.game.controller.decision.InterfaceDecision;

public class Joueur {

	private String nom;
	private List<Carte> cartes;
	private int jetons;
	private int mise;
	private CarteCombinaison carteCombinaison;
	private boolean doitJouer;
	private ActionJoueurEnum action;
	private boolean dealer;

	private final InterfaceDecision decision;

	public Joueur(final String nom, final InterfaceDecision decision) {

		this.nom = nom;
		this.cartes = Lists.newArrayList();
		// cave initiale
		this.jetons = 2000;
		// etat initial
		this.action = ActionJoueurEnum.ATTENDRE;
		// init du niveau de décision
		this.decision = decision;
	}

	public void decider(final ActionJoueurEnum actionPrecedente, final List<Carte> cartesVisibles,
			final Partie partie) {

		this.decision.decider(this, actionPrecedente, cartesVisibles, partie);
	}

	public String getNom() {

		return this.nom;
	}

	public void setNom(final String nom) {

		this.nom = nom;
	}

	public List<Carte> getCartes() {

		return this.cartes;
	}

	public void setCartes(final List<Carte> cartes) {

		this.cartes = cartes;
	}

	public CarteCombinaison getCarteCombinaison() {

		return this.carteCombinaison;
	}

	public void setCarteCombinaison(final CarteCombinaison combinaison) {

		this.carteCombinaison = combinaison;
	}

	public int getJetons() {

		return this.jetons;
	}

	public void setJetons(final int jetons) {

		this.jetons = jetons;
	}

	public void recupererLePot(final int pot) {

		this.setJetons(this.getJetons() + pot);

	}

	public ActionJoueurEnum getAction() {

		return this.action;
	}

	public void setAction(final ActionJoueurEnum action) {

		this.action = action;
	}

	public boolean doitJouer() {

		return this.doitJouer;
	}

	public void setDoitJouer(final boolean doitJouer) {

		this.doitJouer = doitJouer;
	}

	public int getMise() {
		return this.mise;
	}

	public void setMise(final int mise) {
		this.mise = mise;
	}

	public boolean isDealer() {
		return this.dealer;
	}

	public void setDealer(final boolean dealer) {
		this.dealer = dealer;
	}

}
