package game;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Window extends JPanel implements ActionListener {
	public static Font defaultFont = new Font("Arial", Font.PLAIN, 10);
	JFrame frame = new JFrame();
	Timer drawUpdate = new Timer(1000/60, this);
	Game game = new Game();
	Input peripherals = new Input();
	void setup(String mode) {
		frame.add(this);
		frame.setPreferredSize(new Dimension(1280,800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Retrovirus");
		frame.addKeyListener(peripherals);
		frame.setResizable(false);
		frame.pack();
		
		if(mode == "debug") {
			game.start(new SinglePlayerWorld());
			drawUpdate.start();
		} else {
			
		}
		
		frame.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Game.draw(g);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}
}
