

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


/**
 * TODO Put here a description of what this class does.
 *
 * @author plungicb.
 *         Created Apr 19, 2012.
 */
public class BattleshipWeek1Tests {

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void GridConstructorTests() throws Exception{
		Grid g = new Grid(10,10);
		Assert.assertNotNull(g);
	}

	@Test
	public void NormalGridConstructorTests() {
		Grid g = new Grid(10,10);
		Assert.assertEquals(g.getWidth(), 10);
		Assert.assertEquals(g.getHeight(), 10);
	}
	
	@Test
	public void VerticalGridConstructorTests() {
		Grid g = new Grid(1,10);
		Assert.assertEquals(g.getWidth(), 1);
		Assert.assertEquals(g.getHeight(), 10);
	}
	
	@Test
	public void HorizontalGridConstructorTests() {
		Grid g = new Grid(10,1);
		Assert.assertEquals(g.getWidth(), 10);
		Assert.assertEquals(g.getHeight(), 1);
	}
	
	@Test
	public void SmallestGridConstructorTests() {
		Grid g = new Grid(1,1);
		Assert.assertEquals(g.getWidth(), 1);
		Assert.assertEquals(g.getHeight(), 1);
	}
	
	@Test
	public void WidestGridConstructorTests() {
		Grid g = new Grid(100,10);
		Assert.assertEquals(g.getWidth(), 100);
		Assert.assertEquals(g.getHeight(), 10);
	}
	
	@Test
	public void TallestGridConstructorTests() {
		Grid g = new Grid(10,100);
		Assert.assertEquals(g.getWidth(), 10);
		Assert.assertEquals(g.getHeight(), 100);
	}
	
	@Test
	public void BiggestGridConstructorTests() {
		Grid g = new Grid(100,100);
		Assert.assertEquals(g.getWidth(), 100);
		Assert.assertEquals(g.getHeight(), 100);
	}
	
	@Test
	public void ShipConstructorTests() throws Exception{
		Ship s = new Ship(5,5,3,true);
		Assert.assertNotNull(s);
	}
	
	@Test
	public void NormalShipConstructorTests() throws Exception{
		Ship s = new Ship(5,5,3,true);
		Assert.assertEquals(s.getSize(), 3);
		Assert.assertEquals(s.getRow(), 5);
		Assert.assertEquals(s.getCol(), 5);
		Assert.assertTrue(s.isHorizontal());
	}
	
	@Test
	public void SmallShipConstructorTests() throws Exception{
		Ship s = new Ship(5,5,1,true);
		Assert.assertEquals(s.getSize(), 1);
		Assert.assertEquals(s.getRow(), 5);
		Assert.assertEquals(s.getCol(), 5);
		Assert.assertTrue(s.isHorizontal());
		
	}
	
	@Test
	public void LargeShipConstructorTests() throws Exception{
		Ship s = new Ship(5,5,100,true);
		Assert.assertEquals(s.getSize(), 100);
		Assert.assertEquals(s.getRow(), 5);
		Assert.assertEquals(s.getCol(), 5);
		Assert.assertTrue(s.isHorizontal());
	}
	
	@Test
	public void VerticalShipConstructorTests() throws Exception{
		Ship s = new Ship(5,5,3,false);
		Assert.assertEquals(s.getSize(), 3);
		Assert.assertEquals(s.getRow(), 5);
		Assert.assertEquals(s.getCol(), 5);
		Assert.assertFalse(s.isHorizontal());
	}
	
	
	
	@Test
	public void GameBoardConstructorTests() throws Exception{
		GameBoard gb = new GameBoard(1,1,1);
		Assert.assertNotNull(gb);
	}
	
	@Test
	public void PlayerConstructorTests() throws Exception{
		Player p = new Player();
		Assert.assertNotNull(p);
	}
	
	@Test
	public void AIConstructorTests() throws Exception{
		int[] blank = {5,4,3,2,1};
		AI ai = new AI(new GameBoard(), blank);
		Assert.assertNotNull(ai);
	}
	
