package model;

public class RedKey extends SolidObject{
	
	public RedKey(int xp, int yp){
		setPosition(xp, yp);
		setImg1("resources/redkey.jpg");
		setImg2("resources/blank.png");
		initialize();
	}
	
	public void onCollision(Chip c, Map m){
		if(!getCollided()){
			c.getRedKey();
			setImg(getImg2());
		}
	}
	
	public void initialize(){
		setImg(getImg1());
		setCollided(false);
	}
	
}
