package model;

import java.util.List;

import com.google.common.collect.Lists;

public class Joueur {

	private String nom;
	private List<Carte> cartes;
	private int jetons;
	private CarteCombinaison carteCombinaison;
	private boolean dealer;
	private boolean won;

	public Joueur(final String nom) {

		this.nom = nom;
		this.cartes = Lists.newArrayList();
		// cave initiale
		this.jetons = 2000;
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

	public boolean isWon() {

		return this.won;
	}

	public void setWon(final boolean won) {

		this.won = won;
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

	public int miser(final int mise) {

		this.jetons -= mise;
		return mise;

	}

	public boolean isDealer() {

		return this.dealer;
	}

	public void setDealer(boolean dealer) {

		this.dealer = dealer;
	}

	public void recupererLePot(int pot) {

		this.setJetons(this.getJetons() + pot);

	}

}
