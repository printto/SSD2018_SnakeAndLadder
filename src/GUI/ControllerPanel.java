package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
	PlayerPanel playerPanel = new PlayerPanel();
	
	public ControllerPanel(MainFrame frame , Game game){
		this.frame = frame;
		this.game = game;
		game.addObserver(this);
		initComponents();
	}

	private void initComponents() {
		JScrollPane pane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
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
				game.checkCurrentPlayerStatus();
				if(game.currentPlayerIsAtWarp()){
					game.currentPlayerWarp(game.getWarpAtCurrentPosition());
				}
				if(game.isEnded()){
					textArea.append("\n" + game.currentPlayerName() + " wins~!\n");
					infoBox("Player "+game.currentPlayerName()+" wins~!","Game ended!");
					frame.end();
				}
				else if(game.getDieFace() != 6 && !game.currentPlayer().isReverse()){
					game.switchPlayer();
				}
			}
			if(temp.equals(ObserverCodes.FREEZE_STRING)){
				textArea.append(game.currentPlayerName() + " is frozen.\n");
				game.switchPlayer();
			}
			if(temp.equals(ObserverCodes.REVERSE_STRING)){
				textArea.append(game.currentPlayerName() + " stepped on reverse buff.\n");
			}
			if(temp.equals(ObserverCodes.PLAYER_CHANGED_STRING)){
				textArea.append("\n" + game.currentPlayerName() + "'s turn.\n");
				textArea.append( game.currentPlayerName() + " is at " + game.currentPlayerPosition() + ".\n");
				if(game.currentPlayer().isFreeze()){
					textArea.append( game.currentPlayerName() + " is freezing.\n");
					game.currentPlayer().unfreeze();
					game.switchPlayer();
				}
				playerPanel.update();
			}
			if(temp.equals(ObserverCodes.PLAYER_WARP_STRING)){
				textArea.append( game.currentPlayerName() + " met the "+ game.getWarpAtCurrentPosition() + "\n");;
				game.checkCurrentPlayerStatus();
			}
		}
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	
	public void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
	
	public JPanel getPlayerPanel(){
		return playerPanel;
	}
	
	class PlayerPanel extends JPanel{
		
		JLabel currentPlayer = new JLabel("P1");
		Font font  = new Font("serif",Font.BOLD,30);
		
		public PlayerPanel(){
			initPlayerPanel();
		}

		private void initPlayerPanel() {
			this.add(currentPlayer);
			currentPlayer.setFont(font);
		}
		
		public void update(){
			String temp = game.currentPlayerName();
			switch(temp){
			case "P1":
				currentPlayer.setForeground(Color.RED);
				break;
			case "P2":
				currentPlayer.setForeground(Color.BLUE);
				break;
			case "P3":
				currentPlayer.setForeground(Color.GREEN);
				break;
			case "P4":
				currentPlayer.setForeground(Color.CYAN);
				break;
			default:
				currentPlayer.setForeground(Color.BLACK);
			}
			currentPlayer.setText(temp);
		}
		
	}
	
}
