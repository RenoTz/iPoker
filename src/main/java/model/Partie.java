package model;

public class Partie {

	private int relanceRestante;
	private Joueur dealer;
	private int smallBlind;
	private int bigBlind;
	private int pot;
	private int misesDesJoueurs;

	public Partie() {

		this.relanceRestante = 3;
	}

	public int getRelanceRestante() {

		return this.relanceRestante;
	}

	public void setRelanceRestante(int relanceRestante) {

		this.relanceRestante = relanceRestante;
	}

	public Joueur getDealer() {

		return this.dealer;
	}

	public void setDealer(Joueur dealer) {

		this.dealer = dealer;
	}

	public int getPot() {

		return this.pot;
	}

	public void setPot(int pot) {

		this.pot = pot;
	}

	public int getSmallBlind() {

		return this.smallBlind;
	}

	public void setSmallBlind(int smallBlind) {

		this.smallBlind = smallBlind;
	}

	public int getBigBlind() {

		return this.bigBlind;
	}

	public void setBigBlind(int bigBlind) {

		this.bigBlind = bigBlind;
	}

	public int getMisesDesJoueurs() {

		return misesDesJoueurs;
	}

	public void setMisesDesJoueurs(int misesDesJoueurs) {

		this.misesDesJoueurs = misesDesJoueurs;
	}

}
