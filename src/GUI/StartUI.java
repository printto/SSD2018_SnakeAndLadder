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

public class StartUI extends JFrame {
	
	JButton twoPlayer;
	JButton threePlayer;
	JButton fourPlayer;

	public StartUI(){
		initComponents();
	}

	private void initComponents() {
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
	
	public void start(){
		this.show();
	}
	
	class GameCreateListener implements ActionListener{
		int player = 0;
		public GameCreateListener(int player){
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
	
	public static void main(String[] args){
		StartUI ui = new StartUI();
		ui.start();
	}
	
}
