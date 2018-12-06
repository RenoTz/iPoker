package utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;

import model.Carte;

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

	private static boolean hasNombreCartesSouhaite(final List<Carte> cartes, final int nbCarteSouhaite) {

		final List<Integer> valeurs = cartes.stream().map(Carte::getValeur).collect(Collectors.toList());
		Collections.sort(valeurs);

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

}
