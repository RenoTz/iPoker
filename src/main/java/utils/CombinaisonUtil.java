package utils;

import static java.util.Objects.nonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.stream.Collectors;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import model.Carte;
import model.CarteCombinaison;
import model.CarteEnum;
import model.ColorEnum;
import model.CombinaisonEnum;
import model.Joueur;

public class CombinaisonUtil {

	public Joueur determinerJoueurAvecLaMeilleureMain(final List<Joueur> joueurs, final List<Carte> cartesVisibles) {

		Joueur joueurAvecMeilleureMain = null;
		CarteCombinaison meilleureCombinaison = null;

		for (final Joueur joueur : joueurs) {

			final CarteCombinaison mainDuJoueur = this.getMeilleureCombinaison(joueur.getCartes(), cartesVisibles);
			joueur.setCarteCombinaison(mainDuJoueur);

			if (meilleureCombinaison != null) {
				if (mainDuJoueur.getCombinaison().getValeur() > meilleureCombinaison.getCombinaison().getValeur()) {
					meilleureCombinaison = mainDuJoueur;
					joueurAvecMeilleureMain = joueur;
				} else if (mainDuJoueur.getCombinaison() == meilleureCombinaison.getCombinaison()) {
					final CarteEnum[] carteDuJoueurEnCours = new CarteEnum[2];
					final CarteEnum[] carteDuJoueurAvecMeilleureMain = new CarteEnum[2];
					switch (mainDuJoueur.getCombinaison()) {
					case PAIRE:
					case BRELAN:
					case CARRE:
						carteDuJoueurEnCours[0] = mainDuJoueur.getCartes()[0];
						carteDuJoueurAvecMeilleureMain[0] = meilleureCombinaison.getCartes()[0];
						joueurAvecMeilleureMain = this.comparerMainsAvecUneCarte(cartesVisibles,
								joueurAvecMeilleureMain, joueur, carteDuJoueurEnCours[0],
								carteDuJoueurAvecMeilleureMain[0]);
						break;
					case DOUBLE_PAIRE:
						carteDuJoueurEnCours[0] = mainDuJoueur.getCartes()[0];
						carteDuJoueurEnCours[1] = mainDuJoueur.getCartes()[1];
						carteDuJoueurAvecMeilleureMain[0] = meilleureCombinaison.getCartes()[0];
						carteDuJoueurAvecMeilleureMain[1] = meilleureCombinaison.getCartes()[1];

						joueurAvecMeilleureMain = this.comparerMainsAvecUneCarte(cartesVisibles,
								joueurAvecMeilleureMain, joueur, carteDuJoueurEnCours[0],
								carteDuJoueurAvecMeilleureMain[0]);
						joueurAvecMeilleureMain = this.comparerMainsAvecUneCarte(cartesVisibles,
								joueurAvecMeilleureMain, joueur, carteDuJoueurEnCours[1],
								carteDuJoueurAvecMeilleureMain[1]);
						break;
					default:
					}

				}
			} else {
				meilleureCombinaison = mainDuJoueur;
				joueurAvecMeilleureMain = joueur;
			}
		}

		if (nonNull(joueurAvecMeilleureMain)) {
			joueurAvecMeilleureMain.setWon(true);
		}
		return joueurAvecMeilleureMain;
	}

	private Joueur comparerMainsAvecUneCarte(final List<Carte> cartesVisibles, Joueur joueurAvecMeilleureMain,
			final Joueur joueur, final CarteEnum carteDuJoueurEnCours, final CarteEnum carteDuJoueurAvecMeilleureMain) {

		if ((carteDuJoueurEnCours.getValeur() > carteDuJoueurAvecMeilleureMain.getValeur())
				|| ((carteDuJoueurEnCours.getValeur() == carteDuJoueurAvecMeilleureMain.getValeur())
						&& (joueur.getCarteCombinaison().getHauteur().getValeur() > joueurAvecMeilleureMain
								.getCarteCombinaison().getHauteur().getValeur()))) {
			joueurAvecMeilleureMain = joueur;
		}
		return joueurAvecMeilleureMain;
	}

