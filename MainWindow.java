package ojam.visualapp;

import java.applet.Applet;
import java.awt.*;
import java.util.ArrayList;
/**
 * Created by ojam1 on 06/05/2017.
 */

public class MainWindow extends Applet implements Runnable
{
    private Thread thread;
    static ArrayList<AppObject> allObjects;

    @Override
    public void init()
    {
        setSize(new Dimension(600, 600));
        setBackground(Color.BLACK);
        allObjects = new ArrayList<>();
        allObjects.add(new AppObject());
        allObjects.add(new AppObject());
        allObjects.add(new AppObject());
        allObjects.add(new AppObject());
        allObjects.add(new AppObject());
        allObjects.add(new AppObject());
        addMouseMotionListener(new AppAdapter());
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void paint(Graphics g)
    {
        for (AppObject o : allObjects)
        {
            o.draw(g);
        }
    }

    @Override
    public void run()
    {
        for (; ; )
        {
            for (AppObject o : allObjects)
            {
                o.move();
                o.changeDir();
            }
            repaint();
            try
            {
                thread.sleep(50);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}