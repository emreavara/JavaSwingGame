import java.util.Random;

public class GameData{
	private static GameData gameData;
	
	GameStatus gameStatus = GameStatus.idle;
	
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
	
	// Ball
	int ballRadius       = 10;
	int ballVelocityX    = 4;
	int ballVelocityY    = 1;
	int ballPosX         = 10;
	int ballPosY         = 10;
	
	// Paddle
	
	int paddleHeight     = 10;
	int paddleWidth      = 120;
	int paddleVelocity   = 30;
	int paddlePosX	     = mainWindowWidth/2;
	int paddlePosY       = middlePanelHeight - 20;
	
	// icons
	
	Random random = new Random();
	
	int starPosX         = random.nextInt(950) + 50;
	int starPosY 		 = random.nextInt(200) + 50;
	int starWidth		 = mainWindowWidth/10;
	int starHeight       = mainWindowHeight/20;
	
	
	int ufoPosX         = random.nextInt(950) + 50;
	int ufoPosY 		 = random.nextInt(200) + 50;
	int ufoWidth		 = mainWindowWidth/10;
	int ufoHeight       = mainWindowHeight/20;
	
	int meteorPosX         = random.nextInt(950) + 50;
	int meteorPosY 		 = random.nextInt(200) + 50;
	int meteorWidth		 = mainWindowWidth/10;
	int meteorHeight       = mainWindowHeight/20;
	
		
	private GameData() {}
	public static GameData getInstance() {
		if(gameData == null) {
			gameData = new GameData();
		}
		return gameData;
	}
	
	public void resetGame() {
		// Game
		gameStatus = GameStatus.idle;
		score = 0;
		remaingLife = 3;
		
		// Ball
		ballVelocityX    = 4;
		ballVelocityY    = 1;
		ballPosX         = 10;
		ballPosY         = 10;
	}
	
	public void movePaddleLeft() {
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
}