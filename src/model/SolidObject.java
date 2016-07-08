package model;

import java.awt.Image;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * The base class for all objects on the map.
 */
public abstract class SolidObject{
	
	private int x, y, xPos, yPos;
	private Image img, img1, img2;
	private boolean collided; // Has the object collided with Chip or been altered in some way?
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getXPos(){
		return xPos;
	}
	public int getYPos(){
		return yPos;
	}
	
	public boolean getCollided(){
		return collided;
	}
	public void setCollided(boolean collided){
		this.collided = collided;
	}
	
	public Image getImg(){
		return img;
	}
	public Image getImg1(){
		return img1;
	}
	public Image getImg2(){
		return img2;
	}
	
	public void setPosition(int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;
		this.x = xPos * 32;
		this.y = yPos * 32;
	}
	public void move(int dx, int dy){
		xPos += dx;
		yPos += dy;
		x = xPos * 32;
		y = yPos * 32;
	}
	
	public void moveUp(){
		move(0, -1);
	}
	public void moveDown(){
		move(0, 1);
	}
	public void moveLeft(){
		move(1, 0);
	}
	public void moveRight(){
		move(-1, 0);
	}
	
	public void setImg(String fileName){
		try{
			img = ImageIO.read(new File(fileName));
		} catch(IOException e){
			System.out.println("An exception occurred while trying to read the file " + fileName + ".");
			System.exit(1);
		}
	}
	/*
	 * Each object has two different images for different states. There aren't usually
	 * more than two, hance the lack of the state pattern. Blocks are the major
	 * exception, having at least four different states.
	 */
	public void setImg1(String fileName){
		try{
			img1 = ImageIO.read(new File(fileName));
		} catch(IOException e){
			System.out.println("An exception occurred while trying to read the file " + fileName + ".");
			System.exit(1);
		}
	}
	
	public void setImg2(String fileName){
		try{
			img2 = ImageIO.read(new File(fileName));
		} catch(IOException e){
			System.out.println("An exception occurred while trying to read the file " + fileName + ".");
			System.exit(1);
		}
	}
	
	public void setImg(Image img){
		this.img = img;
	}
	
	public void draw(Graphics2D g2){
		g2.drawImage(img, x, y, null);
	}
	
	public void draw(Graphics2D g2, int x, int y){
		g2.drawImage(img, x, y, null);
	}
	
	// What happens when Chip collides with this object?
	public abstract void onCollision(Chip c, Map m);
	
	//Used when resetting the map. Sometimes does nothing.
	public abstract void initialize();
}
