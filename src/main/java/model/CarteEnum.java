package model;

public enum CarteEnum {
	AS(1), DEUX(2), TROIS(3), QUATRE(4), CINQ(5), SIX(6), SEPT(7), HUIT(8), NEUF(9), DIX(10), VALET(11), DAME(12), ROI(
			13);

	private final Integer valeur;

	private CarteEnum(final Integer valeur) {

		this.valeur = valeur;
	}

	public static CarteEnum getCarteEnumByValeur(final Integer valeur) {

		CarteEnum retour = null;
		for (final CarteEnum e : values()) {
			if (e.getValeur().intValue() == valeur.intValue()) {
				retour = e;
				break;
			}
		}
		return retour;
	}

	public Integer getValeur() {

		return this.valeur;
	}
}
