package ojam.visualapp;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by ojam1 on 04/06/2017.
 */

public class AppAdapter implements MouseMotionListener
{
    AppAdapter()
    {
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        for (AppObject o : MainWindow.allObjects)
        {
            if (o.isNear(e))
            {
                o.stopMove();
                continue;
            }
            if (o.getPointX() < e.getX() && o.getPointY() < e.getY())
            {
                    o.setXDir(1);
                    o.setYDir(1);
            } else if (o.getPointX() < e.getX() && o.getPointY() > e.getY())
            {
                    o.setXDir(1);
                    o.setYDir(-1);
            } else if (o.getPointX() > e.getX() && o.getPointY() < e.getY())
            {
                    o.setXDir(-1);
                    o.setYDir(1);
            } else if (o.getPointX() > e.getX() && o.getPointY() > e.getY())
            {
                    o.setXDir(-1);
                    o.setYDir(-1);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        for (AppObject o : MainWindow.allObjects)
        {
            if (o.isNear(e))
            {
                o.setXDir(getRandomDir());
                o.setYDir(getRandomDir());
            }
        }
    }

    private int getRandomDir()
    {
        double x = (Math.random()*2)-1;
        if(x < 0)
        {
            return -1;
        } else return 1;
    }
}