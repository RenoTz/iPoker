package poker.ia.game.model;

public enum ActionJoueurEnum {

	MISER_SMALL_BLIND(){

		@Override
		public ActionJoueurEnum actionSuivante(final ActionJoueurEnum action) {

			switch (action) {
			case SUIVRE:
				return SUIVRE;
			case RELANCER:
				return RELANCER;
			case PASSER:
				return PASSER;
			default:
				throw new IllegalArgumentException();
			}
		}

	},

	MISER_BIG_BLIND(){

		@Override
		public ActionJoueurEnum actionSuivante(final ActionJoueurEnum action) {

			switch (action) {
			case CHECKER:
				return CHECKER;
			case SUIVRE:
				return SUIVRE;
			case RELANCER:
				return RELANCER;
			case PASSER:
				return PASSER;
			default:
				throw new IllegalArgumentException();
			}
		}

	},

	ATTENDRE() {

		@Override
		public ActionJoueurEnum actionSuivante(final ActionJoueurEnum action) {

			return action;
		}

	},

	CHECKER() {

		@Override
		public ActionJoueurEnum actionSuivante(final ActionJoueurEnum action) {

			return action;
		}

	},

	SUIVRE() {

		@Override
		public ActionJoueurEnum actionSuivante(final ActionJoueurEnum action) {

			return action;
		}

	},

	RELANCER() {

		@Override
		public ActionJoueurEnum actionSuivante(final ActionJoueurEnum action) {

			return action;
		}

	},

	SURRELANCER() {

		@Override
		public ActionJoueurEnum actionSuivante(final ActionJoueurEnum action) {

			return action;
		}

	},

	PASSER() {

		@Override
		public ActionJoueurEnum actionSuivante(final ActionJoueurEnum action) {

			return A_TERMINER_DE_JOUER;
		}

	},

	A_TERMINER_DE_JOUER() {

		@Override
		public ActionJoueurEnum actionSuivante(final ActionJoueurEnum action) {

			return null;
		}

	};

	public abstract ActionJoueurEnum actionSuivante(ActionJoueurEnum action);
}
