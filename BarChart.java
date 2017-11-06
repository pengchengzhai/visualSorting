import java.awt.Graphics2D;

public class BarChart
{
   private int width;
   private int height;
   private DataItem[] data;
   public BarChart(int aWidth, int aHeight, DataItem data[])
   {
      width = aWidth;
      height = aHeight;
      this.data = data;
      

   }

   public void draw(Graphics2D g2)
   {
      int i = 0;
      int max = 0;

      for (DataItem wrapper : data)
         if(max < wrapper.getNumber())
            max = wrapper.getNumber();

      int xwidth = width - 1;
      int yheight = height - 1;

      int xleft = 0;
      for (i = 0; i < data.length; i++)
      {
         int xright = xwidth * (i + 1) / data.length;
         int barWidth = xwidth / data.length;
         int barHeight = (int) Math.round(yheight * data[i].getNumber() / max);

         g2.setColor(data[i].getColor());
         g2.fillRect(xleft, yheight - barHeight,
                 barWidth, barHeight);


         xleft = xright;
      }
   }
}