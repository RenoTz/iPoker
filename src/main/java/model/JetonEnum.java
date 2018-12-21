package model;

public enum JetonEnum {

	DIX(10), VINGT(20), CINQUANTE(50), CENT(100), DEUX_CENTS(200), CINQ_CENTS(500);

	private final int valeur;

	private JetonEnum(final int valeur) {

		this.valeur = valeur;

	}

	public int getValeur() {

		return this.valeur;
	}
}
