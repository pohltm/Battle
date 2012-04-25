
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


/**
 * TODO Put here a description of what this class does.
 *
 * @author plungicb.
 *         Created Apr 19, 2012.
 */
public class BattleshipWeek2Tests {

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void TestThatGameBoardConstructsGrids(){
		GameBoard g = new GameBoard(10,10,5);
		Assert.assertNotNull(g.getTopGrid());
		Assert.assertNotNull(g.getBottomGrid());
	}
	
	@Test
	public void TestThatCheckerReturnsValidOnValidPlacement(){
		GameBoard g = new GameBoard(10,10,5);
		ArrayList<Ship> ships = new ArrayList<Ship>();
		Ship s = new Ship(5,5,2,true);
		ships.add(s);
		Assert.assertTrue(g.checkAndPlaceShips(ships));
	}
	
	@Test
	public void TestThatCheckerReturnsInvalidOnInvalidPlacementWithNegativeRowOrigin(){
		GameBoard g = new GameBoard(10,10,5);
		ArrayList<Ship> ships = new ArrayList<Ship>();
		Ship s = new Ship(-1,1,2,true);
		ships.add(s);
		Assert.assertFalse(g.checkAndPlaceShips(ships));
	}
	
	@Test
	public void TestThatCheckerReturnsInvalidOnInvalidPlacementWithNegativeColumnOrigin(){
		GameBoard g = new GameBoard(10,10,5);
		ArrayList<Ship> ships = new ArrayList<Ship>();
		Ship s = new Ship(1,-1,2,true);
		ships.add(s);
		Assert.assertFalse(g.checkAndPlaceShips(ships));
	}

	@Test
	public void TestThatCheckerReturnsInvalidOnInvalidPlacementWithNegativeLength(){
		GameBoard g = new GameBoard(10,10,5);
		ArrayList<Ship> ships = new ArrayList<Ship>();
		Ship s = new Ship(1,1,-1,true);
		ships.add(s);
		Assert.assertFalse(g.checkAndPlaceShips(ships));
	}
	
	@Test
	public void TestThatCheckerReturnsInvalidOnInvalidPlacementWithZeroLength(){
		GameBoard g = new GameBoard(10,10,5);
		ArrayList<Ship> ships = new ArrayList<Ship>();
		Ship s = new Ship(1,1,0,true);
		ships.add(s);
		Assert.assertFalse(g.checkAndPlaceShips(ships));
	}
	
	@Test
	public void TestThatCheckerReturnsInvalidOnInvalidPlacementWithOverstretchHorizontal(){
		GameBoard g = new GameBoard(10,10,5);
		ArrayList<Ship> ships = new ArrayList<Ship>();
		Ship s = new Ship(3,7,4,true);
		ships.add(s);
		Assert.assertFalse(g.checkAndPlaceShips(ships));
	}
	
	@Test
	public void TestThatCheckerReturnsInvalidOnInvalidPlacementWithOverstretchVertical(){
		GameBoard g = new GameBoard(10,10,5);
		ArrayList<Ship> ships = new ArrayList<Ship>();
		Ship s = new Ship(3,7,8,false);
		ships.add(s);
		Assert.assertFalse(g.checkAndPlaceShips(ships));
	}
	
	@Test
	public void TestThatCheckerReturnsValidOnValidPlacementWithShipOnEdge(){
		GameBoard g = new GameBoard(10,10,5);
		ArrayList<Ship> ships = new ArrayList<Ship>();
		Ship s = new Ship(0,0,3,true);
		ships.add(s);
		Assert.assertTrue(g.checkAndPlaceShips(ships));
	}
	
	@Test
	public void TestThatCheckerReturnsValidOnValidPlacementWithLongestShipHorizontal(){
		GameBoard g = new GameBoard(10,10,5);
		ArrayList<Ship> ships = new ArrayList<Ship>();
		Ship s = new Ship(3,0,10,true);
		ships.add(s);
		Assert.assertTrue(g.checkAndPlaceShips(ships));
	}
	
	@Test
	public void TestThatCheckerReturnsValidOnValidPlacementWithLongestShipVertical(){
		GameBoard g = new GameBoard(10,10,5);
		ArrayList<Ship> ships = new ArrayList<Ship>();
		Ship s = new Ship(0,3,10,false);
		ships.add(s);
		Assert.assertTrue(g.checkAndPlaceShips(ships));
	}
	
	@Test
	public void TestThatCheckerReturnsValidOnValidPlacementWith2ValidShips(){
		GameBoard g = new GameBoard(10,10,5);
		ArrayList<Ship> ships = new ArrayList<Ship>();
		Ship s1 = new Ship(3,3,3,true);
		Ship s2 = new Ship(4,6,2,true);
		ships.add(s1);
		ships.add(s2);
		Assert.assertTrue(g.checkAndPlaceShips(ships));
	}
	
	@Test
	public void TestThatCheckerReturnsValidOnValidPlacementWithCloseShips(){
		GameBoard g = new GameBoard(10,10,5);
		ArrayList<Ship> ships = new ArrayList<Ship>();
		Ship s1 = new Ship(3,3,3,true);
		Ship s2 = new Ship(4,3,3,false);
		ships.add(s1);
		ships.add(s2);
		Assert.assertTrue(g.checkAndPlaceShips(ships));
	}
	
	@Test
	public void TestThatCheckerReturnsInvalidOnInvalidPlacementWithOverlapParallel(){
		GameBoard g = new GameBoard(10,10,5);
		ArrayList<Ship> ships = new ArrayList<Ship>();
		Ship s1 = new Ship(1,1,3,true);
		Ship s2 = new Ship(1,3,3,true);
		ships.add(s1);
		ships.add(s2);
		Assert.assertFalse(g.checkAndPlaceShips(ships));
	}
	
	@Test
	public void TestThatCheckerReturnsInvalidOnInvalidPlacementWithOverlapPerpendicular(){
		GameBoard g = new GameBoard(10,10,5);
		ArrayList<Ship> ships = new ArrayList<Ship>();
		Ship s1 = new Ship(2,2,5,true);
		Ship s2 = new Ship(1,4,3,false);
		ships.add(s1);
		ships.add(s2);
		Assert.assertFalse(g.checkAndPlaceShips(ships));
	}
	
	@Test
	public void TestThatPlaceShipsPlacesShips(){
		GameBoard g = new GameBoard(10,10,5);
		ArrayList<Ship> ships = new ArrayList<Ship>();
		Ship s1 = new Ship(3,3,3,true);
		Ship s2 = new Ship(4,6,2,true);
		ships.add(s1);
		ships.add(s2);
		g.checkAndPlaceShips(ships);
		for(int r=0;r<g.getHeight();r++){
			for(int c=0;c<g.getWidth();c++){
				if((r == 3) && (c==3) || (r==3) && (c==4) || (r==3) && (c==5) || (r==4) && (c==6) || (r==4) && (c==7)){
					Assert.assertTrue(g.getBottomGrid().getGrid()[r][c] instanceof ShipCell);
				}
				else{
					Assert.assertTrue(g.getBottomGrid().getGrid()[r][c] instanceof Empty);
				}
			}
		}
		System.out.println(g.toString());
	}
	
}
