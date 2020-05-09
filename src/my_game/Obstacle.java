package my_game;

/**
 *obstacle class contains obstacle image and dimension
 */

import java.awt.*;
import javax.swing.ImageIcon;
 
public class Obstacle {
 
        Image snowman;
        int x, y;
        boolean isAlive = true;
       
        public Obstacle(int X, int Y, String loc)
        {
                x = X;
                y = Y;
                ImageIcon i = new ImageIcon(loc);
                snowman = i.getImage();
        }
       
        public int getX()
        {
                return x;
        }
        public int getY()
        {
                return y;
        }
        public boolean Alive()
        {
                return isAlive;
        }
        public Image getImage()
        {
                return snowman;
        }
       
        public void move(int dx){
       
                x -= dx;
        }
 
        public Rectangle getBounds()
        {
                return new Rectangle(x ,y ,30, 30); /// obstacle image smaller dimension.otherwise collision 
                                                     ///problem occurs
        }
       
}
