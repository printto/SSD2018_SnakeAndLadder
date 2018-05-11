package engine;
public class Piece {

	private boolean freeze = false;
	
	public void freeze(){
		freeze = true;
	}
	
	public void unfreeze(){
		freeze = false;
	}
	
	public boolean isFreeze(){
		return freeze;
	}
	
}
