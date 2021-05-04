import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class UpperPanel extends JPanel{
	GameData gameData = GameData.getInstance();
	
	public UpperPanel() {
		setPreferredSize(new Dimension(gameData.upperPanelWidth, gameData.upperPanelHeight));
	}
	
	public void paint(Graphics graphics) {
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(0, 0, gameData.upperPanelWidth, gameData.upperPanelHeight);
		
		
		graphics.setColor(Color.red);
		graphics.setFont(new Font("serif", Font.BOLD, 40));
		graphics.drawString(""+gameData.score, gameData.scoreX, gameData.scoreY);
		
		graphics.setColor(Color.red);
		graphics.setFont(new Font("serif", Font.BOLD, 40));
		graphics.drawString("♥".repeat(gameData.remainingLife), gameData.remainingLifeX, gameData.remainingLifeY);
		
		graphics.dispose();
		repaint();
	}
	
	
}