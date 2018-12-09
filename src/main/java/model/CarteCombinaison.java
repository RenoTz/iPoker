package model;

public class CarteCombinaison {

	private CarteEnum carte;
	private CarteEnum hauteur;
	private CombinaisonEnum combinaison;

	public CarteEnum getCarte() {
		return this.carte;
	}
	public void setCarte(final CarteEnum carte) {
		this.carte = carte;
	}
	public CombinaisonEnum getCombinaison() {
		return this.combinaison;
	}
	public void setCombinaison(final CombinaisonEnum combinaison) {
		this.combinaison = combinaison;
	}
	public CarteEnum getHauteur() {
		return hauteur;
	}
	public void setHauteur(CarteEnum hauteur) {
		this.hauteur = hauteur;
	}

}
