
/**
 * TODO Put here a description of what this class does.
 *
 * @author plungicb.
 *         Created Apr 19, 2012.
 */
public class ShipCell implements IGridCell {
	
	private Ship s;
	
	public ShipCell(Ship s){
		this.s = s;
	}

	public Ship getS() {
		return s;
	}
}
