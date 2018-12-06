package utils;

import java.util.List;

import com.google.common.collect.Lists;

import model.Carte;
import model.ColorEnum;

public class Test {

	public static void main(String[] args) {

		final Carte deuxCarreau = new Carte(2, "Deux", ColorEnum.CARREAU);
		final Carte asCarreau = new Carte(1, "As", ColorEnum.CARREAU);
		final Carte asCoeur = new Carte(1, "As", ColorEnum.COEUR);
		final Carte deuxPique = new Carte(2, "Deux", ColorEnum.PIQUE);
		final Carte asPique = new Carte(1, "As", ColorEnum.PIQUE);
		final List<Carte> cartes = Lists.newArrayList(deuxCarreau, asCarreau, asCoeur, deuxPique, asPique);

		System.out.println("a une paire : " + CombinaisonUtil.hasPaire(cartes));
		System.out.println("a un brelan : " + CombinaisonUtil.hasBrelan(cartes));
		System.out.println("a un carré : " + CombinaisonUtil.hasCarre(cartes));
		System.out.println("a un full : " + CombinaisonUtil.hasFull(cartes));

	}

}
