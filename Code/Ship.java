
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
	private int hits;
	private boolean sunk;
	
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
		this.hits = 0;
		this.sunk = false;
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

	public boolean shootShip(int row, int col)
	{
		if(horizontal)
		{
			if(this.r == row && this.c <= col && this.c + size > col)
			{
				this.hits++;
				return true;
			}
		}
		else
		{
			if(this.c == col && this.r <= row && this.r + size > row)
			{
				this.hits++;
				return true;
			}
		}
		return false;
	}
	
	public boolean justSunk(){
		return this.hits == this.size && !this.sunk;
	}
	
	public void setSunk(boolean s){
		this.sunk = s;
	}
	
}
