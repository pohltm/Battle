
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
	private Grid topGrid;
	private Grid bottomGrid;
	
	public GameBoard (int w, int h, int n){
		this.width = w;
		this.height = h;
		this.numShips = n;
		this.makeGrids();
	}

	private void makeGrids()
	{
		this.topGrid = new Grid(this.width, this.height);
		this.bottomGrid = new Grid(this.width, this.height);
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

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return top grid
	 */
	public Object getTopGrid() {
		return this.topGrid;
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return bottom grid
	 */
	public Object getBottomGrid() {
		return this.bottomGrid;
	}
}
