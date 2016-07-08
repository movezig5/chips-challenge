package model;

public class MagnetShoes extends SolidObject{
	
	public MagnetShoes(int xp, int yp){
		setPosition(xp, yp);
		setImg1("resources/magnetshoes.jpg");
		setImg2("resources/blank.png");
		initialize();
	}
	
	public void onCollision(Chip c, Map m){
		if(!getCollided()){
			c.getMagnetShoes();
			setImg(getImg2());
			setCollided(true);
		}
	}
	
	public void initialize(){
		setImg(getImg1());
		setCollided(false);
	}
	
}
