package model;

public class FireBoots extends SolidObject{
	
	public FireBoots(int xp, int yp){
		setPosition(xp, yp);
		setImg1("resources/fireboots.jpg");
		setImg2("resources/blank.png");
		initialize();
	}
	
	public void onCollision(Chip c, Map m){
		if(!getCollided()){
			c.getFireBoots();
			setImg(getImg2());
			setCollided(true);
		}
	}
	
	public void initialize(){
		setCollided(false);
		setImg(getImg1());
	}
	
}
