
/**
 * TODO Put here a description of what this class does.
 *
 * @author plungicb.
 *         Created Apr 19, 2012.
 */
public class Grid {

	private int width;
	private int height;
	private IGridCell[][] grid;
	
	/**
	 * Constructs a new Grid object
	 *
	 * @param width of grid
	 * @param height of grid
	 */
	public Grid(int w, int h) {
		this.width = w;
		this.height = h;
		this.grid = new IGridCell[w][h];
		this.initializeGrid();
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return width of the grid
	 */
	public Object getWidth() {
		return this.width;
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return height of the grid
	 */
	public Object getHeight() {
		return this.height;
	}

	/**
	 * returns IGridCell[][] that is the grid.
	 *
	 * @return grid
	 */
	public IGridCell[][] getGrid() {
		return this.grid;
	}
	
	public void initializeGrid(){
		for(int i = 0; i < this.width; i++){
			for(int j = 0; j < this.height; j++){
				this.grid[i][j] = new Empty();
			}
		}
	}
	
	public String toString(){
		String ret = "";
		for(int i = 0; i < this.width; i++){
			for(int j = 0; j < this.height; j++){
				ret+= "[" + this.grid[i][j].toString() + "] ";
			}
			ret += "\n";
		}
		return ret;
	}

	/**
	 * TODO Put here a description of what this method does.
	 * @param r 
	 * @param c 
	 */
	public void shoot(int r, int c) {
		if(this.grid[r][c] instanceof Empty){
			this.grid[r][c] = new Miss();
		}
		else if(this.grid[r][c] instanceof ShipCell){
			this.grid[r][c] = new Hit();
		}
	}
	
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param ship
	 * @return whether the ship was placed successfully. False = overlap of ships.
	 */
	public boolean place(Ship ship)
	{
		int i = 0;
		int r = ship.getRow();
		int c = ship.getCol();
		while (i<ship.getSize()){
			if(this.grid[r][c] instanceof ShipCell){
				return false;
			}
			this.grid[r][c] = new ShipCell(ship);
			if(ship.isHorizontal()){
				c++;
			}
			else{
				r++;
			}
			i++;
		}
		return true;
	}
}
