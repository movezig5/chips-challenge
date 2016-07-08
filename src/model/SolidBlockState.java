package model;

public class SolidBlockState extends BlockState{
	
	public SolidBlockState(Block b){
		setBlock(b);
		setImage(readImage("resources/blank.png"));
	}
	
	public void sink(){
		//Used in resetting the block
		getBlock().setState(getBlock().getSunk());
		getBlock().setCollided(false);
	}
	public void solidify(){
		//Do nothing
	}
	public void surface(){
		//Used in resetting the block
		getBlock().setState(getBlock().getNormal());
		getBlock().setCollided(false);
	}
	public void explode(){
		//Do nothing
	}
	
}
