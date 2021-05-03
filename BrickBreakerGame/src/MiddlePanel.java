import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MiddlePanel extends JPanel implements KeyListener, ActionListener{
	GameData gameData = GameData.getInstance();
	ImageIcon starImage = new ImageIcon("img/star.png");
	ImageIcon ufoImage = new ImageIcon("img/ufo.png");
	ImageIcon meteorImage = new ImageIcon("img/meteor.png");
	
	private Timer timer;
	
	public MiddlePanel() {
		setPreferredSize(new Dimension(gameData.middlePanelWidth, gameData.middlePanelHeight));
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(gameData.delay, this);
		timer.start();
		
	}
	
	public void paint(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, gameData.middlePanelWidth, gameData.middlePanelHeight);
		
		graphics.setColor(Color.blue);
		graphics.fillRect(gameData.paddlePosX, gameData.paddlePosY, gameData.paddleWidth, gameData.paddleHeight);
		
		graphics.setColor(Color.MAGENTA);
		graphics.fillOval(gameData.ballPosX, gameData.ballPosY, gameData.ballRadius, gameData.ballRadius);
		
		starImage.paintIcon(this, graphics, gameData.starPosX, gameData.starPosY);
		ufoImage.paintIcon(this, graphics, gameData.ufoPosX, gameData.ufoPosY);
		meteorImage.paintIcon(this, graphics, gameData.meteorPosX, gameData.meteorPosY);
		
		graphics.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			gameData.movePaddleLeft();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gameData.movePaddleRight();
		}
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}
}