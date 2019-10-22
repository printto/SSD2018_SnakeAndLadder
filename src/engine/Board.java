package engine;

import java.util.Observable;

/**
 * This is Board class for snake and ladder game.
 * @author Pappim Pipatkasrira
 * @version 1.0
 * @since May 11, 2018
 */
public class Board extends Observable{

	//Size need to be n^2
	public static final int SIZE = 64;

	private Square [] squares;

	/**
	 * Creates the snake and ladder board.
	 */
	public Board(){
		squares = new Square[SIZE];
		for(int i = 0 ; i < squares.length ; i++){
			squares[i] = new Square(i);
		}
		squares[squares.length - 1].setGoal(true);
	}

	/**
	 * Add piece to the specific square.
	 * @param piece The piece to add to the position.
	 * @param position Position to add the piece.
	 */
	public void addPiece(Piece piece , int position){
		squares[position].addPiece(piece);
	}

	/**
	 * Move piece by amount of the steps.
	 * @param piece The piece to move.
	 * @param steps amount of steps.
	 */
	public void movePiece(Piece piece , int steps){
		int pos = getPiecePosition(piece);
		squares[pos].removePiece(piece);
		int newPos = pos+steps;
		if(newPos >= squares.length){
			newPos = squares.length - 1;
		}
		final int tempNew = newPos;
		new Thread(new Runnable(){
			@Override
			public void run() {
				if(tempNew > pos){
					for(int i = pos ; i < tempNew ; i ++){
						squares[i].removePiece(piece);
						addPiece(piece, i+1);
						setChanged();
						notifyObservers(ObserverCodes.PLAYER_WALKING);
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							System.err.println("Error delay moves.");
						}
					}
				}
				else {
					for(int i = pos ; i > tempNew ; i --){
						squares[i].removePiece(piece);
						addPiece(piece, i-1);
						setChanged();
						notifyObservers(ObserverCodes.PLAYER_WALKING);
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							System.err.println("Error delay moves.");
						}
					}
				}
				if(squares[tempNew].getMode() == Square.FREEZE){
					piece.freeze();
				}
				else if(squares[tempNew].getMode() == Square.REVERSE){
					piece.reverse();
				}
				setChanged();
				notifyObservers(ObserverCodes.BOARD_UPDATED);
			}
		}).start();
	}

	/**
	 * Warp piece by amount of the steps.
	 * @param piece The piece to warp.
	 * @param steps amount of steps.
	 */
	public void warpPiece(Piece piece , int steps){
		int pos = getPiecePosition(piece);
		squares[pos].removePiece(piece);
		int newPos = pos+steps;
		if(newPos >= squares.length){
			newPos = squares.length - 1;
		}
		addPiece(piece, newPos);
		if(squares[newPos].getMode() == Square.FREEZE){
			piece.freeze();
		}
		else if(squares[newPos].getMode() == Square.REVERSE){
			piece.reverse();
		}
		setChanged();
		notifyObservers(ObserverCodes.PLAYER_WALKING);
	}

	/**
	 * Get position of the piece.
	 * @param piece The piece to get the position.
	 * @return Position of the piece.
	 */
	public int getPiecePosition(Piece piece){
		for(Square s : squares){
			if(s.hasPiece(piece)){
				return s.getNumber();
			}
		}
		return -1;
	}

	/**
	 * Check if the piece is at goal.
	 * @param piece The piece to check.
	 * @return <code>true</code> if the piece is at goal.;
	 * <code>false</code> otherwise.
	 */
	public boolean pieceIsAtGoal(Piece piece){
		return squares[getPiecePosition(piece)].isGoal();
	}

	/**
	 * Check if the piece is at warp.
	 * @param piece The piece to check.
	 * @return <code>true</code> if the piece is at warp.;
	 * <code>false</code> otherwise.
	 */
	public boolean pieceIsAtWarp(Piece piece){
		return squares[getPiecePosition(piece)].hasWarp();
	}

	/**
	 * Returns all squares on the board.
	 * @return Array of all the squares on the board.
	 */
	public Square[] getSquares(){
		return squares;
	}

	/**
	 * Add snake or ladder to the board.
	 * @param position The initial position of the snake/ladder.
	 * @param destination Destination position of the snake/ladder.
	 */
	public void addWarp(int position, int destination){
		if(position < destination){
			squares[position].setWarp(new Warp(destination,Warp.LADDER));
		}
		else{
			squares[position].setWarp(new Warp(destination,Warp.SNAKE));
		}
	}

	/**
	 * Get snake/ladder from the square.
	 * @param position Position of the square.
	 * @return Snake or ladder (or warp) in the square.
	 */
	public Warp getWarpInSquare(int position){
		return squares[position].getWarp();
	}

	/**
	 * Adds special ability to the square.
	 * @param position Position of the square to add the ability.
	 * @param mode Ability of the square.
	 */
	public void addTrap(int position, int mode){
		squares[position].setMode(mode);
	}

}
