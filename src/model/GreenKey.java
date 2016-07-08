package model;

public class GreenKey extends SolidObject{
	
	public GreenKey(int xp, int yp){
		setPosition(xp, yp);
		setImg1("resources/greenkey.jpg");
		setImg2("resources/blank.png");
		initialize();
	}
	
	public void onCollision(Chip c, Map m){
		if(!getCollided()){
			c.getGreenKey();
			setImg(getImg2());
			setCollided(true);
		}
	}
	
	public void initialize(){
		setImg(getImg1());
		setCollided(false);
	}
	
}
