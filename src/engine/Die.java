package engine;
import java.util.Random;

/**
 * This object is a die for typical board games.
 * @author Pappim Pipatkasrira
 * @version 1.0
 * @since May 11, 2018
 */
public class Die {
	
	//Max number of the die.
	public static final int MAX_FACE = 6;
	
	//store the last rolled.
	private int face;
	
	/**
	 * Creates a die with initialized face 1.
	 */
	public Die(){
		face = 1;
	}
	
	/**
	 * Roll a die.
	 */
	public void roll(){
		face = 1 + new Random().nextInt(MAX_FACE);
	}
	
	/**
	 * Get last rolled number.
	 * @return Face of a die.
	 */
	public int getFace(){
		return face;
	}

}
