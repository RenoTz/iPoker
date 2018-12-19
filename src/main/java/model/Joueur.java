package model;

import java.util.List;

import com.google.common.collect.Lists;

public class Joueur {

	private String nom;
	private List<Carte> cartes;
	private CarteCombinaison carteCombinaison;
	private boolean won;

	public Joueur(final String nom) {

		this.nom = nom;
		this.cartes = Lists.newArrayList();
	}

	public String getNom() {

		return this.nom;
	}

	public void setNom(String nom) {

		this.nom = nom;
	}

	public List<Carte> getCartes() {

		return this.cartes;
	}

	public void setCartes(List<Carte> cartes) {

		this.cartes = cartes;
	}

	public boolean isWon() {

		return this.won;
	}

	public void setWon(boolean won) {

		this.won = won;
	}

	public CarteCombinaison getCarteCombinaison() {

		return this.carteCombinaison;
	}

	public void setCarteCombinaison(CarteCombinaison combinaison) {

		this.carteCombinaison = combinaison;
	}

}
