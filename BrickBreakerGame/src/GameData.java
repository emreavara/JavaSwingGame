import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

enum GameStatus{
	idle,
	playing,
	pause,
	gameOver
};


public class GameData{
	
	/*
	 * Game Data initialization of the game
	 * 
	 */
	private static GameData gameData;

	private GameData() {}
	public static GameData getInstance() {
		if(gameData == null) {
			gameData = new GameData();
		}
		return gameData;
	}
	
	GameStatus gameStatus = GameStatus.idle;
	
	Timer timer;
	Long startGameTime;
	
	
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
	int remainingLife   = 3;
	int delay           = 20;
	int gameDuration    = 60;
	int passedTime      = 0;
	int passedTimeTemp  = 0;
	float gravity       = 9.8f;
	float velocityRatio = 1/19.8f;
	boolean timerFlag   = true;
	boolean isBallIntersect = false;
	
	// Score
	int scoreX          = 75;
	int scoreY          = 60;
	int bonusScore      = 5;
	
	// Life
	int remainingLifeX  = mainWindowWidth/2 - 50;
	int remainingLifeY  = 60;
	
	// Image Icons
	ImageIcon starImage = new ImageIcon("img/star.png");
	ImageIcon ufoImage  = new ImageIcon("img/ufo.png");
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
	int ballDiameter     = 2*ballRadius;
	int ballVelocityX    = 4;
	float ballVelocityY  = 1;
	int ballPosX         = 10;
	int ballPosY         = 10;
	float ballVelRatio   = 1.0f;
	
	// Paddle
	
	int paddleHeight     = 10;
	int paddleWidth      = 120;
	int paddleVelocity   = 30;
	int paddlePosX	     = mainWindowWidth/2 - paddleWidth/2;
	int paddlePosY       = middlePanelHeight - 20;
	
	// Rectangles
	Rectangle paddleRect = new Rectangle(paddlePosX, paddlePosY, paddleWidth, paddleHeight);
	Rectangle starRect   = new Rectangle(starPosX, starPosY, starWidth, starHeight);;
	Rectangle ufoRect    = new Rectangle(ufoPosX, ufoPosY, ufoWidth, ufoHeight);;
	Rectangle meteorRect = new Rectangle(meteorPosX, meteorPosY, meteorWidth, meteorHeight);;
	Rectangle ballRect   = new Rectangle(ballPosX, ballPosY, ballRadius, ballRadius);
		
	public void resetGame() {
		// Game
		gameStatus       = GameStatus.idle;
		score            = 0;
		remainingLife    = 3;
		
		// Ball
		ballVelocityX    = 4;
		ballVelocityY    = 1;
		ballPosX         = 10;
		ballPosY         = 10;
	}
	
	public void movePaddleLeft() {
		//gameStatus = GameStatus.playing;
		if(paddlePosX - paddleVelocity < 0) {
			paddlePosX = 0;
		} else {
			paddlePosX = paddlePosX - paddleVelocity;
		}
		
	}
	
	public void movePaddleRight() {
		//gameStatus = GameStatus.playing;
		if(paddlePosX + paddleVelocity >  middlePanelWidth - paddleWidth) {
			paddlePosX =  middlePanelWidth - paddleWidth;
		} else {
			paddlePosX = paddlePosX + paddleVelocity;
		}
	}
	
	public void moveBall() {
		//gameStatus = GameStatus.playing;
		
		ballPosX = ballPosX + ballVelocityX;
		ballVelocityY = ballVelocityY - ballVelRatio*gravity*velocityRatio;
		ballPosY = (int) (ballPosY - ballVelocityY);
		if(ballPosX < 0) {
			ballVelocityX = -ballVelocityX;
		}
		if(ballPosX + 2*ballRadius > middlePanelWidth - ballDiameter) {
			ballVelocityX = -ballVelocityX;
		}
		if(ballPosY < 0) {
			ballVelocityY = 0;
			ballPosY      = 1;
		}
		if(ballPosY > middlePanelHeight) {
			remainingLife--;
			ballVelocityX    = 4;
			ballVelocityY    = 1;
			ballPosX         = 10;
			ballPosY         = 10;
			
		}
		
		
	}
	
	public void startStopTimer() {
		if(gameStatus == GameStatus.playing) {
			gameStatus     = GameStatus.pause;
			passedTime     += passedTimeTemp;
			passedTimeTemp = 0;
		} else {
			gameStatus     = GameStatus.playing;
			timerFlag      = true;
		}
		
	}
}