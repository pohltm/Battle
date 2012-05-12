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
	private ArrayList<Empty> shots;
	
    public AI(GameBoard gb, int[] lengths){
		this.gb = gb;
		this.lengths = lengths;
		this.ships = new ArrayList<Ship>();
		this.shots = new ArrayList<Empty>();
	}


	public void placeShips() {
		int r, c, s;
		boolean d;
		this.ships = new ArrayList<Ship>();
		for(int n=0;n<this.gb.getNumberOfShips();n++){
			r = (int)(((double)this.gb.getHeight())*Math.random());
			c = (int)(((double)this.gb.getWidth())*Math.random());
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
		int r, c;
		System.out.println(shots.toString());
		if(shots.isEmpty()){
			r = (int) (Math.random()*((double)gb.getHeight()));
			c = (int) (Math.random()*((double)gb.getWidth()));
		}
		else{
			r = shots.get(shots.size()-1).getR();
			c = shots.get(shots.size()-1).getC();
		}
		String shot = gb.shootBottom(r,c);
		if(shot.equals("Invalid")){
			if(!shots.isEmpty()){
				shots.remove(shots.size()-1);
			}
			this.shoot();
		}else if(shot.equals("Sink")){
			shots.clear();
			this.shoot();
		}else if(shot.equals("Hit")){
			if(shots.isEmpty()){
				shots.add(new Empty(r,c+1));
				shots.add(new Empty(r+1,c));
				shots.add(new Empty(r,c-1));
				shots.add(new Empty(r-1,c));
			}else{
				shots.add(new Empty(r,c+1));
				shots.add(new Empty(r+1,c));
				shots.add(new Empty(r,c-1));
				shots.add(new Empty(r-1,c));
			}	
		}else{
			if(!shots.isEmpty()){
				shots.remove(shots.size()-1);
			}
		}
	}
	
}
