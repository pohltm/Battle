import java.util.ArrayList;


/**
 * TODO Put here a description of what this class does.
 *
 * @author plungicb.
 *         Created Apr 19, 2012.
 */
public class AI {
	
	private GameBoard gb;
	private ArrayList<Ship> ships;
	private int[] lengths;
	
	public AI(GameBoard gb, int[] lengths){
		this.gb = gb;
		this.lengths = lengths;
		this.ships = new ArrayList<Ship>();
	}


	public void placeShips() {
		int r, c, s;
		boolean d;
		this.ships = new ArrayList<Ship>();
		for(int n=0;n<this.gb.getNumberOfShips();n++){
			r = (int)(this.gb.getHeight()*Math.random());
			c = (int)(this.gb.getWidth()*Math.random());
			d = Math.random() < 0.5;
			s = this.lengths[n];
			this.ships.add(new Ship(r,c,s,d));
		}
		if(!gb.checkAndPlaceShips(this.ships, "top")){
			this.ships = new ArrayList<Ship>();
			this.placeShips();
		}
	}


	public void shoot() {
		int r = (int) Math.random()*gb.getHeight();
		int c = (int) Math.random()*gb.getWidth();
		if(gb.getBottomGrid().shoot(r, c)){
			this.shoot();
		}
	}
	
}
