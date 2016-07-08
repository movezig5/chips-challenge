package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BombTile extends Tile{
	
	private Image img1, img2;
	boolean exploded;
	
	public BombTile(int xp, int yp){
		exploded = false;
		setPosition(xp, yp);
		setSolid(false);
		setPushable(true);
		readImages();
		setImg(img1);
	}
	
	public void onWalk(Chip c){
		if(!exploded){
			c.getState().explode();
		}
	}
	
	public void onPush(Block b){
		b.getState().explode();
		exploded = true;
		setImg(img2);
	}
	
	public void reset(){
		setImg(img1);
		exploded = false;
	}
	
	private void readImages(){
		try{
			img1 = ImageIO.read(new File("resources/bomb.jpg"));
			img2 = ImageIO.read(new File("resources/floor.jpg"));
		} catch(IOException e){
			System.err.println("An error occurred while attempting to read the files resources/bomb.jpg and resources/blang.png.");
			System.exit(1);
		}
	}
	
}
