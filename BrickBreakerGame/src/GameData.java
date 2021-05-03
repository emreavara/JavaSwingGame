import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class GameData{
	private static GameData gameData;
	
	GameStatus gameStatus = GameStatus.idle;
	
	private GameData() {}
	public static GameData getInstance() {
		if(gameData == null) {
			gameData = new GameData();
		}
		return gameData;
	}
	
	// Main Window
	int mainWindowWidth  = 1024;
	int mainWindowHeight = 768;
	
	// Upper Panel
	int upperPanelWidth  = mainWindowWidth;
	int upperPanelHeight = 100;
	
	// Lower Panel
	int lowerPanelWidth  = mainWindowWidth;
	int lowerPanelHeight = 100;
	
	// Middle Panel
	int middlePanelWidth  = mainWindowWidth;
	int middlePanelHeight = mainWindowHeight - (upperPanelHeight + lowerPanelHeight + 35 ); // I have no idea why 35 ???
	
	// Game
	int score           = 0;
	int remaingLife     = 3;
	int delay           = 20;
	
	// Image Icons
	ImageIcon starImage = new ImageIcon("img/star.png");
	ImageIcon ufoImage = new ImageIcon("img/ufo.png");
	ImageIcon meteorImage = new ImageIcon("img/meteor.png");
	
	// icons
	
	Random random = new Random();
	
	int starPosX        = random.nextInt(950) + 50;
	int starPosY 		= random.nextInt(200) + 50;
	int starWidth		= starImage.getIconWidth();
	int starHeight      = starImage.getIconHeight();
	
	int ufoPosX         = random.nextInt(950) + 50;
	int ufoPosY 		= random.nextInt(200) + 50;
	int ufoWidth		= ufoImage.getIconWidth();
	int ufoHeight       = ufoImage.getIconHeight();
	
	int meteorPosX      = random.nextInt(950) + 50;
	int meteorPosY 		= random.nextInt(200) + 50;
	int meteorWidth		= meteorImage.getIconWidth();
	int meteorHeight    = meteorImage.getIconHeight();
		
	
	// Ball
	int ballRadius       = 10;
	int ballVelocityX    = 4;
	int ballVelocityY    = 5;
	int ballPosX         = 10;
	int ballPosY         = 10;
	
	// Paddle
	
	int paddleHeight     = 10;
	int paddleWidth      = 120;
	int paddleVelocity   = 30;
	int paddlePosX	     = mainWindowWidth/2;
	int paddlePosY       = middlePanelHeight - 20;
	
	// Rectangles
	Rectangle paddleRect = new Rectangle(paddlePosX, paddlePosY, paddleWidth, paddleHeight);
	Rectangle starRect   = new Rectangle(starPosX, starPosY, starWidth, starHeight);;
	Rectangle ufoRect    = new Rectangle(ufoPosX, ufoPosY, ufoWidth, ufoHeight);;
	Rectangle meteorRect = new Rectangle(meteorPosX, meteorPosY, meteorWidth, meteorHeight);;
	Rectangle ballRect   = new Rectangle(ballPosX, ballPosY, ballRadius, ballRadius);
		
	public void resetGame() {
		// Game
		gameStatus = GameStatus.idle;
		score = 0;
		remaingLife = 3;
		
		// Ball
		ballVelocityX    = 4;
		ballVelocityY    = 3;
		ballPosX         = 10;
		ballPosY         = 10;
	}
	
	public void movePaddleLeft() {
		gameStatus = GameStatus.playing;
		if(paddlePosX < 0) {
			paddlePosX = 0;
		} else {
			paddlePosX = paddlePosX - paddleVelocity;
		}
		
	}
	
	public void movePaddleRight() {
		gameStatus = GameStatus.playing;
		if(paddlePosX > 904) {
			paddlePosX = 904;
		} else {
			paddlePosX = paddlePosX + paddleVelocity;
		}
	}
	
	public void moveBall() {
		gameStatus = GameStatus.playing;
		
		ballPosX = ballPosX + ballVelocityX;
		ballPosY = ballPosY - ballVelocityY;
		if(ballPosX < 0) {
			ballVelocityX = -ballVelocityX;
		}
		if(ballPosX + ballRadius > 1014) {
			ballVelocityX = -ballVelocityX;
		}
		if(ballPosY < 0) {
			ballVelocityY = -ballVelocityY;
		}
		
	}
}