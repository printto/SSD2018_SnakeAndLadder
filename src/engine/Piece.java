package engine;

/**
 * Piece to play on the snake and ladder board.
 * The piece also contains the buff status gained from the square.
 * @author Pappim Pipatkasrira
 * @version 1.0
 * @since May 11, 2018
 */
public class Piece {

	private boolean freeze = false;
	private boolean reverse = false;
	
	/**
	 * Freeze the piece.
	 */
	public void freeze(){
		freeze = true;
	}
	
	/**
	 * Remove the Freeze status from the piece.
	 */
	public void unfreeze(){
		freeze = false;
	}
	
	/**
	 * Check if the piece have Freeze buff status.
	 * @return <code>true</code> if the piece is in the freeze status;
	 * <code>false</code> otherwise.
	 */
	public boolean isFreeze(){
		return freeze;
	}
	
	/**
	 * Reverse the piece.
	 */
	public void reverse(){
		reverse = true;
	}
	
	/**
	 * Remove the Reverse status from the piece.
	 */
	public void unreverse(){
		reverse = false;
	}
	
	/**
	 * Check if the piece have Reverse buff status.
	 * @return <code>true</code> if the piece is in the reverse status;
	 * <code>false</code> otherwise.
	 */
	public boolean isReverse(){
		return reverse;
	}
	
}
