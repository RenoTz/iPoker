package model;

public class Carte {

	private int valeur;
	private String code;
	private ColorEnum couleur;

	public Carte(final int valeur, final String code, final ColorEnum couleur) {

		this.valeur = valeur;
		this.code = code;
		this.couleur = couleur;
	}

	public ColorEnum getCouleur() {

		return this.couleur;
	}

	public void setCouleur(ColorEnum couleur) {

		this.couleur = couleur;
	}

	public int getValeur() {

		return this.valeur;
	}

	public void setValeur(int valeur) {

		this.valeur = valeur;
	}

	public String getCode() {

		return this.code;
	}

	public void setCode(String code) {

		this.code = code;
	}

}
