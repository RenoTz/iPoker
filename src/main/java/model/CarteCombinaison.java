package model;

public class CarteCombinaison {

	private CarteEnum[] cartes;
	private CarteEnum hauteur;
	private ColorEnum couleur;
	private CombinaisonEnum combinaison;

	public CarteCombinaison() {

		this.cartes = new CarteEnum[2];
	}

	public CarteEnum[] getCartes() {

		return this.cartes;
	}

	public void setCartes(final CarteEnum[] cartes) {

		this.cartes = cartes;
	}

	public CombinaisonEnum getCombinaison() {

		return this.combinaison;
	}

	public void setCombinaison(final CombinaisonEnum combinaison) {

		this.combinaison = combinaison;
	}

	public CarteEnum getHauteur() {

		return this.hauteur;
	}

	public void setHauteur(CarteEnum hauteur) {

		this.hauteur = hauteur;
	}

	public ColorEnum getCouleur() {

		return this.couleur;
	}

	public void setCouleur(ColorEnum couleur) {

		this.couleur = couleur;
	}

}
