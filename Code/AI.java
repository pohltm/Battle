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
	
	public AI(GameBoard gb){
		this.gb = gb;
		this.ships = new ArrayList<Ship>();
	}


	public void placeShips() {
		int r, c, s;
		boolean d;
		for(int n=0;n<gb.getNumberOfShips();n++){
			r = (int)(gb.getHeight()*Math.random());
			c = (int)(gb.getWidth()*Math.random());
			d = Math.random() < 0.5;
			s = (int)(Math.random()*5.0);
			ships.add(new Ship(r,c,s,d));
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
