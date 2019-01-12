package poker.ia.game.model;

public class Carte {

	private final CarteEnum carteEnum;
	private ColorEnum couleur;

	public Carte(final CarteEnum carteEnum, final ColorEnum couleur) {

		this.carteEnum = carteEnum;
		this.couleur = couleur;
	}

	public ColorEnum getCouleur() {

		return this.couleur;
	}

	public void setCouleur(ColorEnum couleur) {

		this.couleur = couleur;
	}

	public CarteEnum getCarteEnum() {

		return this.carteEnum;
	}

	public Integer getValeur() {

		return this.carteEnum.getValeur();
	}

}
