package GUI;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import engine.Game;
import engine.ObserverCodes;

public class ControllerPanel extends JPanel implements Observer{

	Game game;
	MainFrame frame;
	JTextArea textArea = new JTextArea("");
	
	public ControllerPanel(MainFrame frame , Game game){
		this.frame = frame;
		this.game = game;
		game.addObserver(this);
		initComponents();
	}

	private void initComponents() {
		JScrollPane pane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setWrapStyleWord(true);
		pane.setPreferredSize(new Dimension(200,400));
		this.add(pane);
		textArea.append("Game started with "+game.getPlayers().size()+" players.\n");
		update(null , ObserverCodes.PLAYER_CHANGED_STRING);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String){
			String temp = (String) arg;
			if(temp.equals(ObserverCodes.BOARD_UPDATED_STRING)){
				textArea.append(game.currentPlayerName() + " moved to " + game.currentPlayerPosition() + "\n");
			}
			if(temp.equals(ObserverCodes.DIE_ROLLED_STRING)){
				textArea.append("Die rolled: " + game.getDieFace() + "\n");
				game.currentPlayerMovePiece(game.getDieFace());
				if(game.isEnded()){
					textArea.append("\n" + game.currentPlayerName() + " wins~!\n");
					infoBox("Player "+game.currentPlayerName()+" wins~!","Game ended!");
					frame.end();
				}
				else if(game.currentPlayerIsAtWarp()){
					game.currentPlayerWarp(game.getWarpAtCurrentPosition());
					game.switchPlayer();
				}
				else{
					game.switchPlayer();
				}
			}
			if(temp.equals(ObserverCodes.PLAYER_CHANGED_STRING)){
				textArea.append("\n" + game.currentPlayerName() + "'s turn.\n");
				textArea.append( game.currentPlayerName() + " is at " + game.currentPlayerPosition() + ".\n");
			}
			if(temp.equals(ObserverCodes.PLAYER_WARP_STRING)){
				textArea.append( game.currentPlayerName() + " met the "+ game.getWarpAtCurrentPosition() + "\n");;
			}
		}
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	
	public void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
	
}
