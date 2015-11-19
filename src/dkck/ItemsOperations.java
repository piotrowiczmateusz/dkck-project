package dkck;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

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

	private void dropItem(int index) {
		Item tempItem = this.getItemsArray().get(index);

		if (tempItem instanceof Bomb && ((Bomb) tempItem).getBombTimer() != null) {
			((Bomb) tempItem).getBombTimer().cancel();
			((Bomb) tempItem).getBombTimer().setItemReference(null);
			((Bomb) tempItem).setBombTimer(null);
		}
		this.getItemsArray().remove(index);
		MainWindow.grid.drawSquare(tempItem.getPositionX(), tempItem.getPositionY(), tempItem.getPositionX(),
				tempItem.getPositionY(), MainWindow.cellColor);
		MainWindow.grid.repairSquare(tempItem.getPositionX(), tempItem.getPositionY());

		MainWindow.grid.drawCircle(tempItem.getPositionX(), tempItem.getPositionY(), tempItem.getRange(),
				MainWindow.cellColor);
		MainWindow.grid.repairCircle(tempItem.getPositionX(), tempItem.getPositionY(), tempItem.getRange());
		MainWindow.grid.repairSquare2();
		MainWindow.grid.repairCircle2();
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
					this.dropItem(currentBombIndex + beginningArrayIndex);
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

		// itemsArray.add(new Sapper());
		itemsArray.add(new Bomb());
		itemsArray.add(new Bomb());
		itemsArray.add(new Bomb());

		for (int i = 0; i < 2; ++i) {
			itemsArray.add(new Bomb());
			itemsArray.add(new Rocket(itemsArray.get(0)));
		}

		for (int i = 0; i < 2000; ++i) {
			 itemsArray.add(new Rocket(itemsArray.get(0)));
			dropItem(itemsArray.size() - 1);
		}

//		for (int i = 0; i < itemsArray.size(); i++) {
//			Item tempItem = itemsArray.get(i);
//			if (tempItem instanceof Bomb && !(tempItem instanceof Rocket)) {
//				MainWindow.timerPanel.add(((Bomb) tempItem).getTimerLog(), BorderLayout.WEST);
//			}
//		}

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