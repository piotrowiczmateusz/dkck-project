package dkck;

import java.util.ArrayList;
import java.util.List;

import dkck.GUI.Grid;
import dkck.GUI.MainWindow;

public class ItemsOperations {

	private List<Item> itemsArray;

	public List<Item> getItemsArray() {
		return itemsArray;
	}

	public void setItemsArray(List<Item> itemsArray) {
		this.itemsArray = itemsArray;
	}

	public ItemsOperations() {
		super();
		this.itemsArray = new ArrayList<Item>();
	}
	
	static void checkGameOver()
	{
		boolean tempVariableSapper = true;
		for (Item tempItem : MainWindow.itemsCollection.getItemsArray()) {
			if (tempItem instanceof Sapper) {
				if (((Sapper) tempItem).getHealthPoints() > 0)
					tempVariableSapper = false;
			}
		}
		if (tempVariableSapper == true)
			MainWindow.updateLog("Wszyscy nie saperzy nie żyją");
		
		boolean tempVariableBomb = true;
		for (Item tempItem : MainWindow.itemsCollection.getItemsArray()) {
			if (tempItem instanceof Bomb && !(tempItem instanceof Rocket)) {
				if (((Bomb) tempItem).getBombStatus() == 1)
					tempVariableBomb = false;
			}
		}
		if (tempVariableBomb == true)
			MainWindow.updateLog("Wszystkie bomby nieaktywne");
		///return temp_variable;
	}

	static void dropItem(Item itemReference) {

		if (itemReference.getMovingTimer() != null) {
			((Bomb) itemReference).getMovingTimer().cancel();
			((Bomb) itemReference).getMovingTimer().setItemRef(null);
			((Bomb) itemReference).setMovingTimer(null);
		}

		if (itemReference instanceof Bomb && ((Bomb) itemReference).getBombTimer() != null) {
			((Bomb) itemReference).getBombTimer().cancel();
			((Bomb) itemReference).getBombTimer().setItemRef(null);
			((Bomb) itemReference).setBombTimer(null);
		}

		if (itemReference instanceof Bomb && !(itemReference instanceof Rocket)) {
			Bomb bombReference = (Bomb) itemReference;
			MainWindow.timerPanel.remove(((Bomb) bombReference).getTimerLog());
			bombReference.setTimerLog(null);
		}

		MainWindow.itemsCollection.getItemsArray().remove(itemReference);

		MainWindow.grid.drawCircle(itemReference.getPositionX(), itemReference.getPositionY(), itemReference.getRange(),
				null);
		MainWindow.grid.drawSquare(itemReference.getPositionX(), itemReference.getPositionY(),
				itemReference.getPositionX(), itemReference.getPositionY(), null);

		Grid.repairCircles();
		Grid.repairSquares();
	}

	// nowa metoda służąca do dodawania itemów

	public void addItems() throws InterruptedException {

		itemsArray.add(new Tree());
		itemsArray.add(new Tree());

		itemsArray.add(new Sapper());
		itemsArray.add(new Sapper());
		itemsArray.add(new Sapper());
		itemsArray.add(new Sapper());

		itemsArray.add(new Bomb());
		itemsArray.add(new Bomb());
		itemsArray.add(new Bomb());
		itemsArray.add(new Bomb());

		dropItem(itemsArray.get(itemsArray.size() - 1));

	}
}