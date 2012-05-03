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
	
	public GameBoard (){
		this(10,10,5);
	}

	private void makeGrids()
	{
		this.makeTopGrid();
		this.makeBottomGrid();
	}
	
	private void makeTopGrid(){
		this.topGrid = new Grid(this.width, this.height);
	}
	
	private void makeBottomGrid(){
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
	public Grid getTopGrid() {
		return this.topGrid;
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return bottom grid
	 */
	public Grid getBottomGrid() {
		return this.bottomGrid;
	}

	public String toString(){
		return "Top Grid:\n" + this.topGrid.toString() + "\nBottom Grid:\n" + this.bottomGrid.toString() + "\n";
	}
	
	/**
	 * Makes sure placements are valid. Also adds ships to the bottom grid.
	 *
	 * @param ships
	 * @return whether placement is valid
	 */
	public boolean checkAndPlaceShips(ArrayList<Ship> ships, String grid) {
		boolean result = true;;
		
		if(ships.size() > this.numShips){
			return false;
		}
		
		for(Ship s : ships){
			result = result && shipFits(s);
			
			if(!result){
				return result;
			}
			
			if(grid.equals("top")){
				result = result && this.getTopGrid().place(s);
			}
			else{
				result = result && this.getBottomGrid().place(s);
			}
			
			if(!result){
				if(grid.equals("top")){
					this.makeTopGrid();
				}
				else{
					this.makeBottomGrid();
				}
				return result;
			}
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

	public boolean isEmpty() {
		return (this.bottomGrid.isEmpty() && this.topGrid.isEmpty());
	}
	
	
}
