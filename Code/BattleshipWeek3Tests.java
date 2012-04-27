import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;


public class BattleshipWeek3Tests {

	@Test
	public void testIsGridEmpty() {
		Grid g = new Grid(10,10);
		Assert.assertTrue(g.isEmpty());
	}
	
	@Test
	public void testIsGameBoardEmpty(){
		GameBoard gb = new GameBoard(10,10,5);
		Assert.asserTrue(gb.isEmpty());
	}

	@Test
	public void testThatGameBoardIsNotEmptyAfterAddingShips(){
		GameBoard gb = new GameBoard(10,10,2);
		ArrayList<Ship> ships = new ArrayList<Ship>();
		ships.add(new Ship(3,3,3,true));
		ships.add(new Ship(4,6,2,true);
		gb.checkAndPlaceShips(ships);
		Assert.assertFalse(gb.isEmpty);
	}
	
	@Test
	public void testThatMakesSureShipsCanBeAddedUsingMultipleCallsToCheckAndPlaceShips(){
		GameBoard gb = new GameBoard(10,10,2);
		ArrayList<Ship> ships = new ArrayList<Ship>();
		ships.add(new Ship(3,3,3,true));
		Assert.assertTrue(gb.checkAndPlaceShips(ships));
		ships.removeAll(ships);
		ships.add(new Ship(4,6,2,true));
		Assert.assertTrue(gb.checkAndPlaceShips(ships));
	}
	
	@Test
	public void testThatMakesSureMoreShipsThanAllowedCanNotBePlaced(){
		GameBoard gb = new GameBoard(10,10,2);
		ArrayList<Ship> ships = new ArrayList<Ship>();
		ships.add(new Ship(3,3,3,true));
		ships.add(new Ship(4,6,2,true));
		Assert.assertTrue(gb.checkAndPlaceShips(ships));
		ships.removeAll(ships);
		ships.add(new Ship(1,1,5,false));
		Assert.assertFalse(gb.checkAndPlaceShips(ships));
	}
	
	@Test
	public void testThatDefaultConstructorForGameBoardInitializesCorrectly(){
		GameBoard gb = new GameBoard();
		Assert.assertEquals(10, gb.getWidth());
		Assert.assertEquals(10,gb.getHeight());
		Assert.assertEquals(5, gb.getNumberOfShips());
	}
	
	@Test
	public void testThatAIConstructsProperly(){
		GameBoard gb = new GameBoard();
		AI ai = new AI(gb);
		Assert.assertNotNull(ai);
	}
	
	@Test
	public void testThatAIPlacesShips(){
		GameBoard gb = new GameBoard();
		AI ai = new AI(gb);
		ai.placeShips();
		Boolean result = false;
		
		for(int r = 0; r < gb.getHeight(); r++){
			for(int c = 0; c < gb.getWidth(); c++){
				result = result || !(gb.getTopGrid()[r][c] instanceof ShipCell); 
			}
		}
		
		Assert.assertTrue(result);
	}
	
	@Test
	public void testThatAIShoots(){
		GameBoard gb = new GameBoard();
		ArrayList<Ship> ships = new ArrayList<Ship>();
		ships.add(new Ship(3,3,3,true));
		ships.add(new Ship(4,6,2,true));
		ships.add(new Ship(1,1,5,false));
		gb.checkAndPlaceShips(ships);
		AI ai = new AI(gb);
		ai.placeShips();
		Boolean result = false;
		
		for(int r = 0; r < gb.getHeight(); r++){
			for(int c = 0; c < gb.getWidth(); c++){
				result = result || (gb.getBottomGrid()[r][c] instanceof Hit) || (gb.getBottomGrid()[r][c] instanceof Miss); 
			}
		}
		
		Assert.assertTrue(result);
	}
	
}
