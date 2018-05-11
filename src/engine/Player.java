package engine;
public class Player {
	
	private String name;
	private Piece piece;
	
	public Player(String name){
		this.name = name;
		piece = new Piece();
	}
	
	public int roll(Die die){
		die.roll();
		return die.getFace();
	}
	
	public String getName(){
		return name;
	}
	
	public void movePiece(Board board, int steps){
		if(isReverse()){
			board.movePiece(piece, - steps);
			piece.unreverse();
		}
		else{
			board.movePiece(piece, steps);
		}
	}
	
	public Piece getPiece(){
		return piece;
	}
	
	public void freeze(){
		piece.freeze();
	}
	
	public void unfreeze(){
		piece.unfreeze();
	}
	
	public boolean isFreeze(){
		return piece.isFreeze();
	}
	
	public void reverse(){
		piece.reverse();
	}
	
	public void unreverse(){
		piece.unreverse();
	}
	
	public boolean isReverse(){
		return piece.isReverse();
	}

}
