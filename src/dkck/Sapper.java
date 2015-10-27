package dkck;

import java.util.Random;

public class Sapper extends Item {

	/**
	 * ATTRIBUTES
	 */
	private int healthPoints;

	private boolean SapperStatus;

	/**
	 * SETTERS AND GETTERS
	 */

	/**
	 * @return the status
	 */
	public int getHealthPoints() {
		return healthPoints;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	/**
	 * @return the sapperStatus
	 */
	public boolean getSapperStatus() {
		return SapperStatus;
	}

	/**
	 * @param sapperStatus
	 *            the sapperStatus to set
	 */
	public void setSapperStatus(boolean sapperStatus) {
		SapperStatus = sapperStatus;
	}

	/**
	 * CONSTRUCTORS
	 */

	public Sapper(int positionX, int positionY, int range, int id) {
		super(positionX, positionY, range, id);
		this.setSapperStatus(true);
		this.setHealthPoints(2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * OTHER METHODS
	 */

	/**
	 * Zmienia pozycje sapera i sprawdza czy znajduje si� w zasi�gu ra�enia
	 * bomby.
	 */
	public void go(int x, int y) throws InterruptedException {
		while ((x != this.getPositionX()) || (y != this.getPositionY())) {
			if (x > this.getPositionX()) {
				this.setPositionX(this.getPositionX() + 1);
			} else if (x < this.getPositionX()) {
				this.setPositionX(this.getPositionX() - 1);
			}
			if (y > this.getPositionY()) {
				this.setPositionY(this.getPositionY() + 1);
			} else if (y < this.getPositionY()) {
				this.setPositionY(this.getPositionY() - 1);
			}
			System.out.println("Sapper position is: [" + this.getPositionX() + "][" + this.getPositionY() + "]");

			for (int i = 0; i < Main.itemsList.getItemsArray().size(); i++) {
				Item tempItem = Main.itemsList.getItemsArray().get(i);
				if (tempItem instanceof Bomb)
					((Bomb) Main.itemsList.getItemsArray().get(i)).checkExplosionRange(this);
			}

			// Tu b�dzie metoda rysuj�ca sapera na mapie;

			Thread.sleep(1000);
		}
	}

	/**
	 * Saper idzie na pozycj� bomby. Nastp�nie na pozycje x,y i zmienia pozycj�
	 * bomby.
	 */
	public void moveBomb(Bomb bomb, int x, int y) throws InterruptedException {
		System.out.println("The sapper will try to move bomb nr: " + bomb.getId());
		this.go(bomb.getPositionX(), bomb.getPositionY());
		System.out.println("The sapper picked up a bomb.");
		this.go(x, y);
		bomb.setPositionX(x);
		bomb.setPositionY(y);
		System.out.println("The bomb was moved to [" + x + "][" + y + "]");
	}

	/**
	 * Wywo�uje metod� 'go', sprawdza status bomby, losowo decyduje, czy bomba
	 * wybuchnie.
	 */
	public void disarmBomb(Bomb bomb) throws InterruptedException {
		go(bomb.getPositionX(), bomb.getPositionY());

		if (bomb.getBombStatus() != 1) {
			System.out.println("The bomb nr: " + bomb.getId() + " was already disarmed");
		} else {
			Random generator = new Random();
			int success = generator.nextInt(2);
			if (success == 1) {
				bomb.setBombStatus(2);
				System.out.println("The bomb nr " + bomb.getId() + " is now disarmed");
			} else {
				bomb.explode(this);
			}
		}
	}
}
