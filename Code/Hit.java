
/**
 * TODO Put here a description of what this class does.
 *
 * @author plungicb.
 *         Created Apr 19, 2012.
 */
public class Hit implements IGridCell {
	
	Ship s;

	@Override
	public String toString(){
		return "H";
	}
	
	public Hit(Ship s){
		this.s = s;
	}
	
	public Hit(){
		this(new Ship(1,1,1,true));
	}
	
	public Ship getS(){
		return this.s;
	}
	
}
