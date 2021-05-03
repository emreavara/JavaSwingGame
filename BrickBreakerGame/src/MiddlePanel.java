import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MiddlePanel extends JPanel {
	GameData gameData = GameData.getInstance();
	ImageIcon starImage = new ImageIcon("img/star.png");
	ImageIcon ufoImage = new ImageIcon("img/ufo.png");
	ImageIcon meteorImage = new ImageIcon("img/meteor.png");
	
	public MiddlePanel() {
		setPreferredSize(new Dimension(gameData.middlePanelWidth, gameData.middlePanelHeight));
		
	}
	
	public void paint(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, gameData.middlePanelWidth, gameData.middlePanelHeight);
		
		graphics.setColor(Color.blue);
		graphics.fillRect(gameData.paddleX, gameData.paddleY, gameData.paddleWidth, gameData.paddleHeight);
		
		graphics.setColor(Color.MAGENTA);
		graphics.fillOval(gameData.ballPosX, gameData.ballPosY, gameData.ballRadius, gameData.ballRadius);
		
		starImage.paintIcon(this, graphics, gameData.starPosX, gameData.starPosY);
		ufoImage.paintIcon(this, graphics, gameData.ufoPosX, gameData.ufoPosY);
		meteorImage.paintIcon(this, graphics, gameData.meteorPosX, gameData.meteorPosY);
	}
}