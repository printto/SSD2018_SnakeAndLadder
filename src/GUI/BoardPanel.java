package GUI;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import engine.Die;
import engine.Game;
import engine.ObserverCodes;
import engine.Player;

/**
 * This is the board UI of the game.
 * @author Pappim Pipatkasrira
 * @version 1.0
 * @since May 11, 2018
 */
public class BoardPanel extends JPanel implements Observer{

	private Game game;
	private JPanel[] panels = new JPanel[64];
	//	JLabel[] numLabel = new JLabel[64];
	private JLabel[] playerLabel;

	/**
	 * Create board panel
	 * @param game Game to link with the board.
	 */
	public BoardPanel(Game game){
		this.game = game;
		game.addObserver(this);
		game.getBoard().addObserver(this);
		initComponents();
	}
	
	private void initComponents() {
		this.setLayout(new GridLayout(8,8));
		this.setPreferredSize(new Dimension(400,400));
		initColor();
		updateBoard();
	}

	private void initColor() {
		Font font = new Font("Courier", Font.BOLD,16);
		playerLabel = new JLabel[game.getPlayers().size()];
		Color[] colors = {
				Color.RED,
				Color.BLUE,
				Color.GREEN,
				Color.CYAN};
		for(int i = 0 ; i < playerLabel.length ; i++){
			playerLabel[i] = new JLabel(game.getPlayers().get(i).getName());
			playerLabel[i].setForeground(colors[i]);
			playerLabel[i].setFont(font);
		}
		for(int i = 0 ; i < 64 ; i++){
			panels[i] = new JPanel();
			panels[i].setLayout(new FlowLayout());
			panels[i].setOpaque(true);
			panels[i].setBackground(new Color(0,0,0,0));
//			if(i%2 == 0){
//				panels[i].setBackground(new Color(200,200,200));
//			}
//			else{
//				panels[i].setBackground(Color.white);
//			}
			this.add(panels[i]);
//			numLabel[i] = new JLabel(i+"");
		}
	}

	/**
	 * Update informations displayed on the board.
	 */
	public void updateBoard(){
		clearPanel();
		for(int i = 63 ; i >= 56 ; i--){
			addPanel(i);
		}
		for(int i = 48 ; i < 56 ; i++){
			addPanel(i);
		}
		for(int i = 47 ; i >= 40 ; i--){
			addPanel(i);
		}
		for(int i = 32 ; i < 40 ; i++){
			addPanel(i);
		}
		for(int i = 31 ; i >= 24 ; i--){
			addPanel(i);
		}
		for(int i = 16 ; i < 24 ; i++){
			addPanel(i);
		}
		for(int i = 15 ; i >= 8 ; i--){
			addPanel(i);
		}
		for(int i = 0 ; i < 8 ; i++){
			addPanel(i);
		}
		this.revalidate();
		this.repaint();
	}
	
	private void addPanel(int i){
		//		panels[i].add(numLabel[i]);
		for(Player player: game.getPlayers()){
			if(game.getBoard().getSquares()[i].hasPiece(player.getPiece())){
				for(JLabel pl : playerLabel){
					if(player.getName().equals(pl.getText())){
						panels[i].add(pl);
					}
				}
			}
		}
		this.add(panels[i]);
	}

	private void clearPanel() {
		for(JPanel panel : panels){
			panel.removeAll();;
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String){
			String temp = (String) arg;
			if(temp.equals(ObserverCodes.WALKING_STRING)){
				updateBoard();
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon bg = new ImageIcon(this.getClass().getResource("/img/board-1.png"));
		g.drawImage(bg.getImage(), 0, 0, null);
	}

}
