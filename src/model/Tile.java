package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

public abstract class Tile { // The abstract tile class from which map tiles are derived
	
	private int x, y, xPos, yPos;
	private boolean isSolid;
	private boolean pushable;
	private Image img;
	
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
	
	public void setPosition(int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;
		x = xPos * 32;
		y = yPos * 32;
	}
	
	public boolean isSolid(){
		return isSolid;
	}
	public void setSolid(boolean isSolid){
		this.isSolid = isSolid;
	}
	
	public boolean isPushable(){
		return pushable;
	}
	
	public void setPushable(boolean pushable){
		this.pushable = pushable;
	}
	
	public Image getImg(){
		return img;
	}
	
	public void setImg(String imgFile){
		try{
			img = ImageIO.read(new File(imgFile));
		} catch(IOException e){
			System.err.println("An exception occurred while attempting to read " + imgFile + ".");
			System.exit(1);
		}
	}
	
	public void setImg(Image img){
		this.img = img;
	}
	
	public void draw(Graphics2D g2){
		g2.drawImage(img, getX(), getY(), null);
	}
	
	public void draw(Graphics2D g2, int x, int y){
		g2.drawImage(img, x, y, null);
	}
	
	public abstract void onWalk(Chip c);
	
	public abstract void onPush(Block b);
	
	public abstract void reset();
	
}
