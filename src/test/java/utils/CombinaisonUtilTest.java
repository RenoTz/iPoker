package utils;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

import poker.ia.game.model.Carte;
import poker.ia.game.model.CarteEnum;
import poker.ia.game.model.ColorEnum;
import poker.ia.game.services.CombinaisonService;

@RunWith(MockitoJUnitRunner.class)
public class CombinaisonUtilTest {

	@InjectMocks
	private CombinaisonService combinaisonUtil;

	@Test
	public void test_aUnePaire() throws Exception {

		final Carte deuxCarreau = new Carte(CarteEnum.DEUX, ColorEnum.CARREAU);
		final Carte asCarreau = new Carte(CarteEnum.AS, ColorEnum.CARREAU);
		final Carte asCoeur = new Carte(CarteEnum.AS, ColorEnum.COEUR);
		final Carte troisPique = new Carte(CarteEnum.TROIS, ColorEnum.PIQUE);
		final Carte quatrePique = new Carte(CarteEnum.QUATRE, ColorEnum.PIQUE);
		final List<Carte> cartes = Lists.newArrayList(deuxCarreau, asCarreau, asCoeur, troisPique, quatrePique);

		final boolean paire = this.combinaisonUtil.hasPaire(cartes, Lists.newArrayList());

		assertTrue(paire);

	}

	@Test
	public void test_aUneDoublePaire() throws Exception {

		final Carte deuxCarreau = new Carte(CarteEnum.DEUX, ColorEnum.CARREAU);
		final Carte asCarreau = new Carte(CarteEnum.AS, ColorEnum.CARREAU);
		final Carte asCoeur = new Carte(CarteEnum.AS, ColorEnum.COEUR);
		final Carte deuxPique = new Carte(CarteEnum.DEUX, ColorEnum.PIQUE);
		final Carte troisPique = new Carte(CarteEnum.TROIS, ColorEnum.PIQUE);
		final List<Carte> cartes = Lists.newArrayList(deuxCarreau, asCarreau, asCoeur, deuxPique, troisPique);

		final boolean doublePaire = this.combinaisonUtil.hasDoublePaire(cartes, Lists.newArrayList());

		assertTrue(doublePaire);

	}

	@Test
	public void test_aUnBrelan() throws Exception {

		final Carte deuxCarreau = new Carte(CarteEnum.DEUX, ColorEnum.CARREAU);
		final Carte asCarreau = new Carte(CarteEnum.AS, ColorEnum.CARREAU);
		final Carte asCoeur = new Carte(CarteEnum.AS, ColorEnum.COEUR);
		final Carte deuxPique = new Carte(CarteEnum.DEUX, ColorEnum.PIQUE);
		final Carte asPique = new Carte(CarteEnum.AS, ColorEnum.PIQUE);
		final List<Carte> cartes = Lists.newArrayList(deuxCarreau, asCarreau, asCoeur, deuxPique, asPique);

		final boolean brelan = this.combinaisonUtil.hasBrelan(cartes, Lists.newArrayList());

		assertTrue(brelan);

	}

	@Test
	public void test_aUnCarre() throws Exception {

		// Arrange
		final Carte asTrefle = new Carte(CarteEnum.AS, ColorEnum.TREFLE);
		final Carte asCarreau = new Carte(CarteEnum.AS, ColorEnum.CARREAU);
		final Carte asCoeur = new Carte(CarteEnum.AS, ColorEnum.COEUR);
		final Carte deuxPique = new Carte(CarteEnum.DEUX, ColorEnum.PIQUE);
		final Carte asPique = new Carte(CarteEnum.AS, ColorEnum.PIQUE);
		final List<Carte> cartes = Lists.newArrayList(asTrefle, asCarreau, asCoeur, deuxPique, asPique);

		// Act
		final boolean carre = this.combinaisonUtil.hasCarre(cartes, Lists.newArrayList());

		// Assert
		assertTrue(carre);
	}

