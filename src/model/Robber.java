package model;

//The robber takes your items. It adds an extra layer of challenge to the game.
public class Robber extends SolidObject{
	
	public Robber(int xp, int yp){
		setPosition(xp, yp);
		setImg1("resources/robber.jpg");
		setImg(getImg1());
		setCollided(false);
	}
	
	public void onCollision(Chip c, Map m){
		c.losePowerUps();
	}
	
	public void initialize(){}
	
}
