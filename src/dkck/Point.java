/**
 * 
 */
package dkck;

/**
 * @author Dariusz
 *
 */
public class Point extends Item {
	
	
	public static int id = 0;

	/**
	 * @param positionX
	 * @param positionY
	 */
	public Point(int positionX, int positionY) {
		super(positionX, positionY, 0, id++, 0);
		
		// TODO Auto-generated constructor stub
	}

}
