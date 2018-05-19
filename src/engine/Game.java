package engine;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * This object is the main engine of the snake and ladder game.
 * This object is responsible for handling board and players.
 * @author Pappim Pipatkasrira
 * @version 1.0
 * @since May 11, 2018
 */
public class Game extends Observable{

	private ArrayList<Player> players;
	private Die die;
	private Board board;

	private int currentPlayerIndex = 0;
	private boolean ended = false;

	/**
	 * Creates a snake and ladder game.
	 * @param playersAmount The amount of the players.
	 */
	public Game(int playersAmount){
		die = new Die();
		board = new Board();
		players = new ArrayList<Player>();
		for(int i = 0 ; i < playersAmount ; i++){
			players.add(new Player("P"+(i+1)));
			board.addPiece(players.get(i).getPiece(), 0);
		}
		ended = false;
		initTraps();
	}

	/**
	 * Check if the game ended.
	 * @return <code>true</code> if the game is ended;
	 * <code>false</code> otherwise.
	 */
	public boolean isEnded() { return ended; }

	/**
	 * End the game.
	 */
	public void end() { ended  = true; }

	/**
	 * Get current player of the current turn.
	 * @return Player in the current turn
	 */
	public Player currentPlayer(){ return players.get(currentPlayerIndex); }

	public void switchPlayer(){
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
		setChanged();
		notifyObservers(ObserverCodes.PLAYER_CHANGED_STRING);
	}

	/**
	 * Get the name of the current player.
	 * @return Name of the current player.
	 */
	public String currentPlayerName(){
		return currentPlayer().getName();
	}

	/**
	 * Get position of the current player.
	 * @return Position of the current player.
	 */
	public int currentPlayerPosition(){
		return board.getPiecePosition(currentPlayer().getPiece());
	}

	/**
	 * Move the current player's piece by steps.
	 * @param steps Steps to move.
	 */
	public void currentPlayerMovePiece(int steps){
		currentPlayer().movePiece(board, steps);
		if(currentPlayerPosition() == board.getSquares().length - 1){
			end();
		}
	}

	/**
	 * Move the current player's piece by warp.
	 * @param warp Warp that the player is stepping on.
	 */
	public void currentPlayerWarp(Warp warp){
		setChanged();
		notifyObservers(ObserverCodes.PLAYER_WARP_STRING);
		currentPlayer().warpPiece(board, warp.getDestination() - currentPlayerPosition());
	}

	/**
	 * Check if the current player wins.
	 * @return <code>true</code> if the current player wins;
	 * <code>false</code> otherwise.
	 */
	public boolean isCurrentPlayerWins(){
		ended = board.pieceIsAtGoal(currentPlayer().getPiece());
		return ended;
	}

	/**
	 * Current player roll a die.
	 * @return Face of the die;
	 * 0 if the game is ended.
	 */
	public int currentPlayerRollDice(){
		int temp = 0;
		if(!ended){
			temp = currentPlayer().roll(die);
			setChanged();
			notifyObservers(ObserverCodes.DIE_ROLLED_STRING);
		}
		return temp;
	}

	/**
	 * Notify observer if the current player has the special buff.
	 */
	public void checkCurrentPlayerStatus(){
		if(currentPlayer().isFreeze()){
			setChanged();
			notifyObservers(ObserverCodes.FREEZE_STRING);
		}
		if(currentPlayer().isReverse()){
			setChanged();
			notifyObservers(ObserverCodes.REVERSE_STRING);
		}
	}

	/**
	 * Returns board of the game.
	 * @return Board of the game.
	 */
	public Board getBoard(){
		return board;
	}

	/**
	 * Returns face of the die.
	 * @return Face of the die.
	 */
	public int getDieFace(){
		return die.getFace();
	}

	/**
	 * Returns list of the players.
	 * @return ArrayList of the players.
	 */
	public ArrayList<Player> getPlayers(){
		return players;
	}

	/**
	 * Initialize snakes, ladders and ability to the squares.
	 */
	private void initTraps() {
		board.addWarp(4, 19);
		board.addWarp(9, 25);
		board.addWarp(14, 30);
		board.addWarp(16, 2);
		board.addWarp(26, 13);
		board.addWarp(28, 36);
		board.addWarp(32, 18);
		board.addWarp(33, 47);
		board.addWarp(35, 51);
		board.addWarp(39, 7);
		board.addWarp(41, 55);
		board.addWarp(43, 29);
		board.addWarp(59, 25);
		board.addWarp(61, 29);
		board.addTrap(10, Square.REVERSE);
		board.addTrap(20, Square.FREEZE);
		board.addTrap(23, Square.REVERSE);
		board.addTrap(31, Square.FREEZE);
		board.addTrap(34, Square.REVERSE);
		board.addTrap(37, Square.FREEZE);
		board.addTrap(45, Square.FREEZE);
		board.addTrap(52, Square.REVERSE);
	}

	/**
	 * Check if current player is at snake, ladder or warp.
	 * @return <code>true</code> if the current player is at any snake, ladder or warp;
	 * <code>false</code> otherwise.
	 */
	public boolean currentPlayerIsAtWarp(){
		return board.pieceIsAtWarp(currentPlayer().getPiece());
	}

	/**
	 * Get snake or ladder from the current player position;
	 * @return Warp at the current position.
	 */
	public Warp getWarpAtCurrentPosition(){
		return board.getWarpInSquare(currentPlayerPosition());
	}

}
