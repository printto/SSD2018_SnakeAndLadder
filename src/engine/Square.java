package engine;
import java.util.ArrayList;
import java.util.List;

/**
 * Square object on the board.
 * This object is responsible for holding the pieces on the board.
 * This object is also contains for special ability and warps.
 * @author Pappim Pipatkasrira
 * @version 1.0
 * @since May 11, 2018
 */
public class Square {
	
	/**
	 * Normal mode. No special ability.
	 */
	final public static int NORMAL = 0;
	
	/**
	 * Freeze mode. Freeze ability.
	 */
	final public static int FREEZE = 1;
	
	/**
	 * Reverse mode. Reverse ability.
	 */
	final public static int REVERSE = 2;

	private List<Piece> pieces;
	private int number;
	private boolean goal;
	private Warp warp;
	private int mode = 0;
	
	/**
	 * Create a square.
	 * @param number Index of a square.
	 */
	public Square(int number){
		this.number = number;
		pieces = new ArrayList<Piece>();
		goal = false;
	}
	
	/**
	 * Set if this square is a goal of the game.
	 * @param goal Is this square a goal?
	 */
	public void setGoal(boolean goal){
		this.goal = goal;
	}
	
	/**
	 * Add piece to the square.
	 * @param piece Piece to add.
	 */
	public void addPiece (Piece piece) {
		pieces.add(piece);
	}
	
	/**
	 * Remove piece from the square.
	 * @param piece Piece to remove.
	 */
	public void removePiece(Piece piece){
		pieces.remove(piece);
	}
	
	/**
	 * Check if the square has the specific piece.
	 * @param piece Piece to check.
	 * @return <code>true</code> if the square contains the piece;
	 * <code>false</code> otherwise.
	 */
	public boolean hasPiece(Piece piece){
		return pieces.contains(piece);
	}
	
	/**
	 * Check if the square is a goal.
	 * @return <code>true</code> if the square is a goal;
	 * <code>false</code> otherwise.
	 */
	public boolean isGoal(){
		return goal;
	}
	
	/**
	 * Get index if the square.
	 * @return Index of the square.
	 */
	public int getNumber(){
		return number;
	}
	
	/**
	 * Set snake, ladder or warp to the square.
	 * @param warp Warp to add.
	 */
	public void setWarp(Warp warp){
		this.warp = warp;
	}
	
	/**
	 * Check if there is a snake, ladder or a warp in this square or not.
	 * @return <code>true</code> if there is a snake, ladder or a warp in this square;
	 * <code>false</code> otherwise.
	 */
	public boolean hasWarp(){
		return warp != null;
	}
	
	/**
	 * Get the warp from the square.
	 * @return Warp in the square.
	 */
	public Warp getWarp(){
		return warp;
	}
	
	/**
	 * Set the special ability of the square.
	 * @param mode Buff to add.
	 */
	public void setMode(int mode){
		this.mode = mode;
	}
	
	/**
	 * Get the special ability of the square.
	 * @return Buff of the square.
	 */
	public int getMode(){
		return mode;
	}
	
}