	@Test
	public void GameConstructorTests() throws Exception{
		Game game = new Game(2,2,2);
		Assert.assertNotNull(game);
	}
	
	@Test 
	public void testThatGridReturnsAnArray(){
		Grid g = new Grid(10,10);
		IGridCell[][] grid = g.getGrid();
		Assert.assertEquals(10,grid.length);
	}
	
	@Test
	public void testThatGridInitializesCorrectly(){
		Grid g  = new Grid(3,3);
		IGridCell[][] grid = g.getGrid();
		
		for (int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				Assert.assertTrue(grid[i][j] instanceof Empty);
			}
		}
	}
	
	@Test
	public void testThatGameBoardReturnsCorrectNumberOfShips(){
		GameBoard g = new GameBoard(10,10,5);
		Assert.assertEquals(g.getNumberOfShips(),5);
	}
	
	@Test
	public void testThatGameReturnsCorrectNumberOfShips(){
		Game g = new Game(10,10,5);
		Assert.assertEquals(g.getNumberOfShips(),5);
	}
	
	@Test
	public void testThatPlacingValidShipResultsInShipCells(){
		Grid g  = new Grid(10,10);
		IGridCell[][] grid = g.getGrid();
		Ship s = new Ship(5,5,3,true);
		for (int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				Assert.assertTrue(grid[i][j] instanceof Empty);
			}
		}
		g.place(s);
		Assert.assertTrue(grid[5][4] instanceof Empty);
		Assert.assertTrue(grid[5][5] instanceof ShipCell);
		Assert.assertTrue(grid[5][6] instanceof ShipCell);
		Assert.assertTrue(grid[5][7] instanceof ShipCell);
		Assert.assertTrue(grid[5][8] instanceof Empty);
	}
	
	@Test
	public void testThatPlacingInvalidShipResultsInException(){
		Grid g  = new Grid(10,10);
		IGridCell[][] grid = g.getGrid();
		Ship s = new Ship(-1,-1,3,true);
		for (int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				Assert.assertTrue(grid[i][j] instanceof Empty);
			}
		}
		try{
			g.place(s);
			Assert.fail();
		}catch(Exception e){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testThatShootingAnEmptyResultsInMiss(){
		Grid g  = new Grid(10,10);
		IGridCell[][] grid = g.getGrid();
		Assert.assertTrue(grid[3][7] instanceof Empty);
		g.shoot(3,7);
		Assert.assertTrue(grid[3][7] instanceof Miss);
	}
	
	@Test
	public void testThatShootingAShipResultsInHit(){
		Grid g = new Grid(10,10);
		IGridCell[][] grid = g.getGrid();
		Ship s = new Ship(3,7,1,true);
		g.place(s);
		Assert.assertTrue(grid[3][7] instanceof ShipCell);
		g.shoot(3,7);
		Assert.assertTrue(grid[3][7] instanceof Hit);
	}
	
	@Test
	public void testThatShootingAnEmptyLocationAgainHasNoEffect(){
		Grid g  = new Grid(10,10);
		IGridCell[][] grid = g.getGrid();
		Assert.assertTrue(grid[3][7] instanceof Empty);
		g.shoot(3,7);
		Assert.assertTrue(grid[3][7] instanceof Miss);
		g.shoot(3,7);
		Assert.assertTrue(grid[3][7] instanceof Miss);
	}
	
	@Test
	public void testThatShootingAShipCellAgainHasNoEffect(){
		Grid g = new Grid(10,10);
		IGridCell[][] grid = g.getGrid();
		Ship s = new Ship(3,7,1,true);
		g.place(s);
		Assert.assertTrue(grid[3][7] instanceof ShipCell);
		g.shoot(3,7);
		Assert.assertTrue(grid[3][7] instanceof Hit);
		g.shoot(3,7);
		Assert.assertTrue(grid[3][7] instanceof Hit);
	}
}
