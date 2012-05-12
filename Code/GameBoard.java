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
	private ArrayList<Ship> topShips;
	private ArrayList<Ship> bottomShips;
	
	public GameBoard (int w, int h, int n){
		if(w<=0 || h<=0 || n<=0){
			throw new NegativeArraySizeException();
		}
		this.width = w;
		this.height = h;
		this.numShips = n;
		this.makeGrids();
		this.topShips = new ArrayList<Ship>();
		this.bottomShips = new ArrayList<Ship>();
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
		boolean result = true;
		
		if(ships.size() != this.numShips){
			return false;
		}
		
		for(Ship s : ships){
			result = result && shipFits(s);
			
			if(grid.equals("top")){
				result = result && this.getTopGrid().place(s);
				this.topShips.add(s);
			}
			else{
				result = result && this.getBottomGrid().place(s);
				this.bottomShips.add(s);
			}
			
			if(!result){
				if(grid.equals("top")){
					this.makeTopGrid();
					this.topShips = new ArrayList<Ship>();
				}
				else{
					this.makeBottomGrid();
					this.bottomShips = new ArrayList<Ship>();
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
	
	public boolean playerWon(){
		boolean ret = true;
		for (Ship s : this.topShips){
			ret = ret && s.isSunk();
		}
		return ret;
	}
	
	public boolean AIWon(){
		boolean ret = true;
		for (Ship s : this.bottomShips){
			ret = ret && s.isSunk();
		}
		return ret;
	}
	
	public boolean checkShotTop(int row, int col)
	{
		return this.topGrid.getGrid()[row][col] instanceof Empty || this.topGrid.getGrid()[row][col] instanceof ShipCell;
	}
	
	public boolean checkShotBottom(int row, int col)
	{
		return this.bottomGrid.getGrid()[row][col] instanceof Empty || this.bottomGrid.getGrid()[row][col] instanceof ShipCell;
	}
	
	
	public boolean shootTop(int row, int col){
		boolean ret = false;
		this.topGrid.shoot(row, col);
		for (Ship s : this.topShips){
			s.shootShip(row, col);
			if(s.justSunk()){
				s.setSunk(true);
				ret = true;
			}
		}
		return ret;
	}
	
	public String shootBottom(int row, int col){
		if(row<0 || col<0 || row>=this.height || col>=this.width){
			return "Invalid";
		}
		if(!this.bottomGrid.shoot(row, col)){
			return "Invalid";
		}
		for(Ship s : this.bottomShips){
			s.shootShip(row, col);
			if(s.justSunk()){
				s.setSunk(true);
				return "Sink";
			}
		}
		if(this.bottomGrid.getGrid()[row][col] instanceof Hit){
			return "Hit";
		}else{
			return "Miss";
		}
	}
}
