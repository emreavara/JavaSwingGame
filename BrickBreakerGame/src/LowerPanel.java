import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

public class LowerPanel extends JPanel{
	/**
	 *  Lower Panel implementation of the game to stop timer
	 */
	
	GameData gameData = GameData.getInstance();
	
	private static final long serialVersionUID = 1L;
	private int lowerPanelWidth  = 1024;
	private int lowerPanelHeight = 100;
	
	JButton startStopButton = new JButton("Start/Stop");
	
	public LowerPanel() {
		setPreferredSize(new Dimension(lowerPanelWidth, lowerPanelHeight));
		startStopButton.setFocusable(false);
		startStopButton.addActionListener(ae -> {
			gameData.startStopTimer();
		});
		add(startStopButton);
		setFocusable(false);
	}
	
	public void paintComponent(Graphics graphics) {
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(0, 0, lowerPanelWidth, lowerPanelHeight);
	}
}