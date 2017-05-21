package ojam.visualapp;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 04/05/2017.
 */
public class Object
{
    private List<OPoint> objectPoint;
    private int xDir;
    private int yDir;
    final int SIZE = 25;
    private boolean colourChanged;
    private Color colour;

    public Object()
    {
      objectPoint = new ArrayList<>();
      colourChanged = false;
      this.colour = Color.WHITE;
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

    public void draw(Graphics g)
    {
        if(colourChanged)
        {
            this.colour = (getRandomColour());
            colourChanged = false;
        }
        else g.setColor(this.colour);
        for(OPoint p: objectPoint)
        {
            g.fillOval(p.getX(),p.getY(),10,10);
        }
    }

    public void move()
    {
        OPoint temp = objectPoint.get(0);
        OPoint newPoint = new OPoint(temp.getX()+(xDir*5),temp.getY()+(yDir*5));
        for (int i = SIZE-1;i>=1;i--)
        {
            objectPoint.set(i,objectPoint.get(i-1));
        }
        objectPoint.set(0,newPoint);
    }

    public void stopMove() {
        xDir=0;
        yDir=0;
    }

    public void changeDir() {

        int x = getPointX();
        int y = getPointY();

        if (x < 0 || x > 590) {
            xDir=-xDir;
            colourChanged = true;
        }

        else if (y < 0 || y > 590) {
            yDir=-yDir;
            colourChanged = true;
        }
    }

    public int getPointX()
    {
        return objectPoint.get(0).getX();
    }

    public int getPointY()
    {
        return objectPoint.get(0).getY();
    }

    public Color getRandomColour()
    {
        int red = (int)(Math.random()*256);
        int blue = (int)(Math.random()*256);
        int green = (int)(Math.random()*256);
        return new Color(red,green,blue);
    }

    public void setxDir(int x) {
        xDir=x;
    }

    public void setyDir(int y) {
        yDir=y;
    }
}
