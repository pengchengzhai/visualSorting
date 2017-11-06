import javax.swing.JComponent;

import java.awt.Graphics;
import java.awt.Graphics2D;


@SuppressWarnings("serial")
public class BarChartComponent extends JComponent
{
	private int totalNumbers = 250;
    private DataItem[] data = new DataItem[totalNumbers];
	
	public BarChartComponent(DataItem[] data) {
	      this.data = data;		
	}
	
	public void setData(DataItem[] data){
		this.data = data;
	}
	
    public void paintComponent(Graphics g)
    {
		Graphics2D g2 = (Graphics2D) g;
		BarChart c = new BarChart(getWidth(), getHeight(), data);
		c.draw(g2);
    }
    

    
    
    
}