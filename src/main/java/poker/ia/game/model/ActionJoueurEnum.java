package poker.ia.game.model;

public enum ActionJoueurEnum {

	ATTENDRE() {

		@Override
		public ActionJoueurEnum actionSuivante(ActionJoueurEnum action) {

			switch (action) {
			case CHECKER:
				return CHECKER;
			case SUIVRE:
				return SUIVRE;
			case RELANCER:
				return RELANCER;
			case SURRELANCER:
				return SURRELANCER;
			case PASSER:
				return PASSER;
			default:
				throw new IllegalArgumentException();
			}
		}

	},

	CHECKER() {

		@Override
		public ActionJoueurEnum actionSuivante(ActionJoueurEnum action) {

			return ATTENDRE;
		}

	},

	SUIVRE() {

		@Override
		public ActionJoueurEnum actionSuivante(ActionJoueurEnum action) {

			return ATTENDRE;
		}

	},

	RELANCER() {

		@Override
		public ActionJoueurEnum actionSuivante(ActionJoueurEnum action) {

			return ATTENDRE;
		}

	},

	SURRELANCER() {

		@Override
		public ActionJoueurEnum actionSuivante(ActionJoueurEnum action) {

			return ATTENDRE;
		}

	},

	PASSER() {

		@Override
		public ActionJoueurEnum actionSuivante(ActionJoueurEnum action) {

			return A_TERMINER_DE_JOUER;
		}

	},

	A_TERMINER_DE_JOUER() {

		@Override
		public ActionJoueurEnum actionSuivante(ActionJoueurEnum action) {

			return null;
		}

	};

	public abstract ActionJoueurEnum actionSuivante(ActionJoueurEnum action);
}
