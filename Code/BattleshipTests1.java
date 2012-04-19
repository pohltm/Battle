import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;


public class BattleshipTests1 {
	
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
	
//	@Test
//	public void NegativeWGridConstructorTests() throws Exception{
//		try{
//			Grid g = new Grid(-1,10);
//			Assert.fail();
//		}catch(Exception e){
//			Assert.assertTrue(true);
//		}
//	}
//	
//	@Test
//	public void NegativeHGridConstructorTests() throws Exception{
//		try{
//			Grid g = new Grid(10,-1);
//			Assert.fail();
//		}catch(Exception e){
//			Assert.assertTrue(true);
//		}
//	}
//	
//	@Test
//	public void ZeroWGridConstructorTests() throws Exception{
//		try{
//			Grid g = new Grid(0,10);
//			Assert.fail();
//		}catch(Exception e){
//			Assert.assertTrue(true);
//		}
//	}
//	
//	@Test
//	public void ZeroHGridConstructorTests() throws Exception{
//		try{
//			Grid g = new Grid(10,0);
//			Assert.fail();
//		}catch(Exception e){
//			Assert.assertTrue(true);
//		}
//	}
//	
//	@Test
//	public void TooWideGridConstructorTests() throws Exception{
//		try{
//			Grid g = new Grid(101,10);
//			Assert.fail();
//		}catch(Exception e){
//			Assert.assertTrue(true);
//		}
//	}
//	
//	@Test
//	public void TooTallGridConstructorTests() throws Exception{
//		try{
//			Grid g = new Grid(10,101);
//			Assert.fail();
//		}catch(Exception e){
//			Assert.assertTrue(true);
//		}
//	}
	
	@Test
	public void ShipConstructorTests() throws Exception{
		Ship s = new Ship(3);
		Assert.assertNotNull(s);
	}
	
	@Test
	public void NormalShipConstructorTests() throws Exception{
		Ship s = new Ship(3);
		Assert.assertEquals(s.getSize(), 3);
	}
	
	@Test
	public void SmallestShipConstructorTests() throws Exception{
		Ship s = new Ship(1);
		Assert.assertEquals(s.getSize(), 1);
	}
	
	@Test
	public void LargestShipConstructorTests() throws Exception{
		Ship s = new Ship(100);
		Assert.assertEquals(s.getSize(), 100);
	}
	
	@Test
	public void GameBoardConstructorTests() throws Exception{
		GameBoard gb = new GameBoard();
		Assert.assertNotNull(gb);
	}
	
	@Test
	public void PlayerConstructorTests() throws Exception{
		Player p = new Player();
		Assert.assertNotNull(p);
	}
	
	@Test
	public void AIConstructorTests() throws Exception{
		AI ai = new AI();
		Assert.assertNotNull(ai);
	}

}
