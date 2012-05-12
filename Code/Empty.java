
/**
 * TODO Put here a description of what this class does.
 *
 * @author plungicb.
 *         Created Apr 19, 2012.
 */
public class Empty implements IGridCell {

	private int r, c;
	
	@Override
	public String toString(){
		return " ";
	}
	
	public Empty (int r, int c){
		this.r = r;
		this.c = c;
	}

	public int getR() {
		return r;
	}

	public int getC() {
		return c;
	}
	
	
	
}
