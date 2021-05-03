import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

enum GameStatus{
	idle,
	playing,
	gameOver
};

public class BrickBreakerGame extends JFrame {

	public static void main(String[] args) {
		
		GameData gameData = GameData.getInstance();
		
		UpperPanel upperPanel   = new UpperPanel();
		MiddlePanel middlePanel = new MiddlePanel();
		LowerPanel lowerPanel   = new LowerPanel();
		
		
		JFrame gameWindow = new JFrame();
		gameWindow.setLayout(new BorderLayout());
		gameWindow.setTitle("Break Brick Game");
		gameWindow.setSize(gameData.mainWindowWidth, gameData.mainWindowHeight);
		gameWindow.setVisible(true);
		gameWindow.setResizable(false);
		
		gameWindow.add(upperPanel, BorderLayout.NORTH);
		gameWindow.add(middlePanel, BorderLayout.CENTER);
		gameWindow.add(lowerPanel, BorderLayout.SOUTH);
		
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}