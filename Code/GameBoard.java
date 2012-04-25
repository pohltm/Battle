import java.util.ArrayList;


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

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param ships
	 * @return whether placement is valid
	 */
	public boolean areValidPlacements(ArrayList<Ship> ships) {
		boolean result = true;
		
		for(Ship s : ships){
			result = result && shipFits(s);
		}
		
		return result;
	}
	
	private boolean shipFits(Ship s){
		boolean fits = s.getSize() > 0 && s.getRow() >= 0 && s.getCol() >= 0 && s.getCol() < this.width && s.getRow() < this.height;
		
		if(s.isHorizontal()){
			fits = fits && s.getCol() + s.getSize() <= this.width;
		}else{
			fits = fits && s.getRow() + s.getSize() <= this.height;
		}
		
		return fits;
	}
}
