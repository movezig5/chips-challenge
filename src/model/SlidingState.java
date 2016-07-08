package model;

/*
 * Unused state, originally intended to be used with the IceTile and MagnetTile classes.
 * It was meant to represent when Chip was "sliding" on those tiles.
 */
public class SlidingState implements ChipState{
	
	private Chip chip;
	
	public SlidingState(Chip chip){
		this.chip = chip;
	}
	
	public void die(){
		chip.setState(chip.getDeadState());
	}
	public void burn(){
		chip.setState(chip.getBurnedState());
	}
	public void drown(){
		chip.setState(chip.getDrownedState());
	}
	public void explode(){
		chip.setState(chip.getDeadState());
	}
	public void revive(){
		chip.setState(chip.getAliveState());
	}
	public void swim(){
		chip.setState(chip.getSwimState());
	}
	public void slide(){
		//Do nothing
	}
	public void win(){
		chip.setState(chip.getWinState());
		chip.getGame().win();
	}
	
}
