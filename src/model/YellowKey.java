package model;

public class YellowKey extends SolidObject{
	
	public YellowKey(int xp, int yp){
		setPosition(xp, yp);
		setImg1("resources/yellowkey.jpg");
		setImg2("resources/blank.png");
		initialize();
	}
	
	public void onCollision(Chip c, Map m){
		if(!getCollided()){
			c.getYellowKey();
			setImg(getImg2());
			setCollided(true);
		}
	}
	
	public void initialize(){
		setImg(getImg1());
		setCollided(false);
	}
	
}