	public CarteCombinaison getMeilleureCombinaison(final List<Carte> cartesJoueur, final List<Carte> cartesVisibles) {

		final CarteCombinaison retour = new CarteCombinaison();
		retour.setHauteur(getCarteHaute(cartesJoueur, cartesVisibles));
		retour.setCombinaison(CombinaisonEnum.HAUTEUR);

		if (this.hasQuinteFlushRoyale(cartesJoueur, cartesVisibles)) {
			retour.setCombinaison(CombinaisonEnum.QUINTE_FLUSH_ROYAL);
		} else if (this.hasQuinteFlush(cartesJoueur, cartesVisibles)) {
			retour.setCombinaison(CombinaisonEnum.QUINTE_FLUSH);
		} else if (this.hasCarre(cartesJoueur, cartesVisibles)) {
			retour.getCartes()[0] = getCarteCarre(cartesJoueur, cartesVisibles);
			retour.setCombinaison(CombinaisonEnum.CARRE);
			retour.setHauteur(this.getHauteurHorsMain(retour.getCartes(), cartesJoueur, cartesVisibles));
		} else if (this.hasFullHouse(cartesJoueur, cartesVisibles)) {
			retour.getCartes()[0] = CarteEnum.AS;
			retour.setCombinaison(CombinaisonEnum.FULL_HOUSE);
			retour.setHauteur(this.getHauteurHorsMain(retour.getCartes(), cartesJoueur, cartesVisibles));
		} else if (this.hasCouleur(cartesJoueur, cartesVisibles)) {
			retour.setCouleur(getCouleurPourMainCouleur(cartesJoueur, cartesVisibles));
			retour.setCombinaison(CombinaisonEnum.COULEUR);
			retour.setHauteur(this.getHauteurHorsMain(retour.getCartes(), cartesJoueur, cartesVisibles));
		} else if (this.hasQuinte(cartesJoueur, cartesVisibles)) {
			retour.setHauteur(this.getCarteHauteQuinte(cartesJoueur, cartesVisibles));
			retour.setCombinaison(CombinaisonEnum.QUINTE);
		} else if (this.hasBrelan(cartesJoueur, cartesVisibles)) {
			retour.getCartes()[0] = getCarteBrelan(cartesJoueur, cartesVisibles);
			retour.setCombinaison(CombinaisonEnum.BRELAN);
			retour.setHauteur(this.getHauteurHorsMain(retour.getCartes(), cartesJoueur, cartesVisibles));
		} else if (this.hasDoublePaire(cartesJoueur, cartesVisibles)) {
			retour.getCartes()[0] = getFirstDoublePaire(cartesJoueur, cartesVisibles);
			retour.getCartes()[1] = getSecondDoublePaire(cartesJoueur, cartesVisibles);
			retour.setCombinaison(CombinaisonEnum.DOUBLE_PAIRE);
			retour.setHauteur(this.getHauteurHorsMain(retour.getCartes(), cartesJoueur, cartesVisibles));
		} else if (this.hasPaire(cartesJoueur, cartesVisibles)) {
			retour.getCartes()[0] = getCartePaire(cartesJoueur, cartesVisibles);
			retour.setCombinaison(CombinaisonEnum.PAIRE);
			retour.setHauteur(this.getHauteurHorsMain(retour.getCartes(), cartesJoueur, cartesVisibles));
		}

		return retour;
	}

	private CarteEnum getHauteurHorsMain(CarteEnum[] cartesCombinaison, List<Carte> cartesJoueur,
			List<Carte> cartesVisibles) {

		final List<Carte> cartes = getCartesJoueurPlusCartesVisibles(cartesJoueur, cartesVisibles);
		cartes.removeIf(c -> Arrays.asList(cartesCombinaison).contains(c.getCarteEnum()));
		final List<Integer> valeurs = cartes.stream().map(Carte::getValeur).collect(Collectors.toList());
		Collections.sort(valeurs);

		if (valeurs.contains(CarteEnum.AS.getValeur())) {
			return CarteEnum.AS;
		}
		return !valeurs.isEmpty() ? CarteEnum.getCarteEnumByValeur(Iterables.getLast(valeurs)) : null;
	}

	public boolean hasPaire(final List<Carte> cartesJoueur, final List<Carte> cartesVisibles) {

		return hasNombreCartesSouhaite(cartesJoueur, cartesVisibles, 2);
	}

	public static CarteEnum getCartePaire(final List<Carte> cartesJoueur, final List<Carte> cartesVisibles) {

		return getCartePourNombreCartesSouhaite(cartesJoueur, cartesVisibles, 2);
	}

	public boolean hasDoublePaire(final List<Carte> cartesJoueur, final List<Carte> cartesVisibles) {

		final List<Integer> valeurs = getValeursCartesTriees(cartesJoueur, cartesVisibles);
		final Map<Integer, Integer> cartesIdentiques = getCartesIdentiques(valeurs);

		final List<Integer> paires = cartesIdentiques.entrySet().stream().filter(c -> c.getValue() == 2)
				.map(c -> c.getKey()).collect(Collectors.toList());

		return paires.size() == 2;
	}

