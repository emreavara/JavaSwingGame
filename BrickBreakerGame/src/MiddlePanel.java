import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class MiddlePanel extends JPanel implements KeyListener, ActionListener{
	/**
	 * Create Middle Panel of the game and draw BrickBreaker Game
	 */
	private static final long serialVersionUID = -9221607621155120740L;
	GameData gameData = GameData.getInstance();
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
		
		graphics.setColor(Color.black);
		graphics.fillRect(gameData.paddlePosX, gameData.paddlePosY, gameData.paddleWidth, gameData.paddleHeight);
		
		graphics.setColor(Color.red);
		graphics.fillOval(gameData.ballPosX, gameData.ballPosY, gameData.ballDiameter, gameData.ballDiameter);
		
		gameData.starImage.paintIcon(this, graphics, gameData.starPosX, gameData.starPosY);
		gameData.ufoImage.paintIcon(this, graphics, gameData.ufoPosX, gameData.ufoPosY);
		gameData.meteorImage.paintIcon(this, graphics, gameData.meteorPosX, gameData.meteorPosY);
		
		graphics.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(gameData.gameStatus == GameStatus.playing) {
			gameData.moveBall();
			
			gameData.paddleRect = new Rectangle(gameData.paddlePosX, gameData.paddlePosY, gameData.paddleWidth, gameData.paddleHeight);
			gameData.ballRect   = new Rectangle(gameData.ballPosX, gameData.ballPosY, gameData.ballDiameter, gameData.ballDiameter);
				
			
			if(gameData.ballRect.intersects(gameData.paddleRect)) {
				// increase score when the ball hits the paddle
				gameData.score++;
				if(gameData.ballPosX + (gameData.ballDiameter-1) <= gameData.paddlePosX ||  gameData.ballPosX + 1 >= gameData.paddlePosX + gameData.paddleWidth) {
					gameData.ballVelocityX = -gameData.ballVelocityX;
				} else {
					gameData.ballVelocityY = -gameData.ballVelocityY;
				}
			}
			if(gameData.ballRect.intersects(gameData.starRect)) {
				if(gameData.ballPosX + (gameData.ballDiameter-1) <= gameData.starPosX ||  gameData.ballPosX + 1 >= gameData.starPosX + gameData.starWidth) {
					gameData.ballVelocityX = -gameData.ballVelocityX;
				} else {
					gameData.ballVelocityY = -gameData.ballVelocityY;
				}
				
			}
			if(gameData.ballRect.intersects(gameData.ufoRect)) {
				if(gameData.ballPosX + (gameData.ballDiameter-1) <= gameData.ufoPosX ||  gameData.ballPosX + 1 >= gameData.ufoPosX + gameData.ufoWidth) {
					gameData.ballVelocityX = -gameData.ballVelocityX;
				} else {
					gameData.ballVelocityY = -gameData.ballVelocityY;
				}
				
			}
			if(gameData.ballRect.intersects(gameData.meteorRect)) {
				if(gameData.ballPosX + (gameData.ballDiameter-1) <= gameData.meteorPosX ||  gameData.ballPosX + 1 >= gameData.meteorPosX + gameData.meteorWidth) {
					gameData.ballVelocityX = -gameData.ballVelocityX;
				} else {
					gameData.ballVelocityY = -gameData.ballVelocityY;
				}
				
			}
			
		}
		
		
		
		
		repaint();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		gameData.gameStatus = GameStatus.playing;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			gameData.movePaddleLeft();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gameData.movePaddleRight();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

	
}