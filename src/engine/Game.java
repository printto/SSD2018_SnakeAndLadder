package engine;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Game extends Observable{

	ArrayList<Player> players;
	private Die die;
	private Board board;

	private int currentPlayerIndex = 0;
	private boolean ended = false;

	public Game(int playersAmount){
		die = new Die();
		board = new Board();
		players = new ArrayList<Player>();
		for(int i = 0 ; i < playersAmount ; i++){
			players.add(new Player("P"+(i+1)));
			board.addPiece(players.get(i).getPiece(), 0);
		}
		ended = false;
		initWarp();
	}

	public boolean isEnded() { return ended; }

	public void end() { ended  = true; }

	public Player currentPlayer(){ return players.get(currentPlayerIndex); }

	public void switchPlayer(){
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
		setChanged();
		notifyObservers(ObserverCodes.PLAYER_CHANGED_STRING);
	}

	public String currentPlayerName(){
		return currentPlayer().getName();
	}

	public int currentPlayerPosition(){
		return board.getPiecePosition(currentPlayer().getPiece());
	}

	public void currentPlayerMovePiece(int steps){
		currentPlayer().movePiece(board, steps);
		setChanged();
		notifyObservers(ObserverCodes.BOARD_UPDATED_STRING);
		if(currentPlayerPosition() == board.getSquares().length - 1){
			end();
		}
	}
	
	public void currentPlayerWarp(Warp warp){
		setChanged();
		notifyObservers(ObserverCodes.PLAYER_WARP_STRING);
		currentPlayer().warpPiece(board, warp);
		setChanged();
		notifyObservers(ObserverCodes.BOARD_UPDATED_STRING);
	}

	public boolean currentPlayerWins(){
		return board.pieceIsAtGoal(currentPlayer().getPiece());
	}

	public int currentPlayerRollDice(){
		int temp = currentPlayer().roll(die);
		if(!ended){
			setChanged();
			notifyObservers(ObserverCodes.DIE_ROLLED_STRING);
		}
		return temp;
	}

	public Board getBoard(){
		return board;
	}

	public int getDieFace(){
		return die.getFace();
	}

	public ArrayList<Player> getPlayers(){
		return players;
	}

	private void initWarp() {
		board.addWarp(4, 19);
		board.addWarp(9, 25);
		board.addWarp(14, 30);
		board.addWarp(16, 2);
		board.addWarp(26, 13);
		board.addWarp(28, 36);
		board.addWarp(32, 18);
		board.addWarp(33, 47);
		board.addWarp(41, 55);
		board.addWarp(43, 29);
		board.addWarp(59, 25);
		board.addWarp(61, 29);
	}
	
	public boolean currentPlayerIsAtWarp(){
		return board.pieceIsAtWarp(currentPlayer().getPiece());
	}
	
	public Warp getWarpAtCurrentPosition(){
		return board.getWarpInSquare(currentPlayerPosition());
	}

}
