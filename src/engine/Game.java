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

}
