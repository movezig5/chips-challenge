package model;

public class Flippers extends SolidObject{
	
	public Flippers(int xp, int yp){
		setPosition(xp, yp);
		setImg1("resources/flippers.jpg");
		setImg2("resources/blank.png");
		initialize();
	}
	
	public void onCollision(Chip c, Map m){
		if(!getCollided()){
			c.getFlippers();
			setImg(getImg2());
			setCollided(true);
		}
	}
	
	public void initialize(){
		setImg(getImg1());
		setCollided(false);
	}
	
}
