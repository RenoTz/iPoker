package model;

public enum ActionJoueurEnum {

	EN_ATTENTE() {

		@Override
		public ActionJoueurEnum actionSuivante(ActionJoueurEnum action, boolean premierJoueur) {

			return JOUER;
		}

	},

	JOUER() {

		@Override
		public ActionJoueurEnum actionSuivante(ActionJoueurEnum action, boolean premierJoueur) {

			switch (action) {
			case CHECKER:
				if (!premierJoueur) {
					return CHECKER;
				} else {
					throw new IllegalArgumentException("Il n'est pas permis au premier joueur de + " + action);
				}
			case SUIVRE:
				return SUIVRE;
			case RELANCER:
				return RELANCER;
			case SURRELANCER:
				if (!premierJoueur) {
					return SURRELANCER;
				} else {
					throw new IllegalArgumentException("Il n'est pas permis au premier joueur de + " + action);
				}
			case PASSER:
				return PASSER;
			default:
				break;
			}
			return null;
		}

	},

	CHECKER() {

		@Override
		public ActionJoueurEnum actionSuivante(ActionJoueurEnum action, boolean premierJoueur) {

			return EN_ATTENTE;
		}

	},

	SUIVRE() {

		@Override
		public ActionJoueurEnum actionSuivante(ActionJoueurEnum action, boolean premierJoueur) {

			return EN_ATTENTE;
		}

	},

	RELANCER() {

		@Override
		public ActionJoueurEnum actionSuivante(ActionJoueurEnum action, boolean premierJoueur) {

			return EN_ATTENTE;
		}

	},

	SURRELANCER() {

		@Override
		public ActionJoueurEnum actionSuivante(ActionJoueurEnum action, boolean premierJoueur) {

			return EN_ATTENTE;
		}

	},

	PASSER() {

		@Override
		public ActionJoueurEnum actionSuivante(ActionJoueurEnum action, boolean premierJoueur) {

			return A_TERMINER_DE_JOUER;
		}

	},

	A_TERMINER_DE_JOUER() {

		@Override
		public ActionJoueurEnum actionSuivante(ActionJoueurEnum action, boolean premierJoueur) {

			return null;
		}

	};

	public abstract ActionJoueurEnum actionSuivante(ActionJoueurEnum action, boolean premierJoueur);
}
