package model;

public class BlueKey extends SolidObject{
	
	public BlueKey(int xp, int yp){
		setPosition(xp, yp);
		setImg1("resources/bluekey.jpg");
		setImg2("resources/blank.png");
		initialize();
	}
	
	public void onCollision(Chip c, Map m){
		if(!getCollided()){
			c.getBlueKey();
			setImg(getImg2());
			setCollided(true);
		}
	}
	
	public void initialize(){
		setImg(getImg1());
		setCollided(false);
	}
	
}
