package engine;
import java.util.ArrayList;

import javax.swing.JLabel;

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
