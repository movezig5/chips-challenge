package tc;

import static org.junit.Assert.*;

import org.junit.Test;
import java.awt.Point;

import controller.PlayChip;
import model.*;

public class ChipsChallengeTests {
	
	PlayChip game;
	
	@Test
	public void itemTest(){
		game = new PlayChip();
		Chip c = new Chip(0, 0, game);
		c.getFlippers();
		c.getIceSkates();
		c.getFireBoots();
		c.getMagnetShoes();
		boolean obtained = false;
		if(c.hasFireBoots()
				&& c.hasFlippers()
				&& c.hasIceSkates()
				&& c.hasMagnetShoes()){
			obtained = true;
		}
		assertEquals(obtained, true);
	}
	
	@Test
	public void keyUseTest(){
		game = new PlayChip();
		Chip c = new Chip(0, 0, game);
		RedKey rkey = new RedKey(1, 1);
		BlueKey ukey = new BlueKey(2, 2);
		YellowKey ykey = new YellowKey(3, 3);
		GreenKey gkey = new GreenKey(4, 4);
		RedDoor rdoor = new RedDoor(1, 2);
		BlueDoor udoor = new BlueDoor(1, 3);
		YellowDoor ydoor = new YellowDoor (1, 4);
		GreenDoor gdoor = new GreenDoor(1, 5);
		rkey.onCollision(c, game.getMap());
		ukey.onCollision(c, game.getMap());
		ykey.onCollision(c, game.getMap());
		gkey.onCollision(c, game.getMap());
		rdoor.onCollision(c, game.getMap());
		udoor.onCollision(c, game.getMap());
		ydoor.onCollision(c, game.getMap());
		gdoor.onCollision(c, game.getMap());
		boolean passed = false;
		if(!c.hasRedKey()
				&& !c.hasBlueKey()
				&& !c.hasYellowKey()
				&& !c.hasGreenKey()){
			passed = true;
		}
		assertEquals(passed, true);
	}
	
	@Test
	public void deathTest(){
		game = new PlayChip();
		Chip c = new Chip(0, 0, game);
		Map m = new Map();
		FireBall f = new FireBall(1, 1, 0, m, true);
		WaterTile w = new WaterTile(1, 2);
		FireTile ft = new FireTile(1, 3);
		BombTile b = new BombTile(1, 4);
		boolean passed = true;
		f.onCollision(c, m);
		if(c.getState() == c.getAliveState()){
			passed = false;
		}
		c.getState().revive();
		w.onWalk(c);
		if(c.getState() == c.getAliveState()){
			passed = false;
		}
		c.getState().revive();
		ft.onWalk(c);
		if(c.getState() == c.getAliveState()){
			passed = false;
		}
		c.getState().revive();
		b.onWalk(c);
		if(c.getState() == c.getAliveState()){
			passed = false;
		}
		assertEquals(passed, true);	
	}
	
	@Test
	public void RobberTest(){
		game = new PlayChip();
		Chip c = new Chip(0, 0, game);
		Map m = new Map();
		Robber r = new Robber(0, 1);
		c.getIceSkates();
		c.getFlippers();
		c.getFireBoots();
		c.getMagnetShoes();
		c.getRedKey();
		c.getBlueKey();
		c.getYellowKey();
		c.getGreenKey();
		r.onCollision(c, m);
		boolean passed = true;
		for(int i = 0; i < 4; i++){
			if(c.keyStatus()[i] < 1 || c.itemStatus()[i] == true){
				passed = false;
			}
		}
		assertEquals(passed, true);
	}
	
	@Test
	public void chipGateTest(){
		game = new PlayChip();
		Chip c = new Chip(0, 0, game);
		Map m = new Map();
		Gate g = new Gate(0, 1);
		for(int i = 0; i < 10; i++){
			c.obtainChip();
		}
		g.onCollision(c, m);
		assertEquals(g.getCollided(), true);
	}
	
	@Test
	public void stateTest(){
		game = new PlayChip();
		Chip c = new Chip(0, 0, game);
		boolean passed = true;
		c.getState().die();
		if(c.getState() != c.getDeadState())
			passed = false;
		c.getState().revive();
		c.getState().burn();
		if(c.getState() != c.getBurnedState())
			passed = false;
		c.getState().revive();
		c.getState().drown();
		if(c.getState() != c.getDrownedState())
			passed = false;
		c.getState().revive();
		c.getState().swim();
		if(c.getState() != c.getSwimState())
			passed = false;
		c.getState().revive();
		c.getState().explode();
		if(c.getState() != c.getDeadState())
			passed = false;
		c.getState().revive();
		c.getState().win();
		if(c.getState() != c.getWinState())
			passed = false;
		assertEquals(passed, true);
	}
	
