/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgraphics;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;

public class TestGraphics {
  static public void main(String args[]) throws Exception {
    try {
      int width = 300, height = 300;
      HashMap<Integer, RLRecord> map = new HashMap<Integer, RLRecord>();
      RLRecord zero = new RLRecord(0,0,0,0,0,0,0);
      RLRecord one = new RLRecord(1,1,1,1,1,1,1);
      RLRecord two = new RLRecord(2,2,2,2,2,2,2);
      RLRecord three = new RLRecord(3,3,3,3,3,3,3);
      RLRecord four = new RLRecord(4,4,4,4,4,4,4);
      map.put(0, zero);
      map.put(1, one);
      map.put(2, two);
      map.put(3, three);
      map.put(4, four);
      
      //read shots into array
      int mapSize = map.size();
      int shotMax = 0;
      int shots[] = new int[mapSize];
      for(int i = 0; i < mapSize; i++)
      {
          RLRecord hold = map.get(i);
          shots[i] = hold.shots;
          if(shots[i] > shotMax){shotMax = shots[i];}
      }
      
      int gap = (int)((width - 40.0) / (mapSize + 1));
      
      BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      Graphics2D ig2 = bi.createGraphics();

      Font font = new Font("TimesRoman", Font.BOLD, 20);
      ig2.setFont(font);
      String message = "Shots per Game";
      FontMetrics fontMetrics = ig2.getFontMetrics();
      int stringWidth = fontMetrics.stringWidth(message);
      int stringHeight = fontMetrics.getAscent();
      ig2.setPaint(Color.BLACK);
      ig2.drawRect(0, 0, width, height);
      ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4 - 130);
      ig2.drawRect(20, 30, width - 40, height - 50);
      int oldX = 0, oldY = 0;
      //draw shot dots
      int xLevel = 20 + gap;
      for(int i = 0; i < mapSize; i++)
      {
          double shotMaxDouble = (double) shotMax;
          int yLevel = (int) (shots[i]/shotMaxDouble)*(height - 50);
          double testY = (shots[i]/shotMaxDouble)*(height - 50);
          int testY2 = testY.intValue();
 
         ig2.fillOval(xLevel - 5, yLevel - 5, 10, 10);
          xLevel = xLevel + gap;
          if(i > 0)
          {
              ig2.drawLine(oldX, oldY, xLevel, yLevel);
          }
          oldX = xLevel;
          oldY = yLevel;
      }

      ImageIO.write(bi, "PNG", new File("testpic.PNG"));

    } catch (IOException ie) {
      ie.printStackTrace();
    }

  }
}