package my_game;

/**
 *when the 5 lives are lost this over window
 * appears and the game stops 
 * for play again we have to exit the game and start over
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Over{
  
    public static void render(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        
        Font fnt = new Font("Comic Sans MS", Font.BOLD, 45);//font of the options
        
        g.setColor(Color.RED);// background color of the buttons
        
       
        g.setFont(fnt);
        g.setColor(Color.RED);
        
        g.drawString("GAME OVER", 380 + 8, 10 + 45);
        
        g.setColor(Color.green);
        
        g2d.drawString("Your Score: " + GameWindow.SCORE , 360, 110);
            

    }

   
    
}
