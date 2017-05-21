package ojam.visualapp;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
/**
 * Created by Oliver on 06/05/2017.
 */
public class MainWindow extends Applet implements Runnable
{
    Thread thread;
    ArrayList<Object> allObjects;
    Object object1, object2, object3, object4, object5, object6;

    @Override
    public void init() {
        setSize(new Dimension(600, 600));
        setBackground(Color.BLACK);
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                for (Object o : allObjects)
                {
                    while (!((  ((e.getX()>=o.getPointX()) &&

                            (e.getX()<=(o.getPointX()+20))) &&

                            ((e.getY()>=o.getPointY()) &&

                                    (e.getY()<=(o.getPointY()+20)))



                    )))
                    {if (  ((e.getX()>=(o.getPointX()-10)) &&

                            (e.getX()<=(o.getPointX()+20))) &&

                            ((e.getY()>=(o.getPointY()-10)) &&

                                    (e.getY()<=(o.getPointY()+20)))



                            )
                    {

                        o.stopMove();
                        break;
                    }
                        if (o.getPointX() < e.getX() && o.getPointY() < e.getY()) {
                            o.setxDir(1);
                            o.setyDir(1);
                        } else if (o.getPointX() < e.getX() && o.getPointY() > e.getY()) {
                            o.setxDir(1);
                            o.setyDir(-1);
                        } else if (o.getPointX() > e.getX() && o.getPointY() < e.getY()) {
                            o.setxDir(-1);
                            o.setyDir(1);
                        } else if (o.getPointX() > e.getX() && o.getPointY() > e.getY()) {
                            o.setxDir(-1);
                            o.setyDir(-1);
                        }
                        return;
                    }

                }
            }
        });
        allObjects = new ArrayList<>();
        allObjects.add(object1 = new Object());
        allObjects.add(object2 = new Object());
        allObjects.add(object3 = new Object());
        allObjects.add(object4 = new Object());
        allObjects.add(object5 = new Object());
        allObjects.add(object6 = new Object());
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void paint(Graphics g) {
        for (Object o : allObjects) {
            o.draw(g);
        }
    }

    @Override
    public void run() {
        for (; ; ) {
            for (Object o : allObjects) {
                o.move();
                o.changeDir();
            }
            repaint();
            try {
                thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}








