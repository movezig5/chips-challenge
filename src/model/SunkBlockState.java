package model;

public class SunkBlockState extends BlockState{
	
	public SunkBlockState(Block b){
		setBlock(b);
		setImage(readImage("resources/blueblock.jpg"));
	}
	
	public void sink(){
		//Do nothing
	}
	public void solidify(){
		getBlock().setState(getBlock().getSolid());
		getBlock().setCollided(true);
	}
	public void surface(){
		//Used when resetting the block
		getBlock().setState(getBlock().getNormal());
		getBlock().setCollided(false);
	}
	public void explode(){
		//Do nothing
	}
	
}
