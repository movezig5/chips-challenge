package model;

// This object looks like a solid wall until the player touches it, at which point it disappears.
public class FakeWall extends SolidObject{
	
	public FakeWall(int xp, int yp){
		setPosition(xp, yp);
		setImg1("resources/wall.jpg");
		setImg2("resources/blank.png");
		initialize();
	}
	
	public void onCollision(Chip c, Map m){
		if(!getCollided()){
			setImg(getImg2());
			setCollided(true);
		}
	}
	
	public void initialize(){
		this.setImg(getImg1());
		this.setCollided(false);
	}
	
}
