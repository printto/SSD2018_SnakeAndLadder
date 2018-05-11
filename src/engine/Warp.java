package engine;

/**
 * Snake or ladder object.
 * @author Pappim Pipatkasrira
 * @version 1.0
 * @since May 11, 2018
 */
public class Warp {
	
	/**
	 * The warp is a snake.
	 */
	final public static int SNAKE = 0;
	
	/**
	 * The warp is a ladder.
	 */
	final public static int LADDER = 1;

	private int destination;
	private int mode;
	
	/**
	 * Create a snake, ladder or warp.
	 * @param destination Destination of the warp.
	 * @param mode Mode of the warp.
	 */
	public Warp(int destination, int mode){
		this.destination = destination;
		this.mode = mode;
	}
	
	/**
	 * Returns destination of the warp.
	 * @return Destination of the warp.
	 */
	public int getDestination(){
		return destination;
	}
	
	@Override
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
