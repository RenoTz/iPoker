package utils;

import static java.util.Objects.nonNull;

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
		final List<Carte> cartesJoueursPlusCartesVisibles = Lists.newArrayList();
		CarteCombinaison meilleureCombinaison = null;
		for (final Joueur joueur : joueurs) {
			cartesJoueursPlusCartesVisibles.addAll(joueur.getCartes());
			cartesJoueursPlusCartesVisibles.addAll(cartesVisibles);
			final CarteCombinaison mainDuJoueur = this.getMeilleureCombinaison(cartesJoueursPlusCartesVisibles);

			if (meilleureCombinaison != null) {
				if (mainDuJoueur.getCombinaison().getValeur() > meilleureCombinaison.getCombinaison().getValeur()) {
					meilleureCombinaison = mainDuJoueur;
					joueurAvecMeilleureMain = joueur;
				} else if (mainDuJoueur.getCombinaison().getValeur() == meilleureCombinaison.getCombinaison()
						.getValeur()) {
					CarteEnum carteDuJoueurEnCours = null;
					CarteEnum carteDuJoueurAvecMeilleureMain = null;
					switch (mainDuJoueur.getCombinaison()) {
					case PAIRE:
						carteDuJoueurEnCours = getCartePaire(cartesJoueursPlusCartesVisibles);
						reinitialiserCartesJoueursPlusCartesVisibles(cartesVisibles, joueurAvecMeilleureMain,
								cartesJoueursPlusCartesVisibles);
						carteDuJoueurAvecMeilleureMain = getCartePaire(cartesJoueursPlusCartesVisibles);
						break;
					case BRELAN:
						carteDuJoueurEnCours = getCarteBrelan(cartesJoueursPlusCartesVisibles);
						reinitialiserCartesJoueursPlusCartesVisibles(cartesVisibles, joueurAvecMeilleureMain,
								cartesJoueursPlusCartesVisibles);
						carteDuJoueurAvecMeilleureMain = getCarteBrelan(cartesJoueursPlusCartesVisibles);
						break;
					case CARRE:
						carteDuJoueurEnCours = getCarteCarre(cartesJoueursPlusCartesVisibles);
						reinitialiserCartesJoueursPlusCartesVisibles(cartesVisibles, joueurAvecMeilleureMain,
								cartesJoueursPlusCartesVisibles);
						carteDuJoueurAvecMeilleureMain = getCarteCarre(cartesJoueursPlusCartesVisibles);
						break;
					default:
					}
					if (nonNull(carteDuJoueurEnCours) && nonNull(carteDuJoueurAvecMeilleureMain)) {
						if (carteDuJoueurEnCours.getValeur() > carteDuJoueurAvecMeilleureMain.getValeur()) {
							joueurAvecMeilleureMain = joueur;
						} else if (carteDuJoueurEnCours.getValeur() == carteDuJoueurAvecMeilleureMain.getValeur()) {
							final List<Integer> valeursCartesTrieesJoueurAvecMeilleureMain = getValeursCartesTriees(
									cartesJoueursPlusCartesVisibles);
							reinitialiserCartesJoueursPlusCartesVisibles(cartesVisibles, joueur,
									cartesJoueursPlusCartesVisibles);
							final List<Integer> valeursCartesTrieesJoueurEnCours = getValeursCartesTriees(
									cartesJoueursPlusCartesVisibles);
							final int valeurCarteJoueurMeilleureMain = carteDuJoueurAvecMeilleureMain.getValeur()
									.intValue();
							final int valeurCarteJoueurEnCours = carteDuJoueurEnCours.getValeur().intValue();
							valeursCartesTrieesJoueurAvecMeilleureMain
									.removeIf(c -> c.intValue() == valeurCarteJoueurMeilleureMain);
							valeursCartesTrieesJoueurEnCours.removeIf(c -> c.intValue() == valeurCarteJoueurEnCours);
							if ((Iterables.getLast(valeursCartesTrieesJoueurEnCours) > Iterables
									.getLast(valeursCartesTrieesJoueurAvecMeilleureMain))
									|| (valeursCartesTrieesJoueurEnCours.get(0) == CarteEnum.AS.getValeur()
											&& valeursCartesTrieesJoueurAvecMeilleureMain.get(0) != CarteEnum.AS
													.getValeur())) {
								joueurAvecMeilleureMain = joueur;
							}
						}
					}
				}
			} else {
				meilleureCombinaison = mainDuJoueur;
				joueurAvecMeilleureMain = joueur;
			}
			cartesJoueursPlusCartesVisibles.clear();
		}

		if (nonNull(joueurAvecMeilleureMain)) {
			joueurAvecMeilleureMain.setWon(true);
		}
		return joueurAvecMeilleureMain;
	}

	private static void reinitialiserCartesJoueursPlusCartesVisibles(final List<Carte> cartesVisibles,
			Joueur joueurAvecMeilleureMain, final List<Carte> cartesJoueursPlusCartesVisibles) {

		cartesJoueursPlusCartesVisibles.clear();
		cartesJoueursPlusCartesVisibles.addAll(joueurAvecMeilleureMain.getCartes());
		cartesJoueursPlusCartesVisibles.addAll(cartesVisibles);
	}

	public CarteCombinaison getMeilleureCombinaison(final List<Carte> cartes) {

		final CarteCombinaison retour = new CarteCombinaison();
		retour.setHauteur(getCarteHaute(cartes));
		retour.setCombinaison(CombinaisonEnum.HAUTEUR);

		if (this.hasQuinteFlush(cartes)) {
			retour.setCombinaison(CombinaisonEnum.QUINTE_FLUSH);
		} else if (hasCarre(cartes)) {
			retour.getCartes()[0] = getCarteCarre(cartes);
			retour.setCombinaison(CombinaisonEnum.CARRE);
		} else if (this.hasFullHouse(cartes)) {
			retour.getCartes()[0] = CarteEnum.AS;
			retour.setCombinaison(CombinaisonEnum.FULL_HOUSE);
		} else if (this.hasCouleur(cartes)) {
			retour.setCouleur(getCouleurPourMainCouleur(cartes));
			retour.setCombinaison(CombinaisonEnum.COULEUR);
		} else if (this.hasQuinte(cartes)) {
			retour.setHauteur(this.getCarteHauteQuinte(cartes));
			retour.setCombinaison(CombinaisonEnum.QUINTE);
		} else if (this.hasBrelan(cartes)) {
			retour.getCartes()[0] = getCarteBrelan(cartes);
			retour.setCombinaison(CombinaisonEnum.BRELAN);
		} else if (hasDoublePaire(cartes)) {
			retour.getCartes()[0] = getFirstDoublePaire(cartes);
			retour.getCartes()[1] = getSecondDoublePaire(cartes);
			retour.setCombinaison(CombinaisonEnum.DOUBLE_PAIRE);
		} else if (hasPaire(cartes)) {
			retour.getCartes()[0] = getCartePaire(cartes);
			retour.setCombinaison(CombinaisonEnum.PAIRE);
		}

		return retour;
	}

	public static boolean hasPaire(final List<Carte> cartes) {

		return hasNombreCartesSouhaite(cartes, 2);
	}

	public static CarteEnum getCartePaire(final List<Carte> cartes) {

		return getCartePourNombreCartesSouhaite(cartes, 2);
	}

	public static boolean hasDoublePaire(final List<Carte> cartes) {

		final List<Integer> valeurs = getValeursCartesTriees(cartes);
		final Map<Integer, Integer> cartesIdentiques = getCartesIdentiques(valeurs);

		final List<Integer> paires = cartesIdentiques.entrySet().stream().filter(c -> c.getValue() == 2)
				.map(c -> c.getKey()).collect(Collectors.toList());

		return paires.size() == 2;
	}

	public static CarteEnum getFirstDoublePaire(final List<Carte> cartes) {

		final List<Integer> valeurs = getValeursCartesTriees(cartes);
		final Map<Integer, Integer> cartesIdentiques = getCartesIdentiques(valeurs);

		final List<Integer> paires = cartesIdentiques.entrySet().stream().filter(c -> c.getValue() == 2)
				.map(c -> c.getKey()).collect(Collectors.toList());

		return CarteEnum.getCarteEnumByValeur(paires.get(0));
	}

	public static CarteEnum getSecondDoublePaire(final List<Carte> cartes) {

		final List<Integer> valeurs = getValeursCartesTriees(cartes);
		final Map<Integer, Integer> cartesIdentiques = getCartesIdentiques(valeurs);

		final List<Integer> paires = cartesIdentiques.entrySet().stream().filter(c -> c.getValue() == 2)
				.map(c -> c.getKey()).collect(Collectors.toList());

		return CarteEnum.getCarteEnumByValeur(paires.get(1));
	}

	public boolean hasBrelan(final List<Carte> cartes) {

		return hasNombreCartesSouhaite(cartes, 3);
	}

	public static CarteEnum getCarteBrelan(final List<Carte> cartes) {

		return getCartePourNombreCartesSouhaite(cartes, 3);
	}

	public static boolean hasCarre(final List<Carte> cartes) {

		return hasNombreCartesSouhaite(cartes, 4);
	}

	private static CarteEnum getCarteCarre(final List<Carte> cartes) {

		return getCartePourNombreCartesSouhaite(cartes, 4);
	}

	public boolean hasCouleur(final List<Carte> cartes) {

		final List<ColorEnum> couleurs = getCouleursCartesTriees(cartes);

		final Map<ColorEnum, Integer> cartesDeMemeCouleur = getCartesMemeCouleur(couleurs);

		return couleurs.stream().anyMatch(c -> cartesDeMemeCouleur.get(c) >= 5);
	}

	public static ColorEnum getCouleurPourMainCouleur(final List<Carte> cartes) {

		final List<ColorEnum> couleurs = getCouleursCartesTriees(cartes);

		final Map<ColorEnum, Integer> cartesDeMemeCouleur = getCartesMemeCouleur(couleurs);

		return couleurs.stream().filter(c -> cartesDeMemeCouleur.get(c) >= 5).findFirst().get();
	}

	public boolean hasQuinte(final List<Carte> cartes) {

		final List<Integer> valeurs = getValeursCartesTriees(cartes);

		int i = 1;
		int valeurPrecedente = -1;
		for (final Integer v : valeurs) {
			if ((valeurPrecedente + 1) == v) {
				i++;
			}
			valeurPrecedente = v;
		}
		return i >= 5;
	}

	public CarteEnum getCarteHauteQuinte(final List<Carte> cartes) {

		final List<Integer> valeurs = getValeursCartesTriees(cartes);

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

	public boolean hasQuinteFlush(final List<Carte> cartes) {

		final Map<Integer, ColorEnum> valeursParCouleur = getValeursCartesTrieesParCouleur(cartes);

		int i = 1;
		Entry<Integer, ColorEnum> valeurPrecedente = null;
		for (final Entry<Integer, ColorEnum> v : valeursParCouleur.entrySet()) {
			if ((valeurPrecedente != null) && ((valeurPrecedente.getKey() + 1) == v.getKey())
					&& (valeurPrecedente.getValue() == v.getValue())) {
				i++;
			}
			valeurPrecedente = v;
		}
		return i >= 5;
	}

	public boolean hasQuinteFlushRoyale(final List<Carte> cartes) {

		final Map<Integer, ColorEnum> valeursParCouleur = getValeursCartesTrieesParCouleur(cartes);

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

	public boolean hasFullHouse(final List<Carte> cartes) {

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

	private static CarteEnum getCartePourNombreCartesSouhaite(final List<Carte> cartes, final int nbCarteSouhaite) {

		final List<Integer> valeurs = getValeursCartesTriees(cartes);

		final Map<Integer, Integer> cartesIdentiques = getCartesIdentiques(valeurs);

		return CarteEnum.getCarteEnumByValeur(
				valeurs.stream().filter(v -> cartesIdentiques.get(v) == nbCarteSouhaite).findFirst().get());
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

	private static Map<Integer, ColorEnum> getValeursCartesTrieesParCouleur(final List<Carte> cartes) {

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

	private static CarteEnum getCarteHaute(final List<Carte> cartes) {

		final List<Integer> valeursTriees = getValeursCartesTriees(cartes);

		return CarteEnum.AS.getValeur() == Iterables.getFirst(valeursTriees, 0) ? CarteEnum.AS
				: CarteEnum.getCarteEnumByValeur(Iterables.getLast(valeursTriees));

	}

}
