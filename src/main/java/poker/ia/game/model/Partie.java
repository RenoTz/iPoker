package poker.ia.game.model;

public class Partie {

	private int relanceRestante;
	private Joueur dealer;
	private int smallBlind;
	private int bigBlind;
	private int pot;

	public Partie() {

		this.relanceRestante = 3;
	}

	public int getRelanceRestante() {

		return this.relanceRestante;
	}

	public void setRelanceRestante(final int relanceRestante) {

		this.relanceRestante = relanceRestante;
	}

	public Joueur getDealer() {

		return this.dealer;
	}

	public void setDealer(final Joueur dealer) {

		this.dealer = dealer;
	}

	public int getPot() {

		return this.pot;
	}

	public void setPot(final int pot) {

		this.pot = pot;
	}

	public int getSmallBlind() {

		return this.smallBlind;
	}

	public void setSmallBlind(final int smallBlind) {

		this.smallBlind = smallBlind;
	}

	public int getBigBlind() {

		return this.bigBlind;
	}

	public void setBigBlind(final int bigBlind) {

		this.bigBlind = bigBlind;
	}


}
