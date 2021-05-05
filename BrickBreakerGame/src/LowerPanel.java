import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class LowerPanel extends JPanel{
	/**
	 *  Lower Panel implementation of the game to stop timer
	 */
	private static final long serialVersionUID = 1L;
	private int lowerPanelWidth  = 1024;
	private int lowerPanelHeight = 100;
	
	public LowerPanel() {
		setPreferredSize(new Dimension(lowerPanelWidth, lowerPanelHeight));
		
	}
	
	public void paint(Graphics graphics) {
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(0, 0, lowerPanelWidth, lowerPanelHeight);
	}
}