package model;

// The fireball class. Most of the code is in the abstract enemy class.
public class FireBall extends Enemy{
	
	public FireBall(int xp, int yp, int dir, Map m, boolean clockwise){
		setImg1("resources/fireball.png");
		setImg2("resources/blank.png");
		setInitPosition(xp, yp);
		setPosition(xp, yp);
		setMap(m);
		setInitDir(dir);
		setDirection(dir);
		setStrategy(new FireBallStrategy(this, clockwise));
		setPreviousObject(null);
		setImg(getImg1());
		setCollided(false);
		setChipAlive(true);
		setChipWon(false);
		setPauseLength(300);
	}
	
	public boolean diesOn(Tile t){
		return (t instanceof WaterTile);
	}
	
}
