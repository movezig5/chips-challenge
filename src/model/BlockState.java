package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * The state of a block. This is used if a block sinks or explodes.
 */
public abstract class BlockState {
	
	private Block block;
	private Image image;
	
	public Block getBlock(){
		return block;
	}
	public void setBlock(Block block){
		this.block = block;
	}
	
	public Image getImg(){
		return image;
	}
	public void setImage(Image image){
		this.image = image;
	}
	
	public Image readImage(String fileName){
		Image result = null;
		try{
			result = ImageIO.read(new File(fileName));
		} catch(IOException e){
			System.err.println("An exception occurred while attempting to read the file " + fileName + ".");
			System.exit(1);
		}
		return result;
	}
	
	public abstract void sink();
	public abstract void solidify();
	public abstract void surface();
	public abstract void explode();
	
}
