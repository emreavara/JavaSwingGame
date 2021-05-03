import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class UpperPanel extends JPanel{
	private int upperPanelWidth  = 1024;
	private int upperPanelHeight = 100;
	
	public UpperPanel() {
		setPreferredSize(new Dimension(upperPanelWidth, upperPanelHeight));
		
	}
	
	public void paint(Graphics graphics) {
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(0, 0, upperPanelWidth, upperPanelHeight);
	}
}