package model;

public class ExplodedBlockState extends BlockState{
	
	public ExplodedBlockState(Block b){
		setBlock(b);
		setImage(readImage("resources/blank.png"));
	}
	
	public void sink(){
		//Do nothing
	}
	public void solidify(){
		//Do nothing
	}
	public void surface(){
		//Used in resetting block
		getBlock().setState(getBlock().getNormal());
		getBlock().setCollided(false);
	}
	public void explode(){
		//Do nothing
	}
	
}