	@Test
	public void test_aUnFull() throws Exception {

		// Arrange
		final Carte asTrefle = new Carte(CarteEnum.AS, ColorEnum.TREFLE);
		final Carte asCarreau = new Carte(CarteEnum.AS, ColorEnum.CARREAU);
		final Carte asCoeur = new Carte(CarteEnum.AS, ColorEnum.COEUR);
		final Carte deuxPique = new Carte(CarteEnum.DEUX, ColorEnum.PIQUE);
		final Carte deuxCarreau = new Carte(CarteEnum.DEUX, ColorEnum.CARREAU);
		final List<Carte> cartes = Lists.newArrayList(asTrefle, asCarreau, asCoeur, deuxPique, deuxCarreau);

		// Act
		final boolean full = this.combinaisonUtil.hasFullHouse(cartes, Lists.newArrayList());

		// Assert
		assertTrue(full);
	}

	@Test
	public void test_aUneCouleur() throws Exception {

		// Arrange
		final Carte asTrefle = new Carte(CarteEnum.AS, ColorEnum.TREFLE);
		final Carte troisTrefle = new Carte(CarteEnum.TROIS, ColorEnum.TREFLE);
		final Carte valetTrefle = new Carte(CarteEnum.VALET, ColorEnum.TREFLE);
		final Carte roiTrefle = new Carte(CarteEnum.ROI, ColorEnum.TREFLE);
		final Carte septTrefle = new Carte(CarteEnum.SEPT, ColorEnum.TREFLE);
		final List<Carte> cartes = Lists.newArrayList(asTrefle, troisTrefle, valetTrefle, roiTrefle, septTrefle);

		// Act
		final boolean couleur = this.combinaisonUtil.hasCouleur(cartes, Lists.newArrayList());

		// Assert
		assertTrue(couleur);
	}

	@Test
	public void test_aUneQuinte() throws Exception {

		// Arrange
		final Carte septPique = new Carte(CarteEnum.SEPT, ColorEnum.PIQUE);
		final Carte huitTrefle = new Carte(CarteEnum.HUIT, ColorEnum.TREFLE);
		final Carte dixPique = new Carte(CarteEnum.DIX, ColorEnum.PIQUE);
		final Carte neufCoeur = new Carte(CarteEnum.NEUF, ColorEnum.COEUR);
		final Carte valetCarreau = new Carte(CarteEnum.VALET, ColorEnum.CARREAU);
		final List<Carte> cartes = Lists.newArrayList(septPique, huitTrefle, dixPique, neufCoeur, valetCarreau);

		// Act
		final boolean quinte = this.combinaisonUtil.hasQuinte(cartes, Lists.newArrayList());

		// Assert
		assertTrue(quinte);
	}

	@Test
	public void test_aUneQuinteFlush() throws Exception {

		// Arrange
		final Carte septPique = new Carte(CarteEnum.SEPT, ColorEnum.PIQUE);
		final Carte huitPique = new Carte(CarteEnum.HUIT, ColorEnum.PIQUE);
		final Carte dixPique = new Carte(CarteEnum.DIX, ColorEnum.PIQUE);
		final Carte neufPique = new Carte(CarteEnum.NEUF, ColorEnum.PIQUE);
		final Carte valetPique = new Carte(CarteEnum.VALET, ColorEnum.PIQUE);
		final List<Carte> cartes = Lists.newArrayList(septPique, huitPique, dixPique, neufPique, valetPique);

		// Act
		final boolean quinteFlush = this.combinaisonUtil.hasQuinteFlush(cartes, Lists.newArrayList());

		// Assert
		assertTrue(quinteFlush);
	}

	@Test
	public void test_aUneQuinteFlushRoyale() throws Exception {

		// Arrange
		final Carte asTrefle = new Carte(CarteEnum.AS, ColorEnum.TREFLE);
		final Carte dameTrefle = new Carte(CarteEnum.DAME, ColorEnum.TREFLE);
		final Carte valetTrefle = new Carte(CarteEnum.VALET, ColorEnum.TREFLE);
		final Carte huitCoeur = new Carte(CarteEnum.HUIT, ColorEnum.COEUR);
		final Carte roiTrefle = new Carte(CarteEnum.ROI, ColorEnum.TREFLE);
		final Carte dixTrefle = new Carte(CarteEnum.DIX, ColorEnum.TREFLE);
		final List<Carte> cartes = Lists.newArrayList(asTrefle, dameTrefle, valetTrefle, huitCoeur, roiTrefle, dixTrefle);

		// Act
		final boolean quinteFlushRoyale = this.combinaisonUtil.hasQuinteFlushRoyale(cartes, Lists.newArrayList());

		// Assert
		assertTrue(quinteFlushRoyale);
	}
}
