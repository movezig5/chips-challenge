package model;

// The interface which Chip's various states implement
public interface ChipState {
	
	public void die();
	public void burn();
	public void drown();
	public void explode();
	public void revive();
	public void swim();
	public void slide(); // Unused -- see SlideState for details.
	public void win();
	
}
