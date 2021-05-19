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
	
	public MiddlePanel() {
		setPreferredSize(new Dimension(gameData.middlePanelWidth, gameData.middlePanelHeight));
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		gameData.timer = new Timer(gameData.delay, this);
		gameData.timer.start();
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
		
		System.out.println("start Time : "+ gameData.startGameTime );
		System.out.println("current Time : "+ System.currentTimeMillis() );
		System.out.println("remaining Time : ");
		
		graphics.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gameData.timer.start();
		if(gameData.remainingLife == 0) {
			gameData.gameStatus = GameStatus.gameOver;
		}
		if (gameData.remainingTime <= 0) {
			gameData.gameLevel = 2;
			gameData.remainingTime = 0;
		}
		if(gameData.gameStatus == GameStatus.playing) {
			if(gameData.timerFlag) {
				gameData.startGameTime = System.currentTimeMillis();
				gameData.timerFlag = false;
			}
			gameData.passedTimeTemp = (int)((System.currentTimeMillis() - gameData.startGameTime)/1000); // Millisecond to second
			gameData.remainingTime = gameData.gameDuration - (gameData.passedTime + gameData.passedTimeTemp);
			gameData.moveBall();
			
			gameData.paddleRect = new Rectangle(gameData.paddlePosX, gameData.paddlePosY, gameData.paddleWidth, gameData.paddleHeight);
			gameData.ballRect   = new Rectangle(gameData.ballPosX, gameData.ballPosY, gameData.ballDiameter, gameData.ballDiameter);
				
			
			if(gameData.ballRect.intersects(gameData.paddleRect)) {
				gameData.isBallIntersect = true;
				// increase score when the ball hits the paddle
				gameData.score++;
				if(gameData.ballPosX + (gameData.ballDiameter-1) <= gameData.paddlePosX ||  gameData.ballPosX + 1 >= gameData.paddlePosX + gameData.paddleWidth) {
					gameData.ballVelocityX = -gameData.ballVelocityX;
				} else {
					gameData.ballVelocityY = -gameData.ballVelocityY;
				}
			} else if(gameData.ballRect.intersects(gameData.starRect)) {
				if(gameData.isBallIntersect == false) {
					gameData.score += gameData.bonusScore;
				}
				gameData.isBallIntersect = true;
			} else if(gameData.ballRect.intersects(gameData.ufoRect)) {
				if(gameData.isBallIntersect == false) {
					gameData.remainingLife--;
				}
				gameData.isBallIntersect = true;
			} else if(gameData.ballRect.intersects(gameData.meteorRect)) {
				if(gameData.isBallIntersect == false) {
					gameData.ballVelRatio = gameData.ballVelRatio*1.2f;
				}
				gameData.isBallIntersect = true;
			} else {
				gameData.isBallIntersect = false;
			}
			
		} else {
			gameData.gameStatus = GameStatus.gameOver;
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(gameData.gameStatus != GameStatus.pause) {
				gameData.gameStatus = GameStatus.playing;
			}
		}
		if(gameData.gameStatus == GameStatus.playing) {
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				if(gameData.gameStatus == GameStatus.playing) {
					gameData.movePaddleLeft();
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if(gameData.gameStatus == GameStatus.playing) {
					gameData.movePaddleRight();
				}
			}
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

	
}