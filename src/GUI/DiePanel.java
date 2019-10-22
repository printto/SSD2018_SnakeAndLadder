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

/**
 * This object is a panel with Roll Die button.
 * This object is responsible for roll die button with action listener.
 * At first, this object is also responsible for storing the previous rolled faces of the die.
 * @author Pappim Pipatkasrira
 * @version 1.0
 * @since May 12, 2018
 */
public class DiePanel extends JPanel{
	
	private Game game;
	private JLabel number = new JLabel("Last roll: 0", SwingConstants.CENTER);
	private JButton roll_btn;
	
	/**
	 * Create a die panel.
	 * @param game Game to link with this die panel.
	 */
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
	
	public void setEnable(){
		roll_btn.setEnabled(true);
	}
	
	/**
	 * Roll die button action listener.
	 * @author Pappim Pipatkasrira
	 * @version 1.0
	 * @since May 12, 2018
	 */
	class rollDie implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int temp = game.currentPlayerRollDice();
			number.setText("Last roll: " + temp);
			roll_btn.setEnabled(false);
		}
	}
	
}