	public static CarteEnum getFirstDoublePaire(final List<Carte> cartesJoueur, final List<Carte> cartesVisibles) {

		final List<Integer> valeurs = getValeursCartesTriees(cartesJoueur, cartesVisibles);
		final Map<Integer, Integer> cartesIdentiques = getCartesIdentiques(valeurs);

		final List<Integer> paires = cartesIdentiques.entrySet().stream().filter(c -> c.getValue() == 2)
				.map(c -> c.getKey()).collect(Collectors.toList());

		return CarteEnum.getCarteEnumByValeur(paires.get(0));
	}

	public static CarteEnum getSecondDoublePaire(final List<Carte> cartesJoueur, final List<Carte> cartesVisibles) {

		final List<Integer> valeurs = getValeursCartesTriees(cartesJoueur, cartesVisibles);
		final Map<Integer, Integer> cartesIdentiques = getCartesIdentiques(valeurs);

		final List<Integer> paires = cartesIdentiques.entrySet().stream().filter(c -> c.getValue() == 2)
				.map(c -> c.getKey()).collect(Collectors.toList());

		return CarteEnum.getCarteEnumByValeur(paires.get(1));
	}

	public boolean hasBrelan(final List<Carte> cartesJoueur, final List<Carte> cartesVisibles) {

		return hasNombreCartesSouhaite(cartesJoueur, cartesVisibles, 3);
	}

	public static CarteEnum getCarteBrelan(final List<Carte> cartesJoueur, final List<Carte> cartesVisibles) {

		return getCartePourNombreCartesSouhaite(cartesJoueur, cartesVisibles, 3);
	}

	public boolean hasCarre(final List<Carte> cartesJoueur, final List<Carte> cartesVisibles) {

		return hasNombreCartesSouhaite(cartesJoueur, cartesVisibles, 4);
	}

	private static CarteEnum getCarteCarre(final List<Carte> cartesJoueur, final List<Carte> cartesVisibles) {

		return getCartePourNombreCartesSouhaite(cartesJoueur, cartesVisibles, 4);
	}

	public boolean hasCouleur(final List<Carte> cartesJoueur, final List<Carte> cartesVisibles) {

		final List<Carte> cartes = getCartesJoueurPlusCartesVisibles(cartesJoueur, cartesVisibles);
		final List<ColorEnum> couleurs = getCouleursCartesTriees(cartes);
		final Map<ColorEnum, Integer> cartesDeMemeCouleur = getCartesMemeCouleur(couleurs);

		return couleurs.stream().anyMatch(c -> cartesDeMemeCouleur.get(c) >= 5);
	}

	public static ColorEnum getCouleurPourMainCouleur(final List<Carte> cartesJoueur,
			final List<Carte> cartesVisibles) {

		final List<Carte> cartes = getCartesJoueurPlusCartesVisibles(cartesJoueur, cartesVisibles);
		final List<ColorEnum> couleurs = getCouleursCartesTriees(cartes);
		final Map<ColorEnum, Integer> cartesDeMemeCouleur = getCartesMemeCouleur(couleurs);

		return couleurs.stream().filter(c -> cartesDeMemeCouleur.get(c) >= 5).findFirst().get();
	}

	public boolean hasQuinte(final List<Carte> cartesJoueur, final List<Carte> cartesVisibles) {

		final List<Integer> valeurs = getValeursCartesTriees(cartesJoueur, cartesVisibles);

		int i = 1;
		int valeurPrecedente = -1;
		for (final Integer v : valeurs) {
			if ((valeurPrecedente + 1) == v) {
				i++;
			} else {
				i = 1;
			}
			valeurPrecedente = v;
		}
		return i >= 5;
	}

	public CarteEnum getCarteHauteQuinte(final List<Carte> cartesJoueur, final List<Carte> cartesVisibles) {

		final List<Integer> valeurs = getValeursCartesTriees(cartesJoueur, cartesVisibles);

		int valeurPrecedente = -1;
		int valeurCarteHaute = 0;
		for (final Integer v : valeurs) {
			if (valeurPrecedente + 1 == v) {
				valeurCarteHaute = v;
			}
			valeurPrecedente = v;
		}
		return CarteEnum.getCarteEnumByValeur(valeurCarteHaute);
	}

	public boolean hasQuinteFlush(final List<Carte> cartesJoueur, final List<Carte> cartesVisibles) {

		final Map<Integer, ColorEnum> valeursParCouleur = getValeursCartesTrieesParCouleur(cartesJoueur,
				cartesVisibles);

		int i = 1;
		Entry<Integer, ColorEnum> valeurPrecedente = null;
		for (final Entry<Integer, ColorEnum> v : valeursParCouleur.entrySet()) {
			if ((valeurPrecedente != null) && ((valeurPrecedente.getKey() + 1) == v.getKey())
					&& (valeurPrecedente.getValue() == v.getValue())) {
				i++;
			} else {
				i = 1;
			}
			valeurPrecedente = v;
		}
		return i >= 5;
	}

