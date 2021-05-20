import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

enum GameStatus{
	pause,
	playing,
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
	
	GameStatus gameStatus = GameStatus.pause;
	
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
	int middlePanelHeight = mainWindowHeight - (upperPanelHeight + lowerPanelHeight + 35 );
	
	// Game
	int score           = 0;
	int remainingLife   = 3;
	int delay           = 20;
	int gameDuration    = 60 ;
	int gameLevel       = 1;
	int passedTime      = 0;
	int passedTimeTemp  = 0;
	int remainingTime   = gameDuration;
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
	
	int maxIconRangeX   = 850;
	int maxIconRangeY   = 150;
	int iconMargin      = 70;
	
	int starPosX        = random.nextInt(maxIconRangeX) + iconMargin;
	int starPosY 		= random.nextInt(maxIconRangeY) + iconMargin;
	int starWidth		= starImage.getIconWidth();
	int starHeight      = starImage.getIconHeight();
	
	int ufoPosX         = random.nextInt(maxIconRangeX) + iconMargin;
	int ufoPosY 		= random.nextInt(maxIconRangeY) + iconMargin;
	int ufoWidth		= ufoImage.getIconWidth();
	int ufoHeight       = ufoImage.getIconHeight();
	
	int meteorPosX      = random.nextInt(maxIconRangeX) + iconMargin;
	int meteorPosY 		= random.nextInt(maxIconRangeY) + iconMargin;
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
		gameStatus       = GameStatus.pause;
		score            = 0;
		remainingLife    = 3;
		
		// Ball
		ballVelocityX    = 4;
		ballVelocityY    = 1;
		ballPosX         = 10;
		ballPosY         = 10;
		
		// Paddle
		paddlePosX	     = mainWindowWidth/2 - paddleWidth/2;
		paddlePosY       = middlePanelHeight - 20;
		
		// Icons
		starPosX        = random.nextInt(maxIconRangeX) + iconMargin;
		starPosY 		= random.nextInt(maxIconRangeY) + iconMargin;
		
		ufoPosX         = random.nextInt(maxIconRangeX) + iconMargin;
		ufoPosY 		= random.nextInt(maxIconRangeY) + iconMargin;
		
		meteorPosX      = random.nextInt(maxIconRangeX) + iconMargin;
		meteorPosY 		= random.nextInt(maxIconRangeY) + iconMargin;
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
		
		switch(gameLevel) {
		case 1:
			ballPosX = ballPosX + ballVelocityX;
			ballVelocityY = ballVelocityY - ballVelRatio*gravity*velocityRatio;
			ballPosY = (int) (ballPosY - ballVelocityY);
			break;
		case 2:
			ballPosX = ballPosX + ballVelocityX;
			ballVelocityY = ballVelocityY - ballVelRatio*gravity*velocityRatio*1.5f;
			ballPosY = (int) (ballPosY - ballVelocityY);
			break;
		}
		
		
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
		} else if (gameStatus == GameStatus.pause){
			gameStatus     = GameStatus.playing;
			timerFlag      = true;
		} else if((gameStatus == GameStatus.gameOver)) {
			resetGame();
			gameStatus     = GameStatus.playing;
			timerFlag      = true;
			
		}
		
	}
}