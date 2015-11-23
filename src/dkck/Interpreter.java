package dkck;

import dkck.GUI.MainWindow;

public class Interpreter {

	public Interpreter() {
		super();
	}

	// metoda sprawdzajaca czy bomba istnieje
	public Item findElementByID(int id, Class<?> typeOfItem) {

		for (int i = 1; i < MainWindow.itemsCollection.getItemsArray().size(); i++) {
			Item tempItem = MainWindow.itemsCollection.getItemsArray().get(i);

			if ((typeOfItem.isInstance(tempItem)) && (typeOfItem.equals(tempItem.getClass()) && tempItem.getId() == id)) {
				return tempItem;
			}
		}
		MainWindow.updateLog("Item nie istnieje");

		return null;
	}

	// metoda zwracająca konkretną bombę w zależności od wejścia
	public Bomb getBomb(String input) {

		Class<?> bombClass = Bomb.class;

		Item tempBomb = null;

		if (input.matches("|(.*)pierwsza(.*)|(.*)pierwszą(.*)|(.*)pierwszej(.*)|(.*)jeden(.*)")) {
			tempBomb = findElementByID(0, bombClass);
		}

		else if (input.matches("|(.*)druga(.*)|(.*)drugą(.*)|(.*)drugiej(.*)|(.*)dwa(.*)")) {
			tempBomb = findElementByID(1, bombClass);
		}

		else if (input.matches("|(.*)trzecia(.*)|(.*)trzecią(.*)|(.*)trzeciej(.*)|(.*)trzy(.*)")) {
			tempBomb = findElementByID(2, bombClass);
		}

		else if (input.matches("|(.*)czwarta(.*)|(.*)czwartą(.*)|(.*)czwartej(.*)|(.*)cztery(.*)")) {
			tempBomb = findElementByID(3, bombClass);
		}

		else if (input.matches("|(.*)piąta(.*)|(.*)piątą(.*)|(.*)piątej(.*)|(.*)pięć(.*)")) {
			tempBomb = findElementByID(4, bombClass);
		}

		else if (input.matches("|(.*)szósta(.*)|(.*)szóstą(.*)|(.*)szóstej(.*)|(.*)sześć(.*)")) {
			tempBomb = findElementByID(5, bombClass);
		}

		else if (input.matches("|(.*)najbliższą(.*)|(.*)najbliższej(.*)|(.*)najbliżej(.*)|(.*)blisko(.*)")) {

			double min = 50;

			for (int i = 1; i < MainWindow.itemsCollection.getItemsArray().size(); i++) {
				Item tempItem = MainWindow.itemsCollection.getItemsArray().get(i);

				if ((tempItem instanceof Bomb) && !(tempItem instanceof Rocket)) {
					double value = tempItem.distanceCalculation(MainWindow.itemsCollection.getItemsArray().get(0));
					if (value < min) {
						min = value;
						tempBomb = (Bomb) tempItem;
					}
				}
			}
		}

		else if (input.matches("|(.*)najdalszą(.*)|(.*)najdalszej(.*)|(.*)najdalej(.*)|(.*)daleko(.*)")) {

			double max = 0;

			for (int i = 1; i < MainWindow.itemsCollection.getItemsArray().size(); i++) {
				Item tempItem = MainWindow.itemsCollection.getItemsArray().get(i);

				if ((tempItem instanceof Bomb) && !(tempItem instanceof Rocket)) {
					double value = tempItem.distanceCalculation(MainWindow.itemsCollection.getItemsArray().get(0));
					if (value > max) {
						max = value;
						tempBomb = (Bomb) tempItem;
					}
				}
			}
		}

		else {
			MainWindow.updateLog("Sprecyzuj swoje polecenie.");
		}

		return (Bomb) tempBomb;
	}

	public void interpret(String input) throws InterruptedException {

		Sapper tempSapper = (Sapper) MainWindow.itemsCollection.getItemsArray().get(0);

		/* Przemieszczanie siê */

		if (input.matches("|(.*)idź(.*)|(.*)pójdź(.*)")) {

			int x = tempSapper.getPositionX();
			int y = tempSapper.getPositionY();

			if (input.matches("(.*)bomba(.*)|(.*)bomby(.*)|(.*)bombę(.*)")) {

				Bomb tempBomb = getBomb(input);

				x = tempBomb.getPositionX();
				y = tempBomb.getPositionY();

			}

			else {
				if (input.matches("(.*)północ(.*)|(.*)północny(.*)|(.*)góra(.*)|(.*)górę(.*)")) {
					x = 0;
				} else if (input.matches("(.*)południe(.*)|(.*)południowy(.*)|(.*)dół(.*)")) {
					x = 49;
				}
				if (input.matches("(.*)zachód(.*)|(.*)zachodni(.*)|(.*)lewo(.*)|(.*)lewy(.*)")) {
					y = 0;
				} else if (input.matches("(.*)wschód(.*)|(.*)wschodni(.*)|(.*)prawo(.*)|(.*)prawy(.*)")) {
					y = 49;
				}
			}

			tempSapper.go(x, y);
		}

		else if (input.matches("(.*)rozbrój(.*)|(.*)rozbroić(.*)")) {

			Bomb tempBomb = getBomb(input);
			tempSapper.disarmBomb(tempBomb);

		}

		else if (input.matches("(.*)przenieś(.*)|(.*)przenieść(.*)")) {

			Bomb tempBomb = getBomb(input);
			tempSapper.moveBomb(tempBomb, 0, 0);

		}

		else {
		}
	}
	/*
	 * if(input.matches("(.*)[0-9](.*)")) { for(int i = 0; i < input.length()-1;
	 * i++) { if(Character.isDigit(input.charAt(i))) { x = input.charAt(i) - 23;
	 * 
	 * System.out.println(x); }
	 * 
	 * } }
	 */
}