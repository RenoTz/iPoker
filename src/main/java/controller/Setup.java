package controller;

import java.util.List;

import com.google.common.collect.Lists;

import model.Carte;
import model.ColorEnum;
import utils.CarteUtils;

public class Setup {

	public List<Carte> creerJeuDeCartes() {

		final List<Carte> jeuDeCartes = Lists.newArrayList();
		// 4 cartes de "UN"
		final Carte asCarreau = new Carte(1, "As", ColorEnum.CARREAU);
		final Carte asCoeur = new Carte(1, "As", ColorEnum.COEUR);
		final Carte asPique = new Carte(1, "As", ColorEnum.PIQUE);
		final Carte asTrefle = new Carte(1, "As", ColorEnum.TREFLE);
		jeuDeCartes.add(asCarreau);
		jeuDeCartes.add(asCoeur);
		jeuDeCartes.add(asPique);
		jeuDeCartes.add(asTrefle);
		// 4 cartes de "DEUX"
		final Carte deuxCarreau = new Carte(2, "Deux", ColorEnum.CARREAU);
		final Carte deuxCoeur = new Carte(2, "Deux", ColorEnum.COEUR);
		final Carte deuxPique = new Carte(2, "Deux", ColorEnum.PIQUE);
		final Carte deuxTrefle = new Carte(2, "Deux", ColorEnum.TREFLE);
		jeuDeCartes.add(deuxCarreau);
		jeuDeCartes.add(deuxCoeur);
		jeuDeCartes.add(deuxPique);
		jeuDeCartes.add(deuxTrefle);
		// 4 cartes de "TROIS"
		final Carte troisCarreau = new Carte(3, "Trois", ColorEnum.CARREAU);
		final Carte troisCoeur = new Carte(3, "Trois", ColorEnum.COEUR);
		final Carte troisPique = new Carte(3, "Trois", ColorEnum.PIQUE);
		final Carte troisTrefle = new Carte(3, "Trois", ColorEnum.TREFLE);
		jeuDeCartes.add(troisCarreau);
		jeuDeCartes.add(troisCoeur);
		jeuDeCartes.add(troisPique);
		jeuDeCartes.add(troisTrefle);
		// 4 cartes de "QUATRE"
		final Carte quatreCarreau = new Carte(4, "Quatre", ColorEnum.CARREAU);
		final Carte quatreCoeur = new Carte(4, "Quatre", ColorEnum.COEUR);
		final Carte quatrePique = new Carte(4, "Quatre", ColorEnum.PIQUE);
		final Carte quatreTrefle = new Carte(4, "Quatre", ColorEnum.TREFLE);
		jeuDeCartes.add(quatreCarreau);
		jeuDeCartes.add(quatreCoeur);
		jeuDeCartes.add(quatrePique);
		jeuDeCartes.add(quatreTrefle);
		// 4 cartes de "CINQ"
		final Carte cinqCarreau = new Carte(5, "Cinq", ColorEnum.CARREAU);
		final Carte cinqCoeur = new Carte(5, "Cinq", ColorEnum.COEUR);
		final Carte cinqPique = new Carte(5, "Cinq", ColorEnum.PIQUE);
		final Carte cinqTrefle = new Carte(5, "Cinq", ColorEnum.TREFLE);
		jeuDeCartes.add(cinqCarreau);
		jeuDeCartes.add(cinqCoeur);
		jeuDeCartes.add(cinqPique);
		jeuDeCartes.add(cinqTrefle);
		// 4 cartes de "SIX"
		final Carte sixCarreau = new Carte(6, "Six", ColorEnum.CARREAU);
		final Carte sixCoeur = new Carte(6, "Six", ColorEnum.COEUR);
		final Carte sixPique = new Carte(6, "Six", ColorEnum.PIQUE);
		final Carte sixTrefle = new Carte(6, "Six", ColorEnum.TREFLE);
		jeuDeCartes.add(sixCarreau);
		jeuDeCartes.add(sixCoeur);
		jeuDeCartes.add(sixPique);
		jeuDeCartes.add(sixTrefle);
		// 4 cartes de "SEPT"
		final Carte septCarreau = new Carte(7, "Sept", ColorEnum.CARREAU);
		final Carte septCoeur = new Carte(7, "Sept", ColorEnum.COEUR);
		final Carte septPique = new Carte(7, "Sept", ColorEnum.PIQUE);
		final Carte septTrefle = new Carte(7, "Sept", ColorEnum.TREFLE);
		jeuDeCartes.add(septCarreau);
		jeuDeCartes.add(septCoeur);
		jeuDeCartes.add(septPique);
		jeuDeCartes.add(septTrefle);
		// 4 cartes de "HUIT"
		final Carte huitCarreau = new Carte(8, "Huit", ColorEnum.CARREAU);
		final Carte huitCoeur = new Carte(8, "Huit", ColorEnum.COEUR);
		final Carte huitPique = new Carte(8, "Huit", ColorEnum.PIQUE);
		final Carte huitTrefle = new Carte(8, "Huit", ColorEnum.TREFLE);
		jeuDeCartes.add(huitCarreau);
		jeuDeCartes.add(huitCoeur);
		jeuDeCartes.add(huitPique);
		jeuDeCartes.add(huitTrefle);
		// 4 cartes de "NEUF"
		final Carte neufCarreau = new Carte(9, "Neuf", ColorEnum.CARREAU);
		final Carte neufCoeur = new Carte(9, "Neuf", ColorEnum.COEUR);
		final Carte neufPique = new Carte(9, "Neuf", ColorEnum.PIQUE);
		final Carte neufTrefle = new Carte(9, "Neuf", ColorEnum.TREFLE);
		jeuDeCartes.add(neufCarreau);
		jeuDeCartes.add(neufCoeur);
		jeuDeCartes.add(neufPique);
		jeuDeCartes.add(neufTrefle);
		// 4 cartes de "DIX"
		final Carte dixCarreau = new Carte(10, "Dix", ColorEnum.CARREAU);
		final Carte dixCoeur = new Carte(10, "Dix", ColorEnum.COEUR);
		final Carte dixPique = new Carte(10, "Dix", ColorEnum.PIQUE);
		final Carte dixTrefle = new Carte(10, "Dix", ColorEnum.TREFLE);
		jeuDeCartes.add(dixCarreau);
		jeuDeCartes.add(dixCoeur);
		jeuDeCartes.add(dixPique);
		jeuDeCartes.add(dixTrefle);
		// 4 cartes de "VALET"
		final Carte valetCarreau = new Carte(11, "Valet", ColorEnum.CARREAU);
		final Carte valetCoeur = new Carte(11, "Valet", ColorEnum.COEUR);
		final Carte valetPique = new Carte(11, "Valet", ColorEnum.PIQUE);
		final Carte valetTrefle = new Carte(11, "Valet", ColorEnum.TREFLE);
		jeuDeCartes.add(valetCarreau);
		jeuDeCartes.add(valetCoeur);
		jeuDeCartes.add(valetPique);
		jeuDeCartes.add(valetTrefle);
		// 4 cartes de "DAME"
		final Carte dameCarreau = new Carte(12, "Dame", ColorEnum.CARREAU);
		final Carte dameCoeur = new Carte(12, "Dame", ColorEnum.COEUR);
		final Carte damePique = new Carte(12, "Dame", ColorEnum.PIQUE);
		final Carte dameTrefle = new Carte(12, "Dame", ColorEnum.TREFLE);
		jeuDeCartes.add(dameCarreau);
		jeuDeCartes.add(dameCoeur);
		jeuDeCartes.add(damePique);
		jeuDeCartes.add(dameTrefle);
		// 4 cartes de "ROI"
		final Carte roiCarreau = new Carte(13, "Roi", ColorEnum.CARREAU);
		final Carte roiCoeur = new Carte(13, "Roi", ColorEnum.COEUR);
		final Carte roiPique = new Carte(13, "Roi", ColorEnum.PIQUE);
		final Carte roiTrefle = new Carte(13, "Roi", ColorEnum.TREFLE);
		jeuDeCartes.add(roiCarreau);
		jeuDeCartes.add(roiCoeur);
		jeuDeCartes.add(roiPique);
		jeuDeCartes.add(roiTrefle);

		// on mélange les cartes
		CarteUtils.melangerJeuDeCartes(jeuDeCartes);
		return jeuDeCartes;
	}

}
