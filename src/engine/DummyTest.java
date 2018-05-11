package engine;
import java.util.ArrayList;

import javax.swing.JLabel;

/**
 * This class is just a test for initializing a squares on the board.
 * The main prints out all the squares and components inside.
 * The class is called DummyTest because it acts as a dummy board.
 * @author Pappim Pipatkasrira
 * @version 1.0
 * @since May 11, 2018
 */
public class DummyTest {

	public static void main(String[] args){
		Game game = new Game(4);
		for(int i = 0 ; i < 64 ; i++){
			System.out.print(i+" ");
			for(Player player: game.getPlayers()){
				if(game.getBoard().getSquares()[i].hasPiece(player.getPiece())){
					System.out.print(player.getName()+" ");
				}
			}
		}
	}
	
}
