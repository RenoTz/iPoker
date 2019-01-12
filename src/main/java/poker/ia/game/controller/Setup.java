package poker.ia.game.controller;

import java.util.List;

import com.google.common.collect.Lists;

import poker.ia.game.model.Carte;
import poker.ia.game.model.CarteEnum;
import poker.ia.game.model.ColorEnum;
import poker.ia.game.utils.CarteUtils;

public class Setup {

	public List<Carte> creerJeuDeCartes() {

		final List<Carte> jeuDeCartes = Lists.newArrayList();
		// 4 cartes de "UN"
		final Carte asCarreau = new Carte(CarteEnum.AS, ColorEnum.CARREAU);
		final Carte asCoeur = new Carte(CarteEnum.AS, ColorEnum.COEUR);
		final Carte asPique = new Carte(CarteEnum.AS, ColorEnum.PIQUE);
		final Carte asTrefle = new Carte(CarteEnum.AS, ColorEnum.TREFLE);
		jeuDeCartes.add(asCarreau);
		jeuDeCartes.add(asCoeur);
		jeuDeCartes.add(asPique);
		jeuDeCartes.add(asTrefle);
		// 4 cartes de "DEUX"
		final Carte deuxCarreau = new Carte(CarteEnum.DEUX, ColorEnum.CARREAU);
		final Carte deuxCoeur = new Carte(CarteEnum.DEUX, ColorEnum.COEUR);
		final Carte deuxPique = new Carte(CarteEnum.DEUX, ColorEnum.PIQUE);
		final Carte deuxTrefle = new Carte(CarteEnum.DEUX, ColorEnum.TREFLE);
		jeuDeCartes.add(deuxCarreau);
		jeuDeCartes.add(deuxCoeur);
		jeuDeCartes.add(deuxPique);
		jeuDeCartes.add(deuxTrefle);
		// 4 cartes de "TROIS"
		final Carte troisCarreau = new Carte(CarteEnum.TROIS, ColorEnum.CARREAU);
		final Carte troisCoeur = new Carte(CarteEnum.TROIS, ColorEnum.COEUR);
		final Carte troisPique = new Carte(CarteEnum.TROIS, ColorEnum.PIQUE);
		final Carte troisTrefle = new Carte(CarteEnum.TROIS, ColorEnum.TREFLE);
		jeuDeCartes.add(troisCarreau);
		jeuDeCartes.add(troisCoeur);
		jeuDeCartes.add(troisPique);
		jeuDeCartes.add(troisTrefle);
		// 4 cartes de "QUATRE"
		final Carte quatreCarreau = new Carte(CarteEnum.QUATRE, ColorEnum.CARREAU);
		final Carte quatreCoeur = new Carte(CarteEnum.QUATRE, ColorEnum.COEUR);
		final Carte quatrePique = new Carte(CarteEnum.QUATRE, ColorEnum.PIQUE);
		final Carte quatreTrefle = new Carte(CarteEnum.QUATRE, ColorEnum.TREFLE);
		jeuDeCartes.add(quatreCarreau);
		jeuDeCartes.add(quatreCoeur);
		jeuDeCartes.add(quatrePique);
		jeuDeCartes.add(quatreTrefle);
		// 4 cartes de "CINQ"
		final Carte cinqCarreau = new Carte(CarteEnum.CINQ, ColorEnum.CARREAU);
		final Carte cinqCoeur = new Carte(CarteEnum.CINQ, ColorEnum.COEUR);
		final Carte cinqPique = new Carte(CarteEnum.CINQ, ColorEnum.PIQUE);
		final Carte cinqTrefle = new Carte(CarteEnum.CINQ, ColorEnum.TREFLE);
		jeuDeCartes.add(cinqCarreau);
		jeuDeCartes.add(cinqCoeur);
		jeuDeCartes.add(cinqPique);
		jeuDeCartes.add(cinqTrefle);
		// 4 cartes de "SIX"
		final Carte sixCarreau = new Carte(CarteEnum.SIX, ColorEnum.CARREAU);
		final Carte sixCoeur = new Carte(CarteEnum.SIX, ColorEnum.COEUR);
		final Carte sixPique = new Carte(CarteEnum.SIX, ColorEnum.PIQUE);
		final Carte sixTrefle = new Carte(CarteEnum.SIX, ColorEnum.TREFLE);
		jeuDeCartes.add(sixCarreau);
		jeuDeCartes.add(sixCoeur);
		jeuDeCartes.add(sixPique);
		jeuDeCartes.add(sixTrefle);
		// 4 cartes de "SEPT"
		final Carte septCarreau = new Carte(CarteEnum.SEPT, ColorEnum.CARREAU);
		final Carte septCoeur = new Carte(CarteEnum.SEPT, ColorEnum.COEUR);
		final Carte septPique = new Carte(CarteEnum.SEPT, ColorEnum.PIQUE);
		final Carte septTrefle = new Carte(CarteEnum.SEPT, ColorEnum.TREFLE);
		jeuDeCartes.add(septCarreau);
		jeuDeCartes.add(septCoeur);
		jeuDeCartes.add(septPique);
		jeuDeCartes.add(septTrefle);
		// 4 cartes de "HUIT"
		final Carte huitCarreau = new Carte(CarteEnum.HUIT, ColorEnum.CARREAU);
		final Carte huitCoeur = new Carte(CarteEnum.HUIT, ColorEnum.COEUR);
		final Carte huitPique = new Carte(CarteEnum.HUIT, ColorEnum.PIQUE);
		final Carte huitTrefle = new Carte(CarteEnum.HUIT, ColorEnum.TREFLE);
		jeuDeCartes.add(huitCarreau);
		jeuDeCartes.add(huitCoeur);
		jeuDeCartes.add(huitPique);
		jeuDeCartes.add(huitTrefle);
		// 4 cartes de "NEUF"
		final Carte neufCarreau = new Carte(CarteEnum.NEUF, ColorEnum.CARREAU);
		final Carte neufCoeur = new Carte(CarteEnum.NEUF, ColorEnum.COEUR);
		final Carte neufPique = new Carte(CarteEnum.NEUF, ColorEnum.PIQUE);
		final Carte neufTrefle = new Carte(CarteEnum.NEUF, ColorEnum.TREFLE);
		jeuDeCartes.add(neufCarreau);
		jeuDeCartes.add(neufCoeur);
		jeuDeCartes.add(neufPique);
		jeuDeCartes.add(neufTrefle);
		// 4 cartes de "DIX"
		final Carte dixCarreau = new Carte(CarteEnum.DIX, ColorEnum.CARREAU);
		final Carte dixCoeur = new Carte(CarteEnum.DIX, ColorEnum.COEUR);
		final Carte dixPique = new Carte(CarteEnum.DIX, ColorEnum.PIQUE);
		final Carte dixTrefle = new Carte(CarteEnum.DIX, ColorEnum.TREFLE);
		jeuDeCartes.add(dixCarreau);
		jeuDeCartes.add(dixCoeur);
		jeuDeCartes.add(dixPique);
		jeuDeCartes.add(dixTrefle);
		// 4 cartes de "VALET"
		final Carte valetCarreau = new Carte(CarteEnum.VALET, ColorEnum.CARREAU);
		final Carte valetCoeur = new Carte(CarteEnum.VALET, ColorEnum.COEUR);
		final Carte valetPique = new Carte(CarteEnum.VALET, ColorEnum.PIQUE);
		final Carte valetTrefle = new Carte(CarteEnum.VALET, ColorEnum.TREFLE);
		jeuDeCartes.add(valetCarreau);
		jeuDeCartes.add(valetCoeur);
		jeuDeCartes.add(valetPique);
		jeuDeCartes.add(valetTrefle);
		// 4 cartes de "DAME"
		final Carte dameCarreau = new Carte(CarteEnum.DAME, ColorEnum.CARREAU);
		final Carte dameCoeur = new Carte(CarteEnum.DAME, ColorEnum.COEUR);
		final Carte damePique = new Carte(CarteEnum.DAME, ColorEnum.PIQUE);
		final Carte dameTrefle = new Carte(CarteEnum.DAME, ColorEnum.TREFLE);
		jeuDeCartes.add(dameCarreau);
		jeuDeCartes.add(dameCoeur);
		jeuDeCartes.add(damePique);
		jeuDeCartes.add(dameTrefle);
		// 4 cartes de "ROI"
		final Carte roiCarreau = new Carte(CarteEnum.ROI, ColorEnum.CARREAU);
		final Carte roiCoeur = new Carte(CarteEnum.ROI, ColorEnum.COEUR);
		final Carte roiPique = new Carte(CarteEnum.ROI, ColorEnum.PIQUE);
		final Carte roiTrefle = new Carte(CarteEnum.ROI, ColorEnum.TREFLE);
		jeuDeCartes.add(roiCarreau);
		jeuDeCartes.add(roiCoeur);
		jeuDeCartes.add(roiPique);
		jeuDeCartes.add(roiTrefle);

		// on mélange les cartes
		CarteUtils.melangerJeuDeCartes(jeuDeCartes);
		return jeuDeCartes;
	}

}
