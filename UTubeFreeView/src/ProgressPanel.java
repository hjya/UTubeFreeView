import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ProgressPanel extends JPanel
{
	private int width = 0;
	
	public ProgressPanel()
	{
		this.setPreferredSize(new Dimension(0, 15));
		this.setBackground(new Color(65, 105, 225));
	}
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int mwidth =  (this.getWidth() * width)/100;
        g.setColor(new Color(65, 105, 225));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(new Color(255, 204, 102));
        g.fillRect(0, 0, mwidth, this.getHeight());
        System.out.println("repainted ==================== ");
        
    }
}