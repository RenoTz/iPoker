package utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;

import model.Carte;
import model.CarteEnum;
import model.ColorEnum;
import model.CombinaisonEnum;

public class CombinaisonUtil {

	public static CombinaisonEnum getMeilleureCombinaison(final List<Carte> cartes) {

		CombinaisonEnum meilleureCombinaison = CombinaisonEnum.NEANT;

		if (hasQuinteFlush(cartes)) {
			meilleureCombinaison = CombinaisonEnum.QUINTE_FLUSH;
		} else if (hasCarre(cartes)) {
			meilleureCombinaison = CombinaisonEnum.CARRE;
		} else if (hasFullHouse(cartes)) {
			meilleureCombinaison = CombinaisonEnum.FULL_HOUSE;
		} else if (hasCouleur(cartes)) {
			meilleureCombinaison = CombinaisonEnum.COULEUR;
		} else if (hasQuinte(cartes)) {
			meilleureCombinaison = CombinaisonEnum.QUINTE;
		} else if (hasBrelan(cartes)) {
			meilleureCombinaison = CombinaisonEnum.BRELAN;
		} else if (hasDoublePaire(cartes)) {
			meilleureCombinaison = CombinaisonEnum.DOUBLE_PAIRE;
		} else if (hasPaire(cartes)) {
			meilleureCombinaison = CombinaisonEnum.PAIRE;
		}

		return meilleureCombinaison;
	}

	public static boolean hasPaire(final List<Carte> cartes) {

		return hasNombreCartesSouhaite(cartes, 2);
	}

	public static boolean hasDoublePaire(final List<Carte> cartes) {

		final List<Integer> valeurs = getValeursCartesTriees(cartes);
		final Map<Integer, Integer> cartesIdentiques = getCartesIdentiques(valeurs);

		final List<Integer> paires = cartesIdentiques.entrySet().stream().filter(c -> c.getValue() == 2)
				.map(c -> c.getKey()).collect(Collectors.toList());

		return paires.size() == 2;
	}

	public static boolean hasBrelan(final List<Carte> cartes) {

		return hasNombreCartesSouhaite(cartes, 3);
	}

	public static boolean hasCarre(final List<Carte> cartes) {

		return hasNombreCartesSouhaite(cartes, 4);
	}

	public static boolean hasCouleur(final List<Carte> cartes) {

		final List<ColorEnum> couleurs = getCouleursCartesTriees(cartes);

		final Map<ColorEnum, Integer> cartesDeMemeCouleur = getCartesMemeCouleur(couleurs);

		return couleurs.stream().anyMatch(c -> cartesDeMemeCouleur.get(c) >= 5);
	}

	public static boolean hasQuinte(final List<Carte> cartes) {

		final List<Integer> valeurs = getValeursCartesTriees(cartes);

		int i = 1;
		int valeurPrecedente = -1;
		for (final Integer v : valeurs) {
			if (valeurPrecedente + 1 == v) {
				i++;
			}
			valeurPrecedente = v;
		}
		return i >= 5;
	}

	public static boolean hasQuinteFlush(final List<Carte> cartes) {

		final Map<Integer, ColorEnum> valeursParCouleur = getValeursCartesTrieesParCouleur(cartes);

		int i = 1;
		Entry<Integer, ColorEnum> valeurPrecedente = null;
		for (final Entry<Integer, ColorEnum> v : valeursParCouleur.entrySet()) {
			if ((valeurPrecedente != null)
					&& (valeurPrecedente.getKey() + 1 == v.getKey() && valeurPrecedente.getValue() == v.getValue())) {
				i++;
			}
			valeurPrecedente = v;
		}
		return i >= 5;
	}

	public static boolean hasQuinteFlushRoyale(final List<Carte> cartes) {

		final Map<Integer, ColorEnum> valeursParCouleur = getValeursCartesTrieesParCouleur(cartes);

		int i = 1;
		Entry<Integer, ColorEnum> valeurPrecedente = null;
		boolean asCarteHaute = false;
		for (final Entry<Integer, ColorEnum> v : valeursParCouleur.entrySet()) {
			if ((valeurPrecedente != null) && ((valeurPrecedente.getKey() + 1 == v.getKey()
					|| (valeurPrecedente.getKey() == CarteEnum.ROI.getValeur()
							&& v.getKey() == CarteEnum.AS.getValeur()))
					&& valeurPrecedente.getValue() == v.getValue())) {
				i++;
				asCarteHaute = v.getKey() == CarteEnum.AS.getValeur();
			}
			valeurPrecedente = v;
		}
		return i >= 5 && asCarteHaute;
	}

	public static boolean hasFullHouse(final List<Carte> cartes) {

		final List<Integer> valeurs = getValeursCartesTriees(cartes);
		final Map<Integer, Integer> cartesIdentiques = getCartesIdentiques(valeurs);

		final boolean hasPaire = cartesIdentiques.entrySet().stream().anyMatch(c -> c.getValue() == 2);
		final boolean hasBrelan = cartesIdentiques.entrySet().stream().anyMatch(c -> c.getValue() == 3);

		return hasPaire && hasBrelan;
	}

	private static boolean hasNombreCartesSouhaite(final List<Carte> cartes, final int nbCarteSouhaite) {

		final List<Integer> valeurs = getValeursCartesTriees(cartes);

		final Map<Integer, Integer> cartesIdentiques = getCartesIdentiques(valeurs);

		return valeurs.stream().anyMatch(v -> cartesIdentiques.get(v) == nbCarteSouhaite);
	}

	private static List<Integer> getValeursCartesTriees(final List<Carte> cartes) {

		final List<Integer> valeurs = cartes.stream().map(Carte::getValeur).collect(Collectors.toList());
		Collections.sort(valeurs);
		return valeurs;
	}

	private static List<ColorEnum> getCouleursCartesTriees(final List<Carte> cartes) {

		final List<ColorEnum> couleurs = cartes.stream().map(Carte::getCouleur).collect(Collectors.toList());
		Collections.sort(couleurs);
		return couleurs;
	}

	private static Map<Integer, ColorEnum> getValeursCartesTrieesParCouleur(List<Carte> cartes) {

		final SortedMap<Integer, ColorEnum> valeursCouleur = Maps.newTreeMap();

		for (final Carte c : cartes) {
			valeursCouleur.put(c.getValeur(), c.getCouleur());
		}
		return valeursCouleur;
	}

	private static Map<Integer, Integer> getCartesIdentiques(final List<Integer> valeurs) {

		final Map<Integer, Integer> cartesIdentiques = Maps.newHashMap();
		for (final Integer v : valeurs) {
			if (cartesIdentiques.containsKey(v)) {
				cartesIdentiques.put(v, cartesIdentiques.get(v) + 1);
			} else {
				cartesIdentiques.put(v, 1);
			}
		}
		return cartesIdentiques;
	}

	private static Map<ColorEnum, Integer> getCartesMemeCouleur(final List<ColorEnum> couleurs) {

		final Map<ColorEnum, Integer> cartesDeMemeCouleur = Maps.newHashMap();
		for (final ColorEnum c : couleurs) {
			if (cartesDeMemeCouleur.containsKey(c)) {
				cartesDeMemeCouleur.put(c, cartesDeMemeCouleur.get(c) + 1);
			} else {
				cartesDeMemeCouleur.put(c, 1);
			}
		}
		return cartesDeMemeCouleur;
	}

}
