package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import sun.audio.*;

import engine.Game;
import engine.ObserverCodes;
import engine.Warp;

/**
 * This object is responsible for controlling the game flows. This object also
 * acted as a JPanel so it can be used as a GUI. This object is mainly control
 * the game steps and link it to the GUI.
 * 
 * @author Pappim Pipatkasrira
 * @version 1.0
 * @since May 11, 2018
 */
public class Controller extends JPanel implements Observer {

	private Game game;
	private MainFrame frame;
	private JTextArea textArea = new JTextArea("");
	private PlayerPanel playerPanel = new PlayerPanel();
	private AudioController ac = new AudioController();

	/**
	 * Create control panel to link with the main frame.
	 * 
	 * @param frame
	 *            Main frame of the snake and ladder game.
	 * @param game
	 *            Game to control.
	 */
	public Controller(MainFrame frame, Game game) {
		this.frame = frame;
		this.game = game;
		game.addObserver(this);
		initComponents();
	}

	/**
	 * Create control panel without linking to any main frame.
	 * 
	 * @param game
	 *            Game to control.
	 */
	public Controller(Game game) {
		this(new MainFrame(game), game);
	}

	private void initComponents() {
		JScrollPane pane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		pane.setPreferredSize(new Dimension(200, 400));
		this.add(pane);
		textArea.append("Game started with " + game.getPlayers().size() + " players.\n");
		update(null, ObserverCodes.PLAYER_CHANGED_STRING);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			String temp = (String) arg;
			if (temp.equals(ObserverCodes.BOARD_UPDATED_STRING)) {
				textArea.append(game.currentPlayerName() + " moved to " + game.currentPlayerPosition() + "\n");
			}
			if (temp.equals(ObserverCodes.DIE_ROLLED_STRING)) {
				textArea.append("Die rolled: " + game.getDieFace() + "\n");
				game.currentPlayerMovePiece(game.getDieFace());
				ac.diceSound(game.getDieFace());
				game.checkCurrentPlayerStatus();
				if (game.currentPlayerIsAtWarp()) {
					Warp warp = game.getWarpAtCurrentPosition();
					if(warp.toString().equals("snake")){
						ac.snakeSound();
					}
					else{
						ac.ladderSound();
					}
					game.currentPlayerWarp(warp);
				}
				if (game.isEnded()) {
					ac.winSound();
					textArea.append("\n" + game.currentPlayerName() + " wins~!\n");
					infoBox("Player " + game.currentPlayerName() + " wins~!", "Game ended!");
					frame.end();
				} else if (game.currentPlayer().isFreeze() || (game.getDieFace() != 6 && !game.currentPlayer().isReverse())) {
					game.switchPlayer();
				}
			}
			if (temp.equals(ObserverCodes.FREEZE_STRING)) {
				textArea.append(game.currentPlayerName() + " is frozen.\n");
				ac.freezeSound();
			}
			if (temp.equals(ObserverCodes.REVERSE_STRING)) {
				textArea.append(game.currentPlayerName() + " stepped on reverse buff.\n");
				ac.reverseSound();
			}
			if (temp.equals(ObserverCodes.PLAYER_CHANGED_STRING)) {
				textArea.append("\n" + game.currentPlayerName() + "'s turn.\n");
				textArea.append(game.currentPlayerName() + " is at " + game.currentPlayerPosition() + ".\n");
				if (game.currentPlayer().isFreeze()) {
					textArea.append(game.currentPlayerName() + " is freezing.\n");
					game.currentPlayer().unfreeze();
					game.switchPlayer();
				}
				playerPanel.update();
			}
			if (temp.equals(ObserverCodes.PLAYER_WARP_STRING)) {
				textArea.append(game.currentPlayerName() + " met the " + game.getWarpAtCurrentPosition() + "\n");
				;
				game.checkCurrentPlayerStatus();
			}
		}
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}

	private void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Get another JPanel that displays the current player.
	 * 
	 * @return JPanel that displays the current player.
	 */
	public JPanel getPlayerPanel() {
		return playerPanel;
	}

	/**
	 * Panel that displays the current player.
	 * 
	 * @author Pappim Pipatkasrira
	 * @version 1.0
	 * @since May 12, 2018
	 */
	class PlayerPanel extends JPanel {

		JLabel currentPlayer = new JLabel("P1");
		Font font = new Font("serif", Font.BOLD, 30);

		private PlayerPanel() {
			initPlayerPanel();
		}

		private void initPlayerPanel() {
			this.add(currentPlayer);
			currentPlayer.setFont(font);
		}

		private void update() {
			String temp = game.currentPlayerName();
			switch (temp) {
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
