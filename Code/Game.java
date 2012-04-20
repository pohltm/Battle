
/**
 * TODO Put here a description of what this class does.
 *
 * @author plungicb.
 *         Created Apr 19, 2012.
 */
public class Game {

	private int width;
	private int height;
	private int numShips;
	
	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param w
	 * @param h
	 * @param n
	 */
	public Game(int w, int h, int n) {
		this.width = w;
		this.height = h;
		this.numShips = n;
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public int getNumberOfShips() {
		return this.numShips;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
}
