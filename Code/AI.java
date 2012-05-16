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
	private ArrayList<Shot> shots;
	
    public AI(GameBoard gb, int[] lengths){
		this.gb = gb;
		this.lengths = lengths;
		this.ships = new ArrayList<Ship>();
		this.shots = new ArrayList<Shot>();
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
			clearShip(this.shots.get(shots.size()-1).getS());
			this.shoot();
		}else if(shot.equals("Hit")){
			Ship tempS = ((Hit)this.gb.getBottomGrid().getGrid()[r][c]).getS();
			if(shots.isEmpty()){
				shots.add(new Shot(r,c+1,tempS));
				shots.add(new Shot(r+1,c,tempS));
				shots.add(new Shot(r,c-1,tempS));
				shots.add(new Shot(r-1,c,tempS));
			}else{
				shots.add(new Shot(r,c+1,tempS));
				shots.add(new Shot(r+1,c,tempS));
				shots.add(new Shot(r,c-1,tempS));
				shots.add(new Shot(r-1,c,tempS));
				if(c-1 >=0 && this.gb.getBottomGrid().getGrid()[r][c-1] instanceof Hit){
					shots.add(new Shot(r,c+1,tempS));
					int x=c-2;
					while(x>=0 && this.gb.getBottomGrid().getGrid()[r][x] instanceof Hit){
						x--;
					}
					shots.add(new Shot(r,x,tempS));
				}
				if(c+1 < this.gb.getWidth() && this.gb.getBottomGrid().getGrid()[r][c+1] instanceof Hit){
					shots.add(new Shot(r,c-1,tempS));
					int x=c+2;
					while(x < this.gb.getWidth() && this.gb.getBottomGrid().getGrid()[r][x] instanceof Hit){
						x++;
					}
					shots.add(new Shot(r,x,tempS));
				}
				if(r-1 >=0 && this.gb.getBottomGrid().getGrid()[r-1][c] instanceof Hit){
					shots.add(new Shot(r+1,c,tempS));
					int x=r-2;
					while(x>=0 && this.gb.getBottomGrid().getGrid()[x][c] instanceof Hit){
						x--;
					}
					shots.add(new Shot(x,c,tempS));
				}
				if(r+1 < this.gb.getHeight() && this.gb.getBottomGrid().getGrid()[r+1][c] instanceof Hit){
					shots.add(new Shot(r-1,c,tempS));
					int x=r+2;
					while(x < this.gb.getHeight() && this.gb.getBottomGrid().getGrid()[x][c] instanceof Hit){
						x++;
					}
					shots.add(new Shot(x,c,tempS));
				}
			}	
		}else{
			if(!shots.isEmpty()){
				shots.remove(shots.size()-1);
			}
		}
	}
	
	private void clearShip(Ship s){
		for(int n=0;n<shots.size();n++){
			if(shots.get(n).getS() == s){
				System.out.println("good");
				shots.remove(n);
				n--;
			}
		}
	}
	
}
