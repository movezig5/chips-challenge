package model;

public class IceSkates extends SolidObject{
	
	public IceSkates(int xp, int yp){
		setPosition(xp, yp);
		setImg1("resources/iceskates.jpg");
		setImg2("resources/blank.png");
		initialize();
	}
	
	public void onCollision(Chip c, Map m){
		if(!getCollided()){
			c.getIceSkates();
			setImg(getImg2());
			setCollided(true);
		}
	}
	
	public void initialize(){
		setImg(getImg1());
		setCollided(false);
	}
	
}