	@Test
	public void threadTest(){ // This test will cause an exception. It will still pass.
		game = new PlayChip();
		game.setUpThreads();
		game.startThreads();
		boolean passed = true;
		for(Thread t : game.getThreads()){
			if(!t.isAlive()){
				passed = false;
			}
		}
		assertEquals(passed, true);	
	}
	
	@Test
	public void itemFunctionTest(){
		game = new PlayChip();
		Chip c = new Chip(0, 0, game);
		WaterTile w = new WaterTile(1, 1);
		FireTile ft = new FireTile(1, 2);
		boolean passed = true;
		c.getFlippers();
		c.getFireBoots();
		w.onWalk(c);
		if(c.getState() != c.getSwimState())
			passed = false;
		c.getState().revive();
		ft.onWalk(c);
		if(c.getState() == c.getBurnedState())
			passed = false;
		assertEquals(passed, true);
	}
	
	@Test
	public void blockStateTest(){
		Map m = new Map();
		boolean passed = true;
		Block b = new Block(0, 0, m);
		b.getState().sink();
		if(b.getState() != b.getSunk())
			passed = false;
		b.getState().solidify();
		if(b.getState() != b.getSolid())
			passed = false;
		b.getState().surface();
		if(b.getState() != b.getNormal())
			passed = false;
		b.getState().sink();
		b.getState().surface();
		if(b.getState() != b.getNormal())
			passed = false;
		b.getState().explode();
		if(b.getState() != b.getExploded())
			passed = false;
		b.getState().surface();
		if(b.getState() != b.getNormal())
			passed = false;
		assertEquals(passed, true);
	}
	
	@Test
	public void blockResetTest(){
		Map m1 = MapMaker.readMapFile("resources/demoLevel.txt", 25, 25, 10);
		Map m2 = MapMaker.readMapFile("resources/demoLevel.txt", 25, 25, 10);
		int i, j;
		SolidObject obj;
		boolean passed = true;
		
		for(Point p : m1){
			i = (int)p.getX();
			j = (int)p.getY();
			obj = m1.getObject(i, j);
			if(obj instanceof Block){
				if(((Block)obj).pushableDown())
					((Block)obj).moveDown();
				else if(((Block)obj).pushableLeft())
					((Block)obj).moveLeft();
				else if(((Block)obj).pushableUp())
					((Block)obj).moveUp();
				else if(((Block)obj).pushableRight())
					((Block)obj).moveRight();
				((Block)obj).getState().explode();
			}
		}
		m1.reset();
		for(Point p : m2){
			i = (int)p.getX();
			j = (int)p.getY();
			if(m2.getObject(i, j) instanceof Block){
				if(!(m1.getObject(i, j) instanceof Block))
					passed = false;
			}
		}
		assertEquals(passed, true);
	}
	
	@Test
	public void factoryTest(){
		Map m = new Map();
		EnemyFactory factory = new EnemyFactory("FIREBALL", m);
		Enemy e1 = factory.makeEnemy(0, 0);
		factory.setEnemyType("FIREBALLCCW");
		Enemy e2 = factory.makeEnemy(0, 0);
		boolean passed = true;
		if(!(e1 instanceof FireBall) || !(e2 instanceof FireBall)
				|| !((FireBallStrategy)e1.getStrategy()).clockWise()
				|| ((FireBallStrategy)e2.getStrategy()).clockWise()){
			passed = false;
		}
		assertEquals(passed, true);
	}
	
	@Test
	public void iteratorTest(){
		Map m = new Map();
		MapIterator itr = new MapIterator(m);
		boolean passed = true;
		Point p = itr.currentItem();
		for(int j = 0; j <= m.getYMax(); j++){
			for(int i = 0; i <= m.getXMax(); i++){
				if(i != p.getX() || j != p.getY()){
					passed = false;
				}
				p = itr.next();
			}
		}
		assertEquals(passed, true);
	}
	
	@Test
	public void blockSinkingTest(){
		Map m = new Map();
		m.setTile(0, 0, new WaterTile(0, 0));
		Block b = new Block(1, 0, m);
		m.getTile(0, 0).onPush(b);
		boolean passed = m.getTile(0, 0) instanceof FloorTile;
		assertEquals(passed, true);
	}
	
	@Test
	public void winTes(){
		game = new PlayChip();
		GoalTile g = new GoalTile(0, 0);
		g.onWalk(game.getChip());
		assertEquals(game.getChip().getState(), game.getChip().getWinState());
	}
	
	@Test
	public void myPatienceTest(){
		boolean daniel = true;
		boolean tiredOfTests = true;
		assertEquals(daniel, tiredOfTests);
	}
	
}
