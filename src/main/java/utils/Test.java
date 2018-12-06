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

		// test main : couleur
		final Carte dixCarreau = new Carte(10, "Dix", ColorEnum.CARREAU);
		final Carte troisCarreau = new Carte(3, "Trois", ColorEnum.CARREAU);
		final Carte quatreCarreau = new Carte(4, "Quatre", ColorEnum.CARREAU);
		final Carte valetPique = new Carte(11, "Valet", ColorEnum.PIQUE);
		final Carte cinqCarreau = new Carte(1, "Cinq", ColorEnum.CARREAU);
		final Carte roisCarreau = new Carte(12, "Roi", ColorEnum.CARREAU);
		final List<Carte> cartesCouleur = Lists.newArrayList(dixCarreau, troisCarreau, quatreCarreau, valetPique,
				cinqCarreau, roisCarreau);

		System.out.println("a une couleur : " + CombinaisonUtil.hasCouleur(cartesCouleur));

		// test : quinte
		final Carte septPique = new Carte(7, "Sept", ColorEnum.PIQUE);
		final Carte huitCarreau = new Carte(8, "Huit", ColorEnum.CARREAU);
		final Carte troisTrefle = new Carte(3, "Trois", ColorEnum.TREFLE);
		final Carte neufCarreau = new Carte(9, "Neuf", ColorEnum.CARREAU);
		final Carte damePique = new Carte(10, "Dame", ColorEnum.PIQUE);
		final Carte valetCarreau = new Carte(11, "Valet", ColorEnum.CARREAU);

		final List<Carte> cartesQuinte = Lists.newArrayList(septPique, huitCarreau, troisTrefle, neufCarreau, damePique,
				valetCarreau);

		System.out.println("a une quinte : " + CombinaisonUtil.hasQuinte(cartesQuinte));
	}

}
