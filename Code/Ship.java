
/**
 * TODO Put here a description of what this class does.
 *
 * @author plungicb.
 *         Created Apr 19, 2012.
 */
public class Ship {

	private int c;
	private int r;
	private int size;
	private boolean horizontal;
	
	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param s
	 */
	public Ship(int r, int c, int s, boolean horizontal) {
		this.size = s;
		this.c = c;
		this.r = r;
		this.size = s;
		this.horizontal = horizontal;
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return size of the ship
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * TODO Put here a description of what this method does
	 *
	 * @return x coordinate
	 */
	public int getRow() {
		return this.r;
	}
	
	/**
	 *  TODO Put here a description of what this method does.
	 * 
	 * @return y coordinate
	 */
	public int getCol() {
		return this.c;
	}
	
	/**
	 *  TODO Put here a description of what this method does.
	 * 
	 * @return horizontal?
	 */
	public boolean isHorizontal() {
		return this.horizontal;
	}

}
