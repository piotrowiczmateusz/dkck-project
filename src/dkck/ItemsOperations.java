package dkck;

import java.util.ArrayList;
import java.util.List;

import dkck.GUI.Grid;
import dkck.GUI.MainWindow;

public class ItemsOperations {

	private List<Item> itemsArray;

	/**
	 * SETTERS AND GETTERS
	 */

	public List<Item> getItemsArray() {
		return itemsArray;
	}

	public void setItemsArray(List<Item> itemsArray) {
		this.itemsArray = itemsArray;
	}

	/**
	 * CONSTRUCTORS
	 */

	public ItemsOperations() {
		super();
		this.itemsArray = new ArrayList<Item>();
	}

	static int findItem(Item itemReference) {
		return MainWindow.itemsCollection.getItemsArray().indexOf(itemReference);
	}

	static void dropItem(int index) {
		Item tempItem = MainWindow.itemsCollection.getItemsArray().get(index);

		if (tempItem.getMovingTimer() != null) {
			((Bomb) tempItem).getMovingTimer().cancel();
			((Bomb) tempItem).getMovingTimer().setItemReference(null);
			((Bomb) tempItem).setMovingTimer(null);
		}

		if (tempItem instanceof Bomb && ((Bomb) tempItem).getBombTimer() != null) {
			((Bomb) tempItem).getBombTimer().cancel();
			((Bomb) tempItem).getBombTimer().setItemReference(null);
			((Bomb) tempItem).setBombTimer(null);
		}
		MainWindow.itemsCollection.getItemsArray().remove(index);
		MainWindow.grid.drawSquare(tempItem.getPositionX(), tempItem.getPositionY(), tempItem.getPositionX(),
				tempItem.getPositionY(), null);
		// MainWindow.grid.repairSquare(tempItem.getPositionX(),
		// tempItem.getPositionY());

		MainWindow.grid.drawCircle(tempItem.getPositionX(), tempItem.getPositionY(), tempItem.getRange(), null);
		// MainWindow.grid.repairCircle(tempItem.getPositionX(),
		// tempItem.getPositionY(), tempItem.getRange());
		Grid.repairSquares();
		Grid.repairCircles();
	}

	protected void createBombs(int numberOfBombs) {

		Point pointReference = new Point(0, 0);

		int beginningArrayIndex = this.getItemsArray().size() - 1;

		for (int currentBombIndex = 0; currentBombIndex < numberOfBombs; currentBombIndex++) {
			boolean goodBombPosition = true;
			do {
				this.getItemsArray().add(new Bomb());

				boolean continueSearching = true;

				int maxCounter = 0;

				for (int tempX = pointReference.getPositionX(); tempX < MainWindow.gridRows; tempX++) {
					for (int tempY = pointReference.getPositionY(); tempY < MainWindow.gridColumns; tempY++) {
						if (continueSearching) {
							int counter = 0;
							for (int arrayIndex = beginningArrayIndex + 1; arrayIndex < currentBombIndex
									+ beginningArrayIndex; arrayIndex++) {
								if (pointReference.checkItemsCenterDistance(this.getItemsArray().get(arrayIndex))) {
									counter++;
								}
							}

							if (maxCounter < counter)
								maxCounter = counter;

							if (maxCounter == currentBombIndex + 1) {
								continueSearching = false;
								pointReference.setPositionX(tempX);
								pointReference.setPositionY(tempY);
							}
						}
					}
				}

				if (continueSearching) {
					dropItem(currentBombIndex + beginningArrayIndex);
				} else {
					goodBombPosition = true;
				}
			} while (goodBombPosition == false);
		}
	}

	// nowa metoda służąca do dodawania itemów
	public void addItems() throws InterruptedException {

		itemsArray.add(new Sapper()); // WAŻNE proponuje, żeby został 1 saper,
										// zawsze dodawnay jako pierwszy, ułatwi
										// to pracę

		itemsArray.add(new Sapper());
		itemsArray.add(new Sapper());

		itemsArray.add(new Sapper());

		// itemsArray.add(new Sapper());
		itemsArray.add(new Bomb());
		itemsArray.add(new Bomb());
		itemsArray.add(new Bomb());

		for (int i = 0; i < 2; ++i) {
			itemsArray.add(new Bomb());
			itemsArray.add(new Rocket(itemsArray.get(0)));
		}

		for (int j = 0; j < 3; ++j) {
			//Thread.sleep(1000);
			for (int i = 0; i < 2; ++i) {
				// Thread.sleep(300);
				itemsArray.add(new Rocket(itemsArray.get(0)));
				// dropItem(findItem(this.getItemsArray().get(itemsArray.size()
				// - 1)));
				// Thread.sleep(300);

				// dropItem(findItem(this.getItemsArray().get(itemsArray.size()
				// - 1)));

				// dropItem(itemsArray.size() - 1);
			}
		}

		for (int j = 0; j < 3; ++j) {
			//Thread.sleep(1000);
			for (int i = 0; i < 2; ++i) {
				//Thread.sleep(300);

				// Thread.sleep(300);

				//dropItem(findItem(this.getItemsArray().get(itemsArray.size() - 1)));

				// dropItem(itemsArray.size() - 1);
			}
		}

		// for (int j = 0; j < 5; ++j) {
		// Thread.sleep(300);
		// for (int i = 0; i < 3; ++i) {
		// // Thread.sleep(300);
		// //itemsArray.add(new Rocket(itemsArray.get(0)));
		// Thread.sleep(300);
		// dropItem(itemsArray.size() - 1);
		// }
		// }
		// while(true)
		// {
		// MainWindow.grid.repairCircles();
		// MainWindow.grid.repairSquares();
		// }
	}

	public void actions() throws InterruptedException {

		/*
		 * testowanie metod, w końcowej wersji wszystko musi być wykonywane
		 * przez interpreter
		 * 
		 * itemsArray.get(3).go(3, 7); ((Sapper)
		 * itemsArray.get(3)).disarmBomb(itemsArray.get(0)); ((Sapper)
		 * itemsArray.get(3)).moveBomb(itemsArray.get(2), 2, 3);
		 * itemsArray.get(3).go(3, 7);
		 * itemsArray.get(3).reachItem(itemsArray.get(1));
		 * itemsArray.get(3).go(30, 49);
		 * itemsArray.get(3).reachItem(itemsArray.get(1)); ((Bomb)
		 * itemsArray.get(0)).explode();
		 * 
		 */
	}
}