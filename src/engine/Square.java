package engine;
import java.util.ArrayList;
import java.util.List;

public class Square {

	private List<Piece> pieces;
	private int number;
	private boolean goal;
	private Warp warp;
	
	public Square(int number){
		this.number = number;
		pieces = new ArrayList<Piece>();
		goal = false;
	}
	
	public void setGoal(boolean goal){
		this.goal = goal;
	}
	
	public void addPiece (Piece piece) {
		pieces.add(piece);
	}
	
	public void removePiece(Piece piece){
		pieces.remove(piece);
	}
	
	public boolean hasPiece(Piece piece){
		return pieces.contains(piece);
	}
	
	public boolean isGoal(){
		return goal;
	}
	
	public int getNumber(){
		return number;
	}
	
	public void setWarp(Warp warp){
		this.warp = warp;
	}
	
	public boolean hasWarp(){
		return warp != null;
	}
	
	public Warp getWarp(){
		return warp;
	}
	
}
