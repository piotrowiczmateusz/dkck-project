package dkck;

import java.util.Timer;
import java.util.TimerTask;

import dkck.GUI.MainWindow;

public class BombTimer extends TimerTask {
	Item itemReference;

	private Timer timer1;

	/**
	 * @param itemReference
	 */
	public BombTimer(Item itemReference, int miliseconds) {
		super();
		this.itemReference = itemReference;
		timer1 = new Timer();
		timer1.schedule(this, 0, miliseconds);
	}

	public void run() {

		if (itemReference instanceof Bomb && (itemReference instanceof Rocket == false)) {
			Bomb tempBombReference = ((Bomb) itemReference);
			if (tempBombReference.getExplosionLeftTime() > 0) {
				tempBombReference.setExplosionLeftTime(tempBombReference.getExplosionLeftTime() - 1);
				tempBombReference.getTimerLog().setText("Bomb nr: " + tempBombReference.getId() + " has: "
						+ tempBombReference.getExplosionLeftTime() + " seconds left to explosion");
			} else {
				tempBombReference.explode();
			}

		} else {
			MainWindow.updateLog("Wrong timer argument!!!");
			this.cancel();
		}
	}
}