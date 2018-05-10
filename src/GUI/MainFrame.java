package GUI;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.Game;
import engine.Player;

public class MainFrame extends JFrame{
	
	Game game;
	JButton restartBtn = new JButton("Restart");
	JButton quitBtn = new JButton("Quit");
	JPanel south;

	public MainFrame(Game game){
		this.game = game;
		initComponents();
	}

	private void initComponents() {
		BoardPanel boardUI = new BoardPanel(game);
		DiePanel dieUI = new DiePanel(game);
		ControllerPanel controllerUI = new ControllerPanel(this,game);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		JPanel center = new JPanel();
		center.add(boardUI);
		this.add(center,BorderLayout.CENTER);
		south = new JPanel();
		south.add(dieUI);
		this.add(south,BorderLayout.SOUTH);
		JPanel east = new JPanel();
		east.add(controllerUI);
		this.add(east, BorderLayout.EAST);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public void start(){
		this.show();
	}
	
	public void end(){
		this.remove(south);
		south = new JPanel();
		south.add(restartBtn);
		south.add(quitBtn);
		restartBtn.addActionListener(new EndListener(EndListener.RESTART));
		quitBtn.addActionListener(new EndListener(EndListener.QUIT));
		this.add(south,BorderLayout.SOUTH);
		pack();
	}
	
	class EndListener implements ActionListener{
		
		final public static int RESTART = 0;
		final public static int QUIT = 0;
		int mode;
		
		public EndListener(int mode){
			this.mode = mode;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(mode == RESTART){
				new StartUI().start();
			}
			setVisible(false);
			dispose();
		}
	}
	
}
