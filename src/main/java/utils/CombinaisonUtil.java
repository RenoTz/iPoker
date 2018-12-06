package utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;

import model.Carte;
import model.ColorEnum;

public class CombinaisonUtil {

	public static boolean hasPaire(final List<Carte> cartes) {

		return hasNombreCartesSouhaite(cartes, 2);
	}

	public static boolean hasBrelan(final List<Carte> cartes) {

		return hasNombreCartesSouhaite(cartes, 3);
	}

	public static boolean hasCarre(final List<Carte> cartes) {

		return hasNombreCartesSouhaite(cartes, 4);
	}

	public static boolean hasFull(final List<Carte> cartes) {

		return hasBrelan(cartes) && hasPaire(cartes);
	}

	public static boolean hasCouleur(final List<Carte> cartes) {

		final List<ColorEnum> couleurs = getCouleursCartesTriees(cartes);

		final Map<ColorEnum, Integer> cartesDeMemeCouleur = Maps.newHashMap();
		for (final ColorEnum c : couleurs) {
			if (cartesDeMemeCouleur.containsKey(c)) {
				cartesDeMemeCouleur.put(c, cartesDeMemeCouleur.get(c) + 1);
			} else {
				cartesDeMemeCouleur.put(c, 1);
			}
		}

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

	public static boolean hasFullHouse(final List<Carte> cartes) {

		return false;
	}

	private static boolean hasNombreCartesSouhaite(final List<Carte> cartes, final int nbCarteSouhaite) {

		final List<Integer> valeurs = getValeursCartesTriees(cartes);

		final Map<Integer, Integer> cartesIdentiques = Maps.newHashMap();
		for (final Integer v : valeurs) {
			if (cartesIdentiques.containsKey(v)) {
				cartesIdentiques.put(v, cartesIdentiques.get(v) + 1);
			} else {
				cartesIdentiques.put(v, 1);
			}
		}

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

}
