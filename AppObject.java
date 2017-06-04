package ojam.visualapp;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ojam1 on 04/05/2017.
 */

class AppObject
{
    private List<OPoint> objectPoint;
    private int xDir;
    private int yDir;
    private final int SIZE = 25;
    private boolean colourChanged;
    private Color colour;

    AppObject()
    {
      objectPoint = new ArrayList<>();
      colourChanged = false;
      this.colour = getRandomColour();
      xDir = 1;
      yDir = 1;
      int xStart = (int) (Math.random()*600);
      int yStart = (int) (Math.random()*600);
      objectPoint.add(new OPoint(xStart,yStart));
      for (int i = 1;i<SIZE;i++)
      {
         objectPoint.add(new OPoint(xStart-(i*5),yStart-(i*5)));
      }
    }

    void draw(Graphics g)
    {
        if(colourChanged)
        {
            this.colour = getRandomColour();
            colourChanged = false;
        }
        else g.setColor(this.colour);
        for(OPoint p: objectPoint)
        {
            g.fillOval(p.getX(),p.getY(),10,10);
        }
    }

    void move()
    {
        OPoint temp = objectPoint.get(0);
        OPoint newPoint = new OPoint(temp.getX()+(xDir*5),temp.getY()+(yDir*5));
        for (int i = SIZE-1;i>=1;i--)
        {
            objectPoint.set(i,objectPoint.get(i-1));
        }
        objectPoint.set(0,newPoint);
    }

    void stopMove() {
        xDir=0;
        yDir=0;
    }

    void changeDir()
    {
        int x = getPointX();
        int y = getPointY();

        if (x < 0 || x > 590)
        {
            xDir=-xDir;
            colourChanged = true;
        }

        else if (y < 0 || y > 590)
        {
            yDir=-yDir;
            colourChanged = true;
        }
    }

    int getPointX()
    {
        return objectPoint.get(0).getX();
    }

    int getPointY()
    {
        return objectPoint.get(0).getY();
    }

    private Color getRandomColour()
    {
        int red = (int)(Math.random()*256);
        int blue = (int)(Math.random()*256);
        int green = (int)(Math.random()*256);
        return new Color(red,green,blue);
    }

    void setXDir(int x)
    {
        xDir=x;
    }

    void setYDir(int y)
    {
        yDir=y;
    }

    boolean isNear(MouseEvent e)
    {
        return (
                ((e.getX()>=(getPointX()-10)) && (e.getX()<=(getPointX()+20))) &&
                ((e.getY()>=(getPointY()-10)) && (e.getY()<=(getPointY()+20)))
                );
    }
}