	public boolean hasQuinteFlushRoyale(final List<Carte> cartesJoueur, final List<Carte> cartesVisibles) {

		final Map<Integer, ColorEnum> valeursParCouleur = getValeursCartesTrieesParCouleur(cartesJoueur,
				cartesVisibles);

		int i = 1;
		Entry<Integer, ColorEnum> valeurPrecedente = null;
		boolean aUnAsMemeCouleur = false;
		ColorEnum couleurQuinte = null;
		for (final Entry<Integer, ColorEnum> v : valeursParCouleur.entrySet()) {
			if ((valeurPrecedente != null)
					&& ((v.getKey() > CarteEnum.DIX.getValeur()) && ((valeurPrecedente.getKey() + 1) == v.getKey()))
					&& (valeurPrecedente.getValue() == v.getValue())) {
				i++;
				couleurQuinte = v.getValue();
			} else {
				i = 1;
			}
			valeurPrecedente = v;
		}
		for (final Entry<Integer, ColorEnum> e : valeursParCouleur.entrySet()) {
			if ((e.getKey() == CarteEnum.AS.getValeur()) && (couleurQuinte == e.getValue())) {
				aUnAsMemeCouleur = true;
				break;
			}
		}
		return (i >= 4) && aUnAsMemeCouleur;
	}

	public boolean hasFullHouse(final List<Carte> cartesJoueur, final List<Carte> cartesVisibles) {

		final List<Integer> valeurs = getValeursCartesTriees(cartesJoueur, cartesVisibles);
		final Map<Integer, Integer> cartesIdentiques = getCartesIdentiques(valeurs);

		final boolean hasPaire = cartesIdentiques.entrySet().stream().anyMatch(c -> c.getValue() == 2);
		final boolean hasBrelan = cartesIdentiques.entrySet().stream().anyMatch(c -> c.getValue() == 3);

		return hasPaire && hasBrelan;
	}

	private static boolean hasNombreCartesSouhaite(final List<Carte> cartesJoueur, final List<Carte> cartesVisibles,
			final int nbCarteSouhaite) {

		final List<Integer> valeurs = getValeursCartesTriees(cartesJoueur, cartesVisibles);

		final Map<Integer, Integer> cartesIdentiques = getCartesIdentiques(valeurs);

		return valeurs.stream().anyMatch(v -> cartesIdentiques.get(v) == nbCarteSouhaite);
	}

	private static CarteEnum getCartePourNombreCartesSouhaite(final List<Carte> cartesJoueur,
			final List<Carte> cartesVisibles, final int nbCarteSouhaite) {

		final List<Integer> valeurs = getValeursCartesTriees(cartesJoueur, cartesVisibles);

		final Map<Integer, Integer> cartesIdentiques = getCartesIdentiques(valeurs);

		return CarteEnum.getCarteEnumByValeur(
				valeurs.stream().filter(v -> cartesIdentiques.get(v) == nbCarteSouhaite).findFirst().get());
	}

	private static List<Integer> getValeursCartesTriees(final List<Carte> cartesJoueur,
			final List<Carte> cartesVisibles) {

		final List<Carte> cartes = getCartesJoueurPlusCartesVisibles(cartesJoueur, cartesVisibles);
		final List<Integer> valeurs = cartes.stream().map(Carte::getValeur).collect(Collectors.toList());
		Collections.sort(valeurs);
		return valeurs;
	}

	private static List<ColorEnum> getCouleursCartesTriees(final List<Carte> cartes) {

		final List<ColorEnum> couleurs = cartes.stream().map(Carte::getCouleur).collect(Collectors.toList());
		Collections.sort(couleurs);
		return couleurs;
	}

	private static Map<Integer, ColorEnum> getValeursCartesTrieesParCouleur(final List<Carte> cartesJoueur,
			final List<Carte> cartesVisibles) {

		final List<Carte> cartes = getCartesJoueurPlusCartesVisibles(cartesJoueur, cartesVisibles);
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

	private static CarteEnum getCarteHaute(final List<Carte> cartesJoueur, final List<Carte> cartesVisibles) {

		final List<Integer> valeursTriees = getValeursCartesTriees(cartesJoueur, cartesVisibles);

		return CarteEnum.AS.getValeur() == Iterables.getFirst(valeursTriees, 0) ? CarteEnum.AS
				: CarteEnum.getCarteEnumByValeur(Iterables.getLast(valeursTriees));

	}

	private static List<Carte> getCartesJoueurPlusCartesVisibles(final List<Carte> cartesJoueur,
			final List<Carte> cartesVisibles) {

		final List<Carte> cartes = Lists.newArrayList();
		cartes.addAll(cartesJoueur);
		cartes.addAll(cartesVisibles);
		return cartes;
	}

}
