package engine;

/**
 * Player of the snake and ladder game.
 * This object is responsible for rolling the die and moving the piece in the board.
 * This object also associated with the buff in the piece.
 * @author Pappim Pipatkasrira
 * @version 1.0
 * @since May 11, 2018
 */
public class Player {
	
	private String name;
	private Piece piece;
	
	/**
	 * Create player.
	 * @param name Name of the player.
	 */
	public Player(String name){
		this.name = name;
		piece = new Piece();
	}
	
	/**
	 * Player roll a die.
	 * @param die Die to roll.
	 * @return Face of the die.
	 */
	public int roll(Die die){
		die.roll();
		return die.getFace();
	}
	
	/**
	 * Returns name of the player.
	 * @return Name of the player.
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Move the piece in the board by steps amount.
	 * @param board Board to move the piece.
	 * @param steps Steps to walk the piece.
	 */
	public void movePiece(Board board, int steps){
		if(isReverse()){
			board.movePiece(piece, - steps);
			piece.unreverse();
		}
		else{
			board.movePiece(piece, steps);
		}
	}
	
	/**
	 * Returns the piece of the player.
	 * @return Player's piece.
	 */
	public Piece getPiece(){
		return piece;
	}
	
	/**
	 * Freeze the player's piece.
	 */
	public void freeze(){
		piece.freeze();
	}
	
	/**
	 * Remove Freeze buff from the piece.
	 */
	public void unfreeze(){
		piece.unfreeze();
	}
	
	/**
	 * Check if the piece has the Freeze buff status.
	 * @return <code>true</code> if the piece has the Freeze buff status;
	 * <code>false</code> otherwise.
	 */
	public boolean isFreeze(){
		return piece.isFreeze();
	}
	
	/**
	 * Add Reverse status to the piece.
	 */
	public void reverse(){
		piece.reverse();
	}
	
	/**
	 * Remove Reverse status from the piece.
	 */
	public void unreverse(){
		piece.unreverse();
	}
	
	/**
	 * Check if the piece has the Reverse buff status.
	 * @return <code>true</code> if the piece has the Reverse buff status;
	 * <code>false</code> otherwise.
	 */
	public boolean isReverse(){
		return piece.isReverse();
	}

}
