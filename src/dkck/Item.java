package dkck;

public abstract class Item {

	/**
	 * ATTRIBUTES
	 */
	private int positionX;

	private int positionY;

	private int range;

	private int id;

	/**
	 * SETTERS AND GETTERS
	 */

	/**
	 * @return the positionX
	 */
	public int getPositionX() {
		return positionX;
	}

	/**
	 * @param positionX
	 *            the positionX to set
	 */
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	/**
	 * @return the positionY
	 */
	public int getPositionY() {
		return positionY;
	}

	/**
	 * @param positionY
	 *            the positionY to set
	 */
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	/**
	 * @return the range
	 */
	public int getRange() {
		return range;
	}

	/**
	 * @param range
	 *            the range to set
	 */
	public void setRange(int range) {
		this.range = range;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * CONSTRUCTORS
	 */

	/**
	 * @param positionX
	 * @param positionY
	 * @param range
	 * @param id
	 */
	public Item(int positionX, int positionY, int range, int id) {
		super();
		this.positionX = positionX;
		this.positionY = positionY;
		this.range = range;
		this.id = id;
	}

	private double distanceCalculation(Item itemArgument) {
		System.out.println("DISTANCE CALCULATION:");

		Class<?> cls = null;
		String tempText = "";
		Item tempItem = null;

		for (int j = 0; j < 2; ++j) {
			if (j == 0)
				tempItem = this;
			else if (j == 1)
				tempItem = itemArgument;

			for (int i = 0; i < 2; ++i) {
				if (i == 0) {
					cls = Bomb.class;
					tempText = "Bomb";
				} else if (i == 1) {
					cls = Sapper.class;
					tempText = "Sapper";
				}
				if (cls.isInstance(tempItem)) {
					System.out.println(tempText + " with id: " + tempItem.id + " and position: [" + tempItem.getPositionX() + "][" + tempItem.getPositionY() + "]");
				}
			}
		}
		
		double tempDistance = Math.sqrt(Math.pow(this.getPositionX() - itemArgument.getPositionX(), 2)
				+ Math.pow(this.getPositionY() - itemArgument.getPositionY(), 2));
		System.out.println("Distance is: " + tempDistance);
		return tempDistance;
	}

	/**
	 * Sprawdza czy saper jest w zasi�gu ra�enia bomby.
	 */

	public boolean checkItemsRange(Item itemArgument) {

		if (distanceCalculation(itemArgument) <= this.getRange() + itemArgument.getRange()) {

			System.out.println("Items are in their range!");
			return true;
		} else {
			System.out.println("Items are NOT in their range!");
			return false;
		}
	}

}