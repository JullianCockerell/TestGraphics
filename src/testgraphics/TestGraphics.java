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
      
      BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      Graphics2D shotGraph = bi.createGraphics();
      shotGraph = drawGraph(shots, shotGraph, height, width, mapSize, shotMax);
      ImageIO.write(bi, "PNG", new File("ShotGraph.PNG"));

    } catch (IOException ie) {
      ie.printStackTrace();
    }

  }
  
  static int convertCo(int yLevel, int height)
  {
      return height - (20 + yLevel);
  }
  
  static Graphics2D drawGraph(int array[], Graphics2D win, int height, int width, int mapSize, int maxVal)
  {
      int gap = (int)((width - 40.0) / (mapSize + 1));
      win.setPaint(Color.WHITE);
      win.fillRect(0, 0, width, height);
      Font titleFont = new Font("TimesRoman", Font.BOLD, 20);
      win.setFont(titleFont);
      String message = "Shots per Game";
      FontMetrics fontMetrics = win.getFontMetrics();
      int stringWidth = fontMetrics.stringWidth(message);
      int stringHeight = fontMetrics.getAscent();
      win.setPaint(Color.BLACK);
      win.drawRect(0, 0, width - 1, height - 1);
      win.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4 - 130);
      win.drawRect(20, 30, width - 40, height - 50);
      int oldX = 0, oldY = 0;
      int xLevel = 20 + gap;
      Font numFont = new Font("TimesRoman", Font.PLAIN, 10);
      win.setFont(numFont);
      
      //converts shot count and draws dots and lines
      for(int i = 0; i < mapSize; i++)
      {
          double shotMaxDouble = (double) maxVal;
          int yLevel = (int) ((array[i]/shotMaxDouble)*(height - 70));
          yLevel = convertCo(yLevel, height);
          win.setPaint(Color.DARK_GRAY);
          win.drawLine(20, yLevel, width - 20, yLevel);
          String num = "" + array[i];
          win.drawString(num, 10, yLevel);
          win.setPaint(Color.BLACK);
          win.fillOval(xLevel - 5, yLevel - 5, 10, 10);
          if(i > 0)
          {
              win.drawLine(oldX, oldY, xLevel, yLevel);
          }
          if(i == mapSize - 1)
          {
              win.drawLine(20, height - 20, width - 20, height - 20);
          }
          oldX = xLevel;
          oldY = yLevel;
          xLevel = xLevel + gap;
      }
      return win;
  }
}