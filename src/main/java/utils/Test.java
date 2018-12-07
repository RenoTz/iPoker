package utils;

import java.util.List;

import com.google.common.collect.Lists;

import model.Carte;
import model.CarteEnum;
import model.ColorEnum;

public class Test {

	public static void main(String[] args) {

		final Carte deuxCarreau = new Carte(CarteEnum.DEUX, ColorEnum.CARREAU);
		final Carte asCarreau = new Carte(CarteEnum.AS, ColorEnum.CARREAU);
		final Carte asCoeur = new Carte(CarteEnum.AS, ColorEnum.COEUR);
		final Carte deuxPique = new Carte(CarteEnum.AS, ColorEnum.PIQUE);
		final Carte asPique = new Carte(CarteEnum.AS, ColorEnum.PIQUE);
		final List<Carte> cartes = Lists.newArrayList(deuxCarreau, asCarreau, asCoeur, deuxPique, asPique);

		System.out.println("a une paire : " + CombinaisonUtil.hasPaire(cartes));
		System.out.println("a une double paire : " + CombinaisonUtil.hasDoublePaire(cartes));
		System.out.println("a un brelan : " + CombinaisonUtil.hasBrelan(cartes));
		System.out.println("a un carré : " + CombinaisonUtil.hasCarre(cartes));
		System.out.println("a un full : " + CombinaisonUtil.hasFullHouse(cartes));

		// test main : couleur
		final Carte dixCarreau = new Carte(CarteEnum.DIX, ColorEnum.CARREAU);
		final Carte troisCarreau = new Carte(CarteEnum.TROIS, ColorEnum.CARREAU);
		final Carte quatreCarreau = new Carte(CarteEnum.QUATRE, ColorEnum.CARREAU);
		final Carte valetPique = new Carte(CarteEnum.VALET, ColorEnum.PIQUE);
		final Carte cinqCarreau = new Carte(CarteEnum.CINQ, ColorEnum.CARREAU);
		final Carte roisCarreau = new Carte(CarteEnum.ROI, ColorEnum.CARREAU);
		final List<Carte> cartesCouleur = Lists.newArrayList(dixCarreau, troisCarreau, quatreCarreau, valetPique,
				cinqCarreau, roisCarreau);

		System.out.println("a une couleur : " + CombinaisonUtil.hasCouleur(cartesCouleur));

		// test : quinte flush
		final Carte septPique = new Carte(CarteEnum.SEPT, ColorEnum.PIQUE);
		final Carte huitPique = new Carte(CarteEnum.HUIT, ColorEnum.PIQUE);
		final Carte dixPique = new Carte(CarteEnum.DIX, ColorEnum.PIQUE);
		final Carte neufPique = new Carte(CarteEnum.NEUF, ColorEnum.PIQUE);
		final Carte damePique = new Carte(CarteEnum.DAME, ColorEnum.PIQUE);

		final List<Carte> cartesQuinteFlush = Lists.newArrayList(septPique, huitPique, dixPique, damePique, neufPique,
				valetPique);

		System.out.println("a une quinte flush : " + CombinaisonUtil.hasQuinteFlush(cartesQuinteFlush));

		// test : quinte flush royal
		final Carte asTrefle = new Carte(CarteEnum.AS, ColorEnum.TREFLE);
		final Carte dameTrefle = new Carte(CarteEnum.DAME, ColorEnum.TREFLE);
		final Carte valetTrefle = new Carte(CarteEnum.VALET, ColorEnum.TREFLE);
		final Carte huitCoeur = new Carte(CarteEnum.HUIT, ColorEnum.COEUR);
		final Carte roiTrefle = new Carte(CarteEnum.ROI, ColorEnum.TREFLE);
		final Carte dixTrefle = new Carte(CarteEnum.DIX, ColorEnum.TREFLE);

		final List<Carte> cartesQuinteFlushRoyale = Lists.newArrayList(asTrefle, dameTrefle, valetTrefle, huitCoeur,
				roiTrefle, dixTrefle);

		System.out.println(
				"a une quinte flush royale : " + CombinaisonUtil.hasQuinteFlushRoyale(cartesQuinteFlushRoyale));
	}

}
