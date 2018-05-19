package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Game;

/**
 * Start screen of the game GUI.
 * This object handles the game setup and initialize the GUI.
 * This object is the main class of the game.
 * @author Pappim Pipatkasrira
 * @version 1.0
 * @since May 12, 2018
 */
public class StartUI extends JFrame {
	
	private JButton twoPlayer;
	private JButton threePlayer;
	private JButton fourPlayer;

	/**
	 * Create game GUI.
	 */
	public StartUI(){
		initComponents();
	}

	private void initComponents() {
		this.setTitle("Snake and Ladder");
		JPanel wrapup = new JPanel();
		wrapup.setLayout(new BoxLayout(wrapup,BoxLayout.Y_AXIS));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel north = new JPanel();
		ImageIcon bannerImg = new ImageIcon(this.getClass().getResource("/img/banner.png"));
		JLabel banner = new JLabel(bannerImg);
		north.add(banner);
		wrapup.add(north);
		JPanel center = new JPanel();
		ImageIcon twoPlayerImg = new ImageIcon(this.getClass().getResource("/img/2p.png"));
		twoPlayer = new JButton(twoPlayerImg);
		ImageIcon threePlayerImg = new ImageIcon(this.getClass().getResource("/img/3p.png"));
		threePlayer = new JButton(threePlayerImg);
		ImageIcon fourPlayerImg = new ImageIcon(this.getClass().getResource("/img/4p.png"));
		fourPlayer = new JButton(fourPlayerImg);
		twoPlayer.addActionListener(new GameCreateListener(2));
		threePlayer.addActionListener(new GameCreateListener(3));
		fourPlayer.addActionListener(new GameCreateListener(4));
		center.add(twoPlayer);
		center.add(threePlayer);
		center.add(fourPlayer);
		wrapup.add(center);
		this.add(wrapup);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * Make GUI visible.
	 */
	public void start(){
		this.show();
	}
	
	/**
	 * ActionListener of the player amount selecting buttons.
	 * @author Pappim Pipatkasrira
	 * @version 1.0
	 * @since May 12, 2018
	 */
	class GameCreateListener implements ActionListener{
		private int player = 0;
		private GameCreateListener(int player){
			this.player = player;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			Game game = new Game(player);
			new MainFrame(game).start();
			setVisible(false);
			dispose();
		}
	}
	
	/**
	 * Main of the program.
	 * @param args
	 */
	public static void main(String[] args){
		new AudioController().startSound();
		StartUI ui = new StartUI();
		ui.start();		
		new AudioController().bgmStart();
	}
	
}
