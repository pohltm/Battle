
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
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param w
	 * @param h
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
	 * TODO Put here a description of what this method does.
	 *
	 * @return grid;
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

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param w
	 * @param h
	 */
	public void shoot(int w, int h) {
		if(this.grid[w][h] instanceof Empty){
			this.grid[w][h] = new Miss();
		}
		
	}

}
