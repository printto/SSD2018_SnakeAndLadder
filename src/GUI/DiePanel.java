package GUI;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import engine.Game;

public class DiePanel extends JPanel{
	
	Game game;
	JLabel number = new JLabel("Last roll: 0", SwingConstants.CENTER);
	JButton roll_btn;
	
	public DiePanel(Game game){
		this.game = game;
		initComponents();
	}

	private void initComponents() {
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
//		this.add(number);
		ImageIcon rollbtnImg = new ImageIcon(this.getClass().getResource("/img/rollbtn.png"));
		roll_btn = new JButton(rollbtnImg);
		roll_btn.addActionListener(new rollDie());
		this.add(roll_btn);
	}
	
	class rollDie implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int temp = game.currentPlayerRollDice();
			number.setText("Last roll: " + temp);
		}
	}
	
}
