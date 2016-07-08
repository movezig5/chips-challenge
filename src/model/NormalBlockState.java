package model;

public class NormalBlockState extends BlockState{
	
	public NormalBlockState(Block b){
		setBlock(b);
		setImage(readImage("resources/brownblock.jpg"));
	}
	
	public void sink(){
		getBlock().setState(getBlock().getSunk());
		getBlock().setCollided(true);
	}
	public void solidify(){
		//Do nothing
	}
	public void surface(){
		//Do nothing
	}
	public void explode(){
		getBlock().setState(getBlock().getExploded());
		getBlock().setCollided(true);
	}
}
