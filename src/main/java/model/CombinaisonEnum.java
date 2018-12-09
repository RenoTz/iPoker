package model;

public enum CombinaisonEnum {
	HAUTEUR(1), PAIRE(2), DOUBLE_PAIRE(3), BRELAN(4), QUINTE(5), COULEUR(6), FULL_HOUSE(7), CARRE(8), QUINTE_FLUSH(9), QUINTE_FLUSH_ROYAL(
			10);

	private final Integer valeur;

	private CombinaisonEnum(final Integer valeur) {

		this.valeur = valeur;

	}

	public Integer getValeur() {

		return this.valeur;
	}
}
