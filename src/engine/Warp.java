package engine;

public class Warp {
	
	final public static int SNAKE = 0;
	final public static int LADDER = 1;

	private int destination;
	private int mode;
	
	public Warp(int destination, int mode){
		this.destination = destination;
		this.mode = mode;
	}
	
	public int getDestination(){
		return destination;
	}
	
	public String toString(){
		if(mode == SNAKE){
			return "snake";
		}
		if(mode == LADDER){
			return "ladder";
		}
		return "warp";
	}
	
}
