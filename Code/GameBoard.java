
/**
 * TODO Put here a description of what this class does.
 *
 * @author plungicb.
 *         Created Apr 19, 2012.
 */
public class GameBoard {
	
	private int width;
	private int height;
	private int numShips;
	private Grid ships;
	private Grid shots;
	
	public GameBoard (int w, int h, int n){
		this.width = w;
		this.height = h;
		this.numShips = n;
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return Number of Ships on the GameBoard
	 */
	public int getNumberOfShips() {
		return this.numShips;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public int getWidth(){
		return this.width;
	}
}
