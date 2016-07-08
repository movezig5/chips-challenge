package model;

public class ComputerChip extends SolidObject{
	
	public ComputerChip(int xp, int yp){
		setPosition(xp, yp);
		setImg1("resources/computerchip.jpg");
		setImg2("resources/blank.png");
		initialize();
	}
	
	public void onCollision(Chip c, Map m){
		if(!getCollided()){
			setCollided(true);
			setImg(getImg2());
			c.obtainChip();
		}
	}
	
	public void initialize(){
		setCollided(false);
		setImg(getImg1());
	}
	
}
