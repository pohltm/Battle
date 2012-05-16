
/**
 * TODO Put here a description of what this class does.
 *
 * @author hoorncj.
 *         Created May 15, 2012.
 */
public class Shot implements IGridCell {

	private int r, c;
	private Ship s;
	
	@Override
	public String toString(){
		return s.toString();
	}
	
	public Shot(int r, int c, Ship s){
		this.r = r;
		this.c = c;
		this.s = s;
	}

	public int getR() {
		return r;
	}

	public int getC() {
		return c;
	}
	
	public Ship getS(){
		return s;
	}

}